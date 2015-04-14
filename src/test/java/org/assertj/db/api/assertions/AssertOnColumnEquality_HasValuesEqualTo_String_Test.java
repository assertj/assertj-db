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
import org.assertj.db.common.NeedReload;
import org.assertj.db.type.Table;
import org.junit.Test;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on {@link AssertOnColumnEquality} class :
 * {@link org.assertj.db.api.assertions.AssertOnColumnEquality#hasValuesEqualTo(String...)} method.
 *
 * @author Régis Pouiller
 *
 */
public class AssertOnColumnEquality_HasValuesEqualTo_String_Test extends AbstractTest {

  /**
   * This method tests the {@code hasValuesEqualTo} assertion method.
   */
  @Test
  @NeedReload
  public void test_has_values_equal_to() throws Exception {
    Table table = new Table(source, "test");
    TableColumnAssert tableColumnAssert = assertThat(table).column("var3");
    TableColumnAssert tableColumnAssertReturn = tableColumnAssert.hasValuesEqualTo("2", "20", "25", "0");
    Assertions.assertThat(tableColumnAssert).isSameAs(tableColumnAssertReturn);

    Table table2 = new Table(source, "test2");
    TableColumnAssert tableColumnAssert2 = assertThat(table2).column("var3");
    TableColumnAssert tableColumnAssertReturn2 = tableColumnAssert2.hasValuesEqualTo("2", null);
    Assertions.assertThat(tableColumnAssert2).isSameAs(tableColumnAssertReturn2);

    TableColumnAssert tableColumnAssert3 = assertThat(table).column("var4");
    TableColumnAssert tableColumnAssertReturn3 = tableColumnAssert3.hasValuesEqualTo("3", "30", "300", "0");
    Assertions.assertThat(tableColumnAssert3).isSameAs(tableColumnAssertReturn3);

    TableColumnAssert tableColumnAssert4 = assertThat(table2).column("var4");
    TableColumnAssert tableColumnAssertReturn4 = tableColumnAssert4.hasValuesEqualTo("3", null);
    Assertions.assertThat(tableColumnAssert4).isSameAs(tableColumnAssertReturn4);

    TableColumnAssert tableColumnAssert5 = assertThat(table).column("var5");
    TableColumnAssert tableColumnAssertReturn5 = tableColumnAssert5.hasValuesEqualTo("4", "40", "400", "0");
    Assertions.assertThat(tableColumnAssert5).isSameAs(tableColumnAssertReturn5);

    TableColumnAssert tableColumnAssert6 = assertThat(table2).column("var5");
    TableColumnAssert tableColumnAssertReturn6 = tableColumnAssert6.hasValuesEqualTo("4", null);
    Assertions.assertThat(tableColumnAssert6).isSameAs(tableColumnAssertReturn6);

    TableColumnAssert tableColumnAssert7 = assertThat(table).column("var6");
    TableColumnAssert tableColumnAssertReturn7 = tableColumnAssert7.hasValuesEqualTo("5.6", "50.6", "500.6", "0");
    Assertions.assertThat(tableColumnAssert7).isSameAs(tableColumnAssertReturn7);

    TableColumnAssert tableColumnAssert8 = assertThat(table2).column("var6");
    TableColumnAssert tableColumnAssertReturn8 = tableColumnAssert8.hasValuesEqualTo("5.6", null);
    Assertions.assertThat(tableColumnAssert8).isSameAs(tableColumnAssertReturn8);

    TableColumnAssert tableColumnAssert9 = assertThat(table).column("var7");
    TableColumnAssert tableColumnAssertReturn9 = tableColumnAssert9.hasValuesEqualTo("7.8", "70.8", "700.8", "0");
    Assertions.assertThat(tableColumnAssert9).isSameAs(tableColumnAssertReturn9);

    TableColumnAssert tableColumnAssert10 = assertThat(table2).column("var7");
    TableColumnAssert tableColumnAssertReturn10 = tableColumnAssert10.hasValuesEqualTo("7.8", null);
    Assertions.assertThat(tableColumnAssert10).isSameAs(tableColumnAssertReturn10);

    TableColumnAssert tableColumnAssert11 = assertThat(table).column("var13");
    TableColumnAssert tableColumnAssertReturn11 = tableColumnAssert11.hasValuesEqualTo("5", "50", "500", "500");
    Assertions.assertThat(tableColumnAssert11).isSameAs(tableColumnAssertReturn11);

    TableColumnAssert tableColumnAssert12 = assertThat(table2).column("var13");
    TableColumnAssert tableColumnAssertReturn12 = tableColumnAssert12.hasValuesEqualTo("5", null);
    Assertions.assertThat(tableColumnAssert12).isSameAs(tableColumnAssertReturn12);

    TableColumnAssert tableColumnAssert13 = assertThat(table).column("var14");
    TableColumnAssert tableColumnAssertReturn13 = tableColumnAssert13.hasValuesEqualTo("7", "70", "700", "700");
    Assertions.assertThat(tableColumnAssert13).isSameAs(tableColumnAssertReturn13);

    TableColumnAssert tableColumnAssert14 = assertThat(table2).column("var14");
    TableColumnAssert tableColumnAssertReturn14 = tableColumnAssert14.hasValuesEqualTo("7", null);
    Assertions.assertThat(tableColumnAssert14).isSameAs(tableColumnAssertReturn14);

    TableColumnAssert tableColumnAssert15 = assertThat(table).column("var12");
    TableColumnAssert tableColumnAssertReturn15 = tableColumnAssert15.hasValuesEqualTo("text", "another text", "another text again", "another text again");
    Assertions.assertThat(tableColumnAssert15).isSameAs(tableColumnAssertReturn15);

    TableColumnAssert tableColumnAssert16 = assertThat(table2).column("var12");
    TableColumnAssert tableColumnAssertReturn16 = tableColumnAssert16.hasValuesEqualTo("text", null);
    Assertions.assertThat(tableColumnAssert16).isSameAs(tableColumnAssertReturn16);

    TableColumnAssert tableColumnAssert20 = assertThat(table).column("var8");
    TableColumnAssert tableColumnAssertReturn20 = tableColumnAssert20.hasValuesEqualTo("09:46:30",
                                                                                   "12:29:49",
                                                                                   "12:29:49",
                                                                                   "12:29:49");
    Assertions.assertThat(tableColumnAssert20).isSameAs(tableColumnAssertReturn20);

    TableColumnAssert tableColumnAssert21 = assertThat(table2).column("var8");
    TableColumnAssert tableColumnAssertReturn21 = tableColumnAssert21.hasValuesEqualTo("09:46:30", null);
    Assertions.assertThat(tableColumnAssert21).isSameAs(tableColumnAssertReturn21);

    TableColumnAssert tableColumnAssert22 = assertThat(table).column("var10");
    TableColumnAssert tableColumnAssertReturn22 = tableColumnAssert22.hasValuesEqualTo(
            "2014-05-24T09:46:30",
            "2014-05-30T12:29:49",
            "2014-05-30T00:00:00",
            "2014-05-30T00:00:00");
    Assertions.assertThat(tableColumnAssert22).isSameAs(tableColumnAssertReturn22);

    TableColumnAssert tableColumnAssert23 = assertThat(table2).column("var10");
    TableColumnAssert tableColumnAssertReturn23 = tableColumnAssert23.hasValuesEqualTo("2014-05-24T09:46:30",
                                                                                     null);
    Assertions.assertThat(tableColumnAssert23).isSameAs(tableColumnAssertReturn23);

    TableColumnAssert tableColumnAssert24 = assertThat(table).column("var9");
    TableColumnAssert tableColumnAssertReturn24 = tableColumnAssert24.hasValuesEqualTo("2014-05-24T00:00:00",
                                                                                     "2014-05-30T00:00:00",
                                                                                     "2014-05-30T00:00:00",
                                                                                     "2014-05-30T00:00:00");
    Assertions.assertThat(tableColumnAssert24).isSameAs(tableColumnAssertReturn24);

    TableColumnAssert tableColumnAssert25 = assertThat(table2).column("var9");
    TableColumnAssert tableColumnAssertReturn25 = tableColumnAssert25.hasValuesEqualTo("2014-05-24T00:00:00",
                                                                                     null);
    Assertions.assertThat(tableColumnAssert25).isSameAs(tableColumnAssertReturn25);

    TableColumnAssert tableColumnAssert17 = assertThat(table).column("var9");
    TableColumnAssert tableColumnAssertReturn17 = tableColumnAssert17.hasValuesEqualTo(
            "2014-05-24",
            "2014-05-30",
            "2014-05-30",
            "2014-05-30");
    Assertions.assertThat(tableColumnAssert17).isSameAs(tableColumnAssertReturn17);

    TableColumnAssert tableColumnAssert18 = assertThat(table2).column("var9");
    TableColumnAssert tableColumnAssertReturn18 = tableColumnAssert18.hasValuesEqualTo("2014-05-24",
                                                                                     null);
    Assertions.assertThat(tableColumnAssert18).isSameAs(tableColumnAssertReturn18);

    update("update test2 set var10 = '2014-05-24' where var1 = 1");
    table2 = new Table(source, "test2");
    TableColumnAssert tableColumnAssert19 = assertThat(table2).column("var10");
    TableColumnAssert tableColumnAssertReturn19 = tableColumnAssert19.hasValuesEqualTo("2014-05-24",
                                                                                     null);
    Assertions.assertThat(tableColumnAssert19).isSameAs(tableColumnAssertReturn19);
  }

  /**
   * This method should fail because the values are different.
   */
  @Test
  public void should_fail_because_values_are_different() throws Exception {
    Table table = new Table(source, "test");
    TableColumnAssert tableColumnAssert = assertThat(table).column("var3");
    try {
      tableColumnAssert.hasValuesEqualTo("2", "20", "35", "0");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[Column at index 2 (column name : VAR3) of test table] \n"
                                                      + "Expecting that the value at index 2:\n"
                                                      + "  <\"25\">\n"
                                                      + "to be equal to: \n"
                                                      + "  <\"35\">");
    }
    Table table2 = new Table(source, "test2");
    TableColumnAssert tableColumnAssert2 = assertThat(table2).column("var3");
    try {
      tableColumnAssert2.hasValuesEqualTo("2", "2");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[Column at index 2 (column name : VAR3) of test2 table] \n"
                                                      + "Expecting that the value at index 1:\n"
                                                      + "  <null>\n"
                                                      + "to be equal to: \n"
                                                      + "  <\"2\">");
    }

    TableColumnAssert tableColumnAssert3 = assertThat(table).column("var4");
    try {
      tableColumnAssert3.hasValuesEqualTo("3", "35", "300", "0");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[Column at index 3 (column name : VAR4) of test table] \n"
                                                      + "Expecting that the value at index 1:\n"
                                                      + "  <\"30\">\n"
                                                      + "to be equal to: \n"
                                                      + "  <\"35\">");
    }

    TableColumnAssert tableColumnAssert4 = assertThat(table2).column("var4");
    try {
      tableColumnAssert4.hasValuesEqualTo("3", "3");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[Column at index 3 (column name : VAR4) of test2 table] \n"
                                                      + "Expecting that the value at index 1:\n"
                                                      + "  <null>\n"
                                                      + "to be equal to: \n"
                                                      + "  <\"3\">");
    }

    TableColumnAssert tableColumnAssert5 = assertThat(table).column("var5");
    try {
      tableColumnAssert5.hasValuesEqualTo("4", "45", "400", "0");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[Column at index 4 (column name : VAR5) of test table] \n"
                                                      + "Expecting that the value at index 1:\n"
                                                      + "  <\"40\">\n"
                                                      + "to be equal to: \n"
                                                      + "  <\"45\">");
    }

    TableColumnAssert tableColumnAssert6 = assertThat(table2).column("var5");
    try {
      tableColumnAssert6.hasValuesEqualTo("4", "4");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[Column at index 4 (column name : VAR5) of test2 table] \n"
                                                      + "Expecting that the value at index 1:\n"
                                                      + "  <null>\n"
                                                      + "to be equal to: \n"
                                                      + "  <\"4\">");
    }

    TableColumnAssert tableColumnAssert7 = assertThat(table).column("var6");
    try {
      tableColumnAssert7.hasValuesEqualTo("5.6", "55.6", "500.6", "0");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[Column at index 5 (column name : VAR6) of test table] \n"
                                                      + "Expecting that the value at index 1:\n"
                                                      + "  <\"50.60\">\n"
                                                      + "to be equal to: \n"
                                                      + "  <\"55.6\">");
    }

    TableColumnAssert tableColumnAssert8 = assertThat(table2).column("var6");
    try {
      tableColumnAssert8.hasValuesEqualTo("5.6", "5.6");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[Column at index 5 (column name : VAR6) of test2 table] \n"
                                                      + "Expecting that the value at index 1:\n"
                                                      + "  <null>\n"
                                                      + "to be equal to: \n"
                                                      + "  <\"5.6\">");
    }

    TableColumnAssert tableColumnAssert9 = assertThat(table).column("var7");
    try {
      tableColumnAssert9.hasValuesEqualTo("7.8", "75.8", "700.8", "0");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[Column at index 6 (column name : VAR7) of test table] \n"
                                                      + "Expecting that the value at index 1:\n"
                                                      + "  <\"70.8\">\n"
                                                      + "to be equal to: \n"
                                                      + "  <\"75.8\">");
    }

    TableColumnAssert tableColumnAssert10 = assertThat(table2).column("var7");
    try {
      tableColumnAssert10.hasValuesEqualTo("7.8", "7.8");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[Column at index 6 (column name : VAR7) of test2 table] \n"
                                                      + "Expecting that the value at index 1:\n"
                                                      + "  <null>\n"
                                                      + "to be equal to: \n"
                                                      + "  <\"7.8\">");
    }

    TableColumnAssert tableColumnAssert11 = assertThat(table).column("var13");
    try {
      tableColumnAssert11.hasValuesEqualTo("5", "50", "505", "500");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[Column at index 12 (column name : VAR13) of test table] \n"
                                                      + "Expecting that the value at index 2:\n"
                                                      + "  <\"500.00\">\n"
                                                      + "to be equal to: \n"
                                                      + "  <\"505\">");
    }

    TableColumnAssert tableColumnAssert12 = assertThat(table2).column("var13");
    try {
      tableColumnAssert12.hasValuesEqualTo("5", "5");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[Column at index 12 (column name : VAR13) of test2 table] \n"
                                                      + "Expecting that the value at index 1:\n"
                                                      + "  <null>\n"
                                                      + "to be equal to: \n"
                                                      + "  <\"5\">");
    }

    TableColumnAssert tableColumnAssert13 = assertThat(table).column("var14");
    try {
      tableColumnAssert13.hasValuesEqualTo("7", "70", "750", "700");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[Column at index 13 (column name : VAR14) of test table] \n"
                                                      + "Expecting that the value at index 2:\n"
                                                      + "  <\"700.0\">\n"
                                                      + "to be equal to: \n"
                                                      + "  <\"750\">");
    }

    TableColumnAssert tableColumnAssert14 = assertThat(table2).column("var14");
    try {
      tableColumnAssert14.hasValuesEqualTo("7", "7");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[Column at index 13 (column name : VAR14) of test2 table] \n"
                                                      + "Expecting that the value at index 1:\n"
                                                      + "  <null>\n"
                                                      + "to be equal to: \n"
                                                      + "  <\"7\">");
    }

    TableColumnAssert tableColumnAssert15 = assertThat(table).column("var12");
    try {
      tableColumnAssert15.hasValuesEqualTo("text", "another text", "another text", "another text again");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[Column at index 11 (column name : VAR12) of test table] \n"
                                                      + "Expecting that the value at index 2:\n"
                                                      + "  <\"another text again\">\n"
                                                      + "to be equal to: \n"
                                                      + "  <\"another text\">");
    }

    TableColumnAssert tableColumnAssert16 = assertThat(table2).column("var12");
    try {
      tableColumnAssert16.hasValuesEqualTo("text", "text");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[Column at index 11 (column name : VAR12) of test2 table] \n"
                                                      + "Expecting that the value at index 1:\n"
                                                      + "  <null>\n"
                                                      + "to be equal to: \n"
                                                      + "  <\"text\">");
    }
    TableColumnAssert tableColumnAssert17 = assertThat(table).column("var9");
    try {
      tableColumnAssert17.hasValuesEqualTo("2014-05-24",
                                         "2014-05-29",
                                         "2014-05-30",
                                         "2014-05-30");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[Column at index 8 (column name : VAR9) of test table] \n"
                                                      + "Expecting that the value at index 1:\n"
                                                      + "  <\"2014-05-30\">\n"
                                                      + "to be equal to: \n"
                                                      + "  <\"2014-05-29\">");
    }
    TableColumnAssert tableColumnAssert18 = assertThat(table2).column("var9");
    try {
      tableColumnAssert18.hasValuesEqualTo("2014-05-24",
                                          "2014-05-24");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[Column at index 8 (column name : VAR9) of test2 table] \n"
                                                      + "Expecting that the value at index 1:\n"
                                                      + "  <null>\n"
                                                      + "to be equal to: \n"
                                                      + "  <\"2014-05-24\">");
    }
    TableColumnAssert tableColumnAssert19 = assertThat(table2).column("var10");
    try {
      tableColumnAssert19.hasValuesEqualTo("2014-05-23",
                                          null);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[Column at index 9 (column name : VAR10) of test2 table] \n"
                                                      + "Expecting that the value at index 0:\n"
                                                      + "  <\"2014-05-24T09:46:30.000000000\">\n"
                                                      + "to be equal to: \n"
                                                      + "  <\"2014-05-23\">");
    }
    TableColumnAssert tableColumnAssert20 = assertThat(table).column("var8");
    try {
      tableColumnAssert20.hasValuesEqualTo("12:29:49",
                                         "12:29:49",
                                         "12:29:49",
                                         "12:29:49");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[Column at index 7 (column name : VAR8) of test table] \n"
                                                      + "Expecting that the value at index 0:\n"
                                                      + "  <\"09:46:30.000000000\">\n"
                                                      + "to be equal to: \n"
                                                      + "  <\"12:29:49\">");
    }
    TableColumnAssert tableColumnAssert21 = assertThat(table2).column("var8");
    try {
      tableColumnAssert21.hasValuesEqualTo("09:46:30",
                                          "12:29:49");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[Column at index 7 (column name : VAR8) of test2 table] \n"
                                                      + "Expecting that the value at index 1:\n"
                                                      + "  <null>\n"
                                                      + "to be equal to: \n"
                                                      + "  <\"12:29:49\">");
    }
    TableColumnAssert tableColumnAssert22 = assertThat(table).column("var10");
    try {
      tableColumnAssert22.hasValuesEqualTo("2014-05-24T09:46:30",
                                           "2014-05-30T13:29:49",
                                           "2014-05-30T00:00:00",
                                           "2014-05-30T00:00:00");
                                           fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[Column at index 9 (column name : VAR10) of test table] \n"
                                                      + "Expecting that the value at index 1:\n"
                                                      + "  <\"2014-05-30T12:29:49.000000000\">\n"
                                                      + "to be equal to: \n"
                                                      + "  <\"2014-05-30T13:29:49\">");
    }
    TableColumnAssert tableColumnAssert23 = assertThat(table2).column("var10");
    try {
      tableColumnAssert23.hasValuesEqualTo("2014-05-24T09:46:30",
                                           "2014-05-24T09:46:30");
                                           fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[Column at index 9 (column name : VAR10) of test2 table] \n"
                                                      + "Expecting that the value at index 1:\n"
                                                      + "  <null>\n"
                                                      + "to be equal to: \n"
                                                      + "  <\"2014-05-24T09:46:30\">");
    }
    TableColumnAssert tableColumnAssert24 = assertThat(table).column("var9");
    try {
      tableColumnAssert24.hasValuesEqualTo("2014-05-24T00:00:00",
                                          "2014-05-24T00:00:00",
                                          "2014-05-30T00:00:00",
                                          "2014-05-30T00:00:00");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[Column at index 8 (column name : VAR9) of test table] \n"
                                                      + "Expecting that the value at index 1:\n"
                                                      + "  <\"2014-05-30T00:00:00.000000000\">\n"
                                                      + "to be equal to: \n"
                                                      + "  <\"2014-05-24T00:00:00\">");
    }
    TableColumnAssert tableColumnAssert25 = assertThat(table2).column("var9");
    try {
      tableColumnAssert25.hasValuesEqualTo("2014-05-24T00:00:00",
                                           "2014-05-24T00:00:00");
                                           fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[Column at index 8 (column name : VAR9) of test2 table] \n"
                                                      + "Expecting that the value at index 1:\n"
                                                      + "  <null>\n"
                                                      + "to be equal to: \n"
                                                      + "  <\"2014-05-24T00:00:00\">");
    }
  }
}
