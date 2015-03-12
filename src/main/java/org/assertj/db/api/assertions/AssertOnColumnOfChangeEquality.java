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
 * Interface that represents a assert on a equality on a column of a change.
 *
 * @param <T> The "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/anMa4g"
 *            target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *            for more details.
 * @author Régis Pouiller
 */
public interface AssertOnColumnOfChangeEquality<T extends AssertOnColumnOfChangeEquality<T>> {

  /**
   * Verifies that the values at the start point and the end point are equal to the parameter.
   * <p>
   * Example where the assertion verifies that the values of the first {@code Column} of the {@code Table} are equal to
   * "Ellen Louise Ripley" :
   * </p>
   * <pre><code class='java'>
   * assertThat(changes).change(1).column().hasValuesEqualTo("Ellen Louise Ripley");
   * </code></pre>
   *
   * @param expected The expected value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the values are not equal to the parameter.
   */
  public T hasValuesEqualTo(Object expected);

  /**
   * Verifies that the values at the start point and the end point are equal to a parameter for start point and a parameter for end point.
   * <p>
   * Example where the assertion verifies that the values of the first {@code Column} of the {@code Table} are equal to
   * "Sigourney" at start point and "Susan Alexandra" at end point :
   * </p>
   * <pre><code class='java'>
   * assertThat(changes).change(1).column().hasValuesEqualTo("Sigourney", "Susan Alexandra");
   * </code></pre>
   *
   * @param expectedAtStartPoint The expected value at start point.
   * @param expectedAtEndPoint   The expected value at end point.
   * @return {@code this} assertion object.
   * @throws AssertionError If the values are not equal to the parameters.
   */
  public T hasValuesEqualTo(Object expectedAtStartPoint, Object expectedAtEndPoint);
}
