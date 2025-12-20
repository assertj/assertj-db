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
 * Copyright 2015-2025 the original author or authors.
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
 * Tests on {@link org.assertj.db.navigation.ToChange} class :
 * {@link org.assertj.db.navigation.ToChange#changeOfDeletion(int)} method.
 *
 * @author Régis Pouiller
 * @author Julien Roy
 */
public class ToChange_ChangeOfDeletion_Integer_Test extends AbstractTest {

  /**
   * This method tests the {@code changeOfDeletion} with index navigation method.
   */
  @Test
  @NeedReload
  public void test_change_of_deletion_with_index_with_assertions() throws Exception {
    Changes changes = assertDbConnection.changes().build().setStartPointNow();
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
    ChangeAssert changeAssert0 = changesAssert.changeOfDeletion(0);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).isNull();
    assertThat(map.get(ChangeType.DELETION)).hasSize(1);
    assertThat(map.get(ChangeType.DELETION).get(null)).isEqualTo(1);
    ChangeAssert changeAssert1 = changesAssert.changeOfDeletion(1);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).isNull();
    assertThat(map.get(ChangeType.DELETION)).hasSize(1);
    assertThat(map.get(ChangeType.DELETION).get(null)).isEqualTo(2);
    try {
      changesAssert.changeOfDeletion(2);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 2 out of the limits [0, 2[");
    }
    try {
      changesAssert.changeOfDeletion(-1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index -1 out of the limits [0, 2[");
    }
    ChangeAssert changeAssertAgain0 = changesAssert.changeOfDeletion(0);
    assertThat(changeAssert0).isSameAs(changeAssertAgain0);

    ChangesAssert changesAssertBis = assertThat(changes);
    PositionWithChanges<ChangesAssert, ChangeAssert> positionBis =
      (PositionWithChanges) fieldPosition.get(changesAssertBis);
    map = (Map<ChangeType, Map<String, Integer>>) fieldIndex.get(positionBis);
    assertThat(map).hasSize(0);
    assertThat(map.get(null)).isNull();
    ChangeAssert changeAssertBis0 = changesAssertBis.changeOfDeletion(0);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).isNull();
    assertThat(map.get(ChangeType.DELETION)).hasSize(1);
    assertThat(map.get(ChangeType.DELETION).get(null)).isEqualTo(1);
    ChangeAssert changeAssertBis1 = changeAssertBis0.changeOfDeletion(1);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).isNull();
    assertThat(map.get(ChangeType.DELETION)).hasSize(1);
    assertThat(map.get(ChangeType.DELETION).get(null)).isEqualTo(2);
    try {
      changeAssertBis1.changeOfDeletion(2);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 2 out of the limits [0, 2[");
    }
    try {
      changeAssertBis1.changeOfDeletion(-1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index -1 out of the limits [0, 2[");
    }
    ChangeAssert changeAssertBisAgain0 = changeAssertBis1.changeOfDeletion(0);
    assertThat(changeAssertBis0).isSameAs(changeAssertBisAgain0);

    List<Changes> changesList = (List<Changes>) fieldList.get(changes);
    assertThat(fieldChange.get(changeAssert0)).isSameAs(fieldChange.get(changeAssertBis0)).isSameAs(changesList.get(6));
    assertThat(fieldChange.get(changeAssert1)).isSameAs(fieldChange.get(changeAssertBis1)).isSameAs(changesList.get(7));
  }

  /**
   * This method tests the {@code changeOfDeletion} with index navigation method.
   */
  @Test
  @NeedReload
  public void test_change_of_deletion_with_index_with_displays() throws Exception {
    Changes changes = assertDbConnection.changes().build().setStartPointNow();
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

    ChangesOutputter changesOutputter = output(changes);
    PositionWithChanges<ChangesAssert, ChangeAssert> position =
      (PositionWithChanges) fieldPosition.get(changesOutputter);
    Map<ChangeType, Map<String, Integer>> map = (Map<ChangeType, Map<String, Integer>>) fieldIndex.get(position);
    assertThat(map).hasSize(0);
    assertThat(map.get(null)).isNull();
    ChangeOutputter changeOutputter0 = changesOutputter.changeOfDeletion(0);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).isNull();
    assertThat(map.get(ChangeType.DELETION)).hasSize(1);
    assertThat(map.get(ChangeType.DELETION).get(null)).isEqualTo(1);
    ChangeOutputter changeOutputter1 = changesOutputter.changeOfDeletion(1);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).isNull();
    assertThat(map.get(ChangeType.DELETION)).hasSize(1);
    assertThat(map.get(ChangeType.DELETION).get(null)).isEqualTo(2);
    try {
      changesOutputter.changeOfDeletion(2);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 2 out of the limits [0, 2[");
    }
    try {
      changesOutputter.changeOfDeletion(-1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index -1 out of the limits [0, 2[");
    }
    ChangeOutputter changeOutputterAgain0 = changesOutputter.changeOfDeletion(0);
    assertThat(changeOutputter0).isSameAs(changeOutputterAgain0);

    ChangesOutputter changesOutputterBis = output(changes);
    PositionWithChanges<ChangesAssert, ChangeAssert> positionBis =
      (PositionWithChanges) fieldPosition.get(changesOutputterBis);
    map = (Map<ChangeType, Map<String, Integer>>) fieldIndex.get(positionBis);
    assertThat(map).hasSize(0);
    assertThat(map.get(null)).isNull();
    ChangeOutputter changeOutputterBis0 = changesOutputterBis.changeOfDeletion(0);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).isNull();
    assertThat(map.get(ChangeType.DELETION)).hasSize(1);
    assertThat(map.get(ChangeType.DELETION).get(null)).isEqualTo(1);
    ChangeOutputter changeOutputterBis1 = changeOutputterBis0.changeOfDeletion(1);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).isNull();
    assertThat(map.get(ChangeType.DELETION)).hasSize(1);
    assertThat(map.get(ChangeType.DELETION).get(null)).isEqualTo(2);
    try {
      changeOutputterBis1.changeOfDeletion(2);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 2 out of the limits [0, 2[");
    }
    try {
      changeOutputterBis1.changeOfDeletion(-1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index -1 out of the limits [0, 2[");
    }
    ChangeOutputter changeOutputterBisAgain0 = changeOutputterBis1.changeOfDeletion(0);
    assertThat(changeOutputterBis0).isSameAs(changeOutputterBisAgain0);

    List<Changes> changesList = (List<Changes>) fieldList.get(changes);
    assertThat(fieldChange.get(changeOutputter0)).isSameAs(fieldChange.get(changeOutputterBis0)).isSameAs(changesList.get(6));
    assertThat(fieldChange.get(changeOutputter1)).isSameAs(fieldChange.get(changeOutputterBis1)).isSameAs(changesList.get(7));
  }
}
