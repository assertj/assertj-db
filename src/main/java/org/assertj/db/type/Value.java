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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * Value in a {@link Row} or a {@link Column}.
 * <p>
 * Note : you never instantiate directly this class. You will get an object of this class from a {@link Row} or a
 * {@link Column}.
 * </p>
 *
 * @author Régis Pouiller
 */
public class Value implements DbElement {

  /**
   * The name of the column.
   */
  private final String columnName;
  /**
   * The value.
   */
  private final Object value;

  /**
   * The type of value.
   */
  private final ValueType valueType;

  /**
   * NULL value.
   */
  public final static Value NULL = new Value(null, null);

  /**
   * Returns the type of the actual value (data).
   *
   * @param object The actual object contained in the value.
   * @return The type of the actual value
   */
  static ValueType getType(Object object) {
    if (object instanceof byte[]) {
      return ValueType.BYTES;
    }
    if (object instanceof Boolean) {
      return ValueType.BOOLEAN;
    }
    if (object instanceof String) {
      return ValueType.TEXT;
    }
    if (object instanceof Date) {
      return ValueType.DATE;
    }
    if (object instanceof Time) {
      return ValueType.TIME;
    }
    if (object instanceof Timestamp) {
      return ValueType.DATE_TIME;
    }
    if (object instanceof java.util.UUID) {
      return ValueType.UUID;
    }
    if (object instanceof Byte
        || object instanceof Short
        || object instanceof Integer
        || object instanceof Long
        || object instanceof Float
        || object instanceof Double
        || object instanceof BigDecimal
        || object instanceof BigInteger) {

      return ValueType.NUMBER;
    }
    return ValueType.NOT_IDENTIFIED;
  }

  /**
   * Constructor.
   *
   * @param columnName The name of the column.
   * @param value      The value.
   */
  Value(String columnName, Object value) {
    this.columnName = columnName;
    this.value = value;
    valueType = getType(value);
  }

  /**
   * Returns the name of the column.
   *
   * @return The name of the column.
   */
  public String getColumnName() {
    return columnName;
  }

  /**
   * Returns the value.
   *
   * @return The value.
   */
  public Object getValue() {
    return value;
  }

  /**
   * Returns the type of the value.
   *
   * @return The type of the value.
   */
  public ValueType getValueType() {
    return valueType;
  }

  /**
   * Returns the representation of the type of the value.
   *
   * @return The representation of the type of the value.
   */
  public String getValueTypeRepresentation() {
    if (value == null || valueType != ValueType.NOT_IDENTIFIED) {
      return valueType.toString();
    }
    return valueType + " : " + value.getClass();
  }

  /**
   * Returns if any kind of comparison is possible between the value and the object in parameter.
   *
   * @param object The potential object for comparison.
   * @return {@code true} is comparison is possible.
   */
  public boolean isComparisonPossible(Object object) {
    switch (valueType) {
    case BYTES:
      return (object instanceof byte[]);
    case BOOLEAN:
      return (object instanceof Boolean);
    case TEXT:
      return (object instanceof String);
    case DATE:
    case DATE_TIME:
      return (object instanceof DateValue ||object instanceof DateTimeValue ||  object instanceof String);
    case TIME:
      return (object instanceof TimeValue || object instanceof String);
    case NUMBER:
      return (object instanceof Number || object instanceof String);
    case UUID:
      return (object instanceof UUID || object instanceof String);
    case NOT_IDENTIFIED:
      if (value == null) {
        return object == null;
      }
      else if (object != null) {
        return value.getClass().equals(object.getClass());
      }
    }
    return false;
  }
}
