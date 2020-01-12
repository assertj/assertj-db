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
 * Copyright 2015-2020 the original author or authors.
 */
package org.assertj.db.type;

import org.assertj.db.common.AbstractTest;
import org.junit.Test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests on the date/time value.
 * 
 * @author Régis Pouiller
 * 
 */
public class DateTimeValue_Test extends AbstractTest {

  /**
   * This method tests the constructor with a {@code String} with only the day.
   */
  @Test
  public void test_contructor_with_string_only_day() throws ParseException {
    DateTimeValue dateTimeValue = new DateTimeValue("2007-12-23");
    assertThat(dateTimeValue.getDate().getDayOfTheMonth()).isEqualTo(23);
    assertThat(dateTimeValue.getDate().getMonth()).isEqualTo(12);
    assertThat(dateTimeValue.getDate().getYear()).isEqualTo(2007);
    assertThat(dateTimeValue.getTime().getHours()).isEqualTo(0);
    assertThat(dateTimeValue.getTime().getMinutes()).isEqualTo(0);
    assertThat(dateTimeValue.getTime().getSeconds()).isEqualTo(0);
    assertThat(dateTimeValue.getTime().getNanoSeconds()).isEqualTo(0);
  }

  /**
   * This method tests the constructor with a {@code String} with minutes.
   */
  @Test
  public void test_contructor_with_string_with_minutes() throws ParseException {
    DateTimeValue dateTimeValue = new DateTimeValue("2007-12-23T09:01");
    assertThat(dateTimeValue.getDate().getDayOfTheMonth()).isEqualTo(23);
    assertThat(dateTimeValue.getDate().getMonth()).isEqualTo(12);
    assertThat(dateTimeValue.getDate().getYear()).isEqualTo(2007);
    assertThat(dateTimeValue.getTime().getHours()).isEqualTo(9);
    assertThat(dateTimeValue.getTime().getMinutes()).isEqualTo(1);
    assertThat(dateTimeValue.getTime().getSeconds()).isEqualTo(0);
    assertThat(dateTimeValue.getTime().getNanoSeconds()).isEqualTo(0);
  }

  /**
   * This method tests the constructor with a {@code String} with seconds.
   */
  @Test
  public void test_contructor_with_string_with_seconds() throws ParseException {
    DateTimeValue dateTimeValue = new DateTimeValue("2007-12-23T09:01:06");
    assertThat(dateTimeValue.getDate().getDayOfTheMonth()).isEqualTo(23);
    assertThat(dateTimeValue.getDate().getMonth()).isEqualTo(12);
    assertThat(dateTimeValue.getDate().getYear()).isEqualTo(2007);
    assertThat(dateTimeValue.getTime().getHours()).isEqualTo(9);
    assertThat(dateTimeValue.getTime().getMinutes()).isEqualTo(1);
    assertThat(dateTimeValue.getTime().getSeconds()).isEqualTo(6);
    assertThat(dateTimeValue.getTime().getNanoSeconds()).isEqualTo(0);
  }

  /**
   * This method tests the constructor with a {@code String} with nanoseconds.
   */
  @Test
  public void test_contructor_with_string_with_nano() throws ParseException {
    DateTimeValue dateTimeValue = new DateTimeValue("2007-12-23T09:01:06.000000003");
    assertThat(dateTimeValue.getDate().getDayOfTheMonth()).isEqualTo(23);
    assertThat(dateTimeValue.getDate().getMonth()).isEqualTo(12);
    assertThat(dateTimeValue.getDate().getYear()).isEqualTo(2007);
    assertThat(dateTimeValue.getTime().getHours()).isEqualTo(9);
    assertThat(dateTimeValue.getTime().getMinutes()).isEqualTo(1);
    assertThat(dateTimeValue.getTime().getSeconds()).isEqualTo(6);
    assertThat(dateTimeValue.getTime().getNanoSeconds()).isEqualTo(3);
  }

  /**
   * This method should throw a {@code NullPointerException} because passing a {@code null} parameter to constructor
   * with a {@code String}.
   */
  @Test(expected = NullPointerException.class)
  public void should_constructor_with_string_fail_if_date_is_null() throws ParseException {
    new DateTimeValue((String) null);
  }

  /**
   * This method should throw a {@code ParseException} because passing a parameter to constructor with a bad length.
   */
  @Test(expected = ParseException.class)
  public void should_constructor_with_string_fail_if_date_has_bad_length() throws ParseException {
    new DateTimeValue("12345678901");
  }

  /**
   * This method should throw a {@code ParseException} because passing a parameter to constructor with a bad character
   * on year.
   */
  @Test(expected = ParseException.class)
  public void should_constructor_with_string_fail_if_date_has_bad_character_on_year() throws ParseException {
    new DateTimeValue("a007-12-23");
  }

  /**
   * This method should throw a {@code ParseException} because passing a parameter to constructor with a bad character
   * on separator.
   */
  @Test(expected = ParseException.class)
  public void should_constructor_with_string_fail_if_date_has_bad_character_on_separator() throws ParseException {
    new DateTimeValue("2007a12-23");
  }

  /**
   * This method tests the constructor with a {@code Calendar}.
   */
  @Test
  public void test_contructor_with_calendar() throws ParseException {
    Calendar calendar = Calendar.getInstance();
    calendar.set(2007, Calendar.DECEMBER, 23, 9, 1, 6);
    calendar.set(Calendar.MILLISECOND, 50);
    DateTimeValue dateTimeValue = new DateTimeValue(calendar);
    assertThat(dateTimeValue.getDate().getDayOfTheMonth()).isEqualTo(23);
    assertThat(dateTimeValue.getDate().getMonth()).isEqualTo(12);
    assertThat(dateTimeValue.getDate().getYear()).isEqualTo(2007);
    assertThat(dateTimeValue.getTime().getHours()).isEqualTo(9);
    assertThat(dateTimeValue.getTime().getMinutes()).isEqualTo(1);
    assertThat(dateTimeValue.getTime().getSeconds()).isEqualTo(6);
    assertThat(dateTimeValue.getTime().getNanoSeconds()).isEqualTo(50000000);
  }

  /**
   * This method tests the {@code from} method containing seconds.
   */
  @Test
  public void test_constructor_with_timestamp() throws ParseException {
    DateTimeValue dateTimeValue = new DateTimeValue(Timestamp.valueOf("2007-12-23 09:01:06.000000003"));
    assertThat(dateTimeValue.getDate().getDayOfTheMonth()).isEqualTo(23);
    assertThat(dateTimeValue.getDate().getMonth()).isEqualTo(12);
    assertThat(dateTimeValue.getDate().getYear()).isEqualTo(2007);
    assertThat(dateTimeValue.getTime().getHours()).isEqualTo(9);
    assertThat(dateTimeValue.getTime().getMinutes()).isEqualTo(1);
    assertThat(dateTimeValue.getTime().getSeconds()).isEqualTo(6);
    assertThat(dateTimeValue.getTime().getNanoSeconds()).isEqualTo(3);
  }

  /**
   * This method should throw a {@code NullPointerException} because passing a {@code null} parameter to constructor
   * with a {@code TimeStamp}.
   */
  @Test(expected = NullPointerException.class)
  public void should_constructor_with_timestamp_fail_if_date_is_null() throws ParseException {
    new DateTimeValue((Timestamp) null);
  }

  /**
   * This method should throw a {@code NullPointerException} because passing a {@code null} parameter to constructor
   * with a {@code DateValue}.
   */
  @Test(expected = NullPointerException.class)
  public void should_constructor_with_datevalue_fail_if_date_is_null() throws ParseException {
    new DateTimeValue(null, TimeValue.of(9, 1));
  }

  /**
   * This method should throw a {@code NullPointerException} because passing a {@code null} parameter to constructor
   * with a {@code TimeValue}.
   */
  @Test(expected = NullPointerException.class)
  public void should_constructor_with_timevalue_fail_if_time_is_null() throws ParseException {
    new DateTimeValue(DateValue.of(2007, 12, 23), null);
  }

  /**
   * This method tests the constructor with values.
   */
  @Test
  public void test_contructor_with_values() throws ParseException {
    DateValue dateValue = new DateValue(2007, 12, 23);
    TimeValue timeValue = new TimeValue(9, 1, 6, 3);
    DateTimeValue dateTimeValue = new DateTimeValue(dateValue, timeValue);
    assertThat(dateTimeValue.getDate().getDayOfTheMonth()).isEqualTo(23);
    assertThat(dateTimeValue.getDate().getMonth()).isEqualTo(12);
    assertThat(dateTimeValue.getDate().getYear()).isEqualTo(2007);
    assertThat(dateTimeValue.getTime().getHours()).isEqualTo(9);
    assertThat(dateTimeValue.getTime().getMinutes()).isEqualTo(1);
    assertThat(dateTimeValue.getTime().getSeconds()).isEqualTo(6);
    assertThat(dateTimeValue.getTime().getNanoSeconds()).isEqualTo(3);
  }

  /**
   * This method tests the {@code parse} static method with a {@code String} with only the day.
   */
  @Test
  public void test_parse_only_day() throws ParseException {
    DateTimeValue dateTimeValue = DateTimeValue.parse("2007-12-23");
    assertThat(dateTimeValue.getDate().getDayOfTheMonth()).isEqualTo(23);
    assertThat(dateTimeValue.getDate().getMonth()).isEqualTo(12);
    assertThat(dateTimeValue.getDate().getYear()).isEqualTo(2007);
    assertThat(dateTimeValue.getTime().getHours()).isEqualTo(0);
    assertThat(dateTimeValue.getTime().getMinutes()).isEqualTo(0);
    assertThat(dateTimeValue.getTime().getSeconds()).isEqualTo(0);
    assertThat(dateTimeValue.getTime().getNanoSeconds()).isEqualTo(0);
  }

  /**
   * This method tests the {@code parse} static method with a {@code String} with minutes.
   */
  @Test
  public void test_parse_with_minutes() throws ParseException {
    DateTimeValue dateTimeValue = DateTimeValue.parse("2007-12-23T09:01");
    assertThat(dateTimeValue.getDate().getDayOfTheMonth()).isEqualTo(23);
    assertThat(dateTimeValue.getDate().getMonth()).isEqualTo(12);
    assertThat(dateTimeValue.getDate().getYear()).isEqualTo(2007);
    assertThat(dateTimeValue.getTime().getHours()).isEqualTo(9);
    assertThat(dateTimeValue.getTime().getMinutes()).isEqualTo(1);
    assertThat(dateTimeValue.getTime().getSeconds()).isEqualTo(0);
    assertThat(dateTimeValue.getTime().getNanoSeconds()).isEqualTo(0);
  }

  /**
   * This method tests the {@code parse} static method with a {@code String} with seconds.
   */
  @Test
  public void test_parse_with_seconds() throws ParseException {
    DateTimeValue dateTimeValue = DateTimeValue.parse("2007-12-23T09:01:06");
    assertThat(dateTimeValue.getDate().getDayOfTheMonth()).isEqualTo(23);
    assertThat(dateTimeValue.getDate().getMonth()).isEqualTo(12);
    assertThat(dateTimeValue.getDate().getYear()).isEqualTo(2007);
    assertThat(dateTimeValue.getTime().getHours()).isEqualTo(9);
    assertThat(dateTimeValue.getTime().getMinutes()).isEqualTo(1);
    assertThat(dateTimeValue.getTime().getSeconds()).isEqualTo(6);
    assertThat(dateTimeValue.getTime().getNanoSeconds()).isEqualTo(0);
  }

  /**
   * This method tests the {@code parse} static method with a {@code String} with nanoseconds.
   */
  @Test
  public void test_parse_with_nano() throws ParseException {
    DateTimeValue dateTimeValue = DateTimeValue.parse("2007-12-23T09:01:06.000000003");
    assertThat(dateTimeValue.getDate().getDayOfTheMonth()).isEqualTo(23);
    assertThat(dateTimeValue.getDate().getMonth()).isEqualTo(12);
    assertThat(dateTimeValue.getDate().getYear()).isEqualTo(2007);
    assertThat(dateTimeValue.getTime().getHours()).isEqualTo(9);
    assertThat(dateTimeValue.getTime().getMinutes()).isEqualTo(1);
    assertThat(dateTimeValue.getTime().getSeconds()).isEqualTo(6);
    assertThat(dateTimeValue.getTime().getNanoSeconds()).isEqualTo(3);
  }

  /**
   * This method should throw a {@code NullPointerException} because passing a {@code null} parameter to the
   * {@code parse} static method.
   */
  @Test(expected = NullPointerException.class)
  public void should_parse_fail_if_date_is_null() throws ParseException {
    DateTimeValue.parse(null);
  }

  /**
   * This method should throw a {@code ParseException} because passing a parameter to {@code parse} static method with a
   * bad length.
   */
  @Test(expected = ParseException.class)
  public void should_parse_fail_if_date_has_bad_length() throws ParseException {
    DateTimeValue.parse("12345678901");
  }

  /**
   * This method should throw a {@code ParseException} because passing a parameter to {@code parse} static method with a
   * bad character on year.
   */
  @Test(expected = ParseException.class)
  public void should_parse_fail_if_date_has_bad_character_on_year() throws ParseException {
    DateTimeValue.parse("a007-12-23");
  }

  /**
   * This method should throw a {@code ParseException} because passing a parameter to {@code parse} static method with a
   * bad character on separator.
   */
  @Test(expected = ParseException.class)
  public void should_parse_fail_if_date_has_bad_character_on_separator() throws ParseException {
    DateTimeValue.parse("2007a12-23");
  }

  /**
   * This method tests the {@code now} static method.
   */
  @Test
  public void test_now() throws ParseException {
    Calendar calendarFirst = Calendar.getInstance();
    DateTimeValue dateTimeValue = DateTimeValue.now();
    Calendar calendarSecond = Calendar.getInstance();
    assertThat(dateTimeValue.getDate().getYear()).isBetween(calendarFirst.get(Calendar.YEAR),
                                                            calendarSecond.get(Calendar.YEAR));
    if (calendarFirst.get(Calendar.YEAR) == calendarSecond.get(Calendar.YEAR)) {
      assertThat(dateTimeValue.getDate().getMonth()).isBetween(calendarFirst.get(Calendar.MONTH) + 1,
                                                               calendarSecond.get(Calendar.MONTH) + 1);
    }
    else {
      assertThat(dateTimeValue.getDate().getMonth()).isBetween(calendarFirst.get(Calendar.MONTH) + 1 - 12,
                                                               calendarSecond.get(Calendar.MONTH) + 1 + 12);
    }
    if (calendarFirst.get(Calendar.MONTH) == calendarSecond.get(Calendar.MONTH)) {
      assertThat(dateTimeValue.getDate().getDayOfTheMonth()).isBetween(calendarFirst.get(Calendar.DAY_OF_MONTH),
                                                                       calendarSecond.get(Calendar.DAY_OF_MONTH));
    }
    else {
      assertThat(dateTimeValue.getDate().getDayOfTheMonth()).isBetween(calendarFirst.get(Calendar.DAY_OF_MONTH) - 31,
                                                                       calendarSecond.get(Calendar.DAY_OF_MONTH) + 31);
    }
    if (calendarFirst.get(Calendar.DAY_OF_MONTH) == calendarSecond.get(Calendar.DAY_OF_MONTH)) {
      assertThat(dateTimeValue.getTime().getHours()).isBetween(calendarFirst.get(Calendar.HOUR_OF_DAY),
                                                              calendarSecond.get(Calendar.HOUR_OF_DAY));
    }
    else {
      assertThat(dateTimeValue.getTime().getHours()).isBetween(calendarFirst.get(Calendar.HOUR_OF_DAY) - 24,
                                                              calendarSecond.get(Calendar.HOUR_OF_DAY) + 24);
    }
    if (calendarFirst.get(Calendar.HOUR_OF_DAY) == calendarSecond.get(Calendar.HOUR_OF_DAY)) {
      assertThat(dateTimeValue.getTime().getMinutes()).isBetween(calendarFirst.get(Calendar.MINUTE),
                                                                 calendarSecond.get(Calendar.MINUTE));
    }
    else {
      assertThat(dateTimeValue.getTime().getMinutes()).isBetween(calendarFirst.get(Calendar.MINUTE) - 60,
                                                                 calendarSecond.get(Calendar.MINUTE) + 60);
    }
    if (calendarFirst.get(Calendar.MINUTE) == calendarSecond.get(Calendar.MINUTE)) {
      assertThat(dateTimeValue.getTime().getSeconds()).isBetween(calendarFirst.get(Calendar.SECOND),
                                                                 calendarSecond.get(Calendar.SECOND));
    }
    else {
      assertThat(dateTimeValue.getTime().getSeconds()).isBetween(calendarFirst.get(Calendar.SECOND) - 60,
                                                                 calendarSecond.get(Calendar.SECOND) + 60);
    }
    if (calendarFirst.get(Calendar.SECOND) == calendarSecond.get(Calendar.SECOND)) {
      assertThat(dateTimeValue.getTime().getNanoSeconds()).isBetween(calendarFirst.get(Calendar.MILLISECOND) * 1000000,
                                                       calendarSecond.get(Calendar.MILLISECOND) * 1000000);
    }
    else {
      assertThat(dateTimeValue.getTime().getNanoSeconds()).isBetween(calendarFirst.get(Calendar.MILLISECOND) * 1000000 - 1000000000,
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
    DateTimeValue dateTimeValue = DateTimeValue.from(calendar);
    assertThat(dateTimeValue.getDate().getDayOfTheMonth()).isEqualTo(23);
    assertThat(dateTimeValue.getDate().getMonth()).isEqualTo(12);
    assertThat(dateTimeValue.getDate().getYear()).isEqualTo(2007);
    assertThat(dateTimeValue.getTime().getHours()).isEqualTo(9);
    assertThat(dateTimeValue.getTime().getMinutes()).isEqualTo(1);
    assertThat(dateTimeValue.getTime().getSeconds()).isEqualTo(6);
    assertThat(dateTimeValue.getTime().getNanoSeconds()).isEqualTo(50000000);
  }

  /**
   * This method tests the {@code from} method containing seconds.
   */
  @Test
  public void test_from() throws ParseException {
    DateTimeValue dateTimeValue = DateTimeValue.from(Timestamp.valueOf("2007-12-23 09:01:06.000000003"));
    assertThat(dateTimeValue.getDate().getDayOfTheMonth()).isEqualTo(23);
    assertThat(dateTimeValue.getDate().getMonth()).isEqualTo(12);
    assertThat(dateTimeValue.getDate().getYear()).isEqualTo(2007);
    assertThat(dateTimeValue.getTime().getHours()).isEqualTo(9);
    assertThat(dateTimeValue.getTime().getMinutes()).isEqualTo(1);
    assertThat(dateTimeValue.getTime().getSeconds()).isEqualTo(6);
    assertThat(dateTimeValue.getTime().getNanoSeconds()).isEqualTo(3);
  }

  /**
   * This method should throw a {@code NullPointerException} because passing a {@code null} parameter to {@code from}
   * method.
   */
  @Test(expected = NullPointerException.class)
  public void should_from_fail_if_date_is_null() throws ParseException {
    DateTimeValue.from((Timestamp) null);
  }

  /**
   * This method should throw a {@code NullPointerException} because passing a {@code null} parameter to {@code from}
   * method.
   */
  @Test(expected = NullPointerException.class)
  public void should_from_fail_if_calendar_is_null() throws ParseException {
    DateTimeValue.from((Calendar) null);
  }

  /**
   * This method tests the {@code of} static method.
   */
  @Test
  public void test_of() throws ParseException {
    DateValue dateValue = DateValue.of(2007, 12, 23);
    TimeValue timeValue = TimeValue.of(9, 1, 6, 3);
    DateTimeValue dateTimeValue = DateTimeValue.of(dateValue, timeValue);
    assertThat(dateTimeValue.getDate().getDayOfTheMonth()).isEqualTo(23);
    assertThat(dateTimeValue.getDate().getMonth()).isEqualTo(12);
    assertThat(dateTimeValue.getDate().getYear()).isEqualTo(2007);
    assertThat(dateTimeValue.getTime().getHours()).isEqualTo(9);
    assertThat(dateTimeValue.getTime().getMinutes()).isEqualTo(1);
    assertThat(dateTimeValue.getTime().getSeconds()).isEqualTo(6);
    assertThat(dateTimeValue.getTime().getNanoSeconds()).isEqualTo(3);
  }

  /**
   * This method tests the {@code toString} method.
   */
  @Test
  public void test_toString() {
    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 3)).toString()).isEqualTo(
        "2007-12-23T09:01:06.000000003");
    assertThat(DateTimeValue.of(DateValue.of(2007, 2, 3), TimeValue.of(9, 1, 6, 3)).toString()).isEqualTo(
        "2007-02-03T09:01:06.000000003");
  }

  /**
   * This method tests the {@code equals} method.
   */
  @Test
  public void test_equals() {
    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(0, 0)).equals(DateValue.of(2007, 12, 23)))
        .isTrue();
    assertThat(
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).equals(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)))).isTrue();
    assertThat(
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6)).equals(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6)))).isTrue();
    assertThat(
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 3)).equals(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 3)))).isTrue();

    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23)).equals(DateValue.of(2007, 12, 24))).isFalse();
    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23)).equals(DateValue.of(2007, 12, 23))).isTrue();
    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 0)).equals(DateValue.of(2007, 12, 23)))
        .isFalse();
    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(0, 1)).equals(DateValue.of(2007, 12, 23)))
        .isFalse();
    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(0, 0, 6)).equals(DateValue.of(2007, 12, 23)))
        .isFalse();
    assertThat(
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(0, 0, 0, 3)).equals(DateValue.of(2007, 12, 23)))
        .isFalse();
    assertThat(
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).equals(
            DateTimeValue.of(DateValue.of(2008, 12, 23), TimeValue.of(9, 1)))).isFalse();
    assertThat(
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).equals(
            DateTimeValue.of(DateValue.of(2007, 11, 23), TimeValue.of(9, 1)))).isFalse();
    assertThat(
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).equals(
            DateTimeValue.of(DateValue.of(2007, 12, 24), TimeValue.of(9, 1)))).isFalse();
    assertThat(
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).equals(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(10, 1)))).isFalse();
    assertThat(
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).equals(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 2)))).isFalse();
    assertThat(
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6)).equals(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 7)))).isFalse();
    assertThat(
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 3)).equals(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 4)))).isFalse();
    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 3)).equals("")).isFalse();
  }

  /**
   * This method tests the {@code hashCode} method.
   */
  @Test
  public void test_hashCode() {
    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(0, 0)).hashCode()).isEqualTo(2606945);
    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).hashCode()).isEqualTo(2876025);
    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6)).hashCode()).isEqualTo(2876031);
    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 3)).hashCode()).isEqualTo(2876124);

    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 0)).hashCode()).isEqualTo(2875064);
    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(0, 1)).hashCode()).isEqualTo(2607906);
    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(0, 0, 6)).hashCode()).isEqualTo(2606951);
    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(0, 0, 0, 3)).hashCode()).isEqualTo(2607038);
  }

  /**
   * This method tests the {@code compareTo} method.
   */
  @Test
  public void test_compareTo() {
    assertThat(
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 3)).compareTo(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 2)))).isEqualTo(1);
    assertThat(
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 3)).compareTo(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 5, 3)))).isEqualTo(1);
    assertThat(
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 3)).compareTo(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 0, 6, 3)))).isEqualTo(1);
    assertThat(
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 3)).compareTo(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(8, 1, 6, 3)))).isEqualTo(1);
    assertThat(
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 3)).compareTo(
            DateTimeValue.of(DateValue.of(2007, 12, 22), TimeValue.of(9, 1, 6, 3)))).isEqualTo(1);
    assertThat(
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 3)).compareTo(
            DateTimeValue.of(DateValue.of(2007, 11, 23), TimeValue.of(9, 1, 6, 3)))).isEqualTo(1);
    assertThat(
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 3)).compareTo(
            DateTimeValue.of(DateValue.of(2006, 12, 23), TimeValue.of(9, 1, 6, 3)))).isEqualTo(1);

    assertThat(
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 3)).compareTo(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 3)))).isEqualTo(0);

    assertThat(
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 3)).compareTo(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 4)))).isEqualTo(-1);
    assertThat(
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 3)).compareTo(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 7, 3)))).isEqualTo(-1);
    assertThat(
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 3)).compareTo(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 2, 6, 3)))).isEqualTo(-1);
    assertThat(
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 3)).compareTo(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(10, 1, 6, 3)))).isEqualTo(-1);
    assertThat(
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 3)).compareTo(
            DateTimeValue.of(DateValue.of(2007, 12, 24), TimeValue.of(9, 1, 6, 3)))).isEqualTo(-1);
    assertThat(
        DateTimeValue.of(DateValue.of(2007, 11, 23), TimeValue.of(9, 1, 6, 3)).compareTo(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 3)))).isEqualTo(-1);
    assertThat(
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 3)).compareTo(
            DateTimeValue.of(DateValue.of(2008, 12, 23), TimeValue.of(9, 1, 6, 3)))).isEqualTo(-1);
  }

  /**
   * This method tests the {@code isBefore} method.
   */
  @Test
  public void test_isBefore() {
    assertThat(
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 3)).isBefore(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 2)))).isFalse();
    assertThat(
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 3)).isBefore(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 5, 3)))).isFalse();
    assertThat(
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 3)).isBefore(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 0, 6, 3)))).isFalse();
    assertThat(
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 3)).isBefore(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(8, 1, 6, 3)))).isFalse();
    assertThat(
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 3)).isBefore(
            DateTimeValue.of(DateValue.of(2007, 12, 22), TimeValue.of(9, 1, 6, 3)))).isFalse();
    assertThat(
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 3)).isBefore(
            DateTimeValue.of(DateValue.of(2007, 11, 23), TimeValue.of(9, 1, 6, 3)))).isFalse();
    assertThat(
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 3)).isBefore(
            DateTimeValue.of(DateValue.of(2006, 12, 23), TimeValue.of(9, 1, 6, 3)))).isFalse();

    assertThat(
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 3)).isBefore(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 3)))).isFalse();

    assertThat(
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 3)).isBefore(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 4)))).isTrue();
    assertThat(
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 3)).isBefore(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 7, 3)))).isTrue();
    assertThat(
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 3)).isBefore(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 2, 6, 3)))).isTrue();
    assertThat(
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 3)).isBefore(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(10, 1, 6, 3)))).isTrue();
    assertThat(
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 3)).isBefore(
            DateTimeValue.of(DateValue.of(2007, 12, 24), TimeValue.of(9, 1, 6, 3)))).isTrue();
    assertThat(
        DateTimeValue.of(DateValue.of(2007, 11, 23), TimeValue.of(9, 1, 6, 3)).isBefore(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 3)))).isTrue();
    assertThat(
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 3)).isBefore(
            DateTimeValue.of(DateValue.of(2008, 12, 23), TimeValue.of(9, 1, 6, 3)))).isTrue();
  }

  /**
   * This method tests the {@code isAfter} method.
   */
  @Test
  public void test_isAfter() {
    assertThat(
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 3)).isAfter(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 2)))).isTrue();
    assertThat(
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 3)).isAfter(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 5, 3)))).isTrue();
    assertThat(
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 3)).isAfter(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 0, 6, 3)))).isTrue();
    assertThat(
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 3)).isAfter(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(8, 1, 6, 3)))).isTrue();
    assertThat(
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 3)).isAfter(
            DateTimeValue.of(DateValue.of(2007, 12, 22), TimeValue.of(9, 1, 6, 3)))).isTrue();
    assertThat(
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 3)).isAfter(
            DateTimeValue.of(DateValue.of(2007, 11, 23), TimeValue.of(9, 1, 6, 3)))).isTrue();
    assertThat(
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 3)).isAfter(
            DateTimeValue.of(DateValue.of(2006, 12, 23), TimeValue.of(9, 1, 6, 3)))).isTrue();

    assertThat(
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 3)).isAfter(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 3)))).isFalse();

    assertThat(
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 3)).isAfter(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 4)))).isFalse();
    assertThat(
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 3)).isAfter(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 7, 3)))).isFalse();
    assertThat(
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 3)).isAfter(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 2, 6, 3)))).isFalse();
    assertThat(
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 3)).isAfter(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(10, 1, 6, 3)))).isFalse();
    assertThat(
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 3)).isAfter(
            DateTimeValue.of(DateValue.of(2007, 12, 24), TimeValue.of(9, 1, 6, 3)))).isFalse();
    assertThat(
        DateTimeValue.of(DateValue.of(2007, 11, 23), TimeValue.of(9, 1, 6, 3)).isAfter(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 3)))).isFalse();
    assertThat(
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 3)).isAfter(
            DateTimeValue.of(DateValue.of(2008, 12, 23), TimeValue.of(9, 1, 6, 3)))).isFalse();
  }

  /**
   * This method tests the {@code isMidnight} method.
   */
  @Test
  public void test_isMidnight() {
    assertThat(
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 3)).isMidnight()).isFalse();
    assertThat(
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6)).isMidnight()).isFalse();
    assertThat(
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).isMidnight()).isFalse();
    assertThat(
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(0, 0)).isMidnight()).isTrue();
    assertThat(
        DateTimeValue.of(DateValue.of(2007, 12, 23)).isMidnight()).isTrue();
  }

  /**
   * This method tests the {@code move} method with a {@code DateValue}.
   */
  @Test
  public void test_move_date() {
    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(DateValue.of(0, 0, 1))).as("add 1 day").isEqualTo(
            DateTimeValue.of(DateValue.of(2007, 12, 24), TimeValue.of(9, 1)));
    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(DateValue.of(0, 0, 2))).as("add 2 days").isEqualTo(
            DateTimeValue.of(DateValue.of(2007, 12, 25), TimeValue.of(9, 1)));

    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(DateValue.of(0, 0, -1))).as("substract 1 day").isEqualTo(
            DateTimeValue.of(DateValue.of(2007, 12, 22), TimeValue.of(9, 1)));
    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(DateValue.of(0, 0, -2))).as("substract 2 days").isEqualTo(
            DateTimeValue.of(DateValue.of(2007, 12, 21), TimeValue.of(9, 1)));

    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(DateValue.of(0, 1, 0))).as("add 1 month").isEqualTo(
            DateTimeValue.of(DateValue.of(2008, 1, 23), TimeValue.of(9, 1)));
    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(DateValue.of(0, 2, 0))).as("add 2 months").isEqualTo(
            DateTimeValue.of(DateValue.of(2008, 2, 23), TimeValue.of(9, 1)));

    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(DateValue.of(0, -1, 0))).as("substract 1 month").isEqualTo(
            DateTimeValue.of(DateValue.of(2007, 11, 23), TimeValue.of(9, 1)));
    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(DateValue.of(0, -2, 0))).as("substract 2 months").isEqualTo(
            DateTimeValue.of(DateValue.of(2007, 10, 23), TimeValue.of(9, 1)));

    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(DateValue.of(1, 0, 0))).as("add 1 year").isEqualTo(
            DateTimeValue.of(DateValue.of(2008, 12, 23), TimeValue.of(9, 1)));
    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(DateValue.of(2, 0, 0))).as("add 2 years").isEqualTo(
            DateTimeValue.of(DateValue.of(2009, 12, 23), TimeValue.of(9, 1)));

    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(DateValue.of(-1, 0, 0))).as("substract 1 year").isEqualTo(
            DateTimeValue.of(DateValue.of(2006, 12, 23), TimeValue.of(9, 1)));
    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(DateValue.of(-2, 0, 0))).as("substract 2 years").isEqualTo(
            DateTimeValue.of(DateValue.of(2005, 12, 23), TimeValue.of(9, 1)));

    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(DateValue.of(1, 1, 1))).as("add 1 all").isEqualTo(
            DateTimeValue.of(DateValue.of(2009, 1, 24), TimeValue.of(9, 1)));
    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(DateValue.of(2, 2, 2))).as("add 2 all").isEqualTo(
            DateTimeValue.of(DateValue.of(2010, 2, 25), TimeValue.of(9, 1)));

    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(DateValue.of(-1, -1, -1))).as("substract 1 all").isEqualTo(
            DateTimeValue.of(DateValue.of(2006, 11, 22), TimeValue.of(9, 1)));
    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(DateValue.of(-2, -2, -2))).as("substract 2 all").isEqualTo(
            DateTimeValue.of(DateValue.of(2005, 10, 21), TimeValue.of(9, 1)));
  }

  /**
   * This method tests the {@code move} method with a {@code TimeValue}.
   */
  @Test
  public void test_move_time() {
    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(TimeValue.of(0, 0, 0, 1))).as("add 1 nano").isEqualTo(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 0, 1)));
    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(TimeValue.of(0, 0, 0, 2))).as("add 2 nanos").isEqualTo(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 0, 2)));

    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(TimeValue.of(0, 0, 0, -1))).as("substract 1 nano").isEqualTo(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 0, 59, 999999999)));
    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(TimeValue.of(0, 0, 0, -2))).as("substract 2 nanos").isEqualTo(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 0, 59, 999999998)));

    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(TimeValue.of(0, 0, 0, 1000000))).as("add 1 milli").isEqualTo(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 0, 1000000)));
    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(TimeValue.of(0, 0, 0, 2000000))).as("add 2 millis").isEqualTo(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 0, 2000000)));

    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(TimeValue.of(0, 0, 0, -1000000))).as("substract 1 milli").isEqualTo(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 0, 59, 999000000)));
    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(TimeValue.of(0, 0, 0, -2000000))).as("substract 2 millis").isEqualTo(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 0, 59, 998000000)));

    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(TimeValue.of(0, 0, 1))).as("add 1 second").isEqualTo(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 1)));
    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(TimeValue.of(0, 0, 2))).as("add 2 seconds").isEqualTo(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 2)));

    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(TimeValue.of(0, 0, -1))).as("substract 1 second").isEqualTo(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 0, 59, 0)));
    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(TimeValue.of(0, 0, -2))).as("substract 2 seconds").isEqualTo(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 0, 58, 0)));

    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(TimeValue.of(0, 1, 0))).as("add 1 minute").isEqualTo(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 2)));
    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(TimeValue.of(0, 2, 0))).as("add 2 minutes").isEqualTo(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 3)));

    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(TimeValue.of(0, -1, 0))).as("substract 1 minute").isEqualTo(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 0, 0, 0)));
    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(TimeValue.of(0, -2, 0))).as("substract 2 minutes").isEqualTo(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(8, 59, 0, 0)));

    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(TimeValue.of(1, 0, 0))).as("add 1 hour").isEqualTo(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(10, 1)));
    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(TimeValue.of(2, 0, 0))).as("add 2 hours").isEqualTo(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(11, 1)));

    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(TimeValue.of(-1, 0, 0))).as("substract 1 hour").isEqualTo(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(8, 1, 0, 0)));
    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(TimeValue.of(-2, 0, 0))).as("substract 2 hours").isEqualTo(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(7, 1, 0, 0)));

    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(TimeValue.of(1, 1, 1, 1000000))).as("add 1 all").isEqualTo(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(10, 2, 1, 1000000)));
    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(TimeValue.of(2, 2, 2, 2000000))).as("add 2 all").isEqualTo(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(11, 3, 2, 2000000)));

    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(TimeValue.of(-1, -1, -1, -1000000))).as("substract 1 all").isEqualTo(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(7, 59, 58, 999000000)));
    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(TimeValue.of(-2, -2, -2, -2000000))).as("substract 2 all").isEqualTo(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(6, 58, 57, 998000000)));
  }

  /**
   * This method tests the {@code move} method with a {@code TimeValue}.
   */
  @Test
  public void test_move_datetime() {
    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1))
                        .move(DateTimeValue.of(DateValue.of(0, 0, 0), TimeValue.of(0, 0, 0, 1)))).as("add 1 nano")
                                                                                                       .isEqualTo(
                                                                                                               DateTimeValue.of(DateValue.of(2007, 12, 23),
                                                                                                                                TimeValue.of(9, 1, 0, 1)));
    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(DateTimeValue.of(DateValue.of(0, 0, 0), TimeValue.of(0, 0, 0, 2)))).as(
            "add 2 millis").isEqualTo(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 0, 2)));

    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(DateTimeValue.of(DateValue.of(0, 0, 0), TimeValue.of(0, 0, 0, -1)))).as(
            "substract 1 nanos").isEqualTo(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 0, 59, 999999999)));
    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(DateTimeValue.of(DateValue.of(0, 0, 0), TimeValue.of(0, 0, 0, -2)))).as(
            "substract 2 nanos").isEqualTo(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 0, 59, 999999998)));

    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1))
                            .move(DateTimeValue.of(DateValue.of(0, 0, 0), TimeValue.of(0, 0, 0, 1000000)))).as("add 1 milli")
                                                                                                           .isEqualTo(
                                                                                                                   DateTimeValue.of(DateValue.of(2007, 12, 23),
                                                                                                                                    TimeValue.of(9, 1, 0, 1000000)));
    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(DateTimeValue.of(DateValue.of(0, 0, 0), TimeValue.of(0, 0, 0, 2000000)))).as(
            "add 2 millis").isEqualTo(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 0, 2000000)));

    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(DateTimeValue.of(DateValue.of(0, 0, 0), TimeValue.of(0, 0, 0, -1000000)))).as(
            "substract 1 milli").isEqualTo(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 0, 59, 999000000)));
    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(DateTimeValue.of(DateValue.of(0, 0, 0), TimeValue.of(0, 0, 0, -2000000)))).as(
            "substract 2 millis").isEqualTo(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 0, 59, 998000000)));

    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(DateTimeValue.of(DateValue.of(0, 0, 0), TimeValue.of(0, 0, 1)))).as(
            "add 1 second").isEqualTo(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 1)));
    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(DateTimeValue.of(DateValue.of(0, 0, 0), TimeValue.of(0, 0, 2)))).as(
            "add 2 seconds").isEqualTo(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 2)));

    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(DateTimeValue.of(DateValue.of(0, 0, 0), TimeValue.of(0, 0, -1)))).as(
            "substract 1 second").isEqualTo(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 0, 59, 0)));
    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(DateTimeValue.of(DateValue.of(0, 0, 0), TimeValue.of(0, 0, -2)))).as(
            "substract 2 seconds").isEqualTo(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 0, 58, 0)));

    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(DateTimeValue.of(DateValue.of(0, 0, 0), TimeValue.of(0, 1, 0)))).as(
            "add 1 minute").isEqualTo(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 2)));
    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(DateTimeValue.of(DateValue.of(0, 0, 0), TimeValue.of(0, 2, 0)))).as(
            "add 2 minutes").isEqualTo(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 3)));

    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(DateTimeValue.of(DateValue.of(0, 0, 0), TimeValue.of(0, -1, 0)))).as(
            "substract 1 minute").isEqualTo(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 0, 0, 0)));
    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(DateTimeValue.of(DateValue.of(0, 0, 0), TimeValue.of(0, -2, 0)))).as(
            "substract 2 minutes").isEqualTo(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(8, 59, 0, 0)));

    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(DateTimeValue.of(DateValue.of(0, 0, 0), TimeValue.of(1, 0, 0)))).as(
            "add 1 hour").isEqualTo(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(10, 1)));
    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(DateTimeValue.of(DateValue.of(0, 0, 0), TimeValue.of(2, 0, 0)))).as(
            "add 2 hours").isEqualTo(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(11, 1)));

    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(DateTimeValue.of(DateValue.of(0, 0, 0), TimeValue.of(-1, 0, 0)))).as(
            "substract 1 hour").isEqualTo(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(8, 1, 0, 0)));
    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(DateTimeValue.of(DateValue.of(0, 0, 0), TimeValue.of(-2, 0, 0)))).as(
            "substract 2 hours").isEqualTo(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(7, 1, 0, 0)));

    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(DateTimeValue.of(DateValue.of(0, 0, 1), TimeValue.of(0, 0)))).as("add 1 day").isEqualTo(
            DateTimeValue.of(DateValue.of(2007, 12, 24), TimeValue.of(9, 1)));
    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(DateTimeValue.of(DateValue.of(0, 0, 2), TimeValue.of(0, 0)))).as("add 2 days").isEqualTo(
            DateTimeValue.of(DateValue.of(2007, 12, 25), TimeValue.of(9, 1)));

    assertThat(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(DateTimeValue.of(DateValue.of(0, 0, -1), TimeValue.of(0, 0)))).as("substract 1 day").isEqualTo(
            DateTimeValue.of(DateValue.of(2007, 12, 22), TimeValue.of(9, 1)));
    assertThat(
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(DateTimeValue.of(DateValue.of(0, 0, -2), TimeValue.of(0, 0)))).as("substract 2 days").isEqualTo(
            DateTimeValue.of(DateValue.of(2007, 12, 21), TimeValue.of(9, 1)));

    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(DateTimeValue.of(DateValue.of(0, 1, 0), TimeValue.of(0, 0)))).as("add 1 month")
                                                                                                            .isEqualTo(DateTimeValue.of(
                                                                                                                    DateValue
                                                                                                                            .of(2008,
                                                                                                                                1,
                                                                                                                                23), TimeValue.of(9, 1)));
    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(DateTimeValue.of(DateValue.of(0, 2, 0), TimeValue.of(0, 0)))).as("add 2 months")
                                                                                                            .isEqualTo(DateTimeValue.of(DateValue.of(2008, 2, 23), TimeValue.of(9, 1)));

    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(DateTimeValue.of(DateValue.of(0, -1, 0), TimeValue.of(0, 0)))).as("substract 1 month")
                                                                                                             .isEqualTo(DateTimeValue.of(DateValue.of(2007, 11, 23), TimeValue.of(9, 1)));
    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(DateTimeValue.of(DateValue.of(0, -2, 0), TimeValue.of(0, 0)))).as("substract 2 months")
                                                                                                             .isEqualTo(DateTimeValue.of(DateValue.of(2007, 10, 23), TimeValue.of(9, 1)));

    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(DateTimeValue.of(DateValue.of(1, 0, 0), TimeValue.of(0, 0)))).as("add 1 year")
                                                                                                            .isEqualTo(DateTimeValue.of(DateValue.of(2008, 12, 23), TimeValue.of(9, 1)));
    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(DateTimeValue.of(DateValue.of(2, 0, 0), TimeValue.of(0, 0)))).as("add 2 years")
                                                                                                            .isEqualTo(DateTimeValue.of(DateValue.of(2009, 12, 23), TimeValue.of(9, 1)));

    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(DateTimeValue.of(DateValue.of(-1, 0, 0), TimeValue.of(0, 0)))).as("substract 1 year")
                                                                                                             .isEqualTo(DateTimeValue.of(DateValue.of(2006, 12, 23), TimeValue.of(9, 1)));
    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(DateTimeValue.of(DateValue.of(-2, 0, 0), TimeValue.of(0, 0)))).as("substract 2 years")
                                                                                                             .isEqualTo(DateTimeValue.of(DateValue.of(2005, 12, 23), TimeValue.of(9, 1)));

    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(DateTimeValue.of(DateValue.of(1, 1, 1), TimeValue.of(1, 1, 1, 1000000)))).as(
            "add 1 all").isEqualTo(
            DateTimeValue.of(DateValue.of(2009, 1, 24), TimeValue.of(10, 2, 1, 1000000)));
    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(DateTimeValue.of(DateValue.of(2, 2, 2), TimeValue.of(2, 2, 2, 2000000)))).as(
            "add 2 all").isEqualTo(
            DateTimeValue.of(DateValue.of(2010, 2, 25), TimeValue.of(11, 3, 2, 2000000)));

    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(DateTimeValue.of(DateValue.of(-1, -1, -1), TimeValue.of(-1, -1, -1, -1000000)))).as(
            "substract 1 all").isEqualTo(
            DateTimeValue.of(DateValue.of(2006, 11, 22), TimeValue.of(7, 59, 58, 999000000)));
    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).move(DateTimeValue.of(DateValue.of(-2, -2, -2), TimeValue.of(-2, -2, -2, -2000000)))).as(
            "substract 2 all").isEqualTo(
            DateTimeValue.of(DateValue.of(2005, 10, 21), TimeValue.of(6, 58, 57, 998000000)));
  }

  /**
   * This method tests the {@code reverse} method with a {@code DateValue}.
   */
  @Test
  public void test_reverse() {
    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)).reverse())
                            .isEqualTo(DateTimeValue.of(DateValue.of(-2007, -12, -23), TimeValue.of(-9, -1)));
    assertThat(DateTimeValue.of(DateValue.of(1, 1, 1), TimeValue.of(1, 1, 1, 1)).reverse())
                            .isEqualTo(DateTimeValue.of(DateValue.of(-1, -1, -1), TimeValue.of(-1, -1, -1, -1)));
  }
}
