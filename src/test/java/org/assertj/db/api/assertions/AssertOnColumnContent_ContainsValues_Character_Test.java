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
import org.assertj.db.api.TableColumnAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Tests on {@link org.assertj.db.api.assertions.AssertOnColumnContent} class :
 * {@link AssertOnColumnContent#containsValues(Character...)} method.
 *
 * @author Régis Pouiller
 *
 */
public class AssertOnColumnContent_ContainsValues_Character_Test extends AbstractTest {

  /**
   * This method tests the {@code containsValues} assertion method.
   */
  @Test
  public void test_has_values() throws Exception {
    Table table = new Table(source, "test");
    TableColumnAssert tableColumnAssert = assertThat(table).column("var16");
    TableColumnAssert tableColumnAssertReturn = tableColumnAssert.containsValues('T', 'e', 's', 't');
    Assertions.assertThat(tableColumnAssert).isSameAs(tableColumnAssertReturn);

    Table table2 = new Table(source, "test2");
    TableColumnAssert tableColumnAssert2 = assertThat(table2).column("var17");
    TableColumnAssert tableColumnAssertReturn2 = tableColumnAssert2.containsValues(null, 'T');
    Assertions.assertThat(tableColumnAssert2).isSameAs(tableColumnAssertReturn2);
  }

  /**
   * This method should fail because the values are different.
   */
  @Test
  public void should_fail_because_values_are_different() throws Exception {
    Table table = new Table(source, "test");
    TableColumnAssert tableColumnAssert = assertThat(table).column("var16");
    try {
      tableColumnAssert.containsValues('t', 'e', 's', 't');
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[Column at index 15 (column name : VAR16) of TEST table] %n"
                                                                    + "Expecting:%n"
                                                                    + "  <[\"T\", \"e\", \"s\", \"t\"]>%n"
                                                                    + "to contain: %n"
                                                                    + "  <['t', 'e', 's', 't']>%n"
                                                                    + " (parameter <'t'> at index 3 is not found)"));
    }
    Table table2 = new Table(source, "test2");
    TableColumnAssert tableColumnAssert2 = assertThat(table2).column("var17");
    try {
      tableColumnAssert2.containsValues('T', 'T');
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format(
              "[Column at index 16 (column name : VAR17) of TEST2 table] %n"
              + "Expecting:%n"
              + "  <[\"T\", null]>%n"
              + "to contain: %n"
              + "  <['T', 'T']>%n"
              + " (parameter <'T'> at index 1 is not found)"));
    }
  }
}

