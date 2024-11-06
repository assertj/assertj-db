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

import org.assertj.db.type.ValueType;

/**
 * Defines the assertion methods on the type of a value.
 * <p>The different type of values are enumerated in {@link org.assertj.db.type.ValueType}.</p>
 *
 * @param <T> The "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/1IZIRcY"
 *            target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *            for more details.
 * @author Régis Pouiller
 * @author Otoniel Isidoro
 */
public interface AssertOnValueType<T extends AssertOnValueType<T>> {

  /**
   * Verifies that the type of the value is equal to the type in parameter.
   * <p>
   * Example where the assertion verifies that the value in the {@code Column} called "title" of the second {@code Row}
   * of the {@code Table} is of type {@code TEXT} :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(table).row(1).value(&quot;title&quot;).isOfType(ValueType.TEXT);
   * </code>
   * </pre>
   * <p>
   * Example where the assertion verifies that the value in the {@code Column} called "title" of the {@code Row} at end point
   * of the first {@code Change} is of type {@code TEXT} :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change().rowAtEndPoint().value(&quot;title&quot;).isOfType(ValueType.TEXT);
   * </code>
   * </pre>
   *
   * @param expected The expected type to compare to.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type of the value is different to the type in parameter.
   * @see org.assertj.db.api.AbstractValueAssert#isOfType(org.assertj.db.type.ValueType)
   * @see org.assertj.db.api.AbstractAssertWithValues#isOfType(org.assertj.db.type.ValueType)
   */
  T isOfType(ValueType expected);

  /**
   * Verifies that the type of the value is equal to one of the types in parameters.
   * <p>
   * Example where the assertion verifies that the value in the {@code Column} called "title" of the second {@code Row}
   * of the {@code Table} is of type {@code TEXT} or of type {@code NUMBER} :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(table).row(1).value(&quot;title&quot;).isOfType(ValueType.TEXT, ValueType.NUMBER);
   * </code>
   * </pre>
   * <p>
   * Example where the assertion verifies that the value in the {@code Column} called "title" of the {@code Row} at end point
   * of the first {@code Change} is of type {@code TEXT} or of type {@code NUMBER} :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change().rowAtEndPoint().value(&quot;title&quot;).isOfType(ValueType.TEXT, ValueType.NUMBER);
   * </code>
   * </pre>
   *
   * @param expected The expected types to compare to.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type of the value is different to all the types in parameters.
   * @see org.assertj.db.api.AbstractValueAssert#isOfAnyTypeIn(org.assertj.db.type.ValueType...)
   * @see org.assertj.db.api.AbstractAssertWithValues#isOfAnyTypeIn(org.assertj.db.type.ValueType...)
   */
  T isOfAnyTypeIn(ValueType... expected);

  /**
   * Verifies that the value is a number.
   * <p>
   * Example where the assertion verifies that the value in the {@code Column} called "year" of the first {@code Row} of
   * the {@code Table} is a number :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(table).row().value(&quot;year&quot;).isNumber();
   * </code>
   * </pre>
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the {@code Row} at end point
   * of the first {@code Change} is a number :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change().rowAtEndPoint().value().isNumber();
   * </code>
   * </pre>
   * <p>
   * This assertion method is equivalent to :
   * </p>
   * <pre>
   * <code class='java'>
   * xxxxx.isOfType(ValueType.NUMBER);
   * </code>
   * </pre>
   *
   * @return {@code this} assertion object.
   * @throws AssertionError If the type of the value is not number.
   * @see org.assertj.db.type.ValueType#NUMBER
   * @see org.assertj.db.api.AbstractValueAssert#isNumber()
   * @see org.assertj.db.api.AbstractAssertWithValues#isNumber()
   */
  T isNumber();

  /**
   * Verifies that the value is a boolean.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is a boolean :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(table).row().value().isBoolean();
   * </code>
   * </pre>
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the {@code Row} at end point
   * of the first {@code Change} is a boolean :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change().rowAtEndPoint().value().isBoolean();
   * </code>
   * </pre>
   * <p>
   * This assertion method is equivalent to :
   * </p>
   * <pre>
   * <code class='java'>
   * xxxxx.isOfType(ValueType.BOOLEAN);
   * </code>
   * </pre>
   *
   * @return {@code this} assertion object.
   * @throws AssertionError If the type of the value is not boolean.
   * @see org.assertj.db.type.ValueType#BOOLEAN
   * @see org.assertj.db.api.AbstractValueAssert#isBoolean()
   * @see org.assertj.db.api.AbstractAssertWithValues#isBoolean()
   */
  T isBoolean();

  /**
   * Verifies that the value is a date.
   * <p>
   * Example where the assertion verifies that the value in the {@code Column} called "birth" of the first {@code Row}
   * of the {@code Table} is a date :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(table).row().value(&quot;birth&quot;).isDate();
   * </code>
   * </pre>
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the {@code Row} at end point
   * of the first {@code Change} is a date :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change().rowAtEndPoint().value().isDate();
   * </code>
   * </pre>
   * <p>
   * This assertion method is equivalent to :
   * </p>
   * <pre>
   * <code class='java'>
   * xxxxx.isOfType(ValueType.DATE);
   * </code>
   * </pre>
   *
   * @return {@code this} assertion object.
   * @throws AssertionError If the type of the value is not date.
   * @see org.assertj.db.type.ValueType#DATE
   * @see org.assertj.db.api.AbstractValueAssert#isDate()
   * @see org.assertj.db.api.AbstractAssertWithValues#isDate()
   */
  T isDate();

  /**
   * Verifies that the value is a time.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is a time :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(table).row().value().isTime();
   * </code>
   * </pre>
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the {@code Row} at end point
   * of the first {@code Change} is a time :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change().rowAtEndPoint().value().isTime();
   * </code>
   * </pre>
   * <p>
   * This assertion method is equivalent to :
   * </p>
   * <pre>
   * <code class='java'>
   * xxxxx.isOfType(ValueType.TIME);
   * </code>
   * </pre>
   *
   * @return {@code this} assertion object.
   * @throws AssertionError If the type of the value is not time.
   * @see org.assertj.db.type.ValueType#TIME
   * @see org.assertj.db.api.AbstractValueAssert#isTime()
   * @see org.assertj.db.api.AbstractAssertWithValues#isTime()
   */
  T isTime();

  /**
   * Verifies that the value is a date/time.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is a date/time :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(table).row().value().isDateTime();
   * </code>
   * </pre>
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the {@code Row} at end point
   * of the first {@code Change} is a date/time :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change().rowAtEndPoint().value().isDateTime();
   * </code>
   * </pre>
   * <p>
   * This assertion method is equivalent to :
   * </p>
   * <pre>
   * <code class='java'>
   * xxxxx.isOfType(ValueType.DATE_TIME);
   * </code>
   * </pre>
   *
   * @return {@code this} assertion object.
   * @throws AssertionError If the type of the value is not date/time.
   * @see org.assertj.db.type.ValueType#DATE_TIME
   * @see org.assertj.db.api.AbstractValueAssert#isDateTime()
   * @see org.assertj.db.api.AbstractAssertWithValues#isDateTime()
   */
  T isDateTime();

  /**
   * Verifies that the value is a array of bytes.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is a array of bytes :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(table).row().value().isBytes();
   * </code>
   * </pre>
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the {@code Row} at end point
   * of the first {@code Change} is a array of bytes :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change().rowAtEndPoint().value().isBytes();
   * </code>
   * </pre>
   * <p>
   * This assertion method is equivalent to :
   * </p>
   * <pre>
   * <code class='java'>
   * xxxxx.isOfType(ValueType.BYTES);
   * </code>
   * </pre>
   *
   * @return {@code this} assertion object.
   * @throws AssertionError If the type of the value is not array of bytes.
   * @see org.assertj.db.type.ValueType#BYTES
   * @see org.assertj.db.api.AbstractValueAssert#isBytes()
   * @see org.assertj.db.api.AbstractAssertWithValues#isBytes()
   */
  T isBytes();

  /**
   * Verifies that the value is a text.
   * <p>
   * Example where the assertion verifies that the value in the {@code Column} called "title" of the first {@code Row}
   * of the {@code Table} is a text :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(table).row().value(&quot;title&quot;).isText();
   * </code>
   * </pre>
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the {@code Row} at end point
   * of the first {@code Change} is a text :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change().rowAtEndPoint().value().isText();
   * </code>
   * </pre>
   * <p>
   * This assertion method is equivalent to :
   * </p>
   * <pre>
   * <code class='java'>
   * xxxxx.isOfType(ValueType.TEXT);
   * </code>
   * </pre>
   *
   * @return {@code this} assertion object.
   * @throws AssertionError If the type of the value is not text.
   * @see org.assertj.db.type.ValueType#TEXT
   * @see org.assertj.db.api.AbstractValueAssert#isText()
   * @see org.assertj.db.api.AbstractAssertWithValues#isText()
   */
  T isText();

  /**
   * Verifies that the value is an UUID.
   * <p>
   * Example where the assertion verifies that the value in the {@code Column} called id of the first {@code Row}
   * of the {@code Table} is an UUID :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(table).row().value(&quot;id&quot;).isUUID();
   * </code>
   * </pre>
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the {@code Row} at end point
   * of the first {@code Change} is an UUID :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change().rowAtEndPoint().value().isUUID();
   * </code>
   * </pre>
   * <p>
   * This assertion method is equivalent to :
   * </p>
   * <pre>
   * <code class='java'>
   * xxxxx.isOfType(ValueType.UUID);
   * </code>
   * </pre>
   *
   * @return {@code this} assertion object.
   * @throws AssertionError If the type of the value is not UUID.
   * @see org.assertj.db.type.ValueType#UUID
   * @see org.assertj.db.api.AbstractValueAssert#isUUID()
   * @see org.assertj.db.api.AbstractAssertWithValues#isUUID()
   * @since 1.1.0
   */
  T isUUID();

}
