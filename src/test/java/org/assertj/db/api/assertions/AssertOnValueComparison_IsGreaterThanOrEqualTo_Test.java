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
import org.assertj.db.api.ChangeColumnValueAssert;
import org.assertj.db.api.TableColumnValueAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.assertj.db.type.Changes;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Tests on {@link  org.assertj.db.api.assertions.AssertOnValueComparison} class :
 * {@link  org.assertj.db.api.assertions.AssertOnValueComparison#isGreaterThanOrEqualTo(Number)} method.
 *
 * @author Régis Pouiller
 * @author Julien Roy
 */
public class AssertOnValueComparison_IsGreaterThanOrEqualTo_Test extends AbstractTest {


  /**
   * This method tests the {@code isGreaterThanOrEqualTo} assertion method.
   */
  @Test
  @NeedReload
  public void test_is_greater_than_or_equal_to() {
    Table table = assertDbConnection.table("test").build();
    Changes changes = assertDbConnection.changes().tables(table).build().setStartPointNow();
    update("update test set var14 = 1 where var1 = 1");
    changes.setEndPointNow();

    ChangeColumnValueAssert changeColumnValueAssert = assertThat(changes).change().column("var3").valueAtEndPoint();
    ChangeColumnValueAssert changeColumnValueAssert2 = changeColumnValueAssert.isGreaterThanOrEqualTo(2);
    Assertions.assertThat(changeColumnValueAssert).isSameAs(changeColumnValueAssert2);

    TableColumnValueAssert tableColumnValueAssert = assertThat(table).column("var3").value();
    TableColumnValueAssert tableColumnValueAssert2 = tableColumnValueAssert.isGreaterThanOrEqualTo(2);
    Assertions.assertThat(tableColumnValueAssert).isSameAs(tableColumnValueAssert2);
  }

  /**
   * This method should fail because the value is less than.
   */
  @Test
  @NeedReload
  public void should_fail_because_value_is_less_than() {
    Table table = assertDbConnection.table("test").build();
    Changes changes = assertDbConnection.changes().tables(table).build().setStartPointNow();
    update("update test set var14 = 1 where var1 = 1");
    changes.setEndPointNow();

    try {
      assertThat(changes).change().column("var3").valueAtEndPoint().isGreaterThanOrEqualTo(3);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[Value at end point of Column at index 2 (column name : VAR3) of Change at index 0 (with primary key : [1]) of Changes on TEST table of 'sa/jdbc:h2:mem:test'] %n"
        + "Expecting:%n"
        + "  <2>%n"
        + "to be greater than or equal to %n"
        + "  <3>"));
    }
    try {
      assertThat(table).column("var3").value().isGreaterThanOrEqualTo(3);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[Value at index 0 of Column at index 2 (column name : VAR3) of TEST table] %n"
        + "Expecting:%n"
        + "  <2>%n"
        + "to be greater than or equal to %n"
        + "  <3>"));
    }
  }
}
