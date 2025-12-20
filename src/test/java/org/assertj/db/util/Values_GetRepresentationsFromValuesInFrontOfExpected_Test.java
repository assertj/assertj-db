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
import java.sql.Time;
import java.sql.Timestamp;
import java.util.UUID;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.DateTimeValue;
import org.assertj.db.type.DateValue;
import org.assertj.db.type.TimeValue;
import org.assertj.db.type.Value;
import org.junit.Test;

/**
 * Tests on {@code getRepresentationsFromValuesInFrontOfExpected} method from utility class {@code Values}.
 *
 * @author Régis Pouiller
 */
public class Values_GetRepresentationsFromValuesInFrontOfExpected_Test extends AbstractTest {

  /**
   * This method tests the {@code getRepresentationsFromValuesInFrontOfExpected} method for {@code Date}s.
   */
  @Test
  public void test_getRepresentationsFromValuesInFrontOfExpected_for_dates() throws Exception {
    Date date = Date.valueOf("2007-12-23");
    assertThat(Values.getRepresentationsFromValuesInFrontOfExpected(new Value[]{getValue(null, date), getValue(null,
        date)},
      new DateValue[]{DateValue.of(2007, 12, 23)}))
      .isEqualTo(new Object[]{DateValue.from(date), date});
    assertThat(Values.getRepresentationsFromValuesInFrontOfExpected(new Value[]{getValue(null, date), getValue(null,
        date)},
      new DateTimeValue[]{DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1))}))
      .isEqualTo(new Object[]{DateTimeValue.of(DateValue.from(date)), date});
    assertThat(Values.getRepresentationsFromValuesInFrontOfExpected(new Value[]{getValue(null, date), getValue(null,
        date)},
      new String[]{""}))
      .isEqualTo(new Object[]{"2007-12-23", date});
    assertThat(Values.getRepresentationsFromValuesInFrontOfExpected(new Value[]{getValue(null, date), getValue(null,
        date)},
      new String[]{"T"}))
      .isEqualTo(new Object[]{"2007-12-23T00:00:00.000000000", date});
    assertThat(Values.getRepresentationsFromValuesInFrontOfExpected(new Value[]{getValue(null, date), getValue(null,
        date)},
      new Object[]{null}))
      .isEqualTo(new Object[]{date, date});
  }

  /**
   * This method tests the {@code getRepresentationsFromValuesInFrontOfExpected} method for {@code Time}s.
   */
  @Test
  public void test_getRepresentationsFromValuesInFrontOfExpected_for_times() throws Exception {
    Time time = Time.valueOf("09:01:06");
    assertThat(Values.getRepresentationsFromValuesInFrontOfExpected(new Value[]{getValue(null, time), getValue(null,
        time)},
      new Object[]{null}))
      .isEqualTo(new Object[]{TimeValue.from(time), time});
    assertThat(Values.getRepresentationsFromValuesInFrontOfExpected(new Value[]{getValue(null, time), getValue(null,
        time)},
      new String[]{""}))
      .isEqualTo(new Object[]{"09:01:06.000000000", time});
  }

  /**
   * This method tests the {@code getRepresentationsFromValuesInFrontOfExpected} method for {@code Timestamp}s.
   */
  @Test
  public void test_getRepresentationsFromValuesInFrontOfExpected_for_datetime() throws Exception {
    Timestamp timestamp = Timestamp.valueOf("2007-12-23 09:01:06.000000003");
    assertThat(Values.getRepresentationsFromValuesInFrontOfExpected(new Value[]{getValue(null, timestamp), getValue(
        null, timestamp)},
      new Object[]{null}))
      .isEqualTo(new Object[]{DateTimeValue.from(timestamp), timestamp});
    assertThat(Values.getRepresentationsFromValuesInFrontOfExpected(new Value[]{getValue(null, timestamp), getValue(
        null, timestamp)},
      new String[]{""}))
      .isEqualTo(new Object[]{"2007-12-23T09:01:06.000000003", timestamp});
  }

  /**
   * This method tests the {@code getRepresentationsFromValuesInFrontOfExpected} method for array of {@code byte}.
   */
  @Test
  public void test_getRepresentationsFromValuesInFrontOfExpected_for_bytes() throws Exception {
    assertThat(Values.getRepresentationsFromValuesInFrontOfExpected(new Value[]{getValue(null, new byte[]{1, 2}), getValue(
        null, new byte[]{1, 2})},
      new Object[]{null}))
      .isEqualTo(new byte[][]{new byte[]{1, 2}, new byte[]{1, 2}});
  }

  /**
   * This method tests the {@code getRepresentationsFromValuesInFrontOfExpected} method for {@code String}s.
   */
  @Test
  public void test_getRepresentationsFromValuesInFrontOfExpected_for_text() throws Exception {
    assertThat(Values.getRepresentationsFromValuesInFrontOfExpected(new Value[]{getValue(null, "text"), getValue(null,
      "text1")}, new Object[]{null}))
      .isEqualTo(new String[]{"text", "text1"});
  }

  /**
   * This method tests the {@code getRepresentationsFromValuesInFrontOfExpected} method for {@code UUID}s.
   */
  @Test
  public void test_getRepresentationsFromValuesInFrontOfExpected_for_UUID() throws Exception {
    assertThat(Values.getRepresentationsFromValuesInFrontOfExpected(
      new Value[]{getValue(null, UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435")),
        getValue(null, UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"))}, new String[]{"30B443AE-C0C9-4790-9BEC-CE1380808435"}))
      .isEqualTo(new Object[]{"30b443ae-c0c9-4790-9bec-ce1380808435", UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435")});
    assertThat(Values.getRepresentationsFromValuesInFrontOfExpected(
      new Value[]{getValue(null, UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435")),
        getValue(null, UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"))}, new Object[]{null}))
      .isEqualTo(new UUID[]{UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"), UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435")});
  }

  /**
   * This method tests the {@code getRepresentationsFromValuesInFrontOfExpected} method for {@code Boolean}s.
   */
  @Test
  public void test_getRepresentationsFromValuesInFrontOfExpected_for_boolean() throws Exception {
    assertThat(Values.getRepresentationsFromValuesInFrontOfExpected(new Value[]{getValue(null, true), getValue(null,
      false)}, new Object[]{null}))
      .isEqualTo(new Boolean[]{true, false});
  }

  /**
   * This method tests the {@code getRepresentationsFromValuesInFrontOfExpected} method for {@code null}.
   */
  @Test
  public void test_getRepresentationsFromValuesInFrontOfExpected_for_null() throws Exception {
    assertThat(Values.getRepresentationsFromValuesInFrontOfExpected(new Value[]{getValue(null, null), getValue(null,
      null)}, new Object[]{null}))
      .isEqualTo(new Boolean[]{null, null});
  }

}
