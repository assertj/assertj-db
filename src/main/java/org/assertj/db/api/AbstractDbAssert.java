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

import org.assertj.db.api.assertions.AssertOnNumberOfColumns;
import org.assertj.db.api.assertions.AssertOnNumberOfRows;
import org.assertj.db.api.origin.OriginWithColumnsAndRows;
import org.assertj.db.exception.AssertJDBException;
import org.assertj.db.type.AbstractDbData;
import org.assertj.db.type.Column;
import org.assertj.db.type.Row;
import org.assertj.db.util.AssertionsOnColumn;
import org.assertj.db.util.AssertionsOnRow;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Assertion methods about the data in a {@code Table} or in a {@code Request}.
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
    extends AbstractAssert<A> implements OriginWithColumnsAndRows<D, A, C, CV, R, RV>, AssertOnNumberOfColumns<A>,
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
   * Class of the assert on the row (used to make instance).
   */
  private final Class<R> rowAssertClass;
  /**
   * Class of the assert on the column (used to make instance).
   */
  private final Class<C> columnAssertClass;
  /**
   * Index of the next column to get.
   */
  private int indexNextColumn;
  /**
   * Map the rows assert with their index in key (contains the rows assert already generated).
   */
  private Map<Integer, R> rowsAssertMap = new HashMap<Integer, R>();
  /**
   * Map the columns assert with their index in key (contains the columns assert already generated).
   */
  private Map<Integer, C> columnsAssertMap = new HashMap<Integer, C>();

  /**
   * Constructor of the database assertions.
   * 
   * @param actualValue The actual value on which the assertion is.
   * @param selfType Class of the assertion.
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

    try {
      Constructor<R> constructor = rowAssertClass.getDeclaredConstructor(myself.getClass(), Row.class);
      R instance = constructor.newInstance(this, getRow(index));
      rowsAssertMap.put(index, instance);
      return instance.as("Row at index " + index + " of " + info.descriptionText());
    } catch (Exception e) {
      throw new AssertJDBException("There is an exception '" + e.getMessage()
          + "'\n\t in the instanciation of the assertion " + rowAssertClass.getName() + "\n\t on the row with "
          + myself.getClass() + ".\n "
          + "It is normally impossible.\n That means there is a big mistake in the development of AssertJDB.\n "
          + "Please write an issue for that if you meet this problem.");
    }
  }

  /**
   * Returns assertion methods on the next {@link Row} in the list of {@link Row}.
   * 
   * @return An object to make assertions on the next {@link Row}.
   * @throws AssertJDBException If the {@code index} is out of the bounds.
   */
  public R row() {
    return getRowAssertInstance(indexNextRow);
  }

  /**
   * Returns assertion methods on the {@link Row} at the {@code index} in parameter.
   * 
   * @param index The index corresponding to the {@link Row}.
   * @return An object to make assertions on the {@link Row}.
   * @throws AssertJDBException If the {@code index} is out of the bounds.
   */
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

    try {
      Constructor<C> constructor = columnAssertClass.getDeclaredConstructor(myself.getClass(), Column.class);
      C instance = constructor.newInstance(this, getColumn(index));
      columnsAssertMap.put(index, instance);
      return instance.as("Column at index " + index + " of " + info.descriptionText());
    } catch (Exception e) {
      throw new AssertJDBException("There is an exception '" + e.getMessage()
          + "'\n\t in the instanciation of the assertion " + columnAssertClass.getName() + "\n\t on the column with "
          + myself.getClass() + ".\n "
          + "It is normally impossible.\n That means there is a big mistake in the development of AssertJDB.\n "
          + "Please write an issue for that if you meet this problem.");
    }
  }

  /**
   * Returns assertion methods on the next {@link Column} in the list of {@link Column}.
   * 
   * @return An object to make assertions on the next {@link Column}.
   * @throws AssertJDBException If the {@code index} is out of the bounds.
   */
  public C column() {
    return getColumnAssertInstance(indexNextColumn);
  }

  /**
   * Returns assertion methods on the {@link Column} at the {@code index} in parameter.
   * 
   * @param index The index corresponding to the {@link Column}.
   * @return An object to make assertions on the {@link Column}.
   * @throws AssertJDBException If the {@code index} is out of the bounds.
   */
  public C column(int index) {
    return getColumnAssertInstance(index);
  }

  /**
   * Returns assertion methods on the {@link Column} corresponding to the column name in parameter.
   * 
   * @param columnName The column name.
   * @return An object to make assertions on the {@link Column}.
   * @throws NullPointerException If the column name in parameter is null.
   * @throws AssertJDBException If there is no column with this name.
   */
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

  /**
   * Verifies that the number of rows is equal to the number in parameter.
   * <p>
   * Example where the assertion verifies that the table has 2 rows :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).hasNumberOfRows(2);
   * </code></pre>
   *
   * @param expected The number to compare to the number of rows.
   * @return {@code this} assertion object.
   * @throws AssertionError If the number of rows is different to the number in parameter.
   */
  public A hasNumberOfRows(int expected) {
    List<Row> rowsList = actual.getRowsList();
    int size = rowsList.size();
    return AssertionsOnColumn.hasSize(myself, info, size, expected);
  }

  /** {@inheritDoc} */
  @Override
  public A hasNumberOfColumns(int expected) {
    List<String> columnsNameList = actual.getColumnsNameList();
    int size = columnsNameList.size();
    return AssertionsOnRow.hasSize(myself, info, size, expected);
  }
}
