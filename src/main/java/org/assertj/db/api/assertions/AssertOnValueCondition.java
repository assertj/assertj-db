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
package org.assertj.db.api.assertions;

import org.assertj.core.api.Condition;

/**
 * Defines the assertion methods on the matching with condition.
 *
 * @param <T> The "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/1IZIRcY"
 *            target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *            for more details.
 * @author Julien Roy
 */
public interface AssertOnValueCondition<T extends AssertOnValueCondition<T>> {

  /**
   * Verifies that the actual value satisfies the given condition.
   *
   * @param condition the given condition.
   * @return {@code this} assertion object.
   * @throws NullPointerException if the given condition is {@code null}.
   * @throws AssertionError       if the actual value does not satisfy the given condition.
   * @see org.assertj.db.api.AbstractValueAssert#is(Condition)
   * @see org.assertj.db.api.AbstractAssertWithValues#is(Condition)
   */
  T is(Condition<?> condition);

  /**
   * Verifies that the actual value does not satisfy the given condition.
   *
   * @param condition the given condition.
   * @return {@code this} assertion object.
   * @throws NullPointerException if the given condition is {@code null}.
   * @throws AssertionError       if the actual value satisfies the given condition.
   * @see org.assertj.db.api.AbstractValueAssert#isNot(Condition)
   * @see org.assertj.db.api.AbstractAssertWithValues#isNot(Condition)
   */
  T isNot(Condition<?> condition);

  /**
   * Verifies that the actual value satisfies the given condition. This method is an alias for <code>{@link #is(Condition)}</code>.
   *
   * @param condition the given condition.
   * @return {@code this} assertion object.
   * @throws NullPointerException if the given condition is {@code null}.
   * @throws AssertionError       if the actual value does not satisfy the given condition.
   * @see org.assertj.db.api.AbstractValueAssert#has(Condition)
   * @see org.assertj.db.api.AbstractAssertWithValues#has(Condition)
   */
  T has(Condition<?> condition);

  /**
   * Verifies that the actual value does not satisfy the given condition. This method is an alias for <code>{@link #isNot(Condition)}</code>.
   *
   * @param condition the given condition.
   * @return {@code this} assertion object.
   * @throws NullPointerException if the given condition is {@code null}.
   * @throws AssertionError       if the actual value satisfies the given condition.
   * @see org.assertj.db.api.AbstractValueAssert#doesNotHave(Condition)
   * @see org.assertj.db.api.AbstractAssertWithValues#doesNotHave(Condition)
   */
  T doesNotHave(Condition<?> condition);

  /**
   * Verifies that the actual value satisfies the given condition. This method is an alias for <code>{@link #is(Condition)}</code>.
   *
   * @param condition the given condition.
   * @return {@code this} assertion object.
   * @throws NullPointerException if the given condition is {@code null}.
   * @throws AssertionError       if the actual value does not satisfy the given condition.
   * @see org.assertj.db.api.AbstractValueAssert#satisfies(Condition)
   * @see org.assertj.db.api.AbstractAssertWithValues#satisfies(Condition)
   */
  T satisfies(Condition<?> condition);

}
