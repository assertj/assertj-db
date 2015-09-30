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
 * Defines the assertion method on the class of a column.
 *
 * @param <T> The "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/1IZIRcY"
 *            target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *            for more details.
 * @author Régis Pouiller
 * @since 1.1.0
 */
public interface AssertOnColumnClass<T extends AssertOnColumnClass<T>> {

  /**
   * Verifies that the class of the values of the column is equal to the class in parameter.
   * <p>
   * Example where the assertion verifies that all the values in the {@code Column} called "title" of the {@code Table}
   * is of class {@code String} :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).column(&quot;title&quot;).isOfClass(String.class, false);
   * </code></pre>
   * <p>
   * Example where the assertion verifies that all the values in the {@code Column} called "title" of the {@code Table}
   * is of class {@code String} or not identified (for example {@code null}) :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).column(&quot;title&quot;).isOfClass(String.class, true);
   * </code></pre>
   *
   * @param expected The expected class to compare to.
   * @param lenient {@code true} if the test is lenient : if the class of a value is not identified (for example when the
   *          value is {@code null}), it consider that it is ok.
   * @return {@code this} assertion object.
   * @throws AssertionError If the class of the column is different to the class in parameter.
   * @see org.assertj.db.api.AbstractColumnAssert#isOfClass(Class, boolean)
   * @see org.assertj.db.api.ChangeColumnAssert#isOfClass(Class, boolean)
   * @since 1.1.0
   */
  public T isOfClass(Class<?> expected, boolean lenient);
}
