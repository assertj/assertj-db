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
import org.assertj.db.type.Column;
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
  public void test_column_with_index_from_change() throws Exception {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    Field fieldIndex = ChangeAssert.class.getDeclaredField("indexNextColumn");
    fieldIndex.setAccessible(true);
    Field fieldColumnName = ChangeColumnAssert.class.getDeclaredField("columnName");
    fieldColumnName.setAccessible(true);
    Field fieldValueAtStartPoint = ChangeColumnAssert.class.getDeclaredField("valueAtStartPoint");
    fieldValueAtStartPoint.setAccessible(true);
    Field fieldValueAtEndPoint = ChangeColumnAssert.class.getDeclaredField("valueAtEndPoint");
    fieldValueAtEndPoint.setAccessible(true);

    ChangesAssert changesAssert = assertThat(changes);
    ChangeAssert changeAssert = changesAssert.change(6);
    Assertions.assertThat(fieldIndex.get(changeAssert)).isEqualTo(0);
    ChangeColumnAssert changeColumnAssert0 = changeAssert.column(0);
    Assertions.assertThat(fieldIndex.get(changeAssert)).isEqualTo(1);
    ChangeColumnAssert changeColumnAssert1 = changeAssert.column(1);
    Assertions.assertThat(fieldIndex.get(changeAssert)).isEqualTo(2);
    ChangeColumnAssert changeColumnAssert2 = changeAssert.column(2);
    Assertions.assertThat(fieldIndex.get(changeAssert)).isEqualTo(3);
    ChangeColumnAssert changeColumnAssert3 = changeAssert.column(3);
    Assertions.assertThat(fieldIndex.get(changeAssert)).isEqualTo(4);
    ChangeColumnAssert changeColumnAssert4 = changeAssert.column(4);
    Assertions.assertThat(fieldIndex.get(changeAssert)).isEqualTo(5);
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
    Assertions.assertThat(fieldIndex.get(changeAssertBis)).isEqualTo(0);
    ChangeColumnAssert changeColumnAssertBis0 = changeAssertBis.column(0);
    Assertions.assertThat(fieldIndex.get(changeAssertBis)).isEqualTo(1);
    ChangeColumnAssert changeColumnAssertBis1 = changeColumnAssertBis0.column(1);
    Assertions.assertThat(fieldIndex.get(changeAssertBis)).isEqualTo(2);
    ChangeColumnAssert changeColumnAssertBis2 = changeColumnAssertBis1.column(2);
    Assertions.assertThat(fieldIndex.get(changeAssertBis)).isEqualTo(3);
    ChangeColumnAssert changeColumnAssertBis3 = changeColumnAssertBis2.column(3);
    Assertions.assertThat(fieldIndex.get(changeAssertBis)).isEqualTo(4);
    ChangeColumnAssert changeColumnAssertBis4 = changeColumnAssertBis3.column(4);
    Assertions.assertThat(fieldIndex.get(changeAssertBis)).isEqualTo(5);
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

    Assertions.assertThat(fieldValueAtStartPoint.get(changeColumnAssert0)).isEqualTo(fieldValueAtStartPoint.get(changeColumnAssertBis0)).isEqualTo(new BigDecimal("3"));
    Assertions.assertThat(fieldValueAtStartPoint.get(changeColumnAssert1)).isEqualTo(fieldValueAtStartPoint.get(changeColumnAssertBis1)).isEqualTo("Worthington");
    Assertions.assertThat(fieldValueAtStartPoint.get(changeColumnAssert2)).isEqualTo(fieldValueAtStartPoint.get(changeColumnAssertBis2)).isEqualTo("Sam");
    Assertions.assertThat(fieldValueAtStartPoint.get(changeColumnAssert3)).isEqualTo(fieldValueAtStartPoint.get(changeColumnAssertBis3)).isEqualTo(
            Date.valueOf("1976-08-02"));
    Assertions.assertThat(fieldValueAtStartPoint.get(changeColumnAssert4)).isEqualTo(fieldValueAtStartPoint.get(changeColumnAssertBis4)).isEqualTo(
            UUID.fromString("D735221B-5DE5-4112-AA1E-49090CB75ADA"));

    Assertions.assertThat(fieldValueAtEndPoint.get(changeColumnAssert0)).isNull();
    Assertions.assertThat(fieldValueAtEndPoint.get(changeColumnAssert1)).isNull();
    Assertions.assertThat(fieldValueAtEndPoint.get(changeColumnAssert2)).isNull();
    Assertions.assertThat(fieldValueAtEndPoint.get(changeColumnAssert3)).isNull();
    Assertions.assertThat(fieldValueAtEndPoint.get(changeColumnAssert4)).isNull();
    Assertions.assertThat(fieldValueAtEndPoint.get(changeColumnAssertBis0)).isNull();
    Assertions.assertThat(fieldValueAtEndPoint.get(changeColumnAssertBis1)).isNull();
    Assertions.assertThat(fieldValueAtEndPoint.get(changeColumnAssertBis2)).isNull();
    Assertions.assertThat(fieldValueAtEndPoint.get(changeColumnAssertBis3)).isNull();
    Assertions.assertThat(fieldValueAtEndPoint.get(changeColumnAssertBis4)).isNull();
  }

  /**
   * This method tests the {@code column} navigation method.
   */
  @Test
  public void test_column_with_index_from_table() throws Exception {
    Field fieldIndex = AbstractDbAssert.class.getDeclaredField("indexNextColumn");
    fieldIndex.setAccessible(true);
    Field fieldColumn = AbstractColumnAssert.class.getDeclaredField("column");
    fieldColumn.setAccessible(true);

    Table table = new Table(source, "actor");
    TableAssert tableAssert = assertThat(table);
    Assertions.assertThat(fieldIndex.get(tableAssert)).isEqualTo(0);
    TableColumnAssert tableColumnAssert0 = tableAssert.column(0);
    Assertions.assertThat(fieldIndex.get(tableAssert)).isEqualTo(1);
    TableColumnAssert tableColumnAssert1 = tableAssert.column(1);
    Assertions.assertThat(fieldIndex.get(tableAssert)).isEqualTo(2);
    TableColumnAssert tableColumnAssert2 = tableAssert.column(2);
    Assertions.assertThat(fieldIndex.get(tableAssert)).isEqualTo(3);
    TableColumnAssert tableColumnAssert3 = tableAssert.column(3);
    Assertions.assertThat(fieldIndex.get(tableAssert)).isEqualTo(4);
    TableColumnAssert tableColumnAssert4 = tableAssert.column(4);
    Assertions.assertThat(fieldIndex.get(tableAssert)).isEqualTo(5);
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
    Assertions.assertThat(fieldIndex.get(tableAssertBis)).isEqualTo(0);
    TableColumnAssert tableColumnAssertBis0 = tableAssertBis.column(0);
    Assertions.assertThat(fieldIndex.get(tableAssertBis)).isEqualTo(1);
    TableColumnAssert tableColumnAssertBis1 = tableColumnAssertBis0.column(1);
    Assertions.assertThat(fieldIndex.get(tableAssertBis)).isEqualTo(2);
    TableColumnAssert tableColumnAssertBis2 = tableColumnAssertBis1.column(2);
    Assertions.assertThat(fieldIndex.get(tableAssertBis)).isEqualTo(3);
    TableColumnAssert tableColumnAssertBis3 = tableColumnAssertBis2.column(3);
    Assertions.assertThat(fieldIndex.get(tableAssertBis)).isEqualTo(4);
    TableColumnAssert tableColumnAssertBis4 = tableColumnAssertBis3.column(4);
    Assertions.assertThat(fieldIndex.get(tableAssertBis)).isEqualTo(5);
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

    Assertions.assertThat(columnId0.getValuesList()).isEqualTo(columnIdBis0.getValuesList()).containsExactly(
            new BigDecimal("1"), new BigDecimal("2"), new BigDecimal("3"));
    Assertions.assertThat(columnId1.getValuesList()).isEqualTo(columnIdBis1.getValuesList()).containsExactly("Weaver",
                                                                                                             "Phoenix",
                                                                                                             "Worthington");
    Assertions
            .assertThat(columnId2.getValuesList()).isEqualTo(columnIdBis2.getValuesList()).containsExactly("Sigourney",
                                                                                                           "Joaquim",
                                                                                                           "Sam");
    Assertions.assertThat(columnId3.getValuesList()).isEqualTo(columnIdBis3.getValuesList()).containsExactly(
            Date.valueOf("1949-10-08"), Date.valueOf("1974-10-28"), Date.valueOf("1976-08-02"));
    Assertions.assertThat(columnId4.getValuesList()).isEqualTo(columnIdBis4.getValuesList()).containsExactly(
           UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"), UUID.fromString("16319617-AE95-4087-9264-D3D21BF611B6"), UUID.fromString("D735221B-5DE5-4112-AA1E-49090CB75ADA"));

  }

  /**
   * This method tests the {@code column} navigation method.
   */
  @Test
  public void test_column_with_index_from_request() throws Exception {
    Field fieldIndex = AbstractDbAssert.class.getDeclaredField("indexNextColumn");
    fieldIndex.setAccessible(true);
    Field fieldColumn = AbstractColumnAssert.class.getDeclaredField("column");
    fieldColumn.setAccessible(true);

    Request request = new Request(source, "select * from actor");
    RequestAssert requestAssert = assertThat(request);
    Assertions.assertThat(fieldIndex.get(requestAssert)).isEqualTo(0);
    RequestColumnAssert requestColumnAssert0 = requestAssert.column(0);
    Assertions.assertThat(fieldIndex.get(requestAssert)).isEqualTo(1);
    RequestColumnAssert requestColumnAssert1 = requestAssert.column(1);
    Assertions.assertThat(fieldIndex.get(requestAssert)).isEqualTo(2);
    RequestColumnAssert requestColumnAssert2 = requestAssert.column(2);
    Assertions.assertThat(fieldIndex.get(requestAssert)).isEqualTo(3);
    RequestColumnAssert requestColumnAssert3 = requestAssert.column(3);
    Assertions.assertThat(fieldIndex.get(requestAssert)).isEqualTo(4);
    RequestColumnAssert requestColumnAssert4 = requestAssert.column(4);
    Assertions.assertThat(fieldIndex.get(requestAssert)).isEqualTo(5);
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
    Assertions.assertThat(fieldIndex.get(requestAssertBis)).isEqualTo(0);
    RequestColumnAssert requestColumnAssertBis0 = requestAssertBis.column(0);
    Assertions.assertThat(fieldIndex.get(requestAssertBis)).isEqualTo(1);
    RequestColumnAssert requestColumnAssertBis1 = requestColumnAssertBis0.column(1);
    Assertions.assertThat(fieldIndex.get(requestAssertBis)).isEqualTo(2);
    RequestColumnAssert requestColumnAssertBis2 = requestColumnAssertBis1.column(2);
    Assertions.assertThat(fieldIndex.get(requestAssertBis)).isEqualTo(3);
    RequestColumnAssert requestColumnAssertBis3 = requestColumnAssertBis2.column(3);
    Assertions.assertThat(fieldIndex.get(requestAssertBis)).isEqualTo(4);
    RequestColumnAssert requestColumnAssertBis4 = requestColumnAssertBis3.column(4);
    Assertions.assertThat(fieldIndex.get(requestAssertBis)).isEqualTo(5);
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

    Assertions.assertThat(columnId0.getValuesList()).isEqualTo(columnIdBis0.getValuesList()).containsExactly(
            new BigDecimal("1"), new BigDecimal("2"), new BigDecimal("3"));
    Assertions.assertThat(columnId1.getValuesList()).isEqualTo(columnIdBis1.getValuesList()).containsExactly("Weaver",
                                                                                                             "Phoenix",
                                                                                                             "Worthington");
    Assertions
            .assertThat(columnId2.getValuesList()).isEqualTo(columnIdBis2.getValuesList()).containsExactly("Sigourney",
                                                                                                           "Joaquim",
                                                                                                           "Sam");
    Assertions.assertThat(columnId3.getValuesList()).isEqualTo(columnIdBis3.getValuesList()).containsExactly(
            Date.valueOf("1949-10-08"), Date.valueOf("1974-10-28"), Date.valueOf("1976-08-02"));
    Assertions.assertThat(columnId4.getValuesList()).isEqualTo(columnIdBis4.getValuesList()).containsExactly(
            UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"), UUID.fromString("16319617-AE95-4087-9264-D3D21BF611B6"), UUID.fromString("D735221B-5DE5-4112-AA1E-49090CB75ADA"));
  }
}
