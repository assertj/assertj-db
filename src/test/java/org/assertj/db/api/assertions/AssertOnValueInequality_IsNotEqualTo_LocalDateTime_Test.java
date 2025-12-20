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
 * Copyright 2015-2025 the original author or authors.
 */
package org.assertj.db.api.assertions;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import java.time.LocalDateTime;

import org.assertj.core.api.Assertions;
import org.assertj.db.api.ChangeColumnValueAssert;
import org.assertj.db.api.TableColumnValueAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.assertj.db.type.Changes;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Tests on {@link org.assertj.db.api.assertions.AssertOnValueInequality} class :
 * {@link org.assertj.db.api.assertions.AssertOnValueInequality#isNotEqualTo(java.time.LocalDateTime)} method.
 *
 * @author Yosuke Nishikawa
 */
public class AssertOnValueInequality_IsNotEqualTo_LocalDateTime_Test extends AbstractTest {

  /**
   * This method tests the {@code isNotEqualTo} assertion method.
   */
  @Test
  @NeedReload
  public void test_is_not_equal_to() {
    Table table = assertDbConnection.table("test").build();
    Changes changes = assertDbConnection.changes().tables(table).build().setStartPointNow();
    update("update test set var14 = 1 where var1 = 1");
    changes.setEndPointNow();

    ChangeColumnValueAssert changeColumnValueAssert = assertThat(changes).change().column("var10").valueAtEndPoint();
    ChangeColumnValueAssert changeColumnValueAssert2 = changeColumnValueAssert.isNotEqualTo(LocalDateTime.of(2014, 5, 24, 9, 46, 31));
    Assertions.assertThat(changeColumnValueAssert).isSameAs(changeColumnValueAssert2);

    TableColumnValueAssert tableColumnValueAssert = assertThat(table).column("var10").value();
    TableColumnValueAssert tableColumnValueAssert2 = tableColumnValueAssert.isNotEqualTo(LocalDateTime.of(2014, 5, 24, 9, 46, 31));
    Assertions.assertThat(tableColumnValueAssert).isSameAs(tableColumnValueAssert2);
  }

  /**
   * This method should fail because the value is equal to.
   */
  @Test
  @NeedReload
  public void should_fail_because_value_is_equal_to() {
    Table table = assertDbConnection.table("test").build();
    Changes changes = assertDbConnection.changes().tables(table).build().setStartPointNow();
    update("update test set var14 = 1 where var1 = 1");
    changes.setEndPointNow();

    try {
      assertThat(changes).change().column("var10").valueAtEndPoint().isNotEqualTo(LocalDateTime.of(2014, 5, 24, 9, 46, 30));
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[Value at end point of Column at index 9 (column name : VAR10) of Change at index 0 (with primary key : [1]) of Changes on TEST table of 'sa/jdbc:h2:mem:test'] %n"
        + "Expecting:%n"
        + "  <2014-05-24T09:46:30.000000000>%n"
        + "not to be equal to: %n"
        + "  <2014-05-24T09:46:30.000000000>"));
    }
    try {
      assertThat(table).column("var10").value().isNotEqualTo(LocalDateTime.of(2014, 5, 24, 9, 46, 30));
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[Value at index 0 of Column at index 9 (column name : VAR10) of TEST table] %n"
        + "Expecting:%n"
        + "  <2014-05-24T09:46:30.000000000>%n"
        + "not to be equal to: %n"
        + "  <2014-05-24T09:46:30.000000000>"));
    }
  }
}
