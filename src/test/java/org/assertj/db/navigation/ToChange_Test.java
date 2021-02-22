/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2015-2021 the original author or authors.
 */
package org.assertj.db.navigation;

import org.assertj.core.api.Assertions;
import org.assertj.db.api.ChangeAssert;
import org.assertj.db.api.ChangesAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.assertj.db.output.ChangeOutputter;
import org.assertj.db.output.ChangesOutputter;
import org.assertj.db.type.Changes;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;

/**
 * Tests on {@link ToChange} interface.
 *
 * @author Régis Pouiller
 *
 */
public class ToChange_Test extends AbstractTest {

  /**
   * This method tests the {@code ToChange} navigation interfaces.
   */
  @Test
  @NeedReload
  public void test_to_change_navigation_with_assertions() throws Exception {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    Field fieldChanges = ChangesAssert.class.getDeclaredField("changes");
    fieldChanges.setAccessible(true);

    ChangesAssert changesAssert = assertThat(changes);
    ChangesAssert changesAssertCreation = changesAssert.ofCreation();
    ChangesAssert changesAssertModification = changesAssert.ofModification();
    ChangesAssert changesAssertDeletion = changesAssert.ofDeletion();

    ChangeAssert changeAssertCreation0 = changesAssert.changeOfCreation();
    ChangeAssert changeAssertCreation1 = changesAssertCreation.changeOfCreation();
    ChangeAssert changeAssertCreation0Bis = changesAssertCreation.changeOfCreation(0);
    ChangeAssert changeAssertCreation1Bis = changesAssert.changeOfCreation(1);

    Assertions.assertThat(changeAssertCreation0).isSameAs(changeAssertCreation0Bis);
    Assertions.assertThat(changeAssertCreation1).isSameAs(changeAssertCreation1Bis);

    ChangeAssert changeAssertModification0 = changesAssert.changeOfModification();
    ChangeAssert changeAssertModification1 = changesAssertModification.changeOfModification();
    ChangeAssert changeAssertModification0Bis = changesAssertModification.changeOfModification(0);
    ChangeAssert changeAssertModification1Bis = changesAssert.changeOfModification(1);

    Assertions.assertThat(changeAssertModification0).isSameAs(changeAssertModification0Bis);
    Assertions.assertThat(changeAssertModification1).isSameAs(changeAssertModification1Bis);

    ChangeAssert changeAssertDeletion0 = changesAssert.changeOfDeletion();
    ChangeAssert changeAssertDeletion1 = changesAssertDeletion.changeOfDeletion();
    ChangeAssert changeAssertDeletion0Bis = changesAssertDeletion.changeOfDeletion(0);
    ChangeAssert changeAssertDeletion1Bis = changesAssert.changeOfDeletion(1);

    Assertions.assertThat(changeAssertDeletion0).isSameAs(changeAssertDeletion0Bis);
    Assertions.assertThat(changeAssertDeletion1).isSameAs(changeAssertDeletion1Bis);

    ChangeAssert changeAssertCreationOnTable0 = changesAssert.changeOfCreationOnTable("actor");
    ChangeAssert changeAssertCreationOnTable1 = changesAssertCreation.changeOfCreationOnTable("interpretation");
    ChangeAssert changeAssertCreationOnTable0Bis = changesAssertCreation.changeOfCreationOnTable("actor", 0);
    ChangeAssert changeAssertCreationOnTable1Bis = changesAssert.changeOfCreationOnTable("interpretation", 0);

    Assertions.assertThat(changeAssertCreationOnTable0).isSameAs(changeAssertCreationOnTable0Bis);
    Assertions.assertThat(changeAssertCreationOnTable1).isSameAs(changeAssertCreationOnTable1Bis);

    ChangeAssert changeAssertModificationOnTable0 = changesAssert.changeOfModificationOnTable("actor");
    ChangeAssert changeAssertModificationOnTable1 = changesAssertModification.changeOfModificationOnTable(
            "interpretation");
    ChangeAssert changeAssertModificationOnTable0Bis = changesAssertModification.changeOfModificationOnTable("actor", 0);
    ChangeAssert changeAssertModificationOnTable1Bis = changesAssert.changeOfModificationOnTable("interpretation", 0);

    Assertions.assertThat(changeAssertModificationOnTable0).isSameAs(changeAssertModificationOnTable0Bis);
    Assertions.assertThat(changeAssertModificationOnTable1).isSameAs(changeAssertModificationOnTable1Bis);

    ChangeAssert changeAssertDeletionOnTable0 = changesAssert.changeOfDeletionOnTable("actor");
    ChangeAssert changeAssertDeletionOnTable1 = changesAssertDeletion.changeOfDeletionOnTable("interpretation");
    ChangeAssert changeAssertDeletionOnTable0Bis = changesAssertDeletion.changeOfDeletionOnTable("actor", 0);
    ChangeAssert changeAssertDeletionOnTable1Bis = changesAssert.changeOfDeletionOnTable("interpretation", 0);

    Assertions.assertThat(changeAssertDeletionOnTable0).isSameAs(changeAssertDeletionOnTable0Bis);
    Assertions.assertThat(changeAssertDeletionOnTable1).isSameAs(changeAssertDeletionOnTable1Bis);

    ChangeAssert changeAssertOnTable0 = changesAssert.changeOnTable("actor");
    ChangeAssert changeAssertOnTable1 = changesAssertCreation.changeOnTable("actor");
    ChangeAssert changeAssertOnTable0Bis = changesAssertCreation.changeOnTable("actor", 0);
    ChangeAssert changeAssertOnTable1Bis = changesAssert.changeOnTable("actor", 1);

    Assertions.assertThat(changeAssertOnTable0).isSameAs(changeAssertOnTable0Bis);
    Assertions.assertThat(changeAssertOnTable1).isSameAs(changeAssertOnTable1Bis);

    ChangeAssert changeAssertOnTableWithPks0 = changesAssert.changeOnTableWithPks("actor", 1);
    ChangeAssert changeAssertOnTableWithPks1 = changesAssertCreation.changeOnTableWithPks("actor", 3);
    ChangeAssert changeAssertOnTableWithPks0Bis = changesAssertCreation.changeOnTableWithPks("actor", 1);
    ChangeAssert changeAssertOnTableWithPks1Bis = changesAssert.changeOnTableWithPks("actor", 3);

    Assertions.assertThat(changeAssertOnTableWithPks0).isSameAs(changeAssertOnTableWithPks0Bis);
    Assertions.assertThat(changeAssertOnTableWithPks1).isSameAs(changeAssertOnTableWithPks1Bis);
  }

  /**
   * This method tests the {@code ToChange} navigation interfaces.
   */
  @Test
  @NeedReload
  public void test_to_change_navigation_with_displays() throws Exception {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    Field fieldChanges = ChangesOutputter.class.getDeclaredField("changes");
    fieldChanges.setAccessible(true);

    ChangesOutputter changesDisplay = output(changes);
    ChangesOutputter changesDisplayCreation = changesDisplay.ofCreation();
    ChangesOutputter changesDisplayModification = changesDisplay.ofModification();
    ChangesOutputter changesDisplayDeletion = changesDisplay.ofDeletion();

    ChangeOutputter changeDisplayCreation0 = changesDisplay.changeOfCreation();
    ChangeOutputter changeDisplayCreation1 = changesDisplayCreation.changeOfCreation();
    ChangeOutputter changeDisplayCreation0Bis = changesDisplayCreation.changeOfCreation(0);
    ChangeOutputter changeDisplayCreation1Bis = changesDisplay.changeOfCreation(1);

    Assertions.assertThat(changeDisplayCreation0).isSameAs(changeDisplayCreation0Bis);
    Assertions.assertThat(changeDisplayCreation1).isSameAs(changeDisplayCreation1Bis);

    ChangeOutputter changeDisplayModification0 = changesDisplay.changeOfModification();
    ChangeOutputter changeDisplayModification1 = changesDisplayModification.changeOfModification();
    ChangeOutputter changeDisplayModification0Bis = changesDisplayModification.changeOfModification(0);
    ChangeOutputter changeDisplayModification1Bis = changesDisplay.changeOfModification(1);

    Assertions.assertThat(changeDisplayModification0).isSameAs(changeDisplayModification0Bis);
    Assertions.assertThat(changeDisplayModification1).isSameAs(changeDisplayModification1Bis);

    ChangeOutputter changeDisplayDeletion0 = changesDisplay.changeOfDeletion();
    ChangeOutputter changeDisplayDeletion1 = changesDisplayDeletion.changeOfDeletion();
    ChangeOutputter changeDisplayDeletion0Bis = changesDisplayDeletion.changeOfDeletion(0);
    ChangeOutputter changeDisplayDeletion1Bis = changesDisplay.changeOfDeletion(1);

    Assertions.assertThat(changeDisplayDeletion0).isSameAs(changeDisplayDeletion0Bis);
    Assertions.assertThat(changeDisplayDeletion1).isSameAs(changeDisplayDeletion1Bis);

    ChangeOutputter changeDisplayCreationOnTable0 = changesDisplay.changeOfCreationOnTable("actor");
    ChangeOutputter changeDisplayCreationOnTable1 = changesDisplayCreation.changeOfCreationOnTable("interpretation");
    ChangeOutputter changeDisplayCreationOnTable0Bis = changesDisplayCreation.changeOfCreationOnTable("actor", 0);
    ChangeOutputter changeDisplayCreationOnTable1Bis = changesDisplay.changeOfCreationOnTable("interpretation", 0);

    Assertions.assertThat(changeDisplayCreationOnTable0).isSameAs(changeDisplayCreationOnTable0Bis);
    Assertions.assertThat(changeDisplayCreationOnTable1).isSameAs(changeDisplayCreationOnTable1Bis);

    ChangeOutputter changeDisplayModificationOnTable0 = changesDisplay.changeOfModificationOnTable("actor");
    ChangeOutputter changeDisplayModificationOnTable1 = changesDisplayModification.changeOfModificationOnTable(
            "interpretation");
    ChangeOutputter changeDisplayModificationOnTable0Bis = changesDisplayModification.changeOfModificationOnTable("actor", 0);
    ChangeOutputter changeDisplayModificationOnTable1Bis = changesDisplay.changeOfModificationOnTable("interpretation", 0);

    Assertions.assertThat(changeDisplayModificationOnTable0).isSameAs(changeDisplayModificationOnTable0Bis);
    Assertions.assertThat(changeDisplayModificationOnTable1).isSameAs(changeDisplayModificationOnTable1Bis);

    ChangeOutputter changeDisplayDeletionOnTable0 = changesDisplay.changeOfDeletionOnTable("actor");
    ChangeOutputter changeDisplayDeletionOnTable1 = changesDisplayDeletion.changeOfDeletionOnTable("interpretation");
    ChangeOutputter changeDisplayDeletionOnTable0Bis = changesDisplayDeletion.changeOfDeletionOnTable("actor", 0);
    ChangeOutputter changeDisplayDeletionOnTable1Bis = changesDisplay.changeOfDeletionOnTable("interpretation", 0);

    Assertions.assertThat(changeDisplayDeletionOnTable0).isSameAs(changeDisplayDeletionOnTable0Bis);
    Assertions.assertThat(changeDisplayDeletionOnTable1).isSameAs(changeDisplayDeletionOnTable1Bis);

    ChangeOutputter changeDisplayOnTable0 = changesDisplay.changeOnTable("actor");
    ChangeOutputter changeDisplayOnTable1 = changesDisplayCreation.changeOnTable("actor");
    ChangeOutputter changeDisplayOnTable0Bis = changesDisplayCreation.changeOnTable("actor", 0);
    ChangeOutputter changeDisplayOnTable1Bis = changesDisplay.changeOnTable("actor", 1);

    Assertions.assertThat(changeDisplayOnTable0).isSameAs(changeDisplayOnTable0Bis);
    Assertions.assertThat(changeDisplayOnTable1).isSameAs(changeDisplayOnTable1Bis);

    ChangeOutputter changeDisplayOnTableWithPks0 = changesDisplay.changeOnTableWithPks("actor", 1);
    ChangeOutputter changeDisplayOnTableWithPks1 = changesDisplayCreation.changeOnTableWithPks("actor", 3);
    ChangeOutputter changeDisplayOnTableWithPks0Bis = changesDisplayCreation.changeOnTableWithPks("actor", 1);
    ChangeOutputter changeDisplayOnTableWithPks1Bis = changesDisplay.changeOnTableWithPks("actor", 3);

    Assertions.assertThat(changeDisplayOnTableWithPks0).isSameAs(changeDisplayOnTableWithPks0Bis);
    Assertions.assertThat(changeDisplayOnTableWithPks1).isSameAs(changeDisplayOnTableWithPks1Bis);
  }
}
