/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2015-2020 the original author or authors.
 */
package org.assertj.db.navigation;

import org.assertj.db.exception.AssertJDBException;
import org.assertj.db.global.AbstractElement;
import org.assertj.db.type.Change;
import org.assertj.db.type.ChangeType;
import org.assertj.db.type.Changes;
import org.assertj.db.type.Value;
import org.assertj.db.util.Values;

import java.lang.reflect.Constructor;
import java.util.*;

import static org.assertj.db.util.Proxies.unProxy;

/**
 * Position during navigation.
 *
 * @param <E> The class of the actual position (an sub-class of {@link org.assertj.db.global.AbstractElement} and of {@link org.assertj.db.navigation.Navigation}).
 * @param <N> The class of the next position where the navigation go (an sub-class of {@link org.assertj.db.global.AbstractElement} and of {@link org.assertj.db.navigation.Navigation}).
 *
 * @author Régis Pouiller
 * @since 1.1.0
 */
public abstract class PositionWithChanges<E extends AbstractElement<E> & Navigation, N extends AbstractElement<N> & Navigation> {

  private final E myself;

  /**
   * Index of the next change to get per type of change.
   */
  private final Map<ChangeType, Map<String, Integer>> indexNextChangeMap = new HashMap<>();

  /**
   * Map the change assert with their type of change and table name in key (contains the change assert already generated).
   */
  private final Map<ChangeType, Map<String, E>> changesAssertMap = new HashMap<>();

  /**
   * Map the change assert with the change in key (contains the change assert already generated).
   */
  private final Map<Change, N> changeMap = new HashMap<>();
  /**
   * Class of the actual element of navigation (used to make instance).
   */
  private final Class<E> actualElementClass;

  /**
   * Class of the next element of navigation (used to make instance).
   */
  private final Class<N> nextElementClass;

  /**
   * Constructor.
   *
   * @param myself Actual value.
   * @param actualElementClass Class of the actual element of navigation (used to make instance).
   * @param nextElementClass Class of the next element of navigation (used to make instance).
   */
  public PositionWithChanges(E myself, Class<E> actualElementClass, Class<N> nextElementClass) {
    this.myself = myself;
    this.actualElementClass = actualElementClass;
    this.nextElementClass = nextElementClass;
  }

  /**
   * Returns an instance of changes from the cache.
   *
   * @param changeType Type of the change on which is the instance of change.
   * @param tableName  Name of the table on which is the instance of change.
   * @return The changes assert from the cache.
   */
  private E getFromCache(ChangeType changeType, String tableName) {
    Map<String, E> mapWithTableName = changesAssertMap.get(changeType);
    if (mapWithTableName == null) {
      return null;
    }
    return mapWithTableName.get(tableName);
  }

  /**
   * Sets an instance of changes in the cache.
   *
   * @param changeType   Type of the change on which is the instance of change.
   * @param tableName    Name of the table on which is the instance of change.
   * @param changesInstance Changes to add in the cache.
   */
  private void setInCache(ChangeType changeType, String tableName, E changesInstance) {
    Map<String, E> mapWithTableName = changesAssertMap.computeIfAbsent(changeType, k -> new HashMap<>());
    mapWithTableName.put(tableName, changesInstance);
  }

  /**
   * Gets an instance of changes corresponding to the index and the type of change. If this instance is already instanced, the method
   * returns it from the cache.
   *
   * @param changes The changes
   * @param changeType Type of the change on which is the instance of change.
   * @param tableName  Name of the table on which is the instance of change.
   * @return The changes implementation.
   */
  public E getChangesInstance(Changes changes, ChangeType changeType, String tableName) {
    E instance = getFromCache(changeType, tableName);
    if (instance != null) {
      return instance;
    }
    Changes nextChanges = changes;
    if (changeType != null) {
      nextChanges = nextChanges.getChangesOfType(changeType);
    }
    if (tableName != null) {
      nextChanges = nextChanges.getChangesOfTable(tableName);
    }

    try {
      Class clazz = unProxy(myself.getClass());
      Constructor<E> constructor = actualElementClass.getDeclaredConstructor(clazz, Changes.class);
      instance = constructor.newInstance(myself, nextChanges);
      instance.as(getChangesDescription(changeType, tableName));
      setInCache(changeType, tableName, instance);
      return instance;
    } catch (Exception e) {
      throw new AssertJDBException(String.format("There is an exception '" + e.getMessage()
                                                 + "'%n\t in the instantiation of the element " + actualElementClass.getName()
                                                 + "%n\t on "
                                                 + Changes.class
                                                 + " with " + myself.getClass() + ".%n "
                                                 + "It is normally impossible.%n That means there is a big mistake in the development of AssertJDB.%n "
                                                 + "Please write an issue for that if you meet this problem."));
    }
  }

  /**
   * Returns the change of the {@code changeType} on the {@code tableName} at the {@code index} in parameter.
   *
   * @param changes The changes
   * @param index      The index corresponding to the change.
   * @param changeType The change type corresponding to the change.
   * @param tableName  The table name
   * @return The change.
   * @throws AssertJDBException If the {@code index} is out of the bounds.
   */
  private Change getChange(Changes changes, int index, ChangeType changeType, String tableName) {
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
   * @param changes The changes
   * @param changeType Type of the change on which is the instance of change assert.
   * @param tableName  Name of the table on which is the instance of change assert.
   * @return The change assert implementation.
   */
  public N getChangeInstance(Changes changes, ChangeType changeType, String tableName) {
    return getChangeInstance(changes, changeType, tableName, getIndexNextChange(changeType, tableName));
  }

  /**
   * Gets an instance of change assert corresponding to the index and the type of change. If this instance is already instanced, the method
   * returns it from the cache.
   *
   * @param changes The changes
   * @param changeType Type of the change on which is the instance of change assert.
   * @param tableName  Name of the table on which is the instance of change assert.
   * @param index      Index of the change on which is the instance of change assert.
   * @return The change assert implementation.
   */
  public N getChangeInstance(Changes changes, ChangeType changeType, String tableName, int index) {
    Change change = getChange(changes, index, changeType, tableName);
    N instance = changeMap.get(change);
    if (instance != null) {
      return instance;
    }

    try {
      Class clazz = unProxy(myself.getClass());
      Constructor<N> constructor = nextElementClass.getDeclaredConstructor(clazz, Change.class);
      instance = constructor.newInstance(myself, change);
      instance.as(getChangeDescription(changes, change, index, changeType, tableName));
      changeMap.put(change, instance);
      setIndexNextChange(changeType, tableName, index + 1);
      return instance;
    } catch (Exception e) {
      throw new AssertJDBException(String.format("There is an exception '" + e.getMessage()
                                                 + "'%n\t in the instantiation of the element " + nextElementClass.getName()
                                                 + "%n\t on "
                                                 + Change.class
                                                 + " with " + myself.getClass() + ".%n "
                                                 + "It is normally impossible.%n That means there is a big mistake in the development of AssertJDB.%n "
                                                 + "Please write an issue for that if you meet this problem."));
    }
  }

  /**
   * Gets an instance of change assert corresponding to the table and the primary keys.
   * If this instance is already instanced, the method returns it from the cache.
   *
   * @param changes The changes
   * @param tableName  Name of the table on which is the instance of change assert.
   * @param pksValues The values of the primary key corresponding to the {@link org.assertj.db.type.Change}.
   * @return The change assert implementation.
   */
  public N getChangeInstanceWithPK(Changes changes, String tableName, Object... pksValues) {
    Changes changesOfTable = changes.getChangesOfTable(tableName);
    List<Change> changesList = changesOfTable.getChangesList();
    int index = 0;
    for (Change change : changesList) {
      List<Value> pksValueList = change.getPksValueList();
      Value[] values = pksValueList.toArray(new Value[0]);
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
        return getChangeInstance(changesOfTable, null, tableName, index);
      }
      index++;
    }
    throw new AssertJDBException("No change found for table " + tableName + " and primary keys " + Arrays
        .asList(pksValues));
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
    Map<String, Integer> map = indexNextChangeMap.computeIfAbsent(changeType, k -> new HashMap<>());
    map.put(tableName, index);
  }

  /**
   * Returns the description.
   *
   * @param changeType Type of the change.
   * @param tableName  Name of the table.
   * @return The description
   */
  protected abstract String getChangesDescription(ChangeType changeType, String tableName);

  /**
   * Returns the description.
   *
   * @param changes    The changes
   * @param change     The change
   * @param index      Index of the value.
   * @param changeType Type of the change.
   * @param tableName  Name of the table.
   * @return The description
   */
  protected abstract String getChangeDescription(Changes changes, Change change, int index,
                                                 ChangeType changeType, String tableName);
}
