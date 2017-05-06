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
 * Copyright 2012-2016 the original author or authors.
 */
package org.assertj.db.api.assertions.impl;

import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.core.internal.Failures;
import org.assertj.db.api.AbstractAssert;
import org.assertj.db.type.*;
import org.assertj.db.util.Values;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.UUID;

import static org.assertj.db.error.ShouldNotBeEqual.shouldNotBeEqual;
import static org.assertj.db.util.Values.areEqual;

/**
 * Implements the assertion methods on the non equality of a value.
 *
 * @author Régis Pouiller
 * @author Otoniel Isidoro
 * @see org.assertj.db.api.assertions.AssertOnValueInequality
 */
public class AssertionsOnValueInequality {

  /**
   * To notice failures in the assertion.
   */
  private final static Failures failures = Failures.instance();

  /**
   * Private constructor.
   */
  private AssertionsOnValueInequality() {
    // Empty
  }

  /**
   * Verifies that the value is not equal to a boolean.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Writable information about an assertion.
   * @param value     The value.
   * @param expected  The expected object value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is equal to the object in parameter.
   */
  public static <A extends AbstractAssert<?>> A isNotEqualTo(A assertion, WritableAssertionInfo info, Value value,
                                                          Object expected) {
    if (value.getValue() != null && expected != null) {
      AssertionsOnValueClass.isOfClass(assertion, info, value, expected.getClass());
    }
    if (!areEqual(value, expected)) {
      return assertion;
    }
    throw failures.failure(info, shouldNotBeEqual(value.getValue(), expected));
  }

  /**
   * Verifies that the value is not equal to a boolean.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Writable information about an assertion.
   * @param value     The value.
   * @param expected  The expected boolean value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is equal to the boolean in parameter.
   */
  public static <A extends AbstractAssert<?>> A isNotEqualTo(A assertion, WritableAssertionInfo info, Value value,
                                                          Boolean expected) {
    if (expected != null) {
      AssertionsOnValueType.isBoolean(assertion, info, value);
    }
    if (!areEqual(value, expected)) {
      return assertion;
    }
    throw failures.failure(info, shouldNotBeEqual(value.getValue(), expected));
  }

  /**
   * Verifies that the value is not equal to a array of bytes.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Writable information about an assertion.
   * @param value     The value.
   * @param expected  The expected array of bytes value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is equal to the array of bytes in parameter.
   */
  public static <A extends AbstractAssert<?>> A isNotEqualTo(A assertion, WritableAssertionInfo info, Value value,
                                                          byte[] expected) {
    if (expected != null) {
      AssertionsOnValueType.isBytes(assertion, info, value);
    }
    if (!areEqual(value, expected)) {
      return assertion;
    }
    throw failures.failure(info, shouldNotBeEqual());
  }

  /**
   * Verifies that the value is not equal to a date/time value.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Writable information about an assertion.
   * @param value     The value.
   * @param expected  The expected date/time value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is equal to the date/time value in parameter.
   */
  public static <A extends AbstractAssert<?>> A isNotEqualTo(A assertion, WritableAssertionInfo info, Value value,
                                                          DateTimeValue expected) {
    if (expected != null){
      AssertionsOnValueType.isOfAnyTypeIn(assertion, info, value, ValueType.DATE, ValueType.DATE_TIME);
    }
    if (!areEqual(value, expected)) {
      return assertion;
    }
    Object object = value.getValue();
    if (value.getValueType() == ValueType.DATE) {
      throw failures.failure(info, shouldNotBeEqual(DateTimeValue.of(DateValue.from((Date) object)), expected));
    }
    if (object != null) {
      throw failures.failure(info, shouldNotBeEqual(DateTimeValue.from((Timestamp) object), expected));
    }
    throw failures.failure(info, shouldNotBeEqual(null, null));
  }

  /**
   * Verifies that the value is not equal to a date value.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Writable information about an assertion.
   * @param value     The value.
   * @param expected  The expected date value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is equal to the date value in parameter.
   */
  public static <A extends AbstractAssert<?>> A isNotEqualTo(A assertion, WritableAssertionInfo info, Value value,
                                                          DateValue expected) {
    if (expected != null) {
      AssertionsOnValueType.isOfAnyTypeIn(assertion, info, value, ValueType.DATE, ValueType.DATE_TIME);
    }
    if (!areEqual(value, expected)) {
      return assertion;
    }
    Object object = value.getValue();
    if (value.getValueType() == ValueType.DATE) {
      throw failures.failure(info, shouldNotBeEqual(DateValue.from((Date) object), expected));
    }
    if (object != null) {
      throw failures.failure(info, shouldNotBeEqual(DateTimeValue.from((Timestamp) object), DateTimeValue.of(expected)));
    }
    throw failures.failure(info, shouldNotBeEqual(null, expected));
  }

  /**
   * Verifies that the value is not equal to a number.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Writable information about an assertion.
   * @param value     The value.
   * @param expected  The expected number value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is equal to the number in parameter.
   */
  public static <A extends AbstractAssert<?>> A isNotEqualTo(A assertion, WritableAssertionInfo info, Value value,
                                                          Number expected) {
    if (expected != null) {
      AssertionsOnValueType.isNumber(assertion, info, value);
    }
    if (!areEqual(value, expected)) {
      return assertion;
    }
    throw failures.failure(info, shouldNotBeEqual(value.getValue(), expected));
  }

  /**
   * Verifies that the value is not equal to a text.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Writable information about an assertion.
   * @param value     The value.
   * @param expected  The expected text value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is equal to the text in parameter.
   */
  public static <A extends AbstractAssert<?>> A isNotEqualTo(A assertion, WritableAssertionInfo info, Value value,
                                                          String expected) {

    if (expected != null) {
      AssertionsOnValueType
              .isOfAnyTypeIn(assertion, info, value, ValueType.TEXT, ValueType.NUMBER, ValueType.DATE, ValueType.TIME,
                             ValueType.DATE_TIME, ValueType.UUID);
    }
    if (!areEqual(value, expected)) {
      return assertion;
    }
    throw failures.failure(info, shouldNotBeEqual(
            Values.getRepresentationFromValueInFrontOfExpected(value, expected),
                                                  expected));
  }

  /**
   * Verifies that the value is not equal to an UUID.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Writable information about an assertion.
   * @param value     The value.
   * @param expected  The expected UUID value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is equal to the UUID in parameter.
   * @since 1.1.0
   */
  public static <A extends AbstractAssert<?>> A isNotEqualTo(A assertion, WritableAssertionInfo info, Value value,
                                                          UUID expected) {

    if (expected != null) {
      AssertionsOnValueType.isUUID(assertion, info, value);
    }
    if (!areEqual(value, expected)) {
      return assertion;
    }
    throw failures.failure(info, shouldNotBeEqual(
            Values.getRepresentationFromValueInFrontOfExpected(value, expected),
            expected));
  }

  /**
   * Verifies that the value is not equal to a time value.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Writable information about an assertion.
   * @param value     The value.
   * @param expected  The expected time value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is equal to the time value in parameter.
   */
  public static <A extends AbstractAssert<?>> A isNotEqualTo(A assertion, WritableAssertionInfo info, Value value,
                                                          TimeValue expected) {
    if (expected != null) {
      AssertionsOnValueType.isTime(assertion, info, value);
    }
    if (!areEqual(value, expected)) {
      return assertion;
    }
    Object object = value.getValue();
    if (object != null) {
      throw failures.failure(info, shouldNotBeEqual(TimeValue.from((Time) object), expected));
    }
    throw failures.failure(info, shouldNotBeEqual(null, null));
  }

  /**
   * Verifies that the value is not equal to zero.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Writable information about an assertion.
   * @param value     The value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is equal to zero.
   */
  public static <A extends AbstractAssert<?>> A isNotZero(A assertion, WritableAssertionInfo info, Value value) {
    return isNotEqualTo(assertion, info, value, 0);
  }
}
