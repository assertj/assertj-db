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
package org.assertj.db.api;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.assertj.db.type.Changes;
import org.assertj.db.type.DateValue;
import org.junit.Test;

import java.text.ParseException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on the methods which verifies if a value is before a date value.
 *
 * @author Régis Pouiller
 *
 */
public class ChangeValueAssert_IsBeforeOrEqualTo_DateValue_Test extends AbstractTest {

  /**
   * This method tests that the value is before or equal to a date.
   * @throws java.text.ParseException
   */
  @Test
  @NeedReload
  public void test_if_value_is_before_or_equal_to_date() throws ParseException {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForOtherTests();
    changes.setEndPointNow();

    assertThat(changes).change().rowAtEndPoint().value("var9")
            .isBeforeOrEqualTo(DateValue.of(2014, 5, 25))
            .change().rowAtEndPoint().value("var9")
            .isBeforeOrEqualTo(DateValue.parse("2014-05-31"))
            .change(0).rowAtEndPoint().value("var10")
            .isBeforeOrEqualTo(DateValue.of(2014, 5, 25))
            .change().rowAtEndPoint().value("var10")
            .isBeforeOrEqualTo(DateValue.parse("2014-05-31"))
            .change(0).rowAtEndPoint().value("var9")
            .isBeforeOrEqualTo(DateValue.of(2014, 5, 24))
            .change().rowAtEndPoint().value("var9")
            .isBeforeOrEqualTo(DateValue.parse("2014-05-30"));
  }

  /**
   * This method should fail because the value is not before or equal to the date value.
   */
  @Test
  @NeedReload
  public void should_fail_because_date_value_is_not_before_or_equal_to() {
    try {
      Changes changes = new Changes(source).setStartPointNow();
      updateChangesForOtherTests();
      changes.setEndPointNow();

      assertThat(changes).change().rowAtEndPoint().value("var9")
                       .isBeforeOrEqualTo(DateValue.of(2014, 5, 23));

      fail("An exception must be raised");
    } catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Value at index 8 of Row at end point of Change at index 0 of Changes on tables of 'sa/jdbc:h2:mem:test' source] \n" +
                                                                                    "Expecting:\n" +
                                                                                    "  <2014-05-24>\n" +
                                                                                    "to be before or equal to \n" +
                                                                                    "  <2014-05-23>");
    }
  }

  /**
   * This method should fail because the value is not a date.
   */
  @Test
  @NeedReload
  public void should_fail_because_value_is_not_a_date() {
    try {
      Changes changes = new Changes(source).setStartPointNow();
      updateChangesForOtherTests();
      changes.setEndPointNow();

      assertThat(changes).change().rowAtEndPoint().value("var1").as("column1")
                       .isBeforeOrEqualTo(DateValue.of(2014, 5, 25));

      fail("An exception must be raised");
    } catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[column1] \n" +
                                                                                    "Expecting:\n" +
                                                                                    "  <1>\n" +
                                                                                    "to be of type\n" +
                                                                                    "  <[DATE, DATE_TIME]>\n" +
                                                                                    "but was of type\n" +
                                                                                    "  <NUMBER>");
    }
  }

  /**
   * This method tests that the date/time value is before or equal to a date.
   * @throws ParseException
   */
  @Test
  @NeedReload
  public void test_if_datetime_value_is_before_or_equal_to_date() throws ParseException {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForOtherTests();
    changes.setEndPointNow();

    assertThat(changes).change(2).rowAtEndPoint().value("var10")
                     .isBeforeOrEqualTo(DateValue.parse("2014-05-31"));
  }

  /**
   * This method should fail because the date/time value is not before or equal to the date value.
   * @throws ParseException
   */
  @Test
  @NeedReload
  public void should_fail_because_datetime_value_is_not_before_or_equal() throws ParseException {
    try {
      Changes changes = new Changes(source).setStartPointNow();
      updateChangesForOtherTests();
      changes.setEndPointNow();

      assertThat(changes).change(2).rowAtEndPoint().value("var10")
                       .isBeforeOrEqualTo(DateValue.parse("2014-05-29"));

      fail("An exception must be raised");
    } catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Value at index 9 of Row at end point of Change at index 2 of Changes on tables of 'sa/jdbc:h2:mem:test' source] \n" +
                                                                                    "Expecting:\n" +
                                                                                    "  <2014-05-30T00:00:00.000000000>\n" +
                                                                                    "to be before or equal to \n" +
                                                                                    "  <2014-05-29T00:00:00.000000000>");
    }
  }

}
