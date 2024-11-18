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
 * Copyright 2015-2024 the original author or authors.
 */
package org.assertj.db.navigation;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.assertj.db.api.AbstractDbAssert;
import org.assertj.db.api.AbstractRowAssert;
import org.assertj.db.api.RequestAssert;
import org.assertj.db.api.RequestRowAssert;
import org.assertj.db.api.TableAssert;
import org.assertj.db.api.TableRowAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.exception.AssertJDBException;
import org.assertj.db.output.AbstractDbOutputter;
import org.assertj.db.output.AbstractRowOutputter;
import org.assertj.db.output.Outputs;
import org.assertj.db.output.RequestOutputter;
import org.assertj.db.output.RequestRowOutputter;
import org.assertj.db.output.TableOutputter;
import org.assertj.db.output.TableRowOutputter;
import org.assertj.db.type.Request;
import org.assertj.db.type.Row;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Tests on {@link org.assertj.db.navigation.ToRow} class :
 * {@link org.assertj.db.navigation.ToRow#row()} method.
 *
 * @author Régis Pouiller
 * @author Julien Roy
 */
public class ToRow_Row_Test extends AbstractTest {

  /**
   * This method tests the {@code row} navigation method.
   */
  @Test
  public void test_row_from_table_with_assertions() throws Exception {
    Field fieldPosition = AbstractDbAssert.class.getDeclaredField("rowPosition");
    fieldPosition.setAccessible(true);
    Field fieldRow = AbstractRowAssert.class.getDeclaredField("row");
    fieldRow.setAccessible(true);
    Field fieldIndex = Position.class.getDeclaredField("nextIndex");
    fieldIndex.setAccessible(true);

    Table table = new Table(jdbcConnectionProvider, "actor");
    TableAssert tableAssert = assertThat(table);
    Position<TableAssert, TableRowAssert, Row> position =
      (Position) fieldPosition.get(tableAssert);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(0);
    TableRowAssert tableRowAssert0 = tableAssert.row();
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(1);
    TableRowAssert tableRowAssert1 = tableAssert.row();
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(2);
    TableRowAssert tableRowAssert2 = tableAssert.row();
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(3);
    try {
      tableAssert.row();
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 3 out of the limits [0, 3[");
    }

    TableAssert tableAssertBis = assertThat(table);
    Position<TableAssert, TableRowAssert, Row> positionBis =
      (Position) fieldPosition.get(tableAssertBis);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(0);
    TableRowAssert tableRowAssertBis0 = tableAssertBis.row();
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(1);
    TableRowAssert tableRowAssertBis1 = tableRowAssertBis0.row();
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(2);
    TableRowAssert tableRowAssertBis2 = tableRowAssertBis1.row();
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(3);
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

    Assertions.assertThat(rowId0.getValuesList().get(0).getValue()).isEqualTo(rowIdBis0.getValuesList().get(0).getValue())
      .isEqualTo(new BigDecimal("1"));
    Assertions.assertThat(rowId0.getValuesList().get(1).getValue()).isEqualTo(rowIdBis0.getValuesList().get(1).getValue())
      .isEqualTo("Weaver");
    Assertions.assertThat(rowId0.getValuesList().get(2).getValue()).isEqualTo(rowIdBis0.getValuesList().get(2).getValue())
      .isEqualTo("Sigourney");
    Assertions.assertThat(rowId0.getValuesList().get(3).getValue()).isEqualTo(rowIdBis0.getValuesList().get(3).getValue())
      .isEqualTo(Date.valueOf("1949-10-08"));
    Assertions.assertThat(rowId0.getValuesList().get(4).getValue()).isEqualTo(rowIdBis0.getValuesList().get(4).getValue())
      .isEqualTo(UUID.fromString("30b443ae-c0c9-4790-9bec-ce1380808435"));
    Assertions.assertThat(rowId1.getValuesList().get(0).getValue()).isEqualTo(rowIdBis1.getValuesList().get(0).getValue())
      .isEqualTo(new BigDecimal("2"));
    Assertions.assertThat(rowId1.getValuesList().get(1).getValue()).isEqualTo(rowIdBis1.getValuesList().get(1).getValue())
      .isEqualTo("Phoenix");
    Assertions.assertThat(rowId1.getValuesList().get(2).getValue()).isEqualTo(rowIdBis1.getValuesList().get(2).getValue())
      .isEqualTo("Joaquim");
    Assertions.assertThat(rowId1.getValuesList().get(3).getValue()).isEqualTo(rowIdBis1.getValuesList().get(3).getValue())
      .isEqualTo(Date.valueOf("1974-10-28"));
    Assertions.assertThat(rowId1.getValuesList().get(4).getValue()).isEqualTo(rowIdBis1.getValuesList().get(4).getValue())
      .isEqualTo(UUID.fromString("16319617-AE95-4087-9264-D3D21BF611B6"));
    Assertions.assertThat(rowId2.getValuesList().get(0).getValue()).isEqualTo(rowIdBis2.getValuesList().get(0).getValue())
      .isEqualTo(new BigDecimal("3"));
    Assertions.assertThat(rowId2.getValuesList().get(1).getValue()).isEqualTo(rowIdBis2.getValuesList().get(1).getValue())
      .isEqualTo("Worthington");
    Assertions.assertThat(rowId2.getValuesList().get(2).getValue()).isEqualTo(rowIdBis2.getValuesList().get(2).getValue())
      .isEqualTo("Sam");
    Assertions.assertThat(rowId2.getValuesList().get(3).getValue()).isEqualTo(rowIdBis2.getValuesList().get(3).getValue())
      .isEqualTo(Date.valueOf("1976-08-02"));
    Assertions.assertThat(rowId2.getValuesList().get(4).getValue()).isEqualTo(rowIdBis2.getValuesList().get(4).getValue())
      .isEqualTo(UUID.fromString("D735221B-5DE5-4112-AA1E-49090CB75ADA"));
  }

  /**
   * This method tests the {@code row} navigation method.
   */
  @Test
  public void test_row_from_request_with_assertions() throws Exception {
    Field fieldPosition = AbstractDbAssert.class.getDeclaredField("rowPosition");
    fieldPosition.setAccessible(true);
    Field fieldRow = AbstractRowAssert.class.getDeclaredField("row");
    fieldRow.setAccessible(true);
    Field fieldIndex = Position.class.getDeclaredField("nextIndex");
    fieldIndex.setAccessible(true);

    Request request = new Request(jdbcConnectionProvider, "select * from actor");
    RequestAssert requestAssert = assertThat(request);
    Position<RequestAssert, RequestRowAssert, Row> position =
      (Position) fieldPosition.get(requestAssert);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(0);
    RequestRowAssert requestRowAssert0 = requestAssert.row();
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(1);
    RequestRowAssert requestRowAssert1 = requestAssert.row();
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(2);
    RequestRowAssert requestRowAssert2 = requestAssert.row();
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(3);
    try {
      requestAssert.row();
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 3 out of the limits [0, 3[");
    }

    RequestAssert requestAssertBis = assertThat(request);
    Position<RequestAssert, RequestRowAssert, Row> positionBis =
      (Position) fieldPosition.get(requestAssertBis);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(0);
    RequestRowAssert requestRowAssertBis0 = requestAssertBis.row();
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(1);
    RequestRowAssert requestRowAssertBis1 = requestRowAssertBis0.row();
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(2);
    RequestRowAssert requestRowAssertBis2 = requestRowAssertBis1.row();
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(3);
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

    Assertions.assertThat(rowId0.getValuesList().get(0).getValue()).isEqualTo(rowIdBis0.getValuesList().get(0).getValue())
      .isEqualTo(new BigDecimal("1"));
    Assertions.assertThat(rowId0.getValuesList().get(1).getValue()).isEqualTo(rowIdBis0.getValuesList().get(1).getValue())
      .isEqualTo("Weaver");
    Assertions.assertThat(rowId0.getValuesList().get(2).getValue()).isEqualTo(rowIdBis0.getValuesList().get(2).getValue())
      .isEqualTo("Sigourney");
    Assertions.assertThat(rowId0.getValuesList().get(3).getValue()).isEqualTo(rowIdBis0.getValuesList().get(3).getValue())
      .isEqualTo(Date.valueOf("1949-10-08"));
    Assertions.assertThat(rowId0.getValuesList().get(4).getValue()).isEqualTo(rowIdBis0.getValuesList().get(4).getValue())
      .isEqualTo(UUID.fromString("30b443ae-c0c9-4790-9bec-ce1380808435"));
    Assertions.assertThat(rowId1.getValuesList().get(0).getValue()).isEqualTo(rowIdBis1.getValuesList().get(0).getValue())
      .isEqualTo(new BigDecimal("2"));
    Assertions.assertThat(rowId1.getValuesList().get(1).getValue()).isEqualTo(rowIdBis1.getValuesList().get(1).getValue())
      .isEqualTo("Phoenix");
    Assertions.assertThat(rowId1.getValuesList().get(2).getValue()).isEqualTo(rowIdBis1.getValuesList().get(2).getValue())
      .isEqualTo("Joaquim");
    Assertions.assertThat(rowId1.getValuesList().get(3).getValue()).isEqualTo(rowIdBis1.getValuesList().get(3).getValue())
      .isEqualTo(Date.valueOf("1974-10-28"));
    Assertions.assertThat(rowId1.getValuesList().get(4).getValue()).isEqualTo(rowIdBis1.getValuesList().get(4).getValue())
      .isEqualTo(UUID.fromString("16319617-AE95-4087-9264-D3D21BF611B6"));
    Assertions.assertThat(rowId2.getValuesList().get(0).getValue()).isEqualTo(rowIdBis2.getValuesList().get(0).getValue())
      .isEqualTo(new BigDecimal("3"));
    Assertions.assertThat(rowId2.getValuesList().get(1).getValue()).isEqualTo(rowIdBis2.getValuesList().get(1).getValue())
      .isEqualTo("Worthington");
    Assertions.assertThat(rowId2.getValuesList().get(2).getValue()).isEqualTo(rowIdBis2.getValuesList().get(2).getValue())
      .isEqualTo("Sam");
    Assertions.assertThat(rowId2.getValuesList().get(3).getValue()).isEqualTo(rowIdBis2.getValuesList().get(3).getValue())
      .isEqualTo(Date.valueOf("1976-08-02"));
    Assertions.assertThat(rowId2.getValuesList().get(4).getValue()).isEqualTo(rowIdBis2.getValuesList().get(4).getValue())
      .isEqualTo(UUID.fromString("D735221B-5DE5-4112-AA1E-49090CB75ADA"));
  }

  /**
   * This method tests the {@code row} navigation method.
   */
  @Test
  public void test_row_from_table_with_displays() throws Exception {
    Field fieldPosition = AbstractDbOutputter.class.getDeclaredField("rowPosition");
    fieldPosition.setAccessible(true);
    Field fieldRow = AbstractRowOutputter.class.getDeclaredField("row");
    fieldRow.setAccessible(true);
    Field fieldIndex = Position.class.getDeclaredField("nextIndex");
    fieldIndex.setAccessible(true);

    Table table = new Table(jdbcConnectionProvider, "actor");
    TableOutputter tableOutputter = Outputs.output(table);
    Position<TableOutputter, TableRowOutputter, Row> position =
      (Position) fieldPosition.get(tableOutputter);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(0);
    TableRowOutputter tableRowOutputter0 = tableOutputter.row();
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(1);
    TableRowOutputter tableRowOutputter1 = tableOutputter.row();
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(2);
    TableRowOutputter tableRowOutputter2 = tableOutputter.row();
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(3);
    try {
      tableOutputter.row();
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 3 out of the limits [0, 3[");
    }

    TableOutputter tableOutputterBis = Outputs.output(table);
    Position<TableOutputter, TableRowOutputter, Row> positionBis =
      (Position) fieldPosition.get(tableOutputterBis);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(0);
    TableRowOutputter tableRowOutputterBis0 = tableOutputterBis.row();
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(1);
    TableRowOutputter tableRowOutputterBis1 = tableRowOutputterBis0.row();
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(2);
    TableRowOutputter tableRowOutputterBis2 = tableRowOutputterBis1.row();
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(3);
    try {
      tableRowOutputterBis2.row();
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 3 out of the limits [0, 3[");
    }

    Row rowId0 = (Row) fieldRow.get(tableRowOutputter0);
    Row rowId1 = (Row) fieldRow.get(tableRowOutputter1);
    Row rowId2 = (Row) fieldRow.get(tableRowOutputter2);
    Row rowIdBis0 = (Row) fieldRow.get(tableRowOutputterBis0);
    Row rowIdBis1 = (Row) fieldRow.get(tableRowOutputterBis1);
    Row rowIdBis2 = (Row) fieldRow.get(tableRowOutputterBis2);

    Assertions.assertThat(rowId0.getValuesList().get(0).getValue()).isEqualTo(rowIdBis0.getValuesList().get(0).getValue())
      .isEqualTo(new BigDecimal("1"));
    Assertions.assertThat(rowId0.getValuesList().get(1).getValue()).isEqualTo(rowIdBis0.getValuesList().get(1).getValue())
      .isEqualTo("Weaver");
    Assertions.assertThat(rowId0.getValuesList().get(2).getValue()).isEqualTo(rowIdBis0.getValuesList().get(2).getValue())
      .isEqualTo("Sigourney");
    Assertions.assertThat(rowId0.getValuesList().get(3).getValue()).isEqualTo(rowIdBis0.getValuesList().get(3).getValue())
      .isEqualTo(Date.valueOf("1949-10-08"));
    Assertions.assertThat(rowId0.getValuesList().get(4).getValue()).isEqualTo(rowIdBis0.getValuesList().get(4).getValue())
      .isEqualTo(UUID.fromString("30b443ae-c0c9-4790-9bec-ce1380808435"));
    Assertions.assertThat(rowId1.getValuesList().get(0).getValue()).isEqualTo(rowIdBis1.getValuesList().get(0).getValue())
      .isEqualTo(new BigDecimal("2"));
    Assertions.assertThat(rowId1.getValuesList().get(1).getValue()).isEqualTo(rowIdBis1.getValuesList().get(1).getValue())
      .isEqualTo("Phoenix");
    Assertions.assertThat(rowId1.getValuesList().get(2).getValue()).isEqualTo(rowIdBis1.getValuesList().get(2).getValue())
      .isEqualTo("Joaquim");
    Assertions.assertThat(rowId1.getValuesList().get(3).getValue()).isEqualTo(rowIdBis1.getValuesList().get(3).getValue())
      .isEqualTo(Date.valueOf("1974-10-28"));
    Assertions.assertThat(rowId1.getValuesList().get(4).getValue()).isEqualTo(rowIdBis1.getValuesList().get(4).getValue())
      .isEqualTo(UUID.fromString("16319617-AE95-4087-9264-D3D21BF611B6"));
    Assertions.assertThat(rowId2.getValuesList().get(0).getValue()).isEqualTo(rowIdBis2.getValuesList().get(0).getValue())
      .isEqualTo(new BigDecimal("3"));
    Assertions.assertThat(rowId2.getValuesList().get(1).getValue()).isEqualTo(rowIdBis2.getValuesList().get(1).getValue())
      .isEqualTo("Worthington");
    Assertions.assertThat(rowId2.getValuesList().get(2).getValue()).isEqualTo(rowIdBis2.getValuesList().get(2).getValue())
      .isEqualTo("Sam");
    Assertions.assertThat(rowId2.getValuesList().get(3).getValue()).isEqualTo(rowIdBis2.getValuesList().get(3).getValue())
      .isEqualTo(Date.valueOf("1976-08-02"));
    Assertions.assertThat(rowId2.getValuesList().get(4).getValue()).isEqualTo(rowIdBis2.getValuesList().get(4).getValue())
      .isEqualTo(UUID.fromString("D735221B-5DE5-4112-AA1E-49090CB75ADA"));
  }

  /**
   * This method tests the {@code row} navigation method.
   */
  @Test
  public void test_row_from_request_with_displays() throws Exception {
    Field fieldPosition = AbstractDbOutputter.class.getDeclaredField("rowPosition");
    fieldPosition.setAccessible(true);
    Field fieldRow = AbstractRowOutputter.class.getDeclaredField("row");
    fieldRow.setAccessible(true);
    Field fieldIndex = Position.class.getDeclaredField("nextIndex");
    fieldIndex.setAccessible(true);

    Request request = new Request(jdbcConnectionProvider, "select * from actor");
    RequestOutputter requestOutputter = Outputs.output(request);
    Position<RequestOutputter, RequestRowOutputter, Row> position =
      (Position) fieldPosition.get(requestOutputter);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(0);
    RequestRowOutputter requestRowOutputter0 = requestOutputter.row();
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(1);
    RequestRowOutputter requestRowOutputter1 = requestOutputter.row();
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(2);
    RequestRowOutputter requestRowOutputter2 = requestOutputter.row();
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(3);
    try {
      requestOutputter.row();
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 3 out of the limits [0, 3[");
    }

    RequestOutputter requestOutputterBis = Outputs.output(request);
    Position<RequestOutputter, RequestRowOutputter, Row> positionBis =
      (Position) fieldPosition.get(requestOutputterBis);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(0);
    RequestRowOutputter requestRowOutputterBis0 = requestOutputterBis.row();
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(1);
    RequestRowOutputter requestRowOutputterBis1 = requestRowOutputterBis0.row();
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(2);
    RequestRowOutputter requestRowOutputterBis2 = requestRowOutputterBis1.row();
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(3);
    try {
      requestRowOutputterBis2.row();
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 3 out of the limits [0, 3[");
    }

    Row rowId0 = (Row) fieldRow.get(requestRowOutputter0);
    Row rowId1 = (Row) fieldRow.get(requestRowOutputter1);
    Row rowId2 = (Row) fieldRow.get(requestRowOutputter2);
    Row rowIdBis0 = (Row) fieldRow.get(requestRowOutputterBis0);
    Row rowIdBis1 = (Row) fieldRow.get(requestRowOutputterBis1);
    Row rowIdBis2 = (Row) fieldRow.get(requestRowOutputterBis2);

    Assertions.assertThat(rowId0.getValuesList().get(0).getValue()).isEqualTo(rowIdBis0.getValuesList().get(0).getValue())
      .isEqualTo(new BigDecimal("1"));
    Assertions.assertThat(rowId0.getValuesList().get(1).getValue()).isEqualTo(rowIdBis0.getValuesList().get(1).getValue())
      .isEqualTo("Weaver");
    Assertions.assertThat(rowId0.getValuesList().get(2).getValue()).isEqualTo(rowIdBis0.getValuesList().get(2).getValue())
      .isEqualTo("Sigourney");
    Assertions.assertThat(rowId0.getValuesList().get(3).getValue()).isEqualTo(rowIdBis0.getValuesList().get(3).getValue())
      .isEqualTo(Date.valueOf("1949-10-08"));
    Assertions.assertThat(rowId0.getValuesList().get(4).getValue()).isEqualTo(rowIdBis0.getValuesList().get(4).getValue())
      .isEqualTo(UUID.fromString("30b443ae-c0c9-4790-9bec-ce1380808435"));
    Assertions.assertThat(rowId1.getValuesList().get(0).getValue()).isEqualTo(rowIdBis1.getValuesList().get(0).getValue())
      .isEqualTo(new BigDecimal("2"));
    Assertions.assertThat(rowId1.getValuesList().get(1).getValue()).isEqualTo(rowIdBis1.getValuesList().get(1).getValue())
      .isEqualTo("Phoenix");
    Assertions.assertThat(rowId1.getValuesList().get(2).getValue()).isEqualTo(rowIdBis1.getValuesList().get(2).getValue())
      .isEqualTo("Joaquim");
    Assertions.assertThat(rowId1.getValuesList().get(3).getValue()).isEqualTo(rowIdBis1.getValuesList().get(3).getValue())
      .isEqualTo(Date.valueOf("1974-10-28"));
    Assertions.assertThat(rowId1.getValuesList().get(4).getValue()).isEqualTo(rowIdBis1.getValuesList().get(4).getValue())
      .isEqualTo(UUID.fromString("16319617-AE95-4087-9264-D3D21BF611B6"));
    Assertions.assertThat(rowId2.getValuesList().get(0).getValue()).isEqualTo(rowIdBis2.getValuesList().get(0).getValue())
      .isEqualTo(new BigDecimal("3"));
    Assertions.assertThat(rowId2.getValuesList().get(1).getValue()).isEqualTo(rowIdBis2.getValuesList().get(1).getValue())
      .isEqualTo("Worthington");
    Assertions.assertThat(rowId2.getValuesList().get(2).getValue()).isEqualTo(rowIdBis2.getValuesList().get(2).getValue())
      .isEqualTo("Sam");
    Assertions.assertThat(rowId2.getValuesList().get(3).getValue()).isEqualTo(rowIdBis2.getValuesList().get(3).getValue())
      .isEqualTo(Date.valueOf("1976-08-02"));
    Assertions.assertThat(rowId2.getValuesList().get(4).getValue()).isEqualTo(rowIdBis2.getValuesList().get(4).getValue())
      .isEqualTo(UUID.fromString("D735221B-5DE5-4112-AA1E-49090CB75ADA"));
  }
}
