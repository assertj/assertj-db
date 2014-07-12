package org.assertj.db.api;

import static org.assertj.db.api.Assertions.assertThat;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.error.AssertJDBException;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Tests on the methods which verifies if a value is not equal to a string.
 * 
 * @author Régis Pouiller
 * 
 */
public class ValueAssert_IsNotEqualTo_String_Test extends AbstractTest {

  /**
   * This method tests that the value is not equal to a string.
   */
  @Test
  public void test_if_value_is_not_equal_to_string() {
    Table table = new Table(source, "test");
    assertThat(table)
        .column("var12")
            .value().isNotEqualTo("Text").returnToRow()
            .value().isNotEqualTo("Another text").returnToRow()
        .returnToTable()
        .column("var1")
            .value().isNotEqualTo("2").returnToRow()
            .value().isNotEqualTo("11").returnToRow()
        .returnToTable()
        .column("var3")
            .value().isNotEqualTo("3").returnToRow()
            .value().isNotEqualTo("21").returnToRow()
        .returnToTable()
        .column()
            .value().isNotEqualTo("4").returnToRow()
            .value().isNotEqualTo("31").returnToRow()
        .returnToTable()
        .column()
            .value().isNotEqualTo("5").returnToRow()
            .value().isNotEqualTo("41").returnToRow()
        .returnToTable()
        .column()
            .value().isNotEqualTo("6.6").returnToRow()
            .value().isNotEqualTo("51.6").returnToRow()
        .returnToTable()
        .column("var7")
            .value().isNotEqualTo("8.8").returnToRow()
            .value().isNotEqualTo("71.8").returnToRow()
        .returnToTable()
        .column("var13")
            .value().isNotEqualTo("6").returnToRow()
            .value().isNotEqualTo("51").returnToRow()
        .returnToTable()
        .column("var14")
            .value().isNotEqualTo("8").returnToRow()
            .value().isNotEqualTo("71").returnToRow()
        .returnToTable()
        .column("var8")
            .value().isNotEqualTo("09:46:31").returnToRow()
            .value().isNotEqualTo("12:29:50").returnToRow()
            .value().isNotEqualTo("12:29:50").returnToRow()
        .returnToTable()
        .column("var9")
            .value().isNotEqualTo("2014-05-25").returnToRow()
            .value().isNotEqualTo("2014-05-31").returnToRow()
            .value().isNotEqualTo("2014-05-31").returnToRow()
        .returnToTable()
        .column("var9")
            .value().isNotEqualTo("2014-05-24T00:01").returnToRow()
            .value().isNotEqualTo("2014-05-30T00:01").returnToRow()
            .value().isNotEqualTo("2014-05-30T00:01").returnToRow()
            .returnToTable()
        .column("var9")
            .value().isNotEqualTo("2014-05-24T00:00:01").returnToRow()
            .value().isNotEqualTo("2014-05-30T00:00:01").returnToRow()
            .value().isNotEqualTo("2014-05-30T00:00:01").returnToRow()
        .returnToTable()
        .column("var9")
            .value().isNotEqualTo("2014-05-24T00:00:00.000000001").returnToRow()
            .value().isNotEqualTo("2014-05-30T00:00:00.000000001").returnToRow()
            .value().isNotEqualTo("2014-05-30T00:00:00.000000001").returnToRow()
        .returnToTable()
        .column("var10")
            .value().isNotEqualTo("2014-05-24T09:46:30.000000001").returnToRow()
            .value().isNotEqualTo("2014-05-30T12:29:49.000000001").returnToRow()
            .value().isNotEqualTo("2014-05-30T00:00:01").returnToRow()
        .returnToTable()
        .column("var10")
            .value().isNotEqualTo("2014-05-24T09:46:31").returnToRow()
            .value().isNotEqualTo("2014-05-30T12:29:50").returnToRow()
            .value().isNotEqualTo("2014-05-31").returnToRow()
        .returnToTable()
        .column("var10")
            .value().isNotEqualTo("2014-05-24T09:46:31").returnToRow()
            .value().isNotEqualTo("2014-05-30T12:29:50").returnToRow()
            .value().isNotEqualTo("2014-05-30T00:01").returnToRow();
  }

  /**
   * This method should fail because the value is equal to the string.
   */
  @Test(expected = AssertionError.class)
  public void should_fail_because_value_is_equal_to_string() {
    Table table = new Table(source, "test");
    assertThat(table).column("var12")
        .value().isNotEqualTo("text");
  }

  /**
   * This method should fail because the value is equal to the number.
   */
  @Test(expected = AssertionError.class)
  public void should_fail_because_value_is_equal_to_number() {
    Table table = new Table(source, "test");
    assertThat(table).column("var1")
        .value().isNotEqualTo("1");
  }

  /**
   * This method should fail because the value is equal to the time.
   */
  @Test(expected = AssertionError.class)
  public void should_fail_because_value_is_equal_to_time() {
    Table table = new Table(source, "test");
    assertThat(table).column("var8")
        .value().isNotEqualTo("09:46:30");
  }

  /**
   * This method should fail because the value is equal to the date.
   */
  @Test(expected = AssertionError.class)
  public void should_fail_because_value_is_equal_to_date() {
    Table table = new Table(source, "test");
    assertThat(table).column("var9")
        .value().isNotEqualTo("2014-05-24");
  }

  /**
   * This method should fail because the value is equal to the date/time.
   */
  @Test(expected = AssertionError.class)
  public void should_fail_because_value_is_equal_to_datetime() {
    Table table = new Table(source, "test");
    assertThat(table).column("var10")
        .value().isNotEqualTo("2014-05-24T09:46:30");
  }

  /**
   * This method should fail because it is not possible to compare.
   */
  @Test(expected = AssertJDBException.class)
  public void should_fail_because_it_is_not_possible_to_compare() {
    Table table = new Table(source, "test");
    assertThat(table).column("var1")
        .value().isNotEqualTo("***");
  }

  /**
   * This method should fail because the value is not a text.
   */
  @Test(expected = AssertionError.class)
  public void should_fail_because_value_is_not_a_text() {
    Table table = new Table(source, "test");
    assertThat(table).column("var2")
        .value().as("var2").isNotEqualTo("Text");
  }

}
