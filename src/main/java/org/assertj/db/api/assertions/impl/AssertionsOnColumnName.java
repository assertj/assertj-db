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

import static org.assertj.db.error.ShouldHaveName.shouldHaveName;

/**
 * Implements the assertion method on the name of a column.
 *
 * @author Régis Pouiller
 * @see org.assertj.db.api.assertions.AssertOnColumnName
 */
public class AssertionsOnColumnName {

  /**
   * To notice failures in the assertion.
   */
  private final static Failures failures = Failures.instance();

  /**
   * Private constructor.
   */
  private AssertionsOnColumnName() {
    // Empty
  }

  /**
   * Verifies that the name of a column is equal to the parameter.
   *
   * @param <A>        The type of the assertion which call this method.
   * @param assertion  The assertion which call this method.
   * @param info       Info on the object to assert.
   * @param columnName The column name.
   * @param expected   The expected column name.
   * @return {@code this} assertion object.
   * @throws AssertionError If the column name is not equal to the parameter.
   */
  public static <A extends AbstractAssert> A hasColumnName(A assertion, WritableAssertionInfo info, String columnName,
                                                           String expected) {
    if (expected == null) {
      throw new NullPointerException("Column name must be not null");
    }
    if (!expected.equalsIgnoreCase(columnName)) {
      throw failures.failure(info, shouldHaveName(columnName, expected));
    }
    return assertion;
  }
}
