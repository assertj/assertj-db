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
package org.assertj.db.api.assertions.impl.constructor;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import org.assertj.db.api.assertions.impl.AssertionsOnValueInequality;
import org.junit.Test;

/**
 * Tests on {@link AssertionsOnValueInequality} class :
 * {@link AssertionsOnValueInequality#AssertionsOnValueNonEquality()} private constructor.
 *
 * @author Régis Pouiller
 */
public class AssertionsOnValueNonEquality_Constructor_Test {

  /**
   * This method tests the private constructor of {@code AssertionsOnValueNonEquality} for the tests coverage..
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
    throws SecurityException, NoSuchMethodException, IllegalArgumentException,
    InstantiationException, IllegalAccessException, InvocationTargetException {

    Constructor<AssertionsOnValueInequality> constructor = AssertionsOnValueInequality.class.getDeclaredConstructor();
    assertThat(Modifier.isPrivate(constructor.getModifiers())).isTrue();
    constructor.setAccessible(true);
    constructor.newInstance();
    constructor.setAccessible(false);
  }
}
