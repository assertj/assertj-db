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

import org.assertj.db.api.assertions.impl.AssertionsOnValueComparison;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test on the utility class {@code AssertionsOnValue} : the private constructor.
 *
 * @author Régis Pouiller
 */
public class AssertionsOnValue_Constructor_Test {

    /**
     * This method tests the private constructor of {@code AssertionsOnValue} for the tests coverage..
     *
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws java.lang.reflect.InvocationTargetException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws IllegalArgumentException
     */
    @Test
    public void test_private_constructor_for_the_tests_coverage()
        throws SecurityException, NoSuchMethodException, IllegalArgumentException, InstantiationException,
        IllegalAccessException, InvocationTargetException {

        Constructor<AssertionsOnValueComparison> constructor = AssertionsOnValueComparison.class
            .getDeclaredConstructor();
        assertThat(Modifier.isPrivate(constructor.getModifiers())).isTrue();
        constructor.setAccessible(true);
        constructor.newInstance();
        constructor.setAccessible(false);
    }
}
