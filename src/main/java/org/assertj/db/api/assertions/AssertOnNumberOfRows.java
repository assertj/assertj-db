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
package org.assertj.db.api.assertions;

/**
 * Defines the assertion method on the number of rows.
 *
 * @param <T> The "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/1IZIRcY"
 *            target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *            for more details.
 * @author Régis Pouiller
 */
public interface AssertOnNumberOfRows<T extends AssertOnNumberOfRows<T>> {

  /**
   * Verifies that the number of rows is equal to the number in parameter.
   * <p>
   * Example where the assertion verifies that the table has 2 rows :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).hasNumberOfRows(2);
   * </code></pre>
   * <p>
   * Example where the assertion verifies that the column with index 1 of the table has 5 rows :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).column(1).hasNumberOfRows(5);
   * </code></pre>
   *
   * @param expected The number to compare to the number of rows.
   * @return {@code this} assertion object.
   * @throws AssertionError If the number of rows is different to the number in parameter.
   * @see org.assertj.db.api.AbstractDbAssert#hasNumberOfRows(int)
   * @see org.assertj.db.api.AbstractColumnAssert#hasNumberOfRows(int)
   */
  T hasNumberOfRows(int expected);

  /**
   * Verifies that the number of rows is greater than the number in parameter.
   * <p>
   * Example where the assertion verifies that the table has more than 8 rows :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).hasNumberOfRowsGreaterThan(8);
   * </code></pre>
   * <p>
   * Example where the assertion verifies that the first row of the table has more than 8 rows :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).row().hasNumberOfRowsGreaterThan(8);
   * </code></pre>
   * <p>
   * Example where the assertion verifies that the row at end point of the first change has more than 8 rows :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(changes).change().rowAtEndPoint().hasNumberOfRowsGreaterThan(8);
   * </code></pre>
   *
   * @param expected The number to compare to the number of rows.
   * @return {@code this} assertion object.
   * @throws AssertionError If the number of rows is less than or equal to the number in parameter.
   * @see org.assertj.db.api.AbstractDbAssert#hasNumberOfRowsGreaterThan(int)
   * @see org.assertj.db.api.AbstractColumnAssert#hasNumberOfRowsGreaterThan(int)
   * @since 1.1.0
   */
  T hasNumberOfRowsGreaterThan(int expected);

  /**
   * Verifies that the number of rows is less than the number in parameter.
   * <p>
   * Example where the assertion verifies that the table has less than 8 rows :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).hasNumberOfRowsLessThan(8);
   * </code></pre>
   * <p>
   * Example where the assertion verifies that the first row of the table has less than 8 rows :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).row().hasNumberOfRowsLessThan(8);
   * </code></pre>
   * <p>
   * Example where the assertion verifies that the row at end point of the first change has less than 8 rows :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(changes).change().rowAtEndPoint().hasNumberOfRowsLessThan(8);
   * </code></pre>
   *
   * @param expected The number to compare to the number of rows.
   * @return {@code this} assertion object.
   * @throws AssertionError If the number of rows is greater than or equal to the number in parameter.
   * @see org.assertj.db.api.AbstractDbAssert#hasNumberOfRowsLessThan(int)
   * @see org.assertj.db.api.AbstractColumnAssert#hasNumberOfRowsLessThan(int)
   * @since 1.1.0
   */
  T hasNumberOfRowsLessThan(int expected);

  /**
   * Verifies that the number of rows is greater than or equal to the number in parameter.
   * <p>
   * Example where the assertion verifies that the table has at least 8 rows :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).hasNumberOfRowsGreaterThanOrEqualTo(8);
   * </code></pre>
   * <p>
   * Example where the assertion verifies that the first row of the table has at least 8 rows :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).row().hasNumberOfRowsGreaterThanOrEqualTo(8);
   * </code></pre>
   * <p>
   * Example where the assertion verifies that the row at end point of the first change has at least 8 rows :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(changes).change().rowAtEndPoint().hasNumberOfRowsGreaterThanOrEqualTo(8);
   * </code></pre>
   *
   * @param expected The number to compare to the number of rows.
   * @return {@code this} assertion object.
   * @throws AssertionError If the number of rows is less than the number in parameter.
   * @see org.assertj.db.api.AbstractDbAssert#hasNumberOfRowsGreaterThanOrEqualTo(int)
   * @see org.assertj.db.api.AbstractColumnAssert#hasNumberOfRowsGreaterThanOrEqualTo(int)
   * @since 1.1.0
   */
  T hasNumberOfRowsGreaterThanOrEqualTo(int expected);

  /**
   * Verifies that the number of rows is less than or equal to the number in parameter.
   * <p>
   * Example where the assertion verifies that the table has at most 8 rows :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).hasNumberOfRowsLessThanOrEqualTo(8);
   * </code></pre>
   * <p>
   * Example where the assertion verifies that the first row of the table has at most 8 rows :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).row().hasNumberOfRowsLessThanOrEqualTo(8);
   * </code></pre>
   * <p>
   * Example where the assertion verifies that the row at end point of the first change has at most 8 rows :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(changes).change().rowAtEndPoint().hasNumberOfRowsLessThanOrEqualTo(8);
   * </code></pre>
   *
   * @param expected The number to compare to the number of rows.
   * @return {@code this} assertion object.
   * @throws AssertionError If the number of rows is greater than the number in parameter.
   * @see org.assertj.db.api.AbstractDbAssert#hasNumberOfRowsLessThanOrEqualTo(int)
   * @see org.assertj.db.api.AbstractColumnAssert#hasNumberOfRowsLessThanOrEqualTo(int)
   * @since 1.1.0
   */
  T hasNumberOfRowsLessThanOrEqualTo(int expected);
}
