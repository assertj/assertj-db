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
 * Copyright 2015-2024 the original author or authors.
 */
package org.assertj.db.api.assertions.impl;

import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.core.internal.Failures;
import org.assertj.db.api.AbstractAssert;
import org.assertj.db.type.Change;
import org.assertj.db.type.lettercase.LetterCase;
import org.assertj.db.util.Changes;

import java.util.*;

import static org.assertj.db.error.ShouldHaveModifications.shouldHaveModifications;
import static org.assertj.db.error.ShouldHaveNumberOfModifications.shouldHaveNumberOfModifications;
import static org.assertj.db.error.ShouldHaveNumberOfModificationsGreater.shouldHaveNumberOfModificationsGreater;
import static org.assertj.db.error.ShouldHaveNumberOfModificationsGreaterOrEqual.shouldHaveNumberOfModificationsGreaterOrEqual;
import static org.assertj.db.error.ShouldHaveNumberOfModificationsLess.shouldHaveNumberOfModificationsLess;
import static org.assertj.db.error.ShouldHaveNumberOfModificationsLessOrEqual.shouldHaveNumberOfModificationsLessOrEqual;

/**
 * Implements the assertion methods on modified columns.
 *
 * @author Régis Pouiller
 * @see org.assertj.db.api.assertions.AssertOnModifiedColumns
 */
public class AssertionsOnModifiedColumns {

  /**
   * To notice failures in the assertion.
   */
  private static final Failures failures = Failures.instance();

  /**
   * Private constructor.
   */
  private AssertionsOnModifiedColumns() {
    // Empty
  }

  /**
   * Verifies that the number of columns with a modification in the values between the start point and the end point
   * is equals to the number in parameter.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Writable information about an assertion.
   * @param change    The change.
   * @param number    The expected number of modified columns
   * @return {@code this} assertion object.
   * @throws AssertionError If the number of modified columns is different to the number in parameter.
   */
  public static <A extends AbstractAssert<?>> A hasNumberOfModifiedColumns(A assertion, WritableAssertionInfo info,
                                                                        Change change, int number) {
    Integer[] indexesOfModifiedColumns = Changes.getIndexesOfModifiedColumns(change);

    if (number != indexesOfModifiedColumns.length) {
      throw failures.failure(info, shouldHaveNumberOfModifications(indexesOfModifiedColumns.length, number));
    }
    return assertion;
  }

  /**
   * Verifies that the number of columns with a modification in the values between the start point and the end point
   * is greater than the number in parameter.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Writable information about an assertion.
   * @param change    The change.
   * @param number    The expected number of modified columns
   * @return {@code this} assertion object.
   * @throws AssertionError If the number of modified columns is less than or equal to the number in parameter.
   */
  public static <A extends AbstractAssert<?>> A hasNumberOfModifiedColumnsGreaterThan(A assertion, WritableAssertionInfo info,
                                                                                   Change change, int number) {
    Integer[] indexesOfModifiedColumns = Changes.getIndexesOfModifiedColumns(change);

    if (number >= indexesOfModifiedColumns.length) {
      throw failures.failure(info, shouldHaveNumberOfModificationsGreater(indexesOfModifiedColumns.length, number));
    }
    return assertion;
  }

  /**
   * Verifies that the number of columns with a modification in the values between the start point and the end point
   * is less than the number in parameter.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Writable information about an assertion.
   * @param change    The change.
   * @param number    The expected number of modified columns
   * @return {@code this} assertion object.
   * @throws AssertionError If the number of modified columns is greater than or equal to the number in parameter.
   */
  public static <A extends AbstractAssert<?>> A hasNumberOfModifiedColumnsLessThan(A assertion, WritableAssertionInfo info,
                                                                                Change change, int number) {
    Integer[] indexesOfModifiedColumns = Changes.getIndexesOfModifiedColumns(change);

    if (number <= indexesOfModifiedColumns.length) {
      throw failures.failure(info, shouldHaveNumberOfModificationsLess(indexesOfModifiedColumns.length, number));
    }
    return assertion;
  }

  /**
   * Verifies that the number of columns with a modification in the values between the start point and the end point
   * is greater than or equal to the number in parameter.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Writable information about an assertion.
   * @param change    The change.
   * @param number    The expected number of modified columns
   * @return {@code this} assertion object.
   * @throws AssertionError If the number of modified columns is less than the number in parameter.
   */
  public static <A extends AbstractAssert<?>> A hasNumberOfModifiedColumnsGreaterThanOrEqualTo(A assertion, WritableAssertionInfo info,
                                                                        Change change, int number) {
    Integer[] indexesOfModifiedColumns = Changes.getIndexesOfModifiedColumns(change);

    if (number > indexesOfModifiedColumns.length) {
      throw failures.failure(info, shouldHaveNumberOfModificationsGreaterOrEqual(indexesOfModifiedColumns.length,
                                                                                 number));
    }
    return assertion;
  }

  /**
   * Verifies that the number of columns with a modification in the values between the start point and the end point
   * is less than or equal to the number in parameter.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Writable information about an assertion.
   * @param change    The change.
   * @param number    The expected number of modified columns
   * @return {@code this} assertion object.
   * @throws AssertionError If the number of modified columns is greater than the number in parameter.
   */
  public static <A extends AbstractAssert<?>> A hasNumberOfModifiedColumnsLessThanOrEqualTo(A assertion, WritableAssertionInfo info,
                                                                                   Change change, int number) {
    Integer[] indexesOfModifiedColumns = Changes.getIndexesOfModifiedColumns(change);

    if (number < indexesOfModifiedColumns.length) {
      throw failures.failure(info, shouldHaveNumberOfModificationsLessOrEqual(indexesOfModifiedColumns.length, number));
    }
    return assertion;
  }

  /**
   * Verifies that the indexes of columns with a modification in the values between the start point and the end point
   * is equals to the parameters.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Writable information about an assertion.
   * @param change    The change.
   * @param indexes   Indexes of the modified columns.
   * @return {@code this} assertion object.
   * @throws AssertionError If the indexes of the modified columns are different to the indexes in parameters.
   */
  public static <A extends AbstractAssert<?>> A hasModifiedColumns(A assertion, WritableAssertionInfo info, Change change,
                                                                Integer... indexes) {
    if (indexes == null) {
      throw new NullPointerException("Columns indexes must be not null");
    }

    // Create a sorted list from the modified columns
    Integer[] indexesOfModifiedColumns = Changes.getIndexesOfModifiedColumns(change);
    List<Integer> indexesOfModifiedList = Arrays.asList(indexesOfModifiedColumns);
    Collections.sort(indexesOfModifiedList);

    // Create a sorted list from the parameters
    List<Integer> indexesList = new ArrayList<>();
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
   * @param info      Writable information about an assertion.
   * @param change    The change.
   * @param columnLetterCase Letter case of the column names.
   * @param names     The names of the modified columns.
   * @return {@code this} assertion object.
   * @throws AssertionError If the names of the modified columns are different to the names in parameters.
   */
  public static <A extends AbstractAssert<?>> A hasModifiedColumns(A assertion, WritableAssertionInfo info, Change change,
                                                                LetterCase columnLetterCase, String... names) {
    if (names == null) {
      throw new NullPointerException("Columns names must be not null");
    }

    // Create a sorted list from the parameters
    List<String> namesList = new ArrayList<>();
    for (String name : names) {
      if (name == null) {
        throw new NullPointerException("Column name must be not null");
      }
      namesList.add(name);
    }
    namesList.sort(columnLetterCase);

    // Create a sorted list from the modified columns
    Integer[] indexesOfModifiedColumns = Changes.getIndexesOfModifiedColumns(change);
    String[] namesOfModifiedColumns = new String[indexesOfModifiedColumns.length];
    List<String> columnsNameList = change.getColumnsNameList();
    for (int i = 0; i < indexesOfModifiedColumns.length; i++) {
      namesOfModifiedColumns[i] = columnsNameList.get(indexesOfModifiedColumns[i]);
    }
    List<String> namesOfModifiedList = Arrays.asList(namesOfModifiedColumns);
    namesOfModifiedList.sort(columnLetterCase);

    // Compare each list
    Iterator<String> namesIterator = namesList.iterator();
    Iterator<String> namesOfModifiedIterator = namesOfModifiedList.iterator();
    while (namesIterator.hasNext() && namesOfModifiedIterator.hasNext()) {
      String name = namesIterator.next();
      String nameOfModified = namesOfModifiedIterator.next();
      if (!columnLetterCase.isEqual(name, nameOfModified)) {
        throw failures.failure(info, shouldHaveModifications(namesOfModifiedColumns, names));
      }
    }
    if (namesIterator.hasNext() || namesOfModifiedIterator.hasNext()) {
      throw failures.failure(info, shouldHaveModifications(namesOfModifiedColumns, names));
    }

    return assertion;
  }
}
