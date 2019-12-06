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
package org.assertj.db.type;

import org.assertj.db.common.AbstractTest;
import org.junit.Test;

import java.sql.Time;
import java.text.ParseException;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests on the time value.
 * 
 * @author Régis Pouiller
 * @author Julien Roy
 *
 */
public class TimeValue_Test extends AbstractTest {

  /**
   * This method tests the constructor with a {@code String} containing nanoseconds.
   */
  @Test
  public void test_contructor_with_string_containing_nanoseconds() throws ParseException {
    TimeValue timeValue = new TimeValue("09:01:06.000000003");
    assertThat(timeValue.getHours()).isEqualTo(9);
    assertThat(timeValue.getMinutes()).isEqualTo(1);
    assertThat(timeValue.getSeconds()).isEqualTo(6);
    assertThat(timeValue.getNanoSeconds()).isEqualTo(3);
  }

  /**
   * This method tests the constructor with a {@code String} containing seconds.
   */
  @Test
  public void test_contructor_with_string_containing_seconds() throws ParseException {
    TimeValue timeValue = new TimeValue("09:01:06");
    assertThat(timeValue.getHours()).isEqualTo(9);
    assertThat(timeValue.getMinutes()).isEqualTo(1);
    assertThat(timeValue.getSeconds()).isEqualTo(6);
    assertThat(timeValue.getNanoSeconds()).isEqualTo(0);
  }

  /**
   * This method tests the constructor with a {@code String} containing minutes.
   */
  @Test
  public void test_contructor_with_string_containing_minutes() throws ParseException {
    TimeValue timeValue = new TimeValue("09:01");
    assertThat(timeValue.getHours()).isEqualTo(9);
    assertThat(timeValue.getMinutes()).isEqualTo(1);
    assertThat(timeValue.getSeconds()).isEqualTo(0);
    assertThat(timeValue.getNanoSeconds()).isEqualTo(0);
  }

  /**
   * This method should throw a {@code NullPointerException} because passing a {@code null} parameter to constructor
   * with a {@code String}.
   */
  @Test(expected = NullPointerException.class)
  public void should_constructor_with_string_fail_if_date_is_null() throws ParseException {
    new TimeValue((String) null);
  }

  /**
   * This method should throw a {@code ParseException} because passing a parameter to constructor with a bad length.
   */
  @Test(expected = ParseException.class)
  public void should_constructor_with_string_fail_if_date_has_bad_length() throws ParseException {
    new TimeValue("12345678901");
  }

  /**
   * This method should throw a {@code ParseException} because passing a parameter to constructor with a bad character
   * on year.
   */
  @Test(expected = ParseException.class)
  public void should_constructor_with_string_fail_if_date_has_bad_character_on_year() throws ParseException {
    new TimeValue("a9:01");
  }

  /**
   * This method should throw a {@code ParseException} because passing a parameter to constructor with a bad character
   * on separator.
   */
  @Test(expected = ParseException.class)
  public void should_constructor_with_string_fail_if_date_has_bad_character_on_separator() throws ParseException {
    new TimeValue("09a01");
  }

  /**
   * This method tests the constructor with a {@code Calendar}.
   */
  @Test
  public void test_contructor_with_calendar() throws ParseException {
    Calendar calendar = Calendar.getInstance();
    calendar.set(2007, Calendar.DECEMBER, 23, 9, 1, 6);
    calendar.set(Calendar.MILLISECOND, 50);
    TimeValue timeValue = new TimeValue(calendar);
    assertThat(timeValue.getHours()).isEqualTo(9);
    assertThat(timeValue.getMinutes()).isEqualTo(1);
    assertThat(timeValue.getSeconds()).isEqualTo(6);
    assertThat(timeValue.getNanoSeconds()).isEqualTo(50000000);
  }

  /**
   * This method tests the constructor with a {@code Time} containing seconds.
   */
  @Test
  public void test_contructor_with_time_containing_seconds() throws ParseException {
    TimeValue timeValue = new TimeValue(Time.valueOf("09:01:06"));
    assertThat(timeValue.getHours()).isEqualTo(9);
    assertThat(timeValue.getMinutes()).isEqualTo(1);
    assertThat(timeValue.getSeconds()).isEqualTo(6);
    assertThat(timeValue.getNanoSeconds()).isEqualTo(0);
  }

  /**
   * This method should throw a {@code NullPointerException} because passing a {@code null} parameter to constructor
   * with a {@code Time}.
   */
  @Test(expected = NullPointerException.class)
  public void should_constructor_with_time_fail_if_date_is_null() throws ParseException {
    new TimeValue((String) null);
  }

  /**
   * This method tests the constructor with values (4 {@code int}).
   */
  @Test
  public void test_contructor_with_four_values() throws ParseException {
    TimeValue timeValue = new TimeValue(9, 1, 6, 3);
    assertThat(timeValue.getHours()).isEqualTo(9);
    assertThat(timeValue.getMinutes()).isEqualTo(1);
    assertThat(timeValue.getSeconds()).isEqualTo(6);
    assertThat(timeValue.getNanoSeconds()).isEqualTo(3);
  }

  /**
   * This method tests the constructor with values (3 {@code int}).
   */
  @Test
  public void test_contructor_with_three_values() throws ParseException {
    TimeValue timeValue = new TimeValue(9, 1, 6);
    assertThat(timeValue.getHours()).isEqualTo(9);
    assertThat(timeValue.getMinutes()).isEqualTo(1);
    assertThat(timeValue.getSeconds()).isEqualTo(6);
    assertThat(timeValue.getNanoSeconds()).isEqualTo(0);
  }

  /**
   * This method tests the constructor with values (2 {@code int}).
   */
  @Test
  public void test_contructor_with_two_values() throws ParseException {
    TimeValue timeValue = new TimeValue(9, 1);
    assertThat(timeValue.getHours()).isEqualTo(9);
    assertThat(timeValue.getMinutes()).isEqualTo(1);
    assertThat(timeValue.getSeconds()).isEqualTo(0);
    assertThat(timeValue.getNanoSeconds()).isEqualTo(0);
  }

  /**
   * This method tests the {@code parse} static method with a {@code String} containing nanoseconds.
   */
  @Test
  public void test_parse_containing_nanoseconds() throws ParseException {
    TimeValue timeValue = TimeValue.parse("09:01:06.000000003");
    assertThat(timeValue.getHours()).isEqualTo(9);
    assertThat(timeValue.getMinutes()).isEqualTo(1);
    assertThat(timeValue.getSeconds()).isEqualTo(6);
    assertThat(timeValue.getNanoSeconds()).isEqualTo(3);
  }

  /**
   * This method tests the {@code parse} static method with a {@code String} containing seconds.
   */
  @Test
  public void test_parse_containing_seconds() throws ParseException {
    TimeValue timeValue = TimeValue.parse("09:01:06");
    assertThat(timeValue.getHours()).isEqualTo(9);
    assertThat(timeValue.getMinutes()).isEqualTo(1);
    assertThat(timeValue.getSeconds()).isEqualTo(6);
    assertThat(timeValue.getNanoSeconds()).isEqualTo(0);
  }

  /**
   * This method tests the {@code parse} static method with a {@code String} containing minutes.
   */
  @Test
  public void test_parse_containing_minutes() throws ParseException {
    TimeValue timeValue = TimeValue.parse("09:01");
    assertThat(timeValue.getHours()).isEqualTo(9);
    assertThat(timeValue.getMinutes()).isEqualTo(1);
    assertThat(timeValue.getSeconds()).isEqualTo(0);
    assertThat(timeValue.getNanoSeconds()).isEqualTo(0);
  }

  /**
   * This method should throw a {@code NullPointerException} because passing a {@code null} parameter to {@code parse}
   * static method with a {@code String}.
   */
  @Test(expected = NullPointerException.class)
  public void should_parse_fail_if_date_is_null() throws ParseException {
    TimeValue.parse(null);
  }

  /**
   * This method should throw a {@code ParseException} because passing a parameter to {@code parse} static method with a
   * bad length.
   */
  @Test(expected = ParseException.class)
  public void should_parse_fail_if_date_has_bad_length() throws ParseException {
    TimeValue.parse("12345678901");
  }

  /**
   * This method should throw a {@code ParseException} because passing a parameter to {@code parse} static method with a
   * bad character on year.
   */
  @Test(expected = ParseException.class)
  public void should_parse_fail_if_date_has_bad_character_on_year() throws ParseException {
    TimeValue.parse("a9:01");
  }

  /**
   * This method should throw a {@code ParseException} because passing a parameter to {@code parse} static method with a
   * bad character on separator.
   */
  @Test(expected = ParseException.class)
  public void should_parse_fail_if_date_has_bad_character_on_separator() throws ParseException {
    TimeValue.parse("09a01");
  }

  /**
   * This method tests the {@code now} static method.
   */
  @Test
  public void test_now() throws ParseException {
    Calendar calendarFirst = Calendar.getInstance();
    TimeValue timeValue = TimeValue.now();
    Calendar calendarSecond = Calendar.getInstance();
    if (calendarFirst.get(Calendar.DAY_OF_YEAR) == calendarSecond.get(Calendar.DAY_OF_YEAR)) {
      assertThat(timeValue.getHours()).isBetween(calendarFirst.get(Calendar.HOUR_OF_DAY),
                                                calendarSecond.get(Calendar.HOUR_OF_DAY));
    }
    else {
      assertThat(timeValue.getHours()).isBetween(calendarFirst.get(Calendar.HOUR_OF_DAY) - 24,
                                                calendarSecond.get(Calendar.HOUR_OF_DAY) + 24);
    }
    if (calendarFirst.get(Calendar.DAY_OF_YEAR) == calendarSecond.get(Calendar.HOUR_OF_DAY)) {
      assertThat(timeValue.getMinutes()).isBetween(calendarFirst.get(Calendar.MINUTE),
                                                   calendarSecond.get(Calendar.MINUTE));
    }
    else {
      assertThat(timeValue.getMinutes()).isBetween(calendarFirst.get(Calendar.MINUTE) - 60,
                                                   calendarSecond.get(Calendar.MINUTE) + 60);
    }
    if (calendarFirst.get(Calendar.MINUTE) == calendarSecond.get(Calendar.MINUTE)) {
      assertThat(timeValue.getSeconds()).isBetween(calendarFirst.get(Calendar.SECOND),
                                                   calendarSecond.get(Calendar.SECOND));
    }
    else {
      assertThat(timeValue.getSeconds()).isBetween(calendarFirst.get(Calendar.SECOND) - 60,
                                                   calendarSecond.get(Calendar.SECOND) + 60);
    }
    if (calendarFirst.get(Calendar.SECOND) == calendarSecond.get(Calendar.SECOND)) {
      assertThat(timeValue.getNanoSeconds()).isBetween(calendarFirst.get(Calendar.MILLISECOND) * 1000000,
                                                       calendarSecond.get(Calendar.MILLISECOND) * 1000000);
    }
    else {
      assertThat(timeValue.getNanoSeconds()).isBetween(calendarFirst.get(Calendar.MILLISECOND) * 1000000 - 1000000000,
                                                       calendarSecond.get(Calendar.MILLISECOND) * 1000000 + 1000000000);
    }
  }

  /**
   * This method tests the constructor with a {@code Calendar}.
   */
  @Test
  public void test_from_calendar() throws ParseException {
    Calendar calendar = Calendar.getInstance();
    calendar.set(2007, Calendar.DECEMBER, 23, 9, 1, 6);
    calendar.set(Calendar.MILLISECOND, 50);
    TimeValue timeValue = TimeValue.from(calendar);
    assertThat(timeValue.getHours()).isEqualTo(9);
    assertThat(timeValue.getMinutes()).isEqualTo(1);
    assertThat(timeValue.getSeconds()).isEqualTo(6);
    assertThat(timeValue.getNanoSeconds()).isEqualTo(50000000);
  }

  /**
   * This method tests the {@code from} method containing seconds.
   */
  @Test
  public void test_from_containing_seconds() throws ParseException {
    TimeValue timeValue = TimeValue.from(Time.valueOf("09:01:06"));
    assertThat(timeValue.getHours()).isEqualTo(9);
    assertThat(timeValue.getMinutes()).isEqualTo(1);
    assertThat(timeValue.getSeconds()).isEqualTo(6);
    assertThat(timeValue.getNanoSeconds()).isEqualTo(0);
  }

  /**
   * This method should throw a {@code NullPointerException} because passing a {@code null} parameter to {@code from}
   * method.
   */
  @Test(expected = NullPointerException.class)
  public void should_from_fail_if_date_is_null() throws ParseException {
    TimeValue.from((Time) null);
  }

  /**
   * This method should throw a {@code NullPointerException} because passing a {@code null} parameter to {@code from}
   * method.
   */
  @Test(expected = NullPointerException.class)
  public void should_from_fail_if_calendar_is_null() throws ParseException {
    TimeValue.from((Calendar) null);
  }

  /**
   * This method tests the {@code of} static method with four values.
   */
  @Test
  public void test_of_with_four_values() throws ParseException {
    TimeValue timeValue = TimeValue.of(9, 1, 6, 3);
    assertThat(timeValue.getHours()).isEqualTo(9);
    assertThat(timeValue.getMinutes()).isEqualTo(1);
    assertThat(timeValue.getSeconds()).isEqualTo(6);
    assertThat(timeValue.getNanoSeconds()).isEqualTo(3);
  }

  /**
   * This method tests the {@code of} static method with three values.
   */
  @Test
  public void test_of_with_three_values() throws ParseException {
    TimeValue timeValue = TimeValue.of(9, 1, 6);
    assertThat(timeValue.getHours()).isEqualTo(9);
    assertThat(timeValue.getMinutes()).isEqualTo(1);
    assertThat(timeValue.getSeconds()).isEqualTo(6);
    assertThat(timeValue.getNanoSeconds()).isEqualTo(0);
  }

  /**
   * This method tests the {@code of} static method with two values.
   */
  @Test
  public void test_of_with_two_values() throws ParseException {
    TimeValue timeValue = TimeValue.of(9, 1);
    assertThat(timeValue.getHours()).isEqualTo(9);
    assertThat(timeValue.getMinutes()).isEqualTo(1);
    assertThat(timeValue.getSeconds()).isEqualTo(0);
    assertThat(timeValue.getNanoSeconds()).isEqualTo(0);
  }

  /**
   * This method tests the {@code toString} method.
   */
  @Test
  public void test_toString() {
    assertThat(TimeValue.of(9, 1, 6, 3).toString()).isEqualTo("09:01:06.000000003");
  }

  /**
   * This method tests the {@code equals} method.
   */
  @Test
  public void test_equals() {
    assertThat(TimeValue.of(9, 1).equals(TimeValue.of(9, 1))).isTrue();
    assertThat(TimeValue.of(9, 1, 6).equals(TimeValue.of(9, 1, 6))).isTrue();
    assertThat(TimeValue.of(9, 1, 6, 3).equals(TimeValue.of(9, 1, 6, 3))).isTrue();

    assertThat(TimeValue.of(9, 1).equals(TimeValue.of(10, 1))).isFalse();
    assertThat(TimeValue.of(9, 1).equals(TimeValue.of(9, 2))).isFalse();
    assertThat(TimeValue.of(9, 1, 6).equals(TimeValue.of(9, 1, 7))).isFalse();
    assertThat(TimeValue.of(9, 1, 6, 3).equals(TimeValue.of(9, 1, 6, 4))).isFalse();
    assertThat(TimeValue.of(9, 1, 6, 3).equals("")).isFalse();
  }

  /**
   * This method tests the {@code hashCode} method.
   */
  @Test
  public void test_hashCode() {
    assertThat(TimeValue.of(9, 1).hashCode()).isEqualTo(1192601);
    assertThat(TimeValue.of(9, 1, 6).hashCode()).isEqualTo(1192607);
    assertThat(TimeValue.of(9, 1, 6, 3).hashCode()).isEqualTo(1192700);
  }

  /**
   * This method tests the {@code compareTo} method.
   */
  @Test
  public void test_compareTo() {
    assertThat(TimeValue.of(9, 1).compareTo(TimeValue.of(9, 0))).isEqualTo(1);
    assertThat(TimeValue.of(9, 1).compareTo(TimeValue.of(8, 1))).isEqualTo(1);
    assertThat(TimeValue.of(9, 1, 6).compareTo(TimeValue.of(9, 1, 5))).isEqualTo(1);
    assertThat(TimeValue.of(9, 1, 6).compareTo(TimeValue.of(9, 0, 6))).isEqualTo(1);
    assertThat(TimeValue.of(9, 1, 6).compareTo(TimeValue.of(8, 1, 6))).isEqualTo(1);
    assertThat(TimeValue.of(9, 1, 6, 3).compareTo(TimeValue.of(9, 1, 6, 2))).isEqualTo(1);
    assertThat(TimeValue.of(9, 1, 6, 3).compareTo(TimeValue.of(9, 1, 5, 3))).isEqualTo(1);
    assertThat(TimeValue.of(9, 1, 6, 3).compareTo(TimeValue.of(9, 0, 6, 3))).isEqualTo(1);
    assertThat(TimeValue.of(9, 1, 6, 3).compareTo(TimeValue.of(8, 1, 6, 3))).isEqualTo(1);

    assertThat(TimeValue.of(9, 1).compareTo(TimeValue.of(9, 1))).isEqualTo(0);
    assertThat(TimeValue.of(9, 1, 6).compareTo(TimeValue.of(9, 1, 6))).isEqualTo(0);
    assertThat(TimeValue.of(9, 1, 6, 3).compareTo(TimeValue.of(9, 1, 6, 3))).isEqualTo(0);

    assertThat(TimeValue.of(9, 1).compareTo(TimeValue.of(9, 2))).isEqualTo(-1);
    assertThat(TimeValue.of(9, 1).compareTo(TimeValue.of(10, 1))).isEqualTo(-1);
    assertThat(TimeValue.of(9, 1, 6).compareTo(TimeValue.of(9, 1, 7))).isEqualTo(-1);
    assertThat(TimeValue.of(9, 1, 6).compareTo(TimeValue.of(9, 2, 6))).isEqualTo(-1);
    assertThat(TimeValue.of(9, 1, 6).compareTo(TimeValue.of(10, 1, 6))).isEqualTo(-1);
    assertThat(TimeValue.of(9, 1, 6, 3).compareTo(TimeValue.of(9, 1, 6, 4))).isEqualTo(-1);
    assertThat(TimeValue.of(9, 1, 6, 3).compareTo(TimeValue.of(9, 1, 7, 3))).isEqualTo(-1);
    assertThat(TimeValue.of(9, 1, 6, 3).compareTo(TimeValue.of(9, 2, 6, 3))).isEqualTo(-1);
    assertThat(TimeValue.of(9, 1, 6, 3).compareTo(TimeValue.of(10, 1, 6, 3))).isEqualTo(-1);
  }

  /**
   * This method tests the {@code isBefore} method.
   */
  @Test
  public void test_isBefore() {
    assertThat(TimeValue.of(9, 1).isBefore(TimeValue.of(9, 0))).isFalse();
    assertThat(TimeValue.of(9, 1).isBefore(TimeValue.of(8, 1))).isFalse();
    assertThat(TimeValue.of(9, 1, 6).isBefore(TimeValue.of(9, 1, 5))).isFalse();
    assertThat(TimeValue.of(9, 1, 6).isBefore(TimeValue.of(9, 0, 6))).isFalse();
    assertThat(TimeValue.of(9, 1, 6).isBefore(TimeValue.of(8, 1, 6))).isFalse();
    assertThat(TimeValue.of(9, 1, 6, 3).isBefore(TimeValue.of(9, 1, 6, 2))).isFalse();
    assertThat(TimeValue.of(9, 1, 6, 3).isBefore(TimeValue.of(9, 1, 5, 3))).isFalse();
    assertThat(TimeValue.of(9, 1, 6, 3).isBefore(TimeValue.of(9, 0, 6, 3))).isFalse();
    assertThat(TimeValue.of(9, 1, 6, 3).isBefore(TimeValue.of(8, 1, 6, 3))).isFalse();

    assertThat(TimeValue.of(9, 1).isBefore(TimeValue.of(9, 1))).isFalse();
    assertThat(TimeValue.of(9, 1, 6).isBefore(TimeValue.of(9, 1, 6))).isFalse();
    assertThat(TimeValue.of(9, 1, 6, 3).isBefore(TimeValue.of(9, 1, 6, 3))).isFalse();

    assertThat(TimeValue.of(9, 1).isBefore(TimeValue.of(9, 2))).isTrue();
    assertThat(TimeValue.of(9, 1).isBefore(TimeValue.of(10, 1))).isTrue();
    assertThat(TimeValue.of(9, 1, 6).isBefore(TimeValue.of(9, 1, 7))).isTrue();
    assertThat(TimeValue.of(9, 1, 6).isBefore(TimeValue.of(9, 2, 6))).isTrue();
    assertThat(TimeValue.of(9, 1, 6).isBefore(TimeValue.of(10, 1, 6))).isTrue();
    assertThat(TimeValue.of(9, 1, 6, 3).isBefore(TimeValue.of(9, 1, 6, 4))).isTrue();
    assertThat(TimeValue.of(9, 1, 6, 3).isBefore(TimeValue.of(9, 1, 7, 3))).isTrue();
    assertThat(TimeValue.of(9, 1, 6, 3).isBefore(TimeValue.of(9, 2, 6, 3))).isTrue();
    assertThat(TimeValue.of(9, 1, 6, 3).isBefore(TimeValue.of(10, 1, 6, 3))).isTrue();
  }

  /**
   * This method tests the {@code isAfter} method.
   */
  @Test
  public void test_isAfter() {
    assertThat(TimeValue.of(9, 1).isAfter(TimeValue.of(9, 0))).isTrue();
    assertThat(TimeValue.of(9, 1).isAfter(TimeValue.of(8, 1))).isTrue();
    assertThat(TimeValue.of(9, 1, 6).isAfter(TimeValue.of(9, 1, 5))).isTrue();
    assertThat(TimeValue.of(9, 1, 6).isAfter(TimeValue.of(9, 0, 6))).isTrue();
    assertThat(TimeValue.of(9, 1, 6).isAfter(TimeValue.of(8, 1, 6))).isTrue();
    assertThat(TimeValue.of(9, 1, 6, 3).isAfter(TimeValue.of(9, 1, 6, 2))).isTrue();
    assertThat(TimeValue.of(9, 1, 6, 3).isAfter(TimeValue.of(9, 1, 5, 3))).isTrue();
    assertThat(TimeValue.of(9, 1, 6, 3).isAfter(TimeValue.of(9, 0, 6, 3))).isTrue();
    assertThat(TimeValue.of(9, 1, 6, 3).isAfter(TimeValue.of(8, 1, 6, 3))).isTrue();

    assertThat(TimeValue.of(9, 1).isAfter(TimeValue.of(9, 1))).isFalse();
    assertThat(TimeValue.of(9, 1, 6).isAfter(TimeValue.of(9, 1, 6))).isFalse();
    assertThat(TimeValue.of(9, 1, 6, 3).isAfter(TimeValue.of(9, 1, 6, 3))).isFalse();

    assertThat(TimeValue.of(9, 1).isAfter(TimeValue.of(9, 2))).isFalse();
    assertThat(TimeValue.of(9, 1).isAfter(TimeValue.of(10, 1))).isFalse();
    assertThat(TimeValue.of(9, 1, 6).isAfter(TimeValue.of(9, 1, 7))).isFalse();
    assertThat(TimeValue.of(9, 1, 6).isAfter(TimeValue.of(9, 2, 6))).isFalse();
    assertThat(TimeValue.of(9, 1, 6).isAfter(TimeValue.of(10, 1, 6))).isFalse();
    assertThat(TimeValue.of(9, 1, 6, 3).isAfter(TimeValue.of(9, 1, 6, 4))).isFalse();
    assertThat(TimeValue.of(9, 1, 6, 3).isAfter(TimeValue.of(9, 1, 7, 3))).isFalse();
    assertThat(TimeValue.of(9, 1, 6, 3).isAfter(TimeValue.of(9, 2, 6, 3))).isFalse();
    assertThat(TimeValue.of(9, 1, 6, 3).isAfter(TimeValue.of(10, 1, 6, 3))).isFalse();
  }

  /**
   * This method tests the {@code move} method with a {@code TimeValue}.
   */
  @Test
  public void test_move_time() {
    assertThat(TimeValue.of(9, 1).move(TimeValue.of(0, 0, 0, 1))).as("add 1 nano").isEqualTo(
            TimeValue.of(9, 1, 0, 1));
    assertThat(TimeValue.of(9, 1).move(TimeValue.of(0, 0, 0, 2))).as("add 2 nanos").isEqualTo(
            TimeValue.of(9, 1, 0, 2));

    assertThat(TimeValue.of(9, 1).move(TimeValue.of(0, 0, 0, -1))).as("substract 1 nano").isEqualTo(
            TimeValue.of(9, 0, 59, 999999999));
    assertThat(TimeValue.of(9, 1).move(TimeValue.of(0, 0, 0, -2))).as("substract 2 nanos").isEqualTo(
            TimeValue.of(9, 0, 59, 999999998));

    assertThat(TimeValue.of(9, 1).move(TimeValue.of(0, 0, 0, 1000000))).as("add 1 milli").isEqualTo(
            TimeValue.of(9, 1, 0, 1000000));
    assertThat(TimeValue.of(9, 1).move(TimeValue.of(0, 0, 0, 2000000))).as("add 2 millis").isEqualTo(
            TimeValue.of(9, 1, 0, 2000000));

    assertThat(TimeValue.of(9, 1).move(TimeValue.of(0, 0, 0, -1000000))).as("substract 1 milli").isEqualTo(
            TimeValue.of(9, 0, 59, 999000000));
    assertThat(TimeValue.of(9, 1).move(TimeValue.of(0, 0, 0, -2000000))).as("substract 2 millis").isEqualTo(
            TimeValue.of(9, 0, 59, 998000000));

    assertThat(TimeValue.of(9, 1).move(TimeValue.of(0, 0, 1))).as("add 1 second").isEqualTo(
            TimeValue.of(9, 1, 1));
    assertThat(TimeValue.of(9, 1).move(TimeValue.of(0, 0, 2))).as("add 2 seconds").isEqualTo(
            TimeValue.of(9, 1, 2));

    assertThat(TimeValue.of(9, 1, 58).move(TimeValue.of(0, 0,  10))).as("add 10 seconds to 58").isEqualTo(
        TimeValue.of(9, 2, 8));

    assertThat(TimeValue.of(9, 59, 58).move(TimeValue.of(0, 0,  50))).as("add 50 seconds to 58").isEqualTo(
        TimeValue.of(10, 00, 48));

    assertThat(TimeValue.of(9, 0, 0).move(TimeValue.of(0, 0,  -10))).as("substract 10 seconds to 0").isEqualTo(
        TimeValue.of(8, 59, 50));

    assertThat(TimeValue.of(9, 1).move(TimeValue.of(0, 0, -1))).as("substract 1 second").isEqualTo(
            TimeValue.of(9, 0, 59, 0));
    assertThat(TimeValue.of(9, 1).move(TimeValue.of(0, 0, -2))).as("substract 2 seconds").isEqualTo(
            TimeValue.of(9, 0, 58, 0));

    assertThat(TimeValue.of(9, 1).move(TimeValue.of(0, 1, 0))).as("add 1 minute").isEqualTo(
            TimeValue.of(9, 2));
    assertThat(TimeValue.of(9, 1).move(TimeValue.of(0, 2, 0))).as("add 2 minutes").isEqualTo(
            TimeValue.of(9, 3));

    assertThat(TimeValue.of(9, 1).move(TimeValue.of(0, -1, 0))).as("substract 1 minute").isEqualTo(
            TimeValue.of(9, 0, 0, 0));
    assertThat(TimeValue.of(9, 1).move(TimeValue.of(0, -2, 0))).as("substract 2 minutes").isEqualTo(
            TimeValue.of(8, 59, 0, 0));

    assertThat(TimeValue.of(9, 1).move(TimeValue.of(1, 0, 0))).as("add 1 hour").isEqualTo(
            TimeValue.of(10, 1));
    assertThat(TimeValue.of(9, 1).move(TimeValue.of(2, 0, 0))).as("add 2 hours").isEqualTo(
            TimeValue.of(11, 1));

    assertThat(TimeValue.of(9, 1).move(TimeValue.of(-1, 0, 0))).as("substract 1 hour").isEqualTo(
            TimeValue.of(8, 1, 0, 0));
    assertThat(TimeValue.of(9, 1).move(TimeValue.of(-2, 0, 0))).as("substract 2 hours").isEqualTo(
            TimeValue.of(7, 1, 0, 0));

    assertThat(TimeValue.of(9, 1).move(TimeValue.of(1, 1, 1, 1000000))).as("add 1 all").isEqualTo(
            TimeValue.of(10, 2, 1, 1000000));
    assertThat(TimeValue.of(9, 1).move(TimeValue.of(2, 2, 2, 2000000))).as("add 2 all").isEqualTo(
            TimeValue.of(11, 3, 2, 2000000));

    assertThat(TimeValue.of(9, 1).move(TimeValue.of(-1, -1, -1, -1000000))).as("substract 1 all").isEqualTo(
            TimeValue.of(7, 59, 58, 999000000));
    assertThat(TimeValue.of(9, 1).move(TimeValue.of(-2, -2, -2, -2000000))).as("substract 2 all").isEqualTo(
            TimeValue.of(6, 58, 57, 998000000));
  }

  /**
   * This method tests the {@code reverse} method with a {@code TimeValue}.
   */
  @Test
  public void test_reverse() {
    assertThat(TimeValue.of(9, 1).reverse()).isEqualTo(TimeValue.of(-9, -1));
    assertThat(TimeValue.of(1, 1, 1, 1).reverse()).isEqualTo(TimeValue.of(-1, -1, -1, -1));
  }
}
