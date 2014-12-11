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

import org.assertj.db.exception.AssertJDBException;
import org.assertj.db.type.Change;
import org.assertj.db.type.ChangeType;
import org.assertj.db.type.Changes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.db.error.ShouldHaveChangesSize.shouldHaveChangesSize;

/**
 * Assertion methods about the {@link Changes}.
 * 
 * @author Régis Pouiller
 * 
 */
public class ChangesAssert extends AbstractAssert<ChangesAssert> implements OriginAssert {

  /**
   * The actual changes on which the assertion is.
   */
  private final Changes changes;

  /**
   * The original assert.
   */
  private final ChangesAssert original;

  /**
   * Index of the next change to get.
   */
  private int indexNextChange;
  /**
   * Map the change assert with their index in key (contains the change assert already generated).
   */
  private Map<Integer, ChangeAssert> changesAssertMap = new HashMap<Integer, ChangeAssert>();

  /**
   * Constructor.
   * 
   * @param changes The {@link Changes} on which are the assertions.
   */
  ChangesAssert(Changes changes) {
    this(null, changes);
  }

  /**
   * Constructor.
   * 
   * @param originalAssert The original assert.
   * @param changes The {@link Changes} on which are the assertions.
   */
  private ChangesAssert(ChangesAssert originalAssert, Changes changes) {
    super(ChangesAssert.class);
    this.original = originalAssert;
    this.changes = changes;
  }

  /**
   * Verifies that the size (number) of {@link Changes} is equal to the number in parameter.
   * <p>
   * Example where the assertion verifies that there are 8 changes :
   * </p>
   * 
   * <pre>
   * <code class='java'>
   * assertThat(changes).hasSize(8);
   * </code>
   * </pre>
   * 
   * @param expected The number to compare to the size.
   * @return {@code this} assertion object.
   * @throws AssertionError If the size is different to the number in parameter.
   */
  public ChangesAssert hasSize(int expected) {
    List<Change> changesList = changes.getChangesList();
    int size = changesList.size();
    if (size != expected) {
      throw failures.failure(info, shouldHaveChangesSize(size, expected));
    }
    return myself;
  }

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
  public ChangesAssert ofCreation() {
    if (original != null) {
      return original.ofCreation();
    }
    Changes changes = this.changes.getChangesOfType(ChangeType.CREATION);
    return new ChangesAssert(this, changes).as(info.descriptionText() + " (only creation changes)");
  }

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
  public ChangesAssert ofModification() {
    if (original != null) {
      return original.ofModification();
    }
    Changes changes = this.changes.getChangesOfType(ChangeType.MODIFICATION);
    return new ChangesAssert(this, changes).as(info.descriptionText() + " (only modification changes)");
  }

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
  public ChangesAssert ofDeletion() {
    if (original != null) {
      return original.ofDeletion();
    }
    Changes changes = this.changes.getChangesOfType(ChangeType.DELETION);
    return new ChangesAssert(this, changes).as(info.descriptionText() + " (only deletion changes)");
  }

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
  public ChangesAssert ofCreationOnTable(String tableName) {
    if (original != null) {
      return original.ofCreationOnTable(tableName);
    }
    Changes changes = this.changes.getChangesOfType(ChangeType.CREATION).getChangesOfTable(tableName);
    return new ChangesAssert(this, changes).as(info.descriptionText() + " (only creation changes on " + tableName + " table)");
  }

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
  public ChangesAssert ofModificationOnTable(String tableName) {
    if (original != null) {
      return original.ofModificationOnTable(tableName);
    }
    Changes changes = this.changes.getChangesOfType(ChangeType.MODIFICATION).getChangesOfTable(tableName);
    return new ChangesAssert(this, changes).as(info.descriptionText() + " (only modification changes on " + tableName + " table)");
  }

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
  public ChangesAssert ofDeletionOnTable(String tableName) {
    if (original != null) {
      return original.ofDeletionOnTable(tableName);
    }
    Changes changes = this.changes.getChangesOfType(ChangeType.DELETION).getChangesOfTable(tableName);
    return new ChangesAssert(this, changes).as(info.descriptionText() + " (only deletion changes on " + tableName + " table)");
  }

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
  public ChangesAssert onTable(String tableName) {
    if (original != null) {
      return original.onTable(tableName);
    }
    Changes changes = this.changes.getChangesOfTable(tableName);
    return new ChangesAssert(this, changes).as(info.descriptionText() + " (only on " + tableName + " table)");
  }

  /**
   * Returns the change at the {@code index} in parameter.
   * 
   * @param index The index corresponding to the change.
   * @return The change.
   * @throws AssertJDBException If the {@code index} is out of the bounds.
   */
  private Change getChange(int index) {
    List<Change> changesList = changes.getChangesList();
    int size = changesList.size();
    if (index < 0 || index >= size) {
      throw new AssertJDBException("Index %s out of the limits [0, %s[", index, size);
    }
    Change change = changesList.get(index);
    indexNextChange = index + 1;
    return change;
  }

  /**
   * Gets an instance of change assert corresponding to the index. If this instance is already instanced, the method
   * returns it from the cache.
   * 
   * @param index Index of the change on which is the instance of change assert.
   * @return The change assert implementation.
   */
  private ChangeAssert getChangeAssertInstance(int index) {
    if (changesAssertMap.containsKey(index)) {
      ChangeAssert changeAssert = changesAssertMap.get(index);
      indexNextChange = index + 1;
      return changeAssert;
    }

    ChangeAssert instance = new ChangeAssert(this, getChange(index));
    changesAssertMap.put(index, instance);
    return instance.as("Change at index " + index + " of " + info.descriptionText());
  }

  /**
   * Returns assertion methods on the next change in the list of changes.
   * 
   * @return An object to make assertions on the next change.
   * @throws AssertJDBException If the {@code index} is out of the bounds.
   */
  public ChangeAssert change() {
    return getChangeAssertInstance(indexNextChange);
  }

  /**
   * Returns assertion methods on the change at the {@code index} in parameter.
   * 
   * @param index The index corresponding to the change.
   * @return An object to make assertions on the change.
   * @throws AssertJDBException If the {@code index} is out of the bounds.
   */
  public ChangeAssert change(int index) {
    return getChangeAssertInstance(index);
  }
}
