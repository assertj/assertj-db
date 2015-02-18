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

import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.core.internal.Failures;
import org.assertj.db.api.AbstractAssert;
import org.assertj.db.error.ShouldBeValueTypeOfAny;
import org.assertj.db.type.ValueType;

import static org.assertj.db.error.ShouldBeValueType.shouldBeValueType;

/**
 * Utility methods related to assert.
 *
 * @author Régis Pouiller
 */
public class Assert {

  /**
   * To notice failures in the assertion.
   */
  private final static Failures failures = Failures.instance();

  /**
   * Private constructor.
   */
  private Assert() {
    // Empty
  }

  /**
   * Returns the type of the value (text, boolean, number, date, ...).
   *
   * @param value The value.
   * @return The type of the value.
   */
  private static ValueType getType(Object value) {
    return ValueType.getType(value);
  }

  /**
   * Verifies that the type of a value is equal to the type in parameter.
   *
   * @param assertion The assertion which call this method.
   * @param info      Info on the object to assert.
   * @param value     The value.
   * @param expected  The expected type to compare to.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type is different to the type in parameter.
   */
  public static <A extends AbstractAssert> A isOfType(A assertion, WritableAssertionInfo info, Object value,
                                                      ValueType expected) {
    ValueType type = getType(value);
    if (type != expected) {
      throw failures.failure(info, shouldBeValueType(value, expected, type));
    }
    return assertion;
  }

  /**
   * Verifies that the type of the value is equal to one of the types in parameters.
   *
   * @param assertion The assertion which call this method.
   * @param info      Info on the object to assert.
   * @param value     The value.
   * @param expected The expected types to compare to.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type is different to all the types in parameters.
   */
  public static <A extends AbstractAssert> A isOfAnyOfTypes(A assertion, WritableAssertionInfo info, Object value, ValueType... expected) {
    ValueType type = getType(value);
    for (ValueType valueType : expected) {
      if (type == valueType) {
        return assertion;
      }
    }
    throw failures.failure(info, ShouldBeValueTypeOfAny.shouldBeValueTypeOfAny(value, type, expected));
  }

  /**
   * Verifies that the value is a number.
   *
   * @param assertion The assertion which call this method.
   * @param info      Info on the object to assert.
   * @param value     The value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type is not a number.
   */
  public static <A extends AbstractAssert> A isNumber(A assertion, WritableAssertionInfo info, Object value) {
    return isOfType(assertion, info, value, ValueType.NUMBER);
  }
}
