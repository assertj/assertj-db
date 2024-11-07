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
 * Copyright 2015-2024 the original author or authors.
 */
package org.assertj.db.type;

import java.util.List;

import org.assertj.db.type.lettercase.LetterCase;
import org.assertj.db.type.lettercase.WithColumnLetterCase;
import org.assertj.db.type.lettercase.WithPrimaryKeyLetterCase;
import org.assertj.db.type.lettercase.WithTableLetterCase;

/**
 * Change in the database.
 * <p>
 * Note : you never instantiate directly this class. You will get an object of this class from a {@link Changes}
 * with the list by using {@link org.assertj.db.type.Changes#getChangesList()},
 * {@link org.assertj.db.type.Changes#getChangesOfTable(String)}
 * or {@link org.assertj.db.type.Changes#getChangesOfType(ChangeType)}.
 * </p>
 *
 * @author Régis Pouiller.
 */
public class Change implements DbElement, WithTableLetterCase, WithColumnLetterCase, WithPrimaryKeyLetterCase {

  /**
   * The type of the date on which is the change.
   */
  private final DataType dataType;
  /**
   * The name of the data on which is the change.
   */
  private final String dataName;
  /**
   * List of the primary key names.
   */
  private final List<String> pksNameList;
  /**
   * The list of the column names.
   */
  private final List<String> columnsNameList;
  /**
   * The type of the change.
   */
  private final ChangeType changeType;
  /**
   * The row at start point.
   */
  private final Row rowAtStartPoint;
  /**
   * The row at end point.
   */
  private final Row rowAtEndPoint;

  /**
   * Letter case of the tables.
   *
   * @since 1.1.0
   */
  private final LetterCase tableLetterCase;

  /**
   * Letter case of the columns.
   *
   * @since 1.1.0
   */
  private final LetterCase columnLetterCase;

  /**
   * Letter case of the primary keys.
   *
   * @since 1.1.0
   */
  private final LetterCase primaryKeyLetterCase;

  /**
   * Constructor.
   *
   * @param dataType             The type of the data on which is the change.
   * @param dataName             The name of the data on which is the change.
   * @param changeType           The type of the change.
   * @param rowAtStartPoint      The row at start point.
   * @param rowAtEndPoint        The row at end point.
   * @param tableLetterCase      Letter case of the tables.
   * @param columnLetterCase     Letter case of the columns.
   * @param primaryKeyLetterCase Letter case of the primary keys.
   * @throws NullPointerException If the type of the data is {@code null} or if the name of the data is {@code null}.
   */
  private Change(DataType dataType, String dataName, ChangeType changeType, Row rowAtStartPoint, Row rowAtEndPoint,
                 LetterCase tableLetterCase, LetterCase columnLetterCase, LetterCase primaryKeyLetterCase) {

    if (dataType == null) {
      throw new NullPointerException("The type of the data must be not null");
    }
    if (dataName == null) {
      throw new NullPointerException("The name of the data must be not null");
    }
    this.dataType = dataType;
    this.dataName = dataName;
    if (rowAtStartPoint != null) {
      this.pksNameList = rowAtStartPoint.getPksNameList();
      this.columnsNameList = rowAtStartPoint.getColumnsNameList();
    } else {
      this.pksNameList = rowAtEndPoint.getPksNameList();
      this.columnsNameList = rowAtEndPoint.getColumnsNameList();
    }
    this.changeType = changeType;
    this.rowAtStartPoint = rowAtStartPoint;
    this.rowAtEndPoint = rowAtEndPoint;
    this.tableLetterCase = tableLetterCase;
    this.columnLetterCase = columnLetterCase;
    this.primaryKeyLetterCase = primaryKeyLetterCase;
  }

  /**
   * Returns a new instance of a creation change.
   *
   * @param dataType             The type of the data on which is the change.
   * @param dataName             The name of the data.
   * @param rowAtEndPoint        The row at end point.
   * @param tableLetterCase      Letter case of the tables.
   * @param columnLetterCase     Letter case of the columns.
   * @param primaryKeyLetterCase Letter case of the primary keys.
   * @return The new instance of a creation change.
   * @throws NullPointerException If the name of the date is {@code null}.
   */
  static Change createCreationChange(DataType dataType, String dataName, Row rowAtEndPoint,
                                     LetterCase tableLetterCase, LetterCase columnLetterCase, LetterCase primaryKeyLetterCase) {
    return new Change(dataType, dataName, ChangeType.CREATION, null, rowAtEndPoint,
      tableLetterCase, columnLetterCase, primaryKeyLetterCase);
  }

  /**
   * Returns a new instance of a modification change.
   *
   * @param dataType             The type of the data on which is the change.
   * @param dataName             The name of the data.
   * @param rowAtStartPoint      The row at start point.
   * @param rowAtEndPoint        The row at end point.
   * @param tableLetterCase      Letter case of the tables.
   * @param columnLetterCase     Letter case of the columns.
   * @param primaryKeyLetterCase Letter case of the primary keys.
   * @return The new instance of a modification change.
   * @throws NullPointerException If the name of the date is {@code null}.
   */
  static Change createModificationChange(DataType dataType, String dataName, Row rowAtStartPoint, Row rowAtEndPoint,
                                         LetterCase tableLetterCase, LetterCase columnLetterCase, LetterCase primaryKeyLetterCase) {
    return new Change(dataType, dataName, ChangeType.MODIFICATION, rowAtStartPoint, rowAtEndPoint,
      tableLetterCase, columnLetterCase, primaryKeyLetterCase);
  }

  /**
   * Returns a new instance of a deletion change.
   *
   * @param dataType             The type of the data on which is the change.
   * @param dataName             The name of the data.
   * @param rowAtStartPoint      The row at start point.
   * @param tableLetterCase      Letter case of the tables.
   * @param columnLetterCase     Letter case of the columns.
   * @param primaryKeyLetterCase Letter case of the primary keys.
   * @return The new instance of a deletion change.
   * @throws NullPointerException If the name of the date is {@code null}.
   */
  static Change createDeletionChange(DataType dataType, String dataName, Row rowAtStartPoint,
                                     LetterCase tableLetterCase, LetterCase columnLetterCase, LetterCase primaryKeyLetterCase) {
    return new Change(dataType, dataName, ChangeType.DELETION, rowAtStartPoint, null,
      tableLetterCase, columnLetterCase, primaryKeyLetterCase);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public LetterCase getColumnLetterCase() {
    return columnLetterCase;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public LetterCase getPrimaryKeyLetterCase() {
    return primaryKeyLetterCase;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public LetterCase getTableLetterCase() {
    return tableLetterCase;
  }

  /**
   * Returns the type of the data on which is the change.
   *
   * @return The type of the data on which is the change.
   */
  public DataType getDataType() {
    return dataType;
  }

  /**
   * Returns the name of the data on which is the change.
   *
   * @return The name of the data on which is the change.
   */
  public String getDataName() {
    return dataName;
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
    if (rowAtStartPoint != null) {
      return rowAtStartPoint.getPksValueList();
    }
    return rowAtEndPoint.getPksValueList();
  }

  /**
   * Returns the list of the column names.
   *
   * @return The list of the column names.
   */
  public List<String> getColumnsNameList() {
    return columnsNameList;
  }

  /**
   * Returns the type of the change.
   *
   * @return The type of the change.
   */
  public ChangeType getChangeType() {
    return changeType;
  }

  /**
   * Returns the row at start point.
   *
   * @return The row at start point.
   */
  public Row getRowAtStartPoint() {
    return rowAtStartPoint;
  }

  /**
   * Returns the row at end point.
   *
   * @return The row at end point.
   */
  public Row getRowAtEndPoint() {
    return rowAtEndPoint;
  }
}
