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
package org.assertj.db.database.h2;

import static org.assertj.db.api.Assertions.assertThat;

import javax.sql.DataSource;

import org.assertj.db.common.NeedReload;
import org.assertj.db.type.Changes;
import org.assertj.db.type.Table;
import org.assertj.db.type.Table.Order;
import org.junit.Before;
import org.junit.Test;

/**
 * Test on the H2 database with reserved names in SQL structure.
 *
 * @author Régis Pouiller
 */
public class ReservedH2Database_Source_NSNSNS_Test extends AbstractReservedH2Test {

  private DataSource dataSource;

  @Before
  public void init() {
    dataSource = dataSourceNSNSNS;
  }

  @Test
  @NeedReload
  public void test_PrimaryKey_hasPksNames() {
    Table table = new Table(dataSource, "GROUP", '`', '`');
    Changes changes = new Changes(table).setStartPointNow();
    update();
    changes.setEndPointNow();

    assertThat(changes).change().hasPksNames("READ")
    ;
  }

  @Test
  @NeedReload
  public void test_ColumnName_hasColumnName() {
    Table table = new Table(dataSource, "GROUP", '`', '`');
    Changes changes = new Changes(table).setStartPointNow();
    update();
    changes.setEndPointNow();

    assertThat(table)
            .column().hasColumnName("READ")
            .column().hasColumnName("BY")
            .column().hasColumnName("SELECT")
            .column().hasColumnName("FROM")
            .column().hasColumnName("WHERE")
            .column().hasColumnName("ORDER")
    ;

    assertThat(changes).change()
            .column().hasColumnName("READ")
            .column().hasColumnName("BY")
            .column().hasColumnName("SELECT")
            .column().hasColumnName("FROM")
            .column().hasColumnName("WHERE")
            .column().hasColumnName("ORDER")
    ;
  }

  @Test
  @NeedReload
  public void test_ColumnName_hasColumnName_with_columns_to_check() {
    Table table = new Table(dataSource, "GROUP", '`', '`')
                      .setColumnsToCheck(new String[] {
                          "READ", "BY", "SELECT", "FROM" 
                      });
    Changes changes = new Changes(table).setStartPointNow();
    update();
    changes.setEndPointNow();

    assertThat(table)
            .column().hasColumnName("READ")
            .column().hasColumnName("BY")
            .column().hasColumnName("SELECT")
            .column().hasColumnName("FROM")
    ;

    assertThat(changes).change()
            .column().hasColumnName("READ")
            .column().hasColumnName("BY")
            .column().hasColumnName("SELECT")
            .column().hasColumnName("FROM")
    ;
  }

  @Test
  @NeedReload
  public void test_ColumnName_hasColumnName_with_columns_to_exclude() {
    Table table = new Table(dataSource, "GROUP", '`', '`')
                      .setColumnsToExclude(new String[] {
                          "READ", "BY", "FROM" 
                      });
    Changes changes = new Changes(table).setStartPointNow();
    update();
    changes.setEndPointNow();

    assertThat(table)
            .column().hasColumnName("SELECT")
            .column().hasColumnName("WHERE")
            .column().hasColumnName("ORDER")
    ;

    assertThat(changes).change()
            .column().hasColumnName("SELECT")
            .column().hasColumnName("WHERE")
            .column().hasColumnName("ORDER")
    ;
  }

  @Test
  @NeedReload
  public void test_ColumnName_hasColumnName_with_order() {
    Table table = new Table(dataSource, "GROUP", '`', '`')
                      .setColumnsToOrder(new Order[] {
                          Order.asc("WHERE")
                      });
    Changes changes = new Changes(table).setStartPointNow();
    update();
    changes.setEndPointNow();

    assertThat(table)
            .column().hasColumnName("READ")
            .column().hasColumnName("BY")
            .column().hasColumnName("SELECT")
            .column().hasColumnName("FROM")
            .column().hasColumnName("WHERE")
            .column().hasColumnName("ORDER")
    ;

    assertThat(changes).change()
            .column().hasColumnName("READ")
            .column().hasColumnName("BY")
            .column().hasColumnName("SELECT")
            .column().hasColumnName("FROM")
            .column().hasColumnName("WHERE")
            .column().hasColumnName("ORDER")
    ;
  }

}
