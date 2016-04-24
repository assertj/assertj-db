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
 * Tests on {@link org.assertj.db.navigation.ToChange} class :
 * {@link org.assertj.db.navigation.ToChange#changeOfCreationOnTable(String, int)} method.
 *
 * @author Régis Pouiller
 *
 */
public class ToChange_ChangeOfCreationOnTable_Integer_Test extends AbstractTest {

  /**
   * This method tests the {@code changeOfCreationOnTable} navigation method.
   */
  @Test
  @NeedReload
  public void test_change_of_creation_on_table_with_index_with_assertions() throws Exception {
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
    PositionWithChanges position = (PositionWithChanges) fieldPosition.get(changesAssert);
    Map<ChangeType, Map<String, Integer>> map = (Map<ChangeType, Map<String, Integer>>)fieldIndex.get(position);
    assertThat(map).hasSize(0);
    assertThat(map.get(null)).isNull();
    ChangeAssert changeAssert0 = changesAssert.changeOfCreationOnTable("actor", 0);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).isNull();
    assertThat(map.get(ChangeType.CREATION)).hasSize(1);
    assertThat(map.get(ChangeType.CREATION).get("actor")).isEqualTo(1);
    ChangeAssert changeAssert1 = changesAssert.changeOfCreationOnTable("interpretation", 0);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).isNull();
    assertThat(map.get(ChangeType.CREATION)).hasSize(2);
    assertThat(map.get(ChangeType.CREATION).get("interpretation")).isEqualTo(1);
    ChangeAssert changeAssert2 = changesAssert.changeOfCreationOnTable("movie", 0);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).isNull();
    assertThat(map.get(ChangeType.CREATION)).hasSize(3);
    assertThat(map.get(ChangeType.CREATION).get("movie")).isEqualTo(1);
    try {
      changesAssert.changeOfCreationOnTable("actor", 2);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 2 out of the limits [0, 1[");
    }
    try {
      changesAssert.changeOfCreationOnTable("actor", -1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index -1 out of the limits [0, 1[");
    }
    ChangeAssert changeAssertAgain0 = changesAssert.changeOfCreationOnTable("actor", 0);
    assertThat(changeAssert0).isSameAs(changeAssertAgain0);

    ChangesAssert changesAssertBis = assertThat(changes);
    PositionWithChanges positionBis = (PositionWithChanges) fieldPosition.get(changesAssertBis);
    map = (Map<ChangeType, Map<String, Integer>>)fieldIndex.get(positionBis);
    assertThat(map).hasSize(0);
    assertThat(map.get(null)).isNull();
    ChangeAssert changeAssertBis0 = changesAssertBis.changeOfCreationOnTable("actor", 0);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).isNull();
    assertThat(map.get(ChangeType.CREATION)).hasSize(1);
    assertThat(map.get(ChangeType.CREATION).get("actor")).isEqualTo(1);
    ChangeAssert changeAssertBis1 = changeAssertBis0.changeOfCreationOnTable("interpretation", 0);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).isNull();
    assertThat(map.get(ChangeType.CREATION)).hasSize(2);
    assertThat(map.get(ChangeType.CREATION).get("interpretation")).isEqualTo(1);
    ChangeAssert changeAssertBis2 = changeAssertBis1.changeOfCreationOnTable("movie", 0);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).isNull();
    assertThat(map.get(ChangeType.CREATION)).hasSize(3);
    assertThat(map.get(ChangeType.CREATION).get("movie")).isEqualTo(1);
    try {
      changeAssertBis2.changeOfCreationOnTable("interpretation", 3);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 3 out of the limits [0, 1[");
    }
    try {
      changeAssertBis2.changeOfCreationOnTable("interpretation", -1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index -1 out of the limits [0, 1[");
    }
    ChangeAssert changeAssertBisAgain0 = changeAssertBis2.changeOfCreationOnTable("actor", 0);
    assertThat(changeAssertBis0).isSameAs(changeAssertBisAgain0);

    List<Changes> changesList = (List<Changes>) fieldList.get(changes);
    assertThat(fieldChange.get(changeAssert0)).isSameAs(fieldChange.get(changeAssertBis0)).isSameAs(changesList.get(0));
    assertThat(fieldChange.get(changeAssert1)).isSameAs(fieldChange.get(changeAssertBis1)).isSameAs(changesList.get(1));
    assertThat(fieldChange.get(changeAssert2)).isSameAs(fieldChange.get(changeAssertBis2)).isSameAs(changesList.get(2));
  }

  /**
   * This method tests the {@code changeOfCreationOnTable} navigation method.
   */
  @Test
  @NeedReload
  public void test_change_of_creation_on_table_with_index_with_displays() throws Exception {
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
    PositionWithChanges position = (PositionWithChanges) fieldPosition.get(changesOutputter);
    Map<ChangeType, Map<String, Integer>> map = (Map<ChangeType, Map<String, Integer>>)fieldIndex.get(position);
    assertThat(map).hasSize(0);
    assertThat(map.get(null)).isNull();
    ChangeOutputter changeOutputter0 = changesOutputter.changeOfCreationOnTable("actor", 0);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).isNull();
    assertThat(map.get(ChangeType.CREATION)).hasSize(1);
    assertThat(map.get(ChangeType.CREATION).get("actor")).isEqualTo(1);
    ChangeOutputter changeOutputter1 = changesOutputter.changeOfCreationOnTable("interpretation", 0);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).isNull();
    assertThat(map.get(ChangeType.CREATION)).hasSize(2);
    assertThat(map.get(ChangeType.CREATION).get("interpretation")).isEqualTo(1);
    ChangeOutputter changeOutputter2 = changesOutputter.changeOfCreationOnTable("movie", 0);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).isNull();
    assertThat(map.get(ChangeType.CREATION)).hasSize(3);
    assertThat(map.get(ChangeType.CREATION).get("movie")).isEqualTo(1);
    try {
      changesOutputter.changeOfCreationOnTable("actor", 2);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 2 out of the limits [0, 1[");
    }
    try {
      changesOutputter.changeOfCreationOnTable("actor", -1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index -1 out of the limits [0, 1[");
    }
    ChangeOutputter changeOutputterAgain0 = changesOutputter.changeOfCreationOnTable("actor", 0);
    assertThat(changeOutputter0).isSameAs(changeOutputterAgain0);

    ChangesOutputter changesOutputterBis = output(changes);
    PositionWithChanges positionBis = (PositionWithChanges) fieldPosition.get(changesOutputterBis);
    map = (Map<ChangeType, Map<String, Integer>>)fieldIndex.get(positionBis);
    assertThat(map).hasSize(0);
    assertThat(map.get(null)).isNull();
    ChangeOutputter changeOutputterBis0 = changesOutputterBis.changeOfCreationOnTable("actor", 0);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).isNull();
    assertThat(map.get(ChangeType.CREATION)).hasSize(1);
    assertThat(map.get(ChangeType.CREATION).get("actor")).isEqualTo(1);
    ChangeOutputter changeOutputterBis1 = changeOutputterBis0.changeOfCreationOnTable("interpretation", 0);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).isNull();
    assertThat(map.get(ChangeType.CREATION)).hasSize(2);
    assertThat(map.get(ChangeType.CREATION).get("interpretation")).isEqualTo(1);
    ChangeOutputter changeOutputterBis2 = changeOutputterBis1.changeOfCreationOnTable("movie", 0);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).isNull();
    assertThat(map.get(ChangeType.CREATION)).hasSize(3);
    assertThat(map.get(ChangeType.CREATION).get("movie")).isEqualTo(1);
    try {
      changeOutputterBis2.changeOfCreationOnTable("interpretation", 3);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 3 out of the limits [0, 1[");
    }
    try {
      changeOutputterBis2.changeOfCreationOnTable("interpretation", -1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index -1 out of the limits [0, 1[");
    }
    ChangeOutputter changeOutputterBisAgain0 = changeOutputterBis2.changeOfCreationOnTable("actor", 0);
    assertThat(changeOutputterBis0).isSameAs(changeOutputterBisAgain0);

    List<Changes> changesList = (List<Changes>) fieldList.get(changes);
    assertThat(fieldChange.get(changeOutputter0)).isSameAs(fieldChange.get(changeOutputterBis0)).isSameAs(changesList.get(0));
    assertThat(fieldChange.get(changeOutputter1)).isSameAs(fieldChange.get(changeOutputterBis1)).isSameAs(changesList.get(1));
    assertThat(fieldChange.get(changeOutputter2)).isSameAs(fieldChange.get(changeOutputterBis2)).isSameAs(changesList.get(2));
  }
}
