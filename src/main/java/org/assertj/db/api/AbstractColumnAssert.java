package org.assertj.db.api;

import static org.assertj.core.error.ShouldBeEqual.shouldBeEqual;
import static org.assertj.db.error.ShouldBeEqual.shouldBeEqual;
import static org.assertj.db.error.ShouldBeType.shouldBeType;
import static org.assertj.db.error.ShouldBeTypeOfAny.shouldBeTypeOfAny;
import static org.assertj.db.error.ShouldContainsOnlyNotNull.shouldContainsOnlyNotNull;
import static org.assertj.db.error.ShouldContainsOnlyNull.shouldContainsOnlyNull;
import static org.assertj.db.error.ShouldHaveRowsSize.shouldHaveRowsSize;
import static org.assertj.db.util.Values.areEqual;

import java.sql.Time;
import java.util.List;

import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.core.internal.Failures;
import org.assertj.db.type.AbstractDbData;
import org.assertj.db.type.Column;
import org.assertj.db.type.DateTimeValue;
import org.assertj.db.type.DateValue;
import org.assertj.db.type.TimeValue;

/**
 * Assertion methods about the data in a <code>{@link Column}</code>.
 * 
 * @author Régis Pouiller
 * 
 * @param <E> The class of the actual value (an sub-class of {@link AbstractDbData}).
 * @param <D> The class of the original assert (an sub-class of {@link AbstractDbAssert}).
 * @param <C> The class of this assert (an sub-class of {@link AbstractColumnAssert}).
 * @param <CV> The class of this assertion on the value (an sub-class of {@link AbstractColumnValueAssert}).
 * @param <R> The class of the equivalent row assert (an sub-class of {@link AbstractRowAssert}).
 * @param <RV> The class of the equivalent row assertion on the value (an sub-class of {@link AbstractRowValueAssert}).
 */
public abstract class AbstractColumnAssert<E extends AbstractDbData<E>, D extends AbstractDbAssert<E, D, C, CV, R, RV>, C extends AbstractColumnAssert<E, D, C, CV, R, RV>, CV extends AbstractColumnValueAssert<E, D, C, CV, R, RV>, R extends AbstractRowAssert<E, D, C, CV, R, RV>, RV extends AbstractRowValueAssert<E, D, C, CV, R, RV>>
    extends AbstractSubAssert<E, D, C, CV, C, CV, R, RV> {

  /**
   * Column on which do the assertion.
   */
  private Column column;

  /**
   * To notice failures in the assertion.
   */
  private static Failures failures = Failures.instance();

  /**
   * Constructor.
   * 
   * @param originalDbAssert The original assert. That could be a {@link RequestAssert} or a {@link TableAssert}.
   * @param selfType Class of this assert (the sub assert) : a sub-class of {@code AbstractSubAssert}.
   * @param valueType Class of the assert on the value : a sub-class of {@code AbstractValueAssert}.
   */
  AbstractColumnAssert(D originalDbAssert, Class<C> selfType, Class<CV> valueType, Column column) {
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
   * <pre>
   * assertThat(table).column(&quot;title&quot;).isOfType(ValueType.TEXT, false);
   * </pre>
   * <p>
   * Example where the assertion verifies that all the values in the {@code Column} called "title" of the {@code Table}
   * is of type {@code TEXT} or not identified (for example {@code null}) :
   * </p>
   * 
   * <pre>
   * assertThat(table).column(&quot;title&quot;).isOfType(ValueType.TEXT, true);
   * </pre>
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
        throw failures.failure(info, shouldBeType(index, value, expected, type));
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
   * <pre>
   * assertThat(table).column(&quot;title&quot;).isOfAnyOfTypes(ValueType.TEXT, ValueType.NUMBER);
   * </pre>
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
      throw failures.failure(info, shouldBeTypeOfAny(index, value, type, expected));
    }
    return myself;
  }

  /**
   * Verifies that the type of the values of the column is a number.
   * <p>
   * Example where the assertion verifies that all the values in the {@code Column} called "year" of the first
   * {@code Row} of the {@code Table} is a number :
   * </p>
   * 
   * <pre>
   * assertThat(table).column(&quot;year&quot;).isNumber(true);
   * </pre>
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
   * Example where the assertion verifies that all the values in the first {@code Column} of the first {@code Row} of
   * the {@code Table} is a boolean :
   * </p>
   * 
   * <pre>
   * assertThat(table).column().isBoolean(false);
   * </pre>
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
   * Example where the assertion verifies that all the values in the {@code Column} called "birth" of the first
   * {@code Row} of the {@code Table} is a date :
   * </p>
   * 
   * <pre>
   * assertThat(table).column(&quot;birth&quot;).isDate(false);
   * </pre>
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
   * Example where the assertion verifies that all the values in the first {@code Column} of the first {@code Row} of
   * the {@code Table} is a time :
   * </p>
   * 
   * <pre>
   * assertThat(table).column().isTime(false);
   * </pre>
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
   * Example where the assertion verifies that all the values in the first {@code Column} of the first {@code Row} of
   * the {@code Table} is a date/time :
   * </p>
   * 
   * <pre>
   * assertThat(table).column().isDateTime(false);
   * </pre>
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
   * Example where the assertion verifies that all the values in the first {@code Column} of the first {@code Row} of
   * the {@code Table} is a array of bytes :
   * </p>
   * 
   * <pre>
   * assertThat(table).column().isBytes(false);
   * </pre>
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
   * Example where the assertion verifies that all the values in the {@code Column} called "title" of the first
   * {@code Row} of the {@code Table} is a text :
   * </p>
   * 
   * <pre>
   * assertThat(table).column(&quot;title&quot;).isText(false);
   * </pre>
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
   * <pre>
   * assertThat(table).column().haveOnlyNullValues();
   * </pre>
   * 
   * @return {@code this} assertion object.
   * @throws AssertionError If at least one of the values of the column are not {@code null}.
   */
  public C haveOnlyNullValues() {
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
   * <pre>
   * assertThat(table).column().haveOnlyNotNullValues();
   * </pre>
   * 
   * @return {@code this} assertion object.
   * @throws AssertionError If at least one of the values of the column are {@code null}.
   */
  public C haveOnlyNotNullValues() {
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
   * <pre>
   * assertThat(table).column().haveValuesEqualTo(true, false, true);
   * </pre>
   * 
   * @param expected The expected boolean values.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not equal to the booleans in parameter.
   */
  public C haveValuesEqualTo(Boolean... expected) {
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
   * <pre>
   * assertThat(table).column().haveValuesEqualTo(5, 10.5, 6);
   * </pre>
   * 
   * @param expected The expected numbers values.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not equal to the numbers in parameter.
   */
  public C haveValuesEqualTo(Number... expected) {
    isNumber(true);
    hasSize(expected.length);
    int index = 0;
    for (Object value : getValuesList()) {
      Number val = (Number) value;
      if (!areEqual(val, expected[index])) {
        throw failures.failure(info, shouldBeEqual(index, val, expected[index]));
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
   * <pre>
   * byte[] bytes1 = bytesContentFromClassPathOf(&quot;file1.png&quot;);
   * byte[] bytes2 = bytesContentFromClassPathOf(&quot;file2.png&quot;);
   * assertThat(table).column().haveValuesEqualTo(bytes1, bytes2);
   * </pre>
   * 
   * @param expected The expected bytes values.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not equal to the bytes in parameter.
   */
  public C haveValuesEqualTo(byte[]... expected) {
    isBytes(true);
    hasSize(expected.length);
    int index = 0;
    for (Object value : getValuesList()) {
      if (!areEqual((byte[]) value, expected[index])) {
        throw failures.failure(info, shouldBeEqual(getValuesList(), expected, info.representation()));
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
   * <pre>
   * assertThat(table).column().haveValuesEqualTo(&quot;text&quot;, &quot;text2&quot;, &quot;text3&quot;);
   * </pre>
   * 
   * @param expected The expected text values.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not equal to the texts in parameter.
   */
  public C haveValuesEqualTo(String... expected) {
    isOfAnyOfTypes(ValueType.TEXT, ValueType.NUMBER, ValueType.DATE, ValueType.TIME, ValueType.DATE_TIME,
        ValueType.NOT_IDENTIFIED);
    hasSize(expected.length);
    int index = 0;
    for (Object value : getValuesList()) {
      if (!areEqual(value, expected[index])) {
        throw failures.failure(info, shouldBeEqual(getValuesList(), expected, info.representation()));
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
   * <pre>
   * assertThat(table).column().haveValuesEqualTo(DateValue.of(2014, 7, 7), DateValue.of(2014, 10, 3),
   *     DateValue.of(2014, 12, 23));
   * </pre>
   * 
   * @param expected The expected date values.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not equal to the date values in parameter.
   */
  public C haveValuesEqualTo(DateValue... expected) {
    isOfAnyOfTypes(ValueType.DATE, ValueType.DATE_TIME, ValueType.NOT_IDENTIFIED);
    hasSize(expected.length);
    int index = 0;
    for (Object value : getValuesList()) {
      if (!areEqual(value, expected[index])) {
        throw failures.failure(info, shouldBeEqual(getValuesList(), expected, info.representation()));
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
   * <pre>
   * assertThat(table).column().haveValuesEqualTo(TimeValue.of(21, 29, 30), TimeValue.of(10, 1, 25), TimeValue.of(9, 1));
   * </pre>
   * 
   * @param expected The expected time values.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not equal to the time values in parameter.
   */
  public C haveValuesEqualTo(TimeValue... expected) {
    isOfAnyOfTypes(ValueType.TIME, ValueType.NOT_IDENTIFIED);
    hasSize(expected.length);
    int index = 0;
    for (Object value : getValuesList()) {
      TimeValue val = null;
      if (value != null) {
        val = TimeValue.from((Time) value);
      }
      if (!areEqual(value, expected[index])) {
        throw failures.failure(info, shouldBeEqual(index, val, expected[index]));
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
   * <pre>
   * assertThat(table).column().haveValuesEqualTo(DateTimeValue.of(DateValue.of(2014, 7, 7), TimeValue.of(21, 29)),
   *     DateTimeValue.of(DateValue.of(2014, 7, 7), TimeValue.of(10, 1, 25)),
   *     DateTimeValue.of(DateValue.of(2014, 7, 7), TimeValue.of(9, 1)));
   * </pre>
   * 
   * @param expected The expected date/time values.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not equal to the date/time values in parameter.
   */
  public C haveValuesEqualTo(DateTimeValue... expected) {
    isOfAnyOfTypes(ValueType.DATE_TIME, ValueType.NOT_IDENTIFIED);
    hasSize(expected.length);
    int index = 0;
    for (Object value : getValuesList()) {
      if (!areEqual(value, expected[index])) {
        throw failures.failure(info, shouldBeEqual(getValuesList(), expected, info.representation()));
      }
      index++;
    }
    return myself;
  }
}
