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
package org.assertj.db.display.impl;

import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.db.type.*;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of plain display of assertj-db.
 *
 * @author Régis Pouiller
 * @since 1.1.0
 */
enum PlainRepresentation implements Representation {

  /**
   * Singleton instance.
   */
  INSTANCE;

  /**
   * Returns the data name of the change.
   * @param change The change.
   * @return The data name.
   */
  private static String getDataName(Change change) {
    DataType dataType = change.getDataType();
    String dataName = change.getDataName();
    if (dataType == DataType.REQUEST && dataName.length() > 30) {
      dataName = dataName.substring(0, 30) + "...";
    }
    return dataName;
  }

  /**
   * Returns a text representing the type of values.
   *
   * @param values The values.
   * @return The text.
   */
  private static String getType(Value... values) {
    ValueType valueType = null;
    for (Value value : values) {
      ValueType type = ValueType.getType(value);
      if (value.getValue() != null) {
        if (type == ValueType.NOT_IDENTIFIED) {
          return "(" + type + " : " + value.getValue().getClass() + ")";
        } else {
          return "(" + type + ")";
        }
      } else {
        valueType = type;
      }
    }
    return "(" + valueType + ")";
  }

  /**
   * Returns the text representing a value.
   *
   * @param value The value
   * @return The text.
   */
  private static String getText(Value value) {
    Object object = value.getValue();
    ValueType type = ValueType.getType(value);
    if (type == ValueType.BYTES) {
      return "...";
    }
    if (type == ValueType.DATE_TIME) {
      return "" + DateTimeValue.from((Timestamp) object);
    }
    if (type == ValueType.DATE) {
      return "" + DateValue.from((Date) object);
    }
    if (type == ValueType.TIME) {
      return "" + TimeValue.from((Time) object);
    } else {
      return "" + object;
    }

  }

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
    if (type != null) {
      int typeSize = type.length();
      if (typeSize > size) {
        size = typeSize;
      }
    }
    if (index != null) {
      int indexSize = ("Index : " + index).length();
      if (indexSize > size) {
        size = indexSize;
      }
    }
    for (Value value : values) {
      int valueSize = getText(value).length();
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
   * @param type       The text representing the type.
   * @param index      The index of the column.
   * @param objects     The objects.
   * @return The size.
   */
  private static int getColumnSize(String columnName, String type, Integer index, Object... objects) {
    int size = ("" + columnName).length();
    if (type != null) {
      int typeSize = type.length();
      if (typeSize > size) {
        size = typeSize;
      }
    }
    if (index != null) {
      int indexSize = ("Index : " + index).length();
      if (indexSize > size) {
        size = indexSize;
      }
    }
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
          stringBuilder.append(getFilledText(getText(value), size)).append("|");
        }
        else {
          stringBuilder.append(getFilledText("", size)).append("|");
        }
      }
      index++;
    }
    stringBuilder.append("%n");
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
    stringBuilder.append("%n");
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
    List<Integer> sizesList = new ArrayList<>();
    for (Integer size : sizes) {
      sizesList.add(size);
    }
    if (columnSizesList != null) {
      for (Integer size : columnSizesList) {
        sizesList.add(size);
      }
    }
    return sizesList;
  }

  /**
   * Returns a {@code StringBuilder} representing a complete line corresponding to the indication about the primary key.
   *
   * @param sizesList           The list of sizes of the column.
   * @param pksNameList         The list of the primary key name.
   * @param columnsNameList     The list of the column name.
   * @param otherColumnsContent Other content in the column (var-args) : the columns before the values.
   * @return The representation.
   */
  private static StringBuilder getCompletePrimaryKey(List<Integer> sizesList, List<String> pksNameList,
                                                     List<String> columnsNameList, String... otherColumnsContent) {
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
      String pk = "";
      if (pksNameList.contains(columnName)) {
        pk = "*";
      }
      stringBuilder.append(getFilledText(pk, columnSize)).append("|");
      index++;
    }
    stringBuilder.append("%n");
    return stringBuilder;
  }

  /**
   * Returns a {@code StringBuilder} representing a complete line corresponding to the indication about the primary key.
   *
   * @param sizesList           The list of sizes of the column.
   * @param columnsNameList     The list of the column name.
   * @param otherColumnsContent Other content in the column (var-args) : the columns before the values.
   * @return The representation.
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
    stringBuilder.append("%n");
    return stringBuilder;
  }

  /**
   * Returns a {@code StringBuilder} representing a complete line corresponding to the indication about the type.
   *
   * @param sizesList           The list of sizes of the column.
   * @param typesList           The list of the type.
   * @param otherColumnsContent Other content in the column (var-args) : the columns before the values.
   * @return The representation.
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
    for (String type : typesList) {
      Integer columnSize = sizesList.get(index);
      stringBuilder.append(getFilledText(type, columnSize)).append("|");
      index++;
    }
    stringBuilder.append("%n");
    return stringBuilder;
  }

  /**
   * Returns a {@code StringBuilder} representing a complete line corresponding to the indication about the index.
   *
   * @param sizesList           The list of sizes of the column.
   * @param otherColumnsContent Other content in the column (var-args) : the columns before the values.
   * @return The representation.
   */
  private static StringBuilder getCompleteIndex(List<Integer> sizesList, String... otherColumnsContent) {
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
      Integer columnSize = sizesList.get(index);
      stringBuilder.append(getFilledText("Index : " + (index - otherColumnsContent.length), columnSize)).append("|");
    }
    stringBuilder.append("%n");
    return stringBuilder;
  }

  /**
   * Returns the labels for the columns corresponding to the type of the values of the column.
   *
   * @param rows The rows.
   * @return The labels.
   */
  private static List<String> getTypesList(Row... rows) {
    List<String> typesList = new ArrayList<>();
    Row row0 = null;
    for (Row row : rows) {
      if (row != null) {
        row0 = row;
        break;
      }
    }
    if (row0 != null) {
      int index = 0;
      for (; index < row0.getColumnsNameList().size(); index++) {
        List<Object> valuesList = new ArrayList<>();
        for (Row row : rows) {
          if (row != null) {
            Object value = row.getValuesList().get(index);
            valuesList.add(value);
          }
        }
        String type = getType(valuesList.toArray(new Value[valuesList.size()]));
        typesList.add(type);
      }
    }
    return typesList;
  }

  /**
   * Returns the size of the column of index.
   * @param size The size of the rows/changes.
   * @return The size.
   */
  private static Integer getIndexColumnSize(int size) {
    return getColumnSize("", null, null, "Index : " + (size - 1));
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
      int changeTypeColumnSize = getColumnSize("TYPE", null, null, changeType);
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
      String dataName = getDataName(change);
      int dataTypeColumnSize = getColumnSize("" + dataType, null, null, dataName);
      if (size < dataTypeColumnSize) {
        size = dataTypeColumnSize;
      }
    }
    return size;
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
    if (row0 != null) {
      List<String> columnsNameList = row0.getColumnsNameList();
      int index = 0;
      for (String columnName : columnsNameList) {
        List<Object> valuesList = new ArrayList<>();
        for (Row row : rows) {
          if (row != null) {
            Object value = row.getValuesList().get(index);
            valuesList.add(value);
          }
        }
        String type = getType(valuesList.toArray(new Value[valuesList.size()]));
        int columnSize = getColumnSize(columnName, type, index, valuesList.toArray(new Value[valuesList.size()]));
        columnSizesList.add(columnSize);
        index++;
      }
    }
    return columnSizesList;
  }

  /**
   * Returns a {@code StringBuilder} representing the values of the primary key.
   *
   * @param changes The changes
   * @return The representation.
   */
  private StringBuilder[] getPksValueStringBuilder(Change... changes) {
    List<StringBuilder> stringBuildersList = new ArrayList<>();
    for (Change change : changes) {
      List<Value> pksValueList = change.getPksValueList();
      StringBuilder pksValueStringBuilder = new StringBuilder();
      for (Value pkValue : pksValueList) {
        if (pksValueStringBuilder.length() > 0) {
          pksValueStringBuilder.append(", ");
        }
        pksValueStringBuilder.append(getText(pkValue));
      }
      stringBuildersList.add(pksValueStringBuilder);
    }
    return stringBuildersList.toArray(new StringBuilder[stringBuildersList.size()]);
  }

  /**
   * Returns a {@code StringBuilder} representing the values of the primary key.
   *
   * @param rows The rows
   * @return The representation.
   */
  private StringBuilder[] getPksValueStringBuilder(Row... rows) {
    List<StringBuilder> stringBuildersList = new ArrayList<>();
    for (Row row : rows) {
      List<Value> pksValueList = row.getPksValueList();
      StringBuilder pksValueStringBuilder = new StringBuilder();
      for (Value pkValue : pksValueList) {
        if (pksValueStringBuilder.length() > 0) {
          pksValueStringBuilder.append(", ");
        }
        pksValueStringBuilder.append(getText(pkValue));
      }
      stringBuildersList.add(pksValueStringBuilder);
    }
    return stringBuildersList.toArray(new StringBuilder[stringBuildersList.size()]);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getTableRepresentation(WritableAssertionInfo info, Table table) {
    List<String> pksNameList = table.getPksNameList();
    List<String> columnsNameList = table.getColumnsNameList();
    List<Row> rowsList = table.getRowsList();
    Row[] rows = rowsList.toArray(new Row[rowsList.size()]);

    List<String> typesList = getTypesList(rows);
    int indexColumnSize = getIndexColumnSize(rows.length);
    StringBuilder[] pksValueStringBuilders = getPksValueStringBuilder(rows);
    int primaryKeyColumnSize = getColumnSize("PRIMARY", null, null, pksValueStringBuilders);
    List<Integer> sizesList = getSizesList(getColumnSizesList(rows),
                                           indexColumnSize,
                                           primaryKeyColumnSize);

    StringBuilder stringBuilder = new StringBuilder();
    // Description
    stringBuilder.append("[").append(info.descriptionText()).append("]%n");
    // Line
    stringBuilder.append(getCompleteLine(sizesList));
    // Primary key
    stringBuilder.append(getCompletePrimaryKey(sizesList, pksNameList, columnsNameList,
                                               "", ""));
    // Column name
    stringBuilder.append(getCompleteColumnName(sizesList, columnsNameList,
                                               "", "PRIMARY"));
    // Type
    stringBuilder.append(getCompleteType(sizesList, typesList,
                                         "", "KEY"));
    // Index
    stringBuilder.append(getCompleteIndex(sizesList,
                                          "", ""));
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

    return String.format(stringBuilder.toString());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getRequestRepresentation(WritableAssertionInfo info, Request request) {
    List<String> pksNameList = request.getPksNameList();
    List<String> columnsNameList = request.getColumnsNameList();
    List<Row> rowsList = request.getRowsList();
    Row[] rows = rowsList.toArray(new Row[rowsList.size()]);

    List<String> typesList = getTypesList(rows);
    int indexColumnSize = getIndexColumnSize(rows.length);
    StringBuilder[] pksValueStringBuilders = getPksValueStringBuilder(rows);
    int primaryKeyColumnSize = getColumnSize("PRIMARY", null, null, pksValueStringBuilders);
    List<Integer> sizesList = getSizesList(getColumnSizesList(rows),
                                           indexColumnSize,
                                           primaryKeyColumnSize);

    StringBuilder stringBuilder = new StringBuilder();
    // Description
    stringBuilder.append("[").append(info.descriptionText()).append("]%n");
    // Line
    stringBuilder.append(getCompleteLine(sizesList));
    // Primary key
    stringBuilder.append(getCompletePrimaryKey(sizesList, pksNameList, columnsNameList,
                                               "", ""));
    // Column name
    stringBuilder.append(getCompleteColumnName(sizesList, columnsNameList,
                                               "", "PRIMARY"));
    // Type
    stringBuilder.append(getCompleteType(sizesList, typesList,
                                         "", "KEY"));
    // Index
    stringBuilder.append(getCompleteIndex(sizesList,
                                          "", ""));
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

    return String.format(stringBuilder.toString());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getChangesRepresentation(WritableAssertionInfo info, Changes changes) {
    List<Change> changesList = changes.getChangesList();
    Change[] changesArray = changesList.toArray(new Change[changesList.size()]);
    int indexColumnSize = getIndexColumnSize(changesList.size());
    int changeTypeColumnSize = getChangeTypeColumnSize(changesArray);
    int dataTypeColumnSize = getDataTypeColumnSize(changesArray);
    StringBuilder[] pksValueStringBuilders = getPksValueStringBuilder(changesArray);
    int primaryKeyColumnSize = getColumnSize("PRIMARY", null, null, pksValueStringBuilders);

    StringBuilder stringBuilder = new StringBuilder();
    // Description
    stringBuilder.append("[").append(info.descriptionText()).append("]%n");
    int index = 0;
    for (Change change : changesList) {
      ChangeType changeType = change.getChangeType();
      DataType dataType = change.getDataType();
      String dataName = getDataName(change);
      List<String> columnsNameList = change.getColumnsNameList();
      Row rowAtStartPoint = change.getRowAtStartPoint();
      Row rowAtEndPoint = change.getRowAtEndPoint();
      List<String> typesList = getTypesList(rowAtStartPoint, rowAtEndPoint);

      List<Integer> sizesList = getSizesList(getColumnSizesList(rowAtStartPoint, rowAtEndPoint),
                                             indexColumnSize,
                                             changeTypeColumnSize,
                                             dataTypeColumnSize,
                                             primaryKeyColumnSize, 16);
      // Line
      stringBuilder.append(getCompleteLine(sizesList));
      // Primary key
      stringBuilder.append(getCompletePrimaryKey(sizesList, change.getPksNameList(), columnsNameList,
                                                 "", "", "", "", ""));
      // Column name
      stringBuilder.append(getCompleteColumnName(sizesList, columnsNameList,
                                                 "", "TYPE", "" + dataType, "PRIMARY", ""));
      // Type
      stringBuilder.append(getCompleteType(sizesList, typesList,
                                           "", "", "", "KEY", ""));
      // Index
      stringBuilder.append(getCompleteIndex(sizesList,
                                            "", "", "", "", ""));
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

    return String.format(stringBuilder.toString());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getChangeRepresentation(WritableAssertionInfo info, Change change) {
    ChangeType changeType = change.getChangeType();
    DataType dataType = change.getDataType();
    String dataName = getDataName(change);
    List<String> columnsNameList = change.getColumnsNameList();
    Row rowAtStartPoint = change.getRowAtStartPoint();
    Row rowAtEndPoint = change.getRowAtEndPoint();

    StringBuilder[] pksValueStringBuilders = getPksValueStringBuilder(change);
    List<String> typesList = getTypesList(rowAtStartPoint, rowAtEndPoint);

    int changeTypeColumnSize = getColumnSize("TYPE", null, null, changeType);
    int dataTypeColumnSize = getColumnSize("" + dataType, null, null, dataName);
    int primaryKeyColumnSize = getColumnSize("PRIMARY", null, null, pksValueStringBuilders);
    List<Integer> sizesList = getSizesList(getColumnSizesList(rowAtStartPoint, rowAtEndPoint),
                                           changeTypeColumnSize,
                                           dataTypeColumnSize,
                                           primaryKeyColumnSize, 16);

    StringBuilder stringBuilder = new StringBuilder();
    // Description
    stringBuilder.append("[").append(info.descriptionText()).append("]%n");
    // Line
    stringBuilder.append(getCompleteLine(sizesList));
    // Primary key
    stringBuilder.append(getCompletePrimaryKey(sizesList, change.getPksNameList(), columnsNameList,
                                               "", "", "", ""));
    // Column name
    stringBuilder.append(getCompleteColumnName(sizesList, columnsNameList,
                                               "TYPE", "" + dataType, "PRIMARY", ""));
    // Type
    stringBuilder.append(getCompleteType(sizesList, typesList,
                                         "", "", "KEY", ""));
    // Index
    stringBuilder.append(getCompleteIndex(sizesList,
                                          "", "", "", ""));
    // Line
    stringBuilder.append(getCompleteLine(sizesList));
    // Value at start point
    stringBuilder.append(getCompleteRow(sizesList, rowAtStartPoint,
                                        "", "", "", "At start point"));
    // Line
    stringBuilder.append(getCompleteLine(sizesList, changeType, dataName, pksValueStringBuilders[0]));
    // Value at end point
    stringBuilder.append(getCompleteRow(sizesList, rowAtEndPoint,
                                        "", "", "", "At end point"));
    // Line
    stringBuilder.append(getCompleteLine(sizesList));

    return String.format(stringBuilder.toString());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getRowRepresentation(WritableAssertionInfo info, Row row) {
    List<String> columnsNameList = row.getColumnsNameList();
    List<String> typesList = getTypesList(row);
    List<Integer> columnSizesList = getColumnSizesList(row);

    StringBuilder stringBuilder = new StringBuilder();
    // Description
    stringBuilder.append("[").append(info.descriptionText()).append("]%n");
    // Line
    stringBuilder.append(getCompleteLine(columnSizesList));
    // Primary key
    stringBuilder.append(getCompletePrimaryKey(columnSizesList, row.getPksNameList(), columnsNameList));
    // Column name
    stringBuilder.append(getCompleteColumnName(columnSizesList, columnsNameList));
    // Type
    stringBuilder.append(getCompleteType(columnSizesList, typesList));
    // Index
    stringBuilder.append(getCompleteIndex(columnSizesList));
    // Line
    stringBuilder.append(getCompleteLine(columnSizesList));
    // Value
    stringBuilder.append(getCompleteRow(columnSizesList, row));
    // Line
    stringBuilder.append(getCompleteLine(columnSizesList));

    return String.format(stringBuilder.toString());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getColumnRepresentation(WritableAssertionInfo info, Column column) {
    String columnName = column.getName();
    List<Value> valuesList = column.getValuesList();
    Value[] values = valuesList.toArray(new Value[valuesList.size()]);
    int indexColumnSize = getIndexColumnSize(values.length);
    String type = getType(values);
    int columnSize = getColumnSize(columnName, type, null, values);
    List<Integer> sizesList = getSizesList(null,
                                           indexColumnSize,
                                           columnSize);

    StringBuilder stringBuilder = new StringBuilder();
    // Description
    stringBuilder.append("[").append(info.descriptionText()).append("]%n");
    // Line
    stringBuilder.append(getCompleteLine(sizesList));
    // Column name
    stringBuilder.append("|").append(getFilledText("", indexColumnSize)).append("|").append(getFilledText(columnName, columnSize)).append("|%n");
    // Type
    stringBuilder.append("|").append(getFilledText("", indexColumnSize)).append("|").append(getFilledText(type, columnSize)).append("|%n");
    // Line
    stringBuilder.append(getCompleteLine(sizesList));
    // Value
    int index = 0;
    for (Value value : values) {
      stringBuilder.append("|").append(getFilledText(getText("Index : " + index), indexColumnSize))
                   .append("|").append(getFilledText(getText(value), columnSize)).append("|%n");
      index++;
    }
    // Line
    stringBuilder.append(getCompleteLine(sizesList));

    return String.format(stringBuilder.toString());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getValueRepresentation(WritableAssertionInfo info, String columnName, Value value) {
    String type = getType(value);
    int columnSize = getColumnSize(columnName, type, null, value);

    StringBuilder stringBuilder = new StringBuilder();
    // Description
    stringBuilder.append("[").append(info.descriptionText()).append("]%n");
    // Line
    stringBuilder.append("|").append(getCellLine(columnSize)).append("|%n");
    // Column name
    stringBuilder.append("|").append(getFilledText(columnName, columnSize)).append("|%n");
    // Type
    stringBuilder.append("|").append(getFilledText(type, columnSize)).append("|%n");
    // Line
    stringBuilder.append("|").append(getCellLine(columnSize)).append("|%n");
    // Value
    stringBuilder.append("|").append(getFilledText(getText(value), columnSize)).append("|%n");
    // Line
    stringBuilder.append("|").append(getCellLine(columnSize)).append("|%n");

    return String.format(stringBuilder.toString());
  }
}
