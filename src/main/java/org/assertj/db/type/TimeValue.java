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

import java.sql.Time;
import java.text.ParseException;
import java.util.Calendar;

/**
 * This class represents a time value in the database.
 * 
 * @author Régis Pouiller
 * 
 */
public class TimeValue implements Comparable<TimeValue> {

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
  public static TimeValue parse(String time) throws ParseException {
    return new TimeValue(time);
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

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof TimeValue) {
      TimeValue timeValue = (TimeValue) obj;
      return hour == timeValue.hour && minutes == timeValue.minutes && seconds == timeValue.seconds
          && nanoSeconds == timeValue.nanoSeconds;
    }
    return false;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + hour;
    result = prime * result + minutes;
    result = prime * result + nanoSeconds;
    result = prime * result + seconds;
    return result;
  }

  @Override
  public int compareTo(TimeValue other) {
    if (hour < other.hour) {
      return -1;
    }
    else if (hour > other.hour) {
      return 1;
    }
    else if (minutes < other.minutes) {
      return -1;
    }
    else if (minutes > other.minutes) {
      return 1;
    }
    else if (seconds < other.seconds) {
      return -1;
    }
    else if (seconds > other.seconds) {
      return 1;
    }
    else if (nanoSeconds < other.nanoSeconds) {
      return -1;
    }
    else if (nanoSeconds > other.nanoSeconds) {
      return 1;
    }
    return 0;
  }

  /**
   * Returns if this time value is before the time value in parameter.
   * @param time The time value to compare to.
   * @return If this time value is before the time value in parameter.
   */
  public boolean isBefore(TimeValue time) {
    return compareTo(time) == -1;
  }

  /**
   * Returns if this time value is after the time value in parameter.
   * @param time The time value to compare to.
   * @return If this time value is after the time value in parameter.
   */
  public boolean isAfter(TimeValue time) {
    return compareTo(time) == 1;
  }

}
