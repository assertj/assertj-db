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
 * Copyright 2012-2015 the original author or authors.
 */
package org.assertj.db.api.assertions.impl;

import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.core.internal.Failures;
import org.assertj.db.api.AbstractAssert;
import org.assertj.db.type.Row;

import static org.assertj.db.error.ShouldExist.shouldExist;
import static org.assertj.db.error.ShouldNotExist.shouldNotExist;

/**
 * Implements the assertion method on the existence of a row of a change.
 *
 * @author Régis Pouiller
 * @see org.assertj.db.api.assertions.AssertOnRowOfChangeExistence
 */
public class AssertionsOnRowOfChangeExistence {

  /**
   * To notice failures in the assertion.
   */
  private final static Failures failures = Failures.instance();

  /**
   * Private constructor.
   */
  private AssertionsOnRowOfChangeExistence() {
    // Empty
  }

  /**
   * Verifies that the row of the change exists.
   *
   * @param <A>               The type of the assertion which call this method.
   * @param assertion         The assertion which call this method.
   * @param info              Writable information about an assertion.
   * @param row               The row.
   * @return {@code this} assertion object.
   * @throws AssertionError If the row of the change does not exist.
   * @see org.assertj.db.api.ChangeRowAssert#exists()
   */
  public static <A extends AbstractAssert> A exists(A assertion, WritableAssertionInfo info, Row row) {
    if (row == null) {
      throw failures.failure(info, shouldExist());
    }
    return assertion;
  }

  /**
   * Verifies that the row of the change does not exist.
   *
   * @param <A>               The type of the assertion which call this method.
   * @param assertion         The assertion which call this method.
   * @param info              Writable information about an assertion.
   * @param row               The row.
   * @return {@code this} assertion object.
   * @throws AssertionError If the row of the change exists.
   * @see org.assertj.db.api.ChangeRowAssert#doesNotExist()
   */
  public static <A extends AbstractAssert> A doesNotExist(A assertion, WritableAssertionInfo info, Row row) {
    if (row != null) {
      throw failures.failure(info, shouldNotExist());
    }
    return assertion;
  }
}
