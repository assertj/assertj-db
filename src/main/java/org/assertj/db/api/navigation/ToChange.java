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
 * Defines methods to navigate to a {@link org.assertj.db.type.Change}.
 * <p>The different methods return an assertion on one change {@link org.assertj.db.api.ChangeAssert}.</p>
 * <p>
 * <img src="https://raw.githubusercontent.com/joel-costigliola/assertj-db/master/doc/changes/navigation/diagramOnNavigationWithChanges_ToChange.png" alt="diagram with navigation to change" height="50%" width="50%" >
 * </p>

 * @author Régis Pouiller
 */
public interface ToChange {

  /**
   * Returns assertion methods on the next change in the list of changes.
   *
   * @return An object to make assertions on the next change.
   * @throws org.assertj.db.exception.AssertJDBException If the {@code index} is out of the bounds.
   */
  public ChangeAssert change();

  /**
   * Returns assertion methods on the change at the {@code index} in parameter.
   *
   * @param index The index corresponding to the change.
   * @return An object to make assertions on the change.
   * @throws org.assertj.db.exception.AssertJDBException If the {@code index} is out of the bounds.
   */
  public ChangeAssert change(int index);

  /**
   * Returns assertion methods on the next change of creation in the list of changes.
   *
   * @return An object to make assertions on the next change of creation.
   * @throws org.assertj.db.exception.AssertJDBException If the {@code index} is out of the bounds.
   */
  public ChangeAssert changeOfCreation();

  /**
   * Returns assertion methods on the change of creation at the {@code index} in parameter.
   *
   * @param index The index corresponding to the change of creation.
   * @return An object to make assertions on the change of creation.
   * @throws org.assertj.db.exception.AssertJDBException If the {@code index} is out of the bounds.
   */
  public ChangeAssert changeOfCreation(int index);

  /**
   * Returns assertion methods on the next change of modification in the list of changes.
   *
   * @return An object to make assertions on the next change of modification.
   * @throws org.assertj.db.exception.AssertJDBException If the {@code index} is out of the bounds.
   */
  public ChangeAssert changeOfModification();

  /**
   * Returns assertion methods on the change of modification at the {@code index} in parameter.
   *
   * @param index The index corresponding to the change of modification.
   * @return An object to make assertions on the change of modification.
   * @throws org.assertj.db.exception.AssertJDBException If the {@code index} is out of the bounds.
   */
  public ChangeAssert changeOfModification(int index);

  /**
   * Returns assertion methods on the next change of deletion in the list of changes.
   *
   * @return An object to make assertions on the next change of deletion.
   * @throws org.assertj.db.exception.AssertJDBException If the {@code index} is out of the bounds.
   */
  public ChangeAssert changeOfDeletion();

  /**
   * Returns assertion methods on the change of deletion at the {@code index} in parameter.
   *
   * @param index The index corresponding to the change of deletion.
   * @return An object to make assertions on the change of deletion.
   * @throws org.assertj.db.exception.AssertJDBException If the {@code index} is out of the bounds.
   */
  public ChangeAssert changeOfDeletion(int index);

  /**
   * Returns assertion methods on the next change on the table {@code tableName} in the list of changes.
   *
   * @param tableName The table name
   * @return An object to make assertions on the next change.
   * @throws org.assertj.db.exception.AssertJDBException If the {@code index} is out of the bounds.
   */
  public ChangeAssert changeOnTable(String tableName);

  /**
   * Returns assertion methods on the change on the table {@code tableName} at the {@code index} in parameter.
   *
   * @param tableName The table name
   * @param index The index corresponding to the change.
   * @return An object to make assertions on the change.
   * @throws org.assertj.db.exception.AssertJDBException If the {@code index} is out of the bounds.
   */
  public ChangeAssert changeOnTable(String tableName, int index);

  /**
   * Returns assertion methods on the change on the table {@code tableName} corresponding to the primary key in parameter.
   *
   * @param tableName The table name
   * @param pksValues The values of the primary key corresponding to the change.
   * @return An object to make assertions on the change of creation.
   * @throws org.assertj.db.exception.AssertJDBException If the {@code pksValues} is not found.
   */
  public ChangeAssert changeOnTableWithPks(String tableName, Object... pksValues);

  /**
   * Returns assertion methods on the next change of creation on the table {@code tableName} in the list of changes.
   *
   * @param tableName The table name
   * @return An object to make assertions on the next change of creation.
   * @throws org.assertj.db.exception.AssertJDBException If the {@code index} is out of the bounds.
   */
  public ChangeAssert changeOfCreationOnTable(String tableName);

  /**
   * Returns assertion methods on the change of creation on the table {@code tableName} at the {@code index} in parameter.
   *
   * @param tableName The table name
   * @param index The index corresponding to the change of creation.
   * @return An object to make assertions on the change of creation.
   * @throws org.assertj.db.exception.AssertJDBException If the {@code index} is out of the bounds.
   */
  public ChangeAssert changeOfCreationOnTable(String tableName, int index);

  /**
   * Returns assertion methods on the next change of modification on the table {@code tableName} in the list of changes.
   *
   * @param tableName The table name
   * @return An object to make assertions on the next change of modification.
   * @throws org.assertj.db.exception.AssertJDBException If the {@code index} is out of the bounds.
   */
  public ChangeAssert changeOfModificationOnTable(String tableName);

  /**
   * Returns assertion methods on the change of modification on the table {@code tableName} at the {@code index} in parameter.
   *
   * @param tableName The table name
   * @param index The index corresponding to the change of modification.
   * @return An object to make assertions on the change of modification.
   * @throws org.assertj.db.exception.AssertJDBException If the {@code index} is out of the bounds.
   */
  public ChangeAssert changeOfModificationOnTable(String tableName, int index);

  /**
   * Returns assertion methods on the next change of deletion on the table {@code tableName} in the list of changes.
   *
   * @param tableName The table name
   * @return An object to make assertions on the next change of deletion.
   * @throws org.assertj.db.exception.AssertJDBException If the {@code index} is out of the bounds.
   */
  public ChangeAssert changeOfDeletionOnTable(String tableName);

  /**
   * Returns assertion methods on the change of deletion on the table {@code tableName} at the {@code index} in parameter.
   *
   * @param tableName The table name
   * @param index The index corresponding to the change of deletion.
   * @return An object to make assertions on the change of deletion.
   * @throws org.assertj.db.exception.AssertJDBException If the {@code index} is out of the bounds.
   */
  public ChangeAssert changeOfDeletionOnTable(String tableName, int index);
}
