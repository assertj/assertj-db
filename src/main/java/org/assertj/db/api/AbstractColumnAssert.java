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
package org.assertj.db.api;

import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.db.error.ShouldBeValueType;
import org.assertj.db.type.*;
import org.assertj.db.util.Values;

import java.util.List;

import static org.assertj.db.error.ShouldBeEqual.shouldBeEqual;
import static org.assertj.db.error.ShouldBeValueTypeOfAny.shouldBeValueTypeOfAny;
import static org.assertj.db.error.ShouldContainsOnlyNotNull.shouldContainsOnlyNotNull;
import static org.assertj.db.error.ShouldContainsOnlyNull.shouldContainsOnlyNull;
import static org.assertj.db.error.ShouldHaveName.shouldHaveName;
import static org.assertj.db.error.ShouldHaveRowsSize.shouldHaveRowsSize;
import static org.assertj.db.util.Values.areEqual;

/**
 * Assertion methods about the data in a <code>{@link Column}</code>.
 * 
 * @author Régis Pouiller
 * 
 * @param <D> The class of the actual value (an sub-class of {@link AbstractDbData}).
 * @param <A> The class of the original assert (an sub-class of {@link AbstractDbAssert}).
 * @param <C> The class of this assert (an sub-class of {@link AbstractColumnAssert}).
 * @param <CV> The class of this assertion on the value (an sub-class of {@link AbstractColumnValueAssert}).
 * @param <R> The class of the equivalent row assert (an sub-class of {@link AbstractRowAssert}).
 * @param <RV> The class of the equivalent row assertion on the value (an sub-class of {@link AbstractRowValueAssert}).
 */
public abstract class AbstractColumnAssert<D extends AbstractDbData<D>, A extends AbstractDbAssert<D, A, C, CV, R, RV>, C extends AbstractColumnAssert<D, A, C, CV, R, RV>, CV extends AbstractColumnValueAssert<D, A, C, CV, R, RV>, R extends AbstractRowAssert<D, A, C, CV, R, RV>, RV extends AbstractRowValueAssert<D, A, C, CV, R, RV>>
    extends AbstractSubAssert<D, A, C, CV, C, CV, R, RV> {

  /**
   * Column on which do the assertion.
   */
  private Column column;

  /**
   * Constructor.
   * 
   * @param originalDbAssert The original assert. That could be a {@link RequestAssert} or a {@link TableAssert}.
   * @param selfType Class of this assert (the sub assert) : a sub-class of {@code AbstractSubAssert}.
   * @param valueType Class of the assert on the value : a sub-class of {@code AbstractValueAssert}.
   */
  AbstractColumnAssert(A originalDbAssert, Class<C> selfType, Class<CV> valueType, Column column) {
    super(originalDbAssert, selfType, valueType);
    this.column = column;
  }

  /** {@inheritDoc} */
  @Override
  protected List<Object> getValuesList() {
    return column.getValuesList();
  }

  /** {@inheritDoc} */
  @Override
  protected void assertHasSize(WritableAssertionInfo info, int expected) {
    List<Object> valuesList = column.getValuesList();
    int size = valuesList.size();
    if (size != expected) {
      throw failures.failure(info, shouldHaveRowsSize(size, expected));
    }
  }

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
  public C isOfType(ValueType expected, boolean lenient) {
    if (lenient) {
      return isOfAnyOfTypes(expected, ValueType.NOT_IDENTIFIED);
    }

    int index = 0;
    for (Object value : getValuesList()) {
      ValueType type = ValueType.getType(value);
      if (type != expected) {
        throw failures.failure(info, ShouldBeValueType.shouldBeValueType(index, value, expected, type));
      }
      index++;
    }
    return myself;
  }

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
  public C isOfAnyOfTypes(ValueType... expected) {
    int index = 0;
    loop: for (Object value : getValuesList()) {
      ValueType type = ValueType.getType(value);
      for (ValueType valueType : expected) {
        if (type == valueType) {
          index++;
          continue loop;
        }
      }
      throw failures.failure(info, shouldBeValueTypeOfAny(index, value, type, expected));
    }
    return myself;
  }

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
  public C isNumber(boolean lenient) {
    return isOfType(ValueType.NUMBER, lenient);
  }

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
  public C isBoolean(boolean lenient) {
    return isOfType(ValueType.BOOLEAN, lenient);
  }

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
  public C isDate(boolean lenient) {
    return isOfType(ValueType.DATE, lenient);
  }

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
  public C isTime(boolean lenient) {
    return isOfType(ValueType.TIME, lenient);
  }

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
  public C isDateTime(boolean lenient) {
    return isOfType(ValueType.DATE_TIME, lenient);
  }

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
  public C isBytes(boolean lenient) {
    return isOfType(ValueType.BYTES, lenient);
  }

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
  public C isText(boolean lenient) {
    return isOfType(ValueType.TEXT, lenient);
  }

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
  public C hasOnlyNullValues() {
    int index = 0;
    for (Object value : getValuesList()) {
      if (value != null) {
        throw failures.failure(info, shouldContainsOnlyNull(index));
      }
      index++;
    }
    return myself;
  }

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
  public C hasOnlyNotNullValues() {
    int index = 0;
    for (Object value : getValuesList()) {
      if (value == null) {
        throw failures.failure(info, shouldContainsOnlyNotNull(index));
      }
      index++;
    }
    return myself;
  }

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
  public C hasValuesEqualTo(Boolean... expected) {
    isBoolean(true);
    hasSize(expected.length);
    int index = 0;
    for (Object value : getValuesList()) {
      Boolean val = (Boolean) value;
      if (!areEqual(val, expected[index])) {
        throw failures.failure(info, shouldBeEqual(index, val, expected[index]));
      }
      index++;
    }
    return myself;
  }

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
  public C hasValuesEqualTo(Number... expected) {
    isNumber(true);
    hasSize(expected.length);
    int index = 0;
    for (Object value : getValuesList()) {
      Number val = (Number) value;
      if (!areEqual(val, expected[index])) {
        throw failures.failure(info,
            shouldBeEqual(index, Values.getRepresentationFromValueInFrontOfExpected(val, expected[index]), expected[index]));
      }
      index++;
    }
    return myself;
  }

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
  public C hasValuesEqualTo(byte[]... expected) {
    isBytes(true);
    hasSize(expected.length);
    int index = 0;
    for (Object value : getValuesList()) {
      if (!areEqual((byte[]) value, expected[index])) {
        throw failures.failure(info, shouldBeEqual(index));
      }
      index++;
    }
    return myself;
  }

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
  public C hasValuesEqualTo(String... expected) {
    isOfAnyOfTypes(ValueType.TEXT, ValueType.NUMBER, ValueType.DATE, ValueType.TIME, ValueType.DATE_TIME,
        ValueType.NOT_IDENTIFIED);
    hasSize(expected.length);
    int index = 0;
    for (Object value : getValuesList()) {
      if (!areEqual(value, expected[index])) {
        throw failures.failure(info,
            shouldBeEqual(index, Values.getRepresentationFromValueInFrontOfExpected(value, expected[index]), expected[index]));
      }
      index++;
    }
    return myself;
  }

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
  public C hasValuesEqualTo(DateValue... expected) {
    isOfAnyOfTypes(ValueType.DATE, ValueType.DATE_TIME, ValueType.NOT_IDENTIFIED);
    hasSize(expected.length);
    int index = 0;
    for (Object value : getValuesList()) {
      if (!areEqual(value, expected[index])) {
        throw failures.failure(info,
            shouldBeEqual(index, Values.getRepresentationFromValueInFrontOfExpected(value, expected[index]), expected[index]));
      }
      index++;
    }
    return myself;
  }

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
  public C hasValuesEqualTo(TimeValue... expected) {
    isOfAnyOfTypes(ValueType.TIME, ValueType.NOT_IDENTIFIED);
    hasSize(expected.length);
    int index = 0;
    for (Object value : getValuesList()) {
      if (!areEqual(value, expected[index])) {
        throw failures.failure(info,
            shouldBeEqual(index, Values.getRepresentationFromValueInFrontOfExpected(value, expected[index]), expected[index]));
      }
      index++;
    }
    return myself;
  }

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
  public C hasValuesEqualTo(DateTimeValue... expected) {
    isOfAnyOfTypes(ValueType.DATE_TIME, ValueType.NOT_IDENTIFIED);
    hasSize(expected.length);
    int index = 0;
    for (Object value : getValuesList()) {
      if (!areEqual(value, expected[index])) {
        throw failures.failure(info,
            shouldBeEqual(index, Values.getRepresentationFromValueInFrontOfExpected(value, expected[index]), expected[index]));
      }
      index++;
    }
    return myself;
  }

  /**
   * Verifies that the values of a column are equal to time values.
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
  public C hasColumnName(String columnName) {
    if (columnName == null) {
      throw new NullPointerException("Column name must be not null");
    }
    String name = column.getName();
    if (!columnName.equalsIgnoreCase(name)) {
      throw failures.failure(info, shouldHaveName(name, columnName));
    }
    return myself;
  }
}
