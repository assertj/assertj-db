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
package org.assertj.db.api.navigation;

/**
 * Defines methods to navigate to a modified {@link org.assertj.db.type.Column} from a {@link org.assertj.db.type.Change}.
 * <p>The different methods return an assertion on one column {@link org.assertj.db.api.navigation.ColumnAssert}.</p>
 * <p>These methods exists when navigating (at the beginning {@code assertThat()}) from changes.</p>
 * <p>The difference with {@link org.assertj.db.api.navigation.ToColumn} is that {@link org.assertj.db.api.navigation.ToColumn}
 * allows to navigate in all the columns of a {@link org.assertj.db.type.Change} and the methods
 * of {@link org.assertj.db.api.navigation.ToColumnFromChange} count only the columns
 * with a modification between the start point and the end point.
 * </p>
 * <p>As shown in the diagram below, it is possible to call the method to navigate to a {@link org.assertj.db.api.navigation.ColumnAssert} from :</p>
 * <ul>
 *     <li>a change ({@link org.assertj.db.api.ChangeAssert})</li>
 *     <li>a column of a change ({@link org.assertj.db.api.ChangeColumnAssert})</li>
 *     <li>a value of a column of a change ({@link org.assertj.db.api.ChangeColumnValueAssert})</li>
 *     <li>a row of a change ({@link org.assertj.db.api.ChangeRowAssert})</li>
 *     <li>a value of a row of a change ({@link org.assertj.db.api.ChangeRowValueAssert})</li>
 * </ul>
 * <p>
 * <img src="https://raw.githubusercontent.com/joel-costigliola/assertj-db/master/doc/changes/navigation/diagramOnNavigationWithChanges_ToColumn.png" alt="diagram with navigation to column" height="55%" width="55%" >
 * </p>
 * <p>It is important to keep in mind that the methods are executed from the point of view of the last instance with assertion methods on a change ({@link org.assertj.db.api.ChangeAssert}).<br>
 * So all the lines of code below are equivalent : they point on the column at index 1 (as usual, the list start at index 0) among the modified columns.
 * </p>
 * <pre>
 * <code class='java'>
 * assertThat(changes).change().columnAmongTheModifiedOnes(1)......;                                                    // Point directly on the column at index 1
 * // Use the returnToChange() method to return on the change and access to the next/second column of the list
 * assertThat(changes).change().columnAmongTheModifiedOnes().returnToChange().columnAmongTheModifiedOnes()......;
 * assertThat(changes).change().columnAmongTheModifiedOnes().columnAmongTheModifiedOnes()......;                        // Same as precedent but returnToChange() is implicit
 * assertThat(changes).change().column().columnAmongTheModifiedOnes(1)......;                                           // The method with the index can be call too
 * assertThat(changes).change().columnAmongTheModifiedOnes(2).column(0).columnAmongTheModifiedOnes(1)......;            // Idem
 * assertThat(changes).change().columnAmongTheModifiedOnes().value().columnAmongTheModifiedOnes()......;
 * assertThat(changes).change().column().value().columnAmongTheModifiedOnes(1)......;
 * // Equivalent to the precedent but with the use of the methods to return to origin
 * assertThat(changes).change().columnAmongTheModifiedOnes().value().returnToColumn().returnToChange().columnAmongTheModifiedOnes(1)......;
 * </code>
 * </pre>
 *
 * @author Régis Pouiller
 *
 * @param <C> The class of a assertion on a column (an sub-class of {@link org.assertj.db.api.navigation.ColumnAssert}).
 */
public interface ToColumnFromChange<C extends ColumnAssert> {

    /**
     * Returns assertion methods on the next {@link org.assertj.db.type.Column} in the list of the modified {@link org.assertj.db.type.Column}s.
     *
     * @return An object to make assertions on the {@link org.assertj.db.type.Column}.
     * @throws org.assertj.db.exception.AssertJDBException If there are no more {@link org.assertj.db.type.Column} among the list of modified {@link org.assertj.db.type.Column}s.
     * @see org.assertj.db.api.ChangeAssert#columnAmongTheModifiedOnes(String)
     * @see org.assertj.db.api.ChangeColumnAssert#columnAmongTheModifiedOnes(String)
     * @see org.assertj.db.api.ChangeColumnValueAssert#columnAmongTheModifiedOnes(String)
     * @see org.assertj.db.api.ChangeRowAssert#columnAmongTheModifiedOnes(String)
     * @see org.assertj.db.api.ChangeRowValueAssert#columnAmongTheModifiedOnes(String)
     */
    public C columnAmongTheModifiedOnes();

    /**
     * Returns assertion methods on the {@link org.assertj.db.type.Column} at the {@code index} in parameter among the modified {@link org.assertj.db.type.Column}s.
     *
     * @param index The index corresponding to the {@link org.assertj.db.type.Column} among the list of modified {@link org.assertj.db.type.Column}s..
     * @return An object to make assertions on the {@link org.assertj.db.type.Column}.
     * @throws org.assertj.db.exception.AssertJDBException If the {@code index} is out of the bounds.
     * @see org.assertj.db.api.ChangeAssert#columnAmongTheModifiedOnes(int)
     * @see org.assertj.db.api.ChangeColumnAssert#columnAmongTheModifiedOnes(int)
     * @see org.assertj.db.api.ChangeColumnValueAssert#columnAmongTheModifiedOnes(int)
     * @see org.assertj.db.api.ChangeRowAssert#columnAmongTheModifiedOnes(int)
     * @see org.assertj.db.api.ChangeRowValueAssert#columnAmongTheModifiedOnes(int)
     */
    public C columnAmongTheModifiedOnes(int index);

    /**
     * Returns assertion methods on the {@link org.assertj.db.type.Column} corresponding to the column name in parameter among the modified {@link org.assertj.db.type.Column}s.
     *
     * @param columnName The column name.
     * @return An object to make assertions on the {@link org.assertj.db.type.Column}.
     * @throws NullPointerException                        If the column name in parameter is {@code null}.
     * @throws org.assertj.db.exception.AssertJDBException If there is no column with this name  among the list of modified {@link org.assertj.db.type.Column}s.
     * @see org.assertj.db.api.ChangeAssert#columnAmongTheModifiedOnes(String)
     * @see org.assertj.db.api.ChangeColumnAssert#columnAmongTheModifiedOnes(String)
     * @see org.assertj.db.api.ChangeColumnValueAssert#columnAmongTheModifiedOnes(String)
     * @see org.assertj.db.api.ChangeRowAssert#columnAmongTheModifiedOnes(String)
     * @see org.assertj.db.api.ChangeRowValueAssert#columnAmongTheModifiedOnes(String)
     */
    public C columnAmongTheModifiedOnes(String columnName);
}
