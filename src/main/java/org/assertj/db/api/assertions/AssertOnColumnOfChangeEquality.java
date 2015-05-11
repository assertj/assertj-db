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

/**
 * Defines the assertion methods on the equality of a column of a change.
 *
 * @param <T> The "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/anMa4g"
 *            target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *            for more details.
 * @author Régis Pouiller
 */
public interface AssertOnColumnOfChangeEquality<T extends AssertOnColumnOfChangeEquality<T>> {

  /**
   * Verifies that the values at the start point and the end point are equal to a boolean.
   * <p>
   * Example where the assertion verifies that the values of the first {@code Column} of the {@code Table} are equal to
   * {@code true} :
   * </p>
   * <pre><code class='java'>
   * assertThat(changes).change(1).column().hasValues(true);
   * </code></pre>
   *
   * @param expected The expected boolean value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the values at start point and at end point are not equal to the boolean.
   * @see org.assertj.db.api.ChangeColumnAssert#hasValues(Boolean)
   */
  public T hasValues(Boolean expected);

  /**
   * Verifies that the values at the start point and the end point are equal to a boolean for start point and another boolean for end point.
   * <p>
   * Example where the assertion verifies that the values of the first {@code Column} of the {@code Table} are equal to
   * {@code true} at start point and {@code false} at end point :
   * </p>
   * <pre><code class='java'>
   * assertThat(changes).change(1).column().hasValues(true, false);
   * </code></pre>
   *
   * @param expectedAtStartPoint The expected boolean at start point.
   * @param expectedAtEndPoint   The expected boolean at end point.
   * @return {@code this} assertion object.
   * @throws AssertionError If the values at start point and at end point are not equal to the corresponding booleans.
   * @see org.assertj.db.api.ChangeColumnAssert#hasValues(Boolean, Boolean)
   */
  public T hasValues(Boolean expectedAtStartPoint, Boolean expectedAtEndPoint);

  /**
   * Verifies that the values at the start point and the end point are equal to a number.
   * <p>
   * Example where the assertion verifies that the values of the first {@code Column} of the {@code Table} are equal to
   * {@code 1} :
   * </p>
   * <pre><code class='java'>
   * assertThat(changes).change(1).column().hasValues(1);
   * </code></pre>
   *
   * @param expected The expected number value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the values at start point and at end point are not equal to the number.
   * @see org.assertj.db.api.ChangeColumnAssert#hasValues(Number)
   */
  public T hasValues(Number expected);

  /**
   * Verifies that the values at the start point and the end point are equal to a number for start point and another number for end point.
   * <p>
   * Example where the assertion verifies that the values of the first {@code Column} of the {@code Table} are equal to
   * {@code 1} at start point and {@code 2} at end point :
   * </p>
   * <pre><code class='java'>
   * assertThat(changes).change(1).column().hasValues(1, 2);
   * </code></pre>
   *
   * @param expectedAtStartPoint The expected number at start point.
   * @param expectedAtEndPoint   The expected number at end point.
   * @return {@code this} assertion object.
   * @throws AssertionError If the values at start point and at end point are not equal to the corresponding numbers.
   * @see org.assertj.db.api.ChangeColumnAssert#hasValues(Number, Number)
   */
  public T hasValues(Number expectedAtStartPoint, Number expectedAtEndPoint);

  /**
   * Verifies that the values at the start point and the end point are equal to bytes.
   * <p>
   * Example where the assertion verifies that the values of the first {@code Column} of the {@code Table} are equal to
   * a array of bytes loaded from a file in the classpath :
   * </p>
   * <pre><code class='java'>
   * byte[] bytes = bytesContentFromClassPathOf(&quot;file.png&quot;);
   * assertThat(changes).change(1).column().hasValues(bytes);
   * </code></pre>
   *
   * @param expected The expected bytes value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the values at start point and at end point are not equal to the bytes.
   * @see org.assertj.db.api.ChangeColumnAssert#hasValues(byte[])
   */
  public T hasValues(byte[] expected);

  /**
   * Verifies that the values at the start point and the end point are equal to bytes for start point and other bytes for end point.
   * <p>
   * Example where the assertion verifies that the values of the first {@code Column} of the {@code Table} are equal to
   * a array of bytes loaded from a file in the classpath at start point and another array at end point :
   * </p>
   * <pre><code class='java'>
   * byte[] bytes = bytesContentFromClassPathOf(&quot;file.png&quot;);
   * byte[] bytes2 = bytesContentFromClassPathOf(&quot;file2.png&quot;);
   * assertThat(changes).change(1).column().hasValues(bytes, bytes2);
   * </code></pre>
   *
   * @param expectedAtStartPoint The expected bytes at start point.
   * @param expectedAtEndPoint   The expected bytes at end point.
   * @return {@code this} assertion object.
   * @throws AssertionError If the values at start point and at end point are not equal to the corresponding bytes.
   * @see org.assertj.db.api.ChangeColumnAssert#hasValues(byte[], byte[])
   */
  public T hasValues(byte[] expectedAtStartPoint, byte[] expectedAtEndPoint);

  /**
   * Verifies that the values at the start point and the end point are equal to a text.
   * <p>
   * Example where the assertion verifies that the values of the first {@code Column} of the {@code Table} are equal to
   * "Ellen Louise Ripley" :
   * </p>
   * <pre><code class='java'>
   * assertThat(changes).change(1).column().hasValues("Ellen Louise Ripley");
   * </code></pre>
   *
   * @param expected The expected text value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the values at start point and at end point are not equal to the text.
   * @see org.assertj.db.api.ChangeColumnAssert#hasValues(String)
   */
  public T hasValues(String expected);

  /**
   * Verifies that the values at the start point and the end point are equal to a text for start point and another text for end point.
   * <p>
   * Example where the assertion verifies that the values of the first {@code Column} of the {@code Table} are equal to
   * "Sigourney" at start point and "Susan Alexandra" at end point :
   * </p>
   * <pre><code class='java'>
   * assertThat(changes).change(1).column().hasValues("Sigourney", "Susan Alexandra");
   * </code></pre>
   *
   * @param expectedAtStartPoint The expected text at start point.
   * @param expectedAtEndPoint   The expected text at end point.
   * @return {@code this} assertion object.
   * @throws AssertionError If the values at start point and at end point are not equal to the corresponding texts.
   * @see org.assertj.db.api.ChangeColumnAssert#hasValues(String, String)
   */
  public T hasValues(String expectedAtStartPoint, String expectedAtEndPoint);

  /**
   * Verifies that the values at the start point and the end point are equal to a date.
   * <p>
   * Example where the assertion verifies that the values of the first {@code Column} of the {@code Table} are equal to
   * 12/23/2007 :
   * </p>
   * <pre><code class='java'>
   * assertThat(changes).change(1).column().hasValues(DateValue.of(2007, 12, 23));
   * </code></pre>
   *
   * @param expected The expected date value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the values at start point and at end point are not equal to the date.
   * @see org.assertj.db.api.ChangeColumnAssert#hasValues(DateValue)
   */
  public T hasValues(DateValue expected);

  /**
   * Verifies that the values at the start point and the end point are equal to a date for start point and another date for end point.
   * <p>
   * Example where the assertion verifies that the values of the first {@code Column} of the {@code Table} are equal to
   * 12/23/2007 at start point and 07/25/2002 at end point :
   * </p>
   * <pre><code class='java'>
   * assertThat(changes).change(1).column().hasValues(DateValue.of(2007, 12, 23), DateValue.of(2002, 7, 25));
   * </code></pre>
   *
   * @param expectedAtStartPoint The expected date at start point.
   * @param expectedAtEndPoint   The expected date at end point.
   * @return {@code this} assertion object.
   * @throws AssertionError If the values at start point and at end point are not equal to the corresponding dates.
   * @see org.assertj.db.api.ChangeColumnAssert#hasValues(DateValue, DateValue)
   */
  public T hasValues(DateValue expectedAtStartPoint, DateValue expectedAtEndPoint);

  /**
   * Verifies that the values at the start point and the end point are equal to a time.
   * <p>
   * Example where the assertion verifies that the values of the first {@code Column} of the {@code Table} are equal to
   * 09:01AM :
   * </p>
   * <pre><code class='java'>
   * assertThat(changes).change(1).column().hasValues(TimeValue.of(9, 1));
   * </code></pre>
   *
   * @param expected The expected time value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the values at start point and at end point are not equal to the time.
   * @see org.assertj.db.api.ChangeColumnAssert#hasValues(TimeValue)
   */
  public T hasValues(TimeValue expected);

  /**
   * Verifies that the values at the start point and the end point are equal to a time for start point and another time for end point.
   * <p>
   * Example where the assertion verifies that the values of the first {@code Column} of the {@code Table} are equal to
   * 09:01AM at start point and 03:30AM at end point :
   * </p>
   * <pre><code class='java'>
   * assertThat(changes).change(1).column().hasValues(TimeValue.of(9, 1), TimeValue.of(3, 30));
   * </code></pre>
   *
   * @param expectedAtStartPoint The expected time at start point.
   * @param expectedAtEndPoint   The expected time at end point.
   * @return {@code this} assertion object.
   * @throws AssertionError If the values at start point and at end point are not equal to the corresponding times.
   * @see org.assertj.db.api.ChangeColumnAssert#hasValues(TimeValue, TimeValue)
   */
  public T hasValues(TimeValue expectedAtStartPoint, TimeValue expectedAtEndPoint);

  /**
   * Verifies that the values at the start point and the end point are equal to a date/time.
   * <p>
   * Example where the assertion verifies that the values of the first {@code Column} of the {@code Table} are equal to
   * 12/23/2007 09:01AM :
   * </p>
   * <pre><code class='java'>
   * assertThat(changes).change(1).column().hasValues(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)));
   * </code></pre>
   *
   * @param expected The expected date/time value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the values at start point and at end point are not equal to the date/time.
   * @see org.assertj.db.api.ChangeColumnAssert#hasValues(DateTimeValue)
   */
  public T hasValues(DateTimeValue expected);

  /**
   * Verifies that the values at the start point and the end point are equal to a date/time for start point and another date/time for end point.
   * <p>
   * Example where the assertion verifies that the values of the first {@code Column} of the {@code Table} are equal to
   * 12/23/2007 09:01AM at start point and 07/25/2002 00:00AM at end point :
   * </p>
   * <pre><code class='java'>
   * assertThat(changes).change(1).column().hasValues(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)), DateTimeValue.of(DateValue.of(2002, 7, 25)));
   * </code></pre>
   *
   * @param expectedAtStartPoint The expected date/time at start point.
   * @param expectedAtEndPoint   The expected date/time at end point.
   * @return {@code this} assertion object.
   * @throws AssertionError If the values at start point and at end point are not equal to the corresponding dates/times.
   * @see org.assertj.db.api.ChangeColumnAssert#hasValues(DateTimeValue, DateTimeValue)
   */
  public T hasValues(DateTimeValue expectedAtStartPoint, DateTimeValue expectedAtEndPoint);
}
