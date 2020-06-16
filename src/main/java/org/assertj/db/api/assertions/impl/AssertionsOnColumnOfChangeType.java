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
 * Copyright 2015-2020 the original author or authors.
 */
package org.assertj.db.api.assertions.impl;

import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.core.internal.Failures;
import org.assertj.db.api.AbstractAssert;
import org.assertj.db.type.Value;
import org.assertj.db.type.ValueType;

import static org.assertj.db.error.ShouldBeValueTypeOfAnyWithEndPoint.shouldBeValueTypeOfAnyWithEndPoint;
import static org.assertj.db.error.ShouldBeValueTypeOfAnyWithStartPoint.shouldBeValueTypeOfAnyWithStartPoint;
import static org.assertj.db.error.ShouldBeValueTypeWithEndPoint.shouldBeValueTypeWithEndPoint;
import static org.assertj.db.error.ShouldBeValueTypeWithStartPoint.shouldBeValueTypeWithStartPoint;

/**
 * Implements the assertion methods on the type of a column of a change.
 * <p>The different type of values are enumerated in {@link org.assertj.db.type.ValueType}.</p>
 *
 * @author Régis Pouiller
 * @author Otoniel Isidoro
 * @see org.assertj.db.api.assertions.AssertOnColumnType
 */
public class AssertionsOnColumnOfChangeType {

  /**
   * To notice failures in the assertion.
   */
  private static final Failures failures = Failures.instance();

  /**
   * Private constructor.
   */
  private AssertionsOnColumnOfChangeType() {
    // Empty
  }

  /**
   * Verifies that the type of the values of the column is equal to the type in parameter.
   *
   * @param <A>        The type of the assertion which call this method.
   * @param assertion  The assertion which call this method.
   * @param info       Writable information about an assertion.
   * @param valueAtStartPoint The value at start point.
   * @param valueAtEndPoint   The value at end point.
   * @param expected   The expected type to compare to.
   * @param lenient    {@code true} if the test is lenient : if the type of a value is not identified (for example when the
   *                   value is {@code null}), it consider that it is ok.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type of the column is different to the type in parameter.
   */
  public static <A extends AbstractAssert<?>> A isOfType(A assertion, WritableAssertionInfo info,
                                                      Value valueAtStartPoint, Value valueAtEndPoint,
                                                      ValueType expected, boolean lenient) {
    if (lenient) {
      return isOfAnyTypeIn(assertion, info, valueAtStartPoint, valueAtEndPoint, expected, ValueType.NOT_IDENTIFIED);
    }

    ValueType typeAtStartPoint = valueAtStartPoint.getValueType();
    if (typeAtStartPoint != expected) {
      throw failures.failure(info, shouldBeValueTypeWithStartPoint(valueAtStartPoint, typeAtStartPoint, expected));
    }
    ValueType typeAtEndPoint = valueAtEndPoint.getValueType();
    if (typeAtEndPoint != expected) {
      throw failures.failure(info, shouldBeValueTypeWithEndPoint(valueAtEndPoint, typeAtEndPoint, expected));
    }

    return assertion;
  }

  /**
   * Verifies that the type of the column is equal to one of the types in parameters.
   *
   * @param <A>        The type of the assertion which call this method.
   * @param assertion  The assertion which call this method.
   * @param info       Writable information about an assertion.
   * @param valueAtStartPoint The value at start point.
   * @param valueAtEndPoint   The value at end point.
   * @param expected   The expected types to compare to.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type of the column is different to all the types in parameters.
   */
  public static <A extends AbstractAssert<?>> A isOfAnyTypeIn(A assertion, WritableAssertionInfo info,
                                                           Value valueAtStartPoint, Value valueAtEndPoint,
                                                           ValueType... expected) {
    ValueType typeAtStartPoint = valueAtStartPoint.getValueType();
    boolean matched = false;
    for (ValueType valueType : expected) {
      if (typeAtStartPoint == valueType) {
        matched = true;
        break;
      }
    }
    if (!matched) {
      throw failures.failure(info, shouldBeValueTypeOfAnyWithStartPoint(valueAtStartPoint, typeAtStartPoint, expected));
    }
    ValueType typeAtEndPoint = valueAtEndPoint.getValueType();
    matched = false;
    for (ValueType valueType : expected) {
      if (typeAtEndPoint == valueType) {
        matched = true;
        break;
      }
    }
    if (!matched) {
      throw failures.failure(info, shouldBeValueTypeOfAnyWithEndPoint(valueAtEndPoint, typeAtEndPoint, expected));
    }
    return assertion;
  }

  /**
   * Verifies that the type of the values of the column is number.
   *
   * @param <A>        The type of the assertion which call this method.
   * @param assertion  The assertion which call this method.
   * @param info       Writable information about an assertion.
   * @param valueAtStartPoint The value at start point.
   * @param valueAtEndPoint   The value at end point.
   * @param lenient    {@code true} if the test is lenient : if the type of a value is not identified (for example when the
   *                   value is {@code null}), it consider that it is ok.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type of the column is not number.
   */
  public static <A extends AbstractAssert<?>> A isNumber(A assertion, WritableAssertionInfo info,
                                                      Value valueAtStartPoint, Value valueAtEndPoint,
                                                      boolean lenient) {
    return isOfType(assertion, info, valueAtStartPoint, valueAtEndPoint, ValueType.NUMBER, lenient);
  }

  /**
   * Verifies that the type of the values of the column is boolean.
   *
   * @param <A>        The type of the assertion which call this method.
   * @param assertion  The assertion which call this method.
   * @param info       Writable information about an assertion.
   * @param valueAtStartPoint The value at start point.
   * @param valueAtEndPoint   The value at end point.
   * @param lenient    {@code true} if the test is lenient : if the type of a value is not identified (for example when the
   *                   value is {@code null}), it consider that it is ok.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type of the column is not boolean.
   */
  public static <A extends AbstractAssert<?>> A isBoolean(A assertion, WritableAssertionInfo info,
                                                       Value valueAtStartPoint, Value valueAtEndPoint,
                                                       boolean lenient) {
    return isOfType(assertion, info, valueAtStartPoint, valueAtEndPoint, ValueType.BOOLEAN, lenient);
  }

  /**
   * Verifies that the type of the values of the column is date.
   *
   * @param <A>        The type of the assertion which call this method.
   * @param assertion  The assertion which call this method.
   * @param info       Writable information about an assertion.
   * @param valueAtStartPoint The value at start point.
   * @param valueAtEndPoint   The value at end point.
   * @param lenient    {@code true} if the test is lenient : if the type of a value is not identified (for example when the
   *                   value is {@code null}), it consider that it is ok.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type of the column is not date.
   */
  public static <A extends AbstractAssert<?>> A isDate(A assertion, WritableAssertionInfo info,
                                                    Value valueAtStartPoint, Value valueAtEndPoint,
                                                    boolean lenient) {
    return isOfType(assertion, info, valueAtStartPoint, valueAtEndPoint, ValueType.DATE, lenient);
  }

  /**
   * Verifies that the type of the values of the column is time.
   *
   * @param <A>        The type of the assertion which call this method.
   * @param assertion  The assertion which call this method.
   * @param info       Writable information about an assertion.
   * @param valueAtStartPoint The value at start point.
   * @param valueAtEndPoint   The value at end point.
   * @param lenient    {@code true} if the test is lenient : if the type of a value is not identified (for example when the
   *                   value is {@code null}), it consider that it is ok.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type of the column is not time.
   */
  public static <A extends AbstractAssert<?>> A isTime(A assertion, WritableAssertionInfo info,
                                                    Value valueAtStartPoint, Value valueAtEndPoint,
                                                    boolean lenient) {
    return isOfType(assertion, info, valueAtStartPoint, valueAtEndPoint, ValueType.TIME, lenient);
  }

  /**
   * Verifies that the type of the values of the column is date/time.
   *
   * @param <A>        The type of the assertion which call this method.
   * @param assertion  The assertion which call this method.
   * @param info       Writable information about an assertion.
   * @param valueAtStartPoint The value at start point.
   * @param valueAtEndPoint   The value at end point.
   * @param lenient    {@code true} if the test is lenient : if the type of a value is not identified (for example when the
   *                   value is {@code null}), it consider that it is ok.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type of the column is not date/time.
   */
  public static <A extends AbstractAssert<?>> A isDateTime(A assertion, WritableAssertionInfo info,
                                                        Value valueAtStartPoint, Value valueAtEndPoint,
                                                        boolean lenient) {
    return isOfType(assertion, info, valueAtStartPoint, valueAtEndPoint, ValueType.DATE_TIME, lenient);
  }

  /**
   * Verifies that the type of the values of the column is array of bytes.
   *
   * @param <A>        The type of the assertion which call this method.
   * @param assertion  The assertion which call this method.
   * @param info       Writable information about an assertion.
   * @param valueAtStartPoint The value at start point.
   * @param valueAtEndPoint   The value at end point.
   * @param lenient    {@code true} if the test is lenient : if the type of a value is not identified (for example when the
   *                   value is {@code null}), it consider that it is ok.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type of the column is not array of bytes.
   */
  public static <A extends AbstractAssert<?>> A isBytes(A assertion, WritableAssertionInfo info,
                                                     Value valueAtStartPoint, Value valueAtEndPoint,
                                                     boolean lenient) {
    return isOfType(assertion, info, valueAtStartPoint, valueAtEndPoint, ValueType.BYTES, lenient);
  }

  /**
   * Verifies that the type of the values of the column is text.
   *
   * @param <A>        The type of the assertion which call this method.
   * @param assertion  The assertion which call this method.
   * @param info       Writable information about an assertion.
   * @param valueAtStartPoint The value at start point.
   * @param valueAtEndPoint   The value at end point.
   * @param lenient    {@code true} if the test is lenient : if the type of a value is not identified (for example when the
   *                   value is {@code null}), it consider that it is ok.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type of the column is not text.
   */
  public static <A extends AbstractAssert<?>> A isText(A assertion, WritableAssertionInfo info,
                                                    Value valueAtStartPoint, Value valueAtEndPoint,
                                                    boolean lenient) {
    return isOfType(assertion, info, valueAtStartPoint, valueAtEndPoint, ValueType.TEXT, lenient);
  }

  /**
   * Verifies that the type of the values of the column is UUID.
   *
   * @param <A>        The type of the assertion which call this method.
   * @param assertion  The assertion which call this method.
   * @param info       Writable information about an assertion.
   * @param valueAtStartPoint The value at start point.
   * @param valueAtEndPoint   The value at end point.
   * @param lenient    {@code true} if the test is lenient : if the type of a value is not identified (for example when the
   *                   value is {@code null}), it consider that it is ok.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type of the column is not UUID.
   * @since 1.1.0
   */
  public static <A extends AbstractAssert<?>> A isUUID(A assertion, WritableAssertionInfo info,
                                                    Value valueAtStartPoint, Value valueAtEndPoint,
                                                    boolean lenient) {
    return isOfType(assertion, info, valueAtStartPoint, valueAtEndPoint, ValueType.UUID, lenient);
  }

}
