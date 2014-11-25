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
import org.junit.Test;

/**
 * Test on the {@code hasValuesEqualTo} assertion method on {@code Column} for the {@code Number}.
 * 
 * @author Régis Pouiller
 * 
 */
public class ColumnAssert_HaveValuesEqualTo_Number_Test extends AbstractTest {

  /**
   * This method tests the {@code hasValuesEqualTo} assertion method.
   */
  @Test
  public void test_hasValuesEqualTo_assertion() {
    Table table = new Table(source, "test");

    assertThat(table)
        .column().as("var1").hasValuesEqualTo(1, 10, 100, 1000)
        .column("var3").hasValuesEqualTo(2, 20, 25, 0)
        .column().as("var4").hasValuesEqualTo(3, 30, 300, 0)
        .column().as("var5").hasValuesEqualTo(4, 40, 400, 0)
        .column().as("var6").hasValuesEqualTo(5.6, 50.6, 500.6, 0)
        .column().as("var7").hasValuesEqualTo(7.8, 70.8, 700.8, 0)
        .column("var13").hasValuesEqualTo(5, 50, 500, 500)
        .column().as("var14").hasValuesEqualTo(7, 70, 700, 700);

    Table table2 = new Table(source, "test2");

    assertThat(table2)
        .column("var1").hasValuesEqualTo(1, null)
        .column("var3").hasValuesEqualTo(2, null)
        .column("var4").hasValuesEqualTo(3, null)
        .column("var5").hasValuesEqualTo(4, null)
        .column("var6").hasValuesEqualTo(5.6, null)
        .column("var7").hasValuesEqualTo(7.8, null)
        .column("var13").hasValuesEqualTo(5, null)
        .column("var14").hasValuesEqualTo(7, null);
  }

  /**
   * This method should fail because the type of the column is {@code ValueType.Boolean}.
   */
  @Test
  public void should_fail_hasValuesEqualTo_assertion_because_column_is_boolean() {
    try {
      Table table = new Table(source, "test2");
  
      assertThat(table)
          .column(1).as("var2 type").hasValuesEqualTo(1, 10, 100, 0);
      
      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[var2 type] \n" +
          "Expecting that the value at index 0:\n" +
          "  <true>\n" +
          "to be of type\n" +
          "  <[NUMBER, NOT_IDENTIFIED]>\n" +
          "but was of type\n" +
          "  <BOOLEAN>");
    }
  }

  /**
   * This method should fail because the type of the column has less values.
   */
  @Test
  public void should_fail_hasValuesEqualTo_assertion_because_column_has_less_values() {
    try {
      Table table2 = new Table(source, "test2");
  
      assertThat(table2)
          .column().as("var1").hasValuesEqualTo(1, 10, 100);
      
      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[var1] \n" +
          "Expecting size (number of rows) to be equal to :\n" +
          "   <3>\n" +
          "but was:\n" +
          "   <2>");
    }
  }

  /**
   * This method should fail because the second value is {@code null}.
   */
  @Test
  public void should_fail_hasValuesEqualTo_assertion_because_value_is_different_because_is_null() {
    try {
      Table table = new Table(source, "test2");
  
      assertThat(table)
          .column().as("var1").hasValuesEqualTo(1, 1);
      
      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[var1] \n" +
          "Expecting that the value at index 1:\n" +
          "  <null>\n" +
          "to be equal to: \n" +
          "  <1>");
    }
  }

  /**
   * This method should fail because the first value is 1.
   */
  @Test
  public void should_fail_hasValuesEqualTo_assertion_because_value_is_different() {
    try {
      Table table = new Table(source, "test2");
  
      assertThat(table)
          .column().as("var1").hasValuesEqualTo(2, 1);
      
      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[var1] \n" +
          "Expecting that the value at index 0:\n" +
          "  <1>\n" +
          "to be equal to: \n" +
          "  <2>");
    }
  }

}
