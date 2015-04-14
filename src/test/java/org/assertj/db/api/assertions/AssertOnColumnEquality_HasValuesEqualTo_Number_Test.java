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
 * Copyright 2012-2014 the original author or authors.
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
 * {@link org.assertj.db.api.assertions.AssertOnColumnEquality#hasValuesEqualTo(Number...)} method.
 *
 * @author Régis Pouiller
 *
 */
public class AssertOnColumnEquality_HasValuesEqualTo_Number_Test extends AbstractTest {

  /**
   * This method tests the {@code hasValuesEqualTo} assertion method.
   */
  @Test
  public void test_has_values_equal_to() throws Exception {
    Table table = new Table(source, "test");
    TableColumnAssert tableColumnAssert = assertThat(table).column("var3");
    TableColumnAssert tableColumnAssertReturn = tableColumnAssert.hasValuesEqualTo(2, 20, 25, 0);
    Assertions.assertThat(tableColumnAssert).isSameAs(tableColumnAssertReturn);

    Table table2 = new Table(source, "test2");
    TableColumnAssert tableColumnAssert2 = assertThat(table2).column("var3");
    TableColumnAssert tableColumnAssertReturn2 = tableColumnAssert2.hasValuesEqualTo(2, null);
    Assertions.assertThat(tableColumnAssert2).isSameAs(tableColumnAssertReturn2);

    TableColumnAssert tableColumnAssert3 = assertThat(table).column("var4");
    TableColumnAssert tableColumnAssertReturn3 = tableColumnAssert3.hasValuesEqualTo(3, 30, 300, 0);
    Assertions.assertThat(tableColumnAssert3).isSameAs(tableColumnAssertReturn3);

    TableColumnAssert tableColumnAssert4 = assertThat(table2).column("var4");
    TableColumnAssert tableColumnAssertReturn4 = tableColumnAssert4.hasValuesEqualTo(3, null);
    Assertions.assertThat(tableColumnAssert4).isSameAs(tableColumnAssertReturn4);

    TableColumnAssert tableColumnAssert5 = assertThat(table).column("var5");
    TableColumnAssert tableColumnAssertReturn5 = tableColumnAssert5.hasValuesEqualTo(4, 40, 400, 0);
    Assertions.assertThat(tableColumnAssert5).isSameAs(tableColumnAssertReturn5);

    TableColumnAssert tableColumnAssert6 = assertThat(table2).column("var5");
    TableColumnAssert tableColumnAssertReturn6 = tableColumnAssert6.hasValuesEqualTo(4, null);
    Assertions.assertThat(tableColumnAssert6).isSameAs(tableColumnAssertReturn6);

    TableColumnAssert tableColumnAssert7 = assertThat(table).column("var6");
    TableColumnAssert tableColumnAssertReturn7 = tableColumnAssert7.hasValuesEqualTo(5.6, 50.6, 500.6, 0);
    Assertions.assertThat(tableColumnAssert7).isSameAs(tableColumnAssertReturn7);

    TableColumnAssert tableColumnAssert8 = assertThat(table2).column("var6");
    TableColumnAssert tableColumnAssertReturn8 = tableColumnAssert8.hasValuesEqualTo(5.6, null);
    Assertions.assertThat(tableColumnAssert8).isSameAs(tableColumnAssertReturn8);

    TableColumnAssert tableColumnAssert9 = assertThat(table).column("var7");
    TableColumnAssert tableColumnAssertReturn9 = tableColumnAssert9.hasValuesEqualTo(7.8, 70.8, 700.8, 0);
    Assertions.assertThat(tableColumnAssert9).isSameAs(tableColumnAssertReturn9);

    TableColumnAssert tableColumnAssert10 = assertThat(table2).column("var7");
    TableColumnAssert tableColumnAssertReturn10 = tableColumnAssert10.hasValuesEqualTo(7.8, null);
    Assertions.assertThat(tableColumnAssert10).isSameAs(tableColumnAssertReturn10);

    TableColumnAssert tableColumnAssert11 = assertThat(table).column("var13");
    TableColumnAssert tableColumnAssertReturn11 = tableColumnAssert11.hasValuesEqualTo(5, 50, 500, 500);
    Assertions.assertThat(tableColumnAssert11).isSameAs(tableColumnAssertReturn11);

    TableColumnAssert tableColumnAssert12 = assertThat(table2).column("var13");
    TableColumnAssert tableColumnAssertReturn12 = tableColumnAssert12.hasValuesEqualTo(5, null);
    Assertions.assertThat(tableColumnAssert12).isSameAs(tableColumnAssertReturn12);

    TableColumnAssert tableColumnAssert13 = assertThat(table).column("var14");
    TableColumnAssert tableColumnAssertReturn13 = tableColumnAssert13.hasValuesEqualTo(7, 70, 700, 700);
    Assertions.assertThat(tableColumnAssert13).isSameAs(tableColumnAssertReturn13);

    TableColumnAssert tableColumnAssert14 = assertThat(table2).column("var14");
    TableColumnAssert tableColumnAssertReturn14 = tableColumnAssert14.hasValuesEqualTo(7, null);
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
      tableColumnAssert.hasValuesEqualTo(2, 20, 35, 0);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[Column at index 2 (column name : VAR3) of test table] \n"
                                                      + "Expecting that the value at index 2:\n"
                                                      + "  <25>\n"
                                                      + "to be equal to: \n"
                                                      + "  <35>");
    }
    Table table2 = new Table(source, "test2");
    TableColumnAssert tableColumnAssert2 = assertThat(table2).column("var3");
    try {
      tableColumnAssert2.hasValuesEqualTo(2, 2);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[Column at index 2 (column name : VAR3) of test2 table] \n"
                                                      + "Expecting that the value at index 1:\n"
                                                      + "  <null>\n"
                                                      + "to be equal to: \n"
                                                      + "  <2>");
    }

    TableColumnAssert tableColumnAssert3 = assertThat(table).column("var4");
    try {
      tableColumnAssert3.hasValuesEqualTo(3, 35, 300, 0);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[Column at index 3 (column name : VAR4) of test table] \n"
                                                      + "Expecting that the value at index 1:\n"
                                                      + "  <30>\n"
                                                      + "to be equal to: \n"
                                                      + "  <35>");
    }

    TableColumnAssert tableColumnAssert4 = assertThat(table2).column("var4");
    try {
      tableColumnAssert4.hasValuesEqualTo(3, 3);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[Column at index 3 (column name : VAR4) of test2 table] \n"
                                                      + "Expecting that the value at index 1:\n"
                                                      + "  <null>\n"
                                                      + "to be equal to: \n"
                                                      + "  <3>");
    }

    TableColumnAssert tableColumnAssert5 = assertThat(table).column("var5");
    try {
      tableColumnAssert5.hasValuesEqualTo(4, 45, 400, 0);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[Column at index 4 (column name : VAR5) of test table] \n"
                                                      + "Expecting that the value at index 1:\n"
                                                      + "  <40L>\n"
                                                      + "to be equal to: \n"
                                                      + "  <45>");
    }

    TableColumnAssert tableColumnAssert6 = assertThat(table2).column("var5");
    try {
      tableColumnAssert6.hasValuesEqualTo(4, 4);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[Column at index 4 (column name : VAR5) of test2 table] \n"
                                                      + "Expecting that the value at index 1:\n"
                                                      + "  <null>\n"
                                                      + "to be equal to: \n"
                                                      + "  <4>");
    }

    TableColumnAssert tableColumnAssert7 = assertThat(table).column("var6");
    try {
      tableColumnAssert7.hasValuesEqualTo(5.6, 55.6, 500.6, 0);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[Column at index 5 (column name : VAR6) of test table] \n"
                                                      + "Expecting that the value at index 1:\n"
                                                      + "  <50.60>\n"
                                                      + "to be equal to: \n"
                                                      + "  <55.6>");
    }

    TableColumnAssert tableColumnAssert8 = assertThat(table2).column("var6");
    try {
      tableColumnAssert8.hasValuesEqualTo(5.6, 5.6);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[Column at index 5 (column name : VAR6) of test2 table] \n"
                                                      + "Expecting that the value at index 1:\n"
                                                      + "  <null>\n"
                                                      + "to be equal to: \n"
                                                      + "  <5.6>");
    }

    TableColumnAssert tableColumnAssert9 = assertThat(table).column("var7");
    try {
      tableColumnAssert9.hasValuesEqualTo(7.8, 75.8, 700.8, 0);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[Column at index 6 (column name : VAR7) of test table] \n"
                                                      + "Expecting that the value at index 1:\n"
                                                      + "  <70.8f>\n"
                                                      + "to be equal to: \n"
                                                      + "  <75.8>");
    }

    TableColumnAssert tableColumnAssert10 = assertThat(table2).column("var7");
    try {
      tableColumnAssert10.hasValuesEqualTo(7.8, 7.8);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[Column at index 6 (column name : VAR7) of test2 table] \n"
                                                      + "Expecting that the value at index 1:\n"
                                                      + "  <null>\n"
                                                      + "to be equal to: \n"
                                                      + "  <7.8>");
    }

    TableColumnAssert tableColumnAssert11 = assertThat(table).column("var13");
    try {
      tableColumnAssert11.hasValuesEqualTo(5, 50, 505, 500);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[Column at index 12 (column name : VAR13) of test table] \n"
                                                      + "Expecting that the value at index 2:\n"
                                                      + "  <500.00>\n"
                                                      + "to be equal to: \n"
                                                      + "  <505>");
    }

    TableColumnAssert tableColumnAssert12 = assertThat(table2).column("var13");
    try {
      tableColumnAssert12.hasValuesEqualTo(5, 5);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[Column at index 12 (column name : VAR13) of test2 table] \n"
                                                      + "Expecting that the value at index 1:\n"
                                                      + "  <null>\n"
                                                      + "to be equal to: \n"
                                                      + "  <5>");
    }

    TableColumnAssert tableColumnAssert13 = assertThat(table).column("var14");
    try {
      tableColumnAssert13.hasValuesEqualTo(7, 70, 750, 700);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[Column at index 13 (column name : VAR14) of test table] \n"
                                                      + "Expecting that the value at index 2:\n"
                                                      + "  <700.0f>\n"
                                                      + "to be equal to: \n"
                                                      + "  <750>");
    }

    TableColumnAssert tableColumnAssert14 = assertThat(table2).column("var14");
    try {
      tableColumnAssert14.hasValuesEqualTo(7, 7);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[Column at index 13 (column name : VAR14) of test2 table] \n"
                                                      + "Expecting that the value at index 1:\n"
                                                      + "  <null>\n"
                                                      + "to be equal to: \n"
                                                      + "  <7>");
    }
  }
}
