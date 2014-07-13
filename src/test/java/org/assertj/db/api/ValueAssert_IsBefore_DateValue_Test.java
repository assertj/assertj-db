package org.assertj.db.api;

import static org.assertj.db.api.Assertions.assertThat;

import java.text.ParseException;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.DateValue;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Tests on the methods which verifies if a value is before a date value.
 * 
 * @author Régis Pouiller
 * 
 */
public class ValueAssert_IsBefore_DateValue_Test extends AbstractTest {

  /**
   * This method tests that the value is before a date.
   * @throws ParseException 
   */
  @Test
  public void test_if_value_is_before_date() throws ParseException {
    Table table = new Table(source, "test");
    assertThat(table).column("var9")
        .value().isBefore(DateValue.of(2014, 5, 25)).returnToRow()
        .value().isBefore(DateValue.parse("2014-05-31"));
  }

  /**
   * This method should fail because the value is not before the date value.
   */
  @Test(expected = AssertionError.class)
  public void should_fail_because_value_is_not_before() {
    Table table = new Table(source, "test");
    assertThat(table).column("var9")
        .value().isBefore(DateValue.of(2014, 5, 24));
  }

  /**
   * This method should fail because the value is not a date.
   */
  @Test(expected = AssertionError.class)
  public void should_fail_because_value_is_not_a_date() {
    Table table = new Table(source, "test");
    assertThat(table).column("var1")
        .value().as("var1").isBefore(DateValue.of(2014, 5, 25));
  }

  /**
   * This method tests that the date/time value is before to a date.
   * @throws ParseException 
   */
  @Test
  public void test_if_datetime_value_is_before_date() throws ParseException {
    Table table = new Table(source, "test");
    assertThat(table).column("var10")
        .value(2).isBefore(DateValue.parse("2014-05-31"));
  }

  /**
   * This method should fail because the date/time value is not before the date value.
   * @throws ParseException 
   */
  @Test(expected = AssertionError.class)
  public void should_fail_because_datetime_value_is_not_before() throws ParseException {
    Table table = new Table(source, "test");
    assertThat(table).column("var10")
        .value(2).isBefore(DateValue.parse("2014-05-30"));
  }

}
