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
 * Tests on {@link org.assertj.db.api.assertions.AssertOnValueInequality} class :
 * {@link org.assertj.db.api.assertions.AssertOnValueInequality#isNotEqualTo(String)} method.
 *
 * @author Régis Pouiller
 * @author Julien Roy
 */
public class AssertOnValueInequality_IsNotEqualTo_String_Test extends AbstractTest {

  /**
   * This method tests the {@code isNotEqualTo} assertion method.
   */
  @Test
  @NeedReload
  public void test_is_not_equal_to() {
    Table table = new Table(jdbcConnectionProvider, "test");
    Changes changes = new Changes(table).setStartPointNow();
    update("update test set var14 = 1 where var1 = 1");
    changes.setEndPointNow();

    ChangeColumnValueAssert changeColumnValueAssert = assertThat(changes).change().column("var12").valueAtEndPoint();
    ChangeColumnValueAssert changeColumnValueAssert2 = changeColumnValueAssert.isNotEqualTo("-");
    Assertions.assertThat(changeColumnValueAssert).isSameAs(changeColumnValueAssert2);

    TableColumnValueAssert tableColumnValueAssert = assertThat(table).column("var12").value();
    TableColumnValueAssert tableColumnValueAssert2 = tableColumnValueAssert.isNotEqualTo("-");
    Assertions.assertThat(tableColumnValueAssert).isSameAs(tableColumnValueAssert2);
  }

  /**
   * This method should fail because the value is equal to.
   */
  @Test
  @NeedReload
  public void should_fail_because_value_is_equal_to() {
    Table table = new Table(jdbcConnectionProvider, "test");
    Changes changes = new Changes(table).setStartPointNow();
    update("update test set var14 = 1 where var1 = 1");
    changes.setEndPointNow();

    try {
      assertThat(changes).change().column("var12").valueAtEndPoint().isNotEqualTo("text");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[Value at end point of Column at index 11 (column name : VAR12) of Change at index 0 (with primary key : [1]) of Changes on TEST table of 'sa/jdbc:h2:mem:test'] %n"
        + "Expecting:%n"
        + "  <\"text\">%n"
        + "not to be equal to: %n"
        + "  <\"text\">"));
    }
    try {
      assertThat(table).column("var12").value().isNotEqualTo("text");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[Value at index 0 of Column at index 11 (column name : VAR12) of TEST table] %n"
        + "Expecting:%n"
        + "  <\"text\">%n"
        + "not to be equal to: %n"
        + "  <\"text\">"));
    }
  }
}
