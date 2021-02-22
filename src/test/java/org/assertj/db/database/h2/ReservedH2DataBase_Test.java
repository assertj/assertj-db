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

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

/**
 * Test on the H2 database with reserved names in SQL structure..
 *
 * @author Régis Pouiller
 */
public class ReservedH2DataBase_Test extends AbstractReservedH2Test {

  private final String URL = "jdbc:h2:mem:testReservedH2";
  private final String USER = "sa";
  private final String PASSWORD = "";

  @Test
  public void test_catalog_for_data_source() throws SQLException {
    try (Connection connection = dataSource.getConnection()) {
      String catalog = connection.getCatalog();
      assertThat(catalog).isEqualTo("TESTRESERVEDH2");
    }
  }

  @Test
  public void test_catalog_for_source() throws SQLException {
    try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
      String catalog = connection.getCatalog();
      assertThat(catalog).isEqualTo("TESTRESERVEDH2");
    }
  }

  @Test
  public void test_schema_for_data_source() throws SQLException {
    try (Connection connection = dataSource.getConnection()) {
      String schema = connection.getSchema();
      assertThat(schema).isEqualTo("PUBLIC");
    }
  }

  @Test
  public void test_schema_for_source() throws SQLException {
    try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
      String schema = connection.getSchema();
      assertThat(schema).isEqualTo("PUBLIC");
    }
  }

  @Test
  public void test_tables_for_data_source() throws SQLException {
    try (Connection connection = dataSource.getConnection()) {
      DatabaseMetaData databaseMetaData = connection.getMetaData();
      ResultSet resultSet = databaseMetaData.getTables("TESTRESERVEDH2", null, null, new String[] { "TABLE" });
      assertThat(resultSet.next()).isTrue();
      assertThat(resultSet.getString("TABLE_NAME")).isEqualTo("GROUP");
      assertThat(resultSet.next()).isTrue();
      assertThat(resultSet.getString("TABLE_NAME")).isEqualTo("TWO WORDS");
      assertThat(resultSet.next()).isFalse();
    }
  }

  @Test
  public void test_tables_for_source() throws SQLException {
    try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
      DatabaseMetaData databaseMetaData = connection.getMetaData();
      ResultSet resultSet = databaseMetaData.getTables("TESTRESERVEDH2", null, null, new String[] { "TABLE" });
      assertThat(resultSet.next()).isTrue();
      assertThat(resultSet.getString("TABLE_NAME")).isEqualTo("GROUP");
      assertThat(resultSet.next()).isTrue();
      assertThat(resultSet.getString("TABLE_NAME")).isEqualTo("TWO WORDS");
      assertThat(resultSet.next()).isFalse();
    }
  }

  @Test
  public void test_table_primary_keys_for_data_source() throws SQLException {
    try (Connection connection = dataSource.getConnection()) {
      DatabaseMetaData databaseMetaData = connection.getMetaData();
      {
        ResultSet resultSet = databaseMetaData.getPrimaryKeys("TESTRESERVEDH2", null, "GROUP");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("READ");
        assertThat(resultSet.next()).isFalse();
      }
      {
        ResultSet resultSet = databaseMetaData.getPrimaryKeys("TESTRESERVEDH2", null, "TWO WORDS");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("PRIMARY KEY");
        assertThat(resultSet.next()).isFalse();
      }
      {
        ResultSet resultSet = databaseMetaData.getPrimaryKeys("TESTRESERVEDH2", null, "group");
        assertThat(resultSet.next()).isFalse();
      }
    }
  }

  @Test
  public void test_table_primary_keys_for_source() throws SQLException {
    try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
      DatabaseMetaData databaseMetaData = connection.getMetaData();
      {
        ResultSet resultSet = databaseMetaData.getPrimaryKeys("TESTRESERVEDH2", null, "GROUP");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("READ");
        assertThat(resultSet.next()).isFalse();
      }
      {
        ResultSet resultSet = databaseMetaData.getPrimaryKeys("TESTRESERVEDH2", null, "TWO WORDS");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("PRIMARY KEY");
        assertThat(resultSet.next()).isFalse();
      }
      {
        ResultSet resultSet = databaseMetaData.getPrimaryKeys("TESTRESERVEDH2", null, "group");
        assertThat(resultSet.next()).isFalse();
      }
    }
  }

  @Test
  public void test_table_columns_for_data_source() throws SQLException {
    try (Connection connection = dataSource.getConnection()) {
      DatabaseMetaData databaseMetaData = connection.getMetaData();
      {
        ResultSet resultSet = databaseMetaData.getColumns("TESTRESERVEDH2", null, "GROUP", null);
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("READ");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("BY");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("SELECT");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("FROM");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("WHERE");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("ORDER");
        assertThat(resultSet.next()).isFalse();
      }
      {
        ResultSet resultSet = databaseMetaData.getColumns("TESTRESERVEDH2", null, "TWO WORDS", null);
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("PRIMARY KEY");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("COLUMN NAME");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("TEST%TEST");
        assertThat(resultSet.next()).isFalse();
      }
      {
        ResultSet resultSet = databaseMetaData.getColumns("TESTRESERVEDH2", null, "group", null);
        assertThat(resultSet.next()).isFalse();
      }
    }
  }

  @Test
  public void test_table_columns_for_source() throws SQLException {
    try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
      DatabaseMetaData databaseMetaData = connection.getMetaData();
      {
        ResultSet resultSet = databaseMetaData.getColumns("TESTRESERVEDH2", null, "GROUP", null);
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("READ");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("BY");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("SELECT");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("FROM");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("WHERE");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("ORDER");
        assertThat(resultSet.next()).isFalse();
      }
      {
        ResultSet resultSet = databaseMetaData.getColumns("TESTRESERVEDH2", null, "TWO WORDS", null);
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("PRIMARY KEY");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("COLUMN NAME");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("TEST%TEST");
        assertThat(resultSet.next()).isFalse();
      }
      {
        ResultSet resultSet = databaseMetaData.getColumns("TESTRESERVEDH2", null, "group", null);
        assertThat(resultSet.next()).isFalse();
      }
    }
  }

  @Test
  public void test_request_columns_for_data_source() throws SQLException {
    try (Connection connection = dataSource.getConnection()) {
      try (Statement statement = connection.createStatement()) {
        try (ResultSet resultSet1 = statement.executeQuery("select * from `group`")) {
          ResultSetMetaData resultSetMetaData = resultSet1.getMetaData();
          assertThat(resultSetMetaData.getColumnCount()).isEqualTo(6);
          assertThat(resultSetMetaData.getColumnName(1)).isEqualTo("READ");
          assertThat(resultSetMetaData.getColumnName(2)).isEqualTo("BY");
          assertThat(resultSetMetaData.getColumnName(3)).isEqualTo("SELECT");
          assertThat(resultSetMetaData.getColumnName(4)).isEqualTo("FROM");
          assertThat(resultSetMetaData.getColumnName(5)).isEqualTo("WHERE");
          assertThat(resultSetMetaData.getColumnName(6)).isEqualTo("ORDER");
        }
        try (ResultSet resultSet1 = statement.executeQuery("select * from `two words`")) {
          ResultSetMetaData resultSetMetaData = resultSet1.getMetaData();
          assertThat(resultSetMetaData.getColumnCount()).isEqualTo(3);
          assertThat(resultSetMetaData.getColumnName(1)).isEqualTo("PRIMARY KEY");
          assertThat(resultSetMetaData.getColumnName(2)).isEqualTo("COLUMN NAME");
          assertThat(resultSetMetaData.getColumnName(3)).isEqualTo("TEST%TEST");
        }
      }
    }
  }

  @Test
  public void test_request_columns_for_source() throws SQLException {
    try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
      try (Statement statement = connection.createStatement()) {
        try (ResultSet resultSet1 = statement.executeQuery("select * from `group`")) {
          ResultSetMetaData resultSetMetaData = resultSet1.getMetaData();
          assertThat(resultSetMetaData.getColumnCount()).isEqualTo(6);
          assertThat(resultSetMetaData.getColumnName(1)).isEqualTo("READ");
          assertThat(resultSetMetaData.getColumnName(2)).isEqualTo("BY");
          assertThat(resultSetMetaData.getColumnName(3)).isEqualTo("SELECT");
          assertThat(resultSetMetaData.getColumnName(4)).isEqualTo("FROM");
          assertThat(resultSetMetaData.getColumnName(5)).isEqualTo("WHERE");
          assertThat(resultSetMetaData.getColumnName(6)).isEqualTo("ORDER");
        }
        try (ResultSet resultSet1 = statement.executeQuery("select * from `two words`")) {
          ResultSetMetaData resultSetMetaData = resultSet1.getMetaData();
          assertThat(resultSetMetaData.getColumnCount()).isEqualTo(3);
          assertThat(resultSetMetaData.getColumnName(1)).isEqualTo("PRIMARY KEY");
          assertThat(resultSetMetaData.getColumnName(2)).isEqualTo("COLUMN NAME");
          assertThat(resultSetMetaData.getColumnName(3)).isEqualTo("TEST%TEST");
        }
      }
    }
  }
}
