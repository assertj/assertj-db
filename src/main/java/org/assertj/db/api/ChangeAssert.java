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
import org.assertj.db.type.Row;

import java.util.*;

import static org.assertj.db.error.ShouldBeChangeType.shouldBeChangeType;
import static org.assertj.db.error.ShouldHaveModifications.shouldHaveModifications;
import static org.assertj.db.error.ShouldHaveNumberOfModifications.shouldHaveNumberOfModifications;

/**
 * Assertion methods about the {@link Change}.
 *
 * @author Régis Pouiller
 */
public class ChangeAssert extends AbstractAssertWithChanges<ChangeAssert, ChangesAssert>
        implements OriginAssertWithColumnsAndRowsFromChange {

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
    ChangeColumnAssert instance = new ChangeColumnAssert(this, valueAtStartPoint, valueAtEndPoint);
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
   * @throws NullPointerException If the column name in parameter is null.
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
    ChangeType type = change.getChangeType();
    if (type != expected) {
      throw failures.failure(info, shouldBeChangeType(expected, type));
    }
    return this;
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
    return isOfType(ChangeType.CREATION);
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
    return isOfType(ChangeType.MODIFICATION);
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
    return isOfType(ChangeType.DELETION);
  }

  /**
   * Returns the indexes of the modified columns.
   * @return The indexes.
   */
  private Integer[] getIndexesOfModifiedColumns() {
    List<Integer> indexesList = new ArrayList<>();
    Row rowAtStartPoint = change.getRowAtStartPoint();
    Row rowAtEndPoint = change.getRowAtEndPoint();
    if (rowAtStartPoint != null && rowAtEndPoint != null) {
      List<Object> valuesListAtStartPoint = rowAtStartPoint.getValuesList();
      List<Object> valuesListAtEndPoint = rowAtEndPoint.getValuesList();
      Iterator<Object> iteratorAtEndPoint = valuesListAtEndPoint.iterator();
      int index = 0;
      for (Object valueAtStartPoint : valuesListAtStartPoint) {
        Object valueAtEndPoint  = iteratorAtEndPoint.next();

        if ((valueAtStartPoint == null && valueAtEndPoint != null) ||
            (valueAtStartPoint != null && !valueAtStartPoint.equals(valueAtEndPoint))) {

          indexesList.add(index);
        }
        index++;
      }
    }
    else if (rowAtStartPoint != null) {
      List<Object> valuesListAtStartPoint = rowAtStartPoint.getValuesList();
      int index = 0;
      for (Object valueAtStartPoint : valuesListAtStartPoint) {
        if (valueAtStartPoint != null) {
          indexesList.add(index);
        }
        index++;
      }
    }
    else if (rowAtEndPoint != null) {
      List<Object> valuesListAtEndPoint = rowAtEndPoint.getValuesList();
      int index = 0;
      for (Object valueAtEndPoint : valuesListAtEndPoint) {
        if (valueAtEndPoint != null) {
          indexesList.add(index);
        }
        index++;
      }
    }

    return indexesList.toArray(new Integer[indexesList.size()]);
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
    Integer[] indexesOfModifiedColumns = getIndexesOfModifiedColumns();

    if (number != indexesOfModifiedColumns.length) {
      throw failures.failure(info, shouldHaveNumberOfModifications(indexesOfModifiedColumns.length, number));
    }
    return this;
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
    List<Integer> indexesList = Arrays.asList(indexes);
    Collections.sort(indexesList);
    Integer[] indexesOfModifiedColumns = getIndexesOfModifiedColumns();
    List<Integer> indexesOfModifiedList = Arrays.asList(indexesOfModifiedColumns);
    Collections.sort(indexesOfModifiedList);

    if (!indexesList.equals(indexesOfModifiedList)) {
      throw failures.failure(info, shouldHaveModifications(indexesOfModifiedColumns, indexes));
    }

    return this;
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
    Integer[] indexesOfModifiedColumns = getIndexesOfModifiedColumns();
    String[] namesOfModifiedColumns = new String[names.length];
    List<String> columnsNameList = change.getColumnsNameList();
    for (int i = 0; i < indexesOfModifiedColumns.length; i++) {
      namesOfModifiedColumns[i] = columnsNameList.get(indexesOfModifiedColumns[i]);
    }

    List<String> namesList = new ArrayList();
    for (String name : names) {
      namesList.add(name.toUpperCase());
    }
    Collections.sort(namesList);

    List<String> namesOfModifiedList = Arrays.asList(namesOfModifiedColumns);
    Collections.sort(namesOfModifiedList);

    if (!namesList.equals(namesOfModifiedList)) {
      throw failures.failure(info, shouldHaveModifications(namesOfModifiedColumns, names));
    }

    return this;
  }
}
