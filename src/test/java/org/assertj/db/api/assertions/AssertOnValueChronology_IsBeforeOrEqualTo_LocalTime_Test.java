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

import java.time.LocalTime;

import org.assertj.core.api.Assertions;
import org.assertj.db.api.ChangeColumnValueAssert;
import org.assertj.db.api.TableColumnValueAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.assertj.db.type.Changes;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Tests on {@link org.assertj.db.api.assertions.AssertOnValueChronology} class :
 * {@link org.assertj.db.api.assertions.AssertOnValueChronology#isBeforeOrEqualTo(java.time.LocalTime)} method.
 *
 * @author Yosuke Nishikawa
 */
public class AssertOnValueChronology_IsBeforeOrEqualTo_LocalTime_Test extends AbstractTest {

  /**
   * This method tests the {@code isBeforeOrEqualTo} assertion method.
   */
  @Test
  @NeedReload
  public void test_is_before_or_equal_to() {
    Table table = new Table(jdbcConnectionProvider, "test");
    Changes changes = new Changes(table).setStartPointNow();
    update("update test set var14 = 1 where var1 = 1");
    changes.setEndPointNow();

    ChangeColumnValueAssert changeColumnValueAssert = assertThat(changes).change().column("var8").valueAtEndPoint();
    ChangeColumnValueAssert changeColumnValueAssert2 = changeColumnValueAssert.isBeforeOrEqualTo(LocalTime.of(9, 46, 30));
    Assertions.assertThat(changeColumnValueAssert).isSameAs(changeColumnValueAssert2);

    TableColumnValueAssert tableColumnValueAssert = assertThat(table).column("var8").value();
    TableColumnValueAssert tableColumnValueAssert2 = tableColumnValueAssert.isBeforeOrEqualTo(LocalTime.of(9, 46, 30));
    Assertions.assertThat(tableColumnValueAssert).isSameAs(tableColumnValueAssert2);
  }

  /**
   * This method should fail because the value is after.
   */
  @Test
  @NeedReload
  public void should_fail_because_value_is_after() {
    Table table = new Table(jdbcConnectionProvider, "test");
    Changes changes = new Changes(table).setStartPointNow();
    update("update test set var14 = 1 where var1 = 1");
    changes.setEndPointNow();

    try {
      assertThat(changes).change().column("var8").valueAtEndPoint().isBeforeOrEqualTo(LocalTime.of(9, 46, 29));
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[Value at end point of Column at index 7 (column name : VAR8) of Change at index 0 (with primary key : [1]) of Changes on TEST table of 'sa/jdbc:h2:mem:test'] %n"
        + "Expecting:%n"
        + "  <09:46:30.000000000>%n"
        + "to be before or equal to %n"
        + "  <09:46:29.000000000>"));
    }
    try {
      assertThat(table).column("var8").value().isBeforeOrEqualTo(LocalTime.of(9, 46, 29));
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[Value at index 0 of Column at index 7 (column name : VAR8) of TEST table] %n"
        + "Expecting:%n"
        + "  <09:46:30.000000000>%n"
        + "to be before or equal to %n"
        + "  <09:46:29.000000000>"));
    }
  }
}
