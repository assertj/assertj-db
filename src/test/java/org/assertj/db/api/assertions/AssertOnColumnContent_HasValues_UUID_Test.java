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
package org.assertj.db.api.assertions;

import org.assertj.core.api.Assertions;
import org.assertj.db.api.TableColumnAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Table;
import org.junit.Test;

import java.util.UUID;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on {@link org.assertj.db.api.assertions.AssertOnColumnContent} class :
 * {@link AssertOnColumnContent#containsValues(java.util.UUID...)} method.
 *
 * @author Otoniel Isidoro (otoniel.isidoro@sofist.com.br)
 */
public class AssertOnColumnContent_HasValues_UUID_Test extends AbstractTest {

  /**
   * This method tests the {@code containsValues} assertion method.
   */
  @Test
  public void test_has_values() throws Exception {
    Table table = new Table(source, "test");
    TableColumnAssert tableColumnAssert = assertThat(table).column("var15");
    TableColumnAssert tableColumnAssertReturn = tableColumnAssert.containsValues(
            UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"),
            UUID.fromString("0E2A1269-EFF0-4233-B87B-B53E8B6F164D"),
            UUID.fromString("2B0D1BDD-909E-4362-BA10-C930BA82718D"),
            UUID.fromString("399FFFCA-7874-4225-9903-E227C4E9DCC1")
    );
    Assertions.assertThat(tableColumnAssert).isSameAs(tableColumnAssertReturn);

    Table table2 = new Table(source, "test2");
    TableColumnAssert tableColumnAssert2 = assertThat(table2).column("var16");
    TableColumnAssert tableColumnAssertReturn2 = tableColumnAssert2
        .containsValues(UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"), null);
    Assertions.assertThat(tableColumnAssert2).isSameAs(tableColumnAssertReturn2);
  }

  /**
   * This method should fail because the values are different.
   */
  @Test
  public void should_fail_because_values_are_different() throws Exception {
    Table table = new Table(source, "test");
    TableColumnAssert tableColumnAssert = assertThat(table).column("var15");
    try {
      tableColumnAssert.containsValues(UUID.fromString("F96EC595-CE91-47CC-9152-CCC8AC48AAD6"),
                                       UUID.fromString("F96EC595-CE91-47CC-9152-CCC8AC48AAD6"),
                                       UUID.fromString("F96EC595-CE91-47CC-9152-CCC8AC48AAD6"),
                                       UUID.fromString("F96EC595-CE91-47CC-9152-CCC8AC48AAD6"));
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage())
                .isEqualTo(String.format("[Column at index 14 (column name : VAR15) of TEST table] %n"
                                         + "Expecting:%n"
                                         + "  <[30b443ae-c0c9-4790-9bec-ce1380808435,%n"
                                         + "    0e2a1269-eff0-4233-b87b-b53e8b6f164d,%n"
                                         + "    2b0d1bdd-909e-4362-ba10-c930ba82718d,%n"
                                         + "    399fffca-7874-4225-9903-e227c4e9dcc1]>%n"
                                         + "to contain: %n"
                                         + "  <[f96ec595-ce91-47cc-9152-ccc8ac48aad6,%n"
                                         + "    f96ec595-ce91-47cc-9152-ccc8ac48aad6,%n"
                                         + "    f96ec595-ce91-47cc-9152-ccc8ac48aad6,%n"
                                         + "    f96ec595-ce91-47cc-9152-ccc8ac48aad6]>%n"
                                         + " (parameter <f96ec595-ce91-47cc-9152-ccc8ac48aad6> at index 0 is not found)"));
    }
    Table table2 = new Table(source, "test2");
    TableColumnAssert tableColumnAssert2 = assertThat(table2).column("var16");
    try {
      tableColumnAssert2.containsValues(UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"),
                                   UUID.fromString("F96EC595-CE91-47CC-9152-CCC8AC48AAD6"));
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage())
                .isEqualTo(String.format("[Column at index 15 (column name : VAR16) of TEST2 table] %n"
                                         + "Expecting:%n"
                                         + "  <[30b443ae-c0c9-4790-9bec-ce1380808435, null]>%n"
                                         + "to contain: %n"
                                         + "  <[30b443ae-c0c9-4790-9bec-ce1380808435, f96ec595-ce91-47cc-9152-ccc8ac48aad6]>%n"
                                         + " (parameter <f96ec595-ce91-47cc-9152-ccc8ac48aad6> at index 1 is not found)"));
    }
  }
}
