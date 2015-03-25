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

import org.assertj.db.api.ChangesAssert;

/**
 * Defines methods to navigate to changes or to a {@link org.assertj.db.type.Change}.
 * <p>The different methods return an assertion on changes {@link org.assertj.db.api.ChangesAssert}.</p>
 * <p>These methods exists when navigating from changes.</p>
 * <p>As shown in the diagram below, it is possible to call the method to navigate to changes from :</p>
 * <ul>
 *     <li>changes ({@link org.assertj.db.api.ChangesAssert})</li>
 *     <li>another change ({@link org.assertj.db.api.ChangeAssert})</li>
 *     <li>a column of a change ({@link org.assertj.db.api.ChangeColumnAssert})</li>
 *     <li>a value of a column of a change ({@link org.assertj.db.api.ChangeColumnValueAssert})</li>
 *     <li>a row of a change ({@link org.assertj.db.api.ChangeRowAssert})</li>
 *     <li>a value of a row of a change ({@link org.assertj.db.api.ChangeRowValueAssert})</li>
 * </ul>
 * <p>
 * <img src="https://raw.githubusercontent.com/joel-costigliola/assertj-db/master/doc/changes/navigation/diagramOnNavigationWithChanges_ToChanges.png" alt="diagram with navigation to changes" height="55%" width="55%" >
 * </p>
 * <p>It is important to keep in mind that the methods are executed from the point of view of the last instance with assertion methods on changes ({@link org.assertj.db.api.ChangesAssert}).<br>
 * So all the lines of code below are equivalent : they point on all the changes.
 * </p>
 * <pre>
 * <code class='java'>
 * assertThat(changes)......;                                               // Point on all the changes
 * assertThat(changes).ofAll()......;                                       // Idem
 * assertThat(changes).change().returnToOrigin().ofAll()......;             // Use the returnToOrigin() of AbstractAssertWithOrigin
 *                                                                          // to return on the changes and access to the next/second change of the list
 * assertThat(changes).change().ofAll()......;                              // Same as precedent but returnToOrigin() is implicit
 * assertThat(changes).change().column().ofAll()......;
 * assertThat(changes).change().rowAtEndPoint().ofAll()......;
 * assertThat(changes).change().column().value().ofAll()......;
 * assertThat(changes).change().rowAtEndPoint().value().ofAll()......;
 * // Equivalent to the precedent but with the use of the returnToOrigin() method of AbstractAssertWithOrigin
 * assertThat(changes).change().rowAtEndPoint().value().returnToOrigin().returnToOrigin().returnToOrigin().ofAll()......;
 * </code>
 * </pre>
 * @author Régis Pouiller
 */
public interface ToChanges {

  /**
   * Returns an assertion of all the changes.
   *
   * @return An object to make assertions on all the changes.
   */
  public ChangesAssert ofAll();

  /**
   * Returns an assertion of the changes of creation ({@link org.assertj.db.type.ChangeType#CREATION}).
   *
   * @return An object to make assertions on the changes of creation ({@link org.assertj.db.type.ChangeType#CREATION}).
   */
  public ChangesAssert ofCreation();

  /**
   * Returns an assertion of the changes of modification ({@link org.assertj.db.type.ChangeType#MODIFICATION}).
   *
   * @return An object to make assertions on the changes of modification ({@link org.assertj.db.type.ChangeType#MODIFICATION}).
   */
  public ChangesAssert ofModification();

  /**
   * Returns an assertion of the changes of deletion ({@link org.assertj.db.type.ChangeType#DELETION}).
   *
   * @return An object to make assertions on the changes of deletion ({@link org.assertj.db.type.ChangeType#DELETION}).
   */
  public ChangesAssert ofDeletion();

  /**
   * Returns an assertion of the changes of creation ({@link org.assertj.db.type.ChangeType#CREATION}) on a table.
   *
   * @param tableName The table name
   * @return An object to make assertions on the changes of creation ({@link org.assertj.db.type.ChangeType#CREATION}) on the table.
   */
  public ChangesAssert ofCreationOnTable(String tableName);

  /**
   * Returns an assertion of the changes of modification ({@link org.assertj.db.type.ChangeType#MODIFICATION}) on a table.
   *
   * @param tableName The table name
   * @return An object to make assertions on the changes of modification ({@link org.assertj.db.type.ChangeType#MODIFICATION}) on the table.
   */
  public ChangesAssert ofModificationOnTable(String tableName);

  /**
   * Returns an assertion of the changes of deletion ({@link org.assertj.db.type.ChangeType#DELETION}) on a table.
   *
   * @param tableName The table name
   * @return An object to make assertions on the changes of deletion ({@link org.assertj.db.type.ChangeType#DELETION}) on the table.
   */
  public ChangesAssert ofDeletionOnTable(String tableName);

  /**
   * Returns an assertion of the changes on a table.
   *
   * @param tableName The table name
   * @return An object to make assertions on the changes on the table.
   */
  public ChangesAssert onTable(String tableName);
}
