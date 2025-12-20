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
 * Copyright 2015-2025 the original author or authors.
 */
package org.assertj.db.api.assertions.impl;

import static org.junit.Assert.fail;

import java.sql.Date;
import java.sql.Timestamp;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.db.api.TableAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.DateTimeValue;
import org.assertj.db.type.DateValue;
import org.assertj.db.type.TimeValue;
import org.junit.Test;

/**
 * Tests on {@link AssertionsOnValueChronology} class :
 * {@link AssertionsOnValueChronology#isAfterOrEqualTo(org.assertj.db.api.AbstractAssert, org.assertj.core.api.WritableAssertionInfo, org.assertj.db.type.Value, org.assertj.db.type.DateTimeValue)} method.
 *
 * @author Régis Pouiller
 */
public class AssertionsOnValueChronology_IsAfterOrEqualTo_DateTimeValue_Test extends AbstractTest {

  /**
   * This method tests the {@code isAfterOrEqualTo} assertion method.
   */
  @Test
  public void test_is_after_or_equal_to() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    TableAssert tableAssert = new TableAssert(null);
    TableAssert tableAssert2 = AssertionsOnValueChronology.isAfterOrEqualTo(tableAssert, info,
      getValue(null, Timestamp.valueOf(
        "2007-12-23 09:01:05")),
      DateTimeValue.of(DateValue.of(2007, 12, 23),
        TimeValue.of(9, 1, 5)));
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    tableAssert2 = AssertionsOnValueChronology.isAfterOrEqualTo(tableAssert, info,
      getValue(null, Timestamp.valueOf("2007-12-23 09:01:05")),
      DateTimeValue.of(DateValue.of(2007, 12, 23),
        TimeValue.of(9, 1, 0)));
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    tableAssert2 = AssertionsOnValueChronology.isAfterOrEqualTo(tableAssert, info,
      getValue(null, Date.valueOf("2007-12-23")),
      DateTimeValue.of(DateValue.of(2007, 12, 23)));
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    tableAssert2 = AssertionsOnValueChronology.isAfterOrEqualTo(tableAssert, info,
      getValue(null, Date.valueOf("2007-12-24")),
      DateTimeValue
        .of(DateValue.of(2007, 12, 23),
          TimeValue.of(9, 1, 5)));
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
  }

  /**
   * This method should fail because the value is before.
   */
  @Test
  public void should_fail_because_value_is_before() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    TableAssert tableAssert = new TableAssert(null);
    try {
      AssertionsOnValueChronology.isAfterOrEqualTo(tableAssert, info,
        getValue(null, Timestamp.valueOf("2007-12-23 09:01:05")),
        DateTimeValue.of(DateValue.of(2007, 12, 23),
          TimeValue.of(9, 1, 6)));
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting:%n"
        + "  <2007-12-23T09:01:05.000000000>%n"
        + "to be after or equal to %n"
        + "  <2007-12-23T09:01:06.000000000>"));
    }
    try {
      AssertionsOnValueChronology.isAfterOrEqualTo(tableAssert, info,
        getValue(null, Date.valueOf("2007-12-23")),
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 5)));
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting:%n"
        + "  <2007-12-23T00:00:00.000000000>%n"
        + "to be after or equal to %n"
        + "  <2007-12-23T09:01:05.000000000>"));
    }
  }

  /**
   * This method should fail because the value is not compatible.
   */
  @Test
  public void should_fail_because_value_is_not_compatible() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    TableAssert tableAssert = new TableAssert(null);
    try {
      AssertionsOnValueChronology.isAfterOrEqualTo(tableAssert, info,
        getValue(null, "test"),
        DateTimeValue.of(DateValue.of(2007, 12, 23),
          TimeValue.of(9, 1, 5)));
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting:%n"
        + "  <\"test\">%n"
        + "to be of type%n"
        + "  <[DATE, DATE_TIME]>%n"
        + "but was of type%n"
        + "  <TEXT>"));
    }
  }
}
