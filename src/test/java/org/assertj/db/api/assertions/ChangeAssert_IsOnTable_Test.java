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
import org.assertj.db.type.Request;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Test on the assertion methods {@code isOnTable} on {@code Change}.
 *
 * @author Régis Pouiller
 */
public class ChangeAssert_IsOnTable_Test extends AbstractTest {

  /**
   * This method tests the {@code isOnTable} assertion method.
   */
  @Test
  @NeedReload
  public void test_is_on_table() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();
    assertThat(changes).hasNumberOfChanges(8)
                       .change().isOnTable("actor")
                       .change().isOnTable("interpretation")
                       .change().isOnTable("movie")
                       .change().isOnTable("actor")
                       .change().isOnTable("interpretation")
                       .change().isOnTable("movie")
                       .change().isOnTable("actor")
                       .change().isOnTable("interpretation");
  }

  /**
   * This method should fail because the table name parameter is null.
   */
  @Test(expected=NullPointerException.class)
  @NeedReload
  public void should_fail_because_table_name_is_null() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    assertThat(changes).change().isOnTable(null);
  }

  /**
   * This method should fail because the change is not on this table.
   */
  @Test
  @NeedReload
  public void should_fail_because_data_type_of_change_is_not_this_table() {
    try {
      Changes changes = new Changes(source).setStartPointNow();
      updateChangesForTests();
      changes.setEndPointNow();

      assertThat(changes).change().isOnTable("movie");

      fail("An exception must be raised");
    } catch (AssertionError e) {
      assertThat(e.getMessage()).isEqualTo("[Change at index 0 of Changes on tables of 'sa/jdbc:h2:mem:test' source] \n"
                                           + "Expecting to be on the table:\n" + "  <\"movie\">\n"
                                           + "but was on tha table:\n" + "  <\"ACTOR\">");
    }
  }

  /**
   * This method should fail because the change is not on a table.
   */
  @Test
  @NeedReload
  public void should_fail_because_data_type_of_change_is_not_on_table() {
    try {
      Changes changes = new Changes(new Request(source, "select * from movie")).setStartPointNow();
      updateChangesForTests();
      changes.setEndPointNow();

      assertThat(changes).change().isOnTable("movie");

      fail("An exception must be raised");
    } catch (AssertionError e) {
      assertThat(e.getMessage()).isEqualTo("[Change at index 0 of Changes on 'select * from movie' request of 'sa/jdbc:h2:mem:test' source] \n"
                                                                           + "Expecting:\n" + "to be on data type\n"
                                                                           + "  <TABLE>\n" + "but was on data type\n"
                                                                           + "  <REQUEST>");
    }
  }
}
