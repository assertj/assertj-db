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
 * Defines the assertion methods on a modified column.
 *
 * @param <T> The "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/anMa4g"
 *            target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *            for more details.
 * @author Régis Pouiller
 */
public interface AssertOnModifiedColumn<T extends AssertOnModifiedColumn<T>> {

  /**
   * Verifies that the column is modified between the start point and the end point.
   * <p>
   * Example where the assertion verifies that :
   * </p>
   * <pre>
   * <code class='java'>
   * assertThat(changes).change(1).column().isModified();
   * </code>
   * </pre>
   *
   * @return {@code this} assertion object.
   * @throws AssertionError If the column is not modified between the start point and the end point.
   * @see org.assertj.db.api.ChangeColumnAssert#isModified()
   */
  public T isModified();

  /**
   * Verifies that the column is not modified between the start point and the end point.
   * <p>
   * Example where the assertion verifies that :
   * </p>
   * <pre>
   * <code class='java'>
   * assertThat(changes).change(1).column().isNotModified();
   * </code>
   * </pre>
   *
   * @return {@code this} assertion object.
   * @throws AssertionError If the column is modified between the start point and the end point.
   * @see org.assertj.db.api.ChangeColumnAssert#isNotModified()
   */
  public T isNotModified();
}
