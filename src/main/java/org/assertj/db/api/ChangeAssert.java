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

import org.assertj.db.api.assertions.*;
import org.assertj.db.api.assertions.impl.*;
import org.assertj.db.api.origin.OriginWithColumnsAndRowsFromChange;
import org.assertj.db.exception.AssertJDBException;
import org.assertj.db.type.Change;
import org.assertj.db.type.ChangeType;
import org.assertj.db.type.DataType;
import org.assertj.db.type.Row;
import org.assertj.db.util.Changes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Assertion methods for a {@link Change}.
 *
 * @author Régis Pouiller
 */
public class ChangeAssert
        extends AbstractAssertWithOriginWithChanges<ChangeAssert, ChangesAssert>
        implements OriginWithColumnsAndRowsFromChange,
                   AssertOnDataType<ChangeAssert>,
                   AssertOnPrimaryKey<ChangeAssert>,
                   AssertOnChangeType<ChangeAssert>,
                   AssertOnModifiedColumns<ChangeAssert>,
                   AssertOnNumberOfColumns<ChangeAssert> {

  /**
   * The actual change on which the assertion is.
   */
  private final Change change;

  /**
   * The assertion on the row at start point.
   */
  private ChangeRowAssert changeRowAssertAtStartPoint;
  /**
   * The assertion on the row at end point.
   */
  private ChangeRowAssert changeRowAssertAtEndPoint;

  /**
   * Index of the next value to get.
   */
  private int indexNextColumn;
  /**
   * Map the columns assert with their index in key (contains the columns assert already generated).
   */
  private final Map<Integer, ChangeColumnAssert> columnsAssertMap = new HashMap<>();

  /**
   * Constructor.
   *
   * @param origin The assertion of {@link org.assertj.db.api.origin.Origin}.
   * @param change The {@link Change} on which are the assertions.
   */
  ChangeAssert(ChangesAssert origin, Change change) {
    super(ChangeAssert.class, origin);
    this.change = change;
  }

  /** {@inheritDoc} */
  @Override
  public ChangeRowAssert rowAtStartPoint() {
    if (changeRowAssertAtStartPoint == null) {
      String string = "Row at start point of " + info.descriptionText();
      changeRowAssertAtStartPoint = new ChangeRowAssert(this, change.getRowAtStartPoint()).as(string);
    }
    return changeRowAssertAtStartPoint;
  }

  /** {@inheritDoc} */
  @Override
  public ChangeRowAssert rowAtEndPoint() {
    if (changeRowAssertAtEndPoint == null) {
      String string = "Row at end point of " + info.descriptionText();
      changeRowAssertAtEndPoint = new ChangeRowAssert(this, change.getRowAtEndPoint()).as(string);
    }
    return changeRowAssertAtEndPoint;
  }

  /**
   * Gets an instance of value assert corresponding to the index. If this instance is already instanced, the method
   * returns it from the cache.
   *
   * @param index Index of the value on which is the instance of value assert.
   * @return The value assert implementation.
   * @throws org.assertj.db.exception.AssertJDBException If the {@code index} is out of the bounds.
   */
  private ChangeColumnAssert getChangeColumnAssertInstance(int index) {
    if (columnsAssertMap.containsKey(index)) {
      ChangeColumnAssert changeColumnAssert = columnsAssertMap.get(index);
      indexNextColumn = index + 1;
      return changeColumnAssert;
    }

    int size = change.getColumnsNameList().size();
    if (index < 0 || index >= size) {
      throw new AssertJDBException("Index %s out of the limits [0, %s[", index, size);
    }
    Row rowAtStartPoint = change.getRowAtStartPoint();
    Row rowAtEndPoint = change.getRowAtEndPoint();
    Object valueAtStartPoint = null;
    Object valueAtEndPoint = null;
    if (rowAtStartPoint != null) {
      List<Object> valuesAtStartPoint = rowAtStartPoint.getValuesList();
      valueAtStartPoint = valuesAtStartPoint.get(index);
    }
    if (rowAtEndPoint != null) {
      List<Object> valuesAtEndPoint = rowAtEndPoint.getValuesList();
      valueAtEndPoint = valuesAtEndPoint.get(index);
    }
    List<String> columnsNameList = change.getColumnsNameList();
    String columnName = columnsNameList.get(index);
    ChangeColumnAssert instance = new ChangeColumnAssert(this, columnName, valueAtStartPoint, valueAtEndPoint);
    columnsAssertMap.put(index, instance);
    indexNextColumn = index + 1;
    return instance.as("Column at index " + index + " (column name : " + columnName + ") of " + info.descriptionText());
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnAssert column() {
    return getChangeColumnAssertInstance(indexNextColumn);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnAssert column(int index) {
    return getChangeColumnAssertInstance(index);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnAssert column(String columnName) {
    if (columnName == null) {
      throw new NullPointerException("Column name must be not null");
    }
    List<String> columnsNameList = change.getColumnsNameList();
    int index = columnsNameList.indexOf(columnName.toUpperCase());
    if (index == -1) {
      throw new AssertJDBException("Column <%s> does not exist", columnName);
    }
    return getChangeColumnAssertInstance(index);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnAssert columnAmongTheModifiedOnes() {
    Integer[] indexesOfModifiedColumns = Changes.getIndexesOfModifiedColumns(change);
    for (Integer indexModified : indexesOfModifiedColumns) {
      if (indexModified >= indexNextColumn) {
        return getChangeColumnAssertInstance(indexModified);
      }
    }
    throw new AssertJDBException("No more modified columns");
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnAssert columnAmongTheModifiedOnes(int index) {
    Integer[] indexesOfModifiedColumns = Changes.getIndexesOfModifiedColumns(change);
    int size = indexesOfModifiedColumns.length;
    if (index < 0 || index >= size) {
      throw new AssertJDBException("Index %s out of the limits of the modified columns [0, %s[", index, size);
    }
    int indexModified = indexesOfModifiedColumns[index];
    return getChangeColumnAssertInstance(indexModified);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnAssert columnAmongTheModifiedOnes(String columnName) {
    if (columnName == null) {
      throw new NullPointerException("Column name must be not null");
    }
    Integer[] indexesOfModifiedColumns = Changes.getIndexesOfModifiedColumns(change);
    List<String> columnsNameList = change.getColumnsNameList();
    for (Integer indexModified : indexesOfModifiedColumns) {
      String modifiedColumnName = columnsNameList.get(indexModified);
      if (modifiedColumnName.equalsIgnoreCase(columnName)) {
        return getChangeColumnAssertInstance(indexModified);
      }
    }
    throw new AssertJDBException("Column <%s> do not exist among the modified columns", columnName);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeAssert isOnDataType(DataType expected) {
    return AssertionsOnDataType.isOnDataType(myself, info, change, expected);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeAssert isOnTable() {
    return AssertionsOnDataType.isOnTable(myself, info, change);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeAssert isOnRequest() {
    return AssertionsOnDataType.isOnRequest(myself, info, change);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeAssert isOnTable(String name) {
    return AssertionsOnDataType.isOnTable(myself, info, change, name);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeAssert hasPksNames(String... names) {
    return AssertionsOnPrimaryKey.hasPksNames(myself, info, change, names);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeAssert hasPksValues(Object... values) {
    return AssertionsOnPrimaryKey.hasPksValues(myself, info, change, values);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeAssert isOfType(ChangeType expected) {
    return AssertionsOnChangeType.isOfType(myself, info, change, expected);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeAssert isCreation() {
    return AssertionsOnChangeType.isCreation(myself, info, change);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeAssert isModification() {
    return AssertionsOnChangeType.isModification(myself, info, change);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeAssert isDeletion() {
    return AssertionsOnChangeType.isDeletion(myself, info, change);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeAssert hasNumberOfModifiedColumns(int number) {
    return AssertionsOnModifiedColumns.hasNumberOfModifiedColumns(myself, info, change, number);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeAssert hasModifiedColumns(Integer... indexes) {
    return AssertionsOnModifiedColumns.hasModifiedColumns(myself, info, change, indexes);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeAssert hasModifiedColumns(String... names) {
    return AssertionsOnModifiedColumns.hasModifiedColumns(myself, info, change, names);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeAssert hasNumberOfColumns(int expected) {
    return AssertionsOnNumberOfColumns.hasNumberOfColumns(myself, info, change.getColumnsNameList().size(), expected);
  }

  /**
   * Returns to level of assertion methods on {@link Changes}.
   *
   * @return a object of assertion methods on {@link Changes}.
   */
  public ChangesAssert returnToChanges() {
    return returnToOrigin();
  }
}
