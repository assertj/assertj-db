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
import org.assertj.db.api.navigation.ColumnAssert;
import org.assertj.db.type.*;

import java.lang.reflect.Constructor;
import java.util.List;

/**
 * Base class for all {@link Column}s assertions.
 *
 * @author Régis Pouiller
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
        implements ColumnAssert,
                   AssertOnColumnEquality<C>,
                   AssertOnNumberOfRows<C>,
                   AssertOnColumnName<C>,
                   AssertOnColumnType<C>,
                   AssertOnColumnNullity<C> {

  /**
   * Column on which do the assertion.
   */
  private Column column;

  /**
   * Constructor.
   * 
   * @param originalDbAssert The original assert. That could be a {@link RequestAssert} or a {@link TableAssert}.
   * @param selfType Type of this assertion class : a sub-class of {@code AbstractColumnAssert}.
   * @param valueType Class of the assert on the value : a sub-class of {@code AbstractColumnValueAssert}.
   */
  AbstractColumnAssert(A originalDbAssert, Class<C> selfType, Class<CV> valueType, Column column) {
    super(originalDbAssert, selfType, valueType);
    this.column = column;
  }

  /** {@inheritDoc} */
  @Override
  protected CV getValueAssertInstance(Class<CV> valueAssertType, int index, Object value) throws Exception {
    Constructor<CV> constructor = valueAssertType.getDeclaredConstructor(myself.getClass(), Object.class);
    CV instance = constructor.newInstance(this, value);
    return instance.as("Value at index " + index + " of " + info.descriptionText());
  }

  /** {@inheritDoc} */
  @Override
  protected List<Object> getValuesList() {
    return column.getValuesList();
  }

  /** {@inheritDoc} */
  @Override
  public C hasNumberOfRows(int expected) {
    return AssertionsOnNumberOfRows.hasNumberOfRows(myself, info, column.getValuesList().size(), expected);
  }

  /** {@inheritDoc} */
  @Override
  public C isOfType(ValueType expected, boolean lenient) {
    return AssertionsOnColumnType.isOfType(myself, info, getValuesList(), expected, lenient);
  }

  /** {@inheritDoc} */
  @Override
  public C isOfAnyOfTypes(ValueType... expected) {
    return AssertionsOnColumnType.isOfAnyOfTypes(myself, info, getValuesList(), expected);
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
  public C hasOnlyNullValues() {
    return AssertionsOnColumnNullity.hasOnlyNullValues(myself, info, getValuesList());
  }

  /** {@inheritDoc} */
  @Override
  public C hasOnlyNotNullValues() {
    return AssertionsOnColumnNullity.hasOnlyNotNullValues(myself, info, getValuesList());
  }

  /** {@inheritDoc} */
  @Override
  public C hasValuesEqualTo(Boolean... expected) {
    return AssertionsOnColumnEquality.hasValuesEqualTo(myself, info, getValuesList(), expected);
  }

  /** {@inheritDoc} */
  @Override
  public C hasValuesEqualTo(Number... expected) {
    return AssertionsOnColumnEquality.hasValuesEqualTo(myself, info, getValuesList(), expected);
  }

  /** {@inheritDoc} */
  @Override
  public C hasValuesEqualTo(byte[]... expected) {
    return AssertionsOnColumnEquality.hasValuesEqualTo(myself, info, getValuesList(), expected);
  }

  /** {@inheritDoc} */
  @Override
  public C hasValuesEqualTo(String... expected) {
    return AssertionsOnColumnEquality.hasValuesEqualTo(myself, info, getValuesList(), expected);
  }

  /** {@inheritDoc} */
  @Override
  public C hasValuesEqualTo(DateValue... expected) {
    return AssertionsOnColumnEquality.hasValuesEqualTo(myself, info, getValuesList(), expected);
  }

  /** {@inheritDoc} */
  @Override
  public C hasValuesEqualTo(TimeValue... expected) {
    return AssertionsOnColumnEquality.hasValuesEqualTo(myself, info, getValuesList(), expected);
  }

  /** {@inheritDoc} */
  @Override
  public C hasValuesEqualTo(DateTimeValue... expected) {
    return AssertionsOnColumnEquality.hasValuesEqualTo(myself, info, getValuesList(), expected);
  }

  /** {@inheritDoc} */
  @Override
  public C hasColumnName(String columnName) {
    String name = column.getName();
    return AssertionsOnColumnName.hasColumnName(myself, info, name, columnName);
  }
}
