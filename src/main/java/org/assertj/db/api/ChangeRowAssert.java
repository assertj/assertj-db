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
package org.assertj.db.api;

import org.assertj.db.api.assertions.AssertOnNumberOfColumns;
import org.assertj.db.api.assertions.AssertOnRowEquality;
import org.assertj.db.api.assertions.AssertOnRowOfChangeExistence;
import org.assertj.db.api.assertions.impl.AssertionsOnNumberOfColumns;
import org.assertj.db.api.assertions.impl.AssertionsOnRowEquality;
import org.assertj.db.api.assertions.impl.AssertionsOnRowOfChangeExistence;
import org.assertj.db.exception.AssertJDBException;
import org.assertj.db.navigation.PositionWithColumns;
import org.assertj.db.navigation.element.RowElement;
import org.assertj.db.navigation.origin.OriginWithValuesFromRow;
import org.assertj.db.type.Row;
import org.assertj.db.type.Value;

import java.util.List;

import static org.assertj.db.util.Descriptions.getRowValueDescription;

/**
 * Assertion methods for a {@code Row} of a {@code Change}.
 *
 * @author Régis Pouiller
 */
public class ChangeRowAssert
        extends AbstractAssertWithOriginWithColumnsAndRowsFromChange<ChangeRowAssert, ChangeAssert>
        implements RowElement,
                   OriginWithValuesFromRow<ChangesAssert, ChangeAssert, ChangeColumnAssert, ChangeRowAssert, ChangeRowValueAssert>,
                   AssertOnRowEquality<ChangeRowAssert>,
                   AssertOnNumberOfColumns<ChangeRowAssert>,
                   AssertOnRowOfChangeExistence<ChangeRowAssert> {

  /**
   * Position of navigation to value.
   */
  private final PositionWithColumns<ChangeRowAssert, ChangeRowValueAssert, Value> valuePosition;

  /**
   * The actual row on which the assertion is.
   */
  private final Row row;

  /**
   * Constructor.
   *
   * @param origin The assertion of {@link org.assertj.db.navigation.origin.Origin}.
   * @param row The {@link Row} on which are the assertions.
   */
  public ChangeRowAssert(ChangeAssert origin, Row row) {
    super(ChangeRowAssert.class, origin);
    this.row = row;
    valuePosition = new PositionWithColumns<ChangeRowAssert, ChangeRowValueAssert, Value>(this, ChangeRowValueAssert.class) {
      @Override protected String getDescription(int index) {
        List<String> columnsNameList = ChangeRowAssert.this.row.getColumnsNameList();
        String columnName = columnsNameList.get(index);
        return getRowValueDescription(info, index, columnName);
      }
    };
  }

  /** {@inheritDoc} */
  @Override
  public ChangeRowValueAssert value() {
    if (row == null) {
      throw new AssertJDBException("Row do not exist");
    }
    return valuePosition.getInstance(row.getValuesList());
  }

  /** {@inheritDoc} */
  @Override
  public ChangeRowValueAssert value(int index) {
    if (row == null) {
      throw new AssertJDBException("Row do not exist");
    }
    return valuePosition.getInstance(row.getValuesList(), index);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeRowValueAssert value(String columnName) {
    if (row == null) {
      throw new AssertJDBException("Row do not exist");
    }
    return valuePosition.getInstance(row.getValuesList(), row.getColumnsNameList(),
                                     columnName, row.getColumnLetterCase());
  }

  /** {@inheritDoc} */
  @Override
  public ChangeRowAssert hasNumberOfColumns(int expected) {
    exists();
    List<String> columnsNameList = row.getColumnsNameList();
    int size = columnsNameList.size();
    return AssertionsOnNumberOfColumns.hasNumberOfColumns(myself, info, size, expected);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeRowAssert hasValues(Object... expected) {
    exists();
    return AssertionsOnRowEquality.hasValues(myself, info, row.getValuesList(), expected);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeRowAssert exists() {
    return AssertionsOnRowOfChangeExistence.exists(myself, info, row);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeRowAssert doesNotExist() {
    return AssertionsOnRowOfChangeExistence.doesNotExist(myself, info, row);
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
