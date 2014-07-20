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
public class ValueAssert_IsBefore_String_Test extends AbstractTest {

  /**
   * This method tests that the value is before a string.
   * 
   * @throws ParseException
   */
  @Test
  public void test_if_value_is_before_string() throws ParseException {
    Table table = new Table(source, "test");
    assertThat(table)
        .column("var10")
            .value()
                .isBefore("2014-05-24T09:46:31")
            .returnToColumn()
            .value()
                .isBefore("2014-05-30T12:29:50")
            .returnToColumn()
        .returnToTable()
        .column("var10")
            .value()
                .isBefore("2014-05-25")
            .returnToColumn()
            .value()
                .isBefore("2014-05-31")
            .returnToColumn()
        .returnToTable()
        .column("var9")
            .value()
                .isBefore("2014-05-24T00:01")
            .returnToColumn()
            .value()
                .isBefore("2014-05-30T00:01")
            .returnToColumn()
        .returnToTable()
        .column("var9")
            .value()
                .isBefore("2014-05-25")
            .returnToColumn()
            .value()
                .isBefore("2014-05-31")
            .returnToColumn()
        .returnToTable()
        .column("var8")
            .value()
                .isBefore("09:46:31")
            .returnToColumn()
            .value()
                .isBefore("12:29:50");
  }

  /**
   * This method should fail because the value is not before the string.
   */
  @Test(expected = AssertionError.class)
  public void should_fail_because_time_value_is_not_before_time() {
    Table table = new Table(source, "test");
    assertThat(table).column("var8").value()
        .isBefore("09:46:30");
  }

  /**
   * This method should fail because the value is not before the string.
   */
  @Test(expected = AssertionError.class)
  public void should_fail_because_date_value_is_not_before_date() {
    Table table = new Table(source, "test");
    assertThat(table).column("var9").value()
        .isBefore("2014-05-24");
  }

  /**
   * This method should fail because the value is not before the string.
   */
  @Test(expected = AssertionError.class)
  public void should_fail_because_date_value_is_not_before_datetime() {
    Table table = new Table(source, "test");
    assertThat(table).column("var9").value()
        .isBefore("2014-05-24T00:00");
  }

  /**
   * This method should fail because the value is not before the string.
   */
  @Test(expected = AssertionError.class)
  public void should_fail_because_datetime_value_is_not_before_date() {
    Table table = new Table(source, "test");
    assertThat(table).column("var10").value(2)
        .isBefore("2014-05-30");
  }

  /**
   * This method should fail because the value is not before the string.
   */
  @Test(expected = AssertionError.class)
  public void should_fail_because_datetime_value_is_not_before_datetime() {
    Table table = new Table(source, "test");
    assertThat(table).column("var10").value()
        .isBefore("2014-05-24T09:46:30");
  }

  /**
   * This method should fail because the value is not a date/time.
   */
  @Test(expected = AssertionError.class)
  public void should_fail_because_value_is_not_a_datetime() {
    Table table = new Table(source, "test");
    assertThat(table).column("var1").value().as("var1")
        .isBefore("2014-05-24T09:46:31");
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
                .isBefore("a014-05-25");
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
                .isBefore("a9:46:31");
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
                .isBefore("a014-05-24T09:46:31");
  }
}
