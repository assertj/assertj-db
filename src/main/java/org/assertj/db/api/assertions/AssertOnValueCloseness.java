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
 * Defines the assertion methods on the closeness of a value.
 *
 * @param <T> The "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/1IZIRcY"
 *            target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *            for more details.
 * @author Régis Pouiller
 */
public interface AssertOnValueCloseness<T extends AssertOnValueCloseness<T>> {

  /**
   * Verifies that the value is close to a number.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is close to number 3 with a tolerance of 1 (that means that all the numbers between 2 and 4
   * inluded are close enough) :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(table).row().value().isCloseTo(3, 1);
   * </code>
   * </pre>
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the {@code Row} at end point
   * of the first {@code Change} is close to number 3 with a tolerance of 0.75 :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change().rowAtEndPoint().value().isCloseTo(3, 0.75);
   * </code>
   * </pre>
   *
   * @param expected The expected number value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not close to the number in parameter.
   * @see org.assertj.db.api.AbstractValueAssert#isCloseTo(Number, Number)
   * @see org.assertj.db.api.AbstractAssertWithValues#isCloseTo(Number, Number)
   */
  T isCloseTo(Number expected, Number tolerance);
}
