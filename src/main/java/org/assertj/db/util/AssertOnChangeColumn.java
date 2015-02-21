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

import static org.assertj.db.error.ShouldBeModified.shouldBeModified;
import static org.assertj.db.error.ShouldNotBeModified.shouldNotBeModified;

/**
 * Utility methods related to assert on column of a change.
 *
 * @author Régis Pouiller
 */
public class AssertOnChangeColumn {

  /**
   * To notice failures in the assertion.
   */
  private final static Failures failures = Failures.instance();

  /**
   * Private constructor.
   */
  private AssertOnChangeColumn() {
    // Empty
  }

  /**
   * Verifies that the column is modified between the start point and the end point.
   *
   * @param <A>               The type of the assertion which call this method.
   * @param assertion         The assertion which call this method.
   * @param info              Info on the object to assert.
   * @param valueAtStartPoint The value at start point.
   * @param valueAtEndPoint   The value at end point.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type is different to the type in parameter.
   */
  public static <A extends AbstractAssert> A isModified(A assertion, WritableAssertionInfo info,
                                                        Object valueAtStartPoint, Object valueAtEndPoint) {
    if ((valueAtStartPoint == null && valueAtEndPoint == null) || (valueAtStartPoint != null && valueAtStartPoint
            .equals(valueAtEndPoint))) {

      throw failures.failure(info, shouldBeModified(valueAtStartPoint, valueAtEndPoint));
    }
    return assertion;
  }

  /**
   * Verifies that the column is not modified between the start point and the end point.
   *
   * @param <A>               The type of the assertion which call this method.
   * @param assertion         The assertion which call this method.
   * @param info              Info on the object to assert.
   * @param valueAtStartPoint The value at start point.
   * @param valueAtEndPoint   The value at end point.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type is different to the type in parameter.
   */
  public static <A extends AbstractAssert> A isNotModified(A assertion, WritableAssertionInfo info,
                                                           Object valueAtStartPoint, Object valueAtEndPoint) {
    if ((valueAtStartPoint == null && valueAtEndPoint != null) || (valueAtStartPoint != null && !valueAtStartPoint
            .equals(valueAtEndPoint))) {

      throw failures.failure(info, shouldNotBeModified(valueAtStartPoint, valueAtEndPoint));
    }
    return assertion;
  }

}
