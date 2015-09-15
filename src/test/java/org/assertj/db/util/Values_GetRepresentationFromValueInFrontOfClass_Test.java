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
 * Copyright 2012-2015 the original author or authors.
 */
package org.assertj.db.util;

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
* Tests on {@code getRepresentationFromValueInFrontOfClass} method from utility class {@code Values}.
*
* @author Régis Pouiller
*
*/
public class Values_GetRepresentationFromValueInFrontOfClass_Test {

  /**
   * This method tests the {@code getRepresentationFromValueInFrontOfClass} method for {@code Date}s.
   */
  @Test
  public void test_getRepresentationFromValueInFrontOfClass_for_dates() {
    Date date = Date.valueOf("2007-12-23");
    assertThat(Values.getRepresentationFromValueInFrontOfClass(date, DateValue.class))
            .isEqualTo(DateValue.from(date));
    assertThat(Values.getRepresentationFromValueInFrontOfClass(date, DateTimeValue.class))
            .isEqualTo(DateTimeValue.of(DateValue.from(date)));
    assertThat(Values.getRepresentationFromValueInFrontOfClass(date, String.class))
            .isEqualTo("2007-12-23T00:00:00.000000000");
    assertThat(Values.getRepresentationFromValueInFrontOfClass(date, null))
            .isEqualTo(date);
  }

  /**
   * This method tests the {@code getRepresentationFromValueInFrontOfClass} method for {@code Time}s.
   */
  @Test
  public void test_getRepresentationFromValueInFrontOfClass_for_times() {
    Time time = Time.valueOf("09:01:06");
    assertThat(Values.getRepresentationFromValueInFrontOfClass(time, null)).isEqualTo(TimeValue.from(time));
    assertThat(Values.getRepresentationFromValueInFrontOfClass(time, String.class)).isEqualTo("09:01:06.000000000");
  }

  /**
   * This method tests the {@code getRepresentationFromValueInFrontOfClass} method for {@code Timestamp}s.
   */
  @Test
  public void test_getRepresentationFromValueInFrontOfClass_for_datetime() {
    Timestamp timestamp = Timestamp.valueOf("2007-12-23 09:01:06.000000003");
    assertThat(Values.getRepresentationFromValueInFrontOfClass(timestamp, null)).isEqualTo(DateTimeValue.from(timestamp));
    assertThat(Values.getRepresentationFromValueInFrontOfClass(timestamp, String.class)).isEqualTo("2007-12-23T09:01:06.000000003");
  }

  /**
   * This method tests the {@code getRepresentationFromValueInFrontOfClass} method for {@code Number}s.
   */
  @Test
  public void test_getRepresentationFromValueInFrontOfClass_for_numbers() {
    assertThat(Values.getRepresentationFromValueInFrontOfClass(8,
                                                               String.class)).isEqualTo("8");
    assertThat(Values.getRepresentationFromValueInFrontOfClass(8,
                                                               null)).isEqualTo(8);
  }

  /**
   * This method tests the {@code getRepresentationFromValueInFrontOfClass} method for {@code UUID}s.
   */
  @Test
  public void test_getRepresentationFromValueInFrontOfClass_for_UUID() {
    assertThat(Values.getRepresentationFromValueInFrontOfClass(UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"),
                                                               String.class)).isEqualTo("30b443ae-c0c9-4790-9bec-ce1380808435");
    assertThat(Values.getRepresentationFromValueInFrontOfClass(UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"),
                                                               null)).isEqualTo(UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"));
  }

  /**
   * This method tests the {@code getRepresentationFromValueInFrontOfClass} method for array of {@code byte}.
   */
  @Test
  public void test_getRepresentationFromValueInFrontOfClass_for_bytes() {
    assertThat(Values.getRepresentationFromValueInFrontOfClass(new byte[] { 1, 2 }, null)).isEqualTo(new byte[] {1, 2});
  }

  /**
   * This method tests the {@code getRepresentationFromValueInFrontOfClass} method for {@code String}s.
   */
  @Test
  public void test_getRepresentationFromValueInFrontOfClass_for_text() {
    assertThat(Values.getRepresentationFromValueInFrontOfClass("text", null)).isEqualTo("text");
  }

  /**
   * This method tests the {@code getRepresentationFromValueInFrontOfClass} method for {@code Boolean}s.
   */
  @Test
  public void test_getRepresentationFromValueInFrontOfClass_for_boolean() {
    assertThat(Values.getRepresentationFromValueInFrontOfClass(true, null)).isEqualTo(true);
    assertThat(Values.getRepresentationFromValueInFrontOfClass(false, null)).isEqualTo(false);
  }

  /**
   * This method tests the {@code getRepresentationFromValueInFrontOfClass} method for {@code null}.
   */
  @Test
  public void test_getRepresentationFromValueInFrontOfClass_for_null() {
    assertThat(Values.getRepresentationFromValueInFrontOfClass(null, null)).isNull();
  }

}
