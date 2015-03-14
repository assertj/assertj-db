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

import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Table;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on the methods which verifies if a value is less than a number.
 * 
 * @author Régis Pouiller
 * 
 */
public class ValueAssert_IsLessThan_Number_Test extends AbstractTest {

  /**
   * This method tests that the value is less than a byte number.
   */
  @Test
  public void test_if_value_is_less_than_number_byte() {
    Table table = new Table(source, "test");
    assertThat(table)
        .row()
            .value("var1").isLessThan((byte) 2).returnToRow()
            .value("var3").isLessThan((byte) 3).returnToRow()
            .value("var4").isLessThan((byte) 4).returnToRow()
            .value("var5").isLessThan((byte) 5).returnToRow()
            .value("var13").isLessThan((byte) 6).returnToRow()
            .value("var14").isLessThan((byte) 8).returnToRow()
        .returnToTable()
        .row()
            .value("var1").isLessThan((byte) 11).returnToRow()
            .value("var3").isLessThan((byte) 21).returnToRow()
            .value("var4").isLessThan((byte) 31).returnToRow()
            .value("var5").isLessThan((byte) 41).returnToRow()
            .value("var13").isLessThan((byte) 51).returnToRow()
            .value("var14").isLessThan((byte) 71);
  }

  /**
   * This method tests that the value is less than a short number.
   */
  @Test
  public void test_if_value_is_less_than_number_short() {
    Table table = new Table(source, "test");
    assertThat(table)
        .row()
            .value("var1").isLessThan((short) 2).returnToRow()
            .value("var3").isLessThan((short) 3).returnToRow()
            .value("var4").isLessThan((short) 4).returnToRow()
            .value("var5").isLessThan((short) 5).returnToRow()
            .value("var13").isLessThan((short) 6).returnToRow()
            .value("var14").isLessThan((short) 8).returnToRow()
        .returnToTable()
        .row()
            .value("var1").isLessThan((short) 11).returnToRow()
            .value("var3").isLessThan((short) 21).returnToRow()
            .value("var4").isLessThan((short) 31).returnToRow()
            .value("var5").isLessThan((short) 41).returnToRow()
            .value("var13").isLessThan((short) 51).returnToRow()
            .value("var14").isLessThan((short) 71);
  }

  /**
   * This method tests that the value is less than a integer number.
   */
  @Test
  public void test_if_value_is_less_than_number_integer() {
    Table table = new Table(source, "test");
    assertThat(table)
        .row()
            .value("var1").isLessThan((int) 2).returnToRow()
            .value("var3").isLessThan((int) 3).returnToRow()
            .value("var4").isLessThan((int) 4).returnToRow()
            .value("var5").isLessThan((int) 5).returnToRow()
            .value("var13").isLessThan((int) 6).returnToRow()
            .value("var14").isLessThan((int) 8).returnToRow()
        .returnToTable()
        .row()
            .value("var1").isLessThan((int) 11).returnToRow()
            .value("var3").isLessThan((int) 21).returnToRow()
            .value("var4").isLessThan((int) 31).returnToRow()
            .value("var5").isLessThan((int) 41).returnToRow()
            .value("var13").isLessThan((int) 51).returnToRow()
            .value("var14").isLessThan((int) 71);
  }

  /**
   * This method tests that the value is less than a long number.
   */
  @Test
  public void test_if_value_is_less_than_number_long() {
    Table table = new Table(source, "test");
    assertThat(table)
        .row()
            .value("var1").isLessThan((long) 2).returnToRow()
            .value("var3").isLessThan((long) 3).returnToRow()
            .value("var4").isLessThan((long) 4).returnToRow()
            .value("var5").isLessThan((long) 5).returnToRow()
            .value("var13").isLessThan((long) 6).returnToRow()
            .value("var14").isLessThan((long) 8).returnToRow()
        .returnToTable()
        .row()
            .value("var1").isLessThan((long) 11).returnToRow()
            .value("var3").isLessThan((long) 21).returnToRow()
            .value("var4").isLessThan((long) 31).returnToRow()
            .value("var5").isLessThan((long) 41).returnToRow()
            .value("var13").isLessThan((long) 51).returnToRow()
            .value("var14").isLessThan((long) 71);
  }

  /**
   * This method tests that the value is less than a integer number.
   */
  @Test
  public void test_if_value_is_less_than_number_biginteger() {
    Table table = new Table(source, "test");
    assertThat(table)
        .row()
            .value("var1").isLessThan(new BigInteger("2")).returnToRow()
            .value("var3").isLessThan(new BigInteger("3")).returnToRow()
            .value("var4").isLessThan(new BigInteger("4")).returnToRow()
            .value("var5").isLessThan(new BigInteger("5")).returnToRow()
        .returnToTable()
        .row()
            .value("var1").isLessThan(new BigInteger("11")).returnToRow()
            .value("var3").isLessThan(new BigInteger("21")).returnToRow()
            .value("var4").isLessThan(new BigInteger("31")).returnToRow()
            .value("var5").isLessThan(new BigInteger("41"));
  }

  /**
   * This method tests that the value is less than a float number.
   */
  @Test
  public void test_if_value_is_less_than_number_float() {
    Table table = new Table(source, "test");
    assertThat(table)
        .row()
            .value("var1").isLessThan((float) 2).returnToRow()
            .value("var3").isLessThan((float) 3).returnToRow()
            .value("var4").isLessThan((float) 4).returnToRow()
            .value("var5").isLessThan((float) 5).returnToRow()
            .value("var6").isLessThan((float) 5.7).returnToRow()
            .value("var7").isLessThan((float) 7.9).returnToRow()
            .value("var13").isLessThan((float) 6).returnToRow()
            .value("var14").isLessThan((float) 8).returnToRow()
        .returnToTable()
        .row()
            .value("var1").isLessThan((float) 11).returnToRow()
            .value("var3").isLessThan((float) 21).returnToRow()
            .value("var4").isLessThan((float) 31).returnToRow()
            .value("var5").isLessThan((float) 41).returnToRow()
            .value("var6").isLessThan((float) 50.7).returnToRow()
            .value("var7").isLessThan((float) 70.9).returnToRow()
            .value("var13").isLessThan((float) 51).returnToRow()
            .value("var14").isLessThan((float) 71);
  }

  /**
   * This method tests that the value is less than a double number.
   */
  @Test
  public void test_if_value_is_less_than_number_double() {
    Table table = new Table(source, "test");
    assertThat(table)
        .row()
            .value("var1").isLessThan((double) 2).returnToRow()
            .value("var3").isLessThan((double) 3).returnToRow()
            .value("var4").isLessThan((double) 4).returnToRow()
            .value("var5").isLessThan((double) 5).returnToRow()
            .value("var6").isLessThan((double) 5.7).returnToRow()
            .value("var7").isLessThan((double) 7.9).returnToRow()
            .value("var13").isLessThan((double) 6).returnToRow()
            .value("var14").isLessThan((double) 8).returnToRow()
        .returnToTable()
        .row()
            .value("var1").isLessThan((double) 11).returnToRow()
            .value("var3").isLessThan((double) 21).returnToRow()
            .value("var4").isLessThan((double) 31).returnToRow()
            .value("var5").isLessThan((double) 41).returnToRow()
            .value("var6").isLessThan((double) 50.7).returnToRow()
            .value("var7").isLessThan((double) 70.9).returnToRow()
            .value("var13").isLessThan((double) 51).returnToRow()
            .value("var14").isLessThan((double) 71);
  }

  /**
   * This method tests that the value is less than a big decimal number.
   */
  @Test
  public void test_if_value_is_less_than_number_bigdecimal() {
    Table table = new Table(source, "test");
    assertThat(table)
        .row()
            .value("var1").isLessThan(new BigDecimal("2")).returnToRow()
            .value("var3").isLessThan(new BigDecimal("3")).returnToRow()
            .value("var4").isLessThan(new BigDecimal("4")).returnToRow()
            .value("var5").isLessThan(new BigDecimal("5")).returnToRow()
            .value("var6").isLessThan(new BigDecimal("5.7")).returnToRow()
            .value("var7").isLessThan(new BigDecimal("7.9")).returnToRow()
            .value("var13").isLessThan(new BigDecimal("6")).returnToRow()
            .value("var14").isLessThan(new BigDecimal("8")).returnToRow()
        .returnToTable()
        .row()
            .value("var1").isLessThan(new BigDecimal("11")).returnToRow()
            .value("var3").isLessThan(new BigDecimal("21")).returnToRow()
            .value("var4").isLessThan(new BigDecimal("31")).returnToRow()
            .value("var5").isLessThan(new BigDecimal("41")).returnToRow()
            .value("var6").isLessThan(new BigDecimal("50.7")).returnToRow()
            .value("var7").isLessThan(new BigDecimal("70.9")).returnToRow()
            .value("var13").isLessThan(new BigDecimal("51")).returnToRow()
            .value("var14").isLessThan(new BigDecimal("71"));
  }

  /**
   * This method should fail because the value is not less than the number in parameter.
   */
  @Test
  public void should_fail_because_value_is_not_less() {
    try {
      Table table = new Table(source, "test");
      assertThat(table)
          .row()
              .value("var1").isLessThan(1);
      
      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Value at index 0 of Row at index 0 of test table] \n" +
          "Expecting:\n" +
          "  <1>\n" +
          "to be less than \n" +
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
          .value().as("var2").isLessThan(1);
      
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
