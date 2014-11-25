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
package org.assertj.db.type;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Timestamp;
import java.text.ParseException;

import org.assertj.db.common.AbstractTest;
import org.junit.Test;

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
    assertThat(dateTimeValue.getTime().getHour()).isEqualTo(0);
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
    assertThat(dateTimeValue.getTime().getHour()).isEqualTo(9);
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
    assertThat(dateTimeValue.getTime().getHour()).isEqualTo(9);
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
    assertThat(dateTimeValue.getTime().getHour()).isEqualTo(9);
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
   * This method tests the {@code from} method containing seconds.
   */
  @Test
  public void test_constructor_with_timestamp() throws ParseException {
    DateTimeValue dateTimeValue = new DateTimeValue(Timestamp.valueOf("2007-12-23 09:01:06.000000003"));
    assertThat(dateTimeValue.getDate().getDayOfTheMonth()).isEqualTo(23);
    assertThat(dateTimeValue.getDate().getMonth()).isEqualTo(12);
    assertThat(dateTimeValue.getDate().getYear()).isEqualTo(2007);
    assertThat(dateTimeValue.getTime().getHour()).isEqualTo(9);
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
    new DateTimeValue((DateValue) null, TimeValue.of(9, 1));
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
    assertThat(dateTimeValue.getTime().getHour()).isEqualTo(9);
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
    assertThat(dateTimeValue.getTime().getHour()).isEqualTo(0);
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
    assertThat(dateTimeValue.getTime().getHour()).isEqualTo(9);
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
    assertThat(dateTimeValue.getTime().getHour()).isEqualTo(9);
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
    assertThat(dateTimeValue.getTime().getHour()).isEqualTo(9);
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
   * This method tests the {@code from} method containing seconds.
   */
  @Test
  public void test_from() throws ParseException {
    DateTimeValue dateTimeValue = DateTimeValue.from(Timestamp.valueOf("2007-12-23 09:01:06.000000003"));
    assertThat(dateTimeValue.getDate().getDayOfTheMonth()).isEqualTo(23);
    assertThat(dateTimeValue.getDate().getMonth()).isEqualTo(12);
    assertThat(dateTimeValue.getDate().getYear()).isEqualTo(2007);
    assertThat(dateTimeValue.getTime().getHour()).isEqualTo(9);
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
    DateTimeValue.from(null);
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
    assertThat(dateTimeValue.getTime().getHour()).isEqualTo(9);
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
}
