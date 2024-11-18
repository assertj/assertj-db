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

import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;

import java.lang.reflect.Field;

import org.assertj.core.api.Assertions;
import org.assertj.db.api.ChangeAssert;
import org.assertj.db.api.ChangeRowAssert;
import org.assertj.db.api.ChangesAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.assertj.db.output.ChangeOutputter;
import org.assertj.db.output.ChangeRowOutputter;
import org.assertj.db.output.ChangesOutputter;
import org.assertj.db.type.Change;
import org.assertj.db.type.Changes;
import org.assertj.db.type.Row;
import org.junit.Test;

/**
 * Tests on {@link org.assertj.db.navigation.ToRowFromChange} class :
 * {@link org.assertj.db.navigation.ToRowFromChange#rowAtStartPoint()} method.
 *
 * @author Régis Pouiller
 * @author Julien Roy
 */
public class ToRowFromChange_RowAtStartPoint_Test extends AbstractTest {

  /**
   * This method tests the {@code rowAtStartPoint} navigation method.
   */
  @Test
  @NeedReload
  public void test_row_at_start_point_with_assertions() throws Exception {
    Changes changes = new Changes(jdbcConnectionProvider).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    Field fieldPosition = ChangeAssert.class.getDeclaredField("rowPosition");
    fieldPosition.setAccessible(true);
    Field fieldRowAssert = PositionWithPoints.class.getDeclaredField("instanceAtStartPoint");
    fieldRowAssert.setAccessible(true);
    Field fieldRowFromChange = Change.class.getDeclaredField("rowAtStartPoint");
    fieldRowFromChange.setAccessible(true);
    Field fieldRowFromAssert = ChangeRowAssert.class.getDeclaredField("row");
    fieldRowFromAssert.setAccessible(true);

    ChangesAssert changesAssert = assertThat(changes);

    ChangeAssert changeCreationAssert = changesAssert.change();
    PositionWithPoints<ChangeAssert, ChangeRowAssert, Row> positionCreation =
      (PositionWithPoints) fieldPosition.get(changeCreationAssert);
    Assertions.assertThat(fieldRowAssert.get(positionCreation)).isNull();
    ChangeRowAssert changeCreationRowAssert = changeCreationAssert.rowAtStartPoint();
    Assertions.assertThat(fieldRowAssert.get(positionCreation)).isNotNull();
    ChangeRowAssert changeCreationRowAssertBis = changeCreationRowAssert.rowAtStartPoint();
    Assertions.assertThat(changeCreationRowAssert).isSameAs(changeCreationRowAssertBis);
    Assertions.assertThat(fieldRowFromAssert.get(changeCreationRowAssert)).isNull();

    ChangeAssert changeModificationAssert = changesAssert.change(3);
    PositionWithPoints<ChangeAssert, ChangeRowAssert, Row> positionModification =
      (PositionWithPoints) fieldPosition.get(changeModificationAssert);
    Assertions.assertThat(fieldRowAssert.get(positionModification)).isNull();
    ChangeRowAssert changeModificationRowAssert = changeModificationAssert.rowAtStartPoint();
    Assertions.assertThat(fieldRowAssert.get(positionModification)).isNotNull();
    ChangeRowAssert changeModificationRowAssertBis = changeModificationRowAssert.rowAtStartPoint();
    Assertions.assertThat(changeModificationRowAssert).isSameAs(changeModificationRowAssertBis);
    Assertions.assertThat(fieldRowFromAssert.get(changeModificationRowAssert)).isEqualTo(
      fieldRowFromChange.get(changes.getChangesList().get(3)));

    ChangeAssert changeDeletionAssert = changesAssert.change(6);
    PositionWithPoints<ChangeAssert, ChangeRowAssert, Row> positionDeletion =
      (PositionWithPoints) fieldPosition.get(changeDeletionAssert);
    Assertions.assertThat(fieldRowAssert.get(positionDeletion)).isNull();
    ChangeRowAssert changeDeletionRowAssert = changeDeletionAssert.rowAtStartPoint();
    Assertions.assertThat(fieldRowAssert.get(positionDeletion)).isNotNull();
    ChangeRowAssert changeDeletionRowAssertBis = changeDeletionRowAssert.rowAtStartPoint();
    Assertions.assertThat(changeDeletionRowAssert).isSameAs(changeDeletionRowAssertBis);
    Assertions.assertThat(fieldRowFromAssert.get(changeDeletionRowAssert)).isEqualTo(
      fieldRowFromChange.get(changes.getChangesList().get(6)));
  }

  /**
   * This method tests the {@code rowAtStartPoint} navigation method.
   */
  @Test
  @NeedReload
  public void test_row_at_start_point_with_displays() throws Exception {
    Changes changes = new Changes(jdbcConnectionProvider).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    Field fieldPosition = ChangeOutputter.class.getDeclaredField("rowPosition");
    fieldPosition.setAccessible(true);
    Field fieldRowOutputter = PositionWithPoints.class.getDeclaredField("instanceAtStartPoint");
    fieldRowOutputter.setAccessible(true);
    Field fieldRowFromChange = Change.class.getDeclaredField("rowAtStartPoint");
    fieldRowFromChange.setAccessible(true);
    Field fieldRowFromOutputter = ChangeRowOutputter.class.getDeclaredField("row");
    fieldRowFromOutputter.setAccessible(true);

    ChangesOutputter changesOutputter = output(changes);

    ChangeOutputter changeCreationOutputter = changesOutputter.change();
    PositionWithPoints<ChangeAssert, ChangeRowAssert, Row> positionCreation =
      (PositionWithPoints) fieldPosition.get(changeCreationOutputter);
    Assertions.assertThat(fieldRowOutputter.get(positionCreation)).isNull();
    ChangeRowOutputter changeCreationRowOutputter = changeCreationOutputter.rowAtStartPoint();
    Assertions.assertThat(fieldRowOutputter.get(positionCreation)).isNotNull();
    ChangeRowOutputter changeCreationRowOutputterBis = changeCreationRowOutputter.rowAtStartPoint();
    Assertions.assertThat(changeCreationRowOutputter).isSameAs(changeCreationRowOutputterBis);
    Assertions.assertThat(fieldRowFromOutputter.get(changeCreationRowOutputter)).isNull();

    ChangeOutputter changeModificationOutputter = changesOutputter.change(3);
    PositionWithPoints<ChangeAssert, ChangeRowAssert, Row> positionModification =
      (PositionWithPoints) fieldPosition.get(changeModificationOutputter);
    Assertions.assertThat(fieldRowOutputter.get(positionModification)).isNull();
    ChangeRowOutputter changeModificationRowOutputter = changeModificationOutputter.rowAtStartPoint();
    Assertions.assertThat(fieldRowOutputter.get(positionModification)).isNotNull();
    ChangeRowOutputter changeModificationRowOutputterBis = changeModificationRowOutputter.rowAtStartPoint();
    Assertions.assertThat(changeModificationRowOutputter).isSameAs(changeModificationRowOutputterBis);
    Assertions.assertThat(fieldRowFromOutputter.get(changeModificationRowOutputter)).isEqualTo(
      fieldRowFromChange.get(changes.getChangesList().get(3)));

    ChangeOutputter changeDeletionOutputter = changesOutputter.change(6);
    PositionWithPoints<ChangeAssert, ChangeRowAssert, Row> positionDeletion =
      (PositionWithPoints) fieldPosition.get(changeDeletionOutputter);
    Assertions.assertThat(fieldRowOutputter.get(positionDeletion)).isNull();
    ChangeRowOutputter changeDeletionRowOutputter = changeDeletionOutputter.rowAtStartPoint();
    Assertions.assertThat(fieldRowOutputter.get(positionDeletion)).isNotNull();
    ChangeRowOutputter changeDeletionRowOutputterBis = changeDeletionRowOutputter.rowAtStartPoint();
    Assertions.assertThat(changeDeletionRowOutputter).isSameAs(changeDeletionRowOutputterBis);
    Assertions.assertThat(fieldRowFromOutputter.get(changeDeletionRowOutputter)).isEqualTo(
      fieldRowFromChange.get(changes.getChangesList().get(6)));
  }
}
