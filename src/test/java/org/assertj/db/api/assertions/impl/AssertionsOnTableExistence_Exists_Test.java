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

import org.assertj.core.api.Assertions;
import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.db.api.AbstractDbAssert;
import org.assertj.db.api.TableAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Source;
import org.assertj.db.type.Table;
import org.junit.Test;

import javax.sql.DataSource;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on {@link AssertionsOnTableExistence} class :
 * {@link AssertionsOnTableExistence#exists(AbstractDbAssert, WritableAssertionInfo, String, Source, DataSource)} method.
 *
 * @author Julien Roy
 *
 */
public class AssertionsOnTableExistence_Exists_Test extends AbstractTest {

  /**
   * This method tests the {@code exists} assertion method.
   */
  @Test
  public void test_exists() {
    WritableAssertionInfo info = new WritableAssertionInfo();
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    TableAssert tableAssert2 = AssertionsOnTableExistence.exists(tableAssert, info, "TEST", source, null);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    TableAssert tableAssert3 = AssertionsOnTableExistence.exists(tableAssert, info, "TEST", null, dataSource);
    Assertions.assertThat(tableAssert3).isSameAs(tableAssert);
  }

  /**
   * This method should fail because the table does not exist.
   */
  @Test
  public void should_fail_because_table_does_not_exist() {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    try {
      AssertionsOnTableExistence.exists(tableAssert, info, "not-exist-test", source, null);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                                    + "Expecting exist but do not exist"));
    }

    try {
      AssertionsOnTableExistence.exists(tableAssert, info, "not-exist-test", null, dataSource);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                                    + "Expecting exist but do not exist"));
    }
  }
}