package org.assertj.db.api;

import static org.assertj.db.api.Assertions.assertThat;

import java.text.ParseException;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.DateTimeValue;
import org.assertj.db.type.DateValue;
import org.assertj.db.type.Table;
import org.assertj.db.type.TimeValue;
import org.junit.Test;

/**
 * Tests on the methods which verifies if a value is before a date/time value.
 * 
 * @author Régis Pouiller
 * 
 */
public class ValueAssert_IsBefore_DateTimeValue_Test extends AbstractTest {

  /**
   * This method tests that the value is before a date/time.
   * 
   * @throws ParseException
   */
  @Test
  public void test_if_value_is_before_datetime() throws ParseException {
    Table table = new Table(source, "test");
    assertThat(table)
        .column("var10")
            .value()
                .isBefore(DateTimeValue.of(DateValue.of(2014, 5, 24), TimeValue.of(9, 46, 31)))
            .returnToRow()
            .value()
                .isBefore(DateTimeValue.parse("2014-05-30T12:29:50"));
  }

  /**
   * This method should fail because the value is not equal to the date/time value.
   */
  @Test(expected = AssertionError.class)
  public void should_fail_because_value_is_not_before() {
    Table table = new Table(source, "test");
    assertThat(table).column("var10").value()
        .isBefore(DateTimeValue.of(DateValue.of(2014, 5, 24), TimeValue.of(9, 46, 30)));
  }

  /**
   * This method should fail because the value is not a date/time.
   */
  @Test(expected = AssertionError.class)
  public void should_fail_because_value_is_not_a_datetime() {
    Table table = new Table(source, "test");
    assertThat(table).column("var1").value().as("var1")
        .isBefore(DateTimeValue.of(DateValue.of(2014, 5, 24), TimeValue.of(9, 46, 31)));
  }
}
