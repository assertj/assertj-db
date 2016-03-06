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
import org.assertj.db.display.*;
import org.assertj.db.type.Changes;
import org.assertj.db.type.Request;
import org.assertj.db.type.Table;
import org.junit.Test;

import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.display.Displaying.display;

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
  public void test_return_to_table_from_column_with_assertions() throws Exception {
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
  public void test_return_to_table_from_row_with_assertions() throws Exception {
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
  public void test_return_to_request_from_column_with_assertions() throws Exception {
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
  public void test_return_to_request_from_row_with_assertions() throws Exception {
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
  public void test_return_to_column_from_value_for_table_with_assertions() throws Exception {
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
  public void test_return_to_row_from_value_for_table_with_assertions() throws Exception {
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
  public void test_return_to_column_from_value_for_request_with_assertions() throws Exception {
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
  public void test_return_to_row_from_value_for_request_with_assertions() throws Exception {
    Request request = new Request(source, "select * from actor");
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
    Changes changes = new Changes(source).setStartPointNow();
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
    Changes changes = new Changes(source).setStartPointNow();
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
    Changes changes = new Changes(source).setStartPointNow();
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
    Changes changes = new Changes(source).setStartPointNow();
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
    Changes changes = new Changes(source).setStartPointNow();
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
    Table table = new Table(source, "actor");
    TableDisplay tableDisplay = display(table);
    TableColumnDisplay tableColumnDisplay = tableDisplay.column();
    TableDisplay tableDisplayBis = tableColumnDisplay.returnToTable();

    Assertions.assertThat(tableDisplay).isSameAs(tableDisplayBis);
  }

  /**
   * This method tests the {@code returnToTable} method from a row for a table.
   */
  @Test
  public void test_return_to_table_from_row_with_displays() throws Exception {
    Table table = new Table(source, "actor");
    TableDisplay tableDisplay = display(table);
    TableRowDisplay tableRowDisplay = tableDisplay.row();
    TableDisplay tableDisplayBis = tableRowDisplay.returnToTable();

    Assertions.assertThat(tableDisplay).isSameAs(tableDisplayBis);
  }

  /**
   * This method tests the {@code returnToColumn} method from a column for a request.
   */
  @Test
  public void test_return_to_request_from_column_with_displays() throws Exception {
    Request request = new Request(source, "select * from actor");
    RequestDisplay requestDisplay = display(request);
    RequestColumnDisplay requestColumnDisplay = requestDisplay.column();
    RequestDisplay requestDisplayBis = requestColumnDisplay.returnToRequest();

    Assertions.assertThat(requestDisplay).isSameAs(requestDisplayBis);
  }

  /**
   * This method tests the {@code returnToRow} method from a row for a request.
   */
  @Test
  public void test_return_to_request_from_row_with_displays() throws Exception {
    Request request = new Request(source, "select * from actor");
    RequestDisplay requestDisplay = display(request);
    RequestRowDisplay requestRowDisplay = requestDisplay.row();
    RequestDisplay requestDisplayBis = requestRowDisplay.returnToRequest();

    Assertions.assertThat(requestDisplay).isSameAs(requestDisplayBis);
  }

  /**
   * This method tests the {@code returnToColumn} method from a value for a table.
   */
  @Test
  public void test_return_to_column_from_value_for_table_with_displays() throws Exception {
    Table table = new Table(source, "actor");
    TableDisplay tableDisplay = display(table);
    TableColumnDisplay tableColumnDisplay = tableDisplay.column();
    TableColumnValueDisplay tableColumnValueDisplay = tableColumnDisplay.value();
    TableColumnDisplay tableColumnDisplayBis = tableColumnValueDisplay.returnToColumn();

    Assertions.assertThat(tableColumnDisplay).isSameAs(tableColumnDisplayBis);
  }

  /**
   * This method tests the {@code returnToRow} method from a value for a table.
   */
  @Test
  public void test_return_to_row_from_value_for_table_with_displays() throws Exception {
    Table table = new Table(source, "actor");
    TableDisplay tableDisplay = display(table);
    TableRowDisplay tableRowDisplay = tableDisplay.row();
    TableRowValueDisplay tableRowValueDisplay = tableRowDisplay.value();
    TableRowDisplay tableRowDisplayBis = tableRowValueDisplay.returnToRow();

    Assertions.assertThat(tableRowDisplay).isSameAs(tableRowDisplayBis);
  }

  /**
   * This method tests the {@code returnToColumn} method from a value for a request.
   */
  @Test
  public void test_return_to_column_from_value_for_request_with_displays() throws Exception {
    Request request = new Request(source, "select * from actor");
    RequestDisplay requestDisplay = display(request);
    RequestColumnDisplay requestColumnDisplay = requestDisplay.column();
    RequestColumnValueDisplay requestColumnValueDisplay = requestColumnDisplay.value();
    RequestColumnDisplay requestColumnDisplayBis = requestColumnValueDisplay.returnToColumn();

    Assertions.assertThat(requestColumnDisplay).isSameAs(requestColumnDisplayBis);
  }

  /**
   * This method tests the {@code returnToRow} method from a value for a request.
   */
  @Test
  public void test_return_to_row_from_value_for_request_with_displays() throws Exception {
    Request request = new Request(source, "select * from actor");
    RequestDisplay requestDisplay = display(request);
    RequestRowDisplay requestRowDisplay = requestDisplay.row();
    RequestRowValueDisplay requestRowValueDisplay = requestRowDisplay.value();
    RequestRowDisplay requestRowDisplayBis = requestRowValueDisplay.returnToRow();

    Assertions.assertThat(requestRowDisplay).isSameAs(requestRowDisplayBis);
  }

  /**
   * This method tests the {@code returnToChanges} method from a change for changes.
   */
  @Test
  @NeedReload
  public void test_return_to_changes_from_change_with_displays() throws Exception {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    ChangesDisplay changesDisplay = display(changes);
    ChangeDisplay changeDisplay = changesDisplay.change();
    ChangesDisplay changesDisplayBis = changeDisplay.returnToChanges();

    Assertions.assertThat(changesDisplay).isSameAs(changesDisplayBis);
  }

  /**
   * This method tests the {@code returnToChange} method from a column for a change.
   */
  @Test
  @NeedReload
  public void test_return_to_change_from_column_with_displays() throws Exception {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    ChangeDisplay changeDisplay = display(changes).change();
    ChangeColumnDisplay changeColumnDisplay = changeDisplay.column();
    ChangeDisplay changeDisplayBis = changeColumnDisplay.returnToChange();

    Assertions.assertThat(changeDisplay).isSameAs(changeDisplayBis);
  }

  /**
   * This method tests the {@code returnToChange} method from a row for a change.
   */
  @Test
  @NeedReload
  public void test_return_to_change_from_row_with_displays() throws Exception {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    ChangeDisplay changeDisplay = display(changes).change();
    ChangeRowDisplay changeRowDisplay = changeDisplay.rowAtEndPoint();
    ChangeDisplay changeDisplayBis = changeRowDisplay.returnToChange();

    Assertions.assertThat(changeDisplay).isSameAs(changeDisplayBis);
  }

  /**
   * This method tests the {@code returnToColumn} method from a value for a column.
   */
  @Test
  @NeedReload
  public void test_return_to_column_from_value_with_displays() throws Exception {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    ChangeColumnDisplay changeColumnDisplay = display(changes).change().column();
    ChangeColumnValueDisplay changeColumnValueDisplay = changeColumnDisplay.valueAtEndPoint();
    ChangeColumnDisplay changeColumnDisplayBis = changeColumnValueDisplay.returnToColumn();

    Assertions.assertThat(changeColumnDisplay).isSameAs(changeColumnDisplayBis);
  }

  /**
   * This method tests the {@code returnToRow} method from a value for a row.
   */
  @Test
  @NeedReload
  public void test_return_to_row_from_value_with_displays() throws Exception {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    ChangeRowDisplay changeRowDisplay = display(changes).change().rowAtEndPoint();
    ChangeRowValueDisplay changeRowValueDisplay = changeRowDisplay.value();
    ChangeRowDisplay changeRowDisplayBis = changeRowValueDisplay.returnToRow();

    Assertions.assertThat(changeRowDisplay).isSameAs(changeRowDisplayBis);
  }
}
