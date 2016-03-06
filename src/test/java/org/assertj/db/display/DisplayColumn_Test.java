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
import org.assertj.db.common.NeedReload;
import org.assertj.db.type.Changes;
import org.assertj.db.type.Request;
import org.assertj.db.type.Table;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.db.display.Displaying.display;

/**
 * Test the display of columns.
 *
 * @author Régis Pouiller
 */
public class DisplayColumn_Test extends AbstractTest {

  /**
   * This method tests the {@code display} display method.
   */
  @Test
  public void test_display_for_table() throws Exception {
    Table table = new Table(source, "actor");

    ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream();
    ByteArrayOutputStream byteArrayOutputStream1 = new ByteArrayOutputStream();
    ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
    display(table).column().display(new PrintStream(byteArrayOutputStream0))
                  .column(1).display(new PrintStream(byteArrayOutputStream1))
                  .column().display(new PrintStream(byteArrayOutputStream2));
    Assertions.assertThat(byteArrayOutputStream0.toString()).isEqualTo(String.format("[Column at index 0 (column name : ID) of actor table]%n"
                                                                                     + "|-----------|----------|%n"
                                                                                     + "|           | ID       |%n"
                                                                                     + "|           | (NUMBER) |%n"
                                                                                     + "|-----------|----------|%n"
                                                                                     + "| Index : 0 | 1        |%n"
                                                                                     + "| Index : 1 | 2        |%n"
                                                                                     + "| Index : 2 | 3        |%n"
                                                                                     + "|-----------|----------|%n"));
    Assertions.assertThat(byteArrayOutputStream1.toString()).isEqualTo(String.format("[Column at index 1 (column name : NAME) of actor table]%n"
                                                                                     + "|-----------|-------------|%n"
                                                                                     + "|           | NAME        |%n"
                                                                                     + "|           | (TEXT)      |%n"
                                                                                     + "|-----------|-------------|%n"
                                                                                     + "| Index : 0 | Weaver      |%n"
                                                                                     + "| Index : 1 | Phoenix     |%n"
                                                                                     + "| Index : 2 | Worthington |%n"
                                                                                     + "|-----------|-------------|%n"));
    Assertions.assertThat(byteArrayOutputStream2.toString()).isEqualTo(String.format("[Column at index 2 (column name : FIRSTNAME) of actor table]%n"
                                                                                     + "|-----------|-----------|%n"
                                                                                     + "|           | FIRSTNAME |%n"
                                                                                     + "|           | (TEXT)    |%n"
                                                                                     + "|-----------|-----------|%n"
                                                                                     + "| Index : 0 | Sigourney |%n"
                                                                                     + "| Index : 1 | Joaquim   |%n"
                                                                                     + "| Index : 2 | Sam       |%n"
                                                                                     + "|-----------|-----------|%n"));
  }

  /**
   * This method tests the {@code display} display method.
   */
  @Test
  public void test_display_for_request() throws Exception {
    Request request = new Request(source, "select * from actor");

    ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream();
    ByteArrayOutputStream byteArrayOutputStream1 = new ByteArrayOutputStream();
    ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
    display(request).column().display(new PrintStream(byteArrayOutputStream0))
                    .column(1).display(new PrintStream(byteArrayOutputStream1))
                    .column().display(new PrintStream(byteArrayOutputStream2));
    Assertions.assertThat(byteArrayOutputStream0.toString()).isEqualTo(String.format("[Column at index 0 (column name : ID) of 'select * from actor' request]%n"
                                                                                     + "|-----------|----------|%n"
                                                                                     + "|           | ID       |%n"
                                                                                     + "|           | (NUMBER) |%n"
                                                                                     + "|-----------|----------|%n"
                                                                                     + "| Index : 0 | 1        |%n"
                                                                                     + "| Index : 1 | 2        |%n"
                                                                                     + "| Index : 2 | 3        |%n"
                                                                                     + "|-----------|----------|%n"));
    Assertions.assertThat(byteArrayOutputStream1.toString()).isEqualTo(String.format("[Column at index 1 (column name : NAME) of 'select * from actor' request]%n"
                                                                                     + "|-----------|-------------|%n"
                                                                                     + "|           | NAME        |%n"
                                                                                     + "|           | (TEXT)      |%n"
                                                                                     + "|-----------|-------------|%n"
                                                                                     + "| Index : 0 | Weaver      |%n"
                                                                                     + "| Index : 1 | Phoenix     |%n"
                                                                                     + "| Index : 2 | Worthington |%n"
                                                                                     + "|-----------|-------------|%n"));
    Assertions.assertThat(byteArrayOutputStream2.toString()).isEqualTo(String.format("[Column at index 2 (column name : FIRSTNAME) of 'select * from actor' request]%n"
                                                                                     + "|-----------|-----------|%n"
                                                                                     + "|           | FIRSTNAME |%n"
                                                                                     + "|           | (TEXT)    |%n"
                                                                                     + "|-----------|-----------|%n"
                                                                                     + "| Index : 0 | Sigourney |%n"
                                                                                     + "| Index : 1 | Joaquim   |%n"
                                                                                     + "| Index : 2 | Sam       |%n"
                                                                                     + "|-----------|-----------|%n"));
  }

  /**
   * This method tests the {@code display} display method.
   */
  @Test
  @NeedReload
  public void test_display_for_change() throws Exception {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream();
    ByteArrayOutputStream byteArrayOutputStream1 = new ByteArrayOutputStream();
    ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
    display(changes).change().column().display(new PrintStream(byteArrayOutputStream0))
                    .column().display(new PrintStream(byteArrayOutputStream1))
                    .column().display(new PrintStream(byteArrayOutputStream2));
    Assertions.assertThat(byteArrayOutputStream0.toString()).isEqualTo(String.format("[Column at index 0 (column name : ID) of Change at index 0 (on table : ACTOR and with primary key : [4]) of Changes on tables of 'sa/jdbc:h2:mem:test' source]%n"
                                                                                     + "|----------------|----------|%n"
                                                                                     + "|                | ID       |%n"
                                                                                     + "|                | (NUMBER) |%n"
                                                                                     + "|----------------|----------|%n"
                                                                                     + "| At start point | null     |%n"
                                                                                     + "|----------------|----------|%n"
                                                                                     + "| At end point   | 4        |%n"
                                                                                     + "|----------------|----------|%n"));
    Assertions.assertThat(byteArrayOutputStream1.toString()).isEqualTo(String.format("[Column at index 1 (column name : NAME) of Change at index 0 (on table : ACTOR and with primary key : [4]) of Changes on tables of 'sa/jdbc:h2:mem:test' source]%n"
                                                                                     + "|----------------|--------|%n"
                                                                                     + "|                | NAME   |%n"
                                                                                     + "|                | (TEXT) |%n"
                                                                                     + "|----------------|--------|%n"
                                                                                     + "| At start point | null   |%n"
                                                                                     + "|----------------|--------|%n"
                                                                                     + "| At end point   | Murray |%n"
                                                                                     + "|----------------|--------|%n"));
    Assertions.assertThat(byteArrayOutputStream2.toString()).isEqualTo(String.format("[Column at index 2 (column name : FIRSTNAME) of Change at index 0 (on table : ACTOR and with primary key : [4]) of Changes on tables of 'sa/jdbc:h2:mem:test' source]%n"
                                                                                     + "|----------------|-----------|%n"
                                                                                     + "|                | FIRSTNAME |%n"
                                                                                     + "|                | (TEXT)    |%n"
                                                                                     + "|----------------|-----------|%n"
                                                                                     + "| At start point | null      |%n"
                                                                                     + "|----------------|-----------|%n"
                                                                                     + "| At end point   | Bill      |%n"
                                                                                     + "|----------------|-----------|%n"));
  }
}
