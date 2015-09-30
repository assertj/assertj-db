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
 * Copyright 2012-2015 the original author or authors.
 */
package org.assertj.db.api.assertions.impl;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.db.api.TableAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.*;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on {@link AssertionsOnColumnContent} class :
 * {@link AssertionsOnColumnContent#containsValues(org.assertj.db.api.AbstractAssert, org.assertj.core.api.WritableAssertionInfo, java.util.List, DateTimeValue...)} method.
 *
 * @author Régis Pouiller
 *
 */
public class AssertionsOnColumnContent_ContainsValues_DateTimeValue extends AbstractTest {

  /**
   * This method tests the {@code containsValues} assertion method.
   */
  @Test
  public void test_contains_values() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    List<Value> list = new ArrayList<>(Arrays.asList(getValue(null, Timestamp.valueOf("2007-12-23 09:01:00")), getValue(
            null, Timestamp.valueOf("2002-07-25 03:30:05")), getValue(null, null)));
    TableAssert tableAssert2 = AssertionsOnColumnContent.containsValues(tableAssert, info, list,
                                                                        DateTimeValue.of(DateValue.of(2007, 12, 23),
                                                                                         TimeValue.of(9, 1)),
                                                                        DateTimeValue.of(DateValue.of(2002, 7, 25),
                                                                                         TimeValue.of(3, 30, 5)),
                                                                        null);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    tableAssert2 = AssertionsOnColumnContent.containsValues(tableAssert, info, list,
                                                            DateTimeValue.of(DateValue.of(2002, 7, 25),
                                                                             TimeValue.of(3, 30, 5)),
                                                            DateTimeValue.of(DateValue.of(2007, 12, 23),
                                                                             TimeValue.of(9, 1)),
                                                            null);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
  }

  /**
   * This method should fail because the values are different.
   */
  @Test
  public void should_fail_because_values_are_different() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    List<Value> list = new ArrayList<>(Arrays.asList(getValue(null, Timestamp.valueOf("2007-12-23 09:01:00")), getValue(
            null, Timestamp.valueOf("2002-07-25 03:30:05")), getValue(null, null)));
    try {
      AssertionsOnColumnContent.containsValues(tableAssert, info, list,
                                               DateTimeValue.of(DateValue.of(2007, 12, 23),
                                                                TimeValue.of(9, 1)),
                                               DateTimeValue.of(DateValue.of(2007, 12, 23),
                                                                TimeValue.of(9, 1)),
                                               null);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                                    + "Expecting:%n"
                                                                    + "  <[2007-12-23T09:01:00.000000000, 2002-07-25T03:30:05.000000000, null]>%n"
                                                                    + "to contain: %n"
                                                                    + "  <[2007-12-23T09:01:00.000000000, 2007-12-23T09:01:00.000000000, null]>%n"
                                                                    + " (parameter <2007-12-23T09:01:00.000000000> at index 1 is not found)"));
    }
  }

  /**
   * This method should fail because one of the values is not a date/time.
   */
  @Test
  public void should_fail_because_one_value_is_not_a_date_time() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    List<Value> list = new ArrayList<>(Arrays.asList(getValue(null, "other"), getValue(
            null, Timestamp.valueOf("2002-07-25 03:30:05"))));
    try {
      AssertionsOnColumnContent.containsValues(tableAssert, info, list,
                                               DateTimeValue.of(DateValue.of(2007, 12, 23),
                                                                TimeValue.of(9, 1)),
                                               DateTimeValue.of(DateValue.of(2007, 12, 23),
                                                                TimeValue.of(9, 1)));
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                                    + "Expecting that the value at index 0:%n"
                                                                    + "  <\"other\">%n"
                                                                    + "to be of type%n"
                                                                    + "  <[DATE, DATE_TIME, NOT_IDENTIFIED]>%n"
                                                                    + "but was of type%n"
                                                                    + "  <TEXT>"));
    }
  }

  /**
   * This method should fail because the number of values is different.
   */
  @Test
  public void should_fail_because_the_number_of_values_is_different() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    List<Value> list = new ArrayList<>(Arrays.asList(getValue(null, Timestamp.valueOf("2007-12-23 09:01:00")),
                                                     getValue(null, Timestamp.valueOf("2002-07-25 03:30:05"))));
    try {
      AssertionsOnColumnContent.containsValues(tableAssert, info, list,
                                               DateTimeValue.of(DateValue.of(2007, 12, 23),
                                                                TimeValue.of(9, 1)),
                                               DateTimeValue.of(DateValue.of(2007, 12, 23),
                                                                TimeValue.of(9, 1)),
                                               DateTimeValue.of(DateValue.of(2007, 12, 23),
                                                                TimeValue.of(9, 1)));
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                                    + "Expecting size (number of rows) to be equal to :%n"
                                                                    + "   <3>%n"
                                                                    + "but was:%n"
                                                                    + "   <2>"));
    }
  }
}
