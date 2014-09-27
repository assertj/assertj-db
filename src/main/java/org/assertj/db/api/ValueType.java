package org.assertj.db.api;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * Enumeration of the different type of value that are in the database.
 * 
 * @author Régis Pouiller
 * 
 */
public enum ValueType {

  /**
   * Bytes type.
   */
  BYTES,
  /**
   * Boolean type.
   */
  BOOLEAN,
  /**
   * Text type (CHAR or VARCHAR column).
   */
  TEXT,
  /**
   * Date type.
   */
  DATE,
  /**
   * Time type.
   */
  TIME,
  /**
   * Date/time type (TIMESTAMP column).
   */
  DATE_TIME,
  /**
   * Number type (INT, SMALLINT, TINYINT, BIGINT, REAL or DECIMAL column).
   */
  NUMBER,
  /**
   * Not identified type : null value for example.
   */
  NOT_IDENTIFIED;

  /**
   * Returns the type of the actual value (data).
   * 
   * @param pValue The actual value
   * @return The type of the actual value
   */
  public static ValueType getType(final Object pValue) {
    if (pValue instanceof byte[]) {
      return BYTES;
    }
    if (pValue instanceof Boolean) {
      return BOOLEAN;
    }
    if (pValue instanceof String) {
      return TEXT;
    }
    if (pValue instanceof Date) {
      return DATE;
    }
    if (pValue instanceof Time) {
      return TIME;
    }
    if (pValue instanceof Timestamp) {
      return DATE_TIME;
    }
    if (pValue instanceof Byte
        || pValue instanceof Short
        || pValue instanceof Integer
        || pValue instanceof Long
        || pValue instanceof Float
        || pValue instanceof BigDecimal) {

      return NUMBER;
    }
    return NOT_IDENTIFIED;
  }
}
