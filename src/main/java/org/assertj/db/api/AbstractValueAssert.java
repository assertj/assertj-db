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

import org.assertj.db.api.assertions.*;
import org.assertj.db.api.assertions.impl.*;
import org.assertj.db.api.navigation.ToValue;
import org.assertj.db.api.navigation.ValueAssert;
import org.assertj.db.type.*;

/**
 * Base class for all values assertions.
 *
 * @author Régis Pouiller
 * 
 * @param <D> The class of the actual value (an sub-class of {@link AbstractDbData}).
 * @param <A> The class of the original assertion (an sub-class of {@link AbstractDbAssert}).
 * @param <S> The class of which contains assertion methods about {@link Column} or {@link Row} (an sub-class of
 *          {@link AbstractSubAssert}).
 * @param <V> The class of this assertion (an sub-class of {@link AbstractValueAssert}).
 * @param <C> The class of this assertion (an sub-class of {@link AbstractColumnAssert}).
 * @param <CV> The class of this assertion on the value (an sub-class of {@link AbstractColumnValueAssert}).
 * @param <R> The class of the equivalent row assertion (an sub-class of {@link AbstractRowAssert}).
 * @param <RV> The class of the equivalent row assertion on the value (an sub-class of {@link AbstractRowValueAssert}).
 */
public abstract class AbstractValueAssert<D extends AbstractDbData<D>, A extends AbstractDbAssert<D, A, C, CV, R, RV>, S extends AbstractSubAssert<D, A, S, V, C, CV, R, RV>, V extends AbstractValueAssert<D, A, S, V, C, CV, R, RV>, C extends AbstractColumnAssert<D, A, C, CV, R, RV>, CV extends AbstractColumnValueAssert<D, A, C, CV, R, RV>, R extends AbstractRowAssert<D, A, C, CV, R, RV>, RV extends AbstractRowValueAssert<D, A, C, CV, R, RV>>
        extends AbstractAssertWithOriginWithColumnsAndRows<V, S, D, A, C, CV, R, RV>
        implements ValueAssert,
                   ToValue<V>,
                   AssertOnValueType<V>,
                   AssertOnValueNullity<V>,
                   AssertOnValueEquality<V>,
                   AssertOnValueNonEquality<V>,
                   AssertOnValueChronology<V>,
                   AssertOnValueComparison<V> {

  /**
   * The actual value on which this assertion is.
   */
  private final Object value;

  /**
   * Constructor.
   * @param selfType Type of this assertion class : a sub-class of {@code AbstractValueAssert}.
   * @param origin The assertion of {@link org.assertj.db.api.origin.Origin}.
   * @param value The value on which are the assertion methods.
   */
  AbstractValueAssert(Class<V> selfType, S origin, Object value) {
    super(selfType, origin);
    this.value = value;
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
    return AssertionsOnValueType.isOfType(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public V isOfAnyTypeIn(ValueType... expected) {
    return AssertionsOnValueType.isOfAnyTypeIn(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public V isNumber() {
    return AssertionsOnValueType.isNumber(myself, info, value);
  }

  /** {@inheritDoc} */
  @Override
  public V isBoolean() {
    return AssertionsOnValueType.isBoolean(myself, info, value);
  }

  /** {@inheritDoc} */
  @Override
  public V isDate() {
    return AssertionsOnValueType.isDate(myself, info, value);
  }

  /** {@inheritDoc} */
  @Override
  public V isTime() {
    return AssertionsOnValueType.isTime(myself, info, value);
  }

  /** {@inheritDoc} */
  @Override
  public V isDateTime() {
    return AssertionsOnValueType.isDateTime(myself, info, value);
  }

  /** {@inheritDoc} */
  @Override
  public V isBytes() {
    return AssertionsOnValueType.isBytes(myself, info, value);
  }

  /** {@inheritDoc} */
  @Override
  public V isText() {
    return AssertionsOnValueType.isText(myself, info, value);
  }

  /** {@inheritDoc} */
  @Override
  public V isNull() {
    return AssertionsOnValueNullity.isNull(myself, info, value);
  }

  /** {@inheritDoc} */
  @Override
  public V isNotNull() {
    return AssertionsOnValueNullity.isNotNull(myself, info, value);
  }

  /** {@inheritDoc} */
  @Override
  public V isEqualTo(Boolean expected) {
    return AssertionsOnValueEquality.isEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public V isTrue() {
    return AssertionsOnValueEquality.isTrue(myself, info, value);
  }

  /** {@inheritDoc} */
  @Override
  public V isFalse() {
    return AssertionsOnValueEquality.isFalse(myself, info, value);
  }

  /** {@inheritDoc} */
  @Override
  public V isEqualTo(Number expected) {
    return AssertionsOnValueEquality.isEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public V isEqualTo(byte[] expected) {
    return AssertionsOnValueEquality.isEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public V isEqualTo(String expected) {
    return AssertionsOnValueEquality.isEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public V isEqualTo(DateValue expected) {
    return AssertionsOnValueEquality.isEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public V isEqualTo(TimeValue expected) {
    return AssertionsOnValueEquality.isEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public V isEqualTo(DateTimeValue expected) {
    return AssertionsOnValueEquality.isEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public V isNotEqualTo(Boolean expected) {
    return AssertionsOnValueNonEquality.isNotEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public V isNotEqualTo(byte[] expected) {
    return AssertionsOnValueNonEquality.isNotEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public V isNotEqualTo(DateTimeValue expected) {
    return AssertionsOnValueNonEquality.isNotEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public V isNotEqualTo(DateValue expected) {
    return AssertionsOnValueNonEquality.isNotEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public V isNotEqualTo(Number expected) {
    return AssertionsOnValueNonEquality.isNotEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public V isNotEqualTo(String expected) {
    return AssertionsOnValueNonEquality.isNotEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public V isNotEqualTo(TimeValue expected) {
    return AssertionsOnValueNonEquality.isNotEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public V isBefore(DateValue date) {
    return AssertionsOnValueChronology.isBefore(myself, info, value, date);
  }

  /** {@inheritDoc} */
  @Override
  public V isBefore(TimeValue time) {
    return AssertionsOnValueChronology.isBefore(myself, info, value, time);
  }

  /** {@inheritDoc} */
  @Override
  public V isBefore(DateTimeValue dateTime) {
    return AssertionsOnValueChronology.isBefore(myself, info, value, dateTime);
  }

  /** {@inheritDoc} */
  @Override
  public V isBefore(String expected) {
    return AssertionsOnValueChronology.isBefore(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public V isBeforeOrEqualTo(DateValue date) {
    return AssertionsOnValueChronology.isBeforeOrEqualTo(myself, info, value, date);
  }

  /** {@inheritDoc} */
  @Override
  public V isBeforeOrEqualTo(TimeValue time) {
    return AssertionsOnValueChronology.isBeforeOrEqualTo(myself, info, value, time);
  }

  /** {@inheritDoc} */
  @Override
  public V isBeforeOrEqualTo(DateTimeValue dateTime) {
    return AssertionsOnValueChronology.isBeforeOrEqualTo(myself, info, value, dateTime);
  }

  /** {@inheritDoc} */
  @Override
  public V isBeforeOrEqualTo(String expected) {
    return AssertionsOnValueChronology.isBeforeOrEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public V isAfter(DateValue date) {
    return AssertionsOnValueChronology.isAfter(myself, info, value, date);
  }

  /** {@inheritDoc} */
  @Override
  public V isAfter(TimeValue time) {
    return AssertionsOnValueChronology.isAfter(myself, info, value, time);
  }

  /** {@inheritDoc} */
  @Override
  public V isAfter(DateTimeValue dateTime) {
    return AssertionsOnValueChronology.isAfter(myself, info, value, dateTime);
  }

  /** {@inheritDoc} */
  @Override
  public V isAfter(String expected) {
    return AssertionsOnValueChronology.isAfter(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public V isAfterOrEqualTo(DateValue date) {
    return AssertionsOnValueChronology.isAfterOrEqualTo(myself, info, value, date);
  }

  /** {@inheritDoc} */
  @Override
  public V isAfterOrEqualTo(TimeValue time) {
    return AssertionsOnValueChronology.isAfterOrEqualTo(myself, info, value, time);
  }

  /** {@inheritDoc} */
  @Override
  public V isAfterOrEqualTo(DateTimeValue dateTime) {
    return AssertionsOnValueChronology.isAfterOrEqualTo(myself, info, value, dateTime);
  }

  /** {@inheritDoc} */
  @Override
  public V isAfterOrEqualTo(String expected) {
    return AssertionsOnValueChronology.isAfterOrEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public V isZero() {
    return AssertionsOnValueEquality.isZero(myself, info, value);
  }

  /** {@inheritDoc} */
  @Override
  public V isNotZero() {
    return AssertionsOnValueNonEquality.isNotZero(myself, info, value);
  }

  /** {@inheritDoc} */
  @Override
  public V isGreaterThan(Number expected) {
    return AssertionsOnValueComparison.isGreaterThan(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public V isLessThan(Number expected) {
    return AssertionsOnValueComparison.isLessThan(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public V isGreaterThanOrEqualTo(Number expected) {
    return AssertionsOnValueComparison.isGreaterThanOrEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public V isLessThanOrEqualTo(Number expected) {
    return AssertionsOnValueComparison.isLessThanOrEqualTo(myself, info, value, expected);
  }
}
