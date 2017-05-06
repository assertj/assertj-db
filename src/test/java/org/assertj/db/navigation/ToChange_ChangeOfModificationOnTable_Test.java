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
 * Copyright 2012-2016 the original author or authors.
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
 * {@link ToChange#changeOfModificationOnTable(String)} method.
 *
 * @author Régis Pouiller
 *
 */
public class ToChange_ChangeOfModificationOnTable_Test extends AbstractTest {

  /**
   * This method tests the {@code changeOfModification} navigation method.
   */
  @Test
  @NeedReload
  public void test_change_of_modification_on_table_with_assertions() throws Exception {
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
    ChangeAssert changeAssert0 = changesAssert.changeOfModificationOnTable("actor");
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).isNull();
    assertThat(map.get(ChangeType.MODIFICATION)).hasSize(1);
    assertThat(map.get(ChangeType.MODIFICATION).get("actor")).isEqualTo(1);
    ChangeAssert changeAssert1 = changesAssert.changeOfModificationOnTable("interpretation");
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).isNull();
    assertThat(map.get(ChangeType.MODIFICATION)).hasSize(2);
    assertThat(map.get(ChangeType.MODIFICATION).get("interpretation")).isEqualTo(1);
    ChangeAssert changeAssert2 = changesAssert.changeOfModificationOnTable("movie");
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).isNull();
    assertThat(map.get(ChangeType.MODIFICATION)).hasSize(3);
    assertThat(map.get(ChangeType.MODIFICATION).get("movie")).isEqualTo(1);
    try {
      changesAssert.changeOfModificationOnTable("actor");
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 1 out of the limits [0, 1[");
    }

    ChangesAssert changesAssertBis = assertThat(changes);
    PositionWithChanges<ChangesAssert, ChangeAssert> positionBis = 
              (PositionWithChanges) fieldPosition.get(changesAssertBis);
    map = (Map<ChangeType, Map<String, Integer>>)fieldIndex.get(positionBis);
    assertThat(map).hasSize(0);
    assertThat(map.get(null)).isNull();
    ChangeAssert changeAssertBis0 = changesAssertBis.changeOfModificationOnTable("actor");
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).isNull();
    assertThat(map.get(ChangeType.MODIFICATION)).hasSize(1);
    assertThat(map.get(ChangeType.MODIFICATION).get("actor")).isEqualTo(1);
    ChangeAssert changeAssertBis1 = changeAssertBis0.changeOfModificationOnTable("interpretation");
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).isNull();
    assertThat(map.get(ChangeType.MODIFICATION)).hasSize(2);
    assertThat(map.get(ChangeType.MODIFICATION).get("interpretation")).isEqualTo(1);
    ChangeAssert changeAssertBis2 = changeAssertBis1.changeOfModificationOnTable("movie");
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).isNull();
    assertThat(map.get(ChangeType.MODIFICATION)).hasSize(3);
    assertThat(map.get(ChangeType.MODIFICATION).get("movie")).isEqualTo(1);
    try {
      changeAssertBis2.changeOfModificationOnTable("interpretation");
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 1 out of the limits [0, 1[");
    }

    List<Changes> changesList = (List<Changes>) fieldList.get(changes);
    assertThat(fieldChange.get(changeAssert0)).isSameAs(fieldChange.get(changeAssertBis0)).isSameAs(changesList.get(3));
    assertThat(fieldChange.get(changeAssert1)).isSameAs(fieldChange.get(changeAssertBis1)).isSameAs(changesList.get(4));
    assertThat(fieldChange.get(changeAssert2)).isSameAs(fieldChange.get(changeAssertBis2)).isSameAs(changesList.get(5));
  }

  /**
   * This method tests the {@code changeOfModification} navigation method.
   */
  @Test
  @NeedReload
  public void test_change_of_modification_on_table_with_displays() throws Exception {
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

    ChangesOutputter changesOutputter = output(changes);
    PositionWithChanges<ChangesAssert, ChangeAssert> position = 
              (PositionWithChanges) fieldPosition.get(changesOutputter);
    Map<ChangeType, Map<String, Integer>> map = (Map<ChangeType, Map<String, Integer>>)fieldIndex.get(position);
    assertThat(map).hasSize(0);
    assertThat(map.get(null)).isNull();
    ChangeOutputter changeOutputter0 = changesOutputter.changeOfModificationOnTable("actor");
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).isNull();
    assertThat(map.get(ChangeType.MODIFICATION)).hasSize(1);
    assertThat(map.get(ChangeType.MODIFICATION).get("actor")).isEqualTo(1);
    ChangeOutputter changeOutputter1 = changesOutputter.changeOfModificationOnTable("interpretation");
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).isNull();
    assertThat(map.get(ChangeType.MODIFICATION)).hasSize(2);
    assertThat(map.get(ChangeType.MODIFICATION).get("interpretation")).isEqualTo(1);
    ChangeOutputter changeOutputter2 = changesOutputter.changeOfModificationOnTable("movie");
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).isNull();
    assertThat(map.get(ChangeType.MODIFICATION)).hasSize(3);
    assertThat(map.get(ChangeType.MODIFICATION).get("movie")).isEqualTo(1);
    try {
      changesOutputter.changeOfModificationOnTable("actor");
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 1 out of the limits [0, 1[");
    }

    ChangesOutputter changesOutputterBis = output(changes);
    PositionWithChanges<ChangesAssert, ChangeAssert> positionBis = 
              (PositionWithChanges) fieldPosition.get(changesOutputterBis);
    map = (Map<ChangeType, Map<String, Integer>>)fieldIndex.get(positionBis);
    assertThat(map).hasSize(0);
    assertThat(map.get(null)).isNull();
    ChangeOutputter changeOutputterBis0 = changesOutputterBis.changeOfModificationOnTable("actor");
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).isNull();
    assertThat(map.get(ChangeType.MODIFICATION)).hasSize(1);
    assertThat(map.get(ChangeType.MODIFICATION).get("actor")).isEqualTo(1);
    ChangeOutputter changeOutputterBis1 = changeOutputterBis0.changeOfModificationOnTable("interpretation");
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).isNull();
    assertThat(map.get(ChangeType.MODIFICATION)).hasSize(2);
    assertThat(map.get(ChangeType.MODIFICATION).get("interpretation")).isEqualTo(1);
    ChangeOutputter changeOutputterBis2 = changeOutputterBis1.changeOfModificationOnTable("movie");
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).isNull();
    assertThat(map.get(ChangeType.MODIFICATION)).hasSize(3);
    assertThat(map.get(ChangeType.MODIFICATION).get("movie")).isEqualTo(1);
    try {
      changeOutputterBis2.changeOfModificationOnTable("interpretation");
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 1 out of the limits [0, 1[");
    }

    List<Changes> changesList = (List<Changes>) fieldList.get(changes);
    assertThat(fieldChange.get(changeOutputter0)).isSameAs(fieldChange.get(changeOutputterBis0)).isSameAs(changesList.get(3));
    assertThat(fieldChange.get(changeOutputter1)).isSameAs(fieldChange.get(changeOutputterBis1)).isSameAs(changesList.get(4));
    assertThat(fieldChange.get(changeOutputter2)).isSameAs(fieldChange.get(changeOutputterBis2)).isSameAs(changesList.get(5));
  }
}
