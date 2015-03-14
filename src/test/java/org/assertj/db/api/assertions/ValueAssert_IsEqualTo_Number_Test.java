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
package org.assertj.db.api.assertions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Tests on the methods which verifies if a value is equal to a number.
 * 
 * @author Régis Pouiller
 * 
 */
public class ValueAssert_IsEqualTo_Number_Test extends AbstractTest {

  /**
   * This method tests that the value is equal to a byte number.
   */
  @Test
  public void test_if_value_is_equal_to_number_byte() {
    Table table = new Table(source, "test");
    assertThat(table)
        .row()
            .value("var1").isEqualTo((byte) 1).returnToRow()
            .value("var3").isEqualTo((byte) 2).returnToRow()
            .value("var4").isEqualTo((byte) 3).returnToRow()
            .value("var5").isEqualTo((byte) 4).returnToRow()
            .value("var13").isEqualTo((byte) 5).returnToRow()
            .value("var14").isEqualTo((byte) 7).returnToRow()
        .returnToTable()
        .row()
            .value("var1").isEqualTo((byte) 10).returnToRow()
            .value("var3").isEqualTo((byte) 20).returnToRow()
            .value("var4").isEqualTo((byte) 30).returnToRow()
            .value("var5").isEqualTo((byte) 40).returnToRow()
            .value("var13").isEqualTo((byte) 50).returnToRow()
            .value("var14").isEqualTo((byte) 70);
  }

  /**
   * This method tests that the value is equal to a short number.
   */
  @Test
  public void test_if_value_is_equal_to_number_short() {
    Table table = new Table(source, "test");
    assertThat(table)
        .row()
            .value("var1").isEqualTo((short) 1).returnToRow()
            .value("var3").isEqualTo((short) 2).returnToRow()
            .value("var4").isEqualTo((short) 3).returnToRow()
            .value("var5").isEqualTo((short) 4).returnToRow()
            .value("var13").isEqualTo((short) 5).returnToRow()
            .value("var14").isEqualTo((short) 7).returnToRow()
        .returnToTable()
        .row()
            .value("var1").isEqualTo((short) 10).returnToRow()
            .value("var3").isEqualTo((short) 20).returnToRow()
            .value("var4").isEqualTo((short) 30).returnToRow()
            .value("var5").isEqualTo((short) 40).returnToRow()
            .value("var13").isEqualTo((short) 50).returnToRow()
            .value("var14").isEqualTo((short) 70);
  }

  /**
   * This method tests that the value is equal to a integer number.
   */
  @Test
  public void test_if_value_is_equal_to_number_integer() {
    Table table = new Table(source, "test");
    assertThat(table)
        .row()
            .value("var1").isEqualTo((int) 1).returnToRow()
            .value("var3").isEqualTo((int) 2).returnToRow()
            .value("var4").isEqualTo((int) 3).returnToRow()
            .value("var5").isEqualTo((int) 4).returnToRow()
            .value("var13").isEqualTo((int) 5).returnToRow()
            .value("var14").isEqualTo((int) 7).returnToRow()
        .returnToTable()
        .row()
            .value("var1").isEqualTo((int) 10).returnToRow()
            .value("var3").isEqualTo((int) 20).returnToRow()
            .value("var4").isEqualTo((int) 30).returnToRow()
            .value("var5").isEqualTo((int) 40).returnToRow()
            .value("var13").isEqualTo((int) 50).returnToRow()
            .value("var14").isEqualTo((int) 70);
  }

  /**
   * This method tests that the value is equal to a long number.
   */
  @Test
  public void test_if_value_is_equal_to_number_long() {
    Table table = new Table(source, "test");
    assertThat(table)
        .row()
            .value("var1").isEqualTo((long) 1).returnToRow()
            .value("var3").isEqualTo((long) 2).returnToRow()
            .value("var4").isEqualTo((long) 3).returnToRow()
            .value("var5").isEqualTo((long) 4).returnToRow()
            .value("var13").isEqualTo((long) 5).returnToRow()
            .value("var14").isEqualTo((long) 7).returnToRow()
        .returnToTable()
        .row()
            .value("var1").isEqualTo((long) 10).returnToRow()
            .value("var3").isEqualTo((long) 20).returnToRow()
            .value("var4").isEqualTo((long) 30).returnToRow()
            .value("var5").isEqualTo((long) 40).returnToRow()
            .value("var13").isEqualTo((long) 50).returnToRow()
            .value("var14").isEqualTo((long) 70);
  }

  /**
   * This method tests that the value is equal to a nig integer number.
   */
  @Test
  public void test_if_value_is_equal_to_number_biginteger() {
    Table table = new Table(source, "test");
    assertThat(table)
        .row()
            .value("var1").isEqualTo(new BigInteger("1")).returnToRow()
            .value("var3").isEqualTo(new BigInteger("2")).returnToRow()
            .value("var4").isEqualTo(new BigInteger("3")).returnToRow()
            .value("var5").isEqualTo(new BigInteger("4")).returnToRow()
        .returnToTable()
        .row()
            .value("var1").isEqualTo(new BigInteger("10")).returnToRow()
            .value("var3").isEqualTo(new BigInteger("20")).returnToRow()
            .value("var4").isEqualTo(new BigInteger("30")).returnToRow()
            .value("var5").isEqualTo(new BigInteger("40"));
  }

  /**
   * This method tests that the value is equal to a float number.
   */
  @Test
  public void test_if_value_is_equal_to_number_float() {
    Table table = new Table(source, "test");
    assertThat(table)
        .row()
            .value("var1").isEqualTo((float) 1).returnToRow()
            .value("var3").isEqualTo((float) 2).returnToRow()
            .value("var4").isEqualTo((float) 3).returnToRow()
            .value("var5").isEqualTo((float) 4).returnToRow()
            .value("var6").isEqualTo((float) 5.6).returnToRow()
            .value("var7").isEqualTo((float) 7.8).returnToRow()
            .value("var13").isEqualTo((float) 5).returnToRow()
            .value("var14").isEqualTo((float) 7).returnToRow()
        .returnToTable()
        .row()
            .value("var1").isEqualTo((float) 10).returnToRow()
            .value("var3").isEqualTo((float) 20).returnToRow()
            .value("var4").isEqualTo((float) 30).returnToRow()
            .value("var5").isEqualTo((float) 40).returnToRow()
            .value("var6").isEqualTo((float) 50.6).returnToRow()
            .value("var7").isEqualTo((float) 70.8).returnToRow()
            .value("var13").isEqualTo((float) 50).returnToRow()
            .value("var14").isEqualTo((float) 70);
  }

  /**
   * This method tests that the value is equal to a double number.
   */
  @Test
  public void test_if_value_is_equal_to_number_double() {
    Table table = new Table(source, "test");
    assertThat(table)
        .row()
            .value("var1").isEqualTo((double) 1).returnToRow()
            .value("var3").isEqualTo((double) 2).returnToRow()
            .value("var4").isEqualTo((double) 3).returnToRow()
            .value("var5").isEqualTo((double) 4).returnToRow()
            .value("var6").isEqualTo((double) 5.6).returnToRow()
            .value("var7").isEqualTo((double) 7.8).returnToRow()
            .value("var13").isEqualTo((double) 5).returnToRow()
            .value("var14").isEqualTo((double) 7).returnToRow()
        .returnToTable()
        .row()
            .value("var1").isEqualTo((double) 10).returnToRow()
            .value("var3").isEqualTo((double) 20).returnToRow()
            .value("var4").isEqualTo((double) 30).returnToRow()
            .value("var5").isEqualTo((double) 40).returnToRow()
            .value("var6").isEqualTo((double) 50.6).returnToRow()
            .value("var7").isEqualTo((double) 70.8).returnToRow()
            .value("var13").isEqualTo((double) 50).returnToRow()
            .value("var14").isEqualTo((double) 70);
  }

  /**
   * This method tests that the value is equal to a big decimal number.
   */
  @Test
  public void test_if_value_is_equal_to_number_bigdecimal() {
    Table table = new Table(source, "test");
    assertThat(table)
        .row()
            .value("var1").isEqualTo(new BigDecimal("1")).returnToRow()
            .value("var3").isEqualTo(new BigDecimal("2")).returnToRow()
            .value("var4").isEqualTo(new BigDecimal("3")).returnToRow()
            .value("var5").isEqualTo(new BigDecimal("4")).returnToRow()
            .value("var6").isEqualTo(new BigDecimal("5.6")).returnToRow()
            .value("var7").isEqualTo(new BigDecimal("7.8")).returnToRow()
            .value("var13").isEqualTo(new BigDecimal("5")).returnToRow()
            .value("var14").isEqualTo(new BigDecimal("7")).returnToRow()
        .returnToTable()
        .row()
            .value("var1").isEqualTo(new BigDecimal("10")).returnToRow()
            .value("var3").isEqualTo(new BigDecimal("20")).returnToRow()
            .value("var4").isEqualTo(new BigDecimal("30")).returnToRow()
            .value("var5").isEqualTo(new BigDecimal("40")).returnToRow()
            .value("var6").isEqualTo(new BigDecimal("50.6")).returnToRow()
            .value("var7").isEqualTo(new BigDecimal("70.8")).returnToRow()
            .value("var13").isEqualTo(new BigDecimal("50")).returnToRow()
            .value("var14").isEqualTo(new BigDecimal("70"));
  }

  /**
   * This method should fail because the value is not equal to the number in parameter.
   */
  @Test
  public void should_fail_because_value_is_not_equal() {
    try {
      Table table = new Table(source, "test");
      assertThat(table)
          .row()
              .value("var1").isEqualTo(2);
      
      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Value at index 0 of Row at index 0 of test table] \n" +
          "Expecting:\n" +
          "  <1>\n" +
          "to be equal to: \n" + 
          "  <2>");
    }
  }

  /**
   * This method should fail because the value is not a number.
   */
  @Test
  public void should_fail_because_value_is_not_a_number() {
    try {
      Table table = new Table(source, "test");
      assertThat(table).column("var2")
          .value().as("var2").isEqualTo(1);
      
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
