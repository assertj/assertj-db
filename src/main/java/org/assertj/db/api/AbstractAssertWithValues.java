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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

import org.assertj.core.api.Condition;
import org.assertj.db.api.assertions.AssertOnValueChronology;
import org.assertj.db.api.assertions.AssertOnValueClass;
import org.assertj.db.api.assertions.AssertOnValueCloseness;
import org.assertj.db.api.assertions.AssertOnValueComparison;
import org.assertj.db.api.assertions.AssertOnValueCondition;
import org.assertj.db.api.assertions.AssertOnValueEquality;
import org.assertj.db.api.assertions.AssertOnValueInequality;
import org.assertj.db.api.assertions.AssertOnValueNullity;
import org.assertj.db.api.assertions.AssertOnValueType;
import org.assertj.db.api.assertions.impl.AssertionsOnValueChronology;
import org.assertj.db.api.assertions.impl.AssertionsOnValueClass;
import org.assertj.db.api.assertions.impl.AssertionsOnValueCloseness;
import org.assertj.db.api.assertions.impl.AssertionsOnValueComparison;
import org.assertj.db.api.assertions.impl.AssertionsOnValueCondition;
import org.assertj.db.api.assertions.impl.AssertionsOnValueEquality;
import org.assertj.db.api.assertions.impl.AssertionsOnValueInequality;
import org.assertj.db.api.assertions.impl.AssertionsOnValueNullity;
import org.assertj.db.api.assertions.impl.AssertionsOnValueType;
import org.assertj.db.navigation.element.ValueElement;
import org.assertj.db.navigation.origin.OriginWithColumnsAndRowsFromChange;
import org.assertj.db.type.DateTimeValue;
import org.assertj.db.type.DateValue;
import org.assertj.db.type.TimeValue;
import org.assertj.db.type.Value;
import org.assertj.db.type.ValueType;

/**
 * Base class for all values from a {@link org.assertj.db.type.Change} assertions.
 *
 * @param <E> The "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/1IZIRcY"
 *            target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *            for more details.
 * @param <O> The type of the assertion class of {@link org.assertj.db.navigation.origin.Origin}.
 * @author Régis Pouiller
 * @author Otoniel Isidoro
 * @author Julien Roy
 */
public abstract class AbstractAssertWithValues<E extends AbstractAssertWithValues<E, O>, O extends OriginWithColumnsAndRowsFromChange<ChangesAssert, ChangeAssert, ChangeColumnAssert, ChangeRowAssert>>
  extends AbstractAssertWithOriginWithColumnsAndRowsFromChange<E, O>
  implements ValueElement,
  AssertOnValueClass<E>,
  AssertOnValueType<E>,
  AssertOnValueNullity<E>,
  AssertOnValueEquality<E>,
  AssertOnValueInequality<E>,
  AssertOnValueComparison<E>,
  AssertOnValueChronology<E>,
  AssertOnValueCloseness<E>,
  AssertOnValueCondition<E> {

  /**
   * The actual value on which the assertion is.
   */
  protected final Value value;

  /**
   * Constructor.
   *
   * @param selfType Type of this assertion class : a sub-class of {@code AbstractAssertWithValues}.
   * @param origin   The assertion of {@link org.assertj.db.navigation.origin.Origin}.
   * @param value    The value on which are the assertion methods.
   */
  AbstractAssertWithValues(Class<E> selfType, O origin, Value value) {
    super(selfType, origin);
    this.value = value;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isOfClass(Class<?> expected) {
    return AssertionsOnValueClass.isOfClass(myself, info, value, expected);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isOfType(ValueType expected) {
    return AssertionsOnValueType.isOfType(myself, info, value, expected);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isOfAnyTypeIn(ValueType... expected) {
    return AssertionsOnValueType.isOfAnyTypeIn(myself, info, value, expected);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isNumber() {
    return AssertionsOnValueType.isNumber(myself, info, value);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isBoolean() {
    return AssertionsOnValueType.isBoolean(myself, info, value);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isDate() {
    return AssertionsOnValueType.isDate(myself, info, value);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isTime() {
    return AssertionsOnValueType.isTime(myself, info, value);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isDateTime() {
    return AssertionsOnValueType.isDateTime(myself, info, value);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isBytes() {
    return AssertionsOnValueType.isBytes(myself, info, value);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isText() {
    return AssertionsOnValueType.isText(myself, info, value);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isUUID() {
    return AssertionsOnValueType.isUUID(myself, info, value);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isNull() {
    return AssertionsOnValueNullity.isNull(myself, info, value);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isNotNull() {
    return AssertionsOnValueNullity.isNotNull(myself, info, value);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isEqualTo(Object expected) {
    return AssertionsOnValueEquality.isEqualTo(myself, info, value, expected);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isEqualTo(Boolean expected) {
    return AssertionsOnValueEquality.isEqualTo(myself, info, value, expected);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isTrue() {
    return AssertionsOnValueEquality.isTrue(myself, info, value);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isFalse() {
    return AssertionsOnValueEquality.isFalse(myself, info, value);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isEqualTo(Number expected) {
    return AssertionsOnValueEquality.isEqualTo(myself, info, value, expected);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isEqualTo(byte[] expected) {
    return AssertionsOnValueEquality.isEqualTo(myself, info, value, expected);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isEqualTo(String expected) {
    return AssertionsOnValueEquality.isEqualTo(myself, info, value, expected);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isEqualTo(Character expected) {
    return AssertionsOnValueEquality.isEqualTo(myself, info, value, expected);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isEqualTo(UUID expected) {
    return AssertionsOnValueEquality.isEqualTo(myself, info, value, expected);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isEqualTo(DateValue expected) {
    return AssertionsOnValueEquality.isEqualTo(myself, info, value, expected);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isEqualTo(TimeValue expected) {
    return AssertionsOnValueEquality.isEqualTo(myself, info, value, expected);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isEqualTo(DateTimeValue expected) {
    return AssertionsOnValueEquality.isEqualTo(myself, info, value, expected);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isEqualTo(LocalDate expected) {
    return isEqualTo(DateValue.from(expected));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isEqualTo(LocalTime expected) {
    return isEqualTo(TimeValue.from(expected));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isEqualTo(LocalDateTime expected) {
    return isEqualTo(DateTimeValue.from(expected));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isNotEqualTo(Object expected) {
    return AssertionsOnValueInequality.isNotEqualTo(myself, info, value, expected);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isNotEqualTo(Boolean expected) {
    return AssertionsOnValueInequality.isNotEqualTo(myself, info, value, expected);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isNotEqualTo(byte[] expected) {
    return AssertionsOnValueInequality.isNotEqualTo(myself, info, value, expected);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isNotEqualTo(DateTimeValue expected) {
    return AssertionsOnValueInequality.isNotEqualTo(myself, info, value, expected);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isNotEqualTo(DateValue expected) {
    return AssertionsOnValueInequality.isNotEqualTo(myself, info, value, expected);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isNotEqualTo(Number expected) {
    return AssertionsOnValueInequality.isNotEqualTo(myself, info, value, expected);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isNotEqualTo(String expected) {
    return AssertionsOnValueInequality.isNotEqualTo(myself, info, value, expected);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isNotEqualTo(Character expected) {
    return AssertionsOnValueInequality.isNotEqualTo(myself, info, value, expected);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isNotEqualTo(UUID expected) {
    return AssertionsOnValueInequality.isNotEqualTo(myself, info, value, expected);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isNotEqualTo(TimeValue expected) {
    return AssertionsOnValueInequality.isNotEqualTo(myself, info, value, expected);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isNotEqualTo(LocalDate expected) {
    return isNotEqualTo(DateValue.from(expected));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isNotEqualTo(LocalTime expected) {
    return isNotEqualTo(TimeValue.from(expected));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isNotEqualTo(LocalDateTime expected) {
    return isNotEqualTo(DateTimeValue.from(expected));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isBefore(DateValue date) {
    return AssertionsOnValueChronology.isBefore(myself, info, value, date);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isBefore(TimeValue time) {
    return AssertionsOnValueChronology.isBefore(myself, info, value, time);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isBefore(DateTimeValue dateTime) {
    return AssertionsOnValueChronology.isBefore(myself, info, value, dateTime);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isBefore(LocalDate date) {
    return isBefore(DateValue.from(date));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isBefore(LocalTime time) {
    return isBefore(TimeValue.from(time));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isBefore(LocalDateTime dateTime) {
    return isBefore(DateTimeValue.from(dateTime));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isBefore(String expected) {
    return AssertionsOnValueChronology.isBefore(myself, info, value, expected);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isBeforeOrEqualTo(DateValue date) {
    return AssertionsOnValueChronology.isBeforeOrEqualTo(myself, info, value, date);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isBeforeOrEqualTo(TimeValue time) {
    return AssertionsOnValueChronology.isBeforeOrEqualTo(myself, info, value, time);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isBeforeOrEqualTo(DateTimeValue dateTime) {
    return AssertionsOnValueChronology.isBeforeOrEqualTo(myself, info, value, dateTime);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isBeforeOrEqualTo(LocalDate date) {
    return isBeforeOrEqualTo(DateValue.from(date));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isBeforeOrEqualTo(LocalTime time) {
    return isBeforeOrEqualTo(TimeValue.from(time));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isBeforeOrEqualTo(LocalDateTime dateTime) {
    return isBeforeOrEqualTo(DateTimeValue.from(dateTime));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isBeforeOrEqualTo(String expected) {
    return AssertionsOnValueChronology.isBeforeOrEqualTo(myself, info, value, expected);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isAfter(DateValue date) {
    return AssertionsOnValueChronology.isAfter(myself, info, value, date);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isAfter(TimeValue time) {
    return AssertionsOnValueChronology.isAfter(myself, info, value, time);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isAfter(DateTimeValue dateTime) {
    return AssertionsOnValueChronology.isAfter(myself, info, value, dateTime);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isAfter(LocalDate date) {
    return isAfter(DateValue.from(date));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isAfter(LocalTime time) {
    return isAfter(TimeValue.from(time));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isAfter(LocalDateTime dateTime) {
    return isAfter(DateTimeValue.from(dateTime));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isAfter(String expected) {
    return AssertionsOnValueChronology.isAfter(myself, info, value, expected);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isAfterOrEqualTo(DateValue date) {
    return AssertionsOnValueChronology.isAfterOrEqualTo(myself, info, value, date);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isAfterOrEqualTo(TimeValue time) {
    return AssertionsOnValueChronology.isAfterOrEqualTo(myself, info, value, time);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isAfterOrEqualTo(DateTimeValue dateTime) {
    return AssertionsOnValueChronology.isAfterOrEqualTo(myself, info, value, dateTime);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isAfterOrEqualTo(LocalDate date) {
    return isAfterOrEqualTo(DateValue.from(date));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isAfterOrEqualTo(LocalTime time) {
    return isAfterOrEqualTo(TimeValue.from(time));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isAfterOrEqualTo(LocalDateTime dateTime) {
    return isAfterOrEqualTo(DateTimeValue.from(dateTime));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isAfterOrEqualTo(String expected) {
    return AssertionsOnValueChronology.isAfterOrEqualTo(myself, info, value, expected);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isZero() {
    return AssertionsOnValueEquality.isZero(myself, info, value);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isNotZero() {
    return AssertionsOnValueInequality.isNotZero(myself, info, value);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isGreaterThan(Number expected) {
    return AssertionsOnValueComparison.isGreaterThan(myself, info, value, expected);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isLessThan(Number expected) {
    return AssertionsOnValueComparison.isLessThan(myself, info, value, expected);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isGreaterThanOrEqualTo(Number expected) {
    return AssertionsOnValueComparison.isGreaterThanOrEqualTo(myself, info, value, expected);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isLessThanOrEqualTo(Number expected) {
    return AssertionsOnValueComparison.isLessThanOrEqualTo(myself, info, value, expected);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isCloseTo(Number expected, Number tolerance) {
    return AssertionsOnValueCloseness.isCloseTo(myself, info, value, expected, tolerance);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isCloseTo(DateValue expected, DateValue tolerance) {
    return AssertionsOnValueCloseness.isCloseTo(myself, info, value, expected, tolerance);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isCloseTo(DateValue expected, TimeValue tolerance) {
    return AssertionsOnValueCloseness.isCloseTo(myself, info, value, expected, tolerance);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isCloseTo(DateValue expected, DateTimeValue tolerance) {
    return AssertionsOnValueCloseness.isCloseTo(myself, info, value, expected, tolerance);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isCloseTo(TimeValue expected, TimeValue tolerance) {
    return AssertionsOnValueCloseness.isCloseTo(myself, info, value, expected, tolerance);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isCloseTo(DateTimeValue expected, DateValue tolerance) {
    return AssertionsOnValueCloseness.isCloseTo(myself, info, value, expected, tolerance);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isCloseTo(DateTimeValue expected, TimeValue tolerance) {
    return AssertionsOnValueCloseness.isCloseTo(myself, info, value, expected, tolerance);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isCloseTo(DateTimeValue expected, DateTimeValue tolerance) {
    return AssertionsOnValueCloseness.isCloseTo(myself, info, value, expected, tolerance);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E is(Condition<?> condition) {
    return AssertionsOnValueCondition.is(myself, info, value, condition);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E isNot(Condition<?> condition) {
    return AssertionsOnValueCondition.isNot(myself, info, value, condition);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E has(Condition<?> condition) {
    return AssertionsOnValueCondition.is(myself, info, value, condition);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E doesNotHave(Condition<?> condition) {
    return AssertionsOnValueCondition.isNot(myself, info, value, condition);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E satisfies(Condition<?> condition) {
    return AssertionsOnValueCondition.satisfies(myself, info, value, condition);
  }
}
