package org.assertj.db.type;

import java.sql.Date;
import java.text.ParseException;
import java.util.Calendar;

/**
 * This class represents a date value in the database.
 * 
 * @author Régis Pouiller
 * 
 */
public class DateValue implements Comparable<DateValue> {

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
      throw new NullPointerException("date should be not null");
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
      throw new NullPointerException("date should be not null");
    }

    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(date.getTime());

    dayOfTheMonth = calendar.get(Calendar.DAY_OF_MONTH);
    month = calendar.get(Calendar.MONTH) + 1;
    year = calendar.get(Calendar.YEAR);
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
    if (obj instanceof DateTimeValue) {
      DateTimeValue dateTimeValue = (DateTimeValue) obj;
      return year == dateTimeValue.getDate().getYear() && month == dateTimeValue.getDate().getMonth()
          && dayOfTheMonth == dateTimeValue.getDate().getDayOfTheMonth() && 0 == dateTimeValue.getTime().getHour()
          && 0 == dateTimeValue.getTime().getMinutes() && 0 == dateTimeValue.getTime().getSeconds()
          && 0 == dateTimeValue.getTime().getNanoSeconds();
    } else if (obj instanceof DateValue) {
      DateValue dateValue = (DateValue) obj;
      return year == dateValue.year && month == dateValue.month && dayOfTheMonth == dateValue.dayOfTheMonth;
    }
    return false;
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
}
