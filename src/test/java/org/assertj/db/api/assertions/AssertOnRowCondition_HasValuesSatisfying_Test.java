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

import static org.assertj.db.api.Assertions.assertThat;

import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;
import org.assertj.core.api.HamcrestCondition;
import org.assertj.db.api.ChangeRowAssert;
import org.assertj.db.api.TableRowAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.assertj.db.type.Changes;
import org.assertj.db.type.Table;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

/**
 * Tests on {@link AssertOnRowCondition} class :
 * {@link AssertOnRowCondition#hasValuesSatisfying(Object...)} method.
 *
 * @author Julien Roy
 */
public class AssertOnRowCondition_HasValuesSatisfying_Test extends AbstractTest {

  /**
   * This method tests the {@code hasValuesSatisfying} assertion method.
   */
  @Test
  @NeedReload
  public void test_has_values() {
    Table table = assertDbConnection.table("actor").build();
    Changes changes = assertDbConnection.changes().tables(table).build().setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    ChangeRowAssert changeRowAssert = assertThat(changes).change().rowAtEndPoint();
    ChangeRowAssert changeRowAssert2 = changeRowAssert
      .hasValuesSatisfying(
        4,
        new Condition<String>(v -> v.equals("Murray"), "isMurray"),
        new HamcrestCondition<>(CoreMatchers.is("Bill")),
        "1950-09-21",
        "30B443AE-C0C9-4790-9BEC-CE1380808435"
      )
      .hasValuesSatisfying(
        4,
        new Condition<String>(v -> v.equals("Murray"), "isMurray"),
        new HamcrestCondition<>(CoreMatchers.is("Bill")),
        "1950-09-21",
        UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435")
      );
    Assertions.assertThat(changeRowAssert).isSameAs(changeRowAssert2);

    TableRowAssert tableRowAssert = assertThat(table).row();
    TableRowAssert tableRowAssert2 = tableRowAssert
      .hasValuesSatisfying(
        1,
        new Condition<String>(v -> v.equals("Weaver"), "isWeaver"),
        new HamcrestCondition<>(CoreMatchers.is("Susan Alexandra")),
        "1949-10-08",
        "30B443AE-C0C9-4790-9BEC-CE1380808435"
      )
      .hasValuesSatisfying(
        1,
        new Condition<String>(v -> v.equals("Weaver"), "isWeaver"),
        new HamcrestCondition<>(CoreMatchers.is("Susan Alexandra")),
        "1949-10-08",
        UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435")
      );
    Assertions.assertThat(tableRowAssert).isSameAs(tableRowAssert2);
  }

  /**
   * This method should fail because the values are different.
   */
  @Test
  @NeedReload
  public void should_fail_because_values_are_different() {
    Table table = assertDbConnection.table("actor").build();
    Changes changes = assertDbConnection.changes().tables(table).build().setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    try {
      assertThat(changes).change().rowAtEndPoint()
        .hasValuesSatisfying(
          4,
          "Murray",
          new Condition<String>(v -> v.equals("Billy"), "isBilly"),
          "1950-09-21",
          UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435")
        );
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format(
        "[Row at end point of Change at index 0 (with primary key : [4]) of Changes on ACTOR table of 'sa/jdbc:h2:mem:test'] %n"
          + "Expecting that the value at index 2:%n"
          + "  \"Bill\"%n"
          + "to satisfy: %n"
          + "  isBilly"));
    }
    try {
      assertThat(table).row().hasValuesSatisfying(
        1,
        "Weaver",
        new Condition<String>(v -> v.equals("Sigourney"), "isSigourney"),
        "1949-10-08",
        UUID.fromString("648DFAC8-14AC-47F7-95CF-3475525A3BE3")
      );
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[Row at index 0 of ACTOR table] %n"
        + "Expecting that the value at index 2:%n"
        + "  \"Susan Alexandra\"%n"
        + "to satisfy: %n"
        + "  isSigourney"));
    }
  }
}
