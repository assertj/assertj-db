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
 * Copyright 2015-2021 the original author or authors.
 */
package org.assertj.db.database.h2;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.DbSetupTracker;
import com.ninja_squad.dbsetup.destination.DriverManagerDestination;
import com.ninja_squad.dbsetup.operation.Operation;
import org.assertj.db.database.AbstractDatabaseTest;
import org.assertj.db.type.DataSourceWithLetterCase;
import org.assertj.db.type.SourceWithLetterCase;
import org.assertj.db.type.lettercase.LetterCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Locale;

import static com.ninja_squad.dbsetup.Operations.*;

/**
 * Parent for all the tests which are specific for H2 database.
 *
 * @author Régis Pouiller
 *
 */
@ContextConfiguration(classes = { H2Configuration.class })
public abstract class AbstractH2Test extends AbstractDatabaseTest {

  protected DataSource dataSource;
  protected DataSourceWithLetterCase dataSourceDDD;
  protected DataSourceWithLetterCase dataSourceUIUIUI;
  protected DataSourceWithLetterCase dataSourceNSNSNS;

  protected final SourceWithLetterCase sourceDDD = new SourceWithLetterCase("jdbc:h2:mem:testH2", "sa", "",
                                                                            LetterCase.TABLE_DEFAULT,
                                                                            LetterCase.COLUMN_DEFAULT,
                                                                            LetterCase.PRIMARY_KEY_DEFAULT);
  protected final SourceWithLetterCase sourceUIUIUI = new SourceWithLetterCase("jdbc:h2:mem:testH2", "sa", "",
                                                                               letterCaseUI,
                                                                               letterCaseUI,
                                                                               letterCaseUI);
  protected final SourceWithLetterCase sourceNSNSNS = new SourceWithLetterCase("jdbc:h2:mem:testH2", "sa", "",
                                                                               letterCaseNS,
                                                                               letterCaseNS,
                                                                               letterCaseNS);

  private static final Operation DELETE_ALL = deleteAllFrom("teSt");

  private static final Operation INSERT_TEST = insertInto("teSt")
      .columns("Var1", "vAr2", "vaR3", "var4", "var5", "var6", "var7", "var8", "var9", "var10",
               "var11", "var12", "var13", "var14", "var15", "var16", "var17", "var18", "var19", "var20",
               "var21", "var22", "var23", "var24", "var25", "var26", "var27", "var28", "var29", "var30",
               "var31", "var32", "var33", "var34", "var35", "var36", "var37", "var38", "var39", "var40",
               "var41", "var42", "var43", "var44", "var45", "var46", "var47", "var48", "var49", "var50",
               "var51", "var52", "var53", "var54", "var55", "var56", "var57", "var58", "var59", "var60",
               "var61")
      .values(1, 2, 3, 4, 5, 6, true, false, true, 7,
              8, 9, 10, 11, 12, 13.13, 14.14, 15.15, 16.16, 17.17,
              18.18, 19.19, 20.20, 21.21, Time.valueOf("09:01:00"),
              Date.valueOf("2007-12-23"), Timestamp.valueOf("2007-12-23 09:01:00"),
              Timestamp.valueOf("2007-12-23 09:01:00"), Timestamp.valueOf("2007-12-23 09:01:00"), null,
              null, null, null, null, Locale.FRENCH, "22", "23", "24", "25", "26",
              "27", "28", "29", "30", "31", null, null, null, null, null,
              null, "32", "33", "34", "35", "36", "37", "38", "30B443AE-C0C9-4790-9BEC-CE1380808435", null,
              null).build();

  private static final Operation SQL = sql(
      "update test set var30 = FILE_READ('classpath:h2-logo-2.png') where Var1 = 1",
      "update test set var31 = FILE_READ('classpath:h2-logo-2.png') where Var1 = 1",
      "update test set var32 = FILE_READ('classpath:h2-logo-2.png') where Var1 = 1",
      "update test set var33 = FILE_READ('classpath:h2-logo-2.png') where Var1 = 1",
      "update test set var34 = FILE_READ('classpath:h2-logo-2.png') where Var1 = 1",
      "update test set var46 = FILE_READ('classpath:h2-logo-2.png') where Var1 = 1",
      "update test set var47 = FILE_READ('classpath:h2-logo-2.png') where Var1 = 1",
      "update test set var48 = FILE_READ('classpath:h2-logo-2.png') where Var1 = 1",
      "update test set var49 = FILE_READ('classpath:h2-logo-2.png') where Var1 = 1",
      "update test set var50 = FILE_READ('classpath:h2-logo-2.png') where Var1 = 1",
      "update test set var51 = FILE_READ('classpath:h2-logo-2.png') where Var1 = 1"
  );

  private static final Operation OPERATIONS = sequenceOf(DELETE_ALL, INSERT_TEST, SQL);

  private static final DbSetup DB_SETUP = new DbSetup(new DriverManagerDestination("jdbc:h2:mem:testH2", "SA", ""), OPERATIONS);
  private static final DbSetupTracker DB_SETUP_TRACKER = new DbSetupTracker();

  protected DbSetup getDbSetup() {
    return DB_SETUP;
  }

  protected DbSetupTracker getDbSetupTracker() {
    return DB_SETUP_TRACKER;
  }

  @Autowired
  protected void setDataSource(DataSource dataSource) {
    this.dataSource = dataSource;
    this.dataSourceDDD = new DataSourceWithLetterCase(dataSource, LetterCase.TABLE_DEFAULT,
                                                      LetterCase.COLUMN_DEFAULT,
                                                      LetterCase.PRIMARY_KEY_DEFAULT);
    this.dataSourceUIUIUI = new DataSourceWithLetterCase(dataSource, letterCaseUI, letterCaseUI, letterCaseUI);
    this.dataSourceNSNSNS = new DataSourceWithLetterCase(dataSource, letterCaseNS, letterCaseNS, letterCaseNS);
  }

  protected void update() {
    update("update teSt set vAr2=20");
  }
}
