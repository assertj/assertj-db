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
 * Test on the {@code haveValuesEqualTo} assertion method on {@code Column} for the {@code Number}.
 * 
 * @author Régis Pouiller
 * 
 */
public class ColumnAssert_HaveValuesEqualTo_Number_Test extends AbstractTest {

  /**
   * This method tests the {@code haveValuesEqualTo} assertion method.
   */
  @Test
  public void test_haveValuesEqualTo_assertion() {
    Table table = new Table(source, "test");

    assertThat(table)
        .column().as("var1").haveValuesEqualTo(1, 10, 100, 0)
        .column("var3").haveValuesEqualTo(2, 20, 25, 0)
        .column().as("var4").haveValuesEqualTo(3, 30, 300, 0)
        .column().as("var5").haveValuesEqualTo(4, 40, 400, 0)
        .column().as("var6").haveValuesEqualTo(5.6, 50.6, 500.6, 0)
        .column().as("var7").haveValuesEqualTo(7.8, 70.8, 700.8, 0)
        .column("var13").haveValuesEqualTo(5, 50, 500, 500)
        .column().as("var14").haveValuesEqualTo(7, 70, 700, 700);

    Table table2 = new Table(source, "test2");

    assertThat(table2)
        .column("var1").haveValuesEqualTo(1, null)
        .column("var3").haveValuesEqualTo(2, null)
        .column("var4").haveValuesEqualTo(3, null)
        .column("var5").haveValuesEqualTo(4, null)
        .column("var6").haveValuesEqualTo(5.6, null)
        .column("var7").haveValuesEqualTo(7.8, null)
        .column("var13").haveValuesEqualTo(5, null)
        .column("var14").haveValuesEqualTo(7, null);
  }

  /**
   * This method should fail because the type of the column is {@code ValueType.Boolean}.
   */
  @Test
  public void should_fail_haveValuesEqualTo_assertion_because_column_is_boolean() {
    try {
      Table table = new Table(source, "test2");
  
      assertThat(table)
          .column(1).as("var2 type").haveValuesEqualTo(1, 10, 100, 0);
      
      fail("Une Erreur doit être levée");
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
   * This method should fail because the type of the column have less values.
   */
  @Test
  public void should_fail_haveValuesEqualTo_assertion_because_column_have_less_values() {
    try {
      Table table2 = new Table(source, "test2");
  
      assertThat(table2)
          .column().as("var1").haveValuesEqualTo(1, 10, 100);
      
      fail("Une Erreur doit être levée");
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
  public void should_fail_haveValuesEqualTo_assertion_because_value_is_different_because_is_null() {
    try {
      Table table = new Table(source, "test2");
  
      assertThat(table)
          .column().as("var1").haveValuesEqualTo(1, 1);
      
      fail("Une Erreur doit être levée");
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
  public void should_fail_haveValuesEqualTo_assertion_because_value_is_different() {
    try {
      Table table = new Table(source, "test2");
  
      assertThat(table)
          .column().as("var1").haveValuesEqualTo(2, 1);
      
      fail("Une Erreur doit être levée");
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
