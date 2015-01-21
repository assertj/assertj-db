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
 * Tests on the methods which verifies if a value is less than or equal to a number.
 *
 * @author Régis Pouiller
 *
 */
public class ChangeValueAssert_IsLessThanOrEqualTo_Number_Test extends AbstractTest {

  /**
   * This method tests that the value is less than or equal to a byte number.
   */
  @Test
  public void test_if_value_is_less_than_or_equal_to_number_byte() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForOtherTests();
    changes.setEndPointNow();

    assertThat(changes).change().rowAtEndPoint()
            .value("var1").isLessThanOrEqualTo((byte) 1)
            .value("var3").isLessThanOrEqualTo((byte) 2)
            .value("var4").isLessThanOrEqualTo((byte) 3)
            .value("var5").isLessThanOrEqualTo((byte) 4)
            .value("var13").isLessThanOrEqualTo((byte) 5)
            .value("var14").isLessThanOrEqualTo((byte) 7)
            .change().rowAtEndPoint()
            .value("var1").isLessThanOrEqualTo((byte) 10)
            .value("var3").isLessThanOrEqualTo((byte) 20)
            .value("var4").isLessThanOrEqualTo((byte) 30)
            .value("var5").isLessThanOrEqualTo((byte) 40)
            .value("var13").isLessThanOrEqualTo((byte) 50)
            .value("var14").isLessThanOrEqualTo((byte) 70);
  }

  /**
   * This method tests that the value is less than or equal to a short number.
   */
  @Test
  public void test_if_value_is_less_than_or_equal_to_number_short() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForOtherTests();
    changes.setEndPointNow();

    assertThat(changes).change().rowAtEndPoint()
            .value("var1").isLessThanOrEqualTo((short) 1)
            .value("var3").isLessThanOrEqualTo((short) 2)
            .value("var4").isLessThanOrEqualTo((short) 3)
            .value("var5").isLessThanOrEqualTo((short) 4)
            .value("var13").isLessThanOrEqualTo((short) 5)
            .value("var14").isLessThanOrEqualTo((short) 7)
            .change().rowAtEndPoint()
            .value("var1").isLessThanOrEqualTo((short) 10)
            .value("var3").isLessThanOrEqualTo((short) 20)
            .value("var4").isLessThanOrEqualTo((short) 30)
            .value("var5").isLessThanOrEqualTo((short) 40)
            .value("var13").isLessThanOrEqualTo((short) 50)
            .value("var14").isLessThanOrEqualTo((short) 70);
  }

  /**
   * This method tests that the value is less than or equal to a integer number.
   */
  @Test
  public void test_if_value_is_less_than_or_equal_to_number_integer() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForOtherTests();
    changes.setEndPointNow();

    assertThat(changes).change().rowAtEndPoint()
            .value("var1").isLessThanOrEqualTo((int) 1)
            .value("var3").isLessThanOrEqualTo((int) 2)
            .value("var4").isLessThanOrEqualTo((int) 3)
            .value("var5").isLessThanOrEqualTo((int) 4)
            .value("var13").isLessThanOrEqualTo((int) 5)
            .value("var14").isLessThanOrEqualTo((int) 7)
            .change().rowAtEndPoint()
            .value("var1").isLessThanOrEqualTo((int) 10)
            .value("var3").isLessThanOrEqualTo((int) 20)
            .value("var4").isLessThanOrEqualTo((int) 30)
            .value("var5").isLessThanOrEqualTo((int) 40)
            .value("var13").isLessThanOrEqualTo((int) 50)
            .value("var14").isLessThanOrEqualTo((int) 70);
  }

  /**
   * This method tests that the value is less than or equal to a long number.
   */
  @Test
  public void test_if_value_is_less_than_or_equal_to_number_long() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForOtherTests();
    changes.setEndPointNow();

    assertThat(changes).change().rowAtEndPoint()
            .value("var1").isLessThanOrEqualTo((long) 1)
            .value("var3").isLessThanOrEqualTo((long) 2)
            .value("var4").isLessThanOrEqualTo((long) 3)
            .value("var5").isLessThanOrEqualTo((long) 4)
            .value("var13").isLessThanOrEqualTo((long) 5)
            .value("var14").isLessThanOrEqualTo((long) 7)
            .change().rowAtEndPoint()
            .value("var1").isLessThanOrEqualTo((long) 10)
            .value("var3").isLessThanOrEqualTo((long) 20)
            .value("var4").isLessThanOrEqualTo((long) 30)
            .value("var5").isLessThanOrEqualTo((long) 40)
            .value("var13").isLessThanOrEqualTo((long) 50)
            .value("var14").isLessThanOrEqualTo((long) 70);
  }

  /**
   * This method tests that the value is less than or equal to a nig integer number.
   */
  @Test
  public void test_if_value_is_less_than_or_equal_to_number_biginteger() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForOtherTests();
    changes.setEndPointNow();

    assertThat(changes).change().rowAtEndPoint()
            .value("var1").isLessThanOrEqualTo(new BigInteger("1"))
            .value("var3").isLessThanOrEqualTo(new BigInteger("2"))
            .value("var4").isLessThanOrEqualTo(new BigInteger("3"))
            .value("var5").isLessThanOrEqualTo(new BigInteger("4"))
            .change().rowAtEndPoint()
            .value("var1").isLessThanOrEqualTo(new BigInteger("10"))
            .value("var3").isLessThanOrEqualTo(new BigInteger("20"))
            .value("var4").isLessThanOrEqualTo(new BigInteger("30"))
            .value("var5").isLessThanOrEqualTo(new BigInteger("40"));
  }

  /**
   * This method tests that the value is less than or equal to a float number.
   */
  @Test
  public void test_if_value_is_less_than_or_equal_to_number_float() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForOtherTests();
    changes.setEndPointNow();

    assertThat(changes).change().rowAtEndPoint()
            .value("var1").isLessThanOrEqualTo((float) 1)
            .value("var3").isLessThanOrEqualTo((float) 2)
            .value("var4").isLessThanOrEqualTo((float) 3)
            .value("var5").isLessThanOrEqualTo((float) 4)
            .value("var6").isLessThanOrEqualTo((float) 5.6)
            .value("var7").isLessThanOrEqualTo((float) 7.8)
            .value("var13").isLessThanOrEqualTo((float) 5)
            .value("var14").isLessThanOrEqualTo((float) 7)
            .change().rowAtEndPoint()
            .value("var1").isLessThanOrEqualTo((float) 10)
            .value("var3").isLessThanOrEqualTo((float) 20)
            .value("var4").isLessThanOrEqualTo((float) 30)
            .value("var5").isLessThanOrEqualTo((float) 40)
            .value("var6").isLessThanOrEqualTo((float) 50.6)
            .value("var7").isLessThanOrEqualTo((float) 70.8)
            .value("var13").isLessThanOrEqualTo((float) 50)
            .value("var14").isLessThanOrEqualTo((float) 70);
  }

  /**
   * This method tests that the value is less than or equal to a double number.
   */
  @Test
  public void test_if_value_is_less_than_or_equal_to_number_double() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForOtherTests();
    changes.setEndPointNow();

    assertThat(changes).change().rowAtEndPoint()
            .value("var1").isLessThanOrEqualTo((double) 1)
            .value("var3").isLessThanOrEqualTo((double) 2)
            .value("var4").isLessThanOrEqualTo((double) 3)
            .value("var5").isLessThanOrEqualTo((double) 4)
            .value("var6").isLessThanOrEqualTo((double) 5.6)
            .value("var7").isLessThanOrEqualTo((double) 7.8)
            .value("var13").isLessThanOrEqualTo((double) 5)
            .value("var14").isLessThanOrEqualTo((double) 7)
            .change().rowAtEndPoint()
            .value("var1").isLessThanOrEqualTo((double) 10)
            .value("var3").isLessThanOrEqualTo((double) 20)
            .value("var4").isLessThanOrEqualTo((double) 30)
            .value("var5").isLessThanOrEqualTo((double) 40)
            .value("var6").isLessThanOrEqualTo((double) 50.6)
            .value("var7").isLessThanOrEqualTo((double) 70.8)
            .value("var13").isLessThanOrEqualTo((double) 50)
            .value("var14").isLessThanOrEqualTo((double) 70);
  }

  /**
   * This method tests that the value is less than or equal to a big decimal number.
   */
  @Test
  public void test_if_value_is_less_than_or_equal_to_number_bigdecimal() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForOtherTests();
    changes.setEndPointNow();

    assertThat(changes).change().rowAtEndPoint()
            .value("var1").isLessThanOrEqualTo(new BigDecimal("1"))
            .value("var3").isLessThanOrEqualTo(new BigDecimal("2"))
            .value("var4").isLessThanOrEqualTo(new BigDecimal("3"))
            .value("var5").isLessThanOrEqualTo(new BigDecimal("4"))
            .value("var6").isLessThanOrEqualTo(new BigDecimal("5.6"))
            .value("var7").isLessThanOrEqualTo(new BigDecimal("7.8"))
            .value("var13").isLessThanOrEqualTo(new BigDecimal("5"))
            .value("var14").isLessThanOrEqualTo(new BigDecimal("7"))
            .change().rowAtEndPoint()
            .value("var1").isLessThanOrEqualTo(new BigDecimal("10"))
            .value("var3").isLessThanOrEqualTo(new BigDecimal("20"))
            .value("var4").isLessThanOrEqualTo(new BigDecimal("30"))
            .value("var5").isLessThanOrEqualTo(new BigDecimal("40"))
            .value("var6").isLessThanOrEqualTo(new BigDecimal("50.6"))
            .value("var7").isLessThanOrEqualTo(new BigDecimal("70.8"))
            .value("var13").isLessThanOrEqualTo(new BigDecimal("50"))
            .value("var14").isLessThanOrEqualTo(new BigDecimal("70"));
  }

  /**
   * This method should fail because the value is not equal to the number in parameter.
   */
  @Test
  public void should_fail_because_value_is_not_less_than_or_equal() {
    try {
      Changes changes = new Changes(source).setStartPointNow();
      updateChangesForOtherTests();
      changes.setEndPointNow();

      assertThat(changes).change().rowAtEndPoint()
              .value("var1").isLessThanOrEqualTo(0);

      fail("An exception must be raised");
    } catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Value at index 0 of Row at end point of Change at index 0 of Changes on tables of 'sa/jdbc:h2:mem:test' source] \n" +
                                                                                    "Expecting:\n" +
                                                                                    "  <1>\n" +
                                                                                    "to be less than or equal to \n" +
                                                                                    "  <0>");
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
                       .as("var2").isLessThanOrEqualTo(1);

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
