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
 * Copyright 2015-2024 the original author or authors.
 */
package org.assertj.db.api.assertions.impl;

import static org.assertj.db.error.ShouldContainsOnlyNotNull.shouldContainsOnlyNotNull;
import static org.assertj.db.error.ShouldContainsOnlyNull.shouldContainsOnlyNull;

import java.util.List;

import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.core.internal.Failures;
import org.assertj.db.api.AbstractAssert;
import org.assertj.db.type.Value;

/**
 * Implements the assertion methods on the nullity of a values of a column/row.
 *
 * @author Régis Pouiller
 * @see org.assertj.db.api.assertions.AssertOnColumnNullity
 * @see org.assertj.db.api.assertions.AssertOnRowNullity
 */
public class AssertionsOnValuesNullity {

  /**
   * To notice failures in the assertion.
   */
  private static final Failures failures = Failures.instance();

  /**
   * Private constructor.
   */
  private AssertionsOnValuesNullity() {
    // Empty
  }

  /**
   * Verifies that all the values of the column are {@code null}.
   *
   * @param <A>        The type of the assertion which call this method.
   * @param assertion  The assertion which call this method.
   * @param info       Writable information about an assertion.
   * @param valuesList The list of values.
   * @return {@code this} assertion object.
   * @throws AssertionError If at least one of the values of the column are not {@code null}.
   */
  public static <A extends AbstractAssert<?>> A hasOnlyNullValues(A assertion, WritableAssertionInfo info,
                                                                  List<Value> valuesList) {
    int index = 0;
    for (Value value : valuesList) {
      if (value.getValue() != null) {
        throw failures.failure(info, shouldContainsOnlyNull(index));
      }
      index++;
    }
    return assertion;
  }

  /**
   * Verifies that all the values of the column are not {@code null}.
   *
   * @param <A>        The type of the assertion which call this method.
   * @param assertion  The assertion which call this method.
   * @param info       Writable information about an assertion.
   * @param valuesList The list of values.
   * @return {@code this} assertion object.
   * @throws AssertionError If at least one of the values of the column are {@code null}.
   */
  public static <A extends AbstractAssert<?>> A hasOnlyNotNullValues(A assertion, WritableAssertionInfo info,
                                                                     List<Value> valuesList) {
    int index = 0;
    for (Value value : valuesList) {
      if (value.getValue() == null) {
        throw failures.failure(info, shouldContainsOnlyNotNull(index));
      }
      index++;
    }
    return assertion;
  }
}
