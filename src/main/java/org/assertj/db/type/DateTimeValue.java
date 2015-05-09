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
package org.assertj.db.type;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Calendar;

/**
 * This class represents a date/time value in the database.
 * 
 * @author Régis Pouiller
 * 
 */
public class DateTimeValue implements Comparable<DateTimeValue>, DateValueContainer {

  /**
   * The date part.
   */
  private final DateValue date;
  /**
   * The time part.
   */
  private final TimeValue time;
  /**
   * Indicates where there are the digits in the {@code String} for {@link DateValue#DateValue(String)}.
   */
  private static final String DATE_FORMAT = "\\d\\d\\d\\d-\\d\\d-\\d\\d";
  /**
   * Indicates where there are the digits for {@code String} for {@link TimeValue#TimeValue(String)}.
   */
  private static final String TIME_FORMAT = "\\d\\d\\d\\d-\\d\\d-\\d\\dT\\d\\d:\\d\\d";
  /**
   * Indicates where there are the digits in style with seconds for {@code String} for
   * {@link TimeValue#TimeValue(String)}.
   */
  private static final String TIME_FORMAT_WITH_SECONDS = "\\d\\d\\d\\d-\\d\\d-\\d\\dT\\d\\d:\\d\\d:\\d\\d";
  /**
   * Indicates where there are the digits in style with nanoseconds for {@code String} for
   * {@link TimeValue#TimeValue(String)}.
   */
  private static final String TIME_FORMAT_WITH_NANO = "\\d\\d\\d\\d-\\d\\d-\\d\\dT\\d\\d:\\d\\d:\\d\\d.\\d\\d\\d\\d\\d\\d\\d\\d\\d";

  /**
   * Makes an instance of date/time value from a date with time at 00:00AM.
   * 
   * @param date The date.
   * @return An instance of date/time.
   */
  public static DateTimeValue of(DateValue date) {
    return new DateTimeValue(date, TimeValue.of(0, 0));
  }

  /**
   * Makes an instance of date/time value from a date and a time.
   * 
   * @param date The date.
   * @param time The time.
   * @return An instance of date/time.
   */
  public static DateTimeValue of(DateValue date, TimeValue time) {
    return new DateTimeValue(date, time);
  }

  /**
   * Makes an instance of date/time value from a {@code String} in {@code yyyy-mm-dd}, {@code yyyy-mm-ddThh:mm},
   * {@code yyyy-mm-ddThh:mm:ss} or {@code yyyy-mm-ddThh:mm:ss.nnnnnnnnn} format.
   * 
   * @param dateTime Date/time in {@code String} format ({@code yyyy-mm-dd}).
   * @throws NullPointerException If {@code dateTime} is {@code null}.
   * @throws ParseException If {@code date} don't respect the {@code yyyy-mm-dd}, {@code yyyy-mm-ddThh:mm},
   *           {@code yyyy-mm-ddThh:mm:ss} or {@code yyyy-mm-ddThh:mm:ss.nnnnnnnnn} format.
   * @return An instance of date/time value.
   */
  public static DateTimeValue parse(String dateTime) throws ParseException {
    return new DateTimeValue(dateTime);
  }

  /**
   * Makes an instance of date/time value from a {@link Timestamp}.
   * 
   * @param timestamp Timestamp.
   * @throws NullPointerException If {@code timestamp} is {@code null}.
   * @return An instance of date/time value.
   */
  public static DateTimeValue from(Timestamp timestamp) {
    return new DateTimeValue(timestamp);
  }

  /**
   * Constructor.
   * 
   * @param date The date.
   * @param time The time.
   * @throws NullPointerException If {@code date} or {@code time} is {@code null}.
   */
  public DateTimeValue(DateValue date, TimeValue time) {
    if (date == null) {
      throw new NullPointerException("date should be not null");
    }
    if (time == null) {
      throw new NullPointerException("time should be not null");
    }
    this.date = date;
    this.time = time;
  }

  /**
   * Constructor.
   * 
   * @param dateTime Time in {@code String} format ({@code yyyy-mm-dd}, {@code yyyy-mm-ddThh:mm},
   *          {@code yyyy-mm-ddThh:mm:ss} or {@code yyyy-mm-ddThh:mm:ss.nnnnnnnnn}).
   * @throws NullPointerException If {@code dateTime} is {@code null}.
   * @throws ParseException If {@code date} don't respect the {@code yyyy-mm-dd}, {@code yyyy-mm-ddThh:mm},
   *           {@code yyyy-mm-ddThh:mm:ss} or {@code yyyy-mm-ddThh:mm:ss.nnnnnnnnn} format.
   */
  public DateTimeValue(String dateTime) throws ParseException {
    if (dateTime == null) {
      throw new NullPointerException("date/time should be not null");
    }

    if (dateTime.matches(DATE_FORMAT)) {
      date = DateValue.parse(dateTime);
      time = new TimeValue(0, 0);
    } else if (dateTime.matches(TIME_FORMAT) || dateTime.matches(TIME_FORMAT_WITH_SECONDS)
        || dateTime.matches(TIME_FORMAT_WITH_NANO)) {

      date = DateValue.parse(dateTime.substring(0, 10));
      time = TimeValue.parse(dateTime.substring(11));
    } else {
      throw new ParseException("date/time must respect yyyy-mm-dd, yyyy-mm-ddThh:mm, "
          + "yyyy-mm-ddThh:mm:ss or yyyy-mm-ddThh:mm:ss.nnnnnnnnn format", dateTime.length());
    }
  }

  /**
   * Constructor.
   * 
   * @param timestamp Timestamp.
   * @throws NullPointerException If {@code dateTime} is {@code null}.
   */
  public DateTimeValue(Timestamp timestamp) {
    if (timestamp == null) {
      throw new NullPointerException("date/time should be not null");
    }

    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(timestamp.getTime());

    date = DateValue.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1,
        calendar.get(Calendar.DAY_OF_MONTH));
    time = TimeValue.of(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),
        calendar.get(Calendar.SECOND), timestamp.getNanos());
  }

  /** {@inheritDoc} */
  @Override
  public DateValue getDate() {
    return date;
  }

  public boolean isMidnight() {
    return time.getHour() == 0 && time.getMinutes() == 0 && time.getSeconds() == 0 && time.getNanoSeconds() == 0;
  }

  /**
   * Returns the time.
   * 
   * @return The time.
   */
  public TimeValue getTime() {
    return time;
  }

  @Override
  public String toString() {
    return String.format("%4d-%02d-%02dT%02d:%02d:%02d.%09d", date.getYear(), date.getMonth(), date.getDayOfTheMonth(),
        time.getHour(), time.getMinutes(), time.getSeconds(), time.getNanoSeconds());
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof DateTimeValue) {
      DateTimeValue dateTimeValue = (DateTimeValue) obj;
      return date.equals(dateTimeValue.date) && time.equals(dateTimeValue.time);
    } else if (obj instanceof DateValueContainer) {
      DateValueContainer value = (DateValueContainer) obj;
      return date.equals(value.getDate()) && isMidnight();
    }
    return false;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + date.hashCode();
    result = prime * result + time.hashCode();
    return result;
  }

  @Override
  public int compareTo(DateTimeValue other) {
    int compareDate = date.compareTo(other.date);
    if (compareDate != 0) {
      return compareDate;
    }
    return time.compareTo(other.time);
  }

  /**
   * Returns if this date/time value is before the date/time value in parameter.
   * 
   * @param dateTime The date/time value to compare to.
   * @return If this date/time value is before the date/time value in parameter.
   */
  public boolean isBefore(DateTimeValue dateTime) {
    return compareTo(dateTime) == -1;
  }

  /**
   * Returns if this date/time value is after the date/time value in parameter.
   * 
   * @param dateTime The date/time value to compare to.
   * @return If this date/time value is after the date/time value in parameter.
   */
  public boolean isAfter(DateTimeValue dateTime) {
    return compareTo(dateTime) == 1;
  }

}
