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
package org.assertj.db.api.assertions.impl;

import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.core.internal.Failures;
import org.assertj.db.api.AbstractAssert;
import org.assertj.db.exception.AssertJDBException;
import org.assertj.db.type.DateTimeValue;
import org.assertj.db.type.DateValue;
import org.assertj.db.type.TimeValue;
import org.assertj.db.type.ValueType;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;

import static org.assertj.db.error.ShouldBeAfter.shouldBeAfter;
import static org.assertj.db.error.ShouldBeAfterOrEqual.shouldBeAfterOrEqual;
import static org.assertj.db.error.ShouldBeBefore.shouldBeBefore;
import static org.assertj.db.error.ShouldBeBeforeOrEqual.shouldBeBeforeOrEqual;
import static org.assertj.db.util.Values.areEqual;

/**
 * Implements the assertion methods on the chronology of a value.
 *
 * @author Régis Pouiller
 * @see org.assertj.db.api.assertions.AssertOnValueChronology
 */
public class AssertionsOnValueChronology {

  /**
   * To notice failures in the assertion.
   */
  private final static Failures failures = Failures.instance();

  /**
   * Private constructor.
   */
  private AssertionsOnValueChronology() {
    // Empty
  }

  /**
   * Verifies that the value is before a date value.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Writable information about an assertion.
   * @param value     The value.
   * @param date      The date value to compare to.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not before to the date value in parameter.
   */
  public static <A extends AbstractAssert> A isBefore(A assertion, WritableAssertionInfo info, Object value,
                                                      DateValue date) {
    AssertionsOnValueType.isOfAnyOfTypes(assertion, info, value, ValueType.DATE, ValueType.DATE_TIME);
    if (value instanceof Date) {
      DateValue dateValue = DateValue.from((Date) value);
      if (dateValue.isBefore(date)) {
        return assertion;
      }
      throw failures.failure(info, shouldBeBefore(dateValue, date));
    } else {
      DateTimeValue dateTimeValue = DateTimeValue.of(date);
      if (DateTimeValue.from((Timestamp) value).isBefore(dateTimeValue)) {
        return assertion;
      }
      throw failures.failure(info, shouldBeBefore(DateTimeValue.from((Timestamp) value), dateTimeValue));
    }
  }

  /**
   * Verifies that the value is before a time value.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Writable information about an assertion.
   * @param value     The value.
   * @param time      The time value to compare to.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not before to the time value in parameter.
   */
  public static <A extends AbstractAssert> A isBefore(A assertion, WritableAssertionInfo info, Object value,
                                                      TimeValue time) {
    AssertionsOnValueType.isTime(assertion, info, value);
    if (TimeValue.from((Time) value).isBefore(time)) {
      return assertion;
    }
    throw failures.failure(info, shouldBeBefore(TimeValue.from((Time) value), time));
  }

  /**
   * Verifies that the value is before a date/time value.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Writable information about an assertion.
   * @param value     The value.
   * @param dateTime  The date/time value to compare to.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not before to the date/time value in parameter.
   */
  public static <A extends AbstractAssert> A isBefore(A assertion, WritableAssertionInfo info, Object value,
                                                      DateTimeValue dateTime) {
    AssertionsOnValueType.isOfAnyOfTypes(assertion, info, value, ValueType.DATE, ValueType.DATE_TIME);
    DateTimeValue dateTimeValue;
    if (value instanceof Date) {
      dateTimeValue = DateTimeValue.of(DateValue.from((Date) value));
    } else {
      dateTimeValue = DateTimeValue.from((Timestamp) value);
    }
    if (dateTimeValue.isBefore(dateTime)) {
      return assertion;
    }
    throw failures.failure(info, shouldBeBefore(dateTimeValue, dateTime));
  }

  /**
   * Verifies that the value is before a date, time or date/time represented by a {@code String}.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Writable information about an assertion.
   * @param value     The value.
   * @param expected  The {@code String} representing a date, time or date/time to compare to.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not before the date, time or date/time represented in parameter.
   */
  public static <A extends AbstractAssert> A isBefore(A assertion, WritableAssertionInfo info, Object value,
                                                      String expected) {
    AssertionsOnValueType.isOfAnyOfTypes(assertion, info, value, ValueType.DATE, ValueType.TIME, ValueType.DATE_TIME);

    // By considering the possible types, the class of the value is
    // java.sql.Date, java.sql.Time or java.sql.Timestamp

    // If the class is java.sql.Time then comparison by using TimeValue
    if (value instanceof Time) {
      TimeValue timeValue = TimeValue.from((Time) value);
      try {
        TimeValue expectedTimeValue = TimeValue.parse(expected);
        if (timeValue.isBefore(expectedTimeValue)) {
          return assertion;
        }
        throw failures.failure(info, shouldBeBefore(timeValue, expectedTimeValue));
      } catch (ParseException e) {
        throw new AssertJDBException("Expected <%s> is not correct to compare to <%s>", expected, timeValue);
      }
    }

    // In the other case then comparison by using DateTimeValue
    DateTimeValue dateTimeValue;
    if (value instanceof Date) {
      dateTimeValue = DateTimeValue.of(DateValue.from((Date) value));
    } else {
      dateTimeValue = DateTimeValue.from((Timestamp) value);
    }

    try {
      DateTimeValue expectedDateTimeValue = DateTimeValue.parse(expected);
      if (dateTimeValue.isBefore(expectedDateTimeValue)) {
        return assertion;
      }
      throw failures.failure(info, shouldBeBefore(dateTimeValue, expectedDateTimeValue));
    } catch (ParseException e) {
      throw new AssertJDBException("Expected <%s> is not correct to compare to <%s>", expected, dateTimeValue);
    }
  }

  /**
   * Verifies that the value is before or equal to a date value.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Writable information about an assertion.
   * @param value     The value.
   * @param date      The date value to compare to.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not before or equal to the date value in parameter.
   */
  public static <A extends AbstractAssert> A isBeforeOrEqualTo(A assertion, WritableAssertionInfo info, Object value,
                                                               DateValue date) {
    AssertionsOnValueType.isOfAnyOfTypes(assertion, info, value, ValueType.DATE, ValueType.DATE_TIME);
    if (value instanceof Date) {
      if (DateValue.from((Date) value).isBefore(date) || areEqual(value, date)) {
        return assertion;
      }
      throw failures.failure(info, shouldBeBeforeOrEqual(DateValue.from((Date) value), date));
    } else {
      DateTimeValue dateTimeValue = DateTimeValue.of(date);
      if (DateTimeValue.from((Timestamp) value).isBefore(dateTimeValue) || areEqual(value, dateTimeValue)) {
        return assertion;
      }
      throw failures.failure(info, shouldBeBeforeOrEqual(DateTimeValue.from((Timestamp) value), dateTimeValue));
    }
  }

  /**
   * Verifies that the value is before or equal to a time value.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Writable information about an assertion.
   * @param value     The value.
   * @param time      The time value to compare to.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not before or equal to the time value in parameter.
   */
  public static <A extends AbstractAssert> A isBeforeOrEqualTo(A assertion, WritableAssertionInfo info, Object value,
                                                               TimeValue time) {
    AssertionsOnValueType.isTime(assertion, info, value);
    if (TimeValue.from((Time) value).isBefore(time) || areEqual(value, time)) {
      return assertion;
    }
    throw failures.failure(info, shouldBeBeforeOrEqual(TimeValue.from((Time) value), time));
  }

  /**
   * Verifies that the value is before or equal to a date/time value.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Writable information about an assertion.
   * @param value     The value.
   * @param dateTime  The date/time value to compare to.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not before or equal to the date/time value in parameter.
   */
  public static <A extends AbstractAssert> A isBeforeOrEqualTo(A assertion, WritableAssertionInfo info, Object value,
                                                               DateTimeValue dateTime) {
    AssertionsOnValueType.isOfAnyOfTypes(assertion, info, value, ValueType.DATE, ValueType.DATE_TIME);
    DateTimeValue dateTimeValue;
    if (value instanceof Date) {
      dateTimeValue = DateTimeValue.of(DateValue.from((Date) value));
    } else {
      dateTimeValue = DateTimeValue.from((Timestamp) value);
    }
    if (dateTimeValue.isBefore(dateTime) || areEqual(value, dateTime)) {
      return assertion;
    }
    throw failures.failure(info, shouldBeBeforeOrEqual(dateTimeValue, dateTime));
  }

  /**
   * Verifies that the value is before or equal to a date, time or date/time represented by a {@code String}.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Writable information about an assertion.
   * @param value     The value.
   * @param expected  The {@code String} representing a date, time or date/time to compare to.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not before or equal to the date, time or date/time represented in parameter.
   */
  public static <A extends AbstractAssert> A isBeforeOrEqualTo(A assertion, WritableAssertionInfo info, Object value,
                                                               String expected) {
    AssertionsOnValueType.isOfAnyOfTypes(assertion, info, value, ValueType.DATE, ValueType.TIME, ValueType.DATE_TIME);

    // By considering the possible types, the class of the value is
    // java.sql.Date, java.sql.Time or java.sql.Timestamp

    // If the class is java.sql.Time then comparison by using TimeValue
    if (value instanceof Time) {
      TimeValue timeValue = TimeValue.from((Time) value);
      try {
        TimeValue expectedTimeValue = TimeValue.parse(expected);
        if (timeValue.isBefore(expectedTimeValue) || areEqual(value, expected)) {
          return assertion;
        }
        throw failures.failure(info, shouldBeBeforeOrEqual(timeValue, expectedTimeValue));
      } catch (ParseException e) {
        throw new AssertJDBException("Expected <%s> is not correct to compare to <%s>", expected, timeValue);
      }
    }

    // In the other case then comparison by using DateTimeValue
    DateTimeValue dateTimeValue;
    if (value instanceof Date) {
      dateTimeValue = DateTimeValue.of(DateValue.from((Date) value));
    } else {
      dateTimeValue = DateTimeValue.from((Timestamp) value);
    }

    try {
      DateTimeValue expectedDateTimeValue = DateTimeValue.parse(expected);
      if (dateTimeValue.isBefore(expectedDateTimeValue) || areEqual(value, expected)) {
        return assertion;
      }
      throw failures.failure(info, shouldBeBeforeOrEqual(dateTimeValue, expectedDateTimeValue));
    } catch (ParseException e) {
      throw new AssertJDBException("Expected <%s> is not correct to compare to <%s>", expected, dateTimeValue);
    }
  }

  /**
   * Verifies that the value is after a date value.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Writable information about an assertion.
   * @param value     The value.
   * @param date      The date value to compare to.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not after to the date value in parameter.
   */
  public static <A extends AbstractAssert> A isAfter(A assertion, WritableAssertionInfo info, Object value,
                                                     DateValue date) {
    AssertionsOnValueType.isOfAnyOfTypes(assertion, info, value, ValueType.DATE, ValueType.DATE_TIME);
    if (value instanceof Date) {
      if (DateValue.from((Date) value).isAfter(date)) {
        return assertion;
      }
      throw failures.failure(info, shouldBeAfter(DateValue.from((Date) value), date));
    } else {
      DateTimeValue dateTimeValue = DateTimeValue.of(date);
      if (DateTimeValue.from((Timestamp) value).isAfter(dateTimeValue)) {
        return assertion;
      }
      throw failures.failure(info, shouldBeAfter(DateTimeValue.from((Timestamp) value), dateTimeValue));
    }
  }

  /**
   * Verifies that the value is after a time value.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Writable information about an assertion.
   * @param value     The value.
   * @param time      The time value to compare to.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not after to the time value in parameter.
   */
  public static <A extends AbstractAssert> A isAfter(A assertion, WritableAssertionInfo info, Object value,
                                                     TimeValue time) {
    AssertionsOnValueType.isTime(assertion, info, value);
    if (TimeValue.from((Time) value).isAfter(time)) {
      return assertion;
    }
    throw failures.failure(info, shouldBeAfter(TimeValue.from((Time) value), time));
  }

  /**
   * Verifies that the value is after a date/time value.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Writable information about an assertion.
   * @param value     The value.
   * @param dateTime  The date/time value to compare to.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not after to the date/time value in parameter.
   */
  public static <A extends AbstractAssert> A isAfter(A assertion, WritableAssertionInfo info, Object value,
                                                     DateTimeValue dateTime) {
    AssertionsOnValueType.isOfAnyOfTypes(assertion, info, value, ValueType.DATE, ValueType.DATE_TIME);
    DateTimeValue dateTimeValue;
    if (value instanceof Date) {
      dateTimeValue = DateTimeValue.of(DateValue.from((Date) value));
    } else {
      dateTimeValue = DateTimeValue.from((Timestamp) value);
    }
    if (dateTimeValue.isAfter(dateTime)) {
      return assertion;
    }
    throw failures.failure(info, shouldBeAfter(dateTimeValue, dateTime));
  }

  /**
   * Verifies that the value is after a date, time or date/time represented by a {@code String}.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Writable information about an assertion.
   * @param value     The value.
   * @param expected  The {@code String} representing a date, time or date/time to compare to.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not after the date, time or date/time represented in parameter.
   */
  public static <A extends AbstractAssert> A isAfter(A assertion, WritableAssertionInfo info, Object value,
                                                     String expected) {
    AssertionsOnValueType.isOfAnyOfTypes(assertion, info, value, ValueType.DATE, ValueType.TIME, ValueType.DATE_TIME);

    // By considering the possible types, the class of the value is
    // java.sql.Date, java.sql.Time or java.sql.Timestamp

    // If the class is java.sql.Time then comparison by using TimeValue
    if (value instanceof Time) {
      TimeValue timeValue = TimeValue.from((Time) value);
      try {
        TimeValue expectedTimeValue = TimeValue.parse(expected);
        if (timeValue.isAfter(expectedTimeValue)) {
          return assertion;
        }
        throw failures.failure(info, shouldBeAfter(timeValue, expectedTimeValue));
      } catch (ParseException e) {
        throw new AssertJDBException("Expected <%s> is not correct to compare to <%s>", expected, timeValue);
      }
    }

    // In the other case then comparison by using DateTimeValue
    DateTimeValue dateTimeValue;
    if (value instanceof Date) {
      dateTimeValue = DateTimeValue.of(DateValue.from((Date) value));
    } else {
      dateTimeValue = DateTimeValue.from((Timestamp) value);
    }

    try {
      DateTimeValue expectedDateTimeValue = DateTimeValue.parse(expected);
      if (dateTimeValue.isAfter(expectedDateTimeValue)) {
        return assertion;
      }
      throw failures.failure(info, shouldBeAfter(dateTimeValue, expectedDateTimeValue));
    } catch (ParseException e) {
      throw new AssertJDBException("Expected <%s> is not correct to compare to <%s>", expected, dateTimeValue);
    }
  }

  /**
   * Verifies that the value is after or equal to a date value.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Writable information about an assertion.
   * @param value     The value.
   * @param date      The date value to compare to.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not after or equal to the time value in parameter.
   */
  public static <A extends AbstractAssert> A isAfterOrEqualTo(A assertion, WritableAssertionInfo info, Object value,
                                                              DateValue date) {
    AssertionsOnValueType.isOfAnyOfTypes(assertion, info, value, ValueType.DATE, ValueType.DATE_TIME);
    if (value instanceof Date) {
      if (DateValue.from((Date) value).isAfter(date) || areEqual(value, date)) {
        return assertion;
      }
      throw failures.failure(info, shouldBeAfterOrEqual(DateValue.from((Date) value), date));
    } else {
      DateTimeValue dateTimeValue = DateTimeValue.of(date);
      if (DateTimeValue.from((Timestamp) value).isAfter(dateTimeValue) || areEqual(value, date)) {
        return assertion;
      }
      throw failures.failure(info, shouldBeAfterOrEqual(DateTimeValue.from((Timestamp) value), date));
    }
  }

  /**
   * Verifies that the value is after or equal to a time value.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Writable information about an assertion.
   * @param value     The value.
   * @param time      The time value to compare to.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not after or equal to the time value in parameter.
   */
  public static <A extends AbstractAssert> A isAfterOrEqualTo(A assertion, WritableAssertionInfo info, Object value,
                                                              TimeValue time) {
    AssertionsOnValueType.isTime(assertion, info, value);
    if (TimeValue.from((Time) value).isAfter(time) || areEqual(value, time)) {
      return assertion;
    }
    throw failures.failure(info, shouldBeAfterOrEqual(TimeValue.from((Time) value), time));
  }

  /**
   * Verifies that the value is after or equal to a date/time value.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Writable information about an assertion.
   * @param value     The value.
   * @param dateTime  The date/time value to compare to.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not after or equal to the date/time value in parameter.
   */
  public static <A extends AbstractAssert> A isAfterOrEqualTo(A assertion, WritableAssertionInfo info, Object value,
                                                              DateTimeValue dateTime) {
    AssertionsOnValueType.isOfAnyOfTypes(assertion, info, value, ValueType.DATE, ValueType.DATE_TIME);
    DateTimeValue dateTimeValue;
    if (value instanceof Date) {
      dateTimeValue = DateTimeValue.of(DateValue.from((Date) value));
    } else {
      dateTimeValue = DateTimeValue.from((Timestamp) value);
    }
    if (dateTimeValue.isAfter(dateTime) || areEqual(value, dateTime)) {
      return assertion;
    }
    throw failures.failure(info, shouldBeAfterOrEqual(dateTimeValue, dateTime));
  }

  /**
   * Verifies that the value is after or equal to a date, time or date/time represented by a {@code String}.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Writable information about an assertion.
   * @param value     The value.
   * @param expected The {@code String} representing a date, time or date/time to compare to.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not after or equal to the date, time or date/time represented in parameter.
   */
  public static <A extends AbstractAssert> A isAfterOrEqualTo(A assertion, WritableAssertionInfo info, Object value, String expected) {
    AssertionsOnValueType.isOfAnyOfTypes(assertion, info, value, ValueType.DATE, ValueType.TIME, ValueType.DATE_TIME);

    // By considering the possible types, the class of the value is
    // java.sql.Date, java.sql.Time or java.sql.Timestamp

    // If the class is java.sql.Time then comparison by using TimeValue
    if (value instanceof Time) {
      TimeValue timeValue = TimeValue.from((Time) value);
      try {
        TimeValue expectedTimeValue = TimeValue.parse(expected);
        if (timeValue.isAfter(expectedTimeValue) || areEqual(value, expected)) {
          return assertion;
        }
        throw failures.failure(info, shouldBeAfterOrEqual(timeValue, expectedTimeValue));
      } catch (ParseException e) {
        throw new AssertJDBException("Expected <%s> is not correct to compare to <%s>", expected, timeValue);
      }
    }

    // In the other case then comparison by using DateTimeValue
    DateTimeValue dateTimeValue;
    if (value instanceof Date) {
      dateTimeValue = DateTimeValue.of(DateValue.from((Date) value));
    } else {
      dateTimeValue = DateTimeValue.from((Timestamp) value);
    }

    try {
      DateTimeValue expectedDateTimeValue = DateTimeValue.parse(expected);
      if (dateTimeValue.isAfter(expectedDateTimeValue) || areEqual(value, expected)) {
        return assertion;
      }
      throw failures.failure(info, shouldBeAfterOrEqual(dateTimeValue, expectedDateTimeValue));
    } catch (ParseException e) {
      throw new AssertJDBException("Expected <%s> is not correct to compare to <%s>", expected, dateTimeValue);
    }
  }
}
