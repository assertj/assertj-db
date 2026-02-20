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
package org.assertj.db.api.assertions;

import static org.assertj.core.api.Assertions.fail;
import static org.assertj.db.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.assertj.db.api.ChangeAssert;
import org.assertj.db.api.ChangeColumnAssert;
import org.assertj.db.api.ChangeRowValueAssert;
import org.assertj.db.api.TableAssert;
import org.assertj.db.api.TableColumnAssert;
import org.assertj.db.api.TableRowValueAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.assertj.db.type.Changes;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Tests on {@link AssertOnColumnName} class :
 * {@link AssertOnColumnName#hasColumnName(String)} method.
 *
 * @author Régis Pouiller
 * @author Julien Roy
 */
public class AssertOnColumnName_HasColumnName_Test extends AbstractTest {

  /**
   * This method tests the {@code hasColumnName} assertion method.
   */
  @Test
  @NeedReload
  public void test_has_column_name() {
    Changes changes = assertDbConnection.changes().build().setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    ChangeAssert changeAssert = assertThat(changes).change();
    ChangeColumnAssert changeColumnAssert = changeAssert.column();
    ChangeColumnAssert changeColumnAssert2 = changeColumnAssert.hasColumnName("ID");
    Assertions.assertThat(changeColumnAssert).isSameAs(changeColumnAssert2);
    ChangeRowValueAssert changeRowValueAssert = changeAssert.rowAtEndPoint().value();
    ChangeRowValueAssert changeRowValueAssert2 = changeRowValueAssert.hasColumnName("id");
    Assertions.assertThat(changeRowValueAssert).isSameAs(changeRowValueAssert2);

    Table table = assertDbConnection.table("actor").build();
    TableAssert tableAssert = assertThat(table);
    TableColumnAssert tableColumnAssert = tableAssert.column();
    TableColumnAssert tableColumnAssert2 = tableColumnAssert.hasColumnName("id");
    Assertions.assertThat(tableColumnAssert).isSameAs(tableColumnAssert2);
    TableRowValueAssert tableRowValueAssert = tableAssert.row().value();
    TableRowValueAssert tableRowValueAssert2 = tableRowValueAssert.hasColumnName("ID");
    Assertions.assertThat(tableRowValueAssert).isSameAs(tableRowValueAssert2);
  }

  /**
   * This method should fail because the column name is different.
   */
  @Test
  @NeedReload
  public void should_fail_because_column_name_is_different() {
    Changes changes = assertDbConnection.changes().build().setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    ChangeAssert changeAssert = assertThat(changes).change();
    try {
      changeAssert.column().hasColumnName("ID2");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[Column at index 0 (column name : ID) of Change at index 0 (on table : ACTOR and with primary key : [4]) of Changes on tables of 'sa/jdbc:h2:mem:test'] %n"
        + "Expecting :%n"
        + "  \"ID2\"%n"
        + "to be the name of the column but was:%n"
        + "  \"ID\""));
    }
    try {
      changeAssert.rowAtEndPoint().value().hasColumnName("ID2");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[Value at index 0 (column name : ID) of Row at end point of Change at index 0 (on table : ACTOR and with primary key : [4]) of Changes on tables of 'sa/jdbc:h2:mem:test'] %n"
        + "Expecting :%n"
        + "  \"ID2\"%n"
        + "to be the name of the column but was:%n"
        + "  \"ID\""));
    }

    Table table = assertDbConnection.table("actor").build();
    TableAssert tableAssert = assertThat(table);
    try {
      tableAssert.column().hasColumnName("ID2");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[Column at index 0 (column name : ID) of ACTOR table] %n"
        + "Expecting :%n"
        + "  \"ID2\"%n"
        + "to be the name of the column but was:%n"
        + "  \"ID\""));
    }
    try {
      tableAssert.row().value().hasColumnName("ID2");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[Value at index 0 (column name : ID) of Row at index 0 of ACTOR table] %n"
        + "Expecting :%n"
        + "  \"ID2\"%n"
        + "to be the name of the column but was:%n"
        + "  \"ID\""));
    }
  }
}
