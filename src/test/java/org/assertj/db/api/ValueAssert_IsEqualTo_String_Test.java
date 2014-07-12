package org.assertj.db.api;

import static org.assertj.db.api.Assertions.assertThat;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.error.AssertJDBException;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Tests on the methods which verifies if a value is equal to a string.
 * 
 * @author Régis Pouiller
 * 
 */
public class ValueAssert_IsEqualTo_String_Test extends AbstractTest {

  /**
   * This method tests that the value is equal to a string.
   */
  @Test
  public void test_if_value_is_equal_to_string() {
    Table table = new Table(source, "test");
    assertThat(table)
        .column("var12")
            .value().isEqualTo("text").returnToRow()
            .value().isEqualTo("another text").returnToRow()
        .returnToTable()
        .column("var1")
            .value().isEqualTo("1").returnToRow()
            .value().isEqualTo("10").returnToRow()
        .returnToTable()
        .column("var3")
            .value().isEqualTo("2").returnToRow()
            .value().isEqualTo("20").returnToRow()
        .returnToTable()
        .column()
            .value().isEqualTo("3").returnToRow()
            .value().isEqualTo("30").returnToRow()
        .returnToTable()
        .column()
            .value().isEqualTo("4").returnToRow()
            .value().isEqualTo("40").returnToRow()
        .returnToTable()
        .column()
            .value().isEqualTo("5.6").returnToRow()
            .value().isEqualTo("50.6").returnToRow()
        .returnToTable()
        .column("var7")
            .value().isEqualTo("7.8").returnToRow()
            .value().isEqualTo("70.8").returnToRow()
        .returnToTable()
        .column("var13")
            .value().isEqualTo("5").returnToRow()
            .value().isEqualTo("50").returnToRow()
        .returnToTable()
        .column("var14")
            .value().isEqualTo("7").returnToRow()
            .value().isEqualTo("70").returnToRow()
        .returnToTable()
        .column("var8")
            .value().isEqualTo("09:46:30").returnToRow()
            .value().isEqualTo("12:29:49").returnToRow()
            .value().isEqualTo("12:29:49").returnToRow()
        .returnToTable()
        .column("var9")
            .value().isEqualTo("2014-05-24").returnToRow()
            .value().isEqualTo("2014-05-30").returnToRow()
            .value().isEqualTo("2014-05-30").returnToRow()
        .returnToTable()
        .column("var9")
            .value().isEqualTo("2014-05-24T00:00").returnToRow()
            .value().isEqualTo("2014-05-30T00:00").returnToRow()
            .value().isEqualTo("2014-05-30T00:00").returnToRow()
            .returnToTable()
        .column("var9")
            .value().isEqualTo("2014-05-24T00:00:00").returnToRow()
            .value().isEqualTo("2014-05-30T00:00:00").returnToRow()
            .value().isEqualTo("2014-05-30T00:00:00").returnToRow()
        .returnToTable()
        .column("var9")
            .value().isEqualTo("2014-05-24T00:00:00.000000000").returnToRow()
            .value().isEqualTo("2014-05-30T00:00:00.000000000").returnToRow()
            .value().isEqualTo("2014-05-30T00:00:00.000000000").returnToRow()
        .returnToTable()
        .column("var10")
            .value().isEqualTo("2014-05-24T09:46:30.000000000").returnToRow()
            .value().isEqualTo("2014-05-30T12:29:49.000000000").returnToRow()
            .value().isEqualTo("2014-05-30T00:00:00").returnToRow()
        .returnToTable()
        .column("var10")
            .value().isEqualTo("2014-05-24T09:46:30").returnToRow()
            .value().isEqualTo("2014-05-30T12:29:49").returnToRow()
            .value().isEqualTo("2014-05-30").returnToRow()
        .returnToTable()
        .column("var10")
            .value().isEqualTo("2014-05-24T09:46:30").returnToRow()
            .value().isEqualTo("2014-05-30T12:29:49").returnToRow()
            .value().isEqualTo("2014-05-30T00:00").returnToRow();
  }

  /**
   * This method should fail because the value is not equal to the string.
   */
  @Test(expected = AssertionError.class)
  public void should_fail_because_value_is_not_equal_to_string() {
    Table table = new Table(source, "test");
    assertThat(table).column("var12")
        .value().isEqualTo("Text");
  }

  /**
   * This method should fail because the value is not equal to the number.
   */
  @Test(expected = AssertionError.class)
  public void should_fail_because_value_is_not_equal_to_number() {
    Table table = new Table(source, "test");
    assertThat(table).column("var1")
        .value().isEqualTo("2");
  }

  /**
   * This method should fail because the value is not equal to the time.
   */
  @Test(expected = AssertionError.class)
  public void should_fail_because_value_is_not_equal_to_time() {
    Table table = new Table(source, "test");
    assertThat(table).column("var8")
        .value().isEqualTo("09:46:31");
  }

  /**
   * This method should fail because the value is not equal to the date.
   */
  @Test(expected = AssertionError.class)
  public void should_fail_because_value_is_not_equal_to_date() {
    Table table = new Table(source, "test");
    assertThat(table).column("var9")
        .value().isEqualTo("2014-05-25");
  }

  /**
   * This method should fail because the value is not equal to the date/time.
   */
  @Test(expected = AssertionError.class)
  public void should_fail_because_value_is_not_equal_to_datetime() {
    Table table = new Table(source, "test");
    assertThat(table).column("var10")
        .value().isEqualTo("2014-05-24T09:46:31");
  }

  /**
   * This method should fail because it is not possible to compare.
   */
  @Test(expected = AssertJDBException.class)
  public void should_fail_because_it_is_not_possible_to_compare() {
    Table table = new Table(source, "test");
    assertThat(table).column("var1")
        .value().isEqualTo("***");
  }

  /**
   * This method should fail because the value is not a text.
   */
  @Test(expected = AssertionError.class)
  public void should_fail_because_value_is_not_a_text() {
    Table table = new Table(source, "test");
    assertThat(table).column("var2")
        .value().as("var2").isEqualTo("Text");
  }

}
