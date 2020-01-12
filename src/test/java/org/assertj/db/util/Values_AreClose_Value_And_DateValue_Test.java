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
 * Copyright 2015-2020 the original author or authors.
 */
package org.assertj.db.util;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.DateTimeValue;
import org.assertj.db.type.DateValue;
import org.assertj.db.type.TimeValue;
import org.junit.Test;

import java.sql.Date;
import java.sql.Timestamp;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests on {@code areClose} method for {@code DateValue}s.
 *
 * @author Régis Pouiller
 *
 */
public class Values_AreClose_Value_And_DateValue_Test extends AbstractTest {

  /**
   * This method tests the {@code areClose} method for {@code DateValue}s and {@code java.sql.Date}.
   */
  @Test
  public void test_are_close_for_dates_with_date() throws Exception {
    assertThat(Values.areClose(getValue(null, Date.valueOf("2007-12-23")), DateValue.of(2007, 12, 23), DateValue.of(0, 0, 0))).isTrue();
    assertThat(Values.areClose(getValue(null, Date.valueOf("2007-12-23")), DateValue.of(2007, 1, 2), DateValue.of(0, 0, 0))).isFalse();
    assertThat(Values.areClose(getValue(null, ""), DateValue.of(2007, 12, 23), DateValue.of(0, 0, 0))).isFalse();
    assertThat(Values.areClose(getValue(null, Date.valueOf("2007-12-23")), (DateValue) null, DateValue.of(0, 0, 0))).isFalse();

    assertThat(Values.areClose(getValue(null, Date.valueOf("2007-12-23")), DateValue.of(2007, 12, 2), DateValue.of(0, 0, 0))).isFalse();
    assertThat(Values.areClose(getValue(null, Date.valueOf("2007-12-23")), DateValue.of(2007, 1, 23), DateValue.of(0, 0, 0))).isFalse();
    assertThat(Values.areClose(getValue(null, Date.valueOf("2007-12-23")), DateValue.of(2008, 1, 23), DateValue.of(0, 0, 0))).isFalse();
    assertThat(Values.areClose(getValue(null, Date.valueOf("2007-12-23")), DateValue.of(2006, 12, 23), DateValue.of(0, 0, 0))).isFalse();

    assertThat(Values.areClose(getValue(null, Date.valueOf("2007-12-23")), DateValue.of(2007, 12, 2), DateValue.of(0, 0, 21))).isTrue();
    assertThat(Values.areClose(getValue(null, Date.valueOf("2007-12-23")), DateValue.of(2007, 1, 23), DateValue.of(0, 11, 0))).isTrue();
    assertThat(Values.areClose(getValue(null, Date.valueOf("2007-12-23")), DateValue.of(2006, 12, 23), DateValue.of(1, 0, 0))).isTrue();
  }

  /**
   * This method tests the {@code areClose} method for {@code DateValue}s and {@code java.sql.Timestamp}.
   */
  @Test
  public void test_are_close_for_timestamp_and_dates_with_date() throws Exception {
    assertThat(Values.areClose(getValue(null, Timestamp.valueOf("2007-12-23 00:00:00.000000000")),
                               DateValue.of(2007, 12, 23), DateValue.of(0, 0, 0))).isTrue();
    assertThat(Values.areClose(getValue(null, Timestamp.valueOf("2007-12-23 00:00:00.000000000")),
                               DateValue.of(2007, 1, 2), DateValue.of(0, 0, 0))).isFalse();
    assertThat(Values.areClose(getValue(null, ""), DateValue.of(2007, 12, 23), DateValue.of(0, 0, 0))).isFalse();
    assertThat(Values.areClose(getValue(null, Timestamp.valueOf("2007-12-23 00:00:00.000000000")), (DateValue) null, DateValue.of(0, 0, 0))).isFalse();

    assertThat(Values.areClose(getValue(null, Timestamp.valueOf("2007-12-23 00:00:00.000000000")),
                               DateValue.of(2007, 12, 2), DateValue.of(0, 0, 0))).isFalse();
    assertThat(Values.areClose(getValue(null, Timestamp.valueOf("2007-12-23 00:00:00.000000000")),
                               DateValue.of(2007, 1, 23), DateValue.of(0, 0, 0))).isFalse();
    assertThat(Values.areClose(getValue(null, Timestamp.valueOf("2007-12-23 00:00:00.000000000")),
                               DateValue.of(2008, 1, 23), DateValue.of(0, 0, 0))).isFalse();
    assertThat(Values.areClose(getValue(null, Timestamp.valueOf("2007-12-23 00:00:00.000000000")),
                               DateValue.of(2006, 12, 23), DateValue.of(0, 0, 0))).isFalse();

    assertThat(Values.areClose(getValue(null, Timestamp.valueOf("2007-12-23 00:00:00.000000000")),
                               DateValue.of(2007, 12, 2), DateValue.of(0, 0, 21))).isTrue();
    assertThat(Values.areClose(getValue(null, Timestamp.valueOf("2007-12-23 00:00:00.000000000")),
                               DateValue.of(2007, 1, 23), DateValue.of(0, 11, 0))).isTrue();
    assertThat(Values.areClose(getValue(null, Timestamp.valueOf("2007-12-23 00:00:00.000000000")),
                               DateValue.of(2006, 12, 23), DateValue.of(1, 0, 0))).isTrue();
  }

  /**
   * This method tests the {@code areClose} method for {@code DateValue}s and {@code java.sql.Date}.
   */
  @Test
  public void test_are_close_for_dates_with_time() throws Exception {
    assertThat(Values.areClose(getValue(null, Date.valueOf("2007-12-23")), DateValue.of(2007, 12, 23), TimeValue.of(0, 0, 0))).isTrue();
    assertThat(Values.areClose(getValue(null, Date.valueOf("2007-12-23")), DateValue.of(2007, 1, 2), TimeValue.of(0, 0, 0))).isFalse();
    assertThat(Values.areClose(getValue(null, ""), DateValue.of(2007, 12, 23), TimeValue.of(0, 0, 0))).isFalse();
    assertThat(Values.areClose(getValue(null, Date.valueOf("2007-12-23")), (DateValue) null, TimeValue.of(0, 0, 0))).isFalse();

    assertThat(Values.areClose(getValue(null, Date.valueOf("2007-12-23")), DateValue.of(2007, 12, 2), TimeValue.of(0, 0, 0))).isFalse();
    assertThat(Values.areClose(getValue(null, Date.valueOf("2007-12-23")), DateValue.of(2007, 1, 23), TimeValue.of(0, 0, 0))).isFalse();
    assertThat(Values.areClose(getValue(null, Date.valueOf("2007-12-23")), DateValue.of(2008, 1, 23), TimeValue.of(0, 0, 0))).isFalse();
    assertThat(Values.areClose(getValue(null, Date.valueOf("2007-12-23")), DateValue.of(2006, 12, 23), TimeValue.of(0, 0, 0))).isFalse();

    assertThat(Values.areClose(getValue(null, Date.valueOf("2007-12-23")), DateValue.of(2007, 12, 2), TimeValue.of(21 * 24, 0, 0))).isTrue();
    assertThat(Values.areClose(getValue(null, Date.valueOf("2007-12-23")), DateValue.of(2007, 1, 23), TimeValue.of(0, 11, 0))).isFalse();
    assertThat(Values.areClose(getValue(null, Date.valueOf("2007-12-23")), DateValue.of(2006, 12, 23), TimeValue
            .of(1, 0, 0))).isFalse();
  }

  /**
   * This method tests the {@code areClose} method for {@code DateValue}s and {@code java.sql.Timestamp}.
   */
  @Test
  public void test_are_close_for_timestamp_and_dates_with_time() throws Exception {
    assertThat(Values.areClose(getValue(null, Timestamp.valueOf("2007-12-23 00:00:00.000000000")),
                               DateValue.of(2007, 12, 23), TimeValue.of(0, 0, 0))).isTrue();
    assertThat(Values.areClose(getValue(null, Timestamp.valueOf("2007-12-23 00:00:00.000000000")),
                               DateValue.of(2007, 1, 2), TimeValue.of(0, 0, 0))).isFalse();
    assertThat(Values.areClose(getValue(null, ""), DateValue.of(2007, 12, 23), TimeValue.of(0, 0, 0))).isFalse();
    assertThat(Values.areClose(getValue(null, Timestamp.valueOf("2007-12-23 00:00:00.000000000")), (DateValue) null, TimeValue.of(0, 0, 0))).isFalse();

    assertThat(Values.areClose(getValue(null, Timestamp.valueOf("2007-12-23 00:00:00.000000000")),
                               DateValue.of(2007, 12, 2), TimeValue.of(0, 0, 0))).isFalse();
    assertThat(Values.areClose(getValue(null, Timestamp.valueOf("2007-12-23 00:00:00.000000000")),
                               DateValue.of(2007, 1, 23), TimeValue.of(0, 0, 0))).isFalse();
    assertThat(Values.areClose(getValue(null, Timestamp.valueOf("2007-12-23 00:00:00.000000000")),
                               DateValue.of(2008, 1, 23), TimeValue.of(0, 0, 0))).isFalse();
    assertThat(Values.areClose(getValue(null, Timestamp.valueOf("2007-12-23 00:00:00.000000000")),
                               DateValue.of(2006, 12, 23), TimeValue.of(0, 0, 0))).isFalse();

    assertThat(Values.areClose(getValue(null, Timestamp.valueOf("2007-12-23 00:00:00.000000000")),
                               DateValue.of(2007, 12, 2), TimeValue.of(21 * 24, 0, 0))).isTrue();
    assertThat(Values.areClose(getValue(null, Timestamp.valueOf("2007-12-23 00:00:00.000000000")),
                               DateValue.of(2007, 1, 23), TimeValue.of(0, 11, 0))).isFalse();
    assertThat(Values.areClose(getValue(null, Timestamp.valueOf("2007-12-23 00:00:00.000000000")),
                               DateValue.of(2006, 12, 23), TimeValue.of(1, 0, 0))).isFalse();
  }

  /**
   * This method tests the {@code areClose} method for {@code DateValue}s and {@code java.sql.Date}.
   */
  @Test
  public void test_are_close_for_dates_with_datetime() throws Exception {
    assertThat(Values.areClose(getValue(null, Date.valueOf("2007-12-23")), DateValue.of(2007, 12, 23), DateTimeValue.of(DateValue.of(0, 0, 0)))).isTrue();
    assertThat(Values.areClose(getValue(null, Date.valueOf("2007-12-23")), DateValue.of(2007, 1, 2), DateTimeValue
            .of(DateValue.of(0,
                             0, 0)))).isFalse();
    assertThat(Values.areClose(getValue(null, ""), DateValue.of(2007, 12, 23), DateTimeValue.of(DateValue.of(0, 0, 0)))).isFalse();
    assertThat(Values.areClose(getValue(null, Date.valueOf("2007-12-23")), (DateValue) null, DateTimeValue.of(DateValue.of(0, 0, 0)))).isFalse();

    assertThat(Values.areClose(getValue(null, Date.valueOf("2007-12-23")), DateValue.of(2007, 12, 2), DateTimeValue.of(DateValue.of(0, 0, 0)))).isFalse();
    assertThat(Values.areClose(getValue(null, Date.valueOf("2007-12-23")), DateValue.of(2007, 1, 23), DateTimeValue.of(DateValue.of(0, 0, 0)))).isFalse();
    assertThat(Values.areClose(getValue(null, Date.valueOf("2007-12-23")), DateValue.of(2008, 1, 23), DateTimeValue.of(DateValue.of(0, 0, 0)))).isFalse();
    assertThat(Values.areClose(getValue(null, Date.valueOf("2007-12-23")), DateValue.of(2006, 12, 23), DateTimeValue.of(DateValue.of(0, 0, 0)))).isFalse();

    assertThat(Values.areClose(getValue(null, Date.valueOf("2007-12-23")), DateValue.of(2007, 12, 2), DateTimeValue.of(DateValue.of(0, 0, 21)))).isTrue();
    assertThat(Values.areClose(getValue(null, Date.valueOf("2007-12-23")), DateValue.of(2007, 1, 23), DateTimeValue.of(DateValue.of(0, 11, 0)))).isTrue();
    assertThat(Values.areClose(getValue(null, Date.valueOf("2007-12-23")), DateValue.of(2006, 12, 23), DateTimeValue.of(DateValue.of(1, 0, 0)))).isTrue();
  }

  /**
   * This method tests the {@code areClose} method for {@code DateValue}s and {@code java.sql.Timestamp}.
   */
  @Test
  public void test_are_close_for_timestamp_and_dates_with_datetime() throws Exception {
    assertThat(Values.areClose(getValue(null, Timestamp.valueOf("2007-12-23 00:00:00.000000000")),
                               DateValue.of(2007, 12, 23), DateTimeValue.of(DateValue.of(0, 0, 0)))).isTrue();
    assertThat(Values.areClose(getValue(null, Timestamp.valueOf("2007-12-23 00:00:00.000000000")),
                               DateValue.of(2007, 1, 2), DateTimeValue.of(DateValue.of(0, 0, 0)))).isFalse();
    assertThat(Values.areClose(getValue(null, ""), DateValue.of(2007, 12, 23), DateTimeValue.of(DateValue.of(0, 0, 0)))).isFalse();
    assertThat(Values.areClose(getValue(null, Timestamp.valueOf("2007-12-23 00:00:00.000000000")), (DateValue) null, DateTimeValue.of(DateValue.of(0,
                                                                                                                                  0, 0)))).isFalse();

    assertThat(Values.areClose(getValue(null, Timestamp.valueOf("2007-12-23 00:00:00.000000000")),
                               DateValue.of(2007, 12, 2), DateTimeValue.of(DateValue.of(0, 0, 0)))).isFalse();
    assertThat(Values.areClose(getValue(null, Timestamp.valueOf("2007-12-23 00:00:00.000000000")),
                               DateValue.of(2007, 1, 23), DateTimeValue.of(DateValue.of(0, 0, 0)))).isFalse();
    assertThat(Values.areClose(getValue(null, Timestamp.valueOf("2007-12-23 00:00:00.000000000")),
                               DateValue.of(2008, 1, 23), DateTimeValue.of(DateValue.of(0, 0, 0)))).isFalse();
    assertThat(Values.areClose(getValue(null, Timestamp.valueOf("2007-12-23 00:00:00.000000000")),
                               DateValue.of(2006, 12, 23), DateTimeValue.of(DateValue.of(0, 0, 0)))).isFalse();

    assertThat(Values.areClose(getValue(null, Timestamp.valueOf("2007-12-23 00:00:00.000000000")),
                               DateValue.of(2007, 12, 2), DateTimeValue.of(DateValue.of(0, 0, 21)))).isTrue();
    assertThat(Values.areClose(getValue(null, Timestamp.valueOf("2007-12-23 00:00:00.000000000")),
                               DateValue.of(2007, 1, 23), DateTimeValue.of(DateValue.of(0, 11, 0)))).isTrue();
    assertThat(Values.areClose(getValue(null, Timestamp.valueOf("2007-12-23 00:00:00.000000000")),
                               DateValue.of(2006, 12, 23), DateTimeValue.of(DateValue.of(1, 0, 0)))).isTrue();
  }
}
