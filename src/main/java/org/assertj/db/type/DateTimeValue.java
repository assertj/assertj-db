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
public class DateTimeValue {

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
   */
  public DateTimeValue(DateValue date, TimeValue time) {
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

    date = DateValue.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
    time = TimeValue.of(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),
        calendar.get(Calendar.SECOND), timestamp.getNanos());
  }

  /**
   * Returns the date.
   * 
   * @return The date.
   */
  public DateValue getDate() {
    return date;
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

}
