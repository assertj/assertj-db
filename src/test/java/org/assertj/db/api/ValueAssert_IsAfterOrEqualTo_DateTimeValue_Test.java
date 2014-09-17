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
 * Tests on the methods which verifies if a value is after a date/time value.
 * 
 * @author Régis Pouiller
 * 
 */
public class ValueAssert_IsAfterOrEqualTo_DateTimeValue_Test extends AbstractTest {

  /**
   * This method tests that the value is after or equal to a date/time.
   * 
   * @throws ParseException
   */
  @Test
  public void test_if_value_is_after_or_equal_to_datetime() throws ParseException {
    Table table = new Table(source, "test");
    assertThat(table)
        .column("var10")
            .value()
                .isAfterOrEqualTo(DateTimeValue.of(DateValue.of(2014, 5, 24), TimeValue.of(9, 46, 29)))
            .returnToColumn()
            .value()
                .isAfterOrEqualTo(DateTimeValue.parse("2014-05-30T12:29:48"))
            .returnToColumn()
        .returnToTable()
        .column("var9")
            .value()
                .isAfterOrEqualTo(DateTimeValue.parse("2014-05-23T23:59:59"))
            .returnToColumn()
            .value()
                .isAfterOrEqualTo(DateTimeValue.of(DateValue.of(2014, 5, 29), TimeValue.of(23, 59, 59)))
        .column("var10")
            .value()
                .isAfterOrEqualTo(DateTimeValue.of(DateValue.of(2014, 5, 24), TimeValue.of(9, 46, 30)))
            .value()
                .isAfterOrEqualTo(DateTimeValue.parse("2014-05-30T12:29:49"));
  }

  /**
   * This method should fail because the value is not after the date/time value.
   */
  @Test(expected = AssertionError.class)
  public void should_fail_because_value_is_not_after_or_equal() {
    Table table = new Table(source, "test");
    assertThat(table).column("var10").value()
        .isAfterOrEqualTo(DateTimeValue.of(DateValue.of(2014, 5, 24), TimeValue.of(9, 46, 31)));
  }

  /**
   * This method should fail because the value is not a date/time.
   */
  @Test(expected = AssertionError.class)
  public void should_fail_because_value_is_not_a_datetime() {
    Table table = new Table(source, "test");
    assertThat(table).column("var1").value().as("var1")
        .isAfterOrEqualTo(DateTimeValue.of(DateValue.of(2014, 5, 24), TimeValue.of(9, 46, 29)));
  }
}
