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
 * Copyright 2015-2020 the original author or authors.
 */
package org.assertj.db.navigation;

import org.assertj.core.api.Assertions;
import org.assertj.db.api.*;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.assertj.db.exception.AssertJDBException;
import org.assertj.db.output.*;
import org.assertj.db.type.Changes;
import org.assertj.db.type.Request;
import org.assertj.db.type.Table;
import org.assertj.db.type.Value;
import org.junit.Test;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;
import static org.junit.Assert.fail;

/**
 * Tests on {@link org.assertj.db.navigation.ToValue} class :
 * {@link org.assertj.db.navigation.ToValue#value(int)} method.
 *
 * @author Régis Pouiller
 *
 */
public class ToValue_Value_Integer_Test extends AbstractTest {

  /**
   * This method tests the {@code value} with index navigation method.
   */
  @Test
  @NeedReload
  public void test_value_from_row_of_change_with_index_with_assertions() throws Exception {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    Field fieldPosition = ChangeRowAssert.class.getDeclaredField("valuePosition");
    fieldPosition.setAccessible(true);
    Field fieldValue = AbstractAssertWithValues.class.getDeclaredField("value");
    fieldValue.setAccessible(true);
    Field fieldIndex = Position.class.getDeclaredField("nextIndex");
    fieldIndex.setAccessible(true);

    ChangesAssert changesAssert = assertThat(changes);
    ChangeAssert changeAssert = changesAssert.change();
    ChangeRowAssert changeRowAssert = changeAssert.rowAtEndPoint();
    PositionWithColumns<ChangeRowAssert, ChangeRowValueAssert, Value> position = 
              (PositionWithColumns) fieldPosition.get(changeRowAssert);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(0);
    ChangeRowValueAssert changeRowValueAssert0 = changeRowAssert.value(0);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(1);
    ChangeRowValueAssert changeRowValueAssert1 = changeRowAssert.value(1);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(2);
    ChangeRowValueAssert changeRowValueAssert2 = changeRowAssert.value(2);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(3);
    ChangeRowValueAssert changeRowValueAssert3 = changeRowAssert.value(3);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(4);
    ChangeRowValueAssert changeRowValueAssert4 = changeRowAssert.value(4);
    try {
      changeRowAssert.value(6);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 6 out of the limits [0, 5[");
    }
    try {
      changeRowAssert.value(-1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index -1 out of the limits [0, 5[");
    }
    try {
      changeAssert.rowAtStartPoint().value(0);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Row do not exist");
    }
    ChangeRowValueAssert changeRowValueAssertAgain0 = changeRowAssert.value(0);
    Assertions.assertThat(changeRowValueAssert0).isSameAs(changeRowValueAssertAgain0);

    ChangesAssert changesAssertBis = assertThat(changes);
    ChangeAssert changeAssertBis = changesAssertBis.change();
    ChangeRowAssert changeRowAssertBis = changeAssertBis.rowAtEndPoint();
    PositionWithColumns<ChangeRowAssert, ChangeRowValueAssert, Value> positionBis = 
              (PositionWithColumns) fieldPosition.get(changeRowAssertBis);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(0);
    ChangeRowValueAssert changeRowValueAssertBis0 = changeRowAssertBis.value(0);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(1);
    ChangeRowValueAssert changeRowValueAssertBis1 = changeRowValueAssertBis0.value(1);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(2);
    ChangeRowValueAssert changeRowValueAssertBis2 = changeRowValueAssertBis1.value(2);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(3);
    ChangeRowValueAssert changeRowValueAssertBis3 = changeRowValueAssertBis2.value(3);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(4);
    ChangeRowValueAssert changeRowValueAssertBis4 = changeRowValueAssertBis3.value(4);
    try {
      changeRowValueAssertBis4.value(6);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 6 out of the limits [0, 5[");
    }
    try {
      changeRowValueAssertBis4.value(-1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index -1 out of the limits [0, 5[");
    }
    try {
      changeAssertBis.rowAtStartPoint().value(0);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Row do not exist");
    }
    ChangeRowValueAssert changeRowValueAssertBisAgain0 = changeRowValueAssertBis4.value(0);
    Assertions.assertThat(changeRowValueAssertBis0).isSameAs(changeRowValueAssertBisAgain0);

    Assertions.assertThat(((Value) fieldValue.get(changeRowValueAssert0)).getValue())
    .isSameAs(((Value) fieldValue.get(changeRowValueAssertBis0)).getValue())
            .isEqualTo(new BigDecimal("4"));
    Assertions.assertThat(((Value) fieldValue.get(changeRowValueAssert1)).getValue())
              .isSameAs(((Value) fieldValue.get(changeRowValueAssertBis1)).getValue())
              .isEqualTo("Murray");
    Assertions.assertThat(((Value) fieldValue.get(changeRowValueAssert2)).getValue())
              .isSameAs(((Value) fieldValue.get(changeRowValueAssertBis2)).getValue())
              .isEqualTo("Bill");
    Assertions.assertThat(((Value) fieldValue.get(changeRowValueAssert3)).getValue())
              .isSameAs(((Value) fieldValue.get(changeRowValueAssertBis3)).getValue())
              .isEqualTo(Date.valueOf("1950-09-21"));
    Assertions.assertThat(((Value) fieldValue.get(changeRowValueAssert4)).getValue())
              .isSameAs(((Value) fieldValue.get(changeRowValueAssertBis4)).getValue())
              .isEqualTo(UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"));
  }

  /**
   * This method tests the {@code value} with index navigation method.
   */
  @Test
  public void test_value_from_column_of_table_with_index_with_assertions() throws Exception {
    Field fieldPosition = AbstractColumnAssert.class.getDeclaredField("valuePosition");
    fieldPosition.setAccessible(true);
    Field fieldValue = AbstractValueAssert.class.getDeclaredField("value");
    fieldValue.setAccessible(true);
    Field fieldIndex = Position.class.getDeclaredField("nextIndex");
    fieldIndex.setAccessible(true);

    Table table = new Table(source, "actor");
    TableAssert tableAssert = assertThat(table);
    TableColumnAssert tableColumnAssert = tableAssert.column();
    Position<TableColumnAssert, TableColumnValueAssert, Value> position = 
            (Position) fieldPosition.get(tableColumnAssert);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(0);
    TableColumnValueAssert tableColumnValueAssert0 = tableColumnAssert.value(0);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(1);
    TableColumnValueAssert tableColumnValueAssert1 = tableColumnAssert.value(1);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(2);
    TableColumnValueAssert tableColumnValueAssert2 = tableColumnAssert.value(2);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(3);
    try {
      tableColumnAssert.value(5);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 5 out of the limits [0, 3[");
    }
    try {
      tableColumnAssert.value(-1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index -1 out of the limits [0, 3[");
    }
    TableColumnValueAssert tableColumnValueAssertAgain0 = tableColumnAssert.value(0);
    Assertions.assertThat(tableColumnValueAssert0).isSameAs(tableColumnValueAssertAgain0);

    TableAssert tableAssertBis = assertThat(table);
    TableColumnAssert tableColumnAssertBis = tableAssertBis.column();
    Position<TableColumnAssert, TableColumnValueAssert, Value> positionBis = 
            (Position) fieldPosition.get(tableColumnAssertBis);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(0);
    TableColumnValueAssert tableColumnValueAssertBis0 = tableColumnAssertBis.value(0);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(1);
    TableColumnValueAssert tableColumnValueAssertBis1 = tableColumnValueAssertBis0.value(1);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(2);
    TableColumnValueAssert tableColumnValueAssertBis2 = tableColumnValueAssertBis1.value(2);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(3);
    try {
      tableColumnValueAssertBis2.value(5);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 5 out of the limits [0, 3[");
    }
    try {
      tableColumnValueAssertBis2.value(-1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index -1 out of the limits [0, 3[");
    }
    TableColumnValueAssert tableColumnValueAssertBisAgain0 = tableColumnValueAssertBis2.value(0);
    Assertions.assertThat(tableColumnValueAssertBis0).isSameAs(tableColumnValueAssertBisAgain0);

    Assertions.assertThat(((Value) fieldValue.get(tableColumnValueAssert0)).getValue())
    .isSameAs(((Value) fieldValue.get(tableColumnValueAssertBis0)).getValue())
            .isEqualTo(new BigDecimal("1"));
    Assertions.assertThat(((Value) fieldValue.get(tableColumnValueAssert1)).getValue())
              .isSameAs(((Value) fieldValue.get(tableColumnValueAssertBis1)).getValue())
              .isEqualTo(new BigDecimal("2"));
    Assertions.assertThat(((Value) fieldValue.get(tableColumnValueAssert2)).getValue())
    .isSameAs(((Value) fieldValue.get(tableColumnValueAssertBis2)).getValue())
              .isEqualTo(new BigDecimal("3"));
  }

  /**
   * This method tests the {@code value} with index navigation method.
   */
  @Test
  public void test_value_from_row_of_table_with_index_with_assertions() throws Exception {
    Field fieldPosition = AbstractRowAssert.class.getDeclaredField("valuePosition");
    fieldPosition.setAccessible(true);
    Field fieldValue = AbstractValueAssert.class.getDeclaredField("value");
    fieldValue.setAccessible(true);
    Field fieldIndex = Position.class.getDeclaredField("nextIndex");
    fieldIndex.setAccessible(true);

    Table table = new Table(source, "actor");
    TableAssert tableAssert = assertThat(table);
    TableRowAssert tableRowAssert = tableAssert.row();
    Position<TableRowAssert, TableRowValueAssert, Value> position = 
            (Position) fieldPosition.get(tableRowAssert);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(0);
    TableRowValueAssert tableRowValueAssert0 = tableRowAssert.value(0);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(1);
    TableRowValueAssert tableRowValueAssert1 = tableRowAssert.value(1);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(2);
    TableRowValueAssert tableRowValueAssert2 = tableRowAssert.value(2);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(3);
    TableRowValueAssert tableRowValueAssert3 = tableRowAssert.value(3);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(4);
    TableRowValueAssert tableRowValueAssert4 = tableRowAssert.value(4);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(5);
    try {
      tableRowAssert.value(7);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 7 out of the limits [0, 5[");
    }
    try {
      tableRowAssert.value(-1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index -1 out of the limits [0, 5[");
    }
    TableRowValueAssert tableRowValueAssertAgain0 = tableRowAssert.value(0);
    Assertions.assertThat(tableRowValueAssert0).isSameAs(tableRowValueAssertAgain0);

    TableAssert tableAssertBis = assertThat(table);
    TableRowAssert tableRowAssertBis = tableAssertBis.row();
    Position<TableRowAssert, TableRowValueAssert, Value> positionBis = 
            (Position) fieldPosition.get(tableRowAssertBis);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(0);
    TableRowValueAssert tableRowValueAssertBis0 = tableRowAssertBis.value(0);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(1);
    TableRowValueAssert tableRowValueAssertBis1 = tableRowValueAssertBis0.value(1);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(2);
    TableRowValueAssert tableRowValueAssertBis2 = tableRowValueAssertBis1.value(2);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(3);
    TableRowValueAssert tableRowValueAssertBis3 = tableRowValueAssertBis2.value(3);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(4);
    TableRowValueAssert tableRowValueAssertBis4 = tableRowValueAssertBis3.value(4);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(5);
    try {
      tableRowValueAssertBis4.value(7);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 7 out of the limits [0, 5[");
    }
    try {
      tableRowValueAssertBis4.value(-1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index -1 out of the limits [0, 5[");
    }
    TableRowValueAssert tableRowValueAssertBisAgain0 = tableRowValueAssertBis4.value(0);
    Assertions.assertThat(tableRowValueAssertBis0).isSameAs(tableRowValueAssertBisAgain0);

    Assertions.assertThat(((Value) fieldValue.get(tableRowValueAssert0)).getValue())
    .isSameAs(((Value) fieldValue.get(tableRowValueAssertBis0)).getValue())
            .isEqualTo(new BigDecimal("1"));
    Assertions.assertThat(((Value) fieldValue.get(tableRowValueAssert1)).getValue())
              .isSameAs(((Value) fieldValue.get(tableRowValueAssertBis1)).getValue())
              .isEqualTo("Weaver");
    Assertions.assertThat(((Value) fieldValue.get(tableRowValueAssert2)).getValue())
              .isSameAs(((Value) fieldValue.get(tableRowValueAssertBis2)).getValue())
              .isEqualTo("Sigourney");
    Assertions.assertThat(((Value) fieldValue.get(tableRowValueAssert3)).getValue())
              .isSameAs(((Value) fieldValue.get(tableRowValueAssertBis3)).getValue())
              .isEqualTo(Date.valueOf("1949-10-08"));
    Assertions.assertThat(((Value) fieldValue.get(tableRowValueAssert4)).getValue())
              .isSameAs(((Value) fieldValue.get(tableRowValueAssertBis4)).getValue())
              .isEqualTo(UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"));
  }

  /**
   * This method tests the {@code value} with index navigation method.
   */
  @Test
  public void test_value_from_column_of_request_with_index_with_assertions() throws Exception {
    Field fieldPosition = AbstractColumnAssert.class.getDeclaredField("valuePosition");
    fieldPosition.setAccessible(true);
    Field fieldValue = AbstractValueAssert.class.getDeclaredField("value");
    fieldValue.setAccessible(true);
    Field fieldIndex = Position.class.getDeclaredField("nextIndex");
    fieldIndex.setAccessible(true);

    Request request = new Request(source, "select * from actor");
    RequestAssert requestAssert = assertThat(request);
    RequestColumnAssert requestColumnAssert = requestAssert.column();
    Position position = (Position) fieldPosition.get(requestColumnAssert);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(0);
    RequestColumnValueAssert requestColumnValueAssert0 = requestColumnAssert.value(0);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(1);
    RequestColumnValueAssert requestColumnValueAssert1 = requestColumnAssert.value(1);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(2);
    RequestColumnValueAssert requestColumnValueAssert2 = requestColumnAssert.value(2);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(3);
    try {
      requestColumnAssert.value(8);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 8 out of the limits [0, 3[");
    }
    try {
      requestColumnAssert.value(-1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index -1 out of the limits [0, 3[");
    }
    RequestColumnValueAssert requestColumnValueAssertAgain0 = requestColumnAssert.value(0);
    Assertions.assertThat(requestColumnValueAssert0).isSameAs(requestColumnValueAssertAgain0);

    RequestAssert requestAssertBis = assertThat(request);
    RequestColumnAssert requestColumnAssertBis = requestAssertBis.column();
    Position positionBis = (Position) fieldPosition.get(requestColumnAssertBis);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(0);
    RequestColumnValueAssert requestColumnValueAssertBis0 = requestColumnAssertBis.value(0);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(1);
    RequestColumnValueAssert requestColumnValueAssertBis1 = requestColumnValueAssertBis0.value(1);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(2);
    RequestColumnValueAssert requestColumnValueAssertBis2 = requestColumnValueAssertBis1.value(2);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(3);
    try {
      requestColumnValueAssertBis2.value(8);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 8 out of the limits [0, 3[");
    }
    try {
      requestColumnValueAssertBis2.value(-1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index -1 out of the limits [0, 3[");
    }
    RequestColumnValueAssert requestColumnValueAssertBisAgain0 = requestColumnValueAssertBis2.value(0);
    Assertions.assertThat(requestColumnValueAssertBis0).isSameAs(requestColumnValueAssertBisAgain0);

    Assertions.assertThat(((Value) fieldValue.get(requestColumnValueAssert0)).getValue())
    .isSameAs(((Value) fieldValue.get(requestColumnValueAssertBis0)).getValue())
            .isEqualTo(new BigDecimal("1"));
    Assertions.assertThat(((Value) fieldValue.get(requestColumnValueAssert1)).getValue())
              .isSameAs(((Value) fieldValue.get(requestColumnValueAssertBis1)).getValue())
              .isEqualTo(new BigDecimal("2"));
    Assertions.assertThat(((Value) fieldValue.get(requestColumnValueAssert2)).getValue())
              .isSameAs(((Value) fieldValue.get(requestColumnValueAssertBis2)).getValue())
              .isEqualTo(new BigDecimal("3"));
  }

  /**
   * This method tests the {@code value} with index navigation method.
   */
  @Test
  public void test_value_from_row_of_request_with_index_with_assertions() throws Exception {
    Field fieldPosition = AbstractRowAssert.class.getDeclaredField("valuePosition");
    fieldPosition.setAccessible(true);
    Field fieldValue = AbstractValueAssert.class.getDeclaredField("value");
    fieldValue.setAccessible(true);
    Field fieldIndex = Position.class.getDeclaredField("nextIndex");
    fieldIndex.setAccessible(true);

    Request request = new Request(source, "select * from actor");
    RequestAssert requestAssert = assertThat(request);
    RequestRowAssert requestRowAssert = requestAssert.row();
    Position position = (Position) fieldPosition.get(requestRowAssert);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(0);
    RequestRowValueAssert requestRowValueAssert0 = requestRowAssert.value(0);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(1);
    RequestRowValueAssert requestRowValueAssert1 = requestRowAssert.value(1);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(2);
    RequestRowValueAssert requestRowValueAssert2 = requestRowAssert.value(2);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(3);
    RequestRowValueAssert requestRowValueAssert3 = requestRowAssert.value(3);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(4);
    RequestRowValueAssert requestRowValueAssert4 = requestRowAssert.value(4);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(5);
    try {
      requestRowAssert.value(9);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 9 out of the limits [0, 5[");
    }
    try {
      requestRowAssert.value(-1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index -1 out of the limits [0, 5[");
    }
    RequestRowValueAssert requestRowValueAssertAgain0 = requestRowAssert.value(0);
    Assertions.assertThat(requestRowValueAssert0).isSameAs(requestRowValueAssertAgain0);

    RequestAssert requestAssertBis = assertThat(request);
    RequestRowAssert requestRowAssertBis = requestAssertBis.row();
    Position positionBis = (Position) fieldPosition.get(requestRowAssertBis);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(0);
    RequestRowValueAssert requestRowValueAssertBis0 = requestRowAssertBis.value(0);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(1);
    RequestRowValueAssert requestRowValueAssertBis1 = requestRowValueAssertBis0.value(1);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(2);
    RequestRowValueAssert requestRowValueAssertBis2 = requestRowValueAssertBis1.value(2);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(3);
    RequestRowValueAssert requestRowValueAssertBis3 = requestRowValueAssertBis2.value(3);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(4);
    RequestRowValueAssert requestRowValueAssertBis4 = requestRowValueAssertBis3.value(4);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(5);
    try {
      requestRowValueAssertBis4.value(9);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 9 out of the limits [0, 5[");
    }
    try {
      requestRowValueAssertBis4.value(-1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index -1 out of the limits [0, 5[");
    }
    RequestRowValueAssert requestRowValueAssertBisAgain0 = requestRowValueAssertBis4.value(0);
    Assertions.assertThat(requestRowValueAssertBis0).isSameAs(requestRowValueAssertBisAgain0);

    Assertions.assertThat(((Value) fieldValue.get(requestRowValueAssert0)).getValue())
    .isSameAs(((Value) fieldValue.get(requestRowValueAssertBis0)).getValue())
            .isEqualTo(new BigDecimal("1"));
    Assertions.assertThat(((Value) fieldValue.get(requestRowValueAssert1)).getValue())
              .isSameAs(((Value) fieldValue.get(requestRowValueAssertBis1)).getValue())
              .isEqualTo("Weaver");
    Assertions.assertThat(((Value) fieldValue.get(requestRowValueAssert2)).getValue())
              .isSameAs(((Value) fieldValue.get(requestRowValueAssertBis2)).getValue())
              .isEqualTo("Sigourney");
    Assertions.assertThat(((Value) fieldValue.get(requestRowValueAssert3)).getValue())
              .isSameAs(((Value) fieldValue.get(requestRowValueAssertBis3)).getValue())
              .isEqualTo(Date.valueOf("1949-10-08"));
    Assertions.assertThat(((Value) fieldValue.get(requestRowValueAssert4)).getValue())
              .isSameAs(((Value) fieldValue.get(requestRowValueAssertBis4)).getValue())
              .isEqualTo(UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"));
  }

  /**
   * This method tests the {@code value} with index navigation method.
   */
  @Test
  @NeedReload
  public void test_value_from_row_of_change_with_index_with_displays() throws Exception {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    Field fieldPosition = ChangeRowOutputter.class.getDeclaredField("valuePosition");
    fieldPosition.setAccessible(true);
    Field fieldValue = AbstractOutputterWithValues.class.getDeclaredField("value");
    fieldValue.setAccessible(true);
    Field fieldIndex = Position.class.getDeclaredField("nextIndex");
    fieldIndex.setAccessible(true);

    ChangesOutputter changesOutputter = output(changes);
    ChangeOutputter changeOutputter = changesOutputter.change();
    ChangeRowOutputter changeRowOutputter = changeOutputter.rowAtEndPoint();
    Position position = (Position) fieldPosition.get(changeRowOutputter);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(0);
    ChangeRowValueOutputter changeRowValueOutputter0 = changeRowOutputter.value(0);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(1);
    ChangeRowValueOutputter changeRowValueOutputter1 = changeRowOutputter.value(1);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(2);
    ChangeRowValueOutputter changeRowValueOutputter2 = changeRowOutputter.value(2);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(3);
    ChangeRowValueOutputter changeRowValueOutputter3 = changeRowOutputter.value(3);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(4);
    ChangeRowValueOutputter changeRowValueOutputter4 = changeRowOutputter.value(4);
    try {
      changeRowOutputter.value(6);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 6 out of the limits [0, 5[");
    }
    try {
      changeRowOutputter.value(-1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index -1 out of the limits [0, 5[");
    }
    try {
      changeOutputter.rowAtStartPoint().value(0);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Row do not exist");
    }
    ChangeRowValueOutputter changeRowValueOutputterAgain0 = changeRowOutputter.value(0);
    Assertions.assertThat(changeRowValueOutputter0).isSameAs(changeRowValueOutputterAgain0);

    ChangesOutputter changesOutputterBis = output(changes);
    ChangeOutputter changeOutputterBis = changesOutputterBis.change();
    ChangeRowOutputter changeRowOutputterBis = changeOutputterBis.rowAtEndPoint();
    Position positionBis = (Position) fieldPosition.get(changeRowOutputterBis);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(0);
    ChangeRowValueOutputter changeRowValueOutputterBis0 = changeRowOutputterBis.value(0);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(1);
    ChangeRowValueOutputter changeRowValueOutputterBis1 = changeRowValueOutputterBis0.value(1);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(2);
    ChangeRowValueOutputter changeRowValueOutputterBis2 = changeRowValueOutputterBis1.value(2);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(3);
    ChangeRowValueOutputter changeRowValueOutputterBis3 = changeRowValueOutputterBis2.value(3);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(4);
    ChangeRowValueOutputter changeRowValueOutputterBis4 = changeRowValueOutputterBis3.value(4);
    try {
      changeRowValueOutputterBis4.value(6);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 6 out of the limits [0, 5[");
    }
    try {
      changeRowValueOutputterBis4.value(-1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index -1 out of the limits [0, 5[");
    }
    try {
      changeOutputterBis.rowAtStartPoint().value(0);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Row do not exist");
    }
    ChangeRowValueOutputter changeRowValueOutputterBisAgain0 = changeRowValueOutputterBis4.value(0);
    Assertions.assertThat(changeRowValueOutputterBis0).isSameAs(changeRowValueOutputterBisAgain0);

    Assertions.assertThat(((Value) fieldValue.get(changeRowValueOutputter0)).getValue())
              .isSameAs(((Value) fieldValue.get(changeRowValueOutputterBis0)).getValue())
              .isEqualTo(new BigDecimal("4"));
    Assertions.assertThat(((Value) fieldValue.get(changeRowValueOutputter1)).getValue())
              .isSameAs(((Value) fieldValue.get(changeRowValueOutputterBis1)).getValue())
              .isEqualTo("Murray");
    Assertions.assertThat(((Value) fieldValue.get(changeRowValueOutputter2)).getValue())
              .isSameAs(((Value) fieldValue.get(changeRowValueOutputterBis2)).getValue())
              .isEqualTo("Bill");
    Assertions.assertThat(((Value) fieldValue.get(changeRowValueOutputter3)).getValue())
              .isSameAs(((Value) fieldValue.get(changeRowValueOutputterBis3)).getValue())
              .isEqualTo(Date.valueOf("1950-09-21"));
    Assertions.assertThat(((Value) fieldValue.get(changeRowValueOutputter4)).getValue())
              .isSameAs(((Value) fieldValue.get(changeRowValueOutputterBis4)).getValue())
              .isEqualTo(UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"));
  }

  /**
   * This method tests the {@code value} with index navigation method.
   */
  @Test
  public void test_value_from_column_of_table_with_index_with_displays() throws Exception {
    Field fieldPosition = AbstractColumnOutputter.class.getDeclaredField("valuePosition");
    fieldPosition.setAccessible(true);
    Field fieldValue = AbstractValueOutputter.class.getDeclaredField("value");
    fieldValue.setAccessible(true);
    Field fieldIndex = Position.class.getDeclaredField("nextIndex");
    fieldIndex.setAccessible(true);

    Table table = new Table(source, "actor");
    TableOutputter tableOutputter = Outputs.output(table);
    TableColumnOutputter tableColumnOutputter = tableOutputter.column();
    Position position = (Position) fieldPosition.get(tableColumnOutputter);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(0);
    TableColumnValueOutputter tableColumnValueOutputter0 = tableColumnOutputter.value(0);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(1);
    TableColumnValueOutputter tableColumnValueOutputter1 = tableColumnOutputter.value(1);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(2);
    TableColumnValueOutputter tableColumnValueOutputter2 = tableColumnOutputter.value(2);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(3);
    try {
      tableColumnOutputter.value(5);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 5 out of the limits [0, 3[");
    }
    try {
      tableColumnOutputter.value(-1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index -1 out of the limits [0, 3[");
    }
    TableColumnValueOutputter tableColumnValueOutputterAgain0 = tableColumnOutputter.value(0);
    Assertions.assertThat(tableColumnValueOutputter0).isSameAs(tableColumnValueOutputterAgain0);

    TableOutputter tableOutputterBis = Outputs.output(table);
    TableColumnOutputter tableColumnOutputterBis = tableOutputterBis.column();
    Position positionBis = (Position) fieldPosition.get(tableColumnOutputterBis);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(0);
    TableColumnValueOutputter tableColumnValueOutputterBis0 = tableColumnOutputterBis.value(0);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(1);
    TableColumnValueOutputter tableColumnValueOutputterBis1 = tableColumnValueOutputterBis0.value(1);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(2);
    TableColumnValueOutputter tableColumnValueOutputterBis2 = tableColumnValueOutputterBis1.value(2);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(3);
    try {
      tableColumnValueOutputterBis2.value(5);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 5 out of the limits [0, 3[");
    }
    try {
      tableColumnValueOutputterBis2.value(-1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index -1 out of the limits [0, 3[");
    }
    TableColumnValueOutputter tableColumnValueOutputterBisAgain0 = tableColumnValueOutputterBis2.value(0);
    Assertions.assertThat(tableColumnValueOutputterBis0).isSameAs(tableColumnValueOutputterBisAgain0);

    Assertions.assertThat(((Value) fieldValue.get(tableColumnValueOutputter0)).getValue())
              .isSameAs(((Value) fieldValue.get(tableColumnValueOutputterBis0)).getValue())
              .isEqualTo(new BigDecimal("1"));
    Assertions.assertThat(((Value) fieldValue.get(tableColumnValueOutputter1)).getValue())
              .isSameAs(((Value) fieldValue.get(tableColumnValueOutputterBis1)).getValue())
              .isEqualTo(new BigDecimal("2"));
    Assertions.assertThat(((Value) fieldValue.get(tableColumnValueOutputter2)).getValue())
              .isSameAs(((Value) fieldValue.get(tableColumnValueOutputterBis2)).getValue())
              .isEqualTo(new BigDecimal("3"));
  }

  /**
   * This method tests the {@code value} with index navigation method.
   */
  @Test
  public void test_value_from_row_of_table_with_index_with_displays() throws Exception {
    Field fieldPosition = AbstractRowOutputter.class.getDeclaredField("valuePosition");
    fieldPosition.setAccessible(true);
    Field fieldValue = AbstractValueOutputter.class.getDeclaredField("value");
    fieldValue.setAccessible(true);
    Field fieldIndex = Position.class.getDeclaredField("nextIndex");
    fieldIndex.setAccessible(true);

    Table table = new Table(source, "actor");
    TableOutputter tableOutputter = Outputs.output(table);
    TableRowOutputter tableRowOutputter = tableOutputter.row();
    Position position = (Position) fieldPosition.get(tableRowOutputter);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(0);
    TableRowValueOutputter tableRowValueOutputter0 = tableRowOutputter.value(0);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(1);
    TableRowValueOutputter tableRowValueOutputter1 = tableRowOutputter.value(1);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(2);
    TableRowValueOutputter tableRowValueOutputter2 = tableRowOutputter.value(2);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(3);
    TableRowValueOutputter tableRowValueOutputter3 = tableRowOutputter.value(3);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(4);
    TableRowValueOutputter tableRowValueOutputter4 = tableRowOutputter.value(4);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(5);
    try {
      tableRowOutputter.value(7);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 7 out of the limits [0, 5[");
    }
    try {
      tableRowOutputter.value(-1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index -1 out of the limits [0, 5[");
    }
    TableRowValueOutputter tableRowValueOutputterAgain0 = tableRowOutputter.value(0);
    Assertions.assertThat(tableRowValueOutputter0).isSameAs(tableRowValueOutputterAgain0);

    TableOutputter tableOutputterBis = Outputs.output(table);
    TableRowOutputter tableRowOutputterBis = tableOutputterBis.row();
    Position positionBis = (Position) fieldPosition.get(tableRowOutputterBis);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(0);
    TableRowValueOutputter tableRowValueOutputterBis0 = tableRowOutputterBis.value(0);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(1);
    TableRowValueOutputter tableRowValueOutputterBis1 = tableRowValueOutputterBis0.value(1);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(2);
    TableRowValueOutputter tableRowValueOutputterBis2 = tableRowValueOutputterBis1.value(2);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(3);
    TableRowValueOutputter tableRowValueOutputterBis3 = tableRowValueOutputterBis2.value(3);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(4);
    TableRowValueOutputter tableRowValueOutputterBis4 = tableRowValueOutputterBis3.value(4);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(5);
    try {
      tableRowValueOutputterBis4.value(7);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 7 out of the limits [0, 5[");
    }
    try {
      tableRowValueOutputterBis4.value(-1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index -1 out of the limits [0, 5[");
    }
    TableRowValueOutputter tableRowValueOutputterBisAgain0 = tableRowValueOutputterBis4.value(0);
    Assertions.assertThat(tableRowValueOutputterBis0).isSameAs(tableRowValueOutputterBisAgain0);

    Assertions.assertThat(((Value) fieldValue.get(tableRowValueOutputter0)).getValue())
              .isSameAs(((Value) fieldValue.get(tableRowValueOutputterBis0)).getValue())
              .isEqualTo(new BigDecimal("1"));
    Assertions.assertThat(((Value) fieldValue.get(tableRowValueOutputter1)).getValue())
              .isSameAs(((Value) fieldValue.get(tableRowValueOutputterBis1)).getValue())
              .isEqualTo("Weaver");
    Assertions.assertThat(((Value) fieldValue.get(tableRowValueOutputter2)).getValue())
              .isSameAs(((Value) fieldValue.get(tableRowValueOutputterBis2)).getValue())
              .isEqualTo("Sigourney");
    Assertions.assertThat(((Value) fieldValue.get(tableRowValueOutputter3)).getValue())
              .isSameAs(((Value) fieldValue.get(tableRowValueOutputterBis3)).getValue())
              .isEqualTo(Date.valueOf("1949-10-08"));
    Assertions.assertThat(((Value) fieldValue.get(tableRowValueOutputter4)).getValue())
              .isSameAs(((Value) fieldValue.get(tableRowValueOutputterBis4)).getValue())
              .isEqualTo(UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"));
  }

  /**
   * This method tests the {@code value} with index navigation method.
   */
  @Test
  public void test_value_from_column_of_request_with_index_with_displays() throws Exception {
    Field fieldPosition = AbstractColumnOutputter.class.getDeclaredField("valuePosition");
    fieldPosition.setAccessible(true);
    Field fieldValue = AbstractValueOutputter.class.getDeclaredField("value");
    fieldValue.setAccessible(true);
    Field fieldIndex = Position.class.getDeclaredField("nextIndex");
    fieldIndex.setAccessible(true);

    Request request = new Request(source, "select * from actor");
    RequestOutputter requestOutputter = Outputs.output(request);
    RequestColumnOutputter requestColumnOutputter = requestOutputter.column();
    Position position = (Position) fieldPosition.get(requestColumnOutputter);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(0);
    RequestColumnValueOutputter requestColumnValueOutputter0 = requestColumnOutputter.value(0);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(1);
    RequestColumnValueOutputter requestColumnValueOutputter1 = requestColumnOutputter.value(1);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(2);
    RequestColumnValueOutputter requestColumnValueOutputter2 = requestColumnOutputter.value(2);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(3);
    try {
      requestColumnOutputter.value(8);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 8 out of the limits [0, 3[");
    }
    try {
      requestColumnOutputter.value(-1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index -1 out of the limits [0, 3[");
    }
    RequestColumnValueOutputter requestColumnValueOutputterAgain0 = requestColumnOutputter.value(0);
    Assertions.assertThat(requestColumnValueOutputter0).isSameAs(requestColumnValueOutputterAgain0);

    RequestOutputter requestOutputterBis = Outputs.output(request);
    RequestColumnOutputter requestColumnOutputterBis = requestOutputterBis.column();
    Position positionBis = (Position) fieldPosition.get(requestColumnOutputterBis);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(0);
    RequestColumnValueOutputter requestColumnValueOutputterBis0 = requestColumnOutputterBis.value(0);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(1);
    RequestColumnValueOutputter requestColumnValueOutputterBis1 = requestColumnValueOutputterBis0.value(1);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(2);
    RequestColumnValueOutputter requestColumnValueOutputterBis2 = requestColumnValueOutputterBis1.value(2);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(3);
    try {
      requestColumnValueOutputterBis2.value(8);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 8 out of the limits [0, 3[");
    }
    try {
      requestColumnValueOutputterBis2.value(-1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index -1 out of the limits [0, 3[");
    }
    RequestColumnValueOutputter requestColumnValueOutputterBisAgain0 = requestColumnValueOutputterBis2.value(0);
    Assertions.assertThat(requestColumnValueOutputterBis0).isSameAs(requestColumnValueOutputterBisAgain0);

    Assertions.assertThat(((Value) fieldValue.get(requestColumnValueOutputter0)).getValue())
              .isSameAs(((Value) fieldValue.get(requestColumnValueOutputterBis0)).getValue())
              .isEqualTo(new BigDecimal("1"));
    Assertions.assertThat(((Value) fieldValue.get(requestColumnValueOutputter1)).getValue())
              .isSameAs(((Value) fieldValue.get(requestColumnValueOutputterBis1)).getValue())
              .isEqualTo(new BigDecimal("2"));
    Assertions.assertThat(((Value) fieldValue.get(requestColumnValueOutputter2)).getValue())
              .isSameAs(((Value) fieldValue.get(requestColumnValueOutputterBis2)).getValue())
              .isEqualTo(new BigDecimal("3"));
  }

  /**
   * This method tests the {@code value} with index navigation method.
   */
  @Test
  public void test_value_from_row_of_request_with_index_with_displays() throws Exception {
    Field fieldPosition = AbstractRowOutputter.class.getDeclaredField("valuePosition");
    fieldPosition.setAccessible(true);
    Field fieldValue = AbstractValueOutputter.class.getDeclaredField("value");
    fieldValue.setAccessible(true);
    Field fieldIndex = Position.class.getDeclaredField("nextIndex");
    fieldIndex.setAccessible(true);

    Request request = new Request(source, "select * from actor");
    RequestOutputter requestOutputter = Outputs.output(request);
    RequestRowOutputter requestRowOutputter = requestOutputter.row();
    Position position = (Position) fieldPosition.get(requestRowOutputter);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(0);
    RequestRowValueOutputter requestRowValueOutputter0 = requestRowOutputter.value(0);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(1);
    RequestRowValueOutputter requestRowValueOutputter1 = requestRowOutputter.value(1);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(2);
    RequestRowValueOutputter requestRowValueOutputter2 = requestRowOutputter.value(2);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(3);
    RequestRowValueOutputter requestRowValueOutputter3 = requestRowOutputter.value(3);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(4);
    RequestRowValueOutputter requestRowValueOutputter4 = requestRowOutputter.value(4);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(5);
    try {
      requestRowOutputter.value(9);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 9 out of the limits [0, 5[");
    }
    try {
      requestRowOutputter.value(-1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index -1 out of the limits [0, 5[");
    }
    RequestRowValueOutputter requestRowValueOutputterAgain0 = requestRowOutputter.value(0);
    Assertions.assertThat(requestRowValueOutputter0).isSameAs(requestRowValueOutputterAgain0);

    RequestOutputter requestOutputterBis = Outputs.output(request);
    RequestRowOutputter requestRowOutputterBis = requestOutputterBis.row();
    Position positionBis = (Position) fieldPosition.get(requestRowOutputterBis);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(0);
    RequestRowValueOutputter requestRowValueOutputterBis0 = requestRowOutputterBis.value(0);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(1);
    RequestRowValueOutputter requestRowValueOutputterBis1 = requestRowValueOutputterBis0.value(1);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(2);
    RequestRowValueOutputter requestRowValueOutputterBis2 = requestRowValueOutputterBis1.value(2);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(3);
    RequestRowValueOutputter requestRowValueOutputterBis3 = requestRowValueOutputterBis2.value(3);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(4);
    RequestRowValueOutputter requestRowValueOutputterBis4 = requestRowValueOutputterBis3.value(4);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(5);
    try {
      requestRowValueOutputterBis4.value(9);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 9 out of the limits [0, 5[");
    }
    try {
      requestRowValueOutputterBis4.value(-1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index -1 out of the limits [0, 5[");
    }
    RequestRowValueOutputter requestRowValueOutputterBisAgain0 = requestRowValueOutputterBis4.value(0);
    Assertions.assertThat(requestRowValueOutputterBis0).isSameAs(requestRowValueOutputterBisAgain0);

    Assertions.assertThat(((Value) fieldValue.get(requestRowValueOutputter0)).getValue())
              .isSameAs(((Value) fieldValue.get(requestRowValueOutputterBis0)).getValue())
              .isEqualTo(new BigDecimal("1"));
    Assertions.assertThat(((Value) fieldValue.get(requestRowValueOutputter1)).getValue())
              .isSameAs(((Value) fieldValue.get(requestRowValueOutputterBis1)).getValue())
              .isEqualTo("Weaver");
    Assertions.assertThat(((Value) fieldValue.get(requestRowValueOutputter2)).getValue())
              .isSameAs(((Value) fieldValue.get(requestRowValueOutputterBis2)).getValue())
              .isEqualTo("Sigourney");
    Assertions.assertThat(((Value) fieldValue.get(requestRowValueOutputter3)).getValue())
              .isSameAs(((Value) fieldValue.get(requestRowValueOutputterBis3)).getValue())
              .isEqualTo(Date.valueOf("1949-10-08"));
    Assertions.assertThat(((Value) fieldValue.get(requestRowValueOutputter4)).getValue())
              .isSameAs(((Value) fieldValue.get(requestRowValueOutputterBis4)).getValue())
              .isEqualTo(UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"));
  }
}
