/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2015-2024 the original author or authors.
 */
package org.assertj.db.database.sqlite;

import static com.ninja_squad.dbsetup.Operations.deleteAllFrom;
import static com.ninja_squad.dbsetup.Operations.insertInto;
import static com.ninja_squad.dbsetup.Operations.sequenceOf;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.logging.Level;
import javax.sql.DataSource;

import org.assertj.db.database.AbstractDatabaseTest;
import org.assertj.db.type.AssertDbConnection;
import org.assertj.db.type.AssertDbConnectionFactory;
import org.assertj.db.type.lettercase.LetterCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.DbSetupTracker;
import com.ninja_squad.dbsetup.destination.DriverManagerDestination;
import com.ninja_squad.dbsetup.operation.Operation;

/**
 * Parent for all the tests which are specific for Derby database.
 *
 * @author Régis Pouiller
 * @author Julien Roy
 */
@ContextConfiguration(classes = {SqliteConfiguration.class})
public abstract class AbstractSqliteTest extends AbstractDatabaseTest {

  private static final Operation DELETE_ALL = deleteAllFrom("teSt");
  private static final DbSetupTracker DB_SETUP_TRACKER = new DbSetupTracker();
  private static byte[] BYTES;

  static {
    try {
      InputStream inputStream = AbstractDatabaseTest.class.getResourceAsStream("/h2-logo-2.png");
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      byte[] bytes = new byte[1024];
      int nb = inputStream.read(bytes);
      while (nb > 0) {
        byteArrayOutputStream.write(bytes, 0, nb);
        nb = inputStream.read(bytes);
      }
      BYTES = byteArrayOutputStream.toByteArray();
    } catch (Exception e) {
      LOG.log(Level.SEVERE, "Error during loading bytes sample data", e);
    }
  }

  private static final Operation INSERT_TEST = insertInto("teSt")
    .columns("Var1", "vAr2", "vaR3", "var4", "var5", "var6", "var7", "var8", "var9", "var10",
      "var11", "var12", "var13", "var14", "var15", "var16", "var17", "var18", "var19", "var20")
    .values(1, "13", "2", "14", "15",
      Timestamp.valueOf("2007-12-23 09:01:00"), 3.3, 4.4, 5.5, 6.6,
      7, "8", "16", 9, 10.1,
      11, Time.valueOf("09:01:00"), Timestamp.valueOf("2007-12-23 09:01:00"), "12", BYTES)
    .build();
  private static final Operation OPERATIONS = sequenceOf(DELETE_ALL, INSERT_TEST);
  private static final DbSetup DB_SETUP = new DbSetup(new DriverManagerDestination("jdbc:sqlite:target/testDerby.db", "", ""),
    OPERATIONS);

  protected final AssertDbConnection jdbcConnectionDDD = AssertDbConnectionFactory.of("jdbc:sqlite:target/testDerby.db", "", "").letterCase(
    LetterCase.TABLE_DEFAULT,
    LetterCase.COLUMN_DEFAULT,
    LetterCase.PRIMARY_KEY_DEFAULT).create();
  protected final AssertDbConnection jdbcConnectionUIUIUI = AssertDbConnectionFactory.of("jdbc:sqlite:target/testDerby.db", "", "").letterCase(
    letterCaseUI,
    letterCaseUI,
    letterCaseUI).create();
  protected final AssertDbConnection jdbcConnectionNSNSNS = AssertDbConnectionFactory.of("jdbc:sqlite:target/testDerby.db", "", "").letterCase(
    letterCaseNS,
    letterCaseNS,
    letterCaseNS).create();
  protected DataSource dataSource;
  protected AssertDbConnection dsConnectionDDD;
  protected AssertDbConnection dsConnectionUIUIUI;
  protected AssertDbConnection dsConnectionNSNSNS;

  protected DbSetup getDbSetup() {
    return DB_SETUP;
  }

  protected DbSetupTracker getDbSetupTracker() {
    return DB_SETUP_TRACKER;
  }

  @Autowired
  protected void setDataSource(DataSource dataSource) {
    this.dataSource = dataSource;
    this.dsConnectionDDD = AssertDbConnectionFactory.of(dataSource).letterCase(LetterCase.TABLE_DEFAULT, LetterCase.COLUMN_DEFAULT, LetterCase.PRIMARY_KEY_DEFAULT).create();
    this.dsConnectionUIUIUI = AssertDbConnectionFactory.of(dataSource).letterCase(letterCaseUI, letterCaseUI, letterCaseUI).create();
    this.dsConnectionNSNSNS = AssertDbConnectionFactory.of(dataSource).letterCase(letterCaseNS, letterCaseNS, letterCaseNS).create();
  }

  protected void update() {
    update("update test set var11=20");
  }
}
