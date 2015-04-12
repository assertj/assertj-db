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
 * Defines the assertion method on the existence of a row of a change.
 *
 * @param <T> The "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/anMa4g"
 *            target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *            for more details.
 * @author Régis Pouiller
 */
public interface AssertOnRowOfChangeExistence<T extends AssertOnRowOfChangeExistence<T>> {

  /**
   * Verifies that the row of the change exists.
   * <p>
   * Example where the assertion verifies that the row at end point of the first change are equal exists :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(changes).change().rowAtEndPoint().exists();
   * </code></pre>
   *
   * @return {@code this} assertion object.
   * @throws AssertionError If the row of the change does not exist.
   * @see org.assertj.db.api.ChangeRowAssert#exists()
   */
  public T exists();

  /**
   * Verifies that the row of the change does not exist.
   * <p>
   * Example where the assertion verifies that the row at end point of the first change are equal does not exist :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(changes).change().rowAtEndPoint().doesNotExist();
   * </code></pre>
   *
   * @return {@code this} assertion object.
   * @throws AssertionError If the row of the change exists.
   * @see org.assertj.db.api.ChangeRowAssert#doesNotExist()
   */
  public T doesNotExist();
}
