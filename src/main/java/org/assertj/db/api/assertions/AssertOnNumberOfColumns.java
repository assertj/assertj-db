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
 * Defines the assertion method on the number of columns.
 *
 * @param <T> The "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/1IZIRcY"
 *            target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *            for more details.
 * @author Régis Pouiller
 */
public interface AssertOnNumberOfColumns<T extends AssertOnNumberOfColumns<T>> {

  /**
   * Verifies that the number of columns is equal to the number in parameter.
   * <p>
   * Example where the assertion verifies that the table has 8 columns :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).hasNumberOfColumns(8);
   * </code></pre>
   * <p>
   * Example where the assertion verifies that the first row of the table has 8 columns :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).row().hasNumberOfColumns(8);
   * </code></pre>
   * <p>
   * Example where the assertion verifies that the row at end point of the first change has 8 columns :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(changes).change().rowAtEndPoint().hasNumberOfColumns(8);
   * </code></pre>
   *
   * @param expected The number to compare to the number of columns.
   * @return {@code this} assertion object.
   * @throws AssertionError If the number of columns is different to the number in parameter.
   * @see org.assertj.db.api.AbstractDbAssert#hasNumberOfColumns(int)
   * @see org.assertj.db.api.AbstractRowAssert#hasNumberOfColumns(int)
   * @see org.assertj.db.api.ChangeRowAssert#hasNumberOfColumns(int)
   */
  T hasNumberOfColumns(int expected);

  /**
   * Verifies that the number of columns is greater than the number in parameter.
   * <p>
   * Example where the assertion verifies that the table has more than 8 columns :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).hasNumberOfColumnsGreaterThan(8);
   * </code></pre>
   * <p>
   * Example where the assertion verifies that the first row of the table has more than 8 columns :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).row().hasNumberOfColumnsGreaterThan(8);
   * </code></pre>
   * <p>
   * Example where the assertion verifies that the row at end point of the first change has more than 8 columns :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(changes).change().rowAtEndPoint().hasNumberOfColumnsGreaterThan(8);
   * </code></pre>
   *
   * @param expected The number to compare to the number of columns.
   * @return {@code this} assertion object.
   * @throws AssertionError If the number of columns is less than or equal to the number in parameter.
   * @see org.assertj.db.api.AbstractDbAssert#hasNumberOfColumnsGreaterThan(int)
   * @see org.assertj.db.api.AbstractRowAssert#hasNumberOfColumnsGreaterThan(int)
   * @see org.assertj.db.api.ChangeRowAssert#hasNumberOfColumnsGreaterThan(int)
   * @since 1.1.0
   */
  T hasNumberOfColumnsGreaterThan(int expected);

  /**
   * Verifies that the number of columns is less than the number in parameter.
   * <p>
   * Example where the assertion verifies that the table has less than 8 columns :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).hasNumberOfColumnsLessThan(8);
   * </code></pre>
   * <p>
   * Example where the assertion verifies that the first row of the table has less than 8 columns :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).row().hasNumberOfColumnsLessThan(8);
   * </code></pre>
   * <p>
   * Example where the assertion verifies that the row at end point of the first change has less than 8 columns :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(changes).change().rowAtEndPoint().hasNumberOfColumnsLessThan(8);
   * </code></pre>
   *
   * @param expected The number to compare to the number of columns.
   * @return {@code this} assertion object.
   * @throws AssertionError If the number of columns is greater than or equal to the number in parameter.
   * @see org.assertj.db.api.AbstractDbAssert#hasNumberOfColumnsLessThan(int)
   * @see org.assertj.db.api.AbstractRowAssert#hasNumberOfColumnsLessThan(int)
   * @see org.assertj.db.api.ChangeRowAssert#hasNumberOfColumnsLessThan(int)
   * @since 1.1.0
   */
  T hasNumberOfColumnsLessThan(int expected);

  /**
   * Verifies that the number of columns is greater than or equal to the number in parameter.
   * <p>
   * Example where the assertion verifies that the table has at least 8 columns :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).hasNumberOfColumnsGreaterThanOrEqualTo(8);
   * </code></pre>
   * <p>
   * Example where the assertion verifies that the first row of the table has at least 8 columns :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).row().hasNumberOfColumnsGreaterThanOrEqualTo(8);
   * </code></pre>
   * <p>
   * Example where the assertion verifies that the row at end point of the first change has at least 8 columns :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(changes).change().rowAtEndPoint().hasNumberOfColumnsGreaterThanOrEqualTo(8);
   * </code></pre>
   *
   * @param expected The number to compare to the number of columns.
   * @return {@code this} assertion object.
   * @throws AssertionError If the number of columns is less than the number in parameter.
   * @see org.assertj.db.api.AbstractDbAssert#hasNumberOfColumnsGreaterThanOrEqualTo(int)
   * @see org.assertj.db.api.AbstractRowAssert#hasNumberOfColumnsGreaterThanOrEqualTo(int)
   * @see org.assertj.db.api.ChangeRowAssert#hasNumberOfColumnsGreaterThanOrEqualTo(int)
   * @since 1.1.0
   */
  T hasNumberOfColumnsGreaterThanOrEqualTo(int expected);

  /**
   * Verifies that the number of columns is less than or equal to the number in parameter.
   * <p>
   * Example where the assertion verifies that the table has at most 8 columns :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).hasNumberOfColumnsLessThanOrEqualTo(8);
   * </code></pre>
   * <p>
   * Example where the assertion verifies that the first row of the table has at most 8 columns :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).row().hasNumberOfColumnsLessThanOrEqualTo(8);
   * </code></pre>
   * <p>
   * Example where the assertion verifies that the row at end point of the first change has at most 8 columns :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(changes).change().rowAtEndPoint().hasNumberOfColumnsLessThanOrEqualTo(8);
   * </code></pre>
   *
   * @param expected The number to compare to the number of columns.
   * @return {@code this} assertion object.
   * @throws AssertionError If the number of columns is greater than the number in parameter.
   * @see org.assertj.db.api.AbstractDbAssert#hasNumberOfColumnsLessThanOrEqualTo(int)
   * @see org.assertj.db.api.AbstractRowAssert#hasNumberOfColumnsLessThanOrEqualTo(int)
   * @see org.assertj.db.api.ChangeRowAssert#hasNumberOfColumnsLessThanOrEqualTo(int)
   * @since 1.1.0
   */
  T hasNumberOfColumnsLessThanOrEqualTo(int expected);
}
