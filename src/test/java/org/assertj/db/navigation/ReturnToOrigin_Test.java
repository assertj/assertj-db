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
package org.assertj.db.navigation;

import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;

import org.assertj.core.api.Assertions;
import org.assertj.db.api.ChangeAssert;
import org.assertj.db.api.ChangeColumnAssert;
import org.assertj.db.api.ChangeColumnValueAssert;
import org.assertj.db.api.ChangeRowAssert;
import org.assertj.db.api.ChangeRowValueAssert;
import org.assertj.db.api.ChangesAssert;
import org.assertj.db.api.RequestAssert;
import org.assertj.db.api.RequestColumnAssert;
import org.assertj.db.api.RequestColumnValueAssert;
import org.assertj.db.api.RequestRowAssert;
import org.assertj.db.api.RequestRowValueAssert;
import org.assertj.db.api.TableAssert;
import org.assertj.db.api.TableColumnAssert;
import org.assertj.db.api.TableColumnValueAssert;
import org.assertj.db.api.TableRowAssert;
import org.assertj.db.api.TableRowValueAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.assertj.db.output.ChangeColumnOutputter;
import org.assertj.db.output.ChangeColumnValueOutputter;
import org.assertj.db.output.ChangeOutputter;
import org.assertj.db.output.ChangeRowOutputter;
import org.assertj.db.output.ChangeRowValueOutputter;
import org.assertj.db.output.ChangesOutputter;
import org.assertj.db.output.Outputs;
import org.assertj.db.output.RequestColumnOutputter;
import org.assertj.db.output.RequestColumnValueOutputter;
import org.assertj.db.output.RequestOutputter;
import org.assertj.db.output.RequestRowOutputter;
import org.assertj.db.output.RequestRowValueOutputter;
import org.assertj.db.output.TableColumnOutputter;
import org.assertj.db.output.TableColumnValueOutputter;
import org.assertj.db.output.TableOutputter;
import org.assertj.db.output.TableRowOutputter;
import org.assertj.db.output.TableRowValueOutputter;
import org.assertj.db.type.Changes;
import org.assertj.db.type.Request;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Tests on the different methods linked on the {@code returnToOrigin()} method.
 *
 * @author Régis Pouiller
 * @author Julien Roy
 */
public class ReturnToOrigin_Test extends AbstractTest {

  /**
   * This method tests the {@code returnToTable} method from a column for a table.
   */
  @Test
  public void test_return_to_table_from_column_with_assertions() throws Exception {
    Table table = assertDbConnection.table("actor").build();
    TableAssert tableAssert = assertThat(table);
    TableColumnAssert tableColumnAssert = tableAssert.column();
    TableAssert tableAssertBis = tableColumnAssert.returnToTable();

    Assertions.assertThat(tableAssert).isSameAs(tableAssertBis);
  }

  /**
   * This method tests the {@code returnToTable} method from a row for a table.
   */
  @Test
  public void test_return_to_table_from_row_with_assertions() throws Exception {
    Table table = assertDbConnection.table("actor").build();
    TableAssert tableAssert = assertThat(table);
    TableRowAssert tableRowAssert = tableAssert.row();
    TableAssert tableAssertBis = tableRowAssert.returnToTable();

    Assertions.assertThat(tableAssert).isSameAs(tableAssertBis);
  }

  /**
   * This method tests the {@code returnToColumn} method from a column for a request.
   */
  @Test
  public void test_return_to_request_from_column_with_assertions() throws Exception {
    Request request = assertDbConnection.request("select * from actor").build();
    RequestAssert requestAssert = assertThat(request);
    RequestColumnAssert requestColumnAssert = requestAssert.column();
    RequestAssert requestAssertBis = requestColumnAssert.returnToRequest();

    Assertions.assertThat(requestAssert).isSameAs(requestAssertBis);
  }

  /**
   * This method tests the {@code returnToRow} method from a row for a request.
   */
  @Test
  public void test_return_to_request_from_row_with_assertions() throws Exception {
    Request request = assertDbConnection.request("select * from actor").build();
    RequestAssert requestAssert = assertThat(request);
    RequestRowAssert requestRowAssert = requestAssert.row();
    RequestAssert requestAssertBis = requestRowAssert.returnToRequest();

    Assertions.assertThat(requestAssert).isSameAs(requestAssertBis);
  }

  /**
   * This method tests the {@code returnToColumn} method from a value for a table.
   */
  @Test
  public void test_return_to_column_from_value_for_table_with_assertions() throws Exception {
    Table table = assertDbConnection.table("actor").build();
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
  public void test_return_to_row_from_value_for_table_with_assertions() throws Exception {
    Table table = assertDbConnection.table("actor").build();
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
  public void test_return_to_column_from_value_for_request_with_assertions() throws Exception {
    Request request = assertDbConnection.request("select * from actor").build();
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
  public void test_return_to_row_from_value_for_request_with_assertions() throws Exception {
    Request request = assertDbConnection.request("select * from actor").build();
    RequestAssert requestAssert = assertThat(request);
    RequestRowAssert requestRowAssert = requestAssert.row();
    RequestRowValueAssert requestRowValueAssert = requestRowAssert.value();
    RequestRowAssert requestRowAssertBis = requestRowValueAssert.returnToRow();

    Assertions.assertThat(requestRowAssert).isSameAs(requestRowAssertBis);
  }

  /**
   * This method tests the {@code returnToChanges} method from a change for changes.
   */
  @Test
  @NeedReload
  public void test_return_to_changes_from_change_with_assertions() throws Exception {
    Changes changes = assertDbConnection.changes().build().setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    ChangesAssert changesAssert = assertThat(changes);
    ChangeAssert changeAssert = changesAssert.change();
    ChangesAssert changesAssertBis = changeAssert.returnToChanges();

    Assertions.assertThat(changesAssert).isSameAs(changesAssertBis);
  }

  /**
   * This method tests the {@code returnToChange} method from a column for a change.
   */
  @Test
  @NeedReload
  public void test_return_to_change_from_column_with_assertions() throws Exception {
    Changes changes = assertDbConnection.changes().build().setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    ChangeAssert changeAssert = assertThat(changes).change();
    ChangeColumnAssert changeColumnAssert = changeAssert.column();
    ChangeAssert changeAssertBis = changeColumnAssert.returnToChange();

    Assertions.assertThat(changeAssert).isSameAs(changeAssertBis);
  }

  /**
   * This method tests the {@code returnToChange} method from a row for a change.
   */
  @Test
  @NeedReload
  public void test_return_to_change_from_row_with_assertions() throws Exception {
    Changes changes = assertDbConnection.changes().build().setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    ChangeAssert changeAssert = assertThat(changes).change();
    ChangeRowAssert changeRowAssert = changeAssert.rowAtEndPoint();
    ChangeAssert changeAssertBis = changeRowAssert.returnToChange();

    Assertions.assertThat(changeAssert).isSameAs(changeAssertBis);
  }

  /**
   * This method tests the {@code returnToColumn} method from a value for a column.
   */
  @Test
  @NeedReload
  public void test_return_to_column_from_value_with_assertions() throws Exception {
    Changes changes = assertDbConnection.changes().build().setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    ChangeColumnAssert changeColumnAssert = assertThat(changes).change().column();
    ChangeColumnValueAssert changeColumnValueAssert = changeColumnAssert.valueAtEndPoint();
    ChangeColumnAssert changeColumnAssertBis = changeColumnValueAssert.returnToColumn();

    Assertions.assertThat(changeColumnAssert).isSameAs(changeColumnAssertBis);
  }

  /**
   * This method tests the {@code returnToRow} method from a value for a row.
   */
  @Test
  @NeedReload
  public void test_return_to_row_from_value_with_assertions() throws Exception {
    Changes changes = assertDbConnection.changes().build().setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    ChangeRowAssert changeRowAssert = assertThat(changes).change().rowAtEndPoint();
    ChangeRowValueAssert changeRowValueAssert = changeRowAssert.value();
    ChangeRowAssert changeRowAssertBis = changeRowValueAssert.returnToRow();

    Assertions.assertThat(changeRowAssert).isSameAs(changeRowAssertBis);
  }

  /**
   * This method tests the {@code returnToTable} method from a column for a table.
   */
  @Test
  public void test_return_to_table_from_column_with_displays() throws Exception {
    Table table = assertDbConnection.table("actor").build();
    TableOutputter tableOutputter = Outputs.output(table);
    TableColumnOutputter tableColumnOutputter = tableOutputter.column();
    TableOutputter tableOutputterBis = tableColumnOutputter.returnToTable();

    Assertions.assertThat(tableOutputter).isSameAs(tableOutputterBis);
  }

  /**
   * This method tests the {@code returnToTable} method from a row for a table.
   */
  @Test
  public void test_return_to_table_from_row_with_displays() throws Exception {
    Table table = assertDbConnection.table("actor").build();
    TableOutputter tableOutputter = Outputs.output(table);
    TableRowOutputter tableRowOutputter = tableOutputter.row();
    TableOutputter tableOutputterBis = tableRowOutputter.returnToTable();

    Assertions.assertThat(tableOutputter).isSameAs(tableOutputterBis);
  }

  /**
   * This method tests the {@code returnToColumn} method from a column for a request.
   */
  @Test
  public void test_return_to_request_from_column_with_displays() throws Exception {
    Request request = assertDbConnection.request("select * from actor").build();
    RequestOutputter requestOutputter = Outputs.output(request);
    RequestColumnOutputter requestColumnOutputter = requestOutputter.column();
    RequestOutputter requestOutputterBis = requestColumnOutputter.returnToRequest();

    Assertions.assertThat(requestOutputter).isSameAs(requestOutputterBis);
  }

  /**
   * This method tests the {@code returnToRow} method from a row for a request.
   */
  @Test
  public void test_return_to_request_from_row_with_displays() throws Exception {
    Request request = assertDbConnection.request("select * from actor").build();
    RequestOutputter requestOutputter = Outputs.output(request);
    RequestRowOutputter requestRowOutputter = requestOutputter.row();
    RequestOutputter requestOutputterBis = requestRowOutputter.returnToRequest();

    Assertions.assertThat(requestOutputter).isSameAs(requestOutputterBis);
  }

  /**
   * This method tests the {@code returnToColumn} method from a value for a table.
   */
  @Test
  public void test_return_to_column_from_value_for_table_with_displays() throws Exception {
    Table table = assertDbConnection.table("actor").build();
    TableOutputter tableOutputter = Outputs.output(table);
    TableColumnOutputter tableColumnOutputter = tableOutputter.column();
    TableColumnValueOutputter tableColumnValueOutputter = tableColumnOutputter.value();
    TableColumnOutputter tableColumnOutputterBis = tableColumnValueOutputter.returnToColumn();

    Assertions.assertThat(tableColumnOutputter).isSameAs(tableColumnOutputterBis);
  }

  /**
   * This method tests the {@code returnToRow} method from a value for a table.
   */
  @Test
  public void test_return_to_row_from_value_for_table_with_displays() throws Exception {
    Table table = assertDbConnection.table("actor").build();
    TableOutputter tableOutputter = Outputs.output(table);
    TableRowOutputter tableRowOutputter = tableOutputter.row();
    TableRowValueOutputter tableRowValueOutputter = tableRowOutputter.value();
    TableRowOutputter tableRowOutputterBis = tableRowValueOutputter.returnToRow();

    Assertions.assertThat(tableRowOutputter).isSameAs(tableRowOutputterBis);
  }

  /**
   * This method tests the {@code returnToColumn} method from a value for a request.
   */
  @Test
  public void test_return_to_column_from_value_for_request_with_displays() throws Exception {
    Request request = assertDbConnection.request("select * from actor").build();
    RequestOutputter requestOutputter = Outputs.output(request);
    RequestColumnOutputter requestColumnOutputter = requestOutputter.column();
    RequestColumnValueOutputter requestColumnValueOutputter = requestColumnOutputter.value();
    RequestColumnOutputter requestColumnOutputterBis = requestColumnValueOutputter.returnToColumn();

    Assertions.assertThat(requestColumnOutputter).isSameAs(requestColumnOutputterBis);
  }

  /**
   * This method tests the {@code returnToRow} method from a value for a request.
   */
  @Test
  public void test_return_to_row_from_value_for_request_with_displays() throws Exception {
    Request request = assertDbConnection.request("select * from actor").build();
    RequestOutputter requestOutputter = Outputs.output(request);
    RequestRowOutputter requestRowOutputter = requestOutputter.row();
    RequestRowValueOutputter requestRowValueOutputter = requestRowOutputter.value();
    RequestRowOutputter requestRowOutputterBis = requestRowValueOutputter.returnToRow();

    Assertions.assertThat(requestRowOutputter).isSameAs(requestRowOutputterBis);
  }

  /**
   * This method tests the {@code returnToChanges} method from a change for changes.
   */
  @Test
  @NeedReload
  public void test_return_to_changes_from_change_with_displays() throws Exception {
    Changes changes = assertDbConnection.changes().build().setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    ChangesOutputter changesOutputter = output(changes);
    ChangeOutputter changeOutputter = changesOutputter.change();
    ChangesOutputter changesOutputterBis = changeOutputter.returnToChanges();

    Assertions.assertThat(changesOutputter).isSameAs(changesOutputterBis);
  }

  /**
   * This method tests the {@code returnToChange} method from a column for a change.
   */
  @Test
  @NeedReload
  public void test_return_to_change_from_column_with_displays() throws Exception {
    Changes changes = assertDbConnection.changes().build().setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    ChangeOutputter changeOutputter = output(changes).change();
    ChangeColumnOutputter changeColumnOutputter = changeOutputter.column();
    ChangeOutputter changeOutputterBis = changeColumnOutputter.returnToChange();

    Assertions.assertThat(changeOutputter).isSameAs(changeOutputterBis);
  }

  /**
   * This method tests the {@code returnToChange} method from a row for a change.
   */
  @Test
  @NeedReload
  public void test_return_to_change_from_row_with_displays() throws Exception {
    Changes changes = assertDbConnection.changes().build().setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    ChangeOutputter changeOutputter = output(changes).change();
    ChangeRowOutputter changeRowOutputter = changeOutputter.rowAtEndPoint();
    ChangeOutputter changeOutputterBis = changeRowOutputter.returnToChange();

    Assertions.assertThat(changeOutputter).isSameAs(changeOutputterBis);
  }

  /**
   * This method tests the {@code returnToColumn} method from a value for a column.
   */
  @Test
  @NeedReload
  public void test_return_to_column_from_value_with_displays() throws Exception {
    Changes changes = assertDbConnection.changes().build().setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    ChangeColumnOutputter changeColumnOutputter = output(changes).change().column();
    ChangeColumnValueOutputter changeColumnValueOutputter = changeColumnOutputter.valueAtEndPoint();
    ChangeColumnOutputter changeColumnOutputterBis = changeColumnValueOutputter.returnToColumn();

    Assertions.assertThat(changeColumnOutputter).isSameAs(changeColumnOutputterBis);
  }

  /**
   * This method tests the {@code returnToRow} method from a value for a row.
   */
  @Test
  @NeedReload
  public void test_return_to_row_from_value_with_displays() throws Exception {
    Changes changes = assertDbConnection.changes().build().setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    ChangeRowOutputter changeRowOutputter = output(changes).change().rowAtEndPoint();
    ChangeRowValueOutputter changeRowValueOutputter = changeRowOutputter.value();
    ChangeRowOutputter changeRowOutputterBis = changeRowValueOutputter.returnToRow();

    Assertions.assertThat(changeRowOutputter).isSameAs(changeRowOutputterBis);
  }
}
