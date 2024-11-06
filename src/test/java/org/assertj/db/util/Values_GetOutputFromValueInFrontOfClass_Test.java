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
import org.junit.Test;

/**
 * Tests on {@code getRepresentationFromValueInFrontOfClass} method from utility class {@code Values}.
 *
 * @author Régis Pouiller
 */
public class Values_GetOutputFromValueInFrontOfClass_Test extends AbstractTest {

  /**
   * This method tests the {@code getRepresentationFromValueInFrontOfClass} method for {@code Date}s.
   */
  @Test
  public void test_getRepresentationFromValueInFrontOfClass_for_dates() throws Exception {
    Date date = Date.valueOf("2007-12-23");
    assertThat(Values.getRepresentationFromValueInFrontOfClass(getValue(null, date), DateValue.class))
      .isEqualTo(DateValue.from(date));
    assertThat(Values.getRepresentationFromValueInFrontOfClass(getValue(null, date), DateTimeValue.class))
      .isEqualTo(DateTimeValue.of(DateValue.from(date)));
    assertThat(Values.getRepresentationFromValueInFrontOfClass(getValue(null, date), String.class))
      .isEqualTo("2007-12-23T00:00:00.000000000");
    assertThat(Values.getRepresentationFromValueInFrontOfClass(getValue(null, date), null))
      .isEqualTo(date);
  }

  /**
   * This method tests the {@code getRepresentationFromValueInFrontOfClass} method for {@code Time}s.
   */
  @Test
  public void test_getRepresentationFromValueInFrontOfClass_for_times() throws Exception {
    Time time = Time.valueOf("09:01:06");
    assertThat(Values.getRepresentationFromValueInFrontOfClass(getValue(null, time), null)).isEqualTo(TimeValue.from(time));
    assertThat(Values.getRepresentationFromValueInFrontOfClass(getValue(null, time), String.class)).isEqualTo(
      "09:01:06.000000000");
  }

  /**
   * This method tests the {@code getRepresentationFromValueInFrontOfClass} method for {@code Timestamp}s.
   */
  @Test
  public void test_getRepresentationFromValueInFrontOfClass_for_datetime() throws Exception {
    Timestamp timestamp = Timestamp.valueOf("2007-12-23 09:01:06.000000003");
    assertThat(Values.getRepresentationFromValueInFrontOfClass(getValue(null, timestamp), null)).isEqualTo(
      DateTimeValue.from(timestamp));
    assertThat(Values.getRepresentationFromValueInFrontOfClass(getValue(null, timestamp), String.class)).isEqualTo(
      "2007-12-23T09:01:06.000000003");
  }

  /**
   * This method tests the {@code getRepresentationFromValueInFrontOfClass} method for {@code Number}s.
   */
  @Test
  public void test_getRepresentationFromValueInFrontOfClass_for_numbers() throws Exception {
    assertThat(Values.getRepresentationFromValueInFrontOfClass(getValue(null, 8),
      String.class)).isEqualTo("8");
    assertThat(Values.getRepresentationFromValueInFrontOfClass(getValue(null, 8),
      null)).isEqualTo(8);
  }

  /**
   * This method tests the {@code getRepresentationFromValueInFrontOfClass} method for {@code UUID}s.
   */
  @Test
  public void test_getRepresentationFromValueInFrontOfClass_for_UUID() throws Exception {
    assertThat(Values.getRepresentationFromValueInFrontOfClass(getValue(null, UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435")),
      String.class)).isEqualTo(
      "30b443ae-c0c9-4790-9bec-ce1380808435");
    assertThat(Values.getRepresentationFromValueInFrontOfClass(getValue(null, UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435")),
      null)).isEqualTo(
      UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"));
  }

  /**
   * This method tests the {@code getRepresentationFromValueInFrontOfClass} method for array of {@code byte}.
   */
  @Test
  public void test_getRepresentationFromValueInFrontOfClass_for_bytes() throws Exception {
    assertThat(Values.getRepresentationFromValueInFrontOfClass(getValue(null, new byte[]{1, 2}), null)).isEqualTo(
      new byte[]{1, 2});
  }

  /**
   * This method tests the {@code getRepresentationFromValueInFrontOfClass} method for {@code String}s.
   */
  @Test
  public void test_getRepresentationFromValueInFrontOfClass_for_text() throws Exception {
    assertThat(Values.getRepresentationFromValueInFrontOfClass(getValue(null, "text"), null)).isEqualTo("text");
  }

  /**
   * This method tests the {@code getRepresentationFromValueInFrontOfClass} method for {@code Boolean}s.
   */
  @Test
  public void test_getRepresentationFromValueInFrontOfClass_for_boolean() throws Exception {
    assertThat(Values.getRepresentationFromValueInFrontOfClass(getValue(null, true), null)).isEqualTo(true);
    assertThat(Values.getRepresentationFromValueInFrontOfClass(getValue(null, false), null)).isEqualTo(false);
  }

  /**
   * This method tests the {@code getRepresentationFromValueInFrontOfClass} method for {@code null}.
   */
  @Test
  public void test_getRepresentationFromValueInFrontOfClass_for_null() throws Exception {
    assertThat(Values.getRepresentationFromValueInFrontOfClass(getValue(null, null), null)).isNull();
  }

}
