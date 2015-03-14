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
 * Test on the {@code hasValuesEqualTo} assertion method on {@code Column}.
 *
 * @author Régis Pouiller
 *
 */
public class ChangeColumnAssert_HasValuesEqualTo_Test extends AbstractTest {

  /**
   * This method test the assertion on the values of a {@code Column} from a {@code Change}.
   */
  @Test
  @NeedReload
  public void test_values() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    assertThat(changes).changeOfModification()
                       .column(1).hasValuesEqualTo("Weaver")
                       .column().hasValuesEqualTo("Sigourney", "Susan Alexandra");
  }

  /**
   * This test should fail because the value at start point is different.
   */
  @Test
  @NeedReload
  public void should_fail_because_the_value_at_start_point_is_different() {
    try {
      Changes changes = new Changes(source).setStartPointNow();
      updateChangesForTests();
      changes.setEndPointNow();

      assertThat(changes).changeOfModification(2).column(1).hasValuesEqualTo("The Avatar");

      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Column at index 1 of Change at index 2 of Changes on tables of 'sa/jdbc:h2:mem:test' source (only modification changes)] \n"
                                                    + "Expecting that start point:\n" + "  <\"Avatar\">\n"
                                                    + "to be equal to: \n" + "  <\"The Avatar\">");
    }
  }

  /**
   * This test should fail because the value at end point is different.
   */
  @Test
  @NeedReload
  public void should_fail_because_the_value_at_end_point_is_different() {
    try {
      Changes changes = new Changes(source).setStartPointNow();
      updateChangesForTests();
      changes.setEndPointNow();

      assertThat(changes).changeOfModification(2).column(1).hasValuesEqualTo("Avatar");

      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Column at index 1 of Change at index 2 of Changes on tables of 'sa/jdbc:h2:mem:test' source (only modification changes)] \n"
                                                    + "Expecting that end point:\n" + "  <\"The Avatar\">\n"
                                                    + "to be equal to: \n" + "  <\"Avatar\">");
    }
  }

  /**
   * This test should fail because the value at start point is different.
   */
  @Test
  @NeedReload
  public void should_fail_because_the_value_at_start_point_is_different_with_two_parameters() {
    try {
      Changes changes = new Changes(source).setStartPointNow();
      updateChangesForTests();
      changes.setEndPointNow();

      assertThat(changes).changeOfModification().column(2).hasValuesEqualTo("sigourney", "Susan Alexandra");

      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Column at index 2 of Change at index 0 of Changes on tables of 'sa/jdbc:h2:mem:test' source (only modification changes)] \n"
                                                    + "Expecting that start point:\n" + "  <\"Sigourney\">\n"
                                                    + "to be equal to: \n" + "  <\"sigourney\">");
    }
  }

  /**
   * This test should fail because the value at end point is different.
   */
  @Test
  @NeedReload
  public void should_fail_because_the_value_at_end_point_is_different_with_two_parameters() {
    try {
      Changes changes = new Changes(source).setStartPointNow();
      updateChangesForTests();
      changes.setEndPointNow();

      assertThat(changes).changeOfModification().column(2).hasValuesEqualTo("Sigourney", "susan Alexandra");

      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Column at index 2 of Change at index 0 of Changes on tables of 'sa/jdbc:h2:mem:test' source (only modification changes)] \n"
                                                    + "Expecting that end point:\n" + "  <\"Susan Alexandra\">\n"
                                                    + "to be equal to: \n" + "  <\"susan Alexandra\">");
    }
  }
}
