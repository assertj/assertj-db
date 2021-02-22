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
package org.assertj.db.util;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.DateTimeValue;
import org.assertj.db.type.DateValue;
import org.assertj.db.type.TimeValue;
import org.junit.Test;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
* Tests on {@code getRepresentationFromValueInFrontOfExpected} method from utility class {@code Values}.
*
* @author Régis Pouiller
*
*/
public class Values_GetOutputFromValueInFrontOfExpected_Test extends AbstractTest {

  /**
   * This method tests the {@code getRepresentationFromValueInFrontOfExpected} method for {@code Date}s.
   */
  @Test
  public void test_getRepresentationFromValueInFrontOfExpected_for_dates() throws Exception {
    Date date = Date.valueOf("2007-12-23");
    assertThat(Values.getRepresentationFromValueInFrontOfExpected(getValue(null, date), DateValue.of(2007, 12, 23))).isEqualTo(
            DateValue.from(date));
    assertThat(Values.getRepresentationFromValueInFrontOfExpected(getValue(null, date), DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)))).isEqualTo(
            DateTimeValue.of(DateValue.from(date)));
    assertThat(Values.getRepresentationFromValueInFrontOfExpected(getValue(null, date), "")).isEqualTo("2007-12-23");
    assertThat(Values.getRepresentationFromValueInFrontOfExpected(getValue(null, date), "T")).isEqualTo(
            "2007-12-23T00:00:00.000000000");
    assertThat(Values.getRepresentationFromValueInFrontOfExpected(getValue(null, date), null)).isEqualTo(date);
  }

  /**
   * This method tests the {@code getRepresentationFromValueInFrontOfExpected} method for {@code Time}s.
   */
  @Test
  public void test_getRepresentationFromValueInFrontOfExpected_for_times() throws Exception {
    Time time = Time.valueOf("09:01:06");
    assertThat(Values.getRepresentationFromValueInFrontOfExpected(getValue(null, time), null)).isEqualTo(TimeValue.from(time));
    assertThat(Values.getRepresentationFromValueInFrontOfExpected(getValue(null, time), "")).isEqualTo("09:01:06.000000000");
  }

  /**
   * This method tests the {@code getRepresentationFromValueInFrontOfExpected} method for {@code Timestamp}s.
   */
  @Test
  public void test_getRepresentationFromValueInFrontOfExpected_for_datetime() throws Exception {
    Timestamp timestamp = Timestamp.valueOf("2007-12-23 09:01:06.000000003");
    assertThat(Values.getRepresentationFromValueInFrontOfExpected(getValue(null, timestamp), null)).isEqualTo(
            DateTimeValue.from(timestamp));
    assertThat(Values.getRepresentationFromValueInFrontOfExpected(getValue(null, timestamp), "")).isEqualTo(
            "2007-12-23T09:01:06.000000003");
  }

  /**
   * This method tests the {@code getRepresentationFromValueInFrontOfExpected} method for array of {@code byte}.
   */
  @Test
  public void test_getRepresentationFromValueInFrontOfExpected_for_bytes() throws Exception {
    assertThat(Values.getRepresentationFromValueInFrontOfExpected(getValue(null, new byte[] {1, 2}), null)).isEqualTo(
            new byte[] { 1, 2 });
  }

  /**
   * This method tests the {@code getRepresentationFromValueInFrontOfExpected} method for {@code String}s.
   */
  @Test
  public void test_getRepresentationFromValueInFrontOfExpected_for_text() throws Exception {
    assertThat(Values.getRepresentationFromValueInFrontOfExpected(getValue(null, "text"), null)).isEqualTo("text");
  }

  /**
   * This method tests the {@code getRepresentationFromValueInFrontOfExpected} method for {@code UUID}s.
   */
  @Test
  public void test_getRepresentationFromValueInFrontOfExpected_for_UUID() throws Exception {
    assertThat(Values.getRepresentationFromValueInFrontOfExpected(getValue(null, UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435")), "30B443AE-C0C9-4790-9BEC-CE1380808435")).isEqualTo(
            "30b443ae-c0c9-4790-9bec-ce1380808435");
    assertThat(Values.getRepresentationFromValueInFrontOfExpected(getValue(null, UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435")), null)).isEqualTo(
            UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"));
  }

  /**
   * This method tests the {@code getRepresentationFromValueInFrontOfExpected} method for {@code Boolean}s.
   */
  @Test
  public void test_getRepresentationFromValueInFrontOfExpected_for_boolean() throws Exception {
    assertThat(Values.getRepresentationFromValueInFrontOfExpected(getValue(null, true), null)).isEqualTo(true);
    assertThat(Values.getRepresentationFromValueInFrontOfExpected(getValue(null, false), null)).isEqualTo(false);
  }

  /**
   * This method tests the {@code getRepresentationFromValueInFrontOfExpected} method for {@code null}.
   */
  @Test
  public void test_getRepresentationFromValueInFrontOfExpected_for_null() throws Exception {
    assertThat(Values.getRepresentationFromValueInFrontOfExpected(getValue(null, null), null)).isNull();
  }

}
