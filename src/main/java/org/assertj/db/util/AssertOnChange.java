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
import org.assertj.db.type.ChangeType;

import static org.assertj.db.error.ShouldBeChangeType.shouldBeChangeType;

/**
 * Utility methods related to assert on change.
 *
 * @author Régis Pouiller
 */
public class AssertOnChange {

  /**
   * To notice failures in the assertion.
   */
  private final static Failures failures = Failures.instance();

  /**
   * Private constructor.
   */
  private AssertOnChange() {
    // Empty
  }

  /**
   * Verifies that the type of the change is equal to the type in parameter.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Info on the object to assert.
   * @param type      The type.
   * @param expected  The expected type to compare to.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type is different to the type in parameter.
   */
  public static <A extends AbstractAssert> A isOfType(A assertion, WritableAssertionInfo info, ChangeType type,
                                                      ChangeType expected) {
    if (type != expected) {
      throw failures.failure(info, shouldBeChangeType(expected, type));
    }
    return assertion;
  }
}
