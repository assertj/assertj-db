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
package org.assertj.db.api.assertions;

import static org.assertj.core.api.Assertions.fail;
import static org.assertj.db.api.Assertions.assertThat;

import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.assertj.db.api.TableColumnAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Tests on {@link AssertOnColumnEquality} class :
 * {@link org.assertj.db.api.assertions.AssertOnColumnEquality#hasValues(java.util.UUID...)} method.
 *
 * @author Otoniel Isidoro (otoniel.isidoro@sofist.com.br)
 */
public class AssertOnColumnEquality_HasValues_UUID_Test extends AbstractTest {

  /**
   * This method tests the {@code hasValues} assertion method.
   */
  @Test
  public void test_has_values() {
    Table table = assertDbConnection.table("test").build();
    TableColumnAssert tableColumnAssert = assertThat(table).column("var15");
    TableColumnAssert tableColumnAssertReturn = tableColumnAssert.hasValues(
      UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"),
      UUID.fromString("0E2A1269-EFF0-4233-B87B-B53E8B6F164D"),
      UUID.fromString("2B0D1BDD-909E-4362-BA10-C930BA82718D"),
      UUID.fromString("399FFFCA-7874-4225-9903-E227C4E9DCC1")
    );
    Assertions.assertThat(tableColumnAssert).isSameAs(tableColumnAssertReturn);

    Table table2 = assertDbConnection.table("test2").build();
    TableColumnAssert tableColumnAssert2 = assertThat(table2).column("var16");
    TableColumnAssert tableColumnAssertReturn2 = tableColumnAssert2
      .hasValues(UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"), null);
    Assertions.assertThat(tableColumnAssert2).isSameAs(tableColumnAssertReturn2);
  }

  /**
   * This method should fail because the values are different.
   */
  @Test
  public void should_fail_because_values_are_different() {
    Table table = assertDbConnection.table("test").build();
    TableColumnAssert tableColumnAssert = assertThat(table).column("var15");
    try {
      tableColumnAssert.hasValues(UUID.fromString("F96EC595-CE91-47CC-9152-CCC8AC48AAD6"),
        UUID.fromString("F96EC595-CE91-47CC-9152-CCC8AC48AAD6"),
        UUID.fromString("F96EC595-CE91-47CC-9152-CCC8AC48AAD6"),
        UUID.fromString("F96EC595-CE91-47CC-9152-CCC8AC48AAD6"));
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage())
        .isEqualTo(String.format("[Column at index 14 (column name : VAR15) of TEST table] %n"
          + "Expecting that the value at index 0:%n"
          + "  <30b443ae-c0c9-4790-9bec-ce1380808435>%n"
          + "to be equal to: %n"
          + "  <f96ec595-ce91-47cc-9152-ccc8ac48aad6>"));
    }
    Table table2 = assertDbConnection.table("test2").build();
    TableColumnAssert tableColumnAssert2 = assertThat(table2).column("var16");
    try {
      tableColumnAssert2.hasValues(UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"),
        UUID.fromString("F96EC595-CE91-47CC-9152-CCC8AC48AAD6"));
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage())
        .isEqualTo(String.format("[Column at index 15 (column name : VAR16) of TEST2 table] %n"
          + "Expecting that the value at index 1:%n"
          + "  <null>%n"
          + "to be equal to: %n"
          + "  <f96ec595-ce91-47cc-9152-ccc8ac48aad6>"));
    }
  }
}
