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

import org.assertj.db.type.ValueType;

/**
 * Defines the assertion methods on the type of a column.
 * <p>The different type of values are enumerated in {@link org.assertj.db.type.ValueType}.</p>
 *
 * @param <T> The "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/anMa4g"
 *            target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *            for more details.
 * @author Régis Pouiller
 */
public interface AssertOnColumnType<T extends AssertOnColumnType<T>> {

  /**
   * Verifies that the type of the values of the column is equal to the type in parameter.
   * <p>
   * Example where the assertion verifies that all the values in the {@code Column} called "title" of the {@code Table}
   * is of type {@code TEXT} :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).column(&quot;title&quot;).isOfType(ValueType.TEXT, false);
   * </code></pre>
   * <p>
   * Example where the assertion verifies that all the values in the {@code Column} called "title" of the {@code Table}
   * is of type {@code TEXT} or not identified (for example {@code null}) :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).column(&quot;title&quot;).isOfType(ValueType.TEXT, true);
   * </code></pre>
   *
   * @param expected The expected type to compare to.
   * @param lenient {@code true} if the test is lenient : if the type of a value is not identified (for example when the
   *          value is {@code null}), it consider that it is ok.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type of the column is different to the type in parameter.
   * @see org.assertj.db.api.AbstractColumnAssert#isOfType(org.assertj.db.type.ValueType, boolean)
   */
  public T isOfType(ValueType expected, boolean lenient);

  /**
   * Verifies that the type of the column is equal to one of the types in parameters.
   * <p>
   * Example where the assertion verifies that the values in the {@code Column} called "title" of the {@code Table} is
   * of type {@code TEXT} or of type {@code NUMBER} :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).column(&quot;title&quot;).isOfAnyTypeIn(ValueType.TEXT, ValueType.NUMBER);
   * </code></pre>
   *
   * @param expected The expected types to compare to.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type of the column is different to all the types in parameters.
   * @see org.assertj.db.api.AbstractColumnAssert#isOfAnyTypeIn(org.assertj.db.type.ValueType...)
   */
  public T isOfAnyTypeIn(ValueType... expected);

  /**
   * Verifies that the type of the values of the column is number.
   * <p>
   * Example where the assertion verifies that all the values in the {@code Column} called "year"
   * of the {@code Table} is a number :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).column(&quot;year&quot;).isNumber(true);
   * </code></pre>
   * <p>
   * This assertion method is equivalent to :
   * </p>
   * <pre>
   * <code class='java'>
   * xxxxx.isOfType(ValueType.NUMBER, lenient);
   * </code>
   * </pre>
   *
   * @param lenient {@code true} if the test is lenient : if the type of a value is not identified (for example when the
   *          value is {@code null}), it consider that it is ok.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type of the column is not number.
   * @see org.assertj.db.type.ValueType#NUMBER
   * @see org.assertj.db.api.AbstractColumnAssert#isNumber(boolean)
   */
  public T isNumber(boolean lenient);

  /**
   * Verifies that the type of the values of the column is boolean.
   * <p>
   * Example where the assertion verifies that all the values in the first {@code Column} of
   * the {@code Table} is a boolean :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).column().isBoolean(false);
   * </code></pre>
   * <p>
   * This assertion method is equivalent to :
   * </p>
   * <pre>
   * <code class='java'>
   * xxxxx.isOfType(ValueType.BOOLEAN, lenient);
   * </code>
   * </pre>
   *
   * @param lenient {@code true} if the test is lenient : if the type of a value is not identified (for example when the
   *          value is {@code null}), it consider that it is ok.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type of the column is not boolean.
   * @see org.assertj.db.type.ValueType#BOOLEAN
   * @see org.assertj.db.api.AbstractColumnAssert#isBoolean(boolean)
   */
  public T isBoolean(boolean lenient);

  /**
   * Verifies that the type of the values of the column is date.
   * <p>
   * Example where the assertion verifies that all the values in the {@code Column} called "birth"
   * of the {@code Table} is a date :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).column(&quot;birth&quot;).isDate(false);
   * </code></pre>
   * <p>
   * This assertion method is equivalent to :
   * </p>
   * <pre>
   * <code class='java'>
   * xxxxx.isOfType(ValueType.DATE, lenient);
   * </code>
   * </pre>
   *
   * @param lenient {@code true} if the test is lenient : if the type of a value is not identified (for example when the
   *          value is {@code null}), it consider that it is ok.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type of the column is not date.
   * @see org.assertj.db.type.ValueType#DATE
   * @see org.assertj.db.api.AbstractColumnAssert#isDate(boolean)
   */
  public T isDate(boolean lenient);

  /**
   * Verifies that the type of the values of the column is time.
   * <p>
   * Example where the assertion verifies that all the values in the first {@code Column} of
   * the {@code Table} is a time :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).column().isTime(false);
   * </code></pre>
   * <p>
   * This assertion method is equivalent to :
   * </p>
   * <pre>
   * <code class='java'>
   * xxxxx.isOfType(ValueType.TIME, lenient);
   * </code>
   * </pre>
   *
   * @param lenient {@code true} if the test is lenient : if the type of a value is not identified (for example when the
   *          value is {@code null}), it consider that it is ok.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type of the column is not time.
   * @see org.assertj.db.type.ValueType#TIME
   * @see org.assertj.db.api.AbstractColumnAssert#isTime(boolean)
   */
  public T isTime(boolean lenient);

  /**
   * Verifies that the type of the values of the column is date/time.
   * <p>
   * Example where the assertion verifies that all the values in the first {@code Column} of
   * the {@code Table} is a date/time :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).column().isDateTime(false);
   * </code></pre>
   * <p>
   * This assertion method is equivalent to :
   * </p>
   * <pre>
   * <code class='java'>
   * xxxxx.isOfType(ValueType.DATE_TIME, lenient);
   * </code>
   * </pre>
   *
   * @param lenient {@code true} if the test is lenient : if the type of a value is not identified (for example when the
   *          value is {@code null}), it consider that it is ok.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type of the column is not date/time.
   * @see org.assertj.db.type.ValueType#DATE_TIME
   * @see org.assertj.db.api.AbstractColumnAssert#isDateTime(boolean)
   */
  public T isDateTime(boolean lenient);

  /**
   * Verifies that the type of the values of the column is array of bytes.
   * <p>
   * Example where the assertion verifies that all the values in the first {@code Column} of
   * the {@code Table} is a array of bytes :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).column().isBytes(false);
   * </code></pre>
   * <p>
   * This assertion method is equivalent to :
   * </p>
   * <pre>
   * <code class='java'>
   * xxxxx.isOfType(ValueType.BYTES, lenient);
   * </code>
   * </pre>
   *
   * @param lenient {@code true} if the test is lenient : if the type of a value is not identified (for example when the
   *          value is {@code null}), it consider that it is ok.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type of the column is not array of bytes.
   * @see org.assertj.db.type.ValueType#BYTES
   * @see org.assertj.db.api.AbstractColumnAssert#isBytes(boolean)
   */
  public T isBytes(boolean lenient);

  /**
   * Verifies that the type of the values of the column is text.
   * <p>
   * Example where the assertion verifies that all the values in the {@code Column} called "title"
   * of the {@code Table} is a text :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).column(&quot;title&quot;).isText(false);
   * </code></pre>
   * <p>
   * This assertion method is equivalent to :
   * </p>
   * <pre>
   * <code class='java'>
   * xxxxx.isOfType(ValueType.TEXT, lenient);
   * </code>
   * </pre>
   *
   * @param lenient {@code true} if the test is lenient : if the type of a value is not identified (for example when the
   *          value is {@code null}), it consider that it is ok.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type of the column is not text.
   * @see org.assertj.db.type.ValueType#TEXT
   * @see org.assertj.db.api.AbstractColumnAssert#isText(boolean)
   */
  public T isText(boolean lenient);
}
