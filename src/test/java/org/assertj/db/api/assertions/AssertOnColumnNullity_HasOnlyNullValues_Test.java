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
import org.assertj.db.api.TableColumnAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Table;
import org.junit.Test;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on {@link AssertOnColumnNullity} class :
 * {@link org.assertj.db.api.assertions.AssertOnColumnNullity#hasOnlyNullValues()} method.
 *
 * @author Régis Pouiller
 *
 */
public class AssertOnColumnNullity_HasOnlyNullValues_Test extends AbstractTest {

  /**
   * This method tests the {@code hasOnlyNullValues} assertion method.
   */
  @Test
  public void test_has_only_null_values() {
    Table table = new Table(source, "test2");
    TableColumnAssert tableColumnAssert = assertThat(table).column("var15");
    TableColumnAssert tableColumnAssert2 = tableColumnAssert.hasOnlyNullValues();
    Assertions.assertThat(tableColumnAssert).isSameAs(tableColumnAssert2);
  }

  /**
   * This method should fail because the column has a not null value.
   */
  @Test
  public void should_fail_because_column_has_not_null_value() throws Exception {
    Table table = new Table(source, "test2");
    TableColumnAssert tableColumnAssert = assertThat(table).column("var14");
    try {
      tableColumnAssert.hasOnlyNullValues();
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[Column at index 13 (column name : VAR14) of TEST2 table] %n"
                                                      + "Expecting to contain only null:%n"
                                                      + "but contains not null at index: 0"));
    }
  }
}
