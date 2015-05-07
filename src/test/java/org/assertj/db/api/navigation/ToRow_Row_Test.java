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
import org.assertj.db.exception.AssertJDBException;
import org.assertj.db.type.Request;
import org.assertj.db.type.Row;
import org.assertj.db.type.Table;
import org.junit.Test;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Date;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on {@link ToRow} class :
 * {@link ToRow#row()} method.
 *
 * @author Régis Pouiller
 *
 */
public class ToRow_Row_Test extends AbstractTest {

  /**
   * This method tests the {@code row} navigation method.
   */
  @Test
  public void test_column_from_table() throws Exception {
    Field fieldIndex = AbstractDbAssert.class.getDeclaredField("indexNextRow");
    fieldIndex.setAccessible(true);
    Field fieldRow = AbstractRowAssert.class.getDeclaredField("row");
    fieldRow.setAccessible(true);

    Table table = new Table(source, "actor");
    TableAssert tableAssert = assertThat(table);
    Assertions.assertThat(fieldIndex.get(tableAssert)).isEqualTo(0);
    TableRowAssert tableRowAssert0 = tableAssert.row();
    Assertions.assertThat(fieldIndex.get(tableAssert)).isEqualTo(1);
    TableRowAssert tableRowAssert1 = tableAssert.row();
    Assertions.assertThat(fieldIndex.get(tableAssert)).isEqualTo(2);
    TableRowAssert tableRowAssert2 = tableAssert.row();
    Assertions.assertThat(fieldIndex.get(tableAssert)).isEqualTo(3);
    try {
      tableAssert.row();
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 3 out of the limits [0, 3[");
    }

    TableAssert tableAssertBis = assertThat(table);
    Assertions.assertThat(fieldIndex.get(tableAssertBis)).isEqualTo(0);
    TableRowAssert tableRowAssertBis0 = tableAssertBis.row();
    Assertions.assertThat(fieldIndex.get(tableAssertBis)).isEqualTo(1);
    TableRowAssert tableRowAssertBis1 = tableRowAssertBis0.row();
    Assertions.assertThat(fieldIndex.get(tableAssertBis)).isEqualTo(2);
    TableRowAssert tableRowAssertBis2 = tableRowAssertBis1.row();
    Assertions.assertThat(fieldIndex.get(tableAssertBis)).isEqualTo(3);
    try {
      tableRowAssertBis2.row();
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 3 out of the limits [0, 3[");
    }

    Row rowId0 = (Row) fieldRow.get(tableRowAssert0);
    Row rowId1 = (Row) fieldRow.get(tableRowAssert1);
    Row rowId2 = (Row) fieldRow.get(tableRowAssert2);
    Row rowIdBis0 = (Row) fieldRow.get(tableRowAssertBis0);
    Row rowIdBis1 = (Row) fieldRow.get(tableRowAssertBis1);
    Row rowIdBis2 = (Row) fieldRow.get(tableRowAssertBis2);

    Assertions.assertThat(rowId0.getValuesList()).isEqualTo(rowIdBis0.getValuesList())
              .containsExactly(new BigDecimal("1"), "Weaver", "Sigourney", Date.valueOf("1949-10-08"));
    Assertions.assertThat(rowId1.getValuesList()).isEqualTo(rowIdBis1.getValuesList())
              .containsExactly(new BigDecimal("2"), "Phoenix", "Joaquim", Date.valueOf("1974-10-28"));
    Assertions.assertThat(rowId2.getValuesList()).isEqualTo(rowIdBis2.getValuesList())
              .containsExactly(new BigDecimal("3"), "Worthington", "Sam", Date.valueOf("1976-08-02"));
  }

  /**
   * This method tests the {@code row} navigation method.
   */
  @Test
  public void test_column_from_request() throws Exception {
    Field fieldIndex = AbstractDbAssert.class.getDeclaredField("indexNextRow");
    fieldIndex.setAccessible(true);
    Field fieldRow = AbstractRowAssert.class.getDeclaredField("row");
    fieldRow.setAccessible(true);

    Request request = new Request(source, "select * from actor");
    RequestAssert requestAssert = assertThat(request);
    Assertions.assertThat(fieldIndex.get(requestAssert)).isEqualTo(0);
    RequestRowAssert requestRowAssert0 = requestAssert.row();
    Assertions.assertThat(fieldIndex.get(requestAssert)).isEqualTo(1);
    RequestRowAssert requestRowAssert1 = requestAssert.row();
    Assertions.assertThat(fieldIndex.get(requestAssert)).isEqualTo(2);
    RequestRowAssert requestRowAssert2 = requestAssert.row();
    Assertions.assertThat(fieldIndex.get(requestAssert)).isEqualTo(3);
    try {
      requestAssert.row();
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 3 out of the limits [0, 3[");
    }

    RequestAssert requestAssertBis = assertThat(request);
    Assertions.assertThat(fieldIndex.get(requestAssertBis)).isEqualTo(0);
    RequestRowAssert requestRowAssertBis0 = requestAssertBis.row();
    Assertions.assertThat(fieldIndex.get(requestAssertBis)).isEqualTo(1);
    RequestRowAssert requestRowAssertBis1 = requestRowAssertBis0.row();
    Assertions.assertThat(fieldIndex.get(requestAssertBis)).isEqualTo(2);
    RequestRowAssert requestRowAssertBis2 = requestRowAssertBis1.row();
    Assertions.assertThat(fieldIndex.get(requestAssertBis)).isEqualTo(3);
    try {
      requestRowAssertBis2.row();
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 3 out of the limits [0, 3[");
    }

    Row rowId0 = (Row) fieldRow.get(requestRowAssert0);
    Row rowId1 = (Row) fieldRow.get(requestRowAssert1);
    Row rowId2 = (Row) fieldRow.get(requestRowAssert2);
    Row rowIdBis0 = (Row) fieldRow.get(requestRowAssertBis0);
    Row rowIdBis1 = (Row) fieldRow.get(requestRowAssertBis1);
    Row rowIdBis2 = (Row) fieldRow.get(requestRowAssertBis2);

    Assertions.assertThat(rowId0.getValuesList()).isEqualTo(rowIdBis0.getValuesList())
              .containsExactly(new BigDecimal("1"), "Weaver", "Sigourney", Date.valueOf("1949-10-08"));
    Assertions.assertThat(rowId1.getValuesList()).isEqualTo(rowIdBis1.getValuesList())
              .containsExactly(new BigDecimal("2"), "Phoenix", "Joaquim", Date.valueOf("1974-10-28"));
    Assertions.assertThat(rowId2.getValuesList()).isEqualTo(rowIdBis2.getValuesList())
              .containsExactly(new BigDecimal("3"), "Worthington", "Sam", Date.valueOf("1976-08-02"));
  }
}
