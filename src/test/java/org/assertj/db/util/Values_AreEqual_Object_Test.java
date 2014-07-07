package org.assertj.db.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.bytesContentFromClassPathOf;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.DateTimeValue;
import org.assertj.db.type.DateValue;
import org.assertj.db.type.TimeValue;
import org.junit.Test;

/**
 * Tests on {@code areEqual} method for {@code Object}s.
 * 
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

  /**
   * This method tests the {@code areEqual} method for arrays of {@code byte}s.
   */
  @Test
  public void test_are_equal_for_bytes() {
    byte[] bytes = bytesContentFromClassPathOf("test.txt");
    Object goodBytes = (Object) new byte[] { 'T', 'e', 'x', 't', ' ', 'f', 'o', 'r', ' ', 't', 'e', 's', 't', 's' };
    Object badBytes = (Object) new byte[] { 'T', 'e', 'x', 't', ' ', 'f', 'o', 'r', ' ', ' ', 'e', 's', 't', 's' };
    assertThat(Values.areEqual(bytes, goodBytes)).isTrue();
    assertThat(Values.areEqual(bytes, badBytes)).isFalse();
    assertThat(Values.areEqual(bytes, (Object) "")).isFalse();
  }

  /**
   * This method tests the {@code areEqual} method for {@code String}s.
   */
  @Test
  public void test_are_equal_for_string() {
    assertThat(Values.areEqual("text", (Object) "text")).isTrue();
    assertThat(Values.areEqual("Text", (Object) "text")).isFalse();
    assertThat(Values.areEqual("Text", (Object) 1)).isFalse();
  }

  /**
   * This method tests the {@code areEqual} method for {@code DateValue}s.
   */
  @Test
  public void test_are_equal_for_dates() {
    assertThat(Values.areEqual(Date.valueOf("2007-12-23"), (Object) DateValue.of(2007, 12, 23))).isTrue();
    assertThat(Values.areEqual(Date.valueOf("2007-12-23"), (Object) DateValue.of(2007, 1, 2))).isFalse();
    assertThat(Values.areEqual("", (Object) DateValue.of(2007, 12, 23))).isFalse();
    assertThat(Values.areEqual(Date.valueOf("2007-12-23"), (Object) null)).isFalse();
  }

  /**
   * This method tests the {@code areEqual} method for {@code TimeValue}s.
   */
  @Test
  public void test_are_equal_for_times() {
    assertThat(Values.areEqual(Time.valueOf("09:01:06"), (Object) TimeValue.of(9, 1, 6))).isTrue();
    assertThat(Values.areEqual(Time.valueOf("09:01:06"), (Object) TimeValue.of(9, 1, 5))).isFalse();
    assertThat(Values.areEqual("", (Object) TimeValue.of(9, 1, 6))).isFalse();
    assertThat(Values.areEqual(Time.valueOf("09:01:06"), (Object) null)).isFalse();
  }

  /**
   * This method tests the {@code areEqual} method for {@code TimeValue}s.
   */
  @Test
  public void test_are_equal_for_timestamps() {
    assertThat(
        Values.areEqual(Timestamp.valueOf("2007-12-23 09:01:06.000000003"),
            (Object) DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 3)))).isTrue();
    assertThat(
        Values.areEqual(Timestamp.valueOf("2007-12-23 09:01:06.000000003"),
            (Object) DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 5, 3)))).isFalse();
    assertThat(Values.areEqual("", (Object) DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 3))))
        .isFalse();
    assertThat(Values.areEqual(Timestamp.valueOf("2007-12-23 09:01:06.000000003"), (Object) null)).isFalse();
  }
}
