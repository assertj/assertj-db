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
import org.assertj.db.type.ChangeType;
import org.assertj.db.type.Changes;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Test on the assertion methods {@code isOfType} on {@code Change}.
 *
 * @author Régis Pouiller
 */
public class ChangeAssert_IsOfType_Test extends AbstractTest {

  /**
   * This method tests the {@code isOfType} assertion method and the derived.
   */
  @Test
  @NeedReload
  public void test_is_of_type() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();
    assertThat(changes).hasNumberOfChanges(8)
                       .change().isOfType(ChangeType.CREATION).isCreation()
                       .change().isOfType(ChangeType.CREATION).isCreation()
                       .change().isOfType(ChangeType.CREATION).isCreation()
                       .change().isOfType(ChangeType.MODIFICATION).isModification()
                       .change().isOfType(ChangeType.MODIFICATION).isModification()
                       .change().isOfType(ChangeType.MODIFICATION).isModification()
                       .change().isOfType(ChangeType.DELETION).isDeletion()
                       .change().isOfType(ChangeType.DELETION).isDeletion();
  }

  /**
   * This method should fail because the change is not a modification.
   */
  @Test
  @NeedReload
  public void should_fail_because_type_of_change_is_not_modification() {
    try {
      Changes changes = new Changes(source).setStartPointNow();
      updateChangesForTests();
      changes.setEndPointNow();

      assertThat(changes).change().isOfType(ChangeType.MODIFICATION);

      fail("An exception must be raised");
    } catch (AssertionError e) {
      assertThat(e.getMessage()).isEqualTo("[Change at index 0 of Changes on tables of 'sa/jdbc:h2:mem:test' source] \n"
                                           + "Expecting:\n" + "to be of type\n" + "  <MODIFICATION>\n"
                                           + "but was of type\n" + "  <CREATION>");
    }
  }

  /**
   * This method should fail because the change is not a creation.
   */
  @Test
  @NeedReload
  public void should_fail_because_type_of_change_is_not_creation() {
    try {
      Changes changes = new Changes(source).setStartPointNow();
      updateChangesForTests();
      changes.setEndPointNow();

      assertThat(changes).change(3).isOfType(ChangeType.CREATION);

      fail("An exception must be raised");
    } catch (AssertionError e) {
      assertThat(e.getMessage()).isEqualTo("[Change at index 3 of Changes on tables of 'sa/jdbc:h2:mem:test' source] \n"
                                           + "Expecting:\n" + "to be of type\n" + "  <CREATION>\n"
                                           + "but was of type\n" + "  <MODIFICATION>");
    }
  }

  /**
   * This method should fail because the change is not a deletion.
   */
  @Test
  @NeedReload
  public void should_fail_because_type_of_change_is_not_deletion() {
    try {
      Changes changes = new Changes(source).setStartPointNow();
      updateChangesForTests();
      changes.setEndPointNow();

      assertThat(changes).change().isOfType(ChangeType.DELETION);

      fail("An exception must be raised");
    } catch (AssertionError e) {
      assertThat(e.getMessage()).isEqualTo("[Change at index 0 of Changes on tables of 'sa/jdbc:h2:mem:test' source] \n"
                                           + "Expecting:\n" + "to be of type\n" + "  <DELETION>\n"
                                           + "but was of type\n" + "  <CREATION>");
    }
  }

  /**
   * This method should fail because the change is not a modification.
   */
  @Test
  @NeedReload
  public void should_fail_because_change_is_not_modification() {
    try {
      Changes changes = new Changes(source).setStartPointNow();
      updateChangesForTests();
      changes.setEndPointNow();

      assertThat(changes).change().isModification();

      fail("An exception must be raised");
    } catch (AssertionError e) {
      assertThat(e.getMessage()).isEqualTo("[Change at index 0 of Changes on tables of 'sa/jdbc:h2:mem:test' source] \n"
                                           + "Expecting:\n" + "to be of type\n" + "  <MODIFICATION>\n"
                                           + "but was of type\n" + "  <CREATION>");
    }
  }

  /**
   * This method should fail because the change is not a creation.
   */
  @Test
  @NeedReload
  public void should_fail_because_change_is_not_creation() {
    try {
      Changes changes = new Changes(source).setStartPointNow();
      updateChangesForTests();
      changes.setEndPointNow();

      assertThat(changes).change(3).isCreation();

      fail("An exception must be raised");
    } catch (AssertionError e) {
      assertThat(e.getMessage()).isEqualTo("[Change at index 3 of Changes on tables of 'sa/jdbc:h2:mem:test' source] \n"
                                           + "Expecting:\n" + "to be of type\n" + "  <CREATION>\n"
                                           + "but was of type\n" + "  <MODIFICATION>");
    }
  }

  /**
   * This method should fail because the change is not a deletion.
   */
  @Test
  @NeedReload
  public void should_fail_because_change_is_not_deletion() {
    try {
      Changes changes = new Changes(source).setStartPointNow();
      updateChangesForTests();
      changes.setEndPointNow();

      assertThat(changes).change().isDeletion();

      fail("An exception must be raised");
    } catch (AssertionError e) {
      assertThat(e.getMessage()).isEqualTo("[Change at index 0 of Changes on tables of 'sa/jdbc:h2:mem:test' source] \n"
                                           + "Expecting:\n" + "to be of type\n" + "  <DELETION>\n"
                                           + "but was of type\n" + "  <CREATION>");
    }
  }
}
