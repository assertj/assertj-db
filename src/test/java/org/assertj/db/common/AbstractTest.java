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
package org.assertj.db.common;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.DbSetupTracker;
import com.ninja_squad.dbsetup.destination.DriverManagerDestination;
import com.ninja_squad.dbsetup.operation.Operation;
import org.assertj.db.configuration.TestsConfiguration;
import org.assertj.db.type.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.List;
import java.util.logging.Logger;

import static com.ninja_squad.dbsetup.Operations.*;

/**
 * Parent for all the tests. It contains the variables like a {@code DataSource} and a {@code Source}.
 *
 * @author Régis Pouiller
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestsConfiguration.class})
@Transactional
public abstract class AbstractTest {

  protected static final Logger LOG = Logger.getLogger("Test");

  @Rule
  public TestName testNameRule = new TestName();

  @Autowired(required = true)
  protected DataSource dataSource;

  protected final Source source = new Source("jdbc:h2:mem:test", "sa", "");

  private static final DbSetupTracker dbSetupTracker = new DbSetupTracker();

  private static final Operation DELETE_ALL = deleteAllFrom("test3", "test2", "test", "interpretation", "actor", "movie");

  private static final Operation INSERT_MOVIE = sequenceOf(insertInto("movie").columns("id", "title", "year", "movie_imdb")
                                                                              .values(1, "Alien", 1979, "30B443AE-C0C9-4790-9BEC-CE1380808435")
                                                                              .values(2, "The Village", 2004, "16319617-AE95-4087-9264-D3D21BF611B6")
                                                                              .values(3, "Avatar", 2009, "D735221B-5DE5-4112-AA1E-49090CB75ADA").build());

  private static final Operation INSERT_ACTOR = insertInto("actor").columns("id", "name", "firstname", "birth", "actor_imdb")
                                                                   .values(1, "Weaver", "Sigourney", Date.valueOf("1949-10-08"),"30B443AE-C0C9-4790-9BEC-CE1380808435")
                                                                   .values(2, "Phoenix", "Joaquim", Date.valueOf("1974-10-28"), "16319617-AE95-4087-9264-D3D21BF611B6" )
                                                                   .values(3, "Worthington", "Sam", Date.valueOf("1976-08-02"), "D735221B-5DE5-4112-AA1E-49090CB75ADA" )
                                                                   .build();

  private static final Operation INSERT_INTERPRETATION = insertInto("interpretation")
          .columns("id", "id_movie", "id_actor", "character")
          .values(1, 1, 1, "Ellen Louise Ripley")
          .values(2, 2, 1, "Alice Hunt")
          .values(3, 3, 1, "Dr Grace Augustine")
          .values(4, 2, 2, "Lucius Hunt")
          .values(5, 3, 3, "Jake Sully")
          .build();

  private static final Operation INSERT_TEST = insertInto("test")
          .columns("var1", "var2", "var3", "var4", "var5", "var6", "var7", "var8", "var9", "var10", "var11", "var12",
                   "var13", "var14", "var15")
          .values(1, true, 2, 3, 4, 5.6, 7.8, Time.valueOf("09:46:30"), Date.valueOf("2014-05-24"),
                  Timestamp.valueOf("2014-05-24 09:46:30"), new byte[0], "text", 5, 7, "30B443AE-C0C9-4790-9BEC-CE1380808435")

          .values(10, false, 20, 30, 40, 50.6, 70.8, Time.valueOf("12:29:49"), Date.valueOf("2014-05-30"),
                  Timestamp.valueOf("2014-05-30 12:29:49"), new byte[0], "another text", 50, 70, "0E2A1269-EFF0-4233-B87B-B53E8B6F164D")

          .values(100, false, 25, 300, 400, 500.6, 700.8, Time.valueOf("12:29:49"), Date.valueOf("2014-05-30"),
                  Timestamp.valueOf("2014-05-30 00:00:00"), new byte[0], "another text again", 500, 700, "2B0D1BDD-909E-4362-BA10-C930BA82718D")

          .values(1000, false, 0, 0, 0, 0, 0, Time.valueOf("12:29:49"), Date.valueOf("2014-05-30"),
                  Timestamp.valueOf("2014-05-30 00:00:00"), new byte[0], "another text again", 500, 700, "399FFFCA-7874-4225-9903-E227C4E9DCC1")
          .build();

  private static final Operation INSERT_TEST2 = insertInto("test2")
          .columns("var1", "var2", "var3", "var4", "var5", "var6", "var7", "var8", "var9", "var10", "var11", "var12",
                   "var13", "var14", "var15", "var16")
          .values(1, true, 2, 3, 4, 5.6, 7.8, Time.valueOf("09:46:30"), Date.valueOf("2014-05-24"),
                  Timestamp.valueOf("2014-05-24 09:46:30"), new byte[0], "text", 5, 7, null, "30B443AE-C0C9-4790-9BEC-CE1380808435")
          .values(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null).build();

  private static final Operation INSERT_TEST3 = insertInto("test3")
          .columns("var1", "var2", "var3", "var4", "var5", "var6", "var7", "var8", "var9", "var10",
                   "var11", "var12", "var13", "var14", "var15", "var16", "var17", "var18", "var19", "var20",
                   "var21", "var22", "var23", "var24", "var25", "var26", "var27", "var28", "var29", "var30",
                   "var31", "var32", "var33", "var34", "var35", "var36", "var37", "var38", "var39", "var40",
                   "var41", "var42", "var43", "var44", "var45", "var46", "var47", "var48", "var49", "var50",
                   "var51", "var52", "var53", "var54", "var55", "var56", "var57", "var58", "var59", "var60",
                   "var61")
          .values(1, 2, 3, 4, 5, 6, true, false, true, 7,
                  8, 9, 10, 11, 12, 13.13, 14.14, 15.15, 16.16, 17.17,
                  18.18, 19.19, 20.20, 21.21, Time.valueOf("09:01:00"),
                  Date.valueOf("2007-12-23"), Timestamp.valueOf("2007-12-23 09:01:00"), Timestamp.valueOf("2007-12-23 09:01:00"), Timestamp.valueOf("2007-12-23 09:01:00"), null,
                  null, null, null, null, null, "22", "23", "24", "25", "26",
                  "27", "28", "29", "30", "31", null, null, null, null, null,
                  null,

                  // TODO Implements CLOB (for the moment commit insertion of null otherwise a lot of tests are broken)
                  null, null, null, null, null, null, null,
//                  "32", "33", "34", "35", "36", "37", "38",

                  "30B443AE-C0C9-4790-9BEC-CE1380808435", null,
                  null).build();

  private static final Operation SQL = sql(
          "update test set var11 = FILE_READ('classpath:h2-logo-2.png') where var1 = 1",
          "update test set var11 = FILE_READ('classpath:logo-dev.jpg') where var1 = 10",
          "update test set var11 = FILE_READ('classpath:logo-dev.jpg') where var1 = 100",
          "update test set var11 = FILE_READ('classpath:logo-dev.jpg') where var1 = 1000",
          "update test2 set var11 = FILE_READ('classpath:h2-logo-2.png') where var1 = 1",
          "update test3 set var30 = FILE_READ('classpath:h2-logo-2.png') where var1 = 1",
          "update test3 set var31 = FILE_READ('classpath:h2-logo-2.png') where var1 = 1",
          "update test3 set var32 = FILE_READ('classpath:h2-logo-2.png') where var1 = 1",
          "update test3 set var33 = FILE_READ('classpath:h2-logo-2.png') where var1 = 1",
          "update test3 set var34 = FILE_READ('classpath:h2-logo-2.png') where var1 = 1"
          // TODO Make this update with SQL type OTHER working
//          "update test3 set var35 = FILE_READ('classpath:h2-logo-2.png') where var1 = 1",
          // TODO Implement SQL type BLOB
//          "update test3 set var46 = FILE_READ('classpath:h2-logo-2.png') where var1 = 1",
          // TODO Implement SQL type TINYBLOB
//          "update test3 set var47 = FILE_READ('classpath:h2-logo-2.png') where var1 = 1",
          // TODO Implement SQL type MEDIUMBLOB
//          "update test3 set var48 = FILE_READ('classpath:h2-logo-2.png') where var1 = 1",
          // TODO Implement SQL type LONGBLOB
//          "update test3 set var49 = FILE_READ('classpath:h2-logo-2.png') where var1 = 1",
          // TODO Implement SQL type IMAGE
//          "update test3 set var50 = FILE_READ('classpath:h2-logo-2.png') where var1 = 1",
          // TODO Implement SQL type OID
//          "update test3 set var51 = FILE_READ('classpath:h2-logo-2.png') where var1 = 1"
  );

  private static final Operation OPERATIONS = sequenceOf(DELETE_ALL, INSERT_MOVIE, INSERT_ACTOR, INSERT_INTERPRETATION,
                                                         INSERT_TEST, INSERT_TEST2, INSERT_TEST3, SQL);

  private static final DbSetup DB_SETUP = new DbSetup(new DriverManagerDestination("jdbc:h2:mem:test", "SA", ""), OPERATIONS);

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
   * Returns an instance of a {@code Row}.
   *
   * @param pksNameList The list of the primary keys name.
   * @param columnsNameList The list of the columns name.
   * @param valuesList The values in the row.
   * @return An instance.
   * @throws Exception Exception
   */
  protected static Row getRow(List<String> pksNameList, List<String> columnsNameList, List<?> valuesList)
          throws Exception {
    Constructor<Row> constructor = Row.class.getDeclaredConstructor(List.class, List.class, List.class);
    constructor.setAccessible(true);
    return constructor.newInstance(pksNameList, columnsNameList, valuesList);
  }

  /**
   * Returns an instance of a {@code Changes}.
   *
   * @param changesList The list of changes.
   * @return An instance.
   * @throws Exception Exception
   */
  protected static Changes getChanges(List<Change> changesList) throws Exception {
    Constructor<Changes> constructor = Changes.class.getDeclaredConstructor();
    constructor.setAccessible(true);
    Changes changes = constructor.newInstance();
    Field field = Changes.class.getDeclaredField("changesList");
    field.setAccessible(true);
    field.set(changes, changesList);
    return changes;
  }

  /**
   * Returns an instance of a {@code Change}.
   *
   * @param dataType The type of the data on which is the change.
   * @param dataName The name of the data on which is the change.
   * @param changeType The type of the change.
   * @param rowAtStartPoint The row at start point.
   * @param rowAtEndPoint The row at end point.
   * @return An instance.
   * @throws Exception Exception
   */
  protected static Change getChange(DataType dataType, String dataName, ChangeType changeType, Row rowAtStartPoint, Row rowAtEndPoint)
          throws Exception {
    Constructor<Change> constructor = Change.class.getDeclaredConstructor(DataType.class, String.class, ChangeType.class, Row.class, Row.class);
    constructor.setAccessible(true);
    return constructor.newInstance(dataType, dataName, changeType, rowAtStartPoint, rowAtEndPoint);
  }

  /**
   * Returns an instance of a {@code Change} for creation on a table.
   *
   * @param dataName The name of the data on which is the change.
   * @param rowAtEndPoint The row at end point.
   * @return An instance.
   * @throws Exception Exception
   */
  protected static Change getTableCreationChange(String dataName, Row rowAtEndPoint) throws Exception {
    return getChange(DataType.TABLE, dataName, ChangeType.CREATION, null, rowAtEndPoint);
  }

  /**
   * Returns an instance of a {@code Change} for creation on a table.
   *
   * @param dataName The name of the data on which is the change.
   * @param rowAtStartPoint The row at start point.
   * @param rowAtEndPoint The row at end point.
   * @return An instance.
   * @throws Exception Exception
   */
  protected static Change getTableModificationChange(String dataName, Row rowAtStartPoint, Row rowAtEndPoint) throws Exception {
    return getChange(DataType.TABLE, dataName, ChangeType.MODIFICATION, rowAtStartPoint, rowAtEndPoint);
  }

  /**
   * Returns an instance of a {@code Change} for deletion on a table.
   *
   * @param dataName The name of the data on which is the change.
   * @param rowAtStartPoint The row at start point.
   * @return An instance.
   * @throws Exception Exception
   */
  protected static Change getTableDeletionChange(String dataName, Row rowAtStartPoint) throws Exception {
    return getChange(DataType.TABLE, dataName, ChangeType.DELETION, rowAtStartPoint, null);
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

  /**
   * Update the database for tests.
   */
  protected void updateChangesForTests() {
    update("insert into movie values(4, 'Ghostbusters', 1984, '30B443AE-C0C9-4790-9BEC-CE1380808435')");
    update("insert into actor values(4, 'Murray', 'Bill', PARSEDATETIME('21/09/1950', 'dd/MM/yyyy'), '30B443AE-C0C9-4790-9BEC-CE1380808435')");
    update("insert into interpretation values(6, 4, 4, 'Dr Peter Venkman')");

    update("delete from interpretation where id = 5");
    update("delete from actor where id = 3");

    update("update movie set title = 'The Avatar' where id = 3");
    update("update actor set firstname = 'Susan Alexandra' where id = 1");
    update("update interpretation set character = 'Doctor Grace Augustine' where id = 3");
  }
}
