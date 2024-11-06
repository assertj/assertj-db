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
package org.assertj.db.database.sqlite;

import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;

import java.io.ByteArrayOutputStream;
import java.sql.Date;

import org.assertj.core.api.Assertions;
import org.assertj.db.common.NeedReload;
import org.assertj.db.output.Outputs;
import org.assertj.db.type.Changes;
import org.assertj.db.type.DateValue;
import org.assertj.db.type.Source;
import org.assertj.db.type.Table;
import org.assertj.db.type.ValueType;
import org.junit.Before;
import org.junit.Test;

/**
 * Test on the Sqlite database.
 *
 * @author Régis Pouiller
 */
public class SqliteDatabase_Source_UIUIUI_Test extends AbstractSqliteTest {

  private Source source;

  @Before
  public void init() {
    source = sourceUIUIUI;
  }

  @Test
  @NeedReload
  public void test_Outputs_output() {
    Table table = new Table(source, "test", null, new String[]{"var20"});
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
      .value().toStream(byteArrayOutputStream4)
    ;
    output(changes).toStream(byteArrayOutputStream5)
      .change().toStream(byteArrayOutputStream6)
      .rowAtEndPoint().toStream(byteArrayOutputStream7)
      .value().toStream(byteArrayOutputStream8)
      .column().toStream(byteArrayOutputStream9)
      .valueAtEndPoint().toStream(byteArrayOutputStream10);

    Assertions.assertThat(byteArrayOutputStream0.toString()).isEqualTo(String.format("[TEST table]%n"
      + "|-----------|---------|-----------|-----------|-----------|-----------|-----------|------------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|-----------------------|------------|%n"
      + "|           |         | *         |           |           |           |           |            |           |           |           |           |            |            |            |            |            |            |            |                       |            |%n"
      + "|           | PRIMARY | VAR1      | VAR2      | VAR3      | VAR4      | VAR5      | VAR6       | VAR7      | VAR8      | VAR9      | VAR10     | VAR11      | VAR12      | VAR13      | VAR14      | VAR15      | VAR16      | VAR17      | VAR18                 | VAR19      |%n"
      + "|           | KEY     | (NUMBER)  | (TEXT)    | (TEXT)    | (TEXT)    | (TEXT)    | (DATE)     | (NUMBER)  | (NUMBER)  | (NUMBER)  | (NUMBER)  | (NUMBER)   | (TEXT)     | (TEXT)     | (NUMBER)   | (NUMBER)   | (NUMBER)   | (TEXT)     | (TEXT)                | (TEXT)     |%n"
      + "|           |         | Index : 0 | Index : 1 | Index : 2 | Index : 3 | Index : 4 | Index : 5  | Index : 6 | Index : 7 | Index : 8 | Index : 9 | Index : 10 | Index : 11 | Index : 12 | Index : 13 | Index : 14 | Index : 15 | Index : 16 | Index : 17            | Index : 18 |%n"
      + "|-----------|---------|-----------|-----------|-----------|-----------|-----------|------------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|-----------------------|------------|%n"
      + "| Index : 0 | 1       | 1         | 13        | 2         | 14        | 15        | 2007-12-23 | 3.3       | 4.4       | 5.5       | 6.6       | 20         | 8          | 16         | 9          | 10.1       | 11         | 09:01:00   | 2007-12-23 09:01:00.0 | 12         |%n"
      + "|-----------|---------|-----------|-----------|-----------|-----------|-----------|------------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|-----------------------|------------|%n"));
    Assertions.assertThat(byteArrayOutputStream1.toString()).isEqualTo(String.format("[Column at index 0 (column name : VAR1) of TEST table]%n"
      + "|-----------|----------|%n"
      + "|           | VAR1     |%n"
      + "|           | (NUMBER) |%n"
      + "|-----------|----------|%n"
      + "| Index : 0 | 1        |%n"
      + "|-----------|----------|%n"));
    Assertions.assertThat(byteArrayOutputStream2.toString()).isEqualTo(String.format("[Value at index 0 of Column at index 0 (column name : VAR1) of TEST table]%n"
      + "|----------|%n"
      + "| VAR1     |%n"
      + "| (NUMBER) |%n"
      + "|----------|%n"
      + "| 1        |%n"
      + "|----------|%n"));
    Assertions.assertThat(byteArrayOutputStream3.toString()).isEqualTo(String.format("[Row at index 0 of TEST table]%n"
      + "|---------|-----------|-----------|-----------|-----------|-----------|------------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|-----------------------|------------|%n"
      + "|         | *         |           |           |           |           |            |           |           |           |           |            |            |            |            |            |            |            |                       |            |%n"
      + "| PRIMARY | VAR1      | VAR2      | VAR3      | VAR4      | VAR5      | VAR6       | VAR7      | VAR8      | VAR9      | VAR10     | VAR11      | VAR12      | VAR13      | VAR14      | VAR15      | VAR16      | VAR17      | VAR18                 | VAR19      |%n"
      + "| KEY     | (NUMBER)  | (TEXT)    | (TEXT)    | (TEXT)    | (TEXT)    | (DATE)     | (NUMBER)  | (NUMBER)  | (NUMBER)  | (NUMBER)  | (NUMBER)   | (TEXT)     | (TEXT)     | (NUMBER)   | (NUMBER)   | (NUMBER)   | (TEXT)     | (TEXT)                | (TEXT)     |%n"
      + "|         | Index : 0 | Index : 1 | Index : 2 | Index : 3 | Index : 4 | Index : 5  | Index : 6 | Index : 7 | Index : 8 | Index : 9 | Index : 10 | Index : 11 | Index : 12 | Index : 13 | Index : 14 | Index : 15 | Index : 16 | Index : 17            | Index : 18 |%n"
      + "|---------|-----------|-----------|-----------|-----------|-----------|------------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|-----------------------|------------|%n"
      + "| 1       | 1         | 13        | 2         | 14        | 15        | 2007-12-23 | 3.3       | 4.4       | 5.5       | 6.6       | 20         | 8          | 16         | 9          | 10.1       | 11         | 09:01:00   | 2007-12-23 09:01:00.0 | 12         |%n"
      + "|---------|-----------|-----------|-----------|-----------|-----------|------------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|-----------------------|------------|%n"));
    Assertions.assertThat(byteArrayOutputStream4.toString()).isEqualTo(String.format("[Value at index 0 (column name : VAR1) of Row at index 0 of TEST table]%n"
      + "|----------|%n"
      + "| VAR1     |%n"
      + "| (NUMBER) |%n"
      + "|----------|%n"
      + "| 1        |%n"
      + "|----------|%n"));
    Assertions.assertThat(byteArrayOutputStream5.toString()).isEqualTo(String.format("[Changes on TEST table of '/jdbc:sqlite:target/testDerby.db' source]%n"
      + "|-----------|--------------|-------|---------|----------------|-----------|-----------|-----------|-----------|-----------|------------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|-----------------------|------------|%n"
      + "|           |              |       |         |                | *         |           |           |           |           |            |           |           |           |           |            |            |            |            |            |            |            |                       |            |%n"
      + "|           | TYPE         | TABLE | PRIMARY |                | VAR1      | VAR2      | VAR3      | VAR4      | VAR5      | VAR6       | VAR7      | VAR8      | VAR9      | VAR10     | VAR11      | VAR12      | VAR13      | VAR14      | VAR15      | VAR16      | VAR17      | VAR18                 | VAR19      |%n"
      + "|           |              |       | KEY     |                | (NUMBER)  | (TEXT)    | (TEXT)    | (TEXT)    | (TEXT)    | (DATE)     | (NUMBER)  | (NUMBER)  | (NUMBER)  | (NUMBER)  | (NUMBER)   | (TEXT)     | (TEXT)     | (NUMBER)   | (NUMBER)   | (NUMBER)   | (TEXT)     | (TEXT)                | (TEXT)     |%n"
      + "|           |              |       |         |                | Index : 0 | Index : 1 | Index : 2 | Index : 3 | Index : 4 | Index : 5  | Index : 6 | Index : 7 | Index : 8 | Index : 9 | Index : 10 | Index : 11 | Index : 12 | Index : 13 | Index : 14 | Index : 15 | Index : 16 | Index : 17            | Index : 18 |%n"
      + "|-----------|--------------|-------|---------|----------------|-----------|-----------|-----------|-----------|-----------|------------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|-----------------------|------------|%n"
      + "|           |              |       |         | At start point | 1         | 13        | 2         | 14        | 15        | 2007-12-23 | 3.3       | 4.4       | 5.5       | 6.6       | 7          | 8          | 16         | 9          | 10.1       | 11         | 09:01:00   | 2007-12-23 09:01:00.0 | 12         |%n"
      + "| Index : 0 | MODIFICATION | TEST  | 1       |----------------|-----------|-----------|-----------|-----------|-----------|------------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|-----------------------|------------|%n"
      + "|           |              |       |         | At end point   | 1         | 13        | 2         | 14        | 15        | 2007-12-23 | 3.3       | 4.4       | 5.5       | 6.6       | 20         | 8          | 16         | 9          | 10.1       | 11         | 09:01:00   | 2007-12-23 09:01:00.0 | 12         |%n"
      + "|-----------|--------------|-------|---------|----------------|-----------|-----------|-----------|-----------|-----------|------------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|-----------------------|------------|%n"));
    Assertions.assertThat(byteArrayOutputStream6.toString()).isEqualTo(String.format("[Change at index 0 (with primary key : [1]) of Changes on TEST table of '/jdbc:sqlite:target/testDerby.db' source]%n"
      + "|--------------|-------|---------|----------------|-----------|-----------|-----------|-----------|-----------|------------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|-----------------------|------------|%n"
      + "|              |       |         |                | *         |           |           |           |           |            |           |           |           |           |            |            |            |            |            |            |            |                       |            |%n"
      + "| TYPE         | TABLE | PRIMARY |                | VAR1      | VAR2      | VAR3      | VAR4      | VAR5      | VAR6       | VAR7      | VAR8      | VAR9      | VAR10     | VAR11      | VAR12      | VAR13      | VAR14      | VAR15      | VAR16      | VAR17      | VAR18                 | VAR19      |%n"
      + "|              |       | KEY     |                | (NUMBER)  | (TEXT)    | (TEXT)    | (TEXT)    | (TEXT)    | (DATE)     | (NUMBER)  | (NUMBER)  | (NUMBER)  | (NUMBER)  | (NUMBER)   | (TEXT)     | (TEXT)     | (NUMBER)   | (NUMBER)   | (NUMBER)   | (TEXT)     | (TEXT)                | (TEXT)     |%n"
      + "|              |       |         |                | Index : 0 | Index : 1 | Index : 2 | Index : 3 | Index : 4 | Index : 5  | Index : 6 | Index : 7 | Index : 8 | Index : 9 | Index : 10 | Index : 11 | Index : 12 | Index : 13 | Index : 14 | Index : 15 | Index : 16 | Index : 17            | Index : 18 |%n"
      + "|--------------|-------|---------|----------------|-----------|-----------|-----------|-----------|-----------|------------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|-----------------------|------------|%n"
      + "|              |       |         | At start point | 1         | 13        | 2         | 14        | 15        | 2007-12-23 | 3.3       | 4.4       | 5.5       | 6.6       | 7          | 8          | 16         | 9          | 10.1       | 11         | 09:01:00   | 2007-12-23 09:01:00.0 | 12         |%n"
      + "| MODIFICATION | TEST  | 1       |----------------|-----------|-----------|-----------|-----------|-----------|------------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|-----------------------|------------|%n"
      + "|              |       |         | At end point   | 1         | 13        | 2         | 14        | 15        | 2007-12-23 | 3.3       | 4.4       | 5.5       | 6.6       | 20         | 8          | 16         | 9          | 10.1       | 11         | 09:01:00   | 2007-12-23 09:01:00.0 | 12         |%n"
      + "|--------------|-------|---------|----------------|-----------|-----------|-----------|-----------|-----------|------------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|-----------------------|------------|%n"));
    Assertions.assertThat(byteArrayOutputStream7.toString()).isEqualTo(String.format("[Row at end point of Change at index 0 (with primary key : [1]) of Changes on TEST table of '/jdbc:sqlite:target/testDerby.db' source]%n"
      + "|---------|-----------|-----------|-----------|-----------|-----------|------------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|-----------------------|------------|%n"
      + "|         | *         |           |           |           |           |            |           |           |           |           |            |            |            |            |            |            |            |                       |            |%n"
      + "| PRIMARY | VAR1      | VAR2      | VAR3      | VAR4      | VAR5      | VAR6       | VAR7      | VAR8      | VAR9      | VAR10     | VAR11      | VAR12      | VAR13      | VAR14      | VAR15      | VAR16      | VAR17      | VAR18                 | VAR19      |%n"
      + "| KEY     | (NUMBER)  | (TEXT)    | (TEXT)    | (TEXT)    | (TEXT)    | (DATE)     | (NUMBER)  | (NUMBER)  | (NUMBER)  | (NUMBER)  | (NUMBER)   | (TEXT)     | (TEXT)     | (NUMBER)   | (NUMBER)   | (NUMBER)   | (TEXT)     | (TEXT)                | (TEXT)     |%n"
      + "|         | Index : 0 | Index : 1 | Index : 2 | Index : 3 | Index : 4 | Index : 5  | Index : 6 | Index : 7 | Index : 8 | Index : 9 | Index : 10 | Index : 11 | Index : 12 | Index : 13 | Index : 14 | Index : 15 | Index : 16 | Index : 17            | Index : 18 |%n"
      + "|---------|-----------|-----------|-----------|-----------|-----------|------------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|-----------------------|------------|%n"
      + "| 1       | 1         | 13        | 2         | 14        | 15        | 2007-12-23 | 3.3       | 4.4       | 5.5       | 6.6       | 20         | 8          | 16         | 9          | 10.1       | 11         | 09:01:00   | 2007-12-23 09:01:00.0 | 12         |%n"
      + "|---------|-----------|-----------|-----------|-----------|-----------|------------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|-----------------------|------------|%n"));
    Assertions.assertThat(byteArrayOutputStream8.toString()).isEqualTo(String.format("[Value at index 0 (column name : VAR1) of Row at end point of Change at index 0 (with primary key : [1]) of Changes on TEST table of '/jdbc:sqlite:target/testDerby.db' source]%n"
      + "|----------|%n"
      + "| VAR1     |%n"
      + "| (NUMBER) |%n"
      + "|----------|%n"
      + "| 1        |%n"
      + "|----------|%n"));
    Assertions.assertThat(byteArrayOutputStream9.toString()).isEqualTo(String.format("[Column at index 0 (column name : VAR1) of Change at index 0 (with primary key : [1]) of Changes on TEST table of '/jdbc:sqlite:target/testDerby.db' source]%n"
      + "|----------------|----------|%n"
      + "|                | VAR1     |%n"
      + "|                | (NUMBER) |%n"
      + "|----------------|----------|%n"
      + "| At start point | 1        |%n"
      + "|----------------|----------|%n"
      + "| At end point   | 1        |%n"
      + "|----------------|----------|%n"));
    Assertions.assertThat(byteArrayOutputStream10.toString()).isEqualTo(String.format("[Value at end point of Column at index 0 (column name : VAR1) of Change at index 0 (with primary key : [1]) of Changes on TEST table of '/jdbc:sqlite:target/testDerby.db' source]%n"
      + "|----------|%n"
      + "| VAR1     |%n"
      + "| (NUMBER) |%n"
      + "|----------|%n"
      + "| 1        |%n"
      + "|----------|%n"));
  }

  @Test
  @NeedReload
  public void test_PrimaryKey_hasPksNames() {
    Table table = new Table(source, "test");
    Changes changes = new Changes(table).setStartPointNow();
    update();
    changes.setEndPointNow();

    assertThat(changes).change().hasPksNames("var1")
    ;
  }

  @Test
  @NeedReload
  public void test_ColumnName_hasColumnName() {
    Table table = new Table(source, "test");
    Changes changes = new Changes(table).setStartPointNow();
    update();
    changes.setEndPointNow();

    assertThat(table)
      .column().hasColumnName("var1")
      .column().hasColumnName("var2")
      .column().hasColumnName("var3")
      .column().hasColumnName("var4")
      .column().hasColumnName("var5")
      .column().hasColumnName("var6")
      .column().hasColumnName("var7")
      .column().hasColumnName("var8")
      .column().hasColumnName("var9")
      .column().hasColumnName("var10")
      .column().hasColumnName("var11")
      .column().hasColumnName("var12")
      .column().hasColumnName("var13")
      .column().hasColumnName("var14")
      .column().hasColumnName("var15")
      .column().hasColumnName("var16")
      .column().hasColumnName("var17")
      .column().hasColumnName("var18")
      .column().hasColumnName("var19")
      .column().hasColumnName("var20")
    ;

    assertThat(changes).change()
      .column().hasColumnName("var1")
      .column().hasColumnName("var2")
      .column().hasColumnName("var3")
      .column().hasColumnName("var4")
      .column().hasColumnName("var5")
      .column().hasColumnName("var6")
      .column().hasColumnName("var7")
      .column().hasColumnName("var8")
      .column().hasColumnName("var9")
      .column().hasColumnName("var10")
      .column().hasColumnName("var11")
      .column().hasColumnName("var12")
      .column().hasColumnName("var13")
      .column().hasColumnName("var14")
      .column().hasColumnName("var15")
      .column().hasColumnName("var16")
      .column().hasColumnName("var17")
      .column().hasColumnName("var18")
      .column().hasColumnName("var19")
      .column().hasColumnName("var20")
    ;
  }

  @Test
  @NeedReload
  public void test_ColumnClass_isOfClass() {
    Table table = new Table(source, "test");
    Changes changes = new Changes(table).setStartPointNow();
    update();
    changes.setEndPointNow();

    assertThat(table)
      .column("var1").isOfClass(Integer.class, false)
      .column("var2").isOfClass(String.class, false)
      .column("var3").isOfClass(String.class, false)
      .column("var4").isOfClass(String.class, false)
      .column("var5").isOfClass(String.class, false)
      .column("var6").isOfClass(Date.class, false)
      .column("var7").isOfClass(Double.class, false)
      .column("var8").isOfClass(Double.class, false)
      .column("var9").isOfClass(Double.class, false)
      .column("var10").isOfClass(Double.class, false)
      .column("var11").isOfClass(Integer.class, false)
      .column("var12").isOfClass(String.class, false)
      .column("var13").isOfClass(String.class, false)
      .column("var14").isOfClass(Integer.class, false)
      .column("var15").isOfClass(Double.class, false)
      .column("var16").isOfClass(Integer.class, false)
      .column("var17").isOfClass(String.class, false)
      .column("var18").isOfClass(String.class, false)
      .column("var19").isOfClass(String.class, false)
      .column("var20").isOfClass(String.class, false)
    ;

    assertThat(changes).change()
      .column("var1").isOfClass(Integer.class, false)
      .column("var2").isOfClass(String.class, false)
      .column("var3").isOfClass(String.class, false)
      .column("var4").isOfClass(String.class, false)
      .column("var5").isOfClass(String.class, false)
      .column("var6").isOfClass(Date.class, false)
      .column("var7").isOfClass(Double.class, false)
      .column("var8").isOfClass(Double.class, false)
      .column("var9").isOfClass(Double.class, false)
      .column("var10").isOfClass(Double.class, false)
      .column("var11").isOfClass(Integer.class, false)
      .column("var12").isOfClass(String.class, false)
      .column("var13").isOfClass(String.class, false)
      .column("var14").isOfClass(Integer.class, false)
      .column("var15").isOfClass(Double.class, false)
      .column("var16").isOfClass(Integer.class, false)
      .column("var17").isOfClass(String.class, false)
      .column("var18").isOfClass(String.class, false)
      .column("var19").isOfClass(String.class, false)
      .column("var20").isOfClass(String.class, false)
    ;
  }

  @Test
  @NeedReload
  public void test_ColumnEquality_hasValues() {
    Table table = new Table(source, "test", null, new String[]{"var20"});
    Changes changes = new Changes(table).setStartPointNow();
    update();
    changes.setEndPointNow();

    assertThat(table).column("var1").hasValues(1)
      .column("var2").hasValues("13")
      .column("var3").hasValues("2")
      .column("var4").hasValues("14")
      .column("var5").hasValues("15")
      .column("var6").hasValues(Date.valueOf("2007-12-23"))
      .column("var7").hasValues(3.3)
      .column("var8").hasValues(4.4)
      .column("var9").hasValues(5.5)
      .column("var10").hasValues(6.6)
      .column("var11").hasValues(20)
      .column("var12").hasValues("8")
      .column("var13").hasValues("16")
      .column("var14").hasValues(9)
      .column("var15").hasValues(10.1)
      .column("var16").hasValues(11)
      .column("var17").hasValues("09:01:00")
      .column("var18").hasValues("2007-12-23 09:01:00.0")
      .column("var19").hasValues("12")
    ;

    assertThat(changes).change()
      .column("var1").hasValues(1)
      .column("var2").hasValues("13")
      .column("var3").hasValues("2")
      .column("var4").hasValues("14")
      .column("var5").hasValues("15")
      .column("var6").hasValues(Date.valueOf("2007-12-23"))
      .column("var7").hasValues(3.3)
      .column("var8").hasValues(4.4)
      .column("var9").hasValues(5.5)
      .column("var10").hasValues(6.6)
      .column("var11").hasValues(7, 20)
      .column("var12").hasValues("8")
      .column("var13").hasValues("16")
      .column("var14").hasValues(9)
      .column("var15").hasValues(10.1)
      .column("var16").hasValues(11)
      .column("var17").hasValues("09:01:00")
      .column("var18").hasValues("2007-12-23 09:01:00.0")
      .column("var19").hasValues("12")
    ;
  }

  @Test
  @NeedReload
  public void test_ColumnEquality_containsValues() {
    Table table = new Table(source, "test", null, new String[]{"var20"});
    Changes changes = new Changes(table).setStartPointNow();
    update();
    changes.setEndPointNow();

    assertThat(table).column("var1").containsValues(1)
      .column("var2").containsValues("13")
      .column("var3").containsValues("2")
      .column("var4").containsValues("14")
      .column("var5").containsValues("15")
      .column("var6").containsValues(Date.valueOf("2007-12-23"))
      .column("var7").containsValues(3.3)
      .column("var8").containsValues(4.4)
      .column("var9").containsValues(5.5)
      .column("var10").containsValues(6.6)
      .column("var11").containsValues(20)
      .column("var12").containsValues("8")
      .column("var13").containsValues("16")
      .column("var14").containsValues(9)
      .column("var15").containsValues(10.1)
      .column("var16").containsValues(11)
      .column("var17").containsValues("09:01:00")
      .column("var18").containsValues("2007-12-23 09:01:00.0")
      .column("var19").containsValues("12")
    ;
  }

  @Test
  @NeedReload
  public void test_ColumnType_isOfType() {
    Table table = new Table(source, "test");
    Changes changes = new Changes(table).setStartPointNow();
    update();
    changes.setEndPointNow();

    assertThat(table).column("var1").isNumber(false).isOfType(ValueType.NUMBER, false)
      .column("var2").isText(false).isOfType(ValueType.TEXT, false)
      .column("var3").isText(false).isOfType(ValueType.TEXT, false)
      .column("var4").isText(false).isOfType(ValueType.TEXT, false)
      .column("var5").isText(false).isOfType(ValueType.TEXT, false)
      .column("var6").isDate(false).isOfType(ValueType.DATE, false)
      .column("var7").isNumber(false).isOfType(ValueType.NUMBER, false)
      .column("var8").isNumber(false).isOfType(ValueType.NUMBER, false)
      .column("var9").isNumber(false).isOfType(ValueType.NUMBER, false)
      .column("var10").isNumber(false).isOfType(ValueType.NUMBER, false)
      .column("var11").isNumber(false).isOfType(ValueType.NUMBER, false)
      .column("var12").isText(false).isOfType(ValueType.TEXT, false)
      .column("var13").isText(false).isOfType(ValueType.TEXT, false)
      .column("var14").isNumber(false).isOfType(ValueType.NUMBER, false)
      .column("var15").isNumber(false).isOfType(ValueType.NUMBER, false)
      .column("var16").isNumber(false).isOfType(ValueType.NUMBER, false)
      .column("var17").isText(false).isOfType(ValueType.TEXT, false)
      .column("var18").isText(false).isOfType(ValueType.TEXT, false)
      .column("var19").isText(false).isOfType(ValueType.TEXT, false)
      .column("var20").isText(false).isOfType(ValueType.TEXT, false)
    ;

    assertThat(changes).change()
      .column("var1").isNumber(false).isOfType(ValueType.NUMBER, false)
      .column("var2").isText(false).isOfType(ValueType.TEXT, false)
      .column("var3").isText(false).isOfType(ValueType.TEXT, false)
      .column("var4").isText(false).isOfType(ValueType.TEXT, false)
      .column("var5").isText(false).isOfType(ValueType.TEXT, false)
      .column("var6").isDate(false).isOfType(ValueType.DATE, false)
      .column("var7").isNumber(false).isOfType(ValueType.NUMBER, false)
      .column("var8").isNumber(false).isOfType(ValueType.NUMBER, false)
      .column("var9").isNumber(false).isOfType(ValueType.NUMBER, false)
      .column("var10").isNumber(false).isOfType(ValueType.NUMBER, false)
      .column("var11").isNumber(false).isOfType(ValueType.NUMBER, false)
      .column("var12").isText(false).isOfType(ValueType.TEXT, false)
      .column("var13").isText(false).isOfType(ValueType.TEXT, false)
      .column("var14").isNumber(false).isOfType(ValueType.NUMBER, false)
      .column("var15").isNumber(false).isOfType(ValueType.NUMBER, false)
      .column("var16").isNumber(false).isOfType(ValueType.NUMBER, false)
      .column("var17").isText(false).isOfType(ValueType.TEXT, false)
      .column("var18").isText(false).isOfType(ValueType.TEXT, false)
      .column("var19").isText(false).isOfType(ValueType.TEXT, false)
      .column("var20").isText(false).isOfType(ValueType.TEXT, false)
    ;
  }

  @Test
  @NeedReload
  public void test_ColumnOfChangeEquality_hasValues() {
    Table table = new Table(source, "test", null, new String[]{"var20"});
    Changes changes = new Changes(table).setStartPointNow();
    update();
    changes.setEndPointNow();

    assertThat(changes).change()
      .column("var1").hasValues(1)
      .column("var2").hasValues("13")
      .column("var3").hasValues("2")
      .column("var4").hasValues("14")
      .column("var5").hasValues("15")
      .column("var6").hasValues(Date.valueOf("2007-12-23"))
      .column("var7").hasValues(3.3)
      .column("var8").hasValues(4.4)
      .column("var9").hasValues(5.5)
      .column("var10").hasValues(6.6)
      .column("var11").hasValues(7, 20)
      .column("var12").hasValues("8")
      .column("var13").hasValues("16")
      .column("var14").hasValues(9)
      .column("var15").hasValues(10.1)
      .column("var16").hasValues(11)
      .column("var17").hasValues("09:01:00")
      .column("var18").hasValues("2007-12-23 09:01:00.0")
      .column("var19").hasValues("12")
    ;

    assertThat(changes).change()
      .column("var1").hasValues(1, 1)
      .column("var2").hasValues("13", "13")
      .column("var3").hasValues("2", "2")
      .column("var4").hasValues("14", "14")
      .column("var5").hasValues("15", "15")
      .column("var6").hasValues(Date.valueOf("2007-12-23"), Date.valueOf("2007-12-23"))
      .column("var7").hasValues(3.3, 3.3)
      .column("var8").hasValues(4.4, 4.4)
      .column("var9").hasValues(5.5, 5.5)
      .column("var10").hasValues(6.6, 6.6)
      .column("var11").hasValues(7, 20)
      .column("var12").hasValues("8", "8")
      .column("var13").hasValues("16", "16")
      .column("var14").hasValues(9, 9)
      .column("var15").hasValues(10.1, 10.1)
      .column("var16").hasValues(11, 11)
      .column("var17").hasValues("09:01:00", "09:01:00")
      .column("var18").hasValues("2007-12-23 09:01:00.0", "2007-12-23 09:01:00.0")
      .column("var19").hasValues("12", "12")
    ;
  }

  @Test
  @NeedReload
  public void test_RowEquality_hasValues() {
    Table table = new Table(source, "test", null, new String[]{"var20"});
    Changes changes = new Changes(table).setStartPointNow();
    update();
    changes.setEndPointNow();

    assertThat(table).row()
      .hasValues(1, "13", "2", "14", "15",
        DateValue.of(2007, 12, 23), 3.3, 4.4, 5.5, 6.6,
        20, "8", "16", 9, 10.1,
        11, "09:01:00", "2007-12-23 09:01:00.0", "12")
    ;

    assertThat(changes).change().rowAtStartPoint()
      .hasValues(1, "13", "2", "14", "15",
        DateValue.of(2007, 12, 23), 3.3, 4.4, 5.5, 6.6,
        7, "8", "16", 9, 10.1,
        11, "09:01:00", "2007-12-23 09:01:00.0", "12")
    ;
  }

  @Test
  @NeedReload
  public void test_ValueClass_isOfClass() {
    Table table = new Table(source, "test");
    Changes changes = new Changes(table).setStartPointNow();
    update();
    changes.setEndPointNow();

    assertThat(table).row()
      .value("var1").isOfClass(Integer.class)
      .value("var2").isOfClass(String.class)
      .value("var3").isOfClass(String.class)
      .value("var4").isOfClass(String.class)
      .value("var5").isOfClass(String.class)
      .value("var6").isOfClass(Date.class)
      .value("var7").isOfClass(Double.class)
      .value("var8").isOfClass(Double.class)
      .value("var9").isOfClass(Double.class)
      .value("var10").isOfClass(Double.class)
      .value("var11").isOfClass(Integer.class)
      .value("var12").isOfClass(String.class)
      .value("var13").isOfClass(String.class)
      .value("var14").isOfClass(Integer.class)
      .value("var15").isOfClass(Double.class)
      .value("var16").isOfClass(Integer.class)
      .value("var17").isOfClass(String.class)
      .value("var18").isOfClass(String.class)
      .value("var19").isOfClass(String.class)
      .value("var20").isOfClass(String.class)
    ;

    assertThat(changes).change().rowAtStartPoint()
      .value("var1").isOfClass(Integer.class)
      .value("var2").isOfClass(String.class)
      .value("var3").isOfClass(String.class)
      .value("var4").isOfClass(String.class)
      .value("var5").isOfClass(String.class)
      .value("var6").isOfClass(Date.class)
      .value("var7").isOfClass(Double.class)
      .value("var8").isOfClass(Double.class)
      .value("var9").isOfClass(Double.class)
      .value("var10").isOfClass(Double.class)
      .value("var11").isOfClass(Integer.class)
      .value("var12").isOfClass(String.class)
      .value("var13").isOfClass(String.class)
      .value("var14").isOfClass(Integer.class)
      .value("var15").isOfClass(Double.class)
      .value("var16").isOfClass(Integer.class)
      .value("var17").isOfClass(String.class)
      .value("var18").isOfClass(String.class)
      .value("var19").isOfClass(String.class)
      .value("var20").isOfClass(String.class)
    ;
  }

  @Test
  @NeedReload
  public void test_ValueEquality_isEqualTo() {
    Table table = new Table(source, "test");
    Changes changes = new Changes(table).setStartPointNow();
    update();
    changes.setEndPointNow();

    assertThat(table).row()
      .value("var1").isEqualTo(1)
      .value("var2").isEqualTo("13")
      .value("var3").isEqualTo("2")
      .value("var4").isEqualTo("14")
      .value("var5").isEqualTo("15")
      .value("var6").isEqualTo(Date.valueOf("2007-12-23"))
      .value("var7").isEqualTo(3.3)
      .value("var8").isEqualTo(4.4)
      .value("var9").isEqualTo(5.5)
      .value("var10").isEqualTo(6.6)
      .value("var11").isEqualTo(20)
      .value("var12").isEqualTo("8")
      .value("var13").isEqualTo("16")
      .value("var14").isEqualTo(9)
      .value("var15").isEqualTo(10.1)
      .value("var16").isEqualTo(11)
      .value("var17").isEqualTo("09:01:00")
      .value("var18").isEqualTo("2007-12-23 09:01:00.0")
      .value("var19").isEqualTo("12")
    ;

    assertThat(changes).change().rowAtStartPoint()
      .value("var1").isEqualTo(1)
      .value("var2").isEqualTo("13")
      .value("var3").isEqualTo("2")
      .value("var4").isEqualTo("14")
      .value("var5").isEqualTo("15")
      .value("var6").isEqualTo(Date.valueOf("2007-12-23"))
      .value("var7").isEqualTo(3.3)
      .value("var8").isEqualTo(4.4)
      .value("var9").isEqualTo(5.5)
      .value("var10").isEqualTo(6.6)
      .value("var11").isEqualTo(7)
      .value("var12").isEqualTo("8")
      .value("var13").isEqualTo("16")
      .value("var14").isEqualTo(9)
      .value("var15").isEqualTo(10.1)
      .value("var16").isEqualTo(11)
      .value("var17").isEqualTo("09:01:00")
      .value("var18").isEqualTo("2007-12-23 09:01:00.0")
      .value("var19").isEqualTo("12")
    ;
  }

  @Test
  @NeedReload
  public void test_ValueNonEquality_isNotEqualTo() {
    Table table = new Table(source, "test");
    Changes changes = new Changes(table).setStartPointNow();
    update();
    changes.setEndPointNow();

    assertThat(table).row()
      .value("var1").isNotEqualTo(2)
      .value("var2").isNotEqualTo("14")
      .value("var3").isNotEqualTo("3")
      .value("var4").isNotEqualTo("15")
      .value("var5").isNotEqualTo("16")
      .value("var6").isNotEqualTo(Date.valueOf("2008-12-23"))
      .value("var7").isNotEqualTo(4.3)
      .value("var8").isNotEqualTo(5.4)
      .value("var9").isNotEqualTo(6.5)
      .value("var10").isNotEqualTo(7.6)
      .value("var11").isNotEqualTo(21)
      .value("var12").isNotEqualTo("9")
      .value("var13").isNotEqualTo("17")
      .value("var14").isNotEqualTo(8)
      .value("var15").isNotEqualTo(11.1)
      .value("var16").isNotEqualTo(12)
      .value("var17").isNotEqualTo("09:02:00")
      .value("var18").isNotEqualTo("2007-12-24 09:01:00.0")
      .value("var19").isNotEqualTo("121")
    ;

    assertThat(changes).change().rowAtStartPoint()
      .value("var1").isNotEqualTo(2)
      .value("var2").isNotEqualTo("14")
      .value("var3").isNotEqualTo("3")
      .value("var4").isNotEqualTo("15")
      .value("var5").isNotEqualTo("16")
      .value("var6").isNotEqualTo(Date.valueOf("2008-12-23"))
      .value("var7").isNotEqualTo(4.3)
      .value("var8").isNotEqualTo(5.4)
      .value("var9").isNotEqualTo(6.5)
      .value("var10").isNotEqualTo(7.6)
      .value("var11").isNotEqualTo(21)
      .value("var12").isNotEqualTo("9")
      .value("var13").isNotEqualTo("17")
      .value("var14").isNotEqualTo(8)
      .value("var15").isNotEqualTo(11.1)
      .value("var16").isNotEqualTo(12)
      .value("var17").isNotEqualTo("09:02:00")
      .value("var18").isNotEqualTo("2007-12-24 09:01:00.0")
      .value("var19").isNotEqualTo("121")
    ;
  }

  @Test
  @NeedReload
  public void test_ValueType_isOfType() {
    Table table = new Table(source, "test");
    Changes changes = new Changes(table).setStartPointNow();
    update();
    changes.setEndPointNow();

    assertThat(table).row()
      .value("var1").isNumber().isOfType(ValueType.NUMBER)
      .value("var2").isText().isOfType(ValueType.TEXT)
      .value("var3").isText().isOfType(ValueType.TEXT)
      .value("var4").isText().isOfType(ValueType.TEXT)
      .value("var5").isText().isOfType(ValueType.TEXT)
      .value("var6").isDate().isOfType(ValueType.DATE)
      .value("var7").isNumber().isOfType(ValueType.NUMBER)
      .value("var8").isNumber().isOfType(ValueType.NUMBER)
      .value("var9").isNumber().isOfType(ValueType.NUMBER)
      .value("var10").isNumber().isOfType(ValueType.NUMBER)
      .value("var11").isNumber().isOfType(ValueType.NUMBER)
      .value("var12").isText().isOfType(ValueType.TEXT)
      .value("var13").isText().isOfType(ValueType.TEXT)
      .value("var14").isNumber().isOfType(ValueType.NUMBER)
      .value("var15").isNumber().isOfType(ValueType.NUMBER)
      .value("var16").isNumber().isOfType(ValueType.NUMBER)
      .value("var17").isText().isOfType(ValueType.TEXT)
      .value("var18").isText().isOfType(ValueType.TEXT)
      .value("var19").isText().isOfType(ValueType.TEXT)
      .value("var20").isText().isOfType(ValueType.TEXT)
    ;

    assertThat(changes).change().rowAtStartPoint()
      .value("var1").isNumber().isOfType(ValueType.NUMBER)
      .value("var2").isText().isOfType(ValueType.TEXT)
      .value("var3").isText().isOfType(ValueType.TEXT)
      .value("var4").isText().isOfType(ValueType.TEXT)
      .value("var5").isText().isOfType(ValueType.TEXT)
      .value("var6").isDate().isOfType(ValueType.DATE)
      .value("var7").isNumber().isOfType(ValueType.NUMBER)
      .value("var8").isNumber().isOfType(ValueType.NUMBER)
      .value("var9").isNumber().isOfType(ValueType.NUMBER)
      .value("var10").isNumber().isOfType(ValueType.NUMBER)
      .value("var11").isNumber().isOfType(ValueType.NUMBER)
      .value("var12").isText().isOfType(ValueType.TEXT)
      .value("var13").isText().isOfType(ValueType.TEXT)
      .value("var14").isNumber().isOfType(ValueType.NUMBER)
      .value("var15").isNumber().isOfType(ValueType.NUMBER)
      .value("var16").isNumber().isOfType(ValueType.NUMBER)
      .value("var17").isText().isOfType(ValueType.TEXT)
      .value("var18").isText().isOfType(ValueType.TEXT)
      .value("var19").isText().isOfType(ValueType.TEXT)
      .value("var20").isText().isOfType(ValueType.TEXT)
    ;
  }
}
