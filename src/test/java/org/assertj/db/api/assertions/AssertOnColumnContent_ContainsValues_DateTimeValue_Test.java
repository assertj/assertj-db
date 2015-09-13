/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2015 the original author or authors.
 */
package org.assertj.db.api.assertions;

import org.assertj.core.api.Assertions;
import org.assertj.db.api.TableColumnAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.DateTimeValue;
import org.assertj.db.type.DateValue;
import org.assertj.db.type.Table;
import org.assertj.db.type.TimeValue;
import org.junit.Test;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on {@link org.assertj.db.api.assertions.AssertOnColumnContent} class :
 * {@link AssertOnColumnContent#containsValues(org.assertj.db.type.DateTimeValue...)} method.
 *
 * @author Régis Pouiller
 *
 */
public class AssertOnColumnContent_ContainsValues_DateTimeValue_Test extends AbstractTest {

  /**
   * This method tests the {@code containsValues} assertion method.
   */
  @Test
  public void test_has_values() throws Exception {
    Table table = new Table(source, "test");
    TableColumnAssert tableColumnAssert = assertThat(table).column("var10");
    TableColumnAssert tableColumnAssertReturn = tableColumnAssert.containsValues(
            DateTimeValue.of(DateValue.of(2014, 5, 24), TimeValue.of(9, 46, 30)),
            DateTimeValue.of(DateValue.of(2014, 5, 30), TimeValue.of(12, 29, 49)),
            DateTimeValue.of(DateValue.of(2014, 5, 30)),
            DateTimeValue.of(DateValue.of(2014, 5, 30)));
    Assertions.assertThat(tableColumnAssert).isSameAs(tableColumnAssertReturn);

    Table table2 = new Table(source, "test2");
    TableColumnAssert tableColumnAssert2 = assertThat(table2).column("var10");
    TableColumnAssert tableColumnAssertReturn2 = tableColumnAssert2.containsValues(
            DateTimeValue.of(DateValue.of(2014, 5, 24), TimeValue.of(9, 46, 30)),
            null);
    Assertions.assertThat(tableColumnAssert2).isSameAs(tableColumnAssertReturn2);

    TableColumnAssert tableColumnAssert3 = assertThat(table).column("var9");
    TableColumnAssert tableColumnAssertReturn3 = tableColumnAssert3.containsValues(
            DateTimeValue.of(DateValue.of(2014, 5, 24)),
            DateTimeValue.of(DateValue.of(2014, 5, 30)),
            DateTimeValue.of(DateValue.of(2014, 5, 30)),
            DateTimeValue.of(DateValue.of(2014, 5, 30)));
    Assertions.assertThat(tableColumnAssert3).isSameAs(tableColumnAssertReturn3);

    TableColumnAssert tableColumnAssert4 = assertThat(table2).column("var9");
    TableColumnAssert tableColumnAssertReturn4 = tableColumnAssert4.containsValues(
            DateTimeValue.of(DateValue.of(2014, 5, 24)),
            null);
    Assertions.assertThat(tableColumnAssert4).isSameAs(tableColumnAssertReturn4);
  }

  /**
   * This method should fail because the values are different.
   */
  @Test
  public void should_fail_because_values_are_different() throws Exception {
    Table table = new Table(source, "test");
    TableColumnAssert tableColumnAssert = assertThat(table).column("var10");
    try {
      tableColumnAssert.containsValues(DateTimeValue.of(DateValue.of(2014, 5, 24), TimeValue.of(9, 46, 30)),
                                       DateTimeValue.of(DateValue.of(2014, 5, 30), TimeValue.of(13, 29, 49)),
                                       DateTimeValue.of(DateValue.of(2014, 5, 30)),
                                       DateTimeValue.of(DateValue.of(2014, 5, 30)));
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[Column at index 9 (column name : VAR10) of test table] %n"
                                                                    + "Expecting:%n"
                                                                    + "  <[2014-05-24T09:46:30.000000000,%n"
                                                                    + "    2014-05-30T12:29:49.000000000,%n"
                                                                    + "    2014-05-30T00:00:00.000000000,%n"
                                                                    + "    2014-05-30T00:00:00.000000000]>%n"
                                                                    + "to contain: %n"
                                                                    + "  <[2014-05-24T09:46:30.000000000,%n"
                                                                    + "    2014-05-30T13:29:49.000000000,%n"
                                                                    + "    2014-05-30T00:00:00.000000000,%n"
                                                                    + "    2014-05-30T00:00:00.000000000]>%n"
                                                                    + " (parameter <2014-05-30T13:29:49.000000000> at index 1 is not found)"));
    }
    Table table2 = new Table(source, "test2");
    TableColumnAssert tableColumnAssert2 = assertThat(table2).column("var10");
    try {
      tableColumnAssert2.containsValues(DateTimeValue.of(DateValue.of(2014, 5, 24), TimeValue.of(9, 46, 30)),
                                        DateTimeValue.of(DateValue.of(2014, 5, 24), TimeValue.of(9, 46, 30)));
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[Column at index 9 (column name : VAR10) of test2 table] %n"
                                                                    + "Expecting:%n"
                                                                    + "  <[2014-05-24T09:46:30.000000000, null]>%n"
                                                                    + "to contain: %n"
                                                                    + "  <[2014-05-24T09:46:30.000000000, 2014-05-24T09:46:30.000000000]>%n"
                                                                    + " (parameter <2014-05-24T09:46:30.000000000> at index 1 is not found)"));
    }
    TableColumnAssert tableColumnAssert3 = assertThat(table).column("var9");
    try {
      tableColumnAssert3.containsValues(DateTimeValue.of(DateValue.of(2014, 5, 24)),
                                        DateTimeValue.of(DateValue.of(2014, 5, 24)),
                                        DateTimeValue.of(DateValue.of(2014, 5, 30)),
                                        DateTimeValue.of(DateValue.of(2014, 5, 30)));
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[Column at index 8 (column name : VAR9) of test table] %n"
                                                                    + "Expecting:%n"
                                                                    + "  <[2014-05-24T00:00:00.000,%n"
                                                                    + "    2014-05-30T00:00:00.000,%n"
                                                                    + "    2014-05-30T00:00:00.000,%n"
                                                                    + "    2014-05-30T00:00:00.000]>%n"
                                                                    + "to contain: %n"
                                                                    + "  <[2014-05-24T00:00:00.000000000,%n"
                                                                    + "    2014-05-24T00:00:00.000000000,%n"
                                                                    + "    2014-05-30T00:00:00.000000000,%n"
                                                                    + "    2014-05-30T00:00:00.000000000]>%n"
                                                                    + " (parameter <2014-05-24T00:00:00.000000000> at index 1 is not found)"));
    }
    TableColumnAssert tableColumnAssert4 = assertThat(table2).column("var9");
    try {
      tableColumnAssert4.containsValues(DateTimeValue.of(DateValue.of(2014, 5, 24)),
                                        DateTimeValue.of(DateValue.of(2014, 5, 24)));
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[Column at index 8 (column name : VAR9) of test2 table] %n"
                                                                    + "Expecting:%n"
                                                                    + "  <[2014-05-24T00:00:00.000, null]>%n"
                                                                    + "to contain: %n"
                                                                    + "  <[2014-05-24T00:00:00.000000000, 2014-05-24T00:00:00.000000000]>%n"
                                                                    + " (parameter <2014-05-24T00:00:00.000000000> at index 1 is not found)"));
    }
  }
}
