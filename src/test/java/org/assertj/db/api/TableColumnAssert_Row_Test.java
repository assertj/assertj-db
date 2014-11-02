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
 * Copyright 2012-2014 the original author or authors.
 */
package org.assertj.db.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Test on {@code row} methods on column assert from a table.
 * 
 * @author Régis Pouiller
 * 
 */
public class TableColumnAssert_Row_Test extends AbstractTest {

  /**
   * This method tests the result of {@code row} methods on column assert from a table.
   */
  @Test
  public void test_with_table_and_column() {
    Table table = new Table(source, "test");
    
    TableAssert tableAssert = assertThat(table);
    TableRowAssert tableRowAssert = tableAssert.row(1);
    TableColumnAssert tableColumnAssert = tableAssert.column(1);
    
    assertThat(tableRowAssert).isEqualTo(tableColumnAssert.row(0).row());
    assertThat(tableRowAssert).isEqualTo(tableColumnAssert.row(1));
  }
}