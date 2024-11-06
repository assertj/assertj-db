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

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;
import static org.junit.Assert.fail;

/**
 * Tests on {@link ToChange} class :
 * {@link ToChange#changeOnTable(String, int)} method.
 *
 * @author Régis Pouiller
 *
 */
public class ToChange_ChangeOnTable_Integer_Test extends AbstractTest {

  /**
   * This method tests the {@code changeOnTable} navigation method.
   */
  @Test
  @NeedReload
  public void test_change_on_table_with_index_with_assertions() throws Exception {
    Changes changes = new Changes(source).setStartPointNow();
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
    Map<ChangeType, Map<String, Integer>> map = (Map<ChangeType, Map<String, Integer>>)fieldIndex.get(position);
    assertThat(map).hasSize(0);
    assertThat(map.get(null)).isNull();
    ChangeAssert changeAssert0 = changesAssert.changeOnTable("actor", 0);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).hasSize(1);
    assertThat(map.get(null).get("actor")).isEqualTo(1);
    ChangeAssert changeAssert1 = changesAssert.changeOnTable("actor", 1);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).hasSize(1);
    assertThat(map.get(null).get("actor")).isEqualTo(2);
    ChangeAssert changeAssert2 = changesAssert.changeOnTable("actor", 2);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).hasSize(1);
    assertThat(map.get(null).get("actor")).isEqualTo(3);
    try {
      changesAssert.changeOnTable("actor", 3);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 3 out of the limits [0, 3[");
    }
    try {
      changesAssert.changeOnTable("actor", -1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index -1 out of the limits [0, 3[");
    }
    ChangeAssert changeAssertAgain0 = changesAssert.changeOnTable("actor", 0);
    assertThat(changeAssert0).isSameAs(changeAssertAgain0);

    ChangesAssert changesAssertBis = assertThat(changes);
    PositionWithChanges<ChangesAssert, ChangeAssert> positionBis = 
              (PositionWithChanges) fieldPosition.get(changesAssertBis);
    map = (Map<ChangeType, Map<String, Integer>>)fieldIndex.get(positionBis);
    assertThat(map).hasSize(0);
    assertThat(map.get(null)).isNull();
    ChangeAssert changeAssertBis0 = changesAssertBis.changeOnTable("actor", 0);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).hasSize(1);
    assertThat(map.get(null).get("actor")).isEqualTo(1);
    ChangeAssert changeAssertBis1 = changeAssertBis0.changeOnTable("actor", 1);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).hasSize(1);
    assertThat(map.get(null).get("actor")).isEqualTo(2);
    ChangeAssert changeAssertBis2 = changeAssertBis1.changeOnTable("actor", 2);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).hasSize(1);
    assertThat(map.get(null).get("actor")).isEqualTo(3);
    try {
      changeAssertBis2.changeOnTable("actor", 3);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 3 out of the limits [0, 3[");
    }
    try {
      changeAssertBis2.changeOnTable("actor", -1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index -1 out of the limits [0, 3[");
    }
    ChangeAssert changeAssertBisAgain0 = changeAssertBis2.changeOnTable("actor", 0);
    Assertions.assertThat(changeAssertBis0).isSameAs(changeAssertBisAgain0);

    List<Changes> changesList = (List<Changes>) fieldList.get(changes);
    assertThat(fieldChange.get(changeAssert0)).isSameAs(fieldChange.get(changeAssertBis0)).isSameAs(changesList.get(0));
    assertThat(fieldChange.get(changeAssert1)).isSameAs(fieldChange.get(changeAssertBis1)).isSameAs(changesList.get(3));
    assertThat(fieldChange.get(changeAssert2)).isSameAs(fieldChange.get(changeAssertBis2)).isSameAs(changesList.get(6));
  }

  /**
   * This method tests the {@code changeOnTable} navigation method.
   */
  @Test
  @NeedReload
  public void test_change_on_table_with_index_with_displays() throws Exception {
    Changes changes = new Changes(source).setStartPointNow();
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
    Map<ChangeType, Map<String, Integer>> map = (Map<ChangeType, Map<String, Integer>>)fieldIndex.get(position);
    assertThat(map).hasSize(0);
    assertThat(map.get(null)).isNull();
    ChangeOutputter changeDisplay0 = changesDisplay.changeOnTable("actor", 0);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).hasSize(1);
    assertThat(map.get(null).get("actor")).isEqualTo(1);
    ChangeOutputter changeDisplay1 = changesDisplay.changeOnTable("actor", 1);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).hasSize(1);
    assertThat(map.get(null).get("actor")).isEqualTo(2);
    ChangeOutputter changeDisplay2 = changesDisplay.changeOnTable("actor", 2);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).hasSize(1);
    assertThat(map.get(null).get("actor")).isEqualTo(3);
    try {
      changesDisplay.changeOnTable("actor", 3);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 3 out of the limits [0, 3[");
    }
    try {
      changesDisplay.changeOnTable("actor", -1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index -1 out of the limits [0, 3[");
    }
    ChangeOutputter changeDisplayAgain0 = changesDisplay.changeOnTable("actor", 0);
    assertThat(changeDisplay0).isSameAs(changeDisplayAgain0);

    ChangesOutputter changesDisplayBis = output(changes);
    PositionWithChanges<ChangesAssert, ChangeAssert> positionBis = 
              (PositionWithChanges) fieldPosition.get(changesDisplayBis);
    map = (Map<ChangeType, Map<String, Integer>>)fieldIndex.get(positionBis);
    assertThat(map).hasSize(0);
    assertThat(map.get(null)).isNull();
    ChangeOutputter changeDisplayBis0 = changesDisplayBis.changeOnTable("actor", 0);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).hasSize(1);
    assertThat(map.get(null).get("actor")).isEqualTo(1);
    ChangeOutputter changeDisplayBis1 = changeDisplayBis0.changeOnTable("actor", 1);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).hasSize(1);
    assertThat(map.get(null).get("actor")).isEqualTo(2);
    ChangeOutputter changeDisplayBis2 = changeDisplayBis1.changeOnTable("actor", 2);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).hasSize(1);
    assertThat(map.get(null).get("actor")).isEqualTo(3);
    try {
      changeDisplayBis2.changeOnTable("actor", 3);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 3 out of the limits [0, 3[");
    }
    try {
      changeDisplayBis2.changeOnTable("actor", -1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index -1 out of the limits [0, 3[");
    }
    ChangeOutputter changeDisplayBisAgain0 = changeDisplayBis2.changeOnTable("actor", 0);
    Assertions.assertThat(changeDisplayBis0).isSameAs(changeDisplayBisAgain0);

    List<Changes> changesList = (List<Changes>) fieldList.get(changes);
    assertThat(fieldChange.get(changeDisplay0)).isSameAs(fieldChange.get(changeDisplayBis0)).isSameAs(changesList.get(0));
    assertThat(fieldChange.get(changeDisplay1)).isSameAs(fieldChange.get(changeDisplayBis1)).isSameAs(changesList.get(3));
    assertThat(fieldChange.get(changeDisplay2)).isSameAs(fieldChange.get(changeDisplayBis2)).isSameAs(changesList.get(6));
  }
}
