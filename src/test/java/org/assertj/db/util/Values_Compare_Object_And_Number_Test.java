/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2014 the original author or authors.
 */
package org.assertj.db.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Test;

/**
 * Tests on {@code compare} method for {@code Number}s.
 * 
 * @author Régis Pouiller
 * 
 */
public class Values_Compare_Object_And_Number_Test {

  /**
   * This method tests the {@code compare} method for {@code BigInteger}s.
   */
  @Test
  public void test_compare_for_biginteger() {
    assertThat(Values.compare(1, new BigInteger("1"))).isEqualTo(0);
    assertThat(Values.compare(new BigInteger("1"), new BigInteger("1"))).isEqualTo(0);
    assertThat(Values.compare(1, new BigInteger("2"))).isEqualTo(-1);
    assertThat(Values.compare(new BigInteger("1"), new BigInteger("2"))).isEqualTo(-1);
    assertThat(Values.compare(2, new BigInteger("1"))).isEqualTo(1);
    assertThat(Values.compare(new BigInteger("2"), new BigInteger("1"))).isEqualTo(1);
  }

  /**
   * This method should fail because the value ("-") can not be compared to a {@code BigInteger}.
   */
  @Test(expected = AssertJDBException.class)
  public void should_fail_because_value_can_not_be_compared_to_a_biginteger() {
    Values.compare("-", new BigInteger("1"));
  }

  /**
   * This method tests the {@code compare} method for {@code BigDecimal}s.
   */
  @Test
  public void test_compare_for_bigdecimal() {
    assertThat(Values.compare(1, new BigDecimal("1"))).isEqualTo(0);
    assertThat(Values.compare(new BigDecimal("1"), new BigDecimal("1"))).isEqualTo(0);
    assertThat(Values.compare(1, new BigDecimal("2"))).isEqualTo(-1);
    assertThat(Values.compare(new BigDecimal("1"), new BigDecimal("2"))).isEqualTo(-1);
    assertThat(Values.compare(2, new BigDecimal("1"))).isEqualTo(1);
    assertThat(Values.compare(new BigDecimal("2"), new BigDecimal("1"))).isEqualTo(1);
  }

  /**
   * This method should fail because the value ("-") can not be compared to a {@code BigDecimal}.
   */
  @Test(expected = AssertJDBException.class)
  public void should_fail_because_value_can_not_be_compared_to_a_bigdecimal() {
    Values.compare("-", new BigDecimal("1"));
  }

  /**
   * This method tests the {@code compare} method for another type of value and {@code Float}s.
   */
  @Test
  public void test_compare_for_other_and_float() {
    assertThat(Values.compare(1F, 1)).isEqualTo(0);
    assertThat(Values.compare(1F, 2)).isEqualTo(-1);
    assertThat(Values.compare(2F, 1)).isEqualTo(1);
    assertThat(Values.compare(1F, 1L)).isEqualTo(0);
    assertThat(Values.compare(1F, 2L)).isEqualTo(-1);
    assertThat(Values.compare(2F, 1L)).isEqualTo(1);
    assertThat(Values.compare(1F, 1F)).isEqualTo(0);
    assertThat(Values.compare(1F, 2F)).isEqualTo(-1);
    assertThat(Values.compare(2F, 1F)).isEqualTo(1);
    assertThat(Values.compare(1F, 1D)).isEqualTo(0);
    assertThat(Values.compare(1F, 2D)).isEqualTo(-1);
    assertThat(Values.compare(2F, 1D)).isEqualTo(1);
    assertThat(Values.compare(1.5F, 1.5F)).isEqualTo(0);
    assertThat(Values.compare(1.5F, 2.5F)).isEqualTo(-1);
    assertThat(Values.compare(2.5F, 1.5F)).isEqualTo(1);
    assertThat(Values.compare(1.5F, 1.5D)).isEqualTo(0);
    assertThat(Values.compare(1.5F, 2.5D)).isEqualTo(-1);
    assertThat(Values.compare(2.5F, 1.5D)).isEqualTo(1);
  }

  /**
   * This method tests the {@code compare} method for another type of value and {@code Double}s.
   */
  @Test
  public void test_compare_for_other_and_double() {
    assertThat(Values.compare(1D, 1)).isEqualTo(0);
    assertThat(Values.compare(1D, 2)).isEqualTo(-1);
    assertThat(Values.compare(2D, 1)).isEqualTo(1);
    assertThat(Values.compare(1D, 1L)).isEqualTo(0);
    assertThat(Values.compare(1D, 2L)).isEqualTo(-1);
    assertThat(Values.compare(2D, 1L)).isEqualTo(1);
    assertThat(Values.compare(1D, 1F)).isEqualTo(0);
    assertThat(Values.compare(1D, 2F)).isEqualTo(-1);
    assertThat(Values.compare(2D, 1F)).isEqualTo(1);
    assertThat(Values.compare(1D, 1D)).isEqualTo(0);
    assertThat(Values.compare(1D, 2D)).isEqualTo(-1);
    assertThat(Values.compare(2D, 1D)).isEqualTo(1);
    assertThat(Values.compare(1.5D, 1.5F)).isEqualTo(0);
    assertThat(Values.compare(1.5D, 2.5F)).isEqualTo(-1);
    assertThat(Values.compare(2.5D, 1.5F)).isEqualTo(1);
    assertThat(Values.compare(1.5D, 1.5D)).isEqualTo(0);
    assertThat(Values.compare(1.5D, 2.5D)).isEqualTo(-1);
    assertThat(Values.compare(2.5D, 1.5D)).isEqualTo(1);
  }

  /**
   * This method tests the {@code compare} method for another type of value and {@code BigInteger}s.
   */
  @Test
  public void test_compare_for_other_and_biginteger() {
    assertThat(Values.compare(new BigInteger("1"), 1)).isEqualTo(0);
    assertThat(Values.compare(new BigInteger("1"), 2)).isEqualTo(-1);
    assertThat(Values.compare(new BigInteger("2"), 1)).isEqualTo(1);
    assertThat(Values.compare(new BigInteger("1"), 1L)).isEqualTo(0);
    assertThat(Values.compare(new BigInteger("1"), 2L)).isEqualTo(-1);
    assertThat(Values.compare(new BigInteger("2"), 1L)).isEqualTo(1);
  }

  /**
   * This method tests the {@code compare} method for another type of value and {@code BigDecimal}s.
   */
  @Test
  public void test_compare_for_other_and_bigdecimal() {
    assertThat(Values.compare(new BigDecimal("1"), 1)).isEqualTo(0);
    assertThat(Values.compare(new BigDecimal("1"), 2)).isEqualTo(-1);
    assertThat(Values.compare(new BigDecimal("2"), 1)).isEqualTo(1);
    assertThat(Values.compare(new BigDecimal("1"), 1L)).isEqualTo(0);
    assertThat(Values.compare(new BigDecimal("1"), 2L)).isEqualTo(-1);
    assertThat(Values.compare(new BigDecimal("2"), 1L)).isEqualTo(1);
    assertThat(Values.compare(new BigDecimal("1"), 1F)).isEqualTo(0);
    assertThat(Values.compare(new BigDecimal("1"), 2F)).isEqualTo(-1);
    assertThat(Values.compare(new BigDecimal("2"), 1F)).isEqualTo(1);
    assertThat(Values.compare(new BigDecimal("1"), 1D)).isEqualTo(0);
    assertThat(Values.compare(new BigDecimal("1"), 2D)).isEqualTo(-1);
    assertThat(Values.compare(new BigDecimal("2"), 1D)).isEqualTo(1);
    assertThat(Values.compare(new BigDecimal("1.5"), 1.5F)).isEqualTo(0);
    assertThat(Values.compare(new BigDecimal("1.5"), 2.5F)).isEqualTo(-1);
    assertThat(Values.compare(new BigDecimal("2.5"), 1.5F)).isEqualTo(1);
    assertThat(Values.compare(new BigDecimal("1.5"), 1.5D)).isEqualTo(0);
    assertThat(Values.compare(new BigDecimal("1.5"), 2.5D)).isEqualTo(-1);
    assertThat(Values.compare(new BigDecimal("2.5"), 1.5D)).isEqualTo(1);
  }

  /**
   * This method tests the {@code compare} method for another type of value and {@code Byte}s.
   */
  @Test
  public void test_compare_for_other_and_byte() {
    assertThat(Values.compare((byte) 1, 1)).isEqualTo(0);
    assertThat(Values.compare((byte) 1, 2)).isEqualTo(-1);
    assertThat(Values.compare((byte) 2, 1)).isEqualTo(1);
    assertThat(Values.compare((byte) 1, 1L)).isEqualTo(0);
    assertThat(Values.compare((byte) 1, 2L)).isEqualTo(-1);
    assertThat(Values.compare((byte) 2, 1L)).isEqualTo(1);
    assertThat(Values.compare((byte) 1, 1F)).isEqualTo(0);
    assertThat(Values.compare((byte) 1, 2F)).isEqualTo(-1);
    assertThat(Values.compare((byte) 2, 1F)).isEqualTo(1);
    assertThat(Values.compare((byte) 1, 1D)).isEqualTo(0);
    assertThat(Values.compare((byte) 1, 2D)).isEqualTo(-1);
    assertThat(Values.compare((byte) 2, 1D)).isEqualTo(1);
  }

  /**
   * This method tests the {@code compare} method for another type of value and {@code Short}s.
   */
  @Test
  public void test_compare_for_other_and_short() {
    assertThat(Values.compare((short) 1, 1)).isEqualTo(0);
    assertThat(Values.compare((short) 1, 2)).isEqualTo(-1);
    assertThat(Values.compare((short) 2, 1)).isEqualTo(1);
    assertThat(Values.compare((short) 1, 1L)).isEqualTo(0);
    assertThat(Values.compare((short) 1, 2L)).isEqualTo(-1);
    assertThat(Values.compare((short) 2, 1L)).isEqualTo(1);
    assertThat(Values.compare((short) 1, 1F)).isEqualTo(0);
    assertThat(Values.compare((short) 1, 2F)).isEqualTo(-1);
    assertThat(Values.compare((short) 2, 1F)).isEqualTo(1);
    assertThat(Values.compare((short) 1, 1D)).isEqualTo(0);
    assertThat(Values.compare((short) 1, 2D)).isEqualTo(-1);
    assertThat(Values.compare((short) 2, 1D)).isEqualTo(1);
  }

  /**
   * This method tests the {@code compare} method for another type of value and {@code Integer}s.
   */
  @Test
  public void test_compare_for_other_and_integer() {
    assertThat(Values.compare((int) 1, 1)).isEqualTo(0);
    assertThat(Values.compare((int) 1, 2)).isEqualTo(-1);
    assertThat(Values.compare((int) 2, 1)).isEqualTo(1);
    assertThat(Values.compare((int) 1, 1L)).isEqualTo(0);
    assertThat(Values.compare((int) 1, 2L)).isEqualTo(-1);
    assertThat(Values.compare((int) 2, 1L)).isEqualTo(1);
    assertThat(Values.compare((int) 1, 1F)).isEqualTo(0);
    assertThat(Values.compare((int) 1, 2F)).isEqualTo(-1);
    assertThat(Values.compare((int) 2, 1F)).isEqualTo(1);
    assertThat(Values.compare((int) 1, 1D)).isEqualTo(0);
    assertThat(Values.compare((int) 1, 2D)).isEqualTo(-1);
    assertThat(Values.compare((int) 2, 1D)).isEqualTo(1);
  }

  /**
   * This method tests the {@code compare} method for another type of value and {@code Long}s.
   */
  @Test
  public void test_compare_for_other_and_long() {
    assertThat(Values.compare((long) 1, 1)).isEqualTo(0);
    assertThat(Values.compare((long) 1, 2)).isEqualTo(-1);
    assertThat(Values.compare((long) 2, 1)).isEqualTo(1);
    assertThat(Values.compare((long) 1, 1L)).isEqualTo(0);
    assertThat(Values.compare((long) 1, 2L)).isEqualTo(-1);
    assertThat(Values.compare((long) 2, 1L)).isEqualTo(1);
    assertThat(Values.compare((long) 1, 1F)).isEqualTo(0);
    assertThat(Values.compare((long) 1, 2F)).isEqualTo(-1);
    assertThat(Values.compare((long) 2, 1F)).isEqualTo(1);
    assertThat(Values.compare((long) 1, 1D)).isEqualTo(0);
    assertThat(Values.compare((long) 1, 2D)).isEqualTo(-1);
    assertThat(Values.compare((long) 2, 1D)).isEqualTo(1);
  }

  /**
   * This method should fail because the value ("-") can not be compared to a {@code Number}.
   */
  @Test(expected = AssertJDBException.class)
  public void should_fail_because_value_can_not_be_compared_to_a_number() {
    assertThat(Values.compare("-", 1));
  }

}
