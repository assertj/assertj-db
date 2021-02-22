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

import static com.ninja_squad.dbsetup.Operations.*;

/**
 * Parent for all the tests which are specific for H2 database with reserved names in SQL structure.
 *
 * @author Régis Pouiller
 *
 */
@ContextConfiguration(classes = { ReservedH2Configuration.class })
public abstract class AbstractReservedH2Test extends AbstractDatabaseTest {

  protected DataSource dataSource;
  protected DataSourceWithLetterCase dataSourceDDD;
  protected DataSourceWithLetterCase dataSourceUIUIUI;
  protected DataSourceWithLetterCase dataSourceNSNSNS;

  protected final SourceWithLetterCase sourceDDD = new SourceWithLetterCase("jdbc:h2:mem:testReservedH2", "sa", "",
                                                                            LetterCase.TABLE_DEFAULT,
                                                                            LetterCase.COLUMN_DEFAULT,
                                                                            LetterCase.PRIMARY_KEY_DEFAULT);
  protected final SourceWithLetterCase sourceUIUIUI = new SourceWithLetterCase("jdbc:h2:mem:testReservedH2", "sa", "",
                                                                               letterCaseUI,
                                                                               letterCaseUI,
                                                                               letterCaseUI);
  protected final SourceWithLetterCase sourceNSNSNS = new SourceWithLetterCase("jdbc:h2:mem:testReservedH2", "sa", "",
                                                                               letterCaseNS,
                                                                               letterCaseNS,
                                                                               letterCaseNS);

  private static final Operation DELETE_ALL = deleteAllFrom("`group`", "`Two Words`");

  private static final Operation INSERT_TEST = insertInto("`group`")
      .columns("read", "by", "`select`", "`from`", "`where`", "`order`")
      .values(1, 2, 3, 4, '5', '6').build();

  private static final Operation INSERT_TWO_WORDS = insertInto("`Two Words`")
      .columns("`Primary Key`", "`Column Name`", "`Test%Test`")
      .values(1, "Nom 1", "Test 1")
      .values(2, "Nom 2", "Test 2").build();

  private static final Operation OPERATIONS = sequenceOf(DELETE_ALL, INSERT_TEST, INSERT_TWO_WORDS);

  private static final DbSetup DB_SETUP = new DbSetup(new DriverManagerDestination("jdbc:h2:mem:testReservedH2", "SA", ""),
                                                      OPERATIONS);
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
    update("update `group` set `select`=20");
    update("update `Two Words` set `Test%Test`= 'Test 3' where `Primary Key` = 2");
  }

}
