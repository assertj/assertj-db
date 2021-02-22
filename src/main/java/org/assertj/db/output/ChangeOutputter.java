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
 * Copyright 2015-2021 the original author or authors.
 */
package org.assertj.db.output;

import org.assertj.db.navigation.PositionWithColumnsChange;
import org.assertj.db.navigation.PositionWithPoints;
import org.assertj.db.navigation.element.ChangeElement;
import org.assertj.db.navigation.origin.OriginWithColumnsAndRowsFromChange;
import org.assertj.db.output.impl.Output;
import org.assertj.db.type.Change;
import org.assertj.db.type.Row;
import org.assertj.db.util.Changes;

import static org.assertj.db.util.Descriptions.*;

/**
 * Output methods for a {@link Change}.
 *
 * @author Régis Pouiller
 */
public class ChangeOutputter
        extends AbstractOutputterWithOriginWithChanges<ChangeOutputter, ChangesOutputter>
        implements ChangeElement,
        OriginWithColumnsAndRowsFromChange<ChangesOutputter, ChangeOutputter, ChangeColumnOutputter, ChangeRowOutputter> {

  /**
   * The actual change on which the output is.
   */
  private final Change change;

  /**
   * Position of navigation to row.
   */
  private final PositionWithPoints<ChangeOutputter, ChangeRowOutputter, Row> rowPosition;

  /**
   * Position of navigation to column.
   */
  private final PositionWithColumnsChange<ChangeOutputter, ChangeColumnOutputter> columnPosition;

  /**
   * Constructor.
   *
   * @param origin The output of {@link org.assertj.db.navigation.origin.Origin}.
   * @param change The {@link Change} on which are the displays.
   */
  public ChangeOutputter(ChangesOutputter origin, Change change) {
    super(ChangeOutputter.class, origin);
    this.change = change;
    rowPosition = new PositionWithPoints<ChangeOutputter, ChangeRowOutputter, Row>(this, ChangeRowOutputter.class, Row.class, change.getRowAtStartPoint(), change.getRowAtEndPoint()) {

      @Override protected String getDescriptionAtStartPoint() {
        return getRowAtStartPointDescription(info);
      }

      @Override protected String getDescriptionAtEndPoint() {
        return getRowAtEndPointDescription(info);
      }
    };
    columnPosition = new PositionWithColumnsChange<ChangeOutputter, ChangeColumnOutputter>(this, ChangeColumnOutputter.class){

      @Override protected String getDescription(int index, String columnName) {
        return getColumnDescription(info, index, columnName);
      }
    };
  }

  /** {@inheritDoc} */
  @Override
  public ChangeRowOutputter rowAtStartPoint() {
    return rowPosition.getInstanceAtStartPoint().withType(outputType);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeRowOutputter rowAtEndPoint() {
    return rowPosition.getInstanceAtEndPoint().withType(outputType);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnOutputter column() {
    return columnPosition.getChangeColumnInstance(change).withType(outputType);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnOutputter column(int index) {
    return columnPosition.getChangeColumnInstance(change, index).withType(outputType);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnOutputter column(String columnName) {
    return columnPosition.getChangeColumnInstance(change, columnName, change.getColumnLetterCase()).withType(
            outputType);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnOutputter columnAmongTheModifiedOnes() {
    return columnPosition.getModifiedChangeColumnInstance(change).withType(outputType);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnOutputter columnAmongTheModifiedOnes(int index) {
    return columnPosition.getModifiedChangeColumnInstance(change, index).withType(outputType);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnOutputter columnAmongTheModifiedOnes(String columnName) {
    return columnPosition.getModifiedChangeColumnInstance(change, columnName, change.getColumnLetterCase()).withType(
            outputType);
  }

  /**
   * Returns to level of output methods on {@link Changes}.
   *
   * @return a object of output methods on {@link Changes}.
   */
  public ChangesOutputter returnToChanges() {
    return returnToOrigin();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getOutput(Output outputType) {
    return outputType.getChangeOutput(info, change);
  }
}
