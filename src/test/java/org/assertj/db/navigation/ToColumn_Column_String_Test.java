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
 * Copyright 2015-2025 the original author or authors.
 */
package org.assertj.db.navigation;

import static org.assertj.core.api.Assertions.fail;
import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Date;

import org.assertj.core.api.Assertions;
import org.assertj.db.api.AbstractColumnAssert;
import org.assertj.db.api.AbstractDbAssert;
import org.assertj.db.api.ChangeAssert;
import org.assertj.db.api.ChangeColumnAssert;
import org.assertj.db.api.ChangesAssert;
import org.assertj.db.api.RequestAssert;
import org.assertj.db.api.RequestColumnAssert;
import org.assertj.db.api.TableAssert;
import org.assertj.db.api.TableColumnAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.assertj.db.exception.AssertJDBException;
import org.assertj.db.output.AbstractColumnOutputter;
import org.assertj.db.output.AbstractDbOutputter;
import org.assertj.db.output.ChangeColumnOutputter;
import org.assertj.db.output.ChangeOutputter;
import org.assertj.db.output.ChangesOutputter;
import org.assertj.db.output.Outputs;
import org.assertj.db.output.RequestColumnOutputter;
import org.assertj.db.output.RequestOutputter;
import org.assertj.db.output.TableColumnOutputter;
import org.assertj.db.output.TableOutputter;
import org.assertj.db.type.Changes;
import org.assertj.db.type.Column;
import org.assertj.db.type.Request;
import org.assertj.db.type.Table;
import org.assertj.db.type.Value;
import org.junit.Test;

/**
 * Tests on {@link org.assertj.db.navigation.ToColumn} class :
 * {@link org.assertj.db.navigation.ToColumn#column(String)} method.
 *
 * @author Régis Pouiller
 * @author Julien Roy
 */
public class ToColumn_Column_String_Test extends AbstractTest {

  /**
   * This method tests the {@code column} navigation method.
   */
  @Test
  @NeedReload
  public void test_column_with_column_name_from_change_with_assertions() throws Exception {
    Changes changes = assertDbConnection.changes().build().setStartPointNow();
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
    PositionWithColumnsChange<ChangeAssert, ChangeColumnAssert> position =
      (PositionWithColumnsChange) fieldPosition.get(changeAssert);
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
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("Column <TEST> does not exist%n"
        + "in <[ID, NAME, FIRSTNAME, BIRTH, ACTOR_IMDB]>%n"
        + "with comparison IGNORE - Ignore the case"));
    }
    try {
      changeAssert.column(null);
      fail("An exception must be raised");
    } catch (NullPointerException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Column name must be not null");
    }

    ChangesAssert changesAssertBis = assertThat(changes);
    ChangeAssert changeAssertBis = changesAssertBis.change();
    PositionWithColumnsChange<ChangeAssert, ChangeColumnAssert> positionBis =
      (PositionWithColumnsChange) fieldPosition.get(changeAssertBis);
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
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("Column <TEST> does not exist%n"
        + "in <[ID, NAME, FIRSTNAME, BIRTH, ACTOR_IMDB]>%n"
        + "with comparison IGNORE - Ignore the case"));
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

    Table table = assertDbConnection.table("actor").build();
    TableAssert tableAssert = assertThat(table);
    Position<TableAssert, TableColumnAssert, Column> position = (Position) fieldPosition.get(tableAssert);
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
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("Column <TEST> does not exist%n"
        + "in <[ID, NAME, FIRSTNAME, BIRTH, ACTOR_IMDB]>%n"
        + "with comparison IGNORE - Ignore the case"));
    }
    try {
      tableAssert.column(null);
      fail("An exception must be raised");
    } catch (NullPointerException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Column name must be not null");
    }

    TableAssert tableAssertBis = assertThat(table);
    Position<TableAssert, TableColumnAssert, Column> positionBis = (Position) fieldPosition.get(tableAssertBis);
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
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("Column <TEST> does not exist%n"
        + "in <[ID, NAME, FIRSTNAME, BIRTH, ACTOR_IMDB]>%n"
        + "with comparison IGNORE - Ignore the case"));
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

    Request request = assertDbConnection.request("select * from actor").build();
    RequestAssert requestAssert = assertThat(request);
    Position<RequestAssert, RequestColumnAssert, Column> position = (Position) fieldPosition.get(requestAssert);
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
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("Column <TEST> does not exist%n"
        + "in <[ID, NAME, FIRSTNAME, BIRTH, ACTOR_IMDB]>%n"
        + "with comparison IGNORE - Ignore the case"));
    }
    try {
      requestAssert.column(null);
      fail("An exception must be raised");
    } catch (NullPointerException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Column name must be not null");
    }

    RequestAssert requestAssertBis = assertThat(request);
    Position<RequestAssert, RequestColumnAssert, Column> positionBis = (Position) fieldPosition.get(requestAssertBis);
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
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("Column <TEST> does not exist%n"
        + "in <[ID, NAME, FIRSTNAME, BIRTH, ACTOR_IMDB]>%n"
        + "with comparison IGNORE - Ignore the case"));
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
    Changes changes = assertDbConnection.changes().build().setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    Field fieldPosition = ChangeOutputter.class.getDeclaredField("columnPosition");
    fieldPosition.setAccessible(true);
    Field fieldIndex = PositionWithColumnsChange.class.getDeclaredField("nextIndex");
    fieldIndex.setAccessible(true);
    Field fieldColumnName = ChangeColumnOutputter.class.getDeclaredField("columnName");
    fieldColumnName.setAccessible(true);
    Field fieldValueAtStartPoint = ChangeColumnOutputter.class.getDeclaredField("valueAtStartPoint");
    fieldValueAtStartPoint.setAccessible(true);
    Field fieldValueAtEndPoint = ChangeColumnOutputter.class.getDeclaredField("valueAtEndPoint");
    fieldValueAtEndPoint.setAccessible(true);

    ChangesOutputter changesOutputter = output(changes);
    ChangeOutputter changeOutputter = changesOutputter.change();
    PositionWithColumnsChange<ChangeOutputter, ChangeColumnOutputter> position =
      (PositionWithColumnsChange) fieldPosition.get(changeOutputter);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(0);
    ChangeColumnOutputter changeColumnOutputter0 = changeOutputter.column("ID");
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(1);
    ChangeColumnOutputter changeColumnOutputter1 = changeOutputter.column("NAME");
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(2);
    ChangeColumnOutputter changeColumnOutputter2 = changeOutputter.column("FIRSTNAME");
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(3);
    ChangeColumnOutputter changeColumnOutputter3 = changeOutputter.column("BIRTH");
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(4);
    try {
      changeOutputter.column("TEST");
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("Column <TEST> does not exist%n"
        + "in <[ID, NAME, FIRSTNAME, BIRTH, ACTOR_IMDB]>%n"
        + "with comparison IGNORE - Ignore the case"));
    }
    try {
      changeOutputter.column(null);
      fail("An exception must be raised");
    } catch (NullPointerException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Column name must be not null");
    }

    ChangesOutputter changesOutputterBis = output(changes);
    ChangeOutputter changeOutputterBis = changesOutputterBis.change();
    PositionWithColumnsChange<ChangeOutputter, ChangeColumnOutputter> positionBis =
      (PositionWithColumnsChange) fieldPosition.get(changeOutputterBis);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(0);
    ChangeColumnOutputter changeColumnOutputterBis0 = changeOutputterBis.column("ID");
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(1);
    ChangeColumnOutputter changeColumnOutputterBis1 = changeColumnOutputterBis0.column("NAME");
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(2);
    ChangeColumnOutputter changeColumnOutputterBis2 = changeColumnOutputterBis1.column("FIRSTNAME");
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(3);
    ChangeColumnOutputter changeColumnOutputterBis3 = changeColumnOutputterBis2.column("BIRTH");
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(4);
    try {
      changeColumnOutputterBis3.column("TEST");
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("Column <TEST> does not exist%n"
        + "in <[ID, NAME, FIRSTNAME, BIRTH, ACTOR_IMDB]>%n"
        + "with comparison IGNORE - Ignore the case"));
    }
    try {
      changeColumnOutputterBis3.column(null);
      fail("An exception must be raised");
    } catch (NullPointerException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Column name must be not null");
    }

    Assertions.assertThat(fieldColumnName.get(changeColumnOutputter0)).isEqualTo(fieldColumnName.get(changeColumnOutputterBis0)).isEqualTo(
      "ID");
    Assertions.assertThat(fieldColumnName.get(changeColumnOutputter1)).isEqualTo(fieldColumnName.get(changeColumnOutputterBis1)).isEqualTo(
      "NAME");
    Assertions.assertThat(fieldColumnName.get(changeColumnOutputter2)).isEqualTo(fieldColumnName.get(changeColumnOutputterBis2)).isEqualTo(
      "FIRSTNAME");
    Assertions.assertThat(fieldColumnName.get(changeColumnOutputter3)).isEqualTo(fieldColumnName.get(changeColumnOutputterBis3)).isEqualTo(
      "BIRTH");

    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnOutputter0)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnOutputter1)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnOutputter2)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnOutputter3)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnOutputterBis0)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnOutputterBis1)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnOutputterBis2)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnOutputterBis3)).getValue()).isNull();

    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnOutputter0)).getValue())
      .isEqualTo(((Value) fieldValueAtEndPoint.get(changeColumnOutputterBis0)).getValue())
      .isEqualTo(new BigDecimal("4"));
    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnOutputter1)).getValue())
      .isEqualTo(((Value) fieldValueAtEndPoint.get(changeColumnOutputterBis1)).getValue())
      .isEqualTo("Murray");
    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnOutputter2)).getValue())
      .isEqualTo(((Value) fieldValueAtEndPoint.get(changeColumnOutputterBis2)).getValue())
      .isEqualTo("Bill");
    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnOutputter3)).getValue())
      .isEqualTo(((Value) fieldValueAtEndPoint.get(changeColumnOutputterBis3)).getValue())
      .isEqualTo(Date.valueOf("1950-09-21"));
  }

  /**
   * This method tests the {@code column} navigation method.
   */
  @Test
  public void test_column_with_column_name_from_table_with_displays() throws Exception {
    Field fieldPosition = AbstractDbOutputter.class.getDeclaredField("columnPosition");
    fieldPosition.setAccessible(true);
    Field fieldColumn = AbstractColumnOutputter.class.getDeclaredField("column");
    fieldColumn.setAccessible(true);
    Field fieldIndex = Position.class.getDeclaredField("nextIndex");
    fieldIndex.setAccessible(true);

    Table table = assertDbConnection.table("actor").build();
    TableOutputter tableOutputter = Outputs.output(table);
    Position<TableOutputter, TableColumnOutputter, Column> position =
      (Position) fieldPosition.get(tableOutputter);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(0);
    TableColumnOutputter tableColumnOutputter0 = tableOutputter.column("ID");
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(1);
    TableColumnOutputter tableColumnOutputter1 = tableOutputter.column("NAME");
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(2);
    TableColumnOutputter tableColumnOutputter2 = tableOutputter.column("FIRSTNAME");
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(3);
    TableColumnOutputter tableColumnOutputter3 = tableOutputter.column("BIRTH");
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(4);
    try {
      tableOutputter.column("TEST");
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("Column <TEST> does not exist%n"
        + "in <[ID, NAME, FIRSTNAME, BIRTH, ACTOR_IMDB]>%n"
        + "with comparison IGNORE - Ignore the case"));
    }
    try {
      tableOutputter.column(null);
      fail("An exception must be raised");
    } catch (NullPointerException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Column name must be not null");
    }

    TableOutputter tableOutputterBis = Outputs.output(table);
    Position<TableOutputter, TableColumnOutputter, Column> positionBis =
      (Position) fieldPosition.get(tableOutputterBis);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(0);
    TableColumnOutputter tableColumnOutputterBis0 = tableOutputterBis.column("ID");
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(1);
    TableColumnOutputter tableColumnOutputterBis1 = tableColumnOutputterBis0.column("NAME");
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(2);
    TableColumnOutputter tableColumnOutputterBis2 = tableColumnOutputterBis1.column("FIRSTNAME");
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(3);
    TableColumnOutputter tableColumnOutputterBis3 = tableColumnOutputterBis2.column("BIRTH");
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(4);
    try {
      tableColumnOutputterBis3.column("TEST");
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("Column <TEST> does not exist%n"
        + "in <[ID, NAME, FIRSTNAME, BIRTH, ACTOR_IMDB]>%n"
        + "with comparison IGNORE - Ignore the case"));
    }
    try {
      tableColumnOutputterBis3.column(null);
      fail("An exception must be raised");
    } catch (NullPointerException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Column name must be not null");
    }

    Column columnId0 = (Column) fieldColumn.get(tableColumnOutputter0);
    Column columnId1 = (Column) fieldColumn.get(tableColumnOutputter1);
    Column columnId2 = (Column) fieldColumn.get(tableColumnOutputter2);
    Column columnId3 = (Column) fieldColumn.get(tableColumnOutputter3);
    Column columnIdBis0 = (Column) fieldColumn.get(tableColumnOutputterBis0);
    Column columnIdBis1 = (Column) fieldColumn.get(tableColumnOutputterBis1);
    Column columnIdBis2 = (Column) fieldColumn.get(tableColumnOutputterBis2);
    Column columnIdBis3 = (Column) fieldColumn.get(tableColumnOutputterBis3);

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
    Field fieldPosition = AbstractDbOutputter.class.getDeclaredField("columnPosition");
    fieldPosition.setAccessible(true);
    Field fieldColumn = AbstractColumnOutputter.class.getDeclaredField("column");
    fieldColumn.setAccessible(true);
    Field fieldIndex = Position.class.getDeclaredField("nextIndex");
    fieldIndex.setAccessible(true);

    Request request = assertDbConnection.request("select * from actor").build();
    RequestOutputter requestOutputter = Outputs.output(request);
    Position<RequestOutputter, RequestColumnOutputter, Column> position =
      (Position) fieldPosition.get(requestOutputter);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(0);
    RequestColumnOutputter requestColumnOutputter0 = requestOutputter.column("ID");
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(1);
    RequestColumnOutputter requestColumnOutputter1 = requestOutputter.column("NAME");
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(2);
    RequestColumnOutputter requestColumnOutputter2 = requestOutputter.column("FIRSTNAME");
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(3);
    RequestColumnOutputter requestColumnOutputter3 = requestOutputter.column("BIRTH");
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(4);
    try {
      requestOutputter.column("TEST");
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("Column <TEST> does not exist%n"
        + "in <[ID, NAME, FIRSTNAME, BIRTH, ACTOR_IMDB]>%n"
        + "with comparison IGNORE - Ignore the case"));
    }
    try {
      requestOutputter.column(null);
      fail("An exception must be raised");
    } catch (NullPointerException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Column name must be not null");
    }

    RequestOutputter requestOutputterBis = Outputs.output(request);
    Position<RequestOutputter, RequestColumnOutputter, Column> positionBis =
      (Position) fieldPosition.get(requestOutputterBis);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(0);
    RequestColumnOutputter requestColumnOutputterBis0 = requestOutputterBis.column("ID");
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(1);
    RequestColumnOutputter requestColumnOutputterBis1 = requestColumnOutputterBis0.column("NAME");
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(2);
    RequestColumnOutputter requestColumnOutputterBis2 = requestColumnOutputterBis1.column("FIRSTNAME");
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(3);
    RequestColumnOutputter requestColumnOutputterBis3 = requestColumnOutputterBis2.column("BIRTH");
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(4);
    try {
      requestColumnOutputterBis3.column("TEST");
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("Column <TEST> does not exist%n"
        + "in <[ID, NAME, FIRSTNAME, BIRTH, ACTOR_IMDB]>%n"
        + "with comparison IGNORE - Ignore the case"));
    }
    try {
      requestColumnOutputterBis3.column(null);
      fail("An exception must be raised");
    } catch (NullPointerException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Column name must be not null");
    }

    Column columnId0 = (Column) fieldColumn.get(requestColumnOutputter0);
    Column columnId1 = (Column) fieldColumn.get(requestColumnOutputter1);
    Column columnId2 = (Column) fieldColumn.get(requestColumnOutputter2);
    Column columnId3 = (Column) fieldColumn.get(requestColumnOutputter3);
    Column columnIdBis0 = (Column) fieldColumn.get(requestColumnOutputterBis0);
    Column columnIdBis1 = (Column) fieldColumn.get(requestColumnOutputterBis1);
    Column columnIdBis2 = (Column) fieldColumn.get(requestColumnOutputterBis2);
    Column columnIdBis3 = (Column) fieldColumn.get(requestColumnOutputterBis3);

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
