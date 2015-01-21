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
 * Tests on the methods which verifies if a value is greater than or equal to a number.
 *
 * @author Régis Pouiller
 *
 */
public class ChangeValueAssert_IsGreaterThanOrEqualTo_Number_Test extends AbstractTest {

  /**
   * This method tests that the value is greater than or equal to a byte number.
   */
  @Test
  public void test_if_value_is_greater_than_or_equal_to_number_byte() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForOtherTests();
    changes.setEndPointNow();

    assertThat(changes).change().rowAtEndPoint()
            .value("var1").isGreaterThanOrEqualTo((byte) 1)
            .value("var3").isGreaterThanOrEqualTo((byte) 2)
            .value("var4").isGreaterThanOrEqualTo((byte) 3)
            .value("var5").isGreaterThanOrEqualTo((byte) 4)
            .value("var13").isGreaterThanOrEqualTo((byte) 5)
            .value("var14").isGreaterThanOrEqualTo((byte) 7)
            .change().rowAtEndPoint()
            .value("var1").isGreaterThanOrEqualTo((byte) 10)
            .value("var3").isGreaterThanOrEqualTo((byte) 20)
            .value("var4").isGreaterThanOrEqualTo((byte) 30)
            .value("var5").isGreaterThanOrEqualTo((byte) 40)
            .value("var13").isGreaterThanOrEqualTo((byte) 50)
            .value("var14").isGreaterThanOrEqualTo((byte) 70);
  }

  /**
   * This method tests that the value is greater than or equal to a short number.
   */
  @Test
  public void test_if_value_is_greater_than_or_equal_to_number_short() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForOtherTests();
    changes.setEndPointNow();

    assertThat(changes).change().rowAtEndPoint()
            .value("var1").isGreaterThanOrEqualTo((short) 1)
            .value("var3").isGreaterThanOrEqualTo((short) 2)
            .value("var4").isGreaterThanOrEqualTo((short) 3)
            .value("var5").isGreaterThanOrEqualTo((short) 4)
            .value("var13").isGreaterThanOrEqualTo((short) 5)
            .value("var14").isGreaterThanOrEqualTo((short) 7)
            .change().rowAtEndPoint()
            .value("var1").isGreaterThanOrEqualTo((short) 10)
            .value("var3").isGreaterThanOrEqualTo((short) 20)
            .value("var4").isGreaterThanOrEqualTo((short) 30)
            .value("var5").isGreaterThanOrEqualTo((short) 40)
            .value("var13").isGreaterThanOrEqualTo((short) 50)
            .value("var14").isGreaterThanOrEqualTo((short) 70);
  }

  /**
   * This method tests that the value is greater than or equal to a integer number.
   */
  @Test
  public void test_if_value_is_greater_than_or_equal_to_number_integer() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForOtherTests();
    changes.setEndPointNow();

    assertThat(changes).change().rowAtEndPoint()
            .value("var1").isGreaterThanOrEqualTo((int) 1)
            .value("var3").isGreaterThanOrEqualTo((int) 2)
            .value("var4").isGreaterThanOrEqualTo((int) 3)
            .value("var5").isGreaterThanOrEqualTo((int) 4)
            .value("var13").isGreaterThanOrEqualTo((int) 5)
            .value("var14").isGreaterThanOrEqualTo((int) 7)
            .change().rowAtEndPoint()
            .value("var1").isGreaterThanOrEqualTo((int) 10)
            .value("var3").isGreaterThanOrEqualTo((int) 20)
            .value("var4").isGreaterThanOrEqualTo((int) 30)
            .value("var5").isGreaterThanOrEqualTo((int) 40)
            .value("var13").isGreaterThanOrEqualTo((int) 50)
            .value("var14").isGreaterThanOrEqualTo((int) 70);
  }

  /**
   * This method tests that the value is greater than or equal to a long number.
   */
  @Test
  public void test_if_value_is_greater_than_or_equal_to_number_long() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForOtherTests();
    changes.setEndPointNow();

    assertThat(changes).change().rowAtEndPoint()
            .value("var1").isGreaterThanOrEqualTo((long) 1)
            .value("var3").isGreaterThanOrEqualTo((long) 2)
            .value("var4").isGreaterThanOrEqualTo((long) 3)
            .value("var5").isGreaterThanOrEqualTo((long) 4)
            .value("var13").isGreaterThanOrEqualTo((long) 5)
            .value("var14").isGreaterThanOrEqualTo((long) 7)
            .change().rowAtEndPoint()
            .value("var1").isGreaterThanOrEqualTo((long) 10)
            .value("var3").isGreaterThanOrEqualTo((long) 20)
            .value("var4").isGreaterThanOrEqualTo((long) 30)
            .value("var5").isGreaterThanOrEqualTo((long) 40)
            .value("var13").isGreaterThanOrEqualTo((long) 50)
            .value("var14").isGreaterThanOrEqualTo((long) 70);
  }

  /**
   * This method tests that the value is greater than or equal to a nig integer number.
   */
  @Test
  public void test_if_value_is_greater_than_or_equal_to_number_biginteger() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForOtherTests();
    changes.setEndPointNow();

    assertThat(changes).change().rowAtEndPoint()
            .value("var1").isGreaterThanOrEqualTo(new BigInteger("1"))
            .value("var3").isGreaterThanOrEqualTo(new BigInteger("2"))
            .value("var4").isGreaterThanOrEqualTo(new BigInteger("3"))
            .value("var5").isGreaterThanOrEqualTo(new BigInteger("4"))
            .change().rowAtEndPoint()
            .value("var1").isGreaterThanOrEqualTo(new BigInteger("10"))
            .value("var3").isGreaterThanOrEqualTo(new BigInteger("20"))
            .value("var4").isGreaterThanOrEqualTo(new BigInteger("30"))
            .value("var5").isGreaterThanOrEqualTo(new BigInteger("40"));
  }

  /**
   * This method tests that the value is greater than or equal to a float number.
   */
  @Test
  public void test_if_value_is_greater_than_or_equal_to_number_float() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForOtherTests();
    changes.setEndPointNow();

    assertThat(changes).change().rowAtEndPoint()
            .value("var1").isGreaterThanOrEqualTo((float) 1)
            .value("var3").isGreaterThanOrEqualTo((float) 2)
            .value("var4").isGreaterThanOrEqualTo((float) 3)
            .value("var5").isGreaterThanOrEqualTo((float) 4)
            .value("var6").isGreaterThanOrEqualTo((float) 5.6)
            .value("var7").isGreaterThanOrEqualTo((float) 7.8)
            .value("var13").isGreaterThanOrEqualTo((float) 5)
            .value("var14").isGreaterThanOrEqualTo((float) 7)
            .change().rowAtEndPoint()
            .value("var1").isGreaterThanOrEqualTo((float) 10)
            .value("var3").isGreaterThanOrEqualTo((float) 20)
            .value("var4").isGreaterThanOrEqualTo((float) 30)
            .value("var5").isGreaterThanOrEqualTo((float) 40)
            .value("var6").isGreaterThanOrEqualTo((float) 50.6)
            .value("var7").isGreaterThanOrEqualTo((float) 70.8)
            .value("var13").isGreaterThanOrEqualTo((float) 50)
            .value("var14").isGreaterThanOrEqualTo((float) 70);
  }

  /**
   * This method tests that the value is greater than or equal to a double number.
   */
  @Test
  public void test_if_value_is_greater_than_or_equal_to_number_double() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForOtherTests();
    changes.setEndPointNow();

    assertThat(changes).change().rowAtEndPoint()
            .value("var1").isGreaterThanOrEqualTo((double) 1)
            .value("var3").isGreaterThanOrEqualTo((double) 2)
            .value("var4").isGreaterThanOrEqualTo((double) 3)
            .value("var5").isGreaterThanOrEqualTo((double) 4)
            .value("var6").isGreaterThanOrEqualTo((double) 5.6)
            .value("var7").isGreaterThanOrEqualTo((double) 7.8)
            .value("var13").isGreaterThanOrEqualTo((double) 5)
            .value("var14").isGreaterThanOrEqualTo((double) 7)
            .change().rowAtEndPoint()
            .value("var1").isGreaterThanOrEqualTo((double) 10)
            .value("var3").isGreaterThanOrEqualTo((double) 20)
            .value("var4").isGreaterThanOrEqualTo((double) 30)
            .value("var5").isGreaterThanOrEqualTo((double) 40)
            .value("var6").isGreaterThanOrEqualTo((double) 50.6)
            .value("var7").isGreaterThanOrEqualTo((double) 70.8)
            .value("var13").isGreaterThanOrEqualTo((double) 50)
            .value("var14").isGreaterThanOrEqualTo((double) 70);
  }

  /**
   * This method tests that the value is greater than or equal to a big decimal number.
   */
  @Test
  public void test_if_value_is_greater_than_or_equal_to_number_bigdecimal() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForOtherTests();
    changes.setEndPointNow();

    assertThat(changes).change().rowAtEndPoint()
            .value("var1").isGreaterThanOrEqualTo(new BigDecimal("1"))
            .value("var3").isGreaterThanOrEqualTo(new BigDecimal("2"))
            .value("var4").isGreaterThanOrEqualTo(new BigDecimal("3"))
            .value("var5").isGreaterThanOrEqualTo(new BigDecimal("4"))
            .value("var6").isGreaterThanOrEqualTo(new BigDecimal("5.6"))
            .value("var7").isGreaterThanOrEqualTo(new BigDecimal("7.8"))
            .value("var13").isGreaterThanOrEqualTo(new BigDecimal("5"))
            .value("var14").isGreaterThanOrEqualTo(new BigDecimal("7"))
            .change().rowAtEndPoint()
            .value("var1").isGreaterThanOrEqualTo(new BigDecimal("10"))
            .value("var3").isGreaterThanOrEqualTo(new BigDecimal("20"))
            .value("var4").isGreaterThanOrEqualTo(new BigDecimal("30"))
            .value("var5").isGreaterThanOrEqualTo(new BigDecimal("40"))
            .value("var6").isGreaterThanOrEqualTo(new BigDecimal("50.6"))
            .value("var7").isGreaterThanOrEqualTo(new BigDecimal("70.8"))
            .value("var13").isGreaterThanOrEqualTo(new BigDecimal("50"))
            .value("var14").isGreaterThanOrEqualTo(new BigDecimal("70"));
  }

  /**
   * This method should fail because the value is not greater than or equal to the number in parameter.
   */
  @Test
  public void should_fail_because_value_is_not_greater_than_or_equal() {
    try {
      Changes changes = new Changes(source).setStartPointNow();
      updateChangesForOtherTests();
      changes.setEndPointNow();

      assertThat(changes).change().rowAtEndPoint()
              .value("var1").isGreaterThanOrEqualTo(2);

      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Value at index 0 of Row at end point of Change at index 0 of Changes on tables of 'sa/jdbc:h2:mem:test' source] \n" +
                                                                                    "Expecting:\n" +
                                                                                    "  <1>\n" +
                                                                                    "to be greater than or equal to \n" +
                                                                                    "  <2>");
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
                       .as("var2").isGreaterThanOrEqualTo(1);

      fail("An exception must be raised");
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
