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
import org.assertj.db.navigation.PositionWithColumnsChange;
import org.assertj.db.navigation.PositionWithPoints;
import org.assertj.db.navigation.element.ChangeElement;
import org.assertj.db.navigation.origin.OriginWithColumnsAndRowsFromChange;
import org.assertj.db.type.Change;
import org.assertj.db.type.Row;
import org.assertj.db.util.Changes;

import static org.assertj.db.util.Descriptions.*;

/**
 * Display methods for a {@link Change}.
 *
 * @author Régis Pouiller
 */
public class ChangeDisplay
        extends AbstractDisplayWithOriginWithChanges<ChangeDisplay, ChangesDisplay>
        implements ChangeElement,
        OriginWithColumnsAndRowsFromChange<ChangesDisplay, ChangeDisplay, ChangeColumnDisplay, ChangeRowDisplay> {

  /**
   * The actual change on which the display is.
   */
  private final Change change;

  /**
   * Position of navigation to row.
   */
  private final PositionWithPoints<ChangeDisplay, ChangeRowDisplay, Row> rowPosition;

  /**
   * Position of navigation to column.
   */
  private final PositionWithColumnsChange<ChangeDisplay, ChangeColumnDisplay> columnPosition;

  /**
   * Constructor.
   *
   * @param origin The display of {@link org.assertj.db.navigation.origin.Origin}.
   * @param change The {@link Change} on which are the displays.
   */
  public ChangeDisplay(ChangesDisplay origin, Change change) {
    super(ChangeDisplay.class, origin);
    this.change = change;
    rowPosition = new PositionWithPoints<ChangeDisplay, ChangeRowDisplay, Row>(this, ChangeRowDisplay.class, Row.class, change.getRowAtStartPoint(), change.getRowAtEndPoint()) {

      @Override protected String getDescriptionAtStartPoint() {
        return getRowAtStartPointDescription(info);
      }

      @Override protected String getDescriptionAtEndPoint() {
        return getRowAtEndPointDescription(info);
      }
    };
    columnPosition = new PositionWithColumnsChange<ChangeDisplay, ChangeColumnDisplay>(this, ChangeColumnDisplay.class){

      @Override protected String getDescription(int index, String columnName) {
        return getColumnDescription(info, index, columnName);
      }
    };
  }

  /** {@inheritDoc} */
  @Override
  public ChangeRowDisplay rowAtStartPoint() {
    return rowPosition.getInstanceAtStartPoint().withType(representationType);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeRowDisplay rowAtEndPoint() {
    return rowPosition.getInstanceAtEndPoint().withType(representationType);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnDisplay column() {
    return columnPosition.getChangeColumnInstance(change).withType(representationType);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnDisplay column(int index) {
    return columnPosition.getChangeColumnInstance(change, index).withType(representationType);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnDisplay column(String columnName) {
    return columnPosition.getChangeColumnInstance(change, columnName, change.getColumnLetterCase()).withType(
            representationType);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnDisplay columnAmongTheModifiedOnes() {
    return columnPosition.getModifiedChangeColumnInstance(change).withType(representationType);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnDisplay columnAmongTheModifiedOnes(int index) {
    return columnPosition.getModifiedChangeColumnInstance(change, index).withType(representationType);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnDisplay columnAmongTheModifiedOnes(String columnName) {
    return columnPosition.getModifiedChangeColumnInstance(change, columnName, change.getColumnLetterCase()).withType(representationType);
  }

  /**
   * Returns to level of display methods on {@link Changes}.
   *
   * @return a object of display methods on {@link Changes}.
   */
  public ChangesDisplay returnToChanges() {
    return returnToOrigin();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getRepresentation(Representation representationType) {
    return representationType.getChangeRepresentation(info, change);
  }
}
