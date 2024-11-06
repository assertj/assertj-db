/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2015-2024 the original author or authors.
 */
package org.assertj.db.type;

import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Calendar;

/**
 * This class represents a date value in the database.
 * 
 * @author Régis Pouiller
 * 
 */
public class DateValue implements Comparable<DateValue>, DateValueContainer {

  public static final String DATE_SHOULD_BE_NOT_NULL = "date should be not null";
  /**
   * Day of the month.
   */
  private final int dayOfTheMonth;
  /**
   * Month.
   */
  private final int month;
  /**
   * Year.
   */
  private final int year;
  /**
   * Indicates where there are the digits in the {@code String} for {@link DateValue#DateValue(String)}.
   */
  private static final String DATE_FORMAT = "\\d\\d\\d\\d-\\d\\d-\\d\\d";

  /**
   * Makes an instance of date value from a day of month, a month and an year.
   * 
   * @param year Year.
   * @param month Month.
   * @param dayOfTheMonth Day of the month.
   * @return An instance of date value.
   */
  public static DateValue of(int year, int month, int dayOfTheMonth) {
    return new DateValue(year, month, dayOfTheMonth);
  }

  /**
   * Makes an instance of date value from a {@code String} in format {@code yyyy-mm-dd}.
   * 
   * @param date Date in {@code String} format ({@code yyyy-mm-dd}).
   * @throws NullPointerException If {@code date} is {@code null}.
   * @throws ParseException If {@code date} don't respect the {@code yyyy-mm-dd} format.
   * @return An instance of date value.
   */
  public static DateValue parse(String date) throws ParseException {
    return new DateValue(date);
  }

  /**
   * Makes an instance of date value from a {@link Date}.
   * 
   * @param date Date.
   * @throws NullPointerException If {@code date} is {@code null}.
   * @return An instance of date value.
   */
  public static DateValue from(Date date) {
    return new DateValue(date);
  }

  /**
   * Makes an instance of date value from a {@link Calendar}.
   *
   * @param calendar Calendar.
   * @throws NullPointerException If {@code calendar} is {@code null}.
   * @return An instance of date value.
   * @since 1.1.0
   */
  public static DateValue from(Calendar calendar) {
    return new DateValue(calendar);
  }

  /**
   * Makes an instance of date value from a {@link LocalDate}.
   *
   * @param localDate LocalDate.
   * @throws NullPointerException If {@code localDate} is {@code null}.
   * @return An instance of date value.
   * @since 2.0.0
   */
  public static DateValue from(LocalDate localDate) {
    return new DateValue(localDate);
  }

  /**
   * Makes an instance of the date value corresponding to now.
   *
   * @return An instance of date value.
   * @since 1.1.0
   */
  public static DateValue now() {
    return from(Calendar.getInstance());
  }

  /**
   * Constructor.
   * 
   * @param year Year.
   * @param month Month.
   * @param dayOfTheMonth Day of the month.
   */
  public DateValue(int year, int month, int dayOfTheMonth) {
    this.dayOfTheMonth = dayOfTheMonth;
    this.month = month;
    this.year = year;
  }

  /**
   * Constructor.
   * 
   * @param date Date in {@code String} format ({@code yyyy-mm-dd}).
   * @throws NullPointerException If {@code date} is {@code null}.
   * @throws ParseException If {@code date} don't respect the {@code yyyy-mm-dd} format.
   */
  public DateValue(String date) throws ParseException {
    if (date == null) {
      throw new NullPointerException(DATE_SHOULD_BE_NOT_NULL);
    }

    if (date.matches(DATE_FORMAT)) {
      year = Integer.parseInt(date.substring(0, 4));
      month = Integer.parseInt(date.substring(5, 7));
      dayOfTheMonth = Integer.parseInt(date.substring(8));
    } else {
      throw new ParseException("date must respect yyyy-mm-dd format", date.length());
    }
  }

  /**
   * Constructor.
   * 
   * @param date Date.
   * @throws NullPointerException If {@code date} is {@code null}.
   */
  public DateValue(Date date) {
    if (date == null) {
      throw new NullPointerException(DATE_SHOULD_BE_NOT_NULL);
    }

    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(date.getTime());

    dayOfTheMonth = calendar.get(Calendar.DAY_OF_MONTH);
    month = calendar.get(Calendar.MONTH) + 1;
    year = calendar.get(Calendar.YEAR);
  }

  /**
   * Constructor.
   *
   * @param calendar Calendar.
   * @throws NullPointerException If {@code calendar} is {@code null}.
   */
  public DateValue(Calendar calendar) {
    if (calendar == null) {
      throw new NullPointerException(DATE_SHOULD_BE_NOT_NULL);
    }

    dayOfTheMonth = calendar.get(Calendar.DAY_OF_MONTH);
    month = calendar.get(Calendar.MONTH) + 1;
    year = calendar.get(Calendar.YEAR);
  }

  /**
   * Constructor.
   *
   * @param localDate LocalDate.
   * @throws NullPointerException If {@code localDate} is {@code null}.
   * @since 2.0.0
   */
  public DateValue(LocalDate localDate) {
    if (localDate == null) {
      throw new NullPointerException(DATE_SHOULD_BE_NOT_NULL);
    }

    dayOfTheMonth = localDate.getDayOfMonth();
    month = localDate.getMonthValue();
    year = localDate.getYear();
  }

  /** {@inheritDoc} */
  @Override
  public DateValue getDate() {
    return this;
  }

  public boolean isMidnight() {
    return true;
  }

  /**
   * Returns the day of the month.
   * 
   * @return The day of the month.
   */
  public int getDayOfTheMonth() {
    return dayOfTheMonth;
  }

  /**
   * Returns the month.
   * 
   * @return The month.
   */
  public int getMonth() {
    return month;
  }

  /**
   * Returns the year.
   * 
   * @return The year.
   */
  public int getYear() {
    return year;
  }

  @Override
  public String toString() {
    return String.format("%4d-%02d-%02d", year, month, dayOfTheMonth);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof DateValue) {
      DateValue dateValue = (DateValue) obj;
      return year == dateValue.year && month == dateValue.month && dayOfTheMonth == dateValue.dayOfTheMonth;
    } else if (obj instanceof DateValueContainer) {
      DateValueContainer value = (DateValueContainer) obj;
      return equals(value.getDate()) && value.isMidnight();
    }
    return false;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + dayOfTheMonth;
    result = prime * result + month;
    result = prime * result + year;
    return result;
  }

  @Override
  public int compareTo(DateValue other) {
    if (year < other.year) {
      return -1;
    }
    else if (year > other.year) {
      return 1;
    }
    else if (month < other.month) {
      return -1;
    }
    else if (month > other.month) {
      return 1;
    }
    else if (dayOfTheMonth < other.dayOfTheMonth) {
      return -1;
    }
    else if (dayOfTheMonth > other.dayOfTheMonth) {
      return 1;
    }
    return 0;
  }

  /**
   * Returns if this date value is before the date value in parameter.
   * @param date The date value to compare to.
   * @return If this date value is before the date value in parameter.
   */
  public boolean isBefore(DateValue date) {
    return compareTo(date) < 0;
  }

  /**
   * Returns if this date value is after the date value in parameter.
   * @param date The date value to compare to.
   * @return If this date value is after the date value in parameter.
   */
  public boolean isAfter(DateValue date) {
    return compareTo(date) > 0;
  }

  /**
   * Moves the date with the value in parameter.
   * @param date Value to move the date.
   * @return The date moved.
   */
  public DateValue move(DateValue date) {
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.YEAR, year);
    calendar.set(Calendar.MONTH, month - 1);
    calendar.set(Calendar.DAY_OF_MONTH, dayOfTheMonth);
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);

    if (date.getYear() != 0) {
      calendar.add(Calendar.YEAR, date.getYear());
    }
    if (date.getMonth() != 0) {
      calendar.add(Calendar.MONTH, date.getMonth());
    }
    if (date.getDayOfTheMonth() != 0) {
      calendar.add(Calendar.DAY_OF_MONTH, date.getDayOfTheMonth());
    }

    return from(calendar);
  }

  /**
   * Moves the date with the value in parameter.
   * @param time Value to move the date.
   * @return The date/time moved.
   */
  public DateTimeValue move(TimeValue time) {
    TimeValue timeValue = TimeValue.of(0, 0);
    TimeValue movedTimeValue = timeValue.move(time);

    int hours = movedTimeValue.getHours();
    int days = hours / 24;
    if (hours > 0) {
      hours -= days * 24;
    }
    else {
      hours += days * 24;
    }
    if (hours < 0) {
      days--;
      hours += 24;
    }

    DateValue dateValue = getDate();
    DateValue movedDateValue = dateValue.move(DateValue.of(0, 0, days));

    return DateTimeValue.of(movedDateValue, TimeValue.of(hours, movedTimeValue.getMinutes(),
                                                         movedTimeValue.getSeconds(), movedTimeValue.getNanoSeconds()));
  }

  /**
   * Moves the date with the value in parameter.
   * @param dateTime Value to move the date.
   * @return The date/time moved.
   */
  public DateTimeValue move(DateTimeValue dateTime) {
    DateValue date = move(dateTime.getDate());
    return date.move(dateTime.getTime());
  }

  /**
   * Returns the reverse of the date.
   * @return The reverse.
   */
  public DateValue reverse() {
    return of(-getYear(), -getMonth(), -getDayOfTheMonth());
  }
}
