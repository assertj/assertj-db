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
import org.assertj.db.type.Request;
import org.junit.Test;

import java.io.ByteArrayOutputStream;

import static org.assertj.db.display.Displaying.display;

/**
 * Test the display of requests.
 *
 * @author Régis Pouiller
 */
public class DisplayRequest_Test extends AbstractTest {

  /**
   * This method tests the {@code display} display method.
   */
  @Test
  public void test_display() throws Exception {
    Request request = new Request(source, "select * from actor");

    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    display(request).inStream(byteArrayOutputStream);
    Assertions.assertThat(byteArrayOutputStream.toString()).isEqualTo(String.format("['select * from actor' request]%n"
                                                                                    + "|-----------|---------|-----------|-------------|-----------|------------|--------------------------------------|%n"
                                                                                    + "|           |         |           |             |           |            |                                      |%n"
                                                                                    + "|           | PRIMARY | ID        | NAME        | FIRSTNAME | BIRTH      | ACTOR_IMDB                           |%n"
                                                                                    + "|           | KEY     | (NUMBER)  | (TEXT)      | (TEXT)    | (DATE)     | (UUID)                               |%n"
                                                                                    + "|           |         | Index : 0 | Index : 1   | Index : 2 | Index : 3  | Index : 4                            |%n"
                                                                                    + "|-----------|---------|-----------|-------------|-----------|------------|--------------------------------------|%n"
                                                                                    + "| Index : 0 |         | 1         | Weaver      | Sigourney | 1949-10-08 | 30b443ae-c0c9-4790-9bec-ce1380808435 |%n"
                                                                                    + "| Index : 1 |         | 2         | Phoenix     | Joaquim   | 1974-10-28 | 16319617-ae95-4087-9264-d3d21bf611b6 |%n"
                                                                                    + "| Index : 2 |         | 3         | Worthington | Sam       | 1976-08-02 | d735221b-5de5-4112-aa1e-49090cb75ada |%n"
                                                                                    + "|-----------|---------|-----------|-------------|-----------|------------|--------------------------------------|%n"));
  }

  /**
   * This method tests the navigation.
   */
  @Test
  public void test_navigation() throws Exception {
    Request request = new Request(source, "select * from actor");

    RequestDisplay requestDisplay = display(request);

    RequestRowDisplay requestRowDisplay0 = requestDisplay.row();
    RequestRowDisplay requestRowDisplay1 = requestDisplay.row();
    RequestRowDisplay requestRowDisplay2 = requestDisplay.row();

    RequestDisplay requestDisplayFromRow = requestRowDisplay0.returnToRequest();

    RequestRowDisplay requestRowDisplay2_1 = requestDisplay.row(2);
    RequestRowDisplay requestRowDisplay1_1 = requestDisplay.row(1);
    RequestRowDisplay requestRowDisplay0_1 = requestDisplay.row(0);

    RequestRowDisplay requestRowDisplay1_2 = requestRowDisplay0_1.row();
    RequestRowDisplay requestRowDisplay2_2 = requestRowDisplay0_1.row();

    RequestRowDisplay requestRowDisplay2_3 = requestRowDisplay1_2.row(2);
    RequestRowDisplay requestRowDisplay1_3 = requestRowDisplay1_2.row(1);
    RequestRowDisplay requestRowDisplay0_3 = requestRowDisplay1_2.row(0);

    Assertions.assertThat(requestRowDisplay0).isSameAs(requestRowDisplay0_1).isSameAs(requestRowDisplay0_3);
    Assertions.assertThat(requestRowDisplay1).isSameAs(requestRowDisplay1_1).isSameAs(requestRowDisplay1_2).isSameAs(requestRowDisplay1_3);
    Assertions.assertThat(requestRowDisplay2).isSameAs(requestRowDisplay2_1).isSameAs(requestRowDisplay2_2).isSameAs(requestRowDisplay2_3);

    RequestRowValueDisplay requestRowValueDisplay0 = requestRowDisplay0.value();
    RequestRowValueDisplay requestRowValueDisplay1 = requestRowDisplay0.value();
    RequestRowValueDisplay requestRowValueDisplay2 = requestRowDisplay0.value();
    RequestRowValueDisplay requestRowValueDisplay3 = requestRowDisplay0.value();

    RequestDisplay requestDisplayFromRowValue = requestRowValueDisplay0.returnToRow().returnToRequest();

    RequestRowValueDisplay requestRowValueDisplay3_1 = requestRowDisplay0.value(3);
    RequestRowValueDisplay requestRowValueDisplay2_1 = requestRowDisplay0.value(2);
    RequestRowValueDisplay requestRowValueDisplay1_1 = requestRowDisplay0.value(1);
    RequestRowValueDisplay requestRowValueDisplay0_1 = requestRowDisplay0.value(0);

    RequestRowValueDisplay requestRowValueDisplay3_2 = requestRowDisplay0.value("BIRTH");
    RequestRowValueDisplay requestRowValueDisplay2_2 = requestRowDisplay0.value("FIRSTNAME");
    RequestRowValueDisplay requestRowValueDisplay1_2 = requestRowDisplay0.value("NAME");
    RequestRowValueDisplay requestRowValueDisplay0_2 = requestRowDisplay0.value("ID");

    RequestRowValueDisplay requestRowValueDisplay1_3 = requestRowValueDisplay0_2.value();
    RequestRowValueDisplay requestRowValueDisplay2_3 = requestRowValueDisplay0_2.value();
    RequestRowValueDisplay requestRowValueDisplay3_3 = requestRowValueDisplay0_2.value();

    RequestRowValueDisplay requestRowValueDisplay3_4 = requestRowValueDisplay1_3.value(3);
    RequestRowValueDisplay requestRowValueDisplay2_4 = requestRowValueDisplay1_3.value(2);
    RequestRowValueDisplay requestRowValueDisplay1_4 = requestRowValueDisplay1_3.value(1);
    RequestRowValueDisplay requestRowValueDisplay0_4 = requestRowValueDisplay1_3.value(0);

    RequestRowValueDisplay requestRowValueDisplay3_5 = requestRowValueDisplay3_1.value("BIRTH");
    RequestRowValueDisplay requestRowValueDisplay2_5 = requestRowValueDisplay3_1.value("FIRSTNAME");
    RequestRowValueDisplay requestRowValueDisplay1_5 = requestRowValueDisplay3_1.value("NAME");
    RequestRowValueDisplay requestRowValueDisplay0_5 = requestRowValueDisplay3_1.value("ID");

    Assertions.assertThat(requestRowValueDisplay0).isSameAs(requestRowValueDisplay0_1).isSameAs(requestRowValueDisplay0_2).isSameAs(requestRowValueDisplay0_4).isSameAs(requestRowValueDisplay0_5);
    Assertions.assertThat(requestRowValueDisplay1).isSameAs(requestRowValueDisplay1_1).isSameAs(requestRowValueDisplay1_2).isSameAs(requestRowValueDisplay1_3).isSameAs(requestRowValueDisplay1_4).isSameAs(requestRowValueDisplay1_5);
    Assertions.assertThat(requestRowValueDisplay2).isSameAs(requestRowValueDisplay2_1).isSameAs(requestRowValueDisplay2_2).isSameAs(requestRowValueDisplay2_3).isSameAs(requestRowValueDisplay2_4).isSameAs(requestRowValueDisplay2_5);
    Assertions.assertThat(requestRowValueDisplay3).isSameAs(requestRowValueDisplay3_1).isSameAs(requestRowValueDisplay3_2).isSameAs(requestRowValueDisplay3_3).isSameAs(requestRowValueDisplay3_4).isSameAs(requestRowValueDisplay3_5);

    RequestColumnDisplay requestColumnDisplay0 = requestDisplay.column();
    RequestColumnDisplay requestColumnDisplay1 = requestDisplay.column();
    RequestColumnDisplay requestColumnDisplay2 = requestDisplay.column();
    RequestColumnDisplay requestColumnDisplay3 = requestDisplay.column();

    RequestDisplay requestDisplayFromColumn = requestColumnDisplay0.returnToRequest();

    RequestColumnDisplay requestColumnDisplay3_1 = requestDisplay.column(3);
    RequestColumnDisplay requestColumnDisplay2_1 = requestDisplay.column(2);
    RequestColumnDisplay requestColumnDisplay1_1 = requestDisplay.column(1);
    RequestColumnDisplay requestColumnDisplay0_1 = requestDisplay.column(0);

    RequestColumnDisplay requestColumnDisplay3_2 = requestDisplay.column("BIRTH");
    RequestColumnDisplay requestColumnDisplay2_2 = requestDisplay.column("FIRSTNAME");
    RequestColumnDisplay requestColumnDisplay1_2 = requestDisplay.column("NAME");
    RequestColumnDisplay requestColumnDisplay0_2 = requestDisplay.column("ID");

    RequestColumnDisplay requestColumnDisplay1_3 = requestColumnDisplay0_2.column();
    RequestColumnDisplay requestColumnDisplay2_3 = requestColumnDisplay0_2.column();
    RequestColumnDisplay requestColumnDisplay3_3 = requestColumnDisplay0_2.column();

    RequestColumnDisplay requestColumnDisplay3_4 = requestColumnDisplay1_3.column(3);
    RequestColumnDisplay requestColumnDisplay2_4 = requestColumnDisplay1_3.column(2);
    RequestColumnDisplay requestColumnDisplay1_4 = requestColumnDisplay1_3.column(1);
    RequestColumnDisplay requestColumnDisplay0_4 = requestColumnDisplay1_3.column(0);

    RequestColumnDisplay requestColumnDisplay3_5 = requestColumnDisplay3_1.column("BIRTH");
    RequestColumnDisplay requestColumnDisplay2_5 = requestColumnDisplay3_1.column("FIRSTNAME");
    RequestColumnDisplay requestColumnDisplay1_5 = requestColumnDisplay3_1.column("NAME");
    RequestColumnDisplay requestColumnDisplay0_5 = requestColumnDisplay3_1.column("ID");

    Assertions.assertThat(requestColumnDisplay0).isSameAs(requestColumnDisplay0_1).isSameAs(requestColumnDisplay0_2).isSameAs(requestColumnDisplay0_4).isSameAs(requestColumnDisplay0_5);
    Assertions.assertThat(requestColumnDisplay1).isSameAs(requestColumnDisplay1_1).isSameAs(requestColumnDisplay1_2).isSameAs(requestColumnDisplay1_3).isSameAs(requestColumnDisplay1_4).isSameAs(requestColumnDisplay1_5);
    Assertions.assertThat(requestColumnDisplay2).isSameAs(requestColumnDisplay2_1).isSameAs(requestColumnDisplay2_2).isSameAs(requestColumnDisplay2_3).isSameAs(requestColumnDisplay2_4).isSameAs(requestColumnDisplay2_5);
    Assertions.assertThat(requestColumnDisplay3).isSameAs(requestColumnDisplay3_1).isSameAs(requestColumnDisplay3_2).isSameAs(requestColumnDisplay3_3).isSameAs(requestColumnDisplay3_4).isSameAs(requestColumnDisplay3_5);

    RequestColumnValueDisplay requestColumnValueDisplay0 = requestColumnDisplay0.value();
    RequestColumnValueDisplay requestColumnValueDisplay1 = requestColumnDisplay0.value();
    RequestColumnValueDisplay requestColumnValueDisplay2 = requestColumnDisplay0.value();

    RequestDisplay requestDisplayFromColumnValue = requestColumnValueDisplay0.returnToColumn().returnToRequest();

    RequestColumnValueDisplay requestColumnValueDisplay2_1 = requestColumnDisplay0.value(2);
    RequestColumnValueDisplay requestColumnValueDisplay1_1 = requestColumnDisplay0.value(1);
    RequestColumnValueDisplay requestColumnValueDisplay0_1 = requestColumnDisplay0.value(0);

    RequestColumnValueDisplay requestColumnValueDisplay1_2 = requestColumnValueDisplay0_1.value();
    RequestColumnValueDisplay requestColumnValueDisplay2_2 = requestColumnValueDisplay0_1.value();

    RequestColumnValueDisplay requestColumnValueDisplay2_3 = requestColumnValueDisplay1_2.value(2);
    RequestColumnValueDisplay requestColumnValueDisplay1_3 = requestColumnValueDisplay1_2.value(1);
    RequestColumnValueDisplay requestColumnValueDisplay0_3 = requestColumnValueDisplay1_2.value(0);

    Assertions.assertThat(requestColumnValueDisplay0).isSameAs(requestColumnValueDisplay0_1).isSameAs(requestColumnValueDisplay0_3);
    Assertions.assertThat(requestColumnValueDisplay1).isSameAs(requestColumnValueDisplay1_1).isSameAs(requestColumnValueDisplay1_2).isSameAs(requestColumnValueDisplay1_3);
    Assertions.assertThat(requestColumnValueDisplay2).isSameAs(requestColumnValueDisplay2_1).isSameAs(requestColumnValueDisplay2_2).isSameAs(requestColumnValueDisplay2_3);

    Assertions.assertThat(requestDisplay).isSameAs(requestDisplayFromRow).isSameAs(requestDisplayFromRowValue).isSameAs(requestDisplayFromColumn).isSameAs(requestDisplayFromColumnValue);
  }
}
