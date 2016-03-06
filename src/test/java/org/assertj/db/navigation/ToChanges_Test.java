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
 * Copyright 2012-2015 the original author or authors.
 */
package org.assertj.db.navigation;

import org.assertj.core.api.Assertions;
import org.assertj.db.api.ChangeAssert;
import org.assertj.db.api.ChangesAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.assertj.db.display.ChangeDisplay;
import org.assertj.db.display.ChangesDisplay;
import org.assertj.db.type.Change;
import org.assertj.db.type.Changes;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.List;

import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.display.Displaying.display;

/**
 * Tests on {@link org.assertj.db.navigation.ToChanges} interface.
 *
 * @author Régis Pouiller
 *
 */
public class ToChanges_Test extends AbstractTest {

  /**
   * This method tests the {@code ToChanges} navigation interfaces.
   */
  @Test
  @NeedReload
  public void test_to_changes_navigation_with_assertions() throws Exception {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    Field fieldChanges = ChangesAssert.class.getDeclaredField("changes");
    fieldChanges.setAccessible(true);

    ChangesAssert changesAssert = assertThat(changes);
    ChangeAssert changeAssert = changesAssert.change();
    ChangesAssert changesAssertAll = changesAssert.ofAll();
    ChangesAssert changesAssertCreation = changesAssert.ofCreation();
    ChangesAssert changesAssertModification = changesAssert.ofModification();
    ChangesAssert changesAssertDeletion = changesAssert.ofDeletion();
    ChangesAssert changesAssertCreationOnTable = changesAssert.ofCreationOnTable("actor");
    ChangesAssert changesAssertModificationOnTable = changesAssert.ofModificationOnTable("actor");
    ChangesAssert changesAssertDeletionOnTable = changesAssert.ofDeletionOnTable("actor");
    ChangesAssert changesAssertOnTable = changesAssert.onTable("actor");
    ChangesAssert changesAssertAllBis = changesAssertCreation.ofAll();
    ChangesAssert changesAssertCreationBis = changesAssertCreation.ofCreation();
    ChangesAssert changesAssertModificationBis = changesAssertCreation.ofModification();
    ChangesAssert changesAssertDeletionBis = changesAssertCreation.ofDeletion();
    ChangesAssert changesAssertCreationOnTableBis = changesAssertCreation.ofCreationOnTable("actor");
    ChangesAssert changesAssertModificationOnTableBis = changesAssertCreation.ofModificationOnTable("actor");
    ChangesAssert changesAssertDeletionOnTableBis = changesAssertCreation.ofDeletionOnTable("actor");
    ChangesAssert changesAssertOnTableBis = changesAssertCreation.onTable("actor");
    ChangesAssert changesAssertAllTer = changeAssert.ofAll();
    ChangesAssert changesAssertCreationTer = changeAssert.ofCreation();
    ChangesAssert changesAssertModificationTer = changeAssert.ofModification();
    ChangesAssert changesAssertDeletionTer = changeAssert.ofDeletion();
    ChangesAssert changesAssertCreationOnTableTer = changeAssert.ofCreationOnTable("actor");
    ChangesAssert changesAssertModificationOnTableTer = changeAssert.ofModificationOnTable("actor");
    ChangesAssert changesAssertDeletionOnTableTer = changeAssert.ofDeletionOnTable("actor");
    ChangesAssert changesAssertOnTableTer = changeAssert.onTable("actor");

    Assertions.assertThat(changesAssert).isSameAs(changesAssertAll).isSameAs(changesAssertAllBis).isSameAs(changesAssertAllTer);
    Assertions.assertThat(changesAssertCreation).isSameAs(changesAssertCreationBis).isSameAs(changesAssertCreationTer);
    Assertions.assertThat(changesAssertModification).isSameAs(changesAssertModificationBis).isSameAs(changesAssertModificationTer);
    Assertions.assertThat(changesAssertDeletion).isSameAs(changesAssertDeletionBis).isSameAs(changesAssertDeletionTer);
    Assertions.assertThat(changesAssertCreationOnTable).isSameAs(changesAssertCreationOnTableBis).isSameAs(changesAssertCreationOnTableTer);
    Assertions.assertThat(changesAssertModificationOnTable).isSameAs(changesAssertModificationOnTableBis).isSameAs(changesAssertModificationOnTableTer);
    Assertions.assertThat(changesAssertDeletionOnTable).isSameAs(changesAssertDeletionOnTableBis).isSameAs(changesAssertDeletionOnTableTer);
    Assertions.assertThat(changesAssertOnTable).isSameAs(changesAssertOnTableBis).isSameAs(changesAssertOnTableTer);

    Changes changesAll = (Changes) fieldChanges.get(changesAssertAll);
    Changes changesCreation = (Changes) fieldChanges.get(changesAssertCreation);
    Changes changesModification = (Changes) fieldChanges.get(changesAssertModification);
    Changes changesDeletion = (Changes) fieldChanges.get(changesAssertDeletion);
    Changes changesCreationOnTable = (Changes) fieldChanges.get(changesAssertCreationOnTable);
    Changes changesModificationOnTable = (Changes) fieldChanges.get(changesAssertModificationOnTable);
    Changes changesDeletionOnTable = (Changes) fieldChanges.get(changesAssertDeletionOnTable);
    Changes changesOnTable = (Changes) fieldChanges.get(changesAssertOnTable);

    List<Change> changesList = changes.getChangesList();
    List<Change> changesAllList = changesAll.getChangesList();
    List<Change> changesCreationList = changesCreation.getChangesList();
    List<Change> changesModificationList = changesModification.getChangesList();
    List<Change> changesDeletionList = changesDeletion.getChangesList();
    List<Change> changesCreationOnTableList = changesCreationOnTable.getChangesList();
    List<Change> changesModificationOnTableList = changesModificationOnTable.getChangesList();
    List<Change> changesDeletionOnTableList = changesDeletionOnTable.getChangesList();
    List<Change> changesOnTableList = changesOnTable.getChangesList();

    Assertions.assertThat(changesAllList).containsExactlyElementsOf(changesList);
    Assertions.assertThat(changesCreationList).containsExactly(changesList.get(0), changesList.get(1),
                                                               changesList.get(2));
    Assertions.assertThat(changesModificationList).containsExactly(changesList.get(3), changesList.get(4), changesList.get(5));
    Assertions.assertThat(changesDeletionList).containsExactly(changesList.get(6), changesList.get(7));
    Assertions.assertThat(changesCreationOnTableList).containsExactly(changesList.get(0));
    Assertions.assertThat(changesModificationOnTableList).containsExactly(changesList.get(3));
    Assertions.assertThat(changesDeletionOnTableList).containsExactly(changesList.get(6));
    Assertions.assertThat(changesOnTableList).containsExactly(changesList.get(0), changesList.get(3), changesList.get(6));
  }

  /**
   * This method tests the {@code ToChanges} navigation interfaces.
   */
  @Test
  @NeedReload
  public void test_to_changes_navigation_with_displays() throws Exception {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    Field fieldChanges = ChangesDisplay.class.getDeclaredField("changes");
    fieldChanges.setAccessible(true);

    ChangesDisplay changesDisplay = display(changes);
    ChangeDisplay changeDisplay = changesDisplay.change();
    ChangesDisplay changesDisplayAll = changesDisplay.ofAll();
    ChangesDisplay changesDisplayCreation = changesDisplay.ofCreation();
    ChangesDisplay changesDisplayModification = changesDisplay.ofModification();
    ChangesDisplay changesDisplayDeletion = changesDisplay.ofDeletion();
    ChangesDisplay changesDisplayCreationOnTable = changesDisplay.ofCreationOnTable("actor");
    ChangesDisplay changesDisplayModificationOnTable = changesDisplay.ofModificationOnTable("actor");
    ChangesDisplay changesDisplayDeletionOnTable = changesDisplay.ofDeletionOnTable("actor");
    ChangesDisplay changesDisplayOnTable = changesDisplay.onTable("actor");
    ChangesDisplay changesDisplayAllBis = changesDisplayCreation.ofAll();
    ChangesDisplay changesDisplayCreationBis = changesDisplayCreation.ofCreation();
    ChangesDisplay changesDisplayModificationBis = changesDisplayCreation.ofModification();
    ChangesDisplay changesDisplayDeletionBis = changesDisplayCreation.ofDeletion();
    ChangesDisplay changesDisplayCreationOnTableBis = changesDisplayCreation.ofCreationOnTable("actor");
    ChangesDisplay changesDisplayModificationOnTableBis = changesDisplayCreation.ofModificationOnTable("actor");
    ChangesDisplay changesDisplayDeletionOnTableBis = changesDisplayCreation.ofDeletionOnTable("actor");
    ChangesDisplay changesDisplayOnTableBis = changesDisplayCreation.onTable("actor");
    ChangesDisplay changesDisplayAllTer = changeDisplay.ofAll();
    ChangesDisplay changesDisplayCreationTer = changeDisplay.ofCreation();
    ChangesDisplay changesDisplayModificationTer = changeDisplay.ofModification();
    ChangesDisplay changesDisplayDeletionTer = changeDisplay.ofDeletion();
    ChangesDisplay changesDisplayCreationOnTableTer = changeDisplay.ofCreationOnTable("actor");
    ChangesDisplay changesDisplayModificationOnTableTer = changeDisplay.ofModificationOnTable("actor");
    ChangesDisplay changesDisplayDeletionOnTableTer = changeDisplay.ofDeletionOnTable("actor");
    ChangesDisplay changesDisplayOnTableTer = changeDisplay.onTable("actor");

    Assertions.assertThat(changesDisplay).isSameAs(changesDisplayAll).isSameAs(changesDisplayAllBis).isSameAs(changesDisplayAllTer);
    Assertions.assertThat(changesDisplayCreation).isSameAs(changesDisplayCreationBis).isSameAs(changesDisplayCreationTer);
    Assertions.assertThat(changesDisplayModification).isSameAs(changesDisplayModificationBis).isSameAs(changesDisplayModificationTer);
    Assertions.assertThat(changesDisplayDeletion).isSameAs(changesDisplayDeletionBis).isSameAs(changesDisplayDeletionTer);
    Assertions.assertThat(changesDisplayCreationOnTable).isSameAs(changesDisplayCreationOnTableBis).isSameAs(changesDisplayCreationOnTableTer);
    Assertions.assertThat(changesDisplayModificationOnTable).isSameAs(changesDisplayModificationOnTableBis).isSameAs(changesDisplayModificationOnTableTer);
    Assertions.assertThat(changesDisplayDeletionOnTable).isSameAs(changesDisplayDeletionOnTableBis).isSameAs(changesDisplayDeletionOnTableTer);
    Assertions.assertThat(changesDisplayOnTable).isSameAs(changesDisplayOnTableBis).isSameAs(changesDisplayOnTableTer);

    Changes changesAll = (Changes) fieldChanges.get(changesDisplayAll);
    Changes changesCreation = (Changes) fieldChanges.get(changesDisplayCreation);
    Changes changesModification = (Changes) fieldChanges.get(changesDisplayModification);
    Changes changesDeletion = (Changes) fieldChanges.get(changesDisplayDeletion);
    Changes changesCreationOnTable = (Changes) fieldChanges.get(changesDisplayCreationOnTable);
    Changes changesModificationOnTable = (Changes) fieldChanges.get(changesDisplayModificationOnTable);
    Changes changesDeletionOnTable = (Changes) fieldChanges.get(changesDisplayDeletionOnTable);
    Changes changesOnTable = (Changes) fieldChanges.get(changesDisplayOnTable);

    List<Change> changesList = changes.getChangesList();
    List<Change> changesAllList = changesAll.getChangesList();
    List<Change> changesCreationList = changesCreation.getChangesList();
    List<Change> changesModificationList = changesModification.getChangesList();
    List<Change> changesDeletionList = changesDeletion.getChangesList();
    List<Change> changesCreationOnTableList = changesCreationOnTable.getChangesList();
    List<Change> changesModificationOnTableList = changesModificationOnTable.getChangesList();
    List<Change> changesDeletionOnTableList = changesDeletionOnTable.getChangesList();
    List<Change> changesOnTableList = changesOnTable.getChangesList();

    Assertions.assertThat(changesAllList).containsExactlyElementsOf(changesList);
    Assertions.assertThat(changesCreationList).containsExactly(changesList.get(0), changesList.get(1),
                                                               changesList.get(2));
    Assertions.assertThat(changesModificationList).containsExactly(changesList.get(3), changesList.get(4), changesList.get(5));
    Assertions.assertThat(changesDeletionList).containsExactly(changesList.get(6), changesList.get(7));
    Assertions.assertThat(changesCreationOnTableList).containsExactly(changesList.get(0));
    Assertions.assertThat(changesModificationOnTableList).containsExactly(changesList.get(3));
    Assertions.assertThat(changesDeletionOnTableList).containsExactly(changesList.get(6));
    Assertions.assertThat(changesOnTableList).containsExactly(changesList.get(0), changesList.get(3), changesList.get(6));
  }
}
