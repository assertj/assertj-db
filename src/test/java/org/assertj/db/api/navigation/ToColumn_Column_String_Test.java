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
package org.assertj.db.api.navigation;

import org.assertj.core.api.Assertions;
import org.assertj.db.api.*;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.assertj.db.exception.AssertJDBException;
import org.assertj.db.type.Changes;
import org.assertj.db.type.Column;
import org.assertj.db.type.Table;
import org.junit.Test;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Date;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on {@link ToColumn} class :
 * {@link ToColumn#column(String)} method.
 *
 * @author Régis Pouiller
 *
 */
public class ToColumn_Column_String_Test extends AbstractTest {

  /**
   * This method tests the {@code column} navigation method.
   */
  @Test
  @NeedReload
  public void test_column_with_column_name_from_change() throws Exception {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    Field fieldIndex = ChangeAssert.class.getDeclaredField("indexNextColumn");
    fieldIndex.setAccessible(true);
    Field fieldColumnName = ChangeColumnAssert.class.getDeclaredField("columnName");
    fieldColumnName.setAccessible(true);
    Field fieldValueAtStartPoint = ChangeColumnAssert.class.getDeclaredField("valueAtStartPoint");
    fieldValueAtStartPoint.setAccessible(true);
    Field fieldValueAtEndPoint = ChangeColumnAssert.class.getDeclaredField("valueAtEndPoint");
    fieldValueAtEndPoint.setAccessible(true);

    ChangesAssert changesAssert = assertThat(changes);
    ChangeAssert changeAssert = changesAssert.change();
    Assertions.assertThat(fieldIndex.get(changeAssert)).isEqualTo(0);
    ChangeColumnAssert changeColumnAssert0 = changeAssert.column("ID");
    Assertions.assertThat(fieldIndex.get(changeAssert)).isEqualTo(1);
    ChangeColumnAssert changeColumnAssert1 = changeAssert.column("NAME");
    Assertions.assertThat(fieldIndex.get(changeAssert)).isEqualTo(2);
    ChangeColumnAssert changeColumnAssert2 = changeAssert.column("FIRSTNAME");
    Assertions.assertThat(fieldIndex.get(changeAssert)).isEqualTo(3);
    ChangeColumnAssert changeColumnAssert3 = changeAssert.column("BIRTH");
    Assertions.assertThat(fieldIndex.get(changeAssert)).isEqualTo(4);
    try {
      changeAssert.column("TEST");
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Column <TEST> does not exist");
    }
    try {
      changeAssert.column(null);
      fail("An exception must be raised");
    } catch (NullPointerException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Column name must be not null");
    }

    ChangesAssert changesAssertBis = assertThat(changes);
    ChangeAssert changeAssertBis = changesAssertBis.change();
    Assertions.assertThat(fieldIndex.get(changeAssertBis)).isEqualTo(0);
    ChangeColumnAssert changeColumnAssertBis0 = changeAssertBis.column("ID");
    Assertions.assertThat(fieldIndex.get(changeAssertBis)).isEqualTo(1);
    ChangeColumnAssert changeColumnAssertBis1 = changeColumnAssertBis0.column("NAME");
    Assertions.assertThat(fieldIndex.get(changeAssertBis)).isEqualTo(2);
    ChangeColumnAssert changeColumnAssertBis2 = changeColumnAssertBis1.column("FIRSTNAME");
    Assertions.assertThat(fieldIndex.get(changeAssertBis)).isEqualTo(3);
    ChangeColumnAssert changeColumnAssertBis3 = changeColumnAssertBis2.column("BIRTH");
    Assertions.assertThat(fieldIndex.get(changeAssertBis)).isEqualTo(4);
    try {
      changeColumnAssertBis3.column("TEST");
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Column <TEST> does not exist");
    }
    try {
      changeColumnAssertBis3.column(null);
      fail("An exception must be raised");
    } catch (NullPointerException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Column name must be not null");
    }

    Assertions.assertThat(fieldColumnName.get(changeColumnAssert0)).isEqualTo(fieldColumnName.get(changeColumnAssertBis0)).isEqualTo(
            "ID");
    Assertions.assertThat(fieldColumnName.get(changeColumnAssert1)).isEqualTo(fieldColumnName.get(changeColumnAssertBis1)).isEqualTo(
            "NAME");
    Assertions.assertThat(fieldColumnName.get(changeColumnAssert2)).isEqualTo(fieldColumnName.get(changeColumnAssertBis2)).isEqualTo(
            "FIRSTNAME");
    Assertions.assertThat(fieldColumnName.get(changeColumnAssert3)).isEqualTo(fieldColumnName.get(changeColumnAssertBis3)).isEqualTo(
            "BIRTH");

    Assertions.assertThat(fieldValueAtStartPoint.get(changeColumnAssert0)).isNull();
    Assertions.assertThat(fieldValueAtStartPoint.get(changeColumnAssert1)).isNull();
    Assertions.assertThat(fieldValueAtStartPoint.get(changeColumnAssert2)).isNull();
    Assertions.assertThat(fieldValueAtStartPoint.get(changeColumnAssert3)).isNull();
    Assertions.assertThat(fieldValueAtStartPoint.get(changeColumnAssertBis0)).isNull();
    Assertions.assertThat(fieldValueAtStartPoint.get(changeColumnAssertBis1)).isNull();
    Assertions.assertThat(fieldValueAtStartPoint.get(changeColumnAssertBis2)).isNull();
    Assertions.assertThat(fieldValueAtStartPoint.get(changeColumnAssertBis3)).isNull();

    Assertions.assertThat(fieldValueAtEndPoint.get(changeColumnAssert0)).isEqualTo(fieldValueAtEndPoint.get(changeColumnAssertBis0)).isEqualTo(new BigDecimal("4"));
    Assertions.assertThat(fieldValueAtEndPoint.get(changeColumnAssert1)).isEqualTo(fieldValueAtEndPoint.get(changeColumnAssertBis1)).isEqualTo("Murray");
    Assertions.assertThat(fieldValueAtEndPoint.get(changeColumnAssert2)).isEqualTo(fieldValueAtEndPoint.get(changeColumnAssertBis2)).isEqualTo("Bill");
    Assertions.assertThat(fieldValueAtEndPoint.get(changeColumnAssert3)).isEqualTo(fieldValueAtEndPoint.get(changeColumnAssertBis3)).isEqualTo(
            Date.valueOf("1950-09-21"));
  }

  /**
   * This method tests the {@code column} navigation method.
   */
  @Test
  public void test_column_with_column_name_from_table() throws Exception {
    Field fieldIndex = AbstractDbAssert.class.getDeclaredField("indexNextColumn");
    fieldIndex.setAccessible(true);
    Field fieldColumn = AbstractColumnAssert.class.getDeclaredField("column");
    fieldColumn.setAccessible(true);

    Table table = new Table(source, "actor");
    TableAssert tableAssert = assertThat(table);
    Assertions.assertThat(fieldIndex.get(tableAssert)).isEqualTo(0);
    TableColumnAssert tableColumnAssert0 = tableAssert.column("ID");
    Assertions.assertThat(fieldIndex.get(tableAssert)).isEqualTo(1);
    TableColumnAssert tableColumnAssert1 = tableAssert.column("NAME");
    Assertions.assertThat(fieldIndex.get(tableAssert)).isEqualTo(2);
    TableColumnAssert tableColumnAssert2 = tableAssert.column("FIRSTNAME");
    Assertions.assertThat(fieldIndex.get(tableAssert)).isEqualTo(3);
    TableColumnAssert tableColumnAssert3 = tableAssert.column("BIRTH");
    Assertions.assertThat(fieldIndex.get(tableAssert)).isEqualTo(4);
    try {
      tableAssert.column("TEST");
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Column <TEST> does not exist");
    }
    try {
      tableAssert.column(null);
      fail("An exception must be raised");
    } catch (NullPointerException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Column name must be not null");
    }

    TableAssert tableAssertBis = assertThat(table);
    Assertions.assertThat(fieldIndex.get(tableAssertBis)).isEqualTo(0);
    TableColumnAssert tableColumnAssertBis0 = tableAssertBis.column("ID");
    Assertions.assertThat(fieldIndex.get(tableAssertBis)).isEqualTo(1);
    TableColumnAssert tableColumnAssertBis1 = tableColumnAssertBis0.column("NAME");
    Assertions.assertThat(fieldIndex.get(tableAssertBis)).isEqualTo(2);
    TableColumnAssert tableColumnAssertBis2 = tableColumnAssertBis1.column("FIRSTNAME");
    Assertions.assertThat(fieldIndex.get(tableAssertBis)).isEqualTo(3);
    TableColumnAssert tableColumnAssertBis3 = tableColumnAssertBis2.column("BIRTH");
    Assertions.assertThat(fieldIndex.get(tableAssertBis)).isEqualTo(4);
    try {
      tableColumnAssertBis3.column("TEST");
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Column <TEST> does not exist");
    }
    try {
      tableColumnAssertBis3.column(null);
      fail("An exception must be raised");
    } catch (NullPointerException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Column name must be not null");
    }

    Column columnId0 = (Column) fieldColumn.get(tableColumnAssert0);
    Column columnId1 = (Column) fieldColumn.get(tableColumnAssert1);
    Column columnId2 = (Column) fieldColumn.get(tableColumnAssert2);
    Column columnId3 = (Column) fieldColumn.get(tableColumnAssert3);
    Column columnIdBis0 = (Column) fieldColumn.get(tableColumnAssertBis0);
    Column columnIdBis1 = (Column) fieldColumn.get(tableColumnAssertBis1);
    Column columnIdBis2 = (Column) fieldColumn.get(tableColumnAssertBis2);
    Column columnIdBis3 = (Column) fieldColumn.get(tableColumnAssertBis3);

    Assertions.assertThat(columnId0.getName()).isEqualTo(columnIdBis0.getName()).isEqualTo("ID");
    Assertions.assertThat(columnId1.getName()).isEqualTo(columnIdBis1.getName()).isEqualTo("NAME");
    Assertions.assertThat(columnId2.getName()).isEqualTo(columnIdBis2.getName()).isEqualTo("FIRSTNAME");
    Assertions.assertThat(columnId3.getName()).isEqualTo(columnIdBis3.getName()).isEqualTo("BIRTH");

    Assertions.assertThat(columnId0.getValuesList()).isEqualTo(columnIdBis0.getValuesList()).containsExactly(
            new BigDecimal("1"), new BigDecimal("2"), new BigDecimal("3"));
    Assertions.assertThat(columnId1.getValuesList()).isEqualTo(columnIdBis1.getValuesList()).containsExactly("Weaver",
                                                                                                             "Phoenix",
                                                                                                             "Worthington");
    Assertions
            .assertThat(columnId2.getValuesList()).isEqualTo(columnIdBis2.getValuesList()).containsExactly("Sigourney",
                                                                                                           "Joaquim",
                                                                                                           "Sam");
    Assertions.assertThat(columnId3.getValuesList()).isEqualTo(columnIdBis3.getValuesList()).containsExactly(
            Date.valueOf("1949-10-08"), Date.valueOf("1974-10-28"), Date.valueOf("1976-08-02"));
  }
}
