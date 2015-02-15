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
import org.assertj.db.type.Request;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on the methods which verifies the names of the columns which are in the primary key.
 *
 * @author Régis Pouiller
 *
 */
public class ChangeAssert_HasPksNames_Test extends AbstractTest {

  /**
   * This method tests the names of the columns which are in the primary key.
   */
  @Test
  @NeedReload
  public void test_primary_keys_names_on_table() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    assertThat(changes).hasSize(8)
                       .change().hasPksNames("id")
                       .change().hasPksNames("id")
                       .change().hasPksNames("id")
                       .change().hasPksNames("id")
                       .change().hasPksNames("id")
                       .change().hasPksNames("id")
                       .change().hasPksNames("id")
                       .change().hasPksNames("id");
  }

  /**
   * This method tests the names of the columns which are in the primary key.
   */
  @Test
  @NeedReload
  public void test_primary_keys_names_on_request() {
    Changes changes = new Changes(new Request(source, "select * from movie").setPksName("id")).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    assertThat(changes).change().hasPksNames("id");
  }

  /**
   * This method should fail because the column name parameter is null.
   */
  @Test
  @NeedReload
  public void should_fail_because_column_name_is_null() {
    try {
      Changes changes = new Changes(source).setStartPointNow();
      updateChangesForTests();
      changes.setEndPointNow();

      assertThat(changes).change().hasPksNames(null);

      fail("An exception must be raised");
    } catch (NullPointerException e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("Column name must be not null");
    }
  }

  /**
   * This method should fail because the column name parameter is null.
   */
  @Test
  @NeedReload
  public void should_fail_because_one_column_name_is_null() {
    try {
      Changes changes = new Changes(source).setStartPointNow();
      updateChangesForTests();
      changes.setEndPointNow();

      assertThat(changes).change().hasPksNames("", null);

      fail("An exception must be raised");
    } catch (NullPointerException e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("Column name must be not null");
    }
  }

  /**
   * This method should fail because the primary key column is different.
   */
  @Test
  @NeedReload
  public void should_fail_because_primary_key_column_is_different() {
    try {
      Changes changes = new Changes(source).setStartPointNow();
      updateChangesForTests();
      changes.setEndPointNow();

      assertThat(changes).change().hasPksNames("not_that");

      fail("An exception must be raised");
    } catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Change at index 0 of Changes on tables of 'sa/jdbc:h2:mem:test' source] \n"
                                                    + "Expecting :\n" + "  [\"not_that\"]\n"
                                                    + "as modified columns but was:\n" + "  [\"ID\"]");
    }
  }
}
