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
package org.assertj.db.type;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.assertj.db.exception.AssertJDBException;

/**
 * @author Julien Roy
 * @since 3.0.0
 */
class FromConnectionSchemaMetadata implements SchemaMetadata {

  private final ConnectionProvider connectionProvider;

  public FromConnectionSchemaMetadata(ConnectionProvider connectionProvider) {
    this.connectionProvider = connectionProvider;
  }

  /**
   * Returns the catalog from a connection.
   *
   * @param connection The connection with the catalog
   * @return The catalog from a connection.
   * @throws SQLException SQL Exception
   */
  private static String getCatalog(Connection connection) throws SQLException {
    try {
      return connection.getCatalog();
    } catch (SQLException exception) {
      throw exception;
    } catch (Exception throwable) {
      return null;
    }
  }

  /**
   * Returns the schema from a connection.
   *
   * @param connection The connection with the catalog
   * @return The schema from a connection.
   * @throws SQLException SQL Exception
   */
  private static String getSchema(Connection connection) throws SQLException {
    try {
      return connection.getSchema();
    } catch (SQLException exception) {
      throw exception;
    } catch (Exception throwable) {
      return null;
    }
  }

  @Override
  public Collection<String> getTablesName() {
    try (Connection connection = connectionProvider.getConnection()) {
      List<String> tables = new ArrayList<>();
      DatabaseMetaData metaData = connection.getMetaData();
      try (ResultSet tableResultSet = metaData.getTables(getCatalog(connection), getSchema(connection), null, new String[]{"TABLE"})) {
        while (tableResultSet.next()) {
          String tableName = tableResultSet.getString("TABLE_NAME");
          tables.add(tableName);
        }
      }
      return tables;
    } catch (SQLException e) {
      throw new AssertJDBException(e);
    }
  }

  @Override
  public Collection<String> getColumnsName(String tableName) {
    try (Connection connection = connectionProvider.getConnection()) {
      List<String> columnsList = new ArrayList<>();
      try (ResultSet columnsResultSet = connection.getMetaData().getColumns(getCatalog(connection), getSchema(connection), tableName, null)) {
        while (columnsResultSet.next()) {
          String column = columnsResultSet.getString("COLUMN_NAME");
          columnsList.add(column);
        }
      }
      return columnsList;
    } catch (SQLException e) {
      throw new AssertJDBException(e);
    }
  }

  @Override
  public Collection<String> getPrimaryKeys(String tableName) {
    try (Connection connection = connectionProvider.getConnection()) {
      List<String> pksNameList = new ArrayList<>();
      try (ResultSet resultSet = connection.getMetaData().getPrimaryKeys(getCatalog(connection), getSchema(connection), tableName)) {
        while (resultSet.next()) {
          String pkName = resultSet.getString("COLUMN_NAME");
          pksNameList.add(pkName);
        }
      }
      return pksNameList;
    } catch (SQLException e) {
      throw new AssertJDBException(e);
    }
  }
}
