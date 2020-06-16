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
 * Copyright 2015-2020 the original author or authors.
 */
package org.assertj.db.util;

import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.db.type.*;
import org.assertj.db.type.Changes;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility methods related to descriptions.
 *
 * @author Régis Pouiller
 * @since 1.0.0
 */
public class Descriptions {

  /**
   * Private constructor.
   */
  private Descriptions() {
    // Empty
  }

  /**
   * Returns the description of the {@code table}.
   *
   * @param table The table
   * @return The description
   */
  public static String getDescription(Table table) {
    return table.getName() + " table";
  }

  /**
   * Returns the description of the {@code request}.
   *
   * @param request The request
   * @return The description
   */
  public static String getDescription(Request request) {
    String sql = request.getRequest();
    if (sql.length() > 30) {
      sql = sql.substring(0, 30) + "...";
    }
    return "'" + sql + "' request";
  }

  /**
   * Returns the description of the {@code changes}.
   *
   * @param changes The changes
   * @return The description
   */
  public static String getDescription(Changes changes) {
    StringBuilder stringBuilder = new StringBuilder();
    if (changes.getTablesList() != null) {
      List<Table> tablesList = changes.getTablesList();
      if (tablesList.size() == 1) {
        Table table = tablesList.get(0);
        stringBuilder.append("Changes on ").append(table.getName()).append(" table");
      } else {
        stringBuilder.append("Changes on tables");
      }
    } else {
      Request request = changes.getRequest();
      if (request != null) {
        String sql = request.getRequest();
        if (sql.length() > 30) {
          sql = sql.substring(0, 30) + "...";
        }
        stringBuilder.append("Changes on '").append(sql).append("' request");
      }
      else {
        stringBuilder.append("Changes");
      }
    }
    if (changes.getSource() != null) {
      Source source = changes.getSource();
      stringBuilder.append(" of '").append(source.getUser()).append("/").append(source.getUrl()).append("' source");
    } else {
      stringBuilder.append(" of a data source");
    }
    return stringBuilder.toString();
  }

  /**
   * Returns the description of the {@code row}.
   *
   * @param info  Writable information about an assertion.
   * @param index Index of the row.
   * @return The description
   */
  public static String getRowDescription(WritableAssertionInfo info, int index) {
    return "Row at index " + index + " of " + info.descriptionText();
  }

  /**
   * Returns the description of the {@code row}.
   *
   * @param info  Writable information about an assertion.
   * @return The description
   */
  public static String getRowAtStartPointDescription(WritableAssertionInfo info) {
    return "Row at start point of " + info.descriptionText();
  }

  /**
   * Returns the description of the {@code row}.
   *
   * @param info  Writable information about an assertion.
   * @return The description
   */
  public static String getRowAtEndPointDescription(WritableAssertionInfo info) {
    return "Row at end point of " + info.descriptionText();
  }

  /**
   * Returns the description of the {@code column}.
   *
   * @param info       Writable information about an assertion.
   * @param index      Index of the column.
   * @param columnName Name of column.
   * @return The description
   */
  public static String getColumnDescription(WritableAssertionInfo info, int index, String columnName) {
    return "Column at index " + index + " (column name : " + columnName + ") of " + info.descriptionText();
  }

  /**
   * Returns the description of the {@code value} of the {@code column}.
   *
   * @param info  Writable information about an assertion.
   * @param index Index of the value.
   * @return The description
   */
  public static String getColumnValueDescription(WritableAssertionInfo info, int index) {
    return "Value at index " + index + " of " + info.descriptionText();
  }

  /**
   * Returns the description of the {@code value} of the {@code column}.
   *
   * @param info Writable information about an assertion.
   * @return The description
   */
  public static String getColumnValueAtStartPointDescription(WritableAssertionInfo info) {
    return "Value at start point of " + info.descriptionText();
  }

  /**
   * Returns the description of the {@code value} of the {@code column}.
   *
   * @param info Writable information about an assertion.
   * @return The description
   */
  public static String getColumnValueAtEndPointDescription(WritableAssertionInfo info) {
    return "Value at end point of " + info.descriptionText();
  }

  /**
   * Returns the description of the {@code value} of the {@code row}.
   *
   * @param info       Writable information about an assertion.
   * @param index      Index of the value.
   * @param columnName Name of column of the value.
   * @return The description
   */
  public static String getRowValueDescription(WritableAssertionInfo info, int index, String columnName) {
    return "Value at index " + index + " (column name : " + columnName + ") of " + info.descriptionText();
  }

  /**
   * Gets a StringBuilder about the type of change and the table name.
   *
   * @param changeType Type of the change.
   * @param tableName  Name of the table.
   * @return The changes assert implementation.
   */
  private static StringBuilder getStringBuilderAboutChangeTypeAndTableName(ChangeType changeType, String tableName) {
    StringBuilder stringBuilder = new StringBuilder();
    if (changeType != null || tableName != null) {
      stringBuilder.append(" (only");
      if (changeType != null) {
        stringBuilder.append(" ");
        stringBuilder.append(changeType.name().toLowerCase());
      }
      stringBuilder.append(" ");
      stringBuilder.append("changes");
      if (tableName != null) {
        stringBuilder.append(" on ");
        stringBuilder.append(tableName);
        stringBuilder.append(" table");
      }
      stringBuilder.append(")");
    }
    return stringBuilder;
  }

  /**
   * Returns the description of the {@code changes}.
   *
   * @param info       Writable information about an assertion.
   * @param changeType Type of the change.
   * @param tableName  Name of the table.
   * @return The description
   */
  public static String getChangesDescription(WritableAssertionInfo info, ChangeType changeType, String tableName) {
    return info.descriptionText() + getStringBuilderAboutChangeTypeAndTableName(changeType, tableName);
  }

  /**
   * Returns the description of the {@code changes}.
   *
   * @param info       Writable information about an assertion.
   * @param changes    The changes
   * @param change     The change
   * @param index      Index of the value.
   * @param changeType Type of the change.
   * @param tableName  Name of the table.
   * @return The description
   */
  public static String getChangeDescription(WritableAssertionInfo info, Changes changes, Change change, int index,
                                            ChangeType changeType, String tableName) {
    StringBuilder stringBuilder = new StringBuilder("Change at index " + index);
    List<Value> pksValueList = change.getPksValueList();
    boolean isAChangeOnATableAmongOtherTables = changes.getTablesList() != null && changes.getTablesList().size() > 1;
    boolean havePksValues = !pksValueList.isEmpty();
    if (isAChangeOnATableAmongOtherTables || havePksValues) {
      stringBuilder.append(" (");
      if (isAChangeOnATableAmongOtherTables) {
        stringBuilder.append("on table : ").append(change.getDataName());
      }
      if (isAChangeOnATableAmongOtherTables && havePksValues) {
        stringBuilder.append(" and ");
      }
      if (havePksValues) {
        List<Object> objectsList = new ArrayList<>();
        for (Value value : pksValueList) {
          objectsList.add(value.getValue());
        }
        stringBuilder.append("with primary key : ").append(objectsList);
      }
      stringBuilder.append(")");
    }
    stringBuilder.append(" of ").append(info.descriptionText());
    stringBuilder.append(getStringBuilderAboutChangeTypeAndTableName(changeType, tableName));
    return stringBuilder.toString();
  }
}
