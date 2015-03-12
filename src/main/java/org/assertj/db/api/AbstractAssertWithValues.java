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
import org.assertj.db.api.origin.OriginWithColumnsAndRowsFromChange;
import org.assertj.db.type.DateTimeValue;
import org.assertj.db.type.DateValue;
import org.assertj.db.type.TimeValue;
import org.assertj.db.type.ValueType;
import org.assertj.db.util.AssertionsOnValue;

/**
 * Abstract class that represents a assert with an origin assert and which is the origin assert of another assert and have value.
 *
 * @param <E> The "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/anMa4g"
 *            target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *            for more details.
 * @param <O> The class of the assert of origin
 * @author Régis Pouiller
 */
public abstract class AbstractAssertWithValues <E extends AbstractAssertWithValues<E, O>, O extends OriginWithColumnsAndRowsFromChange>
        extends AbstractAssertWithOriginWithColumnsAndRowsFromChange<E, O> implements ValueAssert, OriginWithColumnsAndRowsFromChange,
        AssertOnValueComparison<E>,
        AssertOnValueType<E>, AssertOnValueNullity<E>, AssertOnValueEquality<E>, AssertOnValueNonEquality<E>, AssertOnValueChronology<E> {

  /**
   * The actual value on which the assertion is.
   */
  private final Object value;

  /**
   * Constructor.
   *
   * @param selfType     Class of this assert : a sub-class of {@code AbstractAssertWithValues}.
   * @param originAssert The assert of origin.
   * @param value The value on which are the assertions.
   */
  AbstractAssertWithValues(Class<E> selfType, O originAssert, Object value) {
    super(selfType, originAssert);
    this.value = value;
  }

  /** {@inheritDoc} */
  @Override
  public E isOfType(ValueType expected) {
    return AssertionsOnValue.isOfType(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public E isOfAnyOfTypes(ValueType... expected) {
    return AssertionsOnValue.isOfAnyOfTypes(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public E isNumber() {
    return AssertionsOnValue.isNumber(myself, info, value);
  }

  /** {@inheritDoc} */
  @Override
  public E isBoolean() {
    return AssertionsOnValue.isBoolean(myself, info, value);
  }

  /** {@inheritDoc} */
  @Override
  public E isDate() {
    return AssertionsOnValue.isDate(myself, info, value);
  }

  /** {@inheritDoc} */
  @Override
  public E isTime() {
    return AssertionsOnValue.isTime(myself, info, value);
  }

  /** {@inheritDoc} */
  @Override
  public E isDateTime() {
    return AssertionsOnValue.isDateTime(myself, info, value);
  }

  /** {@inheritDoc} */
  @Override
  public E isBytes() {
    return AssertionsOnValue.isBytes(myself, info, value);
  }

  /** {@inheritDoc} */
  @Override
  public E isText() {
    return AssertionsOnValue.isText(myself, info, value);
  }

  /** {@inheritDoc} */
  @Override
  public E isNull() {
    return AssertionsOnValue.isNull(myself, info, value);
  }

  /** {@inheritDoc} */
  @Override
  public E isNotNull() {
    return AssertionsOnValue.isNotNull(myself, info, value);
  }

  /** {@inheritDoc} */
  @Override
  public E isEqualTo(Boolean expected) {
    return AssertionsOnValue.isEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public E isTrue() {
    return AssertionsOnValue.isTrue(myself, info, value);
  }

  /** {@inheritDoc} */
  @Override
  public E isFalse() {
    return AssertionsOnValue.isFalse(myself, info, value);
  }

  /** {@inheritDoc} */
  @Override
  public E isEqualTo(Number expected) {
    return AssertionsOnValue.isEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public E isEqualTo(byte[] expected) {
    return AssertionsOnValue.isEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public E isEqualTo(String expected) {
    return AssertionsOnValue.isEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public E isEqualTo(DateValue expected) {
    return AssertionsOnValue.isEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public E isEqualTo(TimeValue expected) {
    return AssertionsOnValue.isEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public E isEqualTo(DateTimeValue expected) {
    return AssertionsOnValue.isEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public E isNotEqualTo(Boolean expected) {
    return AssertionsOnValue.isNotEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public E isNotEqualTo(byte[] expected) {
    return AssertionsOnValue.isNotEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public E isNotEqualTo(DateTimeValue expected) {
    return AssertionsOnValue.isNotEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public E isNotEqualTo(DateValue expected) {
    return AssertionsOnValue.isNotEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public E isNotEqualTo(Number expected) {
    return AssertionsOnValue.isNotEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public E isNotEqualTo(String expected) {
    return AssertionsOnValue.isNotEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public E isNotEqualTo(TimeValue expected) {
    return AssertionsOnValue.isNotEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public E isBefore(DateValue date) {
    return AssertionsOnValue.isBefore(myself, info, value, date);
  }

  /** {@inheritDoc} */
  @Override
  public E isBefore(TimeValue time) {
    return AssertionsOnValue.isBefore(myself, info, value, time);
  }

  /** {@inheritDoc} */
  @Override
  public E isBefore(DateTimeValue dateTime) {
    return AssertionsOnValue.isBefore(myself, info, value, dateTime);
  }

  /** {@inheritDoc} */
  @Override
  public E isBefore(String expected) {
    return AssertionsOnValue.isBefore(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public E isBeforeOrEqualTo(DateValue date) {
    return AssertionsOnValue.isBeforeOrEqualTo(myself, info, value, date);
  }

  /** {@inheritDoc} */
  @Override
  public E isBeforeOrEqualTo(TimeValue time) {
    return AssertionsOnValue.isBeforeOrEqualTo(myself, info, value, time);
  }

  /** {@inheritDoc} */
  @Override
  public E isBeforeOrEqualTo(DateTimeValue dateTime) {
    return AssertionsOnValue.isBeforeOrEqualTo(myself, info, value, dateTime);
  }

  /** {@inheritDoc} */
  @Override
  public E isBeforeOrEqualTo(String expected) {
    return AssertionsOnValue.isBeforeOrEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public E isAfter(DateValue date) {
    return AssertionsOnValue.isAfter(myself, info, value, date);
  }

  /** {@inheritDoc} */
  @Override
  public E isAfter(TimeValue time) {
    return AssertionsOnValue.isAfter(myself, info, value, time);
  }

  /** {@inheritDoc} */
  @Override
  public E isAfter(DateTimeValue dateTime) {
    return AssertionsOnValue.isAfter(myself, info, value, dateTime);
  }

  /** {@inheritDoc} */
  @Override
  public E isAfter(String expected) {
    return AssertionsOnValue.isAfter(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public E isAfterOrEqualTo(DateValue date) {
    return AssertionsOnValue.isAfterOrEqualTo(myself, info, value, date);
  }

  /** {@inheritDoc} */
  @Override
  public E isAfterOrEqualTo(TimeValue time) {
    return AssertionsOnValue.isAfterOrEqualTo(myself, info, value, time);
  }

  /** {@inheritDoc} */
  @Override
  public E isAfterOrEqualTo(DateTimeValue dateTime) {
    return AssertionsOnValue.isAfterOrEqualTo(myself, info, value, dateTime);
  }

  /** {@inheritDoc} */
  @Override
  public E isAfterOrEqualTo(String expected) {
    return AssertionsOnValue.isAfterOrEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public E isZero() {
    return AssertionsOnValue.isZero(myself, info, value);
  }

  /** {@inheritDoc} */
  @Override
  public E isNotZero() {
    return AssertionsOnValue.isNotZero(myself, info, value);
  }

  /** {@inheritDoc} */
  @Override
  public E isGreaterThan(Number expected) {
    return AssertionsOnValue.isGreaterThan(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public E isLessThan(Number expected) {
    return AssertionsOnValue.isLessThan(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public E isGreaterThanOrEqualTo(Number expected) {
    return AssertionsOnValue.isGreaterThanOrEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public E isLessThanOrEqualTo(Number expected) {
    return AssertionsOnValue.isLessThanOrEqualTo(myself, info, value, expected);
  }
}
