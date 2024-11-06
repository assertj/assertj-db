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
package org.assertj.db.output.impl;

import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.db.type.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Implementation of plain output of assertj-db.
 *
 * @author Régis Pouiller
 * @since 1.1.0
 */
enum PlainOutput implements Output {

  /**
   * Singleton instance.
   */
  INSTANCE;

  /**
   * End of a line.
   */
  private static final String EOL = String.format("%n");

  /**
   * Returns the text representing a object.
   *
   * @param object The object
   * @return The text.
   */
  private static String getText(Object object) {
    return "" + object;
  }

  /**
   * Returns the size of the column corresponding to the maximum in the length of the column name,
   * the length of the text of the index and the length of the texts representing the values.
   *
   * @param columnName The column name.
   * @param type       The text representing the type.
   * @param index      The index of the column.
   * @param values     The values.
   * @return The size.
   */
  private static int getColumnSize(String columnName, String type, Integer index, Value... values) {
    int size = ("" + columnName).length();
    int typeSize = type.length();
    if (typeSize > size) {
      size = typeSize;
    }
    if (index != null) {
      int indexSize = ("Index : " + index).length();
      if (indexSize > size) {
        size = indexSize;
      }
    }
    for (Value value : values) {
      int valueSize = OutputType.getText(value).length();
      if (valueSize > size) {
        size = valueSize;
      }
    }
    return size + 2;
  }

  /**
   * Returns the size of the column corresponding to the maximum in the length of the column name,
   * the length of the text of the index and the length of the texts representing the objects.
   *
   * @param columnName The column name.
   * @param objects     The objects.
   * @return The size.
   */
  private static int getColumnSize(String columnName, Object... objects) {
    int size = ("" + columnName).length();
    for (Object object : objects) {
      int valueSize = getText(object).length();
      if (valueSize > size) {
        size = valueSize;
      }
    }
    return size + 2;
  }

  /**
   * Returns a {@code StringBuilder} representing a text containing the {@code text} in parameter
   * and the remaining space to corresponding to the {@code size} in parameter is filled with spaces.
   *
   * @param text The text.
   * @param size The size.
   * @return The text after filling.
   */
  private static StringBuilder getFilledText(String text, int size) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(" ").append(text);
    while (stringBuilder.length() < size) {
      stringBuilder.append(" ");
    }
    return stringBuilder;
  }

  /**
   * Returns a {@code StringBuilder} representing a line for a cell corresponding to the {@code size} in parameter.
   *
   * @param size The size.
   * @return The line.
   */
  private static StringBuilder getCellLine(int size) {
    StringBuilder stringBuilder = new StringBuilder();
    while (stringBuilder.length() < size) {
      stringBuilder.append('-');
    }
    return stringBuilder;
  }

  /**
   * Returns a {@code StringBuilder} representing a complete line corresponding to the {@code row} in parameter.
   *
   * @param sizesList           The list of sizes.
   *                            @param row The row
   * @param otherColumnsContent Other content in the column (var-args) : the columns before the values.
   * @return The line.
   */
  private static StringBuilder getCompleteRow(List<Integer> sizesList, Row row, Object... otherColumnsContent) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("|");
    int index = 0;
    for (Integer size : sizesList) {
      if (index < otherColumnsContent.length) {
        stringBuilder.append(getFilledText("" + otherColumnsContent[index], size));
        stringBuilder.append("|");
      }
      else {
        if (row != null) {
          Value value = row.getValuesList().get(index - otherColumnsContent.length);
          stringBuilder.append(getFilledText(OutputType.getText(value), size)).append("|");
        }
        else {
          stringBuilder.append(getFilledText("", size)).append("|");
        }
      }
      index++;
    }
    stringBuilder.append(EOL);
    return stringBuilder;
  }

  /**
   * Returns a {@code StringBuilder} representing a complete line corresponding to the {@code sizes} in parameter.
   *
   * @param sizesList           The list of sizes.
   * @param otherColumnsContent Other content in the column (var-args) : the columns before the values.
   * @return The line.
   */
  private static StringBuilder getCompleteLine(List<Integer> sizesList, Object... otherColumnsContent) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("|");
    int index = 0;
    for (int size : sizesList) {
      if (index < otherColumnsContent.length) {
        stringBuilder.append(getFilledText("" + otherColumnsContent[index], size));
      } else {
        stringBuilder.append(getCellLine(size));
      }
      stringBuilder.append("|");
      index++;
    }
    stringBuilder.append(EOL);
    return stringBuilder;
  }

  /**
   * Returns the columns sizes in array.
   *
   * @param columnSizesList List of column sizes (the columns with the values).
   * @param sizes           Sizes (var-args) : the columns before the values.
   * @return An array with the sizes.
   */
  private List<Integer> getSizesList(List<Integer> columnSizesList, Integer... sizes) {
    List<Integer> sizesList = new ArrayList<>(Arrays.asList(sizes));
    if (columnSizesList != null) {
      sizesList.addAll(columnSizesList);
    }
    return sizesList;
  }

  /**
   * Returns a {@code StringBuilder} representing a complete line corresponding to the indication about the primary key.
   *
   * @param sizesList           The list of sizes of the column.
   * @param pksNameList         The list of the primary key name.
   * @param columnsNameList     The list of the column name.
   * @return The output.
   */
  private static StringBuilder getCompletePrimaryKey(List<Integer> sizesList, List<String> pksNameList,
                                                     List<String> columnsNameList) {

    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("|");
    int index = 0;
    for (; index < sizesList.size() - columnsNameList.size(); index++) {
      Integer columnSize = sizesList.get(index);
      stringBuilder.append(getFilledText("", columnSize));
      stringBuilder.append("|");
    }
    for (String columnName : columnsNameList) {
      Integer columnSize = sizesList.get(index);
      String pk = "";
      if (pksNameList != null && pksNameList.contains(columnName)) {
        pk = "*";
      }
      stringBuilder.append(getFilledText(pk, columnSize)).append("|");
      index++;
    }
    stringBuilder.append(EOL);
    return stringBuilder;
  }

  /**
   * Returns a {@code StringBuilder} representing a complete line corresponding to the indication about the primary key.
   *
   * @param sizesList           The list of sizes of the column.
   * @param columnsNameList     The list of the column name.
   * @param otherColumnsContent Other content in the column (var-args) : the columns before the values.
   * @return The output.
   */
  private static StringBuilder getCompleteColumnName(List<Integer> sizesList, List<String> columnsNameList,
                                                     String... otherColumnsContent) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("|");
    int index = 0;
    for (String content : otherColumnsContent) {
      Integer columnSize = sizesList.get(index);
      stringBuilder.append(getFilledText(content, columnSize));
      stringBuilder.append("|");
      index++;
    }
    for (String columnName : columnsNameList) {
      Integer columnSize = sizesList.get(index);
      stringBuilder.append(getFilledText(columnName, columnSize)).append("|");
      index++;
    }
    stringBuilder.append(EOL);
    return stringBuilder;
  }

  /**
   * Returns a {@code StringBuilder} representing a complete line corresponding to the indication about the type.
   *
   * @param sizesList           The list of sizes of the column.
   * @param typesList           The list of the type.
   * @param otherColumnsContent Other content in the column (var-args) : the columns before the values.
   * @return The output.
   */
  private static StringBuilder getCompleteType(List<Integer> sizesList, List<String> typesList,
                                               String... otherColumnsContent) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("|");
    int index = 0;
    for (String content : otherColumnsContent) {
      Integer columnSize = sizesList.get(index);
      stringBuilder.append(getFilledText(content, columnSize));
      stringBuilder.append("|");
      index++;
    }
    for (; index < sizesList.size(); index++) {
      int index1 = index - otherColumnsContent.length;
      Integer columnSize = sizesList.get(index);
      String type = index1 < typesList.size() ? typesList.get(index1) : "";
      stringBuilder.append(getFilledText(type, columnSize)).append("|");
    }
    stringBuilder.append(EOL);
    return stringBuilder;
  }

  /**
   * Returns a {@code StringBuilder} representing a complete line corresponding to the indication about the index.
   *
   * @param sizesList                  The list of sizes of the column.
   * @param numberOfAdditionalColumns The number of additional columns.
   * @return The output.
   */
  private static StringBuilder getCompleteIndex(List<Integer> sizesList, int numberOfAdditionalColumns) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("|");
    int index = 0;
    for (; index < numberOfAdditionalColumns; index++) {
      Integer columnSize = sizesList.get(index);
      stringBuilder.append(getFilledText("", columnSize));
      stringBuilder.append("|");
    }
    for (; index < sizesList.size(); index++) {
      Integer columnSize = sizesList.get(index);
      stringBuilder.append(getFilledText("Index : " + (index - numberOfAdditionalColumns), columnSize)).append("|");
    }
    stringBuilder.append(EOL);
    return stringBuilder;
  }

  /**
   * Returns the size of the column of index.
   * @param size The size of the rows/changes.
   * @return The size.
   */
  private static Integer getIndexColumnSize(int size) {
    return getColumnSize("", "Index : " + (size - 1));
  }

  /**
   * Returns the size of the column of change type.
   * @param changes The changes.
   * @return The size.
   */
  private static Integer getChangeTypeColumnSize(Change... changes) {
    int size = 0;
    for (Change change : changes) {
      ChangeType changeType = change.getChangeType();
      int changeTypeColumnSize = getColumnSize("TYPE", changeType);
      if (size < changeTypeColumnSize) {
        size = changeTypeColumnSize;
      }
    }
    return size;
  }

  /**
   * Returns the size of the column of change type.
   * @param changes The changes.
   * @return The size.
   */
  private static Integer getDataTypeColumnSize(Change... changes) {
    int size = 0;
    for (Change change : changes) {
      DataType dataType = change.getDataType();
      String dataName = OutputType.getDataName(change);
      int dataTypeColumnSize = getColumnSize("" + dataType, dataName);
      if (size < dataTypeColumnSize) {
        size = dataTypeColumnSize;
      }
    }
    return size;
  }

  /**
   * Returns the sizes for the columns corresponding to the size of the columns name.
   *
   * @param columnsNameList The list of column names.
   * @return The labels.
   */
  private static List<Integer> getColumnSizesList(List<String> columnsNameList) {
    List<Integer> columnSizesList = new ArrayList<>();

    int index = 0;
    for (String columnName : columnsNameList) {
      int columnSize = getColumnSize(columnName, "", index);
      columnSizesList.add(columnSize);
      index++;
    }
    return columnSizesList;
  }

  /**
   * Returns the sizes for the columns corresponding to the size of the values of the column.
   *
   * @param rows The rows.
   * @return The labels.
   */
  private static List<Integer> getColumnSizesList(Row... rows) {
    List<Integer> columnSizesList = new ArrayList<>();
    Row row0 = null;
    for (Row row : rows) {
      if (row != null) {
        row0 = row;
        break;
      }
    }

    if (row0 == null) {
      return columnSizesList;
    }

    List<String> columnsNameList = row0.getColumnsNameList();
    int index = 0;
    for (String columnName : columnsNameList) {
      List<Value> valuesList = new ArrayList<>();
      for (Row row : rows) {
        if (row != null) {
          Value value = row.getValuesList().get(index);
          valuesList.add(value);
        }
      }
      String type = OutputType.getType(valuesList.toArray(new Value[0]));
      int columnSize = getColumnSize(columnName, type, index, valuesList.toArray(new Value[0]));
      columnSizesList.add(columnSize);
      index++;
    }

    return columnSizesList;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getTableOutput(WritableAssertionInfo info, Table table) {
    List<String> pksNameList = table.getPksNameList();
    List<String> columnsNameList = table.getColumnsNameList();
    List<Row> rowsList = table.getRowsList();
    Row[] rows = rowsList.toArray(new Row[0]);

    List<String> typesList = OutputType.getTypesList(rows);
    int indexColumnSize = getIndexColumnSize(rows.length);
    StringBuilder[] pksValueStringBuilders = OutputType.getPksValueStringBuilder(rows);
    int primaryKeyColumnSize = getColumnSize("PRIMARY", pksValueStringBuilders);
    List<Integer> sizesList = getSizesList(rows.length == 0 ? getColumnSizesList(columnsNameList) : getColumnSizesList(rows),
                                           indexColumnSize,
                                           primaryKeyColumnSize);

    StringBuilder stringBuilder = new StringBuilder();
    // Description
    stringBuilder.append("[").append(info.descriptionText()).append("]").append(EOL);
    // Line
    stringBuilder.append(getCompleteLine(sizesList));
    // Primary key
    stringBuilder.append(getCompletePrimaryKey(sizesList, pksNameList, columnsNameList));
    // Column name
    stringBuilder.append(getCompleteColumnName(sizesList, columnsNameList, "", "PRIMARY"));
    // Type
    stringBuilder.append(getCompleteType(sizesList, typesList, "", "KEY"));
    // Index
    stringBuilder.append(getCompleteIndex(sizesList, 2));
    // Line
    stringBuilder.append(getCompleteLine(sizesList));
    // Values
    int index = 0;
    for (Row row : rows) {
      stringBuilder.append(getCompleteRow(sizesList, row,
                                          "Index : " + index, pksValueStringBuilders[index]));
      index++;
    }
    // Line
    stringBuilder.append(getCompleteLine(sizesList));

    return stringBuilder.toString();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getRequestOutput(WritableAssertionInfo info, Request request) {
    List<String> pksNameList = request.getPksNameList();
    List<String> columnsNameList = request.getColumnsNameList();
    List<Row> rowsList = request.getRowsList();
    Row[] rows = rowsList.toArray(new Row[0]);

    List<String> typesList = OutputType.getTypesList(rows);
    int indexColumnSize = getIndexColumnSize(rows.length);
    StringBuilder[] pksValueStringBuilders = OutputType.getPksValueStringBuilder(rows);
    int primaryKeyColumnSize = getColumnSize("PRIMARY", pksValueStringBuilders);
    List<Integer> sizesList = getSizesList(rows.length == 0 ? getColumnSizesList(columnsNameList) : getColumnSizesList(rows),
                                           indexColumnSize,
                                           primaryKeyColumnSize);

    StringBuilder stringBuilder = new StringBuilder();
    // Description
    stringBuilder.append("[").append(info.descriptionText()).append("]").append(EOL);
    // Line
    stringBuilder.append(getCompleteLine(sizesList));
    // Primary key
    stringBuilder.append(getCompletePrimaryKey(sizesList, pksNameList, columnsNameList
    ));
    // Column name
    stringBuilder.append(getCompleteColumnName(sizesList, columnsNameList,
                                               "", "PRIMARY"));
    // Type
    stringBuilder.append(getCompleteType(sizesList, typesList,
                                         "", "KEY"));
    // Index
    stringBuilder.append(getCompleteIndex(sizesList, 2));
    // Line
    stringBuilder.append(getCompleteLine(sizesList));
    // Values
    int index = 0;
    for (Row row : rows) {
      stringBuilder.append(getCompleteRow(sizesList, row,
                                          "Index : " + index, pksValueStringBuilders[index]));
      index++;
    }
    // Line
    stringBuilder.append(getCompleteLine(sizesList));

    return stringBuilder.toString();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getChangesOutput(WritableAssertionInfo info, Changes changes) {
    List<Change> changesList = changes.getChangesList();
    Change[] changesArray = changesList.toArray(new Change[0]);
    int indexColumnSize = getIndexColumnSize(changesList.size());
    int changeTypeColumnSize = getChangeTypeColumnSize(changesArray);
    int dataTypeColumnSize = getDataTypeColumnSize(changesArray);
    StringBuilder[] pksValueStringBuilders = OutputType.getPksValueStringBuilder(changesArray);
    int primaryKeyColumnSize = getColumnSize("PRIMARY", pksValueStringBuilders);

    StringBuilder stringBuilder = new StringBuilder();
    // Description
    stringBuilder.append("[").append(info.descriptionText()).append("]").append(EOL);
    int index = 0;
    for (Change change : changesList) {
      ChangeType changeType = change.getChangeType();
      DataType dataType = change.getDataType();
      String dataName = OutputType.getDataName(change);
      List<String> columnsNameList = change.getColumnsNameList();
      Row rowAtStartPoint = change.getRowAtStartPoint();
      Row rowAtEndPoint = change.getRowAtEndPoint();
      List<String> typesList = OutputType.getTypesList(rowAtStartPoint, rowAtEndPoint);

      List<Integer> sizesList = getSizesList(getColumnSizesList(rowAtStartPoint, rowAtEndPoint),
                                             indexColumnSize,
                                             changeTypeColumnSize,
                                             dataTypeColumnSize,
                                             primaryKeyColumnSize, 16);
      // Line
      stringBuilder.append(getCompleteLine(sizesList));
      // Primary key
      stringBuilder.append(getCompletePrimaryKey(sizesList, change.getPksNameList(), columnsNameList
      ));
      // Column name
      stringBuilder.append(getCompleteColumnName(sizesList, columnsNameList,
                                                 "", "TYPE", "" + dataType, "PRIMARY", ""));
      // Type
      stringBuilder.append(getCompleteType(sizesList, typesList,
                                           "", "", "", "KEY", ""));
      // Index
      stringBuilder.append(getCompleteIndex(sizesList, 5));
      // Line
      stringBuilder.append(getCompleteLine(sizesList));
      // Value at start point
      stringBuilder.append(getCompleteRow(sizesList, rowAtStartPoint,
                                          "", "", "", "", "At start point"));
      // Line
      stringBuilder.append(getCompleteLine(sizesList,
                                           "Index : " + index, changeType, dataName, pksValueStringBuilders[index]));
      // Value at end point
      stringBuilder.append(getCompleteRow(sizesList, rowAtEndPoint,
                                          "", "", "", "", "At end point"));
      // Line
      stringBuilder.append(getCompleteLine(sizesList));

      index++;
    }

    return stringBuilder.toString();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getChangeOutput(WritableAssertionInfo info, Change change) {
    ChangeType changeType = change.getChangeType();
    DataType dataType = change.getDataType();
    String dataName = OutputType.getDataName(change);
    List<String> columnsNameList = change.getColumnsNameList();
    Row rowAtStartPoint = change.getRowAtStartPoint();
    Row rowAtEndPoint = change.getRowAtEndPoint();

    StringBuilder[] pksValueStringBuilders = OutputType.getPksValueStringBuilder(change);
    List<String> typesList = OutputType.getTypesList(rowAtStartPoint, rowAtEndPoint);

    int changeTypeColumnSize = getColumnSize("TYPE", changeType);
    int dataTypeColumnSize = getColumnSize("" + dataType, dataName);
    int primaryKeyColumnSize = getColumnSize("PRIMARY", pksValueStringBuilders);
    List<Integer> sizesList = getSizesList(getColumnSizesList(rowAtStartPoint, rowAtEndPoint),
                                           changeTypeColumnSize,
                                           dataTypeColumnSize,
                                           primaryKeyColumnSize, 16);

    // Description

    return "[" + info.descriptionText() + "]" + EOL
           // Line
           + getCompleteLine(sizesList)
           // Primary key
           + getCompletePrimaryKey(sizesList, change.getPksNameList(), columnsNameList
    )
           // Column name
           + getCompleteColumnName(sizesList, columnsNameList,
                                                   "TYPE", "" + dataType, "PRIMARY", "")
           // Type
           + getCompleteType(sizesList, typesList,
                                             "", "", "KEY", "")
           // Index
           + getCompleteIndex(sizesList, 4)
           // Line
           + getCompleteLine(sizesList)
           // Value at start point
           + getCompleteRow(sizesList, rowAtStartPoint,
                                            "", "", "", "At start point")
           // Line
           + getCompleteLine(sizesList, changeType, dataName, pksValueStringBuilders[0])
           // Value at end point
           + getCompleteRow(sizesList, rowAtEndPoint,
                                            "", "", "", "At end point")
           // Line
           + getCompleteLine(sizesList);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getRowOutput(WritableAssertionInfo info, Row row) {
    if (row == null) {
      return "[" + info.descriptionText() + "]" + EOL
             + "Row does not exist" + EOL;
    }
    List<String> columnsNameList = row.getColumnsNameList();
    List<String> typesList = OutputType.getTypesList(row);
    StringBuilder[] pksValueStringBuilders = OutputType.getPksValueStringBuilder(row);
    int primaryKeyColumnSize = getColumnSize("PRIMARY", pksValueStringBuilders);
    List<Integer> sizesList = getSizesList(getColumnSizesList(row),
                                           primaryKeyColumnSize);

    // Description

    return "[" + info.descriptionText() + "]" + EOL
           // Line
           + getCompleteLine(sizesList)
           // Primary key
           + getCompletePrimaryKey(sizesList, row.getPksNameList(), columnsNameList)
           // Column name
           + getCompleteColumnName(sizesList, columnsNameList, "PRIMARY")
           // Type
           + getCompleteType(sizesList, typesList, "KEY")
           // Index
           + getCompleteIndex(sizesList, 1)
           // Line
           + getCompleteLine(sizesList)
           // Value
           + getCompleteRow(sizesList, row, pksValueStringBuilders[0])
           // Line
           + getCompleteLine(sizesList);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getColumnOutput(WritableAssertionInfo info, Column column) {
    String columnName = column.getName();
    List<Value> valuesList = column.getValuesList();
    Value[] values = valuesList.toArray(new Value[0]);
    int indexColumnSize = getIndexColumnSize(values.length);
    String type = OutputType.getType(values);
    int columnSize = getColumnSize(columnName, type, null, values);
    List<Integer> sizesList = getSizesList(null,
                                           indexColumnSize,
                                           columnSize);

    StringBuilder stringBuilder = new StringBuilder();
    // Description
    stringBuilder.append("[").append(info.descriptionText()).append("]").append(EOL);
    // Line
    stringBuilder.append(getCompleteLine(sizesList));
    // Column name
    stringBuilder.append("|").append(getFilledText("", indexColumnSize)).append("|").append(getFilledText(columnName, columnSize))
                .append("|").append(EOL);
    // Type
    stringBuilder.append("|").append(getFilledText("", indexColumnSize)).append("|").append(getFilledText(type, columnSize))
                .append("|").append(EOL);
    // Line
    stringBuilder.append(getCompleteLine(sizesList));
    // Value
    int index = 0;
    for (Value value : values) {
      stringBuilder.append("|").append(getFilledText(getText("Index : " + index), indexColumnSize))
                   .append("|").append(getFilledText(OutputType.getText(value), columnSize))
                   .append("|").append(EOL);
      index++;
    }
    // Line
    stringBuilder.append(getCompleteLine(sizesList));

    return stringBuilder.toString();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getChangeColumnOutput(WritableAssertionInfo info, String columnName,
                                      Value valueAtStartPoint, Value valueAtEndPoint) {

    String typeAtStartPoint = OutputType.getType(valueAtStartPoint);
    String typeAtEndPoint = OutputType.getType(valueAtEndPoint);
    String type = valueAtStartPoint.getValue() != null ? typeAtStartPoint : typeAtEndPoint;

    int columnSize = getColumnSize(columnName, type, null, valueAtStartPoint, valueAtEndPoint);

    // Description

    return "[" + info.descriptionText() + "]" + EOL
           // Line
           + "|----------------|" + getCellLine(columnSize)
           + "|" + EOL
           // Column name
           + "|                |" + getFilledText(columnName, columnSize)
           + "|" + EOL
           // Type
           + "|                |" + getFilledText(type, columnSize)
           + "|" + EOL
           // Line
           + "|----------------|" + getCellLine(columnSize)
           + "|" + EOL
           // Value at start point
           + "| At start point |" + getFilledText(OutputType.getText(valueAtStartPoint), columnSize)
           + "|" + EOL
           // Line
           + "|----------------|" + getCellLine(columnSize)
           + "|" + EOL
           // Value at end point
           + "| At end point   |" + getFilledText(OutputType.getText(valueAtEndPoint), columnSize)
           + "|" + EOL
           // Line
           + "|----------------|" + getCellLine(columnSize)
           + "|" + EOL;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getValueOutput(WritableAssertionInfo info, Value value) {
    String columnName = value.getColumnName();
    String type = OutputType.getType(value);
    int columnSize = getColumnSize(columnName, type, null, value);

    // Description

    return "[" + info.descriptionText() + "]" + EOL
           // Line
           + "|" + getCellLine(columnSize)
           + "|" + EOL
           // Column name
           + "|" + getFilledText(columnName, columnSize)
           + "|" + EOL
           // Type
           + "|" + getFilledText(type, columnSize)
           + "|" + EOL
           // Line
           + "|" + getCellLine(columnSize)
           + "|" + EOL
           // Value
           + "|" + getFilledText(OutputType.getText(value), columnSize)
           + "|" + EOL
           // Line
           + "|" + getCellLine(columnSize)
           + "|" + EOL;
  }
}
