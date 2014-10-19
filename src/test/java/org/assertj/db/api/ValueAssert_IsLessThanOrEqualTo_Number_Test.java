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
 * Tests on the methods which verifies if a value is less than or equal to a number.
 * 
 * @author Régis Pouiller
 * 
 */
public class ValueAssert_IsLessThanOrEqualTo_Number_Test extends AbstractTest {

  /**
   * This method tests that the value is less than or equal to a byte number.
   */
  @Test
  public void test_if_value_is_less_than_or_equal_to_number_byte() {
    Table table = new Table(source, "test");
    assertThat(table)
        .row()
            .value("var1").isLessThanOrEqualTo((byte) 1).returnToRow()
            .value("var3").isLessThanOrEqualTo((byte) 2).returnToRow()
            .value("var4").isLessThanOrEqualTo((byte) 3).returnToRow()
            .value("var5").isLessThanOrEqualTo((byte) 4).returnToRow()
            .value("var13").isLessThanOrEqualTo((byte) 5).returnToRow()
            .value("var14").isLessThanOrEqualTo((byte) 7).returnToRow()
        .returnToTable()
        .row()
            .value("var1").isLessThanOrEqualTo((byte) 10).returnToRow()
            .value("var3").isLessThanOrEqualTo((byte) 20).returnToRow()
            .value("var4").isLessThanOrEqualTo((byte) 30).returnToRow()
            .value("var5").isLessThanOrEqualTo((byte) 40).returnToRow()
            .value("var13").isLessThanOrEqualTo((byte) 50).returnToRow()
            .value("var14").isLessThanOrEqualTo((byte) 70);
  }

  /**
   * This method tests that the value is less than or equal to a short number.
   */
  @Test
  public void test_if_value_is_less_than_or_equal_to_number_short() {
    Table table = new Table(source, "test");
    assertThat(table)
        .row()
            .value("var1").isLessThanOrEqualTo((short) 1).returnToRow()
            .value("var3").isLessThanOrEqualTo((short) 2).returnToRow()
            .value("var4").isLessThanOrEqualTo((short) 3).returnToRow()
            .value("var5").isLessThanOrEqualTo((short) 4).returnToRow()
            .value("var13").isLessThanOrEqualTo((short) 5).returnToRow()
            .value("var14").isLessThanOrEqualTo((short) 7).returnToRow()
        .returnToTable()
        .row()
            .value("var1").isLessThanOrEqualTo((short) 10).returnToRow()
            .value("var3").isLessThanOrEqualTo((short) 20).returnToRow()
            .value("var4").isLessThanOrEqualTo((short) 30).returnToRow()
            .value("var5").isLessThanOrEqualTo((short) 40).returnToRow()
            .value("var13").isLessThanOrEqualTo((short) 50).returnToRow()
            .value("var14").isLessThanOrEqualTo((short) 70);
  }

  /**
   * This method tests that the value is less than or equal to a integer number.
   */
  @Test
  public void test_if_value_is_less_than_or_equal_to_number_integer() {
    Table table = new Table(source, "test");
    assertThat(table)
        .row()
            .value("var1").isLessThanOrEqualTo((int) 1).returnToRow()
            .value("var3").isLessThanOrEqualTo((int) 2).returnToRow()
            .value("var4").isLessThanOrEqualTo((int) 3).returnToRow()
            .value("var5").isLessThanOrEqualTo((int) 4).returnToRow()
            .value("var13").isLessThanOrEqualTo((int) 5).returnToRow()
            .value("var14").isLessThanOrEqualTo((int) 7).returnToRow()
        .returnToTable()
        .row()
            .value("var1").isLessThanOrEqualTo((int) 10).returnToRow()
            .value("var3").isLessThanOrEqualTo((int) 20).returnToRow()
            .value("var4").isLessThanOrEqualTo((int) 30).returnToRow()
            .value("var5").isLessThanOrEqualTo((int) 40).returnToRow()
            .value("var13").isLessThanOrEqualTo((int) 50).returnToRow()
            .value("var14").isLessThanOrEqualTo((int) 70);
  }

  /**
   * This method tests that the value is less than or equal to a long number.
   */
  @Test
  public void test_if_value_is_less_than_or_equal_to_number_long() {
    Table table = new Table(source, "test");
    assertThat(table)
        .row()
            .value("var1").isLessThanOrEqualTo((long) 1).returnToRow()
            .value("var3").isLessThanOrEqualTo((long) 2).returnToRow()
            .value("var4").isLessThanOrEqualTo((long) 3).returnToRow()
            .value("var5").isLessThanOrEqualTo((long) 4).returnToRow()
            .value("var13").isLessThanOrEqualTo((long) 5).returnToRow()
            .value("var14").isLessThanOrEqualTo((long) 7).returnToRow()
        .returnToTable()
        .row()
            .value("var1").isLessThanOrEqualTo((long) 10).returnToRow()
            .value("var3").isLessThanOrEqualTo((long) 20).returnToRow()
            .value("var4").isLessThanOrEqualTo((long) 30).returnToRow()
            .value("var5").isLessThanOrEqualTo((long) 40).returnToRow()
            .value("var13").isLessThanOrEqualTo((long) 50).returnToRow()
            .value("var14").isLessThanOrEqualTo((long) 70);
  }

  /**
   * This method tests that the value is less than or equal to a nig integer number.
   */
  @Test
  public void test_if_value_is_less_than_or_equal_to_number_biginteger() {
    Table table = new Table(source, "test");
    assertThat(table)
        .row()
            .value("var1").isLessThanOrEqualTo(new BigInteger("1")).returnToRow()
            .value("var3").isLessThanOrEqualTo(new BigInteger("2")).returnToRow()
            .value("var4").isLessThanOrEqualTo(new BigInteger("3")).returnToRow()
            .value("var5").isLessThanOrEqualTo(new BigInteger("4")).returnToRow()
        .returnToTable()
        .row()
            .value("var1").isLessThanOrEqualTo(new BigInteger("10")).returnToRow()
            .value("var3").isLessThanOrEqualTo(new BigInteger("20")).returnToRow()
            .value("var4").isLessThanOrEqualTo(new BigInteger("30")).returnToRow()
            .value("var5").isLessThanOrEqualTo(new BigInteger("40"));
  }

  /**
   * This method tests that the value is less than or equal to a float number.
   */
  @Test
  public void test_if_value_is_less_than_or_equal_to_number_float() {
    Table table = new Table(source, "test");
    assertThat(table)
        .row()
            .value("var1").isLessThanOrEqualTo((float) 1).returnToRow()
            .value("var3").isLessThanOrEqualTo((float) 2).returnToRow()
            .value("var4").isLessThanOrEqualTo((float) 3).returnToRow()
            .value("var5").isLessThanOrEqualTo((float) 4).returnToRow()
            .value("var6").isLessThanOrEqualTo((float) 5.6).returnToRow()
            .value("var7").isLessThanOrEqualTo((float) 7.8).returnToRow()
            .value("var13").isLessThanOrEqualTo((float) 5).returnToRow()
            .value("var14").isLessThanOrEqualTo((float) 7).returnToRow()
        .returnToTable()
        .row()
            .value("var1").isLessThanOrEqualTo((float) 10).returnToRow()
            .value("var3").isLessThanOrEqualTo((float) 20).returnToRow()
            .value("var4").isLessThanOrEqualTo((float) 30).returnToRow()
            .value("var5").isLessThanOrEqualTo((float) 40).returnToRow()
            .value("var6").isLessThanOrEqualTo((float) 50.6).returnToRow()
            .value("var7").isLessThanOrEqualTo((float) 70.8).returnToRow()
            .value("var13").isLessThanOrEqualTo((float) 50).returnToRow()
            .value("var14").isLessThanOrEqualTo((float) 70);
  }

  /**
   * This method tests that the value is less than or equal to a double number.
   */
  @Test
  public void test_if_value_is_less_than_or_equal_to_number_double() {
    Table table = new Table(source, "test");
    assertThat(table)
        .row()
            .value("var1").isLessThanOrEqualTo((double) 1).returnToRow()
            .value("var3").isLessThanOrEqualTo((double) 2).returnToRow()
            .value("var4").isLessThanOrEqualTo((double) 3).returnToRow()
            .value("var5").isLessThanOrEqualTo((double) 4).returnToRow()
            .value("var6").isLessThanOrEqualTo((double) 5.6).returnToRow()
            .value("var7").isLessThanOrEqualTo((double) 7.8).returnToRow()
            .value("var13").isLessThanOrEqualTo((double) 5).returnToRow()
            .value("var14").isLessThanOrEqualTo((double) 7).returnToRow()
        .returnToTable()
        .row()
            .value("var1").isLessThanOrEqualTo((double) 10).returnToRow()
            .value("var3").isLessThanOrEqualTo((double) 20).returnToRow()
            .value("var4").isLessThanOrEqualTo((double) 30).returnToRow()
            .value("var5").isLessThanOrEqualTo((double) 40).returnToRow()
            .value("var6").isLessThanOrEqualTo((double) 50.6).returnToRow()
            .value("var7").isLessThanOrEqualTo((double) 70.8).returnToRow()
            .value("var13").isLessThanOrEqualTo((double) 50).returnToRow()
            .value("var14").isLessThanOrEqualTo((double) 70);
  }

  /**
   * This method tests that the value is less than or equal to a big decimal number.
   */
  @Test
  public void test_if_value_is_less_than_or_equal_to_number_bigdecimal() {
    Table table = new Table(source, "test");
    assertThat(table)
        .row()
            .value("var1").isLessThanOrEqualTo(new BigDecimal("1")).returnToRow()
            .value("var3").isLessThanOrEqualTo(new BigDecimal("2")).returnToRow()
            .value("var4").isLessThanOrEqualTo(new BigDecimal("3")).returnToRow()
            .value("var5").isLessThanOrEqualTo(new BigDecimal("4")).returnToRow()
            .value("var6").isLessThanOrEqualTo(new BigDecimal("5.6")).returnToRow()
            .value("var7").isLessThanOrEqualTo(new BigDecimal("7.8")).returnToRow()
            .value("var13").isLessThanOrEqualTo(new BigDecimal("5")).returnToRow()
            .value("var14").isLessThanOrEqualTo(new BigDecimal("7")).returnToRow()
        .returnToTable()
        .row()
            .value("var1").isLessThanOrEqualTo(new BigDecimal("10")).returnToRow()
            .value("var3").isLessThanOrEqualTo(new BigDecimal("20")).returnToRow()
            .value("var4").isLessThanOrEqualTo(new BigDecimal("30")).returnToRow()
            .value("var5").isLessThanOrEqualTo(new BigDecimal("40")).returnToRow()
            .value("var6").isLessThanOrEqualTo(new BigDecimal("50.6")).returnToRow()
            .value("var7").isLessThanOrEqualTo(new BigDecimal("70.8")).returnToRow()
            .value("var13").isLessThanOrEqualTo(new BigDecimal("50")).returnToRow()
            .value("var14").isLessThanOrEqualTo(new BigDecimal("70"));
  }

  /**
   * This method should fail because the value is not equal to the number in parameter.
   */
  @Test
  public void should_fail_because_value_is_not_less_than_or_equal() {
    try {
      Table table = new Table(source, "test");
      assertThat(table)
          .row()
              .value("var1").isLessThanOrEqualTo(0);
      
      fail("Une Erreur doit être levée");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Value at index 0 of Row at index 0 of test table] \n" +
          "Expecting:\n" +
          "  <1>\n" +
          "to be less than or equal to \n" +
          "  <0>");
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
          .value().as("var2").isLessThanOrEqualTo(1);
      
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
