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

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests on {@code areEqual} method for {@code String}s.
 *
 * @author Régis Pouiller
 *
 */
public class Values_AreEqual_Object_And_String_Test {

    /**
     * This method tests the {@code areEqual} method for {@code String}s.
     */
    @Test
    public void test_are_equal_for_string() {
        assertThat(Values.areEqual("text", "text")).isTrue();
        assertThat(Values.areEqual("Text", "text")).isFalse();
    }

}
