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
 * Copyright 2012-2016 the original author or authors.
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
 * Tests on {@link org.assertj.db.api.assertions.AssertOnColumnContent} class :
 * {@link AssertOnColumnContent#containsValues(Number...)} method.
 *
 * @author Régis Pouiller
 *
 */
public class AssertOnColumnContent_ContainsValues_Number_Test extends AbstractTest {

  /**
   * This method tests the {@code containsValues} assertion method.
   */
  @Test
  public void test_has_values() throws Exception {
    Table table = new Table(source, "test");
    TableColumnAssert tableColumnAssert = assertThat(table).column("var3");
    TableColumnAssert tableColumnAssertReturn = tableColumnAssert.containsValues(2, 20, 25, 0);
    Assertions.assertThat(tableColumnAssert).isSameAs(tableColumnAssertReturn);

    Table table2 = new Table(source, "test2");
    TableColumnAssert tableColumnAssert2 = assertThat(table2).column("var3");
    TableColumnAssert tableColumnAssertReturn2 = tableColumnAssert2.containsValues(2, null);
    Assertions.assertThat(tableColumnAssert2).isSameAs(tableColumnAssertReturn2);

    TableColumnAssert tableColumnAssert3 = assertThat(table).column("var4");
    TableColumnAssert tableColumnAssertReturn3 = tableColumnAssert3.containsValues(3, 30, 300, 0);
    Assertions.assertThat(tableColumnAssert3).isSameAs(tableColumnAssertReturn3);

    TableColumnAssert tableColumnAssert4 = assertThat(table2).column("var4");
    TableColumnAssert tableColumnAssertReturn4 = tableColumnAssert4.containsValues(3, null);
    Assertions.assertThat(tableColumnAssert4).isSameAs(tableColumnAssertReturn4);

    TableColumnAssert tableColumnAssert5 = assertThat(table).column("var5");
    TableColumnAssert tableColumnAssertReturn5 = tableColumnAssert5.containsValues(4, 40, 400, 0);
    Assertions.assertThat(tableColumnAssert5).isSameAs(tableColumnAssertReturn5);

    TableColumnAssert tableColumnAssert6 = assertThat(table2).column("var5");
    TableColumnAssert tableColumnAssertReturn6 = tableColumnAssert6.containsValues(4, null);
    Assertions.assertThat(tableColumnAssert6).isSameAs(tableColumnAssertReturn6);

    TableColumnAssert tableColumnAssert7 = assertThat(table).column("var6");
    TableColumnAssert tableColumnAssertReturn7 = tableColumnAssert7.containsValues(5.6, 50.6, 500.6, 0);
    Assertions.assertThat(tableColumnAssert7).isSameAs(tableColumnAssertReturn7);

    TableColumnAssert tableColumnAssert8 = assertThat(table2).column("var6");
    TableColumnAssert tableColumnAssertReturn8 = tableColumnAssert8.containsValues(5.6, null);
    Assertions.assertThat(tableColumnAssert8).isSameAs(tableColumnAssertReturn8);

    TableColumnAssert tableColumnAssert9 = assertThat(table).column("var7");
    TableColumnAssert tableColumnAssertReturn9 = tableColumnAssert9.containsValues(7.8, 70.8, 700.8, 0);
    Assertions.assertThat(tableColumnAssert9).isSameAs(tableColumnAssertReturn9);

    TableColumnAssert tableColumnAssert10 = assertThat(table2).column("var7");
    TableColumnAssert tableColumnAssertReturn10 = tableColumnAssert10.containsValues(7.8, null);
    Assertions.assertThat(tableColumnAssert10).isSameAs(tableColumnAssertReturn10);

    TableColumnAssert tableColumnAssert11 = assertThat(table).column("var13");
    TableColumnAssert tableColumnAssertReturn11 = tableColumnAssert11.containsValues(5, 50, 500, 500);
    Assertions.assertThat(tableColumnAssert11).isSameAs(tableColumnAssertReturn11);

    TableColumnAssert tableColumnAssert12 = assertThat(table2).column("var13");
    TableColumnAssert tableColumnAssertReturn12 = tableColumnAssert12.containsValues(5, null);
    Assertions.assertThat(tableColumnAssert12).isSameAs(tableColumnAssertReturn12);

    TableColumnAssert tableColumnAssert13 = assertThat(table).column("var14");
    TableColumnAssert tableColumnAssertReturn13 = tableColumnAssert13.containsValues(7, 70, 700, 700);
    Assertions.assertThat(tableColumnAssert13).isSameAs(tableColumnAssertReturn13);

    TableColumnAssert tableColumnAssert14 = assertThat(table2).column("var14");
    TableColumnAssert tableColumnAssertReturn14 = tableColumnAssert14.containsValues(7, null);
    Assertions.assertThat(tableColumnAssert14).isSameAs(tableColumnAssertReturn14);
  }

  /**
   * This method should fail because the values are different.
   */
  @Test
  public void should_fail_because_values_are_different() throws Exception {
    Table table = new Table(source, "test");
    TableColumnAssert tableColumnAssert = assertThat(table).column("var3");
    try {
      tableColumnAssert.containsValues(2, 20, 35, 0);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[Column at index 2 (column name : VAR3) of TEST table] %n"
                                                                    + "Expecting:%n"
                                                                    + "  <[2, 20, 25, 0]>%n"
                                                                    + "to contain: %n"
                                                                    + "  <[2, 20, 35, 0]>%n"
                                                                    + " (parameter <35> at index 2 is not found)"));
    }
    Table table2 = new Table(source, "test2");
    TableColumnAssert tableColumnAssert2 = assertThat(table2).column("var3");
    try {
      tableColumnAssert2.containsValues(2, 2);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[Column at index 2 (column name : VAR3) of TEST2 table] %n"
                                                                    + "Expecting:%n"
                                                                    + "  <[2, null]>%n"
                                                                    + "to contain: %n"
                                                                    + "  <[2, 2]>%n"
                                                                    + " (parameter <2> at index 1 is not found)"));
    }

    TableColumnAssert tableColumnAssert3 = assertThat(table).column("var4");
    try {
      tableColumnAssert3.containsValues(3, 35, 300, 0);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[Column at index 3 (column name : VAR4) of TEST table] %n"
                                                                    + "Expecting:%n"
                                                                    + "  <[3, 30, 300, 0]>%n"
                                                                    + "to contain: %n"
                                                                    + "  <[3, 35, 300, 0]>%n"
                                                                    + " (parameter <35> at index 1 is not found)"));
    }

    TableColumnAssert tableColumnAssert4 = assertThat(table2).column("var4");
    try {
      tableColumnAssert4.containsValues(3, 3);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[Column at index 3 (column name : VAR4) of TEST2 table] %n"
                                                                    + "Expecting:%n"
                                                                    + "  <[3, null]>%n"
                                                                    + "to contain: %n"
                                                                    + "  <[3, 3]>%n"
                                                                    + " (parameter <3> at index 1 is not found)"));
    }

    TableColumnAssert tableColumnAssert5 = assertThat(table).column("var5");
    try {
      tableColumnAssert5.containsValues(4, 45, 400, 0);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[Column at index 4 (column name : VAR5) of TEST table] %n"
                                                                    + "Expecting:%n"
                                                                    + "  <[4L, 40L, 400L, 0L]>%n"
                                                                    + "to contain: %n"
                                                                    + "  <[4, 45, 400, 0]>%n"
                                                                    + " (parameter <45> at index 1 is not found)"));
    }

    TableColumnAssert tableColumnAssert6 = assertThat(table2).column("var5");
    try {
      tableColumnAssert6.containsValues(4, 4);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[Column at index 4 (column name : VAR5) of TEST2 table] %n"
                                                                    + "Expecting:%n"
                                                                    + "  <[4L, null]>%n"
                                                                    + "to contain: %n"
                                                                    + "  <[4, 4]>%n"
                                                                    + " (parameter <4> at index 1 is not found)"));
    }

    TableColumnAssert tableColumnAssert7 = assertThat(table).column("var6");
    try {
      tableColumnAssert7.containsValues(5.6, 55.6, 500.6, 0);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[Column at index 5 (column name : VAR6) of TEST table] %n"
                                                                    + "Expecting:%n"
                                                                    + "  <[5.60, 50.60, 500.60, 0.00]>%n"
                                                                    + "to contain: %n"
                                                                    + "  <[5.6, 55.6, 500.6, 0]>%n"
                                                                    + " (parameter <55.6> at index 1 is not found)"));
    }

    TableColumnAssert tableColumnAssert8 = assertThat(table2).column("var6");
    try {
      tableColumnAssert8.containsValues(5.6, 5.6);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[Column at index 5 (column name : VAR6) of TEST2 table] %n"
                                                                    + "Expecting:%n"
                                                                    + "  <[5.60, null]>%n"
                                                                    + "to contain: %n"
                                                                    + "  <[5.6, 5.6]>%n"
                                                                    + " (parameter <5.6> at index 1 is not found)"));
    }

    TableColumnAssert tableColumnAssert9 = assertThat(table).column("var7");
    try {
      tableColumnAssert9.containsValues(7.8, 75.8, 700.8, 0);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[Column at index 6 (column name : VAR7) of TEST table] %n"
                                                                    + "Expecting:%n"
                                                                    + "  <[7.8f, 70.8f, 700.8f, 0.0f]>%n"
                                                                    + "to contain: %n"
                                                                    + "  <[7.8, 75.8, 700.8, 0]>%n"
                                                                    + " (parameter <75.8> at index 1 is not found)"));
    }

    TableColumnAssert tableColumnAssert10 = assertThat(table2).column("var7");
    try {
      tableColumnAssert10.containsValues(7.8, 7.8);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[Column at index 6 (column name : VAR7) of TEST2 table] %n"
                                                                    + "Expecting:%n"
                                                                    + "  <[7.8f, null]>%n"
                                                                    + "to contain: %n"
                                                                    + "  <[7.8, 7.8]>%n"
                                                                    + " (parameter <7.8> at index 1 is not found)"));
    }

    TableColumnAssert tableColumnAssert11 = assertThat(table).column("var13");
    try {
      tableColumnAssert11.containsValues(5, 50, 505, 500);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[Column at index 12 (column name : VAR13) of TEST table] %n"
                                                                    + "Expecting:%n"
                                                                    + "  <[5.00, 50.00, 500.00, 500.00]>%n"
                                                                    + "to contain: %n"
                                                                    + "  <[5, 50, 505, 500]>%n"
                                                                    + " (parameter <505> at index 2 is not found)"));
    }

    TableColumnAssert tableColumnAssert12 = assertThat(table2).column("var13");
    try {
      tableColumnAssert12.containsValues(5, 5);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[Column at index 12 (column name : VAR13) of TEST2 table] %n"
                                                                    + "Expecting:%n"
                                                                    + "  <[5.00, null]>%n"
                                                                    + "to contain: %n"
                                                                    + "  <[5, 5]>%n"
                                                                    + " (parameter <5> at index 1 is not found)"));
    }

    TableColumnAssert tableColumnAssert13 = assertThat(table).column("var14");
    try {
      tableColumnAssert13.containsValues(7, 70, 750, 700);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[Column at index 13 (column name : VAR14) of TEST table] %n"
                                                                    + "Expecting:%n"
                                                                    + "  <[7.0f, 70.0f, 700.0f, 700.0f]>%n"
                                                                    + "to contain: %n"
                                                                    + "  <[7, 70, 750, 700]>%n"
                                                                    + " (parameter <750> at index 2 is not found)"));
    }

    TableColumnAssert tableColumnAssert14 = assertThat(table2).column("var14");
    try {
      tableColumnAssert14.containsValues(7, 7);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[Column at index 13 (column name : VAR14) of TEST2 table] %n"
                                                                    + "Expecting:%n"
                                                                    + "  <[7.0f, null]>%n"
                                                                    + "to contain: %n"
                                                                    + "  <[7, 7]>%n"
                                                                    + " (parameter <7> at index 1 is not found)"));
    }
  }
}
