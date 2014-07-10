package org.assertj.db.api;

import static org.assertj.core.error.ShouldBeEqual.shouldBeEqual;
import static org.assertj.db.error.ShouldBeType.shouldBeType;
import static org.assertj.db.error.ShouldBeTypeOfAny.shouldBeTypeOfAny;
import static org.assertj.db.util.Values.areEqual;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import org.assertj.core.api.Descriptable;
import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.core.description.Description;
import org.assertj.core.internal.Failures;
import org.assertj.core.internal.Objects;
import org.assertj.db.type.AbstractDbData;
import org.assertj.db.type.DateTimeValue;
import org.assertj.db.type.DateValue;
import org.assertj.db.type.TimeValue;

public abstract class AbstractValueAssert<S extends AbstractDbAssert<S, A>, A extends AbstractDbData<A>, T extends AbstractSubAssert<S, A, T>, V extends AbstractValueAssert<S, A, T, V>>
    implements Descriptable<V> {

  /**
   * The original assert.
   */
  private final T originalAssert;
  /**
   * The actual value on which this assertion is.
   */
  private final Object value;
  /**
   * This assertion.
   */
  private final V myself;
  /**
   * The information about the assertion.
   */
  private final WritableAssertionInfo info;

  /**
   * To notice failures in the assertion.
   */
  private static Failures failures = Failures.instance();
  /**
   * Assertions for {@code Object}s provided by assertj-core.
   */
  private Objects objects = Objects.instance();

  // Like in AbstractAssert from assertj-core :
  // we prefer not to use Class<? extends S> selfType because it would force inherited
  // constructor to cast with a compiler warning
  // let's keep compiler warning internal (when we can) and not expose them to our end users.
  /**
   * Constructor.
   * 
   * @param originalAssert The original assert.
   * @param selfType Class of this assert (the value assert) : a sub-class of {@code AbstractValueAssert}.
   * @param actualValue The value to assert.
   */
  @SuppressWarnings("unchecked")
  AbstractValueAssert(T originalAssert, Class<?> selfType, Object actualValue) {
    myself = (V) selfType.cast(this);
    this.originalAssert = originalAssert;
    this.value = actualValue;
    info = new WritableAssertionInfo();
  }

  /** {@inheritDoc} */
  public V as(String description, Object... args) {
    return describedAs(description, args);
  }

  /** {@inheritDoc} */
  @Override
  public V as(Description description) {
    return describedAs(description);
  }

  /** {@inheritDoc} */
  @Override
  public V describedAs(String description, Object... args) {
    info.description(description, args);
    return myself;
  }

  /** {@inheritDoc} */
  @Override
  public V describedAs(Description description) {
    info.description(description);
    return myself;
  }

  /**
   * Returns the original assertion (an instance of a sub-class of {@link AbstractSubAssert}.
   * 
   * @return The original assertion.
   */
  protected T returnToSubAssert() {
    return originalAssert;
  }

  /**
   * Returns the type of the value (text, boolean, number, date, ...).
   * 
   * @return The type of the value.
   */
  protected ValueType getType() {
    return ValueType.getType(value);
  }

  /**
   * Verifies that the type of the value is equal to the type in parameter.
   * <p>
   * Example where the assertion verifies that the value in the {@code Column} called "title" of the second {@code Row}
   * of the {@code Table} is of type {@code TEXT} :
   * </p>
   * 
   * <pre>
   * assertThat(table).row(1).value(&quot;title&quot;).isOfType(ValueType.TEXT);
   * </pre>
   * 
   * @param expected The expected type to compare to.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type is different to the type in parameter.
   */
  public V isOfType(ValueType expected) {
    ValueType type = getType();
    if (type != expected) {
      throw failures.failure(info, shouldBeType(value, expected, type));
    }
    return myself;
  }

  /**
   * Verifies that the type of the value is equal to one of the types in parameters.
   * <p>
   * Example where the assertion verifies that the value in the {@code Column} called "title" of the second {@code Row}
   * of the {@code Table} is of type {@code TEXT} or of type {@code NUMBER} :
   * </p>
   * 
   * <pre>
   * assertThat(table).row(1).value(&quot;title&quot;).isOfType(ValueType.TEXT, ValueType.NUMBER);
   * </pre>
   * 
   * @param expected The expected types to compare to.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type is different to all the types in parameters.
   */
  public V isOfAnyOfTypes(ValueType... expected) {
    ValueType type = getType();
    for (ValueType valueType : expected) {
      if (type == valueType) {
        return myself;
      }
    }
    throw failures.failure(info, shouldBeTypeOfAny(value, type, expected));
  }

  /**
   * Verifies that the value is a number.
   * <p>
   * Example where the assertion verifies that the value in the {@code Column} called "year" of the first {@code Row} of
   * the {@code Table} is a number :
   * </p>
   * 
   * <pre>
   * assertThat(table).row().value(&quot;year&quot;).isNumber();
   * </pre>
   * 
   * @return {@code this} assertion object.
   * @throws AssertionError If the type is not a number.
   */
  public V isNumber() {
    return isOfType(ValueType.NUMBER);
  }

  /**
   * Verifies that the value is a boolean.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is a boolean :
   * </p>
   * 
   * <pre>
   * assertThat(table).row().value().isBoolean();
   * </pre>
   * 
   * @return {@code this} assertion object.
   * @throws AssertionError If the type is not a number.
   */
  public V isBoolean() {
    return isOfType(ValueType.BOOLEAN);
  }

  /**
   * Verifies that the value is a date.
   * <p>
   * Example where the assertion verifies that the value in the {@code Column} called "birth" of the first {@code Row}
   * of the {@code Table} is a date :
   * </p>
   * 
   * <pre>
   * assertThat(table).row().value(&quot;birth&quot;).isDate();
   * </pre>
   * 
   * @return {@code this} assertion object.
   * @throws AssertionError If the type is not a number.
   */
  public V isDate() {
    return isOfType(ValueType.DATE);
  }

  /**
   * Verifies that the value is a time.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is a time :
   * </p>
   * 
   * <pre>
   * assertThat(table).row().value().isTime();
   * </pre>
   * 
   * @return {@code this} assertion object.
   * @throws AssertionError If the type is not a number.
   */
  public V isTime() {
    return isOfType(ValueType.TIME);
  }

  /**
   * Verifies that the value is a date/time.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is a date/time :
   * </p>
   * 
   * <pre>
   * assertThat(table).row().value().isDateTime();
   * </pre>
   * 
   * @return {@code this} assertion object.
   * @throws AssertionError If the type is not a number.
   */
  public V isDateTime() {
    return isOfType(ValueType.DATE_TIME);
  }

  /**
   * Verifies that the value is a array of bytes.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is a array of bytes :
   * </p>
   * 
   * <pre>
   * assertThat(table).row().value().isBytes();
   * </pre>
   * 
   * @return {@code this} assertion object.
   * @throws AssertionError If the type is not a number.
   */
  public V isBytes() {
    return isOfType(ValueType.BYTES);
  }

  /**
   * Verifies that the value is a text.
   * <p>
   * Example where the assertion verifies that the value in the {@code Column} called "title" of the first {@code Row}
   * of the {@code Table} is a text :
   * </p>
   * 
   * <pre>
   * assertThat(table).row().value(&quot;title&quot;).isText();
   * </pre>
   * 
   * @return {@code this} assertion object.
   * @throws AssertionError If the type is not a number.
   */
  public V isText() {
    return isOfType(ValueType.TEXT);
  }

  /**
   * Verifies that the value is a array of bytes.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is null :
   * </p>
   * 
   * <pre>
   * assertThat(table).row().value().isNull();
   * </pre>
   * 
   * @return {@code this} assertion object.
   * @throws AssertionError If the type is not a number.
   */
  public V isNull() {
    objects.assertNull(info, value);
    return myself;
  }

  /**
   * Verifies that the value is a array of bytes.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is not null :
   * </p>
   * 
   * <pre>
   * assertThat(table).row().value().isNotNull();
   * </pre>
   * 
   * @return {@code this} assertion object.
   * @throws AssertionError If the type is not a number.
   */
  public V isNotNull() {
    objects.assertNotNull(info, value);
    return myself;
  }

  /**
   * Verifies that the value is equal to a boolean.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is equal to true boolean :
   * </p>
   * 
   * <pre>
   * assertThat(table).row().value().isEqualTo(true);
   * </pre>
   * 
   * @param expected The expected boolean value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not equal to the boolean in parameter.
   */
  public V isEqualTo(Boolean expected) {
    isBoolean();
    if (areEqual(value, expected)) {
      return myself;
    }
    throw failures.failure(info, shouldBeEqual(value, expected, info.representation()));
  }

  /**
   * Verifies that the value is equal to true boolean.
   * <p>
   * Example with the value in the first {@code Column} of the first {@code Row} of the {@code Table} :
   * </p>
   * 
   * <pre>
   * assertThat(table).row().value().isTrue();
   * </pre>
   * 
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not equal to true boolean.
   */
  public V isTrue() {
    return isEqualTo(true);
  }

  /**
   * Verifies that the value is equal to false boolean.
   * <p>
   * Example with the value in the first {@code Column} of the first {@code Row} of the {@code Table} :
   * </p>
   * 
   * <pre>
   * assertThat(table).row().value().isFalse();
   * </pre>
   * 
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not equal to false boolean.
   */
  public V isFalse() {
    return isEqualTo(false);
  }

  /**
   * Verifies that the value is equal to a number.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is equal to number 3 :
   * </p>
   * 
   * <pre>
   * assertThat(table).row().value().isEqualTo(3);
   * </pre>
   * 
   * @param expected The expected number value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not equal to the number in parameter.
   */
  public V isEqualTo(Number expected) {
    isNumber();
    if (areEqual(value, expected)) {
      return myself;
    }
    throw failures.failure(info, shouldBeEqual(value, expected, info.representation()));
  }

  /**
   * Verifies that the value is equal to a array of bytes.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is equal to a array of bytes loaded from a file in the classpath :
   * </p>
   * 
   * <pre>
   * byte[] bytes = bytesContentFromClassPathOf(&quot;file.png&quot;);
   * assertThat(table).row().value().isEqualTo(bytes);
   * </pre>
   * 
   * @param expected The expected array of bytes value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not equal to the array of bytes in parameter.
   */
  public V isEqualTo(byte[] expected) {
    isBytes();
    if (areEqual(value, expected)) {
      return myself;
    }
    throw failures.failure(info, shouldBeEqual(value, expected, info.representation()));
  }

  /**
   * Verifies that the value is equal to a text.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is equal to a text :
   * </p>
   * 
   * <pre>
   * assertThat(table).row().value().isEqualTo(&quot;text&quot;);
   * </pre>
   * 
   * @param expected The expected text value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not equal to the text in parameter.
   */
  public V isEqualTo(String expected) {
    isOfAnyOfTypes(ValueType.TEXT, ValueType.NUMBER);
    if (areEqual(value, expected)) {
      return myself;
    }
    throw failures.failure(info, shouldBeEqual(value, expected, info.representation()));
  }

  /**
   * Verifies that the value is equal to a date value.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is equal to a date value :
   * </p>
   * 
   * <pre>
   * assertThat(table).row().value().isEqualTo(DateValue.of(2014, 7, 7));
   * </pre>
   * 
   * @param expected The expected date value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not equal to the date value in parameter.
   */
  public V isEqualTo(DateValue expected) {
    isDate();
    if (areEqual(value, expected)) {
      return myself;
    }
    throw failures.failure(info, shouldBeEqual(DateValue.from((Date) value), expected, info.representation()));
  }

  /**
   * Verifies that the value is equal to a time value.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is equal to a time value :
   * </p>
   * 
   * <pre>
   * assertThat(table).row().value().isEqualTo(TimeValue.of(21, 29, 30));
   * </pre>
   * 
   * @param expected The expected time value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not equal to the time value in parameter.
   */
  public V isEqualTo(TimeValue expected) {
    isTime();
    if (areEqual(value, expected)) {
      return myself;
    }
    throw failures.failure(info, shouldBeEqual(TimeValue.from((Time) value), expected, info.representation()));
  }

  /**
   * Verifies that the value is equal to a date/time value.
   * <p>
   * Example where the assertion verifies that the value in the first {@code Column} of the first {@code Row} of the
   * {@code Table} is equal to a date/time value :
   * </p>
   * 
   * <pre>
   * assertThat(table).row().value().isEqualTo(DateTimeValue.of(DateValue.of(2014, 7, 7), TimeValue.of(21, 29)));
   * </pre>
   * 
   * @param expected The expected date/time value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not equal to the date/time value in parameter.
   */
  public V isEqualTo(DateTimeValue expected) {
    isDateTime();
    if (areEqual(value, expected)) {
      return myself;
    }
    throw failures.failure(info, shouldBeEqual(DateTimeValue.from((Timestamp) value), expected, info.representation()));
  }
}
