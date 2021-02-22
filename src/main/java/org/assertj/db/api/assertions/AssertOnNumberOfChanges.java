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
package org.assertj.db.api.assertions;

/**
 * Defines the assertion method on the number of changes.
 *
 * @param <T> The "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/1IZIRcY"
 *            target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *            for more details.
 * @author Régis Pouiller
 */
public interface AssertOnNumberOfChanges<T extends AssertOnNumberOfChanges<T>> {

  /**
   * Verifies that the number of changes is equal to the number in parameter.
   * <p>
   * Example where the assertion verifies that there are 8 changes :
   * </p>
   * <pre>
   * <code class='java'>
   * assertThat(changes).hasNumberOfChanges(8);
   * </code>
   * </pre>
   *
   * @param expected The number to compare to.
   * @return {@code this} assertion object.
   * @throws AssertionError If the number of changes is different to the number in parameter.
   * @see org.assertj.db.api.ChangesAssert#hasNumberOfChanges(int)
   */
  T hasNumberOfChanges(int expected);

  /**
   * Verifies that the number of changes is greater than the number in parameter.
   * <p>
   * Example where the assertion verifies that there are greater than 8 changes :
   * </p>
   * <pre>
   * <code class='java'>
   * assertThat(changes).hasNumberOfChangesGreaterThan(8);
   * </code>
   * </pre>
   *
   * @param expected The number to compare to.
   * @return {@code this} assertion object.
   * @throws AssertionError If the number of changes is less than or equal to the number in parameter.
   * @see org.assertj.db.api.ChangesAssert#hasNumberOfChangesGreaterThan(int)
   * @since 1.1.0
   */
  T hasNumberOfChangesGreaterThan(int expected);

  /**
   * Verifies that the number of changes is less than the number in parameter.
   * <p>
   * Example where the assertion verifies that there are less than 8 changes :
   * </p>
   * <pre>
   * <code class='java'>
   * assertThat(changes).hasNumberOfChangesLessThan(8);
   * </code>
   * </pre>
   *
   * @param expected The number to compare to.
   * @return {@code this} assertion object.
   * @throws AssertionError If the number of changes is greater than or equal to the number in parameter.
   * @see org.assertj.db.api.ChangesAssert#hasNumberOfChangesLessThan(int)
   * @since 1.1.0
   */
  T hasNumberOfChangesLessThan(int expected);

  /**
   * Verifies that the number of changes is greater than or equal to the number in parameter.
   * <p>
   * Example where the assertion verifies that there are at least 8 changes :
   * </p>
   * <pre>
   * <code class='java'>
   * assertThat(changes).hasNumberOfChangesGreaterThanOrEqualTo(8);
   * </code>
   * </pre>
   *
   * @param expected The number to compare to.
   * @return {@code this} assertion object.
   * @throws AssertionError If the number of changes is less than the number in parameter.
   * @see org.assertj.db.api.ChangesAssert#hasNumberOfChangesGreaterThanOrEqualTo(int)
   * @since 1.1.0
   */
  T hasNumberOfChangesGreaterThanOrEqualTo(int expected);

  /**
   * Verifies that the number of changes is less than or equal to the number in parameter.
   * <p>
   * Example where the assertion verifies that there are at most 8 changes :
   * </p>
   * <pre>
   * <code class='java'>
   * assertThat(changes).hasNumberOfChangesLessThanOrEqualTo(8);
   * </code>
   * </pre>
   *
   * @param expected The number to compare to.
   * @return {@code this} assertion object.
   * @throws AssertionError If the number of changes is greater than the number in parameter.
   * @see org.assertj.db.api.ChangesAssert#hasNumberOfChangesLessThanOrEqualTo(int)
   * @since 1.1.0
   */
  T hasNumberOfChangesLessThanOrEqualTo(int expected);
}
