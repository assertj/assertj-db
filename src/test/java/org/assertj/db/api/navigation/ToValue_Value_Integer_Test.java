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
package org.assertj.db.api.navigation;

import org.assertj.core.api.Assertions;
import org.assertj.db.api.*;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.assertj.db.exception.AssertJDBException;
import org.assertj.db.type.Changes;
import org.assertj.db.type.Request;
import org.assertj.db.type.Table;
import org.junit.Test;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on {@link org.assertj.db.api.navigation.ToValue} class :
 * {@link org.assertj.db.api.navigation.ToValue#value(int)} method.
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

    Assertions.assertThat(fieldValue.get(changeRowValueAssert0)).isSameAs(fieldValue.get(changeRowValueAssertBis0)).isEqualTo(new BigDecimal("4"));
    Assertions.assertThat(fieldValue.get(changeRowValueAssert1)).isSameAs(fieldValue.get(changeRowValueAssertBis1)).isEqualTo("Murray");
    Assertions.assertThat(fieldValue.get(changeRowValueAssert2)).isSameAs(fieldValue.get(changeRowValueAssertBis2)).isEqualTo("Bill");
    Assertions.assertThat(fieldValue.get(changeRowValueAssert3)).isSameAs(fieldValue.get(changeRowValueAssertBis3)).isEqualTo(
            Date.valueOf("1950-09-21"));
    Assertions.assertThat(fieldValue.get(changeRowValueAssert4)).isSameAs(fieldValue.get(changeRowValueAssertBis4)).isEqualTo(
            UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"));
  }

  /**
   * This method tests the {@code value} with index navigation method.
   */
  @Test
  public void test_value_from_column_of_table_with_index() throws Exception {
    Field fieldIndex = AbstractSubAssert.class.getDeclaredField("indexNextValue");
    fieldIndex.setAccessible(true);
    Field fieldValue = AbstractValueAssert.class.getDeclaredField("value");
    fieldValue.setAccessible(true);

    Table table = new Table(source, "actor");
    TableAssert tableAssert = assertThat(table);
    TableColumnAssert tableColumnAssert = tableAssert.column();
    Assertions.assertThat(fieldIndex.get(tableColumnAssert)).isEqualTo(0);
    TableColumnValueAssert tableColumnValueAssert0 = tableColumnAssert.value(0);
    Assertions.assertThat(fieldIndex.get(tableColumnAssert)).isEqualTo(1);
    TableColumnValueAssert tableColumnValueAssert1 = tableColumnAssert.value(1);
    Assertions.assertThat(fieldIndex.get(tableColumnAssert)).isEqualTo(2);
    TableColumnValueAssert tableColumnValueAssert2 = tableColumnAssert.value(2);
    Assertions.assertThat(fieldIndex.get(tableColumnAssert)).isEqualTo(3);
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
    Assertions.assertThat(fieldIndex.get(tableColumnAssertBis)).isEqualTo(0);
    TableColumnValueAssert tableColumnValueAssertBis0 = tableColumnAssertBis.value(0);
    Assertions.assertThat(fieldIndex.get(tableColumnAssertBis)).isEqualTo(1);
    TableColumnValueAssert tableColumnValueAssertBis1 = tableColumnValueAssertBis0.value(1);
    Assertions.assertThat(fieldIndex.get(tableColumnAssertBis)).isEqualTo(2);
    TableColumnValueAssert tableColumnValueAssertBis2 = tableColumnValueAssertBis1.value(2);
    Assertions.assertThat(fieldIndex.get(tableColumnAssertBis)).isEqualTo(3);
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

    Assertions.assertThat(fieldValue.get(tableColumnValueAssert0)).isSameAs(fieldValue.get(tableColumnValueAssertBis0)).isEqualTo(new BigDecimal("1"));
    Assertions.assertThat(fieldValue.get(tableColumnValueAssert1)).isSameAs(fieldValue.get(tableColumnValueAssertBis1)).isEqualTo(new BigDecimal("2"));
    Assertions.assertThat(fieldValue.get(tableColumnValueAssert2)).isSameAs(fieldValue.get(tableColumnValueAssertBis2)).isEqualTo(new BigDecimal("3"));
  }

  /**
   * This method tests the {@code value} with index navigation method.
   */
  @Test
  public void test_value_from_row_of_table_with_index() throws Exception {
    Field fieldIndex = AbstractSubAssert.class.getDeclaredField("indexNextValue");
    fieldIndex.setAccessible(true);
    Field fieldValue = AbstractValueAssert.class.getDeclaredField("value");
    fieldValue.setAccessible(true);

    Table table = new Table(source, "actor");
    TableAssert tableAssert = assertThat(table);
    TableRowAssert tableRowAssert = tableAssert.row();
    Assertions.assertThat(fieldIndex.get(tableRowAssert)).isEqualTo(0);
    TableRowValueAssert tableRowValueAssert0 = tableRowAssert.value(0);
    Assertions.assertThat(fieldIndex.get(tableRowAssert)).isEqualTo(1);
    TableRowValueAssert tableRowValueAssert1 = tableRowAssert.value(1);
    Assertions.assertThat(fieldIndex.get(tableRowAssert)).isEqualTo(2);
    TableRowValueAssert tableRowValueAssert2 = tableRowAssert.value(2);
    Assertions.assertThat(fieldIndex.get(tableRowAssert)).isEqualTo(3);
    TableRowValueAssert tableRowValueAssert3 = tableRowAssert.value(3);
    Assertions.assertThat(fieldIndex.get(tableRowAssert)).isEqualTo(4);
    TableRowValueAssert tableRowValueAssert4 = tableRowAssert.value(4);
    Assertions.assertThat(fieldIndex.get(tableRowAssert)).isEqualTo(5);
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
    Assertions.assertThat(fieldIndex.get(tableRowAssertBis)).isEqualTo(0);
    TableRowValueAssert tableRowValueAssertBis0 = tableRowAssertBis.value(0);
    Assertions.assertThat(fieldIndex.get(tableRowAssertBis)).isEqualTo(1);
    TableRowValueAssert tableRowValueAssertBis1 = tableRowValueAssertBis0.value(1);
    Assertions.assertThat(fieldIndex.get(tableRowAssertBis)).isEqualTo(2);
    TableRowValueAssert tableRowValueAssertBis2 = tableRowValueAssertBis1.value(2);
    Assertions.assertThat(fieldIndex.get(tableRowAssertBis)).isEqualTo(3);
    TableRowValueAssert tableRowValueAssertBis3 = tableRowValueAssertBis2.value(3);
    Assertions.assertThat(fieldIndex.get(tableRowAssertBis)).isEqualTo(4);
    TableRowValueAssert tableRowValueAssertBis4 = tableRowValueAssertBis3.value(4);
    Assertions.assertThat(fieldIndex.get(tableRowAssertBis)).isEqualTo(5);
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

    Assertions.assertThat(fieldValue.get(tableRowValueAssert0)).isSameAs(fieldValue.get(tableRowValueAssertBis0)).isEqualTo(new BigDecimal("1"));
    Assertions.assertThat(fieldValue.get(tableRowValueAssert1)).isSameAs(fieldValue.get(tableRowValueAssertBis1)).isEqualTo("Weaver");
    Assertions.assertThat(fieldValue.get(tableRowValueAssert2)).isSameAs(fieldValue.get(tableRowValueAssertBis2)).isEqualTo("Sigourney");
    Assertions.assertThat(fieldValue.get(tableRowValueAssert3)).isSameAs(fieldValue.get(tableRowValueAssertBis3)).isEqualTo(Date.valueOf("1949-10-08"));
    Assertions.assertThat(fieldValue.get(tableRowValueAssert4)).isSameAs(fieldValue.get(tableRowValueAssertBis4)).isEqualTo(UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"));
  }

  /**
   * This method tests the {@code value} with index navigation method.
   */
  @Test
  public void test_value_from_column_of_request_with_index() throws Exception {
    Field fieldIndex = AbstractSubAssert.class.getDeclaredField("indexNextValue");
    fieldIndex.setAccessible(true);
    Field fieldValue = AbstractValueAssert.class.getDeclaredField("value");
    fieldValue.setAccessible(true);

    Request request = new Request(source, "select * from actor");
    RequestAssert requestAssert = assertThat(request);
    RequestColumnAssert requestColumnAssert = requestAssert.column();
    Assertions.assertThat(fieldIndex.get(requestColumnAssert)).isEqualTo(0);
    RequestColumnValueAssert requestColumnValueAssert0 = requestColumnAssert.value(0);
    Assertions.assertThat(fieldIndex.get(requestColumnAssert)).isEqualTo(1);
    RequestColumnValueAssert requestColumnValueAssert1 = requestColumnAssert.value(1);
    Assertions.assertThat(fieldIndex.get(requestColumnAssert)).isEqualTo(2);
    RequestColumnValueAssert requestColumnValueAssert2 = requestColumnAssert.value(2);
    Assertions.assertThat(fieldIndex.get(requestColumnAssert)).isEqualTo(3);
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
    Assertions.assertThat(fieldIndex.get(requestColumnAssertBis)).isEqualTo(0);
    RequestColumnValueAssert requestColumnValueAssertBis0 = requestColumnAssertBis.value(0);
    Assertions.assertThat(fieldIndex.get(requestColumnAssertBis)).isEqualTo(1);
    RequestColumnValueAssert requestColumnValueAssertBis1 = requestColumnValueAssertBis0.value(1);
    Assertions.assertThat(fieldIndex.get(requestColumnAssertBis)).isEqualTo(2);
    RequestColumnValueAssert requestColumnValueAssertBis2 = requestColumnValueAssertBis1.value(2);
    Assertions.assertThat(fieldIndex.get(requestColumnAssertBis)).isEqualTo(3);
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

    Assertions.assertThat(fieldValue.get(requestColumnValueAssert0)).isSameAs(fieldValue.get(requestColumnValueAssertBis0)).isEqualTo(new BigDecimal("1"));
    Assertions.assertThat(fieldValue.get(requestColumnValueAssert1)).isSameAs(fieldValue.get(requestColumnValueAssertBis1)).isEqualTo(new BigDecimal("2"));
    Assertions.assertThat(fieldValue.get(requestColumnValueAssert2)).isSameAs(fieldValue.get(requestColumnValueAssertBis2)).isEqualTo(new BigDecimal("3"));
  }

  /**
   * This method tests the {@code value} with index navigation method.
   */
  @Test
  public void test_value_from_row_of_request_with_index() throws Exception {
    Field fieldIndex = AbstractSubAssert.class.getDeclaredField("indexNextValue");
    fieldIndex.setAccessible(true);
    Field fieldValue = AbstractValueAssert.class.getDeclaredField("value");
    fieldValue.setAccessible(true);

    Request request = new Request(source, "select * from actor");
    RequestAssert requestAssert = assertThat(request);
    RequestRowAssert requestRowAssert = requestAssert.row();
    Assertions.assertThat(fieldIndex.get(requestRowAssert)).isEqualTo(0);
    RequestRowValueAssert requestRowValueAssert0 = requestRowAssert.value(0);
    Assertions.assertThat(fieldIndex.get(requestRowAssert)).isEqualTo(1);
    RequestRowValueAssert requestRowValueAssert1 = requestRowAssert.value(1);
    Assertions.assertThat(fieldIndex.get(requestRowAssert)).isEqualTo(2);
    RequestRowValueAssert requestRowValueAssert2 = requestRowAssert.value(2);
    Assertions.assertThat(fieldIndex.get(requestRowAssert)).isEqualTo(3);
    RequestRowValueAssert requestRowValueAssert3 = requestRowAssert.value(3);
    Assertions.assertThat(fieldIndex.get(requestRowAssert)).isEqualTo(4);
    RequestRowValueAssert requestRowValueAssert4 = requestRowAssert.value(4);
    Assertions.assertThat(fieldIndex.get(requestRowAssert)).isEqualTo(5);
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
    Assertions.assertThat(fieldIndex.get(requestRowAssertBis)).isEqualTo(0);
    RequestRowValueAssert requestRowValueAssertBis0 = requestRowAssertBis.value(0);
    Assertions.assertThat(fieldIndex.get(requestRowAssertBis)).isEqualTo(1);
    RequestRowValueAssert requestRowValueAssertBis1 = requestRowValueAssertBis0.value(1);
    Assertions.assertThat(fieldIndex.get(requestRowAssertBis)).isEqualTo(2);
    RequestRowValueAssert requestRowValueAssertBis2 = requestRowValueAssertBis1.value(2);
    Assertions.assertThat(fieldIndex.get(requestRowAssertBis)).isEqualTo(3);
    RequestRowValueAssert requestRowValueAssertBis3 = requestRowValueAssertBis2.value(3);
    Assertions.assertThat(fieldIndex.get(requestRowAssertBis)).isEqualTo(4);
    RequestRowValueAssert requestRowValueAssertBis4 = requestRowValueAssertBis3.value(4);
    Assertions.assertThat(fieldIndex.get(requestRowAssertBis)).isEqualTo(5);
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

    Assertions.assertThat(fieldValue.get(requestRowValueAssert0)).isSameAs(fieldValue.get(requestRowValueAssertBis0)).isEqualTo(new BigDecimal("1"));
    Assertions.assertThat(fieldValue.get(requestRowValueAssert1)).isSameAs(fieldValue.get(requestRowValueAssertBis1)).isEqualTo("Weaver");
    Assertions.assertThat(fieldValue.get(requestRowValueAssert2)).isSameAs(fieldValue.get(requestRowValueAssertBis2)).isEqualTo("Sigourney");
    Assertions.assertThat(fieldValue.get(requestRowValueAssert3)).isSameAs(fieldValue.get(requestRowValueAssertBis3)).isEqualTo(Date.valueOf("1949-10-08"));
    Assertions.assertThat(fieldValue.get(requestRowValueAssert4)).isSameAs(fieldValue.get(requestRowValueAssertBis4)).isEqualTo(UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"));
  }
}
