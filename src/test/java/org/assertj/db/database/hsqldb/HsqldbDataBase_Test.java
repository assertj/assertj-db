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
 * Copyright 2015-2025 the original author or authors.
 */
package org.assertj.db.database.hsqldb;

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
 * Test on the HSQLDB database.
 *
 * @author Régis Pouiller
 */
public class HsqldbDataBase_Test extends AbstractHsqldbTest {

  private final String URL = "jdbc:hsqldb:mem:testHsqldb";
  private final String USER = "SA";
  private final String PASSWORD = "";

  @Test
  public void test_catalog_for_data_source() throws SQLException {
    try (Connection connection = dataSource.getConnection()) {
      String catalog = connection.getCatalog();
      assertThat(catalog).isEqualTo("PUBLIC");
    }
  }

  @Test
  public void test_catalogfor_jdbc_connection() throws SQLException {
    try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
      String catalog = connection.getCatalog();
      assertThat(catalog).isEqualTo("PUBLIC");
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
  public void test_schemafor_jdbc_connection() throws SQLException {
    try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
      String schema = connection.getSchema();
      assertThat(schema).isEqualTo("PUBLIC");
    }
  }

  @Test
  public void test_tables_for_data_source() throws SQLException {
    try (Connection connection = dataSource.getConnection()) {
      DatabaseMetaData databaseMetaData = connection.getMetaData();
      ResultSet resultSet = databaseMetaData.getTables("PUBLIC", "PUBLIC", null, new String[]{"TABLE"});
      assertThat(resultSet.next()).isTrue();
      assertThat(resultSet.getString("TABLE_NAME")).isEqualTo("TEST");
      assertThat(resultSet.next()).isFalse();
    }
  }

  @Test
  public void test_tablesfor_jdbc_connection() throws SQLException {
    try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
      DatabaseMetaData databaseMetaData = connection.getMetaData();
      ResultSet resultSet = databaseMetaData.getTables("PUBLIC", "PUBLIC", null, new String[]{"TABLE"});
      assertThat(resultSet.next()).isTrue();
      assertThat(resultSet.getString("TABLE_NAME")).isEqualTo("TEST");
      assertThat(resultSet.next()).isFalse();
    }
  }

  @Test
  public void test_table_primary_keys_for_data_source() throws SQLException {
    try (Connection connection = dataSource.getConnection()) {
      DatabaseMetaData databaseMetaData = connection.getMetaData();
      {
        ResultSet resultSet = databaseMetaData.getPrimaryKeys("PUBLIC", "PUBLIC", "TEST");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR1");
        assertThat(resultSet.next()).isFalse();
      }
      {
        ResultSet resultSet = databaseMetaData.getPrimaryKeys("PUBLIC", "PUBLIC", "test");
        assertThat(resultSet.next()).isFalse();
      }
    }
  }

  @Test
  public void test_table_primary_keysfor_jdbc_connection() throws SQLException {
    try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
      DatabaseMetaData databaseMetaData = connection.getMetaData();
      {
        ResultSet resultSet = databaseMetaData.getPrimaryKeys("PUBLIC", "PUBLIC", "TEST");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR1");
        assertThat(resultSet.next()).isFalse();
      }
      {
        ResultSet resultSet = databaseMetaData.getPrimaryKeys("PUBLIC", "PUBLIC", "test");
        assertThat(resultSet.next()).isFalse();
      }
    }
  }

  @Test
  public void test_table_columns_for_data_source() throws SQLException {
    try (Connection connection = dataSource.getConnection()) {
      DatabaseMetaData databaseMetaData = connection.getMetaData();
      {
        ResultSet resultSet = databaseMetaData.getColumns("PUBLIC", "PUBLIC", "TEST", null);
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR1");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR2");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR3");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR4");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR5");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR6");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR7");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR8");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR9");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR10");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR11");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR12");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR13");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR14");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR15");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR16");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR17");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR18");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR19");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR20");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR21");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR22");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR23");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR24");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR25");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR26");
        assertThat(resultSet.next()).isFalse();
      }
      {
        ResultSet resultSet = databaseMetaData.getColumns("PUBLIC", "PUBLIC", "test", null);
        assertThat(resultSet.next()).isFalse();
      }
    }
  }

  @Test
  public void test_table_columnsfor_jdbc_connection() throws SQLException {
    try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
      DatabaseMetaData databaseMetaData = connection.getMetaData();
      {
        ResultSet resultSet = databaseMetaData.getColumns("PUBLIC", "PUBLIC", "TEST", null);
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR1");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR2");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR3");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR4");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR5");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR6");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR7");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR8");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR9");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR10");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR11");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR12");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR13");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR14");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR15");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR16");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR17");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR18");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR19");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR20");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR21");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR22");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR23");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR24");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR25");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR26");
        assertThat(resultSet.next()).isFalse();
      }
      {
        ResultSet resultSet = databaseMetaData.getColumns("PUBLIC", "PUBLIC", "test", null);
        assertThat(resultSet.next()).isFalse();
      }
    }
  }

  @Test
  public void test_request_columns_for_data_source() throws SQLException {
    try (Connection connection = dataSource.getConnection()) {
      try (Statement statement = connection.createStatement()) {
        try (ResultSet resultSet1 = statement.executeQuery("select * from test")) {
          ResultSetMetaData resultSetMetaData = resultSet1.getMetaData();
          assertThat(resultSetMetaData.getColumnCount()).isEqualTo(26);
          assertThat(resultSetMetaData.getColumnName(1)).isEqualTo("VAR1");
          assertThat(resultSetMetaData.getColumnName(2)).isEqualTo("VAR2");
          assertThat(resultSetMetaData.getColumnName(3)).isEqualTo("VAR3");
          assertThat(resultSetMetaData.getColumnName(4)).isEqualTo("VAR4");
          assertThat(resultSetMetaData.getColumnName(5)).isEqualTo("VAR5");
          assertThat(resultSetMetaData.getColumnName(6)).isEqualTo("VAR6");
          assertThat(resultSetMetaData.getColumnName(7)).isEqualTo("VAR7");
          assertThat(resultSetMetaData.getColumnName(8)).isEqualTo("VAR8");
          assertThat(resultSetMetaData.getColumnName(9)).isEqualTo("VAR9");
          assertThat(resultSetMetaData.getColumnName(10)).isEqualTo("VAR10");
          assertThat(resultSetMetaData.getColumnName(11)).isEqualTo("VAR11");
          assertThat(resultSetMetaData.getColumnName(12)).isEqualTo("VAR12");
          assertThat(resultSetMetaData.getColumnName(13)).isEqualTo("VAR13");
          assertThat(resultSetMetaData.getColumnName(14)).isEqualTo("VAR14");
          assertThat(resultSetMetaData.getColumnName(15)).isEqualTo("VAR15");
          assertThat(resultSetMetaData.getColumnName(16)).isEqualTo("VAR16");
          assertThat(resultSetMetaData.getColumnName(17)).isEqualTo("VAR17");
          assertThat(resultSetMetaData.getColumnName(18)).isEqualTo("VAR18");
          assertThat(resultSetMetaData.getColumnName(19)).isEqualTo("VAR19");
          assertThat(resultSetMetaData.getColumnName(20)).isEqualTo("VAR20");
          assertThat(resultSetMetaData.getColumnName(21)).isEqualTo("VAR21");
          assertThat(resultSetMetaData.getColumnName(22)).isEqualTo("VAR22");
          assertThat(resultSetMetaData.getColumnName(23)).isEqualTo("VAR23");
          assertThat(resultSetMetaData.getColumnName(24)).isEqualTo("VAR24");
          assertThat(resultSetMetaData.getColumnName(25)).isEqualTo("VAR25");
          assertThat(resultSetMetaData.getColumnName(26)).isEqualTo("VAR26");
        }
      }
    }
  }

  @Test
  public void test_request_columnsfor_jdbc_connection() throws SQLException {
    try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
      try (Statement statement = connection.createStatement()) {
        try (ResultSet resultSet1 = statement.executeQuery("select * from test")) {
          ResultSetMetaData resultSetMetaData = resultSet1.getMetaData();
          assertThat(resultSetMetaData.getColumnCount()).isEqualTo(26);
          assertThat(resultSetMetaData.getColumnName(1)).isEqualTo("VAR1");
          assertThat(resultSetMetaData.getColumnName(2)).isEqualTo("VAR2");
          assertThat(resultSetMetaData.getColumnName(3)).isEqualTo("VAR3");
          assertThat(resultSetMetaData.getColumnName(4)).isEqualTo("VAR4");
          assertThat(resultSetMetaData.getColumnName(5)).isEqualTo("VAR5");
          assertThat(resultSetMetaData.getColumnName(6)).isEqualTo("VAR6");
          assertThat(resultSetMetaData.getColumnName(7)).isEqualTo("VAR7");
          assertThat(resultSetMetaData.getColumnName(8)).isEqualTo("VAR8");
          assertThat(resultSetMetaData.getColumnName(9)).isEqualTo("VAR9");
          assertThat(resultSetMetaData.getColumnName(10)).isEqualTo("VAR10");
          assertThat(resultSetMetaData.getColumnName(11)).isEqualTo("VAR11");
          assertThat(resultSetMetaData.getColumnName(12)).isEqualTo("VAR12");
          assertThat(resultSetMetaData.getColumnName(13)).isEqualTo("VAR13");
          assertThat(resultSetMetaData.getColumnName(14)).isEqualTo("VAR14");
          assertThat(resultSetMetaData.getColumnName(15)).isEqualTo("VAR15");
          assertThat(resultSetMetaData.getColumnName(16)).isEqualTo("VAR16");
          assertThat(resultSetMetaData.getColumnName(17)).isEqualTo("VAR17");
          assertThat(resultSetMetaData.getColumnName(18)).isEqualTo("VAR18");
          assertThat(resultSetMetaData.getColumnName(19)).isEqualTo("VAR19");
          assertThat(resultSetMetaData.getColumnName(20)).isEqualTo("VAR20");
          assertThat(resultSetMetaData.getColumnName(21)).isEqualTo("VAR21");
          assertThat(resultSetMetaData.getColumnName(22)).isEqualTo("VAR22");
          assertThat(resultSetMetaData.getColumnName(23)).isEqualTo("VAR23");
          assertThat(resultSetMetaData.getColumnName(24)).isEqualTo("VAR24");
          assertThat(resultSetMetaData.getColumnName(25)).isEqualTo("VAR25");
          assertThat(resultSetMetaData.getColumnName(26)).isEqualTo("VAR26");
        }
      }
    }
  }
}
