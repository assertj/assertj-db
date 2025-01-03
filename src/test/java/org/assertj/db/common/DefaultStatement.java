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
package org.assertj.db.common;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;

/**
 * Default Statement.
 *
 * @author Régis Pouiller
 */
public class DefaultStatement implements Statement {

  protected Statement thisStatement;

  public DefaultStatement(Statement statement) {
    thisStatement = statement;
  }

  @Override
  public <T> T unwrap(Class<T> iface) throws SQLException {
    return thisStatement.unwrap(iface);
  }

  @Override
  public boolean isWrapperFor(Class<?> iface) throws SQLException {
    return thisStatement.isWrapperFor(iface);
  }

  @Override
  public ResultSet executeQuery(String sql) throws SQLException {
    return new DefaultResultSet();
  }

  @Override
  public int executeUpdate(String sql) throws SQLException {
    return thisStatement.executeUpdate(sql);
  }

  @Override
  public void close() throws SQLException {
    thisStatement.close();
  }

  @Override
  public int getMaxFieldSize() throws SQLException {
    return thisStatement.getMaxFieldSize();
  }

  @Override
  public void setMaxFieldSize(int max) throws SQLException {
    thisStatement.setMaxFieldSize(max);
  }

  @Override
  public int getMaxRows() throws SQLException {
    return thisStatement.getMaxRows();
  }

  @Override
  public void setMaxRows(int max) throws SQLException {
    thisStatement.setMaxRows(max);
  }

  @Override
  public void setEscapeProcessing(boolean enable) throws SQLException {
    thisStatement.setEscapeProcessing(enable);
  }

  @Override
  public int getQueryTimeout() throws SQLException {
    return thisStatement.getQueryTimeout();
  }

  @Override
  public void setQueryTimeout(int seconds) throws SQLException {
    thisStatement.setQueryTimeout(seconds);
  }

  @Override
  public void cancel() throws SQLException {
    thisStatement.cancel();
  }

  @Override
  public SQLWarning getWarnings() throws SQLException {
    return thisStatement.getWarnings();
  }

  @Override
  public void clearWarnings() throws SQLException {
    thisStatement.clearWarnings();
  }

  @Override
  public void setCursorName(String name) throws SQLException {
    thisStatement.setCursorName(name);
  }

  @Override
  public boolean execute(String sql) throws SQLException {
    return thisStatement.execute(sql);
  }

  @Override
  public ResultSet getResultSet() throws SQLException {
    return thisStatement.getResultSet();
  }

  @Override
  public int getUpdateCount() throws SQLException {
    return thisStatement.getUpdateCount();
  }

  @Override
  public boolean getMoreResults() throws SQLException {
    return thisStatement.getMoreResults();
  }

  @Override
  public int getFetchDirection() throws SQLException {
    return thisStatement.getFetchDirection();
  }

  @Override
  public void setFetchDirection(int direction) throws SQLException {
    thisStatement.setFetchDirection(direction);
  }

  @Override
  public int getFetchSize() throws SQLException {
    return thisStatement.getFetchSize();
  }

  @Override
  public void setFetchSize(int rows) throws SQLException {
    thisStatement.setFetchSize(rows);
  }

  @Override
  public int getResultSetConcurrency() throws SQLException {
    return thisStatement.getResultSetConcurrency();
  }

  @Override
  public int getResultSetType() throws SQLException {
    return thisStatement.getResultSetType();
  }

  @Override
  public void addBatch(String sql) throws SQLException {
    thisStatement.addBatch(sql);
  }

  @Override
  public void clearBatch() throws SQLException {
    thisStatement.clearBatch();
  }

  @Override
  public int[] executeBatch() throws SQLException {
    return thisStatement.executeBatch();
  }

  @Override
  public Connection getConnection() throws SQLException {
    return thisStatement.getConnection();
  }

  @Override
  public boolean getMoreResults(int current) throws SQLException {
    return thisStatement.getMoreResults(current);
  }

  @Override
  public ResultSet getGeneratedKeys() throws SQLException {
    return thisStatement.getGeneratedKeys();
  }

  @Override
  public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
    return thisStatement.executeUpdate(sql, autoGeneratedKeys);
  }

  @Override
  public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
    return thisStatement.executeUpdate(sql, columnIndexes);
  }

  @Override
  public int executeUpdate(String sql, String[] columnNames) throws SQLException {
    return thisStatement.executeUpdate(sql, columnNames);
  }

  @Override
  public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
    return thisStatement.execute(sql, autoGeneratedKeys);
  }

  @Override
  public boolean execute(String sql, int[] columnIndexes) throws SQLException {
    return execute(sql, columnIndexes);
  }

  @Override
  public boolean execute(String sql, String[] columnNames) throws SQLException {
    return thisStatement.execute(sql, columnNames);
  }

  @Override
  public int getResultSetHoldability() throws SQLException {
    return thisStatement.getResultSetHoldability();
  }

  @Override
  public boolean isClosed() throws SQLException {
    return thisStatement.isClosed();
  }

  @Override
  public boolean isPoolable() throws SQLException {
    return thisStatement.isPoolable();
  }

  @Override
  public void setPoolable(boolean poolable) throws SQLException {
    thisStatement.setPoolable(poolable);
  }

  @Override
  public void closeOnCompletion() throws SQLException {
    thisStatement.closeOnCompletion();
  }

  @Override
  public boolean isCloseOnCompletion() throws SQLException {
    return thisStatement.isCloseOnCompletion();
  }
}
