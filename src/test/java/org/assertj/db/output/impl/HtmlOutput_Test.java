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
 * Copyright 2012-2016 the original author or authors.
 */
package org.assertj.db.output.impl;

import static org.assertj.core.api.Assertions.assertThat;

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

import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Change;
import org.assertj.db.type.ChangeType;
import org.assertj.db.type.DataType;
import org.assertj.db.type.Row;
import org.assertj.db.type.Value;
import org.junit.Test;

/**
 * Test on the utility class {@code HtmlRepresentation}.
 *
 * @author Régis Pouiller
 */
public class HtmlOutput_Test extends AbstractTest {

  private static String htmlTextFromClassPathOf(String resource) throws IOException {
    ClassLoader classLoader = HtmlOutput_Test.class.getClassLoader();
    try (InputStream inputStream = classLoader.getResourceAsStream("output/html/" + resource)) {
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
   * This method tests the {@code getTableOutput} output method.
   */
  @Test
  public void test_table_output() throws Exception {
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

    assertThat(HtmlOutput.INSTANCE.getTableOutput(info,
                                                  getTable(row1.getColumnsNameList(), row1.getPksNameList(),
                                                           Arrays.asList(row1, row2, row3))))
            .isEqualTo(htmlTextFromClassPathOf("test_table_output1.html"));

    assertThat(HtmlOutput.INSTANCE.getTableOutput(info,
                                                  getTable(row1.getColumnsNameList(), row1.getPksNameList(),
                                                           new ArrayList<Row>())))
            .isEqualTo(htmlTextFromClassPathOf("test_table_output2.html"));
  }

  /**
   * This method tests the {@code getRequestOutput} output method.
   */
  @Test
  public void test_request_output() throws Exception {
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

    assertThat(HtmlOutput.INSTANCE.getRequestOutput(info,
                                                    getRequest(row1.getColumnsNameList(), row1.getPksNameList(),
                                                               Arrays.asList(row1, row2, row3))))
            .isEqualTo(htmlTextFromClassPathOf("test_request_output1.html"));

    assertThat(HtmlOutput.INSTANCE.getRequestOutput(info,
                                                    getRequest(row1.getColumnsNameList(), row1.getPksNameList(),
                                                               new ArrayList<Row>())))
            .isEqualTo(htmlTextFromClassPathOf("test_request_output2.html"));
  }

  /**
   * This method tests the {@code getChangesOutput} output method.
   */
  @Test
  public void test_changes_output() throws Exception {
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

    assertThat(HtmlOutput.INSTANCE.getChangesOutput(info,
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
            .isEqualTo(htmlTextFromClassPathOf("test_changes_output1.html"));

    assertThat(HtmlOutput.INSTANCE.getChangesOutput(info,
                                                    getChanges(new ArrayList<Change>())))
            .isEqualTo(htmlTextFromClassPathOf("test_changes_output2.html"));
  }

  /**
   * This method tests the {@code getChangeOutput} output method.
   */
  @Test
  public void test_change_output() throws Exception {
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

    assertThat(HtmlOutput.INSTANCE.getChangeOutput(info, getTableCreationChange("table", row2)))
            .as("output1")
            .isEqualTo(htmlTextFromClassPathOf("test_change_output1.html"));
    assertThat(HtmlOutput.INSTANCE.getChangeOutput(info, getTableModificationChange("table", row1, row2)))
            .as("output2")
            .isEqualTo(htmlTextFromClassPathOf("test_change_output2.html"));
    assertThat(HtmlOutput.INSTANCE.getChangeOutput(info, getTableDeletionChange("table", row1)))
            .as("output3")
            .isEqualTo(htmlTextFromClassPathOf("test_change_output3.html"));
    assertThat(HtmlOutput.INSTANCE.getChangeOutput(info,
                                                   getChange(DataType.REQUEST, "select * from table",
                                                             ChangeType.MODIFICATION, row1, row2)))
            .as("output4")
            .isEqualTo(htmlTextFromClassPathOf("test_change_output4.html"));
    assertThat(HtmlOutput.INSTANCE.getChangeOutput(info,
                                                   getChange(DataType.REQUEST,
                                                             "select id, name, firstname, birth, actor_imdb from actor",
                                                             ChangeType.MODIFICATION, row1, row2)))
            .as("output5")
            .isEqualTo(htmlTextFromClassPathOf("test_change_output5.html"));
  }

  /**
   * This method tests the {@code getRowOutput} output method.
   */
  @Test
  public void test_row_output() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    assertThat(HtmlOutput.INSTANCE.getRowOutput(info, getRow(Arrays.asList("column1", "column5"),
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
            .isEqualTo(htmlTextFromClassPathOf("test_row_output.html"));
    assertThat(HtmlOutput.INSTANCE.getRowOutput(info, null))
            .isEqualTo("<html><head><title>description</title></head><body>"
                       + "<h1>description</h1>Row does not exist</body></html>");
  }

  /**
   * This method tests the {@code getColumnOutput} output method.
   */
  @Test
  public void test_column_output() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    assertThat(HtmlOutput.INSTANCE.getColumnOutput(info, getColumn("column", Arrays.asList(getValue(
            null, null), getValue(null, null)))))
            .isEqualTo(htmlTextFromClassPathOf("test_column_output1.html"));
    assertThat(HtmlOutput.INSTANCE.getColumnOutput(info, getColumn("column", Arrays.asList(getValue(
            null, true), getValue(null, false)))))
            .isEqualTo(htmlTextFromClassPathOf("test_column_output2.html"));
    assertThat(HtmlOutput.INSTANCE.getColumnOutput(info,
                                                   getColumn("column",
                                                             Arrays.asList(getValue(null, new byte[] { 0, 1 }),
                                                                           getValue(null, null)))))
            .isEqualTo(htmlTextFromClassPathOf("test_column_output3.html"));
    assertThat(HtmlOutput.INSTANCE.getColumnOutput(info,
                                                   getColumn("column", Arrays.asList(getValue(null, null),
                                                                                     getValue(null, Timestamp.valueOf(
                                                                                             "2007-12-23 09:01:00")),
                                                                                     getValue(null, Timestamp.valueOf(
                                                                                             "2002-07-25 03:30:05"))))))
            .isEqualTo(htmlTextFromClassPathOf("test_column_output4.html"));
    assertThat(HtmlOutput.INSTANCE.getColumnOutput(info,
                                                   getColumn("column",
                                                             Arrays.asList(getValue(null, Date.valueOf("2007-12-23"))))))
            .isEqualTo(htmlTextFromClassPathOf("test_column_output5.html"));
    assertThat(HtmlOutput.INSTANCE.getColumnOutput(info,
                                                   getColumn("column", Arrays.asList(getValue(null, 8),
                                                                                     getValue(null, 1000000000L)))))
            .isEqualTo(htmlTextFromClassPathOf("test_column_output6.html"));
    assertThat(HtmlOutput.INSTANCE.getColumnOutput(info,
                                                   getColumn("column", Arrays.asList(getValue(null, Locale.FRENCH),
                                                                                     getValue(null, Locale.ENGLISH)))))
            .isEqualTo(htmlTextFromClassPathOf("test_column_output7.html"));
    assertThat(HtmlOutput.INSTANCE.getColumnOutput(info,
                                                   getColumn("column", Arrays.asList(getValue(null, "test"),
                                                                                     getValue(null, "test2")))))
            .isEqualTo(htmlTextFromClassPathOf("test_column_output8.html"));
    assertThat(HtmlOutput.INSTANCE.getColumnOutput(info,
                                                   getColumn("column",
                                                             Arrays.asList(getValue(null, Time.valueOf("09:01:00")),
                                                                           getValue(null, Time.valueOf("03:30:05"))))))
            .isEqualTo(htmlTextFromClassPathOf("test_column_output9.html"));
    assertThat(HtmlOutput.INSTANCE
                       .getColumnOutput(info, getColumn("column",
                                                        Arrays.asList(getValue(null, UUID.fromString(
                                                                              "30B443AE-C0C9-4790-9BEC-CE1380808435")),
                                                                      getValue(null, UUID.fromString(
                                                                              "0E2A1269-EFF0-4233-B87B-B53E8B6F164D"))))))
            .isEqualTo(htmlTextFromClassPathOf("test_column_output10.html"));
    assertThat(HtmlOutput.INSTANCE
                       .getColumnOutput(info, getColumn("column", new ArrayList<Value>())))
            .isEqualTo(htmlTextFromClassPathOf("test_column_output11.html"));
  }

  /**
   * This method tests the {@code getChangeColumnOutput} output method.
   */
  @Test
  public void test_change_column_output() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");

    assertThat(HtmlOutput.INSTANCE.getChangeColumnOutput(info, "column2", getValue(null, true), getValue(null, false)))
            .isEqualTo(htmlTextFromClassPathOf("test_change_column_output1.html"));
    assertThat(HtmlOutput.INSTANCE.getChangeColumnOutput(info, "column1", getValue(null, null),
                                                         getValue(null, null)))
            .isEqualTo(htmlTextFromClassPathOf("test_change_column_output2.html"));
    assertThat(HtmlOutput.INSTANCE.getChangeColumnOutput(info, "column", getValue(null, UUID.fromString(
                                                                 "30B443AE-C0C9-4790-9BEC-CE1380808435")),
                                                         getValue(null, UUID.fromString(
                                                                 "0E2A1269-EFF0-4233-B87B-B53E8B6F164D"))))
            .isEqualTo(htmlTextFromClassPathOf("test_change_column_output3.html"));
  }

  /**
   * This method tests the {@code getValueOutput} output method.
   */
  @Test
  public void test_value_output() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    assertThat(HtmlOutput.INSTANCE.getValueOutput(info, getValue("column", null)))
            .isEqualTo(htmlTextFromClassPathOf("test_value_output1.html"));
    assertThat(HtmlOutput.INSTANCE.getValueOutput(info, getValue("column", true)))
            .isEqualTo(htmlTextFromClassPathOf("test_value_output2.html"));
    assertThat(HtmlOutput.INSTANCE.getValueOutput(info, getValue("column", new byte[] { 0, 1 })))
            .isEqualTo(htmlTextFromClassPathOf("test_value_output3.html"));
    assertThat(HtmlOutput.INSTANCE.getValueOutput(info, getValue("column", Timestamp.valueOf("2007-12-23 09:01:00"))))
            .isEqualTo(htmlTextFromClassPathOf("test_value_output4.html"));
    assertThat(HtmlOutput.INSTANCE.getValueOutput(info, getValue("column", Date.valueOf("2007-12-23"))))
            .isEqualTo(htmlTextFromClassPathOf("test_value_output5.html"));
    assertThat(HtmlOutput.INSTANCE.getValueOutput(info, getValue("column", 8)))
            .isEqualTo(htmlTextFromClassPathOf("test_value_output6.html"));
    assertThat(HtmlOutput.INSTANCE.getValueOutput(info, getValue("column", Locale.FRENCH)))
            .isEqualTo(htmlTextFromClassPathOf("test_value_output7.html"));
    assertThat(HtmlOutput.INSTANCE.getValueOutput(info, getValue("column", "test")))
            .isEqualTo(htmlTextFromClassPathOf("test_value_output8.html"));
    assertThat(HtmlOutput.INSTANCE.getValueOutput(info, getValue("column", Time.valueOf("09:01:00"))))
            .isEqualTo(htmlTextFromClassPathOf("test_value_output9.html"));
    assertThat(HtmlOutput.INSTANCE.getValueOutput(info, getValue("column", UUID.fromString(
            "30B443AE-C0C9-4790-9BEC-CE1380808435"))))
            .isEqualTo(htmlTextFromClassPathOf("test_value_output10.html"));
  }
}
