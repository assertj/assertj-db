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

import org.assertj.core.api.Condition;
import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.core.internal.Conditions;
import org.assertj.db.api.AbstractAssert;
import org.assertj.db.type.Value;

/**
 * Implements the assertion methods on the matching with condition.
 *
 * @author Julien Roy
 */
public class AssertionsOnValueCondition {

  /**
   * Assertions for {@code Object}s provided by assertj-core.
   */
  private static final Conditions conditions = Conditions.instance();

  /**
   * Private constructor.
   */
  private AssertionsOnValueCondition() {
    // Empty
  }

  /**
   * Verifies that the value match with condition.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Writable information about an assertion.
   * @param value     The value.
   * @param condition The condition to use for validation.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not equal to the number in parameter.
   */
  @SuppressWarnings("unchecked")
  public static <A extends AbstractAssert<?>> A is(A assertion, WritableAssertionInfo info, Value value, Condition<?> condition) {
    conditions.assertIs(info, value.getValue(), (Condition<? super Object>) condition);
    return assertion;
  }

  /**
   * Verifies that the value not match with condition.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Writable information about an assertion.
   * @param value     The value.
   * @param condition The condition to use for validation.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not equal to the number in parameter.
   */
  @SuppressWarnings("unchecked")
  public static <A extends AbstractAssert<?>> A isNot(A assertion, WritableAssertionInfo info, Value value, Condition<?> condition) {
    conditions.assertIsNot(info, value.getValue(), (Condition<? super Object>) condition);
    return assertion;
  }

}
