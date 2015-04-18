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

import org.assertj.db.type.DateValue;
import org.junit.Test;

import java.sql.Date;
import java.sql.Timestamp;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests on {@code areEqual} method for {@code DateValue}s.
 * 
 * @author Régis Pouiller
 * 
 */
public class Values_AreEqual_Object_And_DateValue_Test {

  /**
   * This method tests the {@code areEqual} method for {@code DateValue}s and {@code java.sql.Date}.
   */
  @Test
  public void test_are_equal_for_dates() {
    assertThat(Values.areEqual(Date.valueOf("2007-12-23"), DateValue.of(2007, 12, 23))).isTrue();
    assertThat(Values.areEqual(Date.valueOf("2007-12-23"), DateValue.of(2007, 1, 2))).isFalse();
    assertThat(Values.areEqual("", DateValue.of(2007, 12, 23))).isFalse();
    assertThat(Values.areEqual(Date.valueOf("2007-12-23"), (DateValue) null)).isFalse();

    assertThat(Values.areEqual(Date.valueOf("2007-12-23"), DateValue.of(2007, 12, 2))).isFalse();
    assertThat(Values.areEqual(Date.valueOf("2007-12-23"), DateValue.of(2007, 1, 23))).isFalse();
    assertThat(Values.areEqual(Date.valueOf("2007-12-23"), DateValue.of(2006, 12, 23))).isFalse();
  }

  /**
   * This method tests the {@code areEqual} method for {@code DateValue}s and {@code java.sql.Timestamp}.
   */
  @Test
  public void test_are_equal_for_timestamp_and_dates() {
    assertThat(Values.areEqual(Timestamp.valueOf("2007-12-23 00:00:00.000000000"), DateValue.of(2007, 12, 23))).isTrue();
    assertThat(Values.areEqual(Timestamp.valueOf("2007-12-23 00:00:00.000000000"), DateValue.of(2007, 1, 2))).isFalse();
    assertThat(Values.areEqual("", DateValue.of(2007, 12, 23))).isFalse();
    assertThat(Values.areEqual(Timestamp.valueOf("2007-12-23 00:00:00.000000000"), (DateValue) null)).isFalse();

    assertThat(Values.areEqual(Timestamp.valueOf("2007-12-23 00:00:00.000000000"), DateValue.of(2007, 12, 2))).isFalse();
    assertThat(Values.areEqual(Timestamp.valueOf("2007-12-23 00:00:00.000000000"), DateValue.of(2007, 1, 23))).isFalse();
    assertThat(Values.areEqual(Timestamp.valueOf("2007-12-23 00:00:00.000000000"), DateValue.of(2006, 12, 23))).isFalse();
  }

}
