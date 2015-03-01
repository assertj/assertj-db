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

import org.assertj.db.api.assertions.AssertOnNumberOfChanges;
import org.assertj.db.exception.AssertJDBException;
import org.assertj.db.type.Change;
import org.assertj.db.type.ChangeType;
import org.assertj.db.type.Changes;
import org.assertj.db.util.AssertionsOnChanges;
import org.assertj.db.util.Values;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Assertion methods about the {@link Changes}.
 *
 * @author Régis Pouiller
 */
public class ChangesAssert extends AbstractAssertWithChanges<ChangesAssert, ChangesAssert> implements
        AssertOnNumberOfChanges<ChangesAssert> {

  /**
   * The actual changes on which the assertion is.
   */
  private final Changes changes;

  /**
   * Index of the next change to get per type of change.
   */
  private Map<ChangeType, Map<String, Integer>> indexNextChangeMap = new HashMap<>();

  /**
   * Map the change assert with their type of change and table name in key (contains the change assert already generated).
   */
  private Map<ChangeType, Map<String, ChangesAssert>> changesAssertMap = new HashMap<>();

  /**
   * Map the change assert with the change in key (contains the change assert already generated).
   */
  private Map<Change, ChangeAssert> changeMap = new HashMap<>();

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
   * @param changes        The {@link Changes} on which are the assertions.
   */
  private ChangesAssert(ChangesAssert originalAssert, Changes changes) {
    super(ChangesAssert.class, originalAssert);
    this.changes = changes;
  }

  /**
   * Returns an instance of changes assert from the cache.
   *
   * @param changeType Type of the change on which is the instance of change assert.
   * @param tableName  Name of the table on which is the instance of change assert.
   * @return The changes assert from the cache.
   */
  private ChangesAssert getAssertFromCache(ChangeType changeType, String tableName) {
    Map<String, ChangesAssert> mapWithTableName = changesAssertMap.get(changeType);
    if (mapWithTableName == null) {
      return null;
    }
    return mapWithTableName.get(tableName);
  }

  /**
   * Sets an instance of changes assert in the cache.
   *
   * @param changeType   Type of the change on which is the instance of change assert.
   * @param tableName    Name of the table on which is the instance of change assert.
   * @param changesAssert Changes assert to add in the cache.
   */
  private void setAssertInCache(ChangeType changeType, String tableName, ChangesAssert changesAssert) {
    Map<String, ChangesAssert> mapWithTableName = changesAssertMap.get(changeType);
    if (mapWithTableName == null) {
      mapWithTableName = new HashMap<>();
      changesAssertMap.put(changeType, mapWithTableName);
    }
    mapWithTableName.put(tableName, changesAssert);
  }

  /**
   * Gets a StringBuilder about the type of change and the table name.
   *
   * @param changeType Type of the change on which is the instance of change assert.
   * @param tableName  Name of the table on which is the instance of change assert.
   * @return The changes assert implementation.
   */
  private StringBuilder getStringBuilderAboutChangeTypeAndTableName(ChangeType changeType, String tableName) {
    StringBuilder stringBuilder = new StringBuilder();
    if (changeType != null || tableName != null) {
      stringBuilder.append(" (only");
      if (changeType != null) {
        stringBuilder.append(" ");
        stringBuilder.append(changeType.name().toLowerCase());
      }
      stringBuilder.append(" ");
      stringBuilder.append("changes");
      if (tableName != null) {
        stringBuilder.append(" on ");
        stringBuilder.append(tableName);
        stringBuilder.append(" table");
      }
      stringBuilder.append(")");
    }
    return stringBuilder;
  }

  /**
   * Gets an instance of changes assert corresponding to the index and the type of change. If this instance is already instanced, the method
   * returns it from the cache.
   *
   * @param changeType Type of the change on which is the instance of change assert.
   * @param tableName  Name of the table on which is the instance of change assert.
   * @return The changes assert implementation.
   */
  private ChangesAssert getChangeAssertInstance(ChangeType changeType, String tableName) {
    ChangesAssert changesAssert = getAssertFromCache(changeType, tableName);
    if (changesAssert != null) {
      return changesAssert;
    }
    Changes changes = this.changes;
    if (changeType != null) {
      changes = changes.getChangesOfType(changeType);
    }
    if (tableName != null) {
      changes = changes.getChangesOfTable(tableName);
    }
    changesAssert = new ChangesAssert(this, changes).as(info.descriptionText() + getStringBuilderAboutChangeTypeAndTableName(
            changeType, tableName));
    setAssertInCache(changeType, tableName, changesAssert);
    return changesAssert;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangesAssert ofAll() {
    if (originAssert != null) {
      return originAssert.ofAll();
    }
    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangesAssert ofCreation() {
    if (originAssert != null) {
      return originAssert.ofCreation();
    }
    return getChangeAssertInstance(ChangeType.CREATION, null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangesAssert ofModification() {
    if (originAssert != null) {
      return originAssert.ofModification();
    }
    return getChangeAssertInstance(ChangeType.MODIFICATION, null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangesAssert ofDeletion() {
    if (originAssert != null) {
      return originAssert.ofDeletion();
    }
    return getChangeAssertInstance(ChangeType.DELETION, null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangesAssert ofCreationOnTable(String tableName) {
    if (originAssert != null) {
      return originAssert.ofCreationOnTable(tableName);
    }
    return getChangeAssertInstance(ChangeType.CREATION, tableName);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangesAssert ofModificationOnTable(String tableName) {
    if (originAssert != null) {
      return originAssert.ofModificationOnTable(tableName);
    }
    return getChangeAssertInstance(ChangeType.MODIFICATION, tableName);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangesAssert ofDeletionOnTable(String tableName) {
    if (originAssert != null) {
      return originAssert.ofDeletionOnTable(tableName);
    }
    return getChangeAssertInstance(ChangeType.DELETION, tableName);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangesAssert onTable(String tableName) {
    if (originAssert != null) {
      return originAssert.onTable(tableName);
    }
    return getChangeAssertInstance(null, tableName);
  }

  /**
   * Returns the change of the {@code changeType} on the {@code tableName} at the {@code index} in parameter.
   *
   * @param index      The index corresponding to the change.
   * @param changeType The change type corresponding to the change.
   * @param tableName  The table name
   * @return The change.
   * @throws AssertJDBException If the {@code index} is out of the bounds.
   */
  private Change getChange(int index, ChangeType changeType, String tableName) {
    Changes changes = this.changes;
    if (changeType != null) {
      changes = changes.getChangesOfType(changeType);
    }
    if (tableName != null) {
      changes = changes.getChangesOfTable(tableName);
    }
    List<Change> changesList = changes.getChangesList();
    int size = changesList.size();
    if (index < 0 || index >= size) {
      throw new AssertJDBException("Index %s out of the limits [0, %s[", index, size);
    }
    Change change = changesList.get(index);
    setIndexNextChange(changeType, tableName, index + 1);
    return change;
  }

  /**
   * Gets an instance of change assert corresponding to the index and the type of change. If this instance is already instanced, the method
   * returns it from the cache.
   *
   * @param changeType Type of the change on which is the instance of change assert.
   * @param tableName  Name of the table on which is the instance of change assert.
   * @param index      Index of the change on which is the instance of change assert.
   * @return The change assert implementation.
   */
  private ChangeAssert getChangeAssertInstance(ChangeType changeType, String tableName, int index) {
    Change change = getChange(index, changeType, tableName);
    ChangeAssert changeAssert = changeMap.get(change);
    if (changeAssert != null) {
      return changeAssert;
    }

    ChangeAssert instance = new ChangeAssert(this, change);
    changeMap.put(change, instance);
    setIndexNextChange(changeType, tableName, index + 1);
    return instance.as(
            "Change at index " + index + " of " + info.descriptionText() + getStringBuilderAboutChangeTypeAndTableName(
                    changeType, tableName));
  }

  /**
   * Returns the index of the next change of the type and the table in parameter.
   * @param changeType Type of the change ({@code null} if there is no filter on the type of change)
   * @param tableName Name of the table ({@code null} if there is no filter on the table)
   * @return The index of the next change.
   */
  private Integer getIndexNextChange(ChangeType changeType, String tableName) {
    Map<String, Integer> map = indexNextChangeMap.get(changeType);
    if (map != null) {
      Integer index = map.get(tableName);
      if (index != null) {
        return index;
      }
    }
    return 0;
  }

  /**
   * Sets the index of the next change of the type and the table in parameter.
   * @param changeType Type of the change ({@code null} if there is no filter on the type of change)
   * @param tableName Name of the table ({@code null} if there is no filter on the table)
   * @param index The index of the next change.
   */
  private void setIndexNextChange(ChangeType changeType, String tableName, int index) {
    Map<String, Integer> map = indexNextChangeMap.get(changeType);
    if (map == null) {
      map = new HashMap<>();
      indexNextChangeMap.put(changeType, map);
    }
    map.put(tableName, index);
  }

  /**
   * Returns assertion methods on the next change in the list of changes.
   *
   * @return An object to make assertions on the next change.
   * @throws AssertJDBException If the {@code index} is out of the bounds.
   */
  public ChangeAssert change() {
    return getChangeAssertInstance(null, null, getIndexNextChange(null, null));
  }

  /**
   * Returns assertion methods on the change at the {@code index} in parameter.
   *
   * @param index The index corresponding to the change.
   * @return An object to make assertions on the change.
   * @throws AssertJDBException If the {@code index} is out of the bounds.
   */
  public ChangeAssert change(int index) {
    return getChangeAssertInstance(null, null, index);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeAssert changeOfCreation() {
    if (originAssert != null) {
      return originAssert.changeOfCreation();
    }
    return getChangeAssertInstance(ChangeType.CREATION, null, getIndexNextChange(ChangeType.CREATION, null));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeAssert changeOfCreation(int index) {
    if (originAssert != null) {
      return originAssert.changeOfCreation(index);
    }
    return getChangeAssertInstance(ChangeType.CREATION, null, index);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeAssert changeOfModification() {
    if (originAssert != null) {
      return originAssert.changeOfModification();
    }
    return getChangeAssertInstance(ChangeType.MODIFICATION, null, getIndexNextChange(ChangeType.MODIFICATION, null));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeAssert changeOfModification(int index) {
    if (originAssert != null) {
      return originAssert.changeOfModification(index);
    }
    return getChangeAssertInstance(ChangeType.MODIFICATION, null, index);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeAssert changeOfDeletion() {
    if (originAssert != null) {
      return originAssert.changeOfDeletion();
    }
    return getChangeAssertInstance(ChangeType.DELETION, null, getIndexNextChange(ChangeType.DELETION, null));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeAssert changeOfDeletion(int index) {
    if (originAssert != null) {
      return originAssert.changeOfDeletion(index);
    }
    return getChangeAssertInstance(ChangeType.DELETION, null, index);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeAssert changeOnTable(String tableName) {
    if (originAssert != null) {
      return originAssert.changeOnTable(tableName);
    }
    return getChangeAssertInstance(null, tableName, getIndexNextChange(null, tableName));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeAssert changeOnTable(String tableName, int index) {
    if (originAssert != null) {
      return originAssert.changeOnTable(tableName, index);
    }
    return getChangeAssertInstance(null, tableName, index);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeAssert changeOnTableWithPks(String tableName, Object... pksValues) {
    if (originAssert != null) {
      return originAssert.changeOnTableWithPks(tableName, pksValues);
    }
    Changes changes = this.changes.getChangesOfTable(tableName);
    List<Change> changesList = changes.getChangesList();
    int index = 0;
    for (Change change : changesList) {
      List<Object> pksValueList = change.getPksValueList();
      Object[] values = pksValueList.toArray(new Object[pksValueList.size()]);
      boolean equal = false;
      if (pksValues.length == values.length) {
        equal = true;
        for (int i = 0; i < pksValues.length; i++) {
          if (!Values.areEqual(values[i], pksValues[i])) {
            equal = false;
          }
        }
      }
      if (equal) {
        return getChangeAssertInstance(null, tableName, index);
      }
      index++;
    }
    throw new AssertJDBException("No change found for table " + tableName + " and primary keys " + Arrays.asList(pksValues));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeAssert changeOfCreationOnTable(String tableName) {
    if (originAssert != null) {
      return originAssert.changeOfCreationOnTable(tableName);
    }
    return getChangeAssertInstance(ChangeType.CREATION, tableName, getIndexNextChange(ChangeType.CREATION, tableName));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeAssert changeOfCreationOnTable(String tableName, int index) {
    if (originAssert != null) {
      return originAssert.changeOfCreationOnTable(tableName, index);
    }
    return getChangeAssertInstance(ChangeType.CREATION, tableName, index);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeAssert changeOfModificationOnTable(String tableName) {
    if (originAssert != null) {
      return originAssert.changeOfModificationOnTable(tableName);
    }
    return getChangeAssertInstance(ChangeType.MODIFICATION, tableName, getIndexNextChange(ChangeType.MODIFICATION, tableName));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeAssert changeOfModificationOnTable(String tableName, int index) {
    if (originAssert != null) {
      return originAssert.changeOfModificationOnTable(tableName, index);
    }
    return getChangeAssertInstance(ChangeType.MODIFICATION, tableName, index);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeAssert changeOfDeletionOnTable(String tableName) {
    if (originAssert != null) {
      return originAssert.changeOfDeletionOnTable(tableName);
    }
    return getChangeAssertInstance(ChangeType.DELETION, tableName, getIndexNextChange(ChangeType.DELETION, tableName));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeAssert changeOfDeletionOnTable(String tableName, int index) {
    if (originAssert != null) {
      return originAssert.changeOfDeletionOnTable(tableName, index);
    }
    return getChangeAssertInstance(ChangeType.DELETION, tableName, index);
  }

  /** {@inheritDoc} */
  @Override
  public ChangesAssert hasNumberOfChanges(int expected) {
    return AssertionsOnChanges.hasSize(myself, info, changes, expected);
  }
}
