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
package org.assertj.db.api.assertions.impl;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.db.api.AbstractAssert;
import org.assertj.db.api.TableAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.*;
import org.assertj.db.type.lettercase.LetterCase;
import org.junit.Test;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on {@link AssertionsOnDataType} class :
 * {@link AssertionsOnDataType#isOnTable(AbstractAssert, WritableAssertionInfo, Change, LetterCase, String)} method.
 *
 * @author Régis Pouiller
 *
 */
public class AssertionsOnDataType_IsOnTable_Name_Test extends AbstractTest {

  /**
   * This method tests the {@code isOnTable} assertion method.
   */
  @Test
  public void test_is_on_table() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    Row rowAtStartPoint = getRow(null, null, null);
    Row rowAtEndPoint = getRow(null, null, null);
    Change change = getChange(DataType.TABLE, "test", ChangeType.CREATION, rowAtStartPoint, rowAtEndPoint);
    TableAssert tableAssert2 = AssertionsOnDataType.isOnTable(tableAssert, info, change, LetterCase.PRIMARY_KEY_DEFAULT, "test");
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
  }

  /**
   * This method should fail because the data type is different.
   */
  @Test
  public void should_fail_because_data_type_is_different() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    Row rowAtStartPoint = getRow(null, null, null);
    Row rowAtEndPoint = getRow(null, null, null);
    Change change = getChange(DataType.REQUEST, "test", ChangeType.CREATION, rowAtStartPoint, rowAtEndPoint);
    try {
      AssertionsOnDataType.isOnTable(tableAssert, info, change, LetterCase.PRIMARY_KEY_DEFAULT, "test");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                      + "Expecting:%n"
                                                      + "to be on data type%n"
                                                      + "  <TABLE>%n"
                                                      + "but was on data type%n"
                                                      + "  <REQUEST>"));
    }
  }

  /**
   * This method should fail because the table name is different.
   */
  @Test
  public void should_fail_because_table_name_is_different() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    Row rowAtStartPoint = getRow(null, null, null);
    Row rowAtEndPoint = getRow(null, null, null);
    Change change = getChange(DataType.TABLE, "test", ChangeType.CREATION, rowAtStartPoint, rowAtEndPoint);
    try {
      AssertionsOnDataType.isOnTable(tableAssert, info, change, LetterCase.PRIMARY_KEY_DEFAULT, "test2");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                      + "Expecting to be on the table:%n"
                                                      + "  <\"test2\">%n"
                                                      + "but was on the table:%n"
                                                      + "  <\"test\">"));
    }
  }

  /**
   * This method should fail because the expected table name is {@code null}.
   */
  @Test
  public void should_fail_because_expected_table_name_is_null() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    Row rowAtStartPoint = getRow(null, null, null);
    Row rowAtEndPoint = getRow(null, null, null);
    Change change = getChange(DataType.TABLE, "test", ChangeType.CREATION, rowAtStartPoint, rowAtEndPoint);
    try {
      AssertionsOnDataType.isOnTable(tableAssert, info, change, LetterCase.PRIMARY_KEY_DEFAULT, null);
      fail("An exception must be raised");
    } catch (NullPointerException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("Table name must be not null"));
    }
  }
}
