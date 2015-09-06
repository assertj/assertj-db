package org.assertj.db.database.h2;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.DbSetupTracker;
import com.ninja_squad.dbsetup.destination.DriverManagerDestination;
import com.ninja_squad.dbsetup.operation.Operation;
import org.assertj.db.common.NeedReload;
import org.assertj.db.configuration.TestsConfiguration;
import org.assertj.db.type.Source;
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
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.logging.Logger;

import static com.ninja_squad.dbsetup.Operations.*;

/**
 * Parent for all the tests which are specific for H2 database.
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

  private static final Operation DELETE_ALL = deleteAllFrom("test");

  private static final Operation INSERT_TEST = insertInto("test")
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
          "update test set var30 = FILE_READ('classpath:h2-logo-2.png') where var1 = 1",
          "update test set var31 = FILE_READ('classpath:h2-logo-2.png') where var1 = 1",
          "update test set var32 = FILE_READ('classpath:h2-logo-2.png') where var1 = 1",
          "update test set var33 = FILE_READ('classpath:h2-logo-2.png') where var1 = 1",
          "update test set var34 = FILE_READ('classpath:h2-logo-2.png') where var1 = 1"
          // TODO Make this update with SQL type OTHER working
//          "update test set var35 = FILE_READ('classpath:h2-logo-2.png') where var1 = 1",
          // TODO Implement SQL type BLOB
//          "update test set var46 = FILE_READ('classpath:h2-logo-2.png') where var1 = 1",
          // TODO Implement SQL type TINYBLOB
//          "update test set var47 = FILE_READ('classpath:h2-logo-2.png') where var1 = 1",
          // TODO Implement SQL type MEDIUMBLOB
//          "update test set var48 = FILE_READ('classpath:h2-logo-2.png') where var1 = 1",
          // TODO Implement SQL type LONGBLOB
//          "update test set var49 = FILE_READ('classpath:h2-logo-2.png') where var1 = 1",
          // TODO Implement SQL type IMAGE
//          "update test set var50 = FILE_READ('classpath:h2-logo-2.png') where var1 = 1",
          // TODO Implement SQL type OID
//          "update test set var51 = FILE_READ('classpath:h2-logo-2.png') where var1 = 1"
  );

  private static final Operation OPERATIONS = sequenceOf(DELETE_ALL, INSERT_TEST, SQL);

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
