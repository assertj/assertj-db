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

import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Table;
import org.assertj.db.type.ValueType;
import org.junit.Test;

/**
 * Test on the type methods ({@code getType}, {@code isOfType}, ...) on value.
 * 
 * @author Régis Pouiller
 * 
 */
public class ValueAssert_Type_Test extends AbstractTest {

  /**
   * This method tests the result of {@code getType} method.
   */
  @Test
  public void test_the_result_when_getting_the_type() {
    Table table = new Table(source, "test");
    TableRowAssert tableAssert = assertThat(table).row();

    assertThat(tableAssert.value().getType()).as("var1 type").isEqualTo(ValueType.NUMBER);
    assertThat(tableAssert.value().getType()).as("var2 type").isEqualTo(ValueType.BOOLEAN);
    assertThat(tableAssert.value().getType()).as("var3 type").isEqualTo(ValueType.NUMBER);
    assertThat(tableAssert.value().getType()).as("var4 type").isEqualTo(ValueType.NUMBER);
    assertThat(tableAssert.value().getType()).as("var5 type").isEqualTo(ValueType.NUMBER);
    assertThat(tableAssert.value().getType()).as("var6 type").isEqualTo(ValueType.NUMBER);
    assertThat(tableAssert.value().getType()).as("var7 type").isEqualTo(ValueType.NUMBER);
    assertThat(tableAssert.value().getType()).as("var8 type").isEqualTo(ValueType.TIME);
    assertThat(tableAssert.value().getType()).as("var9 type").isEqualTo(ValueType.DATE);
    assertThat(tableAssert.value().getType()).as("var10 type").isEqualTo(ValueType.DATE_TIME);
    assertThat(tableAssert.value().getType()).as("var11 type").isEqualTo(ValueType.BYTES);
    assertThat(tableAssert.value().getType()).as("var12 type").isEqualTo(ValueType.TEXT);
    assertThat(tableAssert.value().getType()).as("var13 type").isEqualTo(ValueType.NUMBER);
    assertThat(tableAssert.value().getType()).as("var14 type").isEqualTo(ValueType.NUMBER);
  }

  /**
   * This method tests the {@code isOfType} assertion method.
   */
  @Test
  public void test_isOfType_assertion() {
    Table table = new Table(source, "test");

    assertThat(table)
        .row()
            .value().as("var1").isOfType(ValueType.NUMBER).returnToRow()
            .value().as("var2").isOfType(ValueType.BOOLEAN).returnToRow()
            .value().as("var3").isOfType(ValueType.NUMBER).returnToRow()
            .value().as("var4").isOfType(ValueType.NUMBER).returnToRow()
            .value().as("var5").isOfType(ValueType.NUMBER).returnToRow()
            .value().as("var6").isOfType(ValueType.NUMBER).returnToRow()
            .value().as("var7").isOfType(ValueType.NUMBER).returnToRow()
            .value().as("var8").isOfType(ValueType.TIME).returnToRow()
            .value().as("var9").isOfType(ValueType.DATE).returnToRow()
            .value().as("var10").isOfType(ValueType.DATE_TIME).returnToRow()
            .value().as("var11").isOfType(ValueType.BYTES).returnToRow()
            .value().as("var12").isOfType(ValueType.TEXT).returnToRow()
            .value().as("var13").isOfType(ValueType.NUMBER).returnToRow()
            .value().as("var14").isOfType(ValueType.NUMBER);
  }

  /**
   * This method should fail because the type of the value is {@code ValueType.Number}.
   */
  @Test
  public void should_fail_isOfType_assertion_because_value_is_number() {
    try {
      Table table = new Table(source, "test");
  
      assertThat(table)
          .row()
              .value().as("var1 type").isOfType(ValueType.BOOLEAN);
      
      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[var1 type] \n" +
          "Expecting:\n" +
          "  <1>\n" +
          "to be of type\n" +
          "  <BOOLEAN>\n" +
          "but was of type\n" +
          "  <NUMBER>");
    }
  }

  /**
   * This method tests the result of {@code isNumber}, {@code isBoolean} and others methods.
   */
  @Test
  public void test_the_types_with_the_methods_to_test_that() {
    Table table = new Table(source, "test");

    assertThat(table)
        .row()
            .value().as("var1").isNumber().returnToRow()
            .value().as("var2").isBoolean().returnToRow()
            .value().as("var3").isNumber().returnToRow()
            .value().as("var4").isNumber().returnToRow()
            .value().as("var5").isNumber().returnToRow()
            .value().as("var6").isNumber().returnToRow()
            .value().as("var7").isNumber().returnToRow()
            .value().as("var8").isTime().returnToRow()
            .value().as("var9").isDate().returnToRow()
            .value().as("var10").isDateTime().returnToRow()
            .value().as("var11").isBytes().returnToRow()
            .value().as("var12").isText().returnToRow()
            .value().as("var13").isNumber().returnToRow()
            .value().as("var14").isNumber();
  }

  /**
   * This method should fail because var2 is a boolean and not a number.
   */
  @Test
  public void should_fail_because_var2_is_not_a_number() {
    try {
      Table table = new Table(source, "test");
  
      assertThat(table)
          .row()
              .value().as("var1").isNumber().returnToRow()
              .value().as("var2").isNumber();
      
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

  /**
   * This method should fail because var1 is a boolean and not a number.
   */
  @Test
  public void should_fail_because_var1_is_not_a_boolean() {
    try {
      Table table = new Table(source, "test");
  
      assertThat(table)
          .row()
              .value().as("var1").isBoolean();
      
      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[var1] \n" +
          "Expecting:\n" +
          "  <1>\n" +
          "to be of type\n" +
          "  <BOOLEAN>\n" +
          "but was of type\n" +
          "  <NUMBER>");
    }
  }

  /**
   * This method should fail because var1 is a boolean and not a time.
   */
  @Test
  public void should_fail_because_var1_is_not_a_time() {
    try {
      Table table = new Table(source, "test");
  
      assertThat(table)
          .row()
              .value().as("var1").isTime();
      
      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[var1] \n" +
          "Expecting:\n" +
          "  <1>\n" +
          "to be of type\n" +
          "  <TIME>\n" +
          "but was of type\n" +
          "  <NUMBER>");
    }
  }

  /**
   * This method should fail because var1 is a boolean and not a date.
   */
  @Test
  public void should_fail_because_var1_is_not_a_date() {
    try {
      Table table = new Table(source, "test");
  
      assertThat(table)
          .row()
              .value().as("var1").isDate();
      
      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[var1] \n" +
          "Expecting:\n" +
          "  <1>\n" +
          "to be of type\n" +
          "  <DATE>\n" +
          "but was of type\n" +
          "  <NUMBER>");
    }
  }

  /**
   * This method should fail because var1 is a boolean and not a date/time.
   */
  @Test
  public void should_fail_because_var1_is_not_a_datetime() {
    try {
      Table table = new Table(source, "test");
  
      assertThat(table)
          .row()
              .value().as("var1").isDateTime();
      
      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[var1] \n" +
          "Expecting:\n" +
          "  <1>\n" +
          "to be of type\n" +
          "  <DATE_TIME>\n" +
          "but was of type\n" +
          "  <NUMBER>");
    }
  }

  /**
   * This method should fail because var1 is a boolean and not a array of bytes.
   */
  @Test
  public void should_fail_because_var1_is_not_a_array_of_byte() {
    try {
      Table table = new Table(source, "test");
  
      assertThat(table)
          .row()
              .value().as("var1").isBytes();
      
      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[var1] \n" +
          "Expecting:\n" +
          "  <1>\n" +
          "to be of type\n" +
          "  <BYTES>\n" +
          "but was of type\n" +
          "  <NUMBER>");
    }
  }

  /**
   * This method should fail because var1 is a boolean and not a text.
   */
  @Test
  public void should_fail_because_var1_is_not_a_text() {
    try {
      Table table = new Table(source, "test");
  
      assertThat(table)
          .row()
              .value().as("var1").isText();
      
      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[var1] \n" +
          "Expecting:\n" +
          "  <1>\n" +
          "to be of type\n" +
          "  <TEXT>\n" +
          "but was of type\n" +
          "  <NUMBER>");
    }
  }
}
