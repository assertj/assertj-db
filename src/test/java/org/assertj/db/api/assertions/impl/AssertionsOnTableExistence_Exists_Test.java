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
package org.assertj.db.api.assertions.impl;

import static org.junit.Assert.fail;

import java.sql.SQLException;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.db.api.AbstractDbAssert;
import org.assertj.db.api.TableAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.DefaultConnectionProvider;
import org.junit.Test;

/**
 * Tests on {@link AssertionsOnTableExistence} class :
 * {@link AssertionsOnTableExistence#exists(AbstractDbAssert, WritableAssertionInfo, String, org.assertj.db.type.ConnectionProvider)} method.
 *
 * @author Julien Roy
 */
public class AssertionsOnTableExistence_Exists_Test extends AbstractTest {

  /**
   * This method tests the {@code exists} assertion method.
   */
  @Test
  public void test_exists() throws SQLException {
    DefaultConnectionProvider connectionProvider = new DefaultConnectionProvider(this.dataSource.getConnection());
    WritableAssertionInfo info = new WritableAssertionInfo();
    TableAssert tableAssert = new TableAssert(null);
    TableAssert tableAssert2 = AssertionsOnTableExistence.exists(tableAssert, info, "TEST", connectionProvider);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
  }

  /**
   * This method should fail because the table does not exist.
   */
  @Test
  public void should_fail_because_table_does_not_exist() throws SQLException {
    DefaultConnectionProvider connectionProvider = new DefaultConnectionProvider(this.dataSource.getConnection());
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    TableAssert tableAssert = new TableAssert(null);
    try {
      AssertionsOnTableExistence.exists(tableAssert, info, "not-exist-test", connectionProvider);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting exist but do not exist"));
    }
  }
}
