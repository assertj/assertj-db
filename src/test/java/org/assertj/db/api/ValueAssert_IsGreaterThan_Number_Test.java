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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Tests on the methods which verifies if a value is greater than a number.
 * 
 * @author Régis Pouiller
 * 
 */
public class ValueAssert_IsGreaterThan_Number_Test extends AbstractTest {

  /**
   * This method tests that the value is greater than a byte number.
   */
  @Test
  public void test_if_value_is_greater_than_number_byte() {
    Table table = new Table(source, "test");
    assertThat(table)
        .row()
            .value("var1").isGreaterThan((byte) 0).returnToRow()
            .value("var3").isGreaterThan((byte) 1).returnToRow()
            .value("var4").isGreaterThan((byte) 2).returnToRow()
            .value("var5").isGreaterThan((byte) 3).returnToRow()
            .value("var13").isGreaterThan((byte) 4).returnToRow()
            .value("var14").isGreaterThan((byte) 6).returnToRow()
        .returnToTable()
        .row()
            .value("var1").isGreaterThan((byte) 9).returnToRow()
            .value("var3").isGreaterThan((byte) 19).returnToRow()
            .value("var4").isGreaterThan((byte) 29).returnToRow()
            .value("var5").isGreaterThan((byte) 39).returnToRow()
            .value("var13").isGreaterThan((byte) 49).returnToRow()
            .value("var14").isGreaterThan((byte) 69);
  }

  /**
   * This method tests that the value is greater than a short number.
   */
  @Test
  public void test_if_value_is_greater_than_number_short() {
    Table table = new Table(source, "test");
    assertThat(table)
        .row()
            .value("var1").isGreaterThan((short) 0).returnToRow()
            .value("var3").isGreaterThan((short) 1).returnToRow()
            .value("var4").isGreaterThan((short) 2).returnToRow()
            .value("var5").isGreaterThan((short) 3).returnToRow()
            .value("var13").isGreaterThan((short) 4).returnToRow()
            .value("var14").isGreaterThan((short) 6).returnToRow()
        .returnToTable()
        .row()
            .value("var1").isGreaterThan((short) 9).returnToRow()
            .value("var3").isGreaterThan((short) 19).returnToRow()
            .value("var4").isGreaterThan((short) 29).returnToRow()
            .value("var5").isGreaterThan((short) 39).returnToRow()
            .value("var13").isGreaterThan((short) 49).returnToRow()
            .value("var14").isGreaterThan((short) 69);
  }

  /**
   * This method tests that the value is greater than a integer number.
   */
  @Test
  public void test_if_value_is_greater_than_number_integer() {
    Table table = new Table(source, "test");
    assertThat(table)
        .row()
            .value("var1").isGreaterThan((int) 0).returnToRow()
            .value("var3").isGreaterThan((int) 1).returnToRow()
            .value("var4").isGreaterThan((int) 2).returnToRow()
            .value("var5").isGreaterThan((int) 3).returnToRow()
            .value("var13").isGreaterThan((int) 4).returnToRow()
            .value("var14").isGreaterThan((int) 6).returnToRow()
        .returnToTable()
        .row()
            .value("var1").isGreaterThan((int) 9).returnToRow()
            .value("var3").isGreaterThan((int) 19).returnToRow()
            .value("var4").isGreaterThan((int) 29).returnToRow()
            .value("var5").isGreaterThan((int) 39).returnToRow()
            .value("var13").isGreaterThan((int) 49).returnToRow()
            .value("var14").isGreaterThan((int) 69);
  }

  /**
   * This method tests that the value is greater than a long number.
   */
  @Test
  public void test_if_value_is_greater_than_number_long() {
    Table table = new Table(source, "test");
    assertThat(table)
        .row()
            .value("var1").isGreaterThan((long) 0).returnToRow()
            .value("var3").isGreaterThan((long) 1).returnToRow()
            .value("var4").isGreaterThan((long) 2).returnToRow()
            .value("var5").isGreaterThan((long) 3).returnToRow()
            .value("var13").isGreaterThan((long) 4).returnToRow()
            .value("var14").isGreaterThan((long) 6).returnToRow()
        .returnToTable()
        .row()
            .value("var1").isGreaterThan((long) 9).returnToRow()
            .value("var3").isGreaterThan((long) 19).returnToRow()
            .value("var4").isGreaterThan((long) 29).returnToRow()
            .value("var5").isGreaterThan((long) 39).returnToRow()
            .value("var13").isGreaterThan((long) 49).returnToRow()
            .value("var14").isGreaterThan((long) 69);
  }

  /**
   * This method tests that the value is greater than a nig integer number.
   */
  @Test
  public void test_if_value_is_greater_than_number_biginteger() {
    Table table = new Table(source, "test");
    assertThat(table)
        .row()
            .value("var1").isGreaterThan(new BigInteger("0")).returnToRow()
            .value("var3").isGreaterThan(new BigInteger("1")).returnToRow()
            .value("var4").isGreaterThan(new BigInteger("2")).returnToRow()
            .value("var5").isGreaterThan(new BigInteger("3")).returnToRow()
        .returnToTable()
        .row()
            .value("var1").isGreaterThan(new BigInteger("9")).returnToRow()
            .value("var3").isGreaterThan(new BigInteger("19")).returnToRow()
            .value("var4").isGreaterThan(new BigInteger("29")).returnToRow()
            .value("var5").isGreaterThan(new BigInteger("39"));
  }

  /**
   * This method tests that the value is greater than a float number.
   */
  @Test
  public void test_if_value_is_greater_than_number_float() {
    Table table = new Table(source, "test");
    assertThat(table)
        .row()
            .value("var1").isGreaterThan((float) 0).returnToRow()
            .value("var3").isGreaterThan((float) 1).returnToRow()
            .value("var4").isGreaterThan((float) 2).returnToRow()
            .value("var5").isGreaterThan((float) 3).returnToRow()
            .value("var6").isGreaterThan((float) 5.5).returnToRow()
            .value("var7").isGreaterThan((float) 7.7).returnToRow()
            .value("var13").isGreaterThan((float) 4).returnToRow()
            .value("var14").isGreaterThan((float) 6).returnToRow()
        .returnToTable()
        .row()
            .value("var1").isGreaterThan((float) 9).returnToRow()
            .value("var3").isGreaterThan((float) 19).returnToRow()
            .value("var4").isGreaterThan((float) 29).returnToRow()
            .value("var5").isGreaterThan((float) 39).returnToRow()
            .value("var6").isGreaterThan((float) 50.5).returnToRow()
            .value("var7").isGreaterThan((float) 69.7).returnToRow()
            .value("var13").isGreaterThan((float) 49).returnToRow()
            .value("var14").isGreaterThan((float) 69);
  }

  /**
   * This method tests that the value is greater than a double number.
   */
  @Test
  public void test_if_value_is_greater_than_number_double() {
    Table table = new Table(source, "test");
    assertThat(table)
        .row()
            .value("var1").isGreaterThan((double) 0).returnToRow()
            .value("var3").isGreaterThan((double) 1).returnToRow()
            .value("var4").isGreaterThan((double) 2).returnToRow()
            .value("var5").isGreaterThan((double) 3).returnToRow()
            .value("var6").isGreaterThan((double) 5.5).returnToRow()
            .value("var7").isGreaterThan((double) 7.7).returnToRow()
            .value("var13").isGreaterThan((double) 4).returnToRow()
            .value("var14").isGreaterThan((double) 6).returnToRow()
        .returnToTable()
        .row()
            .value("var1").isGreaterThan((double) 9).returnToRow()
            .value("var3").isGreaterThan((double) 19).returnToRow()
            .value("var4").isGreaterThan((double) 29).returnToRow()
            .value("var5").isGreaterThan((double) 39).returnToRow()
            .value("var6").isGreaterThan((double) 50.5).returnToRow()
            .value("var7").isGreaterThan((double) 69.7).returnToRow()
            .value("var13").isGreaterThan((double) 49).returnToRow()
            .value("var14").isGreaterThan((double) 69);
  }

  /**
   * This method tests that the value is greater than a big decimal number.
   */
  @Test
  public void test_if_value_is_greater_than_number_bigdecimal() {
    Table table = new Table(source, "test");
    assertThat(table)
        .row()
            .value("var1").isGreaterThan(new BigDecimal("0")).returnToRow()
            .value("var3").isGreaterThan(new BigDecimal("1")).returnToRow()
            .value("var4").isGreaterThan(new BigDecimal("2")).returnToRow()
            .value("var5").isGreaterThan(new BigDecimal("3")).returnToRow()
            .value("var6").isGreaterThan(new BigDecimal("5.5")).returnToRow()
            .value("var7").isGreaterThan(new BigDecimal("7.7")).returnToRow()
            .value("var13").isGreaterThan(new BigDecimal("4")).returnToRow()
            .value("var14").isGreaterThan(new BigDecimal("6")).returnToRow()
        .returnToTable()
        .row()
            .value("var1").isGreaterThan(new BigDecimal("9")).returnToRow()
            .value("var3").isGreaterThan(new BigDecimal("19")).returnToRow()
            .value("var4").isGreaterThan(new BigDecimal("29")).returnToRow()
            .value("var5").isGreaterThan(new BigDecimal("39")).returnToRow()
            .value("var6").isGreaterThan(new BigDecimal("50.5")).returnToRow()
            .value("var7").isGreaterThan(new BigDecimal("69.7")).returnToRow()
            .value("var13").isGreaterThan(new BigDecimal("49")).returnToRow()
            .value("var14").isGreaterThan(new BigDecimal("69"));
  }

  /**
   * This method should fail because the value is not greater than the number in parameter.
   */
  @Test
  public void should_fail_because_value_is_not_greater() {
    try {
      Table table = new Table(source, "test");
      assertThat(table)
          .row()
              .value("var1").isGreaterThan(1);
      
      fail("Une Erreur doit être levée");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Value at index 0 of Row at index 0 of test table] \n" +
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
      Table table = new Table(source, "test");
      assertThat(table).column("var2")
          .value().as("var2").isGreaterThan(0);
      
      fail("Une Erreur doit être levée");
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
