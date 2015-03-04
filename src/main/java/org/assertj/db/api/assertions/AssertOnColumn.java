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
import org.assertj.db.type.ValueType;

/**
 * Interface that represents a assert on a column.
 *
 * @param <T> The "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/anMa4g"
 *            target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *            for more details.
 * @author Régis Pouiller
 */
public interface AssertOnColumn <T extends AssertOnColumn<T>> {

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
   * @throws AssertionError If the type is different to the type in parameter.
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
   * assertThat(table).column(&quot;title&quot;).isOfAnyOfTypes(ValueType.TEXT, ValueType.NUMBER);
   * </code></pre>
   *
   * @param expected The expected types to compare to.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type is different to all the types in parameters.
   */
  public T isOfAnyOfTypes(ValueType... expected);

  /**
   * Verifies that the type of the values of the column is a number.
   * <p>
   * Example where the assertion verifies that all the values in the {@code Column} called "year"
   * of the {@code Table} is a number :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).column(&quot;year&quot;).isNumber(true);
   * </code></pre>
   *
   * @param lenient {@code true} if the test is lenient : if the type of a value is not identified (for example when the
   *          value is {@code null}), it consider that it is ok.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type is not a number.
   */
  public T isNumber(boolean lenient);

  /**
   * Verifies that the type of the values of the column is a boolean.
   * <p>
   * Example where the assertion verifies that all the values in the first {@code Column} of
   * the {@code Table} is a boolean :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).column().isBoolean(false);
   * </code></pre>
   *
   * @param lenient {@code true} if the test is lenient : if the type of a value is not identified (for example when the
   *          value is {@code null}), it consider that it is ok.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type is not a number.
   */
  public T isBoolean(boolean lenient);

  /**
   * Verifies that the type of the values of the column is a date.
   * <p>
   * Example where the assertion verifies that all the values in the {@code Column} called "birth"
   * of the {@code Table} is a date :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).column(&quot;birth&quot;).isDate(false);
   * </code></pre>
   *
   * @param lenient {@code true} if the test is lenient : if the type of a value is not identified (for example when the
   *          value is {@code null}), it consider that it is ok.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type is not a number.
   */
  public T isDate(boolean lenient);

  /**
   * Verifies that the type of the values of the column is a time.
   * <p>
   * Example where the assertion verifies that all the values in the first {@code Column} of
   * the {@code Table} is a time :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).column().isTime(false);
   * </code></pre>
   *
   * @param lenient {@code true} if the test is lenient : if the type of a value is not identified (for example when the
   *          value is {@code null}), it consider that it is ok.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type is not a number.
   */
  public T isTime(boolean lenient);

  /**
   * Verifies that the type of the values of the column is a date/time.
   * <p>
   * Example where the assertion verifies that all the values in the first {@code Column} of
   * the {@code Table} is a date/time :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).column().isDateTime(false);
   * </code></pre>
   *
   * @param lenient {@code true} if the test is lenient : if the type of a value is not identified (for example when the
   *          value is {@code null}), it consider that it is ok.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type is not a number.
   */
  public T isDateTime(boolean lenient);

  /**
   * Verifies that the type of the values of the column is a array of bytes.
   * <p>
   * Example where the assertion verifies that all the values in the first {@code Column} of
   * the {@code Table} is a array of bytes :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).column().isBytes(false);
   * </code></pre>
   *
   * @param lenient {@code true} if the test is lenient : if the type of a value is not identified (for example when the
   *          value is {@code null}), it consider that it is ok.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type is not a number.
   */
  public T isBytes(boolean lenient);

  /**
   * Verifies that the type of the values of the column is a text.
   * <p>
   * Example where the assertion verifies that all the values in the {@code Column} called "title"
   * of the {@code Table} is a text :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).column(&quot;title&quot;).isText(false);
   * </code></pre>
   *
   * @param lenient {@code true} if the test is lenient : if the type of a value is not identified (for example when the
   *          value is {@code null}), it consider that it is ok.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type is not a number.
   */
  public T isText(boolean lenient);

  /**
   * Verifies that all the values of the column are {@code null}.
   * <p>
   * Example where the assertion verifies that all the values in the first {@code Column} of the {@code Table} are
   * {@code null} :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).column().hasOnlyNullValues();
   * </code></pre>
   *
   * @return {@code this} assertion object.
   * @throws AssertionError If at least one of the values of the column are not {@code null}.
   */
  public T hasOnlyNullValues();

  /**
   * Verifies that all the values of the column are not {@code null}.
   * <p>
   * Example where the assertion verifies that all the values in the first {@code Column} of the {@code Table} are not
   * {@code null} :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).column().hasOnlyNotNullValues();
   * </code></pre>
   *
   * @return {@code this} assertion object.
   * @throws AssertionError If at least one of the values of the column are {@code null}.
   */
  public T hasOnlyNotNullValues();

  /**
   * Verifies that the values of a column are equal to booleans.
   * <p>
   * Example where the assertion verifies that the values in the first {@code Column} of the {@code Table} are equal to
   * the booleans in parameter :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).column().hasValuesEqualTo(true, false, true);
   * </code></pre>
   *
   * @param expected The expected boolean values.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not equal to the booleans in parameter.
   */
  public T hasValuesEqualTo(Boolean... expected);

  /**
   * Verifies that the values of a column are equal to numbers.
   * <p>
   * Example where the assertion verifies that the values in the first {@code Column} of the {@code Table} are equal to
   * the numbers in parameter :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).column().hasValuesEqualTo(5, 10.5, 6);
   * </code></pre>
   *
   * @param expected The expected numbers values.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not equal to the numbers in parameter.
   */
  public T hasValuesEqualTo(Number... expected);

  /**
   * Verifies that the values of a column are equal to bytes.
   * <p>
   * Example where the assertion verifies that the values in the first {@code Column} of the {@code Table} are equal to
   * arrays of bytes loaded from files in the classpath :
   * </p>
   *
   * <pre><code class='java'>
   * byte[] bytes1 = bytesContentFromClassPathOf(&quot;file1.png&quot;);
   * byte[] bytes2 = bytesContentFromClassPathOf(&quot;file2.png&quot;);
   * assertThat(table).column().hasValuesEqualTo(bytes1, bytes2);
   * </code></pre>
   *
   * @param expected The expected bytes values.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not equal to the bytes in parameter.
   */
  public T hasValuesEqualTo(byte[]... expected);

  /**
   * Verifies that the values of a column are equal to texts.
   * <p>
   * Example where the assertion verifies that the values in the first {@code Column} of the {@code Table} are equal to
   * the texts in parameter :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).column().hasValuesEqualTo(&quot;text&quot;, &quot;text2&quot;, &quot;text3&quot;);
   * </code></pre>
   *
   * @param expected The expected text values.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not equal to the texts in parameter.
   */
  public T hasValuesEqualTo(String... expected);

  /**
   * Verifies that the values of a column are equal to date values.
   * <p>
   * Example where the assertion verifies that the values in the first {@code Column} of the {@code Table} are equal to
   * the date values in parameter :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).column().hasValuesEqualTo(DateValue.of(2014, 7, 7), DateValue.of(2014, 10, 3),
   *     DateValue.of(2014, 12, 23));
   * </code></pre>
   *
   * @param expected The expected date values.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not equal to the date values in parameter.
   */
  public T hasValuesEqualTo(DateValue... expected);

  /**
   * Verifies that the values of a column are equal to time values.
   * <p>
   * Example where the assertion verifies that the values in the first {@code Column} of the {@code Table} are equal to
   * the time values in parameter :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).column().hasValuesEqualTo(TimeValue.of(21, 29, 30), TimeValue.of(10, 1, 25), TimeValue.of(9, 1));
   * </code></pre>
   *
   * @param expected The expected time values.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not equal to the time values in parameter.
   */
  public T hasValuesEqualTo(TimeValue... expected);

  /**
   * Verifies that the values of a column are equal to date/time values.
   * <p>
   * Example where the assertion verifies that the values in the first {@code Column} of the {@code Table} are equal to
   * the date/time values in parameter :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).column().hasValuesEqualTo(DateTimeValue.of(DateValue.of(2014, 7, 7), TimeValue.of(21, 29)),
   *     DateTimeValue.of(DateValue.of(2014, 7, 7), TimeValue.of(10, 1, 25)),
   *     DateTimeValue.of(DateValue.of(2014, 7, 7), TimeValue.of(9, 1)));
   * </code></pre>
   *
   * @param expected The expected date/time values.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not equal to the date/time values in parameter.
   */
  public T hasValuesEqualTo(DateTimeValue... expected);

  /**
   * Verifies that the name of a column is equal to parameter.
   * <p>
   * Example where the assertion verifies that the column name of the first {@code Column} of the {@code Table} is equal to
   * "title" :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).column().hasColumnName("title");
   * </code></pre>
   *
   * @param columnName The expected column name.
   * @return {@code this} assertion object.
   * @throws AssertionError If the column name is not equal to the parameter.
   */
  public T hasColumnName(String columnName);
}
