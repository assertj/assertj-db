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
package org.assertj.db.api.assertions;

/**
 * Defines the assertion methods on comparisons with a value.
 *
 * @param <T> The "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/1IZIRcY"
 *            target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *            for more details.
 * @author Régis Pouiller
 */
public interface AssertOnValueComparison<T extends AssertOnValueComparison<T>> {

  /**
   * Verifies that the value is greater than a number.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is greater than number 3 :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(table).row().value().isGreaterThan(3);
   * </code>
   * </pre>
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the {@code Row} at end point
   * of the first {@code Change} is greater than number 3 :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change().rowAtEndPoint().value().isGreaterThan(3);
   * </code>
   * </pre>
   *
   * @param expected The expected number value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is less than or equal to the number in parameter.
   * @see org.assertj.db.api.AbstractValueAssert#isGreaterThan(Number)
   * @see org.assertj.db.api.AbstractAssertWithValues#isGreaterThan(Number)
   */
  T isGreaterThan(Number expected);

  /**
   * Verifies that the value is less than a number.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is less than number 3 :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(table).row().value().isLessThan(3);
   * </code>
   * </pre>
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the {@code Row} at end point
   * of the first {@code Change} is less than number 3 :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change().rowAtEndPoint().value().isLessThan(3);
   * </code>
   * </pre>
   *
   * @param expected The expected number value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is greater than or equal to the number in parameter.
   * @see org.assertj.db.api.AbstractValueAssert#isLessThan(Number)
   * @see org.assertj.db.api.AbstractAssertWithValues#isLessThan(Number)
   */
  T isLessThan(Number expected);

  /**
   * Verifies that the value is greater than or equal to a number.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is greater than or equal to number 3 :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(table).row().value().isGreaterThanOrEqual(3);
   * </code>
   * </pre>
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the {@code Row} at end point
   * of the first {@code Change} is greater than or equal to number 3 :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change().rowAtEndPoint().value().isGreaterThanOrEqual(3);
   * </code>
   * </pre>
   *
   * @param expected The expected number value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is less than the number in parameter.
   * @see org.assertj.db.api.AbstractValueAssert#isGreaterThanOrEqualTo(Number)
   * @see org.assertj.db.api.AbstractAssertWithValues#isGreaterThanOrEqualTo(Number)
   */
  T isGreaterThanOrEqualTo(Number expected);

  /**
   * Verifies that the value is less than or equal to a number.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is less than or equal to number 3 :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(table).row().value().isLessThanOrEqual(3);
   * </code>
   * </pre>
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the {@code Row} at end point
   * of the first {@code Change} is less than or equal to number 3 :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change().rowAtEndPoint().value().isLessThanOrEqual(3);
   * </code>
   * </pre>
   *
   * @param expected The expected number value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is greater than the number in parameter.
   * @see org.assertj.db.api.AbstractValueAssert#isLessThanOrEqualTo(Number)
   * @see org.assertj.db.api.AbstractAssertWithValues#isLessThanOrEqualTo(Number)
   */
  T isLessThanOrEqualTo(Number expected);
}
