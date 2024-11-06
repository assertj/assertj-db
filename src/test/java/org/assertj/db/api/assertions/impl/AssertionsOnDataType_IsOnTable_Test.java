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

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.db.api.TableAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Change;
import org.assertj.db.type.ChangeType;
import org.assertj.db.type.DataType;
import org.assertj.db.type.Row;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Tests on {@link AssertionsOnDataType} class :
 * {@link AssertionsOnDataType#isOnTable(org.assertj.db.api.AbstractAssert, org.assertj.core.api.WritableAssertionInfo, org.assertj.db.type.Change)} method.
 *
 * @author Régis Pouiller
 */
public class AssertionsOnDataType_IsOnTable_Test extends AbstractTest {

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
    TableAssert tableAssert2 = AssertionsOnDataType.isOnTable(tableAssert, info, change);
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
      AssertionsOnDataType.isOnTable(tableAssert, info, change);
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
}
