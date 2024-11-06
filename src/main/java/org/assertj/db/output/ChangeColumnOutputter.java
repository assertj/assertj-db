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
package org.assertj.db.output;

import static org.assertj.db.util.Descriptions.getColumnValueAtEndPointDescription;
import static org.assertj.db.util.Descriptions.getColumnValueAtStartPointDescription;

import org.assertj.db.navigation.PositionWithPoints;
import org.assertj.db.navigation.element.ColumnElement;
import org.assertj.db.navigation.origin.OriginWithValuesFromColumn;
import org.assertj.db.output.impl.Output;
import org.assertj.db.type.Value;

/**
 * Output methods for a {@code Column} of a {@code Change}.
 *
 * @author Régis Pouiller
 */
public class ChangeColumnOutputter
  extends AbstractOutputterWithOriginWithColumnsAndRowsFromChange<ChangeColumnOutputter, ChangeOutputter>
  implements ColumnElement,
  OriginWithValuesFromColumn<ChangesOutputter, ChangeOutputter, ChangeColumnOutputter, ChangeRowOutputter, ChangeColumnValueOutputter> {

  /**
   * The name of the column.
   */
  private final String columnName;
  /**
   * The actual value at start point.
   */
  private final Value valueAtStartPoint;

  /**
   * The actual value at end point.
   */
  private final Value valueAtEndPoint;

  /**
   * Position of navigation to row.
   */
  private final PositionWithPoints<ChangeColumnOutputter, ChangeColumnValueOutputter, Value> valuePosition;

  /**
   * Constructor.
   *
   * @param columnName        The column name.
   * @param origin            The output of {@link org.assertj.db.navigation.origin.Origin}.
   * @param valueAtStartPoint The value at start point.
   * @param valueAtEndPoint   The value at end point.
   */
  public ChangeColumnOutputter(ChangeOutputter origin, String columnName, Value valueAtStartPoint,
                               Value valueAtEndPoint) {
    super(ChangeColumnOutputter.class, origin);
    this.columnName = columnName;
    this.valueAtStartPoint = valueAtStartPoint;
    this.valueAtEndPoint = valueAtEndPoint;
    valuePosition = new PositionWithPoints<ChangeColumnOutputter, ChangeColumnValueOutputter, Value>(this, ChangeColumnValueOutputter.class, Value.class, valueAtStartPoint, valueAtEndPoint) {

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
  public ChangeColumnValueOutputter valueAtStartPoint() {
    return valuePosition.getInstanceAtStartPoint().withType(outputType);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeColumnValueOutputter valueAtEndPoint() {
    return valuePosition.getInstanceAtEndPoint().withType(outputType);
  }

  /**
   * Returns to level of output methods on a {@link org.assertj.db.type.Change}.
   *
   * @return a object of output methods on a {@link org.assertj.db.type.Change}.
   */
  public ChangeOutputter returnToChange() {
    return returnToOrigin();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getOutput(Output outputType) {
    return outputType.getChangeColumnOutput(info, columnName, valueAtStartPoint, valueAtEndPoint);
  }
}
