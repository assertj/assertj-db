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
package org.assertj.db.navigation;

import org.assertj.core.api.Assertions;
import org.assertj.db.api.*;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.assertj.db.display.*;
import org.assertj.db.exception.AssertJDBException;
import org.assertj.db.type.*;
import org.junit.Test;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Date;

import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.display.Displaying.display;
import static org.junit.Assert.fail;

/**
 * Tests on {@link org.assertj.db.navigation.ToColumn} class :
 * {@link org.assertj.db.navigation.ToColumn#column(String)} method.
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
  public void test_column_with_column_name_from_change_with_assertions() throws Exception {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    Field fieldPosition = ChangeAssert.class.getDeclaredField("columnPosition");
    fieldPosition.setAccessible(true);
    Field fieldIndex = PositionWithColumnsChange.class.getDeclaredField("nextIndex");
    fieldIndex.setAccessible(true);
    Field fieldColumnName = ChangeColumnAssert.class.getDeclaredField("columnName");
    fieldColumnName.setAccessible(true);
    Field fieldValueAtStartPoint = ChangeColumnAssert.class.getDeclaredField("valueAtStartPoint");
    fieldValueAtStartPoint.setAccessible(true);
    Field fieldValueAtEndPoint = ChangeColumnAssert.class.getDeclaredField("valueAtEndPoint");
    fieldValueAtEndPoint.setAccessible(true);

    ChangesAssert changesAssert = assertThat(changes);
    ChangeAssert changeAssert = changesAssert.change();
    PositionWithColumnsChange position = (PositionWithColumnsChange) fieldPosition.get(changeAssert);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(0);
    ChangeColumnAssert changeColumnAssert0 = changeAssert.column("ID");
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(1);
    ChangeColumnAssert changeColumnAssert1 = changeAssert.column("NAME");
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(2);
    ChangeColumnAssert changeColumnAssert2 = changeAssert.column("FIRSTNAME");
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(3);
    ChangeColumnAssert changeColumnAssert3 = changeAssert.column("BIRTH");
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(4);
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
    PositionWithColumnsChange positionBis = (PositionWithColumnsChange) fieldPosition.get(changeAssertBis);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(0);
    ChangeColumnAssert changeColumnAssertBis0 = changeAssertBis.column("ID");
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(1);
    ChangeColumnAssert changeColumnAssertBis1 = changeColumnAssertBis0.column("NAME");
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(2);
    ChangeColumnAssert changeColumnAssertBis2 = changeColumnAssertBis1.column("FIRSTNAME");
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(3);
    ChangeColumnAssert changeColumnAssertBis3 = changeColumnAssertBis2.column("BIRTH");
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(4);
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

    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnAssert0)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnAssert1)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnAssert2)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnAssert3)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnAssertBis0)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnAssertBis1)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnAssertBis2)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnAssertBis3)).getValue()).isNull();

    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnAssert0)).getValue())
    .isEqualTo(((Value) fieldValueAtEndPoint.get(changeColumnAssertBis0)).getValue())
            .isEqualTo(new BigDecimal("4"));
    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnAssert1)).getValue())
              .isEqualTo(((Value) fieldValueAtEndPoint.get(changeColumnAssertBis1)).getValue())
              .isEqualTo("Murray");
    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnAssert2)).getValue())
              .isEqualTo(((Value) fieldValueAtEndPoint.get(changeColumnAssertBis2)).getValue())
              .isEqualTo("Bill");
    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnAssert3)).getValue())
              .isEqualTo(((Value) fieldValueAtEndPoint.get(changeColumnAssertBis3)).getValue())
              .isEqualTo(Date.valueOf("1950-09-21"));
  }

  /**
   * This method tests the {@code column} navigation method.
   */
  @Test
  public void test_column_with_column_name_from_table_with_assertions() throws Exception {
    Field fieldPosition = AbstractDbAssert.class.getDeclaredField("columnPosition");
    fieldPosition.setAccessible(true);
    Field fieldColumn = AbstractColumnAssert.class.getDeclaredField("column");
    fieldColumn.setAccessible(true);
    Field fieldIndex = Position.class.getDeclaredField("nextIndex");
    fieldIndex.setAccessible(true);

    Table table = new Table(source, "actor");
    TableAssert tableAssert = assertThat(table);
    Position position = (Position) fieldPosition.get(tableAssert);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(0);
    TableColumnAssert tableColumnAssert0 = tableAssert.column("ID");
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(1);
    TableColumnAssert tableColumnAssert1 = tableAssert.column("NAME");
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(2);
    TableColumnAssert tableColumnAssert2 = tableAssert.column("FIRSTNAME");
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(3);
    TableColumnAssert tableColumnAssert3 = tableAssert.column("BIRTH");
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(4);
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
    Position positionBis = (Position) fieldPosition.get(tableAssertBis);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(0);
    TableColumnAssert tableColumnAssertBis0 = tableAssertBis.column("ID");
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(1);
    TableColumnAssert tableColumnAssertBis1 = tableColumnAssertBis0.column("NAME");
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(2);
    TableColumnAssert tableColumnAssertBis2 = tableColumnAssertBis1.column("FIRSTNAME");
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(3);
    TableColumnAssert tableColumnAssertBis3 = tableColumnAssertBis2.column("BIRTH");
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(4);
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

    Assertions.assertThat(columnId0.getValuesList().get(0).getValue()).isEqualTo(columnIdBis0.getValuesList().get(0).getValue()).isEqualTo(
            new BigDecimal("1"));
    Assertions.assertThat(columnId0.getValuesList().get(1).getValue()).isEqualTo(columnIdBis0.getValuesList().get(1).getValue()).isEqualTo(
            new BigDecimal("2"));
    Assertions.assertThat(columnId0.getValuesList().get(2).getValue()).isEqualTo(columnIdBis0.getValuesList().get(2).getValue()).isEqualTo(
            new BigDecimal("3"));
    Assertions.assertThat(columnId1.getValuesList().get(0).getValue()).isEqualTo(columnIdBis1.getValuesList().get(0).getValue()).isEqualTo("Weaver");
    Assertions.assertThat(columnId1.getValuesList().get(1).getValue()).isEqualTo(columnIdBis1.getValuesList().get(1).getValue()).isEqualTo("Phoenix");
    Assertions.assertThat(columnId1.getValuesList().get(2).getValue()).isEqualTo(columnIdBis1.getValuesList().get(2).getValue()).isEqualTo("Worthington");
    Assertions.assertThat(columnId2.getValuesList().get(0).getValue()).isEqualTo(columnIdBis2.getValuesList().get(0).getValue()).isEqualTo("Sigourney");
    Assertions.assertThat(columnId2.getValuesList().get(1).getValue()).isEqualTo(columnIdBis2.getValuesList().get(1).getValue()).isEqualTo("Joaquim");
    Assertions.assertThat(columnId2.getValuesList().get(2).getValue()).isEqualTo(columnIdBis2.getValuesList().get(2).getValue()).isEqualTo("Sam");
    Assertions.assertThat(columnId3.getValuesList().get(0).getValue()).isEqualTo(columnIdBis3.getValuesList().get(0).getValue()).isEqualTo(Date.valueOf("1949-10-08"));
    Assertions.assertThat(columnId3.getValuesList().get(1).getValue()).isEqualTo(columnIdBis3.getValuesList().get(1).getValue()).isEqualTo(Date.valueOf("1974-10-28"));
    Assertions.assertThat(columnId3.getValuesList().get(2).getValue()).isEqualTo(columnIdBis3.getValuesList().get(2).getValue()).isEqualTo(Date.valueOf("1976-08-02"));
  }

  /**
   * This method tests the {@code column} navigation method.
   */
  @Test
  public void test_column_with_column_name_from_request_with_assertions() throws Exception {
    Field fieldPosition = AbstractDbAssert.class.getDeclaredField("columnPosition");
    fieldPosition.setAccessible(true);
    Field fieldColumn = AbstractColumnAssert.class.getDeclaredField("column");
    fieldColumn.setAccessible(true);
    Field fieldIndex = Position.class.getDeclaredField("nextIndex");
    fieldIndex.setAccessible(true);

    Request request = new Request(source, "select * from actor");
    RequestAssert requestAssert = assertThat(request);
    Position position = (Position) fieldPosition.get(requestAssert);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(0);
    RequestColumnAssert requestColumnAssert0 = requestAssert.column("ID");
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(1);
    RequestColumnAssert requestColumnAssert1 = requestAssert.column("NAME");
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(2);
    RequestColumnAssert requestColumnAssert2 = requestAssert.column("FIRSTNAME");
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(3);
    RequestColumnAssert requestColumnAssert3 = requestAssert.column("BIRTH");
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(4);
    try {
      requestAssert.column("TEST");
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Column <TEST> does not exist");
    }
    try {
      requestAssert.column(null);
      fail("An exception must be raised");
    } catch (NullPointerException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Column name must be not null");
    }

    RequestAssert requestAssertBis = assertThat(request);
    Position positionBis = (Position) fieldPosition.get(requestAssertBis);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(0);
    RequestColumnAssert requestColumnAssertBis0 = requestAssertBis.column("ID");
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(1);
    RequestColumnAssert requestColumnAssertBis1 = requestColumnAssertBis0.column("NAME");
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(2);
    RequestColumnAssert requestColumnAssertBis2 = requestColumnAssertBis1.column("FIRSTNAME");
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(3);
    RequestColumnAssert requestColumnAssertBis3 = requestColumnAssertBis2.column("BIRTH");
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(4);
    try {
      requestColumnAssertBis3.column("TEST");
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Column <TEST> does not exist");
    }
    try {
      requestColumnAssertBis3.column(null);
      fail("An exception must be raised");
    } catch (NullPointerException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Column name must be not null");
    }

    Column columnId0 = (Column) fieldColumn.get(requestColumnAssert0);
    Column columnId1 = (Column) fieldColumn.get(requestColumnAssert1);
    Column columnId2 = (Column) fieldColumn.get(requestColumnAssert2);
    Column columnId3 = (Column) fieldColumn.get(requestColumnAssert3);
    Column columnIdBis0 = (Column) fieldColumn.get(requestColumnAssertBis0);
    Column columnIdBis1 = (Column) fieldColumn.get(requestColumnAssertBis1);
    Column columnIdBis2 = (Column) fieldColumn.get(requestColumnAssertBis2);
    Column columnIdBis3 = (Column) fieldColumn.get(requestColumnAssertBis3);

    Assertions.assertThat(columnId0.getName()).isEqualTo(columnIdBis0.getName()).isEqualTo("ID");
    Assertions.assertThat(columnId1.getName()).isEqualTo(columnIdBis1.getName()).isEqualTo("NAME");
    Assertions.assertThat(columnId2.getName()).isEqualTo(columnIdBis2.getName()).isEqualTo("FIRSTNAME");
    Assertions.assertThat(columnId3.getName()).isEqualTo(columnIdBis3.getName()).isEqualTo("BIRTH");

    Assertions.assertThat(columnId0.getValuesList().get(0).getValue()).isEqualTo(columnIdBis0.getValuesList().get(0).getValue()).isEqualTo(
            new BigDecimal("1"));
    Assertions.assertThat(columnId0.getValuesList().get(1).getValue()).isEqualTo(columnIdBis0.getValuesList().get(1).getValue()).isEqualTo(
            new BigDecimal("2"));
    Assertions.assertThat(columnId0.getValuesList().get(2).getValue()).isEqualTo(columnIdBis0.getValuesList().get(2).getValue()).isEqualTo(
            new BigDecimal("3"));
    Assertions.assertThat(columnId1.getValuesList().get(0).getValue()).isEqualTo(columnIdBis1.getValuesList().get(0).getValue()).isEqualTo("Weaver");
    Assertions.assertThat(columnId1.getValuesList().get(1).getValue()).isEqualTo(columnIdBis1.getValuesList().get(1).getValue()).isEqualTo("Phoenix");
    Assertions.assertThat(columnId1.getValuesList().get(2).getValue()).isEqualTo(columnIdBis1.getValuesList().get(2).getValue()).isEqualTo("Worthington");
    Assertions.assertThat(columnId2.getValuesList().get(0).getValue()).isEqualTo(columnIdBis2.getValuesList().get(0).getValue()).isEqualTo("Sigourney");
    Assertions.assertThat(columnId2.getValuesList().get(1).getValue()).isEqualTo(columnIdBis2.getValuesList().get(1).getValue()).isEqualTo("Joaquim");
    Assertions.assertThat(columnId2.getValuesList().get(2).getValue()).isEqualTo(columnIdBis2.getValuesList().get(2).getValue()).isEqualTo("Sam");
    Assertions.assertThat(columnId3.getValuesList().get(0).getValue()).isEqualTo(columnIdBis3.getValuesList().get(0).getValue()).isEqualTo(Date.valueOf("1949-10-08"));
    Assertions.assertThat(columnId3.getValuesList().get(1).getValue()).isEqualTo(columnIdBis3.getValuesList().get(1).getValue()).isEqualTo(Date.valueOf("1974-10-28"));
    Assertions.assertThat(columnId3.getValuesList().get(2).getValue()).isEqualTo(columnIdBis3.getValuesList().get(2).getValue()).isEqualTo(Date.valueOf("1976-08-02"));
  }

  /**
   * This method tests the {@code column} navigation method.
   */
  @Test
  @NeedReload
  public void test_column_with_column_name_from_change_with_displays() throws Exception {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    Field fieldPosition = ChangeDisplay.class.getDeclaredField("columnPosition");
    fieldPosition.setAccessible(true);
    Field fieldIndex = PositionWithColumnsChange.class.getDeclaredField("nextIndex");
    fieldIndex.setAccessible(true);
    Field fieldColumnName = ChangeColumnDisplay.class.getDeclaredField("columnName");
    fieldColumnName.setAccessible(true);
    Field fieldValueAtStartPoint = ChangeColumnDisplay.class.getDeclaredField("valueAtStartPoint");
    fieldValueAtStartPoint.setAccessible(true);
    Field fieldValueAtEndPoint = ChangeColumnDisplay.class.getDeclaredField("valueAtEndPoint");
    fieldValueAtEndPoint.setAccessible(true);

    ChangesDisplay changesDisplay = display(changes);
    ChangeDisplay changeDisplay = changesDisplay.change();
    PositionWithColumnsChange position = (PositionWithColumnsChange) fieldPosition.get(changeDisplay);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(0);
    ChangeColumnDisplay changeColumnDisplay0 = changeDisplay.column("ID");
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(1);
    ChangeColumnDisplay changeColumnDisplay1 = changeDisplay.column("NAME");
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(2);
    ChangeColumnDisplay changeColumnDisplay2 = changeDisplay.column("FIRSTNAME");
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(3);
    ChangeColumnDisplay changeColumnDisplay3 = changeDisplay.column("BIRTH");
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(4);
    try {
      changeDisplay.column("TEST");
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Column <TEST> does not exist");
    }
    try {
      changeDisplay.column(null);
      fail("An exception must be raised");
    } catch (NullPointerException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Column name must be not null");
    }

    ChangesDisplay changesDisplayBis = display(changes);
    ChangeDisplay changeDisplayBis = changesDisplayBis.change();
    PositionWithColumnsChange positionBis = (PositionWithColumnsChange) fieldPosition.get(changeDisplayBis);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(0);
    ChangeColumnDisplay changeColumnDisplayBis0 = changeDisplayBis.column("ID");
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(1);
    ChangeColumnDisplay changeColumnDisplayBis1 = changeColumnDisplayBis0.column("NAME");
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(2);
    ChangeColumnDisplay changeColumnDisplayBis2 = changeColumnDisplayBis1.column("FIRSTNAME");
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(3);
    ChangeColumnDisplay changeColumnDisplayBis3 = changeColumnDisplayBis2.column("BIRTH");
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(4);
    try {
      changeColumnDisplayBis3.column("TEST");
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Column <TEST> does not exist");
    }
    try {
      changeColumnDisplayBis3.column(null);
      fail("An exception must be raised");
    } catch (NullPointerException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Column name must be not null");
    }

    Assertions.assertThat(fieldColumnName.get(changeColumnDisplay0)).isEqualTo(fieldColumnName.get(changeColumnDisplayBis0)).isEqualTo(
            "ID");
    Assertions.assertThat(fieldColumnName.get(changeColumnDisplay1)).isEqualTo(fieldColumnName.get(changeColumnDisplayBis1)).isEqualTo(
            "NAME");
    Assertions.assertThat(fieldColumnName.get(changeColumnDisplay2)).isEqualTo(fieldColumnName.get(changeColumnDisplayBis2)).isEqualTo(
            "FIRSTNAME");
    Assertions.assertThat(fieldColumnName.get(changeColumnDisplay3)).isEqualTo(fieldColumnName.get(changeColumnDisplayBis3)).isEqualTo(
            "BIRTH");

    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnDisplay0)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnDisplay1)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnDisplay2)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnDisplay3)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnDisplayBis0)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnDisplayBis1)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnDisplayBis2)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnDisplayBis3)).getValue()).isNull();

    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnDisplay0)).getValue())
              .isEqualTo(((Value) fieldValueAtEndPoint.get(changeColumnDisplayBis0)).getValue())
              .isEqualTo(new BigDecimal("4"));
    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnDisplay1)).getValue())
              .isEqualTo(((Value) fieldValueAtEndPoint.get(changeColumnDisplayBis1)).getValue())
              .isEqualTo("Murray");
    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnDisplay2)).getValue())
              .isEqualTo(((Value) fieldValueAtEndPoint.get(changeColumnDisplayBis2)).getValue())
              .isEqualTo("Bill");
    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnDisplay3)).getValue())
              .isEqualTo(((Value) fieldValueAtEndPoint.get(changeColumnDisplayBis3)).getValue())
              .isEqualTo(Date.valueOf("1950-09-21"));
  }

  /**
   * This method tests the {@code column} navigation method.
   */
  @Test
  public void test_column_with_column_name_from_table_with_displays() throws Exception {
    Field fieldPosition = AbstractDbDisplay.class.getDeclaredField("columnPosition");
    fieldPosition.setAccessible(true);
    Field fieldColumn = AbstractColumnDisplay.class.getDeclaredField("column");
    fieldColumn.setAccessible(true);
    Field fieldIndex = Position.class.getDeclaredField("nextIndex");
    fieldIndex.setAccessible(true);

    Table table = new Table(source, "actor");
    TableDisplay tableDisplay = display(table);
    Position position = (Position) fieldPosition.get(tableDisplay);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(0);
    TableColumnDisplay tableColumnDisplay0 = tableDisplay.column("ID");
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(1);
    TableColumnDisplay tableColumnDisplay1 = tableDisplay.column("NAME");
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(2);
    TableColumnDisplay tableColumnDisplay2 = tableDisplay.column("FIRSTNAME");
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(3);
    TableColumnDisplay tableColumnDisplay3 = tableDisplay.column("BIRTH");
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(4);
    try {
      tableDisplay.column("TEST");
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Column <TEST> does not exist");
    }
    try {
      tableDisplay.column(null);
      fail("An exception must be raised");
    } catch (NullPointerException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Column name must be not null");
    }

    TableDisplay tableDisplayBis = display(table);
    Position positionBis = (Position) fieldPosition.get(tableDisplayBis);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(0);
    TableColumnDisplay tableColumnDisplayBis0 = tableDisplayBis.column("ID");
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(1);
    TableColumnDisplay tableColumnDisplayBis1 = tableColumnDisplayBis0.column("NAME");
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(2);
    TableColumnDisplay tableColumnDisplayBis2 = tableColumnDisplayBis1.column("FIRSTNAME");
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(3);
    TableColumnDisplay tableColumnDisplayBis3 = tableColumnDisplayBis2.column("BIRTH");
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(4);
    try {
      tableColumnDisplayBis3.column("TEST");
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Column <TEST> does not exist");
    }
    try {
      tableColumnDisplayBis3.column(null);
      fail("An exception must be raised");
    } catch (NullPointerException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Column name must be not null");
    }

    Column columnId0 = (Column) fieldColumn.get(tableColumnDisplay0);
    Column columnId1 = (Column) fieldColumn.get(tableColumnDisplay1);
    Column columnId2 = (Column) fieldColumn.get(tableColumnDisplay2);
    Column columnId3 = (Column) fieldColumn.get(tableColumnDisplay3);
    Column columnIdBis0 = (Column) fieldColumn.get(tableColumnDisplayBis0);
    Column columnIdBis1 = (Column) fieldColumn.get(tableColumnDisplayBis1);
    Column columnIdBis2 = (Column) fieldColumn.get(tableColumnDisplayBis2);
    Column columnIdBis3 = (Column) fieldColumn.get(tableColumnDisplayBis3);

    Assertions.assertThat(columnId0.getName()).isEqualTo(columnIdBis0.getName()).isEqualTo("ID");
    Assertions.assertThat(columnId1.getName()).isEqualTo(columnIdBis1.getName()).isEqualTo("NAME");
    Assertions.assertThat(columnId2.getName()).isEqualTo(columnIdBis2.getName()).isEqualTo("FIRSTNAME");
    Assertions.assertThat(columnId3.getName()).isEqualTo(columnIdBis3.getName()).isEqualTo("BIRTH");

    Assertions.assertThat(columnId0.getValuesList().get(0).getValue()).isEqualTo(columnIdBis0.getValuesList().get(0).getValue()).isEqualTo(
            new BigDecimal("1"));
    Assertions.assertThat(columnId0.getValuesList().get(1).getValue()).isEqualTo(columnIdBis0.getValuesList().get(1).getValue()).isEqualTo(
            new BigDecimal("2"));
    Assertions.assertThat(columnId0.getValuesList().get(2).getValue()).isEqualTo(columnIdBis0.getValuesList().get(2).getValue()).isEqualTo(
            new BigDecimal("3"));
    Assertions.assertThat(columnId1.getValuesList().get(0).getValue()).isEqualTo(columnIdBis1.getValuesList().get(0).getValue()).isEqualTo("Weaver");
    Assertions.assertThat(columnId1.getValuesList().get(1).getValue()).isEqualTo(columnIdBis1.getValuesList().get(1).getValue()).isEqualTo("Phoenix");
    Assertions.assertThat(columnId1.getValuesList().get(2).getValue()).isEqualTo(columnIdBis1.getValuesList().get(2).getValue()).isEqualTo("Worthington");
    Assertions.assertThat(columnId2.getValuesList().get(0).getValue()).isEqualTo(columnIdBis2.getValuesList().get(0).getValue()).isEqualTo("Sigourney");
    Assertions.assertThat(columnId2.getValuesList().get(1).getValue()).isEqualTo(columnIdBis2.getValuesList().get(1).getValue()).isEqualTo("Joaquim");
    Assertions.assertThat(columnId2.getValuesList().get(2).getValue()).isEqualTo(columnIdBis2.getValuesList().get(2).getValue()).isEqualTo("Sam");
    Assertions.assertThat(columnId3.getValuesList().get(0).getValue()).isEqualTo(columnIdBis3.getValuesList().get(0).getValue()).isEqualTo(Date.valueOf("1949-10-08"));
    Assertions.assertThat(columnId3.getValuesList().get(1).getValue()).isEqualTo(columnIdBis3.getValuesList().get(1).getValue()).isEqualTo(Date.valueOf("1974-10-28"));
    Assertions.assertThat(columnId3.getValuesList().get(2).getValue()).isEqualTo(columnIdBis3.getValuesList().get(2).getValue()).isEqualTo(Date.valueOf("1976-08-02"));
  }

  /**
   * This method tests the {@code column} navigation method.
   */
  @Test
  public void test_column_with_column_name_from_request_with_displays() throws Exception {
    Field fieldPosition = AbstractDbDisplay.class.getDeclaredField("columnPosition");
    fieldPosition.setAccessible(true);
    Field fieldColumn = AbstractColumnDisplay.class.getDeclaredField("column");
    fieldColumn.setAccessible(true);
    Field fieldIndex = Position.class.getDeclaredField("nextIndex");
    fieldIndex.setAccessible(true);

    Request request = new Request(source, "select * from actor");
    RequestDisplay requestDisplay = display(request);
    Position position = (Position) fieldPosition.get(requestDisplay);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(0);
    RequestColumnDisplay requestColumnDisplay0 = requestDisplay.column("ID");
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(1);
    RequestColumnDisplay requestColumnDisplay1 = requestDisplay.column("NAME");
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(2);
    RequestColumnDisplay requestColumnDisplay2 = requestDisplay.column("FIRSTNAME");
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(3);
    RequestColumnDisplay requestColumnDisplay3 = requestDisplay.column("BIRTH");
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(4);
    try {
      requestDisplay.column("TEST");
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Column <TEST> does not exist");
    }
    try {
      requestDisplay.column(null);
      fail("An exception must be raised");
    } catch (NullPointerException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Column name must be not null");
    }

    RequestDisplay requestDisplayBis = display(request);
    Position positionBis = (Position) fieldPosition.get(requestDisplayBis);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(0);
    RequestColumnDisplay requestColumnDisplayBis0 = requestDisplayBis.column("ID");
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(1);
    RequestColumnDisplay requestColumnDisplayBis1 = requestColumnDisplayBis0.column("NAME");
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(2);
    RequestColumnDisplay requestColumnDisplayBis2 = requestColumnDisplayBis1.column("FIRSTNAME");
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(3);
    RequestColumnDisplay requestColumnDisplayBis3 = requestColumnDisplayBis2.column("BIRTH");
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(4);
    try {
      requestColumnDisplayBis3.column("TEST");
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Column <TEST> does not exist");
    }
    try {
      requestColumnDisplayBis3.column(null);
      fail("An exception must be raised");
    } catch (NullPointerException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Column name must be not null");
    }

    Column columnId0 = (Column) fieldColumn.get(requestColumnDisplay0);
    Column columnId1 = (Column) fieldColumn.get(requestColumnDisplay1);
    Column columnId2 = (Column) fieldColumn.get(requestColumnDisplay2);
    Column columnId3 = (Column) fieldColumn.get(requestColumnDisplay3);
    Column columnIdBis0 = (Column) fieldColumn.get(requestColumnDisplayBis0);
    Column columnIdBis1 = (Column) fieldColumn.get(requestColumnDisplayBis1);
    Column columnIdBis2 = (Column) fieldColumn.get(requestColumnDisplayBis2);
    Column columnIdBis3 = (Column) fieldColumn.get(requestColumnDisplayBis3);

    Assertions.assertThat(columnId0.getName()).isEqualTo(columnIdBis0.getName()).isEqualTo("ID");
    Assertions.assertThat(columnId1.getName()).isEqualTo(columnIdBis1.getName()).isEqualTo("NAME");
    Assertions.assertThat(columnId2.getName()).isEqualTo(columnIdBis2.getName()).isEqualTo("FIRSTNAME");
    Assertions.assertThat(columnId3.getName()).isEqualTo(columnIdBis3.getName()).isEqualTo("BIRTH");

    Assertions.assertThat(columnId0.getValuesList().get(0).getValue()).isEqualTo(columnIdBis0.getValuesList().get(0).getValue()).isEqualTo(
            new BigDecimal("1"));
    Assertions.assertThat(columnId0.getValuesList().get(1).getValue()).isEqualTo(columnIdBis0.getValuesList().get(1).getValue()).isEqualTo(
            new BigDecimal("2"));
    Assertions.assertThat(columnId0.getValuesList().get(2).getValue()).isEqualTo(columnIdBis0.getValuesList().get(2).getValue()).isEqualTo(
            new BigDecimal("3"));
    Assertions.assertThat(columnId1.getValuesList().get(0).getValue()).isEqualTo(columnIdBis1.getValuesList().get(0).getValue()).isEqualTo("Weaver");
    Assertions.assertThat(columnId1.getValuesList().get(1).getValue()).isEqualTo(columnIdBis1.getValuesList().get(1).getValue()).isEqualTo("Phoenix");
    Assertions.assertThat(columnId1.getValuesList().get(2).getValue()).isEqualTo(columnIdBis1.getValuesList().get(2).getValue()).isEqualTo("Worthington");
    Assertions.assertThat(columnId2.getValuesList().get(0).getValue()).isEqualTo(columnIdBis2.getValuesList().get(0).getValue()).isEqualTo("Sigourney");
    Assertions.assertThat(columnId2.getValuesList().get(1).getValue()).isEqualTo(columnIdBis2.getValuesList().get(1).getValue()).isEqualTo("Joaquim");
    Assertions.assertThat(columnId2.getValuesList().get(2).getValue()).isEqualTo(columnIdBis2.getValuesList().get(2).getValue()).isEqualTo("Sam");
    Assertions.assertThat(columnId3.getValuesList().get(0).getValue()).isEqualTo(columnIdBis3.getValuesList().get(0).getValue()).isEqualTo(Date.valueOf("1949-10-08"));
    Assertions.assertThat(columnId3.getValuesList().get(1).getValue()).isEqualTo(columnIdBis3.getValuesList().get(1).getValue()).isEqualTo(Date.valueOf("1974-10-28"));
    Assertions.assertThat(columnId3.getValuesList().get(2).getValue()).isEqualTo(columnIdBis3.getValuesList().get(2).getValue()).isEqualTo(Date.valueOf("1976-08-02"));
  }
}
