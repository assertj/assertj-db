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

import org.assertj.db.exception.AssertJDBException;
import org.assertj.db.type.Row;
import org.assertj.db.util.AssertOnRow;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Assertion methods about a {@code Row} of a {@code Change}.
 *
 * @author Régis Pouiller
 */
public class ChangeRowAssert extends AbstractAssertWithColumnsAndRowsFromChange<ChangeRowAssert, ChangeAssert>
        implements OriginAssertWithValuesFromRow {

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
   * @param originalAssert The original assert.
   * @param row            The {@link Row} on which are the assertions.
   */
  ChangeRowAssert(ChangeAssert originalAssert, Row row) {
    super(ChangeRowAssert.class, originalAssert);
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

    ChangeRowValueAssert instance = new ChangeRowValueAssert(this, getValue(index));
    changeValueAssertMap.put(index, instance);
    return instance.as("Value at index " + index + " of " + info.descriptionText());
  }

  /**
   * Returns assertion methods on the next value in the list of values.
   *
   * @return An object to make assertions on the next value.
   * @throws org.assertj.db.exception.AssertJDBException If the {@code index} is out of the bounds.
   */
  public ChangeRowValueAssert value() {
    return getChangeValueAssertInstance(indexNextValue);
  }

  /**
   * Returns assertion methods on the value at the {@code index} in parameter.
   *
   * @param index The index corresponding to the value.
   * @return An object to make assertions on the value.
   * @throws org.assertj.db.exception.AssertJDBException If the {@code index} is out of the bounds.
   */
  public ChangeRowValueAssert value(int index) {
    return getChangeValueAssertInstance(index);
  }

  /**
   * Returns assertion methods on the value corresponding to the column name in parameter.
   *
   * @param columnName The column name.
   * @return An object to make assertions on the value.
   * @throws NullPointerException If the column name in parameter is null.
   * @throws AssertJDBException If there is no column with this name.
   */
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

  /**
   * Verifies that the size of a {@link Row} is equal to the number in parameter.
   * <p>
   * Example where the assertion verifies that the row at end point of the first change has 8 columns :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(changes).change().rowAtEndPoint().hasSize(8);
   * </code></pre>
   *
   * @param expected The number to compare to the size.
   * @return {@code this} assertion object.
   * @throws AssertionError If the size is different to the number in parameter.
   */
  public ChangeRowAssert hasSize(int expected) {
    List<String> columnsNameList = row.getColumnsNameList();
    int size = columnsNameList.size();
    return AssertOnRow.hasSize(myself, info, size, expected);
  }

  /**
   * Verifies that the values of a column are equal to values in parameter.
   * <p>
   * Example where the assertion verifies that the values of the row at end point of the first change are equal to the
   * values in parameter :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(changes).change().rowAtEndPoint().hasValuesEqualTo(1, &quot;Text&quot;, TimeValue.of(9, 1));
   * </code></pre>
   *
   * @param expected The expected values.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not equal to the values in parameter.
   */
  public ChangeRowAssert hasValuesEqualTo(Object... expected) {
    return AssertOnRow.hasValuesEqualTo(myself, info, row.getValuesList(), expected);
  }
}
