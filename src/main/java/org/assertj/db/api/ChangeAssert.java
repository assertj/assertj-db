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

import org.assertj.db.api.assertions.AssertOnChange;
import org.assertj.db.api.origin.OriginWithColumnsAndRowsFromChange;
import org.assertj.db.exception.AssertJDBException;
import org.assertj.db.type.Change;
import org.assertj.db.type.ChangeType;
import org.assertj.db.type.DataType;
import org.assertj.db.type.Row;
import org.assertj.db.util.AssertionsOnChange;
import org.assertj.db.util.Changes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Assertion methods about the {@link Change}.
 *
 * @author Régis Pouiller
 */
public class ChangeAssert extends AbstractAssertWithOriginWithChanges<ChangeAssert, ChangesAssert>
        implements OriginWithColumnsAndRowsFromChange, AssertOnChange<ChangeAssert> {

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
  private Map<Integer, ChangeColumnAssert> columnsAssertMap = new HashMap<>();

  /**
   * Constructor.
   *
   * @param originalAssert The original assert.
   * @param change         The {@link Change} on which are the assertions.
   */
  ChangeAssert(ChangesAssert originalAssert, Change change) {
    super(ChangeAssert.class, originalAssert);
    this.change = change;
  }

  /**
   * Returns the assert on the row at start point.
   *
   * @return The assert on the row at start point.
   */
  public ChangeRowAssert rowAtStartPoint() {
    if (changeRowAssertAtStartPoint == null) {
      StringBuilder stringBuilder = new StringBuilder("Row at start point of ");
      stringBuilder.append(info.descriptionText());
      changeRowAssertAtStartPoint = new ChangeRowAssert(this, change.getRowAtStartPoint()).as(stringBuilder.toString());
    }
    return changeRowAssertAtStartPoint;
  }

  /**
   * Returns the assert on the row at end point.
   *
   * @return The assert on the row at end point.
   */
  public ChangeRowAssert rowAtEndPoint() {
    if (changeRowAssertAtEndPoint == null) {
      StringBuilder stringBuilder = new StringBuilder("Row at end point of ");
      stringBuilder.append(info.descriptionText());
      changeRowAssertAtEndPoint = new ChangeRowAssert(this, change.getRowAtEndPoint()).as(stringBuilder.toString());
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
  protected ChangeColumnAssert getChangeColumnAssertInstance(int index) {
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
    return instance.as("Column at index " + index + " of " + info.descriptionText());
  }

  /**
   * Returns assertion methods on the next {@link ChangeColumnAssert} in the list of {@link ChangeColumnAssert}.
   *
   * @return An object to make assertions on the next {@link ChangeColumnAssert}.
   * @throws org.assertj.db.exception.AssertJDBException If the {@code index} is out of the bounds.
   */
  public ChangeColumnAssert column() {
    return getChangeColumnAssertInstance(indexNextColumn);
  }

  /**
   * Returns assertion methods on the {@link ChangeColumnAssert} at the {@code index} in parameter.
   *
   * @param index The index corresponding to the {@link ChangeColumnAssert}.
   * @return An object to make assertions on the {@link ChangeColumnAssert}.
   * @throws org.assertj.db.exception.AssertJDBException If the {@code index} is out of the bounds.
   */
  public ChangeColumnAssert column(int index) {
    return getChangeColumnAssertInstance(index);
  }

  /**
   * Returns assertion methods on the {@link ChangeColumnAssert} corresponding to the column name in parameter.
   *
   * @param columnName The column name.
   * @return An object to make assertions on the {@link ChangeColumnAssert}.
   * @throws NullPointerException                        If the column name in parameter is null.
   * @throws org.assertj.db.exception.AssertJDBException If there is no column with this name.
   */
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

  /**
   * Returns assertion methods on the next {@link ChangeColumnAssert} in the list of the modified columns.
   *
   * @return An object to make assertions on the {@link ChangeColumnAssert}.
   * @throws org.assertj.db.exception.AssertJDBException If the {@code index} is out of the bounds.
   */
  public ChangeColumnAssert columnAmongTheModifiedOnes() {
    Integer[] indexesOfModifiedColumns = Changes.getIndexesOfModifiedColumns(change);
    for (Integer indexModified : indexesOfModifiedColumns) {
      if (indexModified >= indexNextColumn) {
        return getChangeColumnAssertInstance(indexModified);
      }
    }
    throw new AssertJDBException("No more modified columns");
  }

  /**
   * Returns assertion methods on the {@link ChangeColumnAssert} at the {@code index} in parameter among the modified columns.
   *
   * @param index The index corresponding to the {@link ChangeColumnAssert}.
   * @return An object to make assertions on the {@link ChangeColumnAssert}.
   * @throws org.assertj.db.exception.AssertJDBException If the {@code index} is out of the bounds.
   */
  public ChangeColumnAssert columnAmongTheModifiedOnes(int index) {
    Integer[] indexesOfModifiedColumns = Changes.getIndexesOfModifiedColumns(change);
    int size = indexesOfModifiedColumns.length;
    if (index < 0 || index >= size) {
      throw new AssertJDBException("Index %s out of the limits of the modified columns [0, %s[", index, size);
    }
    int indexModified = indexesOfModifiedColumns[index];
    return getChangeColumnAssertInstance(indexModified);
  }

  /**
   * Returns assertion methods on the {@link ChangeColumnAssert} corresponding to the column name in parameter among the modified columns.
   *
   * @param columnName The column name.
   * @return An object to make assertions on the {@link ChangeColumnAssert}.
   * @throws NullPointerException                        If the column name in parameter is null.
   * @throws org.assertj.db.exception.AssertJDBException If there is no column with this name.
   */
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

  /**
   * Verifies that the data type on which the change is equal to the type in parameter.
   * <p>
   * Example where the assertion verifies that the change is on data type {@code TABLE} :
   * </p>
   * <pre>
   * <code class='java'>
   * assertThat(changes).change(1).isOnDataType(DataType.TABLE);
   * </code>
   * </pre>
   *
   * @param expected The expected type to compare to.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type is different to the type in parameter.
   */
  public ChangeAssert isOnDataType(DataType expected) {
    return AssertionsOnChange.isOnDataType(myself, info, change, expected);
  }

  /**
   * Verifies that the data type on which the change is a table.
   * <p>
   * Example where the assertion verifies that the change is on data type {@code TABLE} :
   * </p>
   * <pre>
   * <code class='java'>
   * assertThat(changes).change(1).isOnTable();
   * </code>
   * </pre>
   *
   * @return {@code this} assertion object.
   * @throws AssertionError If the type is different to the type in parameter.
   */
  public ChangeAssert isOnTable() {
    return AssertionsOnChange.isOnTable(myself, info, change);
  }

  /**
   * Verifies that the data type on which the change is a request.
   * <p>
   * Example where the assertion verifies that the change is on data type {@code REQUEST} :
   * </p>
   * <pre>
   * <code class='java'>
   * assertThat(changes).change(1).isOnRequest();
   * </code>
   * </pre>
   *
   * @return {@code this} assertion object.
   * @throws AssertionError If the type is different to the type in parameter.
   */
  public ChangeAssert isOnRequest() {
    return AssertionsOnChange.isOnRequest(myself, info, change);
  }

  /**
   * Verifies that the change is a table with the name in parameter.
   * <p>
   * Example where the assertion verifies that the change is on {@code TABLE} called movie :
   * </p>
   * <pre>
   * <code class='java'>
   * assertThat(changes).change(1).isOnTable("movie");
   * </code>
   * </pre>
   *
   * @param name The name of the table on which is the change.
   * @return {@code this} assertion object.
   * @throws AssertionError                 If the type is different to the type in parameter.
   * @throws java.lang.NullPointerException If the name in parameter is {@code null}.
   */
  public ChangeAssert isOnTable(String name) {
    return AssertionsOnChange.isOnTable(myself, info, change, name);
  }

  /**
   * Verifies that primary of the rows of the change is the same as the parameters.
   * <p>
   * Example where the assertion verifies that primary key is the column called id :
   * </p>
   * <pre>
   * <code class='java'>
   * assertThat(changes).change(1).hasPksNames("id");
   * </code>
   * </pre>
   *
   * @param names The names of the primary key associated with the rows of the change.
   * @return {@code this} assertion object.
   * @throws AssertionError                 If the type is different to the type in parameter.
   * @throws java.lang.NullPointerException If one of the names in parameters is {@code null}.
   */
  public ChangeAssert hasPksNames(String... names) {
    return AssertionsOnChange.hasPksNames(myself, info, change, names);
  }

  /**
   * Verifies that the values primary of the rows of the change are the same as the parameters.
   * <p>
   * Example where the assertion verifies that primary key have the value 1 :
   * </p>
   * <pre>
   * <code class='java'>
   * assertThat(changes).change(1).hasPksValues(1);
   * </code>
   * </pre>
   *
   * @param values The values of the primary key associated with the rows of the change.
   * @return {@code this} assertion object.
   * @throws AssertionError                 If the type is different to the type in parameter.
   */
  public ChangeAssert hasPksValues(Object... values) {
    return AssertionsOnChange.hasPksValues(myself, info, change, values);
  }

  /**
   * Verifies that the type of the change is equal to the type in parameter.
   * <p>
   * Example where the assertion verifies that the change is of type {@code CREATION} :
   * </p>
   * <pre>
   * <code class='java'>
   * assertThat(changes).change(1).isOfType(ChangeType.CREATION);
   * </code>
   * </pre>
   *
   * @param expected The expected type to compare to.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type is different to the type in parameter.
   */
  public ChangeAssert isOfType(ChangeType expected) {
    return AssertionsOnChange.isOfType(myself, info, change, expected);
  }

  /**
   * Verifies that the type of the change is a creation.
   * <p>
   * Example where the assertion verifies that the change is a creation :
   * </p>
   * <pre>
   * <code class='java'>
   * assertThat(changes).change(1).isCreation();
   * </code>
   * </pre>
   *
   * @return {@code this} assertion object.
   * @throws AssertionError If the type is different to the type in parameter.
   */
  public ChangeAssert isCreation() {
    return AssertionsOnChange.isCreation(myself, info, change);
  }

  /**
   * Verifies that the type of the change is a modification.
   * <p>
   * Example where the assertion verifies that the change is a modification :
   * </p>
   * <pre>
   * <code class='java'>
   * assertThat(changes).change(1).isModification();
   * </code>
   * </pre>
   *
   * @return {@code this} assertion object.
   * @throws AssertionError If the type is different to the type in parameter.
   */
  public ChangeAssert isModification() {
    return AssertionsOnChange.isModification(myself, info, change);
  }

  /**
   * Verifies that the type of the change is a deletion.
   * <p>
   * Example where the assertion verifies that the change is a deletion :
   * </p>
   * <pre>
   * <code class='java'>
   * assertThat(changes).change(1).isDeletion();
   * </code>
   * </pre>
   *
   * @return {@code this} assertion object.
   * @throws AssertionError If the type is different to the type in parameter.
   */
  public ChangeAssert isDeletion() {
    return AssertionsOnChange.isDeletion(myself, info, change);
  }

  /**
   * Verifies that the number of columns with a modification in the values between the start point and the end point
   * is equals to the number in parameter.
   * <p>
   * Example where the assertion verifies that there are 3 modified columns :
   * </p>
   * <pre>
   * <code class='java'>
   * assertThat(changes).change(1).hasNumberOfModifiedColumns(3);
   * </code>
   * </pre>
   *
   * @param number The expected number of modified columns
   * @return {@code this} assertion object.
   * @throws AssertionError If the type is different to the type in parameter.
   */
  public ChangeAssert hasNumberOfModifiedColumns(int number) {
    return AssertionsOnChange.hasNumberOfModifiedColumns(myself, info, change, number);
  }

  /**
   * Verifies that the indexes of columns with a modification in the values between the start point and the end point
   * is equals to the parameters.
   * <p>
   * Example where the assertion verifies that indexes of modified columns are 3 and 5 :
   * </p>
   * <pre>
   * <code class='java'>
   * assertThat(changes).change(1).hasModifiedColumns(3, 5);
   * </code>
   * </pre>
   *
   * @param indexes Indexes of the modified columns.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type is different to the type in parameter.
   */
  public ChangeAssert hasModifiedColumns(Integer... indexes) {
    return AssertionsOnChange.hasModifiedColumns(myself, info, change, indexes);
  }

  /**
   * Verifies that the names of columns with a modification in the values between the start point and the end point
   * is equals to the parameters.
   * <p>
   * Example where the assertion verifies that names of modified columns are "name" and "birth" :
   * </p>
   * <pre>
   * <code class='java'>
   * assertThat(changes).change(1).hasModifiedColumns("name", "birth");
   * </code>
   * </pre>
   *
   * @param names Names of the modified columns.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type is different to the type in parameter.
   */
  public ChangeAssert hasModifiedColumns(String... names) {
    return AssertionsOnChange.hasModifiedColumns(myself, info, change, names);
  }
}
