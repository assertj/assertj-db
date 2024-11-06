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
package org.assertj.db.api;

import org.assertj.db.api.assertions.*;
import org.assertj.db.api.assertions.impl.*;
import org.assertj.db.navigation.Position;
import org.assertj.db.navigation.element.ColumnElement;
import org.assertj.db.type.*;

import java.util.List;
import java.util.UUID;

import static org.assertj.db.util.Descriptions.getColumnValueDescription;

/**
 * Base class for all {@link Column}s assertions.
 *
 * @author Régis Pouiller
 * @author Otoniel Isidoro
 * 
 * @param <D> The class of the actual value (an sub-class of {@link AbstractDbData}).
 * @param <A> The class of the original assertion (an sub-class of {@link AbstractDbAssert}).
 * @param <C> The class of this assertion (an sub-class of {@link AbstractColumnAssert}).
 * @param <CV> The class of this assertion on the value (an sub-class of {@link AbstractColumnValueAssert}).
 * @param <R> The class of the equivalent row assertion (an sub-class of {@link AbstractRowAssert}).
 * @param <RV> The class of the equivalent row assertion on the value (an sub-class of {@link AbstractRowValueAssert}).
 */
public abstract class AbstractColumnAssert<D extends AbstractDbData<D>, A extends AbstractDbAssert<D, A, C, CV, R, RV>, C extends AbstractColumnAssert<D, A, C, CV, R, RV>, CV extends AbstractColumnValueAssert<D, A, C, CV, R, RV>, R extends AbstractRowAssert<D, A, C, CV, R, RV>, RV extends AbstractRowValueAssert<D, A, C, CV, R, RV>>
        extends AbstractSubAssert<D, A, C, CV, C, CV, R, RV>
        implements ColumnElement,
                   AssertOnColumnClass<C>,
                   AssertOnColumnEquality<C>,
                   AssertOnColumnContent<C>,
                   AssertOnNumberOfRows<C>,
                   AssertOnColumnName<C>,
                   AssertOnColumnType<C>,
                   AssertOnColumnNullity<C> {

  /**
   * Position of navigation to value.
   */
  protected final Position<C, CV, Value> valuePosition;

  /**
   * Column on which do the assertion.
   */
  protected final Column column;

  /**
   * Constructor.
   * 
   * @param originalDbAssert The original assert. That could be a {@link RequestAssert} or a {@link TableAssert}.
   * @param selfType Type of this assertion class : a sub-class of {@code AbstractColumnAssert}.
   * @param valueType Class of the assert on the value : a sub-class of {@code AbstractColumnValueAssert}.
   * @param column The column.
   */
  AbstractColumnAssert(A originalDbAssert, Class<C> selfType, Class<CV> valueType, Column column) {
    super(originalDbAssert, selfType);
    this.column = column;
    valuePosition = new Position<C, CV, Value>(selfType.cast(this), valueType) {
      @Override protected String getDescription(int index) {
        return getValueDescription(index);
      }
    };
  }

  /** {@inheritDoc} */
  @Override
  protected String getValueDescription(int index) {
    return getColumnValueDescription(info, index);
  }

  /** {@inheritDoc} */
  @Override
  protected Position<C, CV, Value> getValuePosition() {
    return valuePosition;
  }

  /** {@inheritDoc} */
  @Override
  protected List<Value> getValuesList() {
    return column.getValuesList();
  }

  /** {@inheritDoc} */
  @Override
  public C isEmpty() {
    return hasNumberOfRows(0);
  }

  /** {@inheritDoc} */
  @Override
  public C hasNumberOfRows(int expected) {
    return AssertionsOnNumberOfRows.hasNumberOfRows(myself, info, column.getValuesList().size(), expected);
  }

  /** {@inheritDoc} */
  @Override
  public C hasNumberOfRowsGreaterThan(int expected) {
    return AssertionsOnNumberOfRows.hasNumberOfRowsGreaterThan(myself, info, column.getValuesList().size(), expected);
  }

  /** {@inheritDoc} */
  @Override
  public C hasNumberOfRowsLessThan(int expected) {
    return AssertionsOnNumberOfRows.hasNumberOfRowsLessThan(myself, info, column.getValuesList().size(), expected);
  }

  /** {@inheritDoc} */
  @Override
  public C hasNumberOfRowsGreaterThanOrEqualTo(int expected) {
    return AssertionsOnNumberOfRows.hasNumberOfRowsGreaterThanOrEqualTo(myself, info, column.getValuesList().size(), expected);
  }

  /** {@inheritDoc} */
  @Override
  public C hasNumberOfRowsLessThanOrEqualTo(int expected) {
    return AssertionsOnNumberOfRows.hasNumberOfRowsLessThanOrEqualTo(myself, info, column.getValuesList().size(), expected);
  }

  /** {@inheritDoc} */
  @Override
  public C isOfClass(Class<?> expected, boolean lenient) {
    return AssertionsOnColumnClass.isOfClass(myself, info, getValuesList(), expected, lenient);
  }

  /** {@inheritDoc} */
  @Override
  public C isOfType(ValueType expected, boolean lenient) {
    return AssertionsOnColumnType.isOfType(myself, info, getValuesList(), expected, lenient);
  }

  /** {@inheritDoc} */
  @Override
  public C isOfAnyTypeIn(ValueType... expected) {
    return AssertionsOnColumnType.isOfAnyTypeIn(myself, info, getValuesList(), expected);
  }

  /** {@inheritDoc} */
  @Override
  public C isNumber(boolean lenient) {
    return AssertionsOnColumnType.isNumber(myself, info, getValuesList(), lenient);
  }

  /** {@inheritDoc} */
  @Override
  public C isBoolean(boolean lenient) {
    return AssertionsOnColumnType.isBoolean(myself, info, getValuesList(), lenient);
  }

  /** {@inheritDoc} */
  @Override
  public C isDate(boolean lenient) {
    return AssertionsOnColumnType.isDate(myself, info, getValuesList(), lenient);
  }

  /** {@inheritDoc} */
  @Override
  public C isTime(boolean lenient) {
    return AssertionsOnColumnType.isTime(myself, info, getValuesList(), lenient);
  }

  /** {@inheritDoc} */
  @Override
  public C isDateTime(boolean lenient) {
    return AssertionsOnColumnType.isDateTime(myself, info, getValuesList(), lenient);
  }

  /** {@inheritDoc} */
  @Override
  public C isBytes(boolean lenient) {
    return AssertionsOnColumnType.isBytes(myself, info, getValuesList(), lenient);
  }

  /** {@inheritDoc} */
  @Override
  public C isText(boolean lenient) {
    return AssertionsOnColumnType.isText(myself, info, getValuesList(), lenient);
  }

  /** {@inheritDoc} */
  @Override
  public C isUUID(boolean lenient) {
    return AssertionsOnColumnType.isUUID(myself, info, getValuesList(), lenient);
  }

  /** {@inheritDoc} */
  @Override
  public C hasOnlyNullValues() {
    return AssertionsOnValuesNullity.hasOnlyNullValues(myself, info, getValuesList());
  }

  /** {@inheritDoc} */
  @Override
  public C hasOnlyNotNullValues() {
    return AssertionsOnValuesNullity.hasOnlyNotNullValues(myself, info, getValuesList());
  }

  /** {@inheritDoc} */
  @Override
  public C hasValues(Object... expected) {
    return AssertionsOnColumnEquality.hasValues(myself, info, getValuesList(), expected);
  }

  /** {@inheritDoc} */
  @Override
  public C hasValues(Boolean... expected) {
    return AssertionsOnColumnEquality.hasValues(myself, info, getValuesList(), expected);
  }

  /** {@inheritDoc} */
  @Override
  public C hasValues(Number... expected) {
    return AssertionsOnColumnEquality.hasValues(myself, info, getValuesList(), expected);
  }

  /** {@inheritDoc} */
  @Override
  public C hasValues(byte[]... expected) {
    return AssertionsOnColumnEquality.hasValues(myself, info, getValuesList(), expected);
  }

  /** {@inheritDoc} */
  @Override
  public C hasValues(String... expected) {
    return AssertionsOnColumnEquality.hasValues(myself, info, getValuesList(), expected);
  }

  /** {@inheritDoc} */
  @Override
  public C hasValues(Character... expected) {
    return AssertionsOnColumnEquality.hasValues(myself, info, getValuesList(), expected);
  }

  /** {@inheritDoc} */
  @Override
  public C hasValues(UUID... expected) {
    return AssertionsOnColumnEquality.hasValues(myself, info, getValuesList(), expected);
  }

  /** {@inheritDoc} */
  @Override
  public C hasValues(DateValue... expected) {
    return AssertionsOnColumnEquality.hasValues(myself, info, getValuesList(), expected);
  }

  /** {@inheritDoc} */
  @Override
  public C hasValues(TimeValue... expected) {
    return AssertionsOnColumnEquality.hasValues(myself, info, getValuesList(), expected);
  }

  /** {@inheritDoc} */
  @Override
  public C hasValues(DateTimeValue... expected) {
    return AssertionsOnColumnEquality.hasValues(myself, info, getValuesList(), expected);
  }

  /** {@inheritDoc} */
  @Override
  public C containsValues(Object... expected) {
    return AssertionsOnColumnContent.containsValues(myself, info, getValuesList(), expected);
  }

  /** {@inheritDoc} */
  @Override
  public C containsValues(Boolean... expected) {
    return AssertionsOnColumnContent.containsValues(myself, info, getValuesList(), expected);
  }

  /** {@inheritDoc} */
  @Override
  public C containsValues(Number... expected) {
    return AssertionsOnColumnContent.containsValues(myself, info, getValuesList(), expected);
  }

  /** {@inheritDoc} */
  @Override
  public C containsValues(byte[]... expected) {
    return AssertionsOnColumnContent.containsValues(myself, info, getValuesList(), expected);
  }

  /** {@inheritDoc} */
  @Override
  public C containsValues(String... expected) {
    return AssertionsOnColumnContent.containsValues(myself, info, getValuesList(), expected);
  }

  /** {@inheritDoc} */
  @Override
  public C containsValues(Character... expected) {
    return AssertionsOnColumnContent.containsValues(myself, info, getValuesList(), expected);
  }

  /** {@inheritDoc} */
  @Override
  public C containsValues(UUID... expected) {
    return AssertionsOnColumnContent.containsValues(myself, info, getValuesList(), expected);
  }

  /** {@inheritDoc} */
  @Override
  public C containsValues(DateValue... expected) {
    return AssertionsOnColumnContent.containsValues(myself, info, getValuesList(), expected);
  }

  /** {@inheritDoc} */
  @Override
  public C containsValues(TimeValue... expected) {
    return AssertionsOnColumnContent.containsValues(myself, info, getValuesList(), expected);
  }

  /** {@inheritDoc} */
  @Override
  public C containsValues(DateTimeValue... expected) {
    return AssertionsOnColumnContent.containsValues(myself, info, getValuesList(), expected);
  }

  /** {@inheritDoc} */
  @Override
  public C hasColumnName(String columnName) {
    String name = column.getName();
    return AssertionsOnColumnName.hasColumnName(myself, info, name, columnName, column.getColumnLetterCase());
  }
}
