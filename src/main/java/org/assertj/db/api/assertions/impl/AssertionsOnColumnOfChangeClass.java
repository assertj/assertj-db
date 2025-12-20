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

import static org.assertj.db.error.ShouldBeValueClassWithEndPoint.shouldBeValueClassWithEndPoint;
import static org.assertj.db.error.ShouldBeValueClassWithStartPoint.shouldBeValueClassWithStartPoint;

import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.core.internal.Failures;
import org.assertj.db.api.AbstractAssert;
import org.assertj.db.exception.AssertJDBException;
import org.assertj.db.type.Value;

/**
 * Implements the assertion methods on the class of a column of a change.
 *
 * @author Régis Pouiller
 * @see org.assertj.db.api.assertions.AssertOnColumnClass
 */
public class AssertionsOnColumnOfChangeClass {

  /**
   * To notice failures in the assertion.
   */
  private static final Failures failures = Failures.instance();

  /**
   * Private constructor.
   */
  private AssertionsOnColumnOfChangeClass() {
    // Empty
  }

  /**
   * Verifies that the class of the values of the column is equal to the class in parameter.
   *
   * @param <A>               The type of the assertion which call this method.
   * @param assertion         The assertion which call this method.
   * @param info              Writable information about an assertion.
   * @param valueAtStartPoint The value at start point.
   * @param valueAtEndPoint   The value at end point.
   * @param expected          The expected class to compare to.
   * @param lenient           {@code true} if the test is lenient : if the class of a value is not identified (for example when the
   *                          value is {@code null}), it consider that it is ok.
   * @return {@code this} assertion object.
   * @throws AssertionError If the class of the column is different to the class in parameter.
   * @since 1.1.0
   */
  public static <A extends AbstractAssert<?>> A isOfClass(A assertion, WritableAssertionInfo info,
                                                          Value valueAtStartPoint, Value valueAtEndPoint,
                                                          Class<?> expected, boolean lenient) {

    if (expected == null) {
      throw new AssertJDBException("Class of the column is null");
    }
    if (valueAtStartPoint.getValue() == null || !expected.isAssignableFrom(valueAtStartPoint.getValue().getClass())) {
      if (!lenient || valueAtStartPoint.getValue() != null) {
        throw failures.failure(info, shouldBeValueClassWithStartPoint(valueAtStartPoint, expected));
      }
    }
    if (valueAtEndPoint.getValue() == null || !expected.isAssignableFrom(valueAtEndPoint.getValue().getClass())) {
      if (!lenient || valueAtEndPoint.getValue() != null) {
        throw failures.failure(info, shouldBeValueClassWithEndPoint(valueAtEndPoint, expected));
      }
    }
    return assertion;
  }
}
