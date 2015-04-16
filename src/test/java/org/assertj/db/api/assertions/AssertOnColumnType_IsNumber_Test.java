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
import org.assertj.db.api.ChangeColumnAssert;
import org.assertj.db.api.TableColumnAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.assertj.db.type.Changes;
import org.assertj.db.type.Table;
import org.junit.Test;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on {@link org.assertj.db.api.assertions.AssertOnColumnType} class :
 * {@link org.assertj.db.api.assertions.AssertOnColumnType#isNumber(boolean)} method.
 *
 * @author Régis Pouiller
 *
 */
public class AssertOnColumnType_IsNumber_Test extends AbstractTest {

  /**
   * This method tests the {@code isNumber} assertion method.
   */
  @Test
  @NeedReload
  public void test_is_number() {
    Changes changes = new Changes(source).setStartPointNow();
    update("update test set var14 = 1 where var1 = 1");
    update("insert into test(var1, var2, var11, var10, var9, var3, var12, var8) values(5, true, FILE_READ('classpath:h2-logo-2.png'), '2014-05-24 09:46:30', '2014-05-24', 3, 'test', '09:46:30')");
    changes.setEndPointNow();
    Table table = new Table(source, "test");
    Table table2 = new Table(source, "test2");

    ChangeColumnAssert changeColumnAssert1 = assertThat(changes).change().column("var3");
    ChangeColumnAssert changeColumnAssertReturn1 = changeColumnAssert1.isNumber(true);
    Assertions.assertThat(changeColumnAssert1).isSameAs(changeColumnAssertReturn1);
    ChangeColumnAssert changeColumnAssert2 = assertThat(changes).change(1).column("var3");
    ChangeColumnAssert changeColumnAssertReturn2 = changeColumnAssert2.isNumber(false);
    Assertions.assertThat(changeColumnAssert2).isSameAs(changeColumnAssertReturn2);
    TableColumnAssert tableColumnAssert1 = assertThat(table).column("var3");
    TableColumnAssert tableColumnAssertReturn1 = tableColumnAssert1.isNumber(false);
    Assertions.assertThat(tableColumnAssert1).isSameAs(tableColumnAssertReturn1);
    TableColumnAssert tableColumnAssert2 = assertThat(table2).column("var3");
    TableColumnAssert tableColumnAssertReturn2 = tableColumnAssert2.isNumber(true);
    Assertions.assertThat(tableColumnAssert2).isSameAs(tableColumnAssertReturn2);
  }

  /**
   * This method should fail because a value have different type.
   */
  @Test
  @NeedReload
  public void should_fail_because_value_have_different_type() {
    Changes changes = new Changes(source).setStartPointNow();
    update("update test set var14 = 1 where var1 = 1");
    update("insert into test(var1, var2, var11, var10, var9, var3, var12, var8) values(5, true, FILE_READ('classpath:h2-logo-2.png'), '2014-05-24 09:46:30', '2014-05-24', 3, 'test', '09:46:30')");
    changes.setEndPointNow();
    Table table = new Table(source, "test");
    Table table2 = new Table(source, "test2");

    try {
      assertThat(changes).change().column("var3").isNumber(false);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[Column at index 2 (column name : VAR3) of Change at index 0 (on table : TEST and with primary key : [5]) of Changes on tables of 'sa/jdbc:h2:mem:test' source] \n"
                                                      + "Expecting that the value at start point:\n"
                                                      + "  <null>\n"
                                                      + "to be of type\n"
                                                      + "  <NUMBER>\n"
                                                      + "but was of type\n"
                                                      + "  <NOT_IDENTIFIED>");
    }
    try {
      assertThat(changes).change(1).column("var2").isNumber(true);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[Column at index 1 (column name : VAR2) of Change at index 1 (on table : TEST and with primary key : [1]) of Changes on tables of 'sa/jdbc:h2:mem:test' source] \n"
                                                      + "Expecting that the value at start point:\n"
                                                      + "  <true>\n"
                                                      + "to be of type\n"
                                                      + "  <[NUMBER, NOT_IDENTIFIED]>\n"
                                                      + "but was of type\n"
                                                      + "  <BOOLEAN>");
    }
    try {
      assertThat(table).column("var2").isNumber(true);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[Column at index 1 (column name : VAR2) of test table] \n"
                                                      + "Expecting that the value at index 0:\n"
                                                      + "  <true>\n"
                                                      + "to be of type\n"
                                                      + "  <[NUMBER, NOT_IDENTIFIED]>\n"
                                                      + "but was of type\n"
                                                      + "  <BOOLEAN>");
    }
    try {
      assertThat(table2).column("var3").isNumber(false);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[Column at index 2 (column name : VAR3) of test2 table] \n"
                                                      + "Expecting that the value at index 1:\n"
                                                      + "  <null>\n"
                                                      + "to be of type\n"
                                                      + "  <NUMBER>\n"
                                                      + "but was of type\n"
                                                      + "  <NOT_IDENTIFIED>");
    }
  }
}
