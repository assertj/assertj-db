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
package org.assertj.db.api;

/**
 * Interface that represents a assert with {@link org.assertj.db.type.Change}.
 *
 * @author Régis Pouiller
 */
public interface AssertWithChanges {

  /**
   * Returns an assertion of the changes of creation.
   * <p>
   * If the actual assertion is on all the changes, the new assertion is on the changes which are for creation. Example
   * :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * // there are two changes of creation
   * assertThat(changes).ofCreation().hasSize(2);
   * </code></pre>
   * <p>
   * But if the actual assertion is on a part of the the changes, the new assertion is on the changes of the original
   * which are for creation. Example :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * // there are three changes of modification are there are always two changes of creation
   * assertThat(changes).ofModification().hasSize(3).ofCreation().hasSize(2);
   * </code></pre>
   *
   * @return The assertion.
   */
  public ChangesAssert ofCreation();

  /**
   * Returns an assertion of the changes of modification.
   * <p>
   * If the actual assertion is on all the changes, the new assertion is on the changes which are for modification. Example
   * :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * // there are two changes of modification
   * assertThat(changes).ofModification().hasSize(2);
   * </code></pre>
   * <p>
   * But if the actual assertion is on a part of the the changes, the new assertion is on the changes of the original
   * which are for modification. Example :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * // there are three changes of creation are there are always two changes of modification
   * assertThat(changes).ofCreation().hasSize(3).ofModification().hasSize(2);
   * </code></pre>
   *
   * @return The assertion.
   */
  public ChangesAssert ofModification();

  /**
   * Returns an assertion of the changes of deletion.
   * <p>
   * If the actual assertion is on all the changes, the new assertion is on the changes which are for deletion. Example
   * :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * // there are two changes of deletion
   * assertThat(changes).ofDeletion().hasSize(2);
   * </code></pre>
   * <p>
   * But if the actual assertion is on a part of the the changes, the new assertion is on the changes of the original
   * which are for deletion. Example :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * // there are three changes of creation are there are always two changes of deletion
   * assertThat(changes).ofCreation().hasSize(3).ofDeletion().hasSize(2);
   * </code></pre>
   *
   * @return The assertion.
   */
  public ChangesAssert ofDeletion();

  /**
   * Returns an assertion of the changes of creation on a table.
   * <p>
   * If the actual assertion is on all the changes, the new assertion is on the changes which are for creation on a table. Example
   * :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * // there are two changes of creation on 'movie' table
   * assertThat(changes).ofCreation().hasSize(2);
   * </code></pre>
   * <p>
   * But if the actual assertion is on a part of the the changes, the new assertion is on the changes of the original
   * which are for creation on a table. Example :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * // there are three changes of modification are there are always two changes of creation on 'movie' table
   * assertThat(changes).ofModification().hasSize(3).ofCreation().hasSize(2);
   * </code></pre>
   *
   * @param tableName The table name
   * @return The assertion.
   */
  public ChangesAssert ofCreationOnTable(String tableName);

  /**
   * Returns an assertion of the changes of modification on a table.
   * <p>
   * If the actual assertion is on all the changes, the new assertion is on the changes which are for modification on a table. Example
   * :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * // there are two changes of modification on 'movie' table
   * assertThat(changes).ofModification().hasSize(2);
   * </code></pre>
   * <p>
   * But if the actual assertion is on a part of the changes, the new assertion is on the changes of the original
   * which are for modification on a table. Example :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * // there are three changes of creation are there are always two changes of modification on 'movie' table
   * assertThat(changes).ofCreation().hasSize(3).ofModificationOnTable("movie").hasSize(2);
   * </code></pre>
   *
   * @param tableName The table name
   * @return The assertion.
   */
  public ChangesAssert ofModificationOnTable(String tableName);

  /**
   * Returns an assertion of the changes of deletion on a table.
   * <p>
   * If the actual assertion is on all the changes, the new assertion is on the changes which are for deletion on a table. Example
   * :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * // there are two changes of deletion on 'movie' table
   * assertThat(changes).ofDeletionOnTable("movie").hasSize(2);
   * </code></pre>
   * <p>
   * But if the actual assertion is on a part of the changes, the new assertion is on the changes of the original
   * which are for deletion on a table. Example :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * // there are three changes of creation are there are always two changes of deletion on 'movie' table
   * assertThat(changes).ofCreation().hasSize(3).ofDeletionOnTable("movie").hasSize(2);
   * </code></pre>
   *
   * @param tableName The table name
   * @return The assertion.
   */
  public ChangesAssert ofDeletionOnTable(String tableName);

  /**
   * Returns an assertion of the changes on a table.
   * <p>
   * If the actual assertion is on all the changes, the new assertion is on the changes which are on a table. Example
   * :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * // there are two changes on 'movie' table
   * assertThat(changes).onTable("movie").hasSize(2);
   * </code></pre>
   * <p>
   * But if the actual assertion is on a part of the changes, the new assertion is on the changes of the original
   * which are on a table. Example :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * // there are three changes of creation are there are always two changes on 'movie' table
   * assertThat(changes).ofCreation().hasSize(3).onTable("movie").hasSize(2);
   * </code></pre>
   *
   * @param tableName The table name
   * @return The assertion.
   */
  public ChangesAssert onTable(String tableName);

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
   * @return An object to make assertions on the next change of creation.
   * @throws org.assertj.db.exception.AssertJDBException If the {@code index} is out of the bounds.
   */
  public ChangeAssert changeOnTable(String tableName);

  /**
   * Returns assertion methods on the change on the table {@code tableName} at the {@code index} in parameter.
   *
   * @param tableName The table name
   * @param index The index corresponding to the change of creation.
   * @return An object to make assertions on the change of creation.
   * @throws org.assertj.db.exception.AssertJDBException If the {@code index} is out of the bounds.
   */
  public ChangeAssert changeOnTable(String tableName, int index);
}
