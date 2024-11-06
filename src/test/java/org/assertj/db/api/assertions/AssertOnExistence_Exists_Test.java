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
package org.assertj.db.api.assertions;

import org.assertj.core.api.Assertions;
import org.assertj.db.api.TableAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.assertj.db.type.Table;
import org.junit.Test;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on {@link AssertOnExistence} class :
 * {@link AssertOnExistence#exists()} method.
 *
 * @author Julien Roy
 *
 */
public class AssertOnExistence_Exists_Test extends AbstractTest {

  /**
   * This method tests the {@code exists} assertion method.
   */
  @Test
  @NeedReload
  public void test_table_exists() {
    Table table = new Table(source, "test");
    TableAssert tableAssert = assertThat(table);
    TableAssert tableAssertExistReturn = tableAssert.exists();
    Assertions.assertThat(tableAssert).isSameAs(tableAssertExistReturn);
  }

  /**
   * This method should fail because the table not exist.
   */
  @Test
  @NeedReload
  public void should_fail_because_table_not_exist() {
    Table table = new Table(source, "not-exist-test");

    try {
      assertThat(table).exists();
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[not-exist-test table] %n"
                                                                    + "Expecting exist but do not exist"));
    }
  }
}
