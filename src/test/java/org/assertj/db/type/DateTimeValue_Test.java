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
    DateTimeValue dateTimeValue = new DateTimeValue("2007-12-23 09:01");
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
    DateTimeValue dateTimeValue = new DateTimeValue("2007-12-23 09:01:06");
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
    DateTimeValue dateTimeValue = new DateTimeValue("2007-12-23 09:01:06.000000003");
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
  public void should_constructor_with_string_fail_if_date_have_bad_length() throws ParseException {
    new DateTimeValue("12345678901");
  }

  /**
   * This method should throw a {@code ParseException} because passing a parameter to constructor with a bad character
   * on year.
   */
  @Test(expected = ParseException.class)
  public void should_constructor_with_string_fail_if_date_have_bad_character_on_year() throws ParseException {
    new DateTimeValue("a007-12-23");
  }

  /**
   * This method should throw a {@code ParseException} because passing a parameter to constructor with a bad character
   * on separator.
   */
  @Test(expected = ParseException.class)
  public void should_constructor_with_string_fail_if_date_have_bad_character_on_separator() throws ParseException {
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
   * This method should throw a {@code NullPointerException} because passing a {@code null} parameter to constructor with a {@code TimeStamp}.
   */
  @Test(expected = NullPointerException.class)
  public void should_constructor_with_timestamp_fail_if_date_is_null() throws ParseException {
    new DateTimeValue((Timestamp) null);
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
    DateTimeValue dateTimeValue = DateTimeValue.parse("2007-12-23 09:01");
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
    DateTimeValue dateTimeValue = DateTimeValue.parse("2007-12-23 09:01:06");
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
    DateTimeValue dateTimeValue = DateTimeValue.parse("2007-12-23 09:01:06.000000003");
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
  public void should_parse_fail_if_date_have_bad_length() throws ParseException {
    DateTimeValue.parse("12345678901");
  }

  /**
   * This method should throw a {@code ParseException} because passing a parameter to {@code parse} static method with a
   * bad character on year.
   */
  @Test(expected = ParseException.class)
  public void should_parse_fail_if_date_have_bad_character_on_year() throws ParseException {
    DateTimeValue.parse("a007-12-23");
  }

  /**
   * This method should throw a {@code ParseException} because passing a parameter to {@code parse} static method with a
   * bad character on separator.
   */
  @Test(expected = ParseException.class)
  public void should_parse_fail_if_date_have_bad_character_on_separator() throws ParseException {
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
   * This method should throw a {@code NullPointerException} because passing a {@code null} parameter to {@code from} method.
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
    assertThat(DateTimeValue.of(DateValue.of(2007, 12, 23), 
        TimeValue.of(9, 1, 6, 3)).toString()).isEqualTo("2007-12-23 09:01:06.000000003");
    assertThat(DateTimeValue.of(DateValue.of(2007, 2, 3), 
        TimeValue.of(9, 1, 6, 3)).toString()).isEqualTo("2007-02-03 09:01:06.000000003");
  }

}
