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
 * Defines methods to navigate to a {@link org.assertj.db.type.Row} from a {@link org.assertj.db.type.Change}.
 * <p>The different methods return an assertion on one row {@link org.assertj.db.api.navigation.RowAssert}.</p>
 * <p>These methods exists when navigating from changes.</p>
 * <p>As shown in the diagram below, it is possible to call the method to navigate to a {@link org.assertj.db.api.navigation.RowAssert} from :</p>
 * <ul>
 *     <li>a change ({@link org.assertj.db.api.ChangeAssert})</li>
 *     <li>a column of a change ({@link org.assertj.db.api.ChangeColumnAssert})</li>
 *     <li>a value of a column of a change ({@link org.assertj.db.api.ChangeColumnValueAssert})</li>
 *     <li>a row of a change ({@link org.assertj.db.api.ChangeRowAssert})</li>
 *     <li>a value of a row of a change ({@link org.assertj.db.api.ChangeRowValueAssert})</li>
 * </ul>
 * <p>
 * <img src="https://raw.githubusercontent.com/joel-costigliola/assertj-db/master/doc/changes/navigation/diagramOnNavigationWithChanges_ToRow.png" alt="diagram with navigation to row" height="55%" width="55%" >
 * </p>
 * <p>It is important to keep in mind that the methods are executed from the point of view of the last instance with assertion methods on a change ({@link org.assertj.db.api.ChangeAssert}).<br>
 * So all the lines of code below are equivalent : they point on the row at end point.
 * </p>
 * <pre>
 * <code class='java'>
 * assertThat(changes).change().rowAtEndPoint()......;                                                    // Point directly on the row at end point
 * // Use the returnToOrigin() method of AbstractAssertWithOrigin to return on the change and access to the row at end point
 * assertThat(changes).change().rowAtStartPoint().returnToOrigin().rowAtEndPoint()......;
 * assertThat(changes).change().rowAtStartPoint().rowAtEndPoint()......;                                  // Same as precedent but returnToOrigin() is implicit
 * assertThat(changes).change().column().rowAtEndPoint()......;                                           // The method can be call from a column
 * assertThat(changes).change().column().value().rowAtEndPoint()......;
 * assertThat(changes).change().column(1).value().rowAtEndPoint()......;
 * // Equivalent to the precedent but with the use of the returnToOrigin() method of AbstractAssertWithOrigin
 * assertThat(changes).change().column(1).value().returnToOrigin().returnToOrigin().rowAtEndPoint()......;
 * </code>
 * </pre>
 *
 * @author Régis Pouiller
 *
 * @param <R> The class of a assertion on a row (an sub-class of {@link org.assertj.db.api.navigation.RowAssert}).
 */
public interface ToRowFromChange<R extends RowAssert> {

  /**
   * Returns assertion methods on the {@link org.assertj.db.type.Row} at start point.
   *
   * @return An object to make assertions on the {@link org.assertj.db.type.Row} at start point.
   */
  public R rowAtStartPoint();

  /**
   * Returns assertion methods on the {@link org.assertj.db.type.Row} at end point.
   *
   * @return An object to make assertions on the {@link org.assertj.db.type.Row} at end point.
   */
  public R rowAtEndPoint();
}
