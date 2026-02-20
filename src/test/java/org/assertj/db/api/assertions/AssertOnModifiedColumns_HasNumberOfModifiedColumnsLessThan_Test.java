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
import org.assertj.db.api.ChangeAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.assertj.db.type.Changes;
import org.junit.Test;

/**
 * Tests on {@link org.assertj.db.api.assertions.AssertOnModifiedColumns} class :
 * {@link org.assertj.db.api.assertions.AssertOnModifiedColumns#hasNumberOfModifiedColumnsLessThan(int)} method.
 *
 * @author Régis Pouiller
 * @author Julien Roy
 */
public class AssertOnModifiedColumns_HasNumberOfModifiedColumnsLessThan_Test extends AbstractTest {

  /**
   * This method tests the {@code hasNumberOfModifiedColumnsLessThan} assertion method.
   */
  @Test
  @NeedReload
  public void test_has_number_of_modified_columns_less_than() {
    Changes changes = assertDbConnection.changes().build().setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    ChangeAssert changeAssert = assertThat(changes).change(3);
    ChangeAssert changeAssert2 = changeAssert.hasNumberOfModifiedColumnsLessThan(2);
    Assertions.assertThat(changeAssert).isSameAs(changeAssert2);
  }

  /**
   * This method should fail because the number of modified columns is greater or equal.
   */
  @Test
  @NeedReload
  public void should_fail_because_number_of_modified_columns_is_greater_or_equal() {
    Changes changes = assertDbConnection.changes().build().setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    try {
      assertThat(changes).change(3).hasNumberOfModifiedColumnsLessThan(1);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[Change at index 3 (on table : ACTOR and with primary key : [1]) of Changes on tables of 'sa/jdbc:h2:mem:test'] %n"
        + "Expecting :%n"
        + "  number of modifications is less than 1%n"
        + "but was:%n"
        + "  1"));
    }
  }
}
