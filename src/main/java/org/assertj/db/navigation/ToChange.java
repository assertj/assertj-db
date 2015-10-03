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

import org.assertj.db.navigation.element.ChangeElement;

/**
 * Defines methods to navigate to an instance with assertion methods on a {@link org.assertj.db.type.Change}.
 * <p>The different methods return an instance with assertion methods on one change ({@link org.assertj.db.api.ChangeAssert}).</p>
 * <p>These methods exists when navigating (at the beginning {@code assertThat()}) from changes.</p>
 * <p>As shown in the diagram below, it is possible to call the method to navigate to a change from :</p>
 * <ul>
 *     <li>changes ({@link org.assertj.db.api.ChangesAssert})</li>
 *     <li>another change ({@link org.assertj.db.api.ChangeAssert})</li>
 *     <li>a column of a change ({@link org.assertj.db.api.ChangeColumnAssert})</li>
 *     <li>a value of a column of a change ({@link org.assertj.db.api.ChangeColumnValueAssert})</li>
 *     <li>a row of a change ({@link org.assertj.db.api.ChangeRowAssert})</li>
 *     <li>a value of a row of a change ({@link org.assertj.db.api.ChangeRowValueAssert})</li>
 * </ul>
 * <p>
 * <img src="../../../../../images/changes/navigation/diagramOnNavigationWithChanges_ToChange.png" alt="diagram with navigation to change" height="50%" width="50%" >
 * </p>
 * <p>It is important to keep in mind that the methods are executed from the point of view of the last instance with assertion methods on changes ({@link org.assertj.db.api.ChangesAssert}).<br>
 * So all the lines of code below are equivalent : they point on the change at index 1 (as usual, the list start at index 0).
 * </p>
 * <pre>
 * <code class='java'>
 * assertThat(changes).change(1)......;                                   // Point directly on the change at index 1
 * assertThat(changes).change().returnToChange().change()......;          // Use the returnToChange() method to return to origin
 *                                                                        // to return on the changes and access to the next/second change of the list
 * assertThat(changes).change().change()......;                           // Same as precedent but returnToChange() is implicit
 * assertThat(changes).change().change(1)......;                          // The method with the index can be call too
 * assertThat(changes).change(2).change(0).change(1)......;               // Idem
 * assertThat(changes).change().column().change()......;
 * assertThat(changes).change().rowAtEndPoint().change(1)......;
 * assertThat(changes).change().column().value().change()......;
 * assertThat(changes).change().rowAtEndPoint().value().change(1)......;
 * // Equivalent to the precedent but with the use of the methods to return to origin
 * assertThat(changes).change().rowAtEndPoint().value().returnToRow().returnToChange().returnToChanges().change(1)......;
 * </code>
 * </pre>
 *
 * @author Régis Pouiller
 *
 * @param <CH> The class of a assertion on a change (an sub-class of {@link org.assertj.db.navigation.element.ChangeElement}).
 */
public interface ToChange<CH extends ChangeElement> {

  /**
   * Returns assertion methods on the next {@link org.assertj.db.type.Change} in the list of changes.
   *
   * @return An object to make assertions on the next {@link org.assertj.db.type.Change}.
   * @throws org.assertj.db.exception.AssertJDBException If there are no more {@link org.assertj.db.type.Change} in the list of changes.
   * @see org.assertj.db.api.ChangesAssert#change()
   * @see org.assertj.db.api.ChangeAssert#change()
   * @see org.assertj.db.api.ChangeColumnAssert#change()
   * @see org.assertj.db.api.ChangeColumnValueAssert#change()
   * @see org.assertj.db.api.ChangeRowAssert#change()
   * @see org.assertj.db.api.ChangeRowValueAssert#change()
   */
  CH change();

  /**
   * Returns assertion methods on the {@link org.assertj.db.type.Change} at the {@code index} in parameter.
   *
   * @param index The index corresponding to the {@link org.assertj.db.type.Change}.
   * @return An object to make assertions on the {@link org.assertj.db.type.Change}.
   * @throws org.assertj.db.exception.AssertJDBException If the {@code index} is out of the bounds.
   * @see org.assertj.db.api.ChangesAssert#change(int)
   * @see org.assertj.db.api.ChangeAssert#change(int)
   * @see org.assertj.db.api.ChangeColumnAssert#change(int)
   * @see org.assertj.db.api.ChangeColumnValueAssert#change(int)
   * @see org.assertj.db.api.ChangeRowAssert#change(int)
   * @see org.assertj.db.api.ChangeRowValueAssert#change(int)
   */
  CH change(int index);

  /**
   * Returns assertion methods on the next {@link org.assertj.db.type.Change} of creation ({@link org.assertj.db.type.ChangeType#CREATION}) in the list of changes.
   *
   * @return An object to make assertions on the next {@link org.assertj.db.type.Change} of creation ({@link org.assertj.db.type.ChangeType#CREATION}).
   * @throws org.assertj.db.exception.AssertJDBException If there are no more {@link org.assertj.db.type.Change} of creation ({@link org.assertj.db.type.ChangeType#CREATION}) in the list of changes.
   * @see org.assertj.db.api.ChangesAssert#changeOfCreation()
   * @see org.assertj.db.api.ChangeAssert#changeOfCreation()
   * @see org.assertj.db.api.ChangeColumnAssert#changeOfCreation()
   * @see org.assertj.db.api.ChangeColumnValueAssert#changeOfCreation()
   * @see org.assertj.db.api.ChangeRowAssert#changeOfCreation()
   * @see org.assertj.db.api.ChangeRowValueAssert#changeOfCreation()
   */
  CH changeOfCreation();

  /**
   * Returns assertion methods on the {@link org.assertj.db.type.Change} of creation ({@link org.assertj.db.type.ChangeType#CREATION}) at the {@code index} in parameter.
   *
   * @param index The index corresponding to the {@link org.assertj.db.type.Change} of creation ({@link org.assertj.db.type.ChangeType#CREATION}).
   * @return An object to make assertions on the {@link org.assertj.db.type.Change} of creation ({@link org.assertj.db.type.ChangeType#CREATION}).
   * @throws org.assertj.db.exception.AssertJDBException If the {@code index} is out of the bounds.
   * @see org.assertj.db.api.ChangesAssert#changeOfCreation(int)
   * @see org.assertj.db.api.ChangeAssert#changeOfCreation(int)
   * @see org.assertj.db.api.ChangeColumnAssert#changeOfCreation(int)
   * @see org.assertj.db.api.ChangeColumnValueAssert#changeOfCreation(int)
   * @see org.assertj.db.api.ChangeRowAssert#changeOfCreation(int)
   * @see org.assertj.db.api.ChangeRowValueAssert#changeOfCreation(int)
   */
  CH changeOfCreation(int index);

  /**
   * Returns assertion methods on the next {@link org.assertj.db.type.Change} of modification ({@link org.assertj.db.type.ChangeType#MODIFICATION}) in the list of changes.
   *
   * @return An object to make assertions on the next {@link org.assertj.db.type.Change} of modification ({@link org.assertj.db.type.ChangeType#MODIFICATION}).
   * @throws org.assertj.db.exception.AssertJDBException If there are no more {@link org.assertj.db.type.Change} of modification ({@link org.assertj.db.type.ChangeType#MODIFICATION}) in the list of changes.
   * @see org.assertj.db.api.ChangesAssert#changeOfModification()
   * @see org.assertj.db.api.ChangeAssert#changeOfModification()
   * @see org.assertj.db.api.ChangeColumnAssert#changeOfModification()
   * @see org.assertj.db.api.ChangeColumnValueAssert#changeOfModification()
   * @see org.assertj.db.api.ChangeRowAssert#changeOfModification()
   * @see org.assertj.db.api.ChangeRowValueAssert#changeOfModification()
   */
  CH changeOfModification();

  /**
   * Returns assertion methods on the {@link org.assertj.db.type.Change} of modification ({@link org.assertj.db.type.ChangeType#MODIFICATION}) at the {@code index} in parameter.
   *
   * @param index The index corresponding to the {@link org.assertj.db.type.Change} of modification ({@link org.assertj.db.type.ChangeType#MODIFICATION}).
   * @return An object to make assertions on the {@link org.assertj.db.type.Change} of modification ({@link org.assertj.db.type.ChangeType#MODIFICATION}).
   * @throws org.assertj.db.exception.AssertJDBException If the {@code index} is out of the bounds.
   * @see org.assertj.db.api.ChangesAssert#changeOfModification(int)
   * @see org.assertj.db.api.ChangeAssert#changeOfModification(int)
   * @see org.assertj.db.api.ChangeColumnAssert#changeOfModification(int)
   * @see org.assertj.db.api.ChangeColumnValueAssert#changeOfModification(int)
   * @see org.assertj.db.api.ChangeRowAssert#changeOfModification(int)
   * @see org.assertj.db.api.ChangeRowValueAssert#changeOfModification(int)
   */
  CH changeOfModification(int index);

  /**
   * Returns assertion methods on the next {@link org.assertj.db.type.Change} of deletion ({@link org.assertj.db.type.ChangeType#DELETION}) in the list of changes.
   *
   * @return An object to make assertions on the next {@link org.assertj.db.type.Change} of deletion ({@link org.assertj.db.type.ChangeType#DELETION}).
   * @throws org.assertj.db.exception.AssertJDBException If there are no more {@link org.assertj.db.type.Change} of deletion in the list of changes.
   * @see org.assertj.db.api.ChangesAssert#changeOfDeletion()
   * @see org.assertj.db.api.ChangeAssert#changeOfDeletion()
   * @see org.assertj.db.api.ChangeColumnAssert#changeOfDeletion()
   * @see org.assertj.db.api.ChangeColumnValueAssert#changeOfDeletion()
   * @see org.assertj.db.api.ChangeRowAssert#changeOfDeletion()
   * @see org.assertj.db.api.ChangeRowValueAssert#changeOfDeletion()
   */
  CH changeOfDeletion();

  /**
   * Returns assertion methods on the {@link org.assertj.db.type.Change} of deletion ({@link org.assertj.db.type.ChangeType#DELETION}) at the {@code index} in parameter.
   *
   * @param index The index corresponding to the {@link org.assertj.db.type.Change} of deletion ({@link org.assertj.db.type.ChangeType#DELETION}).
   * @return An object to make assertions on the {@link org.assertj.db.type.Change} of deletion ({@link org.assertj.db.type.ChangeType#DELETION}).
   * @throws org.assertj.db.exception.AssertJDBException If the {@code index} is out of the bounds.
   * @see org.assertj.db.api.ChangesAssert#changeOfDeletion(int)
   * @see org.assertj.db.api.ChangeAssert#changeOfDeletion(int)
   * @see org.assertj.db.api.ChangeColumnAssert#changeOfDeletion(int)
   * @see org.assertj.db.api.ChangeColumnValueAssert#changeOfDeletion(int)
   * @see org.assertj.db.api.ChangeRowAssert#changeOfDeletion(int)
   * @see org.assertj.db.api.ChangeRowValueAssert#changeOfDeletion(int)
   */
  CH changeOfDeletion(int index);

  /**
   * Returns assertion methods on the next {@link org.assertj.db.type.Change} on the table {@code tableName} in the list of changes.
   *
   * @param tableName The table name
   * @return An object to make assertions on the next {@link org.assertj.db.type.Change}.
   * @throws org.assertj.db.exception.AssertJDBException If there are no more {@link org.assertj.db.type.Change} on the table in the list of changes.
   * @see org.assertj.db.api.ChangesAssert#changeOnTable(String)
   * @see org.assertj.db.api.ChangeAssert#changeOnTable(String)
   * @see org.assertj.db.api.ChangeColumnAssert#changeOnTable(String)
   * @see org.assertj.db.api.ChangeColumnValueAssert#changeOnTable(String)
   * @see org.assertj.db.api.ChangeRowAssert#changeOnTable(String)
   * @see org.assertj.db.api.ChangeRowValueAssert#changeOnTable(String)
   */
  CH changeOnTable(String tableName);

  /**
   * Returns assertion methods on the {@link org.assertj.db.type.Change} on the table {@code tableName} at the {@code index} in parameter.
   *
   * @param tableName The table name
   * @param index The index corresponding to the {@link org.assertj.db.type.Change}.
   * @return An object to make assertions on the {@link org.assertj.db.type.Change}.
   * @throws org.assertj.db.exception.AssertJDBException If the {@code index} is out of the bounds.
   * @see org.assertj.db.api.ChangesAssert#changeOnTable(String, int)
   * @see org.assertj.db.api.ChangeAssert#changeOnTable(String, int)
   * @see org.assertj.db.api.ChangeColumnAssert#changeOnTable(String, int)
   * @see org.assertj.db.api.ChangeColumnValueAssert#changeOnTable(String, int)
   * @see org.assertj.db.api.ChangeRowAssert#changeOnTable(String, int)
   * @see org.assertj.db.api.ChangeRowValueAssert#changeOnTable(String, int)
   */
  CH changeOnTable(String tableName, int index);

  /**
   * Returns assertion methods on the {@link org.assertj.db.type.Change} on the table {@code tableName} corresponding to the primary key in parameter.
   *
   * @param tableName The table name
   * @param pksValues The values of the primary key corresponding to the {@link org.assertj.db.type.Change}.
   * @return An object to make assertions on the {@link org.assertj.db.type.Change}.
   * @throws org.assertj.db.exception.AssertJDBException If the {@code pksValues} is not found.
   * @see org.assertj.db.api.ChangesAssert#changeOnTableWithPks(String, Object...)
   * @see org.assertj.db.api.ChangeAssert#changeOnTableWithPks(String, Object...)
   * @see org.assertj.db.api.ChangeColumnAssert#changeOnTableWithPks(String, Object...)
   * @see org.assertj.db.api.ChangeColumnValueAssert#changeOnTableWithPks(String, Object...)
   * @see org.assertj.db.api.ChangeRowAssert#changeOnTableWithPks(String, Object...)
   * @see org.assertj.db.api.ChangeRowValueAssert#changeOnTableWithPks(String, Object...)
   */
  CH changeOnTableWithPks(String tableName, Object... pksValues);

  /**
   * Returns assertion methods on the next {@link org.assertj.db.type.Change} of creation ({@link org.assertj.db.type.ChangeType#CREATION}) on the table {@code tableName} in the list of changes.
   *
   * @param tableName The table name
   * @return An object to make assertions on the next {@link org.assertj.db.type.Change} of creation ({@link org.assertj.db.type.ChangeType#CREATION}).
   * @throws org.assertj.db.exception.AssertJDBException If there are no more {@link org.assertj.db.type.Change} of creation ({@link org.assertj.db.type.ChangeType#CREATION}) on the table in the list of changes.
   * @see org.assertj.db.api.ChangesAssert#changeOfCreationOnTable(String)
   * @see org.assertj.db.api.ChangeAssert#changeOfCreationOnTable(String)
   * @see org.assertj.db.api.ChangeColumnAssert#changeOfCreationOnTable(String)
   * @see org.assertj.db.api.ChangeColumnValueAssert#changeOfCreationOnTable(String)
   * @see org.assertj.db.api.ChangeRowAssert#changeOfCreationOnTable(String)
   * @see org.assertj.db.api.ChangeRowValueAssert#changeOfCreationOnTable(String)
   */
  CH changeOfCreationOnTable(String tableName);

  /**
   * Returns assertion methods on the {@link org.assertj.db.type.Change} of creation ({@link org.assertj.db.type.ChangeType#CREATION}) on the table {@code tableName} at the {@code index} in parameter.
   *
   * @param tableName The table name
   * @param index The index corresponding to the {@link org.assertj.db.type.Change} of creation ({@link org.assertj.db.type.ChangeType#CREATION}).
   * @return An object to make assertions on the {@link org.assertj.db.type.Change} of creation ({@link org.assertj.db.type.ChangeType#CREATION}).
   * @throws org.assertj.db.exception.AssertJDBException If the {@code index} is out of the bounds.
   * @see org.assertj.db.api.ChangesAssert#changeOfCreationOnTable(String, int)
   * @see org.assertj.db.api.ChangeAssert#changeOfCreationOnTable(String, int)
   * @see org.assertj.db.api.ChangeColumnAssert#changeOfCreationOnTable(String, int)
   * @see org.assertj.db.api.ChangeColumnValueAssert#changeOfCreationOnTable(String, int)
   * @see org.assertj.db.api.ChangeRowAssert#changeOfCreationOnTable(String, int)
   * @see org.assertj.db.api.ChangeRowValueAssert#changeOfCreationOnTable(String, int)
   */
  CH changeOfCreationOnTable(String tableName, int index);

  /**
   * Returns assertion methods on the next {@link org.assertj.db.type.Change} of modification ({@link org.assertj.db.type.ChangeType#MODIFICATION}) on the table {@code tableName} in the list of changes.
   *
   * @param tableName The table name
   * @return An object to make assertions on the next {@link org.assertj.db.type.Change} of modification ({@link org.assertj.db.type.ChangeType#MODIFICATION}).
   * @throws org.assertj.db.exception.AssertJDBException If there are no more {@link org.assertj.db.type.Change} of modification ({@link org.assertj.db.type.ChangeType#MODIFICATION}) on the table in the list of changes.
   * @see org.assertj.db.api.ChangesAssert#changeOfModificationOnTable(String)
   * @see org.assertj.db.api.ChangeAssert#changeOfModificationOnTable(String)
   * @see org.assertj.db.api.ChangeColumnAssert#changeOfModificationOnTable(String)
   * @see org.assertj.db.api.ChangeColumnValueAssert#changeOfModificationOnTable(String)
   * @see org.assertj.db.api.ChangeRowAssert#changeOfModificationOnTable(String)
   * @see org.assertj.db.api.ChangeRowValueAssert#changeOfModificationOnTable(String)
   */
  CH changeOfModificationOnTable(String tableName);

  /**
   * Returns assertion methods on the {@link org.assertj.db.type.Change} of modification ({@link org.assertj.db.type.ChangeType#MODIFICATION}) on the table {@code tableName} at the {@code index} in parameter.
   *
   * @param tableName The table name
   * @param index The index corresponding to the {@link org.assertj.db.type.Change} of modification ({@link org.assertj.db.type.ChangeType#MODIFICATION}).
   * @return An object to make assertions on the {@link org.assertj.db.type.Change} of modification ({@link org.assertj.db.type.ChangeType#MODIFICATION}).
   * @throws org.assertj.db.exception.AssertJDBException If the {@code index} is out of the bounds.
   * @see org.assertj.db.api.ChangesAssert#changeOfModificationOnTable(String, int)
   * @see org.assertj.db.api.ChangeAssert#changeOfModificationOnTable(String, int)
   * @see org.assertj.db.api.ChangeColumnAssert#changeOfModificationOnTable(String, int)
   * @see org.assertj.db.api.ChangeColumnValueAssert#changeOfModificationOnTable(String, int)
   * @see org.assertj.db.api.ChangeRowAssert#changeOfModificationOnTable(String, int)
   * @see org.assertj.db.api.ChangeRowValueAssert#changeOfModificationOnTable(String, int)
   */
  CH changeOfModificationOnTable(String tableName, int index);

  /**
   * Returns assertion methods on the next {@link org.assertj.db.type.Change} of deletion ({@link org.assertj.db.type.ChangeType#DELETION}) on the table {@code tableName} in the list of changes.
   *
   * @param tableName The table name
   * @return An object to make assertions on the next {@link org.assertj.db.type.Change} of deletion ({@link org.assertj.db.type.ChangeType#DELETION}).
   * @throws org.assertj.db.exception.AssertJDBException If there are no more {@link org.assertj.db.type.Change} of deletion ({@link org.assertj.db.type.ChangeType#DELETION}) on the table in the list of changes.
   * @see org.assertj.db.api.ChangesAssert#changeOfDeletionOnTable(String)
   * @see org.assertj.db.api.ChangeAssert#changeOfDeletionOnTable(String)
   * @see org.assertj.db.api.ChangeColumnAssert#changeOfDeletionOnTable(String)
   * @see org.assertj.db.api.ChangeColumnValueAssert#changeOfDeletionOnTable(String)
   * @see org.assertj.db.api.ChangeRowAssert#changeOfDeletionOnTable(String)
   * @see org.assertj.db.api.ChangeRowValueAssert#changeOfDeletionOnTable(String)
   */
  CH changeOfDeletionOnTable(String tableName);

  /**
   * Returns assertion methods on the {@link org.assertj.db.type.Change} of deletion ({@link org.assertj.db.type.ChangeType#DELETION}) on the table {@code tableName} at the {@code index} in parameter.
   *
   * @param tableName The table name
   * @param index The index corresponding to the {@link org.assertj.db.type.Change} of deletion ({@link org.assertj.db.type.ChangeType#DELETION}).
   * @return An object to make assertions on the {@link org.assertj.db.type.Change} of deletion ({@link org.assertj.db.type.ChangeType#DELETION}).
   * @throws org.assertj.db.exception.AssertJDBException If the {@code index} is out of the bounds.
   * @see org.assertj.db.api.ChangesAssert#changeOfDeletionOnTable(String, int)
   * @see org.assertj.db.api.ChangeAssert#changeOfDeletionOnTable(String, int)
   * @see org.assertj.db.api.ChangeColumnAssert#changeOfDeletionOnTable(String, int)
   * @see org.assertj.db.api.ChangeColumnValueAssert#changeOfDeletionOnTable(String, int)
   * @see org.assertj.db.api.ChangeRowAssert#changeOfDeletionOnTable(String, int)
   * @see org.assertj.db.api.ChangeRowValueAssert#changeOfDeletionOnTable(String, int)
   */
  CH changeOfDeletionOnTable(String tableName, int index);
}
