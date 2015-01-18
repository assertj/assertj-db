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
 * Tests on the methods which verifies if a value is not equal to a string.
 *
 * @author Régis Pouiller
 *
 */
public class ChangeValueAssert_IsNotEqualTo_String_Test extends AbstractTest {

  /**
   * This method tests that the value is not equal to a string.
   */
  @Test
  @NeedReload
  public void test_if_value_is_not_equal_to_string() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForOtherTests();
    changes.setEndPointNow();

    assertThat(changes)
            .change().rowAtEndPoint().value("var12").isNotEqualTo("Text")
            .change().rowAtEndPoint().value("var12").isNotEqualTo("Another text")
            .change(0).rowAtEndPoint().value("var1").isNotEqualTo("2")
            .change().rowAtEndPoint().value("var1").isNotEqualTo("11")
            .change(0).rowAtEndPoint().value("var3").isNotEqualTo("3")
            .change().rowAtEndPoint().value("var3").isNotEqualTo("21")
            .change(0).rowAtEndPoint().value("var4").isNotEqualTo("4")
            .change().rowAtEndPoint().value("var4").isNotEqualTo("31")
            .change(0).rowAtEndPoint().value("var5").isNotEqualTo("5")
            .change().rowAtEndPoint().value("var5").isNotEqualTo("41")
            .change(0).rowAtEndPoint().value("var6").isNotEqualTo("6.6")
            .change().rowAtEndPoint().value("var6").isNotEqualTo("51.6")
            .change(0).rowAtEndPoint().value("var7").isNotEqualTo("8.8")
            .change().rowAtEndPoint().value("var7").isNotEqualTo("71.8")
            .change(0).rowAtEndPoint().value("var13").isNotEqualTo("6")
            .change().rowAtEndPoint().value("var13").isNotEqualTo("51")
            .change(0).rowAtEndPoint().value("var14").isNotEqualTo("8")
            .change().rowAtEndPoint().value("var14").isNotEqualTo("71")
            .change(0).rowAtEndPoint().value("var8").isNotEqualTo("09:46:31")
            .change().rowAtEndPoint().value("var8").isNotEqualTo("12:29:50")
            .change().rowAtEndPoint().value("var8").isNotEqualTo("12:29:50")
            .change(0).rowAtEndPoint().value("var9").isNotEqualTo("2014-05-25")
            .change().rowAtEndPoint().value("var9").isNotEqualTo("2014-05-31")
            .change().rowAtEndPoint().value("var9").isNotEqualTo("2014-05-31")
            .change(0).rowAtEndPoint().value("var9").isNotEqualTo("2014-05-24T00:01")
            .change().rowAtEndPoint().value("var9").isNotEqualTo("2014-05-30T00:01")
            .change().rowAtEndPoint().value("var9").isNotEqualTo("2014-05-30T00:01")
            .change(0).rowAtEndPoint().value("var9").isNotEqualTo("2014-05-24T00:00:01")
            .change().rowAtEndPoint().value("var9").isNotEqualTo("2014-05-30T00:00:01")
            .change().rowAtEndPoint().value("var9").isNotEqualTo("2014-05-30T00:00:01")
            .change(0).rowAtEndPoint().value("var9").isNotEqualTo("2014-05-24T00:00:00.000000001")
            .change().rowAtEndPoint().value("var9").isNotEqualTo("2014-05-30T00:00:00.000000001")
            .change().rowAtEndPoint().value("var9").isNotEqualTo("2014-05-30T00:00:00.000000001")
            .change(0).rowAtEndPoint().value("var10").isNotEqualTo("2014-05-24T09:46:30.000000001")
            .change().rowAtEndPoint().value("var10").isNotEqualTo("2014-05-30T12:29:49.000000001")
            .change().rowAtEndPoint().value("var10").isNotEqualTo("2014-05-30T00:00:01")
            .change(0).rowAtEndPoint().value("var10").isNotEqualTo("2014-05-24T09:46:31")
            .change().rowAtEndPoint().value("var10").isNotEqualTo("2014-05-30T12:29:50")
            .change().rowAtEndPoint().value("var10").isNotEqualTo("2014-05-31")
            .change(0).rowAtEndPoint().value("var10").isNotEqualTo("2014-05-24T09:46:31")
            .change().rowAtEndPoint().value("var10").isNotEqualTo("2014-05-30T12:29:50")
            .change().rowAtEndPoint().value("var10").isNotEqualTo("2014-05-30T00:01");
  }

  /**
   * This method should fail because the value is equal to the string.
   */
  @Test
  @NeedReload
  public void should_fail_because_value_is_equal_to_string() {
    try {
      Changes changes = new Changes(source).setStartPointNow();
      updateChangesForOtherTests();
      changes.setEndPointNow();

      assertThat(changes).change().rowAtEndPoint().value("var12").isNotEqualTo("text");

      fail("An exception must be raised");
    } catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Value at index 11 of Row at end point of Change at index 0 of Changes on tables of 'sa/jdbc:h2:mem:test' source] \n" +
                                                                                    "Expecting:\n" +
                                                                                    "  <\"text\">\n" +
                                                                                    "not to be equal to: \n" +
                                                                                    "  <\"text\">");
    }
  }

  /**
   * This method should fail because the value is equal to the number.
   */
  @Test
  @NeedReload
  public void should_fail_because_value_is_equal_to_number() {
    try {
      Changes changes = new Changes(source).setStartPointNow();
      updateChangesForOtherTests();
      changes.setEndPointNow();

      assertThat(changes).change().rowAtEndPoint().value("var1").isNotEqualTo("1");

      fail("An exception must be raised");
    } catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Value at index 0 of Row at end point of Change at index 0 of Changes on tables of 'sa/jdbc:h2:mem:test' source] \n" +
                                                                                    "Expecting:\n" +
                                                                                    "  <\"1\">\n" +
                                                                                    "not to be equal to: \n" +
                                                                                    "  <\"1\">");
    }
  }

  /**
   * This method should fail because the value is equal to the time.
   */
  @Test
  @NeedReload
  public void should_fail_because_value_is_equal_to_time() {
    try {
      Changes changes = new Changes(source).setStartPointNow();
      updateChangesForOtherTests();
      changes.setEndPointNow();

      assertThat(changes).change().rowAtEndPoint().value("var8").isNotEqualTo("09:46:30");

      fail("An exception must be raised");
    } catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Value at index 7 of Row at end point of Change at index 0 of Changes on tables of 'sa/jdbc:h2:mem:test' source] \n" +
                                                                                    "Expecting:\n" +
                                                                                    "  <\"09:46:30.000000000\">\n" +
                                                                                    "not to be equal to: \n" +
                                                                                    "  <\"09:46:30\">");
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
  @NeedReload
  public void should_throw_AssertJDBException_because_the_time_is_not_parsable() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForOtherTests();
    changes.setEndPointNow();

    assertThat(changes).change().rowAtEndPoint().value("var8").isNotEqualTo("-9:46:31");
  }

  /**
   * This method should fail because the value is equal to the date.
   */
  @Test
  @NeedReload
  public void should_fail_because_value_is_equal_to_date() {
    try {
      Changes changes = new Changes(source).setStartPointNow();
      updateChangesForOtherTests();
      changes.setEndPointNow();

      assertThat(changes).change().rowAtEndPoint().value("var9").isNotEqualTo("2014-05-24");

      fail("An exception must be raised");
    } catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Value at index 8 of Row at end point of Change at index 0 of Changes on tables of 'sa/jdbc:h2:mem:test' source] \n" +
                                                                                    "Expecting:\n" +
                                                                                    "  <\"2014-05-24\">\n" +
                                                                                    "not to be equal to: \n" +
                                                                                    "  <\"2014-05-24\">");
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
  @NeedReload
  public void should_throw_AssertJDBException_because_the_date_is_not_parsable() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForOtherTests();
    changes.setEndPointNow();

    assertThat(changes).change().rowAtEndPoint().value("var9").isNotEqualTo("2-14-05-25");
  }

  /**
   * This method should fail because the value is equal to the date/time.
   */
  @Test
  @NeedReload
  public void should_fail_because_value_is_equal_to_datetime() {
    try {
      Changes changes = new Changes(source).setStartPointNow();
      updateChangesForOtherTests();
      changes.setEndPointNow();

      assertThat(changes).change().rowAtEndPoint().value("var10").isNotEqualTo("2014-05-24T09:46:30");

      fail("An exception must be raised");
    } catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Value at index 9 of Row at end point of Change at index 0 of Changes on tables of 'sa/jdbc:h2:mem:test' source] \n" +
                                                                                    "Expecting:\n" +
                                                                                    "  <\"2014-05-24T09:46:30.000000000\">\n" +
                                                                                    "not to be equal to: \n" +
                                                                                    "  <\"2014-05-24T09:46:30\">");
    }
  }

  /**
   * This method should throw a {@code AssertJDBException}, because the date/time is not parsable.
   *
   * <pre>
   * Expected <2014-05-A4T09:46:31.000000000> is not correct to compare to <2014-05-24T09:46:31.0>
   * </pre>
   */
  @Test(expected = AssertJDBException.class)
  @NeedReload
  public void should_throw_AssertJDBException_because_the_datetime_is_not_parsable() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForOtherTests();
    changes.setEndPointNow();

    assertThat(changes).change().rowAtEndPoint().value("var10").isNotEqualTo("2014-05-A4T09:46:31.000000000");
  }

  /**
   * This method should fail because it is not possible to compare.
   */
  @Test(expected = AssertJDBException.class)
  @NeedReload
  public void should_fail_because_it_is_not_possible_to_compare() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForOtherTests();
    changes.setEndPointNow();

    assertThat(changes).change().rowAtEndPoint().value("var1").isNotEqualTo("***");
  }

  /**
   * This method should fail because the value is not a text.
   */
  @Test
  @NeedReload
  public void should_fail_because_value_is_not_a_text() {
    try {
      Changes changes = new Changes(source).setStartPointNow();
      updateChangesForOtherTests();
      changes.setEndPointNow();

      assertThat(changes).change().rowAtEndPoint().value("var2").as("var2").isNotEqualTo("Text");

      fail("An exception must be raised");
    } catch (AssertionError e) {
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
