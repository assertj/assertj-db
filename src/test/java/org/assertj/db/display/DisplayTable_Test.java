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
package org.assertj.db.display;

import org.assertj.core.api.Assertions;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Table;
import org.junit.Test;

import java.io.ByteArrayOutputStream;

import static org.assertj.db.display.Displaying.display;

/**
 * Test the display of tables.
 *
 * @author Régis Pouiller
 */
public class DisplayTable_Test extends AbstractTest {

  /**
   * This method tests the {@code display} display method.
   */
  @Test
  public void test_display() throws Exception {
    Table table = new Table(source, "actor");

    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    display(table).inStream(byteArrayOutputStream);
    Assertions.assertThat(byteArrayOutputStream.toString()).isEqualTo(String.format("[actor table]%n"
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
    Table table = new Table(source, "actor");

    TableDisplay tableDisplay = display(table);

    TableRowDisplay tableRowDisplay0 = tableDisplay.row();
    TableRowDisplay tableRowDisplay1 = tableDisplay.row();
    TableRowDisplay tableRowDisplay2 = tableDisplay.row();

    TableDisplay tableDisplayFromRow = tableRowDisplay0.returnToTable();

    TableRowDisplay tableRowDisplay2_1 = tableDisplay.row(2);
    TableRowDisplay tableRowDisplay1_1 = tableDisplay.row(1);
    TableRowDisplay tableRowDisplay0_1 = tableDisplay.row(0);

    TableRowDisplay tableRowDisplay1_2 = tableRowDisplay0_1.row();
    TableRowDisplay tableRowDisplay2_2 = tableRowDisplay0_1.row();

    TableRowDisplay tableRowDisplay2_3 = tableRowDisplay1_2.row(2);
    TableRowDisplay tableRowDisplay1_3 = tableRowDisplay1_2.row(1);
    TableRowDisplay tableRowDisplay0_3 = tableRowDisplay1_2.row(0);

    Assertions.assertThat(tableRowDisplay0).isSameAs(tableRowDisplay0_1).isSameAs(tableRowDisplay0_3);
    Assertions.assertThat(tableRowDisplay1).isSameAs(tableRowDisplay1_1).isSameAs(tableRowDisplay1_2).isSameAs(tableRowDisplay1_3);
    Assertions.assertThat(tableRowDisplay2).isSameAs(tableRowDisplay2_1).isSameAs(tableRowDisplay2_2).isSameAs(tableRowDisplay2_3);

    TableRowValueDisplay tableRowValueDisplay0 = tableRowDisplay0.value();
    TableRowValueDisplay tableRowValueDisplay1 = tableRowDisplay0.value();
    TableRowValueDisplay tableRowValueDisplay2 = tableRowDisplay0.value();
    TableRowValueDisplay tableRowValueDisplay3 = tableRowDisplay0.value();

    TableDisplay tableDisplayFromRowValue = tableRowValueDisplay0.returnToRow().returnToTable();

    TableRowValueDisplay tableRowValueDisplay3_1 = tableRowDisplay0.value(3);
    TableRowValueDisplay tableRowValueDisplay2_1 = tableRowDisplay0.value(2);
    TableRowValueDisplay tableRowValueDisplay1_1 = tableRowDisplay0.value(1);
    TableRowValueDisplay tableRowValueDisplay0_1 = tableRowDisplay0.value(0);

    TableRowValueDisplay tableRowValueDisplay3_2 = tableRowDisplay0.value("BIRTH");
    TableRowValueDisplay tableRowValueDisplay2_2 = tableRowDisplay0.value("FIRSTNAME");
    TableRowValueDisplay tableRowValueDisplay1_2 = tableRowDisplay0.value("NAME");
    TableRowValueDisplay tableRowValueDisplay0_2 = tableRowDisplay0.value("ID");

    TableRowValueDisplay tableRowValueDisplay1_3 = tableRowValueDisplay0_2.value();
    TableRowValueDisplay tableRowValueDisplay2_3 = tableRowValueDisplay0_2.value();
    TableRowValueDisplay tableRowValueDisplay3_3 = tableRowValueDisplay0_2.value();

    TableRowValueDisplay tableRowValueDisplay3_4 = tableRowValueDisplay1_3.value(3);
    TableRowValueDisplay tableRowValueDisplay2_4 = tableRowValueDisplay1_3.value(2);
    TableRowValueDisplay tableRowValueDisplay1_4 = tableRowValueDisplay1_3.value(1);
    TableRowValueDisplay tableRowValueDisplay0_4 = tableRowValueDisplay1_3.value(0);

    TableRowValueDisplay tableRowValueDisplay3_5 = tableRowValueDisplay3_1.value("BIRTH");
    TableRowValueDisplay tableRowValueDisplay2_5 = tableRowValueDisplay3_1.value("FIRSTNAME");
    TableRowValueDisplay tableRowValueDisplay1_5 = tableRowValueDisplay3_1.value("NAME");
    TableRowValueDisplay tableRowValueDisplay0_5 = tableRowValueDisplay3_1.value("ID");

    Assertions.assertThat(tableRowValueDisplay0).isSameAs(tableRowValueDisplay0_1).isSameAs(tableRowValueDisplay0_2).isSameAs(tableRowValueDisplay0_4).isSameAs(tableRowValueDisplay0_5);
    Assertions.assertThat(tableRowValueDisplay1).isSameAs(tableRowValueDisplay1_1).isSameAs(tableRowValueDisplay1_2).isSameAs(tableRowValueDisplay1_3).isSameAs(tableRowValueDisplay1_4).isSameAs(tableRowValueDisplay1_5);
    Assertions.assertThat(tableRowValueDisplay2).isSameAs(tableRowValueDisplay2_1).isSameAs(tableRowValueDisplay2_2).isSameAs(tableRowValueDisplay2_3).isSameAs(tableRowValueDisplay2_4).isSameAs(tableRowValueDisplay2_5);
    Assertions.assertThat(tableRowValueDisplay3).isSameAs(tableRowValueDisplay3_1).isSameAs(tableRowValueDisplay3_2).isSameAs(tableRowValueDisplay3_3).isSameAs(tableRowValueDisplay3_4).isSameAs(tableRowValueDisplay3_5);

    TableColumnDisplay tableColumnDisplay0 = tableDisplay.column();
    TableColumnDisplay tableColumnDisplay1 = tableDisplay.column();
    TableColumnDisplay tableColumnDisplay2 = tableDisplay.column();
    TableColumnDisplay tableColumnDisplay3 = tableDisplay.column();

    TableDisplay tableDisplayFromColumn = tableColumnDisplay0.returnToTable();

    TableColumnDisplay tableColumnDisplay3_1 = tableDisplay.column(3);
    TableColumnDisplay tableColumnDisplay2_1 = tableDisplay.column(2);
    TableColumnDisplay tableColumnDisplay1_1 = tableDisplay.column(1);
    TableColumnDisplay tableColumnDisplay0_1 = tableDisplay.column(0);

    TableColumnDisplay tableColumnDisplay3_2 = tableDisplay.column("BIRTH");
    TableColumnDisplay tableColumnDisplay2_2 = tableDisplay.column("FIRSTNAME");
    TableColumnDisplay tableColumnDisplay1_2 = tableDisplay.column("NAME");
    TableColumnDisplay tableColumnDisplay0_2 = tableDisplay.column("ID");

    TableColumnDisplay tableColumnDisplay1_3 = tableColumnDisplay0_2.column();
    TableColumnDisplay tableColumnDisplay2_3 = tableColumnDisplay0_2.column();
    TableColumnDisplay tableColumnDisplay3_3 = tableColumnDisplay0_2.column();

    TableColumnDisplay tableColumnDisplay3_4 = tableColumnDisplay1_3.column(3);
    TableColumnDisplay tableColumnDisplay2_4 = tableColumnDisplay1_3.column(2);
    TableColumnDisplay tableColumnDisplay1_4 = tableColumnDisplay1_3.column(1);
    TableColumnDisplay tableColumnDisplay0_4 = tableColumnDisplay1_3.column(0);

    TableColumnDisplay tableColumnDisplay3_5 = tableColumnDisplay3_1.column("BIRTH");
    TableColumnDisplay tableColumnDisplay2_5 = tableColumnDisplay3_1.column("FIRSTNAME");
    TableColumnDisplay tableColumnDisplay1_5 = tableColumnDisplay3_1.column("NAME");
    TableColumnDisplay tableColumnDisplay0_5 = tableColumnDisplay3_1.column("ID");

    Assertions.assertThat(tableColumnDisplay0).isSameAs(tableColumnDisplay0_1).isSameAs(tableColumnDisplay0_2).isSameAs(tableColumnDisplay0_4).isSameAs(tableColumnDisplay0_5);
    Assertions.assertThat(tableColumnDisplay1).isSameAs(tableColumnDisplay1_1).isSameAs(tableColumnDisplay1_2).isSameAs(tableColumnDisplay1_3).isSameAs(tableColumnDisplay1_4).isSameAs(tableColumnDisplay1_5);
    Assertions.assertThat(tableColumnDisplay2).isSameAs(tableColumnDisplay2_1).isSameAs(tableColumnDisplay2_2).isSameAs(tableColumnDisplay2_3).isSameAs(tableColumnDisplay2_4).isSameAs(tableColumnDisplay2_5);
    Assertions.assertThat(tableColumnDisplay3).isSameAs(tableColumnDisplay3_1).isSameAs(tableColumnDisplay3_2).isSameAs(tableColumnDisplay3_3).isSameAs(tableColumnDisplay3_4).isSameAs(tableColumnDisplay3_5);

    TableColumnValueDisplay tableColumnValueDisplay0 = tableColumnDisplay0.value();
    TableColumnValueDisplay tableColumnValueDisplay1 = tableColumnDisplay0.value();
    TableColumnValueDisplay tableColumnValueDisplay2 = tableColumnDisplay0.value();

    TableDisplay tableDisplayFromColumnValue = tableColumnValueDisplay0.returnToColumn().returnToTable();

    TableColumnValueDisplay tableColumnValueDisplay2_1 = tableColumnDisplay0.value(2);
    TableColumnValueDisplay tableColumnValueDisplay1_1 = tableColumnDisplay0.value(1);
    TableColumnValueDisplay tableColumnValueDisplay0_1 = tableColumnDisplay0.value(0);

    TableColumnValueDisplay tableColumnValueDisplay1_2 = tableColumnValueDisplay0_1.value();
    TableColumnValueDisplay tableColumnValueDisplay2_2 = tableColumnValueDisplay0_1.value();

    TableColumnValueDisplay tableColumnValueDisplay2_3 = tableColumnValueDisplay1_2.value(2);
    TableColumnValueDisplay tableColumnValueDisplay1_3 = tableColumnValueDisplay1_2.value(1);
    TableColumnValueDisplay tableColumnValueDisplay0_3 = tableColumnValueDisplay1_2.value(0);

    Assertions.assertThat(tableColumnValueDisplay0).isSameAs(tableColumnValueDisplay0_1).isSameAs(tableColumnValueDisplay0_3);
    Assertions.assertThat(tableColumnValueDisplay1).isSameAs(tableColumnValueDisplay1_1).isSameAs(tableColumnValueDisplay1_2).isSameAs(tableColumnValueDisplay1_3);
    Assertions.assertThat(tableColumnValueDisplay2).isSameAs(tableColumnValueDisplay2_1).isSameAs(tableColumnValueDisplay2_2).isSameAs(tableColumnValueDisplay2_3);

    Assertions.assertThat(tableDisplay).isSameAs(tableDisplayFromRow).isSameAs(tableDisplayFromRowValue).isSameAs(tableDisplayFromColumn).isSameAs(tableDisplayFromColumnValue);
  }
}