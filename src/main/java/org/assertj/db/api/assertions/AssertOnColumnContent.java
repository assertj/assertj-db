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
 * Defines the assertion methods on the content of a column.
 *
 * @param <T> The "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/1IZIRcY"
 *            target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *            for more details.
 * @author Régis Pouiller
 * @since 1.1.0
 */
public interface AssertOnColumnContent<T extends AssertOnColumnContent<T>> {

  /**
   * Verifies that the values of a column contains objects.
   * <p>
   * Example where the assertion verifies that the values in the first {@code Column} of the {@code Table} are containing
   * the objects in parameter :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).column().containsValues(Locale.FRENCH, Locale.ENGLISH, Locale.FRENCH);
   * </code></pre>
   *
   * @param expected The expected object values.
   * @return {@code this} assertion object.
   * @throws AssertionError If the column are not containing the objects in parameter.
   * @see org.assertj.db.api.AbstractColumnAssert#containsValues(Object...)
   */
  T containsValues(Object... expected);

  /**
   * Verifies that the values of a column contains booleans.
   * <p>
   * Example where the assertion verifies that the values in the first {@code Column} of the {@code Table} are containing
   * the booleans in parameter :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).column().containsValues(true, false, true);
   * </code></pre>
   *
   * @param expected The expected boolean values.
   * @return {@code this} assertion object.
   * @throws AssertionError If the column are not containing the booleans in parameter.
   * @see org.assertj.db.api.AbstractColumnAssert#containsValues(Boolean...)
   */
  T containsValues(Boolean... expected);

  /**
   * Verifies that the values of a column contains numbers.
   * <p>
   * Example where the assertion verifies that the values in the first {@code Column} of the {@code Table} are containing
   * the numbers in parameter :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).column().containsValues(5, 10.5, 6);
   * </code></pre>
   *
   * @param expected The expected numbers values.
   * @return {@code this} assertion object.
   * @throws AssertionError If the column are not containing the numbers in parameter.
   * @see org.assertj.db.api.AbstractColumnAssert#containsValues(Number...)
   */
  T containsValues(Number... expected);

  /**
   * Verifies that the values of a column contains bytes.
   * <p>
   * Example where the assertion verifies that the values in the first {@code Column} of the {@code Table} are containing
   * arrays of bytes loaded from files in the classpath :
   * </p>
   *
   * <pre><code class='java'>
   * byte[] bytes1 = bytesContentFromClassPathOf(&quot;file1.png&quot;);
   * byte[] bytes2 = bytesContentFromClassPathOf(&quot;file2.png&quot;);
   * assertThat(table).column().containsValues(bytes1, bytes2);
   * </code></pre>
   *
   * @param expected The expected bytes values.
   * @return {@code this} assertion object.
   * @throws AssertionError If the column are not containing the bytes in parameter.
   * @see org.assertj.db.api.AbstractColumnAssert#containsValues(byte[]...)
   */
  T containsValues(byte[]... expected);

  /**
   * Verifies that the values of a column contains texts.
   * <p>
   * Example where the assertion verifies that the values in the first {@code Column} of the {@code Table} are containing
   * the texts in parameter :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).column().containsValues(&quot;text&quot;, &quot;text2&quot;, &quot;text3&quot;);
   * </code></pre>
   *
   * @param expected The expected text values.
   * @return {@code this} assertion object.
   * @throws AssertionError If the column are not containing the texts in parameter.
   * @see org.assertj.db.api.AbstractColumnAssert#containsValues(String...)
   */
  T containsValues(String... expected);

  /**
   * Verifies that the values of a column containsUUIDs.
   * <p>
   * Example where the assertion verifies that the values in the first {@code Column} of the {@code Table} are containing
   * the UUIDs in parameter :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).column().containsValues(UUID.fromString(&quot;30B443AE-C0C9-4790-9BEC-CE1380808435&quot;),
   * UUID.fromString(&quot;16319617-AE95-4087-9264-D3D21BF611B6&quot;), UUID.fromString(&quot;D735221B-5DE5-4112-AA1E-49090CB75ADA&quot;));
   * </code></pre>
   *
   * @param expected The expected UUID values.
   * @return {@code this} assertion object.
   * @throws AssertionError If the column are not containing the UUIDs in parameter.
   * @see org.assertj.db.api.AbstractColumnAssert#containsValues(java.util.UUID...)
   */
  T containsValues(UUID... expected);

  /**
   * Verifies that the values of a column contains date values.
   * <p>
   * Example where the assertion verifies that the values in the first {@code Column} of the {@code Table} are containing
   * the date values in parameter :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).column().containsValues(DateValue.of(2014, 7, 7), DateValue.of(2014, 10, 3),
   *     DateValue.of(2014, 12, 23));
   * </code></pre>
   *
   * @param expected The expected date values.
   * @return {@code this} assertion object.
   * @throws AssertionError If the column are not containing the date values in parameter.
   * @see org.assertj.db.api.AbstractColumnAssert#containsValues(org.assertj.db.type.DateValue...)
   */
  T containsValues(DateValue... expected);

  /**
   * Verifies that the values of a column contains time values.
   * <p>
   * Example where the assertion verifies that the values in the first {@code Column} of the {@code Table} are containing
   * the time values in parameter :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).column().containsValues(TimeValue.of(21, 29, 30), TimeValue.of(10, 1, 25), TimeValue.of(9, 1));
   * </code></pre>
   *
   * @param expected The expected time values.
   * @return {@code this} assertion object.
   * @throws AssertionError If the column are not containing the time values in parameter.
   * @see org.assertj.db.api.AbstractColumnAssert#containsValues(org.assertj.db.type.TimeValue...)
   */
  T containsValues(TimeValue... expected);

  /**
   * Verifies that the values of a column contains date/time values.
   * <p>
   * Example where the assertion verifies that the values in the first {@code Column} of the {@code Table} are containing
   * the date/time values in parameter :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).column().containsValues(DateTimeValue.of(DateValue.of(2014, 7, 7), TimeValue.of(21, 29)),
   *     DateTimeValue.of(DateValue.of(2014, 7, 7), TimeValue.of(10, 1, 25)),
   *     DateTimeValue.of(DateValue.of(2014, 7, 7), TimeValue.of(9, 1)));
   * </code></pre>
   *
   * @param expected The expected date/time values.
   * @return {@code this} assertion object.
   * @throws AssertionError If the column are not containing the date/time values in parameter.
   * @see org.assertj.db.api.AbstractColumnAssert#containsValues(org.assertj.db.type.DateTimeValue...)
   */
  T containsValues(DateTimeValue... expected);
}
