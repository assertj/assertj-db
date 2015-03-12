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
 * Interface that represents a assert on the equality of a value.
 *
 * @param <T> The "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/anMa4g"
 *            target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *            for more details.
 * @author Régis Pouiller
 */
public interface AssertOnValueEquality<T extends AssertOnValueEquality<T>> {

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
   */
  public T isEqualTo(Boolean expected);

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
   */
  public T isTrue();

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
   */
  public T isFalse();

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
   */
  public T isEqualTo(Number expected);

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
   */
  public T isEqualTo(byte[] expected);

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
   */
  public T isEqualTo(String expected);

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
   */
  public T isEqualTo(DateValue expected);

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
   */
  public T isEqualTo(TimeValue expected);

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
   */
  public T isEqualTo(DateTimeValue expected);

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
   */
  public T isZero();
}
