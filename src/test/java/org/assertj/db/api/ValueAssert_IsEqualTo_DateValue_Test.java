package org.assertj.db.api;

import static org.assertj.db.api.Assertions.assertThat;

import java.text.ParseException;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.DateValue;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Tests on the methods which verifies if a value is equal to a date value.
 * 
 * @author Régis Pouiller
 * 
 */
public class ValueAssert_IsEqualTo_DateValue_Test extends AbstractTest {

  /**
   * This method tests that the value is equal to a date.
   * @throws ParseException 
   */
  @Test
  public void test_if_value_is_equal_to_date() throws ParseException {
    Table table = new Table(source, "test");
    assertThat(table).column("var9")
        .value().isEqualTo(DateValue.of(2014, 5, 24)).returnToColumn()
        .value().isEqualTo(DateValue.parse("2014-05-30"));
  }

  /**
   * This method should fail because the value is not equal to the date value.
   */
  @Test(expected = AssertionError.class)
  public void should_fail_because_value_is_not_equal() {
    Table table = new Table(source, "test");
    assertThat(table).column("var9")
        .value().isEqualTo(DateValue.of(2014, 5, 23));
  }

  /**
   * This method should fail because the value is not a date.
   */
  @Test(expected = AssertionError.class)
  public void should_fail_because_value_is_not_a_date() {
    Table table = new Table(source, "test");
    assertThat(table).column("var1")
        .value().as("var1").isEqualTo(DateValue.of(2014, 5, 23));
  }

  /**
   * This method tests that the date/time value is equal to a date.
   * @throws ParseException 
   */
  @Test
  public void test_if_datetime_value_is_equal_to_date() throws ParseException {
    Table table = new Table(source, "test");
    assertThat(table).column("var10")
        .value(2).isEqualTo(DateValue.parse("2014-05-30"));
  }

  /**
   * This method should fail because the date/time value is not equal to the date value.
   * @throws ParseException 
   */
  @Test(expected = AssertionError.class)
  public void should_fail_because_datetime_value_is_not_equal() throws ParseException {
    Table table = new Table(source, "test");
    assertThat(table).column("var10")
        .value(2).isEqualTo(DateValue.parse("2014-05-31"));
  }

}
