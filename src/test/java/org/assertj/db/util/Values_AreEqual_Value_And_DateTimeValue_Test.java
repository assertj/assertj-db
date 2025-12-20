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
package org.assertj.db.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Date;
import java.sql.Timestamp;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.DateTimeValue;
import org.assertj.db.type.DateValue;
import org.assertj.db.type.TimeValue;
import org.junit.Test;

/**
 * Tests on {@code areEqual} method for {@code DateTimeValue}s.
 *
 * @author Régis Pouiller
 */
public class Values_AreEqual_Value_And_DateTimeValue_Test extends AbstractTest {

  /**
   * This method tests the {@code areEqual} method for {@code DateTimeValue}s.
   */
  @Test
  public void test_are_equal_for_timestamps() throws Exception {
    assertThat(
      Values.areEqual(getValue(null, Timestamp.valueOf("2007-12-23 09:01:06.000000003")),
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 3)))).isTrue();
    assertThat(
      Values.areEqual(getValue(null, Timestamp.valueOf("2007-12-23 09:01:06.000000003")),
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 5, 3)))).isFalse();
    assertThat(Values.areEqual(getValue(null, ""), DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 3))))
      .isFalse();
    assertThat(Values.areEqual(getValue(null, Timestamp.valueOf("2007-12-23 09:01:06.000000003")), (DateTimeValue) null))
      .isFalse();

    assertThat(
      Values.areEqual(getValue(null, Timestamp.valueOf("2007-12-23 09:01:06.000000003")),
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 2)))).isFalse();
    assertThat(
      Values.areEqual(getValue(null, Timestamp.valueOf("2007-12-23 09:01:06.000000003")),
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 5, 3)))).isFalse();
    assertThat(
      Values.areEqual(getValue(null, Timestamp.valueOf("2007-12-23 09:01:06.000000003")),
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 2, 6, 3)))).isFalse();
    assertThat(
      Values.areEqual(getValue(null, Timestamp.valueOf("2007-12-23 09:01:06.000000003")),
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(10, 1, 6, 3)))).isFalse();
    assertThat(
      Values.areEqual(getValue(null, Timestamp.valueOf("2007-12-23 09:01:06.000000003")),
        DateTimeValue.of(DateValue.of(2007, 12, 3), TimeValue.of(9, 1, 6, 3)))).isFalse();
    assertThat(
      Values.areEqual(getValue(null, Timestamp.valueOf("2007-12-23 09:01:06.000000003")),
        DateTimeValue.of(DateValue.of(2007, 2, 23), TimeValue.of(9, 1, 6, 3)))).isFalse();
    assertThat(
      Values.areEqual(getValue(null, Timestamp.valueOf("2007-12-23 09:01:06.000000003")),
        DateTimeValue.of(DateValue.of(2008, 12, 23), TimeValue.of(9, 1, 6, 3)))).isFalse();

    assertThat(
      Values.areEqual(getValue(null, Date.valueOf("2007-12-23")),
        DateTimeValue.of(DateValue.of(2007, 12, 23)))).isTrue();
    assertThat(
      Values.areEqual(getValue(null, Date.valueOf("2007-12-23")),
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 5, 3)))).isFalse();
    assertThat(Values.areEqual(getValue(null, ""), DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 3)))).isFalse();
    assertThat(Values.areEqual(getValue(null, Date.valueOf("2007-12-23")), (DateTimeValue) null)).isFalse();

    assertThat(
      Values.areEqual(getValue(null, Date.valueOf("2007-12-23")),
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 2)))).isFalse();
    assertThat(
      Values.areEqual(getValue(null, Date.valueOf("2007-12-23")),
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 5, 3)))).isFalse();
    assertThat(
      Values.areEqual(getValue(null, Date.valueOf("2007-12-23")),
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 2, 6, 3)))).isFalse();
    assertThat(
      Values.areEqual(getValue(null, Date.valueOf("2007-12-23")),
        DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(10, 1, 6, 3)))).isFalse();
    assertThat(
      Values.areEqual(getValue(null, Date.valueOf("2007-12-23")),
        DateTimeValue.of(DateValue.of(2007, 12, 3), TimeValue.of(9, 1, 6, 3)))).isFalse();
    assertThat(
      Values.areEqual(getValue(null, Date.valueOf("2007-12-23")),
        DateTimeValue.of(DateValue.of(2007, 2, 23), TimeValue.of(9, 1, 6, 3)))).isFalse();
    assertThat(
      Values.areEqual(getValue(null, Date.valueOf("2007-12-23")),
        DateTimeValue.of(DateValue.of(2008, 12, 23), TimeValue.of(9, 1, 6, 3)))).isFalse();
  }

}
