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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

/**
 * Defines the assertion methods on the equality of a value.
 *
 * @param <T> The "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/1IZIRcY"
 *            target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *            for more details.
 * @author Régis Pouiller
 * @author Otoniel Isidoro
 */
public interface AssertOnValueEquality<T extends AssertOnValueEquality<T>> {

  /**
   * Verifies that the value is equal to a boolean.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is equal to french Locale :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(table).row().value().isEqualTo(Locale.FRENCH);
   * </code>
   * </pre>
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the {@code Row} at end point
   * of the first {@code Change} is equal to french Locale :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change().rowAtEndPoint().value().isEqualTo(Locale.FRENCH);
   * </code>
   * </pre>
   *
   * @param expected The expected object value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not equal to the object in parameter.
   * @see org.assertj.db.api.AbstractValueAssert#isEqualTo(Object)
   * @see org.assertj.db.api.AbstractAssertWithValues#isEqualTo(Object)
   */
  T isEqualTo(Object expected);

  /**
   * Verifies that the value is equal to a boolean.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is equal to true boolean :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(table).row().value().isEqualTo(true);
   * </code>
   * </pre>
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the {@code Row} at end point
   * of the first {@code Change} is equal to true boolean :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change().rowAtEndPoint().value().isEqualTo(true);
   * </code>
   * </pre>
   *
   * @param expected The expected boolean value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not equal to the boolean in parameter.
   * @see org.assertj.db.api.AbstractValueAssert#isEqualTo(Boolean)
   * @see org.assertj.db.api.AbstractAssertWithValues#isEqualTo(Boolean)
   */
  T isEqualTo(Boolean expected);

  /**
   * Verifies that the value is equal to true boolean.
   * <p>
   * Example with the value in the first {@code Column} of the first {@code Row} of the {@code Table} :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(table).row().value().isTrue();
   * </code>
   * </pre>
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the {@code Row} at end point
   * of the first {@code Change} :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change().rowAtEndPoint().value().isTrue();
   * </code>
   * </pre>
   *
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not equal to true boolean.
   * @see org.assertj.db.api.AbstractValueAssert#isTrue()
   * @see org.assertj.db.api.AbstractAssertWithValues#isTrue()
   */
  T isTrue();

  /**
   * Verifies that the value is equal to false boolean.
   * <p>
   * Example with the value in the first {@code Column} of the first {@code Row} of the {@code Table} :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(table).row().value().isFalse();
   * </code>
   * </pre>
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the {@code Row} at end point
   * of the first {@code Change} :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change().rowAtEndPoint().value().isFalse();
   * </code>
   * </pre>
   *
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not equal to false boolean.
   * @see org.assertj.db.api.AbstractValueAssert#isFalse()
   * @see org.assertj.db.api.AbstractAssertWithValues#isFalse()
   */
  T isFalse();

  /**
   * Verifies that the value is equal to a number.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is equal to number 3 :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(table).row().value().isEqualTo(3);
   * </code>
   * </pre>
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the {@code Row} at end point
   * of the first {@code Change} is equal to number 3 :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change().rowAtEndPoint().value().isEqualTo(3);
   * </code>
   * </pre>
   *
   * @param expected The expected number value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not equal to the number in parameter.
   * @see org.assertj.db.api.AbstractValueAssert#isEqualTo(Number)
   * @see org.assertj.db.api.AbstractAssertWithValues#isEqualTo(Number)
   */
  T isEqualTo(Number expected);

  /**
   * Verifies that the value is equal to a array of bytes.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is equal to a array of bytes loaded from a file in the classpath :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * byte[] bytes = bytesContentFromClassPathOf(&quot;file.png&quot;);
   * assertThat(table).row().value().isEqualTo(bytes);
   * </code>
   * </pre>
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the {@code Row} at end point
   * of the first {@code Change} is equal to a array of bytes loaded from a file in the classpath :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * byte[] bytes = bytesContentFromClassPathOf(&quot;file.png&quot;);
   * assertThat(changes).change().rowAtEndPoint().value().isEqualTo(bytes);
   * </code>
   * </pre>
   *
   * @param expected The expected array of bytes value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not equal to the array of bytes in parameter.
   * @see org.assertj.db.api.AbstractValueAssert#isEqualTo(byte[])
   * @see org.assertj.db.api.AbstractAssertWithValues#isEqualTo(byte[])
   */
  T isEqualTo(byte[] expected);

  /**
   * Verifies that the value is equal to a text.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is equal to a text :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(table).row().value().isEqualTo(&quot;text&quot;);
   * </code>
   * </pre>
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the {@code Row} at end point
   * of the first {@code Change} is equal to a text :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change().rowAtEndPoint().value().isEqualTo(&quot;text&quot;);
   * </code>
   * </pre>
   *
   * @param expected The expected text value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not equal to the text in parameter.
   * @see org.assertj.db.api.AbstractValueAssert#isEqualTo(String)
   * @see org.assertj.db.api.AbstractAssertWithValues#isEqualTo(String)
   */
  T isEqualTo(String expected);

  /**
   * Verifies that the value is equal to a character.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is equal to a character :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(table).row().value().isEqualTo('c');
   * </code>
   * </pre>
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the {@code Row} at end point
   * of the first {@code Change} is equal to a character :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change().rowAtEndPoint().value().isEqualTo('c');
   * </code>
   * </pre>
   *
   * @param expected The expected character value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not equal to the character in parameter.
   * @see org.assertj.db.api.AbstractValueAssert#isEqualTo(Character)
   * @see org.assertj.db.api.AbstractAssertWithValues#isEqualTo(Character)
   * @since 1.2.0
   */
  T isEqualTo(Character expected);

  /**
   * Verifies that the value is equal to an UUID.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is equal to an UUID :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(table).row().value().isEqualTo(UUID.fromString(&quot;30B443AE-C0C9-4790-9BEC-CE1380808435&quot;));
   * </code>
   * </pre>
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the {@code Row} at end point
   * of the first {@code Change} is equal to an UUID :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change().rowAtEndPoint().value().isEqualTo(UUID.fromString(&quot;30B443AE-C0C9-4790-9BEC-CE1380808435&quot;));
   * </code>
   * </pre>
   *
   * @param expected The expected UUID value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not equal to the UUID in parameter.
   * @see org.assertj.db.api.AbstractValueAssert#isEqualTo(UUID)
   * @see org.assertj.db.api.AbstractAssertWithValues#isEqualTo(UUID)
   * @since 1.1.0
   */
  T isEqualTo(UUID expected);

  /**
   * Verifies that the value is equal to a date value.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is equal to a date value :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(table).row().value().isEqualTo(DateValue.of(2014, 7, 7));
   * </code>
   * </pre>
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the {@code Row} at end point
   * of the first {@code Change} is equal to a date value :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change().rowAtEndPoint().value().isEqualTo(DateValue.of(2014, 7, 7));
   * </code>
   * </pre>
   *
   * @param expected The expected date value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not equal to the date value in parameter.
   * @see org.assertj.db.api.AbstractValueAssert#isEqualTo(DateValue)
   * @see org.assertj.db.api.AbstractAssertWithValues#isEqualTo(DateValue)
   */
  T isEqualTo(DateValue expected);

  /**
   * Verifies that the value is equal to a time value.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is equal to a time value :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(table).row().value().isEqualTo(TimeValue.of(21, 29, 30));
   * </code>
   * </pre>
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the {@code Row} at end point
   * of the first {@code Change} is equal to a time value :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change().rowAtEndPoint().value().isEqualTo(TimeValue.of(21, 29, 30));
   * </code>
   * </pre>
   *
   * @param expected The expected time value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not equal to the time value in parameter.
   * @see org.assertj.db.api.AbstractValueAssert#isEqualTo(TimeValue)
   * @see org.assertj.db.api.AbstractAssertWithValues#isEqualTo(TimeValue)
   */
  T isEqualTo(TimeValue expected);

  /**
   * Verifies that the value is equal to a date/time value.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is equal to a date/time value :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(table).row().value().isEqualTo(DateTimeValue.of(DateValue.of(2014, 7, 7), TimeValue.of(21, 29)));
   * </code>
   * </pre>
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the {@code Row} at end point
   * of the first {@code Change} is equal to a date/time value :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change().rowAtEndPoint().value().isEqualTo(DateTimeValue.of(DateValue.of(2014, 7, 7), TimeValue.of(21, 29)));
   * </code>
   * </pre>
   *
   * @param expected The expected date/time value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not equal to the date/time value in parameter.
   * @see org.assertj.db.api.AbstractValueAssert#isEqualTo(DateTimeValue)
   * @see org.assertj.db.api.AbstractAssertWithValues#isEqualTo(DateTimeValue)
   */
  T isEqualTo(DateTimeValue expected);

  /**
   * Verifies that the value is equal to a date value.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is equal to a date value :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(table).row().value().isEqualTo(LocalDate.of(2014, 7, 7));
   * </code>
   * </pre>
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the {@code Row} at end point
   * of the first {@code Change} is equal to a date value :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change().rowAtEndPoint().value().isEqualTo(LocalDate.of(2014, 7, 7));
   * </code>
   * </pre>
   *
   * @param expected The expected date value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not equal to the date value in parameter.
   * @see org.assertj.db.api.AbstractValueAssert#isEqualTo(LocalDate)
   * @see org.assertj.db.api.AbstractAssertWithValues#isEqualTo(LocalDate)
   */
  T isEqualTo(LocalDate expected);

  /**
   * Verifies that the value is equal to a time value.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is equal to a time value :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(table).row().value().isEqualTo(LocalTime.of(21, 29, 30));
   * </code>
   * </pre>
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the {@code Row} at end point
   * of the first {@code Change} is equal to a time value :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change().rowAtEndPoint().value().isEqualTo(LocalTime.of(21, 29, 30));
   * </code>
   * </pre>
   *
   * @param expected The expected time value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not equal to the time value in parameter.
   * @see org.assertj.db.api.AbstractValueAssert#isEqualTo(LocalTime)
   * @see org.assertj.db.api.AbstractAssertWithValues#isEqualTo(LocalTime)
   */
  T isEqualTo(LocalTime expected);

  /**
   * Verifies that the value is equal to a date/time value.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is equal to a date/time value :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(table).row().value().isEqualTo(LocalDateTime.of(2014, 7, 7, 21, 29));
   * </code>
   * </pre>
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the {@code Row} at end point
   * of the first {@code Change} is equal to a date/time value :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change().rowAtEndPoint().value().isEqualTo(LocalDateTime.of(2014, 7, 7, 21, 29));
   * </code>
   * </pre>
   *
   * @param expected The expected date/time value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not equal to the date/time value in parameter.
   * @see org.assertj.db.api.AbstractValueAssert#isEqualTo(LocalDateTime)
   * @see org.assertj.db.api.AbstractAssertWithValues#isEqualTo(LocalDateTime)
   */
  T isEqualTo(LocalDateTime expected);

  /**
   * Verifies that the value is equal to zero.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is equal to zero :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(table).row().value().isZero();
   * </code>
   * </pre>
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the {@code Row} at end point
   * of the first {@code Change} is equal to zero :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change().rowAtEndPoint().value().isZero();
   * </code>
   * </pre>
   *
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not equal to zero.
   * @see org.assertj.db.api.AbstractValueAssert#isZero()
   * @see org.assertj.db.api.AbstractAssertWithValues#isZero()
   */
  T isZero();
}
