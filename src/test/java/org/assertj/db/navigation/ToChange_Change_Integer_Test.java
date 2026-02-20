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
import static org.assertj.core.api.Assertions.fail;
import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;

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
 * {@link org.assertj.db.navigation.ToChange#change(int)} method.
 *
 * @author Régis Pouiller
 * @author Julien Roy
 */
public class ToChange_Change_Integer_Test extends AbstractTest {

  /**
   * This method tests the {@code change} with index navigation method.
   */
  @SuppressWarnings("unchecked")
  @Test
  @NeedReload
  public void test_change_with_index_with_assertions() throws Exception {
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
      (PositionWithChanges<ChangesAssert, ChangeAssert>) fieldPosition.get(changesAssert);
    Map<ChangeType, Map<String, Integer>> map = (Map<ChangeType, Map<String, Integer>>) fieldIndex.get(position);
    assertThat(map).hasSize(0);
    assertThat(map.get(null)).isNull();
    ChangeAssert changeAssert0 = changesAssert.change(0);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).hasSize(1);
    assertThat(map.get(null).get(null)).isEqualTo(1);
    ChangeAssert changeAssert1 = changesAssert.change(1);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).hasSize(1);
    assertThat(map.get(null).get(null)).isEqualTo(2);
    ChangeAssert changeAssert2 = changesAssert.change(2);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).hasSize(1);
    assertThat(map.get(null).get(null)).isEqualTo(3);
    ChangeAssert changeAssert3 = changesAssert.change(3);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).hasSize(1);
    assertThat(map.get(null).get(null)).isEqualTo(4);
    ChangeAssert changeAssert4 = changesAssert.change(4);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).hasSize(1);
    assertThat(map.get(null).get(null)).isEqualTo(5);
    ChangeAssert changeAssert5 = changesAssert.change(5);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).hasSize(1);
    assertThat(map.get(null).get(null)).isEqualTo(6);
    ChangeAssert changeAssert6 = changesAssert.change(6);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).hasSize(1);
    assertThat(map.get(null).get(null)).isEqualTo(7);
    ChangeAssert changeAssert7 = changesAssert.change(7);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).hasSize(1);
    assertThat(map.get(null).get(null)).isEqualTo(8);
    try {
      changesAssert.change(8);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 8 out of the limits [0, 8[");
    }
    try {
      changesAssert.change(-1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index -1 out of the limits [0, 8[");
    }
    ChangeAssert changeAssertAgain0 = changesAssert.change(0);
    assertThat(changeAssert0).isSameAs(changeAssertAgain0);

    ChangesAssert changesAssertBis = assertThat(changes);
    PositionWithChanges<ChangesAssert, ChangeAssert> positionBis =
      (PositionWithChanges<ChangesAssert, ChangeAssert>) fieldPosition.get(changesAssertBis);
    map = (Map<ChangeType, Map<String, Integer>>) fieldIndex.get(positionBis);
    assertThat(map).hasSize(0);
    assertThat(map.get(null)).isNull();
    ChangeAssert changeAssertBis0 = changesAssertBis.change(0);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).hasSize(1);
    assertThat(map.get(null).get(null)).isEqualTo(1);
    ChangeAssert changeAssertBis1 = changeAssertBis0.change(1);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).hasSize(1);
    assertThat(map.get(null).get(null)).isEqualTo(2);
    ChangeAssert changeAssertBis2 = changeAssertBis1.change(2);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).hasSize(1);
    assertThat(map.get(null).get(null)).isEqualTo(3);
    ChangeAssert changeAssertBis3 = changeAssertBis2.change(3);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).hasSize(1);
    assertThat(map.get(null).get(null)).isEqualTo(4);
    ChangeAssert changeAssertBis4 = changeAssertBis3.change(4);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).hasSize(1);
    assertThat(map.get(null).get(null)).isEqualTo(5);
    ChangeAssert changeAssertBis5 = changeAssertBis4.change(5);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).hasSize(1);
    assertThat(map.get(null).get(null)).isEqualTo(6);
    ChangeAssert changeAssertBis6 = changeAssertBis5.change(6);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).hasSize(1);
    assertThat(map.get(null).get(null)).isEqualTo(7);
    ChangeAssert changeAssertBis7 = changeAssertBis6.change(7);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).hasSize(1);
    assertThat(map.get(null).get(null)).isEqualTo(8);
    try {
      changeAssertBis7.change(8);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 8 out of the limits [0, 8[");
    }
    try {
      changeAssertBis7.change(-1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index -1 out of the limits [0, 8[");
    }
    ChangeAssert changeAssertBisAgain0 = changeAssertBis7.change(0);
    assertThat(changeAssertBis0).isSameAs(changeAssertBisAgain0);

    List<Changes> changesList = (List<Changes>) fieldList.get(changes);
    assertThat(fieldChange.get(changeAssert0)).isSameAs(fieldChange.get(changeAssertBis0)).isSameAs(changesList.get(0));
    assertThat(fieldChange.get(changeAssert1)).isSameAs(fieldChange.get(changeAssertBis1)).isSameAs(changesList.get(1));
    assertThat(fieldChange.get(changeAssert2)).isSameAs(fieldChange.get(changeAssertBis2)).isSameAs(changesList.get(2));
    assertThat(fieldChange.get(changeAssert3)).isSameAs(fieldChange.get(changeAssertBis3)).isSameAs(changesList.get(3));
    assertThat(fieldChange.get(changeAssert4)).isSameAs(fieldChange.get(changeAssertBis4)).isSameAs(changesList.get(4));
    assertThat(fieldChange.get(changeAssert5)).isSameAs(fieldChange.get(changeAssertBis5)).isSameAs(changesList.get(5));
    assertThat(fieldChange.get(changeAssert6)).isSameAs(fieldChange.get(changeAssertBis6)).isSameAs(changesList.get(6));
    assertThat(fieldChange.get(changeAssert7)).isSameAs(fieldChange.get(changeAssertBis7)).isSameAs(changesList.get(7));
  }

  /**
   * This method tests the {@code change} with index navigation method.
   */
  @SuppressWarnings("unchecked")
  @Test
  @NeedReload
  public void test_change_with_index_with_displays() throws Exception {
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
      (PositionWithChanges<ChangesAssert, ChangeAssert>) fieldPosition.get(changesOutputter);
    Map<ChangeType, Map<String, Integer>> map = (Map<ChangeType, Map<String, Integer>>) fieldIndex.get(position);
    assertThat(map).hasSize(0);
    assertThat(map.get(null)).isNull();
    ChangeOutputter changeOutputter0 = changesOutputter.change(0);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).hasSize(1);
    assertThat(map.get(null).get(null)).isEqualTo(1);
    ChangeOutputter changeOutputter1 = changesOutputter.change(1);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).hasSize(1);
    assertThat(map.get(null).get(null)).isEqualTo(2);
    ChangeOutputter changeOutputter2 = changesOutputter.change(2);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).hasSize(1);
    assertThat(map.get(null).get(null)).isEqualTo(3);
    ChangeOutputter changeOutputter3 = changesOutputter.change(3);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).hasSize(1);
    assertThat(map.get(null).get(null)).isEqualTo(4);
    ChangeOutputter changeOutputter4 = changesOutputter.change(4);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).hasSize(1);
    assertThat(map.get(null).get(null)).isEqualTo(5);
    ChangeOutputter changeOutputter5 = changesOutputter.change(5);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).hasSize(1);
    assertThat(map.get(null).get(null)).isEqualTo(6);
    ChangeOutputter changeOutputter6 = changesOutputter.change(6);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).hasSize(1);
    assertThat(map.get(null).get(null)).isEqualTo(7);
    ChangeOutputter changeOutputter7 = changesOutputter.change(7);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).hasSize(1);
    assertThat(map.get(null).get(null)).isEqualTo(8);
    try {
      changesOutputter.change(8);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 8 out of the limits [0, 8[");
    }
    try {
      changesOutputter.change(-1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index -1 out of the limits [0, 8[");
    }
    ChangeOutputter changeOutputterAgain0 = changesOutputter.change(0);
    assertThat(changeOutputter0).isSameAs(changeOutputterAgain0);

    ChangesOutputter changesOutputterBis = output(changes);
    PositionWithChanges<ChangesAssert, ChangeAssert> positionBis =
      (PositionWithChanges<ChangesAssert, ChangeAssert>) fieldPosition.get(changesOutputterBis);
    map = (Map<ChangeType, Map<String, Integer>>) fieldIndex.get(positionBis);
    assertThat(map).hasSize(0);
    assertThat(map.get(null)).isNull();
    ChangeOutputter changeOutputterBis0 = changesOutputterBis.change(0);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).hasSize(1);
    assertThat(map.get(null).get(null)).isEqualTo(1);
    ChangeOutputter changeOutputterBis1 = changeOutputterBis0.change(1);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).hasSize(1);
    assertThat(map.get(null).get(null)).isEqualTo(2);
    ChangeOutputter changeOutputterBis2 = changeOutputterBis1.change(2);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).hasSize(1);
    assertThat(map.get(null).get(null)).isEqualTo(3);
    ChangeOutputter changeOutputterBis3 = changeOutputterBis2.change(3);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).hasSize(1);
    assertThat(map.get(null).get(null)).isEqualTo(4);
    ChangeOutputter changeOutputterBis4 = changeOutputterBis3.change(4);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).hasSize(1);
    assertThat(map.get(null).get(null)).isEqualTo(5);
    ChangeOutputter changeOutputterBis5 = changeOutputterBis4.change(5);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).hasSize(1);
    assertThat(map.get(null).get(null)).isEqualTo(6);
    ChangeOutputter changeOutputterBis6 = changeOutputterBis5.change(6);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).hasSize(1);
    assertThat(map.get(null).get(null)).isEqualTo(7);
    ChangeOutputter changeOutputterBis7 = changeOutputterBis6.change(7);
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).hasSize(1);
    assertThat(map.get(null).get(null)).isEqualTo(8);
    try {
      changeOutputterBis7.change(8);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 8 out of the limits [0, 8[");
    }
    try {
      changeOutputterBis7.change(-1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index -1 out of the limits [0, 8[");
    }
    ChangeOutputter changeOutputterBisAgain0 = changeOutputterBis7.change(0);
    assertThat(changeOutputterBis0).isSameAs(changeOutputterBisAgain0);

    List<Changes> changesList = (List<Changes>) fieldList.get(changes);
    assertThat(fieldChange.get(changeOutputter0)).isSameAs(fieldChange.get(changeOutputterBis0)).isSameAs(changesList.get(0));
    assertThat(fieldChange.get(changeOutputter1)).isSameAs(fieldChange.get(changeOutputterBis1)).isSameAs(changesList.get(1));
    assertThat(fieldChange.get(changeOutputter2)).isSameAs(fieldChange.get(changeOutputterBis2)).isSameAs(changesList.get(2));
    assertThat(fieldChange.get(changeOutputter3)).isSameAs(fieldChange.get(changeOutputterBis3)).isSameAs(changesList.get(3));
    assertThat(fieldChange.get(changeOutputter4)).isSameAs(fieldChange.get(changeOutputterBis4)).isSameAs(changesList.get(4));
    assertThat(fieldChange.get(changeOutputter5)).isSameAs(fieldChange.get(changeOutputterBis5)).isSameAs(changesList.get(5));
    assertThat(fieldChange.get(changeOutputter6)).isSameAs(fieldChange.get(changeOutputterBis6)).isSameAs(changesList.get(6));
    assertThat(fieldChange.get(changeOutputter7)).isSameAs(fieldChange.get(changeOutputterBis7)).isSameAs(changesList.get(7));
  }
}
