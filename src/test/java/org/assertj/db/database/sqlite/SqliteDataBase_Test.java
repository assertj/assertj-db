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
package org.assertj.db.database.sqlite;

import org.junit.Test;

import java.sql.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test on the Sqlite database.
 *
 * @author Régis Pouiller
 */
public class SqliteDataBase_Test extends AbstractSqliteTest {

  private final String URL = "jdbc:sqlite:target/testDerby.db";
  private final String USER = "";
  private final String PASSWORD = "";

  @Test
  public void test_catalog_for_data_source() throws SQLException {
    try (Connection connection = dataSource.getConnection()) {
      String catalog = connection.getCatalog();
      assertThat(catalog).isNull();
    }
  }

  @Test
  public void test_catalog_for_source() throws SQLException {
    try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
      String catalog = connection.getCatalog();
      assertThat(catalog).isNull();
    }
  }

  @Test
  public void test_schema_for_data_source() throws SQLException {
    try (Connection connection = dataSource.getConnection()) {
      String schema = connection.getSchema();
      assertThat(schema).isNull();
    }
  }

  @Test
  public void test_schema_for_source() throws SQLException {
    try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
      String schema = connection.getSchema();
      assertThat(schema).isNull();
    }
  }

  @Test
  public void test_tables_for_data_source() throws SQLException {
    try (Connection connection = dataSource.getConnection()) {
      DatabaseMetaData databaseMetaData = connection.getMetaData();
      ResultSet resultSet = databaseMetaData.getTables(null, null, null, new String[] { "TABLE" });
      assertThat(resultSet.next()).isTrue();
      assertThat(resultSet.getString("TABLE_NAME")).isEqualTo("teSt");
      assertThat(resultSet.next()).isFalse();
    }
  }

  @Test
  public void test_tables_for_source() throws SQLException {
    try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
      DatabaseMetaData databaseMetaData = connection.getMetaData();
      ResultSet resultSet = databaseMetaData.getTables(null, null, null, new String[] { "TABLE" });
      assertThat(resultSet.next()).isTrue();
      assertThat(resultSet.getString("TABLE_NAME")).isEqualTo("teSt");
      assertThat(resultSet.next()).isFalse();
    }
  }

  @Test
  public void test_table_primary_keys_for_data_source() throws SQLException {
    try (Connection connection = dataSource.getConnection()) {
      DatabaseMetaData databaseMetaData = connection.getMetaData();
      {
        ResultSet resultSet = databaseMetaData.getPrimaryKeys(null, null, "teSt");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("Var1");
        assertThat(resultSet.next()).isFalse();
      }
      {
        ResultSet resultSet = databaseMetaData.getPrimaryKeys(null, null, "test");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("Var1");
        assertThat(resultSet.next()).isFalse();
      }
    }
  }

  @Test
  public void test_table_primary_keys_for_source() throws SQLException {
    try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
      DatabaseMetaData databaseMetaData = connection.getMetaData();
      {
        ResultSet resultSet = databaseMetaData.getPrimaryKeys(null, null, "teSt");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("Var1");
        assertThat(resultSet.next()).isFalse();
      }
      {
        ResultSet resultSet = databaseMetaData.getPrimaryKeys(null, null, "test");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("Var1");
        assertThat(resultSet.next()).isFalse();
      }
    }
  }

  @Test
  public void test_table_columns_for_data_source() throws SQLException {
    try (Connection connection = dataSource.getConnection()) {
      DatabaseMetaData databaseMetaData = connection.getMetaData();
      {
        ResultSet resultSet = databaseMetaData.getColumns(null, null, "TEST", null);
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("Var1");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("vAr2");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("vaR3");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("var4");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("var5");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("var6");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("var7");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("var8");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("var9");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("var10");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("var11");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("var12");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("var13");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("var14");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("var15");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("var16");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("var17");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("var18");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("var19");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("var20");
        assertThat(resultSet.next()).isFalse();
      }
      {
        ResultSet resultSet = databaseMetaData.getColumns(null, null, "test", null);
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("Var1");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("vAr2");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("vaR3");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("var4");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("var5");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("var6");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("var7");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("var8");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("var9");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("var10");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("var11");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("var12");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("var13");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("var14");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("var15");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("var16");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("var17");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("var18");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("var19");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("var20");
        assertThat(resultSet.next()).isFalse();
      }
    }
  }

  @Test
  public void test_table_columns_for_source() throws SQLException {
    try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
      DatabaseMetaData databaseMetaData = connection.getMetaData();
      {
        ResultSet resultSet = databaseMetaData.getColumns(null, null, "TEST", null);
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("Var1");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("vAr2");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("vaR3");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("var4");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("var5");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("var6");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("var7");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("var8");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("var9");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("var10");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("var11");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("var12");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("var13");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("var14");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("var15");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("var16");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("var17");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("var18");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("var19");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("var20");
        assertThat(resultSet.next()).isFalse();
      }
      {
        ResultSet resultSet = databaseMetaData.getColumns(null, null, "test", null);
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("Var1");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("vAr2");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("vaR3");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("var4");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("var5");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("var6");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("var7");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("var8");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("var9");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("var10");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("var11");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("var12");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("var13");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("var14");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("var15");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("var16");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("var17");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("var18");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("var19");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("var20");
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
          assertThat(resultSetMetaData.getColumnCount()).isEqualTo(20);
          assertThat(resultSetMetaData.getColumnName(1)).isEqualTo("Var1");
          assertThat(resultSetMetaData.getColumnName(2)).isEqualTo("vAr2");
          assertThat(resultSetMetaData.getColumnName(3)).isEqualTo("vaR3");
          assertThat(resultSetMetaData.getColumnName(4)).isEqualTo("var4");
          assertThat(resultSetMetaData.getColumnName(5)).isEqualTo("var5");
          assertThat(resultSetMetaData.getColumnName(6)).isEqualTo("var6");
          assertThat(resultSetMetaData.getColumnName(7)).isEqualTo("var7");
          assertThat(resultSetMetaData.getColumnName(8)).isEqualTo("var8");
          assertThat(resultSetMetaData.getColumnName(9)).isEqualTo("var9");
          assertThat(resultSetMetaData.getColumnName(10)).isEqualTo("var10");
          assertThat(resultSetMetaData.getColumnName(11)).isEqualTo("var11");
          assertThat(resultSetMetaData.getColumnName(12)).isEqualTo("var12");
          assertThat(resultSetMetaData.getColumnName(13)).isEqualTo("var13");
          assertThat(resultSetMetaData.getColumnName(14)).isEqualTo("var14");
          assertThat(resultSetMetaData.getColumnName(15)).isEqualTo("var15");
          assertThat(resultSetMetaData.getColumnName(16)).isEqualTo("var16");
          assertThat(resultSetMetaData.getColumnName(17)).isEqualTo("var17");
          assertThat(resultSetMetaData.getColumnName(18)).isEqualTo("var18");
          assertThat(resultSetMetaData.getColumnName(19)).isEqualTo("var19");
          assertThat(resultSetMetaData.getColumnName(20)).isEqualTo("var20");
        }
      }
    }
  }

  @Test
  public void test_request_columns_for_source() throws SQLException {
    try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
      try (Statement statement = connection.createStatement()) {
        try (ResultSet resultSet1 = statement.executeQuery("select * from test")) {
          ResultSetMetaData resultSetMetaData = resultSet1.getMetaData();
          assertThat(resultSetMetaData.getColumnCount()).isEqualTo(20);
          assertThat(resultSetMetaData.getColumnName(1)).isEqualTo("Var1");
          assertThat(resultSetMetaData.getColumnName(2)).isEqualTo("vAr2");
          assertThat(resultSetMetaData.getColumnName(3)).isEqualTo("vaR3");
          assertThat(resultSetMetaData.getColumnName(4)).isEqualTo("var4");
          assertThat(resultSetMetaData.getColumnName(5)).isEqualTo("var5");
          assertThat(resultSetMetaData.getColumnName(6)).isEqualTo("var6");
          assertThat(resultSetMetaData.getColumnName(7)).isEqualTo("var7");
          assertThat(resultSetMetaData.getColumnName(8)).isEqualTo("var8");
          assertThat(resultSetMetaData.getColumnName(9)).isEqualTo("var9");
          assertThat(resultSetMetaData.getColumnName(10)).isEqualTo("var10");
          assertThat(resultSetMetaData.getColumnName(11)).isEqualTo("var11");
          assertThat(resultSetMetaData.getColumnName(12)).isEqualTo("var12");
          assertThat(resultSetMetaData.getColumnName(13)).isEqualTo("var13");
          assertThat(resultSetMetaData.getColumnName(14)).isEqualTo("var14");
          assertThat(resultSetMetaData.getColumnName(15)).isEqualTo("var15");
          assertThat(resultSetMetaData.getColumnName(16)).isEqualTo("var16");
          assertThat(resultSetMetaData.getColumnName(17)).isEqualTo("var17");
          assertThat(resultSetMetaData.getColumnName(18)).isEqualTo("var18");
          assertThat(resultSetMetaData.getColumnName(19)).isEqualTo("var19");
          assertThat(resultSetMetaData.getColumnName(20)).isEqualTo("var20");
        }
      }
    }
  }
}
