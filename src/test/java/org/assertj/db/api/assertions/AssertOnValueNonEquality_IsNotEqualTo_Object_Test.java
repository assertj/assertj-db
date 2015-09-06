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
 * Tests on {@link org.assertj.db.api.assertions.AssertOnValueNonEquality} class :
 * {@link org.assertj.db.api.assertions.AssertOnValueNonEquality#isNotEqualTo(Object)} method.
 *
 * @author Régis Pouiller
 *
 */
public class AssertOnValueNonEquality_IsNotEqualTo_Object_Test extends AbstractTest {

  /**
   * This method tests the {@code isNotEqualTo} assertion method.
   */
  @Test
  @NeedReload
  public void test_is_not_equal_to() {
    Table table = new Table(source, "test");
    Changes changes = new Changes(table).setStartPointNow();
    update("update test set var14 = 1 where var1 = 1");
    changes.setEndPointNow();

    ChangeColumnValueAssert changeColumnValueAssert = assertThat(changes).change().column("var10").valueAtEndPoint();
    ChangeColumnValueAssert changeColumnValueAssert2 = changeColumnValueAssert.isNotEqualTo((Object) null);
    Assertions.assertThat(changeColumnValueAssert).isSameAs(changeColumnValueAssert2);

    TableColumnValueAssert tableColumnValueAssert = assertThat(table).column("var10").value();
    TableColumnValueAssert tableColumnValueAssert2 = tableColumnValueAssert.isNotEqualTo((Object) null);
    Assertions.assertThat(tableColumnValueAssert).isSameAs(tableColumnValueAssert2);
  }

  /**
   * This method should fail because the value is equal to.
   */
  @Test
  @NeedReload
  public void should_fail_because_value_is_equal_to() {
    Table table = new Table(source, "test2");
    Changes changes = new Changes(table).setStartPointNow();
    update("update test2 set var14 = 1 where var1 is null");
    changes.setEndPointNow();

    try {
      assertThat(changes).change().column("var3").valueAtEndPoint().isNotEqualTo((Object) null);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[Value at end point of Column at index 2 (column name : VAR3) of Change at index 0 of Changes on test2 table of 'sa/jdbc:h2:mem:test' source] %n"
                                                                    + "Expecting:%n"
                                                                    + "  <null>%n"
                                                                    + "not to be equal to: %n"
                                                                    + "  <null>"));
    }
    try {
      assertThat(table).column("var3").value(1).isNotEqualTo((Object) null);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[Value at index 1 of Column at index 2 (column name : VAR3) of test2 table] %n"
                                                                    + "Expecting:%n"
                                                                    + "  <null>%n"
                                                                    + "not to be equal to: %n"
                                                                    + "  <null>"));
    }
  }
}
