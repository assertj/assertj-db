/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2015 the original author or authors.
 */
package org.assertj.db.database;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.DbSetupTracker;
import org.assertj.db.common.NeedReload;
import org.assertj.db.type.lettercase.CaseComparisons;
import org.assertj.db.type.lettercase.CaseConversions;
import org.assertj.db.type.lettercase.LetterCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.logging.Logger;

import static org.assertj.db.type.lettercase.LetterCase.getLetterCase;

/**
 * Parent for all the tests which are specific for databases.
 *
 * @author Régis Pouiller
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public abstract class AbstractDatabaseTest {

  protected final LetterCase letterCaseUI = getLetterCase(CaseConversions.UPPER, CaseComparisons.IGNORE);
  protected final LetterCase letterCaseUS = getLetterCase(CaseConversions.UPPER, CaseComparisons.STRICT);
  protected final LetterCase letterCaseLI = getLetterCase(CaseConversions.LOWER, CaseComparisons.IGNORE);
  protected final LetterCase letterCaseLS = getLetterCase(CaseConversions.LOWER, CaseComparisons.STRICT);
  protected final LetterCase letterCaseNI = getLetterCase(CaseConversions.NO, CaseComparisons.IGNORE);
  protected final LetterCase letterCaseNS = getLetterCase(CaseConversions.NO, CaseComparisons.STRICT);

  protected static final Logger LOG = Logger.getLogger("Test");

  private static final DbSetupTracker dbSetupTracker = new DbSetupTracker();

  @Rule
  public TestName testNameRule = new TestName();

  @Autowired(required = true)
  protected DataSource dataSource;

  protected static DbSetup DB_SETUP = null;

  @Before
  public void initiate() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException,
          SecurityException {

    Field fieldLastSetup = DbSetupTracker.class.getDeclaredField("lastSetupLaunched");
    Field fieldNextLaunch = DbSetupTracker.class.getDeclaredField("nextLaunchSkipped");
    fieldLastSetup.setAccessible(true);
    fieldNextLaunch.setAccessible(true);
    Boolean nextLaunchSkipped = fieldNextLaunch.getBoolean(dbSetupTracker);
    DbSetup lastSetupLaunched = (DbSetup) fieldLastSetup.get(dbSetupTracker);
    boolean skipLaunch = nextLaunchSkipped && DB_SETUP.equals(lastSetupLaunched);
    LOG.warning("--------------------------------------------------");
    LOG.warning(getClass().getCanonicalName() + " - " + testNameRule.getMethodName() + " - skipLaunch : " + skipLaunch
                + " (" + nextLaunchSkipped + " && " + DB_SETUP.equals(lastSetupLaunched) + ")");
    LOG.warning("--------------------------------------------------");
    dbSetupTracker.launchIfNecessary(DB_SETUP);
  }

  @After
  public void determineIfReloadIsNeeded() throws NoSuchMethodException, SecurityException {
    Annotation classAnnotation = getClass().getAnnotation(NeedReload.class);
    if (classAnnotation != null) {
      return;
    }
    String methodName = testNameRule.getMethodName();
    Method method = getClass().getDeclaredMethod(methodName);
    Annotation methodAnnotation = method.getAnnotation(NeedReload.class);
    if (methodAnnotation != null) {
      return;
    }
    dbSetupTracker.skipNextLaunch();
  }

  /**
   * Update the database.
   *
   * @param request Request to update.
   * @param parameters The parameters of the request.
   */
  protected void update(String request, Object... parameters) {
    try (Connection connection = dataSource.getConnection()) {
      try (PreparedStatement statement = connection.prepareStatement(request)) {
        for (int i = 0; i < parameters.length; i++) {
          statement.setObject(i + 1, parameters[i]);
        }
        statement.executeUpdate();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
