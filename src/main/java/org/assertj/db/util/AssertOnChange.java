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
package org.assertj.db.util;

import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.core.internal.Failures;
import org.assertj.db.api.AbstractAssert;
import org.assertj.db.type.Change;
import org.assertj.db.type.ChangeType;
import org.assertj.db.type.DataType;
import org.assertj.db.type.Row;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.db.error.ShouldBeChangeType.shouldBeChangeType;
import static org.assertj.db.error.ShouldBeDataType.shouldBeDataType;
import static org.assertj.db.error.ShouldBeOnTable.shouldBeOnTable;
import static org.assertj.db.error.ShouldHaveModifications.shouldHaveModifications;
import static org.assertj.db.error.ShouldHaveNumberOfModifications.shouldHaveNumberOfModifications;
import static org.assertj.db.error.ShouldHavePksNames.shouldHavePksNames;
import static org.assertj.db.error.ShouldHavePksValues.shouldHavePksValues;

/**
 * Utility methods related to assert on change.
 *
 * @author Régis Pouiller
 */
public class AssertOnChange {

  /**
   * To notice failures in the assertion.
   */
  private final static Failures failures = Failures.instance();

  /**
   * Private constructor.
   */
  private AssertOnChange() {
    // Empty
  }

  /**
   * Verifies that the type of the change is equal to the type in parameter.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Info on the object to assert.
   * @param change    The change.
   * @param expected  The expected type to compare to.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type is different to the type in parameter.
   */
  public static <A extends AbstractAssert> A isOfType(A assertion, WritableAssertionInfo info, Change change,
                                                      ChangeType expected) {
    ChangeType type = change.getChangeType();
    if (type != expected) {
      throw failures.failure(info, shouldBeChangeType(expected, type));
    }
    return assertion;
  }

  /**
   * Verifies that the type of the change is a creation.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Info on the object to assert.
   * @param change    The change.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type is different to the type in parameter.
   */
  public static <A extends AbstractAssert> A isCreation(A assertion, WritableAssertionInfo info, Change change) {
    return isOfType(assertion, info, change, ChangeType.CREATION);
  }

  /**
   * Verifies that the type of the change is a modification.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Info on the object to assert.
   * @param change    The change.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type is different to the type in parameter.
   */
  public static <A extends AbstractAssert> A isModification(A assertion, WritableAssertionInfo info, Change change) {
    return isOfType(assertion, info, change, ChangeType.MODIFICATION);
  }

  /**
   * Verifies that the type of the change is a deletion.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Info on the object to assert.
   * @param change    The change.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type is different to the type in parameter.
   */
  public static <A extends AbstractAssert> A isDeletion(A assertion, WritableAssertionInfo info, Change change) {
    return isOfType(assertion, info, change, ChangeType.DELETION);
  }

  /**
   * Verifies that the data type on which the change is equal to the type in parameter.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Info on the object to assert.
   * @param change    The change.
   * @param expected  The expected type to compare to.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type is different to the type in parameter.
   */
  public static <A extends AbstractAssert> A isOnDataType(A assertion, WritableAssertionInfo info, Change change,
                                                          DataType expected) {
    DataType dataType = change.getDataType();
    if (dataType != expected) {
      throw failures.failure(info, shouldBeDataType(expected, dataType));
    }
    return assertion;
  }

  /**
   * Verifies that the data type on which the change is a table.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Info on the object to assert.
   * @param change    The change.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type is different to the type in parameter.
   */
  public static <A extends AbstractAssert> A isOnTable(A assertion, WritableAssertionInfo info, Change change) {
    return isOnDataType(assertion, info, change, DataType.TABLE);
  }

  /**
   * Verifies that the data type on which the change is a request.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Info on the object to assert.
   * @param change    The change.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type is different to the type in parameter.
   */
  public static <A extends AbstractAssert> A isOnRequest(A assertion, WritableAssertionInfo info, Change change) {
    return isOnDataType(assertion, info, change, DataType.REQUEST);
  }

  /**
   * Verifies that the change is a table with the name in parameter.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Info on the object to assert.
   * @param change    The change.
   * @param name The name of the table on which is the change.
   * @return {@code this} assertion object.
   * @throws AssertionError                 If the type is different to the type in parameter.
   * @throws java.lang.NullPointerException If the name in parameter is {@code null}.
   */
  public static <A extends AbstractAssert> A isOnTable(A assertion, WritableAssertionInfo info, Change change, String name) {
    if (name == null) {
      throw new NullPointerException("Table name must be not null");
    }
    isOnTable(assertion, info, change);
    String dataName = change.getDataName();
    if (!dataName.equals(name.toUpperCase())) {
      throw failures.failure(info, shouldBeOnTable(name, dataName));
    }
    return assertion;
  }

  /**
   * Verifies that primary of the rows of the change is the same as the parameters.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Info on the object to assert.
   * @param change    The change.
   * @param names The names of the primary key associated with the rows of the change.
   * @return {@code this} assertion object.
   * @throws AssertionError                 If the type is different to the type in parameter.
   * @throws java.lang.NullPointerException If one of the names in parameters is {@code null}.
   */
  public static <A extends AbstractAssert> A hasPksNames(A assertion, WritableAssertionInfo info, Change change, String... names) {
    if (names == null) {
      throw new NullPointerException("Column name must be not null");
    }

    // Create a sorted list from the primary keys columns
    List<String> pksNameList = change.getPksNameList();
    List<String> pksList = new ArrayList(pksNameList);
    Collections.sort(pksList);

    // Create a sorted list from the parameters
    List<String> namesList = new ArrayList();
    for (String name : names) {
      if (name == null) {
        throw new NullPointerException("Column name must be not null");
      }
      namesList.add(name.toUpperCase());
    }
    Collections.sort(namesList);

    // Compare each list
    if (!namesList.equals(pksList)) {
      String[] pksNames = pksNameList.toArray(new String[pksNameList.size()]);
      throw failures.failure(info, shouldHavePksNames(pksNames, names));
    }

    return assertion;
  }

  /**
   * Verifies that the values primary of the rows of the change are the same as the parameters.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Info on the object to assert.
   * @param change    The change.
   * @param values The values of the primary key associated with the rows of the change.
   * @return {@code this} assertion object.
   * @throws AssertionError                 If the type is different to the type in parameter.
   */
  public static <A extends AbstractAssert> A hasPksValues(A assertion, WritableAssertionInfo info, Change change, Object... values) {
    // Create a array from the primary keys columns
    Row rowAtStartPoint = change.getRowAtStartPoint();
    Row rowAtEndPoint = change.getRowAtEndPoint();
    List<Object> pksValuesList;
    if (rowAtStartPoint != null) {
      pksValuesList = rowAtStartPoint.getValuesList();
    }
    else {
      pksValuesList = rowAtEndPoint.getValuesList();
    }
    List<String> columnsNameList = change.getColumnsNameList();
    List<String> pksNamesList = change.getPksNameList();
    List<Object> pksList = new ArrayList();
    for (String name : pksNamesList) {
      int index = columnsNameList.indexOf(name);
      Object value = pksValuesList.get(index);
      pksList.add(value);
    }
    Object[] pksValues = pksList.toArray(new Object[pksList.size()]);

    // If the length of the values is different than the length of the expected values
    if (values.length != pksValues.length) {
      Object[] representationsValues = Values.getRepresentationsFromValuesInFrontOfExpected(pksValues, values);
      throw failures.failure(info, shouldHavePksValues(representationsValues, values));
    }

    // Compare each list
    int index = 0;
    for (Object pkValue : pksList) {
      Object value = values[index];
      if (!Values.areEqual(pkValue, value)) {
        Object[] representationsValues = Values.getRepresentationsFromValuesInFrontOfExpected(pksValues, values);
        throw failures.failure(info, shouldHavePksValues(representationsValues, values));
      }
      index++;
    }

    return assertion;
  }

  /**
   * Verifies that the number of columns with a modification in the values between the start point and the end point
   * is equals to the number in parameter.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Info on the object to assert.
   * @param change    The change.
   * @param number The expected number of modified columns
   * @return {@code this} assertion object.
   * @throws AssertionError If the type is different to the type in parameter.
   */
  public static <A extends AbstractAssert> A hasNumberOfModifiedColumns(A assertion, WritableAssertionInfo info, Change change, int number) {
    Integer[] indexesOfModifiedColumns = Changes.getIndexesOfModifiedColumns(change);

    if (number != indexesOfModifiedColumns.length) {
      throw failures.failure(info, shouldHaveNumberOfModifications(indexesOfModifiedColumns.length, number));
    }
    return assertion;
  }

  /**
   * Verifies that the indexes of columns with a modification in the values between the start point and the end point
   * is equals to the parameters.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Info on the object to assert.
   * @param change    The change.
   * @param indexes Indexes of the modified columns.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type is different to the type in parameter.
   */
  public static <A extends AbstractAssert> A hasModifiedColumns(A assertion, WritableAssertionInfo info, Change change, Integer... indexes) {
    if (indexes == null) {
      throw new NullPointerException("Column index must be not null");
    }

    // Create a sorted list from the modified columns
    Integer[] indexesOfModifiedColumns = Changes.getIndexesOfModifiedColumns(change);
    List<Integer> indexesOfModifiedList = Arrays.asList(indexesOfModifiedColumns);
    Collections.sort(indexesOfModifiedList);

    // Create a sorted list from the parameters
    List<Integer> indexesList = new ArrayList();
    for (Integer index : indexes) {
      if (index == null) {
        throw new NullPointerException("Column index must be not null");
      }
      indexesList.add(index);
    }
    Collections.sort(indexesList);

    // Compare each list
    if (!indexesList.equals(indexesOfModifiedList)) {
      throw failures.failure(info, shouldHaveModifications(indexesOfModifiedColumns, indexes));
    }

    return assertion;
  }

  /**
   * Verifies that the names of columns with a modification in the values between the start point and the end point
   * is equals to the parameters.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Info on the object to assert.
   * @param change    The change.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type is different to the type in parameter.
   */
  public static <A extends AbstractAssert> A hasModifiedColumns(A assertion, WritableAssertionInfo info, Change change, String... names) {
    if (names == null) {
      throw new NullPointerException("Column name must be not null");
    }

    // Create a sorted list from the parameters
    List<String> namesList = new ArrayList();
    for (String name : names) {
      if (name == null) {
        throw new NullPointerException("Column name must be not null");
      }
      namesList.add(name.toUpperCase());
    }
    Collections.sort(namesList);

    // Create a sorted list from the modified columns
    Integer[] indexesOfModifiedColumns = Changes.getIndexesOfModifiedColumns(change);
    String[] namesOfModifiedColumns = new String[names.length];
    List<String> columnsNameList = change.getColumnsNameList();
    for (int i = 0; i < indexesOfModifiedColumns.length; i++) {
      namesOfModifiedColumns[i] = columnsNameList.get(indexesOfModifiedColumns[i]);
    }
    List<String> namesOfModifiedList = Arrays.asList(namesOfModifiedColumns);
    Collections.sort(namesOfModifiedList);

    // Compare each list
    if (!namesList.equals(namesOfModifiedList)) {
      throw failures.failure(info, shouldHaveModifications(namesOfModifiedColumns, names));
    }

    return assertion;
  }

}
