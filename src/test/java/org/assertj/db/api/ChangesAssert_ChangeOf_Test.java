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

/**
 * Test on {@code changeOfCreation()}, {@code changeOfModification()} and {@code changeOfDeletion()} methods.
 *
 * @author Régis Pouiller
 */
public class ChangesAssert_ChangeOf_Test extends AbstractTest {

  /**
   * This method test the {@code changeOfCreation()} of {@code Change}.
   */
  @Test
  @NeedReload
  public void test_changeOfCreation_methods() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    ChangesAssert changesAssert = assertThat(changes);
    ChangesAssert changesOfCreationAssert = changesAssert.ofCreation();
    ChangeAssert changeAssert = changesAssert.change();

    assertThat(changesAssert.changeOfCreation()).as("changesAssert.changeOfCreation() - 1")
                                                .isSameAs(changeAssert.changeOfCreation(0))
                                                .as("changesAssert.changeOfCreation() - 2")
                                                .isSameAs(changesOfCreationAssert.changeOfCreation(0))
                                                .as("changesAssert.changeOfCreation() - 3")
                                                .isSameAs(changesAssert.change(0));
  }

  /**
   * This method test the {@code changeOfModification()} of {@code Change}.
   */
  @Test
  @NeedReload
  public void test_changeOfModification_methods() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    ChangesAssert changesAssert = assertThat(changes);
    ChangesAssert changesOfCreationAssert = changesAssert.ofCreation();
    ChangeAssert changeAssert = changesAssert.change();

    assertThat(changesAssert.changeOfModification()).as("changesAssert.changeOfModification() - 1")
                                                    .isSameAs(changeAssert.changeOfModification(0))
                                                    .as("changesAssert.changeOfModification() - 2")
                                                    .isSameAs(changesOfCreationAssert.changeOfModification(0))
                                                    .as("changesAssert.changeOfModification() - 3")
                                                    .isSameAs(changesAssert.change(3));
  }

  /**
   * This method test the {@code changeOfDeletion()} of {@code Change}.
   */
  @Test
  @NeedReload
  public void test_changeOfDeletion_methods() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    ChangesAssert changesAssert = assertThat(changes);
    ChangesAssert changesOfCreationAssert = changesAssert.ofCreation();
    ChangeAssert changeAssert = changesAssert.change();

    assertThat(changesAssert.changeOfDeletion()).as("changesAssert.changeOfDeletion() - 1")
                                                .isSameAs(changeAssert.changeOfDeletion(0))
                                                .as("changesAssert.changeOfDeletion() - 2")
                                                .isSameAs(changesOfCreationAssert.changeOfDeletion(0))
                                                .as("changesAssert.changeOfDeletion() - 3")
                                                .isSameAs(changesAssert.change(6));
  }

  /**
   * This method test the {@code changeOnTable()} of {@code Change}.
   */
  @Test
  @NeedReload
  public void test_changeOnTable_methods() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    ChangesAssert changesAssert = assertThat(changes);
    ChangesAssert changesOfCreationAssert = changesAssert.ofCreation();
    ChangeAssert changeAssert = changesAssert.change();

    assertThat(changesAssert.changeOnTable("interpretation")).as("changesAssert.changeOnTable(\"interpretation\")")
                                                             .isSameAs(changeAssert.changeOnTable("interpretation", 0))
                                                             .isSameAs(changesOfCreationAssert
                                                                               .changeOnTable("interpretation", 0));
  }

  /**
   * This method test the {@code changeOfCreationOnTable()} of {@code Change}.
   */
  @Test
  @NeedReload
  public void test_changeOfCreationOnTable_methods() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    ChangesAssert changesAssert = assertThat(changes);
    ChangesAssert changesOfCreationAssert = changesAssert.ofCreation();
    ChangeAssert changeAssert = changesAssert.change();

    assertThat(changesAssert.changeOfCreationOnTable("interpretation"))
            .as("changesAssert.changeOfCreationOnTable(\"interpretation\")")
            .isSameAs(changeAssert.changeOfCreationOnTable("interpretation", 0))
            .isSameAs(changesOfCreationAssert.changeOfCreationOnTable("interpretation", 0));
  }

  /**
   * This method test the {@code changeOfModificationOnTable()} of {@code Change}.
   */
  @Test
  @NeedReload
  public void test_changeOfModificationOnTable_methods() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    ChangesAssert changesAssert = assertThat(changes);
    ChangesAssert changesOfCreationAssert = changesAssert.ofCreation();
    ChangeAssert changeAssert = changesAssert.change();

    assertThat(changesAssert.changeOfModificationOnTable("interpretation"))
            .as("changesAssert.changeOfModificationOnTable(\"interpretation\")")
            .isSameAs(changeAssert.changeOfModificationOnTable("interpretation", 0))
            .isSameAs(changesOfCreationAssert.changeOfModificationOnTable("interpretation", 0));
  }

  /**
   * This method test the {@code changeOfDeletionOnTable()} of {@code Change}.
   */
  @Test
  @NeedReload
  public void test_changeOfDeletionOnTable_methods() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    ChangesAssert changesAssert = assertThat(changes);
    ChangesAssert changesOfCreationAssert = changesAssert.ofCreation();
    ChangeAssert changeAssert = changesAssert.change();

    assertThat(changesAssert.changeOfDeletionOnTable("interpretation"))
            .as("changesAssert.changeOfDeletionOnTable(\"interpretation\")")
            .isSameAs(changeAssert.changeOfDeletionOnTable("interpretation", 0))
            .isSameAs(changesOfCreationAssert.changeOfDeletionOnTable("interpretation", 0));
  }

  /**
   * This method test the {@code changeOfCreation()} of {@code Change}.
   */
  @Test
  @NeedReload
  public void test_changeOfCreation_index_methods() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    ChangesAssert changesAssert = assertThat(changes);
    ChangeAssert changeAssert = changesAssert.change();

    assertThat(changesAssert.changeOfCreation(0)).as("changesAssert.changeOfCreation(0)")
                                                 .isSameAs(changeAssert.changeOfCreation(0));
  }

  /**
   * This method test the {@code changeOfModification()} of {@code Change}.
   */
  @Test
  @NeedReload
  public void test_changeOfModification_index_methods() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    ChangesAssert changesAssert = assertThat(changes);
    ChangeAssert changeAssert = changesAssert.change();

    assertThat(changesAssert.changeOfModification(0)).as("changesAssert.changeOfModification(0)")
                                                     .isSameAs(changeAssert.changeOfModification(0));
  }

  /**
   * This method test the {@code changeOfDeletion()} of {@code Change}.
   */
  @Test
  @NeedReload
  public void test_changeOfDeletion_index_methods() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    ChangesAssert changesAssert = assertThat(changes);
    ChangeAssert changeAssert = changesAssert.change();

    assertThat(changesAssert.changeOfDeletion(0)).as("changesAssert.changeOfDeletion(0)")
                                                 .isSameAs(changeAssert.changeOfDeletion(0));
  }

  /**
   * This method test the {@code changeOnTable()} of {@code Change}.
   */
  @Test
  @NeedReload
  public void test_changeOnTable_index_methods() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    ChangesAssert changesAssert = assertThat(changes);
    ChangeAssert changeAssert = changesAssert.change();

    assertThat(changesAssert.changeOnTable("interpretation", 0))
            .as("changesAssert.changeOnTable(\"interpretation\", 0)")
            .isSameAs(changeAssert.changeOnTable("interpretation", 0));
  }

  /**
   * This method test the {@code changeOfCreationOnTable()} of {@code Change}.
   */
  @Test
  @NeedReload
  public void test_changeOfCreationOnTable_index_methods() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    ChangesAssert changesAssert = assertThat(changes);
    ChangeAssert changeAssert = changesAssert.change();

    assertThat(changesAssert.changeOfCreationOnTable("interpretation", 0))
            .as("changesAssert.changeOfCreationOnTable(\"interpretation\", 0)")
            .isSameAs(changeAssert.changeOfCreationOnTable("interpretation", 0));
  }

  /**
   * This method test the {@code changeOfModificationOnTable()} of {@code Change}.
   */
  @Test
  @NeedReload
  public void test_changeOfModificationOnTable_index_methods() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    ChangesAssert changesAssert = assertThat(changes);
    ChangeAssert changeAssert = changesAssert.change();

    assertThat(changesAssert.changeOfModificationOnTable("interpretation", 0))
            .as("changesAssert.changeOfModificationOnTable(\"interpretation\", 0)")
            .isSameAs(changeAssert.changeOfModificationOnTable("interpretation", 0));
  }

  /**
   * This method test the {@code changeOfDeletionOnTable()} of {@code Change}.
   */
  @Test
  @NeedReload
  public void test_changeOfDeletionOnTable_index_methods() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    ChangesAssert changesAssert = assertThat(changes);
    ChangeAssert changeAssert = changesAssert.change();

    assertThat(changesAssert.changeOfDeletionOnTable("interpretation", 0))
            .as("changesAssert.changeOfDeletionOnTable(\"interpretation\", 0)")
            .isSameAs(changeAssert.changeOfDeletionOnTable("interpretation", 0));
  }

  /**
   * This method test the {@code changeOfCreation()} of {@code Change}.
   */
  @Test
  @NeedReload
  public void test_changeOfCreation_origin_methods() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    ChangesAssert changesAssert = assertThat(changes);
    ChangesAssert changesOfCreationAssert = changesAssert.ofCreation();
    ChangeAssert changeAssert = changesAssert.change();

    assertThat(changesOfCreationAssert.changeOfCreation()).as("changesOfCreationAssert.changeOfCreation()")
                                                          .isSameAs(changeAssert.changeOfCreation(0))
                                                          .isSameAs(changesAssert.changeOfCreation(0));
  }

  /**
   * This method test the {@code changeOfModification()} of {@code Change}.
   */
  @Test
  @NeedReload
  public void test_changeOfModification_origin_methods() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    ChangesAssert changesAssert = assertThat(changes);
    ChangesAssert changesOfCreationAssert = changesAssert.ofCreation();
    ChangeAssert changeAssert = changesAssert.change();

    assertThat(changesOfCreationAssert.changeOfModification()).as("changesOfCreationAssert.changeOfModification()")
                                                              .isSameAs(changeAssert.changeOfModification(0))
                                                              .isSameAs(changesAssert.changeOfModification(0));
  }

  /**
   * This method test the {@code changeOfDeletion()} of {@code Change}.
   */
  @Test
  @NeedReload
  public void test_changeOfDeletion_origin_methods() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    ChangesAssert changesAssert = assertThat(changes);
    ChangesAssert changesOfCreationAssert = changesAssert.ofCreation();
    ChangeAssert changeAssert = changesAssert.change();

    assertThat(changesOfCreationAssert.changeOfDeletion()).as("changesOfCreationAssert.changeOfDeletion()")
                                                          .isSameAs(changeAssert.changeOfDeletion(0))
                                                          .isSameAs(changesAssert.changeOfDeletion(0));
  }

  /**
   * This method test the {@code changeOnTable()} of {@code Change}.
   */
  @Test
  @NeedReload
  public void test_changeOnTable_origin_methods() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    ChangesAssert changesAssert = assertThat(changes);
    ChangesAssert changesOfCreationAssert = changesAssert.ofCreation();
    ChangeAssert changeAssert = changesAssert.change();

    assertThat(changesOfCreationAssert.changeOnTable("interpretation"))
            .as("changesAssert.changeOnTable(\"interpretation\")")
            .isSameAs(changeAssert.changeOnTable("interpretation", 0))
            .isSameAs(changesAssert.changeOnTable("interpretation", 0));
  }

  /**
   * This method test the {@code changeOfCreationOnTable()} of {@code Change}.
   */
  @Test
  @NeedReload
  public void test_changeOfCreationOnTable_origin_methods() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    ChangesAssert changesAssert = assertThat(changes);
    ChangesAssert changesOfCreationAssert = changesAssert.ofCreation();
    ChangeAssert changeAssert = changesAssert.change();

    assertThat(changesOfCreationAssert.changeOfCreationOnTable("interpretation"))
            .as("changesAssert.changeOfCreationOnTable(\"interpretation\")")
            .isSameAs(changeAssert.changeOfCreationOnTable("interpretation", 0))
            .isSameAs(changesAssert.changeOfCreationOnTable("interpretation", 0));
  }

  /**
   * This method test the {@code changeOfModificationOnTable()} of {@code Change}.
   */
  @Test
  @NeedReload
  public void test_changeOfModificationOnTable_origin_methods() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    ChangesAssert changesAssert = assertThat(changes);
    ChangesAssert changesOfCreationAssert = changesAssert.ofCreation();
    ChangeAssert changeAssert = changesAssert.change();

    assertThat(changesOfCreationAssert.changeOfModificationOnTable("interpretation"))
            .as("changesAssert.changeOfModificationOnTable(\"interpretation\")")
            .isSameAs(changeAssert.changeOfModificationOnTable("interpretation", 0))
            .isSameAs(changesAssert.changeOfModificationOnTable("interpretation", 0));
  }

  /**
   * This method test the {@code changeOfDeletionOnTable()} of {@code Change}.
   */
  @Test
  @NeedReload
  public void test_changeOfDeletionOnTable_origin_methods() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    ChangesAssert changesAssert = assertThat(changes);
    ChangesAssert changesOfCreationAssert = changesAssert.ofCreation();
    ChangeAssert changeAssert = changesAssert.change();

    assertThat(changesOfCreationAssert.changeOfDeletionOnTable("interpretation"))
            .as("changesAssert.changeOfDeletionOnTable(\"interpretation\")")
            .isSameAs(changeAssert.changeOfDeletionOnTable("interpretation", 0))
            .isSameAs(changesAssert.changeOfDeletionOnTable("interpretation", 0));
  }
}
