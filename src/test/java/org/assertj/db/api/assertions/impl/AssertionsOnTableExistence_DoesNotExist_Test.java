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

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import javax.sql.DataSource;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.db.api.AbstractDbAssert;
import org.assertj.db.api.TableAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.JdbcUrlConnectionProvider;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Tests on {@link AssertionsOnTableExistence} class :
 * {@link AssertionsOnTableExistence#doesNotExists(AbstractDbAssert, WritableAssertionInfo, String, JdbcUrlConnectionProvider, DataSource)} method.
 *
 * @author Julien Roy
 */
public class AssertionsOnTableExistence_DoesNotExist_Test extends AbstractTest {

  /**
   * This method tests the {@code doesNotExists} assertion method.
   */
  @Test
  public void test_does_not_exists() {
    WritableAssertionInfo info = new WritableAssertionInfo();
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    TableAssert tableAssert2 = AssertionsOnTableExistence.doesNotExists(tableAssert, info, "not-exist-test", jdbcConnectionProvider);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    TableAssert tableAssert3 = AssertionsOnTableExistence.doesNotExists(tableAssert, info, "not-exist-test", dsConnectionProvider);
    Assertions.assertThat(tableAssert3).isSameAs(tableAssert);
  }

  /**
   * This method should fail because the table exists.
   */
  @Test
  public void should_fail_because_table_exists() {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    try {
      AssertionsOnTableExistence.doesNotExists(tableAssert, info, "TEST", jdbcConnectionProvider);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting not exist but exists"));
    }

    try {
      AssertionsOnTableExistence.doesNotExists(tableAssert, info, "TEST", dsConnectionProvider);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting not exist but exists"));
    }
  }
}
