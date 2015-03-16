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
package org.assertj.db.api.assertions;

import org.assertj.db.type.DateTimeValue;
import org.assertj.db.type.DateValue;
import org.assertj.db.type.TimeValue;

/**
 * Defines the assertion methods on the chronology of a value.
 *
 * @param <T> The "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/anMa4g"
 *            target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *            for more details.
 * @author Régis Pouiller
 */
public interface AssertOnValueChronology<T extends AssertOnValueChronology<T>> {

  /**
   * Verifies that the value is before a date value.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is before a date value :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(table).row().value().isBefore(DateValue.of(2007, 12, 23));
   * </code>
   * </pre>
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the {@code Row} at end point
   * of the first {@code Change} is before a date value :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change().rowAtEndPoint().value().isBefore(DateValue.of(2007, 12, 23));
   * </code>
   * </pre>
   *
   * @param date The date value to compare to.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not before to the date value in parameter.
   * @see org.assertj.db.api.AbstractValueAssert#isBefore(org.assertj.db.type.DateValue)
   * @see org.assertj.db.api.AbstractAssertWithValues#isBefore(org.assertj.db.type.DateValue)
   */
  public T isBefore(DateValue date);

  /**
   * Verifies that the value is before a time value.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is before a time value :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(table).row().value().isBefore(TimeValue.of(2007, 12, 23));
   * </code>
   * </pre>
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the {@code Row} at end point
   * of the first {@code Change} is before a time value :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change().rowAtEndPoint().value().isBefore(TimeValue.of(2007, 12, 23));
   * </code>
   * </pre>
   *
   * @param time The time value to compare to.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not before to the time value in parameter.
   * @see org.assertj.db.api.AbstractValueAssert#isBefore(org.assertj.db.type.TimeValue)
   * @see org.assertj.db.api.AbstractAssertWithValues#isBefore(org.assertj.db.type.TimeValue)
   */
  public T isBefore(TimeValue time);

  /**
   * Verifies that the value is before a date/time value.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is before a date/time value :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(table).row().value().isBefore(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(21, 29)));
   * </code>
   * </pre>
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the {@code Row} at end point
   * of the first {@code Change} is before a date/time value :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change().rowAtEndPoint().value().isBefore(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(21, 29)));
   * </code>
   * </pre>
   *
   * @param dateTime The date/time value to compare to.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not before to the date/time value in parameter.
   * @see org.assertj.db.api.AbstractValueAssert#isBefore(org.assertj.db.type.DateTimeValue)
   * @see org.assertj.db.api.AbstractAssertWithValues#isBefore(org.assertj.db.type.DateTimeValue)
   */
  public T isBefore(DateTimeValue dateTime);

  /**
   * Verifies that the value is before a date, time or date/time represented by a {@code String}.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is before a date represented by a {@code String} :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(table).row().value().isBefore(&quot;2007-12-23&quot;);
   * </code>
   * </pre>
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the {@code Row} at end point
   * of the first {@code Change} is before a date represented by a {@code String} :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change().rowAtEndPoint().value().isBefore(&quot;2007-12-23&quot;);
   * </code>
   * </pre>
   *
   * @param expected The {@code String} representing a date, time or date/time to compare to.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not before the date, time or date/time represented in parameter.
   * @see org.assertj.db.api.AbstractValueAssert#isBefore(String)
   * @see org.assertj.db.api.AbstractAssertWithValues#isBefore(String)
   */
  public T isBefore(String expected);

  /**
   * Verifies that the value is before or equal to a date value.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is before or equal to a date value :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(table).row().value().isBeforeOrEqualTo(DateValue.of(2007, 12, 23));
   * </code>
   * </pre>
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the {@code Row} at end point
   * of the first {@code Change} is before or equal to a date value :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change().rowAtEndPoint().value().isBeforeOrEqualTo(DateValue.of(2007, 12, 23));
   * </code>
   * </pre>
   *
   * @param date The date value to compare to.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not before or equal to the date value in parameter.
   * @see org.assertj.db.api.AbstractValueAssert#isBeforeOrEqualTo(org.assertj.db.type.DateValue)
   * @see org.assertj.db.api.AbstractAssertWithValues#isBeforeOrEqualTo(org.assertj.db.type.DateValue)
   */
  public T isBeforeOrEqualTo(DateValue date);

  /**
   * Verifies that the value is before or equal to a time value.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is before or equal to a time value :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(table).row().value().isBeforeOrEqualTo(TimeValue.of(2007, 12, 23));
   * </code>
   * </pre>
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the {@code Row} at end point
   * of the first {@code Change} is before or equal to a time value :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change().rowAtEndPoint().value().isBeforeOrEqualTo(TimeValue.of(2007, 12, 23));
   * </code>
   * </pre>
   *
   * @param time The time value to compare to.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not before or equal to the time value in parameter.
   * @see org.assertj.db.api.AbstractValueAssert#isBeforeOrEqualTo(org.assertj.db.type.TimeValue)
   * @see org.assertj.db.api.AbstractAssertWithValues#isBeforeOrEqualTo(org.assertj.db.type.TimeValue)
   */
  public T isBeforeOrEqualTo(TimeValue time);

  /**
   * Verifies that the value is before or equal to a date/time value.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is before or equal to a date/time value :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(table).row().value().isBeforeOrEqualTo(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(21, 29)));
   * </code>
   * </pre>
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the {@code Row} at end point
   * of the first {@code Change} is before or equal to a date/time value :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change().rowAtEndPoint().value().isBeforeOrEqualTo(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(21, 29)));
   * </code>
   * </pre>
   *
   * @param dateTime The date/time value to compare to.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not before or equal to the date/time value in parameter.
   * @see org.assertj.db.api.AbstractValueAssert#isBeforeOrEqualTo(org.assertj.db.type.DateTimeValue)
   * @see org.assertj.db.api.AbstractAssertWithValues#isBeforeOrEqualTo(org.assertj.db.type.DateTimeValue)
   */
  public T isBeforeOrEqualTo(DateTimeValue dateTime);

  /**
   * Verifies that the value is before or equal to a date, time or date/time represented by a {@code String}.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is before or equal to a date represented by a {@code String} :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(table).row().value().isBeforeOrEqualTo(&quot;2007-12-23&quot;);
   * </code>
   * </pre>
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the {@code Row} at end point
   * of the first {@code Change} is before or equal to a date represented by a {@code String} :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change().rowAtEndPoint().value().isBeforeOrEqualTo(&quot;2007-12-23&quot;);
   * </code>
   * </pre>
   *
   * @param expected The {@code String} representing a date, time or date/time to compare to.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not before or equal to the date, time or date/time represented in parameter.
   * @see org.assertj.db.api.AbstractValueAssert#isBeforeOrEqualTo(String)
   * @see org.assertj.db.api.AbstractAssertWithValues#isBeforeOrEqualTo(String)
   */
  public T isBeforeOrEqualTo(String expected);

  /**
   * Verifies that the value is after a date value.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is after a date value :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(table).row().value().isAfter(DateValue.of(2007, 12, 23));
   * </code>
   * </pre>
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the {@code Row} at end point
   * of the first {@code Change} is after a date value :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change().rowAtEndPoint().value().isAfter(DateValue.of(2007, 12, 23));
   * </code>
   * </pre>
   *
   * @param date The date value to compare to.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not after to the date value in parameter.
   * @see org.assertj.db.api.AbstractValueAssert#isAfter(org.assertj.db.type.DateValue)
   * @see org.assertj.db.api.AbstractAssertWithValues#isAfter(org.assertj.db.type.DateValue)
   */
  public T isAfter(DateValue date);

  /**
   * Verifies that the value is after a time value.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is after a time value :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(table).row().value().isAfter(TimeValue.of(2007, 12, 23));
   * </code>
   * </pre>
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the {@code Row} at end point
   * of the first {@code Change} is after a time value :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change().rowAtEndPoint().value().isAfter(TimeValue.of(2007, 12, 23));
   * </code>
   * </pre>
   *
   * @param time The time value to compare to.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not after to the time value in parameter.
   * @see org.assertj.db.api.AbstractValueAssert#isAfter(org.assertj.db.type.TimeValue)
   * @see org.assertj.db.api.AbstractAssertWithValues#isAfter(org.assertj.db.type.TimeValue)
   */
  public T isAfter(TimeValue time);

  /**
   * Verifies that the value is after a date/time value.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is after a date/time value :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(table).row().value().isAfter(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(21, 29)));
   * </code>
   * </pre>
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the {@code Row} at end point
   * of the first {@code Change} is after a date/time value :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change().rowAtEndPoint().value().isAfter(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(21, 29)));
   * </code>
   * </pre>
   *
   * @param dateTime The date/time value to compare to.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not after to the date/time value in parameter.
   * @see org.assertj.db.api.AbstractValueAssert#isAfter(org.assertj.db.type.DateTimeValue)
   * @see org.assertj.db.api.AbstractAssertWithValues#isAfter(org.assertj.db.type.DateTimeValue)
   */
  public T isAfter(DateTimeValue dateTime);

  /**
   * Verifies that the value is after a date, time or date/time represented by a {@code String}.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is after a date represented by a {@code String} :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(table).row().value().isAfter(&quot;2007-12-23&quot;);
   * </code>
   * </pre>
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the {@code Row} at end point
   * of the first {@code Change} is after a date represented by a {@code String} :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change().rowAtEndPoint().value().isAfter(&quot;2007-12-23&quot;);
   * </code>
   * </pre>
   *
   * @param expected The {@code String} representing a date, time or date/time to compare to.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not after the date, time or date/time represented in parameter.
   * @see org.assertj.db.api.AbstractValueAssert#isAfter(String)
   * @see org.assertj.db.api.AbstractAssertWithValues#isAfter(String)
   */
  public T isAfter(String expected);

  /**
   * Verifies that the value is after or equal to a date value.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is after or equal to a date value :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(table).row().value().isAfterOrEqualTo(DateValue.of(2007, 12, 23));
   * </code>
   * </pre>
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the {@code Row} at end point
   * of the first {@code Change} is after or equal to a date value :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change().rowAtEndPoint().value().isAfterOrEqualTo(DateValue.of(2007, 12, 23));
   * </code>
   * </pre>
   *
   * @param date The date value to compare to.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not after or equal to the time value in parameter.
   * @see org.assertj.db.api.AbstractValueAssert#isAfterOrEqualTo(org.assertj.db.type.DateValue)
   * @see org.assertj.db.api.AbstractAssertWithValues#isAfterOrEqualTo(org.assertj.db.type.DateValue)
   */
  public T isAfterOrEqualTo(DateValue date);

  /**
   * Verifies that the value is after or equal to a time value.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is after or equal to a time value :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(table).row().value().isAfterOrEqualTo(TimeValue.of(2007, 12, 23));
   * </code>
   * </pre>
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the {@code Row} at end point
   * of the first {@code Change} is after or equal to a time value :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change().rowAtEndPoint().value().isAfterOrEqualTo(TimeValue.of(2007, 12, 23));
   * </code>
   * </pre>
   *
   * @param time The time value to compare to.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not after or equal to the time value in parameter.
   * @see org.assertj.db.api.AbstractValueAssert#isAfterOrEqualTo(org.assertj.db.type.TimeValue)
   * @see org.assertj.db.api.AbstractAssertWithValues#isAfterOrEqualTo(org.assertj.db.type.TimeValue)
   */
  public T isAfterOrEqualTo(TimeValue time);

  /**
   * Verifies that the value is after or equal to a date/time value.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is after or equal to a date/time value :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(table).row().value().isAfterOrEqualTo(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(21, 29)));
   * </code>
   * </pre>
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the {@code Row} at end point
   * of the first {@code Change} is after or equal to a date/time value :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change().rowAtEndPoint().value().isAfterOrEqualTo(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(21, 29)));
   * </code>
   * </pre>
   *
   * @param dateTime The date/time value to compare to.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not after or equal to the date/time value in parameter.
   * @see org.assertj.db.api.AbstractValueAssert#isAfterOrEqualTo(org.assertj.db.type.DateTimeValue)
   * @see org.assertj.db.api.AbstractAssertWithValues#isAfterOrEqualTo(org.assertj.db.type.DateTimeValue)
   */
  public T isAfterOrEqualTo(DateTimeValue dateTime);

  /**
   * Verifies that the value is after or equal to a date, time or date/time represented by a {@code String}.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is after or equal to a date represented by a {@code String} :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(table).row().value().isAfterOrEqualTo(&quot;2007-12-23&quot;);
   * </code>
   * </pre>
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the {@code Row} at end point
   * of the first {@code Change} is after or equal to a date represented by a {@code String} :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change().rowAtEndPoint().value().isAfterOrEqualTo(&quot;2007-12-23&quot;);
   * </code>
   * </pre>
   *
   * @param expected The {@code String} representing a date, time or date/time to compare to.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not after or equal to the date, time or date/time represented in parameter.
   * @see org.assertj.db.api.AbstractValueAssert#isAfterOrEqualTo(String)
   * @see org.assertj.db.api.AbstractAssertWithValues#isAfterOrEqualTo(String)
   */
  public T isAfterOrEqualTo(String expected);
}
