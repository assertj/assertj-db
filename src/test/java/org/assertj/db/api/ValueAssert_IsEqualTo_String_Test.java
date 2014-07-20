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
            .value().isEqualTo("text").returnToColumn()
            .value().isEqualTo("another text").returnToColumn()
        .returnToTable()
        .column("var1")
            .value().isEqualTo("1").returnToColumn()
            .value().isEqualTo("10").returnToColumn()
        .returnToTable()
        .column("var3")
            .value().isEqualTo("2").returnToColumn()
            .value().isEqualTo("20").returnToColumn()
        .returnToTable()
        .column()
            .value().isEqualTo("3").returnToColumn()
            .value().isEqualTo("30").returnToColumn()
        .returnToTable()
        .column()
            .value().isEqualTo("4").returnToColumn()
            .value().isEqualTo("40").returnToColumn()
        .returnToTable()
        .column()
            .value().isEqualTo("5.6").returnToColumn()
            .value().isEqualTo("50.6").returnToColumn()
        .returnToTable()
        .column("var7")
            .value().isEqualTo("7.8").returnToColumn()
            .value().isEqualTo("70.8").returnToColumn()
        .returnToTable()
        .column("var13")
            .value().isEqualTo("5").returnToColumn()
            .value().isEqualTo("50").returnToColumn()
        .returnToTable()
        .column("var14")
            .value().isEqualTo("7").returnToColumn()
            .value().isEqualTo("70").returnToColumn()
        .returnToTable()
        .column("var8")
            .value().isEqualTo("09:46:30").returnToColumn()
            .value().isEqualTo("12:29:49").returnToColumn()
            .value().isEqualTo("12:29:49").returnToColumn()
        .returnToTable()
        .column("var9")
            .value().isEqualTo("2014-05-24").returnToColumn()
            .value().isEqualTo("2014-05-30").returnToColumn()
            .value().isEqualTo("2014-05-30").returnToColumn()
        .returnToTable()
        .column("var9")
            .value().isEqualTo("2014-05-24T00:00").returnToColumn()
            .value().isEqualTo("2014-05-30T00:00").returnToColumn()
            .value().isEqualTo("2014-05-30T00:00").returnToColumn()
            .returnToTable()
        .column("var9")
            .value().isEqualTo("2014-05-24T00:00:00").returnToColumn()
            .value().isEqualTo("2014-05-30T00:00:00").returnToColumn()
            .value().isEqualTo("2014-05-30T00:00:00").returnToColumn()
        .returnToTable()
        .column("var9")
            .value().isEqualTo("2014-05-24T00:00:00.000000000").returnToColumn()
            .value().isEqualTo("2014-05-30T00:00:00.000000000").returnToColumn()
            .value().isEqualTo("2014-05-30T00:00:00.000000000").returnToColumn()
        .returnToTable()
        .column("var10")
            .value().isEqualTo("2014-05-24T09:46:30.000000000").returnToColumn()
            .value().isEqualTo("2014-05-30T12:29:49.000000000").returnToColumn()
            .value().isEqualTo("2014-05-30T00:00:00").returnToColumn()
        .returnToTable()
        .column("var10")
            .value().isEqualTo("2014-05-24T09:46:30").returnToColumn()
            .value().isEqualTo("2014-05-30T12:29:49").returnToColumn()
            .value().isEqualTo("2014-05-30").returnToColumn()
        .returnToTable()
        .column("var10")
            .value().isEqualTo("2014-05-24T09:46:30").returnToColumn()
            .value().isEqualTo("2014-05-30T12:29:49").returnToColumn()
            .value().isEqualTo("2014-05-30T00:00").returnToColumn();
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
