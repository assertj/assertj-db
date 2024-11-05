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
import org.assertj.db.type.Changes;
import org.assertj.db.type.ConnectionProvider;
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
public class ReservedH2Database_DataSource_NSNSNS_Test extends AbstractReservedH2Test {

  private ConnectionProvider connectionProvider;

  @Before
  public void init() {
    connectionProvider = dsConnectionNSNSNS;
  }

  @Test
  @NeedReload
  public void test_PrimaryKey_hasPksNames() {
    Table table1 = new Table(connectionProvider, "GROUP", '`', '`');
    Table table2 = new Table(connectionProvider, "TWO WORDS", '`', '`');
    Changes changes1 = new Changes(table1).setStartPointNow();
    Changes changes2 = new Changes(table2).setStartPointNow();
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
    Table table1 = new Table(connectionProvider, "GROUP", '`', '`');
    Table table2 = new Table(connectionProvider, "TWO WORDS", '`', '`');
    Changes changes1 = new Changes(table1).setStartPointNow();
    Changes changes2 = new Changes(table2).setStartPointNow();
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
    Table table1 = new Table(connectionProvider, "GROUP", '`', '`')
      .setColumnsToCheck(new String[]{
        "READ", "BY", "SELECT", "FROM"
      });
    Table table2 = new Table(connectionProvider, "TWO WORDS", '`', '`')
      .setColumnsToCheck(new String[]{
        "PRIMARY KEY", "COLUMN NAME", "TEST%TEST"
      });
    Changes changes1 = new Changes(table1).setStartPointNow();
    Changes changes2 = new Changes(table2).setStartPointNow();
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
    Table table1 = new Table(connectionProvider, "GROUP", '`', '`')
      .setColumnsToExclude(new String[]{
        "READ", "BY", "FROM"
      });
    Table table2 = new Table(connectionProvider, "TWO WORDS", '`', '`')
      .setColumnsToExclude(new String[]{
        "COLUMN NAME"
      });
    Changes changes1 = new Changes(table1).setStartPointNow();
    Changes changes2 = new Changes(table2).setStartPointNow();
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
    Table table1 = new Table(connectionProvider, "GROUP", '`', '`')
      .setColumnsToOrder(new Order[]{
        Order.asc("WHERE")
      });
    Table table2 = new Table(connectionProvider, "TWO WORDS", '`', '`')
      .setColumnsToOrder(new Order[]{
        Order.asc("PRIMARY KEY")
      });
    Changes changes1 = new Changes(table1).setStartPointNow();
    Changes changes2 = new Changes(table2).setStartPointNow();
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
