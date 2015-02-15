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
 * Tests on the methods which verifies the number of modified columns.
 *
 * @author Régis Pouiller
 *
 */
public class ChangeAssert_HasNumberOfModifiedColumns_Test extends AbstractTest {

  /**
   * This method tests the number of modifications.
   */
  @Test
  @NeedReload
  public void test_number_of_modifications() {
    update("insert into movie(id, title) values(6, 'Test2')");
    update("insert into movie(id, title) values(7, 'Test3')");
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    update("insert into movie(id, title) values(5, 'Test')");
    update("update movie set title='Test4', year=null where id=6");
    update("delete movie where id=7");
    changes.setEndPointNow();

    assertThat(changes).changeOfModification().hasNumberOfModifiedColumns(1)
            .changeOfModification().hasNumberOfModifiedColumns(1)
            .changeOfModification().hasNumberOfModifiedColumns(1)
            .changeOfModification().hasNumberOfModifiedColumns(1)
            .changeOfCreation().hasNumberOfModifiedColumns(4)
            .changeOfCreation(3).hasNumberOfModifiedColumns(2)
            .changeOfDeletion().hasNumberOfModifiedColumns(4)
            .changeOfDeletion(2).hasNumberOfModifiedColumns(2);
  }

  /**
   * This method should fail because the number of modifications is different.
   */
  @Test
  @NeedReload
  public void should_fail_because_number_of_modifications_is_different() {
    try {
      Changes changes = new Changes(source).setStartPointNow();
      updateChangesForTests();
      changes.setEndPointNow();

      assertThat(changes).changeOfModification().hasNumberOfModifiedColumns(2);

      fail("An exception must be raised");
    } catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Change at index 0 of Changes on tables of 'sa/jdbc:h2:mem:test' source (only modification changes)] \n"
                                                    + "Expecting :\n" + "  2 modifications\n" + "but was:\n" + "  1");
    }
  }
}
