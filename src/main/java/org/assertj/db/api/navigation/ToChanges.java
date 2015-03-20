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
import org.assertj.db.api.ChangesAssert;

/**
 * Defines methods to navigate to changes or to a {@link org.assertj.db.type.Change}.
 * <p>The different methods return an assertion on changes {@link org.assertj.db.api.ChangesAssert}
 * or assertion on one change {@link org.assertj.db.api.ChangeAssert}.</p>
 * <p>
 * <img src="https://raw.githubusercontent.com/joel-costigliola/assertj-db/master/doc/changes/navigation/diagramOnNavigationWithChanges_ToChanges.png" alt="diagram with navigation to changes" height="50%" width="50%" >
 * </p>

 * @author Régis Pouiller
 */
public interface ToChanges {

  /**
   * Returns an assertion of all the changes.
   * <p>
   * If the actual assertion is on all the changes, the new assertion is on the changes which are for creation. Example
   * :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * // there are five changes in total.
   * assertThat(changes).ofAll().hasNumberOfChanges(5);
   * </code></pre>
   * <p>
   * But if the actual assertion is on a part of the the changes, the new assertion is on the changes of the original
   * which are for all the changes. Example :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * // there are three changes of modification are there are always five changes in total
   * assertThat(changes).ofModification().hasNumberOfChanges(3).ofAll().hasNumberOfChanges(5);
   * </code></pre>
   *
   * @return The assertion.
   */
  public ChangesAssert ofAll();

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
   * assertThat(changes).ofCreation().hasNumberOfChanges(2);
   * </code></pre>
   * <p>
   * But if the actual assertion is on a part of the the changes, the new assertion is on the changes of the original
   * which are for creation. Example :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * // there are three changes of modification are there are always two changes of creation
   * assertThat(changes).ofModification().hasNumberOfChanges(3).ofCreation().hasNumberOfChanges(2);
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
   * assertThat(changes).ofModification().hasNumberOfChanges(2);
   * </code></pre>
   * <p>
   * But if the actual assertion is on a part of the the changes, the new assertion is on the changes of the original
   * which are for modification. Example :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * // there are three changes of creation are there are always two changes of modification
   * assertThat(changes).ofCreation().hasNumberOfChanges(3).ofModification().hasNumberOfChanges(2);
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
   * assertThat(changes).ofDeletion().hasNumberOfChanges(2);
   * </code></pre>
   * <p>
   * But if the actual assertion is on a part of the the changes, the new assertion is on the changes of the original
   * which are for deletion. Example :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * // there are three changes of creation are there are always two changes of deletion
   * assertThat(changes).ofCreation().hasNumberOfChanges(3).ofDeletion().hasNumberOfChanges(2);
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
   * assertThat(changes).ofCreation().hasNumberOfChanges(2);
   * </code></pre>
   * <p>
   * But if the actual assertion is on a part of the the changes, the new assertion is on the changes of the original
   * which are for creation on a table. Example :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * // there are three changes of modification are there are always two changes of creation on 'movie' table
   * assertThat(changes).ofModification().hasNumberOfChanges(3).ofCreation().hasNumberOfChanges(2);
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
   * assertThat(changes).ofModification().hasNumberOfChanges(2);
   * </code></pre>
   * <p>
   * But if the actual assertion is on a part of the changes, the new assertion is on the changes of the original
   * which are for modification on a table. Example :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * // there are three changes of creation are there are always two changes of modification on 'movie' table
   * assertThat(changes).ofCreation().hasNumberOfChanges(3).ofModificationOnTable("movie").hasNumberOfChanges(2);
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
   * assertThat(changes).ofDeletionOnTable("movie").hasNumberOfChanges(2);
   * </code></pre>
   * <p>
   * But if the actual assertion is on a part of the changes, the new assertion is on the changes of the original
   * which are for deletion on a table. Example :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * // there are three changes of creation are there are always two changes of deletion on 'movie' table
   * assertThat(changes).ofCreation().hasNumberOfChanges(3).ofDeletionOnTable("movie").hasNumberOfChanges(2);
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
   * assertThat(changes).onTable("movie").hasNumberOfChanges(2);
   * </code></pre>
   * <p>
   * But if the actual assertion is on a part of the changes, the new assertion is on the changes of the original
   * which are on a table. Example :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * // there are three changes of creation are there are always two changes on 'movie' table
   * assertThat(changes).ofCreation().hasNumberOfChanges(3).onTable("movie").hasNumberOfChanges(2);
   * </code></pre>
   *
   * @param tableName The table name
   * @return The assertion.
   */
  public ChangesAssert onTable(String tableName);
}
