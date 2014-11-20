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
package org.assertj.db.type;

import java.util.List;

/**
 * Change in the database.
 * 
 * @author Régis Pouiller.
 * 
 */
public class Change {

  /**
   * The name of the data on which is the change.
   */
  private final String dataName;
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
   * Returns a new instance of a creation change.
   * @param dataName The name of the data.
   * @param rowAtEndPoint The row at end point.
   * @return The new instance of a creation change.
   */
  static Change createCreationChange(String dataName, Row rowAtEndPoint) {
    return new Change(dataName, ChangeType.CREATION, null, rowAtEndPoint);
  }

  /**
   * Returns a new instance of a modification change.
   * @param dataName The name of the data.
   * @param rowAtStartPoint The row at start point.
   * @param rowAtEndPoint The row at end point.
   * @return The new instance of a modification change.
   */
  static Change createModificationChange(String dataName, Row rowAtStartPoint, Row rowAtEndPoint) {
    return new Change(dataName, ChangeType.MODIFICATION, rowAtStartPoint, rowAtEndPoint);
  }

  /**
   * Returns a new instance of a deletion change.
   * @param dataName The name of the data.
   * @param rowAtStartPoint The row at start point.
   * @return The new instance of a deletion change.
   */
  static Change createDeletionChange(String dataName, Row rowAtStartPoint) {
    return new Change(dataName, ChangeType.DELETION, rowAtStartPoint, null);
  }

  /**
   * Constructor.
   * 
   * @param dataName The name of the data on which is the change.
   * @param changeType The type of the change.
   * @param rowAtStartPoint The row at start point.
   * @param rowAtEndPoint The row at end point.
   */
  Change(String dataName, ChangeType changeType, Row rowAtStartPoint, Row rowAtEndPoint) {
    this.dataName = dataName;
    if (rowAtStartPoint != null) {
      this.columnsNameList = rowAtStartPoint.getColumnsNameList();
    } else {
      this.columnsNameList = rowAtEndPoint.getColumnsNameList();
    }
    this.changeType = changeType;
    this.rowAtStartPoint = rowAtStartPoint;
    this.rowAtEndPoint = rowAtEndPoint;
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
