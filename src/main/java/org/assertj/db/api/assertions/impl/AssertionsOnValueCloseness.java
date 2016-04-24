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

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import static org.assertj.db.error.ShouldBeClose.shouldBeClose;
import static org.assertj.db.util.Values.areClose;

/**
 * Implements the assertion methods on the closeness of a value.
 *
 * @author Régis Pouiller
 * @see org.assertj.db.api.assertions.AssertOnValueCloseness
 */
public class AssertionsOnValueCloseness {

  /**
   * To notice failures in the assertion.
   */
  private final static Failures failures = Failures.instance();

  /**
   * Private constructor.
   */
  private AssertionsOnValueCloseness() {
    // Empty
  }

  /**
   * Verifies that the value is close to a number.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Writable information about an assertion.
   * @param value     The value.
   * @param expected  The expected number value.
   * @param tolerance The tolerance of the closeness.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not equal to the number in parameter.
   */
  public static <A extends AbstractAssert> A isCloseTo(A assertion, WritableAssertionInfo info, Value value,
                                                       Number expected, Number tolerance) {
    if (expected != null) {
      AssertionsOnValueType.isNumber(assertion, info, value);
    }
    if (areClose(value, expected, tolerance)) {
      return assertion;
    }
    throw failures.failure(info, shouldBeClose(value.getValue(), expected, tolerance));
  }

  /**
   * Verifies that the value is close to a date.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Writable information about an assertion.
   * @param value     The value.
   * @param expected  The expected date value.
   * @param tolerance The tolerance of the closeness.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not equal to the date in parameter.
   */
  public static <A extends AbstractAssert> A isCloseTo(A assertion, WritableAssertionInfo info, Value value,
                                                       DateValue expected, DateValue tolerance) {
    if (expected != null) {
      AssertionsOnValueType.isOfAnyTypeIn(assertion, info, value, ValueType.DATE, ValueType.DATE_TIME);
    }
    if (areClose(value, expected, tolerance)) {
      return assertion;
    }
    Object object = value.getValue();
    if (value.getValueType() == ValueType.DATE) {
      throw failures.failure(info, shouldBeClose(DateValue.from((Date) object), expected, tolerance));
    }
    if (expected != null) {
      throw failures.failure(info, shouldBeClose(DateTimeValue.from((Timestamp) object), DateTimeValue.of(expected), tolerance));
    }
    throw failures.failure(info, shouldBeClose(DateTimeValue.from((Timestamp) object), null, tolerance));
  }

  /**
   * Verifies that the value is close to a date.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Writable information about an assertion.
   * @param value     The value.
   * @param expected  The expected date value.
   * @param tolerance The tolerance of the closeness.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not equal to the date in parameter.
   */
  public static <A extends AbstractAssert> A isCloseTo(A assertion, WritableAssertionInfo info, Value value,
                                                       DateValue expected, TimeValue tolerance) {
    if (expected != null) {
      AssertionsOnValueType.isOfAnyTypeIn(assertion, info, value, ValueType.DATE, ValueType.DATE_TIME);
    }
    if (areClose(value, expected, tolerance)) {
      return assertion;
    }
    Object object = value.getValue();
    if (value.getValueType() == ValueType.DATE) {
      throw failures.failure(info, shouldBeClose(DateValue.from((Date) object), expected, tolerance));
    }
    if (expected != null) {
      throw failures.failure(info, shouldBeClose(DateTimeValue.from((Timestamp) object), DateTimeValue.of(expected), tolerance));
    }
    throw failures.failure(info, shouldBeClose(DateTimeValue.from((Timestamp) object), null, tolerance));
  }

  /**
   * Verifies that the value is close to a date.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Writable information about an assertion.
   * @param value     The value.
   * @param expected  The expected date value.
   * @param tolerance The tolerance of the closeness.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not equal to the date in parameter.
   */
  public static <A extends AbstractAssert> A isCloseTo(A assertion, WritableAssertionInfo info, Value value,
                                                       DateValue expected, DateTimeValue tolerance) {
    if (expected != null) {
      AssertionsOnValueType.isOfAnyTypeIn(assertion, info, value, ValueType.DATE, ValueType.DATE_TIME);
    }
    if (areClose(value, expected, tolerance)) {
      return assertion;
    }
    Object object = value.getValue();
    if (value.getValueType() == ValueType.DATE) {
      throw failures.failure(info, shouldBeClose(DateValue.from((Date) object), expected, tolerance));
    }
    if (expected != null) {
      throw failures.failure(info, shouldBeClose(DateTimeValue.from((Timestamp) object), DateTimeValue.of(expected), tolerance));
    }
    throw failures.failure(info, shouldBeClose(DateTimeValue.from((Timestamp) object), null, tolerance));
  }

  /**
   * Verifies that the value is close to a time.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Writable information about an assertion.
   * @param value     The value.
   * @param expected  The expected time value.
   * @param tolerance The tolerance of the closeness.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not equal to the date in parameter.
   */
  public static <A extends AbstractAssert> A isCloseTo(A assertion, WritableAssertionInfo info, Value value,
                                                       TimeValue expected, TimeValue tolerance) {
    if (expected != null) {
      AssertionsOnValueType.isTime(assertion, info, value);
    }
    if (areClose(value, expected, tolerance)) {
      return assertion;
    }
    Object object = value.getValue();
    throw failures.failure(info, shouldBeClose(TimeValue.from((Time) object), expected, tolerance));
  }

  /**
   * Verifies that the value is close to a date/time.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Writable information about an assertion.
   * @param value     The value.
   * @param expected  The expected date/time value.
   * @param tolerance The tolerance of the closeness.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not equal to the date in parameter.
   */
  public static <A extends AbstractAssert> A isCloseTo(A assertion, WritableAssertionInfo info, Value value,
                                                       DateTimeValue expected, DateValue tolerance) {
    if (expected != null) {
      AssertionsOnValueType.isOfAnyTypeIn(assertion, info, value, ValueType.DATE, ValueType.DATE_TIME);
    }
    if (areClose(value, expected, tolerance)) {
      return assertion;
    }
    Object object = value.getValue();
    if (value.getValueType() == ValueType.DATE) {
      throw failures.failure(info, shouldBeClose(DateTimeValue.of(DateValue.from((Date) object)), expected, tolerance));
    }
    throw failures.failure(info, shouldBeClose(DateTimeValue.from((Timestamp) object), expected, tolerance));
  }

  /**
   * Verifies that the value is close to a date/time.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Writable information about an assertion.
   * @param value     The value.
   * @param expected  The expected date/time value.
   * @param tolerance The tolerance of the closeness.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not equal to the date in parameter.
   */
  public static <A extends AbstractAssert> A isCloseTo(A assertion, WritableAssertionInfo info, Value value,
                                                       DateTimeValue expected, TimeValue tolerance) {
    if (expected != null) {
      AssertionsOnValueType.isOfAnyTypeIn(assertion, info, value, ValueType.DATE, ValueType.DATE_TIME);
    }
    if (areClose(value, expected, tolerance)) {
      return assertion;
    }
    Object object = value.getValue();
    if (value.getValueType() == ValueType.DATE) {
      throw failures.failure(info, shouldBeClose(DateTimeValue.of(DateValue.from((Date) object)), expected, tolerance));
    }
    throw failures.failure(info, shouldBeClose(DateTimeValue.from((Timestamp) object), expected, tolerance));
  }

  /**
   * Verifies that the value is close to a date/time.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Writable information about an assertion.
   * @param value     The value.
   * @param expected  The expected date/time value.
   * @param tolerance The tolerance of the closeness.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not equal to the date in parameter.
   */
  public static <A extends AbstractAssert> A isCloseTo(A assertion, WritableAssertionInfo info, Value value,
                                                       DateTimeValue expected, DateTimeValue tolerance) {
    if (expected != null) {
      AssertionsOnValueType.isOfAnyTypeIn(assertion, info, value, ValueType.DATE, ValueType.DATE_TIME);
    }
    if (areClose(value, expected, tolerance)) {
      return assertion;
    }
    Object object = value.getValue();
    if (value.getValueType() == ValueType.DATE) {
      throw failures.failure(info, shouldBeClose(DateTimeValue.of(DateValue.from((Date) object)), expected, tolerance));
    }
    throw failures.failure(info, shouldBeClose(DateTimeValue.from((Timestamp) object), expected, tolerance));
  }
}
