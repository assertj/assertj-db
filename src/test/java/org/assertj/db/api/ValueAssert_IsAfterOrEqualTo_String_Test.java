package org.assertj.db.api;

import static org.assertj.db.api.Assertions.assertThat;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.error.AssertJDBException;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Tests on the methods which verifies if a value is after a string.
 * 
 * @author Régis Pouiller
 * 
 */
public class ValueAssert_IsAfterOrEqualTo_String_Test extends AbstractTest {

  /**
   * This method tests that the value is after or equal to a string.
   */
  @Test
  public void test_if_value_is_after_or_equal_to_string() {
    Table table = new Table(source, "test");
    assertThat(table)
        .column("var10")
            .value()
                .isAfterOrEqualTo("2014-05-24T09:46:29")
            .returnToColumn()
            .value()
                .isAfterOrEqualTo("2014-05-30T12:29:48")
            .returnToColumn()
        .returnToTable()
        .column("var10")
            .value()
                .isAfterOrEqualTo("2014-05-24")
            .returnToColumn()
            .value()
                .isAfterOrEqualTo("2014-05-30")
            .returnToColumn()
        .returnToTable()
        .column("var9")
            .value()
                .isAfterOrEqualTo("2014-05-23T23:59:59")
            .returnToColumn()
            .value()
                .isAfterOrEqualTo("2014-05-29T23:59:59")
            .returnToColumn()
        .returnToTable()
        .column("var9")
            .value()
                .isAfterOrEqualTo("2014-05-23")
            .returnToColumn()
            .value()
                .isAfterOrEqualTo("2014-05-29")
            .returnToColumn()
        .returnToTable()
        .column("var8")
            .value()
                .isAfterOrEqualTo("09:46:29")
            .returnToColumn()
            .value()
                .isAfterOrEqualTo("12:29:48")

        .column("var8")
            .value().isAfterOrEqualTo("09:46:30").returnToColumn()
            .value().isAfterOrEqualTo("12:29:49").returnToColumn()
            .value().isAfterOrEqualTo("12:29:49").returnToColumn()
        .returnToTable()
        .column("var9")
            .value().isAfterOrEqualTo("2014-05-24").returnToColumn()
            .value().isAfterOrEqualTo("2014-05-30").returnToColumn()
            .value().isAfterOrEqualTo("2014-05-30").returnToColumn()
        .returnToTable()
        .column("var9")
            .value().isAfterOrEqualTo("2014-05-24T00:00").returnToColumn()
            .value().isAfterOrEqualTo("2014-05-30T00:00").returnToColumn()
            .value().isAfterOrEqualTo("2014-05-30T00:00").returnToColumn()
            .returnToTable()
        .column("var9")
            .value().isAfterOrEqualTo("2014-05-24T00:00:00").returnToColumn()
            .value().isAfterOrEqualTo("2014-05-30T00:00:00").returnToColumn()
            .value().isAfterOrEqualTo("2014-05-30T00:00:00").returnToColumn()
        .returnToTable()
        .column("var9")
            .value().isAfterOrEqualTo("2014-05-24T00:00:00.000000000").returnToColumn()
            .value().isAfterOrEqualTo("2014-05-30T00:00:00.000000000").returnToColumn()
            .value().isAfterOrEqualTo("2014-05-30T00:00:00.000000000").returnToColumn()
        .returnToTable()
        .column("var10")
            .value().isAfterOrEqualTo("2014-05-24T09:46:30.000000000").returnToColumn()
            .value().isAfterOrEqualTo("2014-05-30T12:29:49.000000000").returnToColumn()
            .value().isAfterOrEqualTo("2014-05-30T00:00:00").returnToColumn()
        .returnToTable()
        .column("var10")
            .value().isAfterOrEqualTo("2014-05-24T09:46:30").returnToColumn()
            .value().isAfterOrEqualTo("2014-05-30T12:29:49").returnToColumn()
            .value().isAfterOrEqualTo("2014-05-30").returnToColumn()
        .returnToTable()
        .column("var10")
            .value().isAfterOrEqualTo("2014-05-24T09:46:30").returnToColumn()
            .value().isAfterOrEqualTo("2014-05-30T12:29:49").returnToColumn()
            .value().isAfterOrEqualTo("2014-05-30T00:00").returnToColumn();

  }

  /**
   * This method should fail because the value is not after or equal to the string.
   */
  @Test(expected = AssertionError.class)
  public void should_fail_because_time_value_is_not_after_or_equal_to_time() {
    Table table = new Table(source, "test");
    assertThat(table).column("var8").value()
        .isAfterOrEqualTo("09:46:31");
  }

  /**
   * This method should fail because the value is not after or equal to the string.
   */
  @Test(expected = AssertionError.class)
  public void should_fail_because_date_value_is_not_after_or_equal_to_date() {
    Table table = new Table(source, "test");
    assertThat(table).column("var9").value()
        .isAfterOrEqualTo("2014-05-25");
  }

  /**
   * This method should fail because the value is not after or equal to the string.
   */
  @Test(expected = AssertionError.class)
  public void should_fail_because_date_value_is_not_after_or_equal_to_datetime() {
    Table table = new Table(source, "test");
    assertThat(table).column("var9").value()
        .isAfterOrEqualTo("2014-05-25T00:00");
  }

  /**
   * This method should fail because the value is not after or equal to the string.
   */
  @Test(expected = AssertionError.class)
  public void should_fail_because_datetime_value_is_not_after_or_equal_to_date() {
    Table table = new Table(source, "test");
    assertThat(table).column("var10").value(2)
        .isAfterOrEqualTo("2014-05-31");
  }

  /**
   * This method should fail because the value is not after or equal to the string.
   */
  @Test(expected = AssertionError.class)
  public void should_fail_because_datetime_value_is_not_after_or_equal_to_datetime() {
    Table table = new Table(source, "test");
    assertThat(table).column("var10").value()
        .isAfterOrEqualTo("2014-05-24T09:46:31");
  }

  /**
   * This method should fail because the value is not a date/time.
   */
  @Test(expected = AssertionError.class)
  public void should_fail_because_value_is_not_a_datetime() {
    Table table = new Table(source, "test");
    assertThat(table).column("var1").value().as("var1")
        .isAfterOrEqualTo("2014-05-24T09:46:30");
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
                .isAfterOrEqualTo("a014-05-24");
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
                .isAfterOrEqualTo("a9:46:29");
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
                .isAfterOrEqualTo("a014-05-24T09:46:29");
  }
}
