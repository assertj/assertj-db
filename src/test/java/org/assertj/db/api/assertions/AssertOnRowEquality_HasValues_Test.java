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
package org.assertj.db.api.assertions;

import org.assertj.core.api.Assertions;
import org.assertj.db.api.ChangeRowAssert;
import org.assertj.db.api.TableRowAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.assertj.db.type.Changes;
import org.assertj.db.type.Table;
import org.junit.Test;

import java.util.UUID;

import static org.assertj.db.api.Assertions.assertThat;

/**
 * Tests on {@link org.assertj.db.api.assertions.AssertOnRowEquality} class :
 * {@link org.assertj.db.api.assertions.AssertOnRowEquality#hasValues(Object...)} method.
 *
 * @author Régis Pouiller
 *
 */
public class AssertOnRowEquality_HasValues_Test extends AbstractTest {

  /**
   * This method tests the {@code hasValues} assertion method.
   */
  @Test
  @NeedReload
  public void test_has_values() {
    Table table = new Table(source, "actor");
    Changes changes = new Changes(table).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    ChangeRowAssert changeRowAssert = assertThat(changes).change().rowAtEndPoint();
    ChangeRowAssert changeRowAssert2 = changeRowAssert.hasValues(4, "Murray", "Bill", "1950-09-21", "30B443AE-C0C9-4790-9BEC-CE1380808435")
                                                      .hasValues(4, "Murray", "Bill", "1950-09-21", UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"));
    Assertions.assertThat(changeRowAssert).isSameAs(changeRowAssert2);

    TableRowAssert tableRowAssert = assertThat(table).row();
    TableRowAssert tableRowAssert2 = tableRowAssert.hasValues(1, "Weaver", "Susan Alexandra", "1949-10-08", "30B443AE-C0C9-4790-9BEC-CE1380808435")
                                                   .hasValues(1, "Weaver", "Susan Alexandra", "1949-10-08",
                                                              UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"));
    Assertions.assertThat(tableRowAssert).isSameAs(tableRowAssert2);
  }

  /**
   * This method should fail because the values are different.
   */
  @Test
  @NeedReload
  public void should_fail_because_values_are_different() {
    Table table = new Table(source, "actor");
    Changes changes = new Changes(table).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    try {
      assertThat(changes).change().rowAtEndPoint().hasValues(4, "Murray", "Billy", "1950-09-21", UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"));
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[Row at end point of Change at index 0 (with primary key : [4]) of Changes on ACTOR table of 'sa/jdbc:h2:mem:test' source] %n"
                                                      + "Expecting that the value at index 2:%n"
                                                      + "  <\"Bill\">%n"
                                                      + "to be equal to: %n"
                                                      + "  <\"Billy\">"));
    }
    try {
      assertThat(table).row().hasValues(1, "Weaver", "Sigourney", "1949-10-08", UUID.fromString("648DFAC8-14AC-47F7-95CF-3475525A3BE3"));
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[Row at index 0 of ACTOR table] %n"
                                                      + "Expecting that the value at index 2:%n"
                                                      + "  <\"Susan Alexandra\">%n"
                                                      + "to be equal to: %n"
                                                      + "  <\"Sigourney\">"));
    }
  }
}
