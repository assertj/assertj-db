/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2015-2024 the original author or authors.
 */
package org.assertj.db.api.assertions.impl;

import static org.junit.Assert.fail;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.db.api.TableAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.DateTimeValue;
import org.assertj.db.type.DateValue;
import org.assertj.db.type.TimeValue;
import org.assertj.db.type.Value;
import org.junit.Test;

/**
 * Tests on {@link AssertionsOnColumnEquality} class :
 * {@link AssertionsOnColumnEquality#hasValues(org.assertj.db.api.AbstractAssert, org.assertj.core.api.WritableAssertionInfo, java.util.List, org.assertj.db.type.DateTimeValue...)} method.
 *
 * @author Régis Pouiller
 */
public class AssertionsOnColumnEquality_HasValues_DateTimeValue_Test extends AbstractTest {

  /**
   * This method tests the {@code hasValues} assertion method.
   */
  @Test
  public void test_has_values() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    TableAssert tableAssert = new TableAssert(null);
    List<Value> list = new ArrayList<>(Arrays.asList(getValue(null, Timestamp.valueOf("2007-12-23 09:01:00")),
      getValue(null, Timestamp.valueOf("2002-07-25 03:30:05")),
      getValue(null, null)));
    TableAssert tableAssert2 = AssertionsOnColumnEquality.hasValues(tableAssert, info, list,
      DateTimeValue.of(DateValue.of(2007, 12, 23),
        TimeValue.of(9, 1)),
      DateTimeValue.of(DateValue.of(2002, 7, 25),
        TimeValue.of(3, 30, 5)),
      null);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    list = new ArrayList<>(Arrays.asList(getValue(null, Date.valueOf("2007-12-23")),
      getValue(null, Date.valueOf("2002-07-25")),
      getValue(null, null)));
    TableAssert tableAssert3 = AssertionsOnColumnEquality.hasValues(tableAssert, info, list,
      DateTimeValue.of(DateValue.of(2007, 12, 23)),
      DateTimeValue.of(DateValue.of(2002, 7, 25)), null);
    Assertions.assertThat(tableAssert3).isSameAs(tableAssert);
  }

  /**
   * This method should fail because the values are different.
   */
  @Test
  public void should_fail_because_values_are_different() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    TableAssert tableAssert = new TableAssert(null);
    try {
      List<Value> list = new ArrayList<>(Arrays.asList(getValue(null, Timestamp.valueOf("2007-12-23 09:01:00")),
        getValue(null, Timestamp.valueOf("2002-07-25 03:30:05"))));
      AssertionsOnColumnEquality.hasValues(tableAssert, info, list,
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)),
        DateTimeValue.of(DateValue.of(2002, 7, 25), TimeValue.of(3, 30, 6)));
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting that the value at index 1:%n"
        + "  <2002-07-25T03:30:05.000000000>%n"
        + "to be equal to: %n"
        + "  <2002-07-25T03:30:06.000000000>"));
    }
    try {
      List<Value> list = new ArrayList<>(Arrays.asList(getValue(null, Date.valueOf("2007-12-23")),
        getValue(null, Date.valueOf("2002-07-25"))));
      AssertionsOnColumnEquality.hasValues(tableAssert, info, list,
        DateTimeValue.of(DateValue.of(2007, 12, 23)),
        DateTimeValue.of(DateValue.of(2002, 7, 25), TimeValue.of(3, 30, 6)));
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting that the value at index 1:%n"
        + "  <2002-07-25T00:00:00.000000000>%n"
        + "to be equal to: %n"
        + "  <2002-07-25T03:30:06.000000000>"));
    }
  }

  /**
   * This method should fail because one of the values is not a date/time.
   */
  @Test
  public void should_fail_because_one_value_is_not_a_datetime() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    TableAssert tableAssert = new TableAssert(null);
    List<Value> list = new ArrayList<>(Arrays.asList(getValue(null, false),
      getValue(null, Timestamp.valueOf("2002-07-25 03:30:05"))));
    try {
      AssertionsOnColumnEquality.hasValues(tableAssert, info, list,
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)),
        DateTimeValue.of(DateValue.of(2002, 7, 25), TimeValue.of(3, 30, 6)));
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting that the value at index 0:%n"
        + "  <false>%n"
        + "to be of type%n"
        + "  <[DATE, DATE_TIME, NOT_IDENTIFIED]>%n"
        + "but was of type%n"
        + "  <BOOLEAN>"));
    }
  }

  /**
   * This method should fail because the number of values is different.
   */
  @Test
  public void should_fail_because_the_number_of_values_is_different() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    TableAssert tableAssert = new TableAssert(null);
    List<Value> list = new ArrayList<>(Arrays.asList(getValue(null, Timestamp.valueOf("2007-12-23 09:01:00")),
      getValue(null, Timestamp.valueOf("2002-07-25 03:30:05"))));
    try {
      AssertionsOnColumnEquality.hasValues(tableAssert, info, list,
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)),
        DateTimeValue.of(DateValue.of(2002, 7, 25), TimeValue.of(3, 30, 6)),
        DateTimeValue.of(DateValue.of(2015, 3, 30), TimeValue.of(22, 34)));
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
