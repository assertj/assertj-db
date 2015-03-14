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

import java.text.ParseException;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.DateValue;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Tests on the methods which verifies if a value is before a date value.
 * 
 * @author Régis Pouiller
 * 
 */
public class ValueAssert_IsBefore_DateValue_Test extends AbstractTest {

  /**
   * This method tests that the value is before a date.
   * @throws ParseException 
   */
  @Test
  public void test_if_value_is_before_date() throws ParseException {
    Table table = new Table(source, "test");
    assertThat(table)
        .column("var9")
            .value()
                .isBefore(DateValue.of(2014, 5, 25))
            .returnToColumn()
            .value()
                .isBefore(DateValue.parse("2014-05-31"))
            .returnToColumn()
        .returnToTable()
        .column("var10")
            .value()
                .isBefore(DateValue.of(2014, 5, 25))
            .returnToColumn()
            .value()
                .isBefore(DateValue.parse("2014-05-31"));
  }

  /**
   * This method should fail because the value is not before the date value.
   */
  @Test
  public void should_fail_because_date_value_is_not_before() {
    try {
      Table table = new Table(source, "test");
      assertThat(table).column("var9")
          .value().isBefore(DateValue.of(2014, 5, 24));
      
      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Value at index 0 of Column at index 8 of test table] \n" +
          "Expecting:\n" +
          "  <2014-05-24>\n" +
          "to be before \n" +
          "  <2014-05-24>");
    }
  }

  /**
   * This method should fail because the value is not a date.
   */
  @Test
  public void should_fail_because_value_is_not_a_date() {
    try {
      Table table = new Table(source, "test");
      assertThat(table).column("var1")
          .value().as("var1").isBefore(DateValue.of(2014, 5, 25));
      
      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[var1] \n" +
          "Expecting:\n" +
          "  <1>\n" +
          "to be of type\n" +
          "  <[DATE, DATE_TIME]>\n" +
          "but was of type\n" +
          "  <NUMBER>");
    }
  }

  /**
   * This method tests that the date/time value is before to a date.
   * @throws ParseException 
   */
  @Test
  public void test_if_datetime_value_is_before_date() throws ParseException {
    Table table = new Table(source, "test");
    assertThat(table).column("var10")
        .value(2).isBefore(DateValue.parse("2014-05-31"));
  }

  /**
   * This method should fail because the date/time value is not before the date value.
   * @throws ParseException 
   */
  @Test
  public void should_fail_because_datetime_value_is_not_before() throws ParseException {
    try {
      Table table = new Table(source, "test");
      assertThat(table).column("var10")
          .value(2).isBefore(DateValue.parse("2014-05-30"));
      
      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Value at index 2 of Column at index 9 of test table] \n" +
          "Expecting:\n" +
          "  <2014-05-30T00:00:00.000000000>\n" +
          "to be before \n" +
          "  <2014-05-30T00:00:00.000000000>");
    }
  }

}
