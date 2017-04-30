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
package org.assertj.db.api;

import org.assertj.db.api.assertions.*;
import org.assertj.db.api.assertions.impl.*;
import org.assertj.db.navigation.element.ValueElement;
import org.assertj.db.navigation.origin.OriginWithColumnsAndRowsFromChange;
import org.assertj.db.type.*;

import java.util.UUID;

/**
 * Base class for all values from a {@link org.assertj.db.type.Change} assertions.
 *
 * @param <E> The "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/1IZIRcY"
 *            target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *            for more details.
 * @param <O> The type of the assertion class of {@link org.assertj.db.navigation.origin.Origin}.
 * @author Régis Pouiller
 * @author Otoniel Isidoro
 */
public abstract class AbstractAssertWithValues <E extends AbstractAssertWithValues<E, O>, O extends OriginWithColumnsAndRowsFromChange<ChangesAssert, ChangeAssert, ChangeColumnAssert, ChangeRowAssert>>
        extends AbstractAssertWithOriginWithColumnsAndRowsFromChange<E, O>
        implements ValueElement,
                   AssertOnValueClass<E>,
                   AssertOnValueType<E>,
                   AssertOnValueNullity<E>,
                   AssertOnValueEquality<E>,
                   AssertOnValueInequality<E>,
                   AssertOnValueComparison<E>,
                   AssertOnValueChronology<E>,
                   AssertOnValueCloseness<E> {

  /**
   * The actual value on which the assertion is.
   */
  protected final Value value;

  /**
   * Constructor.
   *
   * @param selfType Type of this assertion class : a sub-class of {@code AbstractAssertWithValues}.
   * @param origin The assertion of {@link org.assertj.db.navigation.origin.Origin}.
   * @param value The value on which are the assertion methods.
   */
  AbstractAssertWithValues(Class<E> selfType, O origin, Value value) {
    super(selfType, origin);
    this.value = value;
  }

  /** {@inheritDoc} */
  @Override
  public E isOfClass(Class<?> expected) {
    return AssertionsOnValueClass.isOfClass(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public E isOfType(ValueType expected) {
    return AssertionsOnValueType.isOfType(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public E isOfAnyTypeIn(ValueType... expected) {
    return AssertionsOnValueType.isOfAnyTypeIn(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public E isNumber() {
    return AssertionsOnValueType.isNumber(myself, info, value);
  }

  /** {@inheritDoc} */
  @Override
  public E isBoolean() {
    return AssertionsOnValueType.isBoolean(myself, info, value);
  }

  /** {@inheritDoc} */
  @Override
  public E isDate() {
    return AssertionsOnValueType.isDate(myself, info, value);
  }

  /** {@inheritDoc} */
  @Override
  public E isTime() {
    return AssertionsOnValueType.isTime(myself, info, value);
  }

  /** {@inheritDoc} */
  @Override
  public E isDateTime() {
    return AssertionsOnValueType.isDateTime(myself, info, value);
  }

  /** {@inheritDoc} */
  @Override
  public E isBytes() {
    return AssertionsOnValueType.isBytes(myself, info, value);
  }

  /** {@inheritDoc} */
  @Override
  public E isText() {
    return AssertionsOnValueType.isText(myself, info, value);
  }

  /** {@inheritDoc} */
  @Override
  public E isUUID() {
    return AssertionsOnValueType.isUUID(myself, info, value);
  }

  /** {@inheritDoc} */
  @Override
  public E isNull() {
    return AssertionsOnValueNullity.isNull(myself, info, value);
  }

  /** {@inheritDoc} */
  @Override
  public E isNotNull() {
    return AssertionsOnValueNullity.isNotNull(myself, info, value);
  }

  /** {@inheritDoc} */
  @Override
  public E isEqualTo(Object expected) {
    return AssertionsOnValueEquality.isEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public E isEqualTo(Boolean expected) {
    return AssertionsOnValueEquality.isEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public E isTrue() {
    return AssertionsOnValueEquality.isTrue(myself, info, value);
  }

  /** {@inheritDoc} */
  @Override
  public E isFalse() {
    return AssertionsOnValueEquality.isFalse(myself, info, value);
  }

  /** {@inheritDoc} */
  @Override
  public E isEqualTo(Number expected) {
    return AssertionsOnValueEquality.isEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public E isEqualTo(byte[] expected) {
    return AssertionsOnValueEquality.isEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public E isEqualTo(String expected) {
    return AssertionsOnValueEquality.isEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public E isEqualTo(UUID expected) {
    return AssertionsOnValueEquality.isEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public E isEqualTo(DateValue expected) {
    return AssertionsOnValueEquality.isEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public E isEqualTo(TimeValue expected) {
    return AssertionsOnValueEquality.isEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public E isEqualTo(DateTimeValue expected) {
    return AssertionsOnValueEquality.isEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public E isNotEqualTo(Object expected) {
    return AssertionsOnValueInequality.isNotEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public E isNotEqualTo(Boolean expected) {
    return AssertionsOnValueInequality.isNotEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public E isNotEqualTo(byte[] expected) {
    return AssertionsOnValueInequality.isNotEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public E isNotEqualTo(DateTimeValue expected) {
    return AssertionsOnValueInequality.isNotEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public E isNotEqualTo(DateValue expected) {
    return AssertionsOnValueInequality.isNotEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public E isNotEqualTo(Number expected) {
    return AssertionsOnValueInequality.isNotEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public E isNotEqualTo(String expected) {
    return AssertionsOnValueInequality.isNotEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public E isNotEqualTo(UUID expected) {
    return AssertionsOnValueInequality.isNotEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public E isNotEqualTo(TimeValue expected) {
    return AssertionsOnValueInequality.isNotEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public E isBefore(DateValue date) {
    return AssertionsOnValueChronology.isBefore(myself, info, value, date);
  }

  /** {@inheritDoc} */
  @Override
  public E isBefore(TimeValue time) {
    return AssertionsOnValueChronology.isBefore(myself, info, value, time);
  }

  /** {@inheritDoc} */
  @Override
  public E isBefore(DateTimeValue dateTime) {
    return AssertionsOnValueChronology.isBefore(myself, info, value, dateTime);
  }

  /** {@inheritDoc} */
  @Override
  public E isBefore(String expected) {
    return AssertionsOnValueChronology.isBefore(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public E isBeforeOrEqualTo(DateValue date) {
    return AssertionsOnValueChronology.isBeforeOrEqualTo(myself, info, value, date);
  }

  /** {@inheritDoc} */
  @Override
  public E isBeforeOrEqualTo(TimeValue time) {
    return AssertionsOnValueChronology.isBeforeOrEqualTo(myself, info, value, time);
  }

  /** {@inheritDoc} */
  @Override
  public E isBeforeOrEqualTo(DateTimeValue dateTime) {
    return AssertionsOnValueChronology.isBeforeOrEqualTo(myself, info, value, dateTime);
  }

  /** {@inheritDoc} */
  @Override
  public E isBeforeOrEqualTo(String expected) {
    return AssertionsOnValueChronology.isBeforeOrEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public E isAfter(DateValue date) {
    return AssertionsOnValueChronology.isAfter(myself, info, value, date);
  }

  /** {@inheritDoc} */
  @Override
  public E isAfter(TimeValue time) {
    return AssertionsOnValueChronology.isAfter(myself, info, value, time);
  }

  /** {@inheritDoc} */
  @Override
  public E isAfter(DateTimeValue dateTime) {
    return AssertionsOnValueChronology.isAfter(myself, info, value, dateTime);
  }

  /** {@inheritDoc} */
  @Override
  public E isAfter(String expected) {
    return AssertionsOnValueChronology.isAfter(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public E isAfterOrEqualTo(DateValue date) {
    return AssertionsOnValueChronology.isAfterOrEqualTo(myself, info, value, date);
  }

  /** {@inheritDoc} */
  @Override
  public E isAfterOrEqualTo(TimeValue time) {
    return AssertionsOnValueChronology.isAfterOrEqualTo(myself, info, value, time);
  }

  /** {@inheritDoc} */
  @Override
  public E isAfterOrEqualTo(DateTimeValue dateTime) {
    return AssertionsOnValueChronology.isAfterOrEqualTo(myself, info, value, dateTime);
  }

  /** {@inheritDoc} */
  @Override
  public E isAfterOrEqualTo(String expected) {
    return AssertionsOnValueChronology.isAfterOrEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public E isZero() {
    return AssertionsOnValueEquality.isZero(myself, info, value);
  }

  /** {@inheritDoc} */
  @Override
  public E isNotZero() {
    return AssertionsOnValueInequality.isNotZero(myself, info, value);
  }

  /** {@inheritDoc} */
  @Override
  public E isGreaterThan(Number expected) {
    return AssertionsOnValueComparison.isGreaterThan(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public E isLessThan(Number expected) {
    return AssertionsOnValueComparison.isLessThan(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public E isGreaterThanOrEqualTo(Number expected) {
    return AssertionsOnValueComparison.isGreaterThanOrEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public E isLessThanOrEqualTo(Number expected) {
    return AssertionsOnValueComparison.isLessThanOrEqualTo(myself, info, value, expected);
  }

  /** {@inheritDoc} */
  @Override
  public E isCloseTo(Number expected, Number tolerance) {
    return AssertionsOnValueCloseness.isCloseTo(myself, info, value, expected, tolerance);
  }

  /** {@inheritDoc} */
  @Override
  public E isCloseTo(DateValue expected, DateValue tolerance) {
    return AssertionsOnValueCloseness.isCloseTo(myself, info, value, expected, tolerance);
  }

  /** {@inheritDoc} */
  @Override
  public E isCloseTo(DateValue expected, TimeValue tolerance) {
    return AssertionsOnValueCloseness.isCloseTo(myself, info, value, expected, tolerance);
  }

  /** {@inheritDoc} */
  @Override
  public E isCloseTo(DateValue expected, DateTimeValue tolerance) {
    return AssertionsOnValueCloseness.isCloseTo(myself, info, value, expected, tolerance);
  }

  /** {@inheritDoc} */
  @Override
  public E isCloseTo(TimeValue expected, TimeValue tolerance) {
    return AssertionsOnValueCloseness.isCloseTo(myself, info, value, expected, tolerance);
  }

  /** {@inheritDoc} */
  @Override
  public E isCloseTo(DateTimeValue expected, DateValue tolerance) {
    return AssertionsOnValueCloseness.isCloseTo(myself, info, value, expected, tolerance);
  }

  /** {@inheritDoc} */
  @Override
  public E isCloseTo(DateTimeValue expected, TimeValue tolerance) {
    return AssertionsOnValueCloseness.isCloseTo(myself, info, value, expected, tolerance);
  }

  /** {@inheritDoc} */
  @Override
  public E isCloseTo(DateTimeValue expected, DateTimeValue tolerance) {
    return AssertionsOnValueCloseness.isCloseTo(myself, info, value, expected, tolerance);
  }
}
