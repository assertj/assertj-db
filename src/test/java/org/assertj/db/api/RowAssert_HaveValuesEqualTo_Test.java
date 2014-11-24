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
import static org.assertj.db.api.Assertions.bytesContentFromClassPathOf;
import static org.junit.Assert.fail;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.DateTimeValue;
import org.assertj.db.type.DateValue;
import org.assertj.db.type.Table;
import org.assertj.db.type.TimeValue;
import org.junit.Test;

/**
 * Test on the {@code haveValuesEqualTo) assertion methods on {@code Row}.
 * 
 * @author Régis Pouiller
 * 
 */
public class RowAssert_HaveValuesEqualTo_Test extends AbstractTest {

  private byte[] bytesDev = bytesContentFromClassPathOf("logo-dev.jpg");
  private byte[] bytesH2 = bytesContentFromClassPathOf("h2-logo-2.png");

  /**
   * This method tests the {@code haveValuesEqualTo} assertion method.
   */
  @Test
  public void test_haveValuesEqualTo_assertion() {
    Table table = new Table(source, "test");

    assertThat(table)
        .row().haveValuesEqualTo(1, true, 2, 3, 4, 5.6, 7.8, TimeValue.of(9, 46, 30),
            DateValue.of(2014, 5, 24), DateTimeValue.of(DateValue.of(2014, 5, 24), TimeValue.of(9, 46, 30)),
            bytesH2, "text", 5, 7)
            .haveValuesEqualTo("1", true, "2", "3", "4", "5.6", "7.8", "09:46:30",
            "2014-05-24", "2014-05-24T09:46:30", bytesH2, "text", "5", "7")
        .row().haveValuesEqualTo("10", false, "20", "30", "40", "50.6", "70.8", TimeValue.of(12, 29, 49),
            DateValue.of(2014, 5, 30), DateTimeValue.of(DateValue.of(2014, 5, 30), TimeValue.of(12, 29, 49)),
            bytesDev, "another text", "50", "70")
            .haveValuesEqualTo("10", false, "20", "30", "40", "50.6", "70.8", TimeValue.of(12, 29, 49),
            "2014-05-30T00:00", DateTimeValue.of(DateValue.of(2014, 5, 30), TimeValue.of(12, 29, 49)),
            bytesDev, "another text", "50", "70")
        .row().haveValuesEqualTo(100, false, 25, 300, 400, 500.6, 700.8, TimeValue.of(12, 29, 49),
            DateValue.of(2014, 5, 30), DateValue.of(2014, 5, 30),
            bytesDev, "another text again", 500, 700)
        .row().haveValuesEqualTo(1000, false, 0, 0, 0, 0, 0, TimeValue.of(12, 29, 49),
            DateValue.of(2014, 5, 30), DateValue.of(2014, 5, 30),
            bytesDev, "another text again", 500, 700)
            .haveValuesEqualTo(1000, false, 0, 0, 0, 0, 0, TimeValue.of(12, 29, 49),
            "2014-05-30", "2014-05-30",
            bytesDev, "another text again", 500, 700)
            .haveValuesEqualTo(1000, false, 0, 0, 0, 0, 0, TimeValue.of(12, 29, 49),
            "2014-05-30T00:00", "2014-05-30T00:00",
            bytesDev, "another text again", 500, 700)
            .haveValuesEqualTo(1000, false, 0, 0, 0, 0, 0, TimeValue.of(12, 29, 49),
            "2014-05-30T00:00:00", "2014-05-30T00:00:00",
            bytesDev, "another text again", 500, 700)
            .haveValuesEqualTo(1000, false, 0, 0, 0, 0, 0, TimeValue.of(12, 29, 49),
            "2014-05-30T00:00:00.000000000", "2014-05-30T00:00:00.000000000",
            bytesDev, "another text again", 500, 700);

    Table table2 = new Table(source, "test2");

    assertThat(table2)
        .row().haveValuesEqualTo(1, true, 2, 3, 4, 5.6, 7.8, TimeValue.of(9, 46, 30),
            DateValue.of(2014, 5, 24), DateTimeValue.of(DateValue.of(2014, 5, 24), TimeValue.of(9, 46, 30)),
            bytesH2, "text", 5, 7, null)
        .row().haveValuesEqualTo(null, null, null, null, null, null, null, null, 
            null, null, 
            null, null, null, null, null);
  }

  /**
   * This method should fail because the type of the column is {@code ValueType.NOT_IDENTIFIED}.
   */
  @Test
  public void should_fail_isOfType_assertion_because_column_is_not_identified() {
    try {
      Table table2 = new Table(source, "test2");
  
      assertThat(table2)
          .row().haveValuesEqualTo(1, true, 2, 3, 4, 5.6, 7.8, TimeValue.of(9, 46, 30),
              DateValue.of(2014, 5, 24), DateTimeValue.of(DateValue.of(2014, 5, 24), TimeValue.of(9, 46, 30)),
              bytesH2, "text", 5, 7, null)
          .row().haveValuesEqualTo(null, "1", null, null, null, null, null, null, 
              null, null, 
              null, null, null, null, null);
      
      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Row at index 1 of test2 table] \n" +
          "Expecting that the value at index 1:\n" +
          "  <null>\n" +
          "to be of type\n" +
          "  <[TEXT, NUMBER, DATE, TIME, DATE_TIME]>\n" +
          "but was of type\n" +
          "  <NOT_IDENTIFIED>");
    }
  }

  /**
   * This method should fail because the second row have more values.
   */
  @Test
  public void should_fail_isOfType_assertion_because_column_have_less_values() {
    try {
      Table table2 = new Table(source, "test2");
  
      assertThat(table2)
          .row().haveValuesEqualTo(1, true, 2, 3, 4, 5.6, 7.8, TimeValue.of(9, 46, 30),
              DateValue.of(2014, 5, 24), DateTimeValue.of(DateValue.of(2014, 5, 24), TimeValue.of(9, 46, 30)),
              bytesH2, "text", 5, 7, null)
          .row().haveValuesEqualTo(null, null, null, null, null, null, null, 
              null, null, 
              null, null, null, null, null);
      
      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Row at index 1 of test2 table] \n" +
          "Expecting size (number of columns) to be equal to :\n" +
          "   <14>\n" +
          "but was:\n" +
          "   <15>");
    }
  }

  /**
   * This method should fail because the second value of the first row is {@code true}.
   */
  @Test
  public void should_fail_isOfType_assertion_because_value_is_different() {
    try {
      Table table2 = new Table(source, "test2");
  
      assertThat(table2)
          .row().haveValuesEqualTo(1, false, 2, 3, 4, 5.6, 7.8, TimeValue.of(9, 46, 30),
              DateValue.of(2014, 5, 24), DateTimeValue.of(DateValue.of(2014, 5, 24), TimeValue.of(9, 46, 30)),
              bytesH2, "text", 5, 7, null)
          .row().haveValuesEqualTo(null, null, null, null, null, null, null, null, 
              null, null, 
              null, null, null, null, null);
      
      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Row at index 0 of test2 table] \n" +
          "Expecting that the value at index 1:\n" +
          "  <true>\n" +
          "to be equal to: \n" +
          "  <false>");
    }
  }

  /**
   * This method should fail because the array of bytes of the first row is different.
   */
  @Test
  public void should_fail_isOfType_assertion_because_value_is_different_on_array_of_bytes() {
    try {
      Table table2 = new Table(source, "test2");
  
      assertThat(table2)
          .row().haveValuesEqualTo(1, true, 2, 3, 4, 5.6, 7.8, TimeValue.of(9, 46, 30),
              DateValue.of(2014, 5, 24), DateTimeValue.of(DateValue.of(2014, 5, 24), TimeValue.of(9, 46, 30)),
              bytesDev, "text", 5, 7, null);
      
      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Row at index 0 of test2 table] \n" +
          "Expecting that the value at index 10 to be equal to the expected value but was not equal");
    }
  }
}
