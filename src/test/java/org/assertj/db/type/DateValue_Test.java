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
package org.assertj.db.type;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Calendar;

import org.assertj.db.common.AbstractTest;
import org.junit.Test;

/**
 * Tests on the date value.
 *
 * @author Régis Pouiller
 */
public class DateValue_Test extends AbstractTest {

  /**
   * This method tests the constructor with a {@code Date}.
   */
  @Test
  public void test_contructor_with_date() throws ParseException {
    DateValue dateValue = new DateValue(Date.valueOf("2007-12-23"));
    assertThat(dateValue.getDayOfTheMonth()).isEqualTo(23);
    assertThat(dateValue.getMonth()).isEqualTo(12);
    assertThat(dateValue.getYear()).isEqualTo(2007);
  }

  /**
   * This method tests the constructor with a {@code Calendar}.
   */
  @Test
  public void test_contructor_with_calendar() throws ParseException {
    Calendar calendar = Calendar.getInstance();
    calendar.set(2007, Calendar.DECEMBER, 23);
    DateValue dateValue = new DateValue(calendar);
    assertThat(dateValue.getDayOfTheMonth()).isEqualTo(23);
    assertThat(dateValue.getMonth()).isEqualTo(12);
    assertThat(dateValue.getYear()).isEqualTo(2007);
  }

  /**
   * This method tests the constructor with a {@code LocalDate}.
   */
  @Test
  public void test_constructor_with_local_date() {
    LocalDate localDate = LocalDate.of(2007, 12, 23);
    DateValue dateValue = new DateValue(localDate);
    assertThat(dateValue.getDayOfTheMonth()).isEqualTo(23);
    assertThat(dateValue.getMonth()).isEqualTo(12);
    assertThat(dateValue.getYear()).isEqualTo(2007);
  }

  /**
   * This method should throw a {@code NullPointerException} because passing a {@code null} parameter to constructor
   * with a {@code Date}.
   */
  @Test(expected = NullPointerException.class)
  public void should_constructor_with_date_fail_if_date_is_null() throws ParseException {
    new DateValue((Date) null);
  }

  /**
   * This method should throw a {@code NullPointerException} because passing a {@code null} parameter to constructor
   * with a {@code LocalDate}.
   */
  @Test(expected = NullPointerException.class)
  public void should_constructor_with_local_date_fail_if_local_date_is_null() {
    new DateValue((LocalDate) null);
  }

  /**
   * This method tests the constructor with a {@code String}.
   */
  @Test
  public void test_contructor_with_string() throws ParseException {
    DateValue dateValue = new DateValue("2007-12-23");
    assertThat(dateValue.getDayOfTheMonth()).isEqualTo(23);
    assertThat(dateValue.getMonth()).isEqualTo(12);
    assertThat(dateValue.getYear()).isEqualTo(2007);
  }

  /**
   * This method should throw a {@code NullPointerException} because passing a {@code null} parameter to constructor
   * with a {@code String}.
   */
  @Test(expected = NullPointerException.class)
  public void should_constructor_with_string_fail_if_date_is_null() throws ParseException {
    new DateValue((String) null);
  }

  /**
   * This method should throw a {@code ParseException} because passing a parameter to constructor with a bad length.
   */
  @Test(expected = ParseException.class)
  public void should_constructor_with_string_fail_if_date_has_bad_length() throws ParseException {
    new DateValue("12345678901");
  }

  /**
   * This method should throw a {@code ParseException} because passing a parameter to constructor with a bad character
   * on year.
   */
  @Test(expected = ParseException.class)
  public void should_constructor_with_string_fail_if_date_has_bad_character_on_year() throws ParseException {
    new DateValue("a007-12-23");
  }

  /**
   * This method should throw a {@code ParseException} because passing a parameter to constructor with a bad character
   * on separator.
   */
  @Test(expected = ParseException.class)
  public void should_constructor_with_string_fail_if_date_has_bad_character_on_separator() throws ParseException {
    new DateValue("2007a12-23");
  }

  /**
   * This method tests the constructor with values (3 {@code int}).
   */
  @Test
  public void test_contructor_with_values() throws ParseException {
    DateValue dateValue = new DateValue(2007, 12, 23);
    assertThat(dateValue.getDayOfTheMonth()).isEqualTo(23);
    assertThat(dateValue.getMonth()).isEqualTo(12);
    assertThat(dateValue.getYear()).isEqualTo(2007);
  }

  /**
   * This method tests the {@code now} static method.
   */
  @Test
  public void test_now() throws ParseException {
    Calendar calendarFirst = Calendar.getInstance();
    DateValue dateValue = DateValue.now();
    Calendar calendarSecond = Calendar.getInstance();
    assertThat(dateValue.getDayOfTheMonth()).isBetween(calendarFirst.get(Calendar.DAY_OF_MONTH),
      calendarSecond.get(Calendar.DAY_OF_MONTH));
    assertThat(dateValue.getMonth()).isBetween(calendarFirst.get(Calendar.MONTH) + 1,
      calendarSecond.get(Calendar.MONTH) + 1);
    assertThat(dateValue.getYear()).isBetween(calendarFirst.get(Calendar.YEAR),
      calendarSecond.get(Calendar.YEAR));
  }

  /**
   * This method tests the {@code from} static method with a {@code Calendar}.
   */
  @Test
  public void test_from_calendar() throws ParseException {
    Calendar calendar = Calendar.getInstance();
    calendar.set(2007, Calendar.DECEMBER, 23);
    DateValue dateValue = DateValue.from(calendar);
    assertThat(dateValue.getDayOfTheMonth()).isEqualTo(23);
    assertThat(dateValue.getMonth()).isEqualTo(12);
    assertThat(dateValue.getYear()).isEqualTo(2007);
  }

  /**
   * This method tests the {@code from} static method with a {@code LocalDate}.
   */
  @Test
  public void test_from_local_date() {
    LocalDate localDate = LocalDate.of(2007, 12, 23);
    DateValue dateValue = DateValue.from(localDate);
    assertThat(dateValue.getDayOfTheMonth()).isEqualTo(23);
    assertThat(dateValue.getMonth()).isEqualTo(12);
    assertThat(dateValue.getYear()).isEqualTo(2007);
  }

  /**
   * This method tests the {@code from} static method.
   */
  @Test
  public void test_from() throws ParseException {
    DateValue dateValue = DateValue.from(Date.valueOf("2002-07-26"));
    assertThat(dateValue.getDayOfTheMonth()).isEqualTo(26);
    assertThat(dateValue.getMonth()).isEqualTo(7);
    assertThat(dateValue.getYear()).isEqualTo(2002);
  }

  /**
   * This method should throw a {@code NullPointerException} because passing a {@code null} parameter to the
   * {@code from} static method.
   */
  @Test(expected = NullPointerException.class)
  public void should_from_fail_if_date_is_null() throws ParseException {
    DateValue.from((Date) null);
  }

  /**
   * This method should throw a {@code NullPointerException} because passing a {@code null} parameter to the
   * {@code from} static method.
   */
  @Test(expected = NullPointerException.class)
  public void should_from_fail_if_calendar_is_null() throws ParseException {
    DateValue.from((Calendar) null);
  }

  /**
   * This method should throw a {@code NullPointerException} because passing a {@code null} parameter to the
   * {@code from} static method.
   */
  @Test(expected = NullPointerException.class)
  public void should_from_fail_if_local_date_is_null() {
    DateValue.from((LocalDate) null);
  }

  /**
   * This method tests the {@code parse} static method.
   */
  @Test
  public void test_parse() throws ParseException {
    DateValue dateValue = DateValue.parse("2002-07-26");
    assertThat(dateValue.getDayOfTheMonth()).isEqualTo(26);
    assertThat(dateValue.getMonth()).isEqualTo(7);
    assertThat(dateValue.getYear()).isEqualTo(2002);
  }

  /**
   * This method should throw a {@code NullPointerException} because passing a {@code null} parameter to the
   * {@code parse} static method.
   */
  @Test(expected = NullPointerException.class)
  public void should_parse_fail_if_date_is_null() throws ParseException {
    DateValue.parse(null);
  }

  /**
   * This method should throw a {@code ParseException} because passing a parameter to {@code parse} static method with a
   * bad length.
   */
  @Test(expected = ParseException.class)
  public void should_parse_fail_if_date_has_bad_length() throws ParseException {
    DateValue.parse("12345678901");
  }

  /**
   * This method should throw a {@code ParseException} because passing a parameter to {@code parse} static method with a
   * bad character on year.
   */
  @Test(expected = ParseException.class)
  public void should_parse_fail_if_date_has_bad_character_on_year() throws ParseException {
    DateValue.parse("a007-12-23");
  }

  /**
   * This method should throw a {@code ParseException} because passing a parameter to {@code parse} static method with a
   * bad character on separator.
   */
  @Test(expected = ParseException.class)
  public void should_parse_fail_if_date_has_bad_character_on_separator() throws ParseException {
    DateValue.parse("2007a12-23");
  }

  /**
   * This method tests the {@code of} static method.
   */
  @Test
  public void test_of() throws ParseException {
    DateValue dateValue = DateValue.of(2002, 7, 26);
    assertThat(dateValue.getDayOfTheMonth()).isEqualTo(26);
    assertThat(dateValue.getMonth()).isEqualTo(7);
    assertThat(dateValue.getYear()).isEqualTo(2002);
  }

  /**
   * This method tests the {@code toString} method.
   */
  @Test
  public void test_toString() {
    assertThat(DateValue.of(2007, 12, 23)).hasToString("2007-12-23");
    assertThat(DateValue.of(2007, 2, 3)).hasToString("2007-02-03");
  }

  /**
   * This method tests the {@code equals} method.
   */
  @Test
  public void test_equals() {
    assertThat(DateValue.of(2007, 12, 23).equals(DateValue.of(2007, 12, 23))).isTrue();
    assertThat(DateValue.of(2007, 12, 23).equals(DateTimeValue.of(DateValue.of(2007, 12, 23)))).isTrue();
    assertThat(DateValue.of(2007, 12, 23).equals(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(0, 0))))
      .isTrue();
    assertThat(DateValue.of(2007, 12, 23).equals(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(0, 0, 0))))
      .isTrue();
    assertThat(
      DateValue.of(2007, 12, 23).equals(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(0, 0, 0, 0))))
      .isTrue();

    assertThat(DateValue.of(2007, 12, 23).equals(DateValue.of(2008, 12, 23))).isFalse();
    assertThat(DateValue.of(2007, 12, 23).equals(DateValue.of(2007, 1, 23))).isFalse();
    assertThat(DateValue.of(2007, 12, 23).equals(DateValue.of(2007, 12, 24))).isFalse();
    assertThat(DateValue.of(2007, 12, 23).equals(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 0))))
      .isFalse();
    assertThat(DateValue.of(2007, 12, 23).equals(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(0, 1))))
      .isFalse();
    assertThat(DateValue.of(2007, 12, 23).equals(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(0, 0, 6))))
      .isFalse();
    assertThat(
      DateValue.of(2007, 12, 23).equals(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(0, 0, 0, 3))))
      .isFalse();
    assertThat(
      DateValue.of(2007, 12, 23).equals(DateTimeValue.of(DateValue.of(2008, 12, 23), TimeValue.of(0, 0, 0, 0))))
      .isFalse();
    assertThat(DateValue.of(2007, 12, 23).equals(DateTimeValue.of(DateValue.of(2007, 1, 23), TimeValue.of(0, 0, 0, 0))))
      .isFalse();
    assertThat(
      DateValue.of(2007, 12, 23).equals(DateTimeValue.of(DateValue.of(2007, 12, 24), TimeValue.of(0, 0, 0, 0))))
      .isFalse();
    assertThat(DateValue.of(2007, 12, 23).equals(DateTimeValue.of(DateValue.of(2007, 12, 24)))).isFalse();
    assertThat(DateValue.of(2007, 12, 23).equals("")).isFalse();
  }

  /**
   * This method tests the {@code hashCode} method.
   */
  @Test
  public void test_hashCode() {
    assertThat(DateValue.of(2007, 12, 23).hashCode()).isEqualTo(54273);
  }

  /**
   * This method tests the {@code compareTo} method.
   */
  @Test
  public void test_compareTo() {
    assertThat(DateValue.of(2007, 12, 23).compareTo(DateValue.of(2007, 12, 22))).isEqualTo(1);
    assertThat(DateValue.of(2007, 12, 23).compareTo(DateValue.of(2007, 11, 23))).isEqualTo(1);
    assertThat(DateValue.of(2007, 12, 23).compareTo(DateValue.of(2006, 12, 23))).isEqualTo(1);
    assertThat(DateValue.of(2007, 12, 23).compareTo(DateValue.of(2007, 11, 24))).isEqualTo(1);
    assertThat(DateValue.of(2007, 11, 23).compareTo(DateValue.of(2006, 12, 23))).isEqualTo(1);

    assertThat(DateValue.of(2007, 12, 23).compareTo(DateValue.of(2007, 12, 23))).isEqualTo(0);

    assertThat(DateValue.of(2007, 12, 23).compareTo(DateValue.of(2007, 12, 24))).isEqualTo(-1);
    assertThat(DateValue.of(2007, 11, 23).compareTo(DateValue.of(2007, 12, 23))).isEqualTo(-1);
    assertThat(DateValue.of(2007, 12, 23).compareTo(DateValue.of(2008, 12, 23))).isEqualTo(-1);
    assertThat(DateValue.of(2007, 11, 23).compareTo(DateValue.of(2007, 12, 22))).isEqualTo(-1);
    assertThat(DateValue.of(2007, 12, 23).compareTo(DateValue.of(2008, 11, 23))).isEqualTo(-1);
  }

  /**
   * This method tests the {@code isBefore} method.
   */
  @Test
  public void test_isBefore() {
    assertThat(DateValue.of(2007, 12, 23).isBefore(DateValue.of(2007, 12, 22))).isFalse();
    assertThat(DateValue.of(2007, 12, 23).isBefore(DateValue.of(2007, 11, 23))).isFalse();
    assertThat(DateValue.of(2007, 12, 23).isBefore(DateValue.of(2006, 12, 23))).isFalse();
    assertThat(DateValue.of(2007, 12, 23).isBefore(DateValue.of(2007, 11, 24))).isFalse();
    assertThat(DateValue.of(2007, 11, 23).isBefore(DateValue.of(2006, 12, 23))).isFalse();

    assertThat(DateValue.of(2007, 12, 23).isBefore(DateValue.of(2007, 12, 23))).isFalse();

    assertThat(DateValue.of(2007, 12, 23).isBefore(DateValue.of(2007, 12, 24))).isTrue();
    assertThat(DateValue.of(2007, 11, 23).isBefore(DateValue.of(2007, 12, 23))).isTrue();
    assertThat(DateValue.of(2007, 12, 23).isBefore(DateValue.of(2008, 12, 23))).isTrue();
    assertThat(DateValue.of(2007, 11, 23).isBefore(DateValue.of(2007, 12, 22))).isTrue();
    assertThat(DateValue.of(2007, 12, 23).isBefore(DateValue.of(2008, 11, 23))).isTrue();
  }

  /**
   * This method tests the {@code isAfter} method.
   */
  @Test
  public void test_isAfter() {
    assertThat(DateValue.of(2007, 12, 23).isAfter(DateValue.of(2007, 12, 22))).isTrue();
    assertThat(DateValue.of(2007, 12, 23).isAfter(DateValue.of(2007, 11, 23))).isTrue();
    assertThat(DateValue.of(2007, 12, 23).isAfter(DateValue.of(2006, 12, 23))).isTrue();
    assertThat(DateValue.of(2007, 12, 23).isAfter(DateValue.of(2007, 11, 24))).isTrue();
    assertThat(DateValue.of(2007, 11, 23).isAfter(DateValue.of(2006, 12, 23))).isTrue();

    assertThat(DateValue.of(2007, 12, 23).isBefore(DateValue.of(2007, 12, 23))).isFalse();

    assertThat(DateValue.of(2007, 12, 23).isAfter(DateValue.of(2007, 12, 24))).isFalse();
    assertThat(DateValue.of(2007, 11, 23).isAfter(DateValue.of(2007, 12, 23))).isFalse();
    assertThat(DateValue.of(2007, 12, 23).isAfter(DateValue.of(2008, 12, 23))).isFalse();
    assertThat(DateValue.of(2007, 11, 23).isAfter(DateValue.of(2007, 12, 22))).isFalse();
    assertThat(DateValue.of(2007, 12, 23).isAfter(DateValue.of(2008, 11, 23))).isFalse();
  }

  /**
   * This method tests the {@code isMidnight} method.
   */
  @Test
  public void test_isMidnight() {
    assertThat(DateValue.of(2007, 12, 23).isMidnight()).isTrue();
  }

  /**
   * This method tests the {@code move} method with a {@code DateValue}.
   */
  @Test
  public void test_move_date() {
    assertThat(DateValue.of(2007, 12, 23).move(DateValue.of(0, 0, 1))).as("add 1 day").isEqualTo(
      DateValue.of(2007, 12, 24));
    assertThat(DateValue.of(2007, 12, 23).move(DateValue.of(0, 0, 2))).as("add 2 days").isEqualTo(
      DateValue.of(2007, 12, 25));

    assertThat(DateValue.of(2007, 12, 23).move(DateValue.of(0, 0, -1))).as("substract 1 day").isEqualTo(
      DateValue.of(2007, 12, 22));
    assertThat(DateValue.of(2007, 12, 23).move(DateValue.of(0, 0, -2))).as("substract 2 days").isEqualTo(
      DateValue.of(2007, 12, 21));

    assertThat(DateValue.of(2007, 12, 23).move(DateValue.of(0, 1, 0))).as("add 1 month").isEqualTo(DateValue.of(2008, 1, 23));
    assertThat(DateValue.of(2007, 12, 23).move(DateValue.of(0, 2, 0))).as("add 2 months").isEqualTo(DateValue.of(2008, 2, 23));

    assertThat(DateValue.of(2007, 12, 23).move(DateValue.of(0, -1, 0))).as("substract 1 month").isEqualTo(DateValue.of(2007, 11, 23));
    assertThat(DateValue.of(2007, 12, 23).move(DateValue.of(0, -2, 0))).as("substract 2 months").isEqualTo(DateValue.of(2007, 10, 23));

    assertThat(DateValue.of(2007, 12, 23).move(DateValue.of(1, 0, 0))).as("add 1 year").isEqualTo(DateValue.of(2008, 12, 23));
    assertThat(DateValue.of(2007, 12, 23).move(DateValue.of(2, 0, 0))).as("add 2 years").isEqualTo(DateValue.of(2009, 12, 23));

    assertThat(DateValue.of(2007, 12, 23).move(DateValue.of(-1, 0, 0))).as("substract 1 year").isEqualTo(DateValue.of(2006, 12, 23));
    assertThat(DateValue.of(2007, 12, 23).move(DateValue.of(-2, 0, 0))).as("substract 2 years").isEqualTo(DateValue.of(2005, 12, 23));

    assertThat(DateValue.of(2007, 12, 23).move(DateValue.of(1, 1, 1))).as("add 1 all").isEqualTo(DateValue.of(2009, 1, 24));
    assertThat(DateValue.of(2007, 12, 23).move(DateValue.of(2, 2, 2))).as("add 2 all").isEqualTo(DateValue.of(2010, 2, 25));

    assertThat(DateValue.of(2007, 12, 23).move(DateValue.of(-1, -1, -1))).as("substract 1 all").isEqualTo(DateValue.of(2006, 11, 22));
    assertThat(DateValue.of(2007, 12, 23).move(DateValue.of(-2, -2, -2))).as("substract 2 all").isEqualTo(DateValue.of(2005, 10, 21));
  }

  /**
   * This method tests the {@code move} method with a {@code TimeValue}.
   */
  @Test
  public void test_move_time() {
    assertThat(DateValue.of(2007, 12, 23).move(TimeValue.of(0, 0, 0, 1000000))).as("add 1 milli").isEqualTo(
      DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(0, 0, 0, 1000000)));
    assertThat(DateValue.of(2007, 12, 23).move(TimeValue.of(0, 0, 0, 2000000))).as("add 2 millis").isEqualTo(
      DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(0, 0, 0, 2000000)));

    assertThat(DateValue.of(2007, 12, 23).move(TimeValue.of(0, 0, 0, -1000000))).as("substract 1 milli").isEqualTo(
      DateTimeValue.of(DateValue.of(2007, 12, 22), TimeValue.of(23, 59, 59, 999000000)));
    assertThat(DateValue.of(2007, 12, 23).move(TimeValue.of(0, 0, 0, -2000000))).as("substract 2 millis").isEqualTo(
      DateTimeValue.of(DateValue.of(2007, 12, 22), TimeValue.of(23, 59, 59, 998000000)));

    assertThat(DateValue.of(2007, 12, 23).move(TimeValue.of(0, 0, 1))).as("add 1 second").isEqualTo(
      DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(0, 0, 1)));
    assertThat(DateValue.of(2007, 12, 23).move(TimeValue.of(0, 0, 2))).as("add 2 seconds").isEqualTo(
      DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(0, 0, 2)));

    assertThat(DateValue.of(2007, 12, 23).move(TimeValue.of(0, 0, -1))).as("substract 1 second").isEqualTo(
      DateTimeValue.of(DateValue.of(2007, 12, 22), TimeValue.of(23, 59, 59, 0)));
    assertThat(DateValue.of(2007, 12, 23).move(TimeValue.of(0, 0, -2))).as("substract 2 seconds").isEqualTo(
      DateTimeValue.of(DateValue.of(2007, 12, 22), TimeValue.of(23, 59, 58, 0)));

    assertThat(DateValue.of(2007, 12, 23).move(TimeValue.of(0, 1, 0))).as("add 1 minute").isEqualTo(
      DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(0, 1)));
    assertThat(DateValue.of(2007, 12, 23).move(TimeValue.of(0, 2, 0))).as("add 2 minutes").isEqualTo(
      DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(0, 2)));

    assertThat(DateValue.of(2007, 12, 23).move(TimeValue.of(0, -1, 0))).as("substract 1 minute").isEqualTo(
      DateTimeValue.of(DateValue.of(2007, 12, 22), TimeValue.of(23, 59, 0, 0)));
    assertThat(DateValue.of(2007, 12, 23).move(TimeValue.of(0, -2, 0))).as("substract 2 minutes").isEqualTo(
      DateTimeValue.of(DateValue.of(2007, 12, 22), TimeValue.of(23, 58, 0, 0)));

    assertThat(DateValue.of(2007, 12, 23).move(TimeValue.of(1, 0, 0))).as("add 1 hour").isEqualTo(
      DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(1, 0)));
    assertThat(DateValue.of(2007, 12, 23).move(TimeValue.of(2, 0, 0))).as("add 2 hours").isEqualTo(
      DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(2, 0)));

    assertThat(DateValue.of(2007, 12, 23).move(TimeValue.of(-1, 0, 0))).as("substract 1 hour").isEqualTo(
      DateTimeValue.of(DateValue.of(2007, 12, 22), TimeValue.of(23, 0, 0, 0)));
    assertThat(DateValue.of(2007, 12, 23).move(TimeValue.of(-2, 0, 0))).as("substract 2 hours").isEqualTo(
      DateTimeValue.of(DateValue.of(2007, 12, 22), TimeValue.of(22, 0, 0, 0)));

    assertThat(DateValue.of(2007, 12, 23).move(TimeValue.of(1, 1, 1, 1000000))).as("add 1 all").isEqualTo(
      DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(1, 1, 1, 1000000)));
    assertThat(DateValue.of(2007, 12, 23).move(TimeValue.of(2, 2, 2, 2000000))).as("add 2 all").isEqualTo(
      DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(2, 2, 2, 2000000)));

    assertThat(DateValue.of(2007, 12, 23).move(TimeValue.of(-1, -1, -1, -1000000))).as("substract 1 all").isEqualTo(
      DateTimeValue.of(DateValue.of(2007, 12, 22), TimeValue.of(22, 58, 58, 999000000)));
    assertThat(DateValue.of(2007, 12, 23).move(TimeValue.of(-2, -2, -2, -2000000))).as("substract 2 all").isEqualTo(
      DateTimeValue.of(DateValue.of(2007, 12, 22), TimeValue.of(21, 57, 57, 998000000)));
  }

  /**
   * This method tests the {@code move} method with a {@code TimeValue}.
   */
  @Test
  public void test_move_datetime() {
    assertThat(DateValue.of(2007, 12, 23)
      .move(DateTimeValue.of(DateValue.of(0, 0, 0), TimeValue.of(0, 0, 0, 1000000)))).as("add 1 milli")
      .isEqualTo(
        DateTimeValue.of(DateValue.of(2007, 12, 23),
          TimeValue.of(0, 0, 0, 1000000)));
    assertThat(DateValue.of(2007, 12, 23).move(DateTimeValue.of(DateValue.of(0, 0, 0), TimeValue.of(0, 0, 0, 2000000)))).as(
      "add 2 millis").isEqualTo(
      DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(0, 0, 0, 2000000)));

    assertThat(DateValue.of(2007, 12, 23).move(DateTimeValue.of(DateValue.of(0, 0, 0), TimeValue.of(0, 0, 0, -1000000)))).as(
      "substract 1 milli").isEqualTo(
      DateTimeValue.of(DateValue.of(2007, 12, 22), TimeValue.of(23, 59, 59, 999000000)));
    assertThat(DateValue.of(2007, 12, 23).move(DateTimeValue.of(DateValue.of(0, 0, 0), TimeValue.of(0, 0, 0, -2000000)))).as(
      "substract 2 millis").isEqualTo(
      DateTimeValue.of(DateValue.of(2007, 12, 22), TimeValue.of(23, 59, 59, 998000000)));

    assertThat(DateValue.of(2007, 12, 23).move(DateTimeValue.of(DateValue.of(0, 0, 0), TimeValue.of(0, 0, 1)))).as(
      "add 1 second").isEqualTo(
      DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(0, 0, 1)));
    assertThat(DateValue.of(2007, 12, 23).move(DateTimeValue.of(DateValue.of(0, 0, 0), TimeValue.of(0, 0, 2)))).as(
      "add 2 seconds").isEqualTo(
      DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(0, 0, 2)));

    assertThat(DateValue.of(2007, 12, 23).move(DateTimeValue.of(DateValue.of(0, 0, 0), TimeValue.of(0, 0, -1)))).as(
      "substract 1 second").isEqualTo(
      DateTimeValue.of(DateValue.of(2007, 12, 22), TimeValue.of(23, 59, 59, 0)));
    assertThat(DateValue.of(2007, 12, 23).move(DateTimeValue.of(DateValue.of(0, 0, 0), TimeValue.of(0, 0, -2)))).as(
      "substract 2 seconds").isEqualTo(
      DateTimeValue.of(DateValue.of(2007, 12, 22), TimeValue.of(23, 59, 58, 0)));

    assertThat(DateValue.of(2007, 12, 23).move(DateTimeValue.of(DateValue.of(0, 0, 0), TimeValue.of(0, 1, 0)))).as(
      "add 1 minute").isEqualTo(
      DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(0, 1)));
    assertThat(DateValue.of(2007, 12, 23).move(DateTimeValue.of(DateValue.of(0, 0, 0), TimeValue.of(0, 2, 0)))).as(
      "add 2 minutes").isEqualTo(
      DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(0, 2)));

    assertThat(DateValue.of(2007, 12, 23).move(DateTimeValue.of(DateValue.of(0, 0, 0), TimeValue.of(0, -1, 0)))).as(
      "substract 1 minute").isEqualTo(
      DateTimeValue.of(DateValue.of(2007, 12, 22), TimeValue.of(23, 59, 0, 0)));
    assertThat(DateValue.of(2007, 12, 23).move(DateTimeValue.of(DateValue.of(0, 0, 0), TimeValue.of(0, -2, 0)))).as(
      "substract 2 minutes").isEqualTo(
      DateTimeValue.of(DateValue.of(2007, 12, 22), TimeValue.of(23, 58, 0, 0)));

    assertThat(DateValue.of(2007, 12, 23).move(DateTimeValue.of(DateValue.of(0, 0, 0), TimeValue.of(1, 0, 0)))).as(
      "add 1 hour").isEqualTo(
      DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(1, 0)));
    assertThat(DateValue.of(2007, 12, 23).move(DateTimeValue.of(DateValue.of(0, 0, 0), TimeValue.of(2, 0, 0)))).as(
      "add 2 hours").isEqualTo(
      DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(2, 0)));

    assertThat(DateValue.of(2007, 12, 23).move(DateTimeValue.of(DateValue.of(0, 0, 0), TimeValue.of(-1, 0, 0)))).as(
      "substract 1 hour").isEqualTo(
      DateTimeValue.of(DateValue.of(2007, 12, 22), TimeValue.of(23, 0, 0, 0)));
    assertThat(DateValue.of(2007, 12, 23).move(DateTimeValue.of(DateValue.of(0, 0, 0), TimeValue.of(-2, 0, 0)))).as(
      "substract 2 hours").isEqualTo(
      DateTimeValue.of(DateValue.of(2007, 12, 22), TimeValue.of(22, 0, 0, 0)));

    assertThat(DateValue.of(2007, 12, 23).move(DateTimeValue.of(DateValue.of(0, 0, 1), TimeValue.of(0, 0)))).as("add 1 day").isEqualTo(
      DateTimeValue.of(DateValue.of(2007, 12, 24)));
    assertThat(DateValue.of(2007, 12, 23).move(DateTimeValue.of(DateValue.of(0, 0, 2), TimeValue.of(0, 0)))).as("add 2 days").isEqualTo(
      DateTimeValue.of(DateValue.of(2007, 12, 25)));

    assertThat(
      DateValue.of(2007, 12, 23).move(DateTimeValue.of(DateValue.of(0, 0, -1), TimeValue.of(0, 0)))).as("substract 1 day").isEqualTo(
      DateTimeValue.of(DateValue.of(2007, 12, 22)));
    assertThat(
      DateValue.of(2007, 12, 23).move(DateTimeValue.of(DateValue.of(0, 0, -2), TimeValue.of(0, 0)))).as("substract 2 days").isEqualTo(
      DateTimeValue.of(DateValue.of(2007, 12, 21)));

    assertThat(DateValue.of(2007, 12, 23).move(DateTimeValue.of(DateValue.of(0, 1, 0), TimeValue.of(0, 0)))).as("add 1 month")
      .isEqualTo(DateTimeValue.of(DateValue.of(2008, 1, 23)));
    assertThat(DateValue.of(2007, 12, 23).move(DateTimeValue.of(DateValue.of(0, 2, 0), TimeValue.of(0, 0)))).as("add 2 months")
      .isEqualTo(DateTimeValue.of(DateValue.of(2008, 2, 23)));

    assertThat(DateValue.of(2007, 12, 23).move(DateTimeValue.of(DateValue.of(0, -1, 0), TimeValue.of(0, 0)))).as("substract 1 month")
      .isEqualTo(DateTimeValue.of(DateValue.of(2007, 11, 23)));
    assertThat(DateValue.of(2007, 12, 23).move(DateTimeValue.of(DateValue.of(0, -2, 0), TimeValue.of(0, 0)))).as("substract 2 months")
      .isEqualTo(DateTimeValue.of(DateValue.of(2007, 10, 23)));

    assertThat(DateValue.of(2007, 12, 23).move(DateTimeValue.of(DateValue.of(1, 0, 0), TimeValue.of(0, 0)))).as("add 1 year")
      .isEqualTo(DateTimeValue.of(DateValue.of(2008, 12, 23)));
    assertThat(DateValue.of(2007, 12, 23).move(DateTimeValue.of(DateValue.of(2, 0, 0), TimeValue.of(0, 0)))).as("add 2 years")
      .isEqualTo(DateTimeValue.of(DateValue.of(2009, 12, 23)));

    assertThat(DateValue.of(2007, 12, 23).move(DateTimeValue.of(DateValue.of(-1, 0, 0), TimeValue.of(0, 0)))).as("substract 1 year")
      .isEqualTo(DateTimeValue.of(DateValue.of(2006, 12, 23)));
    assertThat(DateValue.of(2007, 12, 23).move(DateTimeValue.of(DateValue.of(-2, 0, 0), TimeValue.of(0, 0)))).as("substract 2 years")
      .isEqualTo(DateTimeValue.of(DateValue.of(2005, 12, 23)));

    assertThat(DateValue.of(2007, 12, 23).move(DateTimeValue.of(DateValue.of(1, 1, 1), TimeValue.of(1, 1, 1, 1000000)))).as(
      "add 1 all").isEqualTo(
      DateTimeValue.of(DateValue.of(2009, 1, 24), TimeValue.of(1, 1, 1, 1000000)));
    assertThat(DateValue.of(2007, 12, 23).move(DateTimeValue.of(DateValue.of(2, 2, 2), TimeValue.of(2, 2, 2, 2000000)))).as(
      "add 2 all").isEqualTo(
      DateTimeValue.of(DateValue.of(2010, 2, 25), TimeValue.of(2, 2, 2, 2000000)));

    assertThat(DateValue.of(2007, 12, 23).move(DateTimeValue.of(DateValue.of(-1, -1, -1), TimeValue.of(-1, -1, -1, -1000000)))).as(
      "substract 1 all").isEqualTo(
      DateTimeValue.of(DateValue.of(2006, 11, 21), TimeValue.of(22, 58, 58, 999000000)));
    assertThat(DateValue.of(2007, 12, 23).move(DateTimeValue.of(DateValue.of(-2, -2, -2), TimeValue.of(-2, -2, -2, -2000000)))).as(
      "substract 2 all").isEqualTo(
      DateTimeValue.of(DateValue.of(2005, 10, 20), TimeValue.of(21, 57, 57, 998000000)));
  }

  /**
   * This method tests the {@code reverse} method with a {@code DateValue}.
   */
  @Test
  public void test_reverse() {
    assertThat(DateValue.of(2007, 12, 23).reverse()).isEqualTo(DateValue.of(-2007, -12, -23));
    assertThat(DateValue.of(1, 1, 1).reverse()).isEqualTo(DateValue.of(-1, -1, -1));
  }
}
