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

import java.sql.Time;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.TimeValue;
import org.junit.Test;

/**
 * Tests on {@code areClose} method for {@code TimeValue}s.
 *
 * @author Régis Pouiller
 */
public class Values_AreClose_Value_TimeValue_Test extends AbstractTest {

  /**
   * This method tests the {@code areClose} method for {@code TimeValue}s.
   */
  @Test
  public void test_are_close_for_times() throws Exception {
    assertThat(Values.areClose(getValue(null, Time.valueOf("09:01:06")), TimeValue.of(9, 1, 6), TimeValue.of(0,
      0))).isTrue();
    assertThat(Values.areClose(getValue(null, Time.valueOf("09:01:06")), TimeValue.of(9, 1, 5), TimeValue.of(0, 0))).isFalse();
    assertThat(Values.areClose(getValue(null, ""), TimeValue.of(9, 1, 6), TimeValue.of(0, 0))).isFalse();
    assertThat(Values.areClose(getValue(null, Time.valueOf("09:01:06")), (TimeValue) null, TimeValue.of(0, 0))).isFalse();

    assertThat(Values.areClose(getValue(null, Time.valueOf("09:01:06")), TimeValue.of(9, 1, 5), TimeValue.of(0, 0))).isFalse();
    assertThat(Values.areClose(getValue(null, Time.valueOf("09:01:06")), TimeValue.of(9, 2, 6), TimeValue.of(0, 0))).isFalse();
    assertThat(Values.areClose(getValue(null, Time.valueOf("09:01:06")), TimeValue.of(10, 1, 6), TimeValue.of(0, 0))).isFalse();
    assertThat(Values.areClose(getValue(null, Time.valueOf("09:01:06")), TimeValue.of(9, 1, 6, 3), TimeValue.of(0, 0))).isFalse();

    assertThat(Values.areClose(getValue(null, Time.valueOf("09:01:06")), TimeValue.of(9, 1, 5), TimeValue.of(0, 0, 1))).isTrue();
    assertThat(Values.areClose(getValue(null, Time.valueOf("09:01:06")), TimeValue.of(9, 2, 6), TimeValue.of(0, 1))).isTrue();
    assertThat(Values.areClose(getValue(null, Time.valueOf("09:01:06")), TimeValue.of(10, 1, 6), TimeValue.of(1, 0))).isTrue();
    assertThat(Values.areClose(getValue(null, Time.valueOf("09:01:06")), TimeValue.of(9, 1, 6, 3), TimeValue.of(0, 0, 0, 3))).isTrue();
  }
}
