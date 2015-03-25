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
package org.assertj.db.api.navigation;

/**
 * Defines methods to navigate to a value from a {@link org.assertj.db.type.Row}.
 * <p>The different methods return an assertion on one value {@link org.assertj.db.api.navigation.ValueAssert}.</p>
 *
 * @author Régis Pouiller
 *
 * @param <V> The class of a assertion on a value (an sub-class of {@link org.assertj.db.api.navigation.ValueAssert}).
 */
public interface ToValueFromRow<V extends ValueAssert> {

  /**
   * Returns assertion methods on the value corresponding to the column name in parameter.
   * <p>
   * Example when getting an assertion on the value of the column &quot;title&quot; from a row
   * ({@link ToRow#row()})
   * of a table ({@link org.assertj.db.api.Assertions#assertThat(org.assertj.db.type.Table)}) :
   * </p>
   * <pre>
   * <code class='java'>
   * assertThat(table).row().value(&quot;title&quot;).....;
   * </code>
   * </pre>
   *
   * <p>
   * Example when getting an assertion on the value of the column &quot;title&quot; from another value
   * ({@link ToValue#value()}) from a row :
   * </p>
   * <pre>
   * <code class='java'>
   * assertThat(table).row().value().value(&quot;title&quot;).....;
   * </code>
   * </pre>
   *
   * <p>
   * Example when getting an assertion on the value of the column &quot;title&quot; from a row
   * ({@link org.assertj.db.api.navigation.ToRowFromChange#rowAtEndPoint()})
   * of a change ({@link org.assertj.db.api.navigation.ToChange#change()})
   * of changes ({@link org.assertj.db.api.Assertions#assertThat(org.assertj.db.type.Changes)}) :
   * </p>
   * <pre>
   * <code class='java'>
   * assertThat(changes).change().rowAtEndPoint().value(&quot;title&quot;).....;
   * </code>
   * </pre>
   *
   * <p>
   * Example when getting an assertion on the value of the column &quot;title&quot; from another value from a row :
   * </p>
   * <pre>
   * <code class='java'>
   * assertThat(changes).change().rowAtEndPoint().value().value(&quot;title&quot;).....;
   * </code>
   * </pre>
   *
   * @param columnName The column name.
   * @return An object to make assertions on the value.
   * @throws NullPointerException If the column name in parameter is {@code null}.
   * @throws org.assertj.db.exception.AssertJDBException If there is no column with this name.
   * @see org.assertj.db.api.AbstractRowAssert#value(String)
   * @see org.assertj.db.api.AbstractRowValueAssert#value(String)
   * @see org.assertj.db.api.ChangeRowAssert#value(String)
   * @see org.assertj.db.api.ChangeRowValueAssert#value(String)
   */
  public V value(String columnName);
}
