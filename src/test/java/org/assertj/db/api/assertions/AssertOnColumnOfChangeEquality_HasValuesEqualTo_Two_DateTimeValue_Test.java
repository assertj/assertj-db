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
import org.assertj.db.api.ChangeAssert;
import org.assertj.db.api.ChangeColumnAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.assertj.db.type.Changes;
import org.assertj.db.type.DateTimeValue;
import org.assertj.db.type.DateValue;
import org.assertj.db.type.TimeValue;
import org.junit.Test;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on {@link org.assertj.db.api.assertions.AssertOnColumnOfChangeEquality} class :
 * {@link org.assertj.db.api.assertions.AssertOnColumnOfChangeEquality#hasValuesEqualTo(org.assertj.db.type.DateTimeValue, org.assertj.db.type.DateTimeValue)} method.
 *
 * @author Régis Pouiller
 *
 */
public class AssertOnColumnOfChangeEquality_HasValuesEqualTo_Two_DateTimeValue_Test extends AbstractTest {

  /**
   * This method tests the {@code hasValuesEqualTo} assertion method.
   */
  @Test
  @NeedReload
  public void test_have_values_equal_to() {
    Changes changes = new Changes(source).setStartPointNow();
    update("update test set var14 = 1 where var1 = 1");
    changes.setEndPointNow();

    ChangeAssert changeAssert = assertThat(changes).change();
    ChangeColumnAssert changeColumnAssert = changeAssert.column("var10");
    ChangeColumnAssert changeColumnAssert2 = changeColumnAssert.hasValuesEqualTo(DateTimeValue.of(
            DateValue.of(2014, 5, 24), TimeValue.of(9, 46, 30)), DateTimeValue.of(
            DateValue.of(2014, 5, 24), TimeValue.of(9, 46, 30)));
    Assertions.assertThat(changeColumnAssert).isSameAs(changeColumnAssert2);
  }

  /**
   * This method should fail because the value at start point is different.
   */
  @Test
  @NeedReload
  public void should_fail_because_value_at_start_point_is_different() {
    Changes changes = new Changes(source).setStartPointNow();
    update("insert into test(var1, var10) values(5, '2014-05-24 09:46:30')");
    changes.setEndPointNow();

    try {
      assertThat(changes).change().column("var10").hasValuesEqualTo(DateTimeValue.of(
              DateValue.of(2014, 5, 24), TimeValue.of(9, 46, 30)), DateTimeValue.of(
              DateValue.of(2014, 5, 24), TimeValue.of(9, 46, 30)));
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[Column at index 9 (column name : VAR10) of Change at index 0 (on table : TEST and with primary key : [5]) of Changes on tables of 'sa/jdbc:h2:mem:test' source] \n"
                                                      + "Expecting that start point:\n"
                                                      + "  <null>\n"
                                                      + "to be equal to: \n"
                                                      + "  <2014-05-24T09:46:30.000000000>");
    }
  }

  /**
   * This method should fail because the value at end point is different.
   */
  @Test
  @NeedReload
  public void should_fail_because_value_at_end_point_is_different() {
    Changes changes = new Changes(source).setStartPointNow();
    update("delete from test where var1 = 1");
    changes.setEndPointNow();

    try {
      assertThat(changes).change().column("var10").hasValuesEqualTo(DateTimeValue.of(
              DateValue.of(2014, 5, 24), TimeValue.of(9, 46, 30)), DateTimeValue.of(
              DateValue.of(2014, 5, 24), TimeValue.of(9, 46, 30)));
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[Column at index 9 (column name : VAR10) of Change at index 0 (on table : TEST and with primary key : [1]) of Changes on tables of 'sa/jdbc:h2:mem:test' source] \n"
                                                      + "Expecting that end point:\n"
                                                      + "  <null>\n"
                                                      + "to be equal to: \n"
                                                      + "  <2014-05-24T09:46:30.000000000>");
    }
  }
}
