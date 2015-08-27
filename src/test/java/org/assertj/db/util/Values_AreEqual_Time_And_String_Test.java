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

import org.assertj.db.exception.AssertJDBException;
import org.junit.Test;

import java.sql.Time;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests on {@code areEqual} method for {@code Time}s and {@code String}s.
 * 
 * @author Régis Pouiller
 * 
 */
public class Values_AreEqual_Time_And_String_Test {

  /**
   * This method tests the {@code areEqual} method for {@code TimeValue}s.
   */
  @Test
  public void test_are_equal_for_times() {
    assertThat(Values.areEqual(Time.valueOf("09:01:06"), "09:01:06")).isTrue();
    assertThat(Values.areEqual(Time.valueOf("09:01:00"), "09:01:00")).isTrue();
    assertThat(Values.areEqual(Time.valueOf("09:01:00"), "09:01")).isTrue();
    assertThat(Values.areEqual(null, (String) null)).isTrue();

    assertThat(Values.areEqual(Time.valueOf("09:01:06"), "09:01:05")).isFalse();
    assertThat(Values.areEqual(Time.valueOf("09:01:06"), "09:02:06")).isFalse();
    assertThat(Values.areEqual(Time.valueOf("09:01:06"), "10:01:06")).isFalse();
    assertThat(Values.areEqual(Time.valueOf("09:01:06"), "09:01:06.000000003")).isFalse();
    assertThat(Values.areEqual(Time.valueOf("09:01:06"), (String) null)).isFalse();
  }

  /**
   * This method should fail because the expected value ("***") is not parsable to do the comparison.
   */
  @Test(expected = AssertJDBException.class)
  public void should_fail_because_string_is_not_parseable() {
    Values.areEqual(Time.valueOf("09:01:06"), "***");
  }

}
