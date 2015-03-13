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
import org.assertj.db.api.navigation.ValueAssert;
import org.assertj.db.api.navigation.WithValues;
import org.assertj.db.type.*;
import org.assertj.db.util.AssertionsOnValue;

/**
 * Assertion methods about the value.
 * 
 * @author Régis Pouiller
 * 
 * @param <D> The class of the actual value (an sub-class of {@link AbstractDbData}).
 * @param <A> The class of the original assert (an sub-class of {@link AbstractDbAssert}).
 * @param <S> The class of which contains assertion methods about {@link Column} or {@link Row} (an sub-class of
 *          {@link AbstractSubAssert}).
 * @param <V> The class of this assert (an sub-class of {@link AbstractValueAssert}).
 * @param <C> The class of this assert (an sub-class of {@link AbstractColumnAssert}).
 * @param <CV> The class of this assertion on the value (an sub-class of {@link AbstractColumnValueAssert}).
 * @param <R> The class of the equivalent row assert (an sub-class of {@link AbstractRowAssert}).
 * @param <RV> The class of the equivalent row assertion on the value (an sub-class of {@link AbstractRowValueAssert}).
 */
public abstract class AbstractValueAssert<D extends AbstractDbData<D>, A extends AbstractDbAssert<D, A, C, CV, R, RV>, S extends AbstractSubAssert<D, A, S, V, C, CV, R, RV>, V extends AbstractValueAssert<D, A, S, V, C, CV, R, RV>, C extends AbstractColumnAssert<D, A, C, CV, R, RV>, CV extends AbstractColumnValueAssert<D, A, C, CV, R, RV>, R extends AbstractRowAssert<D, A, C, CV, R, RV>, RV extends AbstractRowValueAssert<D, A, C, CV, R, RV>>
    extends AbstractAssertWithOriginWithColumnsAndRows<V, S, D, A, C, CV, R, RV> implements AssertOnValueType<V>,
        AssertOnValueNullity<V>, AssertOnValueEquality<V>, AssertOnValueNonEquality<V>, AssertOnValueChronology<V>, AssertOnValueComparison<V>,
        ValueAssert, WithValues<V> {

  /**
   * The actual value on which this assertion is.
   */
  private final Object value;

  /**
   * Constructor.
   * @param selfType Class of this assert (the value assert) : a sub-class of {@code AbstractValueAssert}.
   * @param originAssert The assert of origin.
   * @param actualValue The value to assert.
   */
  AbstractValueAssert(Class<V> selfType, S originAssert, Object actualValue) {
    super(selfType, originAssert);
    this.value = actualValue;
  }

  /** {@inheritDoc} */
  @Override
  public V value() {
    return returnToOrigin().value();
  }

  /** {@inheritDoc} */
  @Override
  public V value(int index) {
    return returnToOrigin().value(index);
  }

  /** {@inheritDoc} */
  @Override
  public V isOfType(ValueType expected) {
    return AssertionsOnValue.isOfType(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public V isOfAnyOfTypes(ValueType... expected) {
    return AssertionsOnValue.isOfAnyOfTypes(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public V isNumber() {
    return AssertionsOnValue.isNumber(myself, info, value);
  }

  /** {@inheritDoc} */
  @Override
  public V isBoolean() {
    return AssertionsOnValue.isBoolean(myself, info, value);
  }

  /** {@inheritDoc} */
  @Override
  public V isDate() {
    return AssertionsOnValue.isDate(myself, info, value);
  }

  /** {@inheritDoc} */
  @Override
  public V isTime() {
    return AssertionsOnValue.isTime(myself, info, value);
  }

  /** {@inheritDoc} */
  @Override
  public V isDateTime() {
    return AssertionsOnValue.isDateTime(myself, info, value);
  }

  /** {@inheritDoc} */
  @Override
  public V isBytes() {
    return AssertionsOnValue.isBytes(myself, info, value);
  }

  /** {@inheritDoc} */
  @Override
  public V isText() {
    return AssertionsOnValue.isText(myself, info, value);
  }

  /** {@inheritDoc} */
  @Override
  public V isNull() {
    return AssertionsOnValue.isNull(myself, info, value);
  }

  /** {@inheritDoc} */
  @Override
  public V isNotNull() {
    return AssertionsOnValue.isNotNull(myself, info, value);
  }

  /** {@inheritDoc} */
  @Override
  public V isEqualTo(Boolean expected) {
    return AssertionsOnValue.isEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public V isTrue() {
    return AssertionsOnValue.isTrue(myself, info, value);
  }

  /** {@inheritDoc} */
  @Override
  public V isFalse() {
    return AssertionsOnValue.isFalse(myself, info, value);
  }

  /** {@inheritDoc} */
  @Override
  public V isEqualTo(Number expected) {
    return AssertionsOnValue.isEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public V isEqualTo(byte[] expected) {
    return AssertionsOnValue.isEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public V isEqualTo(String expected) {
    return AssertionsOnValue.isEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public V isEqualTo(DateValue expected) {
    return AssertionsOnValue.isEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public V isEqualTo(TimeValue expected) {
    return AssertionsOnValue.isEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public V isEqualTo(DateTimeValue expected) {
    return AssertionsOnValue.isEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public V isNotEqualTo(Boolean expected) {
    return AssertionsOnValue.isNotEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public V isNotEqualTo(byte[] expected) {
    return AssertionsOnValue.isNotEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public V isNotEqualTo(DateTimeValue expected) {
    return AssertionsOnValue.isNotEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public V isNotEqualTo(DateValue expected) {
    return AssertionsOnValue.isNotEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public V isNotEqualTo(Number expected) {
    return AssertionsOnValue.isNotEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public V isNotEqualTo(String expected) {
    return AssertionsOnValue.isNotEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public V isNotEqualTo(TimeValue expected) {
    return AssertionsOnValue.isNotEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public V isBefore(DateValue date) {
    return AssertionsOnValue.isBefore(myself, info, value, date);
  }

  /** {@inheritDoc} */
  @Override
  public V isBefore(TimeValue time) {
    return AssertionsOnValue.isBefore(myself, info, value, time);
  }

  /** {@inheritDoc} */
  @Override
  public V isBefore(DateTimeValue dateTime) {
    return AssertionsOnValue.isBefore(myself, info, value, dateTime);
  }

  /** {@inheritDoc} */
  @Override
  public V isBefore(String expected) {
    return AssertionsOnValue.isBefore(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public V isBeforeOrEqualTo(DateValue date) {
    return AssertionsOnValue.isBeforeOrEqualTo(myself, info, value, date);
  }

  /** {@inheritDoc} */
  @Override
  public V isBeforeOrEqualTo(TimeValue time) {
    return AssertionsOnValue.isBeforeOrEqualTo(myself, info, value, time);
  }

  /** {@inheritDoc} */
  @Override
  public V isBeforeOrEqualTo(DateTimeValue dateTime) {
    return AssertionsOnValue.isBeforeOrEqualTo(myself, info, value, dateTime);
  }

  /** {@inheritDoc} */
  @Override
  public V isBeforeOrEqualTo(String expected) {
    return AssertionsOnValue.isBeforeOrEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public V isAfter(DateValue date) {
    return AssertionsOnValue.isAfter(myself, info, value, date);
  }

  /** {@inheritDoc} */
  @Override
  public V isAfter(TimeValue time) {
    return AssertionsOnValue.isAfter(myself, info, value, time);
  }

  /** {@inheritDoc} */
  @Override
  public V isAfter(DateTimeValue dateTime) {
    return AssertionsOnValue.isAfter(myself, info, value, dateTime);
  }

  /** {@inheritDoc} */
  @Override
  public V isAfter(String expected) {
    return AssertionsOnValue.isAfter(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public V isAfterOrEqualTo(DateValue date) {
    return AssertionsOnValue.isAfterOrEqualTo(myself, info, value, date);
  }

  /** {@inheritDoc} */
  @Override
  public V isAfterOrEqualTo(TimeValue time) {
    return AssertionsOnValue.isAfterOrEqualTo(myself, info, value, time);
  }

  /** {@inheritDoc} */
  @Override
  public V isAfterOrEqualTo(DateTimeValue dateTime) {
    return AssertionsOnValue.isAfterOrEqualTo(myself, info, value, dateTime);
  }

  /** {@inheritDoc} */
  @Override
  public V isAfterOrEqualTo(String expected) {
    return AssertionsOnValue.isAfterOrEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public V isZero() {
    return AssertionsOnValue.isZero(myself, info, value);
  }

  /** {@inheritDoc} */
  @Override
  public V isNotZero() {
    return AssertionsOnValue.isNotZero(myself, info, value);
  }

  /** {@inheritDoc} */
  @Override
  public V isGreaterThan(Number expected) {
    return AssertionsOnValue.isGreaterThan(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public V isLessThan(Number expected) {
    return AssertionsOnValue.isLessThan(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public V isGreaterThanOrEqualTo(Number expected) {
    return AssertionsOnValue.isGreaterThanOrEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public V isLessThanOrEqualTo(Number expected) {
    return AssertionsOnValue.isLessThanOrEqualTo(myself, info, value, expected);
  }
}
