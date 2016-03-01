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
 * Defines methods to navigate to a value from a {@link org.assertj.db.type.Row}.
 * <p>The different methods return an assertion on one value {@link org.assertj.db.navigation.element.ValueElement}.</p>
 * <p>These methods exists when navigating (at the beginning {@code assertThat()}) from changes, from a {@link org.assertj.db.type.Table} or from a {@link org.assertj.db.type.Request}.</p>
 * <p>As shown in the diagram below, if navigating from table or request, it is possible to call the method to navigate to a {@link org.assertj.db.navigation.element.ValueElement} from :</p>
 * <ul>
 *     <li>a row ({@link org.assertj.db.api.AbstractRowAssert})</li>
 *     <li>a value of a row ({@link org.assertj.db.api.AbstractRowValueAssert})</li>
 * </ul>
 * <p>
 * <img src="../../../../../images/table_and_request/navigation/diagramOnNavigationWithTableOrRequest_ToValue_FromRow.png" alt="diagram with navigation to column" height="45%" width="45%" >
 * </p>
 * <p>If navigating from table or request, it is important to keep in mind that the methods are executed from the point of view of the last instance with assertion methods on a row ({@link org.assertj.db.api.AbstractRowAssert}).<br>
 * So all the lines of code below are equivalent : they point on the value called "name" of first column.
 * </p>
 * <pre>
 * <code class='java'>
 * assertThat(table_or_request).row().value("name")......;                                // Point directly on the value called "name"
 * assertThat(table_or_request).row().value().returnToRow().value("name")......;          // Use the returnToRow() method
 *                                                                                        // to return on the row and access to the value called "name"
 * assertThat(table_or_request).row().value().value("name")......;                        // Same as precedent but returnToRow() is implicit
 * assertThat(table_or_request).row().value(2).value(0).value("name")......;              // Idem
 * assertThat(table_or_request).row().value().row().value("name")......;
 * // Equivalent to the precedent but with the use of the methods to return to origin
 * assertThat(table).row().value().returnToRow().returnToTable().row().value("name")......;
 * assertThat(request).row().value().returnToRow().returnToRequest().row().value("name")......;
 * </code>
 * </pre>
 * <p>As shown in the diagram below, if navigating from changes, it is possible to call the method to navigate to a {@link org.assertj.db.navigation.element.ValueElement} from :</p>
 * <ul>
 *     <li>a row of a change ({@link org.assertj.db.api.ChangeRowAssert})</li>
 *     <li>a value of a row of a change ({@link org.assertj.db.api.ChangeRowValueAssert})</li>
 * </ul>
 * <p>
 * <img src="../../../../../images/changes/navigation/diagramOnNavigationWithChanges_ToValue_FromRow.png" alt="diagram with navigation to column" height="55%" width="55%" >
 * </p>
 * <p>If navigating from changes, it is important to keep in mind that the methods are executed from the point of view of the last instance with assertion methods on a row of a change ({@link org.assertj.db.api.ChangeRowAssert}).<br>
 * So all the lines of code below are equivalent : they point on the value called "name" of first row.
 * </p>
 * <pre>
 * <code class='java'>
 * assertThat(changes).change().row().value("name")......;                                 // Point directly on the value called "name"
 * // Use the returnToRow() method to return on the row and access to the value called "name"
 * assertThat(changes).change().row().value().returnToRow().value("name")......;
 * assertThat(changes).change().row().value().value("name")......;                         // Same as precedent but returnToRow() is implicit
 * assertThat(changes).change().row().value(2).value(0).value("name")......;               // Idem
 * assertThat(changes).change().row().value().change(0).row().value("name")......;
 * // Equivalent to the precedent but with the use of the methods to return to origin
 * assertThat(changes).change().row().value().returnToRow().returnToChange().returnToChanges().change(0).row().value("name")......;
 * </code>
 * </pre>
 *
 * @author Régis Pouiller
 *
 * @param <V> The class of a assertion on a value (an sub-class of {@link org.assertj.db.navigation.element.ValueElement}).
 */
public interface ToValueFromRow<V extends ValueElement> extends Navigation {

  /**
   * Returns assertion methods on the value corresponding to the column name in parameter.
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
  V value(String columnName);
}
