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
 * Copyright 2015-2020 the original author or authors.
 */
package org.assertj.db.database.postgresql;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.DbSetupTracker;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.Operation;
import org.assertj.db.database.AbstractDatabaseTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.Timestamp;

import static com.ninja_squad.dbsetup.Operations.*;

/**
 * Parent for all the tests which are specific for PostgreSQL database.
 *
 * @author Julien Roy
 */
@ContextConfiguration(classes = { PostgresqlConfiguration.class })
public abstract class AbstractPostgresqlTest extends AbstractDatabaseTest {

  private static final Operation DELETE_ALL = deleteAllFrom("test");

  private static final Operation INSERT_TEST = insertInto("test")
      .columns("var1", "var2")
      .values(1, Date.valueOf("2020-04-06"))
      .build();

  private static final Operation OPERATIONS = sequenceOf(DELETE_ALL, INSERT_TEST);

  private static final DbSetupTracker DB_SETUP_TRACKER = new DbSetupTracker();

  private DbSetup dbSetup;

  protected DbSetup getDbSetup() {
    return dbSetup;
  }

  protected DbSetupTracker getDbSetupTracker() {
    return DB_SETUP_TRACKER;
  }

  @Autowired
  protected void setDataSource(DataSource dataSource) {
    this.dbSetup = new DbSetup(
        new DataSourceDestination(dataSource),
        OPERATIONS
    );
  }
}
