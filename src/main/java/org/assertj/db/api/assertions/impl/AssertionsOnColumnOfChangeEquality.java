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
package org.assertj.db.api.assertions.impl;

import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.core.internal.Failures;
import org.assertj.db.api.AbstractAssert;

import static org.assertj.db.error.ShouldBeEqualWithEndPoint.shouldBeEqualWithEndPoint;
import static org.assertj.db.error.ShouldBeEqualWithStartPoint.shouldBeEqualWithStartPoint;
import static org.assertj.db.util.Values.areEqual;

/**
 * Implements the assertion methods on the equality of a column of a change.
 *
 * @author Régis Pouiller
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
   * Verifies that the values at the start point and the end point are equal to the parameter.
   *
   * @param <A>               The type of the assertion which call this method.
   * @param assertion         The assertion which call this method.
   * @param info              Info on the object to assert.
   * @param valueAtStartPoint The value at start point.
   * @param valueAtEndPoint   The value at end point.
   * @param expected          The expected value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the values at start point and at end point are not equal to the parameter.
   */
  public static <A extends AbstractAssert> A hasValuesEqualTo(A assertion, WritableAssertionInfo info,
                                                              Object valueAtStartPoint, Object valueAtEndPoint,
                                                              Object expected) {
    if (!areEqual(valueAtStartPoint, expected)) {
      throw failures.failure(info, shouldBeEqualWithStartPoint(valueAtStartPoint, expected));
    }
    if (!areEqual(valueAtEndPoint, expected)) {
      throw failures.failure(info, shouldBeEqualWithEndPoint(valueAtEndPoint, expected));
    }
    return assertion;
  }

  /**
   * Verifies that the values at the start point and the end point are equal to a parameter for start point and a parameter for end point.
   *
   * @param <A>                  The type of the assertion which call this method.
   * @param assertion            The assertion which call this method.
   * @param info                 Info on the object to assert.
   * @param valueAtStartPoint    The value at start point.
   * @param valueAtEndPoint      The value at end point.
   * @param expectedAtStartPoint The expected value at start point.
   * @param expectedAtEndPoint   The expected value at end point.
   * @return {@code this} assertion object.
   * @throws AssertionError If the values at start point and at end point are not equal to the corresponding parameters.
   */
  public static <A extends AbstractAssert> A hasValuesEqualTo(A assertion, WritableAssertionInfo info,
                                                              Object valueAtStartPoint, Object valueAtEndPoint,
                                                              Object expectedAtStartPoint, Object expectedAtEndPoint) {
    if (!areEqual(valueAtStartPoint, expectedAtStartPoint)) {
      throw failures.failure(info, shouldBeEqualWithStartPoint(valueAtStartPoint, expectedAtStartPoint));
    }
    if (!areEqual(valueAtEndPoint, expectedAtEndPoint)) {
      throw failures.failure(info, shouldBeEqualWithEndPoint(valueAtEndPoint, expectedAtEndPoint));
    }
    return assertion;
  }

}
