/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2015-2024 the original author or authors.
 */
package org.assertj.db.api;

import static org.assertj.db.util.Descriptions.getColumnValueAtEndPointDescription;
import static org.assertj.db.util.Descriptions.getColumnValueAtStartPointDescription;

import java.util.UUID;

import org.assertj.db.api.assertions.AssertOnColumnClass;
import org.assertj.db.api.assertions.AssertOnColumnName;
import org.assertj.db.api.assertions.AssertOnColumnOfChangeEquality;
import org.assertj.db.api.assertions.AssertOnColumnType;
import org.assertj.db.api.assertions.AssertOnModifiedColumn;
import org.assertj.db.api.assertions.impl.AssertionsOnColumnName;
import org.assertj.db.api.assertions.impl.AssertionsOnColumnOfChangeClass;
import org.assertj.db.api.assertions.impl.AssertionsOnColumnOfChangeEquality;
import org.assertj.db.api.assertions.impl.AssertionsOnColumnOfChangeType;
import org.assertj.db.api.assertions.impl.AssertionsOnModifiedColumn;
import org.assertj.db.navigation.PositionWithPoints;
import org.assertj.db.navigation.element.ColumnElement;
import org.assertj.db.navigation.origin.OriginWithValuesFromColumn;
import org.assertj.db.type.DateTimeValue;
import org.assertj.db.type.DateValue;
import org.assertj.db.type.TimeValue;
import org.assertj.db.type.Value;
import org.assertj.db.type.ValueType;

/**
 * Assertion methods for a {@code Column} of a {@code Change}.
 *
 * @author Régis Pouiller
 * @author Otoniel Isidoro
 */
public class ChangeColumnAssert
  extends AbstractAssertWithOriginWithColumnsAndRowsFromChange<ChangeColumnAssert, ChangeAssert>
  implements ColumnElement,
  OriginWithValuesFromColumn<ChangesAssert, ChangeAssert, ChangeColumnAssert, ChangeRowAssert, ChangeColumnValueAssert>,
  AssertOnColumnClass<ChangeColumnAssert>,
  AssertOnColumnOfChangeEquality<ChangeColumnAssert>,
  AssertOnModifiedColumn<ChangeColumnAssert>,
  AssertOnColumnName<ChangeColumnAssert>,
  AssertOnColumnType<ChangeColumnAssert> {

  /**
   * The name of the column.
   */
  protected final String columnName;
  /**
   * The actual value at start point.
   */
  protected final Value valueAtStartPoint;

  /**
   * The actual value at end point.
   */
  protected final Value valueAtEndPoint;

  /**
   * Position of navigation to row.
   */
  private final PositionWithPoints<ChangeColumnAssert, ChangeColumnValueAssert, Value> valuePosition;

  /**
   * Constructor.
   *
   * @param columnName        The column name.
   * @param origin            The assertion of {@link org.assertj.db.navigation.origin.Origin}.
   * @param valueAtStartPoint The value at start point.
   * @param valueAtEndPoint   The value at end point.
   */
  public ChangeColumnAssert(ChangeAssert origin, String columnName, Value valueAtStartPoint, Value valueAtEndPoint) {
    super(ChangeColumnAssert.class, origin);
    this.columnName = columnName;
    this.valueAtStartPoint = valueAtStartPoint;
    this.valueAtEndPoint = valueAtEndPoint;
    valuePosition = new PositionWithPoints<ChangeColumnAssert, ChangeColumnValueAssert, Value>(this, ChangeColumnValueAssert.class, Value.class, valueAtStartPoint, valueAtEndPoint) {

      @Override
      protected String getDescriptionAtStartPoint() {
        return getColumnValueAtStartPointDescription(info);
      }

      @Override
      protected String getDescriptionAtEndPoint() {
        return getColumnValueAtEndPointDescription(info);
      }
    };
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeColumnValueAssert valueAtStartPoint() {
    return valuePosition.getInstanceAtStartPoint();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeColumnValueAssert valueAtEndPoint() {
    return valuePosition.getInstanceAtEndPoint();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeColumnAssert isModified() {
    return AssertionsOnModifiedColumn.isModified(myself, info, valueAtStartPoint, valueAtEndPoint);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeColumnAssert isNotModified() {
    return AssertionsOnModifiedColumn.isNotModified(myself, info, valueAtStartPoint, valueAtEndPoint);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeColumnAssert hasValues(Object expected) {
    return AssertionsOnColumnOfChangeEquality
      .hasValues(myself, info, valueAtStartPoint, valueAtEndPoint, expected);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeColumnAssert hasValues(Object expectedAtStartPoint, Object expectedAtEndPoint) {
    return AssertionsOnColumnOfChangeEquality
      .hasValues(myself, info, valueAtStartPoint, valueAtEndPoint, expectedAtStartPoint,
        expectedAtEndPoint);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeColumnAssert hasValues(Boolean expected) {
    return AssertionsOnColumnOfChangeEquality
      .hasValues(myself, info, valueAtStartPoint, valueAtEndPoint, expected);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeColumnAssert hasValues(Boolean expectedAtStartPoint, Boolean expectedAtEndPoint) {
    return AssertionsOnColumnOfChangeEquality
      .hasValues(myself, info, valueAtStartPoint, valueAtEndPoint, expectedAtStartPoint,
        expectedAtEndPoint);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeColumnAssert hasValues(Number expected) {
    return AssertionsOnColumnOfChangeEquality
      .hasValues(myself, info, valueAtStartPoint, valueAtEndPoint, expected);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeColumnAssert hasValues(Number expectedAtStartPoint, Number expectedAtEndPoint) {
    return AssertionsOnColumnOfChangeEquality
      .hasValues(myself, info, valueAtStartPoint, valueAtEndPoint, expectedAtStartPoint,
        expectedAtEndPoint);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeColumnAssert hasValues(byte[] expected) {
    return AssertionsOnColumnOfChangeEquality
      .hasValues(myself, info, valueAtStartPoint, valueAtEndPoint, expected);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeColumnAssert hasValues(byte[] expectedAtStartPoint, byte[] expectedAtEndPoint) {
    return AssertionsOnColumnOfChangeEquality
      .hasValues(myself, info, valueAtStartPoint, valueAtEndPoint, expectedAtStartPoint,
        expectedAtEndPoint);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeColumnAssert hasValues(String expected) {
    return AssertionsOnColumnOfChangeEquality
      .hasValues(myself, info, valueAtStartPoint, valueAtEndPoint, expected);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeColumnAssert hasValues(String expectedAtStartPoint, String expectedAtEndPoint) {
    return AssertionsOnColumnOfChangeEquality
      .hasValues(myself, info, valueAtStartPoint, valueAtEndPoint, expectedAtStartPoint,
        expectedAtEndPoint);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeColumnAssert hasValues(Character expected) {
    return AssertionsOnColumnOfChangeEquality
      .hasValues(myself, info, valueAtStartPoint, valueAtEndPoint, expected);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeColumnAssert hasValues(Character expectedAtStartPoint, Character expectedAtEndPoint) {
    return AssertionsOnColumnOfChangeEquality
      .hasValues(myself, info, valueAtStartPoint, valueAtEndPoint, expectedAtStartPoint,
        expectedAtEndPoint);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeColumnAssert hasValues(UUID expected) {
    return AssertionsOnColumnOfChangeEquality
      .hasValues(myself, info, valueAtStartPoint, valueAtEndPoint, expected);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeColumnAssert hasValues(UUID expectedAtStartPoint, UUID expectedAtEndPoint) {
    return AssertionsOnColumnOfChangeEquality
      .hasValues(myself, info, valueAtStartPoint, valueAtEndPoint, expectedAtStartPoint,
        expectedAtEndPoint);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeColumnAssert hasValues(DateValue expected) {
    return AssertionsOnColumnOfChangeEquality
      .hasValues(myself, info, valueAtStartPoint, valueAtEndPoint, expected);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeColumnAssert hasValues(DateValue expectedAtStartPoint, DateValue expectedAtEndPoint) {
    return AssertionsOnColumnOfChangeEquality
      .hasValues(myself, info, valueAtStartPoint, valueAtEndPoint, expectedAtStartPoint,
        expectedAtEndPoint);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeColumnAssert hasValues(TimeValue expected) {
    return AssertionsOnColumnOfChangeEquality
      .hasValues(myself, info, valueAtStartPoint, valueAtEndPoint, expected);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeColumnAssert hasValues(TimeValue expectedAtStartPoint, TimeValue expectedAtEndPoint) {
    return AssertionsOnColumnOfChangeEquality
      .hasValues(myself, info, valueAtStartPoint, valueAtEndPoint, expectedAtStartPoint,
        expectedAtEndPoint);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeColumnAssert hasValues(DateTimeValue expected) {
    return AssertionsOnColumnOfChangeEquality
      .hasValues(myself, info, valueAtStartPoint, valueAtEndPoint, expected);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeColumnAssert hasValues(DateTimeValue expectedAtStartPoint, DateTimeValue expectedAtEndPoint) {
    return AssertionsOnColumnOfChangeEquality
      .hasValues(myself, info, valueAtStartPoint, valueAtEndPoint, expectedAtStartPoint,
        expectedAtEndPoint);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeColumnAssert hasColumnName(String columnName) {
    return AssertionsOnColumnName.hasColumnName(myself, info, this.columnName, columnName, valueAtStartPoint.getColumnLetterCase());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeColumnAssert isOfClass(Class<?> expected, boolean lenient) {
    return AssertionsOnColumnOfChangeClass.isOfClass(myself, info, valueAtStartPoint, valueAtEndPoint, expected, lenient);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeColumnAssert isOfType(ValueType expected, boolean lenient) {
    return AssertionsOnColumnOfChangeType.isOfType(myself, info, valueAtStartPoint, valueAtEndPoint, expected, lenient);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeColumnAssert isOfAnyTypeIn(ValueType... expected) {
    return AssertionsOnColumnOfChangeType.isOfAnyTypeIn(myself, info, valueAtStartPoint, valueAtEndPoint, expected);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeColumnAssert isNumber(boolean lenient) {
    return AssertionsOnColumnOfChangeType.isNumber(myself, info, valueAtStartPoint, valueAtEndPoint, lenient);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeColumnAssert isBoolean(boolean lenient) {
    return AssertionsOnColumnOfChangeType.isBoolean(myself, info, valueAtStartPoint, valueAtEndPoint, lenient);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeColumnAssert isDate(boolean lenient) {
    return AssertionsOnColumnOfChangeType.isDate(myself, info, valueAtStartPoint, valueAtEndPoint, lenient);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeColumnAssert isTime(boolean lenient) {
    return AssertionsOnColumnOfChangeType.isTime(myself, info, valueAtStartPoint, valueAtEndPoint, lenient);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeColumnAssert isDateTime(boolean lenient) {
    return AssertionsOnColumnOfChangeType.isDateTime(myself, info, valueAtStartPoint, valueAtEndPoint, lenient);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeColumnAssert isBytes(boolean lenient) {
    return AssertionsOnColumnOfChangeType.isBytes(myself, info, valueAtStartPoint, valueAtEndPoint, lenient);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeColumnAssert isText(boolean lenient) {
    return AssertionsOnColumnOfChangeType.isText(myself, info, valueAtStartPoint, valueAtEndPoint, lenient);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeColumnAssert isUUID(boolean lenient) {
    return AssertionsOnColumnOfChangeType.isUUID(myself, info, valueAtStartPoint, valueAtEndPoint, lenient);
  }

  /**
   * Returns to level of assertion methods on a {@link org.assertj.db.type.Change}.
   *
   * @return a object of assertion methods on a {@link org.assertj.db.type.Change}.
   */
  public ChangeAssert returnToChange() {
    return returnToOrigin();
  }
}
