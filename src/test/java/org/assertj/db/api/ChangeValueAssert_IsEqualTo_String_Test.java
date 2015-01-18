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
import org.assertj.db.exception.AssertJDBException;
import org.assertj.db.type.Changes;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on the methods which verifies if a value is equal to a string.
 *
 * @author Régis Pouiller
 *
 */
public class ChangeValueAssert_IsEqualTo_String_Test extends AbstractTest {

  /**
   * This method tests that the value is equal to a string.
   */
  @Test
  public void test_if_value_is_equal_to_string() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForOtherTests();
    changes.setEndPointNow();

    assertThat(changes)
            .change().rowAtEndPoint().value(11)
                .isEqualTo("text")
            .change().rowAtEndPoint().value("var12")
                .isEqualTo("another text")
            .change(0).rowAtEndPoint().value(0)
                .isEqualTo("1")
            .change().rowAtEndPoint().value("var1")
                .isEqualTo("10")
            .change(0).rowAtEndPoint().value(2)
                .isEqualTo("2")
            .change().rowAtEndPoint().value("var3")
                .isEqualTo("20")
            .change(0).rowAtEndPoint().value(3)
                .isEqualTo("3")
            .change().rowAtEndPoint().value("var4")
                .isEqualTo("30")
            .change(0).rowAtEndPoint().value(4)
                .isEqualTo("4")
            .change().rowAtEndPoint().value("var5")
                .isEqualTo("40")
            .change(0).rowAtEndPoint().value(5)
                .isEqualTo("5.6")
            .change().rowAtEndPoint().value("var6")
                .isEqualTo("50.6")
            .change(0).rowAtEndPoint().value(6)
                .isEqualTo("7.8")
            .change().rowAtEndPoint().value("var7")
                .isEqualTo("70.8")
            .change(0).rowAtEndPoint().value(12)
                .isEqualTo("5")
            .change().rowAtEndPoint().value("var13")
                .isEqualTo("50")
            .change(0).rowAtEndPoint().value(13)
                .isEqualTo("7")
            .change().rowAtEndPoint().value("var14")
                .isEqualTo("70")
            .change(0).rowAtEndPoint().value(7)
                .isEqualTo("09:46:30")
            .change().rowAtEndPoint().value("var8")
                .isEqualTo("12:29:49")
            .change().rowAtEndPoint().value("var8")
                .isEqualTo("12:29:49")
            .change(0).rowAtEndPoint().value(8)
                .isEqualTo("2014-05-24")
            .change().rowAtEndPoint().value("var9")
                .isEqualTo("2014-05-30")
            .change().rowAtEndPoint().value("var9")
                .isEqualTo("2014-05-30")
            .change(0).rowAtEndPoint().value("var9")
                .isEqualTo("2014-05-24T00:00")
            .change().rowAtEndPoint().value("var9")
                .isEqualTo("2014-05-30T00:00")
            .change().rowAtEndPoint().value("var9")
                .isEqualTo("2014-05-30T00:00")
            .change(0).rowAtEndPoint().value("var9")
                .isEqualTo("2014-05-24T00:00:00")
            .change().rowAtEndPoint().value("var9")
                .isEqualTo("2014-05-30T00:00:00")
            .change().rowAtEndPoint().value("var9")
                .isEqualTo("2014-05-30T00:00:00")
            .change(0).rowAtEndPoint().value("var9")
                .isEqualTo("2014-05-24T00:00:00.000000000")
            .change().rowAtEndPoint().value("var9")
                .isEqualTo("2014-05-30T00:00:00.000000000")
            .change().rowAtEndPoint().value("var9")
                .isEqualTo("2014-05-30T00:00:00.000000000")
            .change(0).rowAtEndPoint().value(9)
                .isEqualTo("2014-05-24T09:46:30.000000000")
            .change().rowAtEndPoint().value("var10")
                .isEqualTo("2014-05-30T12:29:49.000000000")
            .change().rowAtEndPoint().value("var10")
                .isEqualTo("2014-05-30T00:00:00.000000000")
            .change(0).rowAtEndPoint().value("var10")
                .isEqualTo("2014-05-24T09:46:30.000000000")
            .change().rowAtEndPoint().value("var10")
                .isEqualTo("2014-05-30T12:29:49.000000000")
            .change().rowAtEndPoint().value("var10")
                .isEqualTo("2014-05-30T00:00:00")
            .change(0).rowAtEndPoint().value("var10")
                .isEqualTo("2014-05-24T09:46:30")
            .change().rowAtEndPoint().value("var10")
                .isEqualTo("2014-05-30T12:29:49")
            .change().rowAtEndPoint().value("var10")
                .isEqualTo("2014-05-30")
            .change(0).rowAtEndPoint().value("var10")
                .isEqualTo("2014-05-24T09:46:30")
            .change().rowAtEndPoint().value("var10")
                .isEqualTo("2014-05-30T12:29:49")
            .change().rowAtEndPoint().value("var10")
                .isEqualTo("2014-05-30T00:00");
  }

  /**
   * This method should fail because the value is not equal to the string.
   */
  @Test
  public void should_fail_because_value_is_not_equal_to_string() {
    try {
      Changes changes = new Changes(source).setStartPointNow();
      updateChangesForOtherTests();
      changes.setEndPointNow();

      assertThat(changes).change().rowAtEndPoint().value("var12").isEqualTo("Text");

      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Value at index 11 of Row at end point of Change at index 0 of Changes on tables of 'sa/jdbc:h2:mem:test' source] \n" +
                                                                                    "Expecting:\n" +
                                                                                    "  <\"text\">\n" +
                                                                                    "to be equal to: \n" +
                                                                                    "  <\"Text\">");
    }
  }

  /**
   * This method should fail because the value is not equal to the number.
   */
  @Test
  public void should_fail_because_value_is_not_equal_to_number() {
    try {
      Changes changes = new Changes(source).setStartPointNow();
      updateChangesForOtherTests();
      changes.setEndPointNow();

      assertThat(changes).change().rowAtEndPoint().value("var1").isEqualTo("2");

      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Value at index 0 of Row at end point of Change at index 0 of Changes on tables of 'sa/jdbc:h2:mem:test' source] \n" +
                                                                                    "Expecting:\n" +
                                                                                    "  <\"1\">\n" +
                                                                                    "to be equal to: \n" +
                                                                                    "  <\"2\">");
    }
  }

  /**
   * This method should fail because the value is not equal to the time.
   */
  @Test
  public void should_fail_because_value_is_not_equal_to_time() {
    try {
      Changes changes = new Changes(source).setStartPointNow();
      updateChangesForOtherTests();
      changes.setEndPointNow();

      assertThat(changes).change().rowAtEndPoint().value("var8").isEqualTo("09:46:31");

      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Value at index 7 of Row at end point of Change at index 0 of Changes on tables of 'sa/jdbc:h2:mem:test' source] \n" +
                                                                                    "Expecting:\n" +
                                                                                    "  <\"09:46:30.000000000\">\n" +
                                                                                    "to be equal to: \n" +
                                                                                    "  <\"09:46:31\">");
    }
  }

  /**
   * This method should throw a {@code AssertJDBException}, because the time is not parsable.
   *
   * <pre>
   * Expected <-9:46:31> is not correct to compare to <09:46:30>
   * </pre>
   */
  @Test(expected = AssertJDBException.class)
  public void should_throw_AssertJDBException_because_the_time_is_not_parsable() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForOtherTests();
    changes.setEndPointNow();

    assertThat(changes).change().rowAtEndPoint().value("var8").isEqualTo("-9:46:31");
  }

  /**
   * This method should fail because the value is not equal to the date.
   */
  @Test
  public void should_fail_because_value_is_not_equal_to_date() {
    try {
      Changes changes = new Changes(source).setStartPointNow();
      updateChangesForOtherTests();
      changes.setEndPointNow();

      assertThat(changes).change().rowAtEndPoint().value("var9").isEqualTo("2014-05-25");

      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Value at index 8 of Row at end point of Change at index 0 of Changes on tables of 'sa/jdbc:h2:mem:test' source] \n" +
                                                                                    "Expecting:\n" +
                                                                                    "  <\"2014-05-24\">\n" +
                                                                                    "to be equal to: \n" +
                                                                                    "  <\"2014-05-25\">");
    }
  }

  /**
   * This method should throw a {@code AssertJDBException}, because the date is not parsable.
   *
   * <pre>
   * Expected <2-14-05-25> is not correct to compare to <2014-05-25>
   * </pre>
   */
  @Test(expected = AssertJDBException.class)
  public void should_throw_AssertJDBException_because_the_date_is_not_parsable() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForOtherTests();
    changes.setEndPointNow();

    assertThat(changes).change().rowAtEndPoint().value("var9").isEqualTo("2-14-05-25");
  }

  /**
   * This method should fail because the value is not equal to the date/time.
   */
  @Test
  public void should_fail_because_value_is_not_equal_to_datetime() {
    try {
      Changes changes = new Changes(source).setStartPointNow();
      updateChangesForOtherTests();
      changes.setEndPointNow();

      assertThat(changes).change().rowAtEndPoint().value("var10").isEqualTo("2014-05-24T09:46:31");

      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Value at index 9 of Row at end point of Change at index 0 of Changes on tables of 'sa/jdbc:h2:mem:test' source] \n" +
                                                                                    "Expecting:\n" +
                                                                                    "  <\"2014-05-24T09:46:30.000000000\">\n" +
                                                                                    "to be equal to: \n" +
                                                                                    "  <\"2014-05-24T09:46:31\">");
    }
  }

  /**
   * This method should throw a {@code AssertJDBException}, because the date/time is not parsable.
   *
   * <pre>
   * Expected <2014-05-A4T09:46:31.000000000> is not correct to compare to <2014-05-24T09:46:31.000000000>
   * </pre>
   */
  @Test(expected = AssertJDBException.class)
  public void should_throw_AssertJDBException_because_the_datetime_is_not_parsable() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForOtherTests();
    changes.setEndPointNow();

    assertThat(changes).change().rowAtEndPoint().value("var10").isEqualTo("2014-05-A4T09:46:31.000000000");
  }

  /**
   * This method should fail because it is not possible to compare.
   */
  @Test(expected = AssertJDBException.class)
  public void should_fail_because_it_is_not_possible_to_compare() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForOtherTests();
    changes.setEndPointNow();

    assertThat(changes).change().rowAtEndPoint().value("var1").isEqualTo("***");
  }

  /**
   * This method should fail because the value is not a text.
   */
  @Test
  public void should_fail_because_value_is_not_a_text() {
    try {
      Changes changes = new Changes(source).setStartPointNow();
      updateChangesForOtherTests();
      changes.setEndPointNow();

      assertThat(changes).change().rowAtEndPoint().value("var2").as("var2").isEqualTo("Text");

      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[var2] \n" +
                                                                                    "Expecting:\n" +
                                                                                    "  <true>\n" +
                                                                                    "to be of type\n" +
                                                                                    "  <[TEXT, NUMBER, DATE, TIME, DATE_TIME]>\n" +
                                                                                    "but was of type\n" +
                                                                                    "  <BOOLEAN>");
    }
  }

}
