package org.assertj.db.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import org.assertj.db.api.ValueType;
import org.assertj.db.error.AssertJDBException;
import org.assertj.db.type.DateTimeValue;
import org.assertj.db.type.DateValue;
import org.assertj.db.type.TimeValue;

/**
 * Utility methods related to values.
 * 
 * @author Régis Pouiller
 */
public class Values {

  /**
   * Private constructor.
   */
  private Values() {
    // Empty
  }

  /**
   * Returns if the value is equal to another value in parameter.
   * 
   * @param value The value.
   * @param expected The other value to compare.
   * @return {@code true} if the value is equal to the value in parameter, {@code false} otherwise.
   */
  public static boolean areEqual(Object value, Object expected) {
    switch (ValueType.getType(value)) {
    case BOOLEAN:
      if (expected instanceof Boolean) {
        return areEqual(value, (Boolean) expected);
      }
      break;
    case NUMBER:
      if (expected instanceof Number) {
        return areEqual(value, (Number) expected);
      }
      else if (expected instanceof String) {
        return areEqual(value, (String) expected);
      }
      break;
    case BYTES:
      if (expected instanceof byte[]) {
        return areEqual(value, (byte[]) expected);
      }
      break;
    case TEXT:
      if (expected instanceof String) {
        return areEqual(value, (String) expected);
      }
      break;
    case DATE:
      if (expected instanceof DateValue) {
        return areEqual(value, (DateValue) expected);
      }
      break;
    case TIME:
      if (expected instanceof TimeValue) {
        return areEqual(value, (TimeValue) expected);
      }
      break;
    case DATE_TIME:
      if (expected instanceof DateTimeValue) {
        return areEqual(value, (DateTimeValue) expected);
      }
      break;
    default:
      if (expected == null && value == null) {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns if the value is equal to the {@code Boolean} in parameter.
   * 
   * @param value The value.
   * @param expected The {@code Boolean} to compare.
   * @return {@code true} if the value is equal to the {@code Boolean} parameter, {@code false} otherwise.
   */
  public static boolean areEqual(Object value, Boolean expected) {
    return expected.equals(value);
  }

  /**
   * Returns if the value is equal to the {@code Number} in parameter.
   * 
   * @param value The value.
   * @param expected The {@code Number} to compare.
   * @return {@code true} if the value is equal to the {@code Number} parameter, {@code false} otherwise.
   */
  public static boolean areEqual(Object value, Number expected) {
    // If parameter is a BigInteger,
    // change the actual in BigInteger to compare
    if (expected instanceof BigInteger) {
      BigInteger bi = null;

      if (value instanceof BigInteger) {
        bi = (BigInteger) value;
      } else {
        try {
          bi = new BigInteger("" + value);
        } catch (NumberFormatException e) {
          throw new AssertJDBException("<%s> can not be compared to a BigInteger (<%s>)", value, expected);
        }
      }

      if (bi.compareTo((BigInteger) expected) == 0) {
        return true;
      }
    }
    // If parameter is a BigDecimal,
    // change the value in BigDecimal to compare
    else if (expected instanceof BigDecimal) {
      BigDecimal bd = null;

      if (value instanceof BigDecimal) {
        bd = (BigDecimal) value;
      } else {
        try {
          bd = new BigDecimal("" + value);
        } catch (NumberFormatException e) {
          throw new AssertJDBException("<%s> can not be compared to a BigDecimal (<%s>)", value, expected);
        }
      }

      if (bd.compareTo((BigDecimal) expected) == 0) {
        return true;
      }
    }
    // Otherwise
    // If the value is Float, Double, BigInteger or BigDecimal
    // change the value to compare to make the comparison possible
    // else
    // get the value value in Long to compare
    else {
      Long actualValue = null;

      if (value instanceof Float) {
        if (((Float) value).floatValue() == expected.floatValue()) {
          return true;
        }
      } else if (value instanceof Double) {
        if (((Double) value).doubleValue() == expected.doubleValue()) {
          return true;
        }
      } else if (value instanceof BigInteger) {
        BigInteger bi = new BigInteger("" + expected);
        if (((BigInteger) value).compareTo(bi) == 0) {
          return true;
        }
      } else if (value instanceof BigDecimal) {
        BigDecimal bd = new BigDecimal("" + expected);
        if (((BigDecimal) value).compareTo(bd) == 0) {
          return true;
        }
      } else if (value instanceof Byte) {
        actualValue = ((Byte) value).longValue();
      } else if (value instanceof Short) {
        actualValue = ((Short) value).longValue();
      } else if (value instanceof Integer) {
        actualValue = ((Integer) value).longValue();
      } else if (value instanceof Long) {
        actualValue = (Long) value;
      }

      if (actualValue != null) {
        if (expected instanceof Float) {
          if (actualValue == expected.floatValue()) {
            return true;
          }
        } else if (expected instanceof Double) {
          if (actualValue == expected.doubleValue()) {
            return true;
          }
        } else {
          if (actualValue == expected.longValue()) {
            return true;
          }
        }
      }
    }

    return false;
  }

  /**
   * Returns if the value is equal to the array of {@code byte} in parameter.
   * 
   * @param value The value.
   * @param expected The array of {@code byte} to compare.
   * @return {@code true} if the value is equal to the array of {@code byte} parameter, {@code false} otherwise.
   */
  public static boolean areEqual(Object value, byte[] expected) {
    if (value instanceof byte[]) {
      byte[] bytes = (byte[]) value;
      if (bytes.length != expected.length) {
        return false;
      }
      for (int i = 0; i < bytes.length; i++) {
        if (bytes[i] != expected[i]) {
          return false;
        }
      }
      return true;
    }
    return false;
  }

  /**
   * Returns if the number is equal to the {@code String} representation in parameter.
   * 
   * @param number The number.
   * @param expected The {@code String} representation to compare.
   * @return {@code true} if the number is equal to the {@code String} representation parameter, {@code false}
   *         otherwise.
   * @throws AssertJDBException If it is not possible to compare {@code number} to {@code expected}.
   */
  private static boolean areEqual(Number number, String expected) {
    try {
      if (number instanceof Float) {
        if (((Float) number).floatValue() == Float.parseFloat(expected)) {
          return true;
        }
      } else if (number instanceof Double) {
        if (((Double) number).doubleValue() == Double.parseDouble(expected)) {
          return true;
        }
      } else if (number instanceof BigInteger) {
        BigInteger bi = new BigInteger("" + expected);
        if (((BigInteger) number).compareTo(bi) == 0) {
          return true;
        }
      } else if (number instanceof BigDecimal) {
        BigDecimal bd = new BigDecimal("" + expected);
        if (((BigDecimal) number).compareTo(bd) == 0) {
          return true;
        }
      } else {
        Long actual = null;

        if (number instanceof Byte) {
          actual = ((Byte) number).longValue();
        } else if (number instanceof Short) {
          actual = ((Short) number).longValue();
        } else if (number instanceof Integer) {
          actual = ((Integer) number).longValue();
        } else if (number instanceof Long) {
          actual = ((Long) number).longValue();
        }

        if (actual != null && actual.longValue() == Long.parseLong(expected)) {
          return true;
        }
      }
    } catch (NumberFormatException e) {
      throw new AssertJDBException("<%s> is not correct to compare to <%s>", number, expected);
    }
    return false;
  }

  /**
   * Returns if the value is equal to the {@code String} in parameter.
   * 
   * @param value The value.
   * @param expected The {@code String} to compare.
   * @return {@code true} if the value is equal to the {@code String} parameter, {@code false} otherwise.
   * @throws AssertJDBException If {@code value} is a {@code Number} and it is not possible to compare to
   *           {@code expected}.
   */
  public static boolean areEqual(Object value, String expected) {
    if (value instanceof Number) {
      return areEqual((Number) value, expected);
    }
    return expected.equals(value);
  }

  /**
   * Returns if the value is equal to the {@link DateValue} in parameter.
   * 
   * @param value The value.
   * @param expected The {@link DateValue} to compare.
   * @return {@code true} if the value is equal to the {@link DateValue} parameter, {@code false} otherwise.
   */
  public static boolean areEqual(Object value, DateValue expected) {
    if (value instanceof Date && expected != null) {
      Date date = (Date) value;
      DateValue dateValue = DateValue.from(date);
      return dateValue.getYear() == expected.getYear() && dateValue.getMonth() == expected.getMonth()
          && dateValue.getDayOfTheMonth() == expected.getDayOfTheMonth();
    }
    return false;
  }

  /**
   * Returns if the value is equal to the {@link TimeValue} in parameter.
   * 
   * @param value The value.
   * @param expected The {@link DatTimeValueeValue} to compare.
   * @return {@code true} if the value is equal to the {@link TimeValue} parameter, {@code false} otherwise.
   */
  public static boolean areEqual(Object value, TimeValue expected) {
    if (value instanceof Time && expected != null) {
      Time time = (Time) value;
      TimeValue timeValue = TimeValue.from(time);
      return timeValue.getHour() == expected.getHour() && timeValue.getMinutes() == expected.getMinutes()
          && timeValue.getSeconds() == expected.getSeconds() && timeValue.getNanoSeconds() == expected.getNanoSeconds();
    }
    return false;
  }

  /**
   * Returns if the value is equal to the {@link DateTimeValue} in parameter.
   * 
   * @param value The value.
   * @param expected The {@link DateTimeValue} to compare.
   * @return {@code true} if the value is equal to the {@link DateTimeValue} parameter, {@code false} otherwise.
   */
  public static boolean areEqual(Object value, DateTimeValue expected) {
    if (value instanceof Timestamp && expected != null) {
      Timestamp timestamp = (Timestamp) value;
      DateTimeValue dateTimeValue = DateTimeValue.from(timestamp);
      return dateTimeValue.getDate().getYear() == expected.getDate().getYear()
          && dateTimeValue.getDate().getMonth() == expected.getDate().getMonth()
          && dateTimeValue.getDate().getDayOfTheMonth() == expected.getDate().getDayOfTheMonth()
          && dateTimeValue.getTime().getHour() == expected.getTime().getHour()
          && dateTimeValue.getTime().getMinutes() == expected.getTime().getMinutes()
          && dateTimeValue.getTime().getSeconds() == expected.getTime().getSeconds()
          && dateTimeValue.getTime().getNanoSeconds() == expected.getTime().getNanoSeconds();
    }
    return false;
  }
}
