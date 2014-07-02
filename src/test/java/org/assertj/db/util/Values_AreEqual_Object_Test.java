package org.assertj.db.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.assertj.db.common.AbstractTest;
import org.junit.Test;

/**
 * Tests on {@code areEqual} method for {@code Object}s.
 * @author Régis Pouiller
 *
 */
public class Values_AreEqual_Object_Test extends AbstractTest {

  /**
   * This method tests the {@code areEqual} method for {@code Object}s with booleans.
   */
  @Test
  public void test_are_equal_for_null() {
    assertThat(Values.areEqual(null, (Object) null)).isTrue();
    assertThat(Values.areEqual(null, (Object) "")).isFalse();
    assertThat(Values.areEqual(new Exception(), (Object) null)).isFalse();
  }

  /**
   * This method tests the {@code areEqual} method for {@code Object}s with booleans.
   */
  @Test
  public void test_are_equal_for_booleans() {
    assertThat(Values.areEqual(true, (Object) true)).isTrue();
    assertThat(Values.areEqual(true, (Object) false)).isFalse();
    assertThat(Values.areEqual(false, (Object) true)).isFalse();
    assertThat(Values.areEqual(false, (Object) false)).isTrue();
    assertThat(Values.areEqual(true, (Object) null)).isFalse();
    assertThat(Values.areEqual(false, (Object) null)).isFalse();
  }

  /**
   * This method tests the {@code areEqual} method for {@code BigInteger}s.
   */
  @Test
  public void test_are_equal_for_biginteger() {
    assertThat(Values.areEqual(1, (Object) new BigInteger("1"))).isTrue();
    assertThat(Values.areEqual(1, (Object) new BigInteger("2"))).isFalse();
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
    assertThat(Values.areEqual(1, (Object) null)).isFalse();
    assertThat(Values.areEqual(new BigDecimal("1"), (Object) null)).isFalse();
  }

  /**
   * This method tests the {@code areEqual} method for another type of value and {@code Float}s.
   */
  @Test
  public void test_are_equal_for_other_and_float() {
    assertThat(Values.areEqual(1F, (Object) 1)).isTrue();
    assertThat(Values.areEqual(2F, (Object) 1)).isFalse();
    assertThat(Values.areEqual(1F, (Object) 1L)).isTrue();
    assertThat(Values.areEqual(2F, (Object) 1L)).isFalse();
    assertThat(Values.areEqual(1F, (Object) 1F)).isTrue();
    assertThat(Values.areEqual(2F, (Object) 1F)).isFalse();
    assertThat(Values.areEqual(1F, (Object) 1D)).isTrue();
    assertThat(Values.areEqual(2F, (Object) 1D)).isFalse();
    assertThat(Values.areEqual(1.5F, (Object) 1.5F)).isTrue();
    assertThat(Values.areEqual(2.5F, (Object) 1.5F)).isFalse();
    assertThat(Values.areEqual(1.5F, (Object) 1.5D)).isTrue();
    assertThat(Values.areEqual(2.5F, (Object) 1.5D)).isFalse();
  }

  /**
   * This method tests the {@code areEqual} method for another type of value and {@code BigDecimal}s.
   */
  @Test
  public void test_are_equal_for_other_and_bigdecimal() {
    assertThat(Values.areEqual(new BigDecimal("1"), (Object) 1)).isTrue();
    assertThat(Values.areEqual(new BigDecimal("2"), (Object) 1)).isFalse();
    assertThat(Values.areEqual(new BigDecimal("1"), (Object) 1L)).isTrue();
    assertThat(Values.areEqual(new BigDecimal("2"), (Object) 1L)).isFalse();
    assertThat(Values.areEqual(new BigDecimal("1"), (Object) 1F)).isTrue();
    assertThat(Values.areEqual(new BigDecimal("2"), (Object) 1F)).isFalse();
    assertThat(Values.areEqual(new BigDecimal("1"), (Object) 1D)).isTrue();
    assertThat(Values.areEqual(new BigDecimal("2"), (Object) 1D)).isFalse();
    assertThat(Values.areEqual(new BigDecimal("1.5"), (Object) 1.5F)).isTrue();
    assertThat(Values.areEqual(new BigDecimal("2.5"), (Object) 1.5F)).isFalse();
    assertThat(Values.areEqual(new BigDecimal("1.5"), (Object) 1.5D)).isTrue();
    assertThat(Values.areEqual(new BigDecimal("2.5"), (Object) 1.5D)).isFalse();
  }

  /**
   * This method tests the {@code areEqual} method for another type of value and {@code Byte}s.
   */
  @Test
  public void test_are_equal_for_other_and_byte() {
    assertThat(Values.areEqual((byte) 1, (Object) 1)).isTrue();
    assertThat(Values.areEqual((byte) 2, (Object) 1)).isFalse();
    assertThat(Values.areEqual((byte) 1, (Object) 1L)).isTrue();
    assertThat(Values.areEqual((byte) 2, (Object) 1L)).isFalse();
    assertThat(Values.areEqual((byte) 1, (Object) 1F)).isTrue();
    assertThat(Values.areEqual((byte) 2, (Object) 1F)).isFalse();
    assertThat(Values.areEqual((byte) 1, (Object) 1D)).isTrue();
    assertThat(Values.areEqual((byte) 2, (Object) 1D)).isFalse();
  }

  /**
   * This method tests the {@code areEqual} method for another type of value and {@code Short}s.
   */
  @Test
  public void test_are_equal_for_other_and_short() {
    assertThat(Values.areEqual((short) 1, (Object) 1)).isTrue();
    assertThat(Values.areEqual((short) 2, (Object) 1)).isFalse();
    assertThat(Values.areEqual((short) 1, (Object) 1L)).isTrue();
    assertThat(Values.areEqual((short) 2, (Object) 1L)).isFalse();
    assertThat(Values.areEqual((short) 1, (Object) 1F)).isTrue();
    assertThat(Values.areEqual((short) 2, (Object) 1F)).isFalse();
    assertThat(Values.areEqual((short) 1, (Object) 1D)).isTrue();
    assertThat(Values.areEqual((short) 2, (Object) 1D)).isFalse();
  }

  /**
   * This method tests the {@code areEqual} method for another type of value and {@code Integer}s.
   */
  @Test
  public void test_are_equal_for_other_and_integer() {
    assertThat(Values.areEqual((int) 1, (Object) 1)).isTrue();
    assertThat(Values.areEqual((int) 2, (Object) 1)).isFalse();
    assertThat(Values.areEqual((int) 1, (Object) 1L)).isTrue();
    assertThat(Values.areEqual((int) 2, (Object) 1L)).isFalse();
    assertThat(Values.areEqual((int) 1, (Object) 1F)).isTrue();
    assertThat(Values.areEqual((int) 2, (Object) 1F)).isFalse();
    assertThat(Values.areEqual((int) 1, (Object) 1D)).isTrue();
    assertThat(Values.areEqual((int) 2, (Object) 1D)).isFalse();
  }

  /**
   * This method tests the {@code areEqual} method for another type of value and {@code Long}s.
   */
  @Test
  public void test_are_equal_for_other_and_long() {
    assertThat(Values.areEqual((long) 1, (Object) 1)).isTrue();
    assertThat(Values.areEqual((long) 2, (Object) 1)).isFalse();
    assertThat(Values.areEqual((long) 1, (Object) 1L)).isTrue();
    assertThat(Values.areEqual((long) 2, (Object) 1L)).isFalse();
    assertThat(Values.areEqual((long) 1, (Object) 1F)).isTrue();
    assertThat(Values.areEqual((long) 2, (Object) 1F)).isFalse();
    assertThat(Values.areEqual((long) 1, (Object) 1D)).isTrue();
    assertThat(Values.areEqual((long) 2, (Object) 1D)).isFalse();
  }
}
