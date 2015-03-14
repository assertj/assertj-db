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
import org.assertj.db.type.Changes;
import org.assertj.db.type.DateTimeValue;
import org.assertj.db.type.DateValue;
import org.assertj.db.type.TimeValue;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.bytesContentFromClassPathOf;
import static org.junit.Assert.fail;

/**
 * Test on the {@code hasValuesEqualTo) assertion methods on {@code Row} of {@code Change}.
 *
 * @author Régis Pouiller
 *
 */
public class ChangeRowAssert_HasValuesEqualTo_Test extends AbstractTest {

  private byte[] bytesDev = bytesContentFromClassPathOf("logo-dev.jpg");
  private byte[] bytesH2 = bytesContentFromClassPathOf("h2-logo-2.png");

  /**
   * This method tests the {@code hasValuesEqualTo} assertion method.
   */
  @Test
  public void test_hasValuesEqualTo_assertion() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForOtherTests();
    changes.setEndPointNow();

    assertThat(changes).change()
            .rowAtEndPoint().hasValuesEqualTo(1, true, 2, 3, 4, 5.6, 7.8, TimeValue.of(9, 46, 30),
                                              DateValue.of(2014, 5, 24),
                                              DateTimeValue.of(DateValue.of(2014, 5, 24), TimeValue.of(9, 46, 30)),
                                              bytesH2, "text", 5, 7)
            .hasValuesEqualTo("1", true, "2", "3", "4", "5.6", "7.8", "09:46:30", "2014-05-24", "2014-05-24T09:46:30",
                              bytesH2, "text", "5", "7")
            .change().rowAtEndPoint().hasValuesEqualTo("10", false, "20", "30", "40", "50.6", "70.8",
                                                       TimeValue.of(12, 29, 49), DateValue.of(2014, 5, 30),
                                                       DateTimeValue
                                                               .of(DateValue.of(2014, 5, 30), TimeValue.of(12, 29, 49)),
                                                       bytesDev, "another text", "50", "70")
            .hasValuesEqualTo("10", false, "20", "30", "40", "50.6", "70.8", TimeValue.of(12, 29, 49),
                              "2014-05-30T00:00", DateTimeValue.of(DateValue.of(2014, 5, 30), TimeValue.of(12, 29, 49)),
                              bytesDev, "another text", "50", "70")
            .change().rowAtEndPoint().hasValuesEqualTo(100, false, 25, 300, 400, 500.6, 700.8, TimeValue.of(12, 29, 49),
                                                       DateValue.of(2014, 5, 30), DateValue.of(2014, 5, 30), bytesDev,
                                                       "another text again", 500, 700)
            .change().rowAtEndPoint().hasValuesEqualTo(1000, false, 0, 0, 0, 0, 0, TimeValue.of(12, 29, 49),
                                    DateValue.of(2014, 5, 30), DateValue.of(2014, 5, 30),
                                    bytesDev, "another text again", 500, 700)
            .hasValuesEqualTo(1000, false, 0, 0, 0, 0, 0, TimeValue.of(12, 29, 49),
                              "2014-05-30", "2014-05-30",
                              bytesDev, "another text again", 500, 700)
            .hasValuesEqualTo(1000, false, 0, 0, 0, 0, 0, TimeValue.of(12, 29, 49),
                              "2014-05-30T00:00", "2014-05-30T00:00",
                              bytesDev, "another text again", 500, 700)
            .hasValuesEqualTo(1000, false, 0, 0, 0, 0, 0, TimeValue.of(12, 29, 49),
                              "2014-05-30T00:00:00", "2014-05-30T00:00:00",
                              bytesDev, "another text again", 500, 700)
            .hasValuesEqualTo(1000, false, 0, 0, 0, 0, 0, TimeValue.of(12, 29, 49),
                              "2014-05-30T00:00:00.000000000", "2014-05-30T00:00:00.000000000",
                              bytesDev, "another text again", 500, 700);

    Changes changes2 = new Changes(source).setStartPointNow();
    updateChangesForOtherTests2();
    changes2.setEndPointNow();

    assertThat(changes2)
            .change().rowAtEndPoint().hasValuesEqualTo(1, true, 2, 3, 4, 5.6, 7.8, TimeValue.of(9, 46, 30),
                                                       DateValue.of(2014, 5, 24), DateTimeValue
            .of(DateValue.of(2014, 5, 24), TimeValue.of(9, 46, 30)), bytesH2, "text", 5, 7, null)
            .change().rowAtEndPoint().hasValuesEqualTo(null, null, null, null, null, null, null, null,
                                    null, null,
                                    null, null, null, null, null);
  }

  /**
   * This method should fail because the type of the column is {@code ValueType.NOT_IDENTIFIED}.
   */
  @Test
  public void should_fail_isOfType_assertion_because_column_is_not_identified() {
    try {
      Changes changes2 = new Changes(source).setStartPointNow();
      updateChangesForOtherTests2();
      changes2.setEndPointNow();

      assertThat(changes2)
              .change().rowAtEndPoint().hasValuesEqualTo(1, true, 2, 3, 4, 5.6, 7.8, TimeValue.of(9, 46, 30),
                                                         DateValue.of(2014, 5, 24), DateTimeValue
              .of(DateValue.of(2014, 5, 24), TimeValue.of(9, 46, 30)), bytesH2, "text", 5, 7, null)
              .change().rowAtEndPoint().hasValuesEqualTo(null, "1", null, null, null, null, null, null,
                                      null, null,
                                      null, null, null, null, null);

      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Row at end point of Change at index 1 of Changes on tables of 'sa/jdbc:h2:mem:test' source] \n" +
                                                                                    "Expecting that the value at index 1:\n" +
                                                                                    "  <null>\n" +
                                                                                    "to be of type\n" +
                                                                                    "  <[TEXT, NUMBER, DATE, TIME, DATE_TIME]>\n" +
                                                                                    "but was of type\n" +
                                                                                    "  <NOT_IDENTIFIED>");
    }
  }

  /**
   * This method should fail because the second row has more values.
   */
  @Test
  public void should_fail_isOfType_assertion_because_column_has_less_values() {
    try {
      Changes changes2 = new Changes(source).setStartPointNow();
      updateChangesForOtherTests2();
      changes2.setEndPointNow();

      assertThat(changes2)
              .change().rowAtEndPoint().hasValuesEqualTo(1, true, 2, 3, 4, 5.6, 7.8, TimeValue.of(9, 46, 30),
                                                         DateValue.of(2014, 5, 24), DateTimeValue
              .of(DateValue.of(2014, 5, 24), TimeValue.of(9, 46, 30)), bytesH2, "text", 5, 7, null)
              .change().rowAtEndPoint().hasValuesEqualTo(null, null, null, null, null, null, null,
                                      null, null,
                                      null, null, null, null, null);

      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Row at end point of Change at index 1 of Changes on tables of 'sa/jdbc:h2:mem:test' source] \n" +
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
      Changes changes2 = new Changes(source).setStartPointNow();
      updateChangesForOtherTests2();
      changes2.setEndPointNow();

      assertThat(changes2)
              .change().rowAtEndPoint().hasValuesEqualTo(1, false, 2, 3, 4, 5.6, 7.8, TimeValue.of(9, 46, 30),
                                                         DateValue.of(2014, 5, 24), DateTimeValue
              .of(DateValue.of(2014, 5, 24), TimeValue.of(9, 46, 30)), bytesH2, "text", 5, 7, null)
              .change().rowAtEndPoint().hasValuesEqualTo(null, null, null, null, null, null, null, null,
                                      null, null,
                                      null, null, null, null, null);

      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Row at end point of Change at index 0 of Changes on tables of 'sa/jdbc:h2:mem:test' source] \n" +
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
      Changes changes2 = new Changes(source).setStartPointNow();
      updateChangesForOtherTests2();
      changes2.setEndPointNow();

      assertThat(changes2)
              .change().rowAtEndPoint().hasValuesEqualTo(1, true, 2, 3, 4, 5.6, 7.8, TimeValue.of(9, 46, 30),
                                      DateValue.of(2014, 5, 24), DateTimeValue.of(DateValue.of(2014, 5, 24), TimeValue.of(9, 46, 30)),
                                      bytesDev, "text", 5, 7, null);

      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Row at end point of Change at index 0 of Changes on tables of 'sa/jdbc:h2:mem:test' source] \n" +
                                                                                    "Expecting that the value at index 10 to be equal to the expected value but was not equal");
    }
  }
}
