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
 * Copyright 2012-2015 the original author or authors.
 */
package org.assertj.db.api.assertions.impl;

import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.core.internal.Failures;
import org.assertj.db.api.AbstractAssert;
import org.assertj.db.type.*;
import org.assertj.db.util.Values;

import java.util.UUID;

import static org.assertj.db.error.ShouldBeEqualWithEndPoint.shouldBeEqualWithEndPoint;
import static org.assertj.db.error.ShouldBeEqualWithStartPoint.shouldBeEqualWithStartPoint;
import static org.assertj.db.util.Values.areEqual;

/**
 * Implements the assertion methods on the equality of a column of a change.
 *
 * @author Régis Pouiller
 * @author Otoniel Isidoro
 * @see org.assertj.db.api.assertions.AssertOnColumnOfChangeEquality
 */
public class AssertionsOnColumnOfChangeEquality {

  /**
   * To notice failures in the assertion.
   */
  private final static Failures failures = Failures.instance();

  /**
   * Private constructor.
   */
  private AssertionsOnColumnOfChangeEquality() {
    // Empty
  }

  /**
   * Verifies that the values at the start point and the end point are equal to a object.
   *
   * @param <A>               The type of the assertion which call this method.
   * @param assertion         The assertion which call this method.
   * @param info              Writable information about an assertion.
   * @param valueAtStartPoint The value at start point.
   * @param valueAtEndPoint   The value at end point.
   * @param expected          The object value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the values at start point and at end point are not equal to the object.
   */
  public static <A extends AbstractAssert> A hasValues(A assertion, WritableAssertionInfo info,
                                                       Value valueAtStartPoint, Value valueAtEndPoint,
                                                       Object expected) {

    if (expected != null) {
      AssertionsOnColumnOfChangeClass.isOfClass(assertion, info, valueAtStartPoint, valueAtEndPoint, expected.getClass(), true);
    }
    if (!areEqual(valueAtStartPoint, expected)) {
      throw failures.failure(info, shouldBeEqualWithStartPoint(valueAtStartPoint.getValue(), expected));
    }
    if (!areEqual(valueAtEndPoint, expected)) {
      throw failures.failure(info, shouldBeEqualWithEndPoint(valueAtEndPoint.getValue(), expected));
    }
    return assertion;
  }

  /**
   * Verifies that the values at the start point and the end point are equal to a boolean for start point and another object for end point.
   *
   * @param <A>                  The type of the assertion which call this method.
   * @param assertion            The assertion which call this method.
   * @param info                 Writable information about an assertion.
   * @param valueAtStartPoint    The value at start point.
   * @param valueAtEndPoint      The value at end point.
   * @param expectedAtStartPoint The expected object at start point.
   * @param expectedAtEndPoint   The expected object at end point.
   * @return {@code this} assertion object.
   * @throws AssertionError If the values at start point and at end point are not equal to the corresponding objects.
   */
  public static <A extends AbstractAssert> A hasValues(A assertion, WritableAssertionInfo info,
                                                       Value valueAtStartPoint, Value valueAtEndPoint,
                                                       Object expectedAtStartPoint, Object expectedAtEndPoint) {

    if (expectedAtStartPoint != null) {
      AssertionsOnColumnOfChangeClass.isOfClass(assertion, info, valueAtStartPoint, Value.NULL, expectedAtStartPoint.getClass(), true);
    }
    if (expectedAtEndPoint != null) {
      AssertionsOnColumnOfChangeClass.isOfClass(assertion, info, Value.NULL, valueAtEndPoint, expectedAtEndPoint.getClass(), true);
    }
    if (!areEqual(valueAtStartPoint, expectedAtStartPoint)) {
      throw failures.failure(info, shouldBeEqualWithStartPoint(valueAtStartPoint.getValue(), expectedAtStartPoint));
    }
    if (!areEqual(valueAtEndPoint, expectedAtEndPoint)) {
      throw failures.failure(info, shouldBeEqualWithEndPoint(valueAtEndPoint.getValue(), expectedAtEndPoint));
    }
    return assertion;
  }

  /**
   * Verifies that the values at the start point and the end point are equal to a boolean.
   *
   * @param <A>               The type of the assertion which call this method.
   * @param assertion         The assertion which call this method.
   * @param info              Writable information about an assertion.
   * @param valueAtStartPoint The value at start point.
   * @param valueAtEndPoint   The value at end point.
   * @param expected          The boolean value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the values at start point and at end point are not equal to the boolean.
   */
  public static <A extends AbstractAssert> A hasValues(A assertion, WritableAssertionInfo info,
                                                       Value valueAtStartPoint, Value valueAtEndPoint,
                                                       Boolean expected) {

    AssertionsOnColumnOfChangeType.isBoolean(assertion, info, valueAtStartPoint, valueAtEndPoint, true);
    if (!areEqual(valueAtStartPoint, expected)) {
      throw failures.failure(info, shouldBeEqualWithStartPoint(valueAtStartPoint.getValue(), expected));
    }
    if (!areEqual(valueAtEndPoint, expected)) {
      throw failures.failure(info, shouldBeEqualWithEndPoint(valueAtEndPoint.getValue(), expected));
    }
    return assertion;
  }

  /**
   * Verifies that the values at the start point and the end point are equal to a boolean for start point and another boolean for end point.
   *
   * @param <A>                  The type of the assertion which call this method.
   * @param assertion            The assertion which call this method.
   * @param info                 Writable information about an assertion.
   * @param valueAtStartPoint    The value at start point.
   * @param valueAtEndPoint      The value at end point.
   * @param expectedAtStartPoint The expected boolean at start point.
   * @param expectedAtEndPoint   The expected boolean at end point.
   * @return {@code this} assertion object.
   * @throws AssertionError If the values at start point and at end point are not equal to the corresponding booleans.
   */
  public static <A extends AbstractAssert> A hasValues(A assertion, WritableAssertionInfo info,
                                                       Value valueAtStartPoint, Value valueAtEndPoint,
                                                       Boolean expectedAtStartPoint, Boolean expectedAtEndPoint) {

    AssertionsOnColumnOfChangeType.isBoolean(assertion, info, valueAtStartPoint, valueAtEndPoint, true);
    if (!areEqual(valueAtStartPoint, expectedAtStartPoint)) {
      throw failures.failure(info, shouldBeEqualWithStartPoint(valueAtStartPoint.getValue(), expectedAtStartPoint));
    }
    if (!areEqual(valueAtEndPoint, expectedAtEndPoint)) {
      throw failures.failure(info, shouldBeEqualWithEndPoint(valueAtEndPoint.getValue(), expectedAtEndPoint));
    }
    return assertion;
  }

  /**
   * Verifies that the values at the start point and the end point are equal to a number.
   *
   * @param <A>               The type of the assertion which call this method.
   * @param assertion         The assertion which call this method.
   * @param info              Writable information about an assertion.
   * @param valueAtStartPoint The value at start point.
   * @param valueAtEndPoint   The value at end point.
   * @param expected          The number value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the values at start point and at end point are not equal to the number.
   */
  public static <A extends AbstractAssert> A hasValues(A assertion, WritableAssertionInfo info,
                                                       Value valueAtStartPoint, Value valueAtEndPoint,
                                                       Number expected) {

    AssertionsOnColumnOfChangeType.isNumber(assertion, info, valueAtStartPoint, valueAtEndPoint, true);
    if (!areEqual(valueAtStartPoint, expected)) {
      throw failures.failure(info, shouldBeEqualWithStartPoint(
              Values.getRepresentationFromValueInFrontOfExpected(valueAtStartPoint, expected), expected));
    }
    if (!areEqual(valueAtEndPoint, expected)) {
      throw failures.failure(info, shouldBeEqualWithEndPoint(
              Values.getRepresentationFromValueInFrontOfExpected(valueAtEndPoint, expected), expected));
    }
    return assertion;
  }

  /**
   * Verifies that the values at the start point and the end point are equal to a number for start point and another number for end point.
   *
   * @param <A>                  The type of the assertion which call this method.
   * @param assertion            The assertion which call this method.
   * @param info                 Writable information about an assertion.
   * @param valueAtStartPoint    The value at start point.
   * @param valueAtEndPoint      The value at end point.
   * @param expectedAtStartPoint The expected number at start point.
   * @param expectedAtEndPoint   The expected number at end point.
   * @return {@code this} assertion object.
   * @throws AssertionError If the values at start point and at end point are not equal to the corresponding numbers.
   */
  public static <A extends AbstractAssert> A hasValues(A assertion, WritableAssertionInfo info,
                                                       Value valueAtStartPoint, Value valueAtEndPoint,
                                                       Number expectedAtStartPoint, Number expectedAtEndPoint) {

    AssertionsOnColumnOfChangeType.isNumber(assertion, info, valueAtStartPoint, valueAtEndPoint, true);
    if (!areEqual(valueAtStartPoint, expectedAtStartPoint)) {
      throw failures.failure(info, shouldBeEqualWithStartPoint(
              Values.getRepresentationFromValueInFrontOfExpected(valueAtStartPoint, expectedAtStartPoint), expectedAtStartPoint));
    }
    if (!areEqual(valueAtEndPoint, expectedAtEndPoint)) {
      throw failures.failure(info, shouldBeEqualWithEndPoint(
              Values.getRepresentationFromValueInFrontOfExpected(valueAtEndPoint, expectedAtEndPoint), expectedAtEndPoint));
    }
    return assertion;
  }

  /**
   * Verifies that the values at the start point and the end point are equal to bytes.
   *
   * @param <A>               The type of the assertion which call this method.
   * @param assertion         The assertion which call this method.
   * @param info              Writable information about an assertion.
   * @param valueAtStartPoint The value at start point.
   * @param valueAtEndPoint   The value at end point.
   * @param expected          The bytes value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the values at start point and at end point are not equal to the bytes.
   */
  public static <A extends AbstractAssert> A hasValues(A assertion, WritableAssertionInfo info,
                                                       Value valueAtStartPoint, Value valueAtEndPoint,
                                                       byte[] expected) {

    AssertionsOnColumnOfChangeType.isBytes(assertion, info, valueAtStartPoint, valueAtEndPoint, true);
    if (!areEqual(valueAtStartPoint, expected)) {
      throw failures.failure(info, shouldBeEqualWithStartPoint());
    }
    if (!areEqual(valueAtEndPoint, expected)) {
      throw failures.failure(info, shouldBeEqualWithEndPoint());
    }
    return assertion;
  }

  /**
   * Verifies that the values at the start point and the end point are equal to bytes for start point and other bytes for end point.
   *
   * @param <A>                  The type of the assertion which call this method.
   * @param assertion            The assertion which call this method.
   * @param info                 Writable information about an assertion.
   * @param valueAtStartPoint    The value at start point.
   * @param valueAtEndPoint      The value at end point.
   * @param expectedAtStartPoint The expected bytes at start point.
   * @param expectedAtEndPoint   The expected bytes at end point.
   * @return {@code this} assertion object.
   * @throws AssertionError If the values at start point and at end point are not equal to the corresponding bytes.
   */
  public static <A extends AbstractAssert> A hasValues(A assertion, WritableAssertionInfo info,
                                                       Value valueAtStartPoint, Value valueAtEndPoint,
                                                       byte[] expectedAtStartPoint, byte[] expectedAtEndPoint) {

    AssertionsOnColumnOfChangeType.isBytes(assertion, info, valueAtStartPoint, valueAtEndPoint, true);
    if (!areEqual(valueAtStartPoint, expectedAtStartPoint)) {
      throw failures.failure(info, shouldBeEqualWithStartPoint());
    }
    if (!areEqual(valueAtEndPoint, expectedAtEndPoint)) {
      throw failures.failure(info, shouldBeEqualWithEndPoint());
    }
    return assertion;
  }

  /**
   * Verifies that the values at the start point and the end point are equal to a text.
   *
   * @param <A>               The type of the assertion which call this method.
   * @param assertion         The assertion which call this method.
   * @param info              Writable information about an assertion.
   * @param valueAtStartPoint The value at start point.
   * @param valueAtEndPoint   The value at end point.
   * @param expected          The text value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the values at start point and at end point are not equal to the text.
   */
  public static <A extends AbstractAssert> A hasValues(A assertion, WritableAssertionInfo info,
                                                       Value valueAtStartPoint, Value valueAtEndPoint,
                                                       String expected) {

    AssertionsOnColumnOfChangeType.isOfAnyTypeIn(assertion, info, valueAtStartPoint, valueAtEndPoint,
                                                 ValueType.TEXT, ValueType.NUMBER, ValueType.DATE,
                                                 ValueType.TIME, ValueType.DATE_TIME, ValueType.UUID, ValueType.NOT_IDENTIFIED);
    if (!areEqual(valueAtStartPoint, expected)) {
      throw failures.failure(info, shouldBeEqualWithStartPoint(
              Values.getRepresentationFromValueInFrontOfExpected(valueAtStartPoint, expected), expected));
    }
    if (!areEqual(valueAtEndPoint, expected)) {
      throw failures.failure(info, shouldBeEqualWithEndPoint(
              Values.getRepresentationFromValueInFrontOfExpected(valueAtEndPoint, expected), expected));
    }
    return assertion;
  }

  /**
   * Verifies that the values at the start point and the end point are equal to a text for start point and another text for end point.
   *
   * @param <A>                  The type of the assertion which call this method.
   * @param assertion            The assertion which call this method.
   * @param info                 Writable information about an assertion.
   * @param valueAtStartPoint    The value at start point.
   * @param valueAtEndPoint      The value at end point.
   * @param expectedAtStartPoint The expected text at start point.
   * @param expectedAtEndPoint   The expected text at end point.
   * @return {@code this} assertion object.
   * @throws AssertionError If the values at start point and at end point are not equal to the corresponding texts.
   */
  public static <A extends AbstractAssert> A hasValues(A assertion, WritableAssertionInfo info,
                                                       Value valueAtStartPoint, Value valueAtEndPoint,
                                                       String expectedAtStartPoint, String expectedAtEndPoint) {

    AssertionsOnColumnOfChangeType.isOfAnyTypeIn(assertion, info, valueAtStartPoint, valueAtEndPoint,
                                                 ValueType.TEXT, ValueType.NUMBER, ValueType.DATE,
                                                 ValueType.TIME, ValueType.DATE_TIME, ValueType.UUID, ValueType.NOT_IDENTIFIED);
    if (!areEqual(valueAtStartPoint, expectedAtStartPoint)) {
      throw failures.failure(info, shouldBeEqualWithStartPoint(
              Values.getRepresentationFromValueInFrontOfExpected(valueAtStartPoint, expectedAtStartPoint), expectedAtStartPoint));
    }
    if (!areEqual(valueAtEndPoint, expectedAtEndPoint)) {
      throw failures.failure(info, shouldBeEqualWithEndPoint(
              Values.getRepresentationFromValueInFrontOfExpected(valueAtEndPoint, expectedAtEndPoint), expectedAtEndPoint));
    }
    return assertion;
  }

  /**
   * Verifies that the values at the start point and the end point are equal to a date.
   *
   * @param <A>               The type of the assertion which call this method.
   * @param assertion         The assertion which call this method.
   * @param info              Writable information about an assertion.
   * @param valueAtStartPoint The value at start point.
   * @param valueAtEndPoint   The value at end point.
   * @param expected          The date value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the values at start point and at end point are not equal to the date.
   */
  public static <A extends AbstractAssert> A hasValues(A assertion, WritableAssertionInfo info,
                                                       Value valueAtStartPoint, Value valueAtEndPoint,
                                                       DateValue expected) {
    AssertionsOnColumnOfChangeType
        .isOfAnyTypeIn(assertion, info, valueAtStartPoint, valueAtEndPoint, ValueType.DATE,
                       ValueType.DATE_TIME, ValueType.NOT_IDENTIFIED);
    if (!areEqual(valueAtStartPoint, expected)) {
      throw failures.failure(info, shouldBeEqualWithStartPoint(
          Values.getRepresentationFromValueInFrontOfExpected(valueAtStartPoint, expected), expected));
    }
    if (!areEqual(valueAtEndPoint, expected)) {
      throw failures.failure(info, shouldBeEqualWithEndPoint(
          Values.getRepresentationFromValueInFrontOfExpected(valueAtEndPoint, expected), expected));
    }
    return assertion;
  }

  /**
   * Verifies that the values at the start point and the end point are equal to a date for start point and another date for end point.
   *
   * @param <A>                  The type of the assertion which call this method.
   * @param assertion            The assertion which call this method.
   * @param info                 Writable information about an assertion.
   * @param valueAtStartPoint    The value at start point.
   * @param valueAtEndPoint      The value at end point.
   * @param expectedAtStartPoint The expected date at start point.
   * @param expectedAtEndPoint   The expected date at end point.
   * @return {@code this} assertion object.
   * @throws AssertionError If the values at start point and at end point are not equal to the corresponding dates.
   */
  public static <A extends AbstractAssert> A hasValues(A assertion, WritableAssertionInfo info,
                                                       Value valueAtStartPoint, Value valueAtEndPoint,
                                                       DateValue expectedAtStartPoint, DateValue expectedAtEndPoint) {

    AssertionsOnColumnOfChangeType.isOfAnyTypeIn(assertion, info, valueAtStartPoint, valueAtEndPoint, ValueType.DATE,
                                                 ValueType.DATE_TIME, ValueType.NOT_IDENTIFIED);
    if (!areEqual(valueAtStartPoint, expectedAtStartPoint)) {
      throw failures.failure(info, shouldBeEqualWithStartPoint(
              Values.getRepresentationFromValueInFrontOfExpected(valueAtStartPoint, expectedAtStartPoint), expectedAtStartPoint));
    }
    if (!areEqual(valueAtEndPoint, expectedAtEndPoint)) {
      throw failures.failure(info, shouldBeEqualWithEndPoint(
              Values.getRepresentationFromValueInFrontOfExpected(valueAtEndPoint, expectedAtEndPoint), expectedAtEndPoint));
    }
    return assertion;
  }

  /**
   * Verifies that the values at the start point and the end point are equal to a time.
   *
   * @param <A>               The type of the assertion which call this method.
   * @param assertion         The assertion which call this method.
   * @param info              Writable information about an assertion.
   * @param valueAtStartPoint The value at start point.
   * @param valueAtEndPoint   The value at end point.
   * @param expected          The time value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the values at start point and at end point are not equal to the time.
   */
  public static <A extends AbstractAssert> A hasValues(A assertion, WritableAssertionInfo info,
                                                       Value valueAtStartPoint, Value valueAtEndPoint,
                                                       TimeValue expected) {

    AssertionsOnColumnOfChangeType.isOfAnyTypeIn(assertion, info, valueAtStartPoint, valueAtEndPoint, ValueType.TIME,
                                                 ValueType.NOT_IDENTIFIED);
    if (!areEqual(valueAtStartPoint, expected)) {
      throw failures.failure(info, shouldBeEqualWithStartPoint(
              Values.getRepresentationFromValueInFrontOfExpected(valueAtStartPoint, expected), expected));
    }
    if (!areEqual(valueAtEndPoint, expected)) {
      throw failures.failure(info, shouldBeEqualWithEndPoint(
              Values.getRepresentationFromValueInFrontOfExpected(valueAtEndPoint, expected), expected));
    }
    return assertion;
  }

  /**
   * Verifies that the values at the start point and the end point are equal to a time for start point and another time for end point.
   *
   * @param <A>                  The type of the assertion which call this method.
   * @param assertion            The assertion which call this method.
   * @param info                 Writable information about an assertion.
   * @param valueAtStartPoint    The value at start point.
   * @param valueAtEndPoint      The value at end point.
   * @param expectedAtStartPoint The expected time at start point.
   * @param expectedAtEndPoint   The expected time at end point.
   * @return {@code this} assertion object.
   * @throws AssertionError If the values at start point and at end point are not equal to the corresponding times.
   */
  public static <A extends AbstractAssert> A hasValues(A assertion, WritableAssertionInfo info,
                                                       Value valueAtStartPoint, Value valueAtEndPoint,
                                                       TimeValue expectedAtStartPoint, TimeValue expectedAtEndPoint) {

    AssertionsOnColumnOfChangeType.isOfAnyTypeIn(assertion, info, valueAtStartPoint, valueAtEndPoint, ValueType.TIME,
                                                 ValueType.NOT_IDENTIFIED);
    if (!areEqual(valueAtStartPoint, expectedAtStartPoint)) {
      throw failures.failure(info, shouldBeEqualWithStartPoint(
              Values.getRepresentationFromValueInFrontOfExpected(valueAtStartPoint, expectedAtStartPoint), expectedAtStartPoint));
    }
    if (!areEqual(valueAtEndPoint, expectedAtEndPoint)) {
      throw failures.failure(info, shouldBeEqualWithEndPoint(
              Values.getRepresentationFromValueInFrontOfExpected(valueAtEndPoint, expectedAtEndPoint), expectedAtEndPoint));
    }
    return assertion;
  }

  /**
   * Verifies that the values at the start point and the end point are equal to a date/time.
   *
   * @param <A>               The type of the assertion which call this method.
   * @param assertion         The assertion which call this method.
   * @param info              Writable information about an assertion.
   * @param valueAtStartPoint The value at start point.
   * @param valueAtEndPoint   The value at end point.
   * @param expected          The date/time value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the values at start point and at end point are not equal to the date/time.
   */
  public static <A extends AbstractAssert> A hasValues(A assertion, WritableAssertionInfo info,
                                                       Value valueAtStartPoint, Value valueAtEndPoint,
                                                       DateTimeValue expected) {

    AssertionsOnColumnOfChangeType.isOfAnyTypeIn(assertion, info, valueAtStartPoint, valueAtEndPoint,
                                                 ValueType.DATE, ValueType.DATE_TIME, ValueType.NOT_IDENTIFIED);
    if (!areEqual(valueAtStartPoint, expected)) {
      throw failures.failure(info, shouldBeEqualWithStartPoint(
              Values.getRepresentationFromValueInFrontOfExpected(valueAtStartPoint, expected), expected));
    }
    if (!areEqual(valueAtEndPoint, expected)) {
      throw failures.failure(info, shouldBeEqualWithEndPoint(
              Values.getRepresentationFromValueInFrontOfExpected(valueAtEndPoint, expected), expected));
    }
    return assertion;
  }

  /**
   * Verifies that the values at the start point and the end point are equal to a date/time for start point and another date/time for end point.
   *
   * @param <A>                  The type of the assertion which call this method.
   * @param assertion            The assertion which call this method.
   * @param info                 Writable information about an assertion.
   * @param valueAtStartPoint    The value at start point.
   * @param valueAtEndPoint      The value at end point.
   * @param expectedAtStartPoint The expected date/time at start point.
   * @param expectedAtEndPoint   The expected date/time at end point.
   * @return {@code this} assertion object.
   * @throws AssertionError If the values at start point and at end point are not equal to the corresponding dates/times.
   */
  public static <A extends AbstractAssert> A hasValues(A assertion, WritableAssertionInfo info,
                                                       Value valueAtStartPoint, Value valueAtEndPoint,
                                                       DateTimeValue expectedAtStartPoint,
                                                       DateTimeValue expectedAtEndPoint) {

    AssertionsOnColumnOfChangeType.isOfAnyTypeIn(assertion, info, valueAtStartPoint, valueAtEndPoint,
                                                 ValueType.DATE, ValueType.DATE_TIME, ValueType.NOT_IDENTIFIED);
    if (!areEqual(valueAtStartPoint, expectedAtStartPoint)) {
      throw failures.failure(info, shouldBeEqualWithStartPoint(
              Values.getRepresentationFromValueInFrontOfExpected(valueAtStartPoint, expectedAtStartPoint), expectedAtStartPoint));
    }
    if (!areEqual(valueAtEndPoint, expectedAtEndPoint)) {
      throw failures.failure(info, shouldBeEqualWithEndPoint(
              Values.getRepresentationFromValueInFrontOfExpected(valueAtEndPoint, expectedAtEndPoint), expectedAtEndPoint));
    }
    return assertion;
  }

  /**
   * Verifies that the values at the start point and the end point are equal to an UUID.
   *
   * @param <A>               The type of the assertion which call this method.
   * @param assertion         The assertion which call this method.
   * @param info              Writable information about an assertion.
   * @param valueAtStartPoint The value at start point.
   * @param valueAtEndPoint   The value at end point.
   * @param expected          The UUID value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the values at start point and at end point are not equal to the UUID.
   * @since 1.1.0
   */
  public static <A extends AbstractAssert> A hasValues(A assertion, WritableAssertionInfo info,
                                                       Value valueAtStartPoint, Value valueAtEndPoint,
                                                       UUID expected) {

    AssertionsOnColumnOfChangeType.isOfAnyTypeIn(assertion, info, valueAtStartPoint, valueAtEndPoint,
                                                 ValueType.UUID, ValueType.NOT_IDENTIFIED);
    if (!areEqual(valueAtStartPoint, expected)) {
      throw failures.failure(info, shouldBeEqualWithStartPoint(
          Values.getRepresentationFromValueInFrontOfExpected(valueAtStartPoint, expected), expected));
    }
    if (!areEqual(valueAtEndPoint, expected)) {
      throw failures.failure(info, shouldBeEqualWithEndPoint(
          Values.getRepresentationFromValueInFrontOfExpected(valueAtEndPoint, expected), expected));
    }
    return assertion;
  }

  /**
   * Verifies that the values at the start point and the end point are equal to an UUID for start point and another UUID for end point.
   *
   * @param <A>                  The type of the assertion which call this method.
   * @param assertion            The assertion which call this method.
   * @param info                 Writable information about an assertion.
   * @param valueAtStartPoint    The value at start point.
   * @param valueAtEndPoint      The value at end point.
   * @param expectedAtStartPoint The expected UUID at start point.
   * @param expectedAtEndPoint   The expected UUID at end point.
   * @return {@code this} assertion object.
   * @throws AssertionError If the values at start point and at end point are not equal to the corresponding UUIDs.
   * @since 1.1.0
   */
  public static <A extends AbstractAssert> A hasValues(A assertion, WritableAssertionInfo info,
                                                       Value valueAtStartPoint, Value valueAtEndPoint,
                                                       UUID expectedAtStartPoint, UUID expectedAtEndPoint) {

    AssertionsOnColumnOfChangeType.isOfAnyTypeIn(assertion, info, valueAtStartPoint, valueAtEndPoint,
                                                 ValueType.UUID, ValueType.NOT_IDENTIFIED);
    if (!areEqual(valueAtStartPoint, expectedAtStartPoint)) {
      throw failures.failure(info, shouldBeEqualWithStartPoint(
          Values.getRepresentationFromValueInFrontOfExpected(valueAtStartPoint, expectedAtStartPoint),
          expectedAtStartPoint));
    }
    if (!areEqual(valueAtEndPoint, expectedAtEndPoint)) {
      throw failures.failure(info, shouldBeEqualWithEndPoint(
          Values.getRepresentationFromValueInFrontOfExpected(valueAtEndPoint, expectedAtEndPoint), expectedAtEndPoint));
    }
    return assertion;
  }
}

