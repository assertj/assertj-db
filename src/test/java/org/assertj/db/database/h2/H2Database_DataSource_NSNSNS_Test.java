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
package org.assertj.db.database.h2;

import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;

import org.assertj.core.api.Assertions;
import org.assertj.db.common.NeedReload;
import org.assertj.db.exception.AssertJDBException;
import org.assertj.db.output.Outputs;
import org.assertj.db.type.AssertDbConnection;
import org.assertj.db.type.Change;
import org.assertj.db.type.ChangeType;
import org.assertj.db.type.Changes;
import org.assertj.db.type.Column;
import org.assertj.db.type.Request;
import org.assertj.db.type.Row;
import org.assertj.db.type.Table;
import org.assertj.db.type.Value;
import org.assertj.db.type.lettercase.CaseComparisons;
import org.assertj.db.type.lettercase.CaseConversions;
import org.junit.Before;
import org.junit.Test;

/**
 * Test on the H2 database.
 *
 * @author Régis Pouiller
 * @author Julien Roy
 */
public class H2Database_DataSource_NSNSNS_Test extends AbstractH2Test {

  private AssertDbConnection connection;

  @Before
  public void init() {
    connection = dsConnectionNSNSNS;
  }

  @Test

  @NeedReload
  public void test_Outputs_output() {
    Table table = connection.table("test").build();
    Changes changes = connection.changes().tables(table).build().setStartPointNow();
    Changes changes2 = connection.changes().build().setStartPointNow();
    update();
    changes.setEndPointNow();
    changes2.setEndPointNow();

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
    ByteArrayOutputStream byteArrayOutputStream11 = new ByteArrayOutputStream();
    ByteArrayOutputStream byteArrayOutputStream12 = new ByteArrayOutputStream();
    ByteArrayOutputStream byteArrayOutputStream13 = new ByteArrayOutputStream();
    ByteArrayOutputStream byteArrayOutputStream14 = new ByteArrayOutputStream();
    ByteArrayOutputStream byteArrayOutputStream15 = new ByteArrayOutputStream();
    ByteArrayOutputStream byteArrayOutputStream16 = new ByteArrayOutputStream();
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
    output(changes2).toStream(byteArrayOutputStream11)
      .change().toStream(byteArrayOutputStream12)
      .rowAtEndPoint().toStream(byteArrayOutputStream13)
      .value().toStream(byteArrayOutputStream14)
      .column().toStream(byteArrayOutputStream15)
      .valueAtEndPoint().toStream(byteArrayOutputStream16);

    Assertions.assertThat(byteArrayOutputStream0.toString()).as("display1").isEqualTo(String.format("[test table]%n"
      + "|-----------|---------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|--------------------|------------|-------------------------------|-------------------------------|-------------------------------|------------|------------|------------|------------|------------|-------------------------------------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|--------------------------------------|------------------|------------------|%n"
      + "|           |         |           |           |           |           |           |           |           |           |           |           |            |            |            |            |            |            |            |            |            |            |            |            |            |            |                    |            |                               |                               |                               |            |            |            |            |            |                                           |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |                                      |                  |                  |%n"
      + "|           | PRIMARY | VAR1      | VAR2      | VAR3      | VAR4      | VAR5      | VAR6      | VAR7      | VAR8      | VAR9      | VAR10     | VAR11      | VAR12      | VAR13      | VAR14      | VAR15      | VAR16      | VAR17      | VAR18      | VAR19      | VAR20      | VAR21      | VAR22      | VAR23      | VAR24      | VAR25              | VAR26      | VAR27                         | VAR28                         | VAR29                         | VAR30      | VAR31      | VAR32      | VAR33      | VAR34      | VAR35                                     | VAR36      | VAR37      | VAR38      | VAR39      | VAR40      | VAR41      | VAR42      | VAR43      | VAR44      | VAR45      | VAR46      | VAR47      | VAR48      | VAR49      | VAR50      | VAR51      | VAR52      | VAR53      | VAR54      | VAR55      | VAR56      | VAR57      | VAR58      | VAR59                                | VAR60            | VAR61            |%n"
      + "|           | KEY     | (NUMBER)  | (NUMBER)  | (NUMBER)  | (NUMBER)  | (NUMBER)  | (NUMBER)  | (BOOLEAN) | (BOOLEAN) | (BOOLEAN) | (NUMBER)  | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (TIME)             | (DATE)     | (DATE_TIME)                   | (DATE_TIME)                   | (DATE_TIME)                   | (BYTES)    | (BYTES)    | (BYTES)    | (BYTES)    | (BYTES)    | (NOT_IDENTIFIED : class java.util.Locale) | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (BYTES)    | (BYTES)    | (BYTES)    | (BYTES)    | (BYTES)    | (BYTES)    | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (UUID)                               | (NOT_IDENTIFIED) | (NOT_IDENTIFIED) |%n"
      + "|           |         | Index : 0 | Index : 1 | Index : 2 | Index : 3 | Index : 4 | Index : 5 | Index : 6 | Index : 7 | Index : 8 | Index : 9 | Index : 10 | Index : 11 | Index : 12 | Index : 13 | Index : 14 | Index : 15 | Index : 16 | Index : 17 | Index : 18 | Index : 19 | Index : 20 | Index : 21 | Index : 22 | Index : 23 | Index : 24         | Index : 25 | Index : 26                    | Index : 27                    | Index : 28                    | Index : 29 | Index : 30 | Index : 31 | Index : 32 | Index : 33 | Index : 34                                | Index : 35 | Index : 36 | Index : 37 | Index : 38 | Index : 39 | Index : 40 | Index : 41 | Index : 42 | Index : 43 | Index : 44 | Index : 45 | Index : 46 | Index : 47 | Index : 48 | Index : 49 | Index : 50 | Index : 51 | Index : 52 | Index : 53 | Index : 54 | Index : 55 | Index : 56 | Index : 57 | Index : 58                           | Index : 59       | Index : 60       |%n"
      + "|-----------|---------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|--------------------|------------|-------------------------------|-------------------------------|-------------------------------|------------|------------|------------|------------|------------|-------------------------------------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|--------------------------------------|------------------|------------------|%n"
      + "| Index : 0 |         | 1         | 20        | 3         | 4         | 5         | 6         | true      | false     | true      | 7         | 8          | 9          | 10         | 11         | 12         | 13.13      | 14.14      | 15.15      | 16.16      | 17.17      | 18.18      | 19.19      | 20.2       | 21.21      | 09:01:00.000000000 | 2007-12-23 | 2007-12-23T09:01:00.000000000 | 2007-12-23T09:01:00.000000000 | 2007-12-23T09:01:00.000000000 | ...        | ...        | ...        | ...        | ...        | fr                                        | 22         | 23         | 24         | 25         | 26         | 27         | 28         | 29         | 30         | 31         | ...        | ...        | ...        | ...        | ...        | ...        | 32         | 33         | 34         | 35         | 36         | 37         | 38         | 30b443ae-c0c9-4790-9bec-ce1380808435 | null             | null             |%n"
      + "|-----------|---------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|--------------------|------------|-------------------------------|-------------------------------|-------------------------------|------------|------------|------------|------------|------------|-------------------------------------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|--------------------------------------|------------------|------------------|%n"));
    Assertions.assertThat(byteArrayOutputStream1.toString()).as("display2").isEqualTo(String.format("[Column at index 0 (column name : VAR1) of test table]%n"
      + "|-----------|----------|%n"
      + "|           | VAR1     |%n"
      + "|           | (NUMBER) |%n"
      + "|-----------|----------|%n"
      + "| Index : 0 | 1        |%n"
      + "|-----------|----------|%n"));
    Assertions.assertThat(byteArrayOutputStream2.toString()).as("display3").isEqualTo(String.format("[Value at index 0 of Column at index 0 (column name : VAR1) of test table]%n"
      + "|----------|%n"
      + "| VAR1     |%n"
      + "| (NUMBER) |%n"
      + "|----------|%n"
      + "| 1        |%n"
      + "|----------|%n"));
    Assertions.assertThat(byteArrayOutputStream3.toString()).as("display4").isEqualTo(String.format("[Row at index 0 of test table]%n"
      + "|---------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|--------------------|------------|-------------------------------|-------------------------------|-------------------------------|------------|------------|------------|------------|------------|-------------------------------------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|--------------------------------------|------------------|------------------|%n"
      + "|         |           |           |           |           |           |           |           |           |           |           |            |            |            |            |            |            |            |            |            |            |            |            |            |            |                    |            |                               |                               |                               |            |            |            |            |            |                                           |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |                                      |                  |                  |%n"
      + "| PRIMARY | VAR1      | VAR2      | VAR3      | VAR4      | VAR5      | VAR6      | VAR7      | VAR8      | VAR9      | VAR10     | VAR11      | VAR12      | VAR13      | VAR14      | VAR15      | VAR16      | VAR17      | VAR18      | VAR19      | VAR20      | VAR21      | VAR22      | VAR23      | VAR24      | VAR25              | VAR26      | VAR27                         | VAR28                         | VAR29                         | VAR30      | VAR31      | VAR32      | VAR33      | VAR34      | VAR35                                     | VAR36      | VAR37      | VAR38      | VAR39      | VAR40      | VAR41      | VAR42      | VAR43      | VAR44      | VAR45      | VAR46      | VAR47      | VAR48      | VAR49      | VAR50      | VAR51      | VAR52      | VAR53      | VAR54      | VAR55      | VAR56      | VAR57      | VAR58      | VAR59                                | VAR60            | VAR61            |%n"
      + "| KEY     | (NUMBER)  | (NUMBER)  | (NUMBER)  | (NUMBER)  | (NUMBER)  | (NUMBER)  | (BOOLEAN) | (BOOLEAN) | (BOOLEAN) | (NUMBER)  | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (TIME)             | (DATE)     | (DATE_TIME)                   | (DATE_TIME)                   | (DATE_TIME)                   | (BYTES)    | (BYTES)    | (BYTES)    | (BYTES)    | (BYTES)    | (NOT_IDENTIFIED : class java.util.Locale) | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (BYTES)    | (BYTES)    | (BYTES)    | (BYTES)    | (BYTES)    | (BYTES)    | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (UUID)                               | (NOT_IDENTIFIED) | (NOT_IDENTIFIED) |%n"
      + "|         | Index : 0 | Index : 1 | Index : 2 | Index : 3 | Index : 4 | Index : 5 | Index : 6 | Index : 7 | Index : 8 | Index : 9 | Index : 10 | Index : 11 | Index : 12 | Index : 13 | Index : 14 | Index : 15 | Index : 16 | Index : 17 | Index : 18 | Index : 19 | Index : 20 | Index : 21 | Index : 22 | Index : 23 | Index : 24         | Index : 25 | Index : 26                    | Index : 27                    | Index : 28                    | Index : 29 | Index : 30 | Index : 31 | Index : 32 | Index : 33 | Index : 34                                | Index : 35 | Index : 36 | Index : 37 | Index : 38 | Index : 39 | Index : 40 | Index : 41 | Index : 42 | Index : 43 | Index : 44 | Index : 45 | Index : 46 | Index : 47 | Index : 48 | Index : 49 | Index : 50 | Index : 51 | Index : 52 | Index : 53 | Index : 54 | Index : 55 | Index : 56 | Index : 57 | Index : 58                           | Index : 59       | Index : 60       |%n"
      + "|---------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|--------------------|------------|-------------------------------|-------------------------------|-------------------------------|------------|------------|------------|------------|------------|-------------------------------------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|--------------------------------------|------------------|------------------|%n"
      + "|         | 1         | 20        | 3         | 4         | 5         | 6         | true      | false     | true      | 7         | 8          | 9          | 10         | 11         | 12         | 13.13      | 14.14      | 15.15      | 16.16      | 17.17      | 18.18      | 19.19      | 20.2       | 21.21      | 09:01:00.000000000 | 2007-12-23 | 2007-12-23T09:01:00.000000000 | 2007-12-23T09:01:00.000000000 | 2007-12-23T09:01:00.000000000 | ...        | ...        | ...        | ...        | ...        | fr                                        | 22         | 23         | 24         | 25         | 26         | 27         | 28         | 29         | 30         | 31         | ...        | ...        | ...        | ...        | ...        | ...        | 32         | 33         | 34         | 35         | 36         | 37         | 38         | 30b443ae-c0c9-4790-9bec-ce1380808435 | null             | null             |%n"
      + "|---------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|--------------------|------------|-------------------------------|-------------------------------|-------------------------------|------------|------------|------------|------------|------------|-------------------------------------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|--------------------------------------|------------------|------------------|%n"));
    Assertions.assertThat(byteArrayOutputStream4.toString()).as("display5").isEqualTo(String.format("[Value at index 0 (column name : VAR1) of Row at index 0 of test table]%n"
      + "|----------|%n"
      + "| VAR1     |%n"
      + "| (NUMBER) |%n"
      + "|----------|%n"
      + "| 1        |%n"
      + "|----------|%n"));
    Assertions.assertThat(byteArrayOutputStream5.toString()).as("display6").isEqualTo(String.format("[Changes on test table of 'data source']%n"
      + "|-----------|----------|-------|---------|----------------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|--------------------|------------|-------------------------------|-------------------------------|-------------------------------|------------|------------|------------|------------|------------|-------------------------------------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|--------------------------------------|------------------|------------------|%n"
      + "|           |          |       |         |                |           |           |           |           |           |           |           |           |           |           |            |            |            |            |            |            |            |            |            |            |            |            |            |            |                    |            |                               |                               |                               |            |            |            |            |            |                                           |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |                                      |                  |                  |%n"
      + "|           | TYPE     | TABLE | PRIMARY |                | VAR1      | VAR2      | VAR3      | VAR4      | VAR5      | VAR6      | VAR7      | VAR8      | VAR9      | VAR10     | VAR11      | VAR12      | VAR13      | VAR14      | VAR15      | VAR16      | VAR17      | VAR18      | VAR19      | VAR20      | VAR21      | VAR22      | VAR23      | VAR24      | VAR25              | VAR26      | VAR27                         | VAR28                         | VAR29                         | VAR30      | VAR31      | VAR32      | VAR33      | VAR34      | VAR35                                     | VAR36      | VAR37      | VAR38      | VAR39      | VAR40      | VAR41      | VAR42      | VAR43      | VAR44      | VAR45      | VAR46      | VAR47      | VAR48      | VAR49      | VAR50      | VAR51      | VAR52      | VAR53      | VAR54      | VAR55      | VAR56      | VAR57      | VAR58      | VAR59                                | VAR60            | VAR61            |%n"
      + "|           |          |       | KEY     |                | (NUMBER)  | (NUMBER)  | (NUMBER)  | (NUMBER)  | (NUMBER)  | (NUMBER)  | (BOOLEAN) | (BOOLEAN) | (BOOLEAN) | (NUMBER)  | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (TIME)             | (DATE)     | (DATE_TIME)                   | (DATE_TIME)                   | (DATE_TIME)                   | (BYTES)    | (BYTES)    | (BYTES)    | (BYTES)    | (BYTES)    | (NOT_IDENTIFIED : class java.util.Locale) | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (BYTES)    | (BYTES)    | (BYTES)    | (BYTES)    | (BYTES)    | (BYTES)    | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (UUID)                               | (NOT_IDENTIFIED) | (NOT_IDENTIFIED) |%n"
      + "|           |          |       |         |                | Index : 0 | Index : 1 | Index : 2 | Index : 3 | Index : 4 | Index : 5 | Index : 6 | Index : 7 | Index : 8 | Index : 9 | Index : 10 | Index : 11 | Index : 12 | Index : 13 | Index : 14 | Index : 15 | Index : 16 | Index : 17 | Index : 18 | Index : 19 | Index : 20 | Index : 21 | Index : 22 | Index : 23 | Index : 24         | Index : 25 | Index : 26                    | Index : 27                    | Index : 28                    | Index : 29 | Index : 30 | Index : 31 | Index : 32 | Index : 33 | Index : 34                                | Index : 35 | Index : 36 | Index : 37 | Index : 38 | Index : 39 | Index : 40 | Index : 41 | Index : 42 | Index : 43 | Index : 44 | Index : 45 | Index : 46 | Index : 47 | Index : 48 | Index : 49 | Index : 50 | Index : 51 | Index : 52 | Index : 53 | Index : 54 | Index : 55 | Index : 56 | Index : 57 | Index : 58                           | Index : 59       | Index : 60       |%n"
      + "|-----------|----------|-------|---------|----------------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|--------------------|------------|-------------------------------|-------------------------------|-------------------------------|------------|------------|------------|------------|------------|-------------------------------------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|--------------------------------------|------------------|------------------|%n"
      + "|           |          |       |         | At start point |           |           |           |           |           |           |           |           |           |           |            |            |            |            |            |            |            |            |            |            |            |            |            |            |                    |            |                               |                               |                               |            |            |            |            |            |                                           |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |                                      |                  |                  |%n"
      + "| Index : 0 | CREATION | test  |         |----------------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|--------------------|------------|-------------------------------|-------------------------------|-------------------------------|------------|------------|------------|------------|------------|-------------------------------------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|--------------------------------------|------------------|------------------|%n"
      + "|           |          |       |         | At end point   | 1         | 20        | 3         | 4         | 5         | 6         | true      | false     | true      | 7         | 8          | 9          | 10         | 11         | 12         | 13.13      | 14.14      | 15.15      | 16.16      | 17.17      | 18.18      | 19.19      | 20.2       | 21.21      | 09:01:00.000000000 | 2007-12-23 | 2007-12-23T09:01:00.000000000 | 2007-12-23T09:01:00.000000000 | 2007-12-23T09:01:00.000000000 | ...        | ...        | ...        | ...        | ...        | fr                                        | 22         | 23         | 24         | 25         | 26         | 27         | 28         | 29         | 30         | 31         | ...        | ...        | ...        | ...        | ...        | ...        | 32         | 33         | 34         | 35         | 36         | 37         | 38         | 30b443ae-c0c9-4790-9bec-ce1380808435 | null             | null             |%n"
      + "|-----------|----------|-------|---------|----------------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|--------------------|------------|-------------------------------|-------------------------------|-------------------------------|------------|------------|------------|------------|------------|-------------------------------------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|--------------------------------------|------------------|------------------|%n"
      + "|-----------|----------|-------|---------|----------------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|--------------------|------------|-------------------------------|-------------------------------|-------------------------------|------------|------------|------------|------------|------------|-------------------------------------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|--------------------------------------|------------------|------------------|%n"
      + "|           |          |       |         |                |           |           |           |           |           |           |           |           |           |           |            |            |            |            |            |            |            |            |            |            |            |            |            |            |                    |            |                               |                               |                               |            |            |            |            |            |                                           |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |                                      |                  |                  |%n"
      + "|           | TYPE     | TABLE | PRIMARY |                | VAR1      | VAR2      | VAR3      | VAR4      | VAR5      | VAR6      | VAR7      | VAR8      | VAR9      | VAR10     | VAR11      | VAR12      | VAR13      | VAR14      | VAR15      | VAR16      | VAR17      | VAR18      | VAR19      | VAR20      | VAR21      | VAR22      | VAR23      | VAR24      | VAR25              | VAR26      | VAR27                         | VAR28                         | VAR29                         | VAR30      | VAR31      | VAR32      | VAR33      | VAR34      | VAR35                                     | VAR36      | VAR37      | VAR38      | VAR39      | VAR40      | VAR41      | VAR42      | VAR43      | VAR44      | VAR45      | VAR46      | VAR47      | VAR48      | VAR49      | VAR50      | VAR51      | VAR52      | VAR53      | VAR54      | VAR55      | VAR56      | VAR57      | VAR58      | VAR59                                | VAR60            | VAR61            |%n"
      + "|           |          |       | KEY     |                | (NUMBER)  | (NUMBER)  | (NUMBER)  | (NUMBER)  | (NUMBER)  | (NUMBER)  | (BOOLEAN) | (BOOLEAN) | (BOOLEAN) | (NUMBER)  | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (TIME)             | (DATE)     | (DATE_TIME)                   | (DATE_TIME)                   | (DATE_TIME)                   | (BYTES)    | (BYTES)    | (BYTES)    | (BYTES)    | (BYTES)    | (NOT_IDENTIFIED : class java.util.Locale) | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (BYTES)    | (BYTES)    | (BYTES)    | (BYTES)    | (BYTES)    | (BYTES)    | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (UUID)                               | (NOT_IDENTIFIED) | (NOT_IDENTIFIED) |%n"
      + "|           |          |       |         |                | Index : 0 | Index : 1 | Index : 2 | Index : 3 | Index : 4 | Index : 5 | Index : 6 | Index : 7 | Index : 8 | Index : 9 | Index : 10 | Index : 11 | Index : 12 | Index : 13 | Index : 14 | Index : 15 | Index : 16 | Index : 17 | Index : 18 | Index : 19 | Index : 20 | Index : 21 | Index : 22 | Index : 23 | Index : 24         | Index : 25 | Index : 26                    | Index : 27                    | Index : 28                    | Index : 29 | Index : 30 | Index : 31 | Index : 32 | Index : 33 | Index : 34                                | Index : 35 | Index : 36 | Index : 37 | Index : 38 | Index : 39 | Index : 40 | Index : 41 | Index : 42 | Index : 43 | Index : 44 | Index : 45 | Index : 46 | Index : 47 | Index : 48 | Index : 49 | Index : 50 | Index : 51 | Index : 52 | Index : 53 | Index : 54 | Index : 55 | Index : 56 | Index : 57 | Index : 58                           | Index : 59       | Index : 60       |%n"
      + "|-----------|----------|-------|---------|----------------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|--------------------|------------|-------------------------------|-------------------------------|-------------------------------|------------|------------|------------|------------|------------|-------------------------------------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|--------------------------------------|------------------|------------------|%n"
      + "|           |          |       |         | At start point | 1         | 2         | 3         | 4         | 5         | 6         | true      | false     | true      | 7         | 8          | 9          | 10         | 11         | 12         | 13.13      | 14.14      | 15.15      | 16.16      | 17.17      | 18.18      | 19.19      | 20.2       | 21.21      | 09:01:00.000000000 | 2007-12-23 | 2007-12-23T09:01:00.000000000 | 2007-12-23T09:01:00.000000000 | 2007-12-23T09:01:00.000000000 | ...        | ...        | ...        | ...        | ...        | fr                                        | 22         | 23         | 24         | 25         | 26         | 27         | 28         | 29         | 30         | 31         | ...        | ...        | ...        | ...        | ...        | ...        | 32         | 33         | 34         | 35         | 36         | 37         | 38         | 30b443ae-c0c9-4790-9bec-ce1380808435 | null             | null             |%n"
      + "| Index : 1 | DELETION | test  |         |----------------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|--------------------|------------|-------------------------------|-------------------------------|-------------------------------|------------|------------|------------|------------|------------|-------------------------------------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|--------------------------------------|------------------|------------------|%n"
      + "|           |          |       |         | At end point   |           |           |           |           |           |           |           |           |           |           |            |            |            |            |            |            |            |            |            |            |            |            |            |            |                    |            |                               |                               |                               |            |            |            |            |            |                                           |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |                                      |                  |                  |%n"
      + "|-----------|----------|-------|---------|----------------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|--------------------|------------|-------------------------------|-------------------------------|-------------------------------|------------|------------|------------|------------|------------|-------------------------------------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|--------------------------------------|------------------|------------------|%n"));
    Assertions.assertThat(byteArrayOutputStream6.toString()).as("display7").isEqualTo(String.format("[Change at index 0 of Changes on test table of 'data source']%n"
      + "|----------|-------|---------|----------------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|--------------------|------------|-------------------------------|-------------------------------|-------------------------------|------------|------------|------------|------------|------------|-------------------------------------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|--------------------------------------|------------------|------------------|%n"
      + "|          |       |         |                |           |           |           |           |           |           |           |           |           |           |            |            |            |            |            |            |            |            |            |            |            |            |            |            |                    |            |                               |                               |                               |            |            |            |            |            |                                           |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |                                      |                  |                  |%n"
      + "| TYPE     | TABLE | PRIMARY |                | VAR1      | VAR2      | VAR3      | VAR4      | VAR5      | VAR6      | VAR7      | VAR8      | VAR9      | VAR10     | VAR11      | VAR12      | VAR13      | VAR14      | VAR15      | VAR16      | VAR17      | VAR18      | VAR19      | VAR20      | VAR21      | VAR22      | VAR23      | VAR24      | VAR25              | VAR26      | VAR27                         | VAR28                         | VAR29                         | VAR30      | VAR31      | VAR32      | VAR33      | VAR34      | VAR35                                     | VAR36      | VAR37      | VAR38      | VAR39      | VAR40      | VAR41      | VAR42      | VAR43      | VAR44      | VAR45      | VAR46      | VAR47      | VAR48      | VAR49      | VAR50      | VAR51      | VAR52      | VAR53      | VAR54      | VAR55      | VAR56      | VAR57      | VAR58      | VAR59                                | VAR60            | VAR61            |%n"
      + "|          |       | KEY     |                | (NUMBER)  | (NUMBER)  | (NUMBER)  | (NUMBER)  | (NUMBER)  | (NUMBER)  | (BOOLEAN) | (BOOLEAN) | (BOOLEAN) | (NUMBER)  | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (TIME)             | (DATE)     | (DATE_TIME)                   | (DATE_TIME)                   | (DATE_TIME)                   | (BYTES)    | (BYTES)    | (BYTES)    | (BYTES)    | (BYTES)    | (NOT_IDENTIFIED : class java.util.Locale) | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (BYTES)    | (BYTES)    | (BYTES)    | (BYTES)    | (BYTES)    | (BYTES)    | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (UUID)                               | (NOT_IDENTIFIED) | (NOT_IDENTIFIED) |%n"
      + "|          |       |         |                | Index : 0 | Index : 1 | Index : 2 | Index : 3 | Index : 4 | Index : 5 | Index : 6 | Index : 7 | Index : 8 | Index : 9 | Index : 10 | Index : 11 | Index : 12 | Index : 13 | Index : 14 | Index : 15 | Index : 16 | Index : 17 | Index : 18 | Index : 19 | Index : 20 | Index : 21 | Index : 22 | Index : 23 | Index : 24         | Index : 25 | Index : 26                    | Index : 27                    | Index : 28                    | Index : 29 | Index : 30 | Index : 31 | Index : 32 | Index : 33 | Index : 34                                | Index : 35 | Index : 36 | Index : 37 | Index : 38 | Index : 39 | Index : 40 | Index : 41 | Index : 42 | Index : 43 | Index : 44 | Index : 45 | Index : 46 | Index : 47 | Index : 48 | Index : 49 | Index : 50 | Index : 51 | Index : 52 | Index : 53 | Index : 54 | Index : 55 | Index : 56 | Index : 57 | Index : 58                           | Index : 59       | Index : 60       |%n"
      + "|----------|-------|---------|----------------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|--------------------|------------|-------------------------------|-------------------------------|-------------------------------|------------|------------|------------|------------|------------|-------------------------------------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|--------------------------------------|------------------|------------------|%n"
      + "|          |       |         | At start point |           |           |           |           |           |           |           |           |           |           |            |            |            |            |            |            |            |            |            |            |            |            |            |            |                    |            |                               |                               |                               |            |            |            |            |            |                                           |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |                                      |                  |                  |%n"
      + "| CREATION | test  |         |----------------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|--------------------|------------|-------------------------------|-------------------------------|-------------------------------|------------|------------|------------|------------|------------|-------------------------------------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|--------------------------------------|------------------|------------------|%n"
      + "|          |       |         | At end point   | 1         | 20        | 3         | 4         | 5         | 6         | true      | false     | true      | 7         | 8          | 9          | 10         | 11         | 12         | 13.13      | 14.14      | 15.15      | 16.16      | 17.17      | 18.18      | 19.19      | 20.2       | 21.21      | 09:01:00.000000000 | 2007-12-23 | 2007-12-23T09:01:00.000000000 | 2007-12-23T09:01:00.000000000 | 2007-12-23T09:01:00.000000000 | ...        | ...        | ...        | ...        | ...        | fr                                        | 22         | 23         | 24         | 25         | 26         | 27         | 28         | 29         | 30         | 31         | ...        | ...        | ...        | ...        | ...        | ...        | 32         | 33         | 34         | 35         | 36         | 37         | 38         | 30b443ae-c0c9-4790-9bec-ce1380808435 | null             | null             |%n"
      + "|----------|-------|---------|----------------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|--------------------|------------|-------------------------------|-------------------------------|-------------------------------|------------|------------|------------|------------|------------|-------------------------------------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|--------------------------------------|------------------|------------------|%n"));
    Assertions.assertThat(byteArrayOutputStream7.toString()).as("display8").isEqualTo(String.format("[Row at end point of Change at index 0 of Changes on test table of 'data source']%n"
      + "|---------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|--------------------|------------|-------------------------------|-------------------------------|-------------------------------|------------|------------|------------|------------|------------|-------------------------------------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|--------------------------------------|------------------|------------------|%n"
      + "|         |           |           |           |           |           |           |           |           |           |           |            |            |            |            |            |            |            |            |            |            |            |            |            |            |                    |            |                               |                               |                               |            |            |            |            |            |                                           |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |                                      |                  |                  |%n"
      + "| PRIMARY | VAR1      | VAR2      | VAR3      | VAR4      | VAR5      | VAR6      | VAR7      | VAR8      | VAR9      | VAR10     | VAR11      | VAR12      | VAR13      | VAR14      | VAR15      | VAR16      | VAR17      | VAR18      | VAR19      | VAR20      | VAR21      | VAR22      | VAR23      | VAR24      | VAR25              | VAR26      | VAR27                         | VAR28                         | VAR29                         | VAR30      | VAR31      | VAR32      | VAR33      | VAR34      | VAR35                                     | VAR36      | VAR37      | VAR38      | VAR39      | VAR40      | VAR41      | VAR42      | VAR43      | VAR44      | VAR45      | VAR46      | VAR47      | VAR48      | VAR49      | VAR50      | VAR51      | VAR52      | VAR53      | VAR54      | VAR55      | VAR56      | VAR57      | VAR58      | VAR59                                | VAR60            | VAR61            |%n"
      + "| KEY     | (NUMBER)  | (NUMBER)  | (NUMBER)  | (NUMBER)  | (NUMBER)  | (NUMBER)  | (BOOLEAN) | (BOOLEAN) | (BOOLEAN) | (NUMBER)  | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (TIME)             | (DATE)     | (DATE_TIME)                   | (DATE_TIME)                   | (DATE_TIME)                   | (BYTES)    | (BYTES)    | (BYTES)    | (BYTES)    | (BYTES)    | (NOT_IDENTIFIED : class java.util.Locale) | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (BYTES)    | (BYTES)    | (BYTES)    | (BYTES)    | (BYTES)    | (BYTES)    | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (UUID)                               | (NOT_IDENTIFIED) | (NOT_IDENTIFIED) |%n"
      + "|         | Index : 0 | Index : 1 | Index : 2 | Index : 3 | Index : 4 | Index : 5 | Index : 6 | Index : 7 | Index : 8 | Index : 9 | Index : 10 | Index : 11 | Index : 12 | Index : 13 | Index : 14 | Index : 15 | Index : 16 | Index : 17 | Index : 18 | Index : 19 | Index : 20 | Index : 21 | Index : 22 | Index : 23 | Index : 24         | Index : 25 | Index : 26                    | Index : 27                    | Index : 28                    | Index : 29 | Index : 30 | Index : 31 | Index : 32 | Index : 33 | Index : 34                                | Index : 35 | Index : 36 | Index : 37 | Index : 38 | Index : 39 | Index : 40 | Index : 41 | Index : 42 | Index : 43 | Index : 44 | Index : 45 | Index : 46 | Index : 47 | Index : 48 | Index : 49 | Index : 50 | Index : 51 | Index : 52 | Index : 53 | Index : 54 | Index : 55 | Index : 56 | Index : 57 | Index : 58                           | Index : 59       | Index : 60       |%n"
      + "|---------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|--------------------|------------|-------------------------------|-------------------------------|-------------------------------|------------|------------|------------|------------|------------|-------------------------------------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|--------------------------------------|------------------|------------------|%n"
      + "|         | 1         | 20        | 3         | 4         | 5         | 6         | true      | false     | true      | 7         | 8          | 9          | 10         | 11         | 12         | 13.13      | 14.14      | 15.15      | 16.16      | 17.17      | 18.18      | 19.19      | 20.2       | 21.21      | 09:01:00.000000000 | 2007-12-23 | 2007-12-23T09:01:00.000000000 | 2007-12-23T09:01:00.000000000 | 2007-12-23T09:01:00.000000000 | ...        | ...        | ...        | ...        | ...        | fr                                        | 22         | 23         | 24         | 25         | 26         | 27         | 28         | 29         | 30         | 31         | ...        | ...        | ...        | ...        | ...        | ...        | 32         | 33         | 34         | 35         | 36         | 37         | 38         | 30b443ae-c0c9-4790-9bec-ce1380808435 | null             | null             |%n"
      + "|---------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|--------------------|------------|-------------------------------|-------------------------------|-------------------------------|------------|------------|------------|------------|------------|-------------------------------------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|--------------------------------------|------------------|------------------|%n"));
    Assertions.assertThat(byteArrayOutputStream8.toString()).as("display9").isEqualTo(String.format("[Value at index 0 (column name : VAR1) of Row at end point of Change at index 0 of Changes on test table of 'data source']%n"
      + "|----------|%n"
      + "| VAR1     |%n"
      + "| (NUMBER) |%n"
      + "|----------|%n"
      + "| 1        |%n"
      + "|----------|%n"));
    Assertions.assertThat(byteArrayOutputStream9.toString()).as("display10").isEqualTo(String.format("[Column at index 0 (column name : VAR1) of Change at index 0 of Changes on test table of 'data source']%n"
      + "|----------------|----------|%n"
      + "|                | VAR1     |%n"
      + "|                | (NUMBER) |%n"
      + "|----------------|----------|%n"
      + "| At start point | null     |%n"
      + "|----------------|----------|%n"
      + "| At end point   | 1        |%n"
      + "|----------------|----------|%n"));
    Assertions.assertThat(byteArrayOutputStream10.toString()).as("display11").isEqualTo(String.format("[Value at end point of Column at index 0 (column name : VAR1) of Change at index 0 of Changes on test table of 'data source']%n"
      + "|----------|%n"
      + "| VAR1     |%n"
      + "| (NUMBER) |%n"
      + "|----------|%n"
      + "| 1        |%n"
      + "|----------|%n"));
    Assertions.assertThat(byteArrayOutputStream11.toString()).as("display12").isEqualTo(String.format("[Changes on TEST table of 'data source']%n"
      + "|-----------|--------------|-------|---------|----------------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|--------------------|------------|-------------------------------|-------------------------------|-------------------------------|------------|------------|------------|------------|------------|-------------------------------------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|--------------------------------------|------------------|------------------|%n"
      + "|           |              |       |         |                | *         |           |           |           |           |           |           |           |           |           |            |            |            |            |            |            |            |            |            |            |            |            |            |            |                    |            |                               |                               |                               |            |            |            |            |            |                                           |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |                                      |                  |                  |%n"
      + "|           | TYPE         | TABLE | PRIMARY |                | VAR1      | VAR2      | VAR3      | VAR4      | VAR5      | VAR6      | VAR7      | VAR8      | VAR9      | VAR10     | VAR11      | VAR12      | VAR13      | VAR14      | VAR15      | VAR16      | VAR17      | VAR18      | VAR19      | VAR20      | VAR21      | VAR22      | VAR23      | VAR24      | VAR25              | VAR26      | VAR27                         | VAR28                         | VAR29                         | VAR30      | VAR31      | VAR32      | VAR33      | VAR34      | VAR35                                     | VAR36      | VAR37      | VAR38      | VAR39      | VAR40      | VAR41      | VAR42      | VAR43      | VAR44      | VAR45      | VAR46      | VAR47      | VAR48      | VAR49      | VAR50      | VAR51      | VAR52      | VAR53      | VAR54      | VAR55      | VAR56      | VAR57      | VAR58      | VAR59                                | VAR60            | VAR61            |%n"
      + "|           |              |       | KEY     |                | (NUMBER)  | (NUMBER)  | (NUMBER)  | (NUMBER)  | (NUMBER)  | (NUMBER)  | (BOOLEAN) | (BOOLEAN) | (BOOLEAN) | (NUMBER)  | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (TIME)             | (DATE)     | (DATE_TIME)                   | (DATE_TIME)                   | (DATE_TIME)                   | (BYTES)    | (BYTES)    | (BYTES)    | (BYTES)    | (BYTES)    | (NOT_IDENTIFIED : class java.util.Locale) | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (BYTES)    | (BYTES)    | (BYTES)    | (BYTES)    | (BYTES)    | (BYTES)    | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (UUID)                               | (NOT_IDENTIFIED) | (NOT_IDENTIFIED) |%n"
      + "|           |              |       |         |                | Index : 0 | Index : 1 | Index : 2 | Index : 3 | Index : 4 | Index : 5 | Index : 6 | Index : 7 | Index : 8 | Index : 9 | Index : 10 | Index : 11 | Index : 12 | Index : 13 | Index : 14 | Index : 15 | Index : 16 | Index : 17 | Index : 18 | Index : 19 | Index : 20 | Index : 21 | Index : 22 | Index : 23 | Index : 24         | Index : 25 | Index : 26                    | Index : 27                    | Index : 28                    | Index : 29 | Index : 30 | Index : 31 | Index : 32 | Index : 33 | Index : 34                                | Index : 35 | Index : 36 | Index : 37 | Index : 38 | Index : 39 | Index : 40 | Index : 41 | Index : 42 | Index : 43 | Index : 44 | Index : 45 | Index : 46 | Index : 47 | Index : 48 | Index : 49 | Index : 50 | Index : 51 | Index : 52 | Index : 53 | Index : 54 | Index : 55 | Index : 56 | Index : 57 | Index : 58                           | Index : 59       | Index : 60       |%n"
      + "|-----------|--------------|-------|---------|----------------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|--------------------|------------|-------------------------------|-------------------------------|-------------------------------|------------|------------|------------|------------|------------|-------------------------------------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|--------------------------------------|------------------|------------------|%n"
      + "|           |              |       |         | At start point | 1         | 2         | 3         | 4         | 5         | 6         | true      | false     | true      | 7         | 8          | 9          | 10         | 11         | 12         | 13.13      | 14.14      | 15.15      | 16.16      | 17.17      | 18.18      | 19.19      | 20.2       | 21.21      | 09:01:00.000000000 | 2007-12-23 | 2007-12-23T09:01:00.000000000 | 2007-12-23T09:01:00.000000000 | 2007-12-23T09:01:00.000000000 | ...        | ...        | ...        | ...        | ...        | fr                                        | 22         | 23         | 24         | 25         | 26         | 27         | 28         | 29         | 30         | 31         | ...        | ...        | ...        | ...        | ...        | ...        | 32         | 33         | 34         | 35         | 36         | 37         | 38         | 30b443ae-c0c9-4790-9bec-ce1380808435 | null             | null             |%n"
      + "| Index : 0 | MODIFICATION | TEST  | 1       |----------------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|--------------------|------------|-------------------------------|-------------------------------|-------------------------------|------------|------------|------------|------------|------------|-------------------------------------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|--------------------------------------|------------------|------------------|%n"
      + "|           |              |       |         | At end point   | 1         | 20        | 3         | 4         | 5         | 6         | true      | false     | true      | 7         | 8          | 9          | 10         | 11         | 12         | 13.13      | 14.14      | 15.15      | 16.16      | 17.17      | 18.18      | 19.19      | 20.2       | 21.21      | 09:01:00.000000000 | 2007-12-23 | 2007-12-23T09:01:00.000000000 | 2007-12-23T09:01:00.000000000 | 2007-12-23T09:01:00.000000000 | ...        | ...        | ...        | ...        | ...        | fr                                        | 22         | 23         | 24         | 25         | 26         | 27         | 28         | 29         | 30         | 31         | ...        | ...        | ...        | ...        | ...        | ...        | 32         | 33         | 34         | 35         | 36         | 37         | 38         | 30b443ae-c0c9-4790-9bec-ce1380808435 | null             | null             |%n"
      + "|-----------|--------------|-------|---------|----------------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|--------------------|------------|-------------------------------|-------------------------------|-------------------------------|------------|------------|------------|------------|------------|-------------------------------------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|--------------------------------------|------------------|------------------|%n"));
    Assertions.assertThat(byteArrayOutputStream12.toString()).as("display13").isEqualTo(String.format("[Change at index 0 (with primary key : [1]) of Changes on TEST table of 'data source']%n"
      + "|--------------|-------|---------|----------------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|--------------------|------------|-------------------------------|-------------------------------|-------------------------------|------------|------------|------------|------------|------------|-------------------------------------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|--------------------------------------|------------------|------------------|%n"
      + "|              |       |         |                | *         |           |           |           |           |           |           |           |           |           |            |            |            |            |            |            |            |            |            |            |            |            |            |            |                    |            |                               |                               |                               |            |            |            |            |            |                                           |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |                                      |                  |                  |%n"
      + "| TYPE         | TABLE | PRIMARY |                | VAR1      | VAR2      | VAR3      | VAR4      | VAR5      | VAR6      | VAR7      | VAR8      | VAR9      | VAR10     | VAR11      | VAR12      | VAR13      | VAR14      | VAR15      | VAR16      | VAR17      | VAR18      | VAR19      | VAR20      | VAR21      | VAR22      | VAR23      | VAR24      | VAR25              | VAR26      | VAR27                         | VAR28                         | VAR29                         | VAR30      | VAR31      | VAR32      | VAR33      | VAR34      | VAR35                                     | VAR36      | VAR37      | VAR38      | VAR39      | VAR40      | VAR41      | VAR42      | VAR43      | VAR44      | VAR45      | VAR46      | VAR47      | VAR48      | VAR49      | VAR50      | VAR51      | VAR52      | VAR53      | VAR54      | VAR55      | VAR56      | VAR57      | VAR58      | VAR59                                | VAR60            | VAR61            |%n"
      + "|              |       | KEY     |                | (NUMBER)  | (NUMBER)  | (NUMBER)  | (NUMBER)  | (NUMBER)  | (NUMBER)  | (BOOLEAN) | (BOOLEAN) | (BOOLEAN) | (NUMBER)  | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (TIME)             | (DATE)     | (DATE_TIME)                   | (DATE_TIME)                   | (DATE_TIME)                   | (BYTES)    | (BYTES)    | (BYTES)    | (BYTES)    | (BYTES)    | (NOT_IDENTIFIED : class java.util.Locale) | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (BYTES)    | (BYTES)    | (BYTES)    | (BYTES)    | (BYTES)    | (BYTES)    | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (UUID)                               | (NOT_IDENTIFIED) | (NOT_IDENTIFIED) |%n"
      + "|              |       |         |                | Index : 0 | Index : 1 | Index : 2 | Index : 3 | Index : 4 | Index : 5 | Index : 6 | Index : 7 | Index : 8 | Index : 9 | Index : 10 | Index : 11 | Index : 12 | Index : 13 | Index : 14 | Index : 15 | Index : 16 | Index : 17 | Index : 18 | Index : 19 | Index : 20 | Index : 21 | Index : 22 | Index : 23 | Index : 24         | Index : 25 | Index : 26                    | Index : 27                    | Index : 28                    | Index : 29 | Index : 30 | Index : 31 | Index : 32 | Index : 33 | Index : 34                                | Index : 35 | Index : 36 | Index : 37 | Index : 38 | Index : 39 | Index : 40 | Index : 41 | Index : 42 | Index : 43 | Index : 44 | Index : 45 | Index : 46 | Index : 47 | Index : 48 | Index : 49 | Index : 50 | Index : 51 | Index : 52 | Index : 53 | Index : 54 | Index : 55 | Index : 56 | Index : 57 | Index : 58                           | Index : 59       | Index : 60       |%n"
      + "|--------------|-------|---------|----------------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|--------------------|------------|-------------------------------|-------------------------------|-------------------------------|------------|------------|------------|------------|------------|-------------------------------------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|--------------------------------------|------------------|------------------|%n"
      + "|              |       |         | At start point | 1         | 2         | 3         | 4         | 5         | 6         | true      | false     | true      | 7         | 8          | 9          | 10         | 11         | 12         | 13.13      | 14.14      | 15.15      | 16.16      | 17.17      | 18.18      | 19.19      | 20.2       | 21.21      | 09:01:00.000000000 | 2007-12-23 | 2007-12-23T09:01:00.000000000 | 2007-12-23T09:01:00.000000000 | 2007-12-23T09:01:00.000000000 | ...        | ...        | ...        | ...        | ...        | fr                                        | 22         | 23         | 24         | 25         | 26         | 27         | 28         | 29         | 30         | 31         | ...        | ...        | ...        | ...        | ...        | ...        | 32         | 33         | 34         | 35         | 36         | 37         | 38         | 30b443ae-c0c9-4790-9bec-ce1380808435 | null             | null             |%n"
      + "| MODIFICATION | TEST  | 1       |----------------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|--------------------|------------|-------------------------------|-------------------------------|-------------------------------|------------|------------|------------|------------|------------|-------------------------------------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|--------------------------------------|------------------|------------------|%n"
      + "|              |       |         | At end point   | 1         | 20        | 3         | 4         | 5         | 6         | true      | false     | true      | 7         | 8          | 9          | 10         | 11         | 12         | 13.13      | 14.14      | 15.15      | 16.16      | 17.17      | 18.18      | 19.19      | 20.2       | 21.21      | 09:01:00.000000000 | 2007-12-23 | 2007-12-23T09:01:00.000000000 | 2007-12-23T09:01:00.000000000 | 2007-12-23T09:01:00.000000000 | ...        | ...        | ...        | ...        | ...        | fr                                        | 22         | 23         | 24         | 25         | 26         | 27         | 28         | 29         | 30         | 31         | ...        | ...        | ...        | ...        | ...        | ...        | 32         | 33         | 34         | 35         | 36         | 37         | 38         | 30b443ae-c0c9-4790-9bec-ce1380808435 | null             | null             |%n"
      + "|--------------|-------|---------|----------------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|--------------------|------------|-------------------------------|-------------------------------|-------------------------------|------------|------------|------------|------------|------------|-------------------------------------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|--------------------------------------|------------------|------------------|%n"));
    Assertions.assertThat(byteArrayOutputStream13.toString()).as("display14").isEqualTo(String.format("[Row at end point of Change at index 0 (with primary key : [1]) of Changes on TEST table of 'data source']%n"
      + "|---------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|--------------------|------------|-------------------------------|-------------------------------|-------------------------------|------------|------------|------------|------------|------------|-------------------------------------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|--------------------------------------|------------------|------------------|%n"
      + "|         | *         |           |           |           |           |           |           |           |           |           |            |            |            |            |            |            |            |            |            |            |            |            |            |            |                    |            |                               |                               |                               |            |            |            |            |            |                                           |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |            |                                      |                  |                  |%n"
      + "| PRIMARY | VAR1      | VAR2      | VAR3      | VAR4      | VAR5      | VAR6      | VAR7      | VAR8      | VAR9      | VAR10     | VAR11      | VAR12      | VAR13      | VAR14      | VAR15      | VAR16      | VAR17      | VAR18      | VAR19      | VAR20      | VAR21      | VAR22      | VAR23      | VAR24      | VAR25              | VAR26      | VAR27                         | VAR28                         | VAR29                         | VAR30      | VAR31      | VAR32      | VAR33      | VAR34      | VAR35                                     | VAR36      | VAR37      | VAR38      | VAR39      | VAR40      | VAR41      | VAR42      | VAR43      | VAR44      | VAR45      | VAR46      | VAR47      | VAR48      | VAR49      | VAR50      | VAR51      | VAR52      | VAR53      | VAR54      | VAR55      | VAR56      | VAR57      | VAR58      | VAR59                                | VAR60            | VAR61            |%n"
      + "| KEY     | (NUMBER)  | (NUMBER)  | (NUMBER)  | (NUMBER)  | (NUMBER)  | (NUMBER)  | (BOOLEAN) | (BOOLEAN) | (BOOLEAN) | (NUMBER)  | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (NUMBER)   | (TIME)             | (DATE)     | (DATE_TIME)                   | (DATE_TIME)                   | (DATE_TIME)                   | (BYTES)    | (BYTES)    | (BYTES)    | (BYTES)    | (BYTES)    | (NOT_IDENTIFIED : class java.util.Locale) | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (BYTES)    | (BYTES)    | (BYTES)    | (BYTES)    | (BYTES)    | (BYTES)    | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (TEXT)     | (UUID)                               | (NOT_IDENTIFIED) | (NOT_IDENTIFIED) |%n"
      + "|         | Index : 0 | Index : 1 | Index : 2 | Index : 3 | Index : 4 | Index : 5 | Index : 6 | Index : 7 | Index : 8 | Index : 9 | Index : 10 | Index : 11 | Index : 12 | Index : 13 | Index : 14 | Index : 15 | Index : 16 | Index : 17 | Index : 18 | Index : 19 | Index : 20 | Index : 21 | Index : 22 | Index : 23 | Index : 24         | Index : 25 | Index : 26                    | Index : 27                    | Index : 28                    | Index : 29 | Index : 30 | Index : 31 | Index : 32 | Index : 33 | Index : 34                                | Index : 35 | Index : 36 | Index : 37 | Index : 38 | Index : 39 | Index : 40 | Index : 41 | Index : 42 | Index : 43 | Index : 44 | Index : 45 | Index : 46 | Index : 47 | Index : 48 | Index : 49 | Index : 50 | Index : 51 | Index : 52 | Index : 53 | Index : 54 | Index : 55 | Index : 56 | Index : 57 | Index : 58                           | Index : 59       | Index : 60       |%n"
      + "|---------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|--------------------|------------|-------------------------------|-------------------------------|-------------------------------|------------|------------|------------|------------|------------|-------------------------------------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|--------------------------------------|------------------|------------------|%n"
      + "| 1       | 1         | 20        | 3         | 4         | 5         | 6         | true      | false     | true      | 7         | 8          | 9          | 10         | 11         | 12         | 13.13      | 14.14      | 15.15      | 16.16      | 17.17      | 18.18      | 19.19      | 20.2       | 21.21      | 09:01:00.000000000 | 2007-12-23 | 2007-12-23T09:01:00.000000000 | 2007-12-23T09:01:00.000000000 | 2007-12-23T09:01:00.000000000 | ...        | ...        | ...        | ...        | ...        | fr                                        | 22         | 23         | 24         | 25         | 26         | 27         | 28         | 29         | 30         | 31         | ...        | ...        | ...        | ...        | ...        | ...        | 32         | 33         | 34         | 35         | 36         | 37         | 38         | 30b443ae-c0c9-4790-9bec-ce1380808435 | null             | null             |%n"
      + "|---------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|--------------------|------------|-------------------------------|-------------------------------|-------------------------------|------------|------------|------------|------------|------------|-------------------------------------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|--------------------------------------|------------------|------------------|%n"));
    Assertions.assertThat(byteArrayOutputStream14.toString()).as("display15").isEqualTo(String.format("[Value at index 0 (column name : VAR1) of Row at end point of Change at index 0 (with primary key : [1]) of Changes on TEST table of 'data source']%n"
      + "|----------|%n"
      + "| VAR1     |%n"
      + "| (NUMBER) |%n"
      + "|----------|%n"
      + "| 1        |%n"
      + "|----------|%n"));
    Assertions.assertThat(byteArrayOutputStream15.toString()).as("display16").isEqualTo(String.format("[Column at index 0 (column name : VAR1) of Change at index 0 (with primary key : [1]) of Changes on TEST table of 'data source']%n"
      + "|----------------|----------|%n"
      + "|                | VAR1     |%n"
      + "|                | (NUMBER) |%n"
      + "|----------------|----------|%n"
      + "| At start point | 1        |%n"
      + "|----------------|----------|%n"
      + "| At end point   | 1        |%n"
      + "|----------------|----------|%n"));
    Assertions.assertThat(byteArrayOutputStream16.toString()).as("display17").isEqualTo(String.format("[Value at end point of Column at index 0 (column name : VAR1) of Change at index 0 (with primary key : [1]) of Changes on TEST table of 'data source']%n"
      + "|----------|%n"
      + "| VAR1     |%n"
      + "| (NUMBER) |%n"
      + "|----------|%n"
      + "| 1        |%n"
      + "|----------|%n"));
  }

  @Test
  @NeedReload
  public void should_fail_because_primary_key_is_different() {
    Table table = connection.table("test").build();
    Changes changes = connection.changes().tables(table).build().setStartPointNow();
    Changes changes2 = connection.changes().build().setStartPointNow();
    update();
    changes.setEndPointNow();
    changes2.setEndPointNow();

    try {
      assertThat(changes).change().hasPksNames("var1");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).as("message1").isEqualTo(String.format("[Change at index 0 of Changes on test table of 'data source'] %n"
        + "Expecting :%n"
        + "  [\"var1\"]%n"
        + "to be the name of the columns of the primary keys but was:%n"
        + "  []"));
    }

    try {
      assertThat(changes2).change().hasPksNames("var1");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).as("message2").isEqualTo(String.format("[Change at index 0 (with primary key : [1]) of Changes on TEST table of 'data source'] %n"
        + "Expecting :%n"
        + "  [\"var1\"]%n"
        + "to be the name of the columns of the primary keys but was:%n"
        + "  [\"VAR1\"]"));
    }
  }

  @Test
  @NeedReload
  public void should_fail_because_column_name_is_different() {
    Table table = connection.table("test").build();
    Changes changes = connection.changes().tables(table).build().setStartPointNow();
    Changes changes2 = connection.changes().build().setStartPointNow();
    update();
    changes.setEndPointNow();
    changes2.setEndPointNow();

    try {
      assertThat(table).column().hasColumnName("Var1");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).as("message1").isEqualTo(String.format("[Column at index 0 (column name : VAR1) of test table] %n"
        + "Expecting :%n"
        + "  \"Var1\"%n"
        + "to be the name of the column but was:%n"
        + "  \"VAR1\""));
    }
    try {
      assertThat(table).row().value().hasColumnName("Var1");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).as("message2").isEqualTo(String.format("[Value at index 0 (column name : VAR1) of Row at index 0 of test table] %n"
        + "Expecting :%n"
        + "  \"Var1\"%n"
        + "to be the name of the column but was:%n"
        + "  \"VAR1\""));
    }

    try {
      assertThat(changes).change().column().hasColumnName("Var1");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).as("message3").isEqualTo(String.format("[Column at index 0 (column name : VAR1) of Change at index 0 of Changes on test table of 'data source'] %n"
        + "Expecting :%n"
        + "  \"Var1\"%n"
        + "to be the name of the column but was:%n"
        + "  \"VAR1\""));
    }
    try {
      assertThat(changes).change().rowAtEndPoint().value().hasColumnName("Var1");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).as("message4").isEqualTo(String.format("[Value at index 0 (column name : VAR1) of Row at end point of Change at index 0 of Changes on test table of 'data source'] %n"
        + "Expecting :%n"
        + "  \"Var1\"%n"
        + "to be the name of the column but was:%n"
        + "  \"VAR1\""));
    }

    try {
      assertThat(changes2).change().column().hasColumnName("Var1");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).as("message5").isEqualTo(String.format("[Column at index 0 (column name : VAR1) of Change at index 0 (with primary key : [1]) of Changes on TEST table of 'data source'] %n"
        + "Expecting :%n"
        + "  \"Var1\"%n"
        + "to be the name of the column but was:%n"
        + "  \"VAR1\""));
    }
    try {
      assertThat(changes2).change().rowAtEndPoint().value().hasColumnName("Var1");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).as("message6").isEqualTo(String.format("[Value at index 0 (column name : VAR1) of Row at end point of Change at index 0 (with primary key : [1]) of Changes on TEST table of 'data source'] %n"
        + "Expecting :%n"
        + "  \"Var1\"%n"
        + "to be the name of the column but was:%n"
        + "  \"VAR1\""));
    }
  }

  @Test
  @NeedReload
  public void should_fail_because_column_name_is_wrong_to_navigate() {
    Table table = connection.table("test").build();
    Changes changes = connection.changes().tables(table).build().setStartPointNow();
    Changes changes2 = connection.changes().build().setStartPointNow();
    update();
    changes.setEndPointNow();
    changes2.setEndPointNow();

    try {
      assertThat(table).column("Var1");
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("Column <Var1> does not exist%n"
        + "in <[VAR1, VAR2, VAR3, VAR4, VAR5, VAR6, VAR7, VAR8, VAR9, VAR10, VAR11, VAR12, VAR13, VAR14, VAR15, VAR16, VAR17, VAR18, VAR19, VAR20, VAR21, VAR22, VAR23, VAR24, VAR25, VAR26, VAR27, VAR28, VAR29, VAR30, VAR31, VAR32, VAR33, VAR34, VAR35, VAR36, VAR37, VAR38, VAR39, VAR40, VAR41, VAR42, VAR43, VAR44, VAR45, VAR46, VAR47, VAR48, VAR49, VAR50, VAR51, VAR52, VAR53, VAR54, VAR55, VAR56, VAR57, VAR58, VAR59, VAR60, VAR61]>%n"
        + "with comparison STRICT - Strictly compare the case"));
    }
    try {
      assertThat(table).row().value("Var1");
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("Column <Var1> does not exist%n"
        + "in <[VAR1, VAR2, VAR3, VAR4, VAR5, VAR6, VAR7, VAR8, VAR9, VAR10, VAR11, VAR12, VAR13, VAR14, VAR15, VAR16, VAR17, VAR18, VAR19, VAR20, VAR21, VAR22, VAR23, VAR24, VAR25, VAR26, VAR27, VAR28, VAR29, VAR30, VAR31, VAR32, VAR33, VAR34, VAR35, VAR36, VAR37, VAR38, VAR39, VAR40, VAR41, VAR42, VAR43, VAR44, VAR45, VAR46, VAR47, VAR48, VAR49, VAR50, VAR51, VAR52, VAR53, VAR54, VAR55, VAR56, VAR57, VAR58, VAR59, VAR60, VAR61]>%n"
        + "with comparison STRICT - Strictly compare the case"));
    }

    try {
      assertThat(changes).change().column("Var1");
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("Column <Var1> does not exist%n"
        + "in <[VAR1, VAR2, VAR3, VAR4, VAR5, VAR6, VAR7, VAR8, VAR9, VAR10, VAR11, VAR12, VAR13, VAR14, VAR15, VAR16, VAR17, VAR18, VAR19, VAR20, VAR21, VAR22, VAR23, VAR24, VAR25, VAR26, VAR27, VAR28, VAR29, VAR30, VAR31, VAR32, VAR33, VAR34, VAR35, VAR36, VAR37, VAR38, VAR39, VAR40, VAR41, VAR42, VAR43, VAR44, VAR45, VAR46, VAR47, VAR48, VAR49, VAR50, VAR51, VAR52, VAR53, VAR54, VAR55, VAR56, VAR57, VAR58, VAR59, VAR60, VAR61]>%n"
        + "with comparison STRICT - Strictly compare the case"));
    }
    try {
      assertThat(changes).change().rowAtEndPoint().value("Var1");
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("Column <Var1> does not exist%n"
        + "in <[VAR1, VAR2, VAR3, VAR4, VAR5, VAR6, VAR7, VAR8, VAR9, VAR10, VAR11, VAR12, VAR13, VAR14, VAR15, VAR16, VAR17, VAR18, VAR19, VAR20, VAR21, VAR22, VAR23, VAR24, VAR25, VAR26, VAR27, VAR28, VAR29, VAR30, VAR31, VAR32, VAR33, VAR34, VAR35, VAR36, VAR37, VAR38, VAR39, VAR40, VAR41, VAR42, VAR43, VAR44, VAR45, VAR46, VAR47, VAR48, VAR49, VAR50, VAR51, VAR52, VAR53, VAR54, VAR55, VAR56, VAR57, VAR58, VAR59, VAR60, VAR61]>%n"
        + "with comparison STRICT - Strictly compare the case"));
    }

    try {
      assertThat(changes2).change().column("Var1");
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("Column <Var1> does not exist%n"
        + "in <[VAR1, VAR2, VAR3, VAR4, VAR5, VAR6, VAR7, VAR8, VAR9, VAR10, VAR11, VAR12, VAR13, VAR14, VAR15, VAR16, VAR17, VAR18, VAR19, VAR20, VAR21, VAR22, VAR23, VAR24, VAR25, VAR26, VAR27, VAR28, VAR29, VAR30, VAR31, VAR32, VAR33, VAR34, VAR35, VAR36, VAR37, VAR38, VAR39, VAR40, VAR41, VAR42, VAR43, VAR44, VAR45, VAR46, VAR47, VAR48, VAR49, VAR50, VAR51, VAR52, VAR53, VAR54, VAR55, VAR56, VAR57, VAR58, VAR59, VAR60, VAR61]>%n"
        + "with comparison STRICT - Strictly compare the case"));
    }
    try {
      assertThat(changes2).change().rowAtEndPoint().value("Var1");
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("Column <Var1> does not exist%n"
        + "in <[VAR1, VAR2, VAR3, VAR4, VAR5, VAR6, VAR7, VAR8, VAR9, VAR10, VAR11, VAR12, VAR13, VAR14, VAR15, VAR16, VAR17, VAR18, VAR19, VAR20, VAR21, VAR22, VAR23, VAR24, VAR25, VAR26, VAR27, VAR28, VAR29, VAR30, VAR31, VAR32, VAR33, VAR34, VAR35, VAR36, VAR37, VAR38, VAR39, VAR40, VAR41, VAR42, VAR43, VAR44, VAR45, VAR46, VAR47, VAR48, VAR49, VAR50, VAR51, VAR52, VAR53, VAR54, VAR55, VAR56, VAR57, VAR58, VAR59, VAR60, VAR61]>%n"
        + "with comparison STRICT - Strictly compare the case"));
    }
  }

  @Test
  @NeedReload
  public void should_fail_because_table_name_is_different() {
    Table table = connection.table("test").build();
    Changes changes = connection.changes().tables(table).build().setStartPointNow();
    Changes changes2 = connection.changes().build().setStartPointNow();
    update();
    changes.setEndPointNow();
    changes2.setEndPointNow();

    try {
      assertThat(changes).change().isOnTable("teSt");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).as("message1").isEqualTo(String.format("[Change at index 0 of Changes on test table of 'data source'] %n"
        + "Expecting to be on the table:%n"
        + "  <\"teSt\">%n"
        + "but was on the table:%n"
        + "  <\"test\">"));
    }
    try {
      assertThat(changes2).change().isOnTable("teSt");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).as("message2").isEqualTo(String.format("[Change at index 0 (with primary key : [1]) of Changes on TEST table of 'data source'] %n"
        + "Expecting to be on the table:%n"
        + "  <\"teSt\">%n"
        + "but was on the table:%n"
        + "  <\"TEST\">"));
    }
  }

  @Test
  @NeedReload
  public void should_fail_because_table_name_is_wrong_to_navigate() {
    Table table = connection.table("test").build();
    Changes changes = connection.changes().tables(table).build().setStartPointNow();
    Changes changes2 = connection.changes().build().setStartPointNow();
    update();
    changes.setEndPointNow();
    changes2.setEndPointNow();

    try {
      assertThat(changes).changeOnTable("teSt");
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("Index 0 out of the limits [0, 0["));
    }
    try {
      assertThat(changes).changeOnTable("teSt", 0);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("Index 0 out of the limits [0, 0["));
    }
    try {
      assertThat(changes).changeOnTableWithPks("teSt", 1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("No change found for table teSt and primary keys [1]"));
    }

    try {
      assertThat(changes2).changeOnTable("teSt");
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("Index 0 out of the limits [0, 0["));
    }
    try {
      assertThat(changes2).changeOnTable("teSt", 0);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("Index 0 out of the limits [0, 0["));
    }
    try {
      assertThat(changes2).changeOnTableWithPks("teSt", 1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("No change found for table teSt and primary keys [1]"));
    }
  }

  @Test
  @NeedReload
  public void test_ColumnName_hasColumnName() {
    Table table = connection.table("test").build();
    Changes changes = connection.changes().tables(table).build().setStartPointNow();
    Changes changes2 = connection.changes().build().setStartPointNow();
    update();
    changes.setEndPointNow();
    changes2.setEndPointNow();

    assertThat(table).column().hasColumnName("VAR1");

    assertThat(changes).change().column().hasColumnName("VAR1");
    assertThat(changes2).change().column().hasColumnName("VAR1");
  }

  @Test
  @NeedReload
  public void test_ColumnClass_isOfClass() {
    Table table = connection.table("test").build();
    Changes changes = connection.changes().tables(table).build().setStartPointNow();
    Changes changes2 = connection.changes().build().setStartPointNow();
    update();
    changes.setEndPointNow();
    changes2.setEndPointNow();

    assertThat(table).column("VAR1").isOfClass(Long.class, false);

    assertThat(changes).change().column("VAR1").isOfClass(Long.class, true);
    assertThat(changes2).change().column("VAR1").isOfClass(Long.class, true);
  }

  @Test
  @NeedReload
  public void test_getTableLetterCase() {
    Table table = connection.table("test").build();

    Request request = connection.request("select * from test").build();

    Changes changes = connection.changes().tables(table).build().setStartPointNow();
    update();
    changes.setEndPointNow();
    Change change = changes.getChangesList().get(0);


    Assertions.assertThat(table.getTableLetterCase().getConversionName()).isSameAs(CaseConversions.NO.getConversionName());

    Assertions.assertThat(request.getTableLetterCase().getConversionName()).isSameAs(CaseConversions.NO.getConversionName());

    Assertions.assertThat(changes.getTableLetterCase().getConversionName()).isSameAs(CaseConversions.NO.getConversionName());
    Assertions.assertThat(change.getTableLetterCase().getConversionName()).isSameAs(CaseConversions.NO.getConversionName());


    Assertions.assertThat(table.getTableLetterCase().getComparisonName()).isSameAs(CaseComparisons.STRICT.getComparisonName());

    Assertions.assertThat(request.getTableLetterCase().getComparisonName()).isSameAs(CaseComparisons.STRICT.getComparisonName());

    Assertions.assertThat(changes.getTableLetterCase().getComparisonName()).isSameAs(CaseComparisons.STRICT.getComparisonName());
    Assertions.assertThat(change.getTableLetterCase().getComparisonName()).isSameAs(CaseComparisons.STRICT.getComparisonName());
  }

  @Test
  @NeedReload
  public void test_getColumnLetterCase() {
    Table table = connection.table("test").build();
    Row tableRow = table.getRow(0);
    Column tableColumn = table.getColumn(0);
    Value tableRowValue = tableRow.getColumnValue(0);
    Value tableColumnValue = tableColumn.getRowValue(0);

    Request request = connection.request("select * from test").build();
    Row requestRow = request.getRow(0);
    Column requestColumn = request.getColumn(0);
    Value requestRowValue = requestRow.getColumnValue(0);
    Value requestColumnValue = requestColumn.getRowValue(0);

    Changes changes = connection.changes().tables(table).build().setStartPointNow();
    update();
    changes.setEndPointNow();
    Change change = changes.getChangesList().get(0);
    Row rowAtStartPoint = changes.getChangesOfType(ChangeType.DELETION).getChangesList().get(0).getRowAtStartPoint();
    Row rowAtEndPoint = change.getRowAtEndPoint();
    Value valueAtStartPoint = rowAtStartPoint.getColumnValue(0);
    Value valueAtEndPoint = rowAtEndPoint.getColumnValue(0);

    Assertions.assertThat(table.getColumnLetterCase().getConversionName()).isSameAs(CaseConversions.NO.getConversionName());
    Assertions.assertThat(tableRow.getColumnLetterCase().getConversionName()).isSameAs(CaseConversions.NO.getConversionName());
    Assertions.assertThat(tableColumn.getColumnLetterCase().getConversionName()).isSameAs(CaseConversions.NO.getConversionName());
    Assertions.assertThat(tableRowValue.getColumnLetterCase().getConversionName()).isSameAs(CaseConversions.NO.getConversionName());
    Assertions.assertThat(tableColumnValue.getColumnLetterCase().getConversionName()).isSameAs(CaseConversions.NO.getConversionName());

    Assertions.assertThat(request.getColumnLetterCase().getConversionName()).isSameAs(CaseConversions.NO.getConversionName());
    Assertions.assertThat(requestRow.getColumnLetterCase().getConversionName()).isSameAs(CaseConversions.NO.getConversionName());
    Assertions.assertThat(requestColumn.getColumnLetterCase().getConversionName()).isSameAs(CaseConversions.NO.getConversionName());
    Assertions.assertThat(requestRowValue.getColumnLetterCase().getConversionName()).isSameAs(CaseConversions.NO.getConversionName());
    Assertions.assertThat(requestColumnValue.getColumnLetterCase().getConversionName()).isSameAs(CaseConversions.NO.getConversionName());

    Assertions.assertThat(changes.getColumnLetterCase().getConversionName()).isSameAs(CaseConversions.NO.getConversionName());
    Assertions.assertThat(change.getColumnLetterCase().getConversionName()).isSameAs(CaseConversions.NO.getConversionName());
    Assertions.assertThat(rowAtStartPoint.getColumnLetterCase().getConversionName()).isSameAs(CaseConversions.NO.getConversionName());
    Assertions.assertThat(rowAtEndPoint.getColumnLetterCase().getConversionName()).isSameAs(CaseConversions.NO.getConversionName());
    Assertions.assertThat(valueAtStartPoint.getColumnLetterCase().getConversionName()).isSameAs(CaseConversions.NO.getConversionName());
    Assertions.assertThat(valueAtEndPoint.getColumnLetterCase().getConversionName()).isSameAs(CaseConversions.NO.getConversionName());

    Assertions.assertThat(table.getColumnLetterCase().getComparisonName()).isSameAs(CaseComparisons.STRICT.getComparisonName());
    Assertions.assertThat(tableRow.getColumnLetterCase().getComparisonName()).isSameAs(CaseComparisons.STRICT.getComparisonName());
    Assertions.assertThat(tableColumn.getColumnLetterCase().getComparisonName()).isSameAs(CaseComparisons.STRICT.getComparisonName());
    Assertions.assertThat(tableRowValue.getColumnLetterCase().getComparisonName()).isSameAs(CaseComparisons.STRICT.getComparisonName());
    Assertions.assertThat(tableColumnValue.getColumnLetterCase().getComparisonName()).isSameAs(CaseComparisons.STRICT.getComparisonName());

    Assertions.assertThat(request.getColumnLetterCase().getComparisonName()).isSameAs(CaseComparisons.STRICT.getComparisonName());
    Assertions.assertThat(requestRow.getColumnLetterCase().getComparisonName()).isSameAs(CaseComparisons.STRICT.getComparisonName());
    Assertions.assertThat(requestColumn.getColumnLetterCase().getComparisonName()).isSameAs(CaseComparisons.STRICT.getComparisonName());
    Assertions.assertThat(requestRowValue.getColumnLetterCase().getComparisonName()).isSameAs(CaseComparisons.STRICT.getComparisonName());
    Assertions.assertThat(requestColumnValue.getColumnLetterCase().getComparisonName()).isSameAs(CaseComparisons.STRICT.getComparisonName());

    Assertions.assertThat(changes.getColumnLetterCase().getComparisonName()).isSameAs(CaseComparisons.STRICT.getComparisonName());
    Assertions.assertThat(change.getColumnLetterCase().getComparisonName()).isSameAs(CaseComparisons.STRICT.getComparisonName());
    Assertions.assertThat(rowAtStartPoint.getColumnLetterCase().getComparisonName()).isSameAs(CaseComparisons.STRICT.getComparisonName());
    Assertions.assertThat(rowAtEndPoint.getColumnLetterCase().getComparisonName()).isSameAs(CaseComparisons.STRICT.getComparisonName());
    Assertions.assertThat(valueAtStartPoint.getColumnLetterCase().getComparisonName()).isSameAs(CaseComparisons.STRICT.getComparisonName());
    Assertions.assertThat(valueAtEndPoint.getColumnLetterCase().getComparisonName()).isSameAs(CaseComparisons.STRICT.getComparisonName());
  }

  @Test
  @NeedReload
  public void test_getPrimaryKeyLetterCase() {
    Table table = connection.table("test").build();
    Row tableRow = table.getRow(0);

    Request request = connection.request("select * from test").build();
    Row requestRow = request.getRow(0);

    Changes changes = connection.changes().tables(table).build().setStartPointNow();
    update();
    changes.setEndPointNow();
    Change change = changes.getChangesList().get(0);
    Row rowAtStartPoint = changes.getChangesOfType(ChangeType.DELETION).getChangesList().get(0).getRowAtStartPoint();
    Row rowAtEndPoint = change.getRowAtEndPoint();

    Assertions.assertThat(table.getPrimaryKeyLetterCase().getConversionName()).isSameAs(CaseConversions.NO.getConversionName());
    Assertions.assertThat(tableRow.getPrimaryKeyLetterCase().getConversionName()).isSameAs(CaseConversions.NO.getConversionName());

    Assertions.assertThat(request.getPrimaryKeyLetterCase().getConversionName()).isSameAs(CaseConversions.NO.getConversionName());
    Assertions.assertThat(requestRow.getPrimaryKeyLetterCase().getConversionName()).isSameAs(CaseConversions.NO.getConversionName());

    Assertions.assertThat(changes.getPrimaryKeyLetterCase().getConversionName()).isSameAs(CaseConversions.NO.getConversionName());
    Assertions.assertThat(change.getPrimaryKeyLetterCase().getConversionName()).isSameAs(CaseConversions.NO.getConversionName());
    Assertions.assertThat(rowAtStartPoint.getPrimaryKeyLetterCase().getConversionName()).isSameAs(CaseConversions.NO.getConversionName());
    Assertions.assertThat(rowAtEndPoint.getPrimaryKeyLetterCase().getConversionName()).isSameAs(CaseConversions.NO.getConversionName());

    Assertions.assertThat(table.getPrimaryKeyLetterCase().getComparisonName()).isSameAs(CaseComparisons.STRICT.getComparisonName());
    Assertions.assertThat(tableRow.getPrimaryKeyLetterCase().getComparisonName()).isSameAs(CaseComparisons.STRICT.getComparisonName());

    Assertions.assertThat(request.getPrimaryKeyLetterCase().getComparisonName()).isSameAs(CaseComparisons.STRICT.getComparisonName());
    Assertions.assertThat(requestRow.getPrimaryKeyLetterCase().getComparisonName()).isSameAs(CaseComparisons.STRICT.getComparisonName());

    Assertions.assertThat(changes.getPrimaryKeyLetterCase().getComparisonName()).isSameAs(CaseComparisons.STRICT.getComparisonName());
    Assertions.assertThat(change.getPrimaryKeyLetterCase().getComparisonName()).isSameAs(CaseComparisons.STRICT.getComparisonName());
    Assertions.assertThat(rowAtStartPoint.getPrimaryKeyLetterCase().getComparisonName()).isSameAs(CaseComparisons.STRICT.getComparisonName());
    Assertions.assertThat(rowAtEndPoint.getPrimaryKeyLetterCase().getComparisonName()).isSameAs(CaseComparisons.STRICT.getComparisonName());
  }
}
