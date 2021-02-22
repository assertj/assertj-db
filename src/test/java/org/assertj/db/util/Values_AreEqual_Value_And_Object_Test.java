/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2015-2021 the original author or authors.
 */
package org.assertj.db.util;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.exception.AssertJDBException;
import org.assertj.db.type.DateTimeValue;
import org.assertj.db.type.DateValue;
import org.assertj.db.type.TimeValue;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.bytesContentFromClassPathOf;

/**
 * Tests on {@code areEqual} method for {@code Object}s.
 *
 * @author Régis Pouiller
 */
public class Values_AreEqual_Value_And_Object_Test extends AbstractTest {

  /**
   * This method tests the {@code areEqual} method for {@code Object}s with {@code null}.
   */
  @Test
  public void test_are_equal_for_null() throws Exception {
    assertThat(Values.areEqual(getValue(null, null), (Object) null)).isTrue();
    assertThat(Values.areEqual(getValue(null, null), (Object) "")).isFalse();
    assertThat(Values.areEqual(getValue(null, new Exception()), (Object) null)).isFalse();
  }

  /**
   * This method tests the {@code areEqual} method for {@code Object}s with booleans.
   */
  @Test
  public void test_are_equal_for_booleans() throws Exception {
    assertThat(Values.areEqual(getValue(null, true), (Object) true)).isTrue();
    assertThat(Values.areEqual(getValue(null, true), (Object) false)).isFalse();
    assertThat(Values.areEqual(getValue(null, false), (Object) true)).isFalse();
    assertThat(Values.areEqual(getValue(null, false), (Object) false)).isTrue();
    assertThat(Values.areEqual(getValue(null, true), (Object) null)).isFalse();
    assertThat(Values.areEqual(getValue(null, false), (Object) null)).isFalse();
  }

  /**
   * This method tests the {@code areEqual} method for {@code BigInteger}s.
   */
  @Test
  public void test_are_equal_for_biginteger() throws Exception {
    assertThat(Values.areEqual(getValue(null, 1), (Object) new BigInteger("1"))).isTrue();
    assertThat(Values.areEqual(getValue(null, 1), (Object) new BigInteger("2"))).isFalse();
  }

  /**
   * This method tests the {@code areEqual} method for {@code BigDecimal}s.
   */
  @Test
  public void test_are_equal_for_bigdecimal() throws Exception {
    assertThat(Values.areEqual(getValue(null, 1), new BigDecimal("1"))).isTrue();
    assertThat(Values.areEqual(getValue(null, new BigDecimal("1")), new BigDecimal("1"))).isTrue();
    assertThat(Values.areEqual(getValue(null, 1), new BigDecimal("2"))).isFalse();
    assertThat(Values.areEqual(getValue(null, new BigDecimal("1")), new BigDecimal("2"))).isFalse();
    assertThat(Values.areEqual(getValue(null, 1), (Object) null)).isFalse();
    assertThat(Values.areEqual(getValue(null, new BigDecimal("1")), (Object) null)).isFalse();
  }

  /**
   * This method tests the {@code areEqual} method for another type of value and {@code Float}s.
   */
  @Test
  public void test_are_equal_for_other_and_float() throws Exception {
    assertThat(Values.areEqual(getValue(null, 1F), (Object) 1)).isTrue();
    assertThat(Values.areEqual(getValue(null, 2F), (Object) 1)).isFalse();
    assertThat(Values.areEqual(getValue(null, 1F), (Object) 1L)).isTrue();
    assertThat(Values.areEqual(getValue(null, 2F), (Object) 1L)).isFalse();
    assertThat(Values.areEqual(getValue(null, 1F), (Object) 1F)).isTrue();
    assertThat(Values.areEqual(getValue(null, 2F), (Object) 1F)).isFalse();
    assertThat(Values.areEqual(getValue(null, 1F), (Object) 1D)).isTrue();
    assertThat(Values.areEqual(getValue(null, 2F), (Object) 1D)).isFalse();
    assertThat(Values.areEqual(getValue(null, 1.5F), (Object) 1.5F)).isTrue();
    assertThat(Values.areEqual(getValue(null, 2.5F), (Object) 1.5F)).isFalse();
    assertThat(Values.areEqual(getValue(null, 1.5F), (Object) 1.5D)).isTrue();
    assertThat(Values.areEqual(getValue(null, 2.5F), (Object) 1.5D)).isFalse();
  }

  /**
   * This method tests the {@code areEqual} method for another type of value and {@code Double}s.
   */
  @Test
  public void test_are_equal_for_other_and_double() throws Exception {
    assertThat(Values.areEqual(getValue(null, 1D), (Object) 1)).isTrue();
    assertThat(Values.areEqual(getValue(null, 2D), (Object) 1)).isFalse();
    assertThat(Values.areEqual(getValue(null, 1D), (Object) 1L)).isTrue();
    assertThat(Values.areEqual(getValue(null, 2D), (Object) 1L)).isFalse();
    assertThat(Values.areEqual(getValue(null, 1D), (Object) 1F)).isTrue();
    assertThat(Values.areEqual(getValue(null, 2D), (Object) 1F)).isFalse();
    assertThat(Values.areEqual(getValue(null, 1D), (Object) 1D)).isTrue();
    assertThat(Values.areEqual(getValue(null, 2D), (Object) 1D)).isFalse();
    assertThat(Values.areEqual(getValue(null, 1.5D), (Object) 1.5F)).isTrue();
    assertThat(Values.areEqual(getValue(null, 2.5D), (Object) 1.5F)).isFalse();
    assertThat(Values.areEqual(getValue(null, 1.5D), (Object) 1.5D)).isTrue();
    assertThat(Values.areEqual(getValue(null, 2.5D), (Object) 1.5D)).isFalse();
  }

  /**
   * This method tests the {@code areEqual} method for another type of value and {@code BigDecimal}s.
   */
  @Test
  public void test_are_equal_for_other_and_bigdecimal() throws Exception {
    assertThat(Values.areEqual(getValue(null, new BigDecimal("1")), (Object) 1)).isTrue();
    assertThat(Values.areEqual(getValue(null, new BigDecimal("2")), (Object) 1)).isFalse();
    assertThat(Values.areEqual(getValue(null, new BigDecimal("1")), (Object) 1L)).isTrue();
    assertThat(Values.areEqual(getValue(null, new BigDecimal("2")), (Object) 1L)).isFalse();
    assertThat(Values.areEqual(getValue(null, new BigDecimal("1")), (Object) 1F)).isTrue();
    assertThat(Values.areEqual(getValue(null, new BigDecimal("2")), (Object) 1F)).isFalse();
    assertThat(Values.areEqual(getValue(null, new BigDecimal("1")), (Object) 1D)).isTrue();
    assertThat(Values.areEqual(getValue(null, new BigDecimal("2")), (Object) 1D)).isFalse();
    assertThat(Values.areEqual(getValue(null, new BigDecimal("1.5")), (Object) 1.5F)).isTrue();
    assertThat(Values.areEqual(getValue(null, new BigDecimal("2.5")), (Object) 1.5F)).isFalse();
    assertThat(Values.areEqual(getValue(null, new BigDecimal("1.5")), (Object) 1.5D)).isTrue();
    assertThat(Values.areEqual(getValue(null, new BigDecimal("2.5")), (Object) 1.5D)).isFalse();
  }

  /**
   * This method tests the {@code areEqual} method for another type of value and {@code Byte}s.
   */
  @Test
  public void test_are_equal_for_other_and_byte() throws Exception {
    assertThat(Values.areEqual(getValue(null, (byte) 1), (Object) 1)).isTrue();
    assertThat(Values.areEqual(getValue(null, (byte) 2), (Object) 1)).isFalse();
    assertThat(Values.areEqual(getValue(null, (byte) 1), (Object) 1L)).isTrue();
    assertThat(Values.areEqual(getValue(null, (byte) 2), (Object) 1L)).isFalse();
    assertThat(Values.areEqual(getValue(null, (byte) 1), (Object) 1F)).isTrue();
    assertThat(Values.areEqual(getValue(null, (byte) 2), (Object) 1F)).isFalse();
    assertThat(Values.areEqual(getValue(null, (byte) 1), (Object) 1D)).isTrue();
    assertThat(Values.areEqual(getValue(null, (byte) 2), (Object) 1D)).isFalse();
  }

  /**
   * This method tests the {@code areEqual} method for another type of value and {@code Short}s.
   */
  @Test
  public void test_are_equal_for_other_and_short() throws Exception {
    assertThat(Values.areEqual(getValue(null, (short) 1), (Object) 1)).isTrue();
    assertThat(Values.areEqual(getValue(null, (short) 2), (Object) 1)).isFalse();
    assertThat(Values.areEqual(getValue(null, (short) 1), (Object) 1L)).isTrue();
    assertThat(Values.areEqual(getValue(null, (short) 2), (Object) 1L)).isFalse();
    assertThat(Values.areEqual(getValue(null, (short) 1), (Object) 1F)).isTrue();
    assertThat(Values.areEqual(getValue(null, (short) 2), (Object) 1F)).isFalse();
    assertThat(Values.areEqual(getValue(null, (short) 1), (Object) 1D)).isTrue();
    assertThat(Values.areEqual(getValue(null, (short) 2), (Object) 1D)).isFalse();
  }

  /**
   * This method tests the {@code areEqual} method for another type of value and {@code Integer}s.
   */
  @Test
  public void test_are_equal_for_other_and_integer() throws Exception {
    assertThat(Values.areEqual(getValue(null, 1), (Object) 1)).isTrue();
    assertThat(Values.areEqual(getValue(null, 2), (Object) 1)).isFalse();
    assertThat(Values.areEqual(getValue(null, 1), (Object) 1L)).isTrue();
    assertThat(Values.areEqual(getValue(null, 2), (Object) 1L)).isFalse();
    assertThat(Values.areEqual(getValue(null, 1), (Object) 1F)).isTrue();
    assertThat(Values.areEqual(getValue(null, 2), (Object) 1F)).isFalse();
    assertThat(Values.areEqual(getValue(null, 1), (Object) 1D)).isTrue();
    assertThat(Values.areEqual(getValue(null, 2), (Object) 1D)).isFalse();
  }

  /**
   * This method tests the {@code areEqual} method for another type of value and {@code Long}s.
   */
  @Test
  public void test_are_equal_for_other_and_long() throws Exception {
    assertThat(Values.areEqual(getValue(null, (long) 1), (Object) 1)).isTrue();
    assertThat(Values.areEqual(getValue(null, (long) 2), (Object) 1)).isFalse();
    assertThat(Values.areEqual(getValue(null, (long) 1), (Object) 1L)).isTrue();
    assertThat(Values.areEqual(getValue(null, (long) 2), (Object) 1L)).isFalse();
    assertThat(Values.areEqual(getValue(null, (long) 1), (Object) 1F)).isTrue();
    assertThat(Values.areEqual(getValue(null, (long) 2), (Object) 1F)).isFalse();
    assertThat(Values.areEqual(getValue(null, (long) 1), (Object) 1D)).isTrue();
    assertThat(Values.areEqual(getValue(null, (long) 2), (Object) 1D)).isFalse();
  }

  /**
   * This method tests the {@code areEqual} method for arrays of {@code byte}s.
   */
  @Test
  public void test_are_equal_for_bytes() throws Exception {
    byte[] bytes = bytesContentFromClassPathOf("test.txt");
    Object goodBytes = new byte[] { 'T', 'e', 'x', 't', ' ', 'f', 'o', 'r', ' ', 't', 'e', 's', 't', 's' };
    Object badBytes = new byte[] { 'T', 'e', 'x', 't', ' ', 'f', 'o', 'r', ' ', ' ', 'e', 's', 't', 's' };
    assertThat(Values.areEqual(getValue(null, bytes), goodBytes)).isTrue();
    assertThat(Values.areEqual(getValue(null, bytes), badBytes)).isFalse();
    assertThat(Values.areEqual(getValue(null, bytes), (Object) "")).isFalse();
  }

  /**
   * This method tests the {@code areEqual} method for {@code String}s.
   */
  @Test
  public void test_are_equal_for_string() throws Exception {
    assertThat(Values.areEqual(getValue(null, "text"), (Object) "text")).isTrue();
    assertThat(Values.areEqual(getValue(null, "Text"), (Object) "text")).isFalse();
    assertThat(Values.areEqual(getValue(null, "Text"), (Object) 1)).isFalse();
  }

  /**
   * This method tests the {@code areEqual} method for {@code UUID}s.
   */
  @Test
  public void test_are_equal_for_UUID() throws Exception {
    assertThat(Values.areEqual(getValue(null, UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435")),
                               (Object) UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"))).isTrue();
    assertThat(Values.areEqual(getValue(null, UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435")),
                               (Object) UUID.fromString("16319617-AE95-4087-9264-D3D21BF611B6"))).isFalse();
    assertThat(Values.areEqual(getValue(null, UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435")),
                               (Object) 1)).isFalse();
    assertThat(Values.areEqual(getValue(null, UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435")),
                               (Object) null)).isFalse();
    assertThat(Values.areEqual(getValue(null, UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435")),
                               (Object) "30B443AE-C0C9-4790-9BEC-CE1380808435")).isTrue();
    assertThat(Values.areEqual(getValue(null, UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435")),
                               (Object) "30B443AE-C0C9-4790-9BED-CE1380808435")).isFalse();
  }

  /**
   * This method tests the {@code areEqual} method for {@code DateValue}s.
   */
  @Test
  public void test_are_equal_for_dates() throws Exception {
    assertThat(Values.areEqual(getValue(null, Date.valueOf("2007-12-23")), (Object) DateValue.of(2007, 12, 23))).isTrue();
    assertThat(Values.areEqual(getValue(null, Date.valueOf("2007-12-23")), (Object) DateValue.of(2007, 1, 2))).isFalse();
    assertThat(Values.areEqual(getValue(null, ""), (Object) DateValue.of(2007, 12, 23))).isFalse();
    assertThat(Values.areEqual(getValue(null, Date.valueOf("2007-12-23")), (Object) null)).isFalse();
    assertThat(Values.areEqual(getValue(null, Date.valueOf("2007-12-23")), (Object) "2007-12-23")).isTrue();
    assertThat(Values.areEqual(getValue(null, Date.valueOf("2007-12-23")), (Object) "2007-12-23T00:00")).isTrue();
    assertThat(Values.areEqual(getValue(null, Date.valueOf("2007-12-23")), (Object) "2007-12-23T00:00:00")).isTrue();
    assertThat(Values.areEqual(getValue(null, Date.valueOf("2007-12-23")), (Object) "2007-12-23T00:00:00.000000000")).isTrue();
    assertThat(Values.areEqual(getValue(null, Date.valueOf("2007-12-23")), Date.valueOf("2007-12-23"))).isTrue();
    assertThat(Values.areEqual(getValue(null, Date.valueOf("2007-12-23")), LocalDate.of(2007, 12, 23))).isTrue();
  }

  /**
   * This method tests the {@code areEqual} method for {@code DateValue}s.
   */
  @Test
  public void test_are_equal_for_timestamp_and_dates() throws Exception {
    assertThat(Values.areEqual(getValue(null, Timestamp.valueOf("2007-12-23 00:00:00.000000000")), (Object) DateValue.of(2007, 12, 23)))
            .isTrue();
    assertThat(Values.areEqual(getValue(null, Timestamp.valueOf("2007-12-23 00:00:00.000000000")), LocalDate.of(2007, 12, 23)))
            .isTrue();
    assertThat(Values.areEqual(getValue(null, Timestamp.valueOf("2007-12-23 00:00:00.000000000")), (Object) DateValue.of(2007, 1, 2)))
            .isFalse();
    assertThat(Values.areEqual(getValue(null, Timestamp.valueOf("2007-12-23 00:00:00.000000000")), LocalDate.of(2007, 1, 2)))
            .isFalse();
    assertThat(Values.areEqual(getValue(null, ""), (Object) DateValue.of(2007, 12, 23))).isFalse();
    assertThat(Values.areEqual(getValue(null, Timestamp.valueOf("2007-12-23 00:00:00.000000000")), (Object) null)).isFalse();

    assertThat(Values.areEqual(getValue(null, Timestamp.valueOf("2007-12-23 00:00:00.000000000")), (Object) DateValue.of(2007, 12, 2)))
            .isFalse();
    assertThat(Values.areEqual(getValue(null, Timestamp.valueOf("2007-12-23 00:00:00.000000000")), (Object) DateValue.of(2007, 1, 23)))
            .isFalse();
    assertThat(Values.areEqual(getValue(null, Timestamp.valueOf("2007-12-23 00:00:00.000000000")), (Object) DateValue.of(2006, 12, 23)))
            .isFalse();
  }

  /**
   * This method tests the {@code areEqual} method for {@code TimeValue}s.
   */
  @Test
  public void test_are_equal_for_times() throws Exception {
    assertThat(Values.areEqual(getValue(null, Time.valueOf("09:01:06")), (Object) TimeValue.of(9, 1, 6))).isTrue();
    assertThat(Values.areEqual(getValue(null, Time.valueOf("09:01:06")), (Object) TimeValue.of(9, 1, 5))).isFalse();
    assertThat(Values.areEqual(getValue(null, ""), (Object) TimeValue.of(9, 1, 6))).isFalse();
    assertThat(Values.areEqual(getValue(null, Time.valueOf("09:01:06")), (Object) null)).isFalse();
    assertThat(Values.areEqual(getValue(null, Time.valueOf("09:01:06")), (Object) "09:01:06")).isTrue();
    assertThat(Values.areEqual(getValue(null, Time.valueOf("09:01:06")), (Object) "09:01:06.000000000")).isTrue();
    assertThat(Values.areEqual(getValue(null, Time.valueOf("09:01:06")), Time.valueOf("09:01:06"))).isTrue();
    assertThat(Values.areEqual(getValue(null, Time.valueOf("09:01:06")), LocalTime.of(9, 1, 6))).isTrue();
  }

  /**
   * This method tests the {@code areEqual} method for {@code TimeValue}s.
   */
  @Test
  public void test_are_equal_for_timestamps() throws Exception {
    assertThat(Values.areEqual(getValue(null, Timestamp.valueOf("2007-12-23 09:01:06.000000003")),
            (Object) DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 3)))).isTrue();
    assertThat(Values.areEqual(getValue(null, Timestamp.valueOf("2007-12-23 09:01:06.000000003")),
            (Object) DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 5, 3)))).isFalse();
    assertThat(Values.areEqual(getValue(null, ""), (Object) DateTimeValue.of(DateValue.of(2007, 12, 23),
            TimeValue.of(9, 1, 6, 3)))).isFalse();
    assertThat(Values.areEqual(getValue(null, Timestamp.valueOf("2007-12-23 09:01:06.000000003")), (Object) null)).isFalse();
    assertThat(Values.areEqual(getValue(null, Timestamp.valueOf("2007-12-23 09:01:06.000000003")),
            (Object) "2007-12-23T09:01:06.000000003")).isTrue();
    assertThat(Values.areEqual(getValue(null, Timestamp.valueOf("2007-12-23 09:01:06.000000003")),
            Timestamp.valueOf("2007-12-23 09:01:06.000000003"))).isTrue();
    assertThat(Values.areEqual(getValue(null, Timestamp.valueOf("2007-12-23 09:01:06.000000003")),
            LocalDateTime.of(2007, 12, 23, 9, 1, 6, 3))).isTrue();
  }

  /**
   * This method tests the {@code areEqual} method for another type of value and {@code Float}s.
   */
  @Test
  public void test_are_equal_for_float_and_string() throws Exception {
    assertThat(Values.areEqual(getValue(null, 1F), (Object) "1")).isTrue();
    assertThat(Values.areEqual(getValue(null, 2F), (Object) "1")).isFalse();
    assertThat(Values.areEqual(getValue(null, 1.5F), (Object) "1.5")).isTrue();
    assertThat(Values.areEqual(getValue(null, 2.5F), (Object) "1.5")).isFalse();
  }

  /**
   * This method tests the {@code areEqual} method for another type of value and {@code Double}s.
   */
  @Test
  public void test_are_equal_for_double_and_string() throws Exception {
    assertThat(Values.areEqual(getValue(null, 1D), (Object) "1")).isTrue();
    assertThat(Values.areEqual(getValue(null, 2D), (Object) "1")).isFalse();
    assertThat(Values.areEqual(getValue(null, 1.5D), (Object) "1.5")).isTrue();
    assertThat(Values.areEqual(getValue(null, 2.5D), (Object) "1.5")).isFalse();
  }

  /**
   * This method tests the {@code areEqual} method for another type of value and {@code BigInteger}s.
   */
  @Test
  public void test_are_equal_for_biginteger_and_string() throws Exception {
    assertThat(Values.areEqual(getValue(null, new BigInteger("1")), (Object) "1")).isTrue();
    assertThat(Values.areEqual(getValue(null, new BigInteger("2")), (Object) "1")).isFalse();
  }

  /**
   * This method tests the {@code areEqual} method for another type of value and {@code BigDecimal}s.
   */
  @Test
  public void test_are_equal_for_bigdecimal_and_string() throws Exception {
    assertThat(Values.areEqual(getValue(null, new BigDecimal("1")), (Object) "1")).isTrue();
    assertThat(Values.areEqual(getValue(null, new BigDecimal("2")), (Object) "1")).isFalse();
    assertThat(Values.areEqual(getValue(null, new BigDecimal("1.5")), (Object) "1.5")).isTrue();
    assertThat(Values.areEqual(getValue(null, new BigDecimal("2.5")), (Object) "1.5")).isFalse();
  }

  /**
   * This method tests the {@code areEqual} method for another type of value and {@code Byte}s.
   */
  @Test
  public void test_are_equal_for_byte_and_string() throws Exception {
    assertThat(Values.areEqual(getValue(null, (byte) 1), (Object) "1")).isTrue();
    assertThat(Values.areEqual(getValue(null, (byte) 2), (Object) "1")).isFalse();
  }

  /**
   * This method tests the {@code areEqual} method for another type of value and {@code Short}s.
   */
  @Test
  public void test_are_equal_for_short_and_string() throws Exception {
    assertThat(Values.areEqual(getValue(null, (short) 1), (Object) "1")).isTrue();
    assertThat(Values.areEqual(getValue(null, (short) 2), (Object) "1")).isFalse();
  }

  /**
   * This method tests the {@code areEqual} method for another type of value and {@code Int}s.
   */
  @Test
  public void test_are_equal_for_int_and_string() throws Exception {
    assertThat(Values.areEqual(getValue(null, 1), (Object) "1")).isTrue();
    assertThat(Values.areEqual(getValue(null, 2), (Object) "1")).isFalse();
  }

  /**
   * This method tests the {@code areEqual} method for another type of value and {@code Long}s.
   */
  @Test
  public void test_are_equal_for_long_and_string() throws Exception {
    assertThat(Values.areEqual(getValue(null, (long) 1), (Object) "1")).isTrue();
    assertThat(Values.areEqual(getValue(null, (long) 2), (Object) "1")).isFalse();
  }

  /**
   * This method tests the {@code areEqual} method for another type of value and {@code AtomicLong}s.
   */
  @Test
  public void test_are_equal_for_atomiclong_and_string() throws Exception {
    assertThat(Values.areEqual(getValue(null, new AtomicLong(1)), (Object) "1")).isFalse();
    assertThat(Values.areEqual(getValue(null, new AtomicLong(2)), (Object) "1")).isFalse();
  }

  /**
   * This method should fail because the expected value ("***") if not parsable to do the comparison.
   */
  @Test(expected = AssertJDBException.class)
  public void should_fail_because_string_is_not_parseable() throws Exception {
    Values.areEqual(getValue(null, 1), (Object) "***");
  }

  /**
   * This method tests the {@code areEqual} method for {@code TimeValue}s.
   */
  @Test
  public void test_are_equal_for_times_in_string() throws Exception {
    assertThat(Values.areEqual(getValue(null, Time.valueOf("09:01:06")), (Object) "09:01:06")).isTrue();
    assertThat(Values.areEqual(getValue(null, Time.valueOf("09:01:00")), (Object) "09:01:00")).isTrue();
    assertThat(Values.areEqual(getValue(null, Time.valueOf("09:01:00")), (Object) "09:01")).isTrue();

    assertThat(Values.areEqual(getValue(null, Time.valueOf("09:01:06")), (Object) "09:01:05")).isFalse();
    assertThat(Values.areEqual(getValue(null, Time.valueOf("09:01:06")), (Object) "09:02:06")).isFalse();
    assertThat(Values.areEqual(getValue(null, Time.valueOf("09:01:06")), (Object) "10:01:06")).isFalse();
    assertThat(Values.areEqual(getValue(null, Time.valueOf("09:01:06")), (Object) "09:01:06.000000003")).isFalse();
  }

  /**
   * This method should fail because the expected value ("***") is not parsable to do the comparison.
   */
  @Test(expected = AssertJDBException.class)
  public void should_fail_because_string_is_not_parseable_in_time() throws Exception {
    Values.areEqual(getValue(null, Time.valueOf("09:01:06")), (Object) "***");
  }

  /**
   * This method tests the {@code areEqual} method for {@code TimeValue}s.
   */
  @Test
  public void test_are_equal_for_dates_in_string() throws Exception {
    assertThat(Values.areEqual(getValue(null, Date.valueOf("2007-12-23")), (Object) "2007-12-23")).isTrue();
    assertThat(Values.areEqual(getValue(null, Date.valueOf("2007-12-23")), (Object) "2007-12-23T00:00")).isTrue();
    assertThat(Values.areEqual(getValue(null, Date.valueOf("2007-12-23")), (Object) "2007-12-23T00:00:00")).isTrue();
    assertThat(Values.areEqual(getValue(null, Date.valueOf("2007-12-23")), (Object) "2007-12-23T00:00:00.000000000")).isTrue();

    assertThat(Values.areEqual(getValue(null, Date.valueOf("2007-12-23")), (Object) "2007-12-24")).isFalse();
    assertThat(Values.areEqual(getValue(null, Date.valueOf("2007-12-23")), (Object) "2007-01-23")).isFalse();
    assertThat(Values.areEqual(getValue(null, Date.valueOf("2007-12-23")), (Object) "2008-12-23")).isFalse();
    assertThat(Values.areEqual(getValue(null, Date.valueOf("2007-12-23")), (Object) "2007-12-23T00:01")).isFalse();
    assertThat(Values.areEqual(getValue(null, Date.valueOf("2007-12-23")), (Object) "2007-12-23T01:00")).isFalse();
    assertThat(Values.areEqual(getValue(null, Date.valueOf("2007-12-23")), (Object) "2007-12-24T00:00")).isFalse();
    assertThat(Values.areEqual(getValue(null, Date.valueOf("2007-12-23")), (Object) "2007-01-23T00:00")).isFalse();
    assertThat(Values.areEqual(getValue(null, Date.valueOf("2007-12-23")), (Object) "2008-12-23T00:00")).isFalse();
    assertThat(Values.areEqual(getValue(null, Date.valueOf("2007-12-23")), (Object) "2007-12-23T00:00:01")).isFalse();
    assertThat(Values.areEqual(getValue(null, Date.valueOf("2007-12-23")), (Object) "2007-12-23T00:01:00")).isFalse();
    assertThat(Values.areEqual(getValue(null, Date.valueOf("2007-12-23")), (Object) "2007-12-23T01:00:00")).isFalse();
    assertThat(Values.areEqual(getValue(null, Date.valueOf("2007-12-23")), (Object) "2007-12-24T00:00:00")).isFalse();
    assertThat(Values.areEqual(getValue(null, Date.valueOf("2007-12-23")), (Object) "2007-01-23T00:00:00")).isFalse();
    assertThat(Values.areEqual(getValue(null, Date.valueOf("2007-12-23")), (Object) "2008-12-23T00:00:00")).isFalse();
    assertThat(Values.areEqual(getValue(null, Date.valueOf("2007-12-23")), (Object) "2007-12-23T00:00:00.000000001")).isFalse();
    assertThat(Values.areEqual(getValue(null, Date.valueOf("2007-12-23")), (Object) "2007-12-23T00:00:01.000000000")).isFalse();
    assertThat(Values.areEqual(getValue(null, Date.valueOf("2007-12-23")), (Object) "2007-12-23T00:01:00.000000000")).isFalse();
    assertThat(Values.areEqual(getValue(null, Date.valueOf("2007-12-23")), (Object) "2007-12-23T01:00:00.000000000")).isFalse();
    assertThat(Values.areEqual(getValue(null, Date.valueOf("2007-12-23")), (Object) "2007-12-24T00:00:00.000000000")).isFalse();
    assertThat(Values.areEqual(getValue(null, Date.valueOf("2007-12-23")), (Object) "2007-01-23T00:00:00.000000000")).isFalse();
    assertThat(Values.areEqual(getValue(null, Date.valueOf("2007-12-23")), (Object) "2008-12-23T00:00:00.000000000")).isFalse();
  }

  /**
   * This method should fail because the expected value ("***") is not parsable to do the comparison.
   */
  @Test(expected = AssertJDBException.class)
  public void should_fail_because_string_is_not_parseable_in_date() throws Exception {
    Values.areEqual(getValue(null, Date.valueOf("2007-12-23")), (Object) "***");
  }

  /**
   * This method tests the {@code areEqual} method for {@code TimeValue}s.
   */
  @Test
  public void test_are_equal_for_datestimes() throws Exception {
    assertThat(Values.areEqual(getValue(null, Timestamp.valueOf("2007-12-23 09:01:06.000000003")),
                               (Object) "2007-12-23T09:01:06.000000003")).isTrue();
    assertThat(Values.areEqual(getValue(null, Timestamp.valueOf("2007-12-23 00:00:00.000000000")),
                               (Object) "2007-12-23T00:00:00.000000000")).isTrue();
    assertThat(Values.areEqual(getValue(null, Timestamp.valueOf("2007-12-23 00:00:00")), (Object) "2007-12-23T00:00:00")).isTrue();
    assertThat(Values.areEqual(getValue(null, Timestamp.valueOf("2007-12-23 00:00:00")), (Object) "2007-12-23T00:00")).isTrue();
    assertThat(Values.areEqual(getValue(null, Timestamp.valueOf("2007-12-23 00:00:00")), (Object) "2007-12-23")).isTrue();

    assertThat(Values.areEqual(getValue(null, Timestamp.valueOf("2007-12-23 09:01:06.000000003")),
                               (Object) "2007-12-23T09:01:06.000000004")).isFalse();
    assertThat(Values.areEqual(getValue(null, Timestamp.valueOf("2007-12-23 09:01:06.000000003")),
                               (Object) "2007-12-23T09:01:07.000000003")).isFalse();
    assertThat(Values.areEqual(getValue(null, Timestamp.valueOf("2007-12-23 09:01:06.000000003")),
                               (Object) "2007-12-23T09:02:06.000000003")).isFalse();
    assertThat(Values.areEqual(getValue(null, Timestamp.valueOf("2007-12-23 09:01:06.000000003")),
                               (Object) "2007-12-23T10:01:06.000000003")).isFalse();
    assertThat(Values.areEqual(getValue(null, Timestamp.valueOf("2007-12-23 09:01:06.000000003")),
                               (Object) "2007-12-24T09:01:06.000000003")).isFalse();
    assertThat(Values.areEqual(getValue(null, Timestamp.valueOf("2007-12-23 09:01:06.000000003")),
                               (Object) "2007-01-23T09:01:06.000000003")).isFalse();
    assertThat(Values.areEqual(getValue(null, Timestamp.valueOf("2007-12-23 09:01:06.000000003")),
                               (Object) "2008-12-23T09:01:06.000000003")).isFalse();

    assertThat(Values.areEqual(getValue(null, Timestamp.valueOf("2007-12-23 00:00:00.000000000")),
                               (Object) "2007-12-23T00:00:00.000000001")).isFalse();
    assertThat(Values.areEqual(getValue(null, Timestamp.valueOf("2007-12-23 00:00:00.000000000")),
                               (Object) "2007-12-23T00:00:01.000000000")).isFalse();
    assertThat(Values.areEqual(getValue(null, Timestamp.valueOf("2007-12-23 00:00:00.000000000")),
                               (Object) "2007-12-23T00:01:00.000000000")).isFalse();
    assertThat(Values.areEqual(getValue(null, Timestamp.valueOf("2007-12-23 00:00:00.000000000")),
                               (Object) "2007-12-23T01:00:00.000000000")).isFalse();
    assertThat(Values.areEqual(getValue(null, Timestamp.valueOf("2007-12-23 00:00:00.000000000")),
                               (Object) "2007-12-24T00:00:00.000000000")).isFalse();
    assertThat(Values.areEqual(getValue(null, Timestamp.valueOf("2007-12-23 00:00:00.000000000")),
                               (Object) "2007-01-23T00:00:00.000000000")).isFalse();
    assertThat(Values.areEqual(getValue(null, Timestamp.valueOf("2007-12-23 00:00:00.000000000")),
                               (Object) "2008-12-23T00:00:00.000000000")).isFalse();

    assertThat(Values.areEqual(getValue(null, Timestamp.valueOf("2007-12-23 00:00:00")), (Object) "2007-12-23T00:00:00.000000001"))
            .isFalse();
    assertThat(Values.areEqual(getValue(null, Timestamp.valueOf("2007-12-23 00:00:00")), (Object) "2007-12-23T00:00:01.000000000"))
            .isFalse();
    assertThat(Values.areEqual(getValue(null, Timestamp.valueOf("2007-12-23 00:00:00")), (Object) "2007-12-23T00:01:00.000000000"))
            .isFalse();
    assertThat(Values.areEqual(getValue(null, Timestamp.valueOf("2007-12-23 00:00:00")), (Object) "2007-12-23T01:00:00.000000000"))
            .isFalse();
    assertThat(Values.areEqual(getValue(null, Timestamp.valueOf("2007-12-23 00:00:00")), (Object) "2007-12-24T00:00:00.000000000"))
            .isFalse();
    assertThat(Values.areEqual(getValue(null, Timestamp.valueOf("2007-12-23 00:00:00")), (Object) "2007-01-23T00:00:00.000000000"))
            .isFalse();
    assertThat(Values.areEqual(getValue(null, Timestamp.valueOf("2007-12-23 00:00:00")), (Object) "2008-12-23T00:00:00.000000000"))
            .isFalse();
  }

  /**
   * This method should fail because the expected value ("***") is not parsable to do the comparison.
   */
  @Test(expected = AssertJDBException.class)
  public void should_fail_because_string_is_not_parseable_in_datetime() throws Exception {
    Values.areEqual(getValue(null, Timestamp.valueOf("2007-12-23 09:01:06.000000003")), (Object) "***");
  }
}
