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
package org.assertj.db.api.assertions;

/**
 * Defines the assertion method on the name of a column.
 *
 * @param <T> The "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/1IZIRcY"
 *            target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *            for more details.
 * @author Régis Pouiller
 */
public interface AssertOnColumnName<T extends AssertOnColumnName<T>> {

  /**
   * Verifies that the name of a column is equal to the parameter.
   * <p>
   * Example where the assertion verifies that the column name of the first {@code Column} of the {@code Table} is equal to
   * "title" :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).column().hasColumnName("title");
   * </code></pre>
   * <p>
   * Example where the assertion verifies that the first value of the first {@code Row} of the {@code Table} is equal to
   * "title" :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).row().value().hasColumnName("title");
   * </code></pre>
   * <p>
   * Example where the assertion verifies that the column name of the first {@code Column} of the {@code Table} is equal to
   * "title" :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(changes).change(1).column().hasColumnName("title");
   * </code></pre>
   * <p>
   * Example where the assertion verifies that the first value of the first {@code Row} of the {@code Table} is equal to
   * "title" :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(changes).change(1).row().value().hasColumnName("title");
   * </code></pre>
   *
   * @param columnName The expected column name.
   * @return {@code this} assertion object.
   * @throws AssertionError If the column name is not equal to the parameter.
   * @see org.assertj.db.api.AbstractColumnAssert#hasColumnName(String)
   * @see org.assertj.db.api.AbstractRowValueAssert#hasColumnName(String)
   * @see org.assertj.db.api.ChangeColumnAssert#hasColumnName(String)
   * @see org.assertj.db.api.ChangeRowValueAssert#hasColumnName(String)
   */
  T hasColumnName(String columnName);
}
