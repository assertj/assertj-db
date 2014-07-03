package org.assertj.db.util;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.assertj.db.api.ValueType;
import org.assertj.db.error.AssertJDBException;

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
   * Returns if the value is equal to the {@code String} in parameter.
   * 
   * @param value The value.
   * @param expected The {@code String} to compare.
   * @return {@code true} if the value is equal to the {@code String} parameter, {@code false} otherwise.
   */
  public static boolean areEqual(Object value, String expected) {
    return expected.equals(value);
  }

}
