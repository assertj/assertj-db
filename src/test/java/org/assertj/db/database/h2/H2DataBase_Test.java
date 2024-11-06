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
 * Test on the H2 database.
 *
 * @author Régis Pouiller
 */
public class H2DataBase_Test extends AbstractH2Test {

  private final String URL = "jdbc:h2:mem:testH2";
  private final String USER = "sa";
  private final String PASSWORD = "";

  @Test
  public void test_catalog_for_data_source() throws SQLException {
    try (Connection connection = dataSource.getConnection()) {
      String catalog = connection.getCatalog();
      assertThat(catalog).isEqualTo("TESTH2");
    }
  }

  @Test
  public void test_catalog_for_source() throws SQLException {
    try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
      String catalog = connection.getCatalog();
      assertThat(catalog).isEqualTo("TESTH2");
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
      ResultSet resultSet = databaseMetaData.getTables("TESTH2", null, null, new String[]{"TABLE"});
      assertThat(resultSet.next()).isTrue();
      assertThat(resultSet.getString("TABLE_NAME")).isEqualTo("TEST");
      assertThat(resultSet.next()).isFalse();
    }
  }

  @Test
  public void test_tables_for_source() throws SQLException {
    try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
      DatabaseMetaData databaseMetaData = connection.getMetaData();
      ResultSet resultSet = databaseMetaData.getTables("TESTH2", null, null, new String[]{"TABLE"});
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
        ResultSet resultSet = databaseMetaData.getPrimaryKeys("TESTH2", null, "TEST");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR1");
        assertThat(resultSet.next()).isFalse();
      }
      {
        ResultSet resultSet = databaseMetaData.getPrimaryKeys("TESTH2", null, "test");
        assertThat(resultSet.next()).isFalse();
      }
    }
  }

  @Test
  public void test_table_primary_keys_for_source() throws SQLException {
    try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
      DatabaseMetaData databaseMetaData = connection.getMetaData();
      {
        ResultSet resultSet = databaseMetaData.getPrimaryKeys("TESTH2", null, "TEST");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR1");
        assertThat(resultSet.next()).isFalse();
      }
      {
        ResultSet resultSet = databaseMetaData.getPrimaryKeys("TESTH2", null, "test");
        assertThat(resultSet.next()).isFalse();
      }
    }
  }

  @Test
  public void test_table_columns_for_data_source() throws SQLException {
    try (Connection connection = dataSource.getConnection()) {
      DatabaseMetaData databaseMetaData = connection.getMetaData();
      {
        ResultSet resultSet = databaseMetaData.getColumns("TESTH2", null, "TEST", null);
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
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR27");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR28");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR29");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR30");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR31");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR32");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR33");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR34");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR35");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR36");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR37");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR38");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR39");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR40");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR41");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR42");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR43");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR44");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR45");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR46");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR47");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR48");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR49");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR50");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR51");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR52");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR53");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR54");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR55");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR56");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR57");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR58");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR59");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR60");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR61");
        assertThat(resultSet.next()).isFalse();
      }
      {
        ResultSet resultSet = databaseMetaData.getColumns("TESTH2", null, "test", null);
        assertThat(resultSet.next()).isFalse();
      }
    }
  }

  @Test
  public void test_table_columns_for_source() throws SQLException {
    try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
      DatabaseMetaData databaseMetaData = connection.getMetaData();
      {
        ResultSet resultSet = databaseMetaData.getColumns("TESTH2", null, "TEST", null);
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
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR27");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR28");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR29");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR30");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR31");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR32");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR33");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR34");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR35");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR36");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR37");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR38");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR39");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR40");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR41");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR42");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR43");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR44");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR45");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR46");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR47");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR48");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR49");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR50");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR51");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR52");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR53");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR54");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR55");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR56");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR57");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR58");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR59");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR60");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getString("COLUMN_NAME")).isEqualTo("VAR61");
        assertThat(resultSet.next()).isFalse();
      }
      {
        ResultSet resultSet = databaseMetaData.getColumns("TESTH2", null, "test", null);
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
          assertThat(resultSetMetaData.getColumnCount()).isEqualTo(61);
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
          assertThat(resultSetMetaData.getColumnName(27)).isEqualTo("VAR27");
          assertThat(resultSetMetaData.getColumnName(28)).isEqualTo("VAR28");
          assertThat(resultSetMetaData.getColumnName(29)).isEqualTo("VAR29");
          assertThat(resultSetMetaData.getColumnName(30)).isEqualTo("VAR30");
          assertThat(resultSetMetaData.getColumnName(31)).isEqualTo("VAR31");
          assertThat(resultSetMetaData.getColumnName(32)).isEqualTo("VAR32");
          assertThat(resultSetMetaData.getColumnName(33)).isEqualTo("VAR33");
          assertThat(resultSetMetaData.getColumnName(34)).isEqualTo("VAR34");
          assertThat(resultSetMetaData.getColumnName(35)).isEqualTo("VAR35");
          assertThat(resultSetMetaData.getColumnName(36)).isEqualTo("VAR36");
          assertThat(resultSetMetaData.getColumnName(37)).isEqualTo("VAR37");
          assertThat(resultSetMetaData.getColumnName(38)).isEqualTo("VAR38");
          assertThat(resultSetMetaData.getColumnName(39)).isEqualTo("VAR39");
          assertThat(resultSetMetaData.getColumnName(40)).isEqualTo("VAR40");
          assertThat(resultSetMetaData.getColumnName(41)).isEqualTo("VAR41");
          assertThat(resultSetMetaData.getColumnName(42)).isEqualTo("VAR42");
          assertThat(resultSetMetaData.getColumnName(43)).isEqualTo("VAR43");
          assertThat(resultSetMetaData.getColumnName(44)).isEqualTo("VAR44");
          assertThat(resultSetMetaData.getColumnName(45)).isEqualTo("VAR45");
          assertThat(resultSetMetaData.getColumnName(46)).isEqualTo("VAR46");
          assertThat(resultSetMetaData.getColumnName(47)).isEqualTo("VAR47");
          assertThat(resultSetMetaData.getColumnName(48)).isEqualTo("VAR48");
          assertThat(resultSetMetaData.getColumnName(49)).isEqualTo("VAR49");
          assertThat(resultSetMetaData.getColumnName(50)).isEqualTo("VAR50");
          assertThat(resultSetMetaData.getColumnName(51)).isEqualTo("VAR51");
          assertThat(resultSetMetaData.getColumnName(52)).isEqualTo("VAR52");
          assertThat(resultSetMetaData.getColumnName(53)).isEqualTo("VAR53");
          assertThat(resultSetMetaData.getColumnName(54)).isEqualTo("VAR54");
          assertThat(resultSetMetaData.getColumnName(55)).isEqualTo("VAR55");
          assertThat(resultSetMetaData.getColumnName(56)).isEqualTo("VAR56");
          assertThat(resultSetMetaData.getColumnName(57)).isEqualTo("VAR57");
          assertThat(resultSetMetaData.getColumnName(58)).isEqualTo("VAR58");
          assertThat(resultSetMetaData.getColumnName(59)).isEqualTo("VAR59");
          assertThat(resultSetMetaData.getColumnName(60)).isEqualTo("VAR60");
          assertThat(resultSetMetaData.getColumnName(61)).isEqualTo("VAR61");
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
          assertThat(resultSetMetaData.getColumnCount()).isEqualTo(61);
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
          assertThat(resultSetMetaData.getColumnName(27)).isEqualTo("VAR27");
          assertThat(resultSetMetaData.getColumnName(28)).isEqualTo("VAR28");
          assertThat(resultSetMetaData.getColumnName(29)).isEqualTo("VAR29");
          assertThat(resultSetMetaData.getColumnName(30)).isEqualTo("VAR30");
          assertThat(resultSetMetaData.getColumnName(31)).isEqualTo("VAR31");
          assertThat(resultSetMetaData.getColumnName(32)).isEqualTo("VAR32");
          assertThat(resultSetMetaData.getColumnName(33)).isEqualTo("VAR33");
          assertThat(resultSetMetaData.getColumnName(34)).isEqualTo("VAR34");
          assertThat(resultSetMetaData.getColumnName(35)).isEqualTo("VAR35");
          assertThat(resultSetMetaData.getColumnName(36)).isEqualTo("VAR36");
          assertThat(resultSetMetaData.getColumnName(37)).isEqualTo("VAR37");
          assertThat(resultSetMetaData.getColumnName(38)).isEqualTo("VAR38");
          assertThat(resultSetMetaData.getColumnName(39)).isEqualTo("VAR39");
          assertThat(resultSetMetaData.getColumnName(40)).isEqualTo("VAR40");
          assertThat(resultSetMetaData.getColumnName(41)).isEqualTo("VAR41");
          assertThat(resultSetMetaData.getColumnName(42)).isEqualTo("VAR42");
          assertThat(resultSetMetaData.getColumnName(43)).isEqualTo("VAR43");
          assertThat(resultSetMetaData.getColumnName(44)).isEqualTo("VAR44");
          assertThat(resultSetMetaData.getColumnName(45)).isEqualTo("VAR45");
          assertThat(resultSetMetaData.getColumnName(46)).isEqualTo("VAR46");
          assertThat(resultSetMetaData.getColumnName(47)).isEqualTo("VAR47");
          assertThat(resultSetMetaData.getColumnName(48)).isEqualTo("VAR48");
          assertThat(resultSetMetaData.getColumnName(49)).isEqualTo("VAR49");
          assertThat(resultSetMetaData.getColumnName(50)).isEqualTo("VAR50");
          assertThat(resultSetMetaData.getColumnName(51)).isEqualTo("VAR51");
          assertThat(resultSetMetaData.getColumnName(52)).isEqualTo("VAR52");
          assertThat(resultSetMetaData.getColumnName(53)).isEqualTo("VAR53");
          assertThat(resultSetMetaData.getColumnName(54)).isEqualTo("VAR54");
          assertThat(resultSetMetaData.getColumnName(55)).isEqualTo("VAR55");
          assertThat(resultSetMetaData.getColumnName(56)).isEqualTo("VAR56");
          assertThat(resultSetMetaData.getColumnName(57)).isEqualTo("VAR57");
          assertThat(resultSetMetaData.getColumnName(58)).isEqualTo("VAR58");
          assertThat(resultSetMetaData.getColumnName(59)).isEqualTo("VAR59");
          assertThat(resultSetMetaData.getColumnName(60)).isEqualTo("VAR60");
          assertThat(resultSetMetaData.getColumnName(61)).isEqualTo("VAR61");
        }
      }
    }
  }
}
