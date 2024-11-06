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

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.db.type.Change;
import org.assertj.db.type.Changes;
import org.assertj.db.type.Column;
import org.assertj.db.type.DataType;
import org.assertj.db.type.DateTimeValue;
import org.assertj.db.type.DateValue;
import org.assertj.db.type.Request;
import org.assertj.db.type.Row;
import org.assertj.db.type.Table;
import org.assertj.db.type.TimeValue;
import org.assertj.db.type.Value;
import org.assertj.db.type.ValueType;

/**
 * Enumeration of the different types of output.
 *
 * @author Régis Pouiller
 * @since 1.1.0
 */
public enum OutputType implements Output {

  /**
   * Plain output.
   *
   * @see PlainOutput
   */
  PLAIN(PlainOutput.INSTANCE),
  /**
   * Html output.
   *
   * @see HtmlOutput
   */
  HTML(HtmlOutput.INSTANCE);

  /**
   * The implementation of the output.
   */
  private final Output displayer;

  /**
   * Constructor.
   *
   * @param displayer The implementation of the output.
   */
  OutputType(Output displayer) {
    this.displayer = displayer;
  }

  /**
   * Returns the data name of the change.
   *
   * @param change The change.
   * @return The data name.
   */
  static String getDataName(Change change) {
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
  static String getType(Value... values) {
    for (Value value : values) {
      if (value.getValue() != null) {
        return "(" + value.getValueTypeRepresentation() + ")";
      }
    }
    return "(" + ValueType.NOT_IDENTIFIED + ")";
  }

  /**
   * Returns the text representing a value.
   *
   * @param value The value
   * @return The text.
   */
  static String getText(Value value) {
    Object object = value.getValue();
    ValueType type = value.getValueType();
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
   * Returns the labels for the columns corresponding to the type of the values of the column.
   *
   * @param rows The rows.
   * @return The labels.
   */
  static List<String> getTypesList(Row... rows) {
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
        List<Value> valuesList = new ArrayList<>();
        for (Row row : rows) {
          if (row != null) {
            Value value = row.getValuesList().get(index);
            valuesList.add(value);
          }
        }
        String type = OutputType.getType(valuesList.toArray(new Value[0]));
        typesList.add(type);
      }
    }
    return typesList;
  }

  /**
   * Returns a {@code StringBuilder} representing the values of the primary key.
   *
   * @param rows The rows
   * @return The output.
   */
  static StringBuilder[] getPksValueStringBuilder(Row... rows) {
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
    return stringBuildersList.toArray(new StringBuilder[0]);
  }

  /**
   * Returns a {@code StringBuilder} representing the values of the primary key.
   *
   * @param changes The changes
   * @return The output.
   */
  static StringBuilder[] getPksValueStringBuilder(Change... changes) {
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
    return stringBuildersList.toArray(new StringBuilder[0]);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getTableOutput(WritableAssertionInfo info, Table table) {
    return displayer.getTableOutput(info, table);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getRequestOutput(WritableAssertionInfo info, Request request) {
    return displayer.getRequestOutput(info, request);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getChangesOutput(WritableAssertionInfo info, Changes changes) {
    return displayer.getChangesOutput(info, changes);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getChangeOutput(WritableAssertionInfo info, Change change) {
    return displayer.getChangeOutput(info, change);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getRowOutput(WritableAssertionInfo info, Row row) {
    return displayer.getRowOutput(info, row);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getColumnOutput(WritableAssertionInfo info, Column column) {
    return displayer.getColumnOutput(info, column);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getChangeColumnOutput(WritableAssertionInfo info, String columnName,
                                      Value valueAtStartPoint, Value valueAtEndPoint) {

    return displayer.getChangeColumnOutput(info, columnName, valueAtStartPoint, valueAtEndPoint);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getValueOutput(WritableAssertionInfo info, Value value) {
    return displayer.getValueOutput(info, value);
  }
}
