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
 * Copyright 2012-2015 the original author or authors.
 */
package org.assertj.db.type;

import org.assertj.db.common.AbstractTest;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests on the {@code Column} of {@code Table}.
 * <p>
 * These tests are on the values in the {@code Column} got from a {@code Table}.
 * </p>
 * 
 * @author Régis Pouiller
 * 
 */
public class Table_Columns_Test extends AbstractTest {

  /**
   * This method test the columns got from a {@code Source}.
   */
  @Test
  public void test_columns_with_source_set() {
    Table table = new Table(source, "movie");

    Column columnFromIndex = table.getColumn(1);

    assertThat(columnFromIndex.getName()).isEqualTo("TITLE");
    assertThat(columnFromIndex.getValuesList().get(0).getValue()).isEqualTo("Alien");
    assertThat(columnFromIndex.getValuesList().get(1).getValue()).isEqualTo("The Village");
    assertThat(columnFromIndex.getValuesList().get(2).getValue()).isEqualTo("Avatar");
    assertThat(columnFromIndex.getRowValue(1).getValue()).isEqualTo("The Village");
  }

  /**
   * This method test the columns got from a {@code DataSource}.
   */
  @Test
  public void test_columns_with_datasource_set() {
    Table table = new Table(dataSource, "movie");

    Column columnFromIndex = table.getColumn(1);

    assertThat(columnFromIndex.getName()).isEqualTo("TITLE");
    assertThat(columnFromIndex.getValuesList().get(0).getValue()).isEqualTo("Alien");
    assertThat(columnFromIndex.getValuesList().get(1).getValue()).isEqualTo("The Village");
    assertThat(columnFromIndex.getValuesList().get(2).getValue()).isEqualTo("Avatar");
    assertThat(columnFromIndex.getRowValue(1).getValue()).isEqualTo("The Village");
  }

}
