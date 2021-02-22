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
 * Defines the assertion methods on the nullity of a values of a column.
 *
 * @param <T> The "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/1IZIRcY"
 *            target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *            for more details.
 * @author Régis Pouiller
 */
public interface AssertOnColumnNullity<T extends AssertOnColumnNullity<T>> {

  /**
   * Verifies that all the values of the column are {@code null}.
   * <p>
   * Example where the assertion verifies that all the values in the first {@code Column} of the {@code Table} are
   * {@code null} :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).column().hasOnlyNullValues();
   * </code></pre>
   *
   * @return {@code this} assertion object.
   * @throws AssertionError If at least one of the values of the column is not {@code null}.
   * @see org.assertj.db.api.AbstractColumnAssert#hasOnlyNullValues()
   */
  T hasOnlyNullValues();

  /**
   * Verifies that all the values of the column are not {@code null}.
   * <p>
   * Example where the assertion verifies that all the values in the first {@code Column} of the {@code Table} are not
   * {@code null} :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).column().hasOnlyNotNullValues();
   * </code></pre>
   *
   * @return {@code this} assertion object.
   * @throws AssertionError If at least one of the values of the column is {@code null}.
   * @see org.assertj.db.api.AbstractColumnAssert#hasOnlyNotNullValues()
   */
  T hasOnlyNotNullValues();
}
