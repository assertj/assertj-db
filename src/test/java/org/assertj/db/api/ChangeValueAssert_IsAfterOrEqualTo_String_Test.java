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
import org.assertj.db.exception.AssertJDBException;
import org.assertj.db.type.Changes;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on the methods which verifies if a value is after a string.
 *
 * @author Régis Pouiller
 *
 */
public class ChangeValueAssert_IsAfterOrEqualTo_String_Test extends AbstractTest {

  /**
   * This method tests that the value is after or equal to a string.
   */
  @Test
  @NeedReload
  public void test_if_value_is_after_or_equal_to_string() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForOtherTests();
    changes.setEndPointNow();

    assertThat(changes).change().rowAtEndPoint().value("var10")
            .isAfterOrEqualTo("2014-05-24T09:46:29")
            .change().rowAtEndPoint().value("var10")
            .isAfterOrEqualTo("2014-05-30T12:29:48")
            .change(0).rowAtEndPoint().value("var10")
            .isAfterOrEqualTo("2014-05-24")
            .change().rowAtEndPoint().value("var10")
            .isAfterOrEqualTo("2014-05-30")
            .change(0).rowAtEndPoint().value("var9")
            .isAfterOrEqualTo("2014-05-23T23:59:59")
            .change().rowAtEndPoint().value("var9")
            .isAfterOrEqualTo("2014-05-29T23:59:59")
            .change(0).rowAtEndPoint().value("var9")
            .isAfterOrEqualTo("2014-05-23")
            .change().rowAtEndPoint().value("var9")
            .isAfterOrEqualTo("2014-05-29")
            .change(0).rowAtEndPoint().value("var8")
            .isAfterOrEqualTo("09:46:29")
            .change().rowAtEndPoint().value("var8")
            .isAfterOrEqualTo("12:29:48")
            .change(0).rowAtEndPoint().value("var8")
            .isAfterOrEqualTo("09:46:30")
            .change().rowAtEndPoint().value("var8")
            .isAfterOrEqualTo("12:29:49")
            .change().rowAtEndPoint().value("var8")
            .isAfterOrEqualTo("12:29:49")
            .change(0).rowAtEndPoint().value("var9")
            .isAfterOrEqualTo("2014-05-24")
            .change().rowAtEndPoint().value("var9")
            .isAfterOrEqualTo("2014-05-30")
            .change().rowAtEndPoint().value("var9")
            .isAfterOrEqualTo("2014-05-30")
            .change(0).rowAtEndPoint().value("var9")
            .isAfterOrEqualTo("2014-05-24T00:00")
            .change().rowAtEndPoint().value("var9")
            .isAfterOrEqualTo("2014-05-30T00:00")
            .change().rowAtEndPoint().value("var9")
            .isAfterOrEqualTo("2014-05-30T00:00")
            .change(0).rowAtEndPoint().value("var9")
            .isAfterOrEqualTo("2014-05-24T00:00:00")
            .change().rowAtEndPoint().value("var9")
            .isAfterOrEqualTo("2014-05-30T00:00:00")
            .change().rowAtEndPoint().value("var9")
            .isAfterOrEqualTo("2014-05-30T00:00:00")
            .change(0).rowAtEndPoint().value("var9")
            .isAfterOrEqualTo("2014-05-24T00:00:00.000000000")
            .change().rowAtEndPoint().value("var9")
            .isAfterOrEqualTo("2014-05-30T00:00:00.000000000")
            .change().rowAtEndPoint().value("var9")
            .isAfterOrEqualTo("2014-05-30T00:00:00.000000000")
            .change(0).rowAtEndPoint().value("var10")
            .isAfterOrEqualTo("2014-05-24T09:46:30.000000000")
            .change().rowAtEndPoint().value("var10")
            .isAfterOrEqualTo("2014-05-30T12:29:49.000000000")
            .change().rowAtEndPoint().value("var10")
            .isAfterOrEqualTo("2014-05-30T00:00:00")
            .change(0).rowAtEndPoint().value("var10")
            .isAfterOrEqualTo("2014-05-24T09:46:30")
            .change().rowAtEndPoint().value("var10")
            .isAfterOrEqualTo("2014-05-30T12:29:49")
            .change().rowAtEndPoint().value("var10")
            .isAfterOrEqualTo("2014-05-30")
            .change(0).rowAtEndPoint().value("var10")
            .isAfterOrEqualTo("2014-05-24T09:46:30")
            .change().rowAtEndPoint().value("var10")
            .isAfterOrEqualTo("2014-05-30T12:29:49")
            .change().rowAtEndPoint().value("var10")
            .isAfterOrEqualTo("2014-05-30T00:00");

  }

  /**
   * This method should fail because the value is not after or equal to the string.
   */
  @Test
  @NeedReload
  public void should_fail_because_time_value_is_not_after_or_equal_to_time() {
    try {
      Changes changes = new Changes(source).setStartPointNow();
      updateChangesForOtherTests();
      changes.setEndPointNow();

      assertThat(changes).change().rowAtEndPoint().value("var8")
                       .isAfterOrEqualTo("09:46:31");

      fail("An exception must be raised");
    } catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Value at index 7 of Row at end point of Change at index 0 of Changes on tables of 'sa/jdbc:h2:mem:test' source] \n" +
                                                                                    "Expecting:\n" +
                                                                                    "  <09:46:30.000000000>\n" +
                                                                                    "to be after or equal to \n" +
                                                                                    "  <09:46:31.000000000>");
    }
  }

  /**
   * This method should fail because the value is not after or equal to the string.
   */
  @Test
  @NeedReload
  public void should_fail_because_date_value_is_not_after_or_equal_to_date() {
    try {
      Changes changes = new Changes(source).setStartPointNow();
      updateChangesForOtherTests();
      changes.setEndPointNow();

      assertThat(changes).change().rowAtEndPoint().value("var9")
                       .isAfterOrEqualTo("2014-05-25");

      fail("An exception must be raised");
    } catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Value at index 8 of Row at end point of Change at index 0 of Changes on tables of 'sa/jdbc:h2:mem:test' source] \n" +
                                                                                    "Expecting:\n" +
                                                                                    "  <2014-05-24T00:00:00.000000000>\n" +
                                                                                    "to be after or equal to \n" +
                                                                                    "  <2014-05-25T00:00:00.000000000>");
    }
  }

  /**
   * This method should fail because the value is not after or equal to the string.
   */
  @Test
  @NeedReload
  public void should_fail_because_date_value_is_not_after_or_equal_to_datetime() {
    try {
      Changes changes = new Changes(source).setStartPointNow();
      updateChangesForOtherTests();
      changes.setEndPointNow();

      assertThat(changes).change().rowAtEndPoint().value("var9")
                       .isAfterOrEqualTo("2014-05-25T00:00");

      fail("An exception must be raised");
    } catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Value at index 8 of Row at end point of Change at index 0 of Changes on tables of 'sa/jdbc:h2:mem:test' source] \n" +
                                                                                    "Expecting:\n" +
                                                                                    "  <2014-05-24T00:00:00.000000000>\n" +
                                                                                    "to be after or equal to \n" +
                                                                                    "  <2014-05-25T00:00:00.000000000>");
    }
  }

  /**
   * This method should fail because the value is not after or equal to the string.
   */
  @Test
  @NeedReload
  public void should_fail_because_datetime_value_is_not_after_or_equal_to_date() {
    try {
      Changes changes = new Changes(source).setStartPointNow();
      updateChangesForOtherTests();
      changes.setEndPointNow();

      assertThat(changes).change(2).rowAtEndPoint().value("var10")
                       .isAfterOrEqualTo("2014-05-31");

      fail("An exception must be raised");
    } catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Value at index 9 of Row at end point of Change at index 2 of Changes on tables of 'sa/jdbc:h2:mem:test' source] \n" +
                                                                                    "Expecting:\n" +
                                                                                    "  <2014-05-30T00:00:00.000000000>\n" +
                                                                                    "to be after or equal to \n" +
                                                                                    "  <2014-05-31T00:00:00.000000000>");
    }
  }

  /**
   * This method should fail because the value is not after or equal to the string.
   */
  @Test
  @NeedReload
  public void should_fail_because_datetime_value_is_not_after_or_equal_to_datetime() {
    try {
      Changes changes = new Changes(source).setStartPointNow();
      updateChangesForOtherTests();
      changes.setEndPointNow();

      assertThat(changes).change().rowAtEndPoint().value("var10")
                       .isAfterOrEqualTo("2014-05-24T09:46:31");

      fail("An exception must be raised");
    } catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Value at index 9 of Row at end point of Change at index 0 of Changes on tables of 'sa/jdbc:h2:mem:test' source] \n" +
                                                                                    "Expecting:\n" +
                                                                                    "  <2014-05-24T09:46:30.000000000>\n" +
                                                                                    "to be after or equal to \n" +
                                                                                    "  <2014-05-24T09:46:31.000000000>");
    }
  }

  /**
   * This method should fail because the value is not a date/time.
   */
  @Test
  @NeedReload
  public void should_fail_because_value_is_not_a_datetime() {
    try {
      Changes changes = new Changes(source).setStartPointNow();
      updateChangesForOtherTests();
      changes.setEndPointNow();

      assertThat(changes).change().rowAtEndPoint().value("var1").as("var1")
                       .isAfterOrEqualTo("2014-05-24T09:46:30");

      fail("An exception must be raised");
    } catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[var1] \n" +
                                                                                    "Expecting:\n" +
                                                                                    "  <1>\n" +
                                                                                    "to be of type\n" +
                                                                                    "  <[DATE, TIME, DATE_TIME]>\n" +
                                                                                    "but was of type\n" +
                                                                                    "  <NUMBER>");
    }
  }

  /**
   * This method should fail because the string is not correct to compare with date.
   */
  @Test(expected = AssertJDBException.class)
  @NeedReload
  public void should_fail_because_string_is_not_correct_to_compare_with_date() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForOtherTests();
    changes.setEndPointNow();

    assertThat(changes).change().rowAtEndPoint().value("var9")
            .isAfterOrEqualTo("a014-05-24");
  }

  /**
   * This method should fail because the string is not correct to compare with time.
   */
  @Test(expected = AssertJDBException.class)
  @NeedReload
  public void should_fail_because_string_is_not_correct_to_compare_with_time() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForOtherTests();
    changes.setEndPointNow();

    assertThat(changes).change().rowAtEndPoint().value("var8")
            .isAfterOrEqualTo("a9:46:29");
  }

  /**
   * This method should fail because the string is not correct to compare with date/time.
   */
  @Test(expected = AssertJDBException.class)
  @NeedReload
  public void should_fail_because_string_is_not_correct_to_compare_with_datetime() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForOtherTests();
    changes.setEndPointNow();

    assertThat(changes).change().rowAtEndPoint().value("var10")
            .isAfterOrEqualTo("a014-05-24T09:46:29");
  }
}
