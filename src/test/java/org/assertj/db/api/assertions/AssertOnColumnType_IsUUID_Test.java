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
 * {@link org.assertj.db.api.assertions.AssertOnColumnType#isUUID(boolean)} method.
 *
 * @author Otoniel Isidoro (otoniel.isidoro@sofist.com.br)
 */
public class AssertOnColumnType_IsUUID_Test extends AbstractTest {

  /**
   * This method tests the {@code isUUID} assertion method.
   */
  @Test
  @NeedReload
  public void test_is_UUID() {
    Changes changes = new Changes(source).setStartPointNow();
    update("update test set var15 = '0E2A1269-EFF0-4233-B87B-B53E8B6F164D' where var1 = 1");
    update(
        "insert into test(var1, var2, var11, var10, var9, var3, var12, var8, var15) values(5, true, FILE_READ('classpath:h2-logo-2.png'), '2014-05-24 09:46:30', '2014-05-24', 3, 'test', '09:46:30', '2B0D1BDD-909E-4362-BA10-C930BA82718D')");
    changes.setEndPointNow();
    Table table = new Table(source, "test");
    Table table2 = new Table(source, "test2");

    ChangeColumnAssert changeColumnAssert1 = assertThat(changes).change().column("var15");
    ChangeColumnAssert changeColumnAssertReturn1 = changeColumnAssert1.isUUID(true);
    Assertions.assertThat(changeColumnAssert1).isSameAs(changeColumnAssertReturn1);
    ChangeColumnAssert changeColumnAssert2 = assertThat(changes).change(1).column("var15");
    ChangeColumnAssert changeColumnAssertReturn2 = changeColumnAssert2.isUUID(false);
    Assertions.assertThat(changeColumnAssert2).isSameAs(changeColumnAssertReturn2);
    TableColumnAssert tableColumnAssert1 = assertThat(table).column("var15");
    TableColumnAssert tableColumnAssertReturn1 = tableColumnAssert1.isUUID(false);
    Assertions.assertThat(tableColumnAssert1).isSameAs(tableColumnAssertReturn1);
    TableColumnAssert tableColumnAssert2 = assertThat(table2).column("var16");
    TableColumnAssert tableColumnAssertReturn2 = tableColumnAssert2.isUUID(true);
    Assertions.assertThat(tableColumnAssert2).isSameAs(tableColumnAssertReturn2);
  }

  /**
   * This method should fail because a value have different type.
   */
  @Test
  @NeedReload
  public void should_fail_because_value_have_different_type() {
    Changes changes = new Changes(source).setStartPointNow();
    update("update test set var15 = null where var1 = 1");
    update(
        "insert into test(var1, var2, var11, var10, var9, var3, var12, var8, var15) values(5, true, FILE_READ('classpath:h2-logo-2.png'), '2014-05-24 09:46:30', '2014-05-24', 3, 'test', '09:46:30', null)");
    changes.setEndPointNow();
    Table table = new Table(source, "test");
    Table table2 = new Table(source, "test2");

    try {
      assertThat(changes).change().column("var15").isUUID(false);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format(
          "[Column at index 14 (column name : VAR15) of Change at index 0 (on table : TEST and with primary key : [5]) of Changes on tables of 'sa/jdbc:h2:mem:test' source] %n"
          + "Expecting that the value at start point:%n"
          + "  <null>%n"
          + "to be of type%n"
          + "  <UUID>%n"
          + "but was of type%n"
          + "  <NOT_IDENTIFIED>"));
    }
    try {
      assertThat(changes).change(1).column("var1").isUUID(true);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format(
          "[Column at index 0 (column name : VAR1) of Change at index 1 (on table : TEST and with primary key : [1]) of Changes on tables of 'sa/jdbc:h2:mem:test' source] %n"
          + "Expecting that the value at start point:%n"
          + "  <1>%n"
          + "to be of type%n"
          + "  <[UUID, NOT_IDENTIFIED]>%n"
          + "but was of type%n"
          + "  <NUMBER>"));
    }
    try {
      assertThat(table).column("var1").isUUID(true);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage())
                .isEqualTo(String.format("[Column at index 0 (column name : VAR1) of test table] %n"
                                         + "Expecting that the value at index 0:%n"
                                         + "  <1>%n"
                                         + "to be of type%n"
                                         + "  <[UUID, NOT_IDENTIFIED]>%n"
                                         + "but was of type%n"
                                         + "  <NUMBER>"));
    }
    try {
      assertThat(table2).column("var15").isUUID(false);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage())
                .isEqualTo(String.format("[Column at index 14 (column name : VAR15) of test2 table] %n"
                                         + "Expecting that the value at index 0:%n"
                                         + "  <null>%n"
                                         + "to be of type%n"
                                         + "  <UUID>%n"
                                         + "but was of type%n"
                                         + "  <NOT_IDENTIFIED>"));
    }
  }
}
