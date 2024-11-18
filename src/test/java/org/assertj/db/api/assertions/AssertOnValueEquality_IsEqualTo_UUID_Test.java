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
package org.assertj.db.api.assertions;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.assertj.db.api.ChangeColumnValueAssert;
import org.assertj.db.api.TableColumnValueAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.assertj.db.type.Changes;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Tests on {@link  org.assertj.db.api.assertions.AssertOnValueEquality} class :
 * {@link  org.assertj.db.api.assertions.AssertOnValueEquality#isEqualTo(UUID)} method.
 *
 * @author Otoniel Isidoro (otoniel.isidoro@sofist.com.br)
 */
public class AssertOnValueEquality_IsEqualTo_UUID_Test extends AbstractTest {

  /**
   * This method tests the {@code isEqualTo} assertion method.
   */
  @Test
  @NeedReload
  public void test_is_equal_to() {
    Table table = assertDbConnection.table("test").build();
    Changes changes = assertDbConnection.changes().tables(table).build().setStartPointNow();
    update("update test set var15 = '2B0D1BDD-909E-4362-BA10-C930BA82718D' where var1 = 1");
    changes.setEndPointNow();

    ChangeColumnValueAssert changeColumnValueAssert = assertThat(changes).change().column("var15").valueAtEndPoint();
    ChangeColumnValueAssert changeColumnValueAssert2 = changeColumnValueAssert
      .isEqualTo(UUID.fromString("2b0d1bdd-909e-4362-ba10-c930ba82718d"));
    Assertions.assertThat(changeColumnValueAssert).isSameAs(changeColumnValueAssert2);

    TableColumnValueAssert tableColumnValueAssert = assertThat(table).column("var15").value();
    TableColumnValueAssert tableColumnValueAssert2 = tableColumnValueAssert
      .isEqualTo(UUID.fromString("2b0d1bdd-909e-4362-ba10-c930ba82718d"));
    Assertions.assertThat(tableColumnValueAssert).isSameAs(tableColumnValueAssert2);
  }

  /**
   * This method should fail because the value is no equal to.
   */
  @Test
  @NeedReload
  public void should_fail_because_value_is_not_equal_to() {
    Table table = assertDbConnection.table("test").build();
    Changes changes = assertDbConnection.changes().tables(table).build().setStartPointNow();
    update("update test set var15 = '2B0D1BDD-909E-4362-BA10-C930BA82718D' where var1 = 10");
    changes.setEndPointNow();

    try {
      assertThat(changes).change().column("var15").valueAtEndPoint()
        .isEqualTo(UUID.fromString("0e2a1269-eff0-4233-b87b-b53e8b6f164d"));
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format(
        "[Value at end point of Column at index 14 (column name : VAR15) of Change at index 0 (with primary key : [10]) of Changes on TEST table of 'sa/jdbc:h2:mem:test'] %n"
          + "Expecting:%n"
          + "  <2b0d1bdd-909e-4362-ba10-c930ba82718d>%n"
          + "to be equal to: %n"
          + "  <0e2a1269-eff0-4233-b87b-b53e8b6f164d>"));
    }
    try {
      assertThat(table).column("var15").value(1).isEqualTo(UUID.fromString("0e2a1269-eff0-4233-b87b-b53e8b6f164d"));
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(
        String.format("[Value at index 1 of Column at index 14 (column name : VAR15) of TEST table] %n"
          + "Expecting:%n"
          + "  <2b0d1bdd-909e-4362-ba10-c930ba82718d>%n"
          + "to be equal to: %n"
          + "  <0e2a1269-eff0-4233-b87b-b53e8b6f164d>"));
    }
  }
}
