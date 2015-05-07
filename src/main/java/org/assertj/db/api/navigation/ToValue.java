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
package org.assertj.db.api.navigation;

/**
 * Defines methods to navigate to a value.
 * <p>The different methods return an assertion on one value {@link org.assertj.db.api.navigation.ValueAssert}.</p>
 * <p>These methods exists when navigating (at the beginning {@code assertThat()}) from changes, from a {@link org.assertj.db.type.Table} or from a {@link org.assertj.db.type.Request}.</p>
 * <p>As shown in the diagram below, if navigating from table or request, it is possible to call the method to navigate to a {@link org.assertj.db.api.navigation.ValueAssert} from :</p>
 * <ul>
 *     <li>a column ({@link org.assertj.db.api.AbstractColumnAssert})</li>
 *     <li>a value of a column ({@link org.assertj.db.api.AbstractColumnValueAssert})</li>
 *     <li>a row ({@link org.assertj.db.api.AbstractRowAssert})</li>
 *     <li>a value of a row ({@link org.assertj.db.api.AbstractRowValueAssert})</li>
 * </ul>
 * <p>
 * <img src="https://raw.githubusercontent.com/joel-costigliola/assertj-db/master/doc/table_and_request/navigation/diagramOnNavigationWithTableOrRequest_ToValue.png" alt="diagram with navigation to column" height="45%" width="45%" >
 * </p>
 * <p>If navigating from table or request, it is important to keep in mind that the methods are executed from the point of view of the last instance with assertion methods on a column ({@link org.assertj.db.api.AbstractColumnAssert}) or on a row ({@link org.assertj.db.api.AbstractRowAssert}).<br>
 * So all the lines of code below are equivalent : they point on the value at index 1 (as usual, the list start at index 0) of first column.
 * </p>
 * <pre>
 * <code class='java'>
 * assertThat(table_or_request).column().value(1)......;                                  // Point directly on the value at index 1
 * assertThat(table_or_request).column().value().returnToOrigin().value()......;          // Use the returnToOrigin() method of AbstractAssertWithOrigin
 *                                                                                        // to return on the table or request and access to the next/second value of the list
 * assertThat(table_or_request).column().value().value()......;                           // Same as precedent but returnToOrigin() is implicit
 * assertThat(table_or_request).column().value().value(1)......;                          // The method with the index can be call too
 * assertThat(table_or_request).column().value(2).value(0).value(1)......;                // Idem
 * assertThat(table_or_request).column().value().column().value(1)......;
 * // Equivalent to the precedent but with the use of the returnToOrigin() method of AbstractAssertWithOrigin
 * assertThat(table_or_request).column().value().returnToOrigin().returnToOrigin().column().value(1)......;
 * </code>
 * </pre>
 * <p>As shown in the diagram below, if navigating from changes, it is possible to call the method to navigate to a {@link org.assertj.db.api.navigation.ValueAssert} from :</p>
 * <ul>
 *     <li>a row of a change ({@link org.assertj.db.api.ChangeRowAssert})</li>
 *     <li>a value of a row of a change ({@link org.assertj.db.api.ChangeRowValueAssert})</li>
 * </ul>
 * <p>
 * <img src="https://raw.githubusercontent.com/joel-costigliola/assertj-db/master/doc/changes/navigation/diagramOnNavigationWithChanges_ToValue.png" alt="diagram with navigation to column" height="55%" width="55%" >
 * </p>
 * <p>If navigating from changes, it is important to keep in mind that the methods are executed from the point of view of the last instance with assertion methods on a row of a change ({@link org.assertj.db.api.ChangeRowAssert}).<br>
 * So all the lines of code below are equivalent : they point on the value at index 1 (as usual, the list start at index 0) of first row.
 * </p>
 * <pre>
 * <code class='java'>
 * assertThat(changes).change().row().value(1)......;                                   // Point directly on the value at index 1
 * // Use the returnToOrigin() method of AbstractAssertWithOrigin to return on the row and access to the next/second value of the list
 * assertThat(changes).change().row().value().returnToOrigin().value()......;
 * assertThat(changes).change().row().value().value()......;                            // Same as precedent but returnToOrigin() is implicit
 * assertThat(changes).change().row().value().value(1)......;                           // The method with the index can be call too
 * assertThat(changes).change().row().value(2).value(0).value(1)......;                 // Idem
 * assertThat(changes).change().row().value().change(0).row().value(1)......;
 * // Equivalent to the precedent but with the use of the returnToOrigin() method of AbstractAssertWithOrigin
 * assertThat(changes).change().row().value().returnToOrigin().returnToOrigin().returnToOrigin().change(0).row().value(1)......;
 * </code>
 * </pre>
 *
 * @author Régis Pouiller
 *
 * @param <V> The class of a assertion on a value (an sub-class of {@link org.assertj.db.api.navigation.ValueAssert}).
 */
public interface ToValue<V extends ValueAssert> {

  /**
   * Returns assertion methods on the next value in the list of values.
   *
   * @return An object to make assertions on the next value.
   * @throws org.assertj.db.exception.AssertJDBException If there are no more value in the list of values.
   * @see org.assertj.db.api.AbstractColumnAssert#value()
   * @see org.assertj.db.api.AbstractColumnValueAssert#value()
   * @see org.assertj.db.api.AbstractRowAssert#value()
   * @see org.assertj.db.api.AbstractRowValueAssert#value()
   * @see org.assertj.db.api.ChangeRowAssert#value()
   * @see org.assertj.db.api.ChangeRowValueAssert#value()
   */
  public V value();

  /**
   * Returns assertion methods on the value at the {@code index} in parameter.
   *
   * @param index The index corresponding to the value.
   * @return An object to make assertions on the value.
   * @throws org.assertj.db.exception.AssertJDBException If the {@code index} is out of the bounds.
   * @see org.assertj.db.api.AbstractColumnAssert#value(int)
   * @see org.assertj.db.api.AbstractColumnValueAssert#value(int)
   * @see org.assertj.db.api.AbstractRowAssert#value(int)
   * @see org.assertj.db.api.AbstractRowValueAssert#value(int)
   * @see org.assertj.db.api.ChangeRowAssert#value(int)
   * @see org.assertj.db.api.ChangeRowValueAssert#value(int)
   */
  public V value(int index);
}
