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

import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.assertj.db.type.Changes;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on the methods which verifies the modified columns.
 *
 * @author Régis Pouiller
 *
 */
public class ChangeAssert_HasModifiedColumns_Test extends AbstractTest {

  /**
   * This method tests the modifications.
   */
  @Test
  @NeedReload
  public void test_number_of_modifications() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    assertThat(changes)
            .changeOfModification().hasModifiedColumns(2).hasModifiedColumns("firstname")
            .changeOfModification().hasModifiedColumns(3).hasModifiedColumns("character")
            .changeOfModification().hasModifiedColumns(1).hasModifiedColumns("title")
            .changeOfCreation().hasModifiedColumns(0, 1, 2, 3).hasModifiedColumns("id", "name", "firstname", "birth")
            .changeOfDeletion().hasModifiedColumns(0, 1, 2, 3).hasModifiedColumns("id", "name", "firstname", "birth");
  }

  /**
   * This method should fail because the column index parameter is null.
   */
  @Test(expected=NullPointerException.class)
  @NeedReload
  public void should_fail_because_column_index_is_null() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    assertThat(changes).change().hasModifiedColumns((Integer[]) null);
  }

  /**
   * This method should fail because the column index parameter is null.
   */
  @Test(expected=NullPointerException.class)
  @NeedReload
  public void should_fail_because_a_column_index_is_null() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    assertThat(changes).change().hasModifiedColumns(1, null);
  }

  /**
   * This method should fail because the column name parameter is null.
   */
  @Test(expected=NullPointerException.class)
  @NeedReload
  public void should_fail_because_column_name_is_null() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    assertThat(changes).change().hasModifiedColumns((String[]) null);
  }

  /**
   * This method should fail because the column name parameter is null.
   */
  @Test(expected=NullPointerException.class)
  @NeedReload
  public void should_fail_because_a_column_name_is_null() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    assertThat(changes).change().hasModifiedColumns("test", null);
  }

  /**
   * This method should fail because the modifications are different (with columns indexes).
   */
  @Test
  @NeedReload
  public void should_fail_because_modifications_is_different_with_columns_indexes() {
    try {
      Changes changes = new Changes(source).setStartPointNow();
      updateChangesForTests();
      changes.setEndPointNow();

      assertThat(changes).changeOfModification().hasModifiedColumns(1);

      fail("An exception must be raised");
    } catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Change at index 0 of Changes on tables of 'sa/jdbc:h2:mem:test' source (only modification changes)] \n"
                                                    + "Expecting :\n" + "  [1]\n"
                                                    + "as indexes of modified columns but was:\n" + "  [2]");
    }
  }

  /**
   * This method should fail because the modifications are different (with columns names).
   */
  @Test
  @NeedReload
  public void should_fail_because_modifications_is_different_with_columns_names() {
    try {
      Changes changes = new Changes(source).setStartPointNow();
      updateChangesForTests();
      changes.setEndPointNow();

      assertThat(changes).changeOfModification().hasModifiedColumns("name");

      fail("An exception must be raised");
    } catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Change at index 0 of Changes on tables of 'sa/jdbc:h2:mem:test' source (only modification changes)] \n"
                                                    + "Expecting :\n" + "  [\"name\"]\n"
                                                    + "as modified columns but was:\n" + "  [\"FIRSTNAME\"]");
    }
  }
}
