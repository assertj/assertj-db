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
 * Copyright 2015-2021 the original author or authors.
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
 * Tests on {@link AssertOnColumnEquality} class :
 * {@link org.assertj.db.api.assertions.AssertOnColumnEquality#hasValues(Boolean...)} method.
 *
 * @author Régis Pouiller
 *
 */
public class AssertOnColumnEquality_HasValues_Boolean_Test extends AbstractTest {

  /**
   * This method tests the {@code hasValues} assertion method.
   */
  @Test
  public void test_has_values() throws Exception {
    Table table = new Table(source, "test");
    TableColumnAssert tableColumnAssert = assertThat(table).column("var2");
    TableColumnAssert tableColumnAssertReturn = tableColumnAssert.hasValues(true, false, false, false);
    Assertions.assertThat(tableColumnAssert).isSameAs(tableColumnAssertReturn);

    Table table2 = new Table(source, "test2");
    TableColumnAssert tableColumnAssert2 = assertThat(table2).column("var2");
    TableColumnAssert tableColumnAssertReturn2 = tableColumnAssert2.hasValues(true, null);
    Assertions.assertThat(tableColumnAssert2).isSameAs(tableColumnAssertReturn2);
  }

  /**
   * This method should fail because the values are different.
   */
  @Test
  public void should_fail_because_values_are_different() throws Exception {
    Table table = new Table(source, "test");
    TableColumnAssert tableColumnAssert = assertThat(table).column("var2");
    try {
      tableColumnAssert.hasValues(true, false, true, false);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[Column at index 1 (column name : VAR2) of TEST table] %n"
                                                      + "Expecting that the value at index 2:%n"
                                                      + "  <false>%n"
                                                      + "to be equal to: %n"
                                                      + "  <true>"));
    }
    Table table2 = new Table(source, "test2");
    TableColumnAssert tableColumnAssert2 = assertThat(table2).column("var2");
    try {
      tableColumnAssert2.hasValues(true, true);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[Column at index 1 (column name : VAR2) of TEST2 table] %n"
                                                      + "Expecting that the value at index 1:%n"
                                                      + "  <null>%n"
                                                      + "to be equal to: %n"
                                                      + "  <true>"));
    }
  }
}
