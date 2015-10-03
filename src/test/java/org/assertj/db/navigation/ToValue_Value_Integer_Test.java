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
  public void test_value_from_row_of_change_with_index() throws Exception {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    Field fieldIndex = ChangeRowAssert.class.getDeclaredField("indexNextValue");
    fieldIndex.setAccessible(true);
    Field fieldValue = AbstractAssertWithValues.class.getDeclaredField("value");
    fieldValue.setAccessible(true);

    ChangesAssert changesAssert = assertThat(changes);
    ChangeAssert changeAssert = changesAssert.change();
    ChangeRowAssert changeRowAssert = changeAssert.rowAtEndPoint();
    Assertions.assertThat(fieldIndex.get(changeRowAssert)).isEqualTo(0);
    ChangeRowValueAssert changeRowValueAssert0 = changeRowAssert.value(0);
    Assertions.assertThat(fieldIndex.get(changeRowAssert)).isEqualTo(1);
    ChangeRowValueAssert changeRowValueAssert1 = changeRowAssert.value(1);
    Assertions.assertThat(fieldIndex.get(changeRowAssert)).isEqualTo(2);
    ChangeRowValueAssert changeRowValueAssert2 = changeRowAssert.value(2);
    Assertions.assertThat(fieldIndex.get(changeRowAssert)).isEqualTo(3);
    ChangeRowValueAssert changeRowValueAssert3 = changeRowAssert.value(3);
    Assertions.assertThat(fieldIndex.get(changeRowAssert)).isEqualTo(4);
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
    Assertions.assertThat(fieldIndex.get(changeRowAssertBis)).isEqualTo(0);
    ChangeRowValueAssert changeRowValueAssertBis0 = changeRowAssertBis.value(0);
    Assertions.assertThat(fieldIndex.get(changeRowAssertBis)).isEqualTo(1);
    ChangeRowValueAssert changeRowValueAssertBis1 = changeRowValueAssertBis0.value(1);
    Assertions.assertThat(fieldIndex.get(changeRowAssertBis)).isEqualTo(2);
    ChangeRowValueAssert changeRowValueAssertBis2 = changeRowValueAssertBis1.value(2);
    Assertions.assertThat(fieldIndex.get(changeRowAssertBis)).isEqualTo(3);
    ChangeRowValueAssert changeRowValueAssertBis3 = changeRowValueAssertBis2.value(3);
    Assertions.assertThat(fieldIndex.get(changeRowAssertBis)).isEqualTo(4);
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
  public void test_value_from_column_of_table_with_index() throws Exception {
    Field fieldPosition = AbstractSubAssert.class.getDeclaredField("valuePosition");
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
  public void test_value_from_row_of_table_with_index() throws Exception {
    Field fieldPosition = AbstractSubAssert.class.getDeclaredField("valuePosition");
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
  public void test_value_from_column_of_request_with_index() throws Exception {
    Field fieldPosition = AbstractSubAssert.class.getDeclaredField("valuePosition");
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
  public void test_value_from_row_of_request_with_index() throws Exception {
    Field fieldPosition = AbstractSubAssert.class.getDeclaredField("valuePosition");
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
}
