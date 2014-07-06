package org.assertj.db.type;

import java.sql.Time;
import java.text.ParseException;
import java.util.Calendar;

/**
 * This class represents a time value in the database.
 * 
 * @author Régis Pouiller
 * 
 */
public class TimeValue {

  /**
   * Hour.
   */
  private final int hour;
  /**
   * Minutes.
   */
  private final int minutes;
  /**
   * Seconds.
   */
  private final int seconds;
  /**
   * Nanoseconds.
   */
  private final int nanoSeconds;
  /**
   * Indicates where there are the digits for {@code String} for {@link TimeValue#TimeValue(String)}.
   */
  private static final String TIME_FORMAT = "\\d\\d:\\d\\d";
  /**
   * Indicates where there are the digits in style with seconds for {@code String} for
   * {@link TimeValue#TimeValue(String)}.
   */
  private static final String TIME_FORMAT_WITH_SECONDS = "\\d\\d:\\d\\d:\\d\\d";
  /**
   * Indicates where there are the digits in style with nanoseconds for {@code String} for
   * {@link TimeValue#TimeValue(String)}.
   */
  private static final String TIME_FORMAT_WITH_NANO = "\\d\\d:\\d\\d:\\d\\d.\\d\\d\\d\\d\\d\\d\\d\\d\\d";

  /**
   * Makes an instance of time value from a hour, minutes, seconds and nanoseconds.
   * 
   * @param hour Hour.
   * @param minutes Minutes.
   * @param seconds Seconds.
   * @param nanoSeconds Nanoseconds.
   * @return An instance of time value.
   */
  public static TimeValue of(int hour, int minutes, int seconds, int nanoSeconds) {
    return new TimeValue(hour, minutes, seconds, nanoSeconds);
  }

  /**
   * Makes an instance of time value from a hour, minutes and seconds.
   * 
   * @param hour Hour.
   * @param minutes Minutes.
   * @param seconds Seconds.
   * @return An instance of time value.
   */
  public static TimeValue of(int hour, int minutes, int seconds) {
    return new TimeValue(hour, minutes, seconds);
  }

  /**
   * Makes an instance of time value from a hour and minutes.
   * 
   * @param hour Hour.
   * @param minutes Minutes.
   * @return An instance of time value.
   */
  public static TimeValue of(int hour, int minutes) {
    return new TimeValue(hour, minutes);
  }

  /**
   * Makes an instance of time value from a {@code String} in {@code hh:mm}, {@code hh:mm:ss} or
   * {@code hh:mm:ss.nnnnnnnnn} format.
   * 
   * @param time Time in {@code String} format ({@code hh:mm}, {@code hh:mm:ss} or {@code hh:mm:ss.nnnnnnnnn}).
   * @return An instance of time value.
   * @throws NullPointerException If {@code time} is {@code null}.
   * @throws ParseException If {@code time} don't respect the {@code hh:mm}, {@code hh:mm:ss} or
   *           {@code hh:mm:ss.nnnnnnnnn} format.
   */
  public static TimeValue parse(String date) throws ParseException {
    return new TimeValue(date);
  }

  /**
   * Makes an instance of time value from a {@link Time}.
   * 
   * @param time Time.
   * @throws NullPointerException If {@code time} is {@code null}.
   * @return An instance of time value.
   */
  public static TimeValue from(Time time) {
    return new TimeValue(time);
  }

  /**
   * Constructor.
   * 
   * @param hour Hour.
   * @param minutes Minutes.
   * @param seconds Seconds.
   * @param nanoSeconds Nanoseconds.
   */
  public TimeValue(int hour, int minutes, int seconds, int nanoSeconds) {
    this.hour = hour;
    this.minutes = minutes;
    this.seconds = seconds;
    this.nanoSeconds = nanoSeconds;
  }

  /**
   * Constructor.
   * 
   * @param hour Hour.
   * @param minutes Minutes.
   * @param seconds Seconds.
   */
  public TimeValue(int hour, int minutes, int seconds) {
    this(hour, minutes, seconds, 0);
  }

  /**
   * Constructor.
   * 
   * @param hour Hour.
   * @param minutes Minutes.
   */
  public TimeValue(int hour, int minutes) {
    this(hour, minutes, 0, 0);
  }

  /**
   * Constructor.
   * 
   * @param time Time in {@code String} format ({@code hh:mm}, {@code hh:mm:ss} or {@code hh:mm:ss.nnnnnnnnn}).
   * @throws NullPointerException If {@code time} is {@code null}.
   * @throws ParseException If {@code time} don't respect the {@code hh:mm}, {@code hh:mm:ss} or
   *           {@code hh:mm:ss.nnnnnnnnn} format.
   */
  public TimeValue(String time) throws ParseException {
    if (time == null) {
      throw new NullPointerException("time should be not null");
    }

    if (time.matches(TIME_FORMAT)) {
      hour = Integer.parseInt(time.substring(0, 2));
      minutes = Integer.parseInt(time.substring(3));
      seconds = 0;
      nanoSeconds = 0;
    } else if (time.matches(TIME_FORMAT_WITH_SECONDS)) {
      hour = Integer.parseInt(time.substring(0, 2));
      minutes = Integer.parseInt(time.substring(3, 5));
      seconds = Integer.parseInt(time.substring(6));
      nanoSeconds = 0;
    } else if (time.matches(TIME_FORMAT_WITH_NANO)) {
      hour = Integer.parseInt(time.substring(0, 2));
      minutes = Integer.parseInt(time.substring(3, 5));
      seconds = Integer.parseInt(time.substring(6, 8));
      nanoSeconds = Integer.parseInt(time.substring(9));
    } else {
      throw new ParseException("time must respect hh:mm, hh:mm:ss or hh:mm:ss.nnnnnnnnn format", time.length());
    }
  }

  /**
   * Constructor.
   * 
   * @param time Time.
   * @throws NullPointerException If {@code time} is {@code null}.
   */
  public TimeValue(Time time) {
    if (time == null) {
      throw new NullPointerException("time should be not null");
    }

    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(time.getTime());

    hour = calendar.get(Calendar.HOUR_OF_DAY);
    minutes = calendar.get(Calendar.MINUTE);
    seconds = calendar.get(Calendar.SECOND);
    nanoSeconds = 0;
  }

  /**
   * Returns the hour.
   * 
   * @return The hour.
   */
  public int getHour() {
    return hour;
  }

  /**
   * Returns the minutes.
   * 
   * @return The minutes.
   */
  public int getMinutes() {
    return minutes;
  }

  /**
   * Returns the seconds.
   * 
   * @return The seconds.
   */
  public int getSeconds() {
    return seconds;
  }

  /**
   * Returns the nanoseconds.
   * 
   * @return The nanoseconds.
   */
  public int getNanoSeconds() {
    return nanoSeconds;
  }

  @Override
  public String toString() {
    return String.format("%02d:%02d:%02d.%09d", hour, minutes, seconds, nanoSeconds);
  }

}
