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
 * Copyright 2015-2020 the original author or authors.
 */
package org.assertj.db.util;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.exception.AssertJDBException;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests on {@code areClose} method for {@code Number}s.
 *
 * @author Régis Pouiller
 *
 */
public class Values_AreClose_Value_And_Number_Test extends AbstractTest {

  /**
   * This method tests the {@code areClose} method for {@code BigInteger}s.
   */
  @Test
  public void test_are_close_for_biginteger() throws Exception {
    assertThat(Values.areClose(getValue(null, 1), new BigInteger("1"), 0)).as("close1").isTrue();
    assertThat(Values.areClose(getValue(null, new BigInteger("1")), new BigInteger("1"), 0)).as("close2").isTrue();
    assertThat(Values.areClose(getValue(null, 1), new BigInteger("2"), 0)).as("close3").isFalse();
    assertThat(Values.areClose(getValue(null, new BigInteger("1")), new BigInteger("2"), 0)).as("close4").isFalse();
    assertThat(Values.areClose(getValue(null, new BigInteger("1")), new BigInteger("0"), 0)).as("close4").isFalse();
    assertThat(Values.areClose(getValue(null, null), (BigInteger) null, 0)).as("close5").isTrue();
    assertThat(Values.areClose(getValue(null, new BigInteger("1")), (BigInteger) null, 0)).as("close6").isFalse();

    assertThat(Values.areClose(getValue(null, 1), new BigInteger("1"), 1)).as("close7").isTrue();
    assertThat(Values.areClose(getValue(null, new BigInteger("1")), new BigInteger("1"), 1)).as("close8").isTrue();
    assertThat(Values.areClose(getValue(null, 1), new BigInteger("2"), 1)).as("close9").isTrue();
    assertThat(Values.areClose(getValue(null, new BigInteger("1")), new BigInteger("2"), 1)).as("close10").isTrue();
    assertThat(Values.areClose(getValue(null, new BigInteger("1")), new BigInteger("0"), 1)).as("close10").isTrue();
    assertThat(Values.areClose(getValue(null, null), (BigInteger) null, 1)).as("close11").isTrue();
    assertThat(Values.areClose(getValue(null, new BigInteger("1")), (BigInteger) null, 1)).as("close12").isFalse();

    assertThat(Values.areClose(getValue(null, 0), new BigInteger("1"), 1)).as("close13").isTrue();
    assertThat(Values.areClose(getValue(null, new BigInteger("0")), new BigInteger("1"), 1)).as("close14").isTrue();
    assertThat(Values.areClose(getValue(null, 0), new BigInteger("2"), 1)).as("close15").isFalse();
    assertThat(Values.areClose(getValue(null, new BigInteger("0")), new BigInteger("2"), 1)).as("close16").isFalse();
    assertThat(Values.areClose(getValue(null, new BigInteger("0")), new BigInteger("-2"), 1)).as("close16").isFalse();
    assertThat(Values.areClose(getValue(null, null), (BigInteger) null, 1)).as("close17").isTrue();
    assertThat(Values.areClose(getValue(null, new BigInteger("0")), (BigInteger) null, 1)).as("close18").isFalse();
  }

  /**
   * This method should fail because the value ("-") can not be compared to a {@code BigInteger}.
   */
  @Test(expected = AssertJDBException.class)
  public void should_fail_because_value_can_not_be_compared_to_a_biginteger() throws Exception {
    Values.areClose(getValue(null, "-"), new BigInteger("1"), 1);
  }

  /**
   * This method tests the {@code areClose} method for {@code BigDecimal}s.
   */
  @Test
  public void test_are_close_for_bigdecimal() throws Exception {
    assertThat(Values.areClose(getValue(null, 1), new BigDecimal("1"), 0)).as("close1").isTrue();
    assertThat(Values.areClose(getValue(null, new BigDecimal("1")), new BigDecimal("1"), 0)).as("close2").isTrue();
    assertThat(Values.areClose(getValue(null, 1), new BigDecimal("2"), 0)).as("close3").isFalse();
    assertThat(Values.areClose(getValue(null, new BigDecimal("1")), new BigDecimal("2"), 0)).as("close4").isFalse();
    assertThat(Values.areClose(getValue(null, new BigDecimal("1")), new BigDecimal("0"), 0)).as("close4").isFalse();
    assertThat(Values.areClose(getValue(null, null), (BigDecimal) null, 0)).as("close5").isTrue();
    assertThat(Values.areClose(getValue(null, new BigDecimal("1")), (BigDecimal) null, 0)).as("close6").isFalse();

    assertThat(Values.areClose(getValue(null, 1), new BigDecimal("1"), 1)).as("close7").isTrue();
    assertThat(Values.areClose(getValue(null, new BigDecimal("1")), new BigDecimal("1"), 1)).as("close8").isTrue();
    assertThat(Values.areClose(getValue(null, 1), new BigDecimal("2"), 1)).as("close9").isTrue();
    assertThat(Values.areClose(getValue(null, new BigDecimal("1")), new BigDecimal("2"), 1)).as("close10").isTrue();
    assertThat(Values.areClose(getValue(null, new BigDecimal("1")), new BigDecimal("0"), 1)).as("close10").isTrue();
    assertThat(Values.areClose(getValue(null, null), (BigDecimal) null, 1)).as("close11").isTrue();
    assertThat(Values.areClose(getValue(null, new BigDecimal("1")), (BigDecimal) null, 1)).as("close12").isFalse();

    assertThat(Values.areClose(getValue(null, 0), new BigDecimal("1"), 1)).as("close13").isTrue();
    assertThat(Values.areClose(getValue(null, new BigDecimal("0")), new BigDecimal("1"), 1)).as("close14").isTrue();
    assertThat(Values.areClose(getValue(null, 0), new BigDecimal("2"), 1)).as("close15").isFalse();
    assertThat(Values.areClose(getValue(null, new BigDecimal("0")), new BigDecimal("2"), 1)).as("close16").isFalse();
    assertThat(Values.areClose(getValue(null, new BigDecimal("0")), new BigDecimal("-2"), 1)).as("close16").isFalse();
    assertThat(Values.areClose(getValue(null, null), (BigDecimal) null, 1)).as("close17").isTrue();
    assertThat(Values.areClose(getValue(null, new BigDecimal("0")), (BigDecimal) null, 1)).as("close18").isFalse();
  }

  /**
   * This method should fail because the value ("-") can not be compared to a {@code BigDecimal}.
   */
  @Test(expected = AssertJDBException.class)
  public void should_fail_because_value_can_not_be_compared_to_a_bigdecimal() throws Exception {
    Values.areClose(getValue(null, "-"), new BigDecimal("1"), 1);
  }

  /**
   * This method tests the {@code areClose} method for another type of value and {@code Float}s.
   */
  @Test
  public void test_are_close_for_other_and_float() throws Exception {
    assertThat(Values.areClose(getValue(null, 1F), 1, 0)).as("close1").isTrue();
    assertThat(Values.areClose(getValue(null, 2F), 1, 0)).as("close2").isFalse();
    assertThat(Values.areClose(getValue(null, 1F), 1L, 0)).as("close3").isTrue();
    assertThat(Values.areClose(getValue(null, 2F), 1L, 0)).as("close4").isFalse();
    assertThat(Values.areClose(getValue(null, 1F), 1F, 0)).as("close5").isTrue();
    assertThat(Values.areClose(getValue(null, 2F), 1F, 0)).as("close6").isFalse();
    assertThat(Values.areClose(getValue(null, 1F), 1D, 0)).as("close7").isTrue();
    assertThat(Values.areClose(getValue(null, 2F), 1D, 0)).as("close8").isFalse();
    assertThat(Values.areClose(getValue(null, 1.5F), 1.5F, 0)).as("close9").isTrue();
    assertThat(Values.areClose(getValue(null, 2.5F), 1.5F, 0)).as("close10").isFalse();
    assertThat(Values.areClose(getValue(null, 1.5F), 1.5D, 0)).as("close11").isTrue();
    assertThat(Values.areClose(getValue(null, 2.5F), 1.5D, 0)).as("close12").isFalse();
    assertThat(Values.areClose(getValue(null, null), (Float) null, 0)).as("close13").isTrue();
    assertThat(Values.areClose(getValue(null, 2.5F), (Float) null, 0)).as("close14").isFalse();

    assertThat(Values.areClose(getValue(null, 1F), 1, 1)).as("close15").isTrue();
    assertThat(Values.areClose(getValue(null, 2F), 1, 1)).as("close16").isTrue();
    assertThat(Values.areClose(getValue(null, 1F), 1L, 1)).as("close17").isTrue();
    assertThat(Values.areClose(getValue(null, 2F), 1L, 1)).as("close18").isTrue();
    assertThat(Values.areClose(getValue(null, 1F), 1F, 1)).as("close19").isTrue();
    assertThat(Values.areClose(getValue(null, 2F), 1F, 1)).as("close20").isTrue();
    assertThat(Values.areClose(getValue(null, 1F), 1D, 1)).as("close21").isTrue();
    assertThat(Values.areClose(getValue(null, 2F), 1D, 1)).as("close22").isTrue();
    assertThat(Values.areClose(getValue(null, 1.5F), 1.5F, 1)).as("close23").isTrue();
    assertThat(Values.areClose(getValue(null, 2.5F), 1.5F, 1)).as("close24").isTrue();
    assertThat(Values.areClose(getValue(null, 1.5F), 1.5D, 1)).as("close25").isTrue();
    assertThat(Values.areClose(getValue(null, 2.5F), 1.5D, 1)).as("close26").isTrue();
    assertThat(Values.areClose(getValue(null, null), (Float) null, 1)).as("close27").isTrue();
    assertThat(Values.areClose(getValue(null, 2.5F), (Float) null, 1)).as("close28").isFalse();

    assertThat(Values.areClose(getValue(null, 0F), 1, 1)).as("close29").isTrue();
    assertThat(Values.areClose(getValue(null, 0F), 2, 1)).as("close30").isFalse();
    assertThat(Values.areClose(getValue(null, 0F), 1L, 1)).as("close31").isTrue();
    assertThat(Values.areClose(getValue(null, 0F), 2L, 1)).as("close32").isFalse();
    assertThat(Values.areClose(getValue(null, 0F), 1F, 1)).as("close33").isTrue();
    assertThat(Values.areClose(getValue(null, 0F), 2F, 1)).as("close34").isFalse();
    assertThat(Values.areClose(getValue(null, 0F), 1D, 1)).as("close35").isTrue();
    assertThat(Values.areClose(getValue(null, 0F), 2D, 1)).as("close36").isFalse();
    assertThat(Values.areClose(getValue(null, 0.5F), 1.5F, 1)).as("close37").isTrue();
    assertThat(Values.areClose(getValue(null, 0.5F), 2.5F, 1)).as("close38").isFalse();
    assertThat(Values.areClose(getValue(null, 0.5F), 1.5D, 1)).as("close39").isTrue();
    assertThat(Values.areClose(getValue(null, 0.5F), 2.5D, 1)).as("close40").isFalse();
    assertThat(Values.areClose(getValue(null, null), (Float) null, 1)).as("close41").isTrue();
    assertThat(Values.areClose(getValue(null, 0.5F), (Float) null, 1)).as("close42").isFalse();


    assertThat(Values.areClose(getValue(null, 1F), 1, 0f)).as("close1").isTrue();
    assertThat(Values.areClose(getValue(null, 2F), 1, 0f)).as("close2").isFalse();
    assertThat(Values.areClose(getValue(null, 1F), 1L, 0f)).as("close3").isTrue();
    assertThat(Values.areClose(getValue(null, 2F), 1L, 0f)).as("close4").isFalse();
    assertThat(Values.areClose(getValue(null, 1F), 1F, 0f)).as("close5").isTrue();
    assertThat(Values.areClose(getValue(null, 2F), 1F, 0f)).as("close6").isFalse();
    assertThat(Values.areClose(getValue(null, 1F), 1D, 0f)).as("close7").isTrue();
    assertThat(Values.areClose(getValue(null, 2F), 1D, 0f)).as("close8").isFalse();
    assertThat(Values.areClose(getValue(null, 1.5F), 1.5F, 0f)).as("close9").isTrue();
    assertThat(Values.areClose(getValue(null, 2.5F), 1.5F, 0f)).as("close10").isFalse();
    assertThat(Values.areClose(getValue(null, 1.5F), 1.5D, 0f)).as("close11").isTrue();
    assertThat(Values.areClose(getValue(null, 2.5F), 1.5D, 0f)).as("close12").isFalse();
    assertThat(Values.areClose(getValue(null, null), (Float) null, 0f)).as("close13").isTrue();
    assertThat(Values.areClose(getValue(null, 2.5F), (Float) null, 0f)).as("close14").isFalse();

    assertThat(Values.areClose(getValue(null, 1F), 1, 1f)).as("close15").isTrue();
    assertThat(Values.areClose(getValue(null, 2F), 1, 1f)).as("close16").isTrue();
    assertThat(Values.areClose(getValue(null, 1F), 1L, 1f)).as("close17").isTrue();
    assertThat(Values.areClose(getValue(null, 2F), 1L, 1f)).as("close18").isTrue();
    assertThat(Values.areClose(getValue(null, 1F), 1F, 1f)).as("close19").isTrue();
    assertThat(Values.areClose(getValue(null, 2F), 1F, 1f)).as("close20").isTrue();
    assertThat(Values.areClose(getValue(null, 1F), 1D, 1f)).as("close21").isTrue();
    assertThat(Values.areClose(getValue(null, 2F), 1D, 1f)).as("close22").isTrue();
    assertThat(Values.areClose(getValue(null, 1.5F), 1.5F, 1f)).as("close23").isTrue();
    assertThat(Values.areClose(getValue(null, 2.5F), 1.5F, 1f)).as("close24").isTrue();
    assertThat(Values.areClose(getValue(null, 1.5F), 1.5D, 1f)).as("close25").isTrue();
    assertThat(Values.areClose(getValue(null, 2.5F), 1.5D, 1f)).as("close26").isTrue();
    assertThat(Values.areClose(getValue(null, null), (Float) null, 1f)).as("close27").isTrue();
    assertThat(Values.areClose(getValue(null, 2.5F), (Float) null, 1)).as("close28").isFalse();

    assertThat(Values.areClose(getValue(null, 0F), 1, 1f)).as("close29").isTrue();
    assertThat(Values.areClose(getValue(null, 0F), 2, 1f)).as("close30").isFalse();
    assertThat(Values.areClose(getValue(null, 0F), 1L, 1f)).as("close31").isTrue();
    assertThat(Values.areClose(getValue(null, 0F), 2L, 1f)).as("close32").isFalse();
    assertThat(Values.areClose(getValue(null, 0F), 1F, 1f)).as("close33").isTrue();
    assertThat(Values.areClose(getValue(null, 0F), 2F, 1f)).as("close34").isFalse();
    assertThat(Values.areClose(getValue(null, 0F), 1D, 1f)).as("close35").isTrue();
    assertThat(Values.areClose(getValue(null, 0F), 2D, 1f)).as("close36").isFalse();
    assertThat(Values.areClose(getValue(null, 0.5F), 1.5F, 1f)).as("close37").isTrue();
    assertThat(Values.areClose(getValue(null, 0.5F), 2.5F, 1f)).as("close38").isFalse();
    assertThat(Values.areClose(getValue(null, 0.5F), 1.5D, 1f)).as("close39").isTrue();
    assertThat(Values.areClose(getValue(null, 0.5F), 2.5D, 1f)).as("close40").isFalse();
    assertThat(Values.areClose(getValue(null, null), (Float) null, 1f)).as("close41").isTrue();
    assertThat(Values.areClose(getValue(null, 0.5F), (Float) null, 1f)).as("close42").isFalse();


    assertThat(Values.areClose(getValue(null, 1F), 1, 0d)).as("close1").isTrue();
    assertThat(Values.areClose(getValue(null, 2F), 1, 0d)).as("close2").isFalse();
    assertThat(Values.areClose(getValue(null, 1F), 1L, 0d)).as("close3").isTrue();
    assertThat(Values.areClose(getValue(null, 2F), 1L, 0d)).as("close4").isFalse();
    assertThat(Values.areClose(getValue(null, 1F), 1F, 0d)).as("close5").isTrue();
    assertThat(Values.areClose(getValue(null, 2F), 1F, 0d)).as("close6").isFalse();
    assertThat(Values.areClose(getValue(null, 1F), 1D, 0d)).as("close7").isTrue();
    assertThat(Values.areClose(getValue(null, 2F), 1D, 0d)).as("close8").isFalse();
    assertThat(Values.areClose(getValue(null, 1.5F), 1.5F, 0d)).as("close9").isTrue();
    assertThat(Values.areClose(getValue(null, 2.5F), 1.5F, 0d)).as("close10").isFalse();
    assertThat(Values.areClose(getValue(null, 1.5F), 1.5D, 0d)).as("close11").isTrue();
    assertThat(Values.areClose(getValue(null, 2.5F), 1.5D, 0d)).as("close12").isFalse();
    assertThat(Values.areClose(getValue(null, null), (Float) null, 0d)).as("close13").isTrue();
    assertThat(Values.areClose(getValue(null, 2.5F), (Float) null, 0d)).as("close14").isFalse();

    assertThat(Values.areClose(getValue(null, 1F), 1, 1d)).as("close15").isTrue();
    assertThat(Values.areClose(getValue(null, 2F), 1, 1d)).as("close16").isTrue();
    assertThat(Values.areClose(getValue(null, 1F), 1L, 1d)).as("close17").isTrue();
    assertThat(Values.areClose(getValue(null, 2F), 1L, 1d)).as("close18").isTrue();
    assertThat(Values.areClose(getValue(null, 1F), 1F, 1d)).as("close19").isTrue();
    assertThat(Values.areClose(getValue(null, 2F), 1F, 1d)).as("close20").isTrue();
    assertThat(Values.areClose(getValue(null, 1F), 1D, 1d)).as("close21").isTrue();
    assertThat(Values.areClose(getValue(null, 2F), 1D, 1d)).as("close22").isTrue();
    assertThat(Values.areClose(getValue(null, 1.5F), 1.5F, 1d)).as("close23").isTrue();
    assertThat(Values.areClose(getValue(null, 2.5F), 1.5F, 1d)).as("close24").isTrue();
    assertThat(Values.areClose(getValue(null, 1.5F), 1.5D, 1d)).as("close25").isTrue();
    assertThat(Values.areClose(getValue(null, 2.5F), 1.5D, 1d)).as("close26").isTrue();
    assertThat(Values.areClose(getValue(null, null), (Float) null, 1d)).as("close27").isTrue();
    assertThat(Values.areClose(getValue(null, 2.5F), (Float) null, 1d)).as("close28").isFalse();

    assertThat(Values.areClose(getValue(null, 0F), 1, 1d)).as("close29").isTrue();
    assertThat(Values.areClose(getValue(null, 0F), 2, 1d)).as("close30").isFalse();
    assertThat(Values.areClose(getValue(null, 0F), 1L, 1d)).as("close31").isTrue();
    assertThat(Values.areClose(getValue(null, 0F), 2L, 1d)).as("close32").isFalse();
    assertThat(Values.areClose(getValue(null, 0F), 1F, 1d)).as("close33").isTrue();
    assertThat(Values.areClose(getValue(null, 0F), 2F, 1d)).as("close34").isFalse();
    assertThat(Values.areClose(getValue(null, 0F), 1D, 1d)).as("close35").isTrue();
    assertThat(Values.areClose(getValue(null, 0F), 2D, 1d)).as("close36").isFalse();
    assertThat(Values.areClose(getValue(null, 0.5F), 1.5F, 1d)).as("close37").isTrue();
    assertThat(Values.areClose(getValue(null, 0.5F), 2.5F, 1d)).as("close38").isFalse();
    assertThat(Values.areClose(getValue(null, 0.5F), 1.5D, 1d)).as("close39").isTrue();
    assertThat(Values.areClose(getValue(null, 0.5F), 2.5D, 1d)).as("close40").isFalse();
    assertThat(Values.areClose(getValue(null, null), (Float) null, 1d)).as("close41").isTrue();
    assertThat(Values.areClose(getValue(null, 0.5F), (Float) null, 1d)).as("close42").isFalse();
  }

  /**
   * This method tests the {@code areClose} method for another type of value and {@code Double}s.
   */
  @Test
  public void test_are_close_for_other_and_double() throws Exception {
    assertThat(Values.areClose(getValue(null, 1D), 1, 0)).as("close1").isTrue();
    assertThat(Values.areClose(getValue(null, 2D), 1, 0)).as("close2").isFalse();
    assertThat(Values.areClose(getValue(null, 1D), 1L, 0)).as("close3").isTrue();
    assertThat(Values.areClose(getValue(null, 2D), 1L, 0)).as("close4").isFalse();
    assertThat(Values.areClose(getValue(null, 1D), 1F, 0)).as("close5").isTrue();
    assertThat(Values.areClose(getValue(null, 2D), 1F, 0)).as("close6").isFalse();
    assertThat(Values.areClose(getValue(null, 1D), 1D, 0)).as("close7").isTrue();
    assertThat(Values.areClose(getValue(null, 2D), 1D, 0)).as("close8").isFalse();
    assertThat(Values.areClose(getValue(null, 1.5D), 1.5F, 0)).as("close9").isTrue();
    assertThat(Values.areClose(getValue(null, 2.5D), 1.5F, 0)).as("close10").isFalse();
    assertThat(Values.areClose(getValue(null, 1.5D), 1.5D, 0)).as("close11").isTrue();
    assertThat(Values.areClose(getValue(null, 2.5D), 1.5D, 0)).as("close12").isFalse();
    assertThat(Values.areClose(getValue(null, null), (Double) null, 0)).as("close13").isTrue();
    assertThat(Values.areClose(getValue(null, 2.5D), (Double) null, 0)).as("close14").isFalse();

    assertThat(Values.areClose(getValue(null, 1D), 1, 1)).as("close15").isTrue();
    assertThat(Values.areClose(getValue(null, 2D), 1, 1)).as("close16").isTrue();
    assertThat(Values.areClose(getValue(null, 1D), 1L, 1)).as("close17").isTrue();
    assertThat(Values.areClose(getValue(null, 2D), 1L, 1)).as("close18").isTrue();
    assertThat(Values.areClose(getValue(null, 1D), 1F, 1)).as("close19").isTrue();
    assertThat(Values.areClose(getValue(null, 2D), 1F, 1)).as("close20").isTrue();
    assertThat(Values.areClose(getValue(null, 1D), 1D, 1)).as("close21").isTrue();
    assertThat(Values.areClose(getValue(null, 2D), 1D, 1)).as("close22").isTrue();
    assertThat(Values.areClose(getValue(null, 1.5D), 1.5F, 1)).as("close23").isTrue();
    assertThat(Values.areClose(getValue(null, 2.5D), 1.5F, 1)).as("close24").isTrue();
    assertThat(Values.areClose(getValue(null, 1.5D), 1.5D, 1)).as("close25").isTrue();
    assertThat(Values.areClose(getValue(null, 2.5D), 1.5D, 1)).as("close26").isTrue();
    assertThat(Values.areClose(getValue(null, null), (Float) null, 1)).as("close27").isTrue();
    assertThat(Values.areClose(getValue(null, 2.5D), (Float) null, 1)).as("close28").isFalse();

    assertThat(Values.areClose(getValue(null, 0D), 1, 1)).as("close29").isTrue();
    assertThat(Values.areClose(getValue(null, 0D), 2, 1)).as("close30").isFalse();
    assertThat(Values.areClose(getValue(null, 0D), 1L, 1)).as("close31").isTrue();
    assertThat(Values.areClose(getValue(null, 0D), 2L, 1)).as("close32").isFalse();
    assertThat(Values.areClose(getValue(null, 0D), 1F, 1)).as("close33").isTrue();
    assertThat(Values.areClose(getValue(null, 0D), 2F, 1)).as("close34").isFalse();
    assertThat(Values.areClose(getValue(null, 0D), 1D, 1)).as("close35").isTrue();
    assertThat(Values.areClose(getValue(null, 0D), 2D, 1)).as("close36").isFalse();
    assertThat(Values.areClose(getValue(null, 0.5D), 1.5F, 1)).as("close37").isTrue();
    assertThat(Values.areClose(getValue(null, 0.5D), 2.5F, 1)).as("close38").isFalse();
    assertThat(Values.areClose(getValue(null, 0.5D), 1.5D, 1)).as("close39").isTrue();
    assertThat(Values.areClose(getValue(null, 0.5D), 2.5D, 1)).as("close40").isFalse();
    assertThat(Values.areClose(getValue(null, null), (Float) null, 1)).as("close41").isTrue();
    assertThat(Values.areClose(getValue(null, 0.5D), (Float) null, 1)).as("close42").isFalse();


    assertThat(Values.areClose(getValue(null, 1D), 1, 0f)).as("close1").isTrue();
    assertThat(Values.areClose(getValue(null, 2D), 1, 0f)).as("close2").isFalse();
    assertThat(Values.areClose(getValue(null, 1D), 1L, 0f)).as("close3").isTrue();
    assertThat(Values.areClose(getValue(null, 2D), 1L, 0f)).as("close4").isFalse();
    assertThat(Values.areClose(getValue(null, 1D), 1F, 0f)).as("close5").isTrue();
    assertThat(Values.areClose(getValue(null, 2D), 1F, 0f)).as("close6").isFalse();
    assertThat(Values.areClose(getValue(null, 1D), 1D, 0f)).as("close7").isTrue();
    assertThat(Values.areClose(getValue(null, 2D), 1D, 0f)).as("close8").isFalse();
    assertThat(Values.areClose(getValue(null, 1.5D), 1.5F, 0f)).as("close9").isTrue();
    assertThat(Values.areClose(getValue(null, 2.5D), 1.5F, 0f)).as("close10").isFalse();
    assertThat(Values.areClose(getValue(null, 1.5D), 1.5D, 0f)).as("close11").isTrue();
    assertThat(Values.areClose(getValue(null, 2.5D), 1.5D, 0f)).as("close12").isFalse();
    assertThat(Values.areClose(getValue(null, null), (Double) null, 0f)).as("close13").isTrue();
    assertThat(Values.areClose(getValue(null, 2.5D), (Double) null, 0f)).as("close14").isFalse();

    assertThat(Values.areClose(getValue(null, 1D), 1, 1f)).as("close15").isTrue();
    assertThat(Values.areClose(getValue(null, 2D), 1, 1f)).as("close16").isTrue();
    assertThat(Values.areClose(getValue(null, 1D), 1L, 1f)).as("close17").isTrue();
    assertThat(Values.areClose(getValue(null, 2D), 1L, 1f)).as("close18").isTrue();
    assertThat(Values.areClose(getValue(null, 1D), 1F, 1f)).as("close19").isTrue();
    assertThat(Values.areClose(getValue(null, 2D), 1F, 1f)).as("close20").isTrue();
    assertThat(Values.areClose(getValue(null, 1D), 1D, 1f)).as("close21").isTrue();
    assertThat(Values.areClose(getValue(null, 2D), 1D, 1f)).as("close22").isTrue();
    assertThat(Values.areClose(getValue(null, 1.5D), 1.5F, 1f)).as("close23").isTrue();
    assertThat(Values.areClose(getValue(null, 2.5D), 1.5F, 1f)).as("close24").isTrue();
    assertThat(Values.areClose(getValue(null, 1.5D), 1.5D, 1f)).as("close25").isTrue();
    assertThat(Values.areClose(getValue(null, 2.5D), 1.5D, 1f)).as("close26").isTrue();
    assertThat(Values.areClose(getValue(null, null), (Float) null, 1f)).as("close27").isTrue();
    assertThat(Values.areClose(getValue(null, 2.5D), (Float) null, 1f)).as("close28").isFalse();

    assertThat(Values.areClose(getValue(null, 0D), 1, 1f)).as("close29").isTrue();
    assertThat(Values.areClose(getValue(null, 0D), 2, 1f)).as("close30").isFalse();
    assertThat(Values.areClose(getValue(null, 0D), 1L, 1f)).as("close31").isTrue();
    assertThat(Values.areClose(getValue(null, 0D), 2L, 1f)).as("close32").isFalse();
    assertThat(Values.areClose(getValue(null, 0D), 1F, 1f)).as("close33").isTrue();
    assertThat(Values.areClose(getValue(null, 0D), 2F, 1f)).as("close34").isFalse();
    assertThat(Values.areClose(getValue(null, 0D), 1D, 1f)).as("close35").isTrue();
    assertThat(Values.areClose(getValue(null, 0D), 2D, 1f)).as("close36").isFalse();
    assertThat(Values.areClose(getValue(null, 0.5D), 1.5F, 1f)).as("close37").isTrue();
    assertThat(Values.areClose(getValue(null, 0.5D), 2.5F, 1f)).as("close38").isFalse();
    assertThat(Values.areClose(getValue(null, 0.5D), 1.5D, 1f)).as("close39").isTrue();
    assertThat(Values.areClose(getValue(null, 0.5D), 2.5D, 1f)).as("close40").isFalse();
    assertThat(Values.areClose(getValue(null, null), (Float) null, 1f)).as("close41").isTrue();
    assertThat(Values.areClose(getValue(null, 0.5D), (Float) null, 1f)).as("close42").isFalse();


    assertThat(Values.areClose(getValue(null, 1D), 1, 0d)).as("close1").isTrue();
    assertThat(Values.areClose(getValue(null, 2D), 1, 0d)).as("close2").isFalse();
    assertThat(Values.areClose(getValue(null, 1D), 1L, 0d)).as("close3").isTrue();
    assertThat(Values.areClose(getValue(null, 2D), 1L, 0d)).as("close4").isFalse();
    assertThat(Values.areClose(getValue(null, 1D), 1F, 0d)).as("close5").isTrue();
    assertThat(Values.areClose(getValue(null, 2D), 1F, 0d)).as("close6").isFalse();
    assertThat(Values.areClose(getValue(null, 1D), 1D, 0d)).as("close7").isTrue();
    assertThat(Values.areClose(getValue(null, 2D), 1D, 0d)).as("close8").isFalse();
    assertThat(Values.areClose(getValue(null, 1.5D), 1.5F, 0d)).as("close9").isTrue();
    assertThat(Values.areClose(getValue(null, 2.5D), 1.5F, 0d)).as("close10").isFalse();
    assertThat(Values.areClose(getValue(null, 1.5D), 1.5D, 0d)).as("close11").isTrue();
    assertThat(Values.areClose(getValue(null, 2.5D), 1.5D, 0d)).as("close12").isFalse();
    assertThat(Values.areClose(getValue(null, null), (Double) null, 0d)).as("close13").isTrue();
    assertThat(Values.areClose(getValue(null, 2.5D), (Double) null, 0d)).as("close14").isFalse();

    assertThat(Values.areClose(getValue(null, 1D), 1, 1d)).as("close15").isTrue();
    assertThat(Values.areClose(getValue(null, 2D), 1, 1d)).as("close16").isTrue();
    assertThat(Values.areClose(getValue(null, 1D), 1L, 1d)).as("close17").isTrue();
    assertThat(Values.areClose(getValue(null, 2D), 1L, 1d)).as("close18").isTrue();
    assertThat(Values.areClose(getValue(null, 1D), 1F, 1d)).as("close19").isTrue();
    assertThat(Values.areClose(getValue(null, 2D), 1F, 1d)).as("close20").isTrue();
    assertThat(Values.areClose(getValue(null, 1D), 1D, 1d)).as("close21").isTrue();
    assertThat(Values.areClose(getValue(null, 2D), 1D, 1d)).as("close22").isTrue();
    assertThat(Values.areClose(getValue(null, 1.5D), 1.5F, 1d)).as("close23").isTrue();
    assertThat(Values.areClose(getValue(null, 2.5D), 1.5F, 1d)).as("close24").isTrue();
    assertThat(Values.areClose(getValue(null, 1.5D), 1.5D, 1d)).as("close25").isTrue();
    assertThat(Values.areClose(getValue(null, 2.5D), 1.5D, 1d)).as("close26").isTrue();
    assertThat(Values.areClose(getValue(null, null), (Float) null, 1d)).as("close27").isTrue();
    assertThat(Values.areClose(getValue(null, 2.5D), (Float) null, 1d)).as("close28").isFalse();

    assertThat(Values.areClose(getValue(null, 0D), 1, 1d)).as("close29").isTrue();
    assertThat(Values.areClose(getValue(null, 0D), 2, 1d)).as("close30").isFalse();
    assertThat(Values.areClose(getValue(null, 0D), 1L, 1d)).as("close31").isTrue();
    assertThat(Values.areClose(getValue(null, 0D), 2L, 1d)).as("close32").isFalse();
    assertThat(Values.areClose(getValue(null, 0D), 1F, 1d)).as("close33").isTrue();
    assertThat(Values.areClose(getValue(null, 0D), 2F, 1d)).as("close34").isFalse();
    assertThat(Values.areClose(getValue(null, 0D), 1D, 1d)).as("close35").isTrue();
    assertThat(Values.areClose(getValue(null, 0D), 2D, 1d)).as("close36").isFalse();
    assertThat(Values.areClose(getValue(null, 0.5D), 1.5F, 1d)).as("close37").isTrue();
    assertThat(Values.areClose(getValue(null, 0.5D), 2.5F, 1d)).as("close38").isFalse();
    assertThat(Values.areClose(getValue(null, 0.5D), 1.5D, 1d)).as("close39").isTrue();
    assertThat(Values.areClose(getValue(null, 0.5D), 2.5D, 1d)).as("close40").isFalse();
    assertThat(Values.areClose(getValue(null, null), (Float) null, 1d)).as("close41").isTrue();
    assertThat(Values.areClose(getValue(null, 0.5D), (Float) null, 1d)).as("close42").isFalse();
  }

  /**
   * This method tests the {@code areClose} method for another type of value and {@code BigInteger}s.
   */
  @Test
  public void test_are_close_for_other_and_biginteger() throws Exception {
    assertThat(Values.areClose(getValue(null, new BigInteger("1")), 1, 0)).as("close1").isTrue();
    assertThat(Values.areClose(getValue(null, new BigInteger("2")), 1, 0)).as("close2").isFalse();
    assertThat(Values.areClose(getValue(null, new BigInteger("1")), 1L, 0)).as("close3").isTrue();
    assertThat(Values.areClose(getValue(null, new BigInteger("2")), 1L, 0)).as("close4").isFalse();

    assertThat(Values.areClose(getValue(null, new BigInteger("1")), 1, 1)).as("close1").isTrue();
    assertThat(Values.areClose(getValue(null, new BigInteger("2")), 1, 1)).as("close2").isTrue();
    assertThat(Values.areClose(getValue(null, new BigInteger("1")), 1L, 1)).as("close3").isTrue();
    assertThat(Values.areClose(getValue(null, new BigInteger("2")), 1L, 1)).as("close4").isTrue();

    assertThat(Values.areClose(getValue(null, new BigInteger("0")), 1, 1)).as("close1").isTrue();
    assertThat(Values.areClose(getValue(null, new BigInteger("0")), 2, 1)).as("close2").isFalse();
    assertThat(Values.areClose(getValue(null, new BigInteger("0")), 1L, 1)).as("close3").isTrue();
    assertThat(Values.areClose(getValue(null, new BigInteger("0")), 2L, 1)).as("close4").isFalse();
  }

  /**
   * This method tests the {@code areClose} method for another type of value and {@code BigDecimal}s.
   */
  @Test
  public void test_are_close_for_other_and_bigdecimal() throws Exception {
    assertThat(Values.areClose(getValue(null, new BigDecimal("1")), 1, 0)).isTrue();
    assertThat(Values.areClose(getValue(null, new BigDecimal("2")), 1, 0)).isFalse();
    assertThat(Values.areClose(getValue(null, new BigDecimal("1")), 1L, 0)).isTrue();
    assertThat(Values.areClose(getValue(null, new BigDecimal("2")), 1L, 0)).isFalse();
    assertThat(Values.areClose(getValue(null, new BigDecimal("1")), 1F, 0)).isTrue();
    assertThat(Values.areClose(getValue(null, new BigDecimal("2")), 1F, 0)).isFalse();
    assertThat(Values.areClose(getValue(null, new BigDecimal("1")), 1D, 0)).isTrue();
    assertThat(Values.areClose(getValue(null, new BigDecimal("2")), 1D, 0)).isFalse();
    assertThat(Values.areClose(getValue(null, new BigDecimal("1.5")), 1.5F, 0)).isTrue();
    assertThat(Values.areClose(getValue(null, new BigDecimal("2.5")), 1.5F, 0)).isFalse();
    assertThat(Values.areClose(getValue(null, new BigDecimal("1.5")), 1.5D, 0)).isTrue();
    assertThat(Values.areClose(getValue(null, new BigDecimal("2.5")), 1.5D, 0)).isFalse();

    assertThat(Values.areClose(getValue(null, new BigDecimal("1")), 1, 1)).isTrue();
    assertThat(Values.areClose(getValue(null, new BigDecimal("2")), 1, 1)).isTrue();
    assertThat(Values.areClose(getValue(null, new BigDecimal("1")), 1L, 1)).isTrue();
    assertThat(Values.areClose(getValue(null, new BigDecimal("2")), 1L, 1)).isTrue();
    assertThat(Values.areClose(getValue(null, new BigDecimal("1")), 1F, 1)).isTrue();
    assertThat(Values.areClose(getValue(null, new BigDecimal("2")), 1F, 1)).isTrue();
    assertThat(Values.areClose(getValue(null, new BigDecimal("1")), 1D, 1)).isTrue();
    assertThat(Values.areClose(getValue(null, new BigDecimal("2")), 1D, 1)).isTrue();
    assertThat(Values.areClose(getValue(null, new BigDecimal("1.5")), 1.5F, 1)).isTrue();
    assertThat(Values.areClose(getValue(null, new BigDecimal("2.5")), 1.5F, 1)).isTrue();
    assertThat(Values.areClose(getValue(null, new BigDecimal("1.5")), 1.5D, 1)).isTrue();
    assertThat(Values.areClose(getValue(null, new BigDecimal("2.5")), 1.5D, 1)).isTrue();

    assertThat(Values.areClose(getValue(null, new BigDecimal("0")), 1, 1)).isTrue();
    assertThat(Values.areClose(getValue(null, new BigDecimal("-1")), 1, 1)).isFalse();
    assertThat(Values.areClose(getValue(null, new BigDecimal("0")), 1L, 1)).isTrue();
    assertThat(Values.areClose(getValue(null, new BigDecimal("-1")), 1L, 1)).isFalse();
    assertThat(Values.areClose(getValue(null, new BigDecimal("0")), 1F, 1)).isTrue();
    assertThat(Values.areClose(getValue(null, new BigDecimal("-1")), 1F, 1)).isFalse();
    assertThat(Values.areClose(getValue(null, new BigDecimal("0")), 1D, 1)).isTrue();
    assertThat(Values.areClose(getValue(null, new BigDecimal("-1")), 1D, 1)).isFalse();
    assertThat(Values.areClose(getValue(null, new BigDecimal("0.5")), 1.5F, 1)).isTrue();
    assertThat(Values.areClose(getValue(null, new BigDecimal("0")), 1.5F, 1)).isFalse();
    assertThat(Values.areClose(getValue(null, new BigDecimal("0.5")), 1.5D, 1)).isTrue();
    assertThat(Values.areClose(getValue(null, new BigDecimal("0")), 1.5D, 1)).isFalse();
  }

  /**
   * This method tests the {@code areClose} method for another type of value and {@code Byte}s.
   */
  @Test
  public void test_are_close_for_other_and_byte() throws Exception {
    assertThat(Values.areClose(getValue(null, (byte) 1), 1, 0)).isTrue();
    assertThat(Values.areClose(getValue(null, (byte) 2), 1, 0)).isFalse();
    assertThat(Values.areClose(getValue(null, (byte) 1), 1L, 0)).isTrue();
    assertThat(Values.areClose(getValue(null, (byte) 2), 1L, 0)).isFalse();
    assertThat(Values.areClose(getValue(null, (byte) 1), 1F, 0)).isTrue();
    assertThat(Values.areClose(getValue(null, (byte) 2), 1F, 0)).isFalse();
    assertThat(Values.areClose(getValue(null, (byte) 1), 1D, 0)).isTrue();
    assertThat(Values.areClose(getValue(null, (byte) 2), 1D, 0)).isFalse();

    assertThat(Values.areClose(getValue(null, (byte) 1), 1, 1)).isTrue();
    assertThat(Values.areClose(getValue(null, (byte) 2), 1, 1)).isTrue();
    assertThat(Values.areClose(getValue(null, (byte) 1), 1L, 1)).isTrue();
    assertThat(Values.areClose(getValue(null, (byte) 2), 1L, 1)).isTrue();
    assertThat(Values.areClose(getValue(null, (byte) 1), 1F, 1)).isTrue();
    assertThat(Values.areClose(getValue(null, (byte) 2), 1F, 1)).isTrue();
    assertThat(Values.areClose(getValue(null, (byte) 1), 1D, 1)).isTrue();
    assertThat(Values.areClose(getValue(null, (byte) 2), 1D, 1)).isTrue();

    assertThat(Values.areClose(getValue(null, (byte) 0), 1, 1)).isTrue();
    assertThat(Values.areClose(getValue(null, (byte) -1), 1, 1)).isFalse();
    assertThat(Values.areClose(getValue(null, (byte) 0), 1L, 1)).isTrue();
    assertThat(Values.areClose(getValue(null, (byte) -1), 1L, 1)).isFalse();
    assertThat(Values.areClose(getValue(null, (byte) 0), 1F, 1)).isTrue();
    assertThat(Values.areClose(getValue(null, (byte) -1), 1F, 1)).isFalse();
    assertThat(Values.areClose(getValue(null, (byte) 0), 1D, 1)).isTrue();
    assertThat(Values.areClose(getValue(null, (byte) -1), 1D, 1)).isFalse();


    assertThat(Values.areClose(getValue(null, (byte) 1), 1, 0f)).isTrue();
    assertThat(Values.areClose(getValue(null, (byte) 2), 1, 0f)).isFalse();
    assertThat(Values.areClose(getValue(null, (byte) 1), 1L, 0f)).isTrue();
    assertThat(Values.areClose(getValue(null, (byte) 2), 1L, 0f)).isFalse();
    assertThat(Values.areClose(getValue(null, (byte) 1), 1F, 0f)).isTrue();
    assertThat(Values.areClose(getValue(null, (byte) 2), 1F, 0f)).isFalse();
    assertThat(Values.areClose(getValue(null, (byte) 1), 1D, 0f)).isTrue();
    assertThat(Values.areClose(getValue(null, (byte) 2), 1D, 0f)).isFalse();

    assertThat(Values.areClose(getValue(null, (byte) 1), 1, 1f)).isTrue();
    assertThat(Values.areClose(getValue(null, (byte) 2), 1, 1f)).isTrue();
    assertThat(Values.areClose(getValue(null, (byte) 1), 1L, 1f)).isTrue();
    assertThat(Values.areClose(getValue(null, (byte) 2), 1L, 1f)).isTrue();
    assertThat(Values.areClose(getValue(null, (byte) 1), 1F, 1f)).isTrue();
    assertThat(Values.areClose(getValue(null, (byte) 2), 1F, 1f)).isTrue();
    assertThat(Values.areClose(getValue(null, (byte) 1), 1D, 1f)).isTrue();
    assertThat(Values.areClose(getValue(null, (byte) 2), 1D, 1f)).isTrue();

    assertThat(Values.areClose(getValue(null, (byte) 0), 1, 1f)).isTrue();
    assertThat(Values.areClose(getValue(null, (byte) -1), 1, 1f)).isFalse();
    assertThat(Values.areClose(getValue(null, (byte) 0), 1L, 1f)).isTrue();
    assertThat(Values.areClose(getValue(null, (byte) -1), 1L, 1f)).isFalse();
    assertThat(Values.areClose(getValue(null, (byte) 0), 1F, 1f)).isTrue();
    assertThat(Values.areClose(getValue(null, (byte) -1), 1F, 1f)).isFalse();
    assertThat(Values.areClose(getValue(null, (byte) 0), 1D, 1f)).isTrue();
    assertThat(Values.areClose(getValue(null, (byte) -1), 1D, 1f)).isFalse();


    assertThat(Values.areClose(getValue(null, (byte) 1), 1, 0d)).isTrue();
    assertThat(Values.areClose(getValue(null, (byte) 2), 1, 0d)).isFalse();
    assertThat(Values.areClose(getValue(null, (byte) 1), 1L, 0d)).isTrue();
    assertThat(Values.areClose(getValue(null, (byte) 2), 1L, 0d)).isFalse();
    assertThat(Values.areClose(getValue(null, (byte) 1), 1F, 0d)).isTrue();
    assertThat(Values.areClose(getValue(null, (byte) 2), 1F, 0d)).isFalse();
    assertThat(Values.areClose(getValue(null, (byte) 1), 1D, 0d)).isTrue();
    assertThat(Values.areClose(getValue(null, (byte) 2), 1D, 0d)).isFalse();

    assertThat(Values.areClose(getValue(null, (byte) 1), 1, 1d)).isTrue();
    assertThat(Values.areClose(getValue(null, (byte) 2), 1, 1d)).isTrue();
    assertThat(Values.areClose(getValue(null, (byte) 1), 1L, 1d)).isTrue();
    assertThat(Values.areClose(getValue(null, (byte) 2), 1L, 1d)).isTrue();
    assertThat(Values.areClose(getValue(null, (byte) 1), 1F, 1d)).isTrue();
    assertThat(Values.areClose(getValue(null, (byte) 2), 1F, 1d)).isTrue();
    assertThat(Values.areClose(getValue(null, (byte) 1), 1D, 1d)).isTrue();
    assertThat(Values.areClose(getValue(null, (byte) 2), 1D, 1d)).isTrue();

    assertThat(Values.areClose(getValue(null, (byte) 0), 1, 1d)).isTrue();
    assertThat(Values.areClose(getValue(null, (byte) -1), 1, 1d)).isFalse();
    assertThat(Values.areClose(getValue(null, (byte) 0), 1L, 1d)).isTrue();
    assertThat(Values.areClose(getValue(null, (byte) -1), 1L, 1d)).isFalse();
    assertThat(Values.areClose(getValue(null, (byte) 0), 1F, 1d)).isTrue();
    assertThat(Values.areClose(getValue(null, (byte) -1), 1F, 1d)).isFalse();
    assertThat(Values.areClose(getValue(null, (byte) 0), 1D, 1d)).isTrue();
    assertThat(Values.areClose(getValue(null, (byte) -1), 1D, 1d)).isFalse();
  }

  /**
   * This method tests the {@code areClose} method for another type of value and {@code Short}s.
   */
  @Test
  public void test_are_close_for_other_and_short() throws Exception {
    assertThat(Values.areClose(getValue(null, (short) 1), 1, 0)).isTrue();
    assertThat(Values.areClose(getValue(null, (short) 2), 1, 0)).isFalse();
    assertThat(Values.areClose(getValue(null, (short) 1), 1L, 0)).isTrue();
    assertThat(Values.areClose(getValue(null, (short) 2), 1L, 0)).isFalse();
    assertThat(Values.areClose(getValue(null, (short) 1), 1F, 0)).isTrue();
    assertThat(Values.areClose(getValue(null, (short) 2), 1F, 0)).isFalse();
    assertThat(Values.areClose(getValue(null, (short) 1), 1D, 0)).isTrue();
    assertThat(Values.areClose(getValue(null, (short) 2), 1D, 0)).isFalse();

    assertThat(Values.areClose(getValue(null, (short) 1), 1, 1)).isTrue();
    assertThat(Values.areClose(getValue(null, (short) 2), 1, 1)).isTrue();
    assertThat(Values.areClose(getValue(null, (short) 1), 1L, 1)).isTrue();
    assertThat(Values.areClose(getValue(null, (short) 2), 1L, 1)).isTrue();
    assertThat(Values.areClose(getValue(null, (short) 1), 1F, 1)).isTrue();
    assertThat(Values.areClose(getValue(null, (short) 2), 1F, 1)).isTrue();
    assertThat(Values.areClose(getValue(null, (short) 1), 1D, 1)).isTrue();
    assertThat(Values.areClose(getValue(null, (short) 2), 1D, 1)).isTrue();

    assertThat(Values.areClose(getValue(null, (short) 0), 1, 1)).isTrue();
    assertThat(Values.areClose(getValue(null, (short) -1), 1, 1)).isFalse();
    assertThat(Values.areClose(getValue(null, (short) 0), 1L, 1)).isTrue();
    assertThat(Values.areClose(getValue(null, (short) -1), 1L, 1)).isFalse();
    assertThat(Values.areClose(getValue(null, (short) 0), 1F, 1)).isTrue();
    assertThat(Values.areClose(getValue(null, (short) -1), 1F, 1)).isFalse();
    assertThat(Values.areClose(getValue(null, (short) 0), 1D, 1)).isTrue();
    assertThat(Values.areClose(getValue(null, (short) -1), 1D, 1)).isFalse();
  }

  /**
   * This method tests the {@code areClose} method for another type of value and {@code Integer}s.
   */
  @Test
  public void test_are_close_for_other_and_integer() throws Exception {
    assertThat(Values.areClose(getValue(null, 1), 1, 0)).isTrue();
    assertThat(Values.areClose(getValue(null, 2), 1, 0)).isFalse();
    assertThat(Values.areClose(getValue(null, 1), 1L, 0)).isTrue();
    assertThat(Values.areClose(getValue(null, 2), 1L, 0)).isFalse();
    assertThat(Values.areClose(getValue(null, 1), 1F, 0)).isTrue();
    assertThat(Values.areClose(getValue(null, 2), 1F, 0)).isFalse();
    assertThat(Values.areClose(getValue(null, 1), 1D, 0)).isTrue();
    assertThat(Values.areClose(getValue(null, 2), 1D, 0)).isFalse();

    assertThat(Values.areClose(getValue(null, 1), 1, 1)).isTrue();
    assertThat(Values.areClose(getValue(null, 2), 1, 1)).isTrue();
    assertThat(Values.areClose(getValue(null, 1), 1L, 1)).isTrue();
    assertThat(Values.areClose(getValue(null, 2), 1L, 1)).isTrue();
    assertThat(Values.areClose(getValue(null, 1), 1F, 1)).isTrue();
    assertThat(Values.areClose(getValue(null, 2), 1F, 1)).isTrue();
    assertThat(Values.areClose(getValue(null, 1), 1D, 1)).isTrue();
    assertThat(Values.areClose(getValue(null, 2), 1D, 1)).isTrue();

    assertThat(Values.areClose(getValue(null, 0), 1, 1)).isTrue();
    assertThat(Values.areClose(getValue(null, -1), 1, 1)).isFalse();
    assertThat(Values.areClose(getValue(null, 0), 1L, 1)).isTrue();
    assertThat(Values.areClose(getValue(null, -1), 1L, 1)).isFalse();
    assertThat(Values.areClose(getValue(null, 0), 1F, 1)).isTrue();
    assertThat(Values.areClose(getValue(null, -1), 1F, 1)).isFalse();
    assertThat(Values.areClose(getValue(null, 0), 1D, 1)).isTrue();
    assertThat(Values.areClose(getValue(null, -1), 1D, 1)).isFalse();
  }

  /**
   * This method tests the {@code areClose} method for another type of value and {@code Long}s.
   */
  @Test
  public void test_are_close_for_other_and_long() throws Exception {
    assertThat(Values.areClose(getValue(null, (long) 1), 1, 0)).isTrue();
    assertThat(Values.areClose(getValue(null, (long) 2), 1, 0)).isFalse();
    assertThat(Values.areClose(getValue(null, (long) 1), 1L, 0)).isTrue();
    assertThat(Values.areClose(getValue(null, (long) 2), 1L, 0)).isFalse();
    assertThat(Values.areClose(getValue(null, (long) 1), 1F, 0)).isTrue();
    assertThat(Values.areClose(getValue(null, (long) 2), 1F, 0)).isFalse();
    assertThat(Values.areClose(getValue(null, (long) 1), 1D, 0)).isTrue();
    assertThat(Values.areClose(getValue(null, (long) 2), 1D, 0)).isFalse();

    assertThat(Values.areClose(getValue(null, (long) 1), 1, 1)).isTrue();
    assertThat(Values.areClose(getValue(null, (long) 2), 1, 1)).isTrue();
    assertThat(Values.areClose(getValue(null, (long) 1), 1L, 1)).isTrue();
    assertThat(Values.areClose(getValue(null, (long) 2), 1L, 1)).isTrue();
    assertThat(Values.areClose(getValue(null, (long) 1), 1F, 1)).isTrue();
    assertThat(Values.areClose(getValue(null, (long) 2), 1F, 1)).isTrue();
    assertThat(Values.areClose(getValue(null, (long) 1), 1D, 1)).isTrue();
    assertThat(Values.areClose(getValue(null, (long) 2), 1D, 1)).isTrue();

    assertThat(Values.areClose(getValue(null, (long) 0), 1, 1)).isTrue();
    assertThat(Values.areClose(getValue(null, (long) -1), 1, 1)).isFalse();
    assertThat(Values.areClose(getValue(null, (long) 0), 1L, 1)).isTrue();
    assertThat(Values.areClose(getValue(null, (long) -1), 1L, 1)).isFalse();
    assertThat(Values.areClose(getValue(null, (long) 0), 1F, 1)).isTrue();
    assertThat(Values.areClose(getValue(null, (long) -1), 1F, 1)).isFalse();
    assertThat(Values.areClose(getValue(null, (long) 0), 1D, 1)).isTrue();
    assertThat(Values.areClose(getValue(null, (long) -1), 1D, 1)).isFalse();
  }

  /**
   * This method tests the {@code areClose} method for another type of values.
   */
  @Test
  public void test_are_close_for_other_and_other() throws Exception {
    assertThat(Values.areClose(getValue(null, "-"), 1, 0)).isFalse();
  }
}
