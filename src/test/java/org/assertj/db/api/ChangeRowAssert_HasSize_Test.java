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
 * Test on the {@code hasNumberOfColumns} assertion method {@code Row} of {@code Change}.
 *
 * @author Régis Pouiller
 *
 */
public class ChangeRowAssert_HasSize_Test extends AbstractTest {

  /**
   * This method test the assertion on the size of {@code Row} of {@code Change}.
   */
  @Test
  @NeedReload
  public void test_size_of_row_assertion() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    assertThat(changes).change().rowAtEndPoint().hasNumberOfColumns(4);
  }

  /**
   * This test should fail because the size of {@code Row} of {@code Change} is different (4).
   */
  @Test
  @NeedReload
  public void should_fail_because_size_of_row_assertion_is_different() {
    try {
      Changes changes = new Changes(source).setStartPointNow();
      updateChangesForTests();
      changes.setEndPointNow();

      assertThat(changes).change().rowAtEndPoint().hasNumberOfColumns(3);

      fail("An exception must be raised");
    } catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Row at end point of Change at index 0 of Changes on tables of 'sa/jdbc:h2:mem:test' source] \n" +
                                                    "Expecting size (number of columns) to be equal to :\n" +
                                                    "   <3>\n"+
                                                    "but was:\n" +
                                                    "   <4>");
    }
  }
}
