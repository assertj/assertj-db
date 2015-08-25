/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 * <p>
 * Copyright 2012-2015 the original author or authors.
 */
package org.assertj.db.util;

import org.assertj.db.type.TimeValue;
import org.junit.Test;

import java.sql.Time;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests on {@code areEqual} method for {@code TimeValue}s.
 *
 * @author Régis Pouiller
 *
 */
public class Values_AreEqual_Object_TimeValue_Test {

    /**
     * This method tests the {@code areEqual} method for {@code TimeValue}s.
     */
    @Test
    public void test_are_equal_for_times() {
        assertThat(Values.areEqual(Time.valueOf("09:01:06"), TimeValue.of(9, 1, 6))).isTrue();
        assertThat(Values.areEqual(Time.valueOf("09:01:06"), TimeValue.of(9, 1, 5))).isFalse();
        assertThat(Values.areEqual("", TimeValue.of(9, 1, 6))).isFalse();
        assertThat(Values.areEqual(Time.valueOf("09:01:06"), (TimeValue) null)).isFalse();

        assertThat(Values.areEqual(Time.valueOf("09:01:06"), TimeValue.of(9, 1, 5))).isFalse();
        assertThat(Values.areEqual(Time.valueOf("09:01:06"), TimeValue.of(9, 2, 6))).isFalse();
        assertThat(Values.areEqual(Time.valueOf("09:01:06"), TimeValue.of(10, 1, 6))).isFalse();
        assertThat(Values.areEqual(Time.valueOf("09:01:06"), TimeValue.of(9, 1, 6, 3))).isFalse();
    }

}
