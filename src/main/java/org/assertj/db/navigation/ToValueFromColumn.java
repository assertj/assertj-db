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
package org.assertj.db.navigation;

import org.assertj.db.navigation.element.ValueElement;

/**
 * Defines methods to navigate to a value from a {@link org.assertj.db.type.Column}
 * (a column from a {@link org.assertj.db.type.Change}}.
 * <p>The different methods return an assertion on one value {@link org.assertj.db.navigation.element.ValueElement}.</p>
 * <p>These methods exists when navigating (at the beginning {@code assertThat()}) from changes.</p>
 * <p>As shown in the diagram below, if navigating from changes, it is possible to call the method to navigate to a {@link org.assertj.db.navigation.element.ColumnElement} from :</p>
 * <ul>
 *     <li>a column of a change ({@link org.assertj.db.api.ChangeColumnAssert})</li>
 *     <li>a value of a column of a change ({@link org.assertj.db.api.ChangeColumnValueAssert})</li>
 * </ul>
 * <p>
 * <img src="../../../../../images/changes/navigation/diagramOnNavigationWithChanges_ToValue_FromColumn.png" alt="diagram with navigation to column" height="55%" width="55%" >
 * </p>
 * <p>It is important to keep in mind that the methods are executed from the point of view of the last instance with assertion methods on a column of a change ({@link org.assertj.db.api.ChangeAssert}).<br>
 * So all the lines of code below are equivalent : they point on the value at end point of first column.
 * </p>
 * <pre>
 * <code class='java'>
 * assertThat(changes).change().column().valueAtEndPoint()......;                                           // Point directly on the value at end point
 * // Use the returnToColumn() method to return on the column and access to the value at the end point
 * assertThat(changes).change().column().valueAtStartPoint().returnToColumn().valueAtEndPoint()......;
 * assertThat(changes).change().column().valueAtStartPoint().valueAtEndPoint()......;                       // Same as precedent but returnToColumn() is implicit
 * assertThat(changes).change().row().value().change(0).column().valueAtEndPoint()......;
 * // Equivalent to the precedent but with the use of the methods to return to origin
 * assertThat(changes).change().row().value().returnToRow().returnToChange().returnToChanges().change(0).column().value(1)......;
 * </code>
 * </pre>
 *
 * @author Régis Pouiller
 *
 * @param <V> The class of a assertion on a value (an sub-class of {@link org.assertj.db.navigation.element.ValueElement}).
 */
public interface ToValueFromColumn<V extends ValueElement> {

  /**
   * Returns assertion methods on the value at the start point.
   *
   * @return An object to make assertions on the next value.
   * @see org.assertj.db.api.ChangeColumnAssert#valueAtStartPoint
   * @see org.assertj.db.api.ChangeColumnValueAssert#valueAtStartPoint
   */
  public V valueAtStartPoint();

  /**
   * Returns assertion methods on the value at the end point.
   *
   * @return An object to make assertions on the value.
   * @see org.assertj.db.api.ChangeColumnAssert#valueAtEndPoint
   * @see org.assertj.db.api.ChangeColumnValueAssert#valueAtEndPoint
   */
  public V valueAtEndPoint();
}
