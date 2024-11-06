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
 * Defines the assertion methods on a primary key.
 *
 * @param <T> The "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/1IZIRcY"
 *            target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *            for more details.
 * @author Régis Pouiller
 */
public interface AssertOnPrimaryKey<T extends AssertOnPrimaryKey<T>> {

  /**
   * Verifies that the columns og the primary key of the rows of the change is the same as the parameters.
   * <p>
   * Example where the assertion verifies that primary key is the column called id :
   * </p>
   * <pre>
   * <code class='java'>
   * assertThat(changes).change(1).hasPksNames("id");
   * </code>
   * </pre>
   *
   * @param names The names of the primary key associated with the rows of the change.
   * @return {@code this} assertion object.
   * @throws AssertionError                 If the columns of the primary key are different to the names in parameters.
   * @throws java.lang.NullPointerException If one of the names in parameters is {@code null}.
   * @see org.assertj.db.api.ChangeAssert#hasPksNames(String...)
   */
  T hasPksNames(String... names);

  /**
   * Verifies that the values of the primary key of the rows of the change are the same as the parameters.
   * <p>
   * Example where the assertion verifies that primary key have the value 1 :
   * </p>
   * <pre>
   * <code class='java'>
   * assertThat(changes).change(1).hasPksValues(1);
   * </code>
   * </pre>
   *
   * @param values The values of the primary key associated with the rows of the change.
   * @return {@code this} assertion object.
   * @throws AssertionError If the values of the primary key are different to the values in parameters.
   * @see org.assertj.db.api.ChangeAssert#hasPksValues(Object...)
   */
  T hasPksValues(Object... values);
}
