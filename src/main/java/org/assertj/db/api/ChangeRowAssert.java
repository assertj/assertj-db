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

import org.assertj.db.api.assertions.AssertOnNumberOfColumns;
import org.assertj.db.api.assertions.AssertOnRowEquality;
import org.assertj.db.api.assertions.AssertOnRowOfChangeExistence;
import org.assertj.db.api.assertions.impl.AssertionsOnNumberOfColumns;
import org.assertj.db.api.assertions.impl.AssertionsOnRowEquality;
import org.assertj.db.api.assertions.impl.AssertionsOnRowOfChangeExistence;
import org.assertj.db.api.navigation.RowAssert;
import org.assertj.db.api.origin.OriginWithValuesFromRow;
import org.assertj.db.exception.AssertJDBException;
import org.assertj.db.type.Row;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Assertion methods for a {@code Row} of a {@code Change}.
 *
 * @author Régis Pouiller
 */
public class ChangeRowAssert
        extends AbstractAssertWithOriginWithColumnsAndRowsFromChange<ChangeRowAssert, ChangeAssert>
        implements RowAssert,
                   OriginWithValuesFromRow,
                   AssertOnRowEquality<ChangeRowAssert>,
                   AssertOnNumberOfColumns<ChangeRowAssert>,
                   AssertOnRowOfChangeExistence<ChangeRowAssert> {

  /**
   * The actual row on which the assertion is.
   */
  private final Row row;

  /**
   * Index of the next value to get.
   */
  private int indexNextValue;
  /**
   * Map the value assert with their index in key (contains the value assert already generated).
   */
  private Map<Integer, ChangeRowValueAssert> changeValueAssertMap = new HashMap<Integer, ChangeRowValueAssert>();

  /**
   * Constructor.
   *
   * @param origin The assertion of {@link org.assertj.db.api.origin.Origin}.
   * @param row The {@link Row} on which are the assertions.
   */
  ChangeRowAssert(ChangeAssert origin, Row row) {
    super(ChangeRowAssert.class, origin);
    this.row = row;
  }

  /**
   * Returns the value at the {@code index} in parameter.
   *
   * @param index The index corresponding to the value.
   * @return The value.
   * @throws org.assertj.db.exception.AssertJDBException If the {@code index} is out of the bounds.
   */
  private Object getValue(int index) {
    if (row == null) {
      throw new AssertJDBException("Row do not exist");
    }
    int size = row.getValuesList().size();
    if (index < 0 || index >= size) {
      throw new AssertJDBException("Index %s out of the limits [0, %s[", index, size);
    }
    Object object = row.getValuesList().get(index);
    indexNextValue = index + 1;
    return object;
  }

  /**
   * Gets an instance of change assert corresponding to the index. If this instance is already instanced, the method
   * returns it from the cache.
   *
   * @param index Index of the change on which is the instance of change assert.
   * @return The change assert implementation.
   */
  private ChangeRowValueAssert getChangeValueAssertInstance(int index) {
    if (changeValueAssertMap.containsKey(index)) {
      ChangeRowValueAssert changeRowValueAssert = changeValueAssertMap.get(index);
      indexNextValue = index + 1;
      return changeRowValueAssert;
    }

    List<String> columnsNameList = row.getColumnsNameList();
    String columnName = columnsNameList.get(index);
    ChangeRowValueAssert instance = new ChangeRowValueAssert(this, columnName, getValue(index));
    changeValueAssertMap.put(index, instance);
    return instance.as("Value at index " + index + " (column name : " + columnName + ") of " + info.descriptionText());
  }

  /** {@inheritDoc} */
  @Override
  public ChangeRowValueAssert value() {
    return getChangeValueAssertInstance(indexNextValue);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeRowValueAssert value(int index) {
    return getChangeValueAssertInstance(index);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeRowValueAssert value(String columnName) {
    if (columnName == null) {
      throw new NullPointerException("Column name must be not null");
    }
    List<String> columnsNameList = row.getColumnsNameList();
    int index = columnsNameList.indexOf(columnName.toUpperCase());
    if (index == -1) {
      throw new AssertJDBException("Column <%s> does not exist", columnName);
    }
    return getChangeValueAssertInstance(index);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeRowAssert hasNumberOfColumns(int expected) {
    List<String> columnsNameList = row.getColumnsNameList();
    int size = columnsNameList.size();
    return AssertionsOnNumberOfColumns.hasNumberOfColumns(myself, info, size, expected);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeRowAssert hasValuesEqualTo(Object... expected) {
    return AssertionsOnRowEquality.hasValuesEqualTo(myself, info, row.getValuesList(), expected);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeRowAssert exists() {
    return AssertionsOnRowOfChangeExistence.exists(myself, info, row);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeRowAssert doNotExist() {
    return AssertionsOnRowOfChangeExistence.doNotExist(myself, info, row);
  }
}
