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

import static org.assertj.db.error.ShouldHaveRowsSize.shouldHaveRowsSize;
import static org.assertj.db.error.ShouldHaveRowsSizeGreater.shouldHaveRowsSizeGreater;
import static org.assertj.db.error.ShouldHaveRowsSizeGreaterOrEqual.shouldHaveRowsSizeGreaterOrEqual;
import static org.assertj.db.error.ShouldHaveRowsSizeLess.shouldHaveRowsSizeLess;
import static org.assertj.db.error.ShouldHaveRowsSizeLessOrEqual.shouldHaveRowsSizeLessOrEqual;

/**
 * Implements the assertion method on the number of rows.
 *
 * @author Régis Pouiller
 * @see org.assertj.db.api.assertions.AssertOnNumberOfRows
 */
public class AssertionsOnNumberOfRows {

  /**
   * To notice failures in the assertion.
   */
  private final static Failures failures = Failures.instance();

  /**
   * Private constructor.
   */
  private AssertionsOnNumberOfRows() {
    // Empty
  }

  /**
   * Verifies that the number of rows is equal to the number in parameter.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Writable information about an assertion.
   * @param size      The size of the column.
   * @param expected  The number to compare to the size.
   * @return {@code this} assertion object.
   * @throws AssertionError If the number of rows is different to the number in parameter.
   */
  public static <A extends AbstractAssert> A hasNumberOfRows(A assertion, WritableAssertionInfo info, int size,
                                                             int expected) {
    if (size != expected) {
      throw failures.failure(info, shouldHaveRowsSize(size, expected));
    }
    return assertion;
  }

  /**
   * Verifies that the number of rows is greater than the number in parameter.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Writable information about an assertion.
   * @param size      The size of the row.
   * @param expected  The number to compare to the size.
   * @return {@code this} assertion object.
   * @throws AssertionError If the number of rows is less than or equal to the number in parameter.
   * @since 1.1.0
   */
  public static <A extends AbstractAssert> A hasNumberOfRowsGreaterThan(A assertion, WritableAssertionInfo info, int size,
                                                                           int expected) {
    if (size <= expected) {
      throw failures.failure(info, shouldHaveRowsSizeGreater(size, expected));
    }
    return assertion;
  }

  /**
   * Verifies that the number of rows is less than the number in parameter.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Writable information about an assertion.
   * @param size      The size of the row.
   * @param expected  The number to compare to the size.
   * @return {@code this} assertion object.
   * @throws AssertionError If the number of rows is greater than or equal to the number in parameter.
   * @since 1.1.0
   */
  public static <A extends AbstractAssert> A hasNumberOfRowsLessThan(A assertion, WritableAssertionInfo info, int size,
                                                                        int expected) {
    if (size >= expected) {
      throw failures.failure(info, shouldHaveRowsSizeLess(size, expected));
    }
    return assertion;
  }

  /**
   * Verifies that the number of rows is greater than or equal to the number in parameter.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Writable information about an assertion.
   * @param size      The size of the row.
   * @param expected  The number to compare to the size.
   * @return {@code this} assertion object.
   * @throws AssertionError If the number of rows is less than the number in parameter.
   * @since 1.1.0
   */
  public static <A extends AbstractAssert> A hasNumberOfRowsGreaterThanOrEqualTo(A assertion, WritableAssertionInfo info, int size,
                                                                                    int expected) {
    if (size < expected) {
      throw failures.failure(info, shouldHaveRowsSizeGreaterOrEqual(size, expected));
    }
    return assertion;
  }

  /**
   * Verifies that the number of rows is less than or equal to the number in parameter.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Writable information about an assertion.
   * @param size      The size of the row.
   * @param expected  The number to compare to the size.
   * @return {@code this} assertion object.
   * @throws AssertionError If the number of rows is greater than the number in parameter.
   * @since 1.1.0
   */
  public static <A extends AbstractAssert> A hasNumberOfRowsLessThanOrEqualTo(A assertion, WritableAssertionInfo info, int size,
                                                                                 int expected) {
    if (size > expected) {
      throw failures.failure(info, shouldHaveRowsSizeLessOrEqual(size, expected));
    }
    return assertion;
  }
}
