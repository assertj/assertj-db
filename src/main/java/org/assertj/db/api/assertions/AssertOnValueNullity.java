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
package org.assertj.db.api.assertions;

/**
 * Defines the assertion methods on the nullity of a value.
 *
 * @param <T> The "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/anMa4g"
 *            target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *            for more details.
 * @author Régis Pouiller
 */
public interface AssertOnValueNullity<T extends AssertOnValueNullity<T>> {

  /**
   * Verifies that the value is {@code null}.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is {@code null} :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(table).row().value().isNull();
   * </code>
   * </pre>
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the {@code Row} at end point
   * of the first {@code Change} is {@code null} :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change().rowAtEndPoint().value().isNull();
   * </code>
   * </pre>
   *
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not {@code null}.
   */
  public T isNull();

  /**
   * Verifies that the value is not {@code null}.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is not {@code null} :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(table).row().value().isNotNull();
   * </code>
   * </pre>
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the {@code Row} at end point
   * of the first {@code Change} is not {@code null} :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change().rowAtEndPoint().value().isNotNull();
   * </code>
   * </pre>
   *
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is {@code null}.
   */
  public T isNotNull();
}
