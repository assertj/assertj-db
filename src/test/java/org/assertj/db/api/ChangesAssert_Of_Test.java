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
 * Test on {@code ofCreation()}, {@code ofModification()}, {@code ofDeletion()}, {@code onTable()},
 * {@code ofCreationOnTable()}, {@code ofModificationOnTable()} and {@code ofDeletionOnTable()} methods.
 *
 * @author Régis Pouiller
 */
public class ChangesAssert_Of_Test extends AbstractTest {

  /**
   * This method test the assertion on the size of {@code Changes} on source.
   */
  @Test
  @NeedReload
  public void test_of_methods() {
    Changes changes = new Changes(source);

    changes.setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    assertThat(changes).hasSize(8).ofCreation().hasSize(3).ofModification().hasSize(3).ofDeletion().hasSize(2)
                       .onTable("movie").hasSize(2).onTable("actor").hasSize(3).ofCreationOnTable("movie").hasSize(1)
                       .ofModificationOnTable("movie").hasSize(1).ofDeletionOnTable("movie").hasSize(0).ofCreation()
                       .hasSize(3).ofAll().hasSize(8);
  }

  /**
   * This method test the {@code ofAll()} of {@code Change}.
   */
  @Test
  @NeedReload
  public void test_ofAll_methods() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    ChangesAssert changesAssert = assertThat(changes);
    ChangesAssert changesOfCreationAssert = changesAssert.ofCreation();
    ChangeAssert changeAssert = changesAssert.change();

    assertThat(changesAssert.ofAll()).as("changesAssert.ofAll()").isSameAs(changeAssert.ofAll())
                                     .isSameAs(changesOfCreationAssert.ofAll()).isSameAs(changesAssert);
  }

  /**
   * This method test the {@code ofCreation()} of {@code Change}.
   */
  @Test
  @NeedReload
  public void test_ofCreation_methods() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    ChangesAssert changesAssert = assertThat(changes);
    ChangesAssert changesOfCreationAssert = changesAssert.ofCreation();
    ChangeAssert changeAssert = changesAssert.change();

    assertThat(changesAssert.ofCreation()).as("changesAssert.ofCreation()").isSameAs(changeAssert.ofCreation())
                                          .isSameAs(changesOfCreationAssert.ofCreation());
  }

  /**
   * This method test the {@code ofModification()} of {@code Change}.
   */
  @Test
  @NeedReload
  public void test_ofModification_methods() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    ChangesAssert changesAssert = assertThat(changes);
    ChangesAssert changesOfCreationAssert = changesAssert.ofCreation();
    ChangeAssert changeAssert = changesAssert.change();

    assertThat(changesAssert.ofModification()).as("changesAssert.ofModification()")
                                              .isSameAs(changeAssert.ofModification())
                                              .isSameAs(changesOfCreationAssert.ofModification());
  }

  /**
   * This method test the {@code ofDeletion()} of {@code Change}.
   */
  @Test
  @NeedReload
  public void test_ofDeletion_methods() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    ChangesAssert changesAssert = assertThat(changes);
    ChangesAssert changesOfCreationAssert = changesAssert.ofCreation();
    ChangeAssert changeAssert = changesAssert.change();

    assertThat(changesAssert.ofDeletion()).as("changesAssert.ofDeletion()").isSameAs(changeAssert.ofDeletion())
                                          .isSameAs(changesOfCreationAssert.ofDeletion());
  }

  /**
   * This method test the {@code ofCreationOnTable()} of {@code Change}.
   */
  @Test
  @NeedReload
  public void test_ofCreationOnTable_methods() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    ChangesAssert changesAssert = assertThat(changes);
    ChangesAssert changesOfCreationAssert = changesAssert.ofCreation();
    ChangeAssert changeAssert = changesAssert.change();

    assertThat(changesAssert.ofCreationOnTable("actor")).as("changesAssert.ofCreationOnTable(\"actor\")")
                                                        .isSameAs(changeAssert.ofCreationOnTable("actor"))
                                                        .isSameAs(changesOfCreationAssert.ofCreationOnTable("actor"));
  }

  /**
   * This method test the {@code ofModificationOnTable()} of {@code Change}.
   */
  @Test
  @NeedReload
  public void test_ofModificationOnTable_methods() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    ChangesAssert changesAssert = assertThat(changes);
    ChangesAssert changesOfCreationAssert = changesAssert.ofCreation();
    ChangeAssert changeAssert = changesAssert.change();

    assertThat(changesAssert.ofModificationOnTable("actor")).as("changesAssert.ofModificationOnTable(\"actor\")")
                                                            .isSameAs(changeAssert.ofModificationOnTable("actor"))
                                                            .isSameAs(changesOfCreationAssert
                                                                              .ofModificationOnTable("actor"));
  }

  /**
   * This method test the {@code ofDeletionOnTable()} of {@code Change}.
   */
  @Test
  @NeedReload
  public void test_ofDeletionOnTable_methods() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    ChangesAssert changesAssert = assertThat(changes);
    ChangesAssert changesOfCreationAssert = changesAssert.ofCreation();
    ChangeAssert changeAssert = changesAssert.change();

    assertThat(changesAssert.ofDeletionOnTable("actor")).as("changesAssert.ofDeletionOnTable(\"actor\")")
                                                        .isSameAs(changeAssert.ofDeletionOnTable("actor"))
                                                        .isSameAs(changesOfCreationAssert.ofDeletionOnTable("actor"));
  }

  /**
   * This method test the {@code onTable()} of {@code Change}.
   */
  @Test
  @NeedReload
  public void test_onTable_methods() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    ChangesAssert changesAssert = assertThat(changes);
    ChangesAssert changesOfCreationAssert = changesAssert.ofCreation();
    ChangeAssert changeAssert = changesAssert.change();

    assertThat(changesAssert.onTable("actor")).as("changesAssert.onTable(\"actor\")")
                                              .isSameAs(changeAssert.onTable("actor"))
                                              .isSameAs(changesOfCreationAssert.onTable("actor"));
  }

}
