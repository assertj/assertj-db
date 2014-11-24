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
import org.assertj.db.type.DateTimeValue;
import org.assertj.db.type.DateValue;
import org.assertj.db.type.Table;
import org.assertj.db.type.TimeValue;
import org.junit.Test;

/**
 * Test on the {@code haveValuesEqualTo} assertion method on {@code Column} for the date/time values.
 * 
 * @author Régis Pouiller
 * 
 */
public class ColumnAssert_HaveValuesEqualTo_DateTimeValue_Test extends AbstractTest {

  /**
   * This method tests the {@code haveValuesEqualTo} assertion method.
   */
  @Test
  public void test_haveValuesEqualTo_assertion() {
    Table table = new Table(source, "test");

    assertThat(table)
        .column("var10").haveValuesEqualTo(DateTimeValue.of(DateValue.of(2014, 5, 24), TimeValue.of(9, 46, 30)), 
            DateTimeValue.of(DateValue.of(2014, 5, 30), TimeValue.of(12, 29, 49)),
            DateTimeValue.of(DateValue.of(2014, 5, 30), TimeValue.of(0, 0)),
            DateTimeValue.of(DateValue.of(2014, 5, 30), TimeValue.of(0, 0)))
            .haveValuesEqualTo("2014-05-24T09:46:30", "2014-05-30T12:29:49", "2014-05-30T00:00", "2014-05-30");

    Table table2 = new Table(source, "test2");

    assertThat(table2)
        .column("var10").haveValuesEqualTo(DateTimeValue.of(DateValue.of(2014, 5, 24), TimeValue.of(9, 46, 30)), null);
  }

  /**
   * This method should fail because the type of the column is {@code ValueType.Boolean}.
   */
  @Test
  public void should_fail_haveValuesEqualTo_assertion_because_column_is_boolean() {
    try {
      Table table = new Table(source, "test2");
  
      assertThat(table)
          .column(1).as("var2 type").haveValuesEqualTo(DateTimeValue.of(DateValue.of(2014, 5, 24), TimeValue.of(9, 46, 30)), 
              DateTimeValue.of(DateValue.of(2014, 5, 30), TimeValue.of(12, 29, 49)),
              DateTimeValue.of(DateValue.of(2014, 5, 30), TimeValue.of(0, 0)),
              DateTimeValue.of(DateValue.of(2014, 5, 30), TimeValue.of(0, 0)));
      
      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[var2 type] \n" +
          "Expecting that the value at index 0:\n" +
          "  <true>\n" +
          "to be of type\n" +
          "  <[DATE_TIME, NOT_IDENTIFIED]>\n" +
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
          .column("var10").haveValuesEqualTo(DateTimeValue.of(DateValue.of(2014, 5, 24), TimeValue.of(9, 46, 30)), 
              DateTimeValue.of(DateValue.of(2014, 5, 30), TimeValue.of(12, 29, 49)),
              DateTimeValue.of(DateValue.of(2014, 5, 30), TimeValue.of(0, 0)));
      
      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Column at index 9 of test2 table] \n" +
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
          .column("var10").haveValuesEqualTo(DateTimeValue.of(DateValue.of(2014, 5, 24), TimeValue.of(9, 46, 30)), 
              DateTimeValue.of(DateValue.of(2014, 5, 24), TimeValue.of(9, 46, 30)));
      
      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Column at index 9 of test2 table] \n" +
          "Expecting that the value at index 1:\n" +
          "  <null>\n" +
          "to be equal to: \n" +
          "  <2014-05-24T09:46:30.000000000>");
    }
  }

  /**
   * This method should fail because the first value is 24/05/2014 09:46:30.
   */
  @Test
  public void should_fail_haveValuesEqualTo_assertion_because_value_is_different() {
    try {
      Table table = new Table(source, "test2");
  
      assertThat(table)
          .column("var10").haveValuesEqualTo(DateTimeValue.of(DateValue.of(2014, 5, 24), TimeValue.of(9, 46, 31)), 
              DateTimeValue.of(DateValue.of(2014, 5, 24), TimeValue.of(9, 46, 30)));
      
      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Column at index 9 of test2 table] \n" +
          "Expecting that the value at index 0:\n" +
          "  <2014-05-24T09:46:30.000000000>\n" +
          "to be equal to: \n" +
          "  <2014-05-24T09:46:31.000000000>");
    }
  }

}
