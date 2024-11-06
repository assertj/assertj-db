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

import static org.assertj.core.api.Assertions.assertThat;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.DefaultConnection;
import org.assertj.db.type.lettercase.LetterCase;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests on the {@code DataSourceWithLetterCase_Test}.
 * 
 * @author Régis Pouiller
 * 
 */
public class DataSourceWithLetterCase_Test extends AbstractTest {

  private static class MyDataSource implements DataSource {

    private Connection connection;
    private int loginTimeout;
    private PrintWriter printWriter;
    private Logger logger;

    private MyDataSource(DataSource dataSource) throws SQLException {
      connection = new DefaultConnection(dataSource.getConnection());
      loginTimeout = 0;
      printWriter = new PrintWriter(System.out);
      logger = Logger.getGlobal();
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
      return printWriter;
    }

    @Override
    public int getLoginTimeout() throws SQLException {
      return loginTimeout;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
      return logger;
    }

    @Override
    public void setLogWriter(PrintWriter arg0) throws SQLException {
      printWriter = arg0;
    }

    @Override
    public void setLoginTimeout(int arg0) throws SQLException {
      loginTimeout = arg0;
    }

    @Override
    public boolean isWrapperFor(Class<?> arg0) throws SQLException {
      if (Boolean.class.equals(arg0)) {
        return true;
      }
      return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
      if (Boolean.class.equals(iface)) {
        return (T) Boolean.TRUE;
      }
      else if (String.class.equals(iface)) {
        return (T) "test";
      }
      return null;
    }

    @Override
    public Connection getConnection() throws SQLException {
      return connection;
    }

    @Override
    public Connection getConnection(String arg0, String arg1) throws SQLException {
      return connection;
    }
    
  };

  private DataSource delegate;
  private DataSourceWithLetterCase dataSourceWithLetterCase;

  @Before
  public void init() throws SQLException {
    delegate = new MyDataSource(dataSource);
    dataSourceWithLetterCase = new DataSourceWithLetterCase(delegate, LetterCase.TABLE_DEFAULT, LetterCase.COLUMN_DEFAULT, LetterCase.PRIMARY_KEY_DEFAULT);
  }

  /**
   * This method test the {@code getConnection} method.
   * @throws SQLException 
   */
  @Test
  public void test_getConnection() throws SQLException {
    assertThat(dataSourceWithLetterCase.getConnection()).isSameAs(delegate.getConnection());
    assertThat(dataSourceWithLetterCase.getConnection("SA", "")).isSameAs(delegate.getConnection("SA", ""));
  }

  /**
   * This method test the {@code getLogWriter} and {@code setLogWriter} method.
   * @throws SQLException 
   */
  @Test
  public void test_LogWriter() throws SQLException {
    assertThat(dataSourceWithLetterCase.getLogWriter()).isSameAs(delegate.getLogWriter());
    PrintWriter printWriter = new PrintWriter(System.out);
    dataSourceWithLetterCase.setLogWriter(printWriter);
    assertThat(dataSourceWithLetterCase.getLogWriter()).isSameAs(delegate.getLogWriter()).isSameAs(printWriter);
  }

  /**
   * This method test the {@code getLoginTimeout} and {@code setLoginTimeout} method.
   * @throws SQLException 
   */
  @Test
  public void test_LoginTimeout() throws SQLException {
    assertThat(dataSourceWithLetterCase.getLoginTimeout()).isEqualTo(delegate.getLoginTimeout()).isEqualTo(0);
    dataSourceWithLetterCase.setLoginTimeout(10);
    assertThat(dataSourceWithLetterCase.getLoginTimeout()).isEqualTo(delegate.getLoginTimeout()).isEqualTo(10);
  }

  /**
   * This method test the {@code getParentLogger} method.
   * @throws SQLException 
   */
  @Test
  public void test_getParentLogger() throws SQLException {
    assertThat(dataSourceWithLetterCase.getParentLogger()).isSameAs(delegate.getParentLogger());
  }

  /**
   * This method test the {@code isWrapperFor} method.
   * @throws SQLException 
   */
  @Test
  public void test_isWrapperFor() throws SQLException {
    assertThat(dataSourceWithLetterCase.isWrapperFor(Boolean.class)).isEqualTo(delegate.isWrapperFor(Boolean.class)).isTrue();
    assertThat(dataSourceWithLetterCase.isWrapperFor(Integer.class)).isEqualTo(delegate.isWrapperFor(Integer.class)).isFalse();
  }

  /**
   * This method test the {@code unwrap} method.
   * @throws SQLException 
   */
  @Test
  public void test_unwrap() throws SQLException {
    assertThat(dataSourceWithLetterCase.unwrap(Boolean.class)).isEqualTo(delegate.unwrap(Boolean.class)).isTrue();
    assertThat(dataSourceWithLetterCase.unwrap(String.class)).isEqualTo(delegate.unwrap(String.class)).isEqualTo("test");
    assertThat(dataSourceWithLetterCase.unwrap(Integer.class)).isEqualTo(delegate.unwrap(Integer.class)).isNull();
  }
}
