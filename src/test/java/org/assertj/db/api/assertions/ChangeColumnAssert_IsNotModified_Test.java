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
public class ChangeColumnAssert_IsNotModified_Test extends AbstractTest {

  /**
   * This method tests the column is not modified.
   */
  @Test
  @NeedReload
  public void test_column_is_not_modified() {
    update("insert into movie(id, title) values(6, 'Test2')");
    update("insert into movie(id, title) values(7, 'Test3')");
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    update("insert into movie(id, title) values(5, 'Test')");
    update("update movie set title='Test4', year=null where id=6");
    update("delete movie where id=7");
    changes.setEndPointNow();

    assertThat(changes)
            .changeOfModification().column("name").isNotModified()
            .changeOfModification().column("id_actor").isNotModified()
            .changeOfModification().column("year").isNotModified()
            .changeOfModification().column("year").isNotModified()
            .changeOfCreation(3).column("year").isNotModified()
            .changeOfDeletion(2).column("year").isNotModified();
  }

  /**
   * This method should fail because the column is modified.
   */
  @Test
  @NeedReload
  public void should_fail_because_column_is_modified() {
    try {
      Changes changes = new Changes(source).setStartPointNow();
      updateChangesForTests();
      changes.setEndPointNow();

      assertThat(changes).changeOfModification().column("firstname").isNotModified();

      fail("An exception must be raised");
    } catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Column at index 2 of Change at index 0 of Changes on tables of 'sa/jdbc:h2:mem:test' source (only modification changes)] \n"
                                                    + "Expecting :\n" + "  <\"Sigourney\">\n"
                                                    + "is not modified but is :\n" + "  <\"Susan Alexandra\">");
    }
  }

  /**
   * This method should fail because the column is modified.
   */
  @Test
  @NeedReload
  public void should_fail_because_column_is_modified_null_become_not_null() {
    try {
      Changes changes = new Changes(source).setStartPointNow();
      updateChangesForTests();
      changes.setEndPointNow();

      assertThat(changes).changeOfCreation().column().isNotModified();

      fail("An exception must be raised");
    } catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Column at index 0 of Change at index 0 of Changes on tables of 'sa/jdbc:h2:mem:test' source (only creation changes)] \n"
                                                    + "Expecting :\n" + "  <null>\n" + "is not modified but is :\n"
                                                    + "  <4>");
    }
  }

  /**
   * This method should fail because the column is modified.
   */
  @Test
  @NeedReload
  public void should_fail_because_column_is_modified_null_become_null() {
    try {
      Changes changes = new Changes(source).setStartPointNow();
      updateChangesForTests();
      changes.setEndPointNow();

      assertThat(changes).changeOfDeletion().column().isNotModified();

      fail("An exception must be raised");
    } catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Column at index 0 of Change at index 0 of Changes on tables of 'sa/jdbc:h2:mem:test' source (only deletion changes)] \n"
                                                    + "Expecting :\n" + "  <3>\n" + "is not modified but is :\n"
                                                    + "  <null>");
    }
  }
}
