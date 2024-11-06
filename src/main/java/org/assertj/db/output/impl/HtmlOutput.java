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

import java.util.List;

/**
 * Implementation of html output of assertj-db.
 *
 * @author Régis Pouiller
 * @since 1.1.0
 */
enum HtmlOutput implements Output {

  /**
   * Singleton instance.
   */
  INSTANCE;

  /**
   * Returns the html document for output.
   * @param info  Writable information about an assertion.
   * @param content Content of the html document.
   * @return The html document.
   */
  private static String getHtml(WritableAssertionInfo info, String content) {

    return "<html><head><title>description</title></head>"
           + "<body><h1>"
           + info.descriptionText()
           + "</h1>"
           + content
           + "</body></html>";
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
    StringBuilder[] pksValueStringBuilders = OutputType.getPksValueStringBuilder(rows);

    StringBuilder stringBuilder = new StringBuilder();

    stringBuilder.append("<table border=\"1\" cellspacing=\"0\">");
    stringBuilder.append("<tr>");
    stringBuilder.append("<th>");
    stringBuilder.append("</th>");
    stringBuilder.append("<th>");
    stringBuilder.append("<br/>PRIMARY<br/>KEY");
    stringBuilder.append("</th>");
    int index = 0;
    for (String columnName : columnsNameList) {
      String pk = "";
      if (pksNameList.contains(columnName)) {
        pk = "*";
      }
      stringBuilder.append("<th>");
      stringBuilder.append(pk);
      stringBuilder.append("<br/>");
      stringBuilder.append(columnName);
      stringBuilder.append("<br/>");
      if (index < typesList.size()) {
        stringBuilder.append(typesList.get(index));
      }
      stringBuilder.append("<br/>Index : ");
      stringBuilder.append(index);
      stringBuilder.append("</th>");

      index++;
    }
    stringBuilder.append("</tr>");
    index = 0;
    for (Row row : table.getRowsList()) {
      stringBuilder.append("<tr>");
      stringBuilder.append("<td>Index :");
      stringBuilder.append(index);
      stringBuilder.append("</td>");
      stringBuilder.append("<td>");
      stringBuilder.append(pksValueStringBuilders[index]);
      stringBuilder.append("</td>");
      for (Value value : row.getValuesList()) {
        stringBuilder.append("<td>");
        stringBuilder.append(OutputType.getText(value));
        stringBuilder.append("</td>");
      }
      stringBuilder.append("</tr>");
      index++;
    }
    stringBuilder.append("</table>");

    return getHtml(info, stringBuilder.toString());
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
    StringBuilder[] pksValueStringBuilders = OutputType.getPksValueStringBuilder(rows);

    StringBuilder stringBuilder = new StringBuilder();

    stringBuilder.append("<table border=\"1\" cellspacing=\"0\">");
    stringBuilder.append("<tr>");
    stringBuilder.append("<th>");
    stringBuilder.append("</th>");
    stringBuilder.append("<th>");
    stringBuilder.append("<br/>PRIMARY<br/>KEY");
    stringBuilder.append("</th>");
    int index = 0;
    for (String columnName : columnsNameList) {
      String pk = "";
      if (pksNameList.contains(columnName)) {
        pk = "*";
      }
      stringBuilder.append("<th>");
      stringBuilder.append(pk);
      stringBuilder.append("<br/>");
      stringBuilder.append(columnName);
      stringBuilder.append("<br/>");
      if (index < typesList.size()) {
        stringBuilder.append(typesList.get(index));
      }
      stringBuilder.append("<br/>Index : ");
      stringBuilder.append(index);
      stringBuilder.append("</th>");

      index++;
    }
    stringBuilder.append("</tr>");
    index = 0;
    for (Row row : request.getRowsList()) {
      stringBuilder.append("<tr>");
      stringBuilder.append("<td>Index :");
      stringBuilder.append(index);
      stringBuilder.append("</td>");
      stringBuilder.append("<td>");
      stringBuilder.append(pksValueStringBuilders[index]);
      stringBuilder.append("</td>");
      for (Value value : row.getValuesList()) {
        stringBuilder.append("<td>");
        stringBuilder.append(OutputType.getText(value));
        stringBuilder.append("</td>");
      }
      stringBuilder.append("</tr>");
      index++;
    }
    stringBuilder.append("</table>");

    return getHtml(info, stringBuilder.toString());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getChangesOutput(WritableAssertionInfo info, Changes changes) {
    StringBuilder stringBuilder = new StringBuilder();

    List<Change> changesList = changes.getChangesList();

    stringBuilder.append("<table border=\"1\" cellspacing=\"0\">");
    stringBuilder.append("<tr>");
    stringBuilder.append("<th>");
    stringBuilder.append("</th>");
    stringBuilder.append("<th>");
    stringBuilder.append("<br/>TYPE");
    stringBuilder.append("</th>");
    stringBuilder.append("<th><br/>");
    stringBuilder.append(changesList.isEmpty() ? "" : changesList.get(0).getDataType());
    stringBuilder.append("</th>");
    stringBuilder.append("<th>");
    stringBuilder.append("<br/>PRIMARY<br/>KEY");
    stringBuilder.append("</th>");
    stringBuilder.append("<th>");
    stringBuilder.append("</th>");
    stringBuilder.append("</tr>");

    int index1 = 0;
    for (Change change : changes.getChangesList()) {
      ChangeType changeType = change.getChangeType();
      String dataName = OutputType.getDataName(change);
      List<String> columnsNameList = change.getColumnsNameList();
      Row rowAtStartPoint = change.getRowAtStartPoint();
      Row rowAtEndPoint = change.getRowAtEndPoint();

      StringBuilder[] pksValueStringBuilders = OutputType.getPksValueStringBuilder(change);
      List<String> typesList = OutputType.getTypesList(rowAtStartPoint, rowAtEndPoint);

      stringBuilder.append("<tr>");
      stringBuilder.append("<td>Index : ");
      stringBuilder.append(index1);
      stringBuilder.append("</td>");
      stringBuilder.append("<td>");
      stringBuilder.append(changeType);
      stringBuilder.append("</td>");
      stringBuilder.append("<td>");
      stringBuilder.append(dataName);
      stringBuilder.append("</td>");
      stringBuilder.append("<td>");
      stringBuilder.append(pksValueStringBuilders[0]);
      stringBuilder.append("</td>");
      stringBuilder.append("<td>");
      stringBuilder.append("<table border=\"1\" cellspacing=\"0\">");
      stringBuilder.append("<tr>");
      stringBuilder.append("<th>");
      stringBuilder.append("</th>");
      int index = 0;
      for (String columnName : columnsNameList) {
        String pk = "";
        if (change.getPksNameList().contains(columnName)) {
          pk = "*";
        }
        stringBuilder.append("<th>");
        stringBuilder.append(pk);
        stringBuilder.append("<br/>");
        stringBuilder.append(columnName);
        stringBuilder.append("<br/>");
        stringBuilder.append(typesList.get(index));
        stringBuilder.append("<br/>Index : ");
        stringBuilder.append(index);
        stringBuilder.append("</th>");

        index++;
      }
      stringBuilder.append("</tr>");
      stringBuilder.append("<tr>");
      stringBuilder.append("<td>");
      stringBuilder.append("At start point");
      stringBuilder.append("</td>");
      if (change.getRowAtStartPoint() == null) {
        for (String s : change.getColumnsNameList()) {
          stringBuilder.append("<td>");
          stringBuilder.append("</td>");
        }
      } else {
        for (Value value : change.getRowAtStartPoint().getValuesList()) {
          stringBuilder.append("<td>");
          stringBuilder.append(OutputType.getText(value));
          stringBuilder.append("</td>");
        }
      }
      stringBuilder.append("</tr>");
      stringBuilder.append("<tr>");
      stringBuilder.append("<td>");
      stringBuilder.append("At end point");
      stringBuilder.append("</td>");
      if (change.getRowAtEndPoint() == null) {
        for (String s : change.getColumnsNameList()) {
          stringBuilder.append("<td>");
          stringBuilder.append("</td>");
        }
      } else {
        for (Value value : change.getRowAtEndPoint().getValuesList()) {
          stringBuilder.append("<td>");
          stringBuilder.append(OutputType.getText(value));
          stringBuilder.append("</td>");
        }
      }
      stringBuilder.append("</tr>");
      stringBuilder.append("</table>");
      stringBuilder.append("</td>");
      stringBuilder.append("</tr>");
      index1++;
    }
    stringBuilder.append("</table>");

    return getHtml(info, stringBuilder.toString());
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

    StringBuilder stringBuilder = new StringBuilder();

    stringBuilder.append("<table border=\"1\" cellspacing=\"0\">");
    stringBuilder.append("<tr>");
    stringBuilder.append("<th>");
    stringBuilder.append("<br/>TYPE");
    stringBuilder.append("</th>");
    stringBuilder.append("<th><br/>");
    stringBuilder.append(dataType);
    stringBuilder.append("</th>");
    stringBuilder.append("<th>");
    stringBuilder.append("<br/>PRIMARY<br/>KEY");
    stringBuilder.append("</th>");
    stringBuilder.append("<th>");
    stringBuilder.append("</th>");
    stringBuilder.append("</tr>");
    stringBuilder.append("<tr>");
    stringBuilder.append("<td>");
    stringBuilder.append(changeType);
    stringBuilder.append("</td>");
    stringBuilder.append("<td>");
    stringBuilder.append(dataName);
    stringBuilder.append("</td>");
    stringBuilder.append("<td>");
    stringBuilder.append(pksValueStringBuilders[0]);
    stringBuilder.append("</td>");
    stringBuilder.append("<td>");


    stringBuilder.append("<table border=\"1\" cellspacing=\"0\">");
    stringBuilder.append("<tr>");
    stringBuilder.append("<th>");
    stringBuilder.append("</th>");
    int index = 0;
    for (String columnName : columnsNameList) {
      String pk = "";
      if (change.getPksNameList().contains(columnName)) {
        pk = "*";
      }
      stringBuilder.append("<th>");
      stringBuilder.append(pk);
      stringBuilder.append("<br/>");
      stringBuilder.append(columnName);
      stringBuilder.append("<br/>");
      stringBuilder.append(typesList.get(index));
      stringBuilder.append("<br/>Index : ");
      stringBuilder.append(index);
      stringBuilder.append("</th>");

      index++;
    }
    stringBuilder.append("</tr>");

    stringBuilder.append("<tr>");
    stringBuilder.append("<td>");
    stringBuilder.append("At start point");
    stringBuilder.append("</td>");
    if (change.getRowAtStartPoint() == null) {
      for (String s : change.getColumnsNameList()) {
        stringBuilder.append("<td>");
        stringBuilder.append("</td>");
      }
    } else {
      for (Value value : change.getRowAtStartPoint().getValuesList()) {
        stringBuilder.append("<td>");
        stringBuilder.append(OutputType.getText(value));
        stringBuilder.append("</td>");
      }
    }
    stringBuilder.append("</tr>");
    stringBuilder.append("<tr>");
    stringBuilder.append("<td>");
    stringBuilder.append("At end point");
    stringBuilder.append("</td>");
    if (change.getRowAtEndPoint() == null) {
      for (String s : change.getColumnsNameList()) {
        stringBuilder.append("<td>");
        stringBuilder.append("</td>");
      }
    } else {
      for (Value value : change.getRowAtEndPoint().getValuesList()) {
        stringBuilder.append("<td>");
        stringBuilder.append(OutputType.getText(value));
        stringBuilder.append("</td>");
      }
    }
    stringBuilder.append("</tr>");
    stringBuilder.append("</table>");
    stringBuilder.append("</td>");
    stringBuilder.append("</tr>");
    stringBuilder.append("</table>");

    return getHtml(info, stringBuilder.toString());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getRowOutput(WritableAssertionInfo info, Row row) {
    if (row == null) {
      return getHtml(info, "Row does not exist");
    }
    List<String> columnsNameList = row.getColumnsNameList();
    List<String> typesList = OutputType.getTypesList(row);
    StringBuilder[] pksValueStringBuilders = OutputType.getPksValueStringBuilder(row);

    StringBuilder stringBuilder = new StringBuilder();

    stringBuilder.append("<table border=\"1\" cellspacing=\"0\">");
    stringBuilder.append("<tr>");
    stringBuilder.append("<th>");
    stringBuilder.append("<br/>PRIMARY<br/>KEY");
    stringBuilder.append("</th>");
    int index = 0;
    for (String columnName : columnsNameList) {
      String pk = "";
      if (row.getPksNameList().contains(columnName)) {
        pk = "*";
      }
      stringBuilder.append("<th>");
      stringBuilder.append(pk);
      stringBuilder.append("<br/>");
      stringBuilder.append(columnName);
      stringBuilder.append("<br/>");
      stringBuilder.append(typesList.get(index));
      stringBuilder.append("<br/>Index : ");
      stringBuilder.append(index);
      stringBuilder.append("</th>");

      index++;
    }
    stringBuilder.append("</tr>");
    stringBuilder.append("<tr>");
    stringBuilder.append("<td>");
    stringBuilder.append(pksValueStringBuilders[0]);
    stringBuilder.append("</td>");
    for (Value value : row.getValuesList()) {
      stringBuilder.append("<td>");
      stringBuilder.append(OutputType.getText(value));
      stringBuilder.append("</td>");
    }
    stringBuilder.append("</tr>");
    stringBuilder.append("</table>");

    return getHtml(info, stringBuilder.toString());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getColumnOutput(WritableAssertionInfo info, Column column) {
    String columnName = column.getName();
    List<Value> valuesList = column.getValuesList();
    Value[] values = valuesList.toArray(new Value[0]);
    String type = OutputType.getType(values);

    StringBuilder stringBuilder = new StringBuilder();

    stringBuilder.append("<table border=\"1\" cellspacing=\"0\">");
    stringBuilder.append("<tr>");
    stringBuilder.append("<th>");
    stringBuilder.append("</th>");
    stringBuilder.append("<th>");
    stringBuilder.append(columnName);
    stringBuilder.append("<br/>");
    stringBuilder.append(type);
    stringBuilder.append("</th>");
    stringBuilder.append("</tr>");
    int index = 0;
    for (Value value : values) {
      stringBuilder.append("<tr>");
      stringBuilder.append("<td>Index : ");
      stringBuilder.append(index);
      stringBuilder.append("</td>");
      stringBuilder.append("<td>");
      stringBuilder.append(OutputType.getText(value));
      stringBuilder.append("</td>");
      stringBuilder.append("</tr>");
      index++;
    }
    stringBuilder.append("</table>");

    return getHtml(info, stringBuilder.toString());
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

    String stringBuilder = "<table border=\"1\" cellspacing=\"0\">"
                           + "<tr>"
                           + "<th>"
                           + "</th>"
                           + "<th>"
                           + columnName
                           + "<br/>"
                           + type
                           + "</th>"
                           + "</tr>"
                           + "<tr>"
                           + "<td>At start point</td>"
                           + "<td>"
                           + OutputType.getText(valueAtStartPoint)
                           + "</td>"
                           + "</tr>"
                           + "<tr>"
                           + "<td>At end point</td>"
                           + "<td>"
                           + OutputType.getText(valueAtEndPoint)
                           + "</td>"
                           + "</tr>"
                           + "</table>";
    return getHtml(info, stringBuilder);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getValueOutput(WritableAssertionInfo info, Value value) {
    String columnName = value.getColumnName();
    String type = OutputType.getType(value);

    String stringBuilder = "<table border=\"1\" cellspacing=\"0\">"
                           + "<tr>"
                           + "<th>"
                           + columnName
                           + "<br/>"
                           + type
                           + "</th>"
                           + "</tr>"
                           + "<tr>"
                           + "<td>"
                           + OutputType.getText(value)
                           + "</td>"
                           + "</tr>"
                           + "</table>";
    return getHtml(info, stringBuilder);
  }
}
