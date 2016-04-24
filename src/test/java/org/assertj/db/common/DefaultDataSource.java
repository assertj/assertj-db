/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2016 the original author or authors.
 */
package org.assertj.db.common;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

/**
 * Default DataSource.
 * 
 * @author Régis Pouiller
 * 
 */
public class DefaultDataSource implements DataSource {

  protected DataSource thisDataSource;

  public DefaultDataSource(DataSource dataSource) {
    this.thisDataSource = dataSource;
  }

  @Override
  public PrintWriter getLogWriter() throws SQLException {
    return thisDataSource.getLogWriter();
  }

  @Override
  public void setLogWriter(PrintWriter out) throws SQLException {
    thisDataSource.setLogWriter(out);
  }

  @Override
  public void setLoginTimeout(int seconds) throws SQLException {
    thisDataSource.setLoginTimeout(seconds);
  }

  @Override
  public int getLoginTimeout() throws SQLException {
    return thisDataSource.getLoginTimeout();
  }

  @Override
  public <T> T unwrap(Class<T> iface) throws SQLException {
    return thisDataSource.unwrap(iface);
  }

  @Override
  public boolean isWrapperFor(Class<?> iface) throws SQLException {
    return thisDataSource.isWrapperFor(iface);
  }

  @Override
  public Connection getConnection() throws SQLException {
    return new DefaultConnection(thisDataSource.getConnection());
  }

  @Override
  public Connection getConnection(String username, String password) throws SQLException {
    return thisDataSource.getConnection(username, password);
  }

  @Override
  public Logger getParentLogger() throws SQLFeatureNotSupportedException {
    return thisDataSource.getParentLogger();
  }
}
