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
 * Defines the assertion method on the a row satisfy conditions.
 *
 * @param <T> The "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/1IZIRcY"
 *            target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *            for more details.
 * @author Julien Roy
 */
public interface AssertOnRowCondition<T extends AssertOnRowCondition<T>> {

  /**
   * Verifies that the values of a row satisfy to conditions in parameter.
   * <p>
   * Example where the assertion verifies that the values in the first {@code Row} of the {@code Table} satisfy to the
   * conditions in parameter :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).row().hasValuesSatisfying(new Condition&lt;String&gt;(v -&gt; v.equals("Weaver"), "isWeaver"));
   * </code></pre>
   * <p>
   * Example where the assertion verifies that the values of the row at end point of the first change are equal to the
   * values in parameter :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(changes).change().rowAtEndPoint().hasValuesSatisfying(new Condition&lt;String&gt;(v -&gt; v.equals("Weaver"), "isWeaver"));
   * </code></pre>
   *
   * @param expected The expected conditions.
   * @return {@code this} assertion object.
   * @throws AssertionError If the values of the row are not satisfy to the conditions in parameters.
   * @see org.assertj.db.api.AbstractRowAssert#hasValuesSatisfying(Object...)
   * @see org.assertj.db.api.ChangeRowAssert#hasValuesSatisfying(Object...)
   */
  T hasValuesSatisfying(Object... expected);
}
