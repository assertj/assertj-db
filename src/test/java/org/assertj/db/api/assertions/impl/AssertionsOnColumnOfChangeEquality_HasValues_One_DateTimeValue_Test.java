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
 * Copyright 2015-2021 the original author or authors.
 */
package org.assertj.db.api.assertions.impl;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.db.api.TableAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.DateTimeValue;
import org.assertj.db.type.DateValue;
import org.assertj.db.type.Table;
import org.assertj.db.type.TimeValue;
import org.junit.Test;

import java.sql.Date;
import java.sql.Timestamp;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on {@link org.assertj.db.api.assertions.impl.AssertionsOnColumnOfChangeEquality} class :
 * {@link org.assertj.db.api.assertions.impl.AssertionsOnColumnOfChangeEquality#hasValues(org.assertj.db.api.AbstractAssert, org.assertj.core.api.WritableAssertionInfo, org.assertj.db.type.Value, org.assertj.db.type.Value, DateTimeValue)} method.
 *
 * @author Régis Pouiller
 *
 */
public class AssertionsOnColumnOfChangeEquality_HasValues_One_DateTimeValue_Test extends AbstractTest {

  /**
   * This method tests the {@code hasValues} assertion method.
   */
  @Test
  public void test_has_values() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    TableAssert tableAssert2 = AssertionsOnColumnOfChangeEquality.hasValues(tableAssert, info,
                                                                            getValue(null, Timestamp.valueOf(
                                                                                    "2007-12-23 09:01:00")),
                                                                            getValue(null, Timestamp.valueOf(
                                                                                    "2007-12-23 09:01:00")),
                                                                            DateTimeValue.of(DateValue.of(2007, 12, 23),
                                                                                             TimeValue.of(9, 1)));
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    TableAssert tableAssert3 = AssertionsOnColumnOfChangeEquality.hasValues(tableAssert, info,
                                                                            getValue(null, Date.valueOf("2007-12-23")),
                                                                            getValue(null, Date.valueOf("2007-12-23")),
                                                                            DateTimeValue
                                                                                    .of(DateValue.of(2007, 12, 23)));
    Assertions.assertThat(tableAssert3).isSameAs(tableAssert);
  }

  /**
   * This method should fail because the value at start point is different.
   */
  @Test
  public void should_fail_because_value_at_start_point_is_different() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    try {
      AssertionsOnColumnOfChangeEquality.hasValues(tableAssert, info,
                                                   getValue(null, Timestamp.valueOf("2007-12-23 09:01:05")),
                                                   getValue(null, Timestamp.valueOf("2007-12-23 09:01:00")),
                                                   DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)));
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                      + "Expecting that start point:%n"
                                                      + "  <2007-12-23T09:01:05.000000000>%n"
                                                      + "to be equal to: %n"
                                                      + "  <2007-12-23T09:01:00.000000000>"));
    }
    try {
      AssertionsOnColumnOfChangeEquality.hasValues(tableAssert, info,
                                                   getValue(null, Date.valueOf("2007-12-24")),
                                                   getValue(null, Date.valueOf("2007-12-23")),
                                                   DateTimeValue.of(DateValue.of(2007, 12, 23)));
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                      + "Expecting that start point:%n"
                                                      + "  <2007-12-24T00:00:00.000000000>%n"
                                                      + "to be equal to: %n"
                                                      + "  <2007-12-23T00:00:00.000000000>"));
    }
  }

  /**
   * This method should fail because the value at end point is different.
   */
  @Test
  public void should_fail_because_value_at_end_point_is_different() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    try {
      AssertionsOnColumnOfChangeEquality.hasValues(tableAssert, info,
                                                   getValue(null, Timestamp.valueOf("2007-12-23 09:01:00")),
                                                   getValue(null, Timestamp.valueOf("2007-12-23 09:01:05")),
                                                   DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)));
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                      + "Expecting that end point:%n"
                                                      + "  <2007-12-23T09:01:05.000000000>%n"
                                                      + "to be equal to: %n"
                                                      + "  <2007-12-23T09:01:00.000000000>"));
    }
    try {
      AssertionsOnColumnOfChangeEquality.hasValues(tableAssert, info,
                                                   getValue(null, Date.valueOf("2007-12-23")),
                                                   getValue(null, Date.valueOf("2007-12-24")),
                                                   DateTimeValue.of(DateValue.of(2007, 12, 23)));
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                      + "Expecting that end point:%n"
                                                      + "  <2007-12-24T00:00:00.000000000>%n"
                                                      + "to be equal to: %n"
                                                      + "  <2007-12-23T00:00:00.000000000>"));
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
    try {
      AssertionsOnColumnOfChangeEquality.hasValues(tableAssert, info,
                                                   getValue(null, "other"),
                                                   getValue(null, Timestamp.valueOf("2007-12-23 09:01:00")),
                                                   DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)));
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                      + "Expecting that the value at start point:%n"
                                                      + "  <\"other\">%n"
                                                      + "to be of type%n"
                                                      + "  <[DATE, DATE_TIME, NOT_IDENTIFIED]>%n"
                                                      + "but was of type%n"
                                                      + "  <TEXT>"));
    }
  }
}
