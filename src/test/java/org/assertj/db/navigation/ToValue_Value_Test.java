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
import static org.assertj.db.output.Outputs.display;
import static org.junit.Assert.fail;

/**
 * Tests on {@link org.assertj.db.navigation.ToValue} class :
 * {@link org.assertj.db.navigation.ToValue#value()} method.
 *
 * @author Régis Pouiller
 *
 */
public class ToValue_Value_Test extends AbstractTest {

  /**
   * This method tests the {@code value} navigation method.
   */
  @Test
  @NeedReload
  public void test_value_from_row_of_change_with_assertions() throws Exception {
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
    Position position = (Position) fieldPosition.get(changeRowAssert);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(0);
    ChangeRowValueAssert changeRowValueAssert0 = changeRowAssert.value();
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(1);
    ChangeRowValueAssert changeRowValueAssert1 = changeRowAssert.value();
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(2);
    ChangeRowValueAssert changeRowValueAssert2 = changeRowAssert.value();
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(3);
    ChangeRowValueAssert changeRowValueAssert3 = changeRowAssert.value();
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(4);
    ChangeRowValueAssert changeRowValueAssert4 = changeRowAssert.value();
    try {
      changeRowAssert.value();
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 5 out of the limits [0, 5[");
    }
    try {
      changeAssert.rowAtStartPoint().value();
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Row do not exist");
    }

    ChangesAssert changesAssertBis = assertThat(changes);
    ChangeAssert changeAssertBis = changesAssertBis.change();
    ChangeRowAssert changeRowAssertBis = changeAssertBis.rowAtEndPoint();
    Position positionBis = (Position) fieldPosition.get(changeRowAssertBis);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(0);
    ChangeRowValueAssert changeRowValueAssertBis0 = changeRowAssertBis.value();
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(1);
    ChangeRowValueAssert changeRowValueAssertBis1 = changeRowValueAssertBis0.value();
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(2);
    ChangeRowValueAssert changeRowValueAssertBis2 = changeRowValueAssertBis1.value();
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(3);
    ChangeRowValueAssert changeRowValueAssertBis3 = changeRowValueAssertBis2.value();
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(4);
    ChangeRowValueAssert changeRowValueAssertBis4 = changeRowValueAssertBis3.value();
    try {
      changeRowValueAssertBis4.value();
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 5 out of the limits [0, 5[");
    }
    try {
      changeAssertBis.rowAtStartPoint().value();
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Row do not exist");
    }

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
   * This method tests the {@code value} navigation method.
   */
  @Test
  public void test_value_from_column_of_table_with_assertions() throws Exception {
    Field fieldPosition = AbstractColumnAssert.class.getDeclaredField("valuePosition");
    fieldPosition.setAccessible(true);
    Field fieldValue = AbstractValueAssert.class.getDeclaredField("value");
    fieldValue.setAccessible(true);
    Field fieldIndex = Position.class.getDeclaredField("nextIndex");
    fieldIndex.setAccessible(true);

    Table table = new Table(source, "actor");
    TableAssert tableAssert = assertThat(table);
    TableColumnAssert tableColumnAssert = tableAssert.column();
    Position position = (Position) fieldPosition.get(tableColumnAssert);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(0);
    TableColumnValueAssert tableColumnValueAssert0 = tableColumnAssert.value();
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(1);
    TableColumnValueAssert tableColumnValueAssert1 = tableColumnAssert.value();
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(2);
    TableColumnValueAssert tableColumnValueAssert2 = tableColumnAssert.value();
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(3);
    try {
      tableColumnAssert.value();
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 3 out of the limits [0, 3[");
    }

    TableAssert tableAssertBis = assertThat(table);
    TableColumnAssert tableColumnAssertBis = tableAssertBis.column();
    Position positionBis = (Position) fieldPosition.get(tableColumnAssertBis);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(0);
    TableColumnValueAssert tableColumnValueAssertBis0 = tableColumnAssertBis.value();
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(1);
    TableColumnValueAssert tableColumnValueAssertBis1 = tableColumnValueAssertBis0.value();
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(2);
    TableColumnValueAssert tableColumnValueAssertBis2 = tableColumnValueAssertBis1.value();
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(3);
    try {
      tableColumnValueAssertBis2.value();
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 3 out of the limits [0, 3[");
    }

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
   * This method tests the {@code value} navigation method.
   */
  @Test
  public void test_value_from_row_of_table_with_assertions() throws Exception {
    Field fieldPosition = AbstractRowAssert.class.getDeclaredField("valuePosition");
    fieldPosition.setAccessible(true);
    Field fieldValue = AbstractValueAssert.class.getDeclaredField("value");
    fieldValue.setAccessible(true);
    Field fieldIndex = Position.class.getDeclaredField("nextIndex");
    fieldIndex.setAccessible(true);

    Table table = new Table(source, "actor");
    TableAssert tableAssert = assertThat(table);
    TableRowAssert tableRowAssert = tableAssert.row();
    Position position = (Position) fieldPosition.get(tableRowAssert);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(0);
    TableRowValueAssert tableRowValueAssert0 = tableRowAssert.value();
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(1);
    TableRowValueAssert tableRowValueAssert1 = tableRowAssert.value();
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(2);
    TableRowValueAssert tableRowValueAssert2 = tableRowAssert.value();
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(3);
    TableRowValueAssert tableRowValueAssert3 = tableRowAssert.value();
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(4);
    TableRowValueAssert tableRowValueAssert4 = tableRowAssert.value();
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(5);
    try {
      tableRowAssert.value();
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 5 out of the limits [0, 5[");
    }

    TableAssert tableAssertBis = assertThat(table);
    TableRowAssert tableRowAssertBis = tableAssertBis.row();
    Position positionBis = (Position) fieldPosition.get(tableRowAssertBis);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(0);
    TableRowValueAssert tableRowValueAssertBis0 = tableRowAssertBis.value();
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(1);
    TableRowValueAssert tableRowValueAssertBis1 = tableRowValueAssertBis0.value();
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(2);
    TableRowValueAssert tableRowValueAssertBis2 = tableRowValueAssertBis1.value();
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(3);
    TableRowValueAssert tableRowValueAssertBis3 = tableRowValueAssertBis2.value();
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(4);
    TableRowValueAssert tableRowValueAssertBis4 = tableRowValueAssertBis3.value();
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(5);
    try {
      tableRowValueAssertBis4.value();
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 5 out of the limits [0, 5[");
    }

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
   * This method tests the {@code value} navigation method.
   */
  @Test
  public void test_value_from_column_of_request_with_assertions() throws Exception {
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
    RequestColumnValueAssert requestColumnValueAssert0 = requestColumnAssert.value();
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(1);
    RequestColumnValueAssert requestColumnValueAssert1 = requestColumnAssert.value();
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(2);
    RequestColumnValueAssert requestColumnValueAssert2 = requestColumnAssert.value();
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(3);
    try {
      requestColumnAssert.value();
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 3 out of the limits [0, 3[");
    }

    RequestAssert requestAssertBis = assertThat(request);
    RequestColumnAssert requestColumnAssertBis = requestAssertBis.column();
    Position positionBis = (Position) fieldPosition.get(requestColumnAssertBis);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(0);
    RequestColumnValueAssert requestColumnValueAssertBis0 = requestColumnAssertBis.value();
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(1);
    RequestColumnValueAssert requestColumnValueAssertBis1 = requestColumnValueAssertBis0.value();
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(2);
    RequestColumnValueAssert requestColumnValueAssertBis2 = requestColumnValueAssertBis1.value();
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(3);
    try {
      requestColumnValueAssertBis2.value();
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 3 out of the limits [0, 3[");
    }

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
   * This method tests the {@code value} navigation method.
   */
  @Test
  public void test_value_from_row_of_request_with_assertions() throws Exception {
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
    RequestRowValueAssert requestRowValueAssert0 = requestRowAssert.value();
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(1);
    RequestRowValueAssert requestRowValueAssert1 = requestRowAssert.value();
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(2);
    RequestRowValueAssert requestRowValueAssert2 = requestRowAssert.value();
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(3);
    RequestRowValueAssert requestRowValueAssert3 = requestRowAssert.value();
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(4);
    RequestRowValueAssert requestRowValueAssert4 = requestRowAssert.value();
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(5);
    try {
      requestRowAssert.value();
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 5 out of the limits [0, 5[");
    }

    RequestAssert requestAssertBis = assertThat(request);
    RequestRowAssert requestRowAssertBis = requestAssertBis.row();
    Position positionBis = (Position) fieldPosition.get(requestRowAssertBis);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(0);
    RequestRowValueAssert requestRowValueAssertBis0 = requestRowAssertBis.value();
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(1);
    RequestRowValueAssert requestRowValueAssertBis1 = requestRowValueAssertBis0.value();
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(2);
    RequestRowValueAssert requestRowValueAssertBis2 = requestRowValueAssertBis1.value();
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(3);
    RequestRowValueAssert requestRowValueAssertBis3 = requestRowValueAssertBis2.value();
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(4);
    RequestRowValueAssert requestRowValueAssertBis4 = requestRowValueAssertBis3.value();
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(5);
    try {
      requestRowValueAssertBis4.value();
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 5 out of the limits [0, 5[");
    }

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
   * This method tests the {@code value} navigation method.
   */
  @Test
  @NeedReload
  public void test_value_from_row_of_change_with_displays() throws Exception {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    Field fieldPosition = ChangeRowOutputter.class.getDeclaredField("valuePosition");
    fieldPosition.setAccessible(true);
    Field fieldValue = AbstractOutputterWithValues.class.getDeclaredField("value");
    fieldValue.setAccessible(true);
    Field fieldIndex = Position.class.getDeclaredField("nextIndex");
    fieldIndex.setAccessible(true);

    ChangesOutputter changesOutputter = display(changes);
    ChangeOutputter changeOutputter = changesOutputter.change();
    ChangeRowOutputter changeRowOutputter = changeOutputter.rowAtEndPoint();
    Position position = (Position) fieldPosition.get(changeRowOutputter);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(0);
    ChangeRowValueOutputter changeRowValueOutputter0 = changeRowOutputter.value();
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(1);
    ChangeRowValueOutputter changeRowValueOutputter1 = changeRowOutputter.value();
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(2);
    ChangeRowValueOutputter changeRowValueOutputter2 = changeRowOutputter.value();
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(3);
    ChangeRowValueOutputter changeRowValueOutputter3 = changeRowOutputter.value();
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(4);
    ChangeRowValueOutputter changeRowValueOutputter4 = changeRowOutputter.value();
    try {
      changeRowOutputter.value();
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 5 out of the limits [0, 5[");
    }
    try {
      changeOutputter.rowAtStartPoint().value();
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Row do not exist");
    }

    ChangesOutputter changesOutputterBis = display(changes);
    ChangeOutputter changeOutputterBis = changesOutputterBis.change();
    ChangeRowOutputter changeRowOutputterBis = changeOutputterBis.rowAtEndPoint();
    Position positionBis = (Position) fieldPosition.get(changeRowOutputterBis);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(0);
    ChangeRowValueOutputter changeRowValueOutputterBis0 = changeRowOutputterBis.value();
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(1);
    ChangeRowValueOutputter changeRowValueOutputterBis1 = changeRowValueOutputterBis0.value();
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(2);
    ChangeRowValueOutputter changeRowValueOutputterBis2 = changeRowValueOutputterBis1.value();
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(3);
    ChangeRowValueOutputter changeRowValueOutputterBis3 = changeRowValueOutputterBis2.value();
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(4);
    ChangeRowValueOutputter changeRowValueOutputterBis4 = changeRowValueOutputterBis3.value();
    try {
      changeRowValueOutputterBis4.value();
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 5 out of the limits [0, 5[");
    }
    try {
      changeOutputterBis.rowAtStartPoint().value();
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Row do not exist");
    }

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
   * This method tests the {@code value} navigation method.
   */
  @Test
  public void test_value_from_column_of_table_with_displays() throws Exception {
    Field fieldPosition = AbstractColumnOutputter.class.getDeclaredField("valuePosition");
    fieldPosition.setAccessible(true);
    Field fieldValue = AbstractValueOutputter.class.getDeclaredField("value");
    fieldValue.setAccessible(true);
    Field fieldIndex = Position.class.getDeclaredField("nextIndex");
    fieldIndex.setAccessible(true);

    Table table = new Table(source, "actor");
    TableOutputter tableOutputter = display(table);
    TableColumnOutputter tableColumnOutputter = tableOutputter.column();
    Position position = (Position) fieldPosition.get(tableColumnOutputter);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(0);
    TableColumnValueOutputter tableColumnValueOutputter0 = tableColumnOutputter.value();
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(1);
    TableColumnValueOutputter tableColumnValueOutputter1 = tableColumnOutputter.value();
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(2);
    TableColumnValueOutputter tableColumnValueOutputter2 = tableColumnOutputter.value();
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(3);
    try {
      tableColumnOutputter.value();
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 3 out of the limits [0, 3[");
    }

    TableOutputter tableOutputterBis = display(table);
    TableColumnOutputter tableColumnOutputterBis = tableOutputterBis.column();
    Position positionBis = (Position) fieldPosition.get(tableColumnOutputterBis);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(0);
    TableColumnValueOutputter tableColumnValueOutputterBis0 = tableColumnOutputterBis.value();
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(1);
    TableColumnValueOutputter tableColumnValueOutputterBis1 = tableColumnValueOutputterBis0.value();
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(2);
    TableColumnValueOutputter tableColumnValueOutputterBis2 = tableColumnValueOutputterBis1.value();
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(3);
    try {
      tableColumnValueOutputterBis2.value();
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 3 out of the limits [0, 3[");
    }

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
   * This method tests the {@code value} navigation method.
   */
  @Test
  public void test_value_from_row_of_table_with_displays() throws Exception {
    Field fieldPosition = AbstractRowOutputter.class.getDeclaredField("valuePosition");
    fieldPosition.setAccessible(true);
    Field fieldValue = AbstractValueOutputter.class.getDeclaredField("value");
    fieldValue.setAccessible(true);
    Field fieldIndex = Position.class.getDeclaredField("nextIndex");
    fieldIndex.setAccessible(true);

    Table table = new Table(source, "actor");
    TableOutputter tableOutputter = display(table);
    TableRowOutputter tableRowOutputter = tableOutputter.row();
    Position position = (Position) fieldPosition.get(tableRowOutputter);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(0);
    TableRowValueOutputter tableRowValueOutputter0 = tableRowOutputter.value();
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(1);
    TableRowValueOutputter tableRowValueOutputter1 = tableRowOutputter.value();
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(2);
    TableRowValueOutputter tableRowValueOutputter2 = tableRowOutputter.value();
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(3);
    TableRowValueOutputter tableRowValueOutputter3 = tableRowOutputter.value();
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(4);
    TableRowValueOutputter tableRowValueOutputter4 = tableRowOutputter.value();
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(5);
    try {
      tableRowOutputter.value();
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 5 out of the limits [0, 5[");
    }

    TableOutputter tableOutputterBis = display(table);
    TableRowOutputter tableRowOutputterBis = tableOutputterBis.row();
    Position positionBis = (Position) fieldPosition.get(tableRowOutputterBis);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(0);
    TableRowValueOutputter tableRowValueOutputterBis0 = tableRowOutputterBis.value();
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(1);
    TableRowValueOutputter tableRowValueOutputterBis1 = tableRowValueOutputterBis0.value();
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(2);
    TableRowValueOutputter tableRowValueOutputterBis2 = tableRowValueOutputterBis1.value();
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(3);
    TableRowValueOutputter tableRowValueOutputterBis3 = tableRowValueOutputterBis2.value();
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(4);
    TableRowValueOutputter tableRowValueOutputterBis4 = tableRowValueOutputterBis3.value();
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(5);
    try {
      tableRowValueOutputterBis4.value();
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 5 out of the limits [0, 5[");
    }

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
   * This method tests the {@code value} navigation method.
   */
  @Test
  public void test_value_from_column_of_request_with_displays() throws Exception {
    Field fieldPosition = AbstractColumnOutputter.class.getDeclaredField("valuePosition");
    fieldPosition.setAccessible(true);
    Field fieldValue = AbstractValueOutputter.class.getDeclaredField("value");
    fieldValue.setAccessible(true);
    Field fieldIndex = Position.class.getDeclaredField("nextIndex");
    fieldIndex.setAccessible(true);

    Request request = new Request(source, "select * from actor");
    RequestOutputter requestOutputter = display(request);
    RequestColumnOutputter requestColumnOutputter = requestOutputter.column();
    Position position = (Position) fieldPosition.get(requestColumnOutputter);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(0);
    RequestColumnValueOutputter requestColumnValueOutputter0 = requestColumnOutputter.value();
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(1);
    RequestColumnValueOutputter requestColumnValueOutputter1 = requestColumnOutputter.value();
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(2);
    RequestColumnValueOutputter requestColumnValueOutputter2 = requestColumnOutputter.value();
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(3);
    try {
      requestColumnOutputter.value();
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 3 out of the limits [0, 3[");
    }

    RequestOutputter requestOutputterBis = display(request);
    RequestColumnOutputter requestColumnOutputterBis = requestOutputterBis.column();
    Position positionBis = (Position) fieldPosition.get(requestColumnOutputterBis);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(0);
    RequestColumnValueOutputter requestColumnValueOutputterBis0 = requestColumnOutputterBis.value();
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(1);
    RequestColumnValueOutputter requestColumnValueOutputterBis1 = requestColumnValueOutputterBis0.value();
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(2);
    RequestColumnValueOutputter requestColumnValueOutputterBis2 = requestColumnValueOutputterBis1.value();
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(3);
    try {
      requestColumnValueOutputterBis2.value();
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 3 out of the limits [0, 3[");
    }

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
   * This method tests the {@code value} navigation method.
   */
  @Test
  public void test_value_from_row_of_request_with_displays() throws Exception {
    Field fieldPosition = AbstractRowOutputter.class.getDeclaredField("valuePosition");
    fieldPosition.setAccessible(true);
    Field fieldValue = AbstractValueOutputter.class.getDeclaredField("value");
    fieldValue.setAccessible(true);
    Field fieldIndex = Position.class.getDeclaredField("nextIndex");
    fieldIndex.setAccessible(true);

    Request request = new Request(source, "select * from actor");
    RequestOutputter requestOutputter = display(request);
    RequestRowOutputter requestRowOutputter = requestOutputter.row();
    Position position = (Position) fieldPosition.get(requestRowOutputter);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(0);
    RequestRowValueOutputter requestRowValueOutputter0 = requestRowOutputter.value();
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(1);
    RequestRowValueOutputter requestRowValueOutputter1 = requestRowOutputter.value();
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(2);
    RequestRowValueOutputter requestRowValueOutputter2 = requestRowOutputter.value();
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(3);
    RequestRowValueOutputter requestRowValueOutputter3 = requestRowOutputter.value();
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(4);
    RequestRowValueOutputter requestRowValueOutputter4 = requestRowOutputter.value();
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(5);
    try {
      requestRowOutputter.value();
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 5 out of the limits [0, 5[");
    }

    RequestOutputter requestOutputterBis = display(request);
    RequestRowOutputter requestRowOutputterBis = requestOutputterBis.row();
    Position positionBis = (Position) fieldPosition.get(requestRowOutputterBis);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(0);
    RequestRowValueOutputter requestRowValueOutputterBis0 = requestRowOutputterBis.value();
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(1);
    RequestRowValueOutputter requestRowValueOutputterBis1 = requestRowValueOutputterBis0.value();
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(2);
    RequestRowValueOutputter requestRowValueOutputterBis2 = requestRowValueOutputterBis1.value();
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(3);
    RequestRowValueOutputter requestRowValueOutputterBis3 = requestRowValueOutputterBis2.value();
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(4);
    RequestRowValueOutputter requestRowValueOutputterBis4 = requestRowValueOutputterBis3.value();
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(5);
    try {
      requestRowValueOutputterBis4.value();
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 5 out of the limits [0, 5[");
    }

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
