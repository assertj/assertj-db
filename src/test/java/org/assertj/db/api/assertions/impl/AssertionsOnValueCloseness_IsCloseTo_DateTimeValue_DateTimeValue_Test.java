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
 * Tests on {@link  AssertionsOnValueCloseness} class :
 * {@link  AssertionsOnValueCloseness#isCloseTo(org.assertj.db.api.AbstractAssert, org.assertj.core.api.WritableAssertionInfo, org.assertj.db.type.Value, org.assertj.db.type.DateTimeValue, org.assertj.db.type.DateTimeValue)} method.
 *
 * @author Régis Pouiller
 *
 */
public class AssertionsOnValueCloseness_IsCloseTo_DateTimeValue_DateTimeValue_Test extends AbstractTest {

  /**
   * This method tests the {@code isCloseTo} assertion method.
   */
  @Test
  public void test_is_close_to() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    TableAssert tableAssert2 = AssertionsOnValueCloseness.isCloseTo(tableAssert, info, getValue(
            null, Date.valueOf("2007-12-23")), DateTimeValue.of(DateValue.of(2007, 12, 23)), DateTimeValue.of(DateValue.of(0, 0, 0)));
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    tableAssert2 = AssertionsOnValueCloseness.isCloseTo(tableAssert, info, getValue(
            null, Timestamp.valueOf("2007-12-23 09:01:00")), DateTimeValue.of(
            DateValue.of(2007, 12, 23), TimeValue.of(9, 1)), DateTimeValue.of(DateValue.of(0, 0, 0)));
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    tableAssert2 = AssertionsOnValueCloseness.isCloseTo(tableAssert, info, getValue(null, null), (DateTimeValue) null, DateTimeValue.of(DateValue.of(0, 0, 0)));
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
  }

  /**
   * This method should fail because the value is not close to.
   */
  @Test
  public void should_fail_because_value_is_not_close_to() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    try {
      AssertionsOnValueCloseness.isCloseTo(tableAssert, info, getValue(null, Date.valueOf("2007-12-24")),
                                           DateTimeValue.of(DateValue.of(2007, 12, 23)), DateTimeValue.of(DateValue.of(0, 0, 0)));
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                                    + "Expecting:%n"
                                                                    + "  <2007-12-24T00:00:00.000000000>%n"
                                                                    + "to be close to: %n"
                                                                    + "  <2007-12-23T00:00:00.000000000> %n"
                                                                    + " with tolerance <   0-00-00T00:00:00.000000000>"));
    }
    try {
      AssertionsOnValueCloseness.isCloseTo(tableAssert, info, getValue(null, Timestamp.valueOf("2007-12-23 09:01:05")),
                                           DateTimeValue.of(
                                                   DateValue.of(2007, 12, 23), TimeValue.of(9, 1)), DateTimeValue.of(DateValue.of(0, 0, 0)));
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                                    + "Expecting:%n"
                                                                    + "  <2007-12-23T09:01:05.000000000>%n"
                                                                    + "to be close to: %n"
                                                                    + "  <2007-12-23T09:01:00.000000000> %n"
                                                                    + " with tolerance <   0-00-00T00:00:00.000000000>"));
    }
    try {
      AssertionsOnValueCloseness.isCloseTo(tableAssert, info, getValue(null, Date.valueOf("2007-12-24")),
                                           (DateTimeValue) null, DateTimeValue.of(DateValue.of(0, 1, 0)));
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                                    + "Expecting:%n"
                                                                    + "  <2007-12-24T00:00:00.000000000>%n"
                                                                    + "to be close to: %n"
                                                                    + "  <null> %n"
                                                                    + " with tolerance <   0-01-00T00:00:00.000000000>"));
    }
    try {
      AssertionsOnValueCloseness.isCloseTo(tableAssert, info, getValue(null, Timestamp.valueOf("2007-12-23 09:01:05")),
                                           (DateTimeValue) null, DateTimeValue.of(DateValue.of(1, 0, 0)));
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                                    + "Expecting:%n"
                                                                    + "  <2007-12-23T09:01:05.000000000>%n"
                                                                    + "to be close to: %n"
                                                                    + "  <null> %n"
                                                                    + " with tolerance <   1-00-00T00:00:00.000000000>"));
    }
  }

  /**
   * This method should fail because the value is not a date/time.
   */
  @Test
  public void should_fail_because_value_is_not_a_date_time() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    try {
      AssertionsOnValueCloseness.isCloseTo(tableAssert, info, getValue(null, 8),
                                           DateTimeValue.of(DateValue.of(2007, 12, 23)), DateTimeValue.of(DateValue.of(0, 0, 1)));
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                                    + "Expecting:%n"
                                                                    + "  <8>%n"
                                                                    + "to be of type%n"
                                                                    + "  <[DATE, DATE_TIME]>%n"
                                                                    + "but was of type%n"
                                                                    + "  <NUMBER>"));
    }
  }
}
