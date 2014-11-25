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

import java.sql.Date;
import java.text.ParseException;

import org.assertj.db.common.AbstractTest;
import org.junit.Test;

/**
 * Tests on the date value.
 * 
 * @author Régis Pouiller
 * 
 */
public class DateValue_Test extends AbstractTest {

  /**
   * This method tests the constructor with a {@code String}.
   */
  @Test
  public void test_contructor_with_date() throws ParseException {
    DateValue dateValue = new DateValue(Date.valueOf("2007-12-23"));
    assertThat(dateValue.getDayOfTheMonth()).isEqualTo(23);
    assertThat(dateValue.getMonth()).isEqualTo(12);
    assertThat(dateValue.getYear()).isEqualTo(2007);
  }

  /**
   * This method should throw a {@code NullPointerException} because passing a {@code null} parameter to constructor
   * with a {@code String}.
   */
  @Test(expected = NullPointerException.class)
  public void should_constructor_with_date_fail_if_date_is_null() throws ParseException {
    new DateValue((Date) null);
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
   * This method tests the {@code from} static method.
   */
  @Test
  public void test_from() throws ParseException {
    DateValue dateValue = DateValue.from(Date.valueOf("2002-07-26"));
    assertThat(dateValue.getDayOfTheMonth()).isEqualTo(26);
    assertThat(dateValue.getMonth()).isEqualTo(07);
    assertThat(dateValue.getYear()).isEqualTo(2002);
  }

  /**
   * This method should throw a {@code NullPointerException} because passing a {@code null} parameter to the
   * {@code from} static method.
   */
  @Test(expected = NullPointerException.class)
  public void should_from_fail_if_date_is_null() throws ParseException {
    DateValue.from(null);
  }

  /**
   * This method tests the {@code parse} static method.
   */
  @Test
  public void test_parse() throws ParseException {
    DateValue dateValue = DateValue.parse("2002-07-26");
    assertThat(dateValue.getDayOfTheMonth()).isEqualTo(26);
    assertThat(dateValue.getMonth()).isEqualTo(07);
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
    assertThat(dateValue.getMonth()).isEqualTo(07);
    assertThat(dateValue.getYear()).isEqualTo(2002);
  }

  /**
   * This method tests the {@code toString} method.
   */
  @Test
  public void test_toString() {
    assertThat(DateValue.of(2007, 12, 23).toString()).isEqualTo("2007-12-23");
    assertThat(DateValue.of(2007, 2, 3).toString()).isEqualTo("2007-02-03");
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
}
