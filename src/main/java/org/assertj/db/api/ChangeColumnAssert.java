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
import org.assertj.db.api.assertions.AssertOnModifiedColumn;
import org.assertj.db.api.assertions.impl.AssertionsOnColumnName;
import org.assertj.db.api.assertions.impl.AssertionsOnModifiedColumn;
import org.assertj.db.api.navigation.ColumnAssert;
import org.assertj.db.api.origin.OriginWithValuesFromColumn;
import org.assertj.db.api.assertions.impl.AssertionsOnColumnOfChangeEquality;

/**
 * Assertion methods about a {@code Column} of a {@code Change}.
 *
 * @author Régis Pouiller
 */
public class ChangeColumnAssert extends AbstractAssertWithOriginWithColumnsAndRowsFromChange<ChangeColumnAssert, ChangeAssert>
        implements OriginWithValuesFromColumn, AssertOnColumnOfChangeEquality<ChangeColumnAssert>,
        AssertOnModifiedColumn<ChangeColumnAssert>, AssertOnColumnName<ChangeColumnAssert>, ColumnAssert {

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
   * @param columnName        The column name.
   * @param originalAssert    The original assert.
   * @param valueAtStartPoint The value at start point.
   * @param valueAtEndPoint   The value at end point.
   */
  ChangeColumnAssert(ChangeAssert originalAssert, String columnName, Object valueAtStartPoint, Object valueAtEndPoint) {
    super(ChangeColumnAssert.class, originalAssert);
    this.columnName = columnName;
    this.valueAtStartPoint = valueAtStartPoint;
    this.valueAtEndPoint = valueAtEndPoint;
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnValueAssert valueAtStartPoint() {
    if (changeColumnValueAssertAtStartPoint == null) {
      StringBuilder stringBuilder = new StringBuilder("Value at start point of ");
      stringBuilder.append(info.descriptionText());
      changeColumnValueAssertAtStartPoint = new ChangeColumnValueAssert(this, valueAtStartPoint)
              .as(stringBuilder.toString());
    }
    return changeColumnValueAssertAtStartPoint;
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnValueAssert valueAtEndPoint() {
    if (changeColumnValueAssertAtEndPoint == null) {
      StringBuilder stringBuilder = new StringBuilder("Value at end point of ");
      stringBuilder.append(info.descriptionText());
      changeColumnValueAssertAtEndPoint = new ChangeColumnValueAssert(this, valueAtEndPoint)
              .as(stringBuilder.toString());
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
  public ChangeColumnAssert hasValuesEqualTo(Object expected) {
    return AssertionsOnColumnOfChangeEquality
            .hasValuesEqualTo(myself, info, valueAtStartPoint, valueAtEndPoint, expected);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnAssert hasValuesEqualTo(Object expectedAtStartPoint, Object expectedAtEndPoint) {
    return AssertionsOnColumnOfChangeEquality
            .hasValuesEqualTo(myself, info, valueAtStartPoint, valueAtEndPoint, expectedAtStartPoint,
                              expectedAtEndPoint);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnAssert hasColumnName(String columnName) {
    return AssertionsOnColumnName.hasColumnName(myself, info, this.columnName, columnName);
  }
}
