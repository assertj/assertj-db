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
public class ReservedH2Database_JdbcUrl_UIUIUI_Test extends AbstractReservedH2Test {

  private AssertDbConnection connection;

  @Before
  public void init() {
    connection = jdbcConnectionUIUIUI;
  }

  @Test
  @NeedReload
  public void test_PrimaryKey_hasPksNames() {
    Table table1 = connection.table("group").delimiters('`', '`').build();
    Table table2 = connection.table("two words").delimiters('`', '`').build();
    Changes changes1 = connection.changes().tables(table1).build().setStartPointNow();
    Changes changes2 = connection.changes().tables(table2).build().setStartPointNow();
    update();
    changes1.setEndPointNow();
    changes2.setEndPointNow();

    assertThat(changes1).change().hasPksNames("read")
    ;

    assertThat(changes2).change().hasPksNames("primary key")
    ;
  }

  @Test
  @NeedReload
  public void test_ColumnName_hasColumnName() {
    Table table1 = connection.table("group").delimiters('`', '`').build();
    Table table2 = connection.table("two words").delimiters('`', '`').build();
    Changes changes1 = connection.changes().tables(table1).build().setStartPointNow();
    Changes changes2 = connection.changes().tables(table2).build().setStartPointNow();
    update();
    changes1.setEndPointNow();
    changes2.setEndPointNow();

    assertThat(table1)
      .column().hasColumnName("read")
      .column().hasColumnName("by")
      .column().hasColumnName("select")
      .column().hasColumnName("from")
      .column().hasColumnName("where")
      .column().hasColumnName("order")
    ;

    assertThat(changes1).change()
      .column().hasColumnName("read")
      .column().hasColumnName("by")
      .column().hasColumnName("select")
      .column().hasColumnName("from")
      .column().hasColumnName("where")
      .column().hasColumnName("order")
    ;

    assertThat(table2)
      .column().hasColumnName("primary key")
      .column().hasColumnName("column name")
      .column().hasColumnName("test%test")
    ;

    assertThat(changes2).change()
      .column().hasColumnName("primary key")
      .column().hasColumnName("column name")
      .column().hasColumnName("test%test")
    ;
  }

  @Test
  @NeedReload
  public void test_ColumnName_hasColumnName_with_columns_to_check() {
    Table table1 = connection.table("group")
      .delimiters('`', '`')
      .columnsToCheck(new String[]{
        "read", "by", "select", "from"
      }).build();
    Table table2 = connection.table("two words")
      .delimiters('`', '`')
      .columnsToCheck(new String[]{
        "primary key", "column name", "test%test"
      }).build();
    Changes changes1 = connection.changes().tables(table1).build().setStartPointNow();
    Changes changes2 = connection.changes().tables(table2).build().setStartPointNow();
    update();
    changes1.setEndPointNow();
    changes2.setEndPointNow();

    assertThat(table1)
      .column().hasColumnName("read")
      .column().hasColumnName("by")
      .column().hasColumnName("select")
      .column().hasColumnName("from")
    ;

    assertThat(changes1).change()
      .column().hasColumnName("read")
      .column().hasColumnName("by")
      .column().hasColumnName("select")
      .column().hasColumnName("from")
    ;

    assertThat(table2)
      .column().hasColumnName("primary key")
      .column().hasColumnName("column name")
      .column().hasColumnName("test%test")
    ;

    assertThat(changes2).change()
      .column().hasColumnName("primary key")
      .column().hasColumnName("column name")
      .column().hasColumnName("test%test")
    ;
  }

  @Test
  @NeedReload
  public void test_ColumnName_hasColumnName_with_columns_to_exclude() {
    Table table1 = connection.table("group")
      .delimiters('`', '`')
      .columnsToExclude(new String[]{
        "read", "by", "from"
      }).build();
    Table table2 = connection.table("two words")
      .delimiters('`', '`')
      .columnsToExclude(new String[]{
        "column name"
      }).build();
    Changes changes1 = connection.changes().tables(table1).build().setStartPointNow();
    Changes changes2 = connection.changes().tables(table2).build().setStartPointNow();
    update();
    changes1.setEndPointNow();
    changes2.setEndPointNow();

    assertThat(table1)
      .column().hasColumnName("select")
      .column().hasColumnName("where")
      .column().hasColumnName("order")
    ;

    assertThat(changes1).change()
      .column().hasColumnName("select")
      .column().hasColumnName("where")
      .column().hasColumnName("order")
    ;

    assertThat(table2)
      .column().hasColumnName("primary key")
      .column().hasColumnName("test%test")
    ;

    assertThat(changes2).change()
      .column().hasColumnName("primary key")
      .column().hasColumnName("test%test")
    ;
  }

  @Test
  @NeedReload
  public void test_ColumnName_hasColumnName_with_order() {
    Table table1 = connection.table("group")
      .delimiters('`', '`')
      .columnsToOrder(new Order[]{
        Order.asc("where")
      }).build();
    Table table2 = connection.table("two words")
      .delimiters('`', '`')
      .columnsToOrder(new Order[]{
        Order.asc("primary key")
      }).build();
    Changes changes1 = connection.changes().tables(table1).build().setStartPointNow();
    Changes changes2 = connection.changes().tables(table2).build().setStartPointNow();
    update();
    changes1.setEndPointNow();
    changes2.setEndPointNow();

    assertThat(table1)
      .column().hasColumnName("read")
      .column().hasColumnName("by")
      .column().hasColumnName("select")
      .column().hasColumnName("from")
      .column().hasColumnName("where")
      .column().hasColumnName("order")
    ;

    assertThat(changes1).change()
      .column().hasColumnName("read")
      .column().hasColumnName("by")
      .column().hasColumnName("select")
      .column().hasColumnName("from")
      .column().hasColumnName("where")
      .column().hasColumnName("order")
    ;

    assertThat(table2)
      .column().hasColumnName("primary key")
      .column().hasColumnName("column name")
      .column().hasColumnName("test%test")
    ;

    assertThat(changes2).change()
      .column().hasColumnName("primary key")
      .column().hasColumnName("column name")
      .column().hasColumnName("test%test")
    ;
  }
}
