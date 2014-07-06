package org.assertj.db.type;

import static org.assertj.core.api.Assertions.assertThat;

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
    new DateValue(null);
  }

  /**
   * This method should throw a {@code ParseException} because passing a parameter to constructor with a bad length.
   */
  @Test(expected = ParseException.class)
  public void should_constructor_with_string_fail_if_date_have_bad_length() throws ParseException {
    new DateValue("12345678901");
  }

  /**
   * This method should throw a {@code ParseException} because passing a parameter to constructor with a bad character
   * on year.
   */
  @Test(expected = ParseException.class)
  public void should_constructor_with_string_fail_if_date_have_bad_character_on_year() throws ParseException {
    new DateValue("a007-12-23");
  }

  /**
   * This method should throw a {@code ParseException} because passing a parameter to constructor with a bad character
   * on separator.
   */
  @Test(expected = ParseException.class)
  public void should_constructor_with_string_fail_if_date_have_bad_character_on_separator() throws ParseException {
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
  public void should_parse_fail_if_date_have_bad_length() throws ParseException {
    DateValue.parse("12345678901");
  }

  /**
   * This method should throw a {@code ParseException} because passing a parameter to {@code parse} static method with a
   * bad character on year.
   */
  @Test(expected = ParseException.class)
  public void should_parse_fail_if_date_have_bad_character_on_year() throws ParseException {
    DateValue.parse("a007-12-23");
  }

  /**
   * This method should throw a {@code ParseException} because passing a parameter to {@code parse} static method with a
   * bad character on separator.
   */
  @Test(expected = ParseException.class)
  public void should_parse_fail_if_date_have_bad_character_on_separator() throws ParseException {
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
}
