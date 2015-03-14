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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Test on the {@code hasValuesEqualTo} assertion method on {@code Column} for the {@code boolean}.
 * 
 * @author Régis Pouiller
 * 
 */
public class ColumnAssert_HaveValuesEqualTo_Boolean_Test extends AbstractTest {

  /**
   * This method tests the {@code hasValuesEqualTo} assertion method.
   */
  @Test
  public void test_hasValuesEqualTo_assertion() {
    Table table = new Table(source, "test");

    assertThat(table)
        .column(1).as("var2").hasValuesEqualTo(true, false, false, false);

    Table table2 = new Table(source, "test2");

    assertThat(table2)
        .column(1).as("var2").hasValuesEqualTo(true, null);
  }

  /**
   * This method should fail because the type of the column is {@code ValueType.Number}.
   */
  @Test
  public void should_fail_hasValuesEqualTo_assertion_because_column_is_number() {
    try {
      Table table = new Table(source, "test2");
  
      assertThat(table)
          .column().as("var1 type").hasValuesEqualTo(true, null);
      
      fail("An exception must be raised");
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
   * This method should fail because the type of the column has less values.
   */
  @Test
  public void should_fail_hasValuesEqualTo_assertion_because_column_has_less_values() {
    try {
      Table table2 = new Table(source, "test2");
  
      assertThat(table2)
          .column(1).as("var2").hasValuesEqualTo(true, null, null);
      
      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[var2] \n" +
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
          .column(1).as("var2").hasValuesEqualTo(true, false);
      
      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[var2] \n" +
          "Expecting that the value at index 1:\n" +
          "  <null>\n" +
          "to be equal to: \n" +
          "  <false>");
    }
  }

  /**
   * This method should fail because the first value is {@code true}.
   */
  @Test
  public void should_fail_hasValuesEqualTo_assertion_because_value_is_different() {
    try {
      Table table = new Table(source, "test2");
  
      assertThat(table)
          .column(1).as("var2").hasValuesEqualTo(false, false);
      
      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[var2] \n" +
          "Expecting that the value at index 0:\n" +
          "  <true>\n" +
          "to be equal to: \n" +
          "  <false>");
    }
  }

}
