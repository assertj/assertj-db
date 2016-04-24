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
 * Copyright 2012-2016 the original author or authors.
 */
package org.assertj.db.output;

import org.assertj.db.exception.AssertJDBException;
import org.assertj.db.navigation.PositionWithColumns;
import org.assertj.db.navigation.element.RowElement;
import org.assertj.db.navigation.origin.OriginWithValuesFromRow;
import org.assertj.db.output.impl.Output;
import org.assertj.db.type.Row;
import org.assertj.db.type.Value;

import java.util.List;

import static org.assertj.db.util.Descriptions.getRowValueDescription;

/**
 * Output methods for a {@code Row} of a {@code Change}.
 *
 * @author Régis Pouiller
 */
public class ChangeRowOutputter
        extends AbstractOutputterWithOriginWithColumnsAndRowsFromChange<ChangeRowOutputter, ChangeOutputter>
        implements RowElement,
        OriginWithValuesFromRow<ChangesOutputter, ChangeOutputter, ChangeColumnOutputter, ChangeRowOutputter, ChangeRowValueOutputter> {

  /**
   * Position of navigation to value.
   */
  private final PositionWithColumns<ChangeRowOutputter, ChangeRowValueOutputter, Value> valuePosition;

  /**
   * The actual row on which the output is.
   */
  private final Row row;

  /**
   * Constructor.
   *
   * @param origin The output of {@link org.assertj.db.navigation.origin.Origin}.
   * @param row The {@link Row} on which are the displays.
   */
  public ChangeRowOutputter(ChangeOutputter origin, Row row) {
    super(ChangeRowOutputter.class, origin);
    this.row = row;
    valuePosition = new PositionWithColumns<ChangeRowOutputter, ChangeRowValueOutputter, Value>(this, ChangeRowValueOutputter.class) {
      @Override protected String getDescription(int index) {
        List<String> columnsNameList = ChangeRowOutputter.this.row.getColumnsNameList();
        String columnName = columnsNameList.get(index);
        return getRowValueDescription(info, index, columnName);
      }
    };
  }

  /** {@inheritDoc} */
  @Override
  public ChangeRowValueOutputter value() {
    if (row == null) {
      throw new AssertJDBException("Row do not exist");
    }
    return valuePosition.getInstance(row.getValuesList()).withType(outputType);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeRowValueOutputter value(int index) {
    if (row == null) {
      throw new AssertJDBException("Row do not exist");
    }
    return valuePosition.getInstance(row.getValuesList(), index).withType(outputType);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeRowValueOutputter value(String columnName) {
    if (row == null) {
      throw new AssertJDBException("Row do not exist");
    }
    return valuePosition.getInstance(row.getValuesList(), row.getColumnsNameList(),
                                     columnName, row.getColumnLetterCase()).withType(outputType);
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
    return outputType.getRowOutput(info, row);
  }
}