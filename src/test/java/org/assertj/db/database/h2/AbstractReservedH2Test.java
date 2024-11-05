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
package org.assertj.db.database.h2;

import static com.ninja_squad.dbsetup.Operations.deleteAllFrom;
import static com.ninja_squad.dbsetup.Operations.insertInto;
import static com.ninja_squad.dbsetup.Operations.sequenceOf;

import javax.sql.DataSource;

import org.assertj.db.database.AbstractDatabaseTest;
import org.assertj.db.type.ConnectionProvider;
import org.assertj.db.type.ConnectionProviderFactory;
import org.assertj.db.type.lettercase.LetterCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.DbSetupTracker;
import com.ninja_squad.dbsetup.destination.DriverManagerDestination;
import com.ninja_squad.dbsetup.operation.Operation;

/**
 * Parent for all the tests which are specific for H2 database with reserved names in SQL structure.
 *
 * @author Régis Pouiller
 * @author Julien Roy
 */
@ContextConfiguration(classes = {ReservedH2Configuration.class})
public abstract class AbstractReservedH2Test extends AbstractDatabaseTest {

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
  protected final ConnectionProvider jdbcConnectionDDD = ConnectionProviderFactory.of("jdbc:h2:mem:testReservedH2", "sa", "").letterCase(
    LetterCase.TABLE_DEFAULT,
    LetterCase.COLUMN_DEFAULT,
    LetterCase.PRIMARY_KEY_DEFAULT).create();
  protected final ConnectionProvider jdbcConnectionUIUIUI = ConnectionProviderFactory.of("jdbc:h2:mem:testReservedH2", "sa", "").letterCase(
    letterCaseUI,
    letterCaseUI,
    letterCaseUI).create();
  protected final ConnectionProvider jdbcConnectionNSNSNS = ConnectionProviderFactory.of("jdbc:h2:mem:testReservedH2", "sa", "").letterCase(
    letterCaseNS,
    letterCaseNS,
    letterCaseNS).create();
  protected DataSource dataSource;
  protected ConnectionProvider dsConnectionDDD;
  protected ConnectionProvider dsConnectionUIUIUI;
  protected ConnectionProvider dsConnectionNSNSNS;

  protected DbSetup getDbSetup() {
    return DB_SETUP;
  }

  protected DbSetupTracker getDbSetupTracker() {
    return DB_SETUP_TRACKER;
  }

  @Autowired
  protected void setDataSource(DataSource dataSource) {
    this.dataSource = dataSource;
    this.dsConnectionDDD = ConnectionProviderFactory.of(dataSource).letterCase(LetterCase.TABLE_DEFAULT, LetterCase.COLUMN_DEFAULT, LetterCase.PRIMARY_KEY_DEFAULT).create();
    this.dsConnectionUIUIUI = ConnectionProviderFactory.of(dataSource).letterCase(letterCaseUI, letterCaseUI, letterCaseUI).create();
    this.dsConnectionNSNSNS = ConnectionProviderFactory.of(dataSource).letterCase(letterCaseNS, letterCaseNS, letterCaseNS).create();
  }

  protected void update() {
    update("update `group` set `select`=20");
    update("update `Two Words` set `Test%Test`= 'Test 3' where `Primary Key` = 2");
  }

}
