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
package org.assertj.db.type;

import org.assertj.db.type.lettercase.LetterCase;
import org.assertj.db.type.lettercase.WithColumnLetterCase;

import java.util.List;

/**
 * Column in a {@link AbstractDbData}.
 * <p>
 * A column can have many rows with a value for each row.
 * </p>
 * <p>
 * Note : you never instantiate directly this class. You will get an object of this class from a {@link Table} or a
 * {@link Request} by using {@link AbstractDbData#getColumn(int)}.
 * </p>
 * 
 * @author Régis Pouiller.
 * 
 */
public class Column implements DbElement, WithColumnLetterCase {

  /**
   * The name of the column.
   */
  private final String name;
  /**
   * The values of the column.
   */
  private final List<Value> valuesList;
  /**
   * Letter case of the columns.
   * @since 1.1.0
   */
  private final LetterCase columnLetterCase;

  /**
   * Constructor of the column with visibility in the package.
   * 
   * @param name The name of the column.
   * @param valuesList The values in the column.
   * @param columnLetterCase The letter case of the columns.
   */
  Column(String name, List<Value> valuesList, LetterCase columnLetterCase) {
    this.name = name;
    this.valuesList = valuesList;
    this.columnLetterCase = columnLetterCase;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public LetterCase getColumnLetterCase() {
    return columnLetterCase;
  }

  /**
   * Returns the name of the column.
   * 
   * @return The name of the column.
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the values of the column.
   * 
   * @return The values of the column.
   */
  public List<Value> getValuesList() {
    return valuesList;
  }

  /**
   * Returns the value corresponding to the row index.
   * 
   * @param index The index
   * @return The value
   */
  public Value getRowValue(int index) {
    return valuesList.get(index);
  }
}
