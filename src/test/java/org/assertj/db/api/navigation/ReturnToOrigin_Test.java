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
 * Copyright 2012-2014 the original author or authors.
 */
package org.assertj.db.api.navigation;

import org.assertj.core.api.Assertions;
import org.assertj.db.api.*;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Request;
import org.assertj.db.type.Table;
import org.junit.Test;

import static org.assertj.db.api.Assertions.assertThat;

/**
 * Tests on the different methods linked on the {@code returnToOrigin()} method.
 *
 * @author Régis Pouiller
 *
 */
public class ReturnToOrigin_Test extends AbstractTest {

  /**
   * This method tests the {@code returnToTable} method from a column for a table.
   */
  @Test
  public void test_return_to_table_from_column() throws Exception {
    Table table = new Table(source, "actor");
    TableAssert tableAssert = assertThat(table);
    TableColumnAssert tableColumnAssert = tableAssert.column();
    TableAssert tableAssertBis = tableColumnAssert.returnToTable();

    Assertions.assertThat(tableAssert).isSameAs(tableAssertBis);
  }

  /**
   * This method tests the {@code returnToTable} method from a row for a table.
   */
  @Test
  public void test_return_to_table_from_row() throws Exception {
    Table table = new Table(source, "actor");
    TableAssert tableAssert = assertThat(table);
    TableRowAssert tableRowAssert = tableAssert.row();
    TableAssert tableAssertBis = tableRowAssert.returnToTable();

    Assertions.assertThat(tableAssert).isSameAs(tableAssertBis);
  }

  /**
   * This method tests the {@code returnToColumn} method from a column for a request.
   */
  @Test
  public void test_return_to_request_from_column() throws Exception {
    Request request = new Request(source, "select * from actor");
    RequestAssert requestAssert = assertThat(request);
    RequestColumnAssert requestColumnAssert = requestAssert.column();
    RequestAssert requestAssertBis = requestColumnAssert.returnToRequest();

    Assertions.assertThat(requestAssert).isSameAs(requestAssertBis);
  }

  /**
   * This method tests the {@code returnToRow} method from a row for a request.
   */
  @Test
  public void test_return_to_request_from_row() throws Exception {
    Request request = new Request(source, "select * from actor");
    RequestAssert requestAssert = assertThat(request);
    RequestRowAssert requestRowAssert = requestAssert.row();
    RequestAssert requestAssertBis = requestRowAssert.returnToRequest();

    Assertions.assertThat(requestAssert).isSameAs(requestAssertBis);
  }

  /**
   * This method tests the {@code returnToColumn} method from a value for a table.
   */
  @Test
  public void test_return_to_column_from_value_for_table() throws Exception {
    Table table = new Table(source, "actor");
    TableAssert tableAssert = assertThat(table);
    TableColumnAssert tableColumnAssert = tableAssert.column();
    TableColumnValueAssert tableColumnValueAssert = tableColumnAssert.value();
    TableColumnAssert tableColumnAssertBis = tableColumnValueAssert.returnToColumn();

    Assertions.assertThat(tableColumnAssert).isSameAs(tableColumnAssertBis);
  }

  /**
   * This method tests the {@code returnToRow} method from a value for a table.
   */
  @Test
  public void test_return_to_row_from_value_for_table() throws Exception {
    Table table = new Table(source, "actor");
    TableAssert tableAssert = assertThat(table);
    TableRowAssert tableRowAssert = tableAssert.row();
    TableRowValueAssert tableRowValueAssert = tableRowAssert.value();
    TableRowAssert tableRowAssertBis = tableRowValueAssert.returnToRow();

    Assertions.assertThat(tableRowAssert).isSameAs(tableRowAssertBis);
  }

  /**
   * This method tests the {@code returnToColumn} method from a value for a request.
   */
  @Test
  public void test_return_to_column_from_value_for_request() throws Exception {
    Request request = new Request(source, "select * from actor");
    RequestAssert requestAssert = assertThat(request);
    RequestColumnAssert requestColumnAssert = requestAssert.column();
    RequestColumnValueAssert requestColumnValueAssert = requestColumnAssert.value();
    RequestColumnAssert requestColumnAssertBis = requestColumnValueAssert.returnToColumn();

    Assertions.assertThat(requestColumnAssert).isSameAs(requestColumnAssertBis);
  }

  /**
   * This method tests the {@code returnToRow} method from a value for a request.
   */
  @Test
  public void test_return_to_row_from_value_for_request() throws Exception {
    Request request = new Request(source, "select * from actor");
    RequestAssert requestAssert = assertThat(request);
    RequestRowAssert requestRowAssert = requestAssert.row();
    RequestRowValueAssert requestRowValueAssert = requestRowAssert.value();
    RequestRowAssert requestRowAssertBis = requestRowValueAssert.returnToRow();

    Assertions.assertThat(requestRowAssert).isSameAs(requestRowAssertBis);
  }
}
