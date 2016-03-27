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
package org.assertj.db.display;

import org.assertj.db.display.impl.Representation;
import org.assertj.db.navigation.PositionWithPoints;
import org.assertj.db.navigation.element.ColumnElement;
import org.assertj.db.navigation.origin.OriginWithValuesFromColumn;
import org.assertj.db.type.Value;

import static org.assertj.db.util.Descriptions.getColumnValueAtEndPointDescription;
import static org.assertj.db.util.Descriptions.getColumnValueAtStartPointDescription;

/**
 * Display methods for a {@code Column} of a {@code Change}.
 *
 * @author Régis Pouiller
 */
public class ChangeColumnDisplay
        extends AbstractDisplayWithOriginWithColumnsAndRowsFromChange<ChangeColumnDisplay, ChangeDisplay>
        implements ColumnElement,
        OriginWithValuesFromColumn<ChangesDisplay, ChangeDisplay, ChangeColumnDisplay, ChangeRowDisplay, ChangeColumnValueDisplay> {

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
  private final PositionWithPoints<ChangeColumnDisplay, ChangeColumnValueDisplay, Value> valuePosition;

  /**
   * Constructor.
   *
   * @param columnName The column name.
   * @param origin The display of {@link org.assertj.db.navigation.origin.Origin}.
   * @param valueAtStartPoint The value at start point.
   * @param valueAtEndPoint The value at end point.
   */
  public ChangeColumnDisplay(ChangeDisplay origin, String columnName, Value valueAtStartPoint, Value valueAtEndPoint) {
    super(ChangeColumnDisplay.class, origin);
    this.columnName = columnName;
    this.valueAtStartPoint = valueAtStartPoint;
    this.valueAtEndPoint = valueAtEndPoint;
    valuePosition = new PositionWithPoints<ChangeColumnDisplay, ChangeColumnValueDisplay, Value>(this, ChangeColumnValueDisplay.class, Value.class, valueAtStartPoint, valueAtEndPoint) {

      @Override protected String getDescriptionAtStartPoint() {
        return getColumnValueAtStartPointDescription(info);
      }

      @Override protected String getDescriptionAtEndPoint() {
        return getColumnValueAtEndPointDescription(info);
      }
    };
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnValueDisplay valueAtStartPoint() {
    return valuePosition.getInstanceAtStartPoint().withType(representationType);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnValueDisplay valueAtEndPoint() {
    return valuePosition.getInstanceAtEndPoint().withType(representationType);
  }

  /**
   * Returns to level of display methods on a {@link org.assertj.db.type.Change}.
   *
   * @return a object of display methods on a {@link org.assertj.db.type.Change}.
   */
  public ChangeDisplay returnToChange() {
    return returnToOrigin();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getRepresentation(Representation representationType) {
    return representationType.getChangeColumnRepresentation(info, columnName, valueAtStartPoint, valueAtEndPoint);
  }
}
