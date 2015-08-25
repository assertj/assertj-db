/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 * <p>
 * Copyright 2012-2015 the original author or authors.
 */
package org.assertj.db.api.assertions;

/**
 * Defines the assertion method on the equality of a row.
 *
 * @param <T> The "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/1IZIRcY"
 *            target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *            for more details.
 * @author Régis Pouiller
 */
public interface AssertOnRowEquality<T extends AssertOnRowEquality<T>> {

    /**
     * Verifies that the values of a row are equal to values in parameter.
     * <p>
     * Example where the assertion verifies that the values in the first {@code Row} of the {@code Table} are equal to the
     * values in parameter :
     * </p>
     *
     * <pre><code class='java'>
     * assertThat(table).row().hasValues(1, &quot;Text&quot;, TimeValue.of(9, 1));
     * </code></pre>
     * <p>
     * Example where the assertion verifies that the values of the row at end point of the first change are equal to the
     * values in parameter :
     * </p>
     *
     * <pre><code class='java'>
     * assertThat(changes).change().rowAtEndPoint().hasValues(1, &quot;Text&quot;, TimeValue.of(9, 1));
     * </code></pre>
     *
     * @param expected The expected values.
     * @return {@code this} assertion object.
     * @throws AssertionError If the values of the row are not equal to the values in parameters.
     * @see org.assertj.db.api.AbstractRowAssert#hasValues(Object...)
     * @see org.assertj.db.api.ChangeRowAssert#hasValues(Object...)
     */
    public T hasValues(Object... expected);
}
