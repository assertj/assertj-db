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

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import org.assertj.core.api.Assertions;
import org.assertj.db.api.TableRowAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Tests on {@link AssertOnRowNullity} class :
 * {@link org.assertj.db.api.assertions.AssertOnRowNullity#hasOnlyNotNullValues()} method.
 */
public class AssertOnRowNullity_HasOnlyNotNullValues_Test extends AbstractTest {

  /**
   * This method tests the {@code hasOnlyNotNullValues} assertion method.
   */
  @Test
  public void test_has_only_not_null_values() {
    Table table = assertDbConnection.table("test").build();
    TableRowAssert tableRowAssert = assertThat(table).row();
    TableRowAssert tableRowAssert2 = tableRowAssert.hasOnlyNotNullValues();
    Assertions.assertThat(tableRowAssert).isSameAs(tableRowAssert2);
  }

  /**
   * This method should fail because the row has a null value.
   */
  @Test
  public void should_fail_because_row_has_null_value() {
    Table table = assertDbConnection.table("test2").build();
    TableRowAssert tableRowAssert = assertThat(table).row().row();
    try {
      tableRowAssert.hasOnlyNotNullValues();
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[Row at index 1 of TEST2 table] %n"
        + "Expecting to contain only not null:%n"
        + "but contains null at index: 0"));
    }
  }
}
