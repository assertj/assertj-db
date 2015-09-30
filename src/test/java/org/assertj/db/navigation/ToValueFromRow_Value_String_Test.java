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

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on {@link org.assertj.db.navigation.ToValueFromRow} class :
 * {@link org.assertj.db.navigation.ToValueFromRow#value(String)} method.
 *
 * @author Régis Pouiller
 *
 */
public class ToValueFromRow_Value_String_Test extends AbstractTest {

  /**
   * This method tests the {@code value} navigation method.
   */
  @Test
  @NeedReload
  public void test_value_from_row_of_change_with_column_name() throws Exception {
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
    ChangeRowValueAssert changeRowValueAssert0 = changeRowAssert.value("ID");
    Assertions.assertThat(fieldIndex.get(changeRowAssert)).isEqualTo(1);
    ChangeRowValueAssert changeRowValueAssert1 = changeRowAssert.value("NAME");
    Assertions.assertThat(fieldIndex.get(changeRowAssert)).isEqualTo(2);
    ChangeRowValueAssert changeRowValueAssert2 = changeRowAssert.value("FIRSTNAME");
    Assertions.assertThat(fieldIndex.get(changeRowAssert)).isEqualTo(3);
    ChangeRowValueAssert changeRowValueAssert3 = changeRowAssert.value("BIRTH");
    try {
      changeRowAssert.value("TEST");
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Column <TEST> does not exist");
    }
    try {
      changeRowAssert.value(null);
      fail("An exception must be raised");
    } catch (NullPointerException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Column name must be not null");
    }

    ChangesAssert changesAssertBis = assertThat(changes);
    ChangeAssert changeAssertBis = changesAssertBis.change();
    ChangeRowAssert changeRowAssertBis = changeAssertBis.rowAtEndPoint();
    Assertions.assertThat(fieldIndex.get(changeRowAssertBis)).isEqualTo(0);
    ChangeRowValueAssert changeRowValueAssertBis0 = changeRowAssertBis.value("ID");
    Assertions.assertThat(fieldIndex.get(changeRowAssertBis)).isEqualTo(1);
    ChangeRowValueAssert changeRowValueAssertBis1 = changeRowValueAssertBis0.value("NAME");
    Assertions.assertThat(fieldIndex.get(changeRowAssertBis)).isEqualTo(2);
    ChangeRowValueAssert changeRowValueAssertBis2 = changeRowValueAssertBis1.value("FIRSTNAME");
    Assertions.assertThat(fieldIndex.get(changeRowAssertBis)).isEqualTo(3);
    ChangeRowValueAssert changeRowValueAssertBis3 = changeRowValueAssertBis2.value("BIRTH");
    try {
      changeRowValueAssertBis3.value("TEST");
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Column <TEST> does not exist");
    }
    try {
      changeRowValueAssertBis3.value(null);
      fail("An exception must be raised");
    } catch (NullPointerException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Column name must be not null");
    }

    Assertions.assertThat(((Value) fieldValue.get(changeRowValueAssert0)).getValue())
              .isSameAs(((Value) fieldValue.get(changeRowValueAssertBis0)).getValue())
              .isEqualTo(new BigDecimal("4"));
    Assertions.assertThat(((Value) fieldValue.get(changeRowValueAssert1)).getValue())
              .isSameAs(((Value) fieldValue.get(changeRowValueAssertBis1)).getValue()).isEqualTo("Murray");
    Assertions.assertThat(((Value) fieldValue.get(changeRowValueAssert2)).getValue())
              .isSameAs(((Value) fieldValue.get(changeRowValueAssertBis2)).getValue())
    .isEqualTo("Bill");
    Assertions.assertThat(((Value) fieldValue.get(changeRowValueAssert3)).getValue())
              .isSameAs(((Value) fieldValue.get(changeRowValueAssertBis3)).getValue())
              .isEqualTo(Date.valueOf("1950-09-21"));
  }

  /**
   * This method tests the {@code value} navigation method.
   */
  @Test
  public void test_value_from_row_of_table() throws Exception {
    Field fieldIndex = AbstractSubAssert.class.getDeclaredField("indexNextValue");
    fieldIndex.setAccessible(true);
    Field fieldValue = AbstractValueAssert.class.getDeclaredField("value");
    fieldValue.setAccessible(true);

    Table table = new Table(source, "actor");
    TableAssert tableAssert = assertThat(table);
    TableRowAssert tableRowAssert = tableAssert.row();
    Assertions.assertThat(fieldIndex.get(tableRowAssert)).isEqualTo(0);
    TableRowValueAssert tableRowValueAssert0 = tableRowAssert.value("ID");
    Assertions.assertThat(fieldIndex.get(tableRowAssert)).isEqualTo(1);
    TableRowValueAssert tableRowValueAssert1 = tableRowAssert.value("NAME");
    Assertions.assertThat(fieldIndex.get(tableRowAssert)).isEqualTo(2);
    TableRowValueAssert tableRowValueAssert2 = tableRowAssert.value("FIRSTNAME");
    Assertions.assertThat(fieldIndex.get(tableRowAssert)).isEqualTo(3);
    TableRowValueAssert tableRowValueAssert3 = tableRowAssert.value("BIRTH");
    Assertions.assertThat(fieldIndex.get(tableRowAssert)).isEqualTo(4);
    try {
      tableRowAssert.value("TEST");
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Column <TEST> does not exist");
    }
    try {
      tableRowAssert.value(null);
      fail("An exception must be raised");
    } catch (NullPointerException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Column name must be not null");
    }

    TableAssert tableAssertBis = assertThat(table);
    TableRowAssert tableRowAssertBis = tableAssertBis.row();
    Assertions.assertThat(fieldIndex.get(tableRowAssertBis)).isEqualTo(0);
    TableRowValueAssert tableRowValueAssertBis0 = tableRowAssertBis.value("ID");
    Assertions.assertThat(fieldIndex.get(tableRowAssertBis)).isEqualTo(1);
    TableRowValueAssert tableRowValueAssertBis1 = tableRowValueAssertBis0.value("NAME");
    Assertions.assertThat(fieldIndex.get(tableRowAssertBis)).isEqualTo(2);
    TableRowValueAssert tableRowValueAssertBis2 = tableRowValueAssertBis1.value("FIRSTNAME");
    Assertions.assertThat(fieldIndex.get(tableRowAssertBis)).isEqualTo(3);
    TableRowValueAssert tableRowValueAssertBis3 = tableRowValueAssertBis2.value("BIRTH");
    Assertions.assertThat(fieldIndex.get(tableRowAssertBis)).isEqualTo(4);
    try {
      tableRowValueAssertBis3.value("TEST");
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Column <TEST> does not exist");
    }
    try {
      tableRowValueAssertBis3.value(null);
      fail("An exception must be raised");
    } catch (NullPointerException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Column name must be not null");
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
  }

  /**
   * This method tests the {@code value} navigation method.
   */
  @Test
  public void test_value_from_row_of_request() throws Exception {
    Field fieldIndex = AbstractSubAssert.class.getDeclaredField("indexNextValue");
    fieldIndex.setAccessible(true);
    Field fieldValue = AbstractValueAssert.class.getDeclaredField("value");
    fieldValue.setAccessible(true);

    Request request = new Request(source, "select * from actor");
    RequestAssert requestAssert = assertThat(request);
    RequestRowAssert requestRowAssert = requestAssert.row();
    Assertions.assertThat(fieldIndex.get(requestRowAssert)).isEqualTo(0);
    RequestRowValueAssert requestRowValueAssert0 = requestRowAssert.value("ID");
    Assertions.assertThat(fieldIndex.get(requestRowAssert)).isEqualTo(1);
    RequestRowValueAssert requestRowValueAssert1 = requestRowAssert.value("NAME");
    Assertions.assertThat(fieldIndex.get(requestRowAssert)).isEqualTo(2);
    RequestRowValueAssert requestRowValueAssert2 = requestRowAssert.value("FIRSTNAME");
    Assertions.assertThat(fieldIndex.get(requestRowAssert)).isEqualTo(3);
    RequestRowValueAssert requestRowValueAssert3 = requestRowAssert.value("BIRTH");
    Assertions.assertThat(fieldIndex.get(requestRowAssert)).isEqualTo(4);
    try {
      requestRowAssert.value("TEST");
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Column <TEST> does not exist");
    }
    try {
      requestRowAssert.value(null);
      fail("An exception must be raised");
    } catch (NullPointerException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Column name must be not null");
    }

    RequestAssert requestAssertBis = assertThat(request);
    RequestRowAssert requestRowAssertBis = requestAssertBis.row();
    Assertions.assertThat(fieldIndex.get(requestRowAssertBis)).isEqualTo(0);
    RequestRowValueAssert requestRowValueAssertBis0 = requestRowAssertBis.value("ID");
    Assertions.assertThat(fieldIndex.get(requestRowAssertBis)).isEqualTo(1);
    RequestRowValueAssert requestRowValueAssertBis1 = requestRowValueAssertBis0.value("NAME");
    Assertions.assertThat(fieldIndex.get(requestRowAssertBis)).isEqualTo(2);
    RequestRowValueAssert requestRowValueAssertBis2 = requestRowValueAssertBis1.value("FIRSTNAME");
    Assertions.assertThat(fieldIndex.get(requestRowAssertBis)).isEqualTo(3);
    RequestRowValueAssert requestRowValueAssertBis3 = requestRowValueAssertBis2.value("BIRTH");
    Assertions.assertThat(fieldIndex.get(requestRowAssertBis)).isEqualTo(4);
    try {
      requestRowValueAssertBis3.value("TEST");
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Column <TEST> does not exist");
    }
    try {
      requestRowValueAssertBis3.value(null);
      fail("An exception must be raised");
    } catch (NullPointerException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Column name must be not null");
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
  }
}
