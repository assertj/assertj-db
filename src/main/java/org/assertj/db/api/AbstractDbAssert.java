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
 * Copyright 2012-2015 the original author or authors.
 */
package org.assertj.db.api;

import org.assertj.db.api.assertions.AssertOnNumberOfColumns;
import org.assertj.db.api.assertions.AssertOnNumberOfRows;
import org.assertj.db.api.assertions.impl.AssertionsOnNumberOfColumns;
import org.assertj.db.api.assertions.impl.AssertionsOnNumberOfRows;
import org.assertj.db.api.origin.OriginWithColumnsAndRows;
import org.assertj.db.exception.AssertJDBException;
import org.assertj.db.type.AbstractDbData;
import org.assertj.db.type.Column;
import org.assertj.db.type.Row;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Base class for all data ({@code Table} or {@code Request}) assertions.
 *
 * @author Régis Pouiller
 * 
 * @param <D> The class of the actual value (an sub-class of {@link AbstractDbData}).
 * @param <A> The class of the original assert (an sub-class of {@link AbstractDbAssert}).
 * @param <C> The class of this assert (an sub-class of {@link AbstractColumnAssert}).
 * @param <CV> The class of this assertion on the value (an sub-class of {@link AbstractColumnValueAssert}).
 * @param <R> The class of the equivalent row assert (an sub-class of {@link AbstractRowAssert}).
 * @param <RV> The class of the equivalent row assertion on the value (an sub-class of {@link AbstractRowValueAssert}).
 */
public abstract class AbstractDbAssert<D extends AbstractDbData<D>, A extends AbstractDbAssert<D, A, C, CV, R, RV>, C extends AbstractColumnAssert<D, A, C, CV, R, RV>, CV extends AbstractColumnValueAssert<D, A, C, CV, R, RV>, R extends AbstractRowAssert<D, A, C, CV, R, RV>, RV extends AbstractRowValueAssert<D, A, C, CV, R, RV>>
        extends AbstractAssert<A>
        implements OriginWithColumnsAndRows<C, R>,
                   AssertOnNumberOfColumns<A>,
                   AssertOnNumberOfRows<A> {

  /**
   * The actual value on which the assertion is.
   */
  private final D actual;

  /**
   * Index of the next row to get.
   */
  private int indexNextRow;
  /**
   * Class of the assertion on the row (used to make instance).
   */
  private final Class<R> rowAssertClass;
  /**
   * Class of the assertion on the column (used to make instance).
   */
  private final Class<C> columnAssertClass;
  /**
   * Index of the next column to get.
   */
  private int indexNextColumn;
  /**
   * Map the rows assertion with their index in key (contains the rows assertion already generated).
   */
  private final Map<Integer, R> rowsAssertMap = new HashMap<>();
  /**
   * Map the columns assertion with their index in key (contains the columns assertion already generated).
   */
  private final Map<Integer, C> columnsAssertMap = new HashMap<>();

  /**
   * Constructor of the database assertions.
   * 
   * @param actualValue The actual value on which the assertion is.
   * @param selfType Type of this assertion class.
   * @param columnAssertType Class of the assertion on the column.
   * @param rowAssertType Class of the assertion on the row.
   */
  AbstractDbAssert(D actualValue, Class<A> selfType, Class<C> columnAssertType, Class<R> rowAssertType) {
    super(selfType);
    actual = actualValue;
    rowAssertClass = rowAssertType;
    columnAssertClass = columnAssertType;
  }

  /**
   * Returns the {@link Row} at the {@code index} in parameter.
   * 
   * @param index The index corresponding to the {@link Row}.
   * @return The {@link Row}.
   * @throws AssertJDBException If the {@code index} is out of the bounds.
   */
  protected Row getRow(int index) {
    int size = actual.getRowsList().size();
    if (index < 0 || index >= size) {
      throw new AssertJDBException("Index %s out of the limits [0, %s[", index, size);
    }
    Row row = actual.getRow(index);
    indexNextRow = index + 1;
    return row;
  }

  /**
   * Gets an instance of row assert corresponding to the index. If this instance is already instanced, the method
   * returns it from the cache.
   * 
   * @param index Index of the row on which is the instance of row assert.
   * @return The row assert implementation.
   */
  private R getRowAssertInstance(int index) {
    if (rowsAssertMap.containsKey(index)) {
      R rowAssert = rowsAssertMap.get(index).initialize();
      indexNextRow = index + 1;
      return rowAssert;
    }

    Row row = getRow(index);
    try {
      Constructor<R> constructor = rowAssertClass.getDeclaredConstructor(myself.getClass(), Row.class);
      R instance = constructor.newInstance(this, row);
      rowsAssertMap.put(index, instance);
      return instance.as("Row at index " + index + " of " + info.descriptionText());
    } catch (Exception e) {
      throw new AssertJDBException("There is an exception '" + e.getMessage()
          + "'\n\t in the instantiation of the assertion " + rowAssertClass.getName() + "\n\t on the row with "
          + myself.getClass() + ".\n "
          + "It is normally impossible.\n That means there is a big mistake in the development of AssertJDB.\n "
          + "Please write an issue for that if you meet this problem.");
    }
  }

  /** {@inheritDoc} */
  @Override
  public R row() {
    return getRowAssertInstance(indexNextRow);
  }

  /** {@inheritDoc} */
  @Override
  public R row(int index) {
    return getRowAssertInstance(index);
  }

  /**
   * Returns the {@link Column} at the {@code index} in parameter.
   * 
   * @param index The index corresponding to the {@link Column}.
   * @return The {@link Column}.
   * @throws AssertJDBException If the {@code index} is out of the bounds.
   */
  protected Column getColumn(int index) {
    List<String> columnsNameList = actual.getColumnsNameList();
    int size = columnsNameList.size();
    if (index < 0 || index >= size) {
      throw new AssertJDBException("Index %s out of the limits [0, %s[", index, size);
    }
    Column column = actual.getColumn(index);
    indexNextColumn = index + 1;
    return column;
  }

  /**
   * Gets an instance of column assert corresponding to the index. If this instance is already instanced, the method
   * returns it from the cache.
   * 
   * @param index Index of the column on which is the instance of column assert.
   * @return The column assert implementation.
   */
  private C getColumnAssertInstance(int index) {
    if (columnsAssertMap.containsKey(index)) {
      C columnAssert = columnsAssertMap.get(index).initialize();
      indexNextColumn = index + 1;
      return columnAssert;
    }

    Column column = getColumn(index);
    List<String> columnsNameList = actual.getColumnsNameList();
    String columnName = columnsNameList.get(index);
    try {
      Constructor<C> constructor = columnAssertClass.getDeclaredConstructor(myself.getClass(), Column.class);
      C instance = constructor.newInstance(this, column);
      columnsAssertMap.put(index, instance);
      return instance.as("Column at index " + index + " (column name : " + columnName + ") of " + info.descriptionText());
    } catch (Exception e) {
      throw new AssertJDBException("There is an exception '" + e.getMessage()
          + "'\n\t in the instantiation of the assertion " + columnAssertClass.getName() + "\n\t on the column with "
          + myself.getClass() + ".\n "
          + "It is normally impossible.\n That means there is a big mistake in the development of AssertJDB.\n "
          + "Please write an issue for that if you meet this problem.");
    }
  }

  /** {@inheritDoc} */
  @Override
  public C column() {
    return getColumnAssertInstance(indexNextColumn);
  }

  /** {@inheritDoc} */
  @Override
  public C column(int index) {
    return getColumnAssertInstance(index);
  }

  /** {@inheritDoc} */
  @Override
  public C column(String columnName) {
    if (columnName == null) {
      throw new NullPointerException("Column name must be not null");
    }
    List<String> columnsNameList = actual.getColumnsNameList();
    int index = columnsNameList.indexOf(columnName.toUpperCase());
    if (index == -1) {
      throw new AssertJDBException("Column <%s> does not exist", columnName);
    }
    return getColumnAssertInstance(index);
  }

  /** {@inheritDoc} */
  @Override
  public A hasNumberOfRows(int expected) {
    List<Row> rowsList = actual.getRowsList();
    int size = rowsList.size();
    return AssertionsOnNumberOfRows.hasNumberOfRows(myself, info, size, expected);
  }

  /** {@inheritDoc} */
  @Override
  public A hasNumberOfColumns(int expected) {
    List<String> columnsNameList = actual.getColumnsNameList();
    int size = columnsNameList.size();
    return AssertionsOnNumberOfColumns.hasNumberOfColumns(myself, info, size, expected);
  }
}
