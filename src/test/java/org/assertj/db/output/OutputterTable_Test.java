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
package org.assertj.db.output;

import static org.assertj.db.output.Outputs.output;

import java.io.ByteArrayOutputStream;

import org.assertj.core.api.Assertions;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Test the output of tables.
 *
 * @author Régis Pouiller
 * @author Julien Roy
 */
public class OutputterTable_Test extends AbstractTest {

  /**
   * This method tests the {@code output} output method.
   */
  @Test
  public void test_output() throws Exception {
    Table table = assertDbConnection.table("actor").build();

    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    output(table).toStream(byteArrayOutputStream);
    Assertions.assertThat(byteArrayOutputStream).hasToString(String.format("[ACTOR table]%n"
      + "|-----------|---------|-----------|-------------|-----------|------------|--------------------------------------|%n"
      + "|           |         | *         |             |           |            |                                      |%n"
      + "|           | PRIMARY | ID        | NAME        | FIRSTNAME | BIRTH      | ACTOR_IMDB                           |%n"
      + "|           | KEY     | (NUMBER)  | (TEXT)      | (TEXT)    | (DATE)     | (UUID)                               |%n"
      + "|           |         | Index : 0 | Index : 1   | Index : 2 | Index : 3  | Index : 4                            |%n"
      + "|-----------|---------|-----------|-------------|-----------|------------|--------------------------------------|%n"
      + "| Index : 0 | 1       | 1         | Weaver      | Sigourney | 1949-10-08 | 30b443ae-c0c9-4790-9bec-ce1380808435 |%n"
      + "| Index : 1 | 2       | 2         | Phoenix     | Joaquim   | 1974-10-28 | 16319617-ae95-4087-9264-d3d21bf611b6 |%n"
      + "| Index : 2 | 3       | 3         | Worthington | Sam       | 1976-08-02 | d735221b-5de5-4112-aa1e-49090cb75ada |%n"
      + "|-----------|---------|-----------|-------------|-----------|------------|--------------------------------------|%n"));
  }

  /**
   * This method tests the navigation.
   */
  @Test
  public void test_navigation() throws Exception {
    Table table = assertDbConnection.table("actor").build();

    TableOutputter tableOutputter = output(table);

    TableRowOutputter tableRowOutputter0 = tableOutputter.row();
    TableRowOutputter tableRowOutputter1 = tableOutputter.row();
    TableRowOutputter tableRowOutputter2 = tableOutputter.row();

    TableOutputter tableOutputterFromRow = tableRowOutputter0.returnToTable();

    TableRowOutputter tableRowOutputter2_1 = tableOutputter.row(2);
    TableRowOutputter tableRowOutputter1_1 = tableOutputter.row(1);
    TableRowOutputter tableRowOutputter0_1 = tableOutputter.row(0);

    TableRowOutputter tableRowOutputter1_2 = tableRowOutputter0_1.row();
    TableRowOutputter tableRowOutputter2_2 = tableRowOutputter0_1.row();

    TableRowOutputter tableRowOutputter2_3 = tableRowOutputter1_2.row(2);
    TableRowOutputter tableRowOutputter1_3 = tableRowOutputter1_2.row(1);
    TableRowOutputter tableRowOutputter0_3 = tableRowOutputter1_2.row(0);

    Assertions.assertThat(tableRowOutputter0).isSameAs(tableRowOutputter0_1).isSameAs(tableRowOutputter0_3);
    Assertions.assertThat(tableRowOutputter1).isSameAs(tableRowOutputter1_1).isSameAs(tableRowOutputter1_2).isSameAs(tableRowOutputter1_3);
    Assertions.assertThat(tableRowOutputter2).isSameAs(tableRowOutputter2_1).isSameAs(tableRowOutputter2_2).isSameAs(tableRowOutputter2_3);

    TableRowValueOutputter tableRowValueOutputter0 = tableRowOutputter0.value();
    TableRowValueOutputter tableRowValueOutputter1 = tableRowOutputter0.value();
    TableRowValueOutputter tableRowValueOutputter2 = tableRowOutputter0.value();
    TableRowValueOutputter tableRowValueOutputter3 = tableRowOutputter0.value();

    TableOutputter tableOutputterFromRowValue = tableRowValueOutputter0.returnToRow().returnToTable();

    TableRowValueOutputter tableRowValueOutputter3_1 = tableRowOutputter0.value(3);
    TableRowValueOutputter tableRowValueOutputter2_1 = tableRowOutputter0.value(2);
    TableRowValueOutputter tableRowValueOutputter1_1 = tableRowOutputter0.value(1);
    TableRowValueOutputter tableRowValueOutputter0_1 = tableRowOutputter0.value(0);

    TableRowValueOutputter tableRowValueOutputter3_2 = tableRowOutputter0.value("BIRTH");
    TableRowValueOutputter tableRowValueOutputter2_2 = tableRowOutputter0.value("FIRSTNAME");
    TableRowValueOutputter tableRowValueOutputter1_2 = tableRowOutputter0.value("NAME");
    TableRowValueOutputter tableRowValueOutputter0_2 = tableRowOutputter0.value("ID");

    TableRowValueOutputter tableRowValueOutputter1_3 = tableRowValueOutputter0_2.value();
    TableRowValueOutputter tableRowValueOutputter2_3 = tableRowValueOutputter0_2.value();
    TableRowValueOutputter tableRowValueOutputter3_3 = tableRowValueOutputter0_2.value();

    TableRowValueOutputter tableRowValueOutputter3_4 = tableRowValueOutputter1_3.value(3);
    TableRowValueOutputter tableRowValueOutputter2_4 = tableRowValueOutputter1_3.value(2);
    TableRowValueOutputter tableRowValueOutputter1_4 = tableRowValueOutputter1_3.value(1);
    TableRowValueOutputter tableRowValueOutputter0_4 = tableRowValueOutputter1_3.value(0);

    TableRowValueOutputter tableRowValueOutputter3_5 = tableRowValueOutputter3_1.value("BIRTH");
    TableRowValueOutputter tableRowValueOutputter2_5 = tableRowValueOutputter3_1.value("FIRSTNAME");
    TableRowValueOutputter tableRowValueOutputter1_5 = tableRowValueOutputter3_1.value("NAME");
    TableRowValueOutputter tableRowValueOutputter0_5 = tableRowValueOutputter3_1.value("ID");

    Assertions.assertThat(tableRowValueOutputter0).isSameAs(tableRowValueOutputter0_1).isSameAs(tableRowValueOutputter0_2).isSameAs(tableRowValueOutputter0_4).isSameAs(tableRowValueOutputter0_5);
    Assertions.assertThat(tableRowValueOutputter1).isSameAs(tableRowValueOutputter1_1).isSameAs(tableRowValueOutputter1_2).isSameAs(tableRowValueOutputter1_3).isSameAs(tableRowValueOutputter1_4).isSameAs(tableRowValueOutputter1_5);
    Assertions.assertThat(tableRowValueOutputter2).isSameAs(tableRowValueOutputter2_1).isSameAs(tableRowValueOutputter2_2).isSameAs(tableRowValueOutputter2_3).isSameAs(tableRowValueOutputter2_4).isSameAs(tableRowValueOutputter2_5);
    Assertions.assertThat(tableRowValueOutputter3).isSameAs(tableRowValueOutputter3_1).isSameAs(tableRowValueOutputter3_2).isSameAs(tableRowValueOutputter3_3).isSameAs(tableRowValueOutputter3_4).isSameAs(tableRowValueOutputter3_5);

    TableColumnOutputter tableColumnOutputter0 = tableOutputter.column();
    TableColumnOutputter tableColumnOutputter1 = tableOutputter.column();
    TableColumnOutputter tableColumnOutputter2 = tableOutputter.column();
    TableColumnOutputter tableColumnOutputter3 = tableOutputter.column();

    TableOutputter tableOutputterFromColumn = tableColumnOutputter0.returnToTable();

    TableColumnOutputter tableColumnOutputter3_1 = tableOutputter.column(3);
    TableColumnOutputter tableColumnOutputter2_1 = tableOutputter.column(2);
    TableColumnOutputter tableColumnOutputter1_1 = tableOutputter.column(1);
    TableColumnOutputter tableColumnOutputter0_1 = tableOutputter.column(0);

    TableColumnOutputter tableColumnOutputter3_2 = tableOutputter.column("BIRTH");
    TableColumnOutputter tableColumnOutputter2_2 = tableOutputter.column("FIRSTNAME");
    TableColumnOutputter tableColumnOutputter1_2 = tableOutputter.column("NAME");
    TableColumnOutputter tableColumnOutputter0_2 = tableOutputter.column("ID");

    TableColumnOutputter tableColumnOutputter1_3 = tableColumnOutputter0_2.column();
    TableColumnOutputter tableColumnOutputter2_3 = tableColumnOutputter0_2.column();
    TableColumnOutputter tableColumnOutputter3_3 = tableColumnOutputter0_2.column();

    TableColumnOutputter tableColumnOutputter3_4 = tableColumnOutputter1_3.column(3);
    TableColumnOutputter tableColumnOutputter2_4 = tableColumnOutputter1_3.column(2);
    TableColumnOutputter tableColumnOutputter1_4 = tableColumnOutputter1_3.column(1);
    TableColumnOutputter tableColumnOutputter0_4 = tableColumnOutputter1_3.column(0);

    TableColumnOutputter tableColumnOutputter3_5 = tableColumnOutputter3_1.column("BIRTH");
    TableColumnOutputter tableColumnOutputter2_5 = tableColumnOutputter3_1.column("FIRSTNAME");
    TableColumnOutputter tableColumnOutputter1_5 = tableColumnOutputter3_1.column("NAME");
    TableColumnOutputter tableColumnOutputter0_5 = tableColumnOutputter3_1.column("ID");

    Assertions.assertThat(tableColumnOutputter0).isSameAs(tableColumnOutputter0_1).isSameAs(tableColumnOutputter0_2).isSameAs(tableColumnOutputter0_4).isSameAs(tableColumnOutputter0_5);
    Assertions.assertThat(tableColumnOutputter1).isSameAs(tableColumnOutputter1_1).isSameAs(tableColumnOutputter1_2).isSameAs(tableColumnOutputter1_3).isSameAs(tableColumnOutputter1_4).isSameAs(tableColumnOutputter1_5);
    Assertions.assertThat(tableColumnOutputter2).isSameAs(tableColumnOutputter2_1).isSameAs(tableColumnOutputter2_2).isSameAs(tableColumnOutputter2_3).isSameAs(tableColumnOutputter2_4).isSameAs(tableColumnOutputter2_5);
    Assertions.assertThat(tableColumnOutputter3).isSameAs(tableColumnOutputter3_1).isSameAs(tableColumnOutputter3_2).isSameAs(tableColumnOutputter3_3).isSameAs(tableColumnOutputter3_4).isSameAs(tableColumnOutputter3_5);

    TableColumnValueOutputter tableColumnValueOutputter0 = tableColumnOutputter0.value();
    TableColumnValueOutputter tableColumnValueOutputter1 = tableColumnOutputter0.value();
    TableColumnValueOutputter tableColumnValueOutputter2 = tableColumnOutputter0.value();

    TableOutputter tableOutputterFromColumnValue = tableColumnValueOutputter0.returnToColumn().returnToTable();

    TableColumnValueOutputter tableColumnValueOutputter2_1 = tableColumnOutputter0.value(2);
    TableColumnValueOutputter tableColumnValueOutputter1_1 = tableColumnOutputter0.value(1);
    TableColumnValueOutputter tableColumnValueOutputter0_1 = tableColumnOutputter0.value(0);

    TableColumnValueOutputter tableColumnValueOutputter1_2 = tableColumnValueOutputter0_1.value();
    TableColumnValueOutputter tableColumnValueOutputter2_2 = tableColumnValueOutputter0_1.value();

    TableColumnValueOutputter tableColumnValueOutputter2_3 = tableColumnValueOutputter1_2.value(2);
    TableColumnValueOutputter tableColumnValueOutputter1_3 = tableColumnValueOutputter1_2.value(1);
    TableColumnValueOutputter tableColumnValueOutputter0_3 = tableColumnValueOutputter1_2.value(0);

    Assertions.assertThat(tableColumnValueOutputter0).isSameAs(tableColumnValueOutputter0_1).isSameAs(tableColumnValueOutputter0_3);
    Assertions.assertThat(tableColumnValueOutputter1).isSameAs(tableColumnValueOutputter1_1).isSameAs(tableColumnValueOutputter1_2).isSameAs(tableColumnValueOutputter1_3);
    Assertions.assertThat(tableColumnValueOutputter2).isSameAs(tableColumnValueOutputter2_1).isSameAs(tableColumnValueOutputter2_2).isSameAs(tableColumnValueOutputter2_3);

    Assertions.assertThat(tableOutputter).isSameAs(tableOutputterFromRow).isSameAs(tableOutputterFromRowValue).isSameAs(tableOutputterFromColumn).isSameAs(tableOutputterFromColumnValue);
  }
}
