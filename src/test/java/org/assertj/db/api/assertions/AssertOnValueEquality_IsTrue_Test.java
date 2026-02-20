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

import static org.assertj.core.api.Assertions.fail;
import static org.assertj.db.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.assertj.db.api.ChangeColumnValueAssert;
import org.assertj.db.api.TableColumnValueAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.assertj.db.type.Changes;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Tests on {@link  org.assertj.db.api.assertions.AssertOnValueEquality} class :
 * {@link  org.assertj.db.api.assertions.AssertOnValueEquality#isTrue()} method.
 *
 * @author Régis Pouiller
 * @author Julien Roy
 */
public class AssertOnValueEquality_IsTrue_Test extends AbstractTest {

  /**
   * This method tests the {@code isTrue} assertion method.
   */
  @Test
  @NeedReload
  public void test_is_true() {
    Table table = assertDbConnection.table("test").build();
    Changes changes = assertDbConnection.changes().tables(table).build().setStartPointNow();
    update("update test set var14 = 1 where var1 = 1");
    changes.setEndPointNow();

    ChangeColumnValueAssert changeColumnValueAssert = assertThat(changes).change().column("var2").valueAtEndPoint();
    ChangeColumnValueAssert changeColumnValueAssert2 = changeColumnValueAssert.isTrue();
    Assertions.assertThat(changeColumnValueAssert).isSameAs(changeColumnValueAssert2);

    TableColumnValueAssert tableColumnValueAssert = assertThat(table).column("var2").value();
    TableColumnValueAssert tableColumnValueAssert2 = tableColumnValueAssert.isTrue();
    Assertions.assertThat(tableColumnValueAssert).isSameAs(tableColumnValueAssert2);
  }

  /**
   * This method should fail because the value is false.
   */
  @Test
  @NeedReload
  public void should_fail_because_value_is_false() {
    Table table = assertDbConnection.table("test").build();
    Changes changes = assertDbConnection.changes().tables(table).build().setStartPointNow();
    update("update test set var14 = 1 where var1 = 10");
    changes.setEndPointNow();

    try {
      assertThat(changes).change().column("var2").valueAtEndPoint().isTrue();
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[Value at end point of Column at index 1 (column name : VAR2) of Change at index 0 (with primary key : [10]) of Changes on TEST table of 'sa/jdbc:h2:mem:test'] %n"
        + "Expecting:%n"
        + "  <false>%n"
        + "to be equal to: %n"
        + "  <true>"));
    }
    try {
      assertThat(table).column("var2").value(2).isTrue();
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[Value at index 2 of Column at index 1 (column name : VAR2) of TEST table] %n"
        + "Expecting:%n"
        + "  <false>%n"
        + "to be equal to: %n"
        + "  <true>"));
    }
  }
}
