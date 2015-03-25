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

import org.assertj.db.api.ChangeAssert;

/**
 * Defines methods to navigate to an instance with assertion methods on a {@link org.assertj.db.type.Change}.
 * <p>The different methods return an instance with assertion methods on one change ({@link org.assertj.db.api.ChangeAssert}).</p>
 * <p>These methods exists when navigating from changes.</p>
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
 * <img src="https://raw.githubusercontent.com/joel-costigliola/assertj-db/master/doc/changes/navigation/diagramOnNavigationWithChanges_ToChange.png" alt="diagram with navigation to change" height="50%" width="50%" >
 * </p>
 * <p>It is important to keep in mind that the methods are executed from the point of view of the last instance with assertion methods on changes ({@link org.assertj.db.api.ChangesAssert}).<br>
 * So all the lines of code below are equivalent : they point on the change at index 1 (as usual, the list start at index 0).
 * </p>
 * <pre>
 * <code class='java'>
 * assertThat(changes).change(1)......;                                   // Point directly on the change at index 1
 * assertThat(changes).change().returnToOrigin().change()......;          // Use the returnToOrigin() method of AbstractAssertWithOrigin
 *                                                                        // to return on the changes and access to the next/second change of the list
 * assertThat(changes).change().change()......;                           // Same as precedent but returnToOrigin() is implicit
 * assertThat(changes).change().change(1)......;                          // The method with the index can be call too
 * assertThat(changes).change(2).change(0).change(1)......;               // Idem
 * assertThat(changes).change().column().change()......;
 * assertThat(changes).change().rowAtEndPoint().change(1)......;
 * assertThat(changes).change().column().value().change()......;
 * assertThat(changes).change().rowAtEndPoint().value().change(1)......;
 * // Equivalent to the precedent but with the use of the returnToOrigin() method of AbstractAssertWithOrigin
 * assertThat(changes).change().rowAtEndPoint().value().returnToOrigin().returnToOrigin().returnToOrigin().change(1)......;
 * </code>
 * </pre>
 *
 * @author Régis Pouiller
 */
public interface ToChange {

  /**
   * Returns assertion methods on the next {@link org.assertj.db.type.Change} in the list of changes.
   *
   * @return An object to make assertions on the next {@link org.assertj.db.type.Change}.
   * @throws org.assertj.db.exception.AssertJDBException If there are no more {@link org.assertj.db.type.Change} in the list of changes.
   */
  public ChangeAssert change();

  /**
   * Returns assertion methods on the {@link org.assertj.db.type.Change} at the {@code index} in parameter.
   *
   * @param index The index corresponding to the {@link org.assertj.db.type.Change}.
   * @return An object to make assertions on the {@link org.assertj.db.type.Change}.
   * @throws org.assertj.db.exception.AssertJDBException If the {@code index} is out of the bounds.
   */
  public ChangeAssert change(int index);

  /**
   * Returns assertion methods on the next {@link org.assertj.db.type.Change} of creation ({@link org.assertj.db.type.ChangeType#CREATION}) in the list of changes.
   *
   * @return An object to make assertions on the next {@link org.assertj.db.type.Change} of creation ({@link org.assertj.db.type.ChangeType#CREATION}).
   * @throws org.assertj.db.exception.AssertJDBException If there are no more {@link org.assertj.db.type.Change} of creation ({@link org.assertj.db.type.ChangeType#CREATION}) in the list of changes.
   */
  public ChangeAssert changeOfCreation();

  /**
   * Returns assertion methods on the {@link org.assertj.db.type.Change} of creation ({@link org.assertj.db.type.ChangeType#CREATION}) at the {@code index} in parameter.
   *
   * @param index The index corresponding to the {@link org.assertj.db.type.Change} of creation ({@link org.assertj.db.type.ChangeType#CREATION}).
   * @return An object to make assertions on the {@link org.assertj.db.type.Change} of creation ({@link org.assertj.db.type.ChangeType#CREATION}).
   * @throws org.assertj.db.exception.AssertJDBException If the {@code index} is out of the bounds.
   */
  public ChangeAssert changeOfCreation(int index);

  /**
   * Returns assertion methods on the next {@link org.assertj.db.type.Change} of modification ({@link org.assertj.db.type.ChangeType#MODIFICATION}) in the list of changes.
   *
   * @return An object to make assertions on the next {@link org.assertj.db.type.Change} of modification ({@link org.assertj.db.type.ChangeType#MODIFICATION}).
   * @throws org.assertj.db.exception.AssertJDBException If there are no more {@link org.assertj.db.type.Change} of modification ({@link org.assertj.db.type.ChangeType#MODIFICATION}) in the list of changes.
   */
  public ChangeAssert changeOfModification();

  /**
   * Returns assertion methods on the {@link org.assertj.db.type.Change} of modification ({@link org.assertj.db.type.ChangeType#MODIFICATION}) at the {@code index} in parameter.
   *
   * @param index The index corresponding to the {@link org.assertj.db.type.Change} of modification ({@link org.assertj.db.type.ChangeType#MODIFICATION}).
   * @return An object to make assertions on the {@link org.assertj.db.type.Change} of modification ({@link org.assertj.db.type.ChangeType#MODIFICATION}).
   * @throws org.assertj.db.exception.AssertJDBException If the {@code index} is out of the bounds.
   */
  public ChangeAssert changeOfModification(int index);

  /**
   * Returns assertion methods on the next {@link org.assertj.db.type.Change} of deletion ({@link org.assertj.db.type.ChangeType#DELETION}) in the list of changes.
   *
   * @return An object to make assertions on the next {@link org.assertj.db.type.Change} of deletion ({@link org.assertj.db.type.ChangeType#DELETION}).
   * @throws org.assertj.db.exception.AssertJDBException If there are no more {@link org.assertj.db.type.Change} of deletion in the list of changes.
   */
  public ChangeAssert changeOfDeletion();

  /**
   * Returns assertion methods on the {@link org.assertj.db.type.Change} of deletion ({@link org.assertj.db.type.ChangeType#DELETION}) at the {@code index} in parameter.
   *
   * @param index The index corresponding to the {@link org.assertj.db.type.Change} of deletion ({@link org.assertj.db.type.ChangeType#DELETION}).
   * @return An object to make assertions on the {@link org.assertj.db.type.Change} of deletion ({@link org.assertj.db.type.ChangeType#DELETION}).
   * @throws org.assertj.db.exception.AssertJDBException If the {@code index} is out of the bounds.
   */
  public ChangeAssert changeOfDeletion(int index);

  /**
   * Returns assertion methods on the next {@link org.assertj.db.type.Change} on the table {@code tableName} in the list of changes.
   *
   * @param tableName The table name
   * @return An object to make assertions on the next {@link org.assertj.db.type.Change}.
   * @throws org.assertj.db.exception.AssertJDBException If there are no more {@link org.assertj.db.type.Change} on the table in the list of changes.
   */
  public ChangeAssert changeOnTable(String tableName);

  /**
   * Returns assertion methods on the {@link org.assertj.db.type.Change} on the table {@code tableName} at the {@code index} in parameter.
   *
   * @param tableName The table name
   * @param index The index corresponding to the {@link org.assertj.db.type.Change}.
   * @return An object to make assertions on the {@link org.assertj.db.type.Change}.
   * @throws org.assertj.db.exception.AssertJDBException If the {@code index} is out of the bounds.
   */
  public ChangeAssert changeOnTable(String tableName, int index);

  /**
   * Returns assertion methods on the {@link org.assertj.db.type.Change} on the table {@code tableName} corresponding to the primary key in parameter.
   *
   * @param tableName The table name
   * @param pksValues The values of the primary key corresponding to the {@link org.assertj.db.type.Change}.
   * @return An object to make assertions on the {@link org.assertj.db.type.Change}.
   * @throws org.assertj.db.exception.AssertJDBException If the {@code pksValues} is not found.
   */
  public ChangeAssert changeOnTableWithPks(String tableName, Object... pksValues);

  /**
   * Returns assertion methods on the next {@link org.assertj.db.type.Change} of creation ({@link org.assertj.db.type.ChangeType#CREATION}) on the table {@code tableName} in the list of changes.
   *
   * @param tableName The table name
   * @return An object to make assertions on the next {@link org.assertj.db.type.Change} of creation ({@link org.assertj.db.type.ChangeType#CREATION}).
   * @throws org.assertj.db.exception.AssertJDBException If there are no more {@link org.assertj.db.type.Change} of creation ({@link org.assertj.db.type.ChangeType#CREATION}) on the table in the list of changes.
   */
  public ChangeAssert changeOfCreationOnTable(String tableName);

  /**
   * Returns assertion methods on the {@link org.assertj.db.type.Change} of creation ({@link org.assertj.db.type.ChangeType#CREATION}) on the table {@code tableName} at the {@code index} in parameter.
   *
   * @param tableName The table name
   * @param index The index corresponding to the {@link org.assertj.db.type.Change} of creation ({@link org.assertj.db.type.ChangeType#CREATION}).
   * @return An object to make assertions on the {@link org.assertj.db.type.Change} of creation ({@link org.assertj.db.type.ChangeType#CREATION}).
   * @throws org.assertj.db.exception.AssertJDBException If the {@code index} is out of the bounds.
   */
  public ChangeAssert changeOfCreationOnTable(String tableName, int index);

  /**
   * Returns assertion methods on the next {@link org.assertj.db.type.Change} of modification ({@link org.assertj.db.type.ChangeType#MODIFICATION}) on the table {@code tableName} in the list of changes.
   *
   * @param tableName The table name
   * @return An object to make assertions on the next {@link org.assertj.db.type.Change} of modification ({@link org.assertj.db.type.ChangeType#MODIFICATION}).
   * @throws org.assertj.db.exception.AssertJDBException If there are no more {@link org.assertj.db.type.Change} of modification ({@link org.assertj.db.type.ChangeType#MODIFICATION}) on the table in the list of changes.
   */
  public ChangeAssert changeOfModificationOnTable(String tableName);

  /**
   * Returns assertion methods on the {@link org.assertj.db.type.Change} of modification ({@link org.assertj.db.type.ChangeType#MODIFICATION}) on the table {@code tableName} at the {@code index} in parameter.
   *
   * @param tableName The table name
   * @param index The index corresponding to the {@link org.assertj.db.type.Change} of modification ({@link org.assertj.db.type.ChangeType#MODIFICATION}).
   * @return An object to make assertions on the {@link org.assertj.db.type.Change} of modification ({@link org.assertj.db.type.ChangeType#MODIFICATION}).
   * @throws org.assertj.db.exception.AssertJDBException If the {@code index} is out of the bounds.
   */
  public ChangeAssert changeOfModificationOnTable(String tableName, int index);

  /**
   * Returns assertion methods on the next {@link org.assertj.db.type.Change} of deletion ({@link org.assertj.db.type.ChangeType#DELETION}) on the table {@code tableName} in the list of changes.
   *
   * @param tableName The table name
   * @return An object to make assertions on the next {@link org.assertj.db.type.Change} of deletion ({@link org.assertj.db.type.ChangeType#DELETION}).
   * @throws org.assertj.db.exception.AssertJDBException If there are no more {@link org.assertj.db.type.Change} of deletion ({@link org.assertj.db.type.ChangeType#DELETION}) on the table in the list of changes.
   */
  public ChangeAssert changeOfDeletionOnTable(String tableName);

  /**
   * Returns assertion methods on the {@link org.assertj.db.type.Change} of deletion ({@link org.assertj.db.type.ChangeType#DELETION}) on the table {@code tableName} at the {@code index} in parameter.
   *
   * @param tableName The table name
   * @param index The index corresponding to the {@link org.assertj.db.type.Change} of deletion ({@link org.assertj.db.type.ChangeType#DELETION}).
   * @return An object to make assertions on the {@link org.assertj.db.type.Change} of deletion ({@link org.assertj.db.type.ChangeType#DELETION}).
   * @throws org.assertj.db.exception.AssertJDBException If the {@code index} is out of the bounds.
   */
  public ChangeAssert changeOfDeletionOnTable(String tableName, int index);
}
