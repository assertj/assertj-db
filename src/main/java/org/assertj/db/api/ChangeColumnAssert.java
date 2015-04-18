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

import org.assertj.db.api.assertions.AssertOnColumnName;
import org.assertj.db.api.assertions.AssertOnColumnOfChangeEquality;
import org.assertj.db.api.assertions.AssertOnColumnType;
import org.assertj.db.api.assertions.AssertOnModifiedColumn;
import org.assertj.db.api.assertions.impl.AssertionsOnColumnName;
import org.assertj.db.api.assertions.impl.AssertionsOnColumnOfChangeEquality;
import org.assertj.db.api.assertions.impl.AssertionsOnColumnOfChangeType;
import org.assertj.db.api.assertions.impl.AssertionsOnModifiedColumn;
import org.assertj.db.api.navigation.ColumnAssert;
import org.assertj.db.api.origin.OriginWithValuesFromColumn;
import org.assertj.db.type.DateTimeValue;
import org.assertj.db.type.DateValue;
import org.assertj.db.type.TimeValue;
import org.assertj.db.type.ValueType;

/**
 * Assertion methods for a {@code Column} of a {@code Change}.
 *
 * @author Régis Pouiller
 */
public class ChangeColumnAssert
        extends AbstractAssertWithOriginWithColumnsAndRowsFromChange<ChangeColumnAssert, ChangeAssert>
        implements ColumnAssert,
                   OriginWithValuesFromColumn,
                   AssertOnColumnOfChangeEquality<ChangeColumnAssert>,
                   AssertOnModifiedColumn<ChangeColumnAssert>,
                   AssertOnColumnName<ChangeColumnAssert>,
                   AssertOnColumnType<ChangeColumnAssert> {

  /**
   * The name of the column.
   */
  private final String columnName;
  /**
   * The actual value at start point.
   */
  private final Object valueAtStartPoint;

  /**
   * The actual value at end point.
   */
  private final Object valueAtEndPoint;

  /**
   * The assertion on the value at start point.
   */
  private ChangeColumnValueAssert changeColumnValueAssertAtStartPoint;
  /**
   * The assertion on the value at end point.
   */
  private ChangeColumnValueAssert changeColumnValueAssertAtEndPoint;

  /**
   * Constructor.
   *
   * @param columnName The column name.
   * @param origin The assertion of {@link org.assertj.db.api.origin.Origin}.
   * @param valueAtStartPoint The value at start point.
   * @param valueAtEndPoint The value at end point.
   */
  ChangeColumnAssert(ChangeAssert origin, String columnName, Object valueAtStartPoint, Object valueAtEndPoint) {
    super(ChangeColumnAssert.class, origin);
    this.columnName = columnName;
    this.valueAtStartPoint = valueAtStartPoint;
    this.valueAtEndPoint = valueAtEndPoint;
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnValueAssert valueAtStartPoint() {
    if (changeColumnValueAssertAtStartPoint == null) {
      String string = "Value at start point of " + info.descriptionText();
      changeColumnValueAssertAtStartPoint = new ChangeColumnValueAssert(this, valueAtStartPoint).as(string);
    }
    return changeColumnValueAssertAtStartPoint;
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnValueAssert valueAtEndPoint() {
    if (changeColumnValueAssertAtEndPoint == null) {
      String string = "Value at end point of " + info.descriptionText();
      changeColumnValueAssertAtEndPoint = new ChangeColumnValueAssert(this, valueAtEndPoint).as(string);
    }
    return changeColumnValueAssertAtEndPoint;
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnAssert isModified() {
    return AssertionsOnModifiedColumn.isModified(myself, info, valueAtStartPoint, valueAtEndPoint);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnAssert isNotModified() {
    return AssertionsOnModifiedColumn.isNotModified(myself, info, valueAtStartPoint, valueAtEndPoint);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnAssert hasValuesEqualTo(Boolean expected) {
    return AssertionsOnColumnOfChangeEquality
            .hasValuesEqualTo(myself, info, valueAtStartPoint, valueAtEndPoint, expected);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnAssert hasValuesEqualTo(Boolean expectedAtStartPoint, Boolean expectedAtEndPoint) {
    return AssertionsOnColumnOfChangeEquality
            .hasValuesEqualTo(myself, info, valueAtStartPoint, valueAtEndPoint, expectedAtStartPoint,
                              expectedAtEndPoint);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnAssert hasValuesEqualTo(Number expected) {
    return AssertionsOnColumnOfChangeEquality
            .hasValuesEqualTo(myself, info, valueAtStartPoint, valueAtEndPoint, expected);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnAssert hasValuesEqualTo(Number expectedAtStartPoint, Number expectedAtEndPoint) {
    return AssertionsOnColumnOfChangeEquality
            .hasValuesEqualTo(myself, info, valueAtStartPoint, valueAtEndPoint, expectedAtStartPoint,
                              expectedAtEndPoint);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnAssert hasValuesEqualTo(byte[] expected) {
    return AssertionsOnColumnOfChangeEquality
            .hasValuesEqualTo(myself, info, valueAtStartPoint, valueAtEndPoint, expected);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnAssert hasValuesEqualTo(byte[] expectedAtStartPoint, byte[] expectedAtEndPoint) {
    return AssertionsOnColumnOfChangeEquality
            .hasValuesEqualTo(myself, info, valueAtStartPoint, valueAtEndPoint, expectedAtStartPoint,
                              expectedAtEndPoint);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnAssert hasValuesEqualTo(String expected) {
    return AssertionsOnColumnOfChangeEquality
            .hasValuesEqualTo(myself, info, valueAtStartPoint, valueAtEndPoint, expected);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnAssert hasValuesEqualTo(String expectedAtStartPoint, String expectedAtEndPoint) {
    return AssertionsOnColumnOfChangeEquality
            .hasValuesEqualTo(myself, info, valueAtStartPoint, valueAtEndPoint, expectedAtStartPoint,
                              expectedAtEndPoint);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnAssert hasValuesEqualTo(DateValue expected) {
    return AssertionsOnColumnOfChangeEquality
            .hasValuesEqualTo(myself, info, valueAtStartPoint, valueAtEndPoint, expected);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnAssert hasValuesEqualTo(DateValue expectedAtStartPoint, DateValue expectedAtEndPoint) {
    return AssertionsOnColumnOfChangeEquality
            .hasValuesEqualTo(myself, info, valueAtStartPoint, valueAtEndPoint, expectedAtStartPoint,
                              expectedAtEndPoint);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnAssert hasValuesEqualTo(TimeValue expected) {
    return AssertionsOnColumnOfChangeEquality
            .hasValuesEqualTo(myself, info, valueAtStartPoint, valueAtEndPoint, expected);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnAssert hasValuesEqualTo(TimeValue expectedAtStartPoint, TimeValue expectedAtEndPoint) {
    return AssertionsOnColumnOfChangeEquality
            .hasValuesEqualTo(myself, info, valueAtStartPoint, valueAtEndPoint, expectedAtStartPoint,
                              expectedAtEndPoint);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnAssert hasValuesEqualTo(DateTimeValue expected) {
    return AssertionsOnColumnOfChangeEquality
            .hasValuesEqualTo(myself, info, valueAtStartPoint, valueAtEndPoint, expected);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnAssert hasValuesEqualTo(DateTimeValue expectedAtStartPoint, DateTimeValue expectedAtEndPoint) {
    return AssertionsOnColumnOfChangeEquality
            .hasValuesEqualTo(myself, info, valueAtStartPoint, valueAtEndPoint, expectedAtStartPoint,
                              expectedAtEndPoint);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnAssert hasColumnName(String columnName) {
    return AssertionsOnColumnName.hasColumnName(myself, info, this.columnName, columnName);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnAssert isOfType(ValueType expected, boolean lenient) {
    return AssertionsOnColumnOfChangeType.isOfType(myself, info, valueAtStartPoint, valueAtEndPoint, expected, lenient);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnAssert isOfAnyOfTypes(ValueType... expected) {
    return AssertionsOnColumnOfChangeType.isOfAnyOfTypes(myself, info, valueAtStartPoint, valueAtEndPoint, expected);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnAssert isNumber(boolean lenient) {
    return AssertionsOnColumnOfChangeType.isNumber(myself, info, valueAtStartPoint, valueAtEndPoint, lenient);
  }
  /** {@inheritDoc} */
  @Override
  public ChangeColumnAssert isBoolean(boolean lenient) {
    return AssertionsOnColumnOfChangeType.isBoolean(myself, info, valueAtStartPoint, valueAtEndPoint, lenient);
  }
  /** {@inheritDoc} */
  @Override
  public ChangeColumnAssert isDate(boolean lenient) {
    return AssertionsOnColumnOfChangeType.isDate(myself, info, valueAtStartPoint, valueAtEndPoint, lenient);
  }
  /** {@inheritDoc} */
  @Override
  public ChangeColumnAssert isTime(boolean lenient) {
    return AssertionsOnColumnOfChangeType.isTime(myself, info, valueAtStartPoint, valueAtEndPoint, lenient);
  }
  /** {@inheritDoc} */
  @Override
  public ChangeColumnAssert isDateTime(boolean lenient) {
    return AssertionsOnColumnOfChangeType.isDateTime(myself, info, valueAtStartPoint, valueAtEndPoint, lenient);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnAssert isBytes(boolean lenient) {
    return AssertionsOnColumnOfChangeType.isBytes(myself, info, valueAtStartPoint, valueAtEndPoint, lenient);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnAssert isText(boolean lenient) {
    return AssertionsOnColumnOfChangeType.isText(myself, info, valueAtStartPoint, valueAtEndPoint, lenient);
  }
}
