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
package org.assertj.db.type;

import org.assertj.db.util.Values;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.db.util.DialectHelper.getColumnName;

/**
 * Row in a {@link AbstractDbData}.
 * <p>
 * A row can have many columns with a value in front of this column.
 * </p>
 * <p>
 * Note : you never instantiate directly this class. You will get an object of this class from a {@link Table} or a
 * {@link Request} by using {@link AbstractDbData#getRow(int)} or with the list by using
 * {@link AbstractDbData#getRowsList()}.
 * </p>
 * 
 * @author Régis Pouiller
 * 
 */
public class Row implements DbElement {

  /**
   * List of the primary key names.
   */
  private List<String> pksNameList;
  /**
   * The list of columns name.
   */
  private final List<String> columnsNameList;
  /**
   * The list of value.
   */
  private final List<Value> valuesList;

  /**
   * Constructor of the row with visibility in the package.
   * 
   * @param pksNameList The list of the primary keys name.
   * @param columnsNameList The list of the columns name.
   * @param valuesList The values in the row.
   */
  Row(List<String> pksNameList, List<String> columnsNameList, List<Value> valuesList) {
    this.pksNameList = pksNameList;
    this.columnsNameList = columnsNameList;
    this.valuesList = valuesList;
  }

  /**
   * Return the list of the primary keys name.
   * 
   * @return The list of the primary keys name.
   */
  public List<String> getPksNameList() {
    return pksNameList;
  }

  /**
   * Return the list of the primary keys value.
   *
   * @return The list of the primary keys value.
   */
  public List<Value> getPksValueList() {
    List<Value> pksValueList = new ArrayList<>();
    if (pksNameList != null) {
      for (String name : pksNameList) {
        int index = columnsNameList.indexOf(name);
        Value value = valuesList.get(index);
        pksValueList.add(value);
      }
    }
    return pksValueList;
  }

  /**
   * Sets the list of the primary keys name.
   *
   * @param pksNameList The list of the primary keys name.
   */
  void setPksNameList(List<String> pksNameList) {
    this.pksNameList = pksNameList;
  }

  /**
   * Returns the list of the columns name.
   * 
   * @return The list of the columns name.
   */
  public List<String> getColumnsNameList() {
    return columnsNameList;
  }

  /**
   * Returns the list of the values for the data from database.
   * 
   * @return The list of the values.
   */
  public List<Value> getValuesList() {
    return valuesList;
  }

  /**
   * Returns the primary keys value.
   * 
   * @return The primary keys value.
   */
  public Value[] getPksValues() {
    List<Value> pksValuesList = new ArrayList<>();
    if (pksNameList != null) {
      for (String pkName : pksNameList) {
        int index = columnsNameList.indexOf(pkName);
        Value value = valuesList.get(index);
        pksValuesList.add(value);
      }
    }
    return pksValuesList.toArray(new Value[pksValuesList.size()]);
  }

  /**
   * Returns if the values of the primary keys are equal to the values in parameter.
   * 
   * @param pksValues The values of the primary keys to compare.
   * @return If the values of the primary keys are equal.
   */
  public boolean hasPksValuesEqualTo(Value[] pksValues) {
    Value[] pksValues1 = getPksValues();
    if (pksValues1.length != 0 && pksValues1.length == pksValues.length) {
      for (int index = 0; index < pksValues1.length; index++) {
        if (!Values.areEqual(pksValues1[index], pksValues[index].getValue())) {
          return false;
        }
      }
      return true;
    }

    return false;
  }

  /**
   * Returns if the values are equal to the value of the {@code Row} in parameter.
   * @param row The {@code Row} to compare with.
   * @return If the values are equal.
   */
  public boolean hasValues(Row row) {
    List<Value> valuesList = getValuesList();
    List<Value> rowValuesList = row.getValuesList();
    for (int index = 0; index < valuesList.size(); index++) {
      Value value = valuesList.get(index);
      Value rowValue = rowValuesList.get(index);
      if (!Values.areEqual(value, rowValue.getValue())) {
        return false;
      }
    }

    return true;
  }

  /**
   * Returns the value corresponding to the column index.
   * 
   * @param index The index
   * @return The value
   */
  public Value getColumnValue(int index) {
    return valuesList.get(index);
  }

  /**
   * Returns the value corresponding to the column name in the {@code Row}.
   * 
   * @param columnName The column name (must be not {@code null}).
   * @return The value
   * @throws NullPointerException If the {@code columnName} parameter is {@code null}.
   */
  public Value getColumnValue(String columnName) {
    if (columnName == null) {
      throw new NullPointerException("Column name must be not null");
    }
    String name = getColumnName(columnName);
    int index = getColumnsNameList().indexOf(name);
    if (index == -1) {
      return null;
    }
    return getColumnValue(index);
  }
}
