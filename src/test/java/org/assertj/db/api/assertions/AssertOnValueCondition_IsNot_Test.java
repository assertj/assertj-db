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
import org.assertj.core.api.Condition;
import org.assertj.db.api.ChangeColumnValueAssert;
import org.assertj.db.api.TableColumnValueAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.assertj.db.type.Changes;
import org.assertj.db.type.Table;
import org.junit.Test;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on {@link  AssertOnValueCondition} class :
 * {@link  AssertOnValueCondition#isNot(Condition)} method.
 *
 * @author Julien Roy
 *
 */
public class AssertOnValueCondition_IsNot_Test extends AbstractTest {

  private Condition<Byte> zero = new Condition<Byte>("isZero") {
    @Override
    public boolean matches(Byte value) {
      return Integer.valueOf(value) == 0;
    }
  };

  /**
   * This method tests the {@code isNot} assertion method.
   */
  @Test
  @NeedReload
  public void test_is_not() {
    Table table = new Table(source, "test");
    Changes changes = new Changes(table).setStartPointNow();
    update("update test set var14 = 1 where var1 = 1");
    changes.setEndPointNow();

    ChangeColumnValueAssert changeColumnValueAssert = assertThat(changes).change().column("var3").valueAtEndPoint();
    ChangeColumnValueAssert changeColumnValueAssert2 = changeColumnValueAssert.isNot(zero);
    Assertions.assertThat(changeColumnValueAssert).isSameAs(changeColumnValueAssert2);

    TableColumnValueAssert tableColumnValueAssert = assertThat(table).column("var3").value();
    TableColumnValueAssert tableColumnValueAssert2 = tableColumnValueAssert.isNot(zero);
    Assertions.assertThat(tableColumnValueAssert).isSameAs(tableColumnValueAssert2);
  }

  /**
   * This method should fail because the value not match with condition.
   */
  @Test
  @NeedReload
  public void should_fail_because_value_not_match_with_condition() {
    Table table = new Table(source, "test");
    Changes changes = new Changes(table).setStartPointNow();
    update("update test set var14 = 1 where var1 = 1000");
    changes.setEndPointNow();

    try {
      assertThat(changes).change().column("var3").valueAtEndPoint().isNot(zero);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format(
          "[Value at end point of Column at index 2 (column name : VAR3) of Change at index 0 (with primary key : [1000]) of "
          + "Changes on TEST table of 'sa/jdbc:h2:mem:test' source] %n"
          + "Expecting:%n"
          + " <0>%n"
          + "not to be <isZero>"));
    }
    try {
      assertThat(table).column("var3").value(3).isNot(zero);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage())
                .isEqualTo(String.format("[Value at index 3 of Column at index 2 (column name : VAR3) of TEST table] %n"
                                         + "Expecting:%n"
                                         + " <0>%n"
                                         + "not to be <isZero>"));
    }
  }
}
