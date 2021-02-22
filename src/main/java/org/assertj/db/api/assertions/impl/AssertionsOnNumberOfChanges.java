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
 * Copyright 2015-2021 the original author or authors.
 */
package org.assertj.db.api.assertions.impl;

import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.core.internal.Failures;
import org.assertj.db.api.AbstractAssert;
import org.assertj.db.type.Change;
import org.assertj.db.type.Changes;

import java.util.List;

import static org.assertj.db.error.ShouldHaveChangesSize.shouldHaveChangesSize;
import static org.assertj.db.error.ShouldHaveChangesSizeGreater.shouldHaveChangesSizeGreater;
import static org.assertj.db.error.ShouldHaveChangesSizeGreaterOrEqual.shouldHaveChangesSizeGreaterOrEqual;
import static org.assertj.db.error.ShouldHaveChangesSizeLess.shouldHaveChangesSizeLess;
import static org.assertj.db.error.ShouldHaveChangesSizeLessOrEqual.shouldHaveChangesSizeLessOrEqual;

/**
 * Implements the assertion method on the number of changes.
 *
 * @author Régis Pouiller
 * @see org.assertj.db.api.assertions.AssertOnNumberOfChanges
 */
public class AssertionsOnNumberOfChanges {

  /**
   * To notice failures in the assertion.
   */
  private static final Failures failures = Failures.instance();

  /**
   * Private constructor.
   */
  private AssertionsOnNumberOfChanges() {
    // Empty
  }

  /**
   * Verifies that the number of changes is equal to the number in parameter.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Writable information about an assertion.
   * @param changes   The changes.
   * @param expected  The number to compare to the size.
   * @return {@code this} assertion object.
   * @throws AssertionError If the number of changes is different to the number in parameter.
   */
  public static <A extends AbstractAssert<?>> A hasNumberOfChanges(A assertion, WritableAssertionInfo info,
                                                                Changes changes, int expected) {
    List<Change> changesList = changes.getChangesList();
    int size = changesList.size();
    if (size != expected) {
      throw failures.failure(info, shouldHaveChangesSize(size, expected));
    }
    return assertion;
  }

  /**
   * Verifies that the number of changes is greater than the number in parameter.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Writable information about an assertion.
   * @param changes   The changes.
   * @param expected  The number to compare to the size.
   * @return {@code this} assertion object.
   * @throws AssertionError If the number of changes is less than or equal to the number in parameter.
   */
  public static <A extends AbstractAssert<?>> A hasNumberOfChangesGreaterThan(A assertion, WritableAssertionInfo info,
                                                                Changes changes, int expected) {
    List<Change> changesList = changes.getChangesList();
    int size = changesList.size();
    if (size <= expected) {
      throw failures.failure(info, shouldHaveChangesSizeGreater(size, expected));
    }
    return assertion;
  }

  /**
   * Verifies that the number of changes is less to the number in parameter.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Writable information about an assertion.
   * @param changes   The changes.
   * @param expected  The number to compare to the size.
   * @return {@code this} assertion object.
   * @throws AssertionError If the number of changes is greater than or equal to the number in parameter.
   */
  public static <A extends AbstractAssert<?>> A hasNumberOfChangesLessThan(A assertion, WritableAssertionInfo info,
                                                                Changes changes, int expected) {
    List<Change> changesList = changes.getChangesList();
    int size = changesList.size();
    if (size >= expected) {
      throw failures.failure(info, shouldHaveChangesSizeLess(size, expected));
    }
    return assertion;
  }

  /**
   * Verifies that the number of changes is greater than or equal to the number in parameter.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Writable information about an assertion.
   * @param changes   The changes.
   * @param expected  The number to compare to the size.
   * @return {@code this} assertion object.
   * @throws AssertionError If the number of changes is less than the number in parameter.
   */
  public static <A extends AbstractAssert<?>> A hasNumberOfChangesGreaterThanOrEqualTo(A assertion, WritableAssertionInfo info,
                                                                Changes changes, int expected) {
    List<Change> changesList = changes.getChangesList();
    int size = changesList.size();
    if (size < expected) {
      throw failures.failure(info, shouldHaveChangesSizeGreaterOrEqual(size, expected));
    }
    return assertion;
  }

  /**
   * Verifies that the number of changes is less than or equal to the number in parameter.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Writable information about an assertion.
   * @param changes   The changes.
   * @param expected  The number to compare to the size.
   * @return {@code this} assertion object.
   * @throws AssertionError If the number of changes is greater than the number in parameter.
   */
  public static <A extends AbstractAssert<?>> A hasNumberOfChangesLessThanOrEqualTo(A assertion, WritableAssertionInfo info,
                                                                Changes changes, int expected) {
    List<Change> changesList = changes.getChangesList();
    int size = changesList.size();
    if (size > expected) {
      throw failures.failure(info, shouldHaveChangesSizeLessOrEqual(size, expected));
    }
    return assertion;
  }
}
