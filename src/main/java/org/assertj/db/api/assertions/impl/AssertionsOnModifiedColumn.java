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
import org.assertj.db.type.Value;

import static org.assertj.db.error.ShouldBeModified.shouldBeModified;
import static org.assertj.db.error.ShouldNotBeModified.shouldNotBeModified;

/**
 * Implements the assertion methods on a modified column.
 *
 * @author Régis Pouiller
 * @see org.assertj.db.api.assertions.AssertOnModifiedColumn
 */
public class AssertionsOnModifiedColumn {

  /**
   * To notice failures in the assertion.
   */
  private final static Failures failures = Failures.instance();

  /**
   * Private constructor.
   */
  private AssertionsOnModifiedColumn() {
    // Empty
  }

  /**
   * Verifies that the column is modified between the start point and the end point.
   *
   * @param <A>               The type of the assertion which call this method.
   * @param assertion         The assertion which call this method.
   * @param info              Writable information about an assertion.
   * @param valueAtStartPoint The value at start point.
   * @param valueAtEndPoint   The value at end point.
   * @return {@code this} assertion object.
   * @throws AssertionError If the column is not modified between the start point and the end point.
   */
  public static <A extends AbstractAssert> A isModified(A assertion, WritableAssertionInfo info,
                                                        Value valueAtStartPoint, Value valueAtEndPoint) {
    if ((valueAtStartPoint.getValue() == null && valueAtEndPoint.getValue() == null)
        || (valueAtStartPoint.getValue() != null && valueAtStartPoint.getValue().equals(valueAtEndPoint.getValue()))) {

      throw failures.failure(info, shouldBeModified(valueAtStartPoint, valueAtEndPoint));
    }
    return assertion;
  }

  /**
   * Verifies that the column is not modified between the start point and the end point.
   *
   * @param <A>               The type of the assertion which call this method.
   * @param assertion         The assertion which call this method.
   * @param info              Writable information about an assertion.
   * @param valueAtStartPoint The value at start point.
   * @param valueAtEndPoint   The value at end point.
   * @return {@code this} assertion object.
   * @throws AssertionError If the column is modified between the start point and the end point.
   */
  public static <A extends AbstractAssert> A isNotModified(A assertion, WritableAssertionInfo info,
                                                           Value valueAtStartPoint, Value valueAtEndPoint) {
    if ((valueAtStartPoint.getValue() == null && valueAtEndPoint.getValue() != null)
        || (valueAtStartPoint.getValue() != null && !valueAtStartPoint.getValue().equals(valueAtEndPoint.getValue()))) {

      throw failures.failure(info, shouldNotBeModified(valueAtStartPoint, valueAtEndPoint));
    }
    return assertion;
  }
}
