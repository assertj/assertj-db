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
 * Copyright 2012-2016 the original author or authors.
 */
package org.assertj.db.api.assertions.impl;

import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.core.internal.Failures;
import org.assertj.db.api.AbstractAssert;
import org.assertj.db.type.*;
import org.assertj.db.util.Values;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.db.error.ShouldContainsValue.shouldContainsValue;

/**
 * Implements the assertion methods on the content of a column.
 *
 * @author Régis Pouiller
 * @see org.assertj.db.api.assertions.AssertOnColumnContent
 * @since 1.1.0
 */
public class AssertionsOnColumnContent {

  /**
   * To notice failures in the assertion.
   */
  private final static Failures failures = Failures.instance();

  /**
   * Private constructor.
   */
  private AssertionsOnColumnContent() {
    // Empty
  }

  /**
   * Verifies that the column contains objects.
   *
   * @param <A>        The type of the assertion which call this method.
   * @param assertion  The assertion which call this method.
   * @param info       Writable information about an assertion.
   * @param valuesList The list of values.
   * @param expected The expected object values.
   * @return {@code this} assertion object.
   * @throws AssertionError If the column are not containing the objects in parameter.
   */
  public static <A extends AbstractAssert<?>> A containsValues(A assertion, WritableAssertionInfo info,
                                                       List<Value> valuesList, Object... expected) {
    AssertionsOnNumberOfRows.hasNumberOfRows(assertion, info, valuesList.size(), expected.length);
    List<Value> list = new ArrayList<>(valuesList);
    int index = 0;
    for (Object val : expected) {
      boolean found = false;
      List<Value> newList = new ArrayList<>();
      for (Value obj : list) {
        if (found || !Values.areEqual(obj, val)) {
          newList.add(obj);
        }
        else {
          found = true;
        }
      }
      if (!found) {
        List<Object> listForError = new ArrayList<>();
        for (Value obj : valuesList) {
          listForError.add(Values.getRepresentationFromValueInFrontOfExpected(obj, Object.class));
        }
        throw failures.failure(info, shouldContainsValue(listForError, expected, val, index));
      }
      list = newList;
      index++;
    }
    return assertion;
  }

  /**
   * Verifies that the column contains booleans.
   *
   * @param <A>        The type of the assertion which call this method.
   * @param assertion  The assertion which call this method.
   * @param info       Writable information about an assertion.
   * @param valuesList The list of values.
   * @param expected The expected boolean values.
   * @return {@code this} assertion object.
   * @throws AssertionError If the column are not containing the booleans in parameter.
   */
  public static <A extends AbstractAssert<?>> A containsValues(A assertion, WritableAssertionInfo info,
                                                       List<Value> valuesList, Boolean... expected) {
    AssertionsOnColumnType.isBoolean(assertion, info, valuesList, true);
    AssertionsOnNumberOfRows.hasNumberOfRows(assertion, info, valuesList.size(), expected.length);
    List<Value> list = new ArrayList<>(valuesList);
    int index = 0;
    for (Boolean val : expected) {
      boolean found = false;
      List<Value> newList = new ArrayList<>();
      for (Value obj : list) {
        if (found || !Values.areEqual(obj, val)) {
          newList.add(obj);
        }
        else {
          found = true;
        }
      }
      if (!found) {
        List<Object> listForError = new ArrayList<>();
        for (Value obj : valuesList) {
          listForError.add(Values.getRepresentationFromValueInFrontOfExpected(obj, Boolean.class));
        }
        throw failures.failure(info, shouldContainsValue(listForError, expected, val, index));
      }
      list = newList;
      index++;
    }
    return assertion;
  }

  /**
   * Verifies that the column contains numbers.
   *
   * @param <A>        The type of the assertion which call this method.
   * @param assertion  The assertion which call this method.
   * @param info       Writable information about an assertion.
   * @param valuesList The list of values.
   * @param expected The expected numbers values.
   * @return {@code this} assertion object.
   * @throws AssertionError If the column are not containing the numbers in parameter.
   */
  public static <A extends AbstractAssert<?>> A containsValues(A assertion, WritableAssertionInfo info,
                                                       List<Value> valuesList, Number... expected) {
    AssertionsOnColumnType.isNumber(assertion, info, valuesList, true);
    AssertionsOnNumberOfRows.hasNumberOfRows(assertion, info, valuesList.size(), expected.length);
    List<Value> list = new ArrayList<>(valuesList);
    int index = 0;
    for (Number val : expected) {
      boolean found = false;
      List<Value> newList = new ArrayList<>();
      for (Value obj : list) {
        if (found || !Values.areEqual(obj, val)) {
          newList.add(obj);
        }
        else {
          found = true;
        }
      }
      if (!found) {
        List<Object> listForError = new ArrayList<>();
        for (Value obj : valuesList) {
          listForError.add(Values.getRepresentationFromValueInFrontOfExpected(obj, Number.class));
        }
        throw failures.failure(info, shouldContainsValue(listForError, expected, val, index));
      }
      list = newList;
      index++;
    }
    return assertion;
  }

  /**
   * Verifies that the column contains bytes.
   *
   * @param <A>        The type of the assertion which call this method.
   * @param assertion  The assertion which call this method.
   * @param info       Writable information about an assertion.
   * @param valuesList The list of values.
   * @param expected The expected bytes values.
   * @return {@code this} assertion object.
   * @throws AssertionError If the column are not containing the bytes in parameter.
   */
  public static <A extends AbstractAssert<?>> A containsValues(A assertion, WritableAssertionInfo info,
                                                       List<Value> valuesList, byte[]... expected) {
    AssertionsOnColumnType.isBytes(assertion, info, valuesList, true);
    AssertionsOnNumberOfRows.hasNumberOfRows(assertion, info, valuesList.size(), expected.length);
    List<Value> list = new ArrayList<>(valuesList);
    int index = 0;
    for (byte[] val : expected) {
      boolean found = false;
      List<Value> newList = new ArrayList<>();
      for (Value obj : list) {
        if (found || !Values.areEqual(obj, val)) {
          newList.add(obj);
        }
        else {
          found = true;
        }
      }
      if (!found) {
        List<Object> listForError = new ArrayList<>();
        for (Value obj : valuesList) {
          listForError.add(Values.getRepresentationFromValueInFrontOfExpected(obj, byte[].class));
        }
        throw failures.failure(info, shouldContainsValue(index));
      }
      list = newList;
      index++;
    }
    return assertion;
  }

  /**
   * Verifies that the column contains texts.
   *
   * @param <A>        The type of the assertion which call this method.
   * @param assertion  The assertion which call this method.
   * @param info       Writable information about an assertion.
   * @param valuesList The list of values.
   * @param expected The expected text values.
   * @return {@code this} assertion object.
   * @throws AssertionError If the column are not containing the texts in parameter.
   */
  public static <A extends AbstractAssert<?>> A containsValues(A assertion, WritableAssertionInfo info,
                                                       List<Value> valuesList, String... expected) {
    AssertionsOnColumnType.isOfAnyTypeIn(assertion, info, valuesList, ValueType.TEXT, ValueType.NUMBER, ValueType.DATE,
                                         ValueType.TIME, ValueType.DATE_TIME, ValueType.UUID, ValueType.NOT_IDENTIFIED);
    AssertionsOnNumberOfRows.hasNumberOfRows(assertion, info, valuesList.size(), expected.length);
    List<Value> list = new ArrayList<>(valuesList);
    int index = 0;
    for (String val : expected) {
      boolean found = false;
      List<Value> newList = new ArrayList<>();
      for (Value obj : list) {
        if (found || !Values.areEqual(obj, val)) {
          newList.add(obj);
        }
        else {
          found = true;
        }
      }
      if (!found) {
        List<Object> listForError = new ArrayList<>();
        for (Value obj : valuesList) {
          listForError.add(Values.getRepresentationFromValueInFrontOfExpected(obj, String.class));
        }
        throw failures.failure(info, shouldContainsValue(listForError, expected, val, index));
      }
      list = newList;
      index++;
    }
    return assertion;
  }

  /**
   * Verifies that the column contains characters.
   *
   * @param <A>        The type of the assertion which call this method.
   * @param assertion  The assertion which call this method.
   * @param info       Writable information about an assertion.
   * @param valuesList The list of values.
   * @param expected The expected character values.
   * @return {@code this} assertion object.
   * @throws AssertionError If the column are not containing the characters in parameter.
   * @since 1.2.0
   */
  public static <A extends AbstractAssert<?>> A containsValues(A assertion, WritableAssertionInfo info,
                                                       List<Value> valuesList, Character... expected) {
    AssertionsOnColumnType.isText(assertion, info, valuesList, true);
    AssertionsOnNumberOfRows.hasNumberOfRows(assertion, info, valuesList.size(), expected.length);
    List<Value> list = new ArrayList<>(valuesList);
    int index = 0;
    for (Character val : expected) {
      boolean found = false;
      List<Value> newList = new ArrayList<>();
      for (Value obj : list) {
        if (found || !Values.areEqual(obj, val)) {
          newList.add(obj);
        }
        else {
          found = true;
        }
      }
      if (!found) {
        List<Object> listForError = new ArrayList<>();
        for (Value obj : valuesList) {
          listForError.add(Values.getRepresentationFromValueInFrontOfExpected(obj, Character.class));
        }
        throw failures.failure(info, shouldContainsValue(listForError, expected, val, index));
      }
      list = newList;
      index++;
    }
    return assertion;
  }

  /**
   * Verifies that the column contains UUIDs.
   *
   * @param <A>        The type of the assertion which call this method.
   * @param assertion  The assertion which call this method.
   * @param info       Writable information about an assertion.
   * @param valuesList The list of values.
   * @param expected The expected UUIDs values.
   * @return {@code this} assertion object.
   * @throws AssertionError If the column are not containing the UUIDs in parameter.
   */
  public static <A extends AbstractAssert<?>> A containsValues(A assertion, WritableAssertionInfo info,
                                                       List<Value> valuesList, UUID... expected) {
    AssertionsOnColumnType.isOfAnyTypeIn(assertion, info, valuesList, ValueType.UUID, ValueType.NOT_IDENTIFIED);
    AssertionsOnNumberOfRows.hasNumberOfRows(assertion, info, valuesList.size(), expected.length);
    List<Value> list = new ArrayList<>(valuesList);
    int index = 0;
    for (UUID val : expected) {
      boolean found = false;
      List<Value> newList = new ArrayList<>();
      for (Value obj : list) {
        if (found || !Values.areEqual(obj, val)) {
          newList.add(obj);
        }
        else {
          found = true;
        }
      }
      if (!found) {
        List<Object> listForError = new ArrayList<>();
        for (Value obj : valuesList) {
          listForError.add(Values.getRepresentationFromValueInFrontOfExpected(obj, UUID.class));
        }
        throw failures.failure(info, shouldContainsValue(listForError, expected, val, index));
      }
      list = newList;
      index++;
    }
    return assertion;
  }

  /**
   * Verifies that the column contains date values.
   *
   * @param <A>        The type of the assertion which call this method.
   * @param assertion  The assertion which call this method.
   * @param info       Writable information about an assertion.
   * @param valuesList The list of values.
   * @param expected The expected date values.
   * @return {@code this} assertion object.
   * @throws AssertionError If the column are not containing the date values in parameter.
   */
  public static <A extends AbstractAssert<?>> A containsValues(A assertion, WritableAssertionInfo info,
                                                       List<Value> valuesList, DateValue... expected) {
    AssertionsOnColumnType
            .isOfAnyTypeIn(assertion, info, valuesList, ValueType.DATE, ValueType.DATE_TIME, ValueType.NOT_IDENTIFIED);
    AssertionsOnNumberOfRows.hasNumberOfRows(assertion, info, valuesList.size(), expected.length);
    List<Value> list = new ArrayList<>(valuesList);
    int index = 0;
    for (DateValue val : expected) {
      boolean found = false;
      List<Value> newList = new ArrayList<>();
      for (Value obj : list) {
        if (found || !Values.areEqual(obj, val)) {
          newList.add(obj);
        }
        else {
          found = true;
        }
      }
      if (!found) {
        List<Object> listForError = new ArrayList<>();
        for (Value obj : valuesList) {
          listForError.add(Values.getRepresentationFromValueInFrontOfExpected(obj, DateValue.class));
        }
        throw failures.failure(info, shouldContainsValue(listForError, expected, val, index));
      }
      list = newList;
      index++;
    }
    return assertion;
  }

  /**
   * Verifies that the column contains time values.
   *
   * @param <A>        The type of the assertion which call this method.
   * @param assertion  The assertion which call this method.
   * @param info       Writable information about an assertion.
   * @param valuesList The list of values.
   * @param expected The expected time values.
   * @return {@code this} assertion object.
   * @throws AssertionError If the column are not containing the time values in parameter.
   */
  public static <A extends AbstractAssert<?>> A containsValues(A assertion, WritableAssertionInfo info,
                                                       List<Value> valuesList, TimeValue... expected) {
    AssertionsOnColumnType.isOfAnyTypeIn(assertion, info, valuesList, ValueType.TIME, ValueType.NOT_IDENTIFIED);
    AssertionsOnNumberOfRows.hasNumberOfRows(assertion, info, valuesList.size(), expected.length);
    List<Value> list = new ArrayList<>(valuesList);
    int index = 0;
    for (TimeValue val : expected) {
      boolean found = false;
      List<Value> newList = new ArrayList<>();
      for (Value obj : list) {
        if (found || !Values.areEqual(obj, val)) {
          newList.add(obj);
        }
        else {
          found = true;
        }
      }
      if (!found) {
        List<Object> listForError = new ArrayList<>();
        for (Value obj : valuesList) {
          listForError.add(Values.getRepresentationFromValueInFrontOfExpected(obj, TimeValue.class));
        }
        throw failures.failure(info, shouldContainsValue(listForError, expected, val, index));
      }
      list = newList;
      index++;
    }
    return assertion;
  }

  /**
   * Verifies that the column contains date/time values.
   *
   * @param <A>        The type of the assertion which call this method.
   * @param assertion  The assertion which call this method.
   * @param info       Writable information about an assertion.
   * @param valuesList The list of values.
   * @param expected The expected date/time values.
   * @return {@code this} assertion object.
   * @throws AssertionError If the column are not containing the date/time values in parameter.
   */
  public static <A extends AbstractAssert<?>> A containsValues(A assertion, WritableAssertionInfo info,
                                                       List<Value> valuesList, DateTimeValue... expected) {
    AssertionsOnColumnType.isOfAnyTypeIn(assertion, info, valuesList, ValueType.DATE, ValueType.DATE_TIME,
                                         ValueType.NOT_IDENTIFIED);
    AssertionsOnNumberOfRows.hasNumberOfRows(assertion, info, valuesList.size(), expected.length);
    List<Value> list = new ArrayList<>(valuesList);
    int index = 0;
    for (DateTimeValue val : expected) {
      boolean found = false;
      List<Value> newList = new ArrayList<>();
      for (Value obj : list) {
        if (found || !Values.areEqual(obj, val)) {
          newList.add(obj);
        }
        else {
          found = true;
        }
      }
      if (!found) {
        List<Object> listForError = new ArrayList<>();
        for (Value obj : valuesList) {
          listForError.add(Values.getRepresentationFromValueInFrontOfExpected(obj, DateTimeValue.class));
        }
        throw failures.failure(info, shouldContainsValue(listForError, expected, val, index));
      }
      list = newList;
      index++;
    }
    return assertion;
  }
}
