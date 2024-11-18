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
package org.assertj.db.api.assertions.impl;

import static org.assertj.db.error.ShouldExist.shouldExist;
import static org.assertj.db.error.ShouldNotExist.shouldNotExist;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.core.internal.Failures;
import org.assertj.db.api.AbstractDbAssert;
import org.assertj.db.exception.AssertJDBException;
import org.assertj.db.type.ConnectionProvider;

/**
 * Implements the assertion method on the existence of a table.
 *
 * @author Avinash Ananth Narayan
 */
public class AssertionsOnTableExistence {

  /**
   * To notice failures in the assertion.
   */
  private static final Failures failures = Failures.instance();

  private AssertionsOnTableExistence() {
    // Empty
  }

  /**
   * Verifies that the table exists.
   *
   * @param <A>                The type of the assertion which call this method.
   * @param assertion          The assertion which call this method.
   * @param info               Writable information about an assertion.
   * @param table              The table name to search in DB.
   * @param connectionProvider The provider to connect to DB.
   * @return {@code this} assertion object.
   * @throws AssertionError If the table does not exist.
   */
  public static <A extends AbstractDbAssert<?, ?, ?, ?, ?, ?>> A exists(A assertion, WritableAssertionInfo info,
                                                                        String table, ConnectionProvider connectionProvider) {
    if (connectionProvider == null) {
      throw new NullPointerException("connectionProvider must be not null");
    }
    try (Connection connection = connectionProvider.getConnection()) {
      DatabaseMetaData metaData = connection.getMetaData();
      ResultSet result = metaData.getTables(null, null, table, null);
      if (!result.next()) {
        throw failures.failure(info, shouldExist());
      }
      result.close();
    } catch (SQLException e) {
      throw new AssertJDBException(e);
    }
    return assertion;
  }


  /**
   * Verifies that the database not contains the table.
   *
   * @param <A>                The type of the assertion which call this method.
   * @param assertion          The assertion which call this method.
   * @param info               Writable information about an assertion.
   * @param table              The table name to search in DB.
   * @param connectionProvider The provider to connect to DB.
   * @return {@code this} assertion object.
   * @throws AssertionError If the table does not exist.
   */
  public static <A extends AbstractDbAssert<?, ?, ?, ?, ?, ?>> A doesNotExists(A assertion, WritableAssertionInfo info,
                                                                               String table, ConnectionProvider connectionProvider) {
    if (connectionProvider == null) {
      throw new NullPointerException("connectionProvider must be not null");
    }
    try (Connection connection = connectionProvider.getConnection()) {
      DatabaseMetaData metaData = connection.getMetaData();
      ResultSet result = metaData.getTables(null, null, table, null);
      if (result.next()) {
        throw failures.failure(info, shouldNotExist());
      }
      result.close();
    } catch (SQLException e) {
      throw new AssertJDBException(e);
    }
    return assertion;
  }
}
