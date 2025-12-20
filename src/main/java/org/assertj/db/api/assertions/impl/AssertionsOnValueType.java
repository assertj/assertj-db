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
 * Copyright 2015-2025 the original author or authors.
 */
package org.assertj.db.api.assertions.impl;

import static org.assertj.db.error.ShouldBeValueType.shouldBeValueType;

import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.core.internal.Failures;
import org.assertj.db.api.AbstractAssert;
import org.assertj.db.error.ShouldBeValueTypeOfAny;
import org.assertj.db.type.Value;
import org.assertj.db.type.ValueType;

/**
 * Implements the assertion methods on the type of a value.
 * <p>The different type of values are enumerated in {@link org.assertj.db.type.ValueType}.</p>
 *
 * @author Régis Pouiller
 * @author Otoniel Isidoro
 * @see org.assertj.db.api.assertions.AssertOnValueType
 */
public class AssertionsOnValueType {

  /**
   * To notice failures in the assertion.
   */
  private static final Failures failures = Failures.instance();

  /**
   * Private constructor.
   */
  private AssertionsOnValueType() {
    // Empty
  }

  /**
   * Verifies that the type of the value is equal to the type in parameter.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Writable information about an assertion.
   * @param value     The value.
   * @param expected  The expected type to compare to.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type of the value is different to the type in parameter.
   */
  public static <A extends AbstractAssert<?>> A isOfType(A assertion, WritableAssertionInfo info, Value value,
                                                         ValueType expected) {
    ValueType type = value.getValueType();
    if (type != expected) {
      throw failures.failure(info, shouldBeValueType(value, type, expected));
    }
    return assertion;
  }

  /**
   * Verifies that the type of the value is equal to one of the types in parameters.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Writable information about an assertion.
   * @param value     The value.
   * @param expected  The expected types to compare to.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type of the value is different to all the types in parameters.
   */
  public static <A extends AbstractAssert<?>> A isOfAnyTypeIn(A assertion, WritableAssertionInfo info, Value value,
                                                              ValueType... expected) {
    ValueType type = value.getValueType();
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
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Writable information about an assertion.
   * @param value     The value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type of the value is not number.
   */
  public static <A extends AbstractAssert<?>> A isNumber(A assertion, WritableAssertionInfo info, Value value) {
    return isOfType(assertion, info, value, ValueType.NUMBER);
  }

  /**
   * Verifies that the value is a boolean.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Writable information about an assertion.
   * @param value     The value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type of the value is not boolean.
   */
  public static <A extends AbstractAssert<?>> A isBoolean(A assertion, WritableAssertionInfo info, Value value) {
    return isOfType(assertion, info, value, ValueType.BOOLEAN);
  }

  /**
   * Verifies that the value is a date.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Writable information about an assertion.
   * @param value     The value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type of the value is not date.
   */
  public static <A extends AbstractAssert<?>> A isDate(A assertion, WritableAssertionInfo info, Value value) {
    return isOfType(assertion, info, value, ValueType.DATE);
  }

  /**
   * Verifies that the value is a time.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Writable information about an assertion.
   * @param value     The value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type of the value is not time.
   */
  public static <A extends AbstractAssert<?>> A isTime(A assertion, WritableAssertionInfo info, Value value) {
    return isOfType(assertion, info, value, ValueType.TIME);
  }

  /**
   * Verifies that the value is a date/time.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Writable information about an assertion.
   * @param value     The value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type of the value is not date/time.
   */
  public static <A extends AbstractAssert<?>> A isDateTime(A assertion, WritableAssertionInfo info, Value value) {
    return isOfType(assertion, info, value, ValueType.DATE_TIME);
  }

  /**
   * Verifies that the value is a array of bytes.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Writable information about an assertion.
   * @param value     The value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type of the value is not array of bytes.
   */
  public static <A extends AbstractAssert<?>> A isBytes(A assertion, WritableAssertionInfo info, Value value) {
    return isOfType(assertion, info, value, ValueType.BYTES);
  }

  /**
   * Verifies that the value is a text.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Writable information about an assertion.
   * @param value     The value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type of the value is not text.
   */
  public static <A extends AbstractAssert<?>> A isText(A assertion, WritableAssertionInfo info, Value value) {
    return isOfType(assertion, info, value, ValueType.TEXT);
  }

  /**
   * Verifies that the value is an UUID.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Writable information about an assertion.
   * @param value     The value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type of the value is not UUID.
   * @since 1.1.0
   */
  public static <A extends AbstractAssert<?>> A isUUID(A assertion, WritableAssertionInfo info, Value value) {
    return isOfType(assertion, info, value, ValueType.UUID);
  }

}
