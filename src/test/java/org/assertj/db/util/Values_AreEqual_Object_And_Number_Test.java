package org.assertj.db.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.error.AssertJDBException;
import org.junit.Test;

/**
 * Tests on {@code areEqual} method for {@code Number}s.
 * 
 * @author Régis Pouiller
 * 
 */
public class Values_AreEqual_Object_And_Number_Test extends AbstractTest {

  /**
   * This method tests the {@code areEqual} method for {@code BigInteger}s.
   */
  @Test
  public void test_are_equal_for_biginteger() {
    assertThat(Values.areEqual(1, new BigInteger("1"))).isTrue();
    assertThat(Values.areEqual(new BigInteger("1"), new BigInteger("1"))).isTrue();
    assertThat(Values.areEqual(1, new BigInteger("2"))).isFalse();
    assertThat(Values.areEqual(new BigInteger("1"), new BigInteger("2"))).isFalse();
  }

  /**
   * This method should fail because the value ("-") can not be compared to a {@code BigInteger}.
   */
  @Test(expected = AssertJDBException.class)
  public void should_fail_because_value_can_not_be_compared_to_a_biginteger() {
    Values.areEqual("-", new BigInteger("1"));
  }

  /**
   * This method tests the {@code areEqual} method for {@code BigDecimal}s.
   */
  @Test
  public void test_are_equal_for_bigdecimal() {
    assertThat(Values.areEqual(1, new BigDecimal("1"))).isTrue();
    assertThat(Values.areEqual(new BigDecimal("1"), new BigDecimal("1"))).isTrue();
    assertThat(Values.areEqual(1, new BigDecimal("2"))).isFalse();
    assertThat(Values.areEqual(new BigDecimal("1"), new BigDecimal("2"))).isFalse();
  }

  /**
   * This method should fail because the value ("-") can not be compared to a {@code BigDecimal}.
   */
  @Test(expected = AssertJDBException.class)
  public void should_fail_because_value_can_not_be_compared_to_a_bigdecimal() {
    Values.areEqual("-", new BigDecimal("1"));
  }

  /**
   * This method tests the {@code areEqual} method for another type of value and {@code Float}s.
   */
  @Test
  public void test_are_equal_for_other_and_float() {
    assertThat(Values.areEqual(1F, 1)).isTrue();
    assertThat(Values.areEqual(2F, 1)).isFalse();
    assertThat(Values.areEqual(1F, 1L)).isTrue();
    assertThat(Values.areEqual(2F, 1L)).isFalse();
    assertThat(Values.areEqual(1F, 1F)).isTrue();
    assertThat(Values.areEqual(2F, 1F)).isFalse();
    assertThat(Values.areEqual(1F, 1D)).isTrue();
    assertThat(Values.areEqual(2F, 1D)).isFalse();
    assertThat(Values.areEqual(1.5F, 1.5F)).isTrue();
    assertThat(Values.areEqual(2.5F, 1.5F)).isFalse();
    assertThat(Values.areEqual(1.5F, 1.5D)).isTrue();
    assertThat(Values.areEqual(2.5F, 1.5D)).isFalse();
  }

  /**
   * This method tests the {@code areEqual} method for another type of value and {@code Double}s.
   */
  @Test
  public void test_are_equal_for_other_and_double() {
    assertThat(Values.areEqual(1D, 1)).isTrue();
    assertThat(Values.areEqual(2D, 1)).isFalse();
    assertThat(Values.areEqual(1D, 1L)).isTrue();
    assertThat(Values.areEqual(2D, 1L)).isFalse();
    assertThat(Values.areEqual(1D, 1F)).isTrue();
    assertThat(Values.areEqual(2D, 1F)).isFalse();
    assertThat(Values.areEqual(1D, 1D)).isTrue();
    assertThat(Values.areEqual(2D, 1D)).isFalse();
    assertThat(Values.areEqual(1.5D, 1.5F)).isTrue();
    assertThat(Values.areEqual(2.5D, 1.5F)).isFalse();
    assertThat(Values.areEqual(1.5D, 1.5D)).isTrue();
    assertThat(Values.areEqual(2.5D, 1.5D)).isFalse();
  }

  /**
   * This method tests the {@code areEqual} method for another type of value and {@code BigInteger}s.
   */
  @Test
  public void test_are_equal_for_other_and_biginteger() {
    assertThat(Values.areEqual(new BigInteger("1"), 1)).isTrue();
    assertThat(Values.areEqual(new BigInteger("2"), 1)).isFalse();
    assertThat(Values.areEqual(new BigInteger("1"), 1L)).isTrue();
    assertThat(Values.areEqual(new BigInteger("2"), 1L)).isFalse();
  }

  /**
   * This method tests the {@code areEqual} method for another type of value and {@code BigDecimal}s.
   */
  @Test
  public void test_are_equal_for_other_and_bigdecimal() {
    assertThat(Values.areEqual(new BigDecimal("1"), 1)).isTrue();
    assertThat(Values.areEqual(new BigDecimal("2"), 1)).isFalse();
    assertThat(Values.areEqual(new BigDecimal("1"), 1L)).isTrue();
    assertThat(Values.areEqual(new BigDecimal("2"), 1L)).isFalse();
    assertThat(Values.areEqual(new BigDecimal("1"), 1F)).isTrue();
    assertThat(Values.areEqual(new BigDecimal("2"), 1F)).isFalse();
    assertThat(Values.areEqual(new BigDecimal("1"), 1D)).isTrue();
    assertThat(Values.areEqual(new BigDecimal("2"), 1D)).isFalse();
    assertThat(Values.areEqual(new BigDecimal("1.5"), 1.5F)).isTrue();
    assertThat(Values.areEqual(new BigDecimal("2.5"), 1.5F)).isFalse();
    assertThat(Values.areEqual(new BigDecimal("1.5"), 1.5D)).isTrue();
    assertThat(Values.areEqual(new BigDecimal("2.5"), 1.5D)).isFalse();
  }

  /**
   * This method tests the {@code areEqual} method for another type of value and {@code Byte}s.
   */
  @Test
  public void test_are_equal_for_other_and_byte() {
    assertThat(Values.areEqual((byte) 1, 1)).isTrue();
    assertThat(Values.areEqual((byte) 2, 1)).isFalse();
    assertThat(Values.areEqual((byte) 1, 1L)).isTrue();
    assertThat(Values.areEqual((byte) 2, 1L)).isFalse();
    assertThat(Values.areEqual((byte) 1, 1F)).isTrue();
    assertThat(Values.areEqual((byte) 2, 1F)).isFalse();
    assertThat(Values.areEqual((byte) 1, 1D)).isTrue();
    assertThat(Values.areEqual((byte) 2, 1D)).isFalse();
  }

  /**
   * This method tests the {@code areEqual} method for another type of value and {@code Short}s.
   */
  @Test
  public void test_are_equal_for_other_and_short() {
    assertThat(Values.areEqual((short) 1, 1)).isTrue();
    assertThat(Values.areEqual((short) 2, 1)).isFalse();
    assertThat(Values.areEqual((short) 1, 1L)).isTrue();
    assertThat(Values.areEqual((short) 2, 1L)).isFalse();
    assertThat(Values.areEqual((short) 1, 1F)).isTrue();
    assertThat(Values.areEqual((short) 2, 1F)).isFalse();
    assertThat(Values.areEqual((short) 1, 1D)).isTrue();
    assertThat(Values.areEqual((short) 2, 1D)).isFalse();
  }

  /**
   * This method tests the {@code areEqual} method for another type of value and {@code Integer}s.
   */
  @Test
  public void test_are_equal_for_other_and_integer() {
    assertThat(Values.areEqual((int) 1, 1)).isTrue();
    assertThat(Values.areEqual((int) 2, 1)).isFalse();
    assertThat(Values.areEqual((int) 1, 1L)).isTrue();
    assertThat(Values.areEqual((int) 2, 1L)).isFalse();
    assertThat(Values.areEqual((int) 1, 1F)).isTrue();
    assertThat(Values.areEqual((int) 2, 1F)).isFalse();
    assertThat(Values.areEqual((int) 1, 1D)).isTrue();
    assertThat(Values.areEqual((int) 2, 1D)).isFalse();
  }

  /**
   * This method tests the {@code areEqual} method for another type of value and {@code Long}s.
   */
  @Test
  public void test_are_equal_for_other_and_long() {
    assertThat(Values.areEqual((long) 1, 1)).isTrue();
    assertThat(Values.areEqual((long) 2, 1)).isFalse();
    assertThat(Values.areEqual((long) 1, 1L)).isTrue();
    assertThat(Values.areEqual((long) 2, 1L)).isFalse();
    assertThat(Values.areEqual((long) 1, 1F)).isTrue();
    assertThat(Values.areEqual((long) 2, 1F)).isFalse();
    assertThat(Values.areEqual((long) 1, 1D)).isTrue();
    assertThat(Values.areEqual((long) 2, 1D)).isFalse();
  }

  /**
   * This method tests the {@code areEqual} method for another type of values.
   */
  @Test
  public void test_are_equal_for_other_and_other() {
    assertThat(Values.areEqual("-", 1)).isFalse();
  }

}
