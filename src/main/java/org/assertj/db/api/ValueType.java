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
 * Copyright 2012-2014 the original author or authors.
 */
package org.assertj.db.api;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import org.assertj.db.type.DateTimeValue;
import org.assertj.db.type.DateValue;
import org.assertj.db.type.TimeValue;

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
   * @param value The actual value
   * @return The type of the actual value
   */
  public static ValueType getType(Object value) {
    if (value instanceof byte[]) {
      return BYTES;
    }
    if (value instanceof Boolean) {
      return BOOLEAN;
    }
    if (value instanceof String) {
      return TEXT;
    }
    if (value instanceof Date) {
      return DATE;
    }
    if (value instanceof Time) {
      return TIME;
    }
    if (value instanceof Timestamp) {
      return DATE_TIME;
    }
    if (value instanceof Byte
        || value instanceof Short
        || value instanceof Integer
        || value instanceof Long
        || value instanceof Float
        || value instanceof BigDecimal) {

      return NUMBER;
    }
    return NOT_IDENTIFIED;
  }

  /**
   * Returns the types which are possible for the actual value (data) for the comparison with an expected value.
   * 
   * @param expected The expected value
   * @return The possible types of the actual value
   */
  public static ValueType[] getPossibleTypesForComparison(Object expected) {
    if (expected instanceof byte[]) {
      return new ValueType[] { BYTES };
    }
    if (expected instanceof Boolean) {
      return new ValueType[] { BOOLEAN };
    }
    if (expected instanceof String) {
      return new ValueType[] { ValueType.TEXT, ValueType.NUMBER, ValueType.DATE, ValueType.TIME, ValueType.DATE_TIME };
    }
    if (expected instanceof DateValue) {
      return new ValueType[] { ValueType.DATE, ValueType.DATE_TIME };
    }
    if (expected instanceof TimeValue) {
      return new ValueType[] { ValueType.TIME };
    }
    if (expected instanceof DateTimeValue) {
      return new ValueType[] { DATE_TIME };
    }
    if (expected instanceof Number) {
      return new ValueType[] { NUMBER };
    }
    return new ValueType[] { NOT_IDENTIFIED };
  }
}
