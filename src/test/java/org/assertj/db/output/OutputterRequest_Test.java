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
package org.assertj.db.output;

import org.assertj.core.api.Assertions;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Request;
import org.junit.Test;

import java.io.ByteArrayOutputStream;

import static org.assertj.db.output.Outputs.output;

/**
 * Test the output of requests.
 *
 * @author Régis Pouiller
 */
public class OutputterRequest_Test extends AbstractTest {

  /**
   * This method tests the {@code output} output method.
   */
  @Test
  public void test_output() throws Exception {
    Request request = new Request(source, "select * from actor");

    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    output(request).toStream(byteArrayOutputStream);
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

    RequestOutputter requestOutputter = output(request);

    RequestRowOutputter requestRowOutputter0 = requestOutputter.row();
    RequestRowOutputter requestRowOutputter1 = requestOutputter.row();
    RequestRowOutputter requestRowOutputter2 = requestOutputter.row();

    RequestOutputter requestOutputterFromRow = requestRowOutputter0.returnToRequest();

    RequestRowOutputter requestRowOutputter2_1 = requestOutputter.row(2);
    RequestRowOutputter requestRowOutputter1_1 = requestOutputter.row(1);
    RequestRowOutputter requestRowOutputter0_1 = requestOutputter.row(0);

    RequestRowOutputter requestRowOutputter1_2 = requestRowOutputter0_1.row();
    RequestRowOutputter requestRowOutputter2_2 = requestRowOutputter0_1.row();

    RequestRowOutputter requestRowOutputter2_3 = requestRowOutputter1_2.row(2);
    RequestRowOutputter requestRowOutputter1_3 = requestRowOutputter1_2.row(1);
    RequestRowOutputter requestRowOutputter0_3 = requestRowOutputter1_2.row(0);

    Assertions.assertThat(requestRowOutputter0).isSameAs(requestRowOutputter0_1).isSameAs(requestRowOutputter0_3);
    Assertions.assertThat(requestRowOutputter1).isSameAs(requestRowOutputter1_1).isSameAs(requestRowOutputter1_2).isSameAs(requestRowOutputter1_3);
    Assertions.assertThat(requestRowOutputter2).isSameAs(requestRowOutputter2_1).isSameAs(requestRowOutputter2_2).isSameAs(requestRowOutputter2_3);

    RequestRowValueOutputter requestRowValueOutputter0 = requestRowOutputter0.value();
    RequestRowValueOutputter requestRowValueOutputter1 = requestRowOutputter0.value();
    RequestRowValueOutputter requestRowValueOutputter2 = requestRowOutputter0.value();
    RequestRowValueOutputter requestRowValueOutputter3 = requestRowOutputter0.value();

    RequestOutputter requestOutputterFromRowValue = requestRowValueOutputter0.returnToRow().returnToRequest();

    RequestRowValueOutputter requestRowValueOutputter3_1 = requestRowOutputter0.value(3);
    RequestRowValueOutputter requestRowValueOutputter2_1 = requestRowOutputter0.value(2);
    RequestRowValueOutputter requestRowValueOutputter1_1 = requestRowOutputter0.value(1);
    RequestRowValueOutputter requestRowValueOutputter0_1 = requestRowOutputter0.value(0);

    RequestRowValueOutputter requestRowValueOutputter3_2 = requestRowOutputter0.value("BIRTH");
    RequestRowValueOutputter requestRowValueOutputter2_2 = requestRowOutputter0.value("FIRSTNAME");
    RequestRowValueOutputter requestRowValueOutputter1_2 = requestRowOutputter0.value("NAME");
    RequestRowValueOutputter requestRowValueOutputter0_2 = requestRowOutputter0.value("ID");

    RequestRowValueOutputter requestRowValueOutputter1_3 = requestRowValueOutputter0_2.value();
    RequestRowValueOutputter requestRowValueOutputter2_3 = requestRowValueOutputter0_2.value();
    RequestRowValueOutputter requestRowValueOutputter3_3 = requestRowValueOutputter0_2.value();

    RequestRowValueOutputter requestRowValueOutputter3_4 = requestRowValueOutputter1_3.value(3);
    RequestRowValueOutputter requestRowValueOutputter2_4 = requestRowValueOutputter1_3.value(2);
    RequestRowValueOutputter requestRowValueOutputter1_4 = requestRowValueOutputter1_3.value(1);
    RequestRowValueOutputter requestRowValueOutputter0_4 = requestRowValueOutputter1_3.value(0);

    RequestRowValueOutputter requestRowValueOutputter3_5 = requestRowValueOutputter3_1.value("BIRTH");
    RequestRowValueOutputter requestRowValueOutputter2_5 = requestRowValueOutputter3_1.value("FIRSTNAME");
    RequestRowValueOutputter requestRowValueOutputter1_5 = requestRowValueOutputter3_1.value("NAME");
    RequestRowValueOutputter requestRowValueOutputter0_5 = requestRowValueOutputter3_1.value("ID");

    Assertions.assertThat(requestRowValueOutputter0).isSameAs(requestRowValueOutputter0_1).isSameAs(requestRowValueOutputter0_2).isSameAs(requestRowValueOutputter0_4).isSameAs(requestRowValueOutputter0_5);
    Assertions.assertThat(requestRowValueOutputter1).isSameAs(requestRowValueOutputter1_1).isSameAs(requestRowValueOutputter1_2).isSameAs(requestRowValueOutputter1_3).isSameAs(requestRowValueOutputter1_4).isSameAs(requestRowValueOutputter1_5);
    Assertions.assertThat(requestRowValueOutputter2).isSameAs(requestRowValueOutputter2_1).isSameAs(requestRowValueOutputter2_2).isSameAs(requestRowValueOutputter2_3).isSameAs(requestRowValueOutputter2_4).isSameAs(requestRowValueOutputter2_5);
    Assertions.assertThat(requestRowValueOutputter3).isSameAs(requestRowValueOutputter3_1).isSameAs(requestRowValueOutputter3_2).isSameAs(requestRowValueOutputter3_3).isSameAs(requestRowValueOutputter3_4).isSameAs(requestRowValueOutputter3_5);

    RequestColumnOutputter requestColumnOutputter0 = requestOutputter.column();
    RequestColumnOutputter requestColumnOutputter1 = requestOutputter.column();
    RequestColumnOutputter requestColumnOutputter2 = requestOutputter.column();
    RequestColumnOutputter requestColumnOutputter3 = requestOutputter.column();

    RequestOutputter requestOutputterFromColumn = requestColumnOutputter0.returnToRequest();

    RequestColumnOutputter requestColumnOutputter3_1 = requestOutputter.column(3);
    RequestColumnOutputter requestColumnOutputter2_1 = requestOutputter.column(2);
    RequestColumnOutputter requestColumnOutputter1_1 = requestOutputter.column(1);
    RequestColumnOutputter requestColumnOutputter0_1 = requestOutputter.column(0);

    RequestColumnOutputter requestColumnOutputter3_2 = requestOutputter.column("BIRTH");
    RequestColumnOutputter requestColumnOutputter2_2 = requestOutputter.column("FIRSTNAME");
    RequestColumnOutputter requestColumnOutputter1_2 = requestOutputter.column("NAME");
    RequestColumnOutputter requestColumnOutputter0_2 = requestOutputter.column("ID");

    RequestColumnOutputter requestColumnOutputter1_3 = requestColumnOutputter0_2.column();
    RequestColumnOutputter requestColumnOutputter2_3 = requestColumnOutputter0_2.column();
    RequestColumnOutputter requestColumnOutputter3_3 = requestColumnOutputter0_2.column();

    RequestColumnOutputter requestColumnOutputter3_4 = requestColumnOutputter1_3.column(3);
    RequestColumnOutputter requestColumnOutputter2_4 = requestColumnOutputter1_3.column(2);
    RequestColumnOutputter requestColumnOutputter1_4 = requestColumnOutputter1_3.column(1);
    RequestColumnOutputter requestColumnOutputter0_4 = requestColumnOutputter1_3.column(0);

    RequestColumnOutputter requestColumnOutputter3_5 = requestColumnOutputter3_1.column("BIRTH");
    RequestColumnOutputter requestColumnOutputter2_5 = requestColumnOutputter3_1.column("FIRSTNAME");
    RequestColumnOutputter requestColumnOutputter1_5 = requestColumnOutputter3_1.column("NAME");
    RequestColumnOutputter requestColumnOutputter0_5 = requestColumnOutputter3_1.column("ID");

    Assertions.assertThat(requestColumnOutputter0).isSameAs(requestColumnOutputter0_1).isSameAs(requestColumnOutputter0_2).isSameAs(requestColumnOutputter0_4).isSameAs(requestColumnOutputter0_5);
    Assertions.assertThat(requestColumnOutputter1).isSameAs(requestColumnOutputter1_1).isSameAs(requestColumnOutputter1_2).isSameAs(requestColumnOutputter1_3).isSameAs(requestColumnOutputter1_4).isSameAs(requestColumnOutputter1_5);
    Assertions.assertThat(requestColumnOutputter2).isSameAs(requestColumnOutputter2_1).isSameAs(requestColumnOutputter2_2).isSameAs(requestColumnOutputter2_3).isSameAs(requestColumnOutputter2_4).isSameAs(requestColumnOutputter2_5);
    Assertions.assertThat(requestColumnOutputter3).isSameAs(requestColumnOutputter3_1).isSameAs(requestColumnOutputter3_2).isSameAs(requestColumnOutputter3_3).isSameAs(requestColumnOutputter3_4).isSameAs(requestColumnOutputter3_5);

    RequestColumnValueOutputter requestColumnValueOutputter0 = requestColumnOutputter0.value();
    RequestColumnValueOutputter requestColumnValueOutputter1 = requestColumnOutputter0.value();
    RequestColumnValueOutputter requestColumnValueOutputter2 = requestColumnOutputter0.value();

    RequestOutputter requestOutputterFromColumnValue = requestColumnValueOutputter0.returnToColumn().returnToRequest();

    RequestColumnValueOutputter requestColumnValueOutputter2_1 = requestColumnOutputter0.value(2);
    RequestColumnValueOutputter requestColumnValueOutputter1_1 = requestColumnOutputter0.value(1);
    RequestColumnValueOutputter requestColumnValueOutputter0_1 = requestColumnOutputter0.value(0);

    RequestColumnValueOutputter requestColumnValueOutputter1_2 = requestColumnValueOutputter0_1.value();
    RequestColumnValueOutputter requestColumnValueOutputter2_2 = requestColumnValueOutputter0_1.value();

    RequestColumnValueOutputter requestColumnValueOutputter2_3 = requestColumnValueOutputter1_2.value(2);
    RequestColumnValueOutputter requestColumnValueOutputter1_3 = requestColumnValueOutputter1_2.value(1);
    RequestColumnValueOutputter requestColumnValueOutputter0_3 = requestColumnValueOutputter1_2.value(0);

    Assertions.assertThat(requestColumnValueOutputter0).isSameAs(requestColumnValueOutputter0_1).isSameAs(requestColumnValueOutputter0_3);
    Assertions.assertThat(requestColumnValueOutputter1).isSameAs(requestColumnValueOutputter1_1).isSameAs(requestColumnValueOutputter1_2).isSameAs(requestColumnValueOutputter1_3);
    Assertions.assertThat(requestColumnValueOutputter2).isSameAs(requestColumnValueOutputter2_1).isSameAs(requestColumnValueOutputter2_2).isSameAs(requestColumnValueOutputter2_3);

    Assertions.assertThat(requestOutputter).isSameAs(requestOutputterFromRow).isSameAs(requestOutputterFromRowValue).isSameAs(requestOutputterFromColumn).isSameAs(requestOutputterFromColumnValue);
  }
}