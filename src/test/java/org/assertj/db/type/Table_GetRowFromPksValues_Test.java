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
package org.assertj.db.type;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.UUID;

import org.assertj.db.common.AbstractTest;
import org.junit.Test;

/**
 * Tests on getting a {@code Row} in a {@code Table} from primary keys values.
 *
 * @author Régis Pouiller
 * @author Julien Roy
 */
public class Table_GetRowFromPksValues_Test extends AbstractTest {

  /**
   * This method test getting a row from primary keys values without finding it.
   */
  @Test
  public void test_getting_row_from_primary_keys_values_without_finding() throws Exception {
    Table table = assertDbConnection.table("movie").build();

    assertThat(table.getRowFromPksValues()).isNull();
    assertThat(table.getRowFromPksValues(getValue(null, 1L), getValue(null, 3))).isNull();
  }

  /**
   * This method test getting a row from primary keys values with finding it.
   */
  @Test
  public void test_getting_row_from_primary_keys_values_with_finding() throws Exception {
    Table table = assertDbConnection.table("movie").build();

    assertThat(table.getRowFromPksValues(getValue(null, 3)).getValuesList().get(0).getValue()).isEqualTo(new BigDecimal(3));
    assertThat(table.getRowFromPksValues(getValue(null, 3)).getValuesList().get(1).getValue()).isEqualTo("Avatar");
    assertThat(table.getRowFromPksValues(getValue(null, 3)).getValuesList().get(2).getValue()).isEqualTo(new BigDecimal(2009));
    assertThat(table.getRowFromPksValues(getValue(null, 3)).getValuesList().get(3).getValue()).isEqualTo(UUID.fromString(
      "D735221B-5DE5-4112-AA1E-49090CB75ADA"));
    assertThat(table.getRowFromPksValues(getValue(null, 1L)).getValuesList().get(0).getValue())
      .isEqualTo(new BigDecimal(1));
    assertThat(table.getRowFromPksValues(getValue(null, 1L)).getValuesList().get(1).getValue())
      .isEqualTo("Alien");
    assertThat(table.getRowFromPksValues(getValue(null, 1L)).getValuesList().get(2).getValue())
      .isEqualTo(new BigDecimal(1979));
    assertThat(table.getRowFromPksValues(getValue(null, 1L)).getValuesList().get(3).getValue())
      .isEqualTo(UUID.fromString(
        "30B443AE-C0C9-4790-9BEC-CE1380808435"));
  }
}
