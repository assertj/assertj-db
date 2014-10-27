package org.assertj.db.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.bytesContentFromClassPathOf;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.concurrent.atomic.AtomicLong;

import org.assertj.db.error.AssertJDBException;
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
public class Values_AreEqual_Object_And_Object_Test {

  /**
   * This method tests the {@code areEqual} method for {@code Object}s with {@code null}.
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
    assertThat(Values.areEqual(Date.valueOf("2007-12-23"), (Object) "2007-12-23")).isTrue();
    assertThat(Values.areEqual(Date.valueOf("2007-12-23"), (Object) "2007-12-23T00:00")).isTrue();
    assertThat(Values.areEqual(Date.valueOf("2007-12-23"), (Object) "2007-12-23T00:00:00")).isTrue();
    assertThat(Values.areEqual(Date.valueOf("2007-12-23"), (Object) "2007-12-23T00:00:00.000000000")).isTrue();
  }

  /**
   * This method tests the {@code areEqual} method for {@code DateValue}s.
   */
  @Test
  public void test_are_equal_for_timestamp_and_dates() {
    assertThat(Values.areEqual(Timestamp.valueOf("2007-12-23 00:00:00.000000000"), (Object) DateValue.of(2007, 12, 23))).isTrue();
    assertThat(Values.areEqual(Timestamp.valueOf("2007-12-23 00:00:00.000000000"), (Object) DateValue.of(2007, 1, 2))).isFalse();
    assertThat(Values.areEqual("", (Object) DateValue.of(2007, 12, 23))).isFalse();
    assertThat(Values.areEqual(Timestamp.valueOf("2007-12-23 00:00:00.000000000"), (Object) null)).isFalse();

    assertThat(Values.areEqual(Timestamp.valueOf("2007-12-23 00:00:00.000000000"), (Object) DateValue.of(2007, 12, 2))).isFalse();
    assertThat(Values.areEqual(Timestamp.valueOf("2007-12-23 00:00:00.000000000"), (Object) DateValue.of(2007, 1, 23))).isFalse();
    assertThat(Values.areEqual(Timestamp.valueOf("2007-12-23 00:00:00.000000000"), (Object) DateValue.of(2006, 12, 23))).isFalse();
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
    assertThat(Values.areEqual(Time.valueOf("09:01:06"), (Object) "09:01:06")).isTrue();
    assertThat(Values.areEqual(Time.valueOf("09:01:06"), (Object) "09:01:06.000000000")).isTrue();
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
    assertThat(
        Values.areEqual(Timestamp.valueOf("2007-12-23 09:01:06.000000003"),
            (Object) "2007-12-23T09:01:06.000000003")).isTrue();
  }

  /**
   * This method tests the {@code areEqual} method for another type of value and {@code Float}s.
   */
  @Test
  public void test_are_equal_for_float_and_string() {
    assertThat(Values.areEqual(1F, (Object) "1")).isTrue();
    assertThat(Values.areEqual(2F, (Object) "1")).isFalse();
    assertThat(Values.areEqual(1.5F, (Object) "1.5")).isTrue();
    assertThat(Values.areEqual(2.5F, (Object) "1.5")).isFalse();
  }

  /**
   * This method tests the {@code areEqual} method for another type of value and {@code Double}s.
   */
  @Test
  public void test_are_equal_for_double_and_string() {
    assertThat(Values.areEqual(1D, (Object) "1")).isFalse();
    assertThat(Values.areEqual(2D, (Object) "1")).isFalse();
    assertThat(Values.areEqual(1.5D, (Object) "1.5")).isFalse();
    assertThat(Values.areEqual(2.5D, (Object) "1.5")).isFalse();
  }

  /**
   * This method tests the {@code areEqual} method for another type of value and {@code BigInteger}s.
   */
  @Test
  public void test_are_equal_for_biginteger_and_string() {
    assertThat(Values.areEqual(new BigInteger("1"), (Object) "1")).isFalse();
    assertThat(Values.areEqual(new BigInteger("2"), (Object) "1")).isFalse();
  }

  /**
   * This method tests the {@code areEqual} method for another type of value and {@code BigDecimal}s.
   */
  @Test
  public void test_are_equal_for_bigdecimal_and_string() {
    assertThat(Values.areEqual(new BigDecimal("1"), (Object) "1")).isTrue();
    assertThat(Values.areEqual(new BigDecimal("2"), (Object) "1")).isFalse();
    assertThat(Values.areEqual(new BigDecimal("1.5"), (Object) "1.5")).isTrue();
    assertThat(Values.areEqual(new BigDecimal("2.5"), (Object) "1.5")).isFalse();
  }

  /**
   * This method tests the {@code areEqual} method for another type of value and {@code Byte}s.
   */
  @Test
  public void test_are_equal_for_byte_and_string() {
    assertThat(Values.areEqual((byte) 1, (Object) "1")).isTrue();
    assertThat(Values.areEqual((byte) 2, (Object) "1")).isFalse();
  }

  /**
   * This method tests the {@code areEqual} method for another type of value and {@code Short}s.
   */
  @Test
  public void test_are_equal_for_short_and_string() {
    assertThat(Values.areEqual((short) 1, (Object) "1")).isTrue();
    assertThat(Values.areEqual((short) 2, (Object) "1")).isFalse();
  }

  /**
   * This method tests the {@code areEqual} method for another type of value and {@code Int}s.
   */
  @Test
  public void test_are_equal_for_int_and_string() {
    assertThat(Values.areEqual((int) 1, (Object) "1")).isTrue();
    assertThat(Values.areEqual((int) 2, (Object) "1")).isFalse();
  }

  /**
   * This method tests the {@code areEqual} method for another type of value and {@code Long}s.
   */
  @Test
  public void test_are_equal_for_long_and_string() {
    assertThat(Values.areEqual((long) 1, (Object) "1")).isTrue();
    assertThat(Values.areEqual((long) 2, (Object) "1")).isFalse();
  }

  /**
   * This method tests the {@code areEqual} method for another type of value and {@code AtomicLong}s.
   */
  @Test
  public void test_are_equal_for_atomiclong_and_string() {
    assertThat(Values.areEqual(new AtomicLong(1), (Object) "1")).isFalse();
    assertThat(Values.areEqual(new AtomicLong(2), (Object) "1")).isFalse();
  }

  /**
   * This method should fail because the expected value ("***") if not parsable to do the comparison.
   */
  @Test(expected = AssertJDBException.class)
  public void should_fail_because_string_is_not_parseable() {
    Values.areEqual(1, (Object) "***");
  }

  /**
   * This method tests the {@code areEqual} method for {@code TimeValue}s.
   */
  @Test
  public void test_are_equal_for_times_in_string() {
    assertThat(Values.areEqual(Time.valueOf("09:01:06"), (Object) "09:01:06")).isTrue();
    assertThat(Values.areEqual(Time.valueOf("09:01:00"), (Object) "09:01:00")).isTrue();
    assertThat(Values.areEqual(Time.valueOf("09:01:00"), (Object) "09:01")).isTrue();

    assertThat(Values.areEqual(Time.valueOf("09:01:06"), (Object) "09:01:05")).isFalse();
    assertThat(Values.areEqual(Time.valueOf("09:01:06"), (Object) "09:02:06")).isFalse();
    assertThat(Values.areEqual(Time.valueOf("09:01:06"), (Object) "10:01:06")).isFalse();
    assertThat(Values.areEqual(Time.valueOf("09:01:06"), (Object) "09:01:06.000000003")).isFalse();
  }

  /**
   * This method should fail because the expected value ("***") is not parsable to do the comparison.
   */
  @Test(expected = AssertJDBException.class)
  public void should_fail_because_string_is_not_parseable_in_time() {
    Values.areEqual(Time.valueOf("09:01:06"), (Object) "***");
  }

  /**
   * This method tests the {@code areEqual} method for {@code TimeValue}s.
   */
  @Test
  public void test_are_equal_for_dates_in_string() {
    assertThat(Values.areEqual(Date.valueOf("2007-12-23"), (Object) "2007-12-23")).isTrue();
    assertThat(Values.areEqual(Date.valueOf("2007-12-23"), (Object) "2007-12-23T00:00")).isTrue();
    assertThat(Values.areEqual(Date.valueOf("2007-12-23"), (Object) "2007-12-23T00:00:00")).isTrue();
    assertThat(Values.areEqual(Date.valueOf("2007-12-23"), (Object) "2007-12-23T00:00:00.000000000")).isTrue();

    assertThat(Values.areEqual(Date.valueOf("2007-12-23"), (Object) "2007-12-24")).isFalse();
    assertThat(Values.areEqual(Date.valueOf("2007-12-23"), (Object) "2007-01-23")).isFalse();
    assertThat(Values.areEqual(Date.valueOf("2007-12-23"), (Object) "2008-12-23")).isFalse();
    assertThat(Values.areEqual(Date.valueOf("2007-12-23"), (Object) "2007-12-23T00:01")).isFalse();
    assertThat(Values.areEqual(Date.valueOf("2007-12-23"), (Object) "2007-12-23T01:00")).isFalse();
    assertThat(Values.areEqual(Date.valueOf("2007-12-23"), (Object) "2007-12-24T00:00")).isFalse();
    assertThat(Values.areEqual(Date.valueOf("2007-12-23"), (Object) "2007-01-23T00:00")).isFalse();
    assertThat(Values.areEqual(Date.valueOf("2007-12-23"), (Object) "2008-12-23T00:00")).isFalse();
    assertThat(Values.areEqual(Date.valueOf("2007-12-23"), (Object) "2007-12-23T00:00:01")).isFalse();
    assertThat(Values.areEqual(Date.valueOf("2007-12-23"), (Object) "2007-12-23T00:01:00")).isFalse();
    assertThat(Values.areEqual(Date.valueOf("2007-12-23"), (Object) "2007-12-23T01:00:00")).isFalse();
    assertThat(Values.areEqual(Date.valueOf("2007-12-23"), (Object) "2007-12-24T00:00:00")).isFalse();
    assertThat(Values.areEqual(Date.valueOf("2007-12-23"), (Object) "2007-01-23T00:00:00")).isFalse();
    assertThat(Values.areEqual(Date.valueOf("2007-12-23"), (Object) "2008-12-23T00:00:00")).isFalse();
    assertThat(Values.areEqual(Date.valueOf("2007-12-23"), (Object) "2007-12-23T00:00:00.000000001")).isFalse();
    assertThat(Values.areEqual(Date.valueOf("2007-12-23"), (Object) "2007-12-23T00:00:01.000000000")).isFalse();
    assertThat(Values.areEqual(Date.valueOf("2007-12-23"), (Object) "2007-12-23T00:01:00.000000000")).isFalse();
    assertThat(Values.areEqual(Date.valueOf("2007-12-23"), (Object) "2007-12-23T01:00:00.000000000")).isFalse();
    assertThat(Values.areEqual(Date.valueOf("2007-12-23"), (Object) "2007-12-24T00:00:00.000000000")).isFalse();
    assertThat(Values.areEqual(Date.valueOf("2007-12-23"), (Object) "2007-01-23T00:00:00.000000000")).isFalse();
    assertThat(Values.areEqual(Date.valueOf("2007-12-23"), (Object) "2008-12-23T00:00:00.000000000")).isFalse();
  }

  /**
   * This method should fail because the expected value ("***") is not parsable to do the comparison.
   */
  @Test(expected = AssertJDBException.class)
  public void should_fail_because_string_is_not_parseable_in_date() {
    Values.areEqual(Date.valueOf("2007-12-23"), (Object) "***");
  }

  /**
   * This method tests the {@code areEqual} method for {@code TimeValue}s.
   */
  @Test
  public void test_are_equal_for_datestimes() {
    assertThat(Values.areEqual(Timestamp.valueOf("2007-12-23 09:01:06.000000003"), (Object) "2007-12-23T09:01:06.000000003")).isTrue();
    assertThat(Values.areEqual(Timestamp.valueOf("2007-12-23 00:00:00.000000000"), (Object) "2007-12-23T00:00:00.000000000")).isTrue();
    assertThat(Values.areEqual(Timestamp.valueOf("2007-12-23 00:00:00"), (Object) "2007-12-23T00:00:00")).isTrue();
    assertThat(Values.areEqual(Timestamp.valueOf("2007-12-23 00:00:00"), (Object) "2007-12-23T00:00")).isTrue();
    assertThat(Values.areEqual(Timestamp.valueOf("2007-12-23 00:00:00"), (Object) "2007-12-23")).isTrue();

    assertThat(Values.areEqual(Timestamp.valueOf("2007-12-23 09:01:06.000000003"), (Object) "2007-12-23T09:01:06.000000004")).isFalse();
    assertThat(Values.areEqual(Timestamp.valueOf("2007-12-23 09:01:06.000000003"), (Object) "2007-12-23T09:01:07.000000003")).isFalse();
    assertThat(Values.areEqual(Timestamp.valueOf("2007-12-23 09:01:06.000000003"), (Object) "2007-12-23T09:02:06.000000003")).isFalse();
    assertThat(Values.areEqual(Timestamp.valueOf("2007-12-23 09:01:06.000000003"), (Object) "2007-12-23T10:01:06.000000003")).isFalse();
    assertThat(Values.areEqual(Timestamp.valueOf("2007-12-23 09:01:06.000000003"), (Object) "2007-12-24T09:01:06.000000003")).isFalse();
    assertThat(Values.areEqual(Timestamp.valueOf("2007-12-23 09:01:06.000000003"), (Object) "2007-01-23T09:01:06.000000003")).isFalse();
    assertThat(Values.areEqual(Timestamp.valueOf("2007-12-23 09:01:06.000000003"), (Object) "2008-12-23T09:01:06.000000003")).isFalse();

    assertThat(Values.areEqual(Timestamp.valueOf("2007-12-23 00:00:00.000000000"), (Object) "2007-12-23T00:00:00.000000001")).isFalse();
    assertThat(Values.areEqual(Timestamp.valueOf("2007-12-23 00:00:00.000000000"), (Object) "2007-12-23T00:00:01.000000000")).isFalse();
    assertThat(Values.areEqual(Timestamp.valueOf("2007-12-23 00:00:00.000000000"), (Object) "2007-12-23T00:01:00.000000000")).isFalse();
    assertThat(Values.areEqual(Timestamp.valueOf("2007-12-23 00:00:00.000000000"), (Object) "2007-12-23T01:00:00.000000000")).isFalse();
    assertThat(Values.areEqual(Timestamp.valueOf("2007-12-23 00:00:00.000000000"), (Object) "2007-12-24T00:00:00.000000000")).isFalse();
    assertThat(Values.areEqual(Timestamp.valueOf("2007-12-23 00:00:00.000000000"), (Object) "2007-01-23T00:00:00.000000000")).isFalse();
    assertThat(Values.areEqual(Timestamp.valueOf("2007-12-23 00:00:00.000000000"), (Object) "2008-12-23T00:00:00.000000000")).isFalse();

    assertThat(Values.areEqual(Timestamp.valueOf("2007-12-23 00:00:00"), (Object) "2007-12-23T00:00:00.000000001")).isFalse();
    assertThat(Values.areEqual(Timestamp.valueOf("2007-12-23 00:00:00"), (Object) "2007-12-23T00:00:01.000000000")).isFalse();
    assertThat(Values.areEqual(Timestamp.valueOf("2007-12-23 00:00:00"), (Object) "2007-12-23T00:01:00.000000000")).isFalse();
    assertThat(Values.areEqual(Timestamp.valueOf("2007-12-23 00:00:00"), (Object) "2007-12-23T01:00:00.000000000")).isFalse();
    assertThat(Values.areEqual(Timestamp.valueOf("2007-12-23 00:00:00"), (Object) "2007-12-24T00:00:00.000000000")).isFalse();
    assertThat(Values.areEqual(Timestamp.valueOf("2007-12-23 00:00:00"), (Object) "2007-01-23T00:00:00.000000000")).isFalse();
    assertThat(Values.areEqual(Timestamp.valueOf("2007-12-23 00:00:00"), (Object) "2008-12-23T00:00:00.000000000")).isFalse();
  }

  /**
   * This method should fail because the expected value ("***") is not parsable to do the comparison.
   */
  @Test(expected = AssertJDBException.class)
  public void should_fail_because_string_is_not_parseable_in_datetime() {
    Values.areEqual(Timestamp.valueOf("2007-12-23 09:01:06.000000003"), (Object) "***");
  }
}
