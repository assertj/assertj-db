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
 * Copyright 2015-2024 the original author or authors.
 */
package org.assertj.db.navigation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;
import static org.junit.Assert.fail;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.assertj.db.api.ChangeAssert;
import org.assertj.db.api.ChangesAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.assertj.db.exception.AssertJDBException;
import org.assertj.db.output.ChangeOutputter;
import org.assertj.db.output.ChangesOutputter;
import org.assertj.db.type.ChangeType;
import org.assertj.db.type.Changes;
import org.junit.Test;

/**
 * Tests on {@link ToChange} class :
 * {@link ToChange#changeOfDeletionOnTable(String, int)} method.
 *
 * @author Régis Pouiller
 * @author Julien Roy
 */
public class ToChange_ChangeOfDeletionOnTable_Integer_Test extends AbstractTest {

  /**
   * This method tests the {@code changeOfDeletionOnTable} navigation method.
   */
  @Test
  @NeedReload
  public void test_change_of_deletion_on_table_with_index_with_assertions() throws Exception {
    Changes changes = new Changes(jdbcConnectionProvider).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    Field fieldPosition = ChangesAssert.class.getDeclaredField("changesPosition");
    fieldPosition.setAccessible(true);
    Field fieldList = Changes.class.getDeclaredField("changesList");
    fieldList.setAccessible(true);
    Field fieldIndex = PositionWithChanges.class.getDeclaredField("indexNextChangeMap");
    fieldIndex.setAccessible(true);
    Field fieldChange = ChangeAssert.class.getDeclaredField("change");
    fieldChange.setAccessible(true);

    ChangesAssert changesAssert = assertThat(changes);
    PositionWithChanges<ChangesAssert, ChangeAssert> position =
      (PositionWithChanges) fieldPosition.get(changesAssert);
    Map<ChangeType, Map<String, Integer>> map = (Map<ChangeType, Map<String, Integer>>) fieldIndex.get(position);
    assertThat(map).hasSize(0);
    assertThat(map.get(null)).isNull();
    ChangeAssert changeAssert0 = changesAssert.changeOfDeletionOnTable("actor", 0);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).isNull();
    assertThat(map.get(ChangeType.DELETION)).hasSize(1);
    assertThat(map.get(ChangeType.DELETION).get("actor")).isEqualTo(1);
    ChangeAssert changeAssert1 = changesAssert.changeOfDeletionOnTable("interpretation", 0);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).isNull();
    assertThat(map.get(ChangeType.DELETION)).hasSize(2);
    assertThat(map.get(ChangeType.DELETION).get("interpretation")).isEqualTo(1);
    try {
      changesAssert.changeOfDeletionOnTable("actor", 4);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 4 out of the limits [0, 1[");
    }
    try {
      changesAssert.changeOfDeletionOnTable("actor", -1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index -1 out of the limits [0, 1[");
    }
    ChangeAssert changeAssertAgain0 = changesAssert.changeOfDeletionOnTable("actor", 0);
    assertThat(changeAssert0).isSameAs(changeAssertAgain0);

    ChangesAssert changesAssertBis = assertThat(changes);
    PositionWithChanges<ChangesAssert, ChangeAssert> positionBis =
      (PositionWithChanges) fieldPosition.get(changesAssertBis);
    map = (Map<ChangeType, Map<String, Integer>>) fieldIndex.get(positionBis);
    assertThat(map).hasSize(0);
    assertThat(map.get(null)).isNull();
    ChangeAssert changeAssertBis0 = changesAssertBis.changeOfDeletionOnTable("actor", 0);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).isNull();
    assertThat(map.get(ChangeType.DELETION)).hasSize(1);
    assertThat(map.get(ChangeType.DELETION).get("actor")).isEqualTo(1);
    ChangeAssert changeAssertBis1 = changeAssertBis0.changeOfDeletionOnTable("interpretation", 0);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).isNull();
    assertThat(map.get(ChangeType.DELETION)).hasSize(2);
    assertThat(map.get(ChangeType.DELETION).get("interpretation")).isEqualTo(1);
    try {
      changeAssertBis1.changeOfDeletionOnTable("interpretation", 2);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 2 out of the limits [0, 1[");
    }
    try {
      changeAssertBis1.changeOfDeletionOnTable("interpretation", -1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index -1 out of the limits [0, 1[");
    }
    ChangeAssert changeAssertBisAgain0 = changeAssertBis1.changeOfDeletionOnTable("actor", 0);
    assertThat(changeAssertBis0).isSameAs(changeAssertBisAgain0);

    List<Changes> changesList = (List<Changes>) fieldList.get(changes);
    assertThat(fieldChange.get(changeAssert0)).isSameAs(fieldChange.get(changeAssertBis0)).isSameAs(changesList.get(6));
    assertThat(fieldChange.get(changeAssert1)).isSameAs(fieldChange.get(changeAssertBis1)).isSameAs(changesList.get(7));
  }

  /**
   * This method tests the {@code changeOfDeletionOnTable} navigation method.
   */
  @Test
  @NeedReload
  public void test_change_of_deletion_on_table_with_index_with_displays() throws Exception {
    Changes changes = new Changes(jdbcConnectionProvider).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    Field fieldPosition = ChangesOutputter.class.getDeclaredField("changesPosition");
    fieldPosition.setAccessible(true);
    Field fieldList = Changes.class.getDeclaredField("changesList");
    fieldList.setAccessible(true);
    Field fieldIndex = PositionWithChanges.class.getDeclaredField("indexNextChangeMap");
    fieldIndex.setAccessible(true);
    Field fieldChange = ChangeOutputter.class.getDeclaredField("change");
    fieldChange.setAccessible(true);

    ChangesOutputter changesDisplay = output(changes);
    PositionWithChanges<ChangesAssert, ChangeAssert> position =
      (PositionWithChanges) fieldPosition.get(changesDisplay);
    Map<ChangeType, Map<String, Integer>> map = (Map<ChangeType, Map<String, Integer>>) fieldIndex.get(position);
    assertThat(map).hasSize(0);
    assertThat(map.get(null)).isNull();
    ChangeOutputter changeDisplay0 = changesDisplay.changeOfDeletionOnTable("actor", 0);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).isNull();
    assertThat(map.get(ChangeType.DELETION)).hasSize(1);
    assertThat(map.get(ChangeType.DELETION).get("actor")).isEqualTo(1);
    ChangeOutputter changeDisplay1 = changesDisplay.changeOfDeletionOnTable("interpretation", 0);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).isNull();
    assertThat(map.get(ChangeType.DELETION)).hasSize(2);
    assertThat(map.get(ChangeType.DELETION).get("interpretation")).isEqualTo(1);
    try {
      changesDisplay.changeOfDeletionOnTable("actor", 4);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 4 out of the limits [0, 1[");
    }
    try {
      changesDisplay.changeOfDeletionOnTable("actor", -1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index -1 out of the limits [0, 1[");
    }
    ChangeOutputter changeDisplayAgain0 = changesDisplay.changeOfDeletionOnTable("actor", 0);
    assertThat(changeDisplay0).isSameAs(changeDisplayAgain0);

    ChangesOutputter changesDisplayBis = output(changes);
    PositionWithChanges<ChangesAssert, ChangeAssert> positionBis =
      (PositionWithChanges) fieldPosition.get(changesDisplayBis);
    map = (Map<ChangeType, Map<String, Integer>>) fieldIndex.get(positionBis);
    assertThat(map).hasSize(0);
    assertThat(map.get(null)).isNull();
    ChangeOutputter changeDisplayBis0 = changesDisplayBis.changeOfDeletionOnTable("actor", 0);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).isNull();
    assertThat(map.get(ChangeType.DELETION)).hasSize(1);
    assertThat(map.get(ChangeType.DELETION).get("actor")).isEqualTo(1);
    ChangeOutputter changeDisplayBis1 = changeDisplayBis0.changeOfDeletionOnTable("interpretation", 0);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).isNull();
    assertThat(map.get(ChangeType.DELETION)).hasSize(2);
    assertThat(map.get(ChangeType.DELETION).get("interpretation")).isEqualTo(1);
    try {
      changeDisplayBis1.changeOfDeletionOnTable("interpretation", 2);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 2 out of the limits [0, 1[");
    }
    try {
      changeDisplayBis1.changeOfDeletionOnTable("interpretation", -1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index -1 out of the limits [0, 1[");
    }
    ChangeOutputter changeDisplayBisAgain0 = changeDisplayBis1.changeOfDeletionOnTable("actor", 0);
    assertThat(changeDisplayBis0).isSameAs(changeDisplayBisAgain0);

    List<Changes> changesList = (List<Changes>) fieldList.get(changes);
    assertThat(fieldChange.get(changeDisplay0)).isSameAs(fieldChange.get(changeDisplayBis0)).isSameAs(changesList.get(6));
    assertThat(fieldChange.get(changeDisplay1)).isSameAs(fieldChange.get(changeDisplayBis1)).isSameAs(changesList.get(7));
  }
}
