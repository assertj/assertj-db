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
 * Tests on the methods which verifies if a value is not equal to a number.
 * 
 * @author Régis Pouiller
 * 
 */
public class ValueAssert_IsNotEqualTo_Number_Test extends AbstractTest {

  /**
   * This method tests that the value is not equal to a byte number.
   */
  @Test
  public void test_if_value_is_not_equal_to_number_byte() {
    Table table = new Table(source, "test");
    assertThat(table)
        .row()
            .value("var1").isNotEqualTo((byte) 2).returnToRow()
            .value("var3").isNotEqualTo((byte) 3).returnToRow()
            .value("var4").isNotEqualTo((byte) 4).returnToRow()
            .value("var5").isNotEqualTo((byte) 5).returnToRow()
            .value("var13").isNotEqualTo((byte) 6).returnToRow()
            .value("var14").isNotEqualTo((byte) 8).returnToRow()
        .returnToTable()
        .row()
            .value("var1").isNotEqualTo((byte) 11).returnToRow()
            .value("var3").isNotEqualTo((byte) 21).returnToRow()
            .value("var4").isNotEqualTo((byte) 31).returnToRow()
            .value("var5").isNotEqualTo((byte) 41).returnToRow()
            .value("var13").isNotEqualTo((byte) 51).returnToRow()
            .value("var14").isNotEqualTo((byte) 71);
  }

  /**
   * This method tests that the value is not equal to a short number.
   */
  @Test
  public void test_if_value_is_not_equal_to_number_short() {
    Table table = new Table(source, "test");
    assertThat(table)
        .row()
            .value("var1").isNotEqualTo((short) 2).returnToRow()
            .value("var3").isNotEqualTo((short) 3).returnToRow()
            .value("var4").isNotEqualTo((short) 4).returnToRow()
            .value("var5").isNotEqualTo((short) 5).returnToRow()
            .value("var13").isNotEqualTo((short) 6).returnToRow()
            .value("var14").isNotEqualTo((short) 8).returnToRow()
        .returnToTable()
        .row()
            .value("var1").isNotEqualTo((short) 11).returnToRow()
            .value("var3").isNotEqualTo((short) 21).returnToRow()
            .value("var4").isNotEqualTo((short) 31).returnToRow()
            .value("var5").isNotEqualTo((short) 41).returnToRow()
            .value("var13").isNotEqualTo((short) 51).returnToRow()
            .value("var14").isNotEqualTo((short) 71);
  }

  /**
   * This method tests that the value is not equal to a integer number.
   */
  @Test
  public void test_if_value_is_not_equal_to_number_integer() {
    Table table = new Table(source, "test");
    assertThat(table)
        .row()
            .value("var1").isNotEqualTo((int) 2).returnToRow()
            .value("var3").isNotEqualTo((int) 3).returnToRow()
            .value("var4").isNotEqualTo((int) 4).returnToRow()
            .value("var5").isNotEqualTo((int) 5).returnToRow()
            .value("var13").isNotEqualTo((int) 6).returnToRow()
            .value("var14").isNotEqualTo((int) 8).returnToRow()
        .returnToTable()
        .row()
            .value("var1").isNotEqualTo((int) 11).returnToRow()
            .value("var3").isNotEqualTo((int) 21).returnToRow()
            .value("var4").isNotEqualTo((int) 31).returnToRow()
            .value("var5").isNotEqualTo((int) 41).returnToRow()
            .value("var13").isNotEqualTo((int) 51).returnToRow()
            .value("var14").isNotEqualTo((int) 71);
  }

  /**
   * This method tests that the value is not equal to a long number.
   */
  @Test
  public void test_if_value_is_not_equal_to_number_long() {
    Table table = new Table(source, "test");
    assertThat(table)
        .row()
            .value("var1").isNotEqualTo((long) 2).returnToRow()
            .value("var3").isNotEqualTo((long) 3).returnToRow()
            .value("var4").isNotEqualTo((long) 4).returnToRow()
            .value("var5").isNotEqualTo((long) 5).returnToRow()
            .value("var13").isNotEqualTo((long) 6).returnToRow()
            .value("var14").isNotEqualTo((long) 8).returnToRow()
        .returnToTable()
        .row()
            .value("var1").isNotEqualTo((long) 11).returnToRow()
            .value("var3").isNotEqualTo((long) 21).returnToRow()
            .value("var4").isNotEqualTo((long) 31).returnToRow()
            .value("var5").isNotEqualTo((long) 41).returnToRow()
            .value("var13").isNotEqualTo((long) 51).returnToRow()
            .value("var14").isNotEqualTo((long) 71);
  }

  /**
   * This method tests that the value is not equal to an integer number.
   */
  @Test
  public void test_if_value_is_not_equal_to_number_biginteger() {
    Table table = new Table(source, "test");
    assertThat(table)
        .row()
            .value("var1").isNotEqualTo(new BigInteger("2")).returnToRow()
            .value("var3").isNotEqualTo(new BigInteger("3")).returnToRow()
            .value("var4").isNotEqualTo(new BigInteger("4")).returnToRow()
            .value("var5").isNotEqualTo(new BigInteger("5")).returnToRow()
        .returnToTable()
        .row()
            .value("var1").isNotEqualTo(new BigInteger("11")).returnToRow()
            .value("var3").isNotEqualTo(new BigInteger("21")).returnToRow()
            .value("var4").isNotEqualTo(new BigInteger("31")).returnToRow()
            .value("var5").isNotEqualTo(new BigInteger("41"));
  }

  /**
   * This method tests that the value is not equal to a float number.
   */
  @Test
  public void test_if_value_is_not_equal_to_number_float() {
    Table table = new Table(source, "test");
    assertThat(table)
        .row()
            .value("var1").isNotEqualTo((float) 2).returnToRow()
            .value("var3").isNotEqualTo((float) 3).returnToRow()
            .value("var4").isNotEqualTo((float) 4).returnToRow()
            .value("var5").isNotEqualTo((float) 5).returnToRow()
            .value("var6").isNotEqualTo((float) 6.6).returnToRow()
            .value("var7").isNotEqualTo((float) 8.8).returnToRow()
            .value("var13").isNotEqualTo((float) 6).returnToRow()
            .value("var14").isNotEqualTo((float) 8).returnToRow()
        .returnToTable()
        .row()
            .value("var1").isNotEqualTo((float) 11).returnToRow()
            .value("var3").isNotEqualTo((float) 21).returnToRow()
            .value("var4").isNotEqualTo((float) 31).returnToRow()
            .value("var5").isNotEqualTo((float) 41).returnToRow()
            .value("var6").isNotEqualTo((float) 51.6).returnToRow()
            .value("var7").isNotEqualTo((float) 71.8).returnToRow()
            .value("var13").isNotEqualTo((float) 51).returnToRow()
            .value("var14").isNotEqualTo((float) 71);
  }

  /**
   * This method tests that the value is not equal to a double number.
   */
  @Test
  public void test_if_value_is_not_equal_to_number_double() {
    Table table = new Table(source, "test");
    assertThat(table)
        .row()
            .value("var1").isNotEqualTo((double) 2).returnToRow()
            .value("var3").isNotEqualTo((double) 3).returnToRow()
            .value("var4").isNotEqualTo((double) 4).returnToRow()
            .value("var5").isNotEqualTo((double) 5).returnToRow()
            .value("var6").isNotEqualTo((double) 6.6).returnToRow()
            .value("var7").isNotEqualTo((double) 8.8).returnToRow()
            .value("var13").isNotEqualTo((double) 6).returnToRow()
            .value("var14").isNotEqualTo((double) 8).returnToRow()
        .returnToTable()
        .row()
            .value("var1").isNotEqualTo((double) 11).returnToRow()
            .value("var3").isNotEqualTo((double) 21).returnToRow()
            .value("var4").isNotEqualTo((double) 31).returnToRow()
            .value("var5").isNotEqualTo((double) 41).returnToRow()
            .value("var6").isNotEqualTo((double) 51.6).returnToRow()
            .value("var7").isNotEqualTo((double) 71.8).returnToRow()
            .value("var13").isNotEqualTo((double) 51).returnToRow()
            .value("var14").isNotEqualTo((double) 71);
  }

  /**
   * This method tests that the value is not equal to a big decimal number.
   */
  @Test
  public void test_if_value_is_not_equal_to_number_bigdecimal() {
    Table table = new Table(source, "test");
    assertThat(table)
        .row()
            .value("var1").isNotEqualTo(new BigDecimal("2")).returnToRow()
            .value("var3").isNotEqualTo(new BigDecimal("3")).returnToRow()
            .value("var4").isNotEqualTo(new BigDecimal("4")).returnToRow()
            .value("var5").isNotEqualTo(new BigDecimal("5")).returnToRow()
            .value("var6").isNotEqualTo(new BigDecimal("6.6")).returnToRow()
            .value("var7").isNotEqualTo(new BigDecimal("8.8")).returnToRow()
            .value("var13").isNotEqualTo(new BigDecimal("6")).returnToRow()
            .value("var14").isNotEqualTo(new BigDecimal("8")).returnToRow()
        .returnToTable()
        .row()
            .value("var1").isNotEqualTo(new BigDecimal("11")).returnToRow()
            .value("var3").isNotEqualTo(new BigDecimal("21")).returnToRow()
            .value("var4").isNotEqualTo(new BigDecimal("31")).returnToRow()
            .value("var5").isNotEqualTo(new BigDecimal("41")).returnToRow()
            .value("var6").isNotEqualTo(new BigDecimal("51.6")).returnToRow()
            .value("var7").isNotEqualTo(new BigDecimal("71.8")).returnToRow()
            .value("var13").isNotEqualTo(new BigDecimal("51")).returnToRow()
            .value("var14").isNotEqualTo(new BigDecimal("71"));
  }

  /**
   * This method should fail because the value is equal to the number in parameter.
   */
  @Test
  public void should_fail_because_value_is_equal() {
    try {
      Table table = new Table(source, "test");
      assertThat(table)
          .row()
              .value("var1").isNotEqualTo(1);
      
      fail("Une Erreur doit être levée");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Value at index 0 of Row at index 0 of test table] \n" +
          "Expecting:\n" +
          "  <1>\n" +
          "not to be equal to: \n" +
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
          .value().as("var2").isNotEqualTo(1);
      
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
