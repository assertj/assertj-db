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

import static org.assertj.db.error.ShouldBeGreater.shouldBeGreater;
import static org.assertj.db.error.ShouldBeGreaterOrEqual.shouldBeGreaterOrEqual;
import static org.assertj.db.error.ShouldBeLess.shouldBeLess;
import static org.assertj.db.error.ShouldBeLessOrEqual.shouldBeLessOrEqual;
import static org.assertj.db.util.Values.compare;

/**
 * Implements the assertion methods on comparisons with a value.
 *
 * @author Régis Pouiller
 * @see org.assertj.db.api.assertions.AssertOnValueComparison
 */
public class AssertionsOnValueComparison {

  /**
   * To notice failures in the assertion.
   */
  private final static Failures failures = Failures.instance();

  /**
   * Private constructor.
   */
  private AssertionsOnValueComparison() {
    // Empty
  }

  /**
   * Verifies that the value is greater than a number.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Writable information about an assertion.
   * @param value     The value.
   * @param expected The expected number value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is less than or equal to the number in parameter.
   */
  public static <A extends AbstractAssert> A isGreaterThan(A assertion, WritableAssertionInfo info, Object value, Number expected) {
    AssertionsOnValueType.isNumber(assertion, info, value);
    if (compare(value, expected) > 0) {
      return assertion;
    }
    throw failures.failure(info, shouldBeGreater(value, expected));
  }

  /**
   * Verifies that the value is less than a number.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Writable information about an assertion.
   * @param value     The value.
   * @param expected The expected number value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is greater than or equal to the number in parameter.
   */
  public static <A extends AbstractAssert> A isLessThan(A assertion, WritableAssertionInfo info, Object value, Number expected) {
    AssertionsOnValueType.isNumber(assertion, info, value);
    if (compare(value, expected) < 0) {
      return assertion;
    }
    throw failures.failure(info, shouldBeLess(value, expected));
  }

  /**
   * Verifies that the value is greater than or equal to a number.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Writable information about an assertion.
   * @param value     The value.
   * @param expected The expected number value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is less than the number in parameter.
   */
  public static <A extends AbstractAssert> A isGreaterThanOrEqualTo(A assertion, WritableAssertionInfo info, Object value, Number expected) {
    AssertionsOnValueType.isNumber(assertion, info, value);
    if (compare(value, expected) >= 0) {
      return assertion;
    }
    throw failures.failure(info, shouldBeGreaterOrEqual(value, expected));
  }

  /**
   * Verifies that the value is less than or equal to a number.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Writable information about an assertion.
   * @param value     The value.
   * @param expected The expected number value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is greater than the number in parameter.
   */
  public static <A extends AbstractAssert> A isLessThanOrEqualTo(A assertion, WritableAssertionInfo info, Object value, Number expected) {
    AssertionsOnValueType.isNumber(assertion, info, value);
    if (compare(value, expected) <= 0) {
      return assertion;
    }
    throw failures.failure(info, shouldBeLessOrEqual(value, expected));
  }
}
