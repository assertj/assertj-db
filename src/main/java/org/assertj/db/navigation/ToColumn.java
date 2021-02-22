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
 * Copyright 2015-2021 the original author or authors.
 */
package org.assertj.db.navigation;

import org.assertj.db.navigation.element.ColumnElement;

/**
 * Defines methods to navigate to a {@link org.assertj.db.type.Column}.
 * <p>The different methods return an assertion on one column {@link org.assertj.db.navigation.element.ColumnElement}.</p>
 * <p>These methods exists when navigating (at the beginning {@code assertThat()}) from changes, from a {@link org.assertj.db.type.Table} or from a {@link org.assertj.db.type.Request}.</p>
 * <p>As shown in the diagram below, if navigating from table or request, it is possible to call the method to navigate to a {@link org.assertj.db.navigation.element.ColumnElement} from :</p>
 * <ul>
 *     <li>a table ({@link org.assertj.db.api.TableAssert})</li>
 *     <li>a request ({@link org.assertj.db.api.RequestAssert})</li>
 *     <li>a column ({@link org.assertj.db.api.AbstractColumnAssert})</li>
 *     <li>a value of a column ({@link org.assertj.db.api.AbstractColumnValueAssert})</li>
 *     <li>a row ({@link org.assertj.db.api.AbstractRowAssert})</li>
 *     <li>a value of a row ({@link org.assertj.db.api.AbstractRowValueAssert})</li>
 * </ul>
 * <p>
 * <img src="../../../../../images/table_and_request/navigation/diagramOnNavigationWithTableOrRequest_ToColumn.png" alt="diagram with navigation to column" height="45%" width="45%" >
 * </p>
 * <p>If navigating from table or request, it is important to keep in mind that the methods are executed from the point of view of the last instance with assertion methods on a table ({@link org.assertj.db.api.TableAssert}) or on a request ({@link org.assertj.db.api.RequestAssert}).<br>
 * So all the lines of code below are equivalent : they point on the column at index 1 (as usual, the list start at index 0).
 * </p>
 * <pre>
 * <code class='java'>
 * assertThat(table_or_request).column(1)......;                                   // Point directly on the column at index 1
 * assertThat(table_or_request).column().returnToColumn().column()......;          // Use the returnToColumn() method to return to origin
 *                                                                                 // to return on the table or request and access to the next/second column of the list
 * assertThat(table_or_request).column().column()......;                           // Same as precedent but returnToColumn() is implicit
 * assertThat(table_or_request).column().column(1)......;                          // The method with the index can be call too
 * assertThat(table_or_request).column(2).column(0).column(1)......;               // Idem
 * assertThat(table_or_request).column().value().column()......;
 * assertThat(table_or_request).column().value().column(1)......;
 * // Equivalent to the precedent but with the use of the methods to return to origin
 * assertThat(table_or_request).column().value().returnToColumn().returnToChange().column(1)......;
 * </code>
 * </pre>
 * <p>As shown in the diagram below, if navigating from changes, it is possible to call the method to navigate to a {@link org.assertj.db.navigation.element.ColumnElement} from :</p>
 * <ul>
 *     <li>a change ({@link org.assertj.db.api.ChangeAssert})</li>
 *     <li>a column of a change ({@link org.assertj.db.api.ChangeColumnAssert})</li>
 *     <li>a value of a column of a change ({@link org.assertj.db.api.ChangeColumnValueAssert})</li>
 *     <li>a row of a change ({@link org.assertj.db.api.ChangeRowAssert})</li>
 *     <li>a value of a row of a change ({@link org.assertj.db.api.ChangeRowValueAssert})</li>
 * </ul>
 * <p>
 * <img src="../../../../../images/changes/navigation/diagramOnNavigationWithChanges_ToColumn.png" alt="diagram with navigation to column" height="55%" width="55%" >
 * </p>
 * <p>If navigating from changes, it is important to keep in mind that the methods are executed from the point of view of the last instance with assertion methods on a change ({@link org.assertj.db.api.ChangeAssert}).<br>
 * So all the lines of code below are equivalent : they point on the column at index 1 (as usual, the list start at index 0).
 * </p>
 * <pre>
 * <code class='java'>
 * assertThat(changes).change().column(1)......;                                   // Point directly on the column at index 1
 * // Use the returnToChange() method to return on the change and access to the next/second column of the list
 * assertThat(changes).change().column().returnToChange().column()......;
 * assertThat(changes).change().column().column()......;                           // Same as precedent but returnToChange() is implicit
 * assertThat(changes).change().column().column(1)......;                          // The method with the index can be call too
 * assertThat(changes).change().column(2).column(0).column(1)......;               // Idem
 * assertThat(changes).change().column().value().column()......;
 * assertThat(changes).change().column().value().column(1)......;
 * // Equivalent to the precedent but with the use of the methods to return to origin
 * assertThat(changes).change().column().value().returnToColumn().returnToChange().column(1)......;
 * </code>
 * </pre>
 *
 * @author Régis Pouiller
 *
 * @param <C> The class of a assertion on a column (an sub-class of {@link org.assertj.db.navigation.element.ColumnElement}).
 */
public interface ToColumn<C extends ColumnElement> extends Navigation {

  /**
   * Returns assertion methods on the next {@link org.assertj.db.type.Column} in the list of {@link org.assertj.db.type.Column}s.
   *
   * @return An object to make assertions on the next {@link org.assertj.db.type.Column}.
   * @throws org.assertj.db.exception.AssertJDBException If there are no more {@link org.assertj.db.type.Column} in the list of {@link org.assertj.db.type.Column}s.
   * @see org.assertj.db.api.TableAssert#column()
   * @see org.assertj.db.api.RequestAssert#column()
   * @see org.assertj.db.api.AbstractColumnAssert#column()
   * @see org.assertj.db.api.AbstractColumnValueAssert#column()
   * @see org.assertj.db.api.AbstractRowAssert#column()
   * @see org.assertj.db.api.AbstractRowValueAssert#column()
   * @see org.assertj.db.api.ChangeAssert#column()
   * @see org.assertj.db.api.ChangeColumnAssert#column()
   * @see org.assertj.db.api.ChangeColumnValueAssert#column()
   * @see org.assertj.db.api.ChangeRowAssert#column()
   * @see org.assertj.db.api.ChangeRowValueAssert#column()
   */
  C column();

  /**
   * Returns assertion methods on the {@link org.assertj.db.type.Column} at the {@code index} in parameter.
   *
   * @param index The index corresponding to the {@link org.assertj.db.type.Column}.
   * @return An object to make assertions on the {@link org.assertj.db.type.Column}.
   * @throws org.assertj.db.exception.AssertJDBException If the {@code index} is out of the bounds.
   * @see org.assertj.db.api.TableAssert#column(int)
   * @see org.assertj.db.api.RequestAssert#column(int)
   * @see org.assertj.db.api.AbstractColumnAssert#column(int)
   * @see org.assertj.db.api.AbstractColumnValueAssert#column(int)
   * @see org.assertj.db.api.AbstractRowAssert#column(int)
   * @see org.assertj.db.api.AbstractRowValueAssert#column(int)
   * @see org.assertj.db.api.ChangeAssert#column(int)
   * @see org.assertj.db.api.ChangeColumnAssert#column(int)
   * @see org.assertj.db.api.ChangeColumnValueAssert#column(int)
   * @see org.assertj.db.api.ChangeRowAssert#column(int)
   * @see org.assertj.db.api.ChangeRowValueAssert#column(int)
   */
  C column(int index);

  /**
   * Returns assertion methods on the {@link org.assertj.db.type.Column} corresponding to the column name in parameter.
   *
   * @param columnName The column name.
   * @return An object to make assertions on the {@link org.assertj.db.type.Column}.
   * @throws NullPointerException If the column name in parameter is {@code null}.
   * @throws org.assertj.db.exception.AssertJDBException If there is no {@link org.assertj.db.type.Column} with this name.
   * @see org.assertj.db.api.TableAssert#column(String)
   * @see org.assertj.db.api.RequestAssert#column(String)
   * @see org.assertj.db.api.AbstractColumnAssert#column(String)
   * @see org.assertj.db.api.AbstractColumnValueAssert#column(String)
   * @see org.assertj.db.api.AbstractRowAssert#column(String)
   * @see org.assertj.db.api.AbstractRowValueAssert#column(String)
   * @see org.assertj.db.api.ChangeAssert#column(String)
   * @see org.assertj.db.api.ChangeColumnAssert#column(String)
   * @see org.assertj.db.api.ChangeColumnValueAssert#column(String)
   * @see org.assertj.db.api.ChangeRowAssert#column(String)
   * @see org.assertj.db.api.ChangeRowValueAssert#column(String)
   */
  C column(String columnName);
}
