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
 * Copyright 2012-2016 the original author or authors.
 */
package org.assertj.db.database.sqlite;

import org.assertj.core.api.Assertions;
import org.assertj.db.common.NeedReload;
import org.assertj.db.exception.AssertJDBException;
import org.assertj.db.output.Outputs;
import org.assertj.db.type.Changes;
import org.assertj.db.type.Source;
import org.assertj.db.type.Table;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;

import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;
import static org.junit.Assert.fail;

/**
 * Test on the Sqlite database.
 *
 * @author Régis Pouiller
 */
public class SqliteDatabase_Source_NSNSNS_Test extends AbstractSqliteTest {

  private Source source;

  @Before
  public void init() {
    source = sourceNSNSNS;
  }

  @Test
  @NeedReload
  public void test_Outputs_output() {
    Table table = new Table(source, "test", null, new String[] {"var20"});
    Changes changes = new Changes(table).setStartPointNow();
    update();
    changes.setEndPointNow();

    ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream();
    ByteArrayOutputStream byteArrayOutputStream1 = new ByteArrayOutputStream();
    ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
    ByteArrayOutputStream byteArrayOutputStream3 = new ByteArrayOutputStream();
    ByteArrayOutputStream byteArrayOutputStream4 = new ByteArrayOutputStream();
    ByteArrayOutputStream byteArrayOutputStream5 = new ByteArrayOutputStream();
    ByteArrayOutputStream byteArrayOutputStream6 = new ByteArrayOutputStream();
    ByteArrayOutputStream byteArrayOutputStream7 = new ByteArrayOutputStream();
    ByteArrayOutputStream byteArrayOutputStream8 = new ByteArrayOutputStream();
    ByteArrayOutputStream byteArrayOutputStream9 = new ByteArrayOutputStream();
    ByteArrayOutputStream byteArrayOutputStream10 = new ByteArrayOutputStream();
    Outputs.output(table).toStream(byteArrayOutputStream0)
           .column().toStream(byteArrayOutputStream1)
           .value().toStream(byteArrayOutputStream2)
           .row().toStream(byteArrayOutputStream3)
           .value().toStream(byteArrayOutputStream4);
    output(changes).toStream(byteArrayOutputStream5)
                   .change().toStream(byteArrayOutputStream6)
                   .rowAtEndPoint().toStream(byteArrayOutputStream7)
                   .value().toStream(byteArrayOutputStream8)
                   .column().toStream(byteArrayOutputStream9)
                   .valueAtEndPoint().toStream(byteArrayOutputStream10);

    Assertions.assertThat(byteArrayOutputStream0.toString()).isEqualTo(String.format("[test table]%n"
                                                                                     + "|-----------|---------|-----------|-----------|-----------|-----------|-----------|------------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|-----------------------|------------|%n"
                                                                                     + "|           |         |           |           |           |           |           |            |           |           |           |           |            |            |            |            |            |            |            |                       |            |%n"
                                                                                     + "|           | PRIMARY | Var1      | vAr2      | vaR3      | var4      | var5      | var6       | var7      | var8      | var9      | var10     | var11      | var12      | var13      | var14      | var15      | var16      | var17      | var18                 | var19      |%n"
                                                                                     + "|           | KEY     | (NUMBER)  | (TEXT)    | (TEXT)    | (TEXT)    | (TEXT)    | (DATE)     | (NUMBER)  | (NUMBER)  | (NUMBER)  | (NUMBER)  | (NUMBER)   | (TEXT)     | (TEXT)     | (NUMBER)   | (NUMBER)   | (NUMBER)   | (TEXT)     | (TEXT)                | (TEXT)     |%n"
                                                                                     + "|           |         | Index : 0 | Index : 1 | Index : 2 | Index : 3 | Index : 4 | Index : 5  | Index : 6 | Index : 7 | Index : 8 | Index : 9 | Index : 10 | Index : 11 | Index : 12 | Index : 13 | Index : 14 | Index : 15 | Index : 16 | Index : 17            | Index : 18 |%n"
                                                                                     + "|-----------|---------|-----------|-----------|-----------|-----------|-----------|------------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|-----------------------|------------|%n"
                                                                                     + "| Index : 0 |         | 1         | 13        | 2         | 14        | 15        | 2007-12-23 | 3.3       | 4.4       | 5.5       | 6.6       | 20         | 8          | 16         | 9          | 10.1       | 11         | 09:01:00   | 2007-12-23 09:01:00.0 | 12         |%n"
                                                                                     + "|-----------|---------|-----------|-----------|-----------|-----------|-----------|------------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|-----------------------|------------|%n"));
    Assertions.assertThat(byteArrayOutputStream1.toString()).isEqualTo(String.format("[Column at index 0 (column name : Var1) of test table]%n"
                                                                                     + "|-----------|----------|%n"
                                                                                     + "|           | Var1     |%n"
                                                                                     + "|           | (NUMBER) |%n"
                                                                                     + "|-----------|----------|%n"
                                                                                     + "| Index : 0 | 1        |%n"
                                                                                     + "|-----------|----------|%n"));
    Assertions.assertThat(byteArrayOutputStream2.toString()).isEqualTo(String.format("[Value at index 0 of Column at index 0 (column name : Var1) of test table]%n"
                                                                                     + "|----------|%n"
                                                                                     + "| Var1     |%n"
                                                                                     + "| (NUMBER) |%n"
                                                                                     + "|----------|%n"
                                                                                     + "| 1        |%n"
                                                                                     + "|----------|%n"));
    Assertions.assertThat(byteArrayOutputStream3.toString()).isEqualTo(String.format("[Row at index 0 of test table]%n"
                                                                                     + "|---------|-----------|-----------|-----------|-----------|-----------|------------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|-----------------------|------------|%n"
                                                                                     + "|         |           |           |           |           |           |            |           |           |           |           |            |            |            |            |            |            |            |                       |            |%n"
                                                                                     + "| PRIMARY | Var1      | vAr2      | vaR3      | var4      | var5      | var6       | var7      | var8      | var9      | var10     | var11      | var12      | var13      | var14      | var15      | var16      | var17      | var18                 | var19      |%n"
                                                                                     + "| KEY     | (NUMBER)  | (TEXT)    | (TEXT)    | (TEXT)    | (TEXT)    | (DATE)     | (NUMBER)  | (NUMBER)  | (NUMBER)  | (NUMBER)  | (NUMBER)   | (TEXT)     | (TEXT)     | (NUMBER)   | (NUMBER)   | (NUMBER)   | (TEXT)     | (TEXT)                | (TEXT)     |%n"
                                                                                     + "|         | Index : 0 | Index : 1 | Index : 2 | Index : 3 | Index : 4 | Index : 5  | Index : 6 | Index : 7 | Index : 8 | Index : 9 | Index : 10 | Index : 11 | Index : 12 | Index : 13 | Index : 14 | Index : 15 | Index : 16 | Index : 17            | Index : 18 |%n"
                                                                                     + "|---------|-----------|-----------|-----------|-----------|-----------|------------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|-----------------------|------------|%n"
                                                                                     + "|         | 1         | 13        | 2         | 14        | 15        | 2007-12-23 | 3.3       | 4.4       | 5.5       | 6.6       | 20         | 8          | 16         | 9          | 10.1       | 11         | 09:01:00   | 2007-12-23 09:01:00.0 | 12         |%n"
                                                                                     + "|---------|-----------|-----------|-----------|-----------|-----------|------------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|-----------------------|------------|%n"));
    Assertions.assertThat(byteArrayOutputStream4.toString()).isEqualTo(String.format("[Value at index 0 (column name : Var1) of Row at index 0 of test table]%n"
                                                                                     + "|----------|%n"
                                                                                     + "| Var1     |%n"
                                                                                     + "| (NUMBER) |%n"
                                                                                     + "|----------|%n"
                                                                                     + "| 1        |%n"
                                                                                     + "|----------|%n"));
    Assertions.assertThat(byteArrayOutputStream5.toString()).isEqualTo(String.format("[Changes on test table of '/jdbc:sqlite:target/testDerby.db' source]%n"
                                                                                     + "|-----------|----------|-------|---------|----------------|-----------|-----------|-----------|-----------|-----------|------------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|-----------------------|------------|%n"
                                                                                     + "|           |          |       |         |                |           |           |           |           |           |            |           |           |           |           |            |            |            |            |            |            |            |                       |            |%n"
                                                                                     + "|           | TYPE     | TABLE | PRIMARY |                | Var1      | vAr2      | vaR3      | var4      | var5      | var6       | var7      | var8      | var9      | var10     | var11      | var12      | var13      | var14      | var15      | var16      | var17      | var18                 | var19      |%n"
                                                                                     + "|           |          |       | KEY     |                | (NUMBER)  | (TEXT)    | (TEXT)    | (TEXT)    | (TEXT)    | (DATE)     | (NUMBER)  | (NUMBER)  | (NUMBER)  | (NUMBER)  | (NUMBER)   | (TEXT)     | (TEXT)     | (NUMBER)   | (NUMBER)   | (NUMBER)   | (TEXT)     | (TEXT)                | (TEXT)     |%n"
                                                                                     + "|           |          |       |         |                | Index : 0 | Index : 1 | Index : 2 | Index : 3 | Index : 4 | Index : 5  | Index : 6 | Index : 7 | Index : 8 | Index : 9 | Index : 10 | Index : 11 | Index : 12 | Index : 13 | Index : 14 | Index : 15 | Index : 16 | Index : 17            | Index : 18 |%n"
                                                                                     + "|-----------|----------|-------|---------|----------------|-----------|-----------|-----------|-----------|-----------|------------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|-----------------------|------------|%n"
                                                                                     + "|           |          |       |         | At start point |           |           |           |           |           |            |           |           |           |           |            |            |            |            |            |            |            |                       |            |%n"
                                                                                     + "| Index : 0 | CREATION | test  |         |----------------|-----------|-----------|-----------|-----------|-----------|------------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|-----------------------|------------|%n"
                                                                                     + "|           |          |       |         | At end point   | 1         | 13        | 2         | 14        | 15        | 2007-12-23 | 3.3       | 4.4       | 5.5       | 6.6       | 20         | 8          | 16         | 9          | 10.1       | 11         | 09:01:00   | 2007-12-23 09:01:00.0 | 12         |%n"
                                                                                     + "|-----------|----------|-------|---------|----------------|-----------|-----------|-----------|-----------|-----------|------------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|-----------------------|------------|%n"
                                                                                     + "|-----------|----------|-------|---------|----------------|-----------|-----------|-----------|-----------|-----------|------------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|-----------------------|------------|%n"
                                                                                     + "|           |          |       |         |                |           |           |           |           |           |            |           |           |           |           |            |            |            |            |            |            |            |                       |            |%n"
                                                                                     + "|           | TYPE     | TABLE | PRIMARY |                | Var1      | vAr2      | vaR3      | var4      | var5      | var6       | var7      | var8      | var9      | var10     | var11      | var12      | var13      | var14      | var15      | var16      | var17      | var18                 | var19      |%n"
                                                                                     + "|           |          |       | KEY     |                | (NUMBER)  | (TEXT)    | (TEXT)    | (TEXT)    | (TEXT)    | (DATE)     | (NUMBER)  | (NUMBER)  | (NUMBER)  | (NUMBER)  | (NUMBER)   | (TEXT)     | (TEXT)     | (NUMBER)   | (NUMBER)   | (NUMBER)   | (TEXT)     | (TEXT)                | (TEXT)     |%n"
                                                                                     + "|           |          |       |         |                | Index : 0 | Index : 1 | Index : 2 | Index : 3 | Index : 4 | Index : 5  | Index : 6 | Index : 7 | Index : 8 | Index : 9 | Index : 10 | Index : 11 | Index : 12 | Index : 13 | Index : 14 | Index : 15 | Index : 16 | Index : 17            | Index : 18 |%n"
                                                                                     + "|-----------|----------|-------|---------|----------------|-----------|-----------|-----------|-----------|-----------|------------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|-----------------------|------------|%n"
                                                                                     + "|           |          |       |         | At start point | 1         | 13        | 2         | 14        | 15        | 2007-12-23 | 3.3       | 4.4       | 5.5       | 6.6       | 7          | 8          | 16         | 9          | 10.1       | 11         | 09:01:00   | 2007-12-23 09:01:00.0 | 12         |%n"
                                                                                     + "| Index : 1 | DELETION | test  |         |----------------|-----------|-----------|-----------|-----------|-----------|------------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|-----------------------|------------|%n"
                                                                                     + "|           |          |       |         | At end point   |           |           |           |           |           |            |           |           |           |           |            |            |            |            |            |            |            |                       |            |%n"
                                                                                     + "|-----------|----------|-------|---------|----------------|-----------|-----------|-----------|-----------|-----------|------------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|-----------------------|------------|%n"));
    Assertions.assertThat(byteArrayOutputStream6.toString()).isEqualTo(String.format("[Change at index 0 of Changes on test table of '/jdbc:sqlite:target/testDerby.db' source]%n"
                                                                                     + "|----------|-------|---------|----------------|-----------|-----------|-----------|-----------|-----------|------------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|-----------------------|------------|%n"
                                                                                     + "|          |       |         |                |           |           |           |           |           |            |           |           |           |           |            |            |            |            |            |            |            |                       |            |%n"
                                                                                     + "| TYPE     | TABLE | PRIMARY |                | Var1      | vAr2      | vaR3      | var4      | var5      | var6       | var7      | var8      | var9      | var10     | var11      | var12      | var13      | var14      | var15      | var16      | var17      | var18                 | var19      |%n"
                                                                                     + "|          |       | KEY     |                | (NUMBER)  | (TEXT)    | (TEXT)    | (TEXT)    | (TEXT)    | (DATE)     | (NUMBER)  | (NUMBER)  | (NUMBER)  | (NUMBER)  | (NUMBER)   | (TEXT)     | (TEXT)     | (NUMBER)   | (NUMBER)   | (NUMBER)   | (TEXT)     | (TEXT)                | (TEXT)     |%n"
                                                                                     + "|          |       |         |                | Index : 0 | Index : 1 | Index : 2 | Index : 3 | Index : 4 | Index : 5  | Index : 6 | Index : 7 | Index : 8 | Index : 9 | Index : 10 | Index : 11 | Index : 12 | Index : 13 | Index : 14 | Index : 15 | Index : 16 | Index : 17            | Index : 18 |%n"
                                                                                     + "|----------|-------|---------|----------------|-----------|-----------|-----------|-----------|-----------|------------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|-----------------------|------------|%n"
                                                                                     + "|          |       |         | At start point |           |           |           |           |           |            |           |           |           |           |            |            |            |            |            |            |            |                       |            |%n"
                                                                                     + "| CREATION | test  |         |----------------|-----------|-----------|-----------|-----------|-----------|------------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|-----------------------|------------|%n"
                                                                                     + "|          |       |         | At end point   | 1         | 13        | 2         | 14        | 15        | 2007-12-23 | 3.3       | 4.4       | 5.5       | 6.6       | 20         | 8          | 16         | 9          | 10.1       | 11         | 09:01:00   | 2007-12-23 09:01:00.0 | 12         |%n"
                                                                                     + "|----------|-------|---------|----------------|-----------|-----------|-----------|-----------|-----------|------------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|-----------------------|------------|%n"));
    Assertions.assertThat(byteArrayOutputStream7.toString()).isEqualTo(String.format("[Row at end point of Change at index 0 of Changes on test table of '/jdbc:sqlite:target/testDerby.db' source]%n"
                                                                                     + "|---------|-----------|-----------|-----------|-----------|-----------|------------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|-----------------------|------------|%n"
                                                                                     + "|         |           |           |           |           |           |            |           |           |           |           |            |            |            |            |            |            |            |                       |            |%n"
                                                                                     + "| PRIMARY | Var1      | vAr2      | vaR3      | var4      | var5      | var6       | var7      | var8      | var9      | var10     | var11      | var12      | var13      | var14      | var15      | var16      | var17      | var18                 | var19      |%n"
                                                                                     + "| KEY     | (NUMBER)  | (TEXT)    | (TEXT)    | (TEXT)    | (TEXT)    | (DATE)     | (NUMBER)  | (NUMBER)  | (NUMBER)  | (NUMBER)  | (NUMBER)   | (TEXT)     | (TEXT)     | (NUMBER)   | (NUMBER)   | (NUMBER)   | (TEXT)     | (TEXT)                | (TEXT)     |%n"
                                                                                     + "|         | Index : 0 | Index : 1 | Index : 2 | Index : 3 | Index : 4 | Index : 5  | Index : 6 | Index : 7 | Index : 8 | Index : 9 | Index : 10 | Index : 11 | Index : 12 | Index : 13 | Index : 14 | Index : 15 | Index : 16 | Index : 17            | Index : 18 |%n"
                                                                                     + "|---------|-----------|-----------|-----------|-----------|-----------|------------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|-----------------------|------------|%n"
                                                                                     + "|         | 1         | 13        | 2         | 14        | 15        | 2007-12-23 | 3.3       | 4.4       | 5.5       | 6.6       | 20         | 8          | 16         | 9          | 10.1       | 11         | 09:01:00   | 2007-12-23 09:01:00.0 | 12         |%n"
                                                                                     + "|---------|-----------|-----------|-----------|-----------|-----------|------------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|-----------------------|------------|%n"));
    Assertions.assertThat(byteArrayOutputStream8.toString()).isEqualTo(String.format("[Value at index 0 (column name : Var1) of Row at end point of Change at index 0 of Changes on test table of '/jdbc:sqlite:target/testDerby.db' source]%n"
                                                                                     + "|----------|%n"
                                                                                     + "| Var1     |%n"
                                                                                     + "| (NUMBER) |%n"
                                                                                     + "|----------|%n"
                                                                                     + "| 1        |%n"
                                                                                     + "|----------|%n"));
    Assertions.assertThat(byteArrayOutputStream9.toString()).isEqualTo(String.format("[Column at index 0 (column name : Var1) of Change at index 0 of Changes on test table of '/jdbc:sqlite:target/testDerby.db' source]%n"
                                                                                     + "|----------------|----------|%n"
                                                                                     + "|                | Var1     |%n"
                                                                                     + "|                | (NUMBER) |%n"
                                                                                     + "|----------------|----------|%n"
                                                                                     + "| At start point | null     |%n"
                                                                                     + "|----------------|----------|%n"
                                                                                     + "| At end point   | 1        |%n"
                                                                                     + "|----------------|----------|%n"));
    Assertions.assertThat(byteArrayOutputStream10.toString()).isEqualTo(String.format("[Value at end point of Column at index 0 (column name : Var1) of Change at index 0 of Changes on test table of '/jdbc:sqlite:target/testDerby.db' source]%n"
                                                                                      + "|----------|%n"
                                                                                      + "| Var1     |%n"
                                                                                      + "| (NUMBER) |%n"
                                                                                      + "|----------|%n"
                                                                                      + "| 1        |%n"
                                                                                      + "|----------|%n"));
  }

  @Test
  @NeedReload
  public void should_fail_because_primary_key_is_different() {
    Table table = new Table(source, "test");
    Changes changes = new Changes(table).setStartPointNow();
    Changes changes2 = new Changes(source).setStartPointNow();
    update();
    changes.setEndPointNow();
    changes2.setEndPointNow();

    try {
      assertThat(changes).change().hasPksNames("var1");
      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[Change at index 0 of Changes on test table of '/jdbc:sqlite:target/testDerby.db' source] %n"
                                                                    + "Expecting :%n"
                                                                    + "  [\"var1\"]%n"
                                                                    + "to be the name of the columns of the primary keys but was:%n"
                                                                    + "  []"));
    }

    try {
      assertThat(changes2).change().hasPksNames("var1");
      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[Change at index 0 of Changes on teSt table of '/jdbc:sqlite:target/testDerby.db' source] %n"
                                                                    + "Expecting :%n"
                                                                    + "  [\"var1\"]%n"
                                                                    + "to be the name of the columns of the primary keys but was:%n"
                                                                    + "  []"));
    }
  }

  @Test
  @NeedReload
  public void should_fail_because_column_name_is_different() {
    Table table = new Table(source, "test");
    Changes changes = new Changes(table).setStartPointNow();
    Changes changes2 = new Changes(source).setStartPointNow();
    update();
    changes.setEndPointNow();
    changes2.setEndPointNow();

    try {
      assertThat(table).column().hasColumnName("var1");
      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[Column at index 0 (column name : Var1) of test table] %n"
                                                                    + "Expecting :%n"
                                                                    + "  \"var1\"%n"
                                                                    + "to be the name of the column but was:%n"
                                                                    + "  \"Var1\""));
    }
    try {
      assertThat(table).row().value().hasColumnName("var1");
      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[Value at index 0 (column name : Var1) of Row at index 0 of test table] %n"
                                                                    + "Expecting :%n"
                                                                    + "  \"var1\"%n"
                                                                    + "to be the name of the column but was:%n"
                                                                    + "  \"Var1\""));
    }

    try {
      assertThat(changes).change().column().hasColumnName("var1");
      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[Column at index 0 (column name : Var1) of Change at index 0 of Changes on test table of '/jdbc:sqlite:target/testDerby.db' source] %n"
                                                                    + "Expecting :%n"
                                                                    + "  \"var1\"%n"
                                                                    + "to be the name of the column but was:%n"
                                                                    + "  \"Var1\""));
    }
    try {
      assertThat(changes).change().rowAtEndPoint().value().hasColumnName("var1");
      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[Value at index 0 (column name : Var1) of Row at end point of Change at index 0 of Changes on test table of '/jdbc:sqlite:target/testDerby.db' source] %n"
                                                                    + "Expecting :%n"
                                                                    + "  \"var1\"%n"
                                                                    + "to be the name of the column but was:%n"
                                                                    + "  \"Var1\""));
    }

    try {
      assertThat(changes2).change().column().hasColumnName("var1");
      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[Column at index 0 (column name : Var1) of Change at index 0 of Changes on teSt table of '/jdbc:sqlite:target/testDerby.db' source] %n"
                                                                    + "Expecting :%n"
                                                                    + "  \"var1\"%n"
                                                                    + "to be the name of the column but was:%n"
                                                                    + "  \"Var1\""));
    }
    try {
      assertThat(changes2).change().rowAtEndPoint().value().hasColumnName("var1");
      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[Value at index 0 (column name : Var1) of Row at end point of Change at index 0 of Changes on teSt table of '/jdbc:sqlite:target/testDerby.db' source] %n"
                                                                    + "Expecting :%n"
                                                                    + "  \"var1\"%n"
                                                                    + "to be the name of the column but was:%n"
                                                                    + "  \"Var1\""));
    }
  }

  @Test
  @NeedReload
  public void should_fail_because_column_name_is_wrong_to_navigate() {
    Table table = new Table(source, "test");
    Changes changes = new Changes(table).setStartPointNow();
    Changes changes2 = new Changes(source).setStartPointNow();
    update();
    changes.setEndPointNow();
    changes2.setEndPointNow();

    try {
      assertThat(table).column("var1");
      fail("An exception must be raised");
    }
    catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("Column <var1> does not exist%n"
                                                                    + "in <[Var1, vAr2, vaR3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17, var18, var19, var20]>%n"
                                                                    + "with comparison STRICT - Strictly compare the case"));
    }
    try {
      assertThat(table).row().value("var1");
      fail("An exception must be raised");
    }
    catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("Column <var1> does not exist%n"
                                                                    + "in <[Var1, vAr2, vaR3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17, var18, var19, var20]>%n"
                                                                    + "with comparison STRICT - Strictly compare the case"));
    }

    try {
      assertThat(changes).change().column("var1");
      fail("An exception must be raised");
    }
    catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("Column <var1> does not exist%n"
                                                                    + "in <[Var1, vAr2, vaR3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17, var18, var19, var20]>%n"
                                                                    + "with comparison STRICT - Strictly compare the case"));
    }
    try {
      assertThat(changes).change().rowAtEndPoint().value("var1");
      fail("An exception must be raised");
    }
    catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("Column <var1> does not exist%n"
                                                                    + "in <[Var1, vAr2, vaR3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17, var18, var19, var20]>%n"
                                                                    + "with comparison STRICT - Strictly compare the case"));
    }

    try {
      assertThat(changes2).change().column("var1");
      fail("An exception must be raised");
    }
    catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("Column <var1> does not exist%n"
                                                                    + "in <[Var1, vAr2, vaR3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17, var18, var19, var20]>%n"
                                                                    + "with comparison STRICT - Strictly compare the case"));
    }
    try {
      assertThat(changes2).change().rowAtEndPoint().value("var1");
      fail("An exception must be raised");
    }
    catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("Column <var1> does not exist%n"
                                                                    + "in <[Var1, vAr2, vaR3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17, var18, var19, var20]>%n"
                                                                    + "with comparison STRICT - Strictly compare the case"));
    }
  }

  @Test
  @NeedReload
  public void should_fail_because_table_name_is_different() {
    Table table = new Table(source, "test");
    Changes changes = new Changes(table).setStartPointNow();
    Changes changes2 = new Changes(source).setStartPointNow();
    update();
    changes.setEndPointNow();
    changes2.setEndPointNow();

    try {
      assertThat(changes).change().isOnTable("teSt");
      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[Change at index 0 of Changes on test table of '/jdbc:sqlite:target/testDerby.db' source] %n"
                                                                    + "Expecting to be on the table:%n"
                                                                    + "  <\"teSt\">%n"
                                                                    + "but was on the table:%n"
                                                                    + "  <\"test\">"));
    }
    try {
      assertThat(changes2).change().isOnTable("test");
      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[Change at index 0 of Changes on teSt table of '/jdbc:sqlite:target/testDerby.db' source] %n"
                                                                    + "Expecting to be on the table:%n"
                                                                    + "  <\"test\">%n"
                                                                    + "but was on the table:%n"
                                                                    + "  <\"teSt\">"));
    }
  }

  @Test
  @NeedReload
  public void should_fail_because_table_name_is_wrong_to_navigate() {
    Table table = new Table(source, "test");
    Changes changes = new Changes(table).setStartPointNow();
    Changes changes2 = new Changes(source).setStartPointNow();
    update();
    changes.setEndPointNow();
    changes2.setEndPointNow();

    try {
      assertThat(changes).changeOnTable("teSt");
      fail("An exception must be raised");
    }
    catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("Index 0 out of the limits [0, 0["));
    }
    try {
      assertThat(changes).changeOnTable("teSt", 0);
      fail("An exception must be raised");
    }
    catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("Index 0 out of the limits [0, 0["));
    }
    try {
      assertThat(changes).changeOnTableWithPks("teSt", 1);
      fail("An exception must be raised");
    }
    catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("No change found for table teSt and primary keys [1]"));
    }

    try {
      assertThat(changes2).changeOnTable("test");
      fail("An exception must be raised");
    }
    catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("Index 0 out of the limits [0, 0["));
    }
    try {
      assertThat(changes2).changeOnTable("test", 0);
      fail("An exception must be raised");
    }
    catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("Index 0 out of the limits [0, 0["));
    }
    try {
      assertThat(changes2).changeOnTableWithPks("test", 1);
      fail("An exception must be raised");
    }
    catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("No change found for table test and primary keys [1]"));
    }
  }

  @Test
  @NeedReload
  public void test_ColumnName_hasColumnName() {
    Table table = new Table(source, "test");
    Changes changes = new Changes(table).setStartPointNow();
    Changes changes2 = new Changes(source).setStartPointNow();
    update();
    changes.setEndPointNow();
    changes2.setEndPointNow();

    assertThat(table).column().hasColumnName("Var1");

    assertThat(changes).change().column().hasColumnName("Var1");
    assertThat(changes2).change().column().hasColumnName("Var1");
  }

  @Test
  @NeedReload
  public void test_ColumnClass_isOfClass() {
    Table table = new Table(source, "test");
    Changes changes = new Changes(table).setStartPointNow();
    Changes changes2 = new Changes(source).setStartPointNow();
    update();
    changes.setEndPointNow();
    changes2.setEndPointNow();

    assertThat(table).column("Var1").isOfClass(Integer.class, false);

    assertThat(changes).change().column("Var1").isOfClass(Integer.class, true);
    assertThat(changes2).change().column("Var1").isOfClass(Integer.class, true);
  }
}
