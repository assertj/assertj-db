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

import static org.assertj.db.output.Outputs.output;

import java.io.ByteArrayOutputStream;

import org.assertj.core.api.Assertions;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.assertj.db.type.Changes;
import org.assertj.db.type.Request;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Test the output of columns.
 *
 * @author Régis Pouiller
 * @author Julien Roy
 */
public class OutputterColumn_Test extends AbstractTest {

  /**
   * This method tests the {@code output} output method.
   */
  @Test
  public void test_output_for_table() throws Exception {
    Table table = new Table(jdbcConnectionProvider, "actor");

    ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream();
    ByteArrayOutputStream byteArrayOutputStream1 = new ByteArrayOutputStream();
    ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
    Outputs.output(table).column().toStream(byteArrayOutputStream0)
      .column(1).toStream(byteArrayOutputStream1)
      .column().toStream(byteArrayOutputStream2);
    Assertions.assertThat(byteArrayOutputStream0.toString()).isEqualTo(String.format("[Column at index 0 (column name : ID) of ACTOR table]%n"
      + "|-----------|----------|%n"
      + "|           | ID       |%n"
      + "|           | (NUMBER) |%n"
      + "|-----------|----------|%n"
      + "| Index : 0 | 1        |%n"
      + "| Index : 1 | 2        |%n"
      + "| Index : 2 | 3        |%n"
      + "|-----------|----------|%n"));
    Assertions.assertThat(byteArrayOutputStream1.toString()).isEqualTo(String.format("[Column at index 1 (column name : NAME) of ACTOR table]%n"
      + "|-----------|-------------|%n"
      + "|           | NAME        |%n"
      + "|           | (TEXT)      |%n"
      + "|-----------|-------------|%n"
      + "| Index : 0 | Weaver      |%n"
      + "| Index : 1 | Phoenix     |%n"
      + "| Index : 2 | Worthington |%n"
      + "|-----------|-------------|%n"));
    Assertions.assertThat(byteArrayOutputStream2.toString()).isEqualTo(String.format("[Column at index 2 (column name : FIRSTNAME) of ACTOR table]%n"
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
   * This method tests the {@code output} output method.
   */
  @Test
  public void test_output_for_request() throws Exception {
    Request request = new Request(jdbcConnectionProvider, "select * from actor");

    ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream();
    ByteArrayOutputStream byteArrayOutputStream1 = new ByteArrayOutputStream();
    ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
    Outputs.output(request).column().toStream(byteArrayOutputStream0)
      .column(1).toStream(byteArrayOutputStream1)
      .column().toStream(byteArrayOutputStream2);
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
   * This method tests the {@code output} output method.
   */
  @Test
  @NeedReload
  public void test_output_for_change() throws Exception {
    Changes changes = new Changes(jdbcConnectionProvider).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream();
    ByteArrayOutputStream byteArrayOutputStream1 = new ByteArrayOutputStream();
    ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
    output(changes).change().column().toStream(byteArrayOutputStream0)
      .column().toStream(byteArrayOutputStream1)
      .column().toStream(byteArrayOutputStream2);
    Assertions.assertThat(byteArrayOutputStream0.toString()).isEqualTo(String.format("[Column at index 0 (column name : ID) of Change at index 0 (on table : ACTOR and with primary key : [4]) of Changes on tables of 'sa/jdbc:h2:mem:test']%n"
      + "|----------------|----------|%n"
      + "|                | ID       |%n"
      + "|                | (NUMBER) |%n"
      + "|----------------|----------|%n"
      + "| At start point | null     |%n"
      + "|----------------|----------|%n"
      + "| At end point   | 4        |%n"
      + "|----------------|----------|%n"));
    Assertions.assertThat(byteArrayOutputStream1.toString()).isEqualTo(String.format("[Column at index 1 (column name : NAME) of Change at index 0 (on table : ACTOR and with primary key : [4]) of Changes on tables of 'sa/jdbc:h2:mem:test']%n"
      + "|----------------|--------|%n"
      + "|                | NAME   |%n"
      + "|                | (TEXT) |%n"
      + "|----------------|--------|%n"
      + "| At start point | null   |%n"
      + "|----------------|--------|%n"
      + "| At end point   | Murray |%n"
      + "|----------------|--------|%n"));
    Assertions.assertThat(byteArrayOutputStream2.toString()).isEqualTo(String.format("[Column at index 2 (column name : FIRSTNAME) of Change at index 0 (on table : ACTOR and with primary key : [4]) of Changes on tables of 'sa/jdbc:h2:mem:test']%n"
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
