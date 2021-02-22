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

import org.assertj.db.exception.AssertJDBException;
import org.assertj.db.navigation.element.RowElement;
import org.assertj.db.type.Row;

/**
 * Defines methods to navigate to a {@link org.assertj.db.type.Row}.
 * <p>The different methods return an assertion on one row {@link org.assertj.db.navigation.element.RowElement}.</p>
 * <p>These methods exists when navigating (at the beginning {@code assertThat()}) from a {@link org.assertj.db.type.Table} or from a {@link org.assertj.db.type.Request}.</p>
 * <p>As shown in the diagram below, it is possible to call the method to navigate to a {@link org.assertj.db.navigation.element.RowElement} from :</p>
 * <ul>
 *     <li>a table ({@link org.assertj.db.api.TableAssert})</li>
 *     <li>a request ({@link org.assertj.db.api.RequestAssert})</li>
 *     <li>a column ({@link org.assertj.db.api.AbstractColumnAssert})</li>
 *     <li>a value of a column ({@link org.assertj.db.api.AbstractColumnValueAssert})</li>
 *     <li>a row ({@link org.assertj.db.api.AbstractRowAssert})</li>
 *     <li>a value of a row ({@link org.assertj.db.api.AbstractRowValueAssert})</li>
 * </ul>
 * <p>
 * <img src="../../../../../images/table_and_request/navigation/diagramOnNavigationWithTableOrRequest_ToRow.png" alt="diagram with navigation to row" height="45%" width="45%" >
 * </p>
 * <p>It is important to keep in mind that the methods are executed from the point of view of the last instance with assertion methods on a table ({@link org.assertj.db.api.TableAssert}) or on a request ({@link org.assertj.db.api.RequestAssert}).<br>
 * So all the lines of code below are equivalent : they point on the row at index 1 (as usual, the list start at index 0).
 * </p>
 * <pre>
 * <code class='java'>
 * assertThat(table_or_request).row(1)......;                             // Point directly on the row at index 1
 * assertThat(table).row().returnToTable().row()......;                   // Use the returnToTable() method to return on the table
 *                                                                        // and access to the next/second row of the list
 * assertThat(request).row().returnToRequest().row()......;               // Use the returnToRequest() method to return on the request
 *                                                                        // and access to the next/second row of the list
 * assertThat(table_or_request).row().row()......;                        // Same as two precedent but returnToTable() or returnToRequest() is implicit
 * assertThat(table_or_request).row().row(1)......;                       // The method with the index can be call too
 * assertThat(table_or_request).row(2).row(0).row(1)......;               // Idem
 * assertThat(table_or_request).row().value().row()......;
 * assertThat(table_or_request).row().value().row(1)......;
 * // Equivalent to the precedent but with the use of the methods to return to origin
 * assertThat(table).row().value().returnToRow().returnToTable().row(1)......;
 * assertThat(request).row().value().returnToRow().returnToRequest().row(1)......;
 * </code>
 * </pre>
 *
 * @author Régis Pouiller
 *
 * @param <R> The class of a assertion on a row (an sub-class of {@link org.assertj.db.navigation.element.RowElement}).
 */
public interface ToRow<R extends RowElement> extends Navigation {

  /**
   * Returns assertion methods on the next {@link org.assertj.db.type.Row} in the list of {@link org.assertj.db.type.Row}.
   * 
   * @return An object to make assertions on the next {@link Row}.
   * @throws AssertJDBException If there are no more {@link org.assertj.db.type.Row} in the list of {@link org.assertj.db.type.Row}s.
   * @see org.assertj.db.api.TableAssert#row()
   * @see org.assertj.db.api.RequestAssert#row()
   * @see org.assertj.db.api.AbstractColumnAssert#row()
   * @see org.assertj.db.api.AbstractColumnValueAssert#row()
   * @see org.assertj.db.api.AbstractRowAssert#row()
   * @see org.assertj.db.api.AbstractRowValueAssert#row()
   */
  R row();

  /**
   * Returns assertion methods on the {@link org.assertj.db.type.Row} at the {@code index} in parameter.
   * 
   * @param index The index corresponding to the {@link org.assertj.db.type.Row}.
   * @return An object to make assertions on the {@link org.assertj.db.type.Row}.
   * @throws AssertJDBException If the {@code index} is out of the bounds.
   * @see org.assertj.db.api.TableAssert#row(int)
   * @see org.assertj.db.api.RequestAssert#row(int)
   * @see org.assertj.db.api.AbstractColumnAssert#row(int)
   * @see org.assertj.db.api.AbstractColumnValueAssert#row(int)
   * @see org.assertj.db.api.AbstractRowAssert#row(int)
   * @see org.assertj.db.api.AbstractRowValueAssert#row(int)
   */
  R row(int index);
}
