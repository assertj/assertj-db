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

import java.text.ParseException;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.DateValue;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Tests on the methods which verifies if a value is after a date value.
 * 
 * @author Régis Pouiller
 * 
 */
public class ValueAssert_IsAfterOrEqualTo_DateValue_Test extends AbstractTest {

  /**
   * This method tests that the value is after or equal to a date.
   * @throws ParseException 
   */
  @Test
  public void test_if_value_is_after_or_equal_to_date() throws ParseException {
    Table table = new Table(source, "test");
    assertThat(table)
        .column("var9")
            .value()
                .isAfterOrEqualTo(DateValue.of(2014, 5, 23))
            .returnToColumn()
            .value()
                .isAfterOrEqualTo(DateValue.parse("2014-05-29"))
            .returnToColumn()
        .returnToTable()
        .column("var10")
            .value()
                .isAfterOrEqualTo(DateValue.of(2014, 5, 23))
            .returnToColumn()
            .value()
                .isAfterOrEqualTo(DateValue.parse("2014-05-29"))
            .returnToColumn()
        .returnToTable()
        .column("var9")
            .value().isAfterOrEqualTo(DateValue.of(2014, 5, 24))
            .value().isAfterOrEqualTo(DateValue.parse("2014-05-30"));
  }

  /**
   * This method should fail because the value is not after or equal to the date value.
   */
  @Test
  public void should_fail_because_value_is_not_after_or_equal_to() {
    try {
      Table table = new Table(source, "test");
      assertThat(table).column("var9")
          .value().isAfterOrEqualTo(DateValue.of(2014, 5, 25));
      
      fail("Une Erreur doit être levée");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Value at index 0 of Column at index 8 of test table] \n" +
          "Expecting:\n" +
          "  <2014-05-24>\n" +
          "to be after or equal to \n" +
          "  <2014-05-25>");
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
          .value().as("var1").isAfterOrEqualTo(DateValue.of(2014, 5, 23));
      
      fail("Une Erreur doit être levée");
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
   * This method tests that the date/time value is after or equal to a date.
   * @throws ParseException 
   */
  @Test
  public void test_if_datetime_value_is_after_or_equal_to_date() throws ParseException {
    Table table = new Table(source, "test");
    assertThat(table).column("var10")
        .value(2).isAfterOrEqualTo(DateValue.parse("2014-05-29"));
  }

  /**
   * This method should fail because the date/time value is not after or equal to the date value.
   * @throws ParseException 
   */
  @Test
  public void should_fail_because_datetime_value_is_not_after_or_equal_to() throws ParseException {
    try {
      Table table = new Table(source, "test");
      assertThat(table).column("var10")
          .value(2).isAfterOrEqualTo(DateValue.parse("2014-05-31"));
      
      fail("Une Erreur doit être levée");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Value at index 2 of Column at index 9 of test table] \n" +
          "Expecting:\n" +
          "  <2014-05-31T00:00:00.000000000>\n" +
          "to be after or equal to \n" +
          "  <2014-05-31T00:00:00.000000000>");
    }
  }

}
