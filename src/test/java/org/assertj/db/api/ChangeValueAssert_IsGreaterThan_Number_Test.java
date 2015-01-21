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
package org.assertj.db.api;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Changes;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on the methods which verifies if a value is greater than a number.
 *
 * @author Régis Pouiller
 *
 */
public class ChangeValueAssert_IsGreaterThan_Number_Test extends AbstractTest {

  /**
   * This method tests that the value is greater than a byte number.
   */
  @Test
  public void test_if_value_is_greater_than_number_byte() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForOtherTests();
    changes.setEndPointNow();

    assertThat(changes).change().rowAtEndPoint()
            .value("var1").isGreaterThan((byte) 0)
            .value("var3").isGreaterThan((byte) 1)
            .value("var4").isGreaterThan((byte) 2)
            .value("var5").isGreaterThan((byte) 3)
            .value("var13").isGreaterThan((byte) 4)
            .value("var14").isGreaterThan((byte) 6)
            .change().rowAtEndPoint()
            .value("var1").isGreaterThan((byte) 9)
            .value("var3").isGreaterThan((byte) 19)
            .value("var4").isGreaterThan((byte) 29)
            .value("var5").isGreaterThan((byte) 39)
            .value("var13").isGreaterThan((byte) 49)
            .value("var14").isGreaterThan((byte) 69);
  }

  /**
   * This method tests that the value is greater than a short number.
   */
  @Test
  public void test_if_value_is_greater_than_number_short() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForOtherTests();
    changes.setEndPointNow();

    assertThat(changes).change().rowAtEndPoint()
            .value("var1").isGreaterThan((short) 0)
            .value("var3").isGreaterThan((short) 1)
            .value("var4").isGreaterThan((short) 2)
            .value("var5").isGreaterThan((short) 3)
            .value("var13").isGreaterThan((short) 4)
            .value("var14").isGreaterThan((short) 6)
            .change().rowAtEndPoint()
            .value("var1").isGreaterThan((short) 9)
            .value("var3").isGreaterThan((short) 19)
            .value("var4").isGreaterThan((short) 29)
            .value("var5").isGreaterThan((short) 39)
            .value("var13").isGreaterThan((short) 49)
            .value("var14").isGreaterThan((short) 69);
  }

  /**
   * This method tests that the value is greater than a integer number.
   */
  @Test
  public void test_if_value_is_greater_than_number_integer() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForOtherTests();
    changes.setEndPointNow();

    assertThat(changes).change().rowAtEndPoint()
            .value("var1").isGreaterThan((int) 0)
            .value("var3").isGreaterThan((int) 1)
            .value("var4").isGreaterThan((int) 2)
            .value("var5").isGreaterThan((int) 3)
            .value("var13").isGreaterThan((int) 4)
            .value("var14").isGreaterThan((int) 6)
            .change().rowAtEndPoint()
            .value("var1").isGreaterThan((int) 9)
            .value("var3").isGreaterThan((int) 19)
            .value("var4").isGreaterThan((int) 29)
            .value("var5").isGreaterThan((int) 39)
            .value("var13").isGreaterThan((int) 49)
            .value("var14").isGreaterThan((int) 69);
  }

  /**
   * This method tests that the value is greater than a long number.
   */
  @Test
  public void test_if_value_is_greater_than_number_long() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForOtherTests();
    changes.setEndPointNow();

    assertThat(changes).change().rowAtEndPoint()
            .value("var1").isGreaterThan((long) 0)
            .value("var3").isGreaterThan((long) 1)
            .value("var4").isGreaterThan((long) 2)
            .value("var5").isGreaterThan((long) 3)
            .value("var13").isGreaterThan((long) 4)
            .value("var14").isGreaterThan((long) 6)
            .change().rowAtEndPoint()
            .value("var1").isGreaterThan((long) 9)
            .value("var3").isGreaterThan((long) 19)
            .value("var4").isGreaterThan((long) 29)
            .value("var5").isGreaterThan((long) 39)
            .value("var13").isGreaterThan((long) 49)
            .value("var14").isGreaterThan((long) 69);
  }

  /**
   * This method tests that the value is greater than a nig integer number.
   */
  @Test
  public void test_if_value_is_greater_than_number_biginteger() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForOtherTests();
    changes.setEndPointNow();

    assertThat(changes).change().rowAtEndPoint()
            .value("var1").isGreaterThan(new BigInteger("0"))
            .value("var3").isGreaterThan(new BigInteger("1"))
            .value("var4").isGreaterThan(new BigInteger("2"))
            .value("var5").isGreaterThan(new BigInteger("3"))
            .change().rowAtEndPoint()
            .value("var1").isGreaterThan(new BigInteger("9"))
            .value("var3").isGreaterThan(new BigInteger("19"))
            .value("var4").isGreaterThan(new BigInteger("29"))
            .value("var5").isGreaterThan(new BigInteger("39"));
  }

  /**
   * This method tests that the value is greater than a float number.
   */
  @Test
  public void test_if_value_is_greater_than_number_float() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForOtherTests();
    changes.setEndPointNow();

    assertThat(changes).change().rowAtEndPoint()
            .value("var1").isGreaterThan((float) 0)
            .value("var3").isGreaterThan((float) 1)
            .value("var4").isGreaterThan((float) 2)
            .value("var5").isGreaterThan((float) 3)
            .value("var6").isGreaterThan((float) 5.5)
            .value("var7").isGreaterThan((float) 7.7)
            .value("var13").isGreaterThan((float) 4)
            .value("var14").isGreaterThan((float) 6)
            .change().rowAtEndPoint()
            .value("var1").isGreaterThan((float) 9)
            .value("var3").isGreaterThan((float) 19)
            .value("var4").isGreaterThan((float) 29)
            .value("var5").isGreaterThan((float) 39)
            .value("var6").isGreaterThan((float) 50.5)
            .value("var7").isGreaterThan((float) 69.7)
            .value("var13").isGreaterThan((float) 49)
            .value("var14").isGreaterThan((float) 69);
  }

  /**
   * This method tests that the value is greater than a double number.
   */
  @Test
  public void test_if_value_is_greater_than_number_double() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForOtherTests();
    changes.setEndPointNow();

    assertThat(changes).change().rowAtEndPoint()
            .value("var1").isGreaterThan((double) 0)
            .value("var3").isGreaterThan((double) 1)
            .value("var4").isGreaterThan((double) 2)
            .value("var5").isGreaterThan((double) 3)
            .value("var6").isGreaterThan((double) 5.5)
            .value("var7").isGreaterThan((double) 7.7)
            .value("var13").isGreaterThan((double) 4)
            .value("var14").isGreaterThan((double) 6)
            .change().rowAtEndPoint()
            .value("var1").isGreaterThan((double) 9)
            .value("var3").isGreaterThan((double) 19)
            .value("var4").isGreaterThan((double) 29)
            .value("var5").isGreaterThan((double) 39)
            .value("var6").isGreaterThan((double) 50.5)
            .value("var7").isGreaterThan((double) 69.7)
            .value("var13").isGreaterThan((double) 49)
            .value("var14").isGreaterThan((double) 69);
  }

  /**
   * This method tests that the value is greater than a big decimal number.
   */
  @Test
  public void test_if_value_is_greater_than_number_bigdecimal() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForOtherTests();
    changes.setEndPointNow();

    assertThat(changes).change().rowAtEndPoint()
            .value("var1").isGreaterThan(new BigDecimal("0"))
            .value("var3").isGreaterThan(new BigDecimal("1"))
            .value("var4").isGreaterThan(new BigDecimal("2"))
            .value("var5").isGreaterThan(new BigDecimal("3"))
            .value("var6").isGreaterThan(new BigDecimal("5.5"))
            .value("var7").isGreaterThan(new BigDecimal("7.7"))
            .value("var13").isGreaterThan(new BigDecimal("4"))
            .value("var14").isGreaterThan(new BigDecimal("6"))
            .change().rowAtEndPoint()
            .value("var1").isGreaterThan(new BigDecimal("9"))
            .value("var3").isGreaterThan(new BigDecimal("19"))
            .value("var4").isGreaterThan(new BigDecimal("29"))
            .value("var5").isGreaterThan(new BigDecimal("39"))
            .value("var6").isGreaterThan(new BigDecimal("50.5"))
            .value("var7").isGreaterThan(new BigDecimal("69.7"))
            .value("var13").isGreaterThan(new BigDecimal("49"))
            .value("var14").isGreaterThan(new BigDecimal("69"));
  }

  /**
   * This method should fail because the value is not greater than the number in parameter.
   */
  @Test
  public void should_fail_because_value_is_not_greater() {
    try {
      Changes changes = new Changes(source).setStartPointNow();
      updateChangesForOtherTests();
      changes.setEndPointNow();

      assertThat(changes).change().rowAtEndPoint()
              .value("var1").isGreaterThan(1);

      fail("An exception must be raised");
    } catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Value at index 0 of Row at end point of Change at index 0 of Changes on tables of 'sa/jdbc:h2:mem:test' source] \n" +
                                                                                    "Expecting:\n" +
                                                                                    "  <1>\n" +
                                                                                    "to be greater than \n" +
                                                                                    "  <1>");
    }
  }

  /**
   * This method should fail because the value is not a number.
   */
  @Test
  public void should_fail_because_value_is_not_a_number() {
    try {
      Changes changes = new Changes(source).setStartPointNow();
      updateChangesForOtherTests();
      changes.setEndPointNow();

      assertThat(changes).change().rowAtEndPoint().value("var2")
                       .as("var2").isGreaterThan(0);

      fail("An exception must be raised");
    } catch (AssertionError e) {
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
