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
package org.assertj.db.api.navigation;

import org.assertj.core.api.Assertions;
import org.assertj.db.api.ChangeAssert;
import org.assertj.db.api.ChangesAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.assertj.db.exception.AssertJDBException;
import org.assertj.db.type.ChangeType;
import org.assertj.db.type.Changes;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on {@link org.assertj.db.api.navigation.ToChange} class :
 * {@link org.assertj.db.api.navigation.ToChange#changeOfModification()} method.
 *
 * @author Régis Pouiller
 *
 */
public class ToChange_ChangeOfModification_Test extends AbstractTest {

  /**
   * This method tests the {@code changeOfModification} navigation method.
   */
  @Test
  @NeedReload
  public void test_change_of_modification() throws Exception {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    Field fieldList = Changes.class.getDeclaredField("changesList");
    fieldList.setAccessible(true);
    Field fieldIndex = ChangesAssert.class.getDeclaredField("indexNextChangeMap");
    fieldIndex.setAccessible(true);
    Field fieldChange = ChangeAssert.class.getDeclaredField("change");
    fieldChange.setAccessible(true);

    ChangesAssert changesAssert = assertThat(changes);
    Map<ChangeType, Map<String, Integer>> map = (Map<ChangeType, Map<String, Integer>>)fieldIndex.get(changesAssert);
    assertThat(map).hasSize(0);
    assertThat(map.get(null)).isNull();
    ChangeAssert changeAssert0 = changesAssert.changeOfModification();
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).isNull();
    assertThat(map.get(ChangeType.MODIFICATION)).hasSize(1);
    assertThat(map.get(ChangeType.MODIFICATION).get(null)).isEqualTo(1);
    ChangeAssert changeAssert1 = changesAssert.changeOfModification();
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).isNull();
    assertThat(map.get(ChangeType.MODIFICATION)).hasSize(1);
    assertThat(map.get(ChangeType.MODIFICATION).get(null)).isEqualTo(2);
    ChangeAssert changeAssert2 = changesAssert.changeOfModification();
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).isNull();
    assertThat(map.get(ChangeType.MODIFICATION)).hasSize(1);
    assertThat(map.get(ChangeType.MODIFICATION).get(null)).isEqualTo(3);
    try {
      changesAssert.changeOfModification();
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 3 out of the limits [0, 3[");
    }

    ChangesAssert changesAssertBis = assertThat(changes);
    map = (Map<ChangeType, Map<String, Integer>>)fieldIndex.get(changesAssertBis);
    assertThat(map).hasSize(0);
    assertThat(map.get(null)).isNull();
    ChangeAssert changeAssertBis0 = changesAssertBis.changeOfModification();
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).isNull();
    assertThat(map.get(ChangeType.MODIFICATION)).hasSize(1);
    assertThat(map.get(ChangeType.MODIFICATION).get(null)).isEqualTo(1);
    ChangeAssert changeAssertBis1 = changeAssertBis0.changeOfModification();
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).isNull();
    assertThat(map.get(ChangeType.MODIFICATION)).hasSize(1);
    assertThat(map.get(ChangeType.MODIFICATION).get(null)).isEqualTo(2);
    ChangeAssert changeAssertBis2 = changeAssertBis1.changeOfModification();
    assertThat(map).hasSize(1);
    assertThat(map.get(null)).isNull();
    assertThat(map.get(ChangeType.MODIFICATION)).hasSize(1);
    assertThat(map.get(ChangeType.MODIFICATION).get(null)).isEqualTo(3);
    try {
      changeAssertBis2.changeOfModification();
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 3 out of the limits [0, 3[");
    }

    List<Changes> changesList = (List<Changes>) fieldList.get(changes);
    assertThat(fieldChange.get(changeAssert0)).isSameAs(fieldChange.get(changeAssertBis0)).isSameAs(changesList.get(3));
    assertThat(fieldChange.get(changeAssert1)).isSameAs(fieldChange.get(changeAssertBis1)).isSameAs(changesList.get(4));
    assertThat(fieldChange.get(changeAssert2)).isSameAs(fieldChange.get(changeAssertBis2)).isSameAs(changesList.get(5));
  }
}
