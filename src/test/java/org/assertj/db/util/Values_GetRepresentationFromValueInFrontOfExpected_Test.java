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
 * Copyright 2012-2014 the original author or authors.
 */
package org.assertj.db.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import org.assertj.db.type.DateTimeValue;
import org.assertj.db.type.DateValue;
import org.assertj.db.type.TimeValue;
import org.junit.Test;

/**
* Tests on {@code getRepresentationFromValueInFrontOfExpected} method from utility class {@code Values}.
*
* @author Régis Pouiller
*
*/
public class Values_GetRepresentationFromValueInFrontOfExpected_Test {

  /**
   * This method tests the {@code getRepresentationFromValueInFrontOfExpected} method for {@code Date}s.
   */
  @Test
  public void test_getRepresentationFromValueInFrontOfExpected_for_dates() {
    Date date = Date.valueOf("2007-12-23");
    assertThat(Values.getRepresentationFromValueInFrontOfExpected(date, DateValue.of(2007, 12, 23))).isEqualTo(DateValue.from(date));
    assertThat(Values.getRepresentationFromValueInFrontOfExpected(date, DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)))).isEqualTo(DateTimeValue.of(DateValue.from(date)));
    assertThat(Values.getRepresentationFromValueInFrontOfExpected(date, "")).isEqualTo("2007-12-23");
    assertThat(Values.getRepresentationFromValueInFrontOfExpected(date, "T")).isEqualTo("2007-12-23T00:00:00.000000000");
    assertThat(Values.getRepresentationFromValueInFrontOfExpected(date, null)).isEqualTo(date);
  }

  /**
   * This method tests the {@code getRepresentationFromValueInFrontOfExpected} method for {@code Time}s.
   */
  @Test
  public void test_getRepresentationFromValueInFrontOfExpected_for_times() {
    Time time = Time.valueOf("09:01:06");
    assertThat(Values.getRepresentationFromValueInFrontOfExpected(time, null)).isEqualTo(TimeValue.from(time));
    assertThat(Values.getRepresentationFromValueInFrontOfExpected(time, "")).isEqualTo("09:01:06.000000000");
  }

  /**
   * This method tests the {@code getRepresentationFromValueInFrontOfExpected} method for {@code Timestamp}s.
   */
  @Test
  public void test_getRepresentationFromValueInFrontOfExpected_for_datetime() {
    Timestamp timestamp = Timestamp.valueOf("2007-12-23 09:01:06.000000003");
    assertThat(Values.getRepresentationFromValueInFrontOfExpected(timestamp, null)).isEqualTo(DateTimeValue.from(timestamp));
    assertThat(Values.getRepresentationFromValueInFrontOfExpected(timestamp, "")).isEqualTo("2007-12-23T09:01:06.000000003");
  }

  /**
   * This method tests the {@code getRepresentationFromValueInFrontOfExpected} method for array of {@code byte}.
   */
  @Test
  public void test_getRepresentationFromValueInFrontOfExpected_for_bytes() {
    assertThat(Values.getRepresentationFromValueInFrontOfExpected(new byte[] {1, 2}, null)).isEqualTo(new byte[] {1, 2});
  }

  /**
   * This method tests the {@code getRepresentationFromValueInFrontOfExpected} method for {@code String}s.
   */
  @Test
  public void test_getRepresentationFromValueInFrontOfExpected_for_text() {
    assertThat(Values.getRepresentationFromValueInFrontOfExpected("text", null)).isEqualTo("text");
  }

  /**
   * This method tests the {@code getRepresentationFromValueInFrontOfExpected} method for {@code Boolean}s.
   */
  @Test
  public void test_getRepresentationFromValueInFrontOfExpected_for_boolean() {
    assertThat(Values.getRepresentationFromValueInFrontOfExpected(true, null)).isEqualTo(true);
    assertThat(Values.getRepresentationFromValueInFrontOfExpected(false, null)).isEqualTo(false);
  }

  /**
   * This method tests the {@code getRepresentationFromValueInFrontOfExpected} method for {@code null}.
   */
  @Test
  public void test_getRepresentationFromValueInFrontOfExpected_for_null() {
    assertThat(Values.getRepresentationFromValueInFrontOfExpected(null, null)).isNull();
  }

}
