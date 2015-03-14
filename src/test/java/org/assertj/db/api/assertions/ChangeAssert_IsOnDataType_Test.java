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
import org.assertj.db.type.DataType;
import org.assertj.db.type.Request;
import org.junit.Test;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Test on the assertion methods {@code isOnDataType} on {@code Change}.
 *
 * @author Régis Pouiller
 */
public class ChangeAssert_IsOnDataType_Test extends AbstractTest {

  /**
   * This method tests the {@code isOnDataType} assertion method and the derived.
   */
  @Test
  @NeedReload
  public void test_is_on_data_type_when_table() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();
    assertThat(changes).hasNumberOfChanges(8)
                       .change().isOnDataType(DataType.TABLE).isOnTable()
                       .change().isOnDataType(DataType.TABLE).isOnTable()
                       .change().isOnDataType(DataType.TABLE).isOnTable()
                       .change().isOnDataType(DataType.TABLE).isOnTable()
                       .change().isOnDataType(DataType.TABLE).isOnTable()
                       .change().isOnDataType(DataType.TABLE).isOnTable()
                       .change().isOnDataType(DataType.TABLE).isOnTable()
                       .change().isOnDataType(DataType.TABLE).isOnTable();
  }

  /**
   * This method tests the {@code isOnDataType} assertion method and the derived.
   */
  @Test
  @NeedReload
  public void test_is_on_data_type_when_request() {
    Changes changes = new Changes(new Request(source, "select * from movie")).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    assertThat(changes).change().isOnDataType(DataType.REQUEST).isOnRequest();
  }

  /**
   * This method should fail because the change is not on a request.
   */
  @Test
  @NeedReload
  public void should_fail_because_data_type_of_change_is_not_on_request() {
    try {
      Changes changes = new Changes(source).setStartPointNow();
      updateChangesForTests();
      changes.setEndPointNow();

      assertThat(changes).change().isOnDataType(DataType.REQUEST);

      fail("An exception must be raised");
    } catch (AssertionError e) {
      org.assertj.core.api.Assertions.assertThat(e.getMessage()).isEqualTo("[Change at index 0 of Changes on tables of 'sa/jdbc:h2:mem:test' source] \n"
                                                                           + "Expecting:\n" + "to be on data type\n"
                                                                           + "  <REQUEST>\n" + "but was on data type\n"
                                                                           + "  <TABLE>");
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

      assertThat(changes).change().isOnDataType(DataType.TABLE);

      fail("An exception must be raised");
    } catch (AssertionError e) {
      org.assertj.core.api.Assertions.assertThat(e.getMessage()).isEqualTo("[Change at index 0 of Changes on 'select * from movie' request of 'sa/jdbc:h2:mem:test' source] \n"
                                                                           + "Expecting:\n" + "to be on data type\n"
                                                                           + "  <TABLE>\n" + "but was on data type\n"
                                                                           + "  <REQUEST>");
    }
  }

  /**
   * This method should fail because the change is not on a request.
   */
  @Test
  @NeedReload
  public void should_fail_because_change_is_not_on_request() {
    try {
      Changes changes = new Changes(source).setStartPointNow();
      updateChangesForTests();
      changes.setEndPointNow();

      assertThat(changes).change().isOnRequest();

      fail("An exception must be raised");
    } catch (AssertionError e) {
      org.assertj.core.api.Assertions.assertThat(e.getMessage()).isEqualTo("[Change at index 0 of Changes on tables of 'sa/jdbc:h2:mem:test' source] \n"
                                                                           + "Expecting:\n" + "to be on data type\n"
                                                                           + "  <REQUEST>\n" + "but was on data type\n"
                                                                           + "  <TABLE>");
    }
  }

  /**
   * This method should fail because the change is not on a table.
   */
  @Test
  @NeedReload
  public void should_fail_because_change_is_not_on_table() {
    try {
      Changes changes = new Changes(new Request(source, "select * from movie")).setStartPointNow();
      updateChangesForTests();
      changes.setEndPointNow();

      assertThat(changes).change().isOnTable();

      fail("An exception must be raised");
    } catch (AssertionError e) {
      org.assertj.core.api.Assertions.assertThat(e.getMessage()).isEqualTo("[Change at index 0 of Changes on 'select * from movie' request of 'sa/jdbc:h2:mem:test' source] \n"
                                                                           + "Expecting:\n" + "to be on data type\n"
                                                                           + "  <TABLE>\n" + "but was on data type\n"
                                                                           + "  <REQUEST>");
    }
  }
}
