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
import static org.assertj.db.display.Displaying.display;
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
    Position position = (Position) fieldPosition.get(changeRowAssert);
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
    Position positionBis = (Position) fieldPosition.get(changeRowAssertBis);
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
    Position position = (Position) fieldPosition.get(tableColumnAssert);
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
    Position positionBis = (Position) fieldPosition.get(tableColumnAssertBis);
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
    Position position = (Position) fieldPosition.get(tableRowAssert);
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
    Position positionBis = (Position) fieldPosition.get(tableRowAssertBis);
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

    Field fieldPosition = ChangeRowDisplay.class.getDeclaredField("valuePosition");
    fieldPosition.setAccessible(true);
    Field fieldValue = AbstractDisplayWithValues.class.getDeclaredField("value");
    fieldValue.setAccessible(true);
    Field fieldIndex = Position.class.getDeclaredField("nextIndex");
    fieldIndex.setAccessible(true);

    ChangesDisplay changesDisplay = display(changes);
    ChangeDisplay changeDisplay = changesDisplay.change();
    ChangeRowDisplay changeRowDisplay = changeDisplay.rowAtEndPoint();
    Position position = (Position) fieldPosition.get(changeRowDisplay);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(0);
    ChangeRowValueDisplay changeRowValueDisplay0 = changeRowDisplay.value(0);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(1);
    ChangeRowValueDisplay changeRowValueDisplay1 = changeRowDisplay.value(1);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(2);
    ChangeRowValueDisplay changeRowValueDisplay2 = changeRowDisplay.value(2);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(3);
    ChangeRowValueDisplay changeRowValueDisplay3 = changeRowDisplay.value(3);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(4);
    ChangeRowValueDisplay changeRowValueDisplay4 = changeRowDisplay.value(4);
    try {
      changeRowDisplay.value(6);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 6 out of the limits [0, 5[");
    }
    try {
      changeRowDisplay.value(-1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index -1 out of the limits [0, 5[");
    }
    try {
      changeDisplay.rowAtStartPoint().value(0);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Row do not exist");
    }
    ChangeRowValueDisplay changeRowValueDisplayAgain0 = changeRowDisplay.value(0);
    Assertions.assertThat(changeRowValueDisplay0).isSameAs(changeRowValueDisplayAgain0);

    ChangesDisplay changesDisplayBis = display(changes);
    ChangeDisplay changeDisplayBis = changesDisplayBis.change();
    ChangeRowDisplay changeRowDisplayBis = changeDisplayBis.rowAtEndPoint();
    Position positionBis = (Position) fieldPosition.get(changeRowDisplayBis);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(0);
    ChangeRowValueDisplay changeRowValueDisplayBis0 = changeRowDisplayBis.value(0);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(1);
    ChangeRowValueDisplay changeRowValueDisplayBis1 = changeRowValueDisplayBis0.value(1);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(2);
    ChangeRowValueDisplay changeRowValueDisplayBis2 = changeRowValueDisplayBis1.value(2);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(3);
    ChangeRowValueDisplay changeRowValueDisplayBis3 = changeRowValueDisplayBis2.value(3);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(4);
    ChangeRowValueDisplay changeRowValueDisplayBis4 = changeRowValueDisplayBis3.value(4);
    try {
      changeRowValueDisplayBis4.value(6);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 6 out of the limits [0, 5[");
    }
    try {
      changeRowValueDisplayBis4.value(-1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index -1 out of the limits [0, 5[");
    }
    try {
      changeDisplayBis.rowAtStartPoint().value(0);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Row do not exist");
    }
    ChangeRowValueDisplay changeRowValueDisplayBisAgain0 = changeRowValueDisplayBis4.value(0);
    Assertions.assertThat(changeRowValueDisplayBis0).isSameAs(changeRowValueDisplayBisAgain0);

    Assertions.assertThat(((Value) fieldValue.get(changeRowValueDisplay0)).getValue())
              .isSameAs(((Value) fieldValue.get(changeRowValueDisplayBis0)).getValue())
              .isEqualTo(new BigDecimal("4"));
    Assertions.assertThat(((Value) fieldValue.get(changeRowValueDisplay1)).getValue())
              .isSameAs(((Value) fieldValue.get(changeRowValueDisplayBis1)).getValue())
              .isEqualTo("Murray");
    Assertions.assertThat(((Value) fieldValue.get(changeRowValueDisplay2)).getValue())
              .isSameAs(((Value) fieldValue.get(changeRowValueDisplayBis2)).getValue())
              .isEqualTo("Bill");
    Assertions.assertThat(((Value) fieldValue.get(changeRowValueDisplay3)).getValue())
              .isSameAs(((Value) fieldValue.get(changeRowValueDisplayBis3)).getValue())
              .isEqualTo(Date.valueOf("1950-09-21"));
    Assertions.assertThat(((Value) fieldValue.get(changeRowValueDisplay4)).getValue())
              .isSameAs(((Value) fieldValue.get(changeRowValueDisplayBis4)).getValue())
              .isEqualTo(UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"));
  }

  /**
   * This method tests the {@code value} with index navigation method.
   */
  @Test
  public void test_value_from_column_of_table_with_index_with_displays() throws Exception {
    Field fieldPosition = AbstractColumnDisplay.class.getDeclaredField("valuePosition");
    fieldPosition.setAccessible(true);
    Field fieldValue = AbstractValueDisplay.class.getDeclaredField("value");
    fieldValue.setAccessible(true);
    Field fieldIndex = Position.class.getDeclaredField("nextIndex");
    fieldIndex.setAccessible(true);

    Table table = new Table(source, "actor");
    TableDisplay tableDisplay = display(table);
    TableColumnDisplay tableColumnDisplay = tableDisplay.column();
    Position position = (Position) fieldPosition.get(tableColumnDisplay);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(0);
    TableColumnValueDisplay tableColumnValueDisplay0 = tableColumnDisplay.value(0);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(1);
    TableColumnValueDisplay tableColumnValueDisplay1 = tableColumnDisplay.value(1);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(2);
    TableColumnValueDisplay tableColumnValueDisplay2 = tableColumnDisplay.value(2);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(3);
    try {
      tableColumnDisplay.value(5);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 5 out of the limits [0, 3[");
    }
    try {
      tableColumnDisplay.value(-1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index -1 out of the limits [0, 3[");
    }
    TableColumnValueDisplay tableColumnValueDisplayAgain0 = tableColumnDisplay.value(0);
    Assertions.assertThat(tableColumnValueDisplay0).isSameAs(tableColumnValueDisplayAgain0);

    TableDisplay tableDisplayBis = display(table);
    TableColumnDisplay tableColumnDisplayBis = tableDisplayBis.column();
    Position positionBis = (Position) fieldPosition.get(tableColumnDisplayBis);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(0);
    TableColumnValueDisplay tableColumnValueDisplayBis0 = tableColumnDisplayBis.value(0);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(1);
    TableColumnValueDisplay tableColumnValueDisplayBis1 = tableColumnValueDisplayBis0.value(1);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(2);
    TableColumnValueDisplay tableColumnValueDisplayBis2 = tableColumnValueDisplayBis1.value(2);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(3);
    try {
      tableColumnValueDisplayBis2.value(5);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 5 out of the limits [0, 3[");
    }
    try {
      tableColumnValueDisplayBis2.value(-1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index -1 out of the limits [0, 3[");
    }
    TableColumnValueDisplay tableColumnValueDisplayBisAgain0 = tableColumnValueDisplayBis2.value(0);
    Assertions.assertThat(tableColumnValueDisplayBis0).isSameAs(tableColumnValueDisplayBisAgain0);

    Assertions.assertThat(((Value) fieldValue.get(tableColumnValueDisplay0)).getValue())
              .isSameAs(((Value) fieldValue.get(tableColumnValueDisplayBis0)).getValue())
              .isEqualTo(new BigDecimal("1"));
    Assertions.assertThat(((Value) fieldValue.get(tableColumnValueDisplay1)).getValue())
              .isSameAs(((Value) fieldValue.get(tableColumnValueDisplayBis1)).getValue())
              .isEqualTo(new BigDecimal("2"));
    Assertions.assertThat(((Value) fieldValue.get(tableColumnValueDisplay2)).getValue())
              .isSameAs(((Value) fieldValue.get(tableColumnValueDisplayBis2)).getValue())
              .isEqualTo(new BigDecimal("3"));
  }

  /**
   * This method tests the {@code value} with index navigation method.
   */
  @Test
  public void test_value_from_row_of_table_with_index_with_displays() throws Exception {
    Field fieldPosition = AbstractRowDisplay.class.getDeclaredField("valuePosition");
    fieldPosition.setAccessible(true);
    Field fieldValue = AbstractValueDisplay.class.getDeclaredField("value");
    fieldValue.setAccessible(true);
    Field fieldIndex = Position.class.getDeclaredField("nextIndex");
    fieldIndex.setAccessible(true);

    Table table = new Table(source, "actor");
    TableDisplay tableDisplay = display(table);
    TableRowDisplay tableRowDisplay = tableDisplay.row();
    Position position = (Position) fieldPosition.get(tableRowDisplay);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(0);
    TableRowValueDisplay tableRowValueDisplay0 = tableRowDisplay.value(0);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(1);
    TableRowValueDisplay tableRowValueDisplay1 = tableRowDisplay.value(1);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(2);
    TableRowValueDisplay tableRowValueDisplay2 = tableRowDisplay.value(2);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(3);
    TableRowValueDisplay tableRowValueDisplay3 = tableRowDisplay.value(3);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(4);
    TableRowValueDisplay tableRowValueDisplay4 = tableRowDisplay.value(4);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(5);
    try {
      tableRowDisplay.value(7);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 7 out of the limits [0, 5[");
    }
    try {
      tableRowDisplay.value(-1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index -1 out of the limits [0, 5[");
    }
    TableRowValueDisplay tableRowValueDisplayAgain0 = tableRowDisplay.value(0);
    Assertions.assertThat(tableRowValueDisplay0).isSameAs(tableRowValueDisplayAgain0);

    TableDisplay tableDisplayBis = display(table);
    TableRowDisplay tableRowDisplayBis = tableDisplayBis.row();
    Position positionBis = (Position) fieldPosition.get(tableRowDisplayBis);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(0);
    TableRowValueDisplay tableRowValueDisplayBis0 = tableRowDisplayBis.value(0);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(1);
    TableRowValueDisplay tableRowValueDisplayBis1 = tableRowValueDisplayBis0.value(1);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(2);
    TableRowValueDisplay tableRowValueDisplayBis2 = tableRowValueDisplayBis1.value(2);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(3);
    TableRowValueDisplay tableRowValueDisplayBis3 = tableRowValueDisplayBis2.value(3);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(4);
    TableRowValueDisplay tableRowValueDisplayBis4 = tableRowValueDisplayBis3.value(4);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(5);
    try {
      tableRowValueDisplayBis4.value(7);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 7 out of the limits [0, 5[");
    }
    try {
      tableRowValueDisplayBis4.value(-1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index -1 out of the limits [0, 5[");
    }
    TableRowValueDisplay tableRowValueDisplayBisAgain0 = tableRowValueDisplayBis4.value(0);
    Assertions.assertThat(tableRowValueDisplayBis0).isSameAs(tableRowValueDisplayBisAgain0);

    Assertions.assertThat(((Value) fieldValue.get(tableRowValueDisplay0)).getValue())
              .isSameAs(((Value) fieldValue.get(tableRowValueDisplayBis0)).getValue())
              .isEqualTo(new BigDecimal("1"));
    Assertions.assertThat(((Value) fieldValue.get(tableRowValueDisplay1)).getValue())
              .isSameAs(((Value) fieldValue.get(tableRowValueDisplayBis1)).getValue())
              .isEqualTo("Weaver");
    Assertions.assertThat(((Value) fieldValue.get(tableRowValueDisplay2)).getValue())
              .isSameAs(((Value) fieldValue.get(tableRowValueDisplayBis2)).getValue())
              .isEqualTo("Sigourney");
    Assertions.assertThat(((Value) fieldValue.get(tableRowValueDisplay3)).getValue())
              .isSameAs(((Value) fieldValue.get(tableRowValueDisplayBis3)).getValue())
              .isEqualTo(Date.valueOf("1949-10-08"));
    Assertions.assertThat(((Value) fieldValue.get(tableRowValueDisplay4)).getValue())
              .isSameAs(((Value) fieldValue.get(tableRowValueDisplayBis4)).getValue())
              .isEqualTo(UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"));
  }

  /**
   * This method tests the {@code value} with index navigation method.
   */
  @Test
  public void test_value_from_column_of_request_with_index_with_displays() throws Exception {
    Field fieldPosition = AbstractColumnDisplay.class.getDeclaredField("valuePosition");
    fieldPosition.setAccessible(true);
    Field fieldValue = AbstractValueDisplay.class.getDeclaredField("value");
    fieldValue.setAccessible(true);
    Field fieldIndex = Position.class.getDeclaredField("nextIndex");
    fieldIndex.setAccessible(true);

    Request request = new Request(source, "select * from actor");
    RequestDisplay requestDisplay = display(request);
    RequestColumnDisplay requestColumnDisplay = requestDisplay.column();
    Position position = (Position) fieldPosition.get(requestColumnDisplay);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(0);
    RequestColumnValueDisplay requestColumnValueDisplay0 = requestColumnDisplay.value(0);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(1);
    RequestColumnValueDisplay requestColumnValueDisplay1 = requestColumnDisplay.value(1);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(2);
    RequestColumnValueDisplay requestColumnValueDisplay2 = requestColumnDisplay.value(2);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(3);
    try {
      requestColumnDisplay.value(8);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 8 out of the limits [0, 3[");
    }
    try {
      requestColumnDisplay.value(-1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index -1 out of the limits [0, 3[");
    }
    RequestColumnValueDisplay requestColumnValueDisplayAgain0 = requestColumnDisplay.value(0);
    Assertions.assertThat(requestColumnValueDisplay0).isSameAs(requestColumnValueDisplayAgain0);

    RequestDisplay requestDisplayBis = display(request);
    RequestColumnDisplay requestColumnDisplayBis = requestDisplayBis.column();
    Position positionBis = (Position) fieldPosition.get(requestColumnDisplayBis);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(0);
    RequestColumnValueDisplay requestColumnValueDisplayBis0 = requestColumnDisplayBis.value(0);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(1);
    RequestColumnValueDisplay requestColumnValueDisplayBis1 = requestColumnValueDisplayBis0.value(1);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(2);
    RequestColumnValueDisplay requestColumnValueDisplayBis2 = requestColumnValueDisplayBis1.value(2);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(3);
    try {
      requestColumnValueDisplayBis2.value(8);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 8 out of the limits [0, 3[");
    }
    try {
      requestColumnValueDisplayBis2.value(-1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index -1 out of the limits [0, 3[");
    }
    RequestColumnValueDisplay requestColumnValueDisplayBisAgain0 = requestColumnValueDisplayBis2.value(0);
    Assertions.assertThat(requestColumnValueDisplayBis0).isSameAs(requestColumnValueDisplayBisAgain0);

    Assertions.assertThat(((Value) fieldValue.get(requestColumnValueDisplay0)).getValue())
              .isSameAs(((Value) fieldValue.get(requestColumnValueDisplayBis0)).getValue())
              .isEqualTo(new BigDecimal("1"));
    Assertions.assertThat(((Value) fieldValue.get(requestColumnValueDisplay1)).getValue())
              .isSameAs(((Value) fieldValue.get(requestColumnValueDisplayBis1)).getValue())
              .isEqualTo(new BigDecimal("2"));
    Assertions.assertThat(((Value) fieldValue.get(requestColumnValueDisplay2)).getValue())
              .isSameAs(((Value) fieldValue.get(requestColumnValueDisplayBis2)).getValue())
              .isEqualTo(new BigDecimal("3"));
  }

  /**
   * This method tests the {@code value} with index navigation method.
   */
  @Test
  public void test_value_from_row_of_request_with_index_with_displays() throws Exception {
    Field fieldPosition = AbstractRowDisplay.class.getDeclaredField("valuePosition");
    fieldPosition.setAccessible(true);
    Field fieldValue = AbstractValueDisplay.class.getDeclaredField("value");
    fieldValue.setAccessible(true);
    Field fieldIndex = Position.class.getDeclaredField("nextIndex");
    fieldIndex.setAccessible(true);

    Request request = new Request(source, "select * from actor");
    RequestDisplay requestDisplay = display(request);
    RequestRowDisplay requestRowDisplay = requestDisplay.row();
    Position position = (Position) fieldPosition.get(requestRowDisplay);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(0);
    RequestRowValueDisplay requestRowValueDisplay0 = requestRowDisplay.value(0);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(1);
    RequestRowValueDisplay requestRowValueDisplay1 = requestRowDisplay.value(1);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(2);
    RequestRowValueDisplay requestRowValueDisplay2 = requestRowDisplay.value(2);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(3);
    RequestRowValueDisplay requestRowValueDisplay3 = requestRowDisplay.value(3);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(4);
    RequestRowValueDisplay requestRowValueDisplay4 = requestRowDisplay.value(4);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(5);
    try {
      requestRowDisplay.value(9);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 9 out of the limits [0, 5[");
    }
    try {
      requestRowDisplay.value(-1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index -1 out of the limits [0, 5[");
    }
    RequestRowValueDisplay requestRowValueDisplayAgain0 = requestRowDisplay.value(0);
    Assertions.assertThat(requestRowValueDisplay0).isSameAs(requestRowValueDisplayAgain0);

    RequestDisplay requestDisplayBis = display(request);
    RequestRowDisplay requestRowDisplayBis = requestDisplayBis.row();
    Position positionBis = (Position) fieldPosition.get(requestRowDisplayBis);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(0);
    RequestRowValueDisplay requestRowValueDisplayBis0 = requestRowDisplayBis.value(0);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(1);
    RequestRowValueDisplay requestRowValueDisplayBis1 = requestRowValueDisplayBis0.value(1);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(2);
    RequestRowValueDisplay requestRowValueDisplayBis2 = requestRowValueDisplayBis1.value(2);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(3);
    RequestRowValueDisplay requestRowValueDisplayBis3 = requestRowValueDisplayBis2.value(3);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(4);
    RequestRowValueDisplay requestRowValueDisplayBis4 = requestRowValueDisplayBis3.value(4);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(5);
    try {
      requestRowValueDisplayBis4.value(9);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 9 out of the limits [0, 5[");
    }
    try {
      requestRowValueDisplayBis4.value(-1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index -1 out of the limits [0, 5[");
    }
    RequestRowValueDisplay requestRowValueDisplayBisAgain0 = requestRowValueDisplayBis4.value(0);
    Assertions.assertThat(requestRowValueDisplayBis0).isSameAs(requestRowValueDisplayBisAgain0);

    Assertions.assertThat(((Value) fieldValue.get(requestRowValueDisplay0)).getValue())
              .isSameAs(((Value) fieldValue.get(requestRowValueDisplayBis0)).getValue())
              .isEqualTo(new BigDecimal("1"));
    Assertions.assertThat(((Value) fieldValue.get(requestRowValueDisplay1)).getValue())
              .isSameAs(((Value) fieldValue.get(requestRowValueDisplayBis1)).getValue())
              .isEqualTo("Weaver");
    Assertions.assertThat(((Value) fieldValue.get(requestRowValueDisplay2)).getValue())
              .isSameAs(((Value) fieldValue.get(requestRowValueDisplayBis2)).getValue())
              .isEqualTo("Sigourney");
    Assertions.assertThat(((Value) fieldValue.get(requestRowValueDisplay3)).getValue())
              .isSameAs(((Value) fieldValue.get(requestRowValueDisplayBis3)).getValue())
              .isEqualTo(Date.valueOf("1949-10-08"));
    Assertions.assertThat(((Value) fieldValue.get(requestRowValueDisplay4)).getValue())
              .isSameAs(((Value) fieldValue.get(requestRowValueDisplayBis4)).getValue())
              .isEqualTo(UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"));
  }
}
