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
 * Enumeration of the different types of representation.
 *
 * @author Régis Pouiller
 * @since 1.1.0
 */
public enum RepresentationType implements Representation {

  /**
   * Plain representation.
   * @see PlainRepresentation
   */
  PLAIN(PlainRepresentation.INSTANCE),
  /**
   * Html representation.
   * @see HtmlRepresentation
   */
  HTML(HtmlRepresentation.INSTANCE);

  /**
   * Returns the data name of the change.
   * @param change The change.
   * @return The data name.
   */
  public static String getDataName(Change change) {
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
  public static String getType(Value... values) {
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
  public static String getText(Value value) {
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
  public static List<String> getTypesList(Row... rows) {
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
        String type = RepresentationType.getType(valuesList.toArray(new Value[valuesList.size()]));
        typesList.add(type);
      }
    }
    return typesList;
  }

  /**
   * Returns a {@code StringBuilder} representing the values of the primary key.
   *
   * @param rows The rows
   * @return The representation.
   */
  public static StringBuilder[] getPksValueStringBuilder(Row... rows) {
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
   * Returns a {@code StringBuilder} representing the values of the primary key.
   *
   * @param changes The changes
   * @return The representation.
   */
  public static StringBuilder[] getPksValueStringBuilder(Change... changes) {
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
   * The implementation of the display.
   */
  private final Representation displayer;

  /**
   * Constructor.
   * @param displayer The implementation of the display.
   */
  RepresentationType(Representation displayer) {
    this.displayer = displayer;
  }

  /** {@inheritDoc} */
  @Override
  public String getTableRepresentation(WritableAssertionInfo info, Table table) {
    return displayer.getTableRepresentation(info, table);
  }

  /** {@inheritDoc} */
  @Override
  public String getRequestRepresentation(WritableAssertionInfo info, Request request) {
    return displayer.getRequestRepresentation(info, request);
  }

  /** {@inheritDoc} */
  @Override
  public String getChangesRepresentation(WritableAssertionInfo info, Changes changes) {
    return displayer.getChangesRepresentation(info, changes);
  }

  /** {@inheritDoc} */
  @Override
  public String getChangeRepresentation(WritableAssertionInfo info, Change change) {
    return displayer.getChangeRepresentation(info, change);
  }

  /** {@inheritDoc} */
  @Override
  public String getRowRepresentation(WritableAssertionInfo info, Row row) {
    return displayer.getRowRepresentation(info, row);
  }

  /** {@inheritDoc} */
  @Override
  public String getColumnRepresentation(WritableAssertionInfo info, Column column) {
    return displayer.getColumnRepresentation(info, column);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getChangeColumnRepresentation(WritableAssertionInfo info, String columnName,
                                              Value valueAtStartPoint, Value valueAtEndPoint) {

    return displayer.getChangeColumnRepresentation(info, columnName, valueAtStartPoint, valueAtEndPoint);
  }

  /** {@inheritDoc} */
  @Override
  public String getValueRepresentation(WritableAssertionInfo info, Value value) {
    return displayer.getValueRepresentation(info, value);
  }
}
