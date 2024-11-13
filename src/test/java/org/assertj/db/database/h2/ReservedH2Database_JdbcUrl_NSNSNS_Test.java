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
package org.assertj.db.database.h2;

import static org.assertj.db.api.Assertions.assertThat;

import org.assertj.db.common.NeedReload;
import org.assertj.db.type.AssertDbConnection;
import org.assertj.db.type.Changes;
import org.assertj.db.type.Table;
import org.assertj.db.type.Table.Order;
import org.junit.Before;
import org.junit.Test;

/**
 * Test on the H2 database with reserved names in SQL structure.
 *
 * @author Régis Pouiller
 * @author Julien Roy
 */
public class ReservedH2Database_JdbcUrl_NSNSNS_Test extends AbstractReservedH2Test {

  private AssertDbConnection connection;

  @Before
  public void init() {
    connection = jdbcConnectionNSNSNS;
  }

  @Test
  @NeedReload
  public void test_PrimaryKey_hasPksNames() {
    Table table1 = connection.table("GROUP").delimiters('`', '`').build();
    Table table2 = connection.table("TWO WORDS").delimiters('`', '`').build();
    Changes changes1 = connection.changes().tables(table1).build().setStartPointNow();
    Changes changes2 = connection.changes().tables(table2).build().setStartPointNow();
    update();
    changes1.setEndPointNow();
    changes2.setEndPointNow();

    assertThat(changes1).change().hasPksNames("READ")
    ;
    assertThat(changes2).change().hasPksNames("PRIMARY KEY")
    ;
  }

  @Test
  @NeedReload
  public void test_ColumnName_hasColumnName() {
    Table table1 = connection.table("GROUP").delimiters('`', '`').build();
    Table table2 = connection.table("TWO WORDS").delimiters('`', '`').build();
    Changes changes1 = connection.changes().tables(table1).build().setStartPointNow();
    Changes changes2 = connection.changes().tables(table2).build().setStartPointNow();
    update();
    changes1.setEndPointNow();
    changes2.setEndPointNow();

    assertThat(table1)
      .column().hasColumnName("READ")
      .column().hasColumnName("BY")
      .column().hasColumnName("SELECT")
      .column().hasColumnName("FROM")
      .column().hasColumnName("WHERE")
      .column().hasColumnName("ORDER")
    ;

    assertThat(changes1).change()
      .column().hasColumnName("READ")
      .column().hasColumnName("BY")
      .column().hasColumnName("SELECT")
      .column().hasColumnName("FROM")
      .column().hasColumnName("WHERE")
      .column().hasColumnName("ORDER")
    ;

    assertThat(table2)
      .column().hasColumnName("PRIMARY KEY")
      .column().hasColumnName("COLUMN NAME")
      .column().hasColumnName("TEST%TEST")
    ;

    assertThat(changes2).change()
      .column().hasColumnName("PRIMARY KEY")
      .column().hasColumnName("COLUMN NAME")
      .column().hasColumnName("TEST%TEST")
    ;
  }

  @Test
  @NeedReload
  public void test_ColumnName_hasColumnName_with_columns_to_check() {
    Table table1 = connection.table("GROUP")
      .delimiters('`', '`')
      .columnsToCheck(new String[]{
        "READ", "BY", "SELECT", "FROM"
      }).build();
    Table table2 = connection.table("TWO WORDS")
      .delimiters('`', '`')
      .columnsToCheck(new String[]{
        "PRIMARY KEY", "COLUMN NAME", "TEST%TEST"
      }).build();
    Changes changes1 = connection.changes().tables(table1).build().setStartPointNow();
    Changes changes2 = connection.changes().tables(table2).build().setStartPointNow();
    update();
    changes1.setEndPointNow();
    changes2.setEndPointNow();

    assertThat(table1)
      .column().hasColumnName("READ")
      .column().hasColumnName("BY")
      .column().hasColumnName("SELECT")
      .column().hasColumnName("FROM")
    ;

    assertThat(changes1).change()
      .column().hasColumnName("READ")
      .column().hasColumnName("BY")
      .column().hasColumnName("SELECT")
      .column().hasColumnName("FROM")
    ;

    assertThat(table2)
      .column().hasColumnName("PRIMARY KEY")
      .column().hasColumnName("COLUMN NAME")
      .column().hasColumnName("TEST%TEST")
    ;

    assertThat(changes2).change()
      .column().hasColumnName("PRIMARY KEY")
      .column().hasColumnName("COLUMN NAME")
      .column().hasColumnName("TEST%TEST")
    ;
  }

  @Test
  @NeedReload
  public void test_ColumnName_hasColumnName_with_columns_to_exclude() {
    Table table1 = connection.table("GROUP")
      .delimiters('`', '`')
      .columnsToExclude(new String[]{
        "READ", "BY", "FROM"
      }).build();
    Table table2 = connection.table("TWO WORDS")
      .delimiters('`', '`')
      .columnsToExclude(new String[]{
        "COLUMN NAME"
      }).build();
    Changes changes1 = connection.changes().tables(table1).build().setStartPointNow();
    Changes changes2 = connection.changes().tables(table2).build().setStartPointNow();
    update();
    changes1.setEndPointNow();
    changes2.setEndPointNow();

    assertThat(table1)
      .column().hasColumnName("SELECT")
      .column().hasColumnName("WHERE")
      .column().hasColumnName("ORDER")
    ;

    assertThat(changes1).change()
      .column().hasColumnName("SELECT")
      .column().hasColumnName("WHERE")
      .column().hasColumnName("ORDER")
    ;

    assertThat(table2)
      .column().hasColumnName("PRIMARY KEY")
      .column().hasColumnName("TEST%TEST")
    ;

    assertThat(changes2).change()
      .column().hasColumnName("PRIMARY KEY")
      .column().hasColumnName("TEST%TEST")
    ;
  }

  @Test
  @NeedReload
  public void test_ColumnName_hasColumnName_with_order() {
    Table table1 = connection.table("GROUP")
      .delimiters('`', '`')
      .columnsToOrder(new Order[]{
        Order.asc("WHERE")
      }).build();
    Table table2 = connection.table("TWO WORDS")
      .delimiters('`', '`')
      .columnsToOrder(new Order[]{
        Order.asc("PRIMARY KEY")
      }).build();
    Changes changes1 = connection.changes().tables(table1).build().setStartPointNow();
    Changes changes2 = connection.changes().tables(table2).build().setStartPointNow();
    update();
    changes1.setEndPointNow();
    changes2.setEndPointNow();

    assertThat(table1)
      .column().hasColumnName("READ")
      .column().hasColumnName("BY")
      .column().hasColumnName("SELECT")
      .column().hasColumnName("FROM")
      .column().hasColumnName("WHERE")
      .column().hasColumnName("ORDER")
    ;

    assertThat(changes1).change()
      .column().hasColumnName("READ")
      .column().hasColumnName("BY")
      .column().hasColumnName("SELECT")
      .column().hasColumnName("FROM")
      .column().hasColumnName("WHERE")
      .column().hasColumnName("ORDER")
    ;

    assertThat(table2)
      .column().hasColumnName("PRIMARY KEY")
      .column().hasColumnName("COLUMN NAME")
      .column().hasColumnName("TEST%TEST")
    ;

    assertThat(changes2).change()
      .column().hasColumnName("PRIMARY KEY")
      .column().hasColumnName("COLUMN NAME")
      .column().hasColumnName("TEST%TEST")
    ;
  }

}
