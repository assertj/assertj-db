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
 * Interface containing assertion method on the number of changes.
 *
 * @param <T> The "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/anMa4g"
 *            target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *            for more details.
 * @author Régis Pouiller
 */
public interface AssertOnNumberOfChanges<T extends AssertOnNumberOfChanges<T>> {

  /**
   * Verifies that the number of {@link org.assertj.db.type.Changes} is equal to the number in parameter.
   * <p>
   * Example where the assertion verifies that there are 8 changes :
   * </p>
   * <pre>
   * <code class='java'>
   * assertThat(changes).hasNumberOfChanges(8);
   * </code>
   * </pre>
   *
   * @param expected The number to compare to.
   * @return {@code this} assertion object.
   * @throws AssertionError If the number of changes is different to the number in parameter.
   */
  public T hasNumberOfChanges(int expected);
}
