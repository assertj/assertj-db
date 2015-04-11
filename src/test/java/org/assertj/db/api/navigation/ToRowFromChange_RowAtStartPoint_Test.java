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
import org.assertj.db.api.ChangeRowAssert;
import org.assertj.db.api.ChangesAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.assertj.db.type.Change;
import org.assertj.db.type.Changes;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.assertj.db.api.Assertions.assertThat;

/**
 * Tests on {@link ToRowFromChange} class :
 * {@link org.assertj.db.api.navigation.ToRowFromChange#rowAtStartPoint()} method.
 *
 * @author Régis Pouiller
 *
 */
public class ToRowFromChange_RowAtStartPoint_Test extends AbstractTest {

  /**
   * This method tests the {@code rowAtStartPoint} navigation method.
   */
  @Test
  @NeedReload
  public void test_row_at_start_point() throws Exception {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    Field fieldRowAssert = ChangeAssert.class.getDeclaredField("changeRowAssertAtStartPoint");
    fieldRowAssert.setAccessible(true);
    Field fieldRowFromChange = Change.class.getDeclaredField("rowAtStartPoint");
    fieldRowFromChange.setAccessible(true);
    Field fieldRowFromAssert = ChangeRowAssert.class.getDeclaredField("row");
    fieldRowFromAssert.setAccessible(true);

    ChangesAssert changesAssert = assertThat(changes);

    ChangeAssert changeCreationAssert = changesAssert.change();
    Assertions.assertThat(fieldRowAssert.get(changeCreationAssert)).isNull();
    ChangeRowAssert changeCreationRowAssert = changeCreationAssert.rowAtStartPoint();
    Assertions.assertThat(fieldRowAssert.get(changeCreationAssert)).isNotNull();
    ChangeRowAssert changeCreationRowAssertBis = changeCreationRowAssert.rowAtStartPoint();
    Assertions.assertThat(changeCreationRowAssert).isSameAs(changeCreationRowAssertBis);
    Assertions.assertThat(fieldRowFromAssert.get(changeCreationRowAssert)).isNull();

    ChangeAssert changeModificationAssert = changesAssert.change(3);
    Assertions.assertThat(fieldRowAssert.get(changeModificationAssert)).isNull();
    ChangeRowAssert changeModificationRowAssert = changeModificationAssert.rowAtStartPoint();
    Assertions.assertThat(fieldRowAssert.get(changeModificationAssert)).isNotNull();
    ChangeRowAssert changeModificationRowAssertBis = changeModificationRowAssert.rowAtStartPoint();
    Assertions.assertThat(changeModificationRowAssert).isSameAs(changeModificationRowAssertBis);
    Assertions.assertThat(fieldRowFromAssert.get(changeModificationRowAssert)).isEqualTo(
            fieldRowFromChange.get(changes.getChangesList().get(3)));

    ChangeAssert changeDeletionAssert = changesAssert.change(6);
    Assertions.assertThat(fieldRowAssert.get(changeDeletionAssert)).isNull();
    ChangeRowAssert changeDeletionRowAssert = changeDeletionAssert.rowAtStartPoint();
    Assertions.assertThat(fieldRowAssert.get(changeDeletionAssert)).isNotNull();
    ChangeRowAssert changeDeletionRowAssertBis = changeDeletionRowAssert.rowAtStartPoint();
    Assertions.assertThat(changeDeletionRowAssert).isSameAs(changeDeletionRowAssertBis);
    Assertions.assertThat(fieldRowFromAssert.get(changeDeletionRowAssert)).isEqualTo(
            fieldRowFromChange.get(changes.getChangesList().get(6)));
  }
}
