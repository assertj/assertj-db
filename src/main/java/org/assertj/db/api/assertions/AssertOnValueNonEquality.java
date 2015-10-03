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
package org.assertj.db.api.assertions;

import org.assertj.db.type.DateTimeValue;
import org.assertj.db.type.DateValue;
import org.assertj.db.type.TimeValue;

import java.util.UUID;

/**
 * Defines the assertion methods on the non equality of a value.
 *
 * @param <T> The "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/1IZIRcY"
 *            target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *            for more details.
 * @author Régis Pouiller
 * @author Otoniel Isidoro
 */
public interface AssertOnValueNonEquality<T extends AssertOnValueNonEquality<T>> {

  /**
   * Verifies that the value is not equal to a object.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is not equal to true french Locale :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(table).row().value().isNotEqualTo(Locale.FRENCH);
   * </code>
   * </pre>
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the {@code Row} at end point
   * of the first {@code Change} is not equal to french Locale :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change().rowAtEndPoint().value().isNotEqualTo(Locale.FRENCH);
   * </code>
   * </pre>
   *
   * @param expected The expected object value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is equal to the object in parameter.
   * @see org.assertj.db.api.AbstractValueAssert#isNotEqualTo(Object)
   * @see org.assertj.db.api.AbstractAssertWithValues#isNotEqualTo(Object)
   */
  T isNotEqualTo(Object expected);

  /**
   * Verifies that the value is not equal to a boolean.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is not equal to true boolean :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(table).row().value().isNotEqualTo(true);
   * </code>
   * </pre>
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the {@code Row} at end point
   * of the first {@code Change} is not equal to true boolean :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change().rowAtEndPoint().value().isNotEqualTo(true);
   * </code>
   * </pre>
   *
   * @param expected The expected boolean value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is equal to the boolean in parameter.
   * @see org.assertj.db.api.AbstractValueAssert#isNotEqualTo(Boolean)
   * @see org.assertj.db.api.AbstractAssertWithValues#isNotEqualTo(Boolean)
   */
  T isNotEqualTo(Boolean expected);

  /**
   * Verifies that the value is not equal to a array of bytes.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is not equal to a array of bytes loaded from a file in the classpath :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * byte[] bytes = bytesContentFromClassPathOf(&quot;file.png&quot;);
   * assertThat(table).row().value().isNotEqualTo(bytes);
   * </code>
   * </pre>
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the {@code Row} at end point
   * of the first {@code Change} is not equal to a array of bytes loaded from a file in the classpath :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * byte[] bytes = bytesContentFromClassPathOf(&quot;file.png&quot;);
   * assertThat(changes).change().rowAtEndPoint().value().isNotEqualTo(bytes);
   * </code>
   * </pre>
   *
   * @param expected The expected array of bytes value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is equal to the array of bytes in parameter.
   * @see org.assertj.db.api.AbstractValueAssert#isNotEqualTo(byte[])
   * @see org.assertj.db.api.AbstractAssertWithValues#isNotEqualTo(byte[])
   */
  T isNotEqualTo(byte[] expected);

  /**
   * Verifies that the value is not equal to a date/time value.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is not equal to a date/time value :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(table).row().value().isNotEqualTo(DateTimeValue.of(DateValue.of(2014, 7, 7), TimeValue.of(21, 29)));
   * </code>
   * </pre>
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the {@code Row} at end point
   * of the first {@code Change} is not equal to a date/time value :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change().rowAtEndPoint().value().isNotEqualTo(DateTimeValue.of(DateValue.of(2014, 7, 7), TimeValue.of(21, 29)));
   * </code>
   * </pre>
   *
   * @param expected The expected date/time value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is equal to the date/time value in parameter.
   * @see org.assertj.db.api.AbstractValueAssert#isNotEqualTo(DateTimeValue)
   * @see org.assertj.db.api.AbstractAssertWithValues#isNotEqualTo(DateTimeValue)
   */
  T isNotEqualTo(DateTimeValue expected);

  /**
   * Verifies that the value is not equal to a date value.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is not equal to a date value :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(table).row().value().isNotEqualTo(DateValue.of(2014, 7, 7));
   * </code>
   * </pre>
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the {@code Row} at end point
   * of the first {@code Change} is not equal to a date value :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change().rowAtEndPoint().value().isNotEqualTo(DateValue.of(2014, 7, 7));
   * </code>
   * </pre>
   *
   * @param expected The expected date value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is equal to the date value in parameter.
   * @see org.assertj.db.api.AbstractValueAssert#isNotEqualTo(DateValue)
   * @see org.assertj.db.api.AbstractAssertWithValues#isNotEqualTo(DateValue)
   */
  T isNotEqualTo(DateValue expected);

  /**
   * Verifies that the value is not equal to a number.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is not equal to number 3 :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(table).row().value().isNotEqualTo(3);
   * </code>
   * </pre>
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the {@code Row} at end point
   * of the first {@code Change} is not equal to number 3 :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change().rowAtEndPoint().value().isNotEqualTo(3);
   * </code>
   * </pre>
   *
   * @param expected The expected number value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is equal to the number in parameter.
   * @see org.assertj.db.api.AbstractValueAssert#isNotEqualTo(Number)
   * @see org.assertj.db.api.AbstractAssertWithValues#isNotEqualTo(Number)
   */
  T isNotEqualTo(Number expected);

  /**
   * Verifies that the value is not equal to a text.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is not equal to a text :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(table).row().value().isNotEqualTo(&quot;text&quot;);
   * </code>
   * </pre>
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the {@code Row} at end point
   * of the first {@code Change} is not equal to a text :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change().rowAtEndPoint().value().isNotEqualTo(&quot;text&quot;);
   * </code>
   * </pre>
   *
   * @param expected The expected text value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is equal to the text in parameter.
   * @see org.assertj.db.api.AbstractValueAssert#isNotEqualTo(String)
   * @see org.assertj.db.api.AbstractAssertWithValues#isNotEqualTo(String)
   */
  T isNotEqualTo(String expected);

  /**
   * Verifies that the value is not equal to an UUID.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is not equal to an UUID :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(table).row().value().isNotEqualTo(UUID.fromString(&quot;30B443AE-C0C9-4790-9BEC-CE1380808435&quot;));
   * </code>
   * </pre>
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the {@code Row} at end point
   * of the first {@code Change} is not equal to an UUID :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change().rowAtEndPoint().value().isNotEqualTo(UUID.fromString(&quot;30B443AE-C0C9-4790-9BEC-CE1380808435&quot;));
   * </code>
   * </pre>
   *
   * @param expected The expected UUID value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is equal to the UUID in parameter.
   * @see org.assertj.db.api.AbstractValueAssert#isNotEqualTo(UUID)
   * @see org.assertj.db.api.AbstractAssertWithValues#isNotEqualTo(UUID)
   * @since 1.1.0
   */
  T isNotEqualTo(UUID expected);

  /**
   * Verifies that the value is not equal to a time value.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is not equal to a time value :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(table).row().value().isNotEqualTo(TimeValue.of(21, 29, 30));
   * </code>
   * </pre>
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the {@code Row} at end point
   * of the first {@code Change} is not equal to a time value :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change().rowAtEndPoint().value().isNotEqualTo(TimeValue.of(21, 29, 30));
   * </code>
   * </pre>
   *
   * @param expected The expected time value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is equal to the time value in parameter.
   * @see org.assertj.db.api.AbstractValueAssert#isNotEqualTo(TimeValue)
   * @see org.assertj.db.api.AbstractAssertWithValues#isNotEqualTo(TimeValue)
   */
  T isNotEqualTo(TimeValue expected);

  /**
   * Verifies that the value is not equal to zero.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is not equal to zero :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(table).row().value().isNotZero();
   * </code>
   * </pre>
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the {@code Row} at end point
   * of the first {@code Change} is not equal to zero :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change().rowAtEndPoint().value().isNotZero();
   * </code>
   * </pre>
   *
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is equal to zero.
   * @see org.assertj.db.api.AbstractValueAssert#isNotZero()
   * @see org.assertj.db.api.AbstractAssertWithValues#isNotZero()
   */
  T isNotZero();
}
