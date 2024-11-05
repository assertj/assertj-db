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
package org.assertj.db.type;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;

import org.assertj.core.api.Assertions;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.DefaultConnection;
import org.assertj.db.common.DefaultDataSource;
import org.assertj.db.common.DefaultStatement;
import org.assertj.db.exception.AssertJDBException;
import org.assertj.db.type.Table.Order;
import org.junit.Test;

/**
 * Test on loading of the data for a table and exception during the different steps.
 *
 * @author Régis Pouiller
 * @author Julien Roy
 */
public class Table_Exception_Test extends AbstractTest {

  /**
   * This method should fail because setting the connection provider to null.
   */
  @Test(expected = NullPointerException.class)
  public void should_fail_because_setting_connection_provider_to_null() {
    new Table().setConnectionProvider(null);
  }

  /**
   * This method should fail because the connection throw an exception when getting an object.
   */
  @Test(expected = AssertJDBException.class)
  public void should_fail_because_connection_throws_exception_when_getting_an_object() {
    DataSource ds = new DefaultDataSource(dataSource);
    ConnectionProvider connectionProvider = ConnectionProviderFactory.of(ds).create();
    Table table = new Table(connectionProvider, "movi");
    table.getColumnsNameList();
  }

  /**
   * This method should fail because trying to set the columns to check first.
   */
  @Test
  public void should_fail_because_trying_to_set_columns_to_check_first() {
    try {
      new Table().setColumnsToCheck(new String[]{"test"});
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("The table name and the connectionProvider must be set first");
    }
  }

  /**
   * This method should fail because trying to set the columns to exclude first.
   */
  @Test
  public void should_fail_because_trying_to_set_columns_to_exclude_first() {
    try {
      new Table().setColumnsToExclude(new String[]{"test"});
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("The table name and the connectionProvider must be set first");
    }
  }

  /**
   * This method should fail because trying to set the columns order first.
   */
  @Test
  public void should_fail_because_trying_to_set_columns_order_first() {
    try {
      new Table().setColumnsToOrder(new Order[]{Order.asc("test")});
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("The table name and the connectionProvider must be set first");
    }
  }

  /**
   * This method should fail because trying to set the columns order with order null.
   */
  @Test
  public void should_fail_because_trying_to_set_columns_order_with_order_null() {
    try {
      new Table(jdbcConnectionProvider, "test").setColumnsToOrder(new Order[]{null});
      fail("An exception must be raised");
    } catch (NullPointerException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("The order can not be null");
    }
  }

  /**
   * This method should fail because trying to set the columns order with column null.
   */
  @Test
  public void should_fail_because_trying_to_set_columns_order_with_column_null() {
    try {
      new Table(jdbcConnectionProvider, "test").setColumnsToOrder(new Order[]{Order.asc(null)});
      fail("An exception must be raised");
    } catch (NullPointerException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("The name of the column for order can not be null");
    }
  }

  /**
   * This method should fail because the connection throw an exception when executing a query.
   */
  @Test(expected = AssertJDBException.class)
  public void should_fail_because_connection_throws_exception_when_executing_a_query() {
    DataSource ds = new DefaultDataSource(dataSource) {
      @Override
      public Connection getConnection() throws SQLException {
        return new DefaultConnection(thisDataSource.getConnection()) {

          @Override
          public Statement createStatement() throws SQLException {
            return new DefaultStatement(thisConnection.createStatement()) {

              @Override
              public ResultSet executeQuery(String sql) throws SQLException {
                throw new SQLException();
              }
            };
          }
        };
      }
    };
    ConnectionProvider connectionProvider = ConnectionProviderFactory.of(ds).create();
    Table table = new Table(connectionProvider, "movi");
    table.getColumnsNameList();
  }

  /**
   * This method should fail because the connection throw an exception when getting metadata.
   */
  @Test(expected = AssertJDBException.class)
  public void should_fail_because_connection_throws_exception_when_getting_metadata() {
    DataSource ds = new DefaultDataSource(dataSource) {
      @Override
      public Connection getConnection() throws SQLException {
        return new DefaultConnection(thisDataSource.getConnection()) {

          @Override
          public DatabaseMetaData getMetaData() throws SQLException {
            throw new SQLException();
          }
        };
      }
    };
    ConnectionProvider connectionProvider = ConnectionProviderFactory.of(ds).create();
    Table table = new Table(connectionProvider, "movi");
    table.getColumnsNameList();
  }

  /**
   * This method should fail because the connection throw an exception when creating a statement.
   */
  @Test(expected = AssertJDBException.class)
  public void should_fail_because_connection_throws_exception_when_creating_a_statement() {
    DataSource ds = new DefaultDataSource(dataSource) {
      @Override
      public Connection getConnection() throws SQLException {
        return new DefaultConnection(thisDataSource.getConnection()) {

          @Override
          public Statement createStatement() throws SQLException {
            throw new SQLException();
          }
        };
      }
    };
    ConnectionProvider connectionProvider = ConnectionProviderFactory.of(ds).create();
    Table table = new Table(connectionProvider, "movi");
    table.getColumnsNameList();
  }

  /**
   * This method should fail because the connection throw an exception when getting a connection.
   */
  @Test(expected = AssertJDBException.class)
  public void should_fail_because_connection_throws_exception_when_getting_a_connection() {
    DataSource ds = new DefaultDataSource(dataSource) {
      @Override
      public Connection getConnection() throws SQLException {
        throw new SQLException();
      }
    };
    ConnectionProvider connectionProvider = ConnectionProviderFactory.of(ds).create();
    Table table = new Table(connectionProvider, "movi");
    table.getColumnsNameList();
  }
}
