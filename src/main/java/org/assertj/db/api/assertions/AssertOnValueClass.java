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
 * Copyright 2015-2025 the original author or authors.
 */
package org.assertj.db.api.assertions;

/**
 * Defines the assertion method on the class of a value.
 *
 * @param <T> The "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/1IZIRcY"
 *            target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *            for more details.
 * @author Régis Pouiller
 * @since 1.1.0
 */
public interface AssertOnValueClass<T extends AssertOnValueClass<T>> {

  /**
   * Verifies that the class of the value is equal to the class in parameter.
   * <p>
   * Example where the assertion verifies that the value in the {@code Column} called "title" of the second {@code Row}
   * of the {@code Table} is of class {@code String} :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(table).row(1).value(&quot;title&quot;).isOfClass(String.class);
   * </code>
   * </pre>
   * <p>
   * Example where the assertion verifies that the value in the {@code Column} called "title" of the {@code Row} at end point
   * of the first {@code Change} is of class {@code String} :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change().rowAtEndPoint().value(&quot;title&quot;).isOfClass(String.class);
   * </code>
   * </pre>
   *
   * @param expected The expected class to compare to.
   * @return {@code this} assertion object.
   * @throws AssertionError If the class of the value is different to the class in parameter.
   * @see org.assertj.db.api.AbstractValueAssert#isOfClass(Class)
   * @see org.assertj.db.api.AbstractAssertWithValues#isOfClass(Class)
   * @since 1.1.0
   */
  T isOfClass(Class<?> expected);
}
