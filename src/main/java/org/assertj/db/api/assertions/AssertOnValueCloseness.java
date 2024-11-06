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
package org.assertj.db.api.assertions;

import org.assertj.db.type.DateTimeValue;
import org.assertj.db.type.DateValue;
import org.assertj.db.type.TimeValue;

/**
 * Defines the assertion methods on the closeness of a value.
 *
 * @param <T> The "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/1IZIRcY"
 *            target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *            for more details.
 * @author Régis Pouiller
 * @since 1.1.0
 */
public interface AssertOnValueCloseness<T extends AssertOnValueCloseness<T>> {

  /**
   * Verifies that the value is close to a number.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is close to number 3 with a tolerance of 1 (that means that all the numbers between 2 and 4
   * included are close enough) :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(table).row().value().isCloseTo(3, 1);
   * </code>
   * </pre>
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the {@code Row} at end point
   * of the first {@code Change} is close to number 3 with a tolerance of 0.75 :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change().rowAtEndPoint().value().isCloseTo(3, 0.75);
   * </code>
   * </pre>
   *
   * @param expected  The expected number value.
   * @param tolerance The tolerance of the closeness.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not close to the number in parameter.
   * @see org.assertj.db.api.AbstractValueAssert#isCloseTo(Number, Number)
   * @see org.assertj.db.api.AbstractAssertWithValues#isCloseTo(Number, Number)
   */
  T isCloseTo(Number expected, Number tolerance);

  /**
   * Verifies that the value is close to a date.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is close to a date value with a tolerance of 2 days :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(table).row().value().isCloseTo(DateValue.of(2014, 7, 7), DateValue.of(0, 0, 2));
   * </code>
   * </pre>
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the {@code Row} at end point
   * of the first {@code Change} is close to a date value with a tolerance of 2 days :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change().rowAtEndPoint().value().isCloseTo(DateValue.of(2014, 7, 7), DateValue.of(0, 0, 2));
   * </code>
   * </pre>
   *
   * @param expected  The expected date value.
   * @param tolerance The tolerance of the closeness.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not close to the date in parameter.
   * @see org.assertj.db.api.AbstractValueAssert#isCloseTo(DateValue, DateValue)
   * @see org.assertj.db.api.AbstractAssertWithValues#isCloseTo(DateValue, DateValue)
   */
  T isCloseTo(DateValue expected, DateValue tolerance);

  /**
   * Verifies that the value is close to a date.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is close to a date value with a tolerance of 1 hour :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(table).row().value().isCloseTo(DateValue.of(2014, 7, 7), TimeValue.of(1, 0));
   * </code>
   * </pre>
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the {@code Row} at end point
   * of the first {@code Change} is close to a date value with a tolerance of 1 hour :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change().rowAtEndPoint().value().isCloseTo(DateValue.of(2014, 7, 7), TimeValue.of(1, 0));
   * </code>
   * </pre>
   *
   * @param expected  The expected date value.
   * @param tolerance The tolerance of the closeness.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not close to the date in parameter.
   * @see org.assertj.db.api.AbstractValueAssert#isCloseTo(DateValue, TimeValue)
   * @see org.assertj.db.api.AbstractAssertWithValues#isCloseTo(DateValue, TimeValue)
   */
  T isCloseTo(DateValue expected, TimeValue tolerance);

  /**
   * Verifies that the value is close to a date.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is close to a date value with a tolerance of 2 days and 1 hour :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(table).row().value().isCloseTo(DateValue.of(2014, 7, 7),
   *                                           DateTimeValue.of(DateValue.of(0, 0, 2), TimeValue.of(1, 0)));
   * </code>
   * </pre>
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the {@code Row} at end point
   * of the first {@code Change} is close to a date value with a tolerance of 2 days and 1 hour :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change().rowAtEndPoint().value().isCloseTo(DateValue.of(2014, 7, 7),
   *                                           DateTimeValue.of(DateValue.of(0, 0, 2), TimeValue.of(1, 0)));
   * </code>
   * </pre>
   *
   * @param expected  The expected date value.
   * @param tolerance The tolerance of the closeness.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not close to the date in parameter.
   * @see org.assertj.db.api.AbstractValueAssert#isCloseTo(DateValue, DateTimeValue)
   * @see org.assertj.db.api.AbstractAssertWithValues#isCloseTo(DateValue, DateTimeValue)
   */
  T isCloseTo(DateValue expected, DateTimeValue tolerance);

  /**
   * Verifies that the value is close to a time.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is close to a time value with a tolerance of 1 hour :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(table).row().value().isCloseTo(TimeValue.of(21, 29, 30), TimeValue.of(1, 0));
   * </code>
   * </pre>
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the {@code Row} at end point
   * of the first {@code Change} is close to a time value with a tolerance of 1 hour :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change().rowAtEndPoint().value().isCloseTo(TimeValue.of(21, 29, 30), TimeValue.of(1, 0));
   * </code>
   * </pre>
   *
   * @param expected  The expected time value.
   * @param tolerance The tolerance of the closeness.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not close to the time in parameter.
   * @see org.assertj.db.api.AbstractValueAssert#isCloseTo(TimeValue, TimeValue)
   * @see org.assertj.db.api.AbstractAssertWithValues#isCloseTo(TimeValue, TimeValue)
   */
  T isCloseTo(TimeValue expected, TimeValue tolerance);

  /**
   * Verifies that the value is close to a date/time.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is close to a date/time value with a tolerance of 2 days :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(table).row().value().isCloseTo(DateTimeValue.of(DateValue.of(2014, 7, 7), TimeValue.of(21, 29, 30)),
   *                                           DateValue.of(0, 0, 2));
   * </code>
   * </pre>
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the {@code Row} at end point
   * of the first {@code Change} is close to a date/time value with a tolerance of 2 days :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change().rowAtEndPoint().value().isCloseTo(
   *                                           DateTimeValue.of(DateValue.of(2014, 7, 7), TimeValue.of(21, 29, 30)),
   *                                           DateValue.of(0, 0, 2));
   * </code>
   * </pre>
   *
   * @param expected  The expected date/time value.
   * @param tolerance The tolerance of the closeness.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not close to the date/time in parameter.
   * @see org.assertj.db.api.AbstractValueAssert#isCloseTo(DateTimeValue, DateValue)
   * @see org.assertj.db.api.AbstractAssertWithValues#isCloseTo(DateTimeValue, DateValue)
   */
  T isCloseTo(DateTimeValue expected, DateValue tolerance);

  /**
   * Verifies that the value is close to a date/time.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is close to a date/time value with a tolerance of 1 hour :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(table).row().value().isCloseTo(DateTimeValue.of(DateValue.of(2014, 7, 7), TimeValue.of(21, 29, 30)),
   *                                           TimeValue.of(1, 0));
   * </code>
   * </pre>
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the {@code Row} at end point
   * of the first {@code Change} is close to a date/time value with a tolerance of 1 hour :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change().rowAtEndPoint().value().isCloseTo(
   *                                           DateTimeValue.of(DateValue.of(2014, 7, 7), TimeValue.of(21, 29, 30)),
   *                                           TimeValue.of(1, 0));
   * </code>
   * </pre>
   *
   * @param expected  The expected date/time value.
   * @param tolerance The tolerance of the closeness.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not close to the date/time in parameter.
   * @see org.assertj.db.api.AbstractValueAssert#isCloseTo(DateTimeValue, TimeValue)
   * @see org.assertj.db.api.AbstractAssertWithValues#isCloseTo(DateTimeValue, TimeValue)
   */
  T isCloseTo(DateTimeValue expected, TimeValue tolerance);

  /**
   * Verifies that the value is close to a date/time.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is close to a date/time value with a tolerance of 2 days and 1 hour :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(table).row().value().isCloseTo(DateTimeValue.of(DateValue.of(2014, 7, 7), TimeValue.of(21, 29, 30)),
   *                                           DateTimeValue.of(DateValue.of(0, 0, 2), TimeValue.of(1, 0)));
   * </code>
   * </pre>
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the {@code Row} at end point
   * of the first {@code Change} is close to a date/time value with a tolerance of 2 days and 1 hour :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change().rowAtEndPoint().value().isCloseTo(
   *                                           DateTimeValue.of(DateValue.of(2014, 7, 7), TimeValue.of(21, 29, 30)),
   *                                           DateTimeValue.of(DateValue.of(0, 0, 2), TimeValue.of(1, 0)));
   * </code>
   * </pre>
   *
   * @param expected  The expected date/time value.
   * @param tolerance The tolerance of the closeness.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not close to the date/time in parameter.
   * @see org.assertj.db.api.AbstractValueAssert#isCloseTo(DateTimeValue, DateTimeValue)
   * @see org.assertj.db.api.AbstractAssertWithValues#isCloseTo(DateTimeValue, DateTimeValue)
   */
  T isCloseTo(DateTimeValue expected, DateTimeValue tolerance);
}
