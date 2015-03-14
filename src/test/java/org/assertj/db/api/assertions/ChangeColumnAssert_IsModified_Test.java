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
package org.assertj.db.api.assertions;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.assertj.db.type.Changes;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on the methods which verifies that a column is not modified.
 *
 * @author Régis Pouiller
 *
 */
public class ChangeColumnAssert_IsModified_Test extends AbstractTest {

  /**
   * This method tests the column is modified.
   */
  @Test
  @NeedReload
  public void test_column_is_modified() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    assertThat(changes)
            .changeOfModification().column("firstname").isModified()
            .changeOfModification().column("character").isModified()
            .changeOfModification().column("title").isModified()
            .changeOfCreation().column().isModified()
            .changeOfDeletion().column().isModified();
  }

  /**
   * This method should fail because the column is not modified.
   */
  @Test
  @NeedReload
  public void should_fail_because_column_is_not_modified() {
    try {
      Changes changes = new Changes(source).setStartPointNow();
      updateChangesForTests();
      changes.setEndPointNow();

      assertThat(changes).changeOfModification().column("name").isModified();

      fail("An exception must be raised");
    } catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Column at index 1 of Change at index 0 of Changes on tables of 'sa/jdbc:h2:mem:test' source (only modification changes)] \n"
                                                    + "Expecting :\n" + "  <\"Weaver\">\n"
                                                    + "is modified but is still:\n" + "  <\"Weaver\">");
    }
  }


  /**
   * This method should fail because the column is not modified and have null values.
   */
  @Test
  @NeedReload
  public void should_fail_because_column_is_not_modified_have_null_values() {
    try {
      Changes changes = new Changes(source).setStartPointNow();
      update("insert into movie(id, title) values(4, 'Ghostbusters')");
      changes.setEndPointNow();

      assertThat(changes).change().column("year").isModified();

      fail("An exception must be raised");
    } catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Column at index 2 of Change at index 0 of Changes on tables of 'sa/jdbc:h2:mem:test' source] \n"
                                                    + "Expecting :\n" + "  <null>\n" + "is modified but is still:\n"
                                                    + "  <null>");
    }
  }
}
