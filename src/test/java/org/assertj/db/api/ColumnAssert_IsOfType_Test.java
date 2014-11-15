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

import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Table;
import org.assertj.db.type.ValueType;
import org.junit.Test;

/**
 * Test on the {@code isOfType} assertion method on {@code Column}.
 * 
 * @author Régis Pouiller
 * 
 */
public class ColumnAssert_IsOfType_Test extends AbstractTest {

  /**
   * This method tests the {@code isOfType} assertion method.
   */
  @Test
  public void test_isOfType_assertion() {
    Table table = new Table(source, "test");

    assertThat(table)
        .column().as("var1").isOfType(ValueType.NUMBER, true)
        .column().as("var2").isOfType(ValueType.BOOLEAN, true)
        .column().as("var3").isOfType(ValueType.NUMBER, true)
        .column().as("var4").isOfType(ValueType.NUMBER, true)
        .column().as("var5").isOfType(ValueType.NUMBER, true)
        .column().as("var6").isOfType(ValueType.NUMBER, true)
        .column().as("var7").isOfType(ValueType.NUMBER, true)
        .column().as("var8").isOfType(ValueType.TIME, true)
        .column().as("var9").isOfType(ValueType.DATE, true)
        .column().as("var10").isOfType(ValueType.DATE_TIME, true)
        .column().as("var11").isOfType(ValueType.BYTES, true)
        .column().as("var12").isOfType(ValueType.TEXT, true)
        .column().as("var13").isOfType(ValueType.NUMBER, true)
        .column().as("var14").isOfType(ValueType.NUMBER, true)
        .column(0).as("var1").isOfType(ValueType.NUMBER, false)
        .column().as("var2").isOfType(ValueType.BOOLEAN, false)
        .column().as("var3").isOfType(ValueType.NUMBER, false)
        .column().as("var4").isOfType(ValueType.NUMBER, false)
        .column().as("var5").isOfType(ValueType.NUMBER, false)
        .column().as("var6").isOfType(ValueType.NUMBER, false)
        .column().as("var7").isOfType(ValueType.NUMBER, false)
        .column().as("var8").isOfType(ValueType.TIME, false)
        .column().as("var9").isOfType(ValueType.DATE, false)
        .column().as("var10").isOfType(ValueType.DATE_TIME, false)
        .column().as("var11").isOfType(ValueType.BYTES, false)
        .column().as("var12").isOfType(ValueType.TEXT, false)
        .column().as("var13").isOfType(ValueType.NUMBER, false)
        .column().as("var14").isOfType(ValueType.NUMBER, false);

    Table table2 = new Table(source, "test2");

    assertThat(table2)
        .column().as("var1").isOfType(ValueType.NUMBER, true)
        .column().as("var2").isOfType(ValueType.BOOLEAN, true)
        .column().as("var3").isOfType(ValueType.NUMBER, true)
        .column().as("var4").isOfType(ValueType.NUMBER, true)
        .column().as("var5").isOfType(ValueType.NUMBER, true)
        .column().as("var6").isOfType(ValueType.NUMBER, true)
        .column().as("var7").isOfType(ValueType.NUMBER, true)
        .column().as("var8").isOfType(ValueType.TIME, true)
        .column().as("var9").isOfType(ValueType.DATE, true)
        .column().as("var10").isOfType(ValueType.DATE_TIME, true)
        .column().as("var11").isOfType(ValueType.BYTES, true)
        .column().as("var12").isOfType(ValueType.TEXT, true)
        .column().as("var13").isOfType(ValueType.NUMBER, true)
        .column().as("var14").isOfType(ValueType.NUMBER, true);
  }

  /**
   * This method should fail because the type of the column is {@code ValueType.Number}.
   */
  @Test
  public void should_fail_isOfType_assertion_because_value_is_number() {
    try {
      Table table = new Table(source, "test");
  
      assertThat(table)
          .column().as("var1 type").isOfType(ValueType.BOOLEAN, true);
      
      fail("Une Erreur doit être levée");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[var1 type] \n" +
          "Expecting that the value at index 0:\n" +
          "  <1>\n" +
          "to be of type\n" +
          "  <[BOOLEAN, NOT_IDENTIFIED]>\n" +
          "but was of type\n" +
          "  <NUMBER>");
    }
  }

  /**
   * This method should fail because a value is {@code null}.
   */
  @Test
  public void should_fail_isOfType_assertion_because_value_is_null() {
    try {
      Table table = new Table(source, "test2");
  
      assertThat(table)
          .column().as("var1 type").isOfType(ValueType.NUMBER, false);
      
      fail("Une Erreur doit être levée");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[var1 type] \n" +
          "Expecting that the value at index 1:\n" +
          "  <null>\n" +
          "to be of type\n" +
          "  <NUMBER>\n" +
          "but was of type\n" +
          "  <NOT_IDENTIFIED>");
    }
  }

  /**
   * This method tests the result of {@code isNumber}, {@code isBoolean} and others methods.
   */
  @Test
  public void test_the_types_with_the_methods_to_test_that() {
    Table table = new Table(source, "test");

    assertThat(table)
        .column().as("var1").isNumber(true)
        .column().as("var2").isBoolean(true)
        .column().as("var3").isNumber(true)
        .column().as("var4").isNumber(true)
        .column().as("var5").isNumber(true)
        .column().as("var6").isNumber(true)
        .column().as("var7").isNumber(true)
        .column().as("var8").isTime(true)
        .column().as("var9").isDate(true)
        .column().as("var10").isDateTime(true)
        .column().as("var11").isBytes(true)
        .column().as("var12").isText(true)
        .column().as("var13").isNumber(true)
        .column().as("var14").isNumber(true)
        .column(0).as("var1").isNumber(false)
        .column().as("var2").isBoolean(false)
        .column().as("var3").isNumber(false)
        .column().as("var4").isNumber(false)
        .column().as("var5").isNumber(false)
        .column().as("var6").isNumber(false)
        .column().as("var7").isNumber(false)
        .column().as("var8").isTime(false)
        .column().as("var9").isDate(false)
        .column().as("var10").isDateTime(false)
        .column().as("var11").isBytes(false)
        .column().as("var12").isText(false)
        .column().as("var13").isNumber(false)
        .column().as("var14").isNumber(false);

    Table table2 = new Table(source, "test2");

    assertThat(table2)
        .column().as("var1").isNumber(true)
        .column().as("var2").isBoolean(true)
        .column().as("var3").isNumber(true)
        .column().as("var4").isNumber(true)
        .column().as("var5").isNumber(true)
        .column().as("var6").isNumber(true)
        .column().as("var7").isNumber(true)
        .column().as("var8").isTime(true)
        .column().as("var9").isDate(true)
        .column().as("var10").isDateTime(true)
        .column().as("var11").isBytes(true)
        .column().as("var12").isText(true)
        .column().as("var13").isNumber(true)
        .column().as("var14").isNumber(true);
  }

  /**
   * This method should fail because the type of the column is a number.
   */
  @Test
  public void should_fail_isBoolean_assertion_because_value_is_number() {
    try {
      Table table = new Table(source, "test");
  
      assertThat(table)
          .column().as("var1 type").isBoolean(true);
      
      fail("Une Erreur doit être levée");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[var1 type] \n" +
          "Expecting that the value at index 0:\n" +
          "  <1>\n" +
          "to be of type\n" +
          "  <[BOOLEAN, NOT_IDENTIFIED]>\n" +
          "but was of type\n" +
          "  <NUMBER>");
    }
  }

  /**
   * This method should fail because a value is {@code null}.
   */
  @Test
  public void should_fail_isNumber_assertion_because_value_is_null() {
    try {
      Table table = new Table(source, "test2");
  
      assertThat(table)
          .column().as("var1 type").isNumber(false);
      
      fail("Une Erreur doit être levée");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[var1 type] \n" +
          "Expecting that the value at index 1:\n" +
          "  <null>\n" +
          "to be of type\n" +
          "  <NUMBER>\n" +
          "but was of type\n" +
          "  <NOT_IDENTIFIED>");
    }
  }

}
