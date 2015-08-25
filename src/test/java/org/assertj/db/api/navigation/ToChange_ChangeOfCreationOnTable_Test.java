/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 * <p>
 * Copyright 2012-2015 the original author or authors.
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
 * Tests on {@link ToChange} class :
 * {@link ToChange#changeOfCreationOnTable(String)} method.
 *
 * @author Régis Pouiller
 *
 */
public class ToChange_ChangeOfCreationOnTable_Test extends AbstractTest {

    /**
     * This method tests the {@code changeOfCreationOnTable} navigation method.
     */
    @Test
    @NeedReload
    public void test_change_of_creation_on_table() throws Exception {
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
        Map<ChangeType, Map<String, Integer>> map = (Map<ChangeType, Map<String, Integer>>) fieldIndex
            .get(changesAssert);
        assertThat(map).hasSize(0);
        assertThat(map.get(null)).isNull();
        ChangeAssert changeAssert0 = changesAssert.changeOfCreationOnTable("actor");
        assertThat(map).hasSize(1);
        assertThat(map.get(null)).isNull();
        assertThat(map.get(ChangeType.CREATION)).hasSize(1);
        assertThat(map.get(ChangeType.CREATION).get("actor")).isEqualTo(1);
        ChangeAssert changeAssert1 = changesAssert.changeOfCreationOnTable("interpretation");
        assertThat(map).hasSize(1);
        assertThat(map.get(null)).isNull();
        assertThat(map.get(ChangeType.CREATION)).hasSize(2);
        assertThat(map.get(ChangeType.CREATION).get("interpretation")).isEqualTo(1);
        ChangeAssert changeAssert2 = changesAssert.changeOfCreationOnTable("movie");
        assertThat(map).hasSize(1);
        assertThat(map.get(null)).isNull();
        assertThat(map.get(ChangeType.CREATION)).hasSize(3);
        assertThat(map.get(ChangeType.CREATION).get("movie")).isEqualTo(1);
        try {
            changesAssert.changeOfCreationOnTable("actor");
            fail("An exception must be raised");
        } catch (AssertJDBException e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("Index 1 out of the limits [0, 1[");
        }

        ChangesAssert changesAssertBis = assertThat(changes);
        map = (Map<ChangeType, Map<String, Integer>>) fieldIndex.get(changesAssertBis);
        assertThat(map).hasSize(0);
        assertThat(map.get(null)).isNull();
        ChangeAssert changeAssertBis0 = changesAssertBis.changeOfCreationOnTable("actor");
        assertThat(map).hasSize(1);
        assertThat(map.get(null)).isNull();
        assertThat(map.get(ChangeType.CREATION)).hasSize(1);
        assertThat(map.get(ChangeType.CREATION).get("actor")).isEqualTo(1);
        ChangeAssert changeAssertBis1 = changeAssertBis0.changeOfCreationOnTable("interpretation");
        assertThat(map).hasSize(1);
        assertThat(map.get(null)).isNull();
        assertThat(map.get(ChangeType.CREATION)).hasSize(2);
        assertThat(map.get(ChangeType.CREATION).get("interpretation")).isEqualTo(1);
        ChangeAssert changeAssertBis2 = changeAssertBis1.changeOfCreationOnTable("movie");
        assertThat(map).hasSize(1);
        assertThat(map.get(null)).isNull();
        assertThat(map.get(ChangeType.CREATION)).hasSize(3);
        assertThat(map.get(ChangeType.CREATION).get("movie")).isEqualTo(1);
        try {
            changeAssertBis2.changeOfCreationOnTable("interpretation");
            fail("An exception must be raised");
        } catch (AssertJDBException e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("Index 1 out of the limits [0, 1[");
        }

        List<Changes> changesList = (List<Changes>) fieldList.get(changes);
        assertThat(fieldChange.get(changeAssert0)).isSameAs(fieldChange.get(changeAssertBis0))
                                                  .isSameAs(changesList.get(0));
        assertThat(fieldChange.get(changeAssert1)).isSameAs(fieldChange.get(changeAssertBis1))
                                                  .isSameAs(changesList.get(1));
        assertThat(fieldChange.get(changeAssert2)).isSameAs(fieldChange.get(changeAssertBis2))
                                                  .isSameAs(changesList.get(2));
    }
}
