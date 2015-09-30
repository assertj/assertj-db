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

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Locale;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test on the utility class {@code PlainDisplay} : the private constructor.
 *
 * @author Régis Pouiller
 */
public class PlainRepresentation_Test extends AbstractTest {

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

    assertThat(PlainRepresentation.INSTANCE.getTableRepresentation(info,
            getTable(row1.getColumnsNameList(), row1.getPksNameList(),
                     Arrays.asList(row1, row2, row3))))
            .isEqualTo(String.format(
                    "[description]%n"
                    + "|-----------|---------------------|------------------|-----------|-----------|-------------------------------|------------|-----------|-------------------------------------------|-----------|--------------------|--------------------------------------|%n"
                    + "|           |                     | *                |           |           |                               | *          |           |                                           |           |                    |                                      |%n"
                    + "|           | PRIMARY             | column1          | column2   | column3   | column4                       | column5    | column6   | column7                                   | column8   | column9            | column10                             |%n"
                    + "|           | KEY                 | (NOT_IDENTIFIED) | (BOOLEAN) | (BYTES)   | (DATE_TIME)                   | (DATE)     | (NUMBER)  | (NOT_IDENTIFIED : class java.util.Locale) | (TEXT)    | (TIME)             | (UUID)                               |%n"
                    + "|           |                     | Index : 0        | Index : 1 | Index : 2 | Index : 3                     | Index : 4  | Index : 5 | Index : 6                                 | Index : 7 | Index : 8          | Index : 9                            |%n"
                    + "|-----------|---------------------|------------------|-----------|-----------|-------------------------------|------------|-----------|-------------------------------------------|-----------|--------------------|--------------------------------------|%n"
                    + "| Index : 0 | null, 2007-12-23    | null             | true      | ...       | 2007-12-23T09:01:00.000000000 | 2007-12-23 | 8         | fr                                        | test      | 09:01:00.000000000 | 30b443ae-c0c9-4790-9bec-ce1380808435 |%n"
                    + "| Index : 1 | null, 2002-07-25    | null             | false     | ...       | 2002-07-25T03:30:05.000000000 | 2002-07-25 | 9         | en                                        | test2     | 03:30:05.000000000 | 0e2a1269-eff0-4233-b87b-b53e8b6f164d |%n"
                    + "| Index : 2 | null, 2002-07-25, 9 | null             | false     | ...       | 2002-07-25T03:30:05.000000000 | 2002-07-25 | 9         | en                                        | test2     | 03:30:05.000000000 | 0e2a1269-eff0-4233-b87b-b53e8b6f164d |%n"
                    + "|-----------|---------------------|------------------|-----------|-----------|-------------------------------|------------|-----------|-------------------------------------------|-----------|--------------------|--------------------------------------|%n"));
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

    assertThat(PlainRepresentation.INSTANCE.getRequestRepresentation(info,
            getRequest(row1.getColumnsNameList(), row1.getPksNameList(), Arrays.asList(row1, row2, row3))))
            .isEqualTo(String.format(
                    "[description]%n"
                    + "|-----------|---------------------|------------------|-----------|-----------|-------------------------------|------------|-----------|-------------------------------------------|-----------|--------------------|--------------------------------------|%n"
                    + "|           |                     | *                |           |           |                               | *          |           |                                           |           |                    |                                      |%n"
                    + "|           | PRIMARY             | column1          | column2   | column3   | column4                       | column5    | column6   | column7                                   | column8   | column9            | column10                             |%n"
                    + "|           | KEY                 | (NOT_IDENTIFIED) | (BOOLEAN) | (BYTES)   | (DATE_TIME)                   | (DATE)     | (NUMBER)  | (NOT_IDENTIFIED : class java.util.Locale) | (TEXT)    | (TIME)             | (UUID)                               |%n"
                    + "|           |                     | Index : 0        | Index : 1 | Index : 2 | Index : 3                     | Index : 4  | Index : 5 | Index : 6                                 | Index : 7 | Index : 8          | Index : 9                            |%n"
                    + "|-----------|---------------------|------------------|-----------|-----------|-------------------------------|------------|-----------|-------------------------------------------|-----------|--------------------|--------------------------------------|%n"
                    + "| Index : 0 | null, 2007-12-23    | null             | true      | ...       | 2007-12-23T09:01:00.000000000 | 2007-12-23 | 8         | fr                                        | test      | 09:01:00.000000000 | 30b443ae-c0c9-4790-9bec-ce1380808435 |%n"
                    + "| Index : 1 | null, 2002-07-25    | null             | false     | ...       | 2002-07-25T03:30:05.000000000 | 2002-07-25 | 9         | en                                        | test2     | 03:30:05.000000000 | 0e2a1269-eff0-4233-b87b-b53e8b6f164d |%n"
                    + "| Index : 2 | null, 2002-07-25, 9 | null             | false     | ...       | 2002-07-25T03:30:05.000000000 | 2002-07-25 | 9         | en                                        | test2     | 03:30:05.000000000 | 0e2a1269-eff0-4233-b87b-b53e8b6f164d |%n"
                    + "|-----------|---------------------|------------------|-----------|-----------|-------------------------------|------------|-----------|-------------------------------------------|-----------|--------------------|--------------------------------------|%n"));
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

    assertThat(PlainRepresentation.INSTANCE.getChangesRepresentation(info,
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
            .isEqualTo(String.format(
                    "[description]%n"
                    + "|-----------|--------------|-----------------------------------|---------------------|----------------|------------------|-----------|-----------|-------------------------------|------------|-----------|-------------------------------------------|-----------|--------------------|--------------------------------------|%n"
                    + "|           |              |                                   |                     |                | *                |           |           |                               | *          | *         |                                           |           |                    |                                      |%n"
                    + "|           | TYPE         | TABLE                             | PRIMARY             |                | column1          | column2   | column3   | column4                       | column5    | column6   | column7                                   | column8   | column9            | column10                             |%n"
                    + "|           |              |                                   | KEY                 |                | (NOT_IDENTIFIED) | (BOOLEAN) | (BYTES)   | (DATE_TIME)                   | (DATE)     | (NUMBER)  | (NOT_IDENTIFIED : class java.util.Locale) | (TEXT)    | (TIME)             | (UUID)                               |%n"
                    + "|           |              |                                   |                     |                | Index : 0        | Index : 1 | Index : 2 | Index : 3                     | Index : 4  | Index : 5 | Index : 6                                 | Index : 7 | Index : 8          | Index : 9                            |%n"
                    + "|-----------|--------------|-----------------------------------|---------------------|----------------|------------------|-----------|-----------|-------------------------------|------------|-----------|-------------------------------------------|-----------|--------------------|--------------------------------------|%n"
                    + "|           |              |                                   |                     | At start point |                  |           |           |                               |            |           |                                           |           |                    |                                      |%n"
                    + "| Index : 0 | CREATION     | table                             | null, 2002-07-25, 9 |----------------|------------------|-----------|-----------|-------------------------------|------------|-----------|-------------------------------------------|-----------|--------------------|--------------------------------------|%n"
                    + "|           |              |                                   |                     | At end point   | null             | false     | ...       | 2002-07-25T03:30:05.000000000 | 2002-07-25 | 9         | en                                        | test2     | 03:30:05.000000000 | 0e2a1269-eff0-4233-b87b-b53e8b6f164d |%n"
                    + "|-----------|--------------|-----------------------------------|---------------------|----------------|------------------|-----------|-----------|-------------------------------|------------|-----------|-------------------------------------------|-----------|--------------------|--------------------------------------|%n"
                    + "|-----------|--------------|-----------------------------------|---------------------|----------------|------------------|-----------|-----------|-------------------------------|------------|-----------|-------------------------------------------|-----------|--------------------|--------------------------------------|%n"
                    + "|           |              |                                   |                     |                | *                |           |           |                               | *          |           |                                           |           |                    |                                      |%n"
                    + "|           | TYPE         | REQUEST                           | PRIMARY             |                | column1          | column2   | column3   | column4                       | column5    | column6   | column7                                   | column8   | column9            | column10                             |%n"
                    + "|           |              |                                   | KEY                 |                | (NOT_IDENTIFIED) | (BOOLEAN) | (BYTES)   | (DATE_TIME)                   | (DATE)     | (NUMBER)  | (NOT_IDENTIFIED : class java.util.Locale) | (TEXT)    | (TIME)             | (UUID)                               |%n"
                    + "|           |              |                                   |                     |                | Index : 0        | Index : 1 | Index : 2 | Index : 3                     | Index : 4  | Index : 5 | Index : 6                                 | Index : 7 | Index : 8          | Index : 9                            |%n"
                    + "|-----------|--------------|-----------------------------------|---------------------|----------------|------------------|-----------|-----------|-------------------------------|------------|-----------|-------------------------------------------|-----------|--------------------|--------------------------------------|%n"
                    + "|           |              |                                   |                     | At start point | null             | true      | ...       | 2007-12-23T09:01:00.000000000 | 2007-12-23 | 8         | fr                                        | test      | 09:01:00.000000000 | 30b443ae-c0c9-4790-9bec-ce1380808435 |%n"
                    + "| Index : 1 | MODIFICATION | select * from table               | null, 2007-12-23    |----------------|------------------|-----------|-----------|-------------------------------|------------|-----------|-------------------------------------------|-----------|--------------------|--------------------------------------|%n"
                    + "|           |              |                                   |                     | At end point   | null             | false     | ...       | 2002-07-25T03:30:05.000000000 | 2002-07-25 | 9         | en                                        | test2     | 03:30:05.000000000 | 0e2a1269-eff0-4233-b87b-b53e8b6f164d |%n"
                    + "|-----------|--------------|-----------------------------------|---------------------|----------------|------------------|-----------|-----------|-------------------------------|------------|-----------|-------------------------------------------|-----------|--------------------|--------------------------------------|%n"
                    + "|-----------|--------------|-----------------------------------|---------------------|----------------|------------------|-----------|-----------|-------------------------------|------------|-----------|-------------------------------------------|-----------|--------------------|--------------------------------------|%n"
                    + "|           |              |                                   |                     |                | *                |           |           |                               | *          |           |                                           |           |                    |                                      |%n"
                    + "|           | TYPE         | REQUEST                           | PRIMARY             |                | column1          | column2   | column3   | column4                       | column5    | column6   | column7                                   | column8   | column9            | column10                             |%n"
                    + "|           |              |                                   | KEY                 |                | (NOT_IDENTIFIED) | (BOOLEAN) | (BYTES)   | (DATE_TIME)                   | (DATE)     | (NUMBER)  | (NOT_IDENTIFIED : class java.util.Locale) | (TEXT)    | (TIME)             | (UUID)                               |%n"
                    + "|           |              |                                   |                     |                | Index : 0        | Index : 1 | Index : 2 | Index : 3                     | Index : 4  | Index : 5 | Index : 6                                 | Index : 7 | Index : 8          | Index : 9                            |%n"
                    + "|-----------|--------------|-----------------------------------|---------------------|----------------|------------------|-----------|-----------|-------------------------------|------------|-----------|-------------------------------------------|-----------|--------------------|--------------------------------------|%n"
                    + "|           |              |                                   |                     | At start point | null             | true      | ...       | 2007-12-23T09:01:00.000000000 | 2007-12-23 | 8         | fr                                        | test      | 09:01:00.000000000 | 30b443ae-c0c9-4790-9bec-ce1380808435 |%n"
                    + "| Index : 2 | MODIFICATION | select id, name, firstname, bi... | null, 2007-12-23    |----------------|------------------|-----------|-----------|-------------------------------|------------|-----------|-------------------------------------------|-----------|--------------------|--------------------------------------|%n"
                    + "|           |              |                                   |                     | At end point   | null             | false     | ...       | 2002-07-25T03:30:05.000000000 | 2002-07-25 | 9         | en                                        | test2     | 03:30:05.000000000 | 0e2a1269-eff0-4233-b87b-b53e8b6f164d |%n"
                    + "|-----------|--------------|-----------------------------------|---------------------|----------------|------------------|-----------|-----------|-------------------------------|------------|-----------|-------------------------------------------|-----------|--------------------|--------------------------------------|%n"
                    + "|-----------|--------------|-----------------------------------|---------------------|----------------|------------------|-----------|-----------|-------------------------------|------------|-----------|-------------------------------------------|-----------|--------------------|--------------------------------------|%n"
                    + "|           |              |                                   |                     |                | *                |           |           |                               | *          |           |                                           |           |                    |                                      |%n"
                    + "|           | TYPE         | TABLE                             | PRIMARY             |                | column1          | column2   | column3   | column4                       | column5    | column6   | column7                                   | column8   | column9            | column10                             |%n"
                    + "|           |              |                                   | KEY                 |                | (NOT_IDENTIFIED) | (BOOLEAN) | (BYTES)   | (DATE_TIME)                   | (DATE)     | (NUMBER)  | (NOT_IDENTIFIED : class java.util.Locale) | (TEXT)    | (TIME)             | (UUID)                               |%n"
                    + "|           |              |                                   |                     |                | Index : 0        | Index : 1 | Index : 2 | Index : 3                     | Index : 4  | Index : 5 | Index : 6                                 | Index : 7 | Index : 8          | Index : 9                            |%n"
                    + "|-----------|--------------|-----------------------------------|---------------------|----------------|------------------|-----------|-----------|-------------------------------|------------|-----------|-------------------------------------------|-----------|--------------------|--------------------------------------|%n"
                    + "|           |              |                                   |                     | At start point | null             | true      | ...       | 2007-12-23T09:01:00.000000000 | 2007-12-23 | 8         | fr                                        | test      | 09:01:00.000000000 | 30b443ae-c0c9-4790-9bec-ce1380808435 |%n"
                    + "| Index : 3 | MODIFICATION | table                             | null, 2007-12-23    |----------------|------------------|-----------|-----------|-------------------------------|------------|-----------|-------------------------------------------|-----------|--------------------|--------------------------------------|%n"
                    + "|           |              |                                   |                     | At end point   | null             | false     | ...       | 2002-07-25T03:30:05.000000000 | 2002-07-25 | 9         | en                                        | test2     | 03:30:05.000000000 | 0e2a1269-eff0-4233-b87b-b53e8b6f164d |%n"
                    + "|-----------|--------------|-----------------------------------|---------------------|----------------|------------------|-----------|-----------|-------------------------------|------------|-----------|-------------------------------------------|-----------|--------------------|--------------------------------------|%n"
                    + "|-----------|--------------|-----------------------------------|---------------------|----------------|------------------|-----------|-----------|-------------------------------|------------|-----------|-------------------------------------------|-----------|--------------------|--------------------------------------|%n"
                    + "|           |              |                                   |                     |                | *                |           |           |                               | *          |           |                                           |           |                    |                                      |%n"
                    + "|           | TYPE         | TABLE                             | PRIMARY             |                | column1          | column2   | column3   | column4                       | column5    | column6   | column7                                   | column8   | column9            | column10                             |%n"
                    + "|           |              |                                   | KEY                 |                | (NOT_IDENTIFIED) | (BOOLEAN) | (BYTES)   | (DATE_TIME)                   | (DATE)     | (NUMBER)  | (NOT_IDENTIFIED : class java.util.Locale) | (TEXT)    | (TIME)             | (UUID)                               |%n"
                    + "|           |              |                                   |                     |                | Index : 0        | Index : 1 | Index : 2 | Index : 3                     | Index : 4  | Index : 5 | Index : 6                                 | Index : 7 | Index : 8          | Index : 9                            |%n"
                    + "|-----------|--------------|-----------------------------------|---------------------|----------------|------------------|-----------|-----------|-------------------------------|------------|-----------|-------------------------------------------|-----------|--------------------|--------------------------------------|%n"
                    + "|           |              |                                   |                     | At start point | null             | true      | ...       | 2007-12-23T09:01:00.000000000 | 2007-12-23 | 8         | fr                                        | test      | 09:01:00.000000000 | 30b443ae-c0c9-4790-9bec-ce1380808435 |%n"
                    + "| Index : 4 | DELETION     | table                             | null, 2007-12-23    |----------------|------------------|-----------|-----------|-------------------------------|------------|-----------|-------------------------------------------|-----------|--------------------|--------------------------------------|%n"
                    + "|           |              |                                   |                     | At end point   |                  |           |           |                               |            |           |                                           |           |                    |                                      |%n"
                    + "|-----------|--------------|-----------------------------------|---------------------|----------------|------------------|-----------|-----------|-------------------------------|------------|-----------|-------------------------------------------|-----------|--------------------|--------------------------------------|%n"));
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

    assertThat(PlainRepresentation.INSTANCE.getChangeRepresentation(info, getTableCreationChange("table", row2)))
            .isEqualTo(String.format(
                    "[description]%n"
                    + "|----------|-------|------------------|----------------|------------------|-----------|-----------|-------------------------------|------------|-----------|-------------------------------------------|-----------|--------------------|--------------------------------------|%n"
                    + "|          |       |                  |                | *                |           |           |                               | *          |           |                                           |           |                    |                                      |%n"
                    + "| TYPE     | TABLE | PRIMARY          |                | column1          | column2   | column3   | column4                       | column5    | column6   | column7                                   | column8   | column9            | column10                             |%n"
                    + "|          |       | KEY              |                | (NOT_IDENTIFIED) | (BOOLEAN) | (BYTES)   | (DATE_TIME)                   | (DATE)     | (NUMBER)  | (NOT_IDENTIFIED : class java.util.Locale) | (TEXT)    | (TIME)             | (UUID)                               |%n"
                    + "|          |       |                  |                | Index : 0        | Index : 1 | Index : 2 | Index : 3                     | Index : 4  | Index : 5 | Index : 6                                 | Index : 7 | Index : 8          | Index : 9                            |%n"
                    + "|----------|-------|------------------|----------------|------------------|-----------|-----------|-------------------------------|------------|-----------|-------------------------------------------|-----------|--------------------|--------------------------------------|%n"
                    + "|          |       |                  | At start point |                  |           |           |                               |            |           |                                           |           |                    |                                      |%n"
                    + "| CREATION | table | null, 2002-07-25 |----------------|------------------|-----------|-----------|-------------------------------|------------|-----------|-------------------------------------------|-----------|--------------------|--------------------------------------|%n"
                    + "|          |       |                  | At end point   | null             | false     | ...       | 2002-07-25T03:30:05.000000000 | 2002-07-25 | 9         | en                                        | test2     | 03:30:05.000000000 | 0e2a1269-eff0-4233-b87b-b53e8b6f164d |%n"
                    + "|----------|-------|------------------|----------------|------------------|-----------|-----------|-------------------------------|------------|-----------|-------------------------------------------|-----------|--------------------|--------------------------------------|%n"));
    assertThat(PlainRepresentation.INSTANCE.getChangeRepresentation(info, getTableModificationChange("table", row1, row2)))
            .isEqualTo(String.format(
                    "[description]%n"
                    + "|--------------|-------|------------------|----------------|------------------|-----------|-----------|-------------------------------|------------|-----------|-------------------------------------------|-----------|--------------------|--------------------------------------|%n"
                    + "|              |       |                  |                | *                |           |           |                               | *          |           |                                           |           |                    |                                      |%n"
                    + "| TYPE         | TABLE | PRIMARY          |                | column1          | column2   | column3   | column4                       | column5    | column6   | column7                                   | column8   | column9            | column10                             |%n"
                    + "|              |       | KEY              |                | (NOT_IDENTIFIED) | (BOOLEAN) | (BYTES)   | (DATE_TIME)                   | (DATE)     | (NUMBER)  | (NOT_IDENTIFIED : class java.util.Locale) | (TEXT)    | (TIME)             | (UUID)                               |%n"
                    + "|              |       |                  |                | Index : 0        | Index : 1 | Index : 2 | Index : 3                     | Index : 4  | Index : 5 | Index : 6                                 | Index : 7 | Index : 8          | Index : 9                            |%n"
                    + "|--------------|-------|------------------|----------------|------------------|-----------|-----------|-------------------------------|------------|-----------|-------------------------------------------|-----------|--------------------|--------------------------------------|%n"
                    + "|              |       |                  | At start point | null             | true      | ...       | 2007-12-23T09:01:00.000000000 | 2007-12-23 | 8         | fr                                        | test      | 09:01:00.000000000 | 30b443ae-c0c9-4790-9bec-ce1380808435 |%n"
                    + "| MODIFICATION | table | null, 2007-12-23 |----------------|------------------|-----------|-----------|-------------------------------|------------|-----------|-------------------------------------------|-----------|--------------------|--------------------------------------|%n"
                    + "|              |       |                  | At end point   | null             | false     | ...       | 2002-07-25T03:30:05.000000000 | 2002-07-25 | 9         | en                                        | test2     | 03:30:05.000000000 | 0e2a1269-eff0-4233-b87b-b53e8b6f164d |%n"
                    + "|--------------|-------|------------------|----------------|------------------|-----------|-----------|-------------------------------|------------|-----------|-------------------------------------------|-----------|--------------------|--------------------------------------|%n"));
    assertThat(PlainRepresentation.INSTANCE.getChangeRepresentation(info, getTableDeletionChange("table", row1)))
            .isEqualTo(String.format(
                    "[description]%n"
                    + "|----------|-------|------------------|----------------|------------------|-----------|-----------|-------------------------------|------------|-----------|-------------------------------------------|-----------|--------------------|--------------------------------------|%n"
                    + "|          |       |                  |                | *                |           |           |                               | *          |           |                                           |           |                    |                                      |%n"
                    + "| TYPE     | TABLE | PRIMARY          |                | column1          | column2   | column3   | column4                       | column5    | column6   | column7                                   | column8   | column9            | column10                             |%n"
                    + "|          |       | KEY              |                | (NOT_IDENTIFIED) | (BOOLEAN) | (BYTES)   | (DATE_TIME)                   | (DATE)     | (NUMBER)  | (NOT_IDENTIFIED : class java.util.Locale) | (TEXT)    | (TIME)             | (UUID)                               |%n"
                    + "|          |       |                  |                | Index : 0        | Index : 1 | Index : 2 | Index : 3                     | Index : 4  | Index : 5 | Index : 6                                 | Index : 7 | Index : 8          | Index : 9                            |%n"
                    + "|----------|-------|------------------|----------------|------------------|-----------|-----------|-------------------------------|------------|-----------|-------------------------------------------|-----------|--------------------|--------------------------------------|%n"
                    + "|          |       |                  | At start point | null             | true      | ...       | 2007-12-23T09:01:00.000000000 | 2007-12-23 | 8         | fr                                        | test      | 09:01:00.000000000 | 30b443ae-c0c9-4790-9bec-ce1380808435 |%n"
                    + "| DELETION | table | null, 2007-12-23 |----------------|------------------|-----------|-----------|-------------------------------|------------|-----------|-------------------------------------------|-----------|--------------------|--------------------------------------|%n"
                    + "|          |       |                  | At end point   |                  |           |           |                               |            |           |                                           |           |                    |                                      |%n"
                    + "|----------|-------|------------------|----------------|------------------|-----------|-----------|-------------------------------|------------|-----------|-------------------------------------------|-----------|--------------------|--------------------------------------|%n"));
    assertThat(PlainRepresentation.INSTANCE.getChangeRepresentation(info,
            getChange(DataType.REQUEST, "select * from table", ChangeType.MODIFICATION, row1, row2)))
            .isEqualTo(String.format(
                    "[description]%n"
                    + "|--------------|---------------------|------------------|----------------|------------------|-----------|-----------|-------------------------------|------------|-----------|-------------------------------------------|-----------|--------------------|--------------------------------------|%n"
                    + "|              |                     |                  |                | *                |           |           |                               | *          |           |                                           |           |                    |                                      |%n"
                    + "| TYPE         | REQUEST             | PRIMARY          |                | column1          | column2   | column3   | column4                       | column5    | column6   | column7                                   | column8   | column9            | column10                             |%n"
                    + "|              |                     | KEY              |                | (NOT_IDENTIFIED) | (BOOLEAN) | (BYTES)   | (DATE_TIME)                   | (DATE)     | (NUMBER)  | (NOT_IDENTIFIED : class java.util.Locale) | (TEXT)    | (TIME)             | (UUID)                               |%n"
                    + "|              |                     |                  |                | Index : 0        | Index : 1 | Index : 2 | Index : 3                     | Index : 4  | Index : 5 | Index : 6                                 | Index : 7 | Index : 8          | Index : 9                            |%n"
                    + "|--------------|---------------------|------------------|----------------|------------------|-----------|-----------|-------------------------------|------------|-----------|-------------------------------------------|-----------|--------------------|--------------------------------------|%n"
                    + "|              |                     |                  | At start point | null             | true      | ...       | 2007-12-23T09:01:00.000000000 | 2007-12-23 | 8         | fr                                        | test      | 09:01:00.000000000 | 30b443ae-c0c9-4790-9bec-ce1380808435 |%n"
                    + "| MODIFICATION | select * from table | null, 2007-12-23 |----------------|------------------|-----------|-----------|-------------------------------|------------|-----------|-------------------------------------------|-----------|--------------------|--------------------------------------|%n"
                    + "|              |                     |                  | At end point   | null             | false     | ...       | 2002-07-25T03:30:05.000000000 | 2002-07-25 | 9         | en                                        | test2     | 03:30:05.000000000 | 0e2a1269-eff0-4233-b87b-b53e8b6f164d |%n"
                    + "|--------------|---------------------|------------------|----------------|------------------|-----------|-----------|-------------------------------|------------|-----------|-------------------------------------------|-----------|--------------------|--------------------------------------|%n"));
    assertThat(PlainRepresentation.INSTANCE.getChangeRepresentation(info,
            getChange(DataType.REQUEST, "select id, name, firstname, birth, actor_imdb from actor",
                      ChangeType.MODIFICATION, row1, row2)))
            .isEqualTo(String.format(
                    "[description]%n"
                    + "|--------------|-----------------------------------|------------------|----------------|------------------|-----------|-----------|-------------------------------|------------|-----------|-------------------------------------------|-----------|--------------------|--------------------------------------|%n"
                    + "|              |                                   |                  |                | *                |           |           |                               | *          |           |                                           |           |                    |                                      |%n"
                    + "| TYPE         | REQUEST                           | PRIMARY          |                | column1          | column2   | column3   | column4                       | column5    | column6   | column7                                   | column8   | column9            | column10                             |%n"
                    + "|              |                                   | KEY              |                | (NOT_IDENTIFIED) | (BOOLEAN) | (BYTES)   | (DATE_TIME)                   | (DATE)     | (NUMBER)  | (NOT_IDENTIFIED : class java.util.Locale) | (TEXT)    | (TIME)             | (UUID)                               |%n"
                    + "|              |                                   |                  |                | Index : 0        | Index : 1 | Index : 2 | Index : 3                     | Index : 4  | Index : 5 | Index : 6                                 | Index : 7 | Index : 8          | Index : 9                            |%n"
                    + "|--------------|-----------------------------------|------------------|----------------|------------------|-----------|-----------|-------------------------------|------------|-----------|-------------------------------------------|-----------|--------------------|--------------------------------------|%n"
                    + "|              |                                   |                  | At start point | null             | true      | ...       | 2007-12-23T09:01:00.000000000 | 2007-12-23 | 8         | fr                                        | test      | 09:01:00.000000000 | 30b443ae-c0c9-4790-9bec-ce1380808435 |%n"
                    + "| MODIFICATION | select id, name, firstname, bi... | null, 2007-12-23 |----------------|------------------|-----------|-----------|-------------------------------|------------|-----------|-------------------------------------------|-----------|--------------------|--------------------------------------|%n"
                    + "|              |                                   |                  | At end point   | null             | false     | ...       | 2002-07-25T03:30:05.000000000 | 2002-07-25 | 9         | en                                        | test2     | 03:30:05.000000000 | 0e2a1269-eff0-4233-b87b-b53e8b6f164d |%n"
                    + "|--------------|-----------------------------------|------------------|----------------|------------------|-----------|-----------|-------------------------------|------------|-----------|-------------------------------------------|-----------|--------------------|--------------------------------------|%n"));
  }

  /**
   * This method tests the {@code getRowRepresentation} display method.
   */
  @Test
  public void test_row_representation() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    assertThat(PlainRepresentation.INSTANCE.getRowRepresentation(info, getRow(Arrays.asList("column1", "column5"),
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
            .isEqualTo(String.format(
                    "[description]%n"
                    + "|------------------|-----------|-----------|-------------------------------|------------|-----------|-------------------------------------------|-----------|--------------------|--------------------------------------|%n"
                    + "| *                |           |           |                               | *          |           |                                           |           |                    |                                      |%n"
                    + "| column1          | column2   | column3   | column4                       | column5    | column6   | column7                                   | column8   | column9            | column10                             |%n"
                    + "| (NOT_IDENTIFIED) | (BOOLEAN) | (BYTES)   | (DATE_TIME)                   | (DATE)     | (NUMBER)  | (NOT_IDENTIFIED : class java.util.Locale) | (TEXT)    | (TIME)             | (UUID)                               |%n"
                    + "| Index : 0        | Index : 1 | Index : 2 | Index : 3                     | Index : 4  | Index : 5 | Index : 6                                 | Index : 7 | Index : 8          | Index : 9                            |%n"
                    + "|------------------|-----------|-----------|-------------------------------|------------|-----------|-------------------------------------------|-----------|--------------------|--------------------------------------|%n"
                    + "| null             | true      | ...       | 2007-12-23T09:01:00.000000000 | 2007-12-23 | 8         | fr                                        | test      | 09:01:00.000000000 | 30b443ae-c0c9-4790-9bec-ce1380808435 |%n"
                    + "|------------------|-----------|-----------|-------------------------------|------------|-----------|-------------------------------------------|-----------|--------------------|--------------------------------------|%n"));
  }

  /**
   * This method tests the {@code getColumnRepresentation} display method.
   */
  @Test
  public void test_column_representation() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    assertThat(PlainRepresentation.INSTANCE.getColumnRepresentation(info, getColumn("column", Arrays.asList(getValue(
            null, null), getValue(null, null)))))
            .isEqualTo(String.format("[description]%n"
                                     + "|-----------|------------------|%n"
                                     + "|           | column           |%n"
                                     + "|           | (NOT_IDENTIFIED) |%n"
                                     + "|-----------|------------------|%n"
                                     + "| Index : 0 | null             |%n"
                                     + "| Index : 1 | null             |%n"
                                     + "|-----------|------------------|%n"));
    assertThat(PlainRepresentation.INSTANCE.getColumnRepresentation(info, getColumn("column", Arrays.asList(getValue(
            null, true), getValue(null, false)))))
                                    .isEqualTo(String.format("[description]%n"
                                                             + "|-----------|-----------|%n"
                                                             + "|           | column    |%n"
                                                             + "|           | (BOOLEAN) |%n"
                                                             + "|-----------|-----------|%n"
                                                             + "| Index : 0 | true      |%n"
                                                             + "| Index : 1 | false     |%n"
                                                             + "|-----------|-----------|%n"));
    assertThat(PlainRepresentation.INSTANCE.getColumnRepresentation(info,
            getColumn("column", Arrays.asList(getValue(null, new byte[] { 0, 1 }), getValue(null, null)))))
            .isEqualTo(String.format("[description]%n"
                                     + "|-----------|---------|%n"
                                     + "|           | column  |%n"
                                     + "|           | (BYTES) |%n"
                                     + "|-----------|---------|%n"
                                     + "| Index : 0 | ...     |%n"
                                     + "| Index : 1 | null    |%n"
                                     + "|-----------|---------|%n"));
    assertThat(PlainRepresentation.INSTANCE.getColumnRepresentation(info,
            getColumn("column", Arrays.asList(getValue(null, null), getValue(null, Timestamp.valueOf("2007-12-23 09:01:00")),
                                              getValue(null, Timestamp.valueOf("2002-07-25 03:30:05"))))))
            .isEqualTo(String.format("[description]%n"
                                     + "|-----------|-------------------------------|%n"
                                     + "|           | column                        |%n"
                                     + "|           | (DATE_TIME)                   |%n"
                                     + "|-----------|-------------------------------|%n"
                                     + "| Index : 0 | null                          |%n"
                                     + "| Index : 1 | 2007-12-23T09:01:00.000000000 |%n"
                                     + "| Index : 2 | 2002-07-25T03:30:05.000000000 |%n"
                                     + "|-----------|-------------------------------|%n"));
    assertThat(PlainRepresentation.INSTANCE.getColumnRepresentation(info,
            getColumn("column", Arrays.asList(getValue(null, Date.valueOf("2007-12-23"))))))
            .isEqualTo(String.format("[description]%n"
                                     + "|-----------|------------|%n"
                                     + "|           | column     |%n"
                                     + "|           | (DATE)     |%n"
                                     + "|-----------|------------|%n"
                                     + "| Index : 0 | 2007-12-23 |%n"
                                     + "|-----------|------------|%n"));
    assertThat(PlainRepresentation.INSTANCE.getColumnRepresentation(info,
            getColumn("column", Arrays.asList(getValue(null, 8), getValue(null, 1000000000L)))))
            .isEqualTo(String.format("[description]%n"
                                     + "|-----------|------------|%n"
                                     + "|           | column     |%n"
                                     + "|           | (NUMBER)   |%n"
                                     + "|-----------|------------|%n"
                                     + "| Index : 0 | 8          |%n"
                                     + "| Index : 1 | 1000000000 |%n"
                                     + "|-----------|------------|%n"));
    assertThat(PlainRepresentation.INSTANCE.getColumnRepresentation(info,
            getColumn("column", Arrays.asList(getValue(null, Locale.FRENCH), getValue(null, Locale.ENGLISH)))))
            .isEqualTo(String.format(
                    "[description]%n"
                    + "|-----------|-------------------------------------------|%n"
                    + "|           | column                                    |%n"
                    + "|           | (NOT_IDENTIFIED : class java.util.Locale) |%n"
                    + "|-----------|-------------------------------------------|%n"
                    + "| Index : 0 | fr                                        |%n"
                    + "| Index : 1 | en                                        |%n"
                    + "|-----------|-------------------------------------------|%n"));
    assertThat(PlainRepresentation.INSTANCE.getColumnRepresentation(info,
            getColumn("column", Arrays.asList(getValue(null, "test"), getValue(null, "test2")))))
            .isEqualTo(String.format("[description]%n"
                                     + "|-----------|--------|%n"
                                     + "|           | column |%n"
                                     + "|           | (TEXT) |%n"
                                     + "|-----------|--------|%n"
                                     + "| Index : 0 | test   |%n"
                                     + "| Index : 1 | test2  |%n"
                                     + "|-----------|--------|%n"));
    assertThat(PlainRepresentation.INSTANCE.getColumnRepresentation(info,
            getColumn("column", Arrays.asList(getValue(null, Time.valueOf("09:01:00")), getValue(null, Time.valueOf("03:30:05"))))))
            .isEqualTo(String.format("[description]%n"
                                     + "|-----------|--------------------|%n"
                                     + "|           | column             |%n"
                                     + "|           | (TIME)             |%n"
                                     + "|-----------|--------------------|%n"
                                     + "| Index : 0 | 09:01:00.000000000 |%n"
                                     + "| Index : 1 | 03:30:05.000000000 |%n"
                                     + "|-----------|--------------------|%n"));
    assertThat(PlainRepresentation.INSTANCE
                       .getColumnRepresentation(info, getColumn("column",
                                                          Arrays.asList(getValue(null, UUID.fromString(
                                                                  "30B443AE-C0C9-4790-9BEC-CE1380808435")),
                                                                        getValue(null, UUID.fromString(
                                                                                "0E2A1269-EFF0-4233-B87B-B53E8B6F164D"))))))
            .isEqualTo(String.format("[description]%n"
                                     + "|-----------|--------------------------------------|%n"
                                     + "|           | column                               |%n"
                                     + "|           | (UUID)                               |%n"
                                     + "|-----------|--------------------------------------|%n"
                                     + "| Index : 0 | 30b443ae-c0c9-4790-9bec-ce1380808435 |%n"
                                     + "| Index : 1 | 0e2a1269-eff0-4233-b87b-b53e8b6f164d |%n"
                                     + "|-----------|--------------------------------------|%n"));
  }

  /**
   * This method tests the {@code getValueRepresentation} display method.
   */
  @Test
  public void test_value_representation() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    assertThat(PlainRepresentation.INSTANCE.getValueRepresentation(info, "column", getValue(null, null)))
            .isEqualTo(String.format("[description]%n"
                                     + "|------------------|%n"
                                     + "| column           |%n"
                                     + "| (NOT_IDENTIFIED) |%n"
                                     + "|------------------|%n"
                                     + "| null             |%n"
                                     + "|------------------|%n"));
    assertThat(PlainRepresentation.INSTANCE.getValueRepresentation(info, "column", getValue(null, true)))
            .isEqualTo(String.format("[description]%n"
                                     + "|-----------|%n"
                                     + "| column    |%n"
                                     + "| (BOOLEAN) |%n"
                                     + "|-----------|%n"
                                     + "| true      |%n"
                                     + "|-----------|%n"));
    assertThat(PlainRepresentation.INSTANCE.getValueRepresentation(info, "column", getValue(null, new byte[] { 0, 1 })))
            .isEqualTo(String.format("[description]%n"
                                     + "|---------|%n"
                                     + "| column  |%n"
                                     + "| (BYTES) |%n"
                                     + "|---------|%n"
                                     + "| ...     |%n"
                                     + "|---------|%n"));
    assertThat(PlainRepresentation.INSTANCE.getValueRepresentation(info, "column", getValue(null, Timestamp.valueOf("2007-12-23 09:01:00"))))
            .isEqualTo(String.format("[description]%n"
                                     + "|-------------------------------|%n"
                                     + "| column                        |%n"
                                     + "| (DATE_TIME)                   |%n"
                                     + "|-------------------------------|%n"
                                     + "| 2007-12-23T09:01:00.000000000 |%n"
                                     + "|-------------------------------|%n"));
    assertThat(PlainRepresentation.INSTANCE.getValueRepresentation(info, "column", getValue(null, Date.valueOf("2007-12-23"))))
            .isEqualTo(String.format("[description]%n"
                                     + "|------------|%n"
                                     + "| column     |%n"
                                     + "| (DATE)     |%n"
                                     + "|------------|%n"
                                     + "| 2007-12-23 |%n"
                                     + "|------------|%n"));
    assertThat(PlainRepresentation.INSTANCE.getValueRepresentation(info, "column", getValue(null, 8)))
            .isEqualTo(String.format("[description]%n"
                                     + "|----------|%n"
                                     + "| column   |%n"
                                     + "| (NUMBER) |%n"
                                     + "|----------|%n"
                                     + "| 8        |%n"
                                     + "|----------|%n"));
    assertThat(PlainRepresentation.INSTANCE.getValueRepresentation(info, "column", getValue(null, Locale.FRENCH)))
            .isEqualTo(String.format("[description]%n"
                                     + "|-------------------------------------------|%n"
                                     + "| column                                    |%n"
                                     + "| (NOT_IDENTIFIED : class java.util.Locale) |%n"
                                     + "|-------------------------------------------|%n"
                                     + "| fr                                        |%n"
                                     + "|-------------------------------------------|%n"));
    assertThat(PlainRepresentation.INSTANCE.getValueRepresentation(info, "column", getValue(null, "test")))
            .isEqualTo(String.format("[description]%n"
                                     + "|--------|%n"
                                     + "| column |%n"
                                     + "| (TEXT) |%n"
                                     + "|--------|%n"
                                     + "| test   |%n"
                                     + "|--------|%n"));
    assertThat(PlainRepresentation.INSTANCE.getValueRepresentation(info, "column", getValue(null, Time.valueOf("09:01:00"))))
            .isEqualTo(String.format("[description]%n"
                                     + "|--------------------|%n"
                                     + "| column             |%n"
                                     + "| (TIME)             |%n"
                                     + "|--------------------|%n"
                                     + "| 09:01:00.000000000 |%n"
                                     + "|--------------------|%n"));
    assertThat(PlainRepresentation.INSTANCE.getValueRepresentation(info, "column", getValue(null, UUID.fromString(
            "30B443AE-C0C9-4790-9BEC-CE1380808435"))))
            .isEqualTo(String.format("[description]%n"
                                     + "|--------------------------------------|%n"
                                     + "| column                               |%n"
                                     + "| (UUID)                               |%n"
                                     + "|--------------------------------------|%n"
                                     + "| 30b443ae-c0c9-4790-9bec-ce1380808435 |%n"
                                     + "|--------------------------------------|%n"));
  }
}
