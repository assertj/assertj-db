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
 * Copyright 2012-2016 the original author or authors.
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
   * Hours.
   */
  private final int hours;
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
   * Makes an instance of time value from a hours, minutes, seconds and nanoseconds.
   * 
   * @param hours Hours.
   * @param minutes Minutes.
   * @param seconds Seconds.
   * @param nanoSeconds Nanoseconds.
   * @return An instance of time value.
   */
  public static TimeValue of(int hours, int minutes, int seconds, int nanoSeconds) {
    return new TimeValue(hours, minutes, seconds, nanoSeconds);
  }

  /**
   * Makes an instance of time value from a hours, minutes and seconds.
   * 
   * @param hours Hours.
   * @param minutes Minutes.
   * @param seconds Seconds.
   * @return An instance of time value.
   */
  public static TimeValue of(int hours, int minutes, int seconds) {
    return new TimeValue(hours, minutes, seconds);
  }

  /**
   * Makes an instance of time value from a hours and minutes.
   * 
   * @param hours Hours.
   * @param minutes Minutes.
   * @return An instance of time value.
   */
  public static TimeValue of(int hours, int minutes) {
    return new TimeValue(hours, minutes);
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
   * Makes an instance of time value from a {@link Calendar}.
   *
   * @param calendar Calendar.
   * @throws NullPointerException If {@code calendar} is {@code null}.
   * @return An instance of time value.
   * @since 1.1.0
   */
  public static TimeValue from(Calendar calendar) {
    return new TimeValue(calendar);
  }

  /**
   * Makes an instance of the time value corresponding to now.
   *
   * @return An instance of time value.
   * @since 1.1.0
   */
  public static TimeValue now() {
    return from(Calendar.getInstance());
  }

  /**
   * Constructor.
   * 
   * @param hours Hours.
   * @param minutes Minutes.
   * @param seconds Seconds.
   * @param nanoSeconds Nanoseconds.
   */
  public TimeValue(int hours, int minutes, int seconds, int nanoSeconds) {
    this.hours = hours;
    this.minutes = minutes;
    this.seconds = seconds;
    this.nanoSeconds = nanoSeconds;
  }

  /**
   * Constructor.
   * 
   * @param hours Hours.
   * @param minutes Minutes.
   * @param seconds Seconds.
   */
  public TimeValue(int hours, int minutes, int seconds) {
    this(hours, minutes, seconds, 0);
  }

  /**
   * Constructor.
   * 
   * @param hours Hours.
   * @param minutes Minutes.
   */
  public TimeValue(int hours, int minutes) {
    this(hours, minutes, 0, 0);
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
      hours = Integer.parseInt(time.substring(0, 2));
      minutes = Integer.parseInt(time.substring(3));
      seconds = 0;
      nanoSeconds = 0;
    } else if (time.matches(TIME_FORMAT_WITH_SECONDS)) {
      hours = Integer.parseInt(time.substring(0, 2));
      minutes = Integer.parseInt(time.substring(3, 5));
      seconds = Integer.parseInt(time.substring(6));
      nanoSeconds = 0;
    } else if (time.matches(TIME_FORMAT_WITH_NANO)) {
      hours = Integer.parseInt(time.substring(0, 2));
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

    hours = calendar.get(Calendar.HOUR_OF_DAY);
    minutes = calendar.get(Calendar.MINUTE);
    seconds = calendar.get(Calendar.SECOND);
    nanoSeconds = calendar.get(Calendar.MILLISECOND) * 1000000;
  }

  /**
   * Constructor.
   *
   * @param calendar Calendar.
   * @throws NullPointerException If {@code calendar} is {@code null}.
   */
  public TimeValue(Calendar calendar) {
    if (calendar == null) {
      throw new NullPointerException("time should be not null");
    }

    hours = calendar.get(Calendar.HOUR_OF_DAY);
    minutes = calendar.get(Calendar.MINUTE);
    seconds = calendar.get(Calendar.SECOND);
    nanoSeconds = calendar.get(Calendar.MILLISECOND) * 1000000;
  }

  /**
   * Returns the hours.
   * 
   * @return The hours.
   */
  public int getHours() {
    return hours;
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
    return String.format("%02d:%02d:%02d.%09d", hours, minutes, seconds, nanoSeconds);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof TimeValue) {
      TimeValue timeValue = (TimeValue) obj;
      return hours == timeValue.hours && minutes == timeValue.minutes && seconds == timeValue.seconds
          && nanoSeconds == timeValue.nanoSeconds;
    }
    return false;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + hours;
    result = prime * result + minutes;
    result = prime * result + nanoSeconds;
    result = prime * result + seconds;
    return result;
  }

  @Override
  public int compareTo(TimeValue other) {
    if (hours < other.hours) {
      return -1;
    }
    else if (hours > other.hours) {
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

  /**
   * Moves the time with the value in parameter.
   * @param time Value to move the date.
   * @return The date/time moved.
   */
  public TimeValue move(TimeValue time) {
    int thisHours = this.hours;
    int thisMinutes = this.minutes;
    int thisSeconds = this.seconds;
    int thisNanoSeconds = this.nanoSeconds;

    int hours = time.getHours();
    int minutes = time.getMinutes();
    int seconds = time.getSeconds();
    int nanoSeconds = time.getNanoSeconds();

    if (nanoSeconds >= 0 || thisNanoSeconds >= -nanoSeconds) {
      thisNanoSeconds += nanoSeconds;
    }
    else {
      thisNanoSeconds += 1000000000 + nanoSeconds;
      seconds--;
    }
    if (seconds >= 0 || thisSeconds >= -seconds) {
      thisSeconds += seconds;
    }
    else {
      thisSeconds += 60 + seconds;
      minutes--;
    }
    if (minutes >= 0 || thisMinutes >= -minutes) {
      thisMinutes += minutes;
    }
    else {
      thisMinutes += 60 + minutes;
      hours--;
    }
    thisHours += hours;

    return of(thisHours, thisMinutes, thisSeconds, thisNanoSeconds);
  }

  /**
   * Returns the reverse of the time.
   * @return The reverse.
   */
  public TimeValue reverse() {
    return of(-getHours(), -getMinutes(), -getSeconds(), -getNanoSeconds());
  }
}
