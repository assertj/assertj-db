package org.assertj.db.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Tests on the methods which verifies if a value is greater than or equal to a number.
 * 
 * @author Régis Pouiller
 * 
 */
public class ValueAssert_IsGreaterThanOrEqualTo_Number_Test extends AbstractTest {

  /**
   * This method tests that the value is greater than or equal to a byte number.
   */
  @Test
  public void test_if_value_is_greater_than_or_equal_to_number_byte() {
    Table table = new Table(source, "test");
    assertThat(table)
        .row()
            .value("var1").isGreaterThanOrEqualTo((byte) 1).returnToRow()
            .value("var3").isGreaterThanOrEqualTo((byte) 2).returnToRow()
            .value("var4").isGreaterThanOrEqualTo((byte) 3).returnToRow()
            .value("var5").isGreaterThanOrEqualTo((byte) 4).returnToRow()
            .value("var13").isGreaterThanOrEqualTo((byte) 5).returnToRow()
            .value("var14").isGreaterThanOrEqualTo((byte) 7).returnToRow()
        .returnToTable()
        .row()
            .value("var1").isGreaterThanOrEqualTo((byte) 10).returnToRow()
            .value("var3").isGreaterThanOrEqualTo((byte) 20).returnToRow()
            .value("var4").isGreaterThanOrEqualTo((byte) 30).returnToRow()
            .value("var5").isGreaterThanOrEqualTo((byte) 40).returnToRow()
            .value("var13").isGreaterThanOrEqualTo((byte) 50).returnToRow()
            .value("var14").isGreaterThanOrEqualTo((byte) 70);
  }

  /**
   * This method tests that the value is greater than or equal to a short number.
   */
  @Test
  public void test_if_value_is_greater_than_or_equal_to_number_short() {
    Table table = new Table(source, "test");
    assertThat(table)
        .row()
            .value("var1").isGreaterThanOrEqualTo((short) 1).returnToRow()
            .value("var3").isGreaterThanOrEqualTo((short) 2).returnToRow()
            .value("var4").isGreaterThanOrEqualTo((short) 3).returnToRow()
            .value("var5").isGreaterThanOrEqualTo((short) 4).returnToRow()
            .value("var13").isGreaterThanOrEqualTo((short) 5).returnToRow()
            .value("var14").isGreaterThanOrEqualTo((short) 7).returnToRow()
        .returnToTable()
        .row()
            .value("var1").isGreaterThanOrEqualTo((short) 10).returnToRow()
            .value("var3").isGreaterThanOrEqualTo((short) 20).returnToRow()
            .value("var4").isGreaterThanOrEqualTo((short) 30).returnToRow()
            .value("var5").isGreaterThanOrEqualTo((short) 40).returnToRow()
            .value("var13").isGreaterThanOrEqualTo((short) 50).returnToRow()
            .value("var14").isGreaterThanOrEqualTo((short) 70);
  }

  /**
   * This method tests that the value is greater than or equal to a integer number.
   */
  @Test
  public void test_if_value_is_greater_than_or_equal_to_number_integer() {
    Table table = new Table(source, "test");
    assertThat(table)
        .row()
            .value("var1").isGreaterThanOrEqualTo((int) 1).returnToRow()
            .value("var3").isGreaterThanOrEqualTo((int) 2).returnToRow()
            .value("var4").isGreaterThanOrEqualTo((int) 3).returnToRow()
            .value("var5").isGreaterThanOrEqualTo((int) 4).returnToRow()
            .value("var13").isGreaterThanOrEqualTo((int) 5).returnToRow()
            .value("var14").isGreaterThanOrEqualTo((int) 7).returnToRow()
        .returnToTable()
        .row()
            .value("var1").isGreaterThanOrEqualTo((int) 10).returnToRow()
            .value("var3").isGreaterThanOrEqualTo((int) 20).returnToRow()
            .value("var4").isGreaterThanOrEqualTo((int) 30).returnToRow()
            .value("var5").isGreaterThanOrEqualTo((int) 40).returnToRow()
            .value("var13").isGreaterThanOrEqualTo((int) 50).returnToRow()
            .value("var14").isGreaterThanOrEqualTo((int) 70);
  }

  /**
   * This method tests that the value is greater than or equal to a long number.
   */
  @Test
  public void test_if_value_is_greater_than_or_equal_to_number_long() {
    Table table = new Table(source, "test");
    assertThat(table)
        .row()
            .value("var1").isGreaterThanOrEqualTo((long) 1).returnToRow()
            .value("var3").isGreaterThanOrEqualTo((long) 2).returnToRow()
            .value("var4").isGreaterThanOrEqualTo((long) 3).returnToRow()
            .value("var5").isGreaterThanOrEqualTo((long) 4).returnToRow()
            .value("var13").isGreaterThanOrEqualTo((long) 5).returnToRow()
            .value("var14").isGreaterThanOrEqualTo((long) 7).returnToRow()
        .returnToTable()
        .row()
            .value("var1").isGreaterThanOrEqualTo((long) 10).returnToRow()
            .value("var3").isGreaterThanOrEqualTo((long) 20).returnToRow()
            .value("var4").isGreaterThanOrEqualTo((long) 30).returnToRow()
            .value("var5").isGreaterThanOrEqualTo((long) 40).returnToRow()
            .value("var13").isGreaterThanOrEqualTo((long) 50).returnToRow()
            .value("var14").isGreaterThanOrEqualTo((long) 70);
  }

  /**
   * This method tests that the value is greater than or equal to a nig integer number.
   */
  @Test
  public void test_if_value_is_greater_than_or_equal_to_number_biginteger() {
    Table table = new Table(source, "test");
    assertThat(table)
        .row()
            .value("var1").isGreaterThanOrEqualTo(new BigInteger("1")).returnToRow()
            .value("var3").isGreaterThanOrEqualTo(new BigInteger("2")).returnToRow()
            .value("var4").isGreaterThanOrEqualTo(new BigInteger("3")).returnToRow()
            .value("var5").isGreaterThanOrEqualTo(new BigInteger("4")).returnToRow()
        .returnToTable()
        .row()
            .value("var1").isGreaterThanOrEqualTo(new BigInteger("10")).returnToRow()
            .value("var3").isGreaterThanOrEqualTo(new BigInteger("20")).returnToRow()
            .value("var4").isGreaterThanOrEqualTo(new BigInteger("30")).returnToRow()
            .value("var5").isGreaterThanOrEqualTo(new BigInteger("40"));
  }

  /**
   * This method tests that the value is greater than or equal to a float number.
   */
  @Test
  public void test_if_value_is_greater_than_or_equal_to_number_float() {
    Table table = new Table(source, "test");
    assertThat(table)
        .row()
            .value("var1").isGreaterThanOrEqualTo((float) 1).returnToRow()
            .value("var3").isGreaterThanOrEqualTo((float) 2).returnToRow()
            .value("var4").isGreaterThanOrEqualTo((float) 3).returnToRow()
            .value("var5").isGreaterThanOrEqualTo((float) 4).returnToRow()
            .value("var6").isGreaterThanOrEqualTo((float) 5.6).returnToRow()
            .value("var7").isGreaterThanOrEqualTo((float) 7.8).returnToRow()
            .value("var13").isGreaterThanOrEqualTo((float) 5).returnToRow()
            .value("var14").isGreaterThanOrEqualTo((float) 7).returnToRow()
        .returnToTable()
        .row()
            .value("var1").isGreaterThanOrEqualTo((float) 10).returnToRow()
            .value("var3").isGreaterThanOrEqualTo((float) 20).returnToRow()
            .value("var4").isGreaterThanOrEqualTo((float) 30).returnToRow()
            .value("var5").isGreaterThanOrEqualTo((float) 40).returnToRow()
            .value("var6").isGreaterThanOrEqualTo((float) 50.6).returnToRow()
            .value("var7").isGreaterThanOrEqualTo((float) 70.8).returnToRow()
            .value("var13").isGreaterThanOrEqualTo((float) 50).returnToRow()
            .value("var14").isGreaterThanOrEqualTo((float) 70);
  }

  /**
   * This method tests that the value is greater than or equal to a double number.
   */
  @Test
  public void test_if_value_is_greater_than_or_equal_to_number_double() {
    Table table = new Table(source, "test");
    assertThat(table)
        .row()
            .value("var1").isGreaterThanOrEqualTo((double) 1).returnToRow()
            .value("var3").isGreaterThanOrEqualTo((double) 2).returnToRow()
            .value("var4").isGreaterThanOrEqualTo((double) 3).returnToRow()
            .value("var5").isGreaterThanOrEqualTo((double) 4).returnToRow()
            .value("var6").isGreaterThanOrEqualTo((double) 5.6).returnToRow()
            .value("var7").isGreaterThanOrEqualTo((double) 7.8).returnToRow()
            .value("var13").isGreaterThanOrEqualTo((double) 5).returnToRow()
            .value("var14").isGreaterThanOrEqualTo((double) 7).returnToRow()
        .returnToTable()
        .row()
            .value("var1").isGreaterThanOrEqualTo((double) 10).returnToRow()
            .value("var3").isGreaterThanOrEqualTo((double) 20).returnToRow()
            .value("var4").isGreaterThanOrEqualTo((double) 30).returnToRow()
            .value("var5").isGreaterThanOrEqualTo((double) 40).returnToRow()
            .value("var6").isGreaterThanOrEqualTo((double) 50.6).returnToRow()
            .value("var7").isGreaterThanOrEqualTo((double) 70.8).returnToRow()
            .value("var13").isGreaterThanOrEqualTo((double) 50).returnToRow()
            .value("var14").isGreaterThanOrEqualTo((double) 70);
  }

  /**
   * This method tests that the value is greater than or equal to a big decimal number.
   */
  @Test
  public void test_if_value_is_greater_than_or_equal_to_number_bigdecimal() {
    Table table = new Table(source, "test");
    assertThat(table)
        .row()
            .value("var1").isGreaterThanOrEqualTo(new BigDecimal("1")).returnToRow()
            .value("var3").isGreaterThanOrEqualTo(new BigDecimal("2")).returnToRow()
            .value("var4").isGreaterThanOrEqualTo(new BigDecimal("3")).returnToRow()
            .value("var5").isGreaterThanOrEqualTo(new BigDecimal("4")).returnToRow()
            .value("var6").isGreaterThanOrEqualTo(new BigDecimal("5.6")).returnToRow()
            .value("var7").isGreaterThanOrEqualTo(new BigDecimal("7.8")).returnToRow()
            .value("var13").isGreaterThanOrEqualTo(new BigDecimal("5")).returnToRow()
            .value("var14").isGreaterThanOrEqualTo(new BigDecimal("7")).returnToRow()
        .returnToTable()
        .row()
            .value("var1").isGreaterThanOrEqualTo(new BigDecimal("10")).returnToRow()
            .value("var3").isGreaterThanOrEqualTo(new BigDecimal("20")).returnToRow()
            .value("var4").isGreaterThanOrEqualTo(new BigDecimal("30")).returnToRow()
            .value("var5").isGreaterThanOrEqualTo(new BigDecimal("40")).returnToRow()
            .value("var6").isGreaterThanOrEqualTo(new BigDecimal("50.6")).returnToRow()
            .value("var7").isGreaterThanOrEqualTo(new BigDecimal("70.8")).returnToRow()
            .value("var13").isGreaterThanOrEqualTo(new BigDecimal("50")).returnToRow()
            .value("var14").isGreaterThanOrEqualTo(new BigDecimal("70"));
  }

  /**
   * This method should fail because the value is not greater than or equal to the number in parameter.
   */
  @Test
  public void should_fail_because_value_is_not_greater_than_or_equal() {
    try {
      Table table = new Table(source, "test");
      assertThat(table)
          .row()
              .value("var1").isGreaterThanOrEqualTo(2);
      
      fail("Une Erreur doit être levée");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Value at index 0 of Row at index 0 of test table] \n" +
          "Expecting:\n" +
          "  <1>\n" +
          "to be greater than or equal to \n" +
          "  <2>");
    }
  }

  /**
   * This method should fail because the value is not a number.
   */
  @Test
  public void should_fail_because_value_is_not_a_number() {
    try {
      Table table = new Table(source, "test");
      assertThat(table).column("var2")
          .value().as("var2").isGreaterThanOrEqualTo(1);
      
      fail("Une Erreur doit être levée");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[var2] \n" +
          "Expecting:\n" +
          "  <true>\n" +
          "to be of type\n" +
          "  <NUMBER>\n" +
          "but was of type\n" +
          "  <BOOLEAN>");
    }
  }

}
