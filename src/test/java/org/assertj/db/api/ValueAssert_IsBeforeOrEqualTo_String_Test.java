package org.assertj.db.api;

import static org.assertj.db.api.Assertions.assertThat;

import java.text.ParseException;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.error.AssertJDBException;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Tests on the methods which verifies if a value is before a string.
 * 
 * @author Régis Pouiller
 * 
 */
public class ValueAssert_IsBeforeOrEqualTo_String_Test extends AbstractTest {

  /**
   * This method tests that the value is before or equal to a string.
   * 
   * @throws ParseException
   */
  @Test
  public void test_if_value_is_before_or_equal_to_string() throws ParseException {
    Table table = new Table(source, "test");
    assertThat(table)
        .column("var10")
            .value()
                .isBeforeOrEqualTo("2014-05-24T09:46:31")
            .returnToColumn()
            .value()
                .isBeforeOrEqualTo("2014-05-30T12:29:50")
            .returnToColumn()
        .returnToTable()
        .column("var10")
            .value()
                .isBeforeOrEqualTo("2014-05-25")
            .returnToColumn()
            .value()
                .isBeforeOrEqualTo("2014-05-31")
            .returnToColumn()
        .returnToTable()
        .column("var9")
            .value()
                .isBeforeOrEqualTo("2014-05-24T00:01")
            .returnToColumn()
            .value()
                .isBeforeOrEqualTo("2014-05-30T00:01")
            .returnToColumn()
        .returnToTable()
        .column("var9")
            .value()
                .isBeforeOrEqualTo("2014-05-25")
            .returnToColumn()
            .value()
                .isBeforeOrEqualTo("2014-05-31")
            .returnToColumn()
        .returnToTable()
        .column("var8")
            .value()
                .isBeforeOrEqualTo("09:46:31")
            .returnToColumn()
            .value()
                .isBeforeOrEqualTo("12:29:50")
        .column("var8")
            .value().isBeforeOrEqualTo("09:46:30").returnToColumn()
            .value().isBeforeOrEqualTo("12:29:49").returnToColumn()
            .value().isBeforeOrEqualTo("12:29:49").returnToColumn()
        .column("var9")
            .value().isBeforeOrEqualTo("2014-05-24").returnToColumn()
            .value().isBeforeOrEqualTo("2014-05-30").returnToColumn()
            .value().isBeforeOrEqualTo("2014-05-30").returnToColumn()
        .column("var9")
            .value().isBeforeOrEqualTo("2014-05-24T00:00").returnToColumn()
            .value().isBeforeOrEqualTo("2014-05-30T00:00").returnToColumn()
            .value().isBeforeOrEqualTo("2014-05-30T00:00").returnToColumn()
        .column("var9")
            .value().isBeforeOrEqualTo("2014-05-24T00:00:00").returnToColumn()
            .value().isBeforeOrEqualTo("2014-05-30T00:00:00").returnToColumn()
            .value().isBeforeOrEqualTo("2014-05-30T00:00:00").returnToColumn()
        .column("var9")
            .value().isBeforeOrEqualTo("2014-05-24T00:00:00.000000000").returnToColumn()
            .value().isBeforeOrEqualTo("2014-05-30T00:00:00.000000000").returnToColumn()
            .value().isBeforeOrEqualTo("2014-05-30T00:00:00.000000000").returnToColumn()
        .column("var10")
            .value().isBeforeOrEqualTo("2014-05-24T09:46:30.000000000").returnToColumn()
            .value().isBeforeOrEqualTo("2014-05-30T12:29:49.000000000").returnToColumn()
            .value().isBeforeOrEqualTo("2014-05-30T00:00:00").returnToColumn()
        .column("var10")
            .value().isBeforeOrEqualTo("2014-05-24T09:46:30").returnToColumn()
            .value().isBeforeOrEqualTo("2014-05-30T12:29:49").returnToColumn()
            .value().isBeforeOrEqualTo("2014-05-30").returnToColumn()
        .column("var10")
            .value().isBeforeOrEqualTo("2014-05-24T09:46:30").returnToColumn()
            .value().isBeforeOrEqualTo("2014-05-30T12:29:49").returnToColumn()
            .value().isBeforeOrEqualTo("2014-05-30T00:00").returnToColumn();
  }

  /**
   * This method should fail because the value is not before or equal to the string.
   */
  @Test(expected = AssertionError.class)
  public void should_fail_because_time_value_is_not_before_or_equal_to_time() {
    Table table = new Table(source, "test");
    assertThat(table).column("var8").value()
        .isBeforeOrEqualTo("09:46:29");
  }

  /**
   * This method should fail because the value is not before or equal to the string.
   */
  @Test(expected = AssertionError.class)
  public void should_fail_because_date_value_is_not_before_or_equal_to_date() {
    Table table = new Table(source, "test");
    assertThat(table).column("var9").value()
        .isBeforeOrEqualTo("2014-05-23");
  }

  /**
   * This method should fail because the value is not before or equal to the string.
   */
  @Test(expected = AssertionError.class)
  public void should_fail_because_date_value_is_not_before_or_equal_to_datetime() {
    Table table = new Table(source, "test");
    assertThat(table).column("var9").value()
        .isBeforeOrEqualTo("2014-05-23T00:00");
  }

  /**
   * This method should fail because the value is not before or equal to the string.
   */
  @Test(expected = AssertionError.class)
  public void should_fail_because_datetime_value_is_not_before_or_equal_to_date() {
    Table table = new Table(source, "test");
    assertThat(table).column("var10").value(2)
        .isBeforeOrEqualTo("2014-05-29");
  }

  /**
   * This method should fail because the value is not before or equal to the string.
   */
  @Test(expected = AssertionError.class)
  public void should_fail_because_datetime_value_is_not_before_or_equal_to_datetime() {
    Table table = new Table(source, "test");
    assertThat(table).column("var10").value()
        .isBeforeOrEqualTo("2014-05-24T09:46:29");
  }

  /**
   * This method should fail because the value is not a date/time.
   */
  @Test(expected = AssertionError.class)
  public void should_fail_because_value_is_not_a_datetime() {
    Table table = new Table(source, "test");
    assertThat(table).column("var1").value().as("var1")
        .isBeforeOrEqualTo("2014-05-24T09:46:31");
  }

  /**
   * This method should fail because the string is not correct to compare with date.
   */
  @Test(expected = AssertJDBException.class)
  public void should_fail_because_string_is_not_correct_to_compare_with_date() {
    Table table = new Table(source, "test");
    assertThat(table)
        .column("var9")
            .value()
                .isBeforeOrEqualTo("a014-05-25");
  }

  /**
   * This method should fail because the string is not correct to compare with time.
   */
  @Test(expected = AssertJDBException.class)
  public void should_fail_because_string_is_not_correct_to_compare_with_time() {
    Table table = new Table(source, "test");
    assertThat(table)
        .column("var8")
            .value()
                .isBeforeOrEqualTo("a9:46:31");
  }

  /**
   * This method should fail because the string is not correct to compare with date/time.
   */
  @Test(expected = AssertJDBException.class)
  public void should_fail_because_string_is_not_correct_to_compare_with_datetime() {
    Table table = new Table(source, "test");
    assertThat(table)
        .column("var10")
            .value()
                .isBeforeOrEqualTo("a014-05-24T09:46:31");
  }
}
