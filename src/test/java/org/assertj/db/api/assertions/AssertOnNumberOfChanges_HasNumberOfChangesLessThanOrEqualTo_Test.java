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

import org.assertj.core.api.Assertions;
import org.assertj.db.api.ChangesAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.assertj.db.type.Changes;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Tests on {@link org.assertj.db.api.assertions.AssertOnNumberOfChanges} class :
 * {@link org.assertj.db.api.assertions.AssertOnNumberOfChanges#hasNumberOfChangesLessThanOrEqualTo(int)} method.
 *
 * @author Régis Pouiller
 * @author Julien Roy
 */
public class AssertOnNumberOfChanges_HasNumberOfChangesLessThanOrEqualTo_Test extends AbstractTest {

  /**
   * This method tests the {@code hasNumberOfChangesLessThanOrEqualTo} assertion method.
   */
  @Test
  @NeedReload
  public void test_has_number_of_changes_greater_than_or_equal_to() {
    Table table = assertDbConnection.table("actor").build();
    Changes changes = assertDbConnection.changes().tables(table).build().setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    ChangesAssert changesAssert = assertThat(changes);
    ChangesAssert changesAssert2 = changesAssert.hasNumberOfChangesLessThanOrEqualTo(3);
    Assertions.assertThat(changesAssert).isSameAs(changesAssert2);
  }

  /**
   * This method should fail because the number of changes is greater.
   */
  @Test
  @NeedReload
  public void should_fail_because_number_of_changes_is_greater() {
    Changes changes = assertDbConnection.changes().request("select * from actor").build().setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    try {
      assertThat(changes).hasNumberOfChangesLessThanOrEqualTo(2);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[Changes on 'select * from actor' request of 'sa/jdbc:h2:mem:test'] %n"
        + "Expecting size (number of changes) to be less than or equal to :%n"
        + "   <2>%n"
        + "but was:%n"
        + "   <4>"));
    }
  }
}
