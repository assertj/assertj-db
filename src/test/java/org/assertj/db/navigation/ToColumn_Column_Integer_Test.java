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
import org.assertj.db.exception.AssertJDBException;
import org.assertj.db.output.*;
import org.assertj.db.type.*;
import org.junit.Test;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;
import static org.junit.Assert.fail;

/**
 * Tests on {@link org.assertj.db.navigation.ToColumn} class :
 * {@link org.assertj.db.navigation.ToColumn#column(int)} method.
 *
 * @author Régis Pouiller
 *
 */
public class ToColumn_Column_Integer_Test extends AbstractTest {

  /**
   * This method tests the {@code column} navigation method.
   */
  @Test
  @NeedReload
  public void test_column_with_index_from_change_with_assertions() throws Exception {
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
    ChangeAssert changeAssert = changesAssert.change(6);
    PositionWithColumnsChange position = (PositionWithColumnsChange) fieldPosition.get(changeAssert);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(0);
    ChangeColumnAssert changeColumnAssert0 = changeAssert.column(0);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(1);
    ChangeColumnAssert changeColumnAssert1 = changeAssert.column(1);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(2);
    ChangeColumnAssert changeColumnAssert2 = changeAssert.column(2);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(3);
    ChangeColumnAssert changeColumnAssert3 = changeAssert.column(3);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(4);
    ChangeColumnAssert changeColumnAssert4 = changeAssert.column(4);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(5);
    try {
      changeAssert.column(5);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 5 out of the limits [0, 5[");
    }
    try {
      changeAssert.column(-1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index -1 out of the limits [0, 5[");
    }
    ChangeColumnAssert changeColumnAssertAgain0 = changeAssert.column(0);
    Assertions.assertThat(changeColumnAssert0).isSameAs(changeColumnAssertAgain0);

    ChangesAssert changesAssertBis = assertThat(changes);
    ChangeAssert changeAssertBis = changesAssertBis.change(6);
    PositionWithColumnsChange positionBis = (PositionWithColumnsChange) fieldPosition.get(changeAssertBis);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(0);
    ChangeColumnAssert changeColumnAssertBis0 = changeAssertBis.column(0);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(1);
    ChangeColumnAssert changeColumnAssertBis1 = changeColumnAssertBis0.column(1);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(2);
    ChangeColumnAssert changeColumnAssertBis2 = changeColumnAssertBis1.column(2);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(3);
    ChangeColumnAssert changeColumnAssertBis3 = changeColumnAssertBis2.column(3);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(4);
    ChangeColumnAssert changeColumnAssertBis4 = changeColumnAssertBis3.column(4);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(5);
    try {
      changeColumnAssertBis4.column(5);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 5 out of the limits [0, 5[");
    }
    try {
      changeColumnAssertBis4.column(-1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index -1 out of the limits [0, 5[");
    }
    ChangeColumnAssert changeColumnAssertBisAgain0 = changeColumnAssertBis4.column(0);
    Assertions.assertThat(changeColumnAssertBis0).isSameAs(changeColumnAssertBisAgain0);

    Assertions.assertThat(fieldColumnName.get(changeColumnAssert0)).isEqualTo(fieldColumnName.get(changeColumnAssertBis0)).isEqualTo(
            "ID");
    Assertions.assertThat(fieldColumnName.get(changeColumnAssert1)).isEqualTo(fieldColumnName.get(changeColumnAssertBis1)).isEqualTo(
            "NAME");
    Assertions.assertThat(fieldColumnName.get(changeColumnAssert2)).isEqualTo(fieldColumnName.get(changeColumnAssertBis2)).isEqualTo(
            "FIRSTNAME");
    Assertions.assertThat(fieldColumnName.get(changeColumnAssert3)).isEqualTo(
            fieldColumnName.get(changeColumnAssertBis3)).isEqualTo(
            "BIRTH");
    Assertions.assertThat(fieldColumnName.get(changeColumnAssert4)).isEqualTo(
            fieldColumnName.get(changeColumnAssertBis4)).isEqualTo(
            "ACTOR_IMDB");

    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnAssert0)).getValue())
              .isEqualTo(((Value) fieldValueAtStartPoint.get(changeColumnAssertBis0)).getValue())
    .isEqualTo(new BigDecimal("3"));
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnAssert1)).getValue())
    .isEqualTo(((Value) fieldValueAtStartPoint.get(changeColumnAssertBis1)).getValue())
    .isEqualTo("Worthington");
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnAssert2)).getValue())
    .isEqualTo(((Value) fieldValueAtStartPoint.get(changeColumnAssertBis2)).getValue())
    .isEqualTo("Sam");
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnAssert3)).getValue())
    .isEqualTo(((Value) fieldValueAtStartPoint.get(changeColumnAssertBis3)).getValue())
    .isEqualTo(Date.valueOf("1976-08-02"));
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnAssert4)).getValue())
    .isEqualTo(((Value) fieldValueAtStartPoint.get(changeColumnAssertBis4)).getValue())
    .isEqualTo(UUID.fromString("D735221B-5DE5-4112-AA1E-49090CB75ADA"));

    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnAssert0)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnAssert1)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnAssert2)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnAssert3)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnAssert4)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnAssertBis0)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnAssertBis1)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnAssertBis2)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnAssertBis3)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnAssertBis4)).getValue()).isNull();
  }

  /**
   * This method tests the {@code column} navigation method.
   */
  @Test
  public void test_column_with_index_from_table_with_assertions() throws Exception {
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
    TableColumnAssert tableColumnAssert0 = tableAssert.column(0);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(1);
    TableColumnAssert tableColumnAssert1 = tableAssert.column(1);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(2);
    TableColumnAssert tableColumnAssert2 = tableAssert.column(2);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(3);
    TableColumnAssert tableColumnAssert3 = tableAssert.column(3);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(4);
    TableColumnAssert tableColumnAssert4 = tableAssert.column(4);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(5);
    try {
      tableAssert.column(5);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 5 out of the limits [0, 5[");
    }
    try {
      tableAssert.column(-1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index -1 out of the limits [0, 5[");
    }
    TableColumnAssert tableColumnAssertAgain0 = tableAssert.column(0);
    Assertions.assertThat(tableColumnAssert0).isSameAs(tableColumnAssertAgain0);

    TableAssert tableAssertBis = assertThat(table);
    Position positionBis = (Position) fieldPosition.get(tableAssertBis);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(0);
    TableColumnAssert tableColumnAssertBis0 = tableAssertBis.column(0);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(1);
    TableColumnAssert tableColumnAssertBis1 = tableColumnAssertBis0.column(1);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(2);
    TableColumnAssert tableColumnAssertBis2 = tableColumnAssertBis1.column(2);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(3);
    TableColumnAssert tableColumnAssertBis3 = tableColumnAssertBis2.column(3);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(4);
    TableColumnAssert tableColumnAssertBis4 = tableColumnAssertBis3.column(4);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(5);
    try {
      tableColumnAssertBis4.column(5);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 5 out of the limits [0, 5[");
    }
    try {
      tableColumnAssertBis4.column(-1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index -1 out of the limits [0, 5[");
    }
    TableColumnAssert tableColumnAssertBisAgain0 = tableColumnAssertBis4.column(0);
    Assertions.assertThat(tableColumnAssertBis0).isSameAs(tableColumnAssertBisAgain0);

    Column columnId0 = (Column) fieldColumn.get(tableColumnAssert0);
    Column columnId1 = (Column) fieldColumn.get(tableColumnAssert1);
    Column columnId2 = (Column) fieldColumn.get(tableColumnAssert2);
    Column columnId3 = (Column) fieldColumn.get(tableColumnAssert3);
    Column columnId4 = (Column) fieldColumn.get(tableColumnAssert4);
    Column columnIdBis0 = (Column) fieldColumn.get(tableColumnAssertBis0);
    Column columnIdBis1 = (Column) fieldColumn.get(tableColumnAssertBis1);
    Column columnIdBis2 = (Column) fieldColumn.get(tableColumnAssertBis2);
    Column columnIdBis3 = (Column) fieldColumn.get(tableColumnAssertBis3);
    Column columnIdBis4 = (Column) fieldColumn.get(tableColumnAssertBis4);

    Assertions.assertThat(columnId0.getName()).isEqualTo(columnIdBis0.getName()).isEqualTo("ID");
    Assertions.assertThat(columnId1.getName()).isEqualTo(columnIdBis1.getName()).isEqualTo("NAME");
    Assertions.assertThat(columnId2.getName()).isEqualTo(columnIdBis2.getName()).isEqualTo("FIRSTNAME");
    Assertions.assertThat(columnId3.getName()).isEqualTo(columnIdBis3.getName()).isEqualTo("BIRTH");
    Assertions.assertThat(columnId4.getName()).isEqualTo(columnIdBis4.getName()).isEqualTo("ACTOR_IMDB");

    Assertions.assertThat(columnId0.getValuesList().get(0).getValue()).isEqualTo(columnIdBis0.getValuesList().get(0).getValue()).isEqualTo(new BigDecimal("1"));
    Assertions.assertThat(columnId0.getValuesList().get(1).getValue()).isEqualTo(columnIdBis0.getValuesList().get(1).getValue()).isEqualTo(new BigDecimal("2"));
    Assertions.assertThat(columnId0.getValuesList().get(2).getValue()).isEqualTo(columnIdBis0.getValuesList().get(2).getValue()).isEqualTo(new BigDecimal("3"));
    Assertions.assertThat(columnId1.getValuesList().get(0).getValue()).isEqualTo(columnIdBis1.getValuesList().get(0).getValue()).isEqualTo("Weaver");
    Assertions.assertThat(columnId1.getValuesList().get(1).getValue()).isEqualTo(columnIdBis1.getValuesList().get(1).getValue()).isEqualTo("Phoenix");
    Assertions.assertThat(columnId1.getValuesList().get(2).getValue()).isEqualTo(columnIdBis1.getValuesList().get(2).getValue()).isEqualTo("Worthington");
    Assertions.assertThat(columnId2.getValuesList().get(0).getValue()).isEqualTo(columnIdBis2.getValuesList().get(0).getValue()).isEqualTo("Sigourney");
    Assertions.assertThat(columnId2.getValuesList().get(1).getValue()).isEqualTo(columnIdBis2.getValuesList().get(1).getValue()).isEqualTo("Joaquim");
    Assertions.assertThat(columnId2.getValuesList().get(2).getValue()).isEqualTo(columnIdBis2.getValuesList().get(2).getValue()).isEqualTo("Sam");
    Assertions.assertThat(columnId3.getValuesList().get(0).getValue()).isEqualTo(columnIdBis3.getValuesList().get(0).getValue()).isEqualTo(Date.valueOf("1949-10-08"));
    Assertions.assertThat(columnId3.getValuesList().get(1).getValue()).isEqualTo(columnIdBis3.getValuesList().get(1).getValue()).isEqualTo(Date.valueOf("1974-10-28"));
    Assertions.assertThat(columnId3.getValuesList().get(2).getValue()).isEqualTo(columnIdBis3.getValuesList().get(2).getValue()).isEqualTo(Date.valueOf("1976-08-02"));
    Assertions.assertThat(columnId4.getValuesList().get(0).getValue()).isEqualTo(columnIdBis4.getValuesList().get(0).getValue()).isEqualTo(UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"));
    Assertions.assertThat(columnId4.getValuesList().get(1).getValue()).isEqualTo(columnIdBis4.getValuesList().get(1).getValue()).isEqualTo(UUID.fromString("16319617-AE95-4087-9264-D3D21BF611B6"));
    Assertions.assertThat(columnId4.getValuesList().get(2).getValue()).isEqualTo(columnIdBis4.getValuesList().get(2).getValue()).isEqualTo(UUID.fromString("D735221B-5DE5-4112-AA1E-49090CB75ADA"));
  }

  /**
   * This method tests the {@code column} navigation method.
   */
  @Test
  public void test_column_with_index_from_request_with_assertions() throws Exception {
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
    RequestColumnAssert requestColumnAssert0 = requestAssert.column(0);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(1);
    RequestColumnAssert requestColumnAssert1 = requestAssert.column(1);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(2);
    RequestColumnAssert requestColumnAssert2 = requestAssert.column(2);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(3);
    RequestColumnAssert requestColumnAssert3 = requestAssert.column(3);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(4);
    RequestColumnAssert requestColumnAssert4 = requestAssert.column(4);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(5);
    try {
      requestAssert.column(5);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 5 out of the limits [0, 5[");
    }
    try {
      requestAssert.column(-1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index -1 out of the limits [0, 5[");
    }
    RequestColumnAssert requestColumnAssertAgain0 = requestAssert.column(0);
    Assertions.assertThat(requestColumnAssert0).isSameAs(requestColumnAssertAgain0);

    RequestAssert requestAssertBis = assertThat(request);
    Position positionBis = (Position) fieldPosition.get(requestAssertBis);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(0);
    RequestColumnAssert requestColumnAssertBis0 = requestAssertBis.column(0);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(1);
    RequestColumnAssert requestColumnAssertBis1 = requestColumnAssertBis0.column(1);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(2);
    RequestColumnAssert requestColumnAssertBis2 = requestColumnAssertBis1.column(2);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(3);
    RequestColumnAssert requestColumnAssertBis3 = requestColumnAssertBis2.column(3);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(4);
    RequestColumnAssert requestColumnAssertBis4 = requestColumnAssertBis3.column(4);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(5);
    try {
      requestColumnAssertBis4.column(5);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 5 out of the limits [0, 5[");
    }
    try {
      requestColumnAssertBis4.column(-1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index -1 out of the limits [0, 5[");
    }
    RequestColumnAssert requestColumnAssertBisAgain0 = requestColumnAssertBis4.column(0);
    Assertions.assertThat(requestColumnAssertBis0).isSameAs(requestColumnAssertBisAgain0);

    Column columnId0 = (Column) fieldColumn.get(requestColumnAssert0);
    Column columnId1 = (Column) fieldColumn.get(requestColumnAssert1);
    Column columnId2 = (Column) fieldColumn.get(requestColumnAssert2);
    Column columnId3 = (Column) fieldColumn.get(requestColumnAssert3);
    Column columnId4 = (Column) fieldColumn.get(requestColumnAssert4);
    Column columnIdBis0 = (Column) fieldColumn.get(requestColumnAssertBis0);
    Column columnIdBis1 = (Column) fieldColumn.get(requestColumnAssertBis1);
    Column columnIdBis2 = (Column) fieldColumn.get(requestColumnAssertBis2);
    Column columnIdBis3 = (Column) fieldColumn.get(requestColumnAssertBis3);
    Column columnIdBis4 = (Column) fieldColumn.get(requestColumnAssertBis4);

    Assertions.assertThat(columnId0.getName()).isEqualTo(columnIdBis0.getName()).isEqualTo("ID");
    Assertions.assertThat(columnId1.getName()).isEqualTo(columnIdBis1.getName()).isEqualTo("NAME");
    Assertions.assertThat(columnId2.getName()).isEqualTo(columnIdBis2.getName()).isEqualTo("FIRSTNAME");
    Assertions.assertThat(columnId3.getName()).isEqualTo(columnIdBis3.getName()).isEqualTo("BIRTH");
    Assertions.assertThat(columnId4.getName()).isEqualTo(columnIdBis4.getName()).isEqualTo("ACTOR_IMDB");

    Assertions.assertThat(columnId0.getValuesList().get(0).getValue()).isEqualTo(columnIdBis0.getValuesList().get(0).getValue()).isEqualTo(new BigDecimal("1"));
    Assertions.assertThat(columnId0.getValuesList().get(1).getValue()).isEqualTo(columnIdBis0.getValuesList().get(1).getValue()).isEqualTo(new BigDecimal("2"));
    Assertions.assertThat(columnId0.getValuesList().get(2).getValue()).isEqualTo(columnIdBis0.getValuesList().get(2).getValue()).isEqualTo(new BigDecimal("3"));
    Assertions.assertThat(columnId1.getValuesList().get(0).getValue()).isEqualTo(columnIdBis1.getValuesList().get(0).getValue()).isEqualTo("Weaver");
    Assertions.assertThat(columnId1.getValuesList().get(1).getValue()).isEqualTo(columnIdBis1.getValuesList().get(1).getValue()).isEqualTo("Phoenix");
    Assertions.assertThat(columnId1.getValuesList().get(2).getValue()).isEqualTo(columnIdBis1.getValuesList().get(2).getValue()).isEqualTo("Worthington");
    Assertions.assertThat(columnId2.getValuesList().get(0).getValue()).isEqualTo(columnIdBis2.getValuesList().get(0).getValue()).isEqualTo("Sigourney");
    Assertions.assertThat(columnId2.getValuesList().get(1).getValue()).isEqualTo(columnIdBis2.getValuesList().get(1).getValue()).isEqualTo("Joaquim");
    Assertions.assertThat(columnId2.getValuesList().get(2).getValue()).isEqualTo(columnIdBis2.getValuesList().get(2).getValue()).isEqualTo("Sam");
    Assertions.assertThat(columnId3.getValuesList().get(0).getValue()).isEqualTo(columnIdBis3.getValuesList().get(0).getValue()).isEqualTo(Date.valueOf("1949-10-08"));
    Assertions.assertThat(columnId3.getValuesList().get(1).getValue()).isEqualTo(columnIdBis3.getValuesList().get(1).getValue()).isEqualTo(
            Date.valueOf("1974-10-28"));
    Assertions.assertThat(columnId3.getValuesList().get(2).getValue()).isEqualTo(columnIdBis3.getValuesList().get(2).getValue()).isEqualTo(
            Date.valueOf("1976-08-02"));
    Assertions.assertThat(
            columnId4.getValuesList().get(0).getValue()).isEqualTo(columnIdBis4.getValuesList().get(0).getValue()).isEqualTo(
            UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"));
    Assertions.assertThat(columnId4.getValuesList().get(1).getValue()).isEqualTo(columnIdBis4.getValuesList().get(1).getValue()).isEqualTo(
            UUID.fromString("16319617-AE95-4087-9264-D3D21BF611B6"));
    Assertions.assertThat(columnId4.getValuesList().get(2).getValue()).isEqualTo(columnIdBis4.getValuesList().get(2).getValue()).isEqualTo(
            UUID.fromString("D735221B-5DE5-4112-AA1E-49090CB75ADA"));
  }

  /**
   * This method tests the {@code column} navigation method.
   */
  @Test
  @NeedReload
  public void test_column_with_index_from_change_with_displays() throws Exception {
    Changes changes = new Changes(source).setStartPointNow();
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
    ChangeOutputter changeOutputter = changesOutputter.change(6);
    PositionWithColumnsChange position = (PositionWithColumnsChange) fieldPosition.get(changeOutputter);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(0);
    ChangeColumnOutputter changeColumnOutputter0 = changeOutputter.column(0);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(1);
    ChangeColumnOutputter changeColumnOutputter1 = changeOutputter.column(1);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(2);
    ChangeColumnOutputter changeColumnOutputter2 = changeOutputter.column(2);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(3);
    ChangeColumnOutputter changeColumnOutputter3 = changeOutputter.column(3);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(4);
    ChangeColumnOutputter changeColumnOutputter4 = changeOutputter.column(4);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(5);
    try {
      changeOutputter.column(5);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 5 out of the limits [0, 5[");
    }
    try {
      changeOutputter.column(-1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index -1 out of the limits [0, 5[");
    }
    ChangeColumnOutputter changeColumnOutputterAgain0 = changeOutputter.column(0);
    Assertions.assertThat(changeColumnOutputter0).isSameAs(changeColumnOutputterAgain0);

    ChangesOutputter changesOutputterBis = output(changes);
    ChangeOutputter changeOutputterBis = changesOutputterBis.change(6);
    PositionWithColumnsChange positionBis = (PositionWithColumnsChange) fieldPosition.get(changeOutputterBis);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(0);
    ChangeColumnOutputter changeColumnOutputterBis0 = changeOutputterBis.column(0);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(1);
    ChangeColumnOutputter changeColumnOutputterBis1 = changeColumnOutputterBis0.column(1);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(2);
    ChangeColumnOutputter changeColumnOutputterBis2 = changeColumnOutputterBis1.column(2);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(3);
    ChangeColumnOutputter changeColumnOutputterBis3 = changeColumnOutputterBis2.column(3);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(4);
    ChangeColumnOutputter changeColumnOutputterBis4 = changeColumnOutputterBis3.column(4);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(5);
    try {
      changeColumnOutputterBis4.column(5);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 5 out of the limits [0, 5[");
    }
    try {
      changeColumnOutputterBis4.column(-1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index -1 out of the limits [0, 5[");
    }
    ChangeColumnOutputter changeColumnOutputterBisAgain0 = changeColumnOutputterBis4.column(0);
    Assertions.assertThat(changeColumnOutputterBis0).isSameAs(changeColumnOutputterBisAgain0);

    Assertions.assertThat(fieldColumnName.get(changeColumnOutputter0)).isEqualTo(fieldColumnName.get(changeColumnOutputterBis0)).isEqualTo(
            "ID");
    Assertions.assertThat(fieldColumnName.get(changeColumnOutputter1)).isEqualTo(fieldColumnName.get(changeColumnOutputterBis1)).isEqualTo(
            "NAME");
    Assertions.assertThat(fieldColumnName.get(changeColumnOutputter2)).isEqualTo(fieldColumnName.get(changeColumnOutputterBis2)).isEqualTo(
            "FIRSTNAME");
    Assertions.assertThat(fieldColumnName.get(changeColumnOutputter3)).isEqualTo(
            fieldColumnName.get(changeColumnOutputterBis3)).isEqualTo(
            "BIRTH");
    Assertions.assertThat(fieldColumnName.get(changeColumnOutputter4)).isEqualTo(
            fieldColumnName.get(changeColumnOutputterBis4)).isEqualTo(
            "ACTOR_IMDB");

    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnOutputter0)).getValue())
              .isEqualTo(((Value) fieldValueAtStartPoint.get(changeColumnOutputterBis0)).getValue())
              .isEqualTo(new BigDecimal("3"));
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnOutputter1)).getValue())
              .isEqualTo(((Value) fieldValueAtStartPoint.get(changeColumnOutputterBis1)).getValue())
              .isEqualTo("Worthington");
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnOutputter2)).getValue())
              .isEqualTo(((Value) fieldValueAtStartPoint.get(changeColumnOutputterBis2)).getValue())
              .isEqualTo("Sam");
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnOutputter3)).getValue())
              .isEqualTo(((Value) fieldValueAtStartPoint.get(changeColumnOutputterBis3)).getValue())
              .isEqualTo(Date.valueOf("1976-08-02"));
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnOutputter4)).getValue())
              .isEqualTo(((Value) fieldValueAtStartPoint.get(changeColumnOutputterBis4)).getValue())
              .isEqualTo(UUID.fromString("D735221B-5DE5-4112-AA1E-49090CB75ADA"));

    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnOutputter0)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnOutputter1)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnOutputter2)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnOutputter3)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnOutputter4)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnOutputterBis0)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnOutputterBis1)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnOutputterBis2)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnOutputterBis3)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnOutputterBis4)).getValue()).isNull();
  }

  /**
   * This method tests the {@code column} navigation method.
   */
  @Test
  public void test_column_with_index_from_table_with_displays() throws Exception {
    Field fieldPosition = AbstractDbOutputter.class.getDeclaredField("columnPosition");
    fieldPosition.setAccessible(true);
    Field fieldColumn = AbstractColumnOutputter.class.getDeclaredField("column");
    fieldColumn.setAccessible(true);
    Field fieldIndex = Position.class.getDeclaredField("nextIndex");
    fieldIndex.setAccessible(true);

    Table table = new Table(source, "actor");
    TableOutputter tableOutputter = Outputs.output(table);
    Position position = (Position) fieldPosition.get(tableOutputter);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(0);
    TableColumnOutputter tableColumnOutputter0 = tableOutputter.column(0);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(1);
    TableColumnOutputter tableColumnOutputter1 = tableOutputter.column(1);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(2);
    TableColumnOutputter tableColumnOutputter2 = tableOutputter.column(2);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(3);
    TableColumnOutputter tableColumnOutputter3 = tableOutputter.column(3);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(4);
    TableColumnOutputter tableColumnOutputter4 = tableOutputter.column(4);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(5);
    try {
      tableOutputter.column(5);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 5 out of the limits [0, 5[");
    }
    try {
      tableOutputter.column(-1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index -1 out of the limits [0, 5[");
    }
    TableColumnOutputter tableColumnOutputterAgain0 = tableOutputter.column(0);
    Assertions.assertThat(tableColumnOutputter0).isSameAs(tableColumnOutputterAgain0);

    TableOutputter tableOutputterBis = Outputs.output(table);
    Position positionBis = (Position) fieldPosition.get(tableOutputterBis);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(0);
    TableColumnOutputter tableColumnOutputterBis0 = tableOutputterBis.column(0);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(1);
    TableColumnOutputter tableColumnOutputterBis1 = tableColumnOutputterBis0.column(1);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(2);
    TableColumnOutputter tableColumnOutputterBis2 = tableColumnOutputterBis1.column(2);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(3);
    TableColumnOutputter tableColumnOutputterBis3 = tableColumnOutputterBis2.column(3);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(4);
    TableColumnOutputter tableColumnOutputterBis4 = tableColumnOutputterBis3.column(4);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(5);
    try {
      tableColumnOutputterBis4.column(5);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 5 out of the limits [0, 5[");
    }
    try {
      tableColumnOutputterBis4.column(-1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index -1 out of the limits [0, 5[");
    }
    TableColumnOutputter tableColumnOutputterBisAgain0 = tableColumnOutputterBis4.column(0);
    Assertions.assertThat(tableColumnOutputterBis0).isSameAs(tableColumnOutputterBisAgain0);

    Column columnId0 = (Column) fieldColumn.get(tableColumnOutputter0);
    Column columnId1 = (Column) fieldColumn.get(tableColumnOutputter1);
    Column columnId2 = (Column) fieldColumn.get(tableColumnOutputter2);
    Column columnId3 = (Column) fieldColumn.get(tableColumnOutputter3);
    Column columnId4 = (Column) fieldColumn.get(tableColumnOutputter4);
    Column columnIdBis0 = (Column) fieldColumn.get(tableColumnOutputterBis0);
    Column columnIdBis1 = (Column) fieldColumn.get(tableColumnOutputterBis1);
    Column columnIdBis2 = (Column) fieldColumn.get(tableColumnOutputterBis2);
    Column columnIdBis3 = (Column) fieldColumn.get(tableColumnOutputterBis3);
    Column columnIdBis4 = (Column) fieldColumn.get(tableColumnOutputterBis4);

    Assertions.assertThat(columnId0.getName()).isEqualTo(columnIdBis0.getName()).isEqualTo("ID");
    Assertions.assertThat(columnId1.getName()).isEqualTo(columnIdBis1.getName()).isEqualTo("NAME");
    Assertions.assertThat(columnId2.getName()).isEqualTo(columnIdBis2.getName()).isEqualTo("FIRSTNAME");
    Assertions.assertThat(columnId3.getName()).isEqualTo(columnIdBis3.getName()).isEqualTo("BIRTH");
    Assertions.assertThat(columnId4.getName()).isEqualTo(columnIdBis4.getName()).isEqualTo("ACTOR_IMDB");

    Assertions.assertThat(columnId0.getValuesList().get(0).getValue()).isEqualTo(columnIdBis0.getValuesList().get(0).getValue()).isEqualTo(new BigDecimal("1"));
    Assertions.assertThat(columnId0.getValuesList().get(1).getValue()).isEqualTo(columnIdBis0.getValuesList().get(1).getValue()).isEqualTo(new BigDecimal("2"));
    Assertions.assertThat(columnId0.getValuesList().get(2).getValue()).isEqualTo(columnIdBis0.getValuesList().get(2).getValue()).isEqualTo(new BigDecimal("3"));
    Assertions.assertThat(columnId1.getValuesList().get(0).getValue()).isEqualTo(columnIdBis1.getValuesList().get(0).getValue()).isEqualTo("Weaver");
    Assertions.assertThat(columnId1.getValuesList().get(1).getValue()).isEqualTo(columnIdBis1.getValuesList().get(1).getValue()).isEqualTo("Phoenix");
    Assertions.assertThat(columnId1.getValuesList().get(2).getValue()).isEqualTo(columnIdBis1.getValuesList().get(2).getValue()).isEqualTo("Worthington");
    Assertions.assertThat(columnId2.getValuesList().get(0).getValue()).isEqualTo(columnIdBis2.getValuesList().get(0).getValue()).isEqualTo("Sigourney");
    Assertions.assertThat(columnId2.getValuesList().get(1).getValue()).isEqualTo(columnIdBis2.getValuesList().get(1).getValue()).isEqualTo("Joaquim");
    Assertions.assertThat(columnId2.getValuesList().get(2).getValue()).isEqualTo(columnIdBis2.getValuesList().get(2).getValue()).isEqualTo("Sam");
    Assertions.assertThat(columnId3.getValuesList().get(0).getValue()).isEqualTo(columnIdBis3.getValuesList().get(0).getValue()).isEqualTo(Date.valueOf("1949-10-08"));
    Assertions.assertThat(columnId3.getValuesList().get(1).getValue()).isEqualTo(columnIdBis3.getValuesList().get(1).getValue()).isEqualTo(Date.valueOf("1974-10-28"));
    Assertions.assertThat(columnId3.getValuesList().get(2).getValue()).isEqualTo(columnIdBis3.getValuesList().get(2).getValue()).isEqualTo(Date.valueOf("1976-08-02"));
    Assertions.assertThat(columnId4.getValuesList().get(0).getValue()).isEqualTo(columnIdBis4.getValuesList().get(0).getValue()).isEqualTo(UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"));
    Assertions.assertThat(columnId4.getValuesList().get(1).getValue()).isEqualTo(columnIdBis4.getValuesList().get(1).getValue()).isEqualTo(UUID.fromString("16319617-AE95-4087-9264-D3D21BF611B6"));
    Assertions.assertThat(columnId4.getValuesList().get(2).getValue()).isEqualTo(columnIdBis4.getValuesList().get(2).getValue()).isEqualTo(UUID.fromString("D735221B-5DE5-4112-AA1E-49090CB75ADA"));
  }

  /**
   * This method tests the {@code column} navigation method.
   */
  @Test
  public void test_column_with_index_from_request_with_displays() throws Exception {
    Field fieldPosition = AbstractDbOutputter.class.getDeclaredField("columnPosition");
    fieldPosition.setAccessible(true);
    Field fieldColumn = AbstractColumnOutputter.class.getDeclaredField("column");
    fieldColumn.setAccessible(true);
    Field fieldIndex = Position.class.getDeclaredField("nextIndex");
    fieldIndex.setAccessible(true);

    Request request = new Request(source, "select * from actor");
    RequestOutputter requestOutputter = Outputs.output(request);
    Position position = (Position) fieldPosition.get(requestOutputter);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(0);
    RequestColumnOutputter requestColumnOutputter0 = requestOutputter.column(0);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(1);
    RequestColumnOutputter requestColumnOutputter1 = requestOutputter.column(1);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(2);
    RequestColumnOutputter requestColumnOutputter2 = requestOutputter.column(2);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(3);
    RequestColumnOutputter requestColumnOutputter3 = requestOutputter.column(3);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(4);
    RequestColumnOutputter requestColumnOutputter4 = requestOutputter.column(4);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(5);
    try {
      requestOutputter.column(5);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 5 out of the limits [0, 5[");
    }
    try {
      requestOutputter.column(-1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index -1 out of the limits [0, 5[");
    }
    RequestColumnOutputter requestColumnOutputterAgain0 = requestOutputter.column(0);
    Assertions.assertThat(requestColumnOutputter0).isSameAs(requestColumnOutputterAgain0);

    RequestOutputter requestOutputterBis = Outputs.output(request);
    Position positionBis = (Position) fieldPosition.get(requestOutputterBis);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(0);
    RequestColumnOutputter requestColumnOutputterBis0 = requestOutputterBis.column(0);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(1);
    RequestColumnOutputter requestColumnOutputterBis1 = requestColumnOutputterBis0.column(1);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(2);
    RequestColumnOutputter requestColumnOutputterBis2 = requestColumnOutputterBis1.column(2);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(3);
    RequestColumnOutputter requestColumnOutputterBis3 = requestColumnOutputterBis2.column(3);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(4);
    RequestColumnOutputter requestColumnOutputterBis4 = requestColumnOutputterBis3.column(4);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(5);
    try {
      requestColumnOutputterBis4.column(5);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 5 out of the limits [0, 5[");
    }
    try {
      requestColumnOutputterBis4.column(-1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index -1 out of the limits [0, 5[");
    }
    RequestColumnOutputter requestColumnOutputterBisAgain0 = requestColumnOutputterBis4.column(0);
    Assertions.assertThat(requestColumnOutputterBis0).isSameAs(requestColumnOutputterBisAgain0);

    Column columnId0 = (Column) fieldColumn.get(requestColumnOutputter0);
    Column columnId1 = (Column) fieldColumn.get(requestColumnOutputter1);
    Column columnId2 = (Column) fieldColumn.get(requestColumnOutputter2);
    Column columnId3 = (Column) fieldColumn.get(requestColumnOutputter3);
    Column columnId4 = (Column) fieldColumn.get(requestColumnOutputter4);
    Column columnIdBis0 = (Column) fieldColumn.get(requestColumnOutputterBis0);
    Column columnIdBis1 = (Column) fieldColumn.get(requestColumnOutputterBis1);
    Column columnIdBis2 = (Column) fieldColumn.get(requestColumnOutputterBis2);
    Column columnIdBis3 = (Column) fieldColumn.get(requestColumnOutputterBis3);
    Column columnIdBis4 = (Column) fieldColumn.get(requestColumnOutputterBis4);

    Assertions.assertThat(columnId0.getName()).isEqualTo(columnIdBis0.getName()).isEqualTo("ID");
    Assertions.assertThat(columnId1.getName()).isEqualTo(columnIdBis1.getName()).isEqualTo("NAME");
    Assertions.assertThat(columnId2.getName()).isEqualTo(columnIdBis2.getName()).isEqualTo("FIRSTNAME");
    Assertions.assertThat(columnId3.getName()).isEqualTo(columnIdBis3.getName()).isEqualTo("BIRTH");
    Assertions.assertThat(columnId4.getName()).isEqualTo(columnIdBis4.getName()).isEqualTo("ACTOR_IMDB");

    Assertions.assertThat(columnId0.getValuesList().get(0).getValue()).isEqualTo(columnIdBis0.getValuesList().get(0).getValue()).isEqualTo(new BigDecimal("1"));
    Assertions.assertThat(columnId0.getValuesList().get(1).getValue()).isEqualTo(columnIdBis0.getValuesList().get(1).getValue()).isEqualTo(new BigDecimal("2"));
    Assertions.assertThat(columnId0.getValuesList().get(2).getValue()).isEqualTo(columnIdBis0.getValuesList().get(2).getValue()).isEqualTo(new BigDecimal("3"));
    Assertions.assertThat(columnId1.getValuesList().get(0).getValue()).isEqualTo(columnIdBis1.getValuesList().get(0).getValue()).isEqualTo("Weaver");
    Assertions.assertThat(columnId1.getValuesList().get(1).getValue()).isEqualTo(columnIdBis1.getValuesList().get(1).getValue()).isEqualTo("Phoenix");
    Assertions.assertThat(columnId1.getValuesList().get(2).getValue()).isEqualTo(columnIdBis1.getValuesList().get(2).getValue()).isEqualTo("Worthington");
    Assertions.assertThat(columnId2.getValuesList().get(0).getValue()).isEqualTo(columnIdBis2.getValuesList().get(0).getValue()).isEqualTo("Sigourney");
    Assertions.assertThat(columnId2.getValuesList().get(1).getValue()).isEqualTo(columnIdBis2.getValuesList().get(1).getValue()).isEqualTo("Joaquim");
    Assertions.assertThat(columnId2.getValuesList().get(2).getValue()).isEqualTo(columnIdBis2.getValuesList().get(2).getValue()).isEqualTo("Sam");
    Assertions.assertThat(columnId3.getValuesList().get(0).getValue()).isEqualTo(columnIdBis3.getValuesList().get(0).getValue()).isEqualTo(Date.valueOf("1949-10-08"));
    Assertions.assertThat(columnId3.getValuesList().get(1).getValue()).isEqualTo(columnIdBis3.getValuesList().get(1).getValue()).isEqualTo(
            Date.valueOf("1974-10-28"));
    Assertions.assertThat(columnId3.getValuesList().get(2).getValue()).isEqualTo(columnIdBis3.getValuesList().get(2).getValue()).isEqualTo(
            Date.valueOf("1976-08-02"));
    Assertions.assertThat(
            columnId4.getValuesList().get(0).getValue()).isEqualTo(columnIdBis4.getValuesList().get(0).getValue()).isEqualTo(
            UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"));
    Assertions.assertThat(columnId4.getValuesList().get(1).getValue()).isEqualTo(columnIdBis4.getValuesList().get(1).getValue()).isEqualTo(
            UUID.fromString("16319617-AE95-4087-9264-D3D21BF611B6"));
    Assertions.assertThat(columnId4.getValuesList().get(2).getValue()).isEqualTo(columnIdBis4.getValuesList().get(2).getValue()).isEqualTo(
            UUID.fromString("D735221B-5DE5-4112-AA1E-49090CB75ADA"));
  }
}
