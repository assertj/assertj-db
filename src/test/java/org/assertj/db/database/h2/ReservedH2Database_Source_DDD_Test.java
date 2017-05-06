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
public class ReservedH2Database_Source_DDD_Test extends AbstractReservedH2Test {

  private DataSource dataSource;

  @Before
  public void init() {
    dataSource = dataSourceDDD;
  }

  @Test
  @NeedReload
  public void test_PrimaryKey_hasPksNames() {
    Table table = new Table(dataSource, "group", '`', '`');
    Changes changes = new Changes(table).setStartPointNow();
    update();
    changes.setEndPointNow();

    assertThat(changes).change().hasPksNames("read")
    ;
  }

  @Test
  @NeedReload
  public void test_ColumnName_hasColumnName() {
    Table table = new Table(dataSource, "group", '`', '`');
    Changes changes = new Changes(table).setStartPointNow();
    update();
    changes.setEndPointNow();

    assertThat(table)
            .column().hasColumnName("read")
            .column().hasColumnName("by")
            .column().hasColumnName("select")
            .column().hasColumnName("from")
            .column().hasColumnName("where")
            .column().hasColumnName("order")
    ;

    assertThat(changes).change()
            .column().hasColumnName("read")
            .column().hasColumnName("by")
            .column().hasColumnName("select")
            .column().hasColumnName("from")
            .column().hasColumnName("where")
            .column().hasColumnName("order")
    ;
  }

  @Test
  @NeedReload
  public void test_ColumnName_hasColumnName_with_columns_to_check() {
    Table table = new Table(dataSource, "group", '`', '`')
                      .setColumnsToCheck(new String[] {
                          "read", "by", "select", "from" 
                      });
    Changes changes = new Changes(table).setStartPointNow();
    update();
    changes.setEndPointNow();

    assertThat(table)
            .column().hasColumnName("read")
            .column().hasColumnName("by")
            .column().hasColumnName("select")
            .column().hasColumnName("from")
    ;

    assertThat(changes).change()
            .column().hasColumnName("read")
            .column().hasColumnName("by")
            .column().hasColumnName("select")
            .column().hasColumnName("from")
    ;
  }

  @Test
  @NeedReload
  public void test_ColumnName_hasColumnName_with_columns_to_exclude() {
    Table table = new Table(dataSource, "group", '`', '`')
                      .setColumnsToExclude(new String[] {
                          "read", "by", "from" 
                      });
    Changes changes = new Changes(table).setStartPointNow();
    update();
    changes.setEndPointNow();

    assertThat(table)
            .column().hasColumnName("select")
            .column().hasColumnName("where")
            .column().hasColumnName("order")
    ;

    assertThat(changes).change()
            .column().hasColumnName("select")
            .column().hasColumnName("where")
            .column().hasColumnName("order")
    ;
  }

  @Test
  @NeedReload
  public void test_ColumnName_hasColumnName_with_order() {
    Table table = new Table(dataSource, "group", '`', '`')
                      .setColumnsToOrder(new Order[] {
                          Order.asc("where")
                      });
    Changes changes = new Changes(table).setStartPointNow();
    update();
    changes.setEndPointNow();

    assertThat(table)
            .column().hasColumnName("read")
            .column().hasColumnName("by")
            .column().hasColumnName("select")
            .column().hasColumnName("from")
            .column().hasColumnName("where")
            .column().hasColumnName("order")
    ;

    assertThat(changes).change()
            .column().hasColumnName("read")
            .column().hasColumnName("by")
            .column().hasColumnName("select")
            .column().hasColumnName("from")
            .column().hasColumnName("where")
            .column().hasColumnName("order")
    ;
  }

}
