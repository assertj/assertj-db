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
 * Defines the assertion method on the number of columns.
 *
 * @param <T> The "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/anMa4g"
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
  public T hasNumberOfColumns(int expected);
}
