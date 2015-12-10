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

import org.assertj.db.display.impl.RepresentationType;
import org.assertj.db.navigation.Position;
import org.assertj.db.navigation.PositionWithColumns;
import org.assertj.db.navigation.ToValueFromRow;
import org.assertj.db.navigation.element.RowElement;
import org.assertj.db.type.AbstractDbData;
import org.assertj.db.type.Row;
import org.assertj.db.type.Value;

import java.util.List;

import static org.assertj.db.util.Descriptions.getRowValueDescription;

/**
 * Base class for all {@link Row}s displays.
 *
 * @author Régis Pouiller
 *
 * @param <D> The class of the actual value (an sub-class of {@link AbstractDbData}).
 * @param <A> The class of the original assertion (an sub-class of {@link AbstractDbDisplay}).
 * @param <C> The class of the equivalent column assertion (an sub-class of {@link AbstractColumnDisplay}).
 * @param <CV> The class of the equivalent column assertion on the value (an sub-class of {@link AbstractColumnValueDisplay}
 *          ).
 * @param <R> The class of this assertion (an sub-class of {@link AbstractRowDisplay}).
 * @param <RV> The class of this assertion on the value (an sub-class of {@link AbstractRowValueDisplay}).
 */
public abstract class AbstractRowDisplay<D extends AbstractDbData<D>, A extends AbstractDbDisplay<D, A, C, CV, R, RV>, C extends AbstractColumnDisplay<D, A, C, CV, R, RV>, CV extends AbstractColumnValueDisplay<D, A, C, CV, R, RV>, R extends AbstractRowDisplay<D, A, C, CV, R, RV>, RV extends AbstractRowValueDisplay<D, A, C, CV, R, RV>>
        extends AbstractSubDisplay<D, A, R, RV, C, CV, R, RV>
        implements RowElement,
        ToValueFromRow<RV> {

  /**
   * Position of navigation to value.
   */
  private final PositionWithColumns<R, RV, Value> valuePosition;

  /**
   * Row on which do the assertion.
   */
  private final Row row;

  /**
   * Constructor.
   *
   * @param originalDbDisplay The original assert. That could be a {@link RequestDisplay} or a {@link TableDisplay}.
   * @param selfType Type of this assertion class : a sub-class of {@code AbstractRowDisplay}.
   * @param valueType Class of the assert on the value : a sub-class of {@code AbstractRowValueDisplay}.
   */
  AbstractRowDisplay(A originalDbDisplay, Class<R> selfType, Class<RV> valueType, Row row) {
    super(originalDbDisplay, selfType);
    this.row = row;
    valuePosition = new PositionWithColumns<R, RV, Value>(selfType.cast(this), valueType) {
      @Override protected String getDescription(int index) {
        return getValueDescription(index);
      }
    };
  }

  /** {@inheritDoc} */
  @Override
  protected String getValueDescription(int index) {
    List<String> columnsNameList = row.getColumnsNameList();
    String columnName = columnsNameList.get(index);
    return getRowValueDescription(info, index, columnName);
  }

  /** {@inheritDoc} */
  @Override
  protected Position<R, RV, Value> getValuePosition() {
    return valuePosition;
  }

  /** {@inheritDoc} */
  @Override
  protected List<Value> getValuesList() {
    return row.getValuesList();
  }

  /** {@inheritDoc} */
  @Override
  public RV value(String columnName) {
    return valuePosition.getInstance(getValuesList(), row.getColumnsNameList(), columnName).withType(displayType);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getRepresentation(RepresentationType displayType) {
    return displayType.getRowRepresentation(info, row);
  }
}
