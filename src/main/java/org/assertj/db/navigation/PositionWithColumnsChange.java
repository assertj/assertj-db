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
import org.assertj.db.type.Row;
import org.assertj.db.type.Value;
import org.assertj.db.type.lettercase.CaseComparison;
import org.assertj.db.util.Changes;
import org.assertj.db.util.NameComparator;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public abstract class PositionWithColumnsChange<E extends AbstractElement<E> & Navigation, N extends AbstractElement<N> & Navigation> {

  /**
   * Actual value.
   */
  private final E myself;
  /**
   * Index of the next to get.
   */
  private int nextIndex;
  /**
   * Class of the element of navigation (used to make instance).
   */
  private final Class<N> elementClass;
  /**
   * Map the elements of navigation with their index in key (contains the elements of navigation already generated).
   */
  private final Map<Integer, N> elementsMap = new HashMap<>();

  /**
   * Constructor.
   *
   * @param myself Actual value.
   * @param elementClass Class of the element of navigation (used to make instance).
   */
  public PositionWithColumnsChange(E myself, Class<N> elementClass) {
    this.myself = myself;
    this.elementClass = elementClass;
  }

  /**
   * Gets an instance of element corresponding to the index. If this instance is already instanced, the method
   * returns it from the cache.
   *
   * @param change The change
   * @return The instance.
   * @throws org.assertj.db.exception.AssertJDBException If the {@code index} is out of the bounds.
   */
  public N getChangeColumnInstance(Change change) {
    return getChangeColumnInstance(change, nextIndex);
  }

  /**
   * Gets an instance of element corresponding to the index. If this instance is already instanced, the method
   * returns it from the cache.
   *
   * @param change The change
   * @param index Index of the instance.
   * @return The instance.
   * @throws org.assertj.db.exception.AssertJDBException If the {@code index} is out of the bounds.
   */
  public N getChangeColumnInstance(Change change, int index) {
    if (elementsMap.containsKey(index)) {
      N instance = elementsMap.get(index);
      nextIndex = index + 1;
      return instance;
    }

    int size = change.getColumnsNameList().size();
    if (index < 0 || index >= size) {
      throw new AssertJDBException("Index %s out of the limits [0, %s[", index, size);
    }
    List<String> columnsNameList = change.getColumnsNameList();
    String columnName = columnsNameList.get(index);
    Row rowAtStartPoint = change.getRowAtStartPoint();
    Row rowAtEndPoint = change.getRowAtEndPoint();
    Value valueAtStartPoint;
    Value valueAtEndPoint;
    if (rowAtStartPoint != null) {
      List<Value> valuesAtStartPoint = rowAtStartPoint.getValuesList();
      valueAtStartPoint = valuesAtStartPoint.get(index);
    } else {
      valueAtStartPoint = Value.getNullValue(columnName, change.getColumnLetterCase());
    }
    if (rowAtEndPoint != null) {
      List<Value> valuesAtEndPoint = rowAtEndPoint.getValuesList();
      valueAtEndPoint = valuesAtEndPoint.get(index);
    } else {
      valueAtEndPoint = Value.getNullValue(columnName, change.getColumnLetterCase());
    }
    try {
      Class clazz = unProxy(myself.getClass());
      Constructor<N> constructor = elementClass.getDeclaredConstructor(clazz, String.class, Value.class, Value.class);
      N instance = constructor.newInstance(myself, columnName, valueAtStartPoint, valueAtEndPoint);
      elementsMap.put(index, instance);
      nextIndex = index + 1;
      instance.as(getDescription(index, columnName));
      return instance;
    } catch (Exception e) {
      throw new AssertJDBException(String.format("There is an exception '" + e.getMessage()
                                                 + "'%n\t in the instantiation of the element " + elementClass.getName() + "%n"
                                                 + "\t with " + myself.getClass() + ".%n "
                                                 + "It is normally impossible.%n That means there is a big mistake in the development of AssertJDB.%n "
                                                 + "Please write an issue for that if you meet this problem."));
    }
  }

  /**
   * Gets an instance of element corresponding to the column name in parameter.
   * If this instance is already instanced, the method returns it from the cache.
   *
   * @param change The change
   * @param columnName      Name of the column of the element on which is the instance of element of navigation.
   * @param comparison      Case comparison for column name.
   * @return The instance.
   * @throws org.assertj.db.exception.AssertJDBException If the {@code index} is out of the bounds.
   */
  public N getChangeColumnInstance(Change change, String columnName, CaseComparison comparison) {
    if (columnName == null) {
      throw new NullPointerException("Column name must be not null");
    }
    List<String> columnsNameList = change.getColumnsNameList();
    int index = NameComparator.INSTANCE.indexOf(columnsNameList, columnName, comparison);
    if (index == -1) {
      throw new AssertJDBException(String.format("Column <%s> does not exist%nin <%s>%nwith comparison %s",
                                                 columnName, columnsNameList, comparison.getComparisonName()));
    }
    return getChangeColumnInstance(change, index);
  }

  /**
   * Gets an instance of next modified element. If this instance is already instanced, the method
   * returns it from the cache.
   *
   * @param change The change
   * @return The instance.
   * @throws org.assertj.db.exception.AssertJDBException If the {@code index} is out of the bounds.
   */
  public N getModifiedChangeColumnInstance(Change change) {
    Integer[] indexesOfModifiedColumns = Changes.getIndexesOfModifiedColumns(change);
    for (Integer indexModified : indexesOfModifiedColumns) {
      if (indexModified >= nextIndex) {
        return getChangeColumnInstance(change, indexModified);
      }
    }
    throw new AssertJDBException("No more modified columns");
  }

  /**
   * Gets an instance of modified element corresponding to the index. If this instance is already instanced, the method
   * returns it from the cache.
   *
   * @param change The change
   * @param index Index of the instance.
   * @return The instance.
   * @throws org.assertj.db.exception.AssertJDBException If the {@code index} is out of the bounds.
   */
  public N getModifiedChangeColumnInstance(Change change, int index) {
    Integer[] indexesOfModifiedColumns = Changes.getIndexesOfModifiedColumns(change);
    int size = indexesOfModifiedColumns.length;
    if (index < 0 || index >= size) {
      throw new AssertJDBException("Index %s out of the limits of the modified columns [0, %s[", index, size);
    }
    int indexModified = indexesOfModifiedColumns[index];
    return getChangeColumnInstance(change, indexModified);
  }

  /**
   * Gets an instance of modified element corresponding to the column name in parameter.
   * If this instance is already instanced, the method returns it from the cache.
   *
   * @param change The change
   * @param columnName      Name of the column of the element on which is the instance of element of navigation.
   * @param comparison      Case comparison for column name.
   * @return The instance.
   * @throws org.assertj.db.exception.AssertJDBException If the {@code index} is out of the bounds.
   */
  public N getModifiedChangeColumnInstance(Change change, String columnName, CaseComparison comparison) {
    if (columnName == null) {
      throw new NullPointerException("Column name must be not null");
    }
    Integer[] indexesOfModifiedColumns = Changes.getIndexesOfModifiedColumns(change);
    List<String> modifiedColumnsNameList = new ArrayList<>();
    List<String> columnsNameList = change.getColumnsNameList();
    for (Integer indexModified : indexesOfModifiedColumns) {
      String modifiedColumnName = columnsNameList.get(indexModified);
      modifiedColumnsNameList.add(modifiedColumnName);
      if (comparison.isEqual(modifiedColumnName, columnName)) {
        return getChangeColumnInstance(change, indexModified);
      }
    }
    throw new AssertJDBException(
        String.format("Column <%s> does not exist among the modified columns%nin <%s>%nwith comparison %s",
                      columnName, modifiedColumnsNameList, comparison.getComparisonName()));
  }

  /**
   * Returns the description.
   *
   * @param index Index of the value.
   * @param columnName      Name of the column of the element.
   * @return The description
   */
  protected abstract String getDescription(int index, String columnName);
}
