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
import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.ChangeType;
import org.assertj.db.type.DataType;
import org.assertj.db.type.Row;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test on the utility class {@code HtmlRepresentation}.
 *
 * @author Régis Pouiller
 */
public class HtmlRepresentation_Test extends AbstractTest {

  private static String htmlTextFromClassPathOf(String resource) throws IOException {
    ClassLoader classLoader = HtmlRepresentation_Test.class.getClassLoader();
    try (InputStream inputStream = classLoader.getResourceAsStream("representation/html/" + resource)) {
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

      int byteInt = inputStream.read();
      while (byteInt != -1) {
        byteArrayOutputStream.write(byteInt);
        byteInt = inputStream.read();
      }

      return byteArrayOutputStream.toString();
    }
  }

  /**
   * This method tests the {@code getTableRepresentation} display method.
   */
  @Test
  public void test_table_representation() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Row row1 = getRow(Arrays.asList("column1", "column5"),
                      Arrays.asList("column1", "column2", "column3", "column4",
                                    "column5", "column6", "column7", "column8",
                                    "column9", "column10"),
                      Arrays.asList(getValue(null, null), getValue(null, true), getValue(null, new byte[] { 0, 1 }),
                                    getValue(null, Timestamp.valueOf("2007-12-23 09:01:00")),
                                    getValue(null, Date.valueOf("2007-12-23")), getValue(null, 8),
                                    getValue(null, Locale.FRENCH), getValue(null, "test"),
                                    getValue(null, Time.valueOf("09:01:00")),
                                    getValue(null, UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"))));
    Row row2 = getRow(Arrays.asList("column1", "column5"),
                      Arrays.asList("column1", "column2", "column3", "column4",
                                    "column5", "column6", "column7", "column8",
                                    "column9", "column10"),
                      Arrays.asList(getValue(null, null), getValue(null, false), getValue(null, new byte[] { 2, 3 }),
                                    getValue(null, Timestamp.valueOf("2002-07-25 03:30:05")),
                                    getValue(null, Date.valueOf("2002-07-25")), getValue(null, 9),
                                    getValue(null, Locale.ENGLISH), getValue(null, "test2"),
                                    getValue(null, Time.valueOf("03:30:05")),
                                    getValue(null, UUID.fromString("0E2A1269-EFF0-4233-B87B-B53E8B6F164D"))));
    Row row3 = getRow(Arrays.asList("column1", "column5", "column6"),
                      Arrays.asList("column1", "column2", "column3", "column4",
                                    "column5", "column6", "column7", "column8",
                                    "column9", "column10"),
                      Arrays.asList(getValue(null, null), getValue(null, false), getValue(null, new byte[] { 2, 3 }),
                                    getValue(null, Timestamp.valueOf("2002-07-25 03:30:05")),
                                    getValue(null, Date.valueOf("2002-07-25")), getValue(null, 9),
                                    getValue(null, Locale.ENGLISH), getValue(null, "test2"),
                                    getValue(null, Time.valueOf("03:30:05")),
                                    getValue(null, UUID.fromString("0E2A1269-EFF0-4233-B87B-B53E8B6F164D"))));

    assertThat(HtmlRepresentation.INSTANCE.getTableRepresentation(info,
                                                                   getTable(row1.getColumnsNameList(), row1.getPksNameList(),
                                                                            Arrays.asList(row1, row2, row3))))
            .isEqualTo(htmlTextFromClassPathOf("test_table_representation1.html"));

    assertThat(HtmlRepresentation.INSTANCE.getTableRepresentation(info,
                                                                   getTable(row1.getColumnsNameList(), row1.getPksNameList(),
                                                                            new ArrayList())))
            .isEqualTo(htmlTextFromClassPathOf("test_table_representation2.html"));
  }

  /**
   * This method tests the {@code getRequestRepresentation} display method.
   */
  @Test
  public void test_request_representation() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Row row1 = getRow(Arrays.asList("column1", "column5"),
                      Arrays.asList("column1", "column2", "column3", "column4",
                                    "column5", "column6", "column7", "column8",
                                    "column9", "column10"),
                      Arrays.asList(getValue(null, null), getValue(null, true), getValue(null, new byte[] { 0, 1 }),
                                    getValue(null, Timestamp.valueOf("2007-12-23 09:01:00")),
                                    getValue(null, Date.valueOf("2007-12-23")), getValue(null, 8),
                                    getValue(null, Locale.FRENCH), getValue(null, "test"),
                                    getValue(null, Time.valueOf("09:01:00")),
                                    getValue(null, UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"))));
    Row row2 = getRow(Arrays.asList("column1", "column5"),
                      Arrays.asList("column1", "column2", "column3", "column4",
                                    "column5", "column6", "column7", "column8",
                                    "column9", "column10"),
                      Arrays.asList(getValue(null, null), getValue(null, false), getValue(null, new byte[] { 2, 3 }),
                                    getValue(null, Timestamp.valueOf("2002-07-25 03:30:05")),
                                    getValue(null, Date.valueOf("2002-07-25")), getValue(null, 9),
                                    getValue(null, Locale.ENGLISH), getValue(null, "test2"),
                                    getValue(null, Time.valueOf("03:30:05")),
                                    getValue(null, UUID.fromString("0E2A1269-EFF0-4233-B87B-B53E8B6F164D"))));
    Row row3 = getRow(Arrays.asList("column1", "column5", "column6"),
                      Arrays.asList("column1", "column2", "column3", "column4",
                                    "column5", "column6", "column7", "column8",
                                    "column9", "column10"),
                      Arrays.asList(getValue(null, null), getValue(null, false), getValue(null, new byte[] { 2, 3 }),
                                    getValue(null, Timestamp.valueOf("2002-07-25 03:30:05")),
                                    getValue(null, Date.valueOf("2002-07-25")), getValue(null, 9),
                                    getValue(null, Locale.ENGLISH), getValue(null, "test2"),
                                    getValue(null, Time.valueOf("03:30:05")),
                                    getValue(null, UUID.fromString("0E2A1269-EFF0-4233-B87B-B53E8B6F164D"))));

    assertThat(HtmlRepresentation.INSTANCE.getRequestRepresentation(info,
                                                                     getRequest(row1.getColumnsNameList(), row1.getPksNameList(), Arrays.asList(row1, row2, row3))))
            .isEqualTo(htmlTextFromClassPathOf("test_request_representation1.html"));

    assertThat(HtmlRepresentation.INSTANCE.getRequestRepresentation(info,
                                                                     getRequest(row1.getColumnsNameList(), row1.getPksNameList(), new ArrayList())))
            .isEqualTo(htmlTextFromClassPathOf("test_request_representation2.html"));
  }

  /**
   * This method tests the {@code getChangesRepresentation} display method.
   */
  @Test
  public void test_changes_representation() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Row row1 = getRow(Arrays.asList("column1", "column5"),
                      Arrays.asList("column1", "column2", "column3", "column4",
                                    "column5", "column6", "column7", "column8",
                                    "column9", "column10"),
                      Arrays.asList(getValue(null, null), getValue(null, true), getValue(null, new byte[] { 0, 1 }),
                                    getValue(null, Timestamp.valueOf("2007-12-23 09:01:00")),
                                    getValue(null, Date.valueOf("2007-12-23")), getValue(null, 8),
                                    getValue(null, Locale.FRENCH), getValue(null, "test"),
                                    getValue(null, Time.valueOf("09:01:00")),
                                    getValue(null, UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"))));
    Row row2 = getRow(Arrays.asList("column1", "column5"),
                      Arrays.asList("column1", "column2", "column3", "column4",
                                    "column5", "column6", "column7", "column8",
                                    "column9", "column10"),
                      Arrays.asList(getValue(null, null), getValue(null, false), getValue(null, new byte[] { 2, 3 }),
                                    getValue(null, Timestamp.valueOf("2002-07-25 03:30:05")),
                                    getValue(null, Date.valueOf("2002-07-25")), getValue(null, 9),
                                    getValue(null, Locale.ENGLISH), getValue(null, "test2"),
                                    getValue(null, Time.valueOf("03:30:05")),
                                    getValue(null, UUID.fromString("0E2A1269-EFF0-4233-B87B-B53E8B6F164D"))));
    Row row3 = getRow(Arrays.asList("column1", "column5", "column6"),
                      Arrays.asList("column1", "column2", "column3", "column4",
                                    "column5", "column6", "column7", "column8",
                                    "column9", "column10"),
                      Arrays.asList(getValue(null, null), getValue(null, false), getValue(null, new byte[] { 2, 3 }),
                                    getValue(null, Timestamp.valueOf("2002-07-25 03:30:05")),
                                    getValue(null, Date.valueOf("2002-07-25")), getValue(null, 9),
                                    getValue(null, Locale.ENGLISH), getValue(null, "test2"),
                                    getValue(null, Time.valueOf("03:30:05")),
                                    getValue(null, UUID.fromString("0E2A1269-EFF0-4233-B87B-B53E8B6F164D"))));

    assertThat(HtmlRepresentation.INSTANCE.getChangesRepresentation(info,
                                                                     getChanges(Arrays.asList(getTableCreationChange("table", row3),
                                                                                              getTableModificationChange("table", row1,
                                                                                                                         row3),
                                                                                              getTableDeletionChange("table", row1),
                                                                                              getChange(DataType.REQUEST,
                                                                                                        "select * from table",
                                                                                                        ChangeType.MODIFICATION, row1,
                                                                                                        row2),
                                                                                              getChange(DataType.REQUEST,
                                                                                                        "select id, name, firstname, birth, actor_imdb from actor",
                                                                                                        ChangeType.MODIFICATION, row1,
                                                                                                        row2)))))
            .isEqualTo(htmlTextFromClassPathOf("test_changes_representation1.html"));

    assertThat(HtmlRepresentation.INSTANCE.getChangesRepresentation(info,
                                                                     getChanges(new ArrayList())))
            .isEqualTo(htmlTextFromClassPathOf("test_changes_representation2.html"));
  }

  /**
   * This method tests the {@code getChangeRepresentation} display method.
   */
  @Test
  public void test_change_representation() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Row row1 = getRow(Arrays.asList("column1", "column5"),
                      Arrays.asList("column1", "column2", "column3", "column4",
                                    "column5", "column6", "column7", "column8",
                                    "column9", "column10"),
                      Arrays.asList(getValue(null, null), getValue(null, true), getValue(null, new byte[] { 0, 1 }),
                                    getValue(null, Timestamp.valueOf("2007-12-23 09:01:00")),
                                    getValue(null, Date.valueOf("2007-12-23")), getValue(null, 8),
                                    getValue(null, Locale.FRENCH), getValue(null, "test"),
                                    getValue(null, Time.valueOf("09:01:00")),
                                    getValue(null, UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"))));
    Row row2 = getRow(Arrays.asList("column1", "column5"),
                      Arrays.asList("column1", "column2", "column3", "column4",
                                    "column5", "column6", "column7", "column8",
                                    "column9", "column10"),
                      Arrays.asList(getValue(null, null), getValue(null, false), getValue(null, new byte[] { 2, 3 }),
                                    getValue(null, Timestamp.valueOf("2002-07-25 03:30:05")),
                                    getValue(null, Date.valueOf("2002-07-25")), getValue(null, 9),
                                    getValue(null, Locale.ENGLISH), getValue(null, "test2"),
                                    getValue(null, Time.valueOf("03:30:05")),
                                    getValue(null, UUID.fromString("0E2A1269-EFF0-4233-B87B-B53E8B6F164D"))));

    assertThat(HtmlRepresentation.INSTANCE.getChangeRepresentation(info, getTableCreationChange("table", row2)))
            .isEqualTo(htmlTextFromClassPathOf("test_change_representation1.html"));
    assertThat(HtmlRepresentation.INSTANCE.getChangeRepresentation(info, getTableModificationChange("table", row1, row2)))
            .isEqualTo(htmlTextFromClassPathOf("test_change_representation2.html"));
    assertThat(HtmlRepresentation.INSTANCE.getChangeRepresentation(info, getTableDeletionChange("table", row1)))
            .isEqualTo(htmlTextFromClassPathOf("test_change_representation3.html"));
    assertThat(HtmlRepresentation.INSTANCE.getChangeRepresentation(info,
                                                                    getChange(DataType.REQUEST, "select * from table", ChangeType.MODIFICATION, row1, row2)))
            .isEqualTo(htmlTextFromClassPathOf("test_change_representation4.html"));
    assertThat(HtmlRepresentation.INSTANCE.getChangeRepresentation(info,
                                                                    getChange(DataType.REQUEST, "select id, name, firstname, birth, actor_imdb from actor",
                                                                              ChangeType.MODIFICATION, row1, row2)))
            .isEqualTo(htmlTextFromClassPathOf("test_change_representation5.html"));
  }

  /**
   * This method tests the {@code getRowRepresentation} display method.
   */
  @Test
  public void test_row_representation() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    assertThat(HtmlRepresentation.INSTANCE.getRowRepresentation(info, getRow(Arrays.asList("column1", "column5"),
                                                                              Arrays.asList("column1", "column2", "column3",
                                                                                            "column4", "column5", "column6",
                                                                                            "column7", "column8", "column9",
                                                                                            "column10"),
                                                                              Arrays.asList(getValue(null, null), getValue(
                                                                                                    null, true),
                                                                                            getValue(null, new byte[] { 0, 1 }),
                                                                                            getValue(null, Timestamp.valueOf(
                                                                                                    "2007-12-23 09:01:00")),
                                                                                            getValue(null, Date.valueOf("2007-12-23")),
                                                                                            getValue(null, 8),
                                                                                            getValue(null, Locale.FRENCH), getValue(
                                                                                              null, "test"),
                                                                                            getValue(null, Time.valueOf("09:01:00")),
                                                                                            getValue(null, UUID.fromString(
                                                                                                    "30B443AE-C0C9-4790-9BEC-CE1380808435"))))))
            .isEqualTo(htmlTextFromClassPathOf("test_row_representation.html"));
  }

  /**
   * This method tests the {@code getColumnRepresentation} display method.
   */
  @Test
  public void test_column_representation() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    assertThat(HtmlRepresentation.INSTANCE.getColumnRepresentation(info, getColumn("column", Arrays.asList(getValue(
            null, null), getValue(null, null)))))
            .isEqualTo(htmlTextFromClassPathOf("test_column_representation1.html"));
    assertThat(HtmlRepresentation.INSTANCE.getColumnRepresentation(info, getColumn("column", Arrays.asList(getValue(
            null, true), getValue(null, false)))))
            .isEqualTo(htmlTextFromClassPathOf("test_column_representation2.html"));
    assertThat(HtmlRepresentation.INSTANCE.getColumnRepresentation(info,
                                                                    getColumn("column", Arrays.asList(getValue(null, new byte[] { 0, 1 }), getValue(null, null)))))
            .isEqualTo(htmlTextFromClassPathOf("test_column_representation3.html"));
    assertThat(HtmlRepresentation.INSTANCE.getColumnRepresentation(info,
                                                                    getColumn("column", Arrays.asList(getValue(null, null), getValue(null, Timestamp.valueOf("2007-12-23 09:01:00")),
                                                                                                      getValue(null, Timestamp.valueOf("2002-07-25 03:30:05"))))))
            .isEqualTo(htmlTextFromClassPathOf("test_column_representation4.html"));
    assertThat(HtmlRepresentation.INSTANCE.getColumnRepresentation(info,
                                                                    getColumn("column", Arrays.asList(getValue(null, Date.valueOf("2007-12-23"))))))
            .isEqualTo(htmlTextFromClassPathOf("test_column_representation5.html"));
    assertThat(HtmlRepresentation.INSTANCE.getColumnRepresentation(info,
                                                                    getColumn("column", Arrays.asList(getValue(null, 8), getValue(null, 1000000000L)))))
            .isEqualTo(htmlTextFromClassPathOf("test_column_representation6.html"));
    assertThat(HtmlRepresentation.INSTANCE.getColumnRepresentation(info,
                                                                    getColumn("column", Arrays.asList(getValue(null, Locale.FRENCH), getValue(null, Locale.ENGLISH)))))
            .isEqualTo(htmlTextFromClassPathOf("test_column_representation7.html"));
    assertThat(HtmlRepresentation.INSTANCE.getColumnRepresentation(info,
                                                                    getColumn("column", Arrays.asList(getValue(null, "test"), getValue(null, "test2")))))
            .isEqualTo(htmlTextFromClassPathOf("test_column_representation8.html"));
    assertThat(HtmlRepresentation.INSTANCE.getColumnRepresentation(info,
                                                                    getColumn("column", Arrays.asList(getValue(null, Time.valueOf("09:01:00")), getValue(null, Time.valueOf("03:30:05"))))))
            .isEqualTo(htmlTextFromClassPathOf("test_column_representation9.html"));
    assertThat(HtmlRepresentation.INSTANCE
                       .getColumnRepresentation(info, getColumn("column",
                                                                Arrays.asList(getValue(null, UUID.fromString(
                                                                                      "30B443AE-C0C9-4790-9BEC-CE1380808435")),
                                                                              getValue(null, UUID.fromString(
                                                                                      "0E2A1269-EFF0-4233-B87B-B53E8B6F164D"))))))
            .isEqualTo(htmlTextFromClassPathOf("test_column_representation10.html"));
    assertThat(HtmlRepresentation.INSTANCE
                       .getColumnRepresentation(info, getColumn("column", new ArrayList())))
            .isEqualTo(htmlTextFromClassPathOf("test_column_representation11.html"));
  }

  /**
   * This method tests the {@code getChangeColumnRepresentation} display method.
   */
  @Test
  public void test_change_column_representation() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");

    assertThat(HtmlRepresentation.INSTANCE.getChangeColumnRepresentation(info, "column2", getValue(null, true), getValue(null, false)))
            .isEqualTo(htmlTextFromClassPathOf("test_change_column_representation1.html"));
    assertThat(HtmlRepresentation.INSTANCE.getChangeColumnRepresentation(info, "column1", getValue(null, null),
                                                                         getValue(null, null)))
            .isEqualTo(htmlTextFromClassPathOf("test_change_column_representation2.html"));
    assertThat(HtmlRepresentation.INSTANCE.getChangeColumnRepresentation(info, "column", getValue(null, UUID.fromString(
                                                                                  "30B443AE-C0C9-4790-9BEC-CE1380808435")),
                                                                          getValue(null, UUID.fromString(
                                                                                  "0E2A1269-EFF0-4233-B87B-B53E8B6F164D"))))
            .isEqualTo(htmlTextFromClassPathOf("test_change_column_representation3.html"));
  }

  /**
   * This method tests the {@code getValueRepresentation} display method.
   */
  @Test
  public void test_value_representation() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    assertThat(HtmlRepresentation.INSTANCE.getValueRepresentation(info, getValue("column", null)))
            .isEqualTo(htmlTextFromClassPathOf("test_value_representation1.html"));
    assertThat(HtmlRepresentation.INSTANCE.getValueRepresentation(info, getValue("column", true)))
            .isEqualTo(htmlTextFromClassPathOf("test_value_representation2.html"));
    assertThat(HtmlRepresentation.INSTANCE.getValueRepresentation(info, getValue("column", new byte[] { 0, 1 })))
            .isEqualTo(htmlTextFromClassPathOf("test_value_representation3.html"));
    assertThat(HtmlRepresentation.INSTANCE.getValueRepresentation(info, getValue("column", Timestamp.valueOf("2007-12-23 09:01:00"))))
            .isEqualTo(htmlTextFromClassPathOf("test_value_representation4.html"));
    assertThat(HtmlRepresentation.INSTANCE.getValueRepresentation(info, getValue("column", Date.valueOf("2007-12-23"))))
            .isEqualTo(htmlTextFromClassPathOf("test_value_representation5.html"));
    assertThat(HtmlRepresentation.INSTANCE.getValueRepresentation(info, getValue("column", 8)))
            .isEqualTo(htmlTextFromClassPathOf("test_value_representation6.html"));
    assertThat(HtmlRepresentation.INSTANCE.getValueRepresentation(info, getValue("column", Locale.FRENCH)))
            .isEqualTo(htmlTextFromClassPathOf("test_value_representation7.html"));
    assertThat(HtmlRepresentation.INSTANCE.getValueRepresentation(info, getValue("column", "test")))
            .isEqualTo(htmlTextFromClassPathOf("test_value_representation8.html"));
    assertThat(HtmlRepresentation.INSTANCE.getValueRepresentation(info, getValue("column", Time.valueOf("09:01:00"))))
            .isEqualTo(htmlTextFromClassPathOf("test_value_representation9.html"));
    assertThat(HtmlRepresentation.INSTANCE.getValueRepresentation(info, getValue("column", UUID.fromString(
            "30B443AE-C0C9-4790-9BEC-CE1380808435"))))
            .isEqualTo(htmlTextFromClassPathOf("test_value_representation10.html"));
  }
}
