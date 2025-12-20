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
 * Copyright 2015-2025 the original author or authors.
 */
package org.assertj.db.type;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Table.Order;
import org.junit.Test;

/**
 * Tests on the {@code Column} of {@code Table}.
 * <p>
 * These tests are on the values in the {@code Column} got from a {@code Table}.
 * </p>
 *
 * @author Régis Pouiller
 * @author Julien Roy
 */
public class Table_Columns_Test extends AbstractTest {

  /**
   * This method test the columns got from a {@code ConnectionProvider}.
   */
  @Test
  public void test_columns() {
    Table table = assertDbConnection.table("movie").build();

    Column columnFromIndex = table.getColumn(1);

    assertThat(columnFromIndex.getName()).isEqualTo("TITLE");
    assertThat(columnFromIndex.getValuesList().get(0).getValue()).isEqualTo("Alien");
    assertThat(columnFromIndex.getValuesList().get(1).getValue()).isEqualTo("The Village");
    assertThat(columnFromIndex.getValuesList().get(2).getValue()).isEqualTo("Avatar");
    assertThat(columnFromIndex.getRowValue(1).getValue()).isEqualTo("The Village");
  }

  /**
   * This method tests the columns to check.
   */
  @Test
  public void test_columns_to_check() {
    Table table = assertDbConnection.table("movie").build();

    assertThat(table.getColumnsToCheck()).isNull();

    Table tableWithCheck = assertDbConnection.table("movie")
      .columnsToCheck(new String[]{"title", "test"})
      .build();

    assertThat(tableWithCheck.getColumnsToCheck()).hasSize(1);
    assertThat(tableWithCheck.getColumnsToCheck()).contains("TITLE");
  }

  /**
   * This method tests the columns to exclude.
   */
  @Test
  public void test_columns_to_exclude() {
    Table table = assertDbConnection.table("movie").build();

    assertThat(table.getColumnsToExclude()).isNull();


    Table tableWithExclude = assertDbConnection.table("movie")
      .columnsToExclude(new String[]{"title", "test"})
      .build();

    assertThat(tableWithExclude.getColumnsToExclude()).hasSize(1);
    assertThat(tableWithExclude.getColumnsToExclude()).contains("TITLE");
  }

  /**
   * This method tests the columns to order.
   */
  @Test
  public void test_columns_to_order() {
    Table table = assertDbConnection.table("movie").build();

    assertThat(table.getColumnsToOrder()).isNull();

    Table tableWithOrder = assertDbConnection.table("movie")
      .columnsToOrder(new Order[]{Order.asc("title"), Order.asc("test")})
      .build();

    assertThat(tableWithOrder.getColumnsToOrder()).hasSize(1);
    assertThat(tableWithOrder.getColumnsToOrder()).contains(Order.asc("TITLE"));
  }
}
