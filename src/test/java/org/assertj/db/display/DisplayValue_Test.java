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
import org.assertj.db.type.Table;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.db.display.Displaying.display;

/**
 * Test the display of values.
 *
 * @author Régis Pouiller
 */
public class DisplayValue_Test extends AbstractTest {

  /**
   * This method tests the {@code display} display method.
   */
  @Test
  public void test_display_for_row_from_table() throws Exception {
    Table table = new Table(source, "actor");

    ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream();
    ByteArrayOutputStream byteArrayOutputStream1 = new ByteArrayOutputStream();
    ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
    ByteArrayOutputStream byteArrayOutputStream3 = new ByteArrayOutputStream();
    display(table).row().value().display(new PrintStream(byteArrayOutputStream0)).returnToRow()
                        .value(1).display(new PrintStream(byteArrayOutputStream1))
                        .value().display(new PrintStream(byteArrayOutputStream2))
                  .row(2).value(2).display(new PrintStream(byteArrayOutputStream3));
    Assertions.assertThat(byteArrayOutputStream0.toString()).isEqualTo(String.format("[Value at index 0 (column name : ID) of Row at index 0 of actor table]%n"
                                                                                     + "|----------|%n"
                                                                                     + "| ID       |%n"
                                                                                     + "| (NUMBER) |%n"
                                                                                     + "|----------|%n"
                                                                                     + "| 1        |%n"
                                                                                     + "|----------|%n"));
    Assertions.assertThat(byteArrayOutputStream1.toString()).isEqualTo(String.format("[Value at index 1 (column name : NAME) of Row at index 0 of actor table]%n"
                                                                                     + "|--------|%n"
                                                                                     + "| NAME   |%n"
                                                                                     + "| (TEXT) |%n"
                                                                                     + "|--------|%n"
                                                                                     + "| Weaver |%n"
                                                                                     + "|--------|%n"));
    Assertions.assertThat(byteArrayOutputStream2.toString()).isEqualTo(String.format("[Value at index 2 (column name : FIRSTNAME) of Row at index 0 of actor table]%n"
                                                                                     + "|-----------|%n"
                                                                                     + "| FIRSTNAME |%n"
                                                                                     + "| (TEXT)    |%n"
                                                                                     + "|-----------|%n"
                                                                                     + "| Sigourney |%n"
                                                                                     + "|-----------|%n"));
    Assertions.assertThat(byteArrayOutputStream3.toString()).isEqualTo(String.format("[Value at index 2 (column name : FIRSTNAME) of Row at index 2 of actor table]%n"
                                                                                     + "|-----------|%n"
                                                                                     + "| FIRSTNAME |%n"
                                                                                     + "| (TEXT)    |%n"
                                                                                     + "|-----------|%n"
                                                                                     + "| Sam       |%n"
                                                                                     + "|-----------|%n"));
  }

  /**
   * This method tests the {@code display} display method.
   */
  @Test
  public void test_display_for_row_from_request() throws Exception {
    Request request = new Request(source, "select * from actor");

    ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream();
    ByteArrayOutputStream byteArrayOutputStream1 = new ByteArrayOutputStream();
    ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
    ByteArrayOutputStream byteArrayOutputStream3 = new ByteArrayOutputStream();
    display(request).row().value().display(new PrintStream(byteArrayOutputStream0)).returnToRow()
                    .value(1).display(new PrintStream(byteArrayOutputStream1))
                          .value().display(new PrintStream(byteArrayOutputStream2))
                    .row(2).value(2).display(new PrintStream(byteArrayOutputStream3));
    Assertions.assertThat(byteArrayOutputStream0.toString()).isEqualTo(String.format("[Value at index 0 (column name : ID) of Row at index 0 of 'select * from actor' request]%n"
                          + "|----------|%n"
                          + "| ID       |%n"
                          + "| (NUMBER) |%n"
                          + "|----------|%n"
                          + "| 1        |%n"
                          + "|----------|%n"));
    Assertions.assertThat(byteArrayOutputStream1.toString()).isEqualTo(String.format("[Value at index 1 (column name : NAME) of Row at index 0 of 'select * from actor' request]%n"
                                                                                     + "|--------|%n"
                                                                                     + "| NAME   |%n"
                                                                                     + "| (TEXT) |%n"
                                                                                     + "|--------|%n"
                                                                                     + "| Weaver |%n"
                                                                                     + "|--------|%n"));
    Assertions.assertThat(byteArrayOutputStream2.toString()).isEqualTo(String.format("[Value at index 2 (column name : FIRSTNAME) of Row at index 0 of 'select * from actor' request]%n"
                                                                                     + "|-----------|%n"
                                                                                     + "| FIRSTNAME |%n"
                                                                                     + "| (TEXT)    |%n"
                                                                                     + "|-----------|%n"
                                                                                     + "| Sigourney |%n"
                                                                                     + "|-----------|%n"));
    Assertions.assertThat(byteArrayOutputStream3.toString()).isEqualTo(String.format("[Value at index 2 (column name : FIRSTNAME) of Row at index 2 of 'select * from actor' request]%n"
                                                                                     + "|-----------|%n"
                                                                                     + "| FIRSTNAME |%n"
                                                                                     + "| (TEXT)    |%n"
                                                                                     + "|-----------|%n"
                                                                                     + "| Sam       |%n"
                                                                                     + "|-----------|%n"));
  }

  /**
   * This method tests the {@code display} display method.
   */
  @Test
  public void test_display_for_column_from_table() throws Exception {
    Table table = new Table(source, "actor");

    ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream();
    ByteArrayOutputStream byteArrayOutputStream1 = new ByteArrayOutputStream();
    ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
    ByteArrayOutputStream byteArrayOutputStream3 = new ByteArrayOutputStream();
    display(table).column().value().display(new PrintStream(byteArrayOutputStream0)).returnToColumn()
                           .value(1).display(new PrintStream(byteArrayOutputStream1))
                           .value().display(new PrintStream(byteArrayOutputStream2))
                  .column("name").value(2).display(new PrintStream(byteArrayOutputStream3));
    Assertions.assertThat(byteArrayOutputStream0.toString()).isEqualTo(String.format("[Value at index 0 of Column at index 0 (column name : ID) of actor table]%n"
                          + "|----------|%n"
                          + "| ID       |%n"
                          + "| (NUMBER) |%n"
                          + "|----------|%n"
                          + "| 1        |%n"
                          + "|----------|%n"));
    Assertions.assertThat(byteArrayOutputStream1.toString()).isEqualTo(String.format("[Value at index 1 of Column at index 0 (column name : ID) of actor table]%n"
                                                                                     + "|----------|%n"
                                                                                     + "| ID       |%n"
                                                                                     + "| (NUMBER) |%n"
                                                                                     + "|----------|%n"
                                                                                     + "| 2        |%n"
                                                                                     + "|----------|%n"));
    Assertions.assertThat(byteArrayOutputStream2.toString()).isEqualTo(String.format("[Value at index 2 of Column at index 0 (column name : ID) of actor table]%n"
                                                                                     + "|----------|%n"
                                                                                     + "| ID       |%n"
                                                                                     + "| (NUMBER) |%n"
                                                                                     + "|----------|%n"
                                                                                     + "| 3        |%n"
                                                                                     + "|----------|%n"));
    Assertions.assertThat(byteArrayOutputStream3.toString()).isEqualTo(String.format("[Value at index 2 of Column at index 1 (column name : NAME) of actor table]%n"
                                                                                     + "|-------------|%n"
                                                                                     + "| NAME        |%n"
                                                                                     + "| (TEXT)      |%n"
                                                                                     + "|-------------|%n"
                                                                                     + "| Worthington |%n"
                                                                                     + "|-------------|%n"));
  }

  /**
   * This method tests the {@code display} display method.
   */
  @Test
  public void test_display_for_column_from_request() throws Exception {
    Request request = new Request(source, "select * from actor");

    ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream();
    ByteArrayOutputStream byteArrayOutputStream1 = new ByteArrayOutputStream();
    ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
    ByteArrayOutputStream byteArrayOutputStream3 = new ByteArrayOutputStream();
    display(request).column().value().display(new PrintStream(byteArrayOutputStream0)).returnToColumn()
                    .value(1).display(new PrintStream(byteArrayOutputStream1))
                             .value().display(new PrintStream(byteArrayOutputStream2))
                    .column("name").value(2).display(new PrintStream(byteArrayOutputStream3));
    Assertions.assertThat(byteArrayOutputStream0.toString()).isEqualTo(String.format(
            "[Value at index 0 of Column at index 0 (column name : ID) of 'select * from actor' request]%n"
            + "|----------|%n"
            + "| ID       |%n"
            + "| (NUMBER) |%n"
            + "|----------|%n"
            + "| 1        |%n"
            + "|----------|%n"));
    Assertions.assertThat(byteArrayOutputStream1.toString()).isEqualTo(String.format("[Value at index 1 of Column at index 0 (column name : ID) of 'select * from actor' request]%n"
                                                                                     + "|----------|%n"
                                                                                     + "| ID       |%n"
                                                                                     + "| (NUMBER) |%n"
                                                                                     + "|----------|%n"
                                                                                     + "| 2        |%n"
                                                                                     + "|----------|%n"));
    Assertions.assertThat(byteArrayOutputStream2.toString()).isEqualTo(String.format("[Value at index 2 of Column at index 0 (column name : ID) of 'select * from actor' request]%n"
                                                                                     + "|----------|%n"
                                                                                     + "| ID       |%n"
                                                                                     + "| (NUMBER) |%n"
                                                                                     + "|----------|%n"
                                                                                     + "| 3        |%n"
                                                                                     + "|----------|%n"));
    Assertions.assertThat(byteArrayOutputStream3.toString()).isEqualTo(String.format("[Value at index 2 of Column at index 1 (column name : NAME) of 'select * from actor' request]%n"
                                                                                     + "|-------------|%n"
                                                                                     + "| NAME        |%n"
                                                                                     + "| (TEXT)      |%n"
                                                                                     + "|-------------|%n"
                                                                                     + "| Worthington |%n"
                                                                                     + "|-------------|%n"));
  }
}
