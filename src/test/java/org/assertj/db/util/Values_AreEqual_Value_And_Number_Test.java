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
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests on {@code areEqual} method for {@code Number}s.
 * 
 * @author Régis Pouiller
 * 
 */
public class Values_AreEqual_Value_And_Number_Test extends AbstractTest {

  /**
   * This method tests the {@code areEqual} method for {@code BigInteger}s.
   */
  @Test
  public void test_are_equal_for_biginteger() throws Exception {
    assertThat(Values.areEqual(getValue(null, 1), new BigInteger("1"))).isTrue();
    assertThat(Values.areEqual(getValue(null, new BigInteger("1")), new BigInteger("1"))).isTrue();
    assertThat(Values.areEqual(getValue(null, 1), new BigInteger("2"))).isFalse();
    assertThat(Values.areEqual(getValue(null, new BigInteger("1")), new BigInteger("2"))).isFalse();
    assertThat(Values.areEqual(getValue(null, null), (BigInteger) null)).isTrue();
    assertThat(Values.areEqual(getValue(null, new BigInteger("1")), (BigInteger) null)).isFalse();
  }

  /**
   * This method should fail because the value ("-") can not be compared to a {@code BigInteger}.
   */
  @Test(expected = AssertJDBException.class)
  public void should_fail_because_value_can_not_be_compared_to_a_biginteger() throws Exception {
    Values.areEqual(getValue(null, "-"), new BigInteger("1"));
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
    assertThat(Values.areEqual(getValue(null, null), (BigDecimal) null)).isTrue();
    assertThat(Values.areEqual(getValue(null, new BigDecimal("1")), (BigDecimal) null)).isFalse();
  }

  /**
   * This method should fail because the value ("-") can not be compared to a {@code BigDecimal}.
   */
  @Test(expected = AssertJDBException.class)
  public void should_fail_because_value_can_not_be_compared_to_a_bigdecimal() throws Exception {
    Values.areEqual(getValue(null, "-"), new BigDecimal("1"));
  }

  /**
   * This method tests the {@code areEqual} method for another type of value and {@code Float}s.
   */
  @Test
  public void test_are_equal_for_other_and_float() throws Exception {
    assertThat(Values.areEqual(getValue(null, 1F), 1)).isTrue();
    assertThat(Values.areEqual(getValue(null, 2F), 1)).isFalse();
    assertThat(Values.areEqual(getValue(null, 1F), 1L)).isTrue();
    assertThat(Values.areEqual(getValue(null, 2F), 1L)).isFalse();
    assertThat(Values.areEqual(getValue(null, 1F), 1F)).isTrue();
    assertThat(Values.areEqual(getValue(null, 2F), 1F)).isFalse();
    assertThat(Values.areEqual(getValue(null, 1F), 1D)).isTrue();
    assertThat(Values.areEqual(getValue(null, 2F), 1D)).isFalse();
    assertThat(Values.areEqual(getValue(null, 1.5F), 1.5F)).isTrue();
    assertThat(Values.areEqual(getValue(null, 2.5F), 1.5F)).isFalse();
    assertThat(Values.areEqual(getValue(null, 1.5F), 1.5D)).isTrue();
    assertThat(Values.areEqual(getValue(null, 2.5F), 1.5D)).isFalse();
    assertThat(Values.areEqual(getValue(null, null), (Float) null)).isTrue();
    assertThat(Values.areEqual(getValue(null, 2.5F), (Float) null)).isFalse();
  }

  /**
   * This method tests the {@code areEqual} method for another type of value and {@code Double}s.
   */
  @Test
  public void test_are_equal_for_other_and_double() throws Exception {
    assertThat(Values.areEqual(getValue(null, 1D), 1)).isTrue();
    assertThat(Values.areEqual(getValue(null, 2D), 1)).isFalse();
    assertThat(Values.areEqual(getValue(null, 1D), 1L)).isTrue();
    assertThat(Values.areEqual(getValue(null, 2D), 1L)).isFalse();
    assertThat(Values.areEqual(getValue(null, 1D), 1F)).isTrue();
    assertThat(Values.areEqual(getValue(null, 2D), 1F)).isFalse();
    assertThat(Values.areEqual(getValue(null, 1D), 1D)).isTrue();
    assertThat(Values.areEqual(getValue(null, 2D), 1D)).isFalse();
    assertThat(Values.areEqual(getValue(null, 1.5D), 1.5F)).isTrue();
    assertThat(Values.areEqual(getValue(null, 2.5D), 1.5F)).isFalse();
    assertThat(Values.areEqual(getValue(null, 1.5D), 1.5D)).isTrue();
    assertThat(Values.areEqual(getValue(null, 2.5D), 1.5D)).isFalse();
    assertThat(Values.areEqual(getValue(null, null), (Double) null)).isTrue();
    assertThat(Values.areEqual(getValue(null, 2.5D), (Double) null)).isFalse();
  }

  /**
   * This method tests the {@code areEqual} method for another type of value and {@code BigInteger}s.
   */
  @Test
  public void test_are_equal_for_other_and_biginteger() throws Exception {
    assertThat(Values.areEqual(getValue(null, new BigInteger("1")), 1)).isTrue();
    assertThat(Values.areEqual(getValue(null, new BigInteger("2")), 1)).isFalse();
    assertThat(Values.areEqual(getValue(null, new BigInteger("1")), 1L)).isTrue();
    assertThat(Values.areEqual(getValue(null, new BigInteger("2")), 1L)).isFalse();
  }

  /**
   * This method tests the {@code areEqual} method for another type of value and {@code BigDecimal}s.
   */
  @Test
  public void test_are_equal_for_other_and_bigdecimal() throws Exception {
    assertThat(Values.areEqual(getValue(null, new BigDecimal("1")), 1)).isTrue();
    assertThat(Values.areEqual(getValue(null, new BigDecimal("2")), 1)).isFalse();
    assertThat(Values.areEqual(getValue(null, new BigDecimal("1")), 1L)).isTrue();
    assertThat(Values.areEqual(getValue(null, new BigDecimal("2")), 1L)).isFalse();
    assertThat(Values.areEqual(getValue(null, new BigDecimal("1")), 1F)).isTrue();
    assertThat(Values.areEqual(getValue(null, new BigDecimal("2")), 1F)).isFalse();
    assertThat(Values.areEqual(getValue(null, new BigDecimal("1")), 1D)).isTrue();
    assertThat(Values.areEqual(getValue(null, new BigDecimal("2")), 1D)).isFalse();
    assertThat(Values.areEqual(getValue(null, new BigDecimal("1.5")), 1.5F)).isTrue();
    assertThat(Values.areEqual(getValue(null, new BigDecimal("2.5")), 1.5F)).isFalse();
    assertThat(Values.areEqual(getValue(null, new BigDecimal("1.5")), 1.5D)).isTrue();
    assertThat(Values.areEqual(getValue(null, new BigDecimal("2.5")), 1.5D)).isFalse();
  }

  /**
   * This method tests the {@code areEqual} method for another type of value and {@code Byte}s.
   */
  @Test
  public void test_are_equal_for_other_and_byte() throws Exception {
    assertThat(Values.areEqual(getValue(null, (byte) 1), 1)).isTrue();
    assertThat(Values.areEqual(getValue(null, (byte) 2), 1)).isFalse();
    assertThat(Values.areEqual(getValue(null, (byte) 1), 1L)).isTrue();
    assertThat(Values.areEqual(getValue(null, (byte) 2), 1L)).isFalse();
    assertThat(Values.areEqual(getValue(null, (byte) 1), 1F)).isTrue();
    assertThat(Values.areEqual(getValue(null, (byte) 2), 1F)).isFalse();
    assertThat(Values.areEqual(getValue(null, (byte) 1), 1D)).isTrue();
    assertThat(Values.areEqual(getValue(null, (byte) 2), 1D)).isFalse();
  }

  /**
   * This method tests the {@code areEqual} method for another type of value and {@code Short}s.
   */
  @Test
  public void test_are_equal_for_other_and_short() throws Exception {
    assertThat(Values.areEqual(getValue(null, (short) 1), 1)).isTrue();
    assertThat(Values.areEqual(getValue(null, (short) 2), 1)).isFalse();
    assertThat(Values.areEqual(getValue(null, (short) 1), 1L)).isTrue();
    assertThat(Values.areEqual(getValue(null, (short) 2), 1L)).isFalse();
    assertThat(Values.areEqual(getValue(null, (short) 1), 1F)).isTrue();
    assertThat(Values.areEqual(getValue(null, (short) 2), 1F)).isFalse();
    assertThat(Values.areEqual(getValue(null, (short) 1), 1D)).isTrue();
    assertThat(Values.areEqual(getValue(null, (short) 2), 1D)).isFalse();
  }

  /**
   * This method tests the {@code areEqual} method for another type of value and {@code Integer}s.
   */
  @Test
  public void test_are_equal_for_other_and_integer() throws Exception {
    assertThat(Values.areEqual(getValue(null, 1), 1)).isTrue();
    assertThat(Values.areEqual(getValue(null, 2), 1)).isFalse();
    assertThat(Values.areEqual(getValue(null, 1), 1L)).isTrue();
    assertThat(Values.areEqual(getValue(null, 2), 1L)).isFalse();
    assertThat(Values.areEqual(getValue(null, 1), 1F)).isTrue();
    assertThat(Values.areEqual(getValue(null, 2), 1F)).isFalse();
    assertThat(Values.areEqual(getValue(null, 1), 1D)).isTrue();
    assertThat(Values.areEqual(getValue(null, 2), 1D)).isFalse();
  }

  /**
   * This method tests the {@code areEqual} method for another type of value and {@code Long}s.
   */
  @Test
  public void test_are_equal_for_other_and_long() throws Exception {
    assertThat(Values.areEqual(getValue(null, (long) 1), 1)).isTrue();
    assertThat(Values.areEqual(getValue(null, (long) 2), 1)).isFalse();
    assertThat(Values.areEqual(getValue(null, (long) 1), 1L)).isTrue();
    assertThat(Values.areEqual(getValue(null, (long) 2), 1L)).isFalse();
    assertThat(Values.areEqual(getValue(null, (long) 1), 1F)).isTrue();
    assertThat(Values.areEqual(getValue(null, (long) 2), 1F)).isFalse();
    assertThat(Values.areEqual(getValue(null, (long) 1), 1D)).isTrue();
    assertThat(Values.areEqual(getValue(null, (long) 2), 1D)).isFalse();
  }

  /**
   * This method tests the {@code areEqual} method for another type of values.
   */
  @Test
  public void test_are_equal_for_other_and_other() throws Exception {
    assertThat(Values.areEqual(getValue(null, "-"), 1)).isFalse();
  }
}
