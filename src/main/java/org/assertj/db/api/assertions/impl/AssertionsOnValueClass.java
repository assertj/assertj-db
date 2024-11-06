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
package org.assertj.db.api.assertions.impl;

import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.core.internal.Failures;
import org.assertj.db.api.AbstractAssert;
import org.assertj.db.exception.AssertJDBException;
import org.assertj.db.type.Value;

import static org.assertj.db.error.ShouldBeValueClass.shouldBeValueClass;

/**
 * Implements the assertion method on the class of a value.
 *
 * @author Régis Pouiller
 * @see org.assertj.db.api.assertions.AssertOnValueClass
 * @since 1.1.0
 */
public class AssertionsOnValueClass {

  /**
   * To notice failures in the assertion.
   */
  private static final Failures failures = Failures.instance();

  /**
   * Private constructor.
   */
  private AssertionsOnValueClass() {
    // Empty
  }

  /**
   * Verifies that the class of the value is equal to the class in parameter.
   *
   * @param <A>          The type of the assertion which call this method.
   * @param assertion    The assertion which call this method.
   * @param info         Writable information about an assertion.
   * @param value        The value.
   * @param classOfValue The expected class to compare to.
   * @return {@code this} assertion object.
   * @throws AssertionError     If the class of the value is different to the class in parameter.
   * @throws AssertJDBException If the class is {@code null}.
   * @since 1.1.0
   */
  public static <A extends AbstractAssert<?>> A isOfClass(A assertion, WritableAssertionInfo info, Value value,
                                                       Class<?> classOfValue) {

    if (value.getValue() == null) {
      throw failures.failure(info, shouldBeValueClass(value, classOfValue));
    }
    if (classOfValue == null) {
      throw new AssertJDBException("Class of the value is null");
    }
    Class<?> testedClass = value.getValue().getClass();
    if (!classOfValue.isAssignableFrom(testedClass)) {
      throw failures.failure(info, shouldBeValueClass(value, classOfValue));
    }
    return assertion;
  }
}
