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
import org.assertj.db.type.Change;
import org.assertj.db.type.ChangeType;

import static org.assertj.db.error.ShouldBeChangeType.shouldBeChangeType;

/**
 * Implements the assertion methods on the type of a change (creation, modification or deletion of a row).
 * <p>The different type of changes are enumerated in {@link org.assertj.db.type.ChangeType}.</p>
 *
 * @author Régis Pouiller
 * @see org.assertj.db.api.assertions.AssertOnChangeType
 */
public class AssertionsOnChangeType {

  /**
   * To notice failures in the assertion.
   */
  private final static Failures failures = Failures.instance();

  /**
   * Private constructor.
   */
  private AssertionsOnChangeType() {
    // Empty
  }

  /**
   * Verifies that the type of the change is equal to the type in parameter.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Info on the object to assert.
   * @param change    The change.
   * @param expected  The expected type to compare to.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type is different to the type in parameter.
   */
  public static <A extends AbstractAssert> A isOfType(A assertion, WritableAssertionInfo info, Change change,
                                                      ChangeType expected) {
    ChangeType type = change.getChangeType();
    if (type != expected) {
      throw failures.failure(info, shouldBeChangeType(expected, type));
    }
    return assertion;
  }

  /**
   * Verifies that the type of the change is a creation.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Info on the object to assert.
   * @param change    The change.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type of the change is not a creation.
   */
  public static <A extends AbstractAssert> A isCreation(A assertion, WritableAssertionInfo info, Change change) {
    return isOfType(assertion, info, change, ChangeType.CREATION);
  }

  /**
   * Verifies that the type of the change is a modification.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Info on the object to assert.
   * @param change    The change.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type of the change is not a modification.
   */
  public static <A extends AbstractAssert> A isModification(A assertion, WritableAssertionInfo info, Change change) {
    return isOfType(assertion, info, change, ChangeType.MODIFICATION);
  }

  /**
   * Verifies that the type of the change is a deletion.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Info on the object to assert.
   * @param change    The change.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type of the change is not a deletion.
   */
  public static <A extends AbstractAssert> A isDeletion(A assertion, WritableAssertionInfo info, Change change) {
    return isOfType(assertion, info, change, ChangeType.DELETION);
  }
}
