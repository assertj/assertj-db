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

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Default PreparedStatement.
 *
 * @author Régis Pouiller
 */
public class DefaultPreparedStatement implements PreparedStatement {

  private PreparedStatement thisPreparedStatement;

  public DefaultPreparedStatement(PreparedStatement preparedStatement) {
    this.thisPreparedStatement = preparedStatement;
  }

  @Override
  public ResultSet executeQuery(String sql) throws SQLException {
    return thisPreparedStatement.executeQuery(sql);
  }

  @Override
  public int executeUpdate(String sql) throws SQLException {
    return thisPreparedStatement.executeUpdate(sql);
  }

  @Override
  public void close() throws SQLException {
    thisPreparedStatement.close();
  }

  @Override
  public int getMaxFieldSize() throws SQLException {
    return thisPreparedStatement.getMaxFieldSize();
  }

  @Override
  public void setMaxFieldSize(int max) throws SQLException {
    thisPreparedStatement.setMaxFieldSize(max);
  }

  @Override
  public int getMaxRows() throws SQLException {
    return thisPreparedStatement.getMaxRows();
  }

  @Override
  public void setMaxRows(int max) throws SQLException {
    thisPreparedStatement.setMaxRows(max);
  }

  @Override
  public void setEscapeProcessing(boolean enable) throws SQLException {
    thisPreparedStatement.setEscapeProcessing(enable);
  }

  @Override
  public int getQueryTimeout() throws SQLException {
    return thisPreparedStatement.getQueryTimeout();
  }

  @Override
  public void setQueryTimeout(int seconds) throws SQLException {
    thisPreparedStatement.setQueryTimeout(seconds);
  }

  @Override
  public void cancel() throws SQLException {
    thisPreparedStatement.cancel();
  }

  @Override
  public SQLWarning getWarnings() throws SQLException {
    return thisPreparedStatement.getWarnings();
  }

  @Override
  public void clearWarnings() throws SQLException {
    thisPreparedStatement.clearWarnings();
  }

  @Override
  public void setCursorName(String name) throws SQLException {
    thisPreparedStatement.setCursorName(name);
  }

  @Override
  public boolean execute(String sql) throws SQLException {
    return thisPreparedStatement.execute(sql);
  }

  @Override
  public ResultSet getResultSet() throws SQLException {
    return thisPreparedStatement.getResultSet();
  }

  @Override
  public int getUpdateCount() throws SQLException {
    return thisPreparedStatement.getUpdateCount();
  }

  @Override
  public boolean getMoreResults() throws SQLException {
    return thisPreparedStatement.getMoreResults();
  }

  @Override
  public int getFetchDirection() throws SQLException {
    return thisPreparedStatement.getFetchDirection();
  }

  @Override
  public void setFetchDirection(int direction) throws SQLException {
    thisPreparedStatement.setFetchDirection(direction);
  }

  @Override
  public int getFetchSize() throws SQLException {
    return thisPreparedStatement.getFetchSize();
  }

  @Override
  public void setFetchSize(int rows) throws SQLException {
    thisPreparedStatement.setFetchSize(rows);
  }

  @Override
  public int getResultSetConcurrency() throws SQLException {
    return thisPreparedStatement.getResultSetConcurrency();
  }

  @Override
  public int getResultSetType() throws SQLException {
    return thisPreparedStatement.getResultSetType();
  }

  @Override
  public void addBatch(String sql) throws SQLException {
    thisPreparedStatement.addBatch(sql);
  }

  @Override
  public void clearBatch() throws SQLException {
    thisPreparedStatement.clearBatch();
  }

  @Override
  public int[] executeBatch() throws SQLException {
    return thisPreparedStatement.executeBatch();
  }

  @Override
  public Connection getConnection() throws SQLException {
    return thisPreparedStatement.getConnection();
  }

  @Override
  public boolean getMoreResults(int current) throws SQLException {
    return thisPreparedStatement.getMoreResults(current);
  }

  @Override
  public ResultSet getGeneratedKeys() throws SQLException {
    return thisPreparedStatement.getGeneratedKeys();
  }

  @Override
  public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
    return thisPreparedStatement.executeUpdate(sql, autoGeneratedKeys);
  }

  @Override
  public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
    return thisPreparedStatement.executeUpdate(sql, columnIndexes);
  }

  @Override
  public int executeUpdate(String sql, String[] columnNames) throws SQLException {
    return thisPreparedStatement.executeUpdate(sql, columnNames);
  }

  @Override
  public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
    return thisPreparedStatement.execute(sql, autoGeneratedKeys);
  }

  @Override
  public boolean execute(String sql, int[] columnIndexes) throws SQLException {
    return thisPreparedStatement.execute(sql, columnIndexes);
  }

  @Override
  public boolean execute(String sql, String[] columnNames) throws SQLException {
    return thisPreparedStatement.execute(sql, columnNames);
  }

  @Override
  public int getResultSetHoldability() throws SQLException {
    return thisPreparedStatement.getResultSetHoldability();
  }

  @Override
  public boolean isClosed() throws SQLException {
    return thisPreparedStatement.isClosed();
  }

  @Override
  public boolean isPoolable() throws SQLException {
    return thisPreparedStatement.isPoolable();
  }

  @Override
  public void setPoolable(boolean poolable) throws SQLException {
    thisPreparedStatement.setPoolable(poolable);
  }

  @Override
  public void closeOnCompletion() throws SQLException {
    thisPreparedStatement.closeOnCompletion();
  }

  @Override
  public boolean isCloseOnCompletion() throws SQLException {
    return thisPreparedStatement.isCloseOnCompletion();
  }

  @Override
  public <T> T unwrap(Class<T> iface) throws SQLException {
    return thisPreparedStatement.unwrap(iface);
  }

  @Override
  public boolean isWrapperFor(Class<?> iface) throws SQLException {
    return thisPreparedStatement.isWrapperFor(iface);
  }

  @Override
  public ResultSet executeQuery() throws SQLException {
    return new DefaultResultSet();
  }

  @Override
  public int executeUpdate() throws SQLException {
    return thisPreparedStatement.executeUpdate();
  }

  @Override
  public void setNull(int parameterIndex, int sqlType) throws SQLException {
    thisPreparedStatement.setNull(parameterIndex, sqlType);
  }

  @Override
  public void setBoolean(int parameterIndex, boolean x) throws SQLException {
    thisPreparedStatement.setBoolean(parameterIndex, x);
  }

  @Override
  public void setByte(int parameterIndex, byte x) throws SQLException {
    thisPreparedStatement.setByte(parameterIndex, x);
  }

  @Override
  public void setShort(int parameterIndex, short x) throws SQLException {
    thisPreparedStatement.setShort(parameterIndex, x);
  }

  @Override
  public void setInt(int parameterIndex, int x) throws SQLException {
    thisPreparedStatement.setInt(parameterIndex, x);
  }

  @Override
  public void setLong(int parameterIndex, long x) throws SQLException {
    thisPreparedStatement.setLong(parameterIndex, x);
  }

  @Override
  public void setFloat(int parameterIndex, float x) throws SQLException {
    thisPreparedStatement.setFloat(parameterIndex, x);
  }

  @Override
  public void setDouble(int parameterIndex, double x) throws SQLException {
    thisPreparedStatement.setDouble(parameterIndex, x);
  }

  @Override
  public void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException {
    thisPreparedStatement.setBigDecimal(parameterIndex, x);
  }

  @Override
  public void setString(int parameterIndex, String x) throws SQLException {
    thisPreparedStatement.setString(parameterIndex, x);
  }

  @Override
  public void setBytes(int parameterIndex, byte[] x) throws SQLException {
    thisPreparedStatement.setBytes(parameterIndex, x);
  }

  @Override
  public void setDate(int parameterIndex, Date x) throws SQLException {
    thisPreparedStatement.setDate(parameterIndex, x);
  }

  @Override
  public void setTime(int parameterIndex, Time x) throws SQLException {
    thisPreparedStatement.setTime(parameterIndex, x);
  }

  @Override
  public void setTimestamp(int parameterIndex, Timestamp x) throws SQLException {
    thisPreparedStatement.setTimestamp(parameterIndex, x);
  }

  @Override
  public void setAsciiStream(int parameterIndex, InputStream x, int length) throws SQLException {
    thisPreparedStatement.setAsciiStream(parameterIndex, x);
  }

  @SuppressWarnings("deprecation")
  @Override
  public void setUnicodeStream(int parameterIndex, InputStream x, int length) throws SQLException {
    thisPreparedStatement.setUnicodeStream(parameterIndex, x, length);
  }

  @Override
  public void setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException {
    thisPreparedStatement.setBinaryStream(parameterIndex, x, length);
  }

  @Override
  public void clearParameters() throws SQLException {
    thisPreparedStatement.clearParameters();
  }

  @Override
  public void setObject(int parameterIndex, Object x, int targetSqlType) throws SQLException {
    thisPreparedStatement.setObject(parameterIndex, x, targetSqlType);
  }

  @Override
  public void setObject(int parameterIndex, Object x) throws SQLException {
    thisPreparedStatement.setObject(parameterIndex, x);
  }

  @Override
  public boolean execute() throws SQLException {
    return thisPreparedStatement.execute();
  }

  @Override
  public void addBatch() throws SQLException {
    thisPreparedStatement.addBatch();
  }

  @Override
  public void setCharacterStream(int parameterIndex, Reader reader, int length) throws SQLException {
    thisPreparedStatement.setCharacterStream(parameterIndex, reader, length);
  }

  @Override
  public void setRef(int parameterIndex, Ref x) throws SQLException {
    thisPreparedStatement.setRef(parameterIndex, x);
  }

  @Override
  public void setBlob(int parameterIndex, Blob x) throws SQLException {
    thisPreparedStatement.setBlob(parameterIndex, x);
  }

  @Override
  public void setClob(int parameterIndex, Clob x) throws SQLException {
    thisPreparedStatement.setClob(parameterIndex, x);
  }

  @Override
  public void setArray(int parameterIndex, Array x) throws SQLException {
    thisPreparedStatement.setArray(parameterIndex, x);
  }

  @Override
  public ResultSetMetaData getMetaData() throws SQLException {
    return thisPreparedStatement.getMetaData();
  }

  @Override
  public void setDate(int parameterIndex, Date x, Calendar cal) throws SQLException {
    thisPreparedStatement.setDate(parameterIndex, x, cal);
  }

  @Override
  public void setTime(int parameterIndex, Time x, Calendar cal) throws SQLException {
    thisPreparedStatement.setTime(parameterIndex, x, cal);
  }

  @Override
  public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal) throws SQLException {
    thisPreparedStatement.setTimestamp(parameterIndex, x, cal);
  }

  @Override
  public void setNull(int parameterIndex, int sqlType, String typeName) throws SQLException {
    thisPreparedStatement.setNull(parameterIndex, sqlType, typeName);
  }

  @Override
  public void setURL(int parameterIndex, URL x) throws SQLException {
    thisPreparedStatement.setURL(parameterIndex, x);
  }

  @Override
  public ParameterMetaData getParameterMetaData() throws SQLException {
    return thisPreparedStatement.getParameterMetaData();
  }

  @Override
  public void setRowId(int parameterIndex, RowId x) throws SQLException {
    thisPreparedStatement.setRowId(parameterIndex, x);
  }

  @Override
  public void setNString(int parameterIndex, String value) throws SQLException {
    thisPreparedStatement.setNString(parameterIndex, value);
  }

  @Override
  public void setNCharacterStream(int parameterIndex, Reader value, long length) throws SQLException {
    thisPreparedStatement.setNCharacterStream(parameterIndex, value, length);
  }

  @Override
  public void setNClob(int parameterIndex, NClob value) throws SQLException {
    thisPreparedStatement.setNClob(parameterIndex, value);
  }

  @Override
  public void setClob(int parameterIndex, Reader reader, long length) throws SQLException {
    thisPreparedStatement.setClob(parameterIndex, reader, length);
  }

  @Override
  public void setBlob(int parameterIndex, InputStream inputStream, long length) throws SQLException {
    thisPreparedStatement.setBlob(parameterIndex, inputStream, length);
  }

  @Override
  public void setNClob(int parameterIndex, Reader reader, long length) throws SQLException {
    thisPreparedStatement.setNClob(parameterIndex, reader, length);
  }

  @Override
  public void setSQLXML(int parameterIndex, SQLXML xmlObject) throws SQLException {
    thisPreparedStatement.setSQLXML(parameterIndex, xmlObject);
  }

  @Override
  public void setObject(int parameterIndex, Object x, int targetSqlType, int scaleOrLength) throws SQLException {
    thisPreparedStatement.setObject(parameterIndex, x, targetSqlType, scaleOrLength);
  }

  @Override
  public void setAsciiStream(int parameterIndex, InputStream x, long length) throws SQLException {
    thisPreparedStatement.setAsciiStream(parameterIndex, x, length);
  }

  @Override
  public void setBinaryStream(int parameterIndex, InputStream x, long length) throws SQLException {
    thisPreparedStatement.setBinaryStream(parameterIndex, x, length);
  }

  @Override
  public void setCharacterStream(int parameterIndex, Reader reader, long length) throws SQLException {
    thisPreparedStatement.setCharacterStream(parameterIndex, reader, length);
  }

  @Override
  public void setAsciiStream(int parameterIndex, InputStream x) throws SQLException {
    thisPreparedStatement.setAsciiStream(parameterIndex, x);
  }

  @Override
  public void setBinaryStream(int parameterIndex, InputStream x) throws SQLException {
    thisPreparedStatement.setBinaryStream(parameterIndex, x);
  }

  @Override
  public void setCharacterStream(int parameterIndex, Reader reader) throws SQLException {
    thisPreparedStatement.setCharacterStream(parameterIndex, reader);
  }

  @Override
  public void setNCharacterStream(int parameterIndex, Reader value) throws SQLException {
    thisPreparedStatement.setNCharacterStream(parameterIndex, value);
  }

  @Override
  public void setClob(int parameterIndex, Reader reader) throws SQLException {
    thisPreparedStatement.setClob(parameterIndex, reader);
  }

  @Override
  public void setBlob(int parameterIndex, InputStream inputStream) throws SQLException {
    thisPreparedStatement.setBlob(parameterIndex, inputStream);
  }

  @Override
  public void setNClob(int parameterIndex, Reader reader) throws SQLException {
    thisPreparedStatement.setNClob(parameterIndex, reader);
  }
}
