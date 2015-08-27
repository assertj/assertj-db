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
 * Defines the assertion methods on the equality of a column.
 *
 * @param <T> The "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/1IZIRcY"
 *            target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *            for more details.
 * @author Régis Pouiller
 */
public interface AssertOnColumnEquality<T extends AssertOnColumnEquality<T>> {

  /**
   * Verifies that the values of a column are equal to booleans.
   * <p>
   * Example where the assertion verifies that the values in the first {@code Column} of the {@code Table} are equal to
   * the booleans in parameter :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).column().hasValues(true, false, true);
   * </code></pre>
   *
   * @param expected The expected boolean values.
   * @return {@code this} assertion object.
   * @throws AssertionError If the values of the column are not equal to the booleans in parameter.
   * @see org.assertj.db.api.AbstractColumnAssert#hasValues(Boolean...)
   */
  public T hasValues(Boolean... expected);

  /**
   * Verifies that the values of a column are equal to numbers.
   * <p>
   * Example where the assertion verifies that the values in the first {@code Column} of the {@code Table} are equal to
   * the numbers in parameter :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).column().hasValues(5, 10.5, 6);
   * </code></pre>
   *
   * @param expected The expected numbers values.
   * @return {@code this} assertion object.
   * @throws AssertionError If the values of the column are not equal to the numbers in parameter.
   * @see org.assertj.db.api.AbstractColumnAssert#hasValues(Number...)
   */
  public T hasValues(Number... expected);

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
   * assertThat(table).column().hasValues(bytes1, bytes2);
   * </code></pre>
   *
   * @param expected The expected bytes values.
   * @return {@code this} assertion object.
   * @throws AssertionError If the values of the column are not equal to the bytes in parameter.
   * @see org.assertj.db.api.AbstractColumnAssert#hasValues(byte[]...)
   */
  public T hasValues(byte[]... expected);

  /**
   * Verifies that the values of a column are equal to texts.
   * <p>
   * Example where the assertion verifies that the values in the first {@code Column} of the {@code Table} are equal to
   * the texts in parameter :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).column().hasValues(&quot;text&quot;, &quot;text2&quot;, &quot;text3&quot;);
   * </code></pre>
   *
   * @param expected The expected text values.
   * @return {@code this} assertion object.
   * @throws AssertionError If the values of the column are not equal to the texts in parameter.
   * @see org.assertj.db.api.AbstractColumnAssert#hasValues(String...)
   */
  public T hasValues(String... expected);


  /**
   * Verifies that the values of a column are equal to UUIDs.
   * <p>
   * Example where the assertion verifies that the values in the first {@code Column} of the {@code Table} are equal to
   * the UUIDs in parameter :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).column().hasValues(UUID.fromString(&quot;30B443AE-C0C9-4790-9BEC-CE1380808435&quot;),
   * &quot;16319617-AE95-4087-9264-D3D21BF611B6&quot;, &quot;D735221B-5DE5-4112-AA1E-49090CB75ADA&quot;);
   * </code></pre>
   *
   * @param expected The expected UUID values.
   * @return {@code this} assertion object.
   * @throws AssertionError If the values of the column are not equal to the UUIDs in parameter.
   * @see org.assertj.db.api.AbstractColumnAssert#hasValues(UUID...)
   */
  public T hasValues(UUID... expected);

  /**
   * Verifies that the values of a column are equal to date values.
   * <p>
   * Example where the assertion verifies that the values in the first {@code Column} of the {@code Table} are equal to
   * the date values in parameter :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).column().hasValues(DateValue.of(2014, 7, 7), DateValue.of(2014, 10, 3),
   *     DateValue.of(2014, 12, 23));
   * </code></pre>
   *
   * @param expected The expected date values.
   * @return {@code this} assertion object.
   * @throws AssertionError If the values of the column are not equal to the date values in parameter.
   * @see org.assertj.db.api.AbstractColumnAssert#hasValues(DateValue...)
   */
  public T hasValues(DateValue... expected);

  /**
   * Verifies that the values of a column are equal to time values.
   * <p>
   * Example where the assertion verifies that the values in the first {@code Column} of the {@code Table} are equal to
   * the time values in parameter :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).column().hasValues(TimeValue.of(21, 29, 30), TimeValue.of(10, 1, 25), TimeValue.of(9, 1));
   * </code></pre>
   *
   * @param expected The expected time values.
   * @return {@code this} assertion object.
   * @throws AssertionError If the values of the column are not equal to the time values in parameter.
   * @see org.assertj.db.api.AbstractColumnAssert#hasValues(TimeValue...)
   */
  public T hasValues(TimeValue... expected);

  /**
   * Verifies that the values of a column are equal to date/time values.
   * <p>
   * Example where the assertion verifies that the values in the first {@code Column} of the {@code Table} are equal to
   * the date/time values in parameter :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).column().hasValues(DateTimeValue.of(DateValue.of(2014, 7, 7), TimeValue.of(21, 29)),
   *     DateTimeValue.of(DateValue.of(2014, 7, 7), TimeValue.of(10, 1, 25)),
   *     DateTimeValue.of(DateValue.of(2014, 7, 7), TimeValue.of(9, 1)));
   * </code></pre>
   *
   * @param expected The expected date/time values.
   * @return {@code this} assertion object.
   * @throws AssertionError If the values of the column are not equal to the date/time values in parameter.
   * @see org.assertj.db.api.AbstractColumnAssert#hasValues(DateTimeValue...)
   */
  public T hasValues(DateTimeValue... expected);
}
