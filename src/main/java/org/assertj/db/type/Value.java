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
   * NULL value.
   */
  public final static Value NULL = new Value(null, null);

  /**
   * Constructor.
   *
   * @param columnName The name of the column.
   * @param value      The value.
   */
  Value(String columnName, Object value) {
    this.columnName = columnName;
    this.value = value;
  }

  /**
   * Returns the name of the column.
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
}
