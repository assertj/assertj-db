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
 * Copyright 2012-2015 the original author or authors.
 */
package org.assertj.db.navigation;

import org.assertj.core.api.Assertions;
import org.assertj.db.api.*;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.assertj.db.display.*;
import org.assertj.db.type.Changes;
import org.assertj.db.type.Value;
import org.junit.Test;

import java.lang.reflect.Field;
import java.math.BigDecimal;

import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.display.Displaying.display;

/**
 * Tests on {@link org.assertj.db.navigation.ToValueFromColumn} class :
 * {@link org.assertj.db.navigation.ToValueFromColumn#valueAtEndPoint()} method.
 *
 * @author Régis Pouiller
 *
 */
public class ToValueFromColumn_ValueAtEndPoint_Test extends AbstractTest {

  /**
   * This method tests the {@code valueAtEndPoint} navigation method.
   */
  @Test
  @NeedReload
  public void test_value_at_end_point_with_assertions() throws Exception {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    Field fieldPosition = ChangeColumnAssert.class.getDeclaredField("valuePosition");
    fieldPosition.setAccessible(true);
    Field fieldRowAssert = PositionWithPoints.class.getDeclaredField("instanceAtEndPoint");
    fieldRowAssert.setAccessible(true);
    Field fieldValueFromColumnAssert = ChangeColumnAssert.class.getDeclaredField("valueAtEndPoint");
    fieldValueFromColumnAssert.setAccessible(true);
    Field fieldValueFromValueAssert = AbstractAssertWithValues.class.getDeclaredField("value");
    fieldValueFromValueAssert.setAccessible(true);

    ChangesAssert changesAssert = assertThat(changes);

    ChangeAssert changeCreationAssert = changesAssert.change();
    ChangeColumnAssert changeColumnCreationAssert = changeCreationAssert.column();
    PositionWithPoints positionCreation = (PositionWithPoints) fieldPosition.get(changeColumnCreationAssert);
    Assertions.assertThat(fieldRowAssert.get(positionCreation)).isNull();
    ChangeColumnValueAssert changeColumnValueCreationAssert = changeColumnCreationAssert.valueAtEndPoint();
    Assertions.assertThat(fieldRowAssert.get(positionCreation)).isNotNull();
    ChangeColumnValueAssert changeCreationRowValueAssertBis = changeColumnCreationAssert.valueAtEndPoint();
    Assertions.assertThat(changeColumnValueCreationAssert).isSameAs(changeCreationRowValueAssertBis);
    Assertions.assertThat(((Value) fieldValueFromColumnAssert.get(changeColumnCreationAssert)).getValue()).isSameAs(
            ((Value) fieldValueFromValueAssert.get(changeColumnValueCreationAssert)).getValue()).isEqualTo(
            new BigDecimal("4"));

    ChangeAssert changeModificationAssert = changesAssert.change(3);
    ChangeColumnAssert changeColumnModificationAssert = changeModificationAssert.column();
    PositionWithPoints positionModification = (PositionWithPoints) fieldPosition.get(changeColumnModificationAssert);
    Assertions.assertThat(fieldRowAssert.get(positionModification)).isNull();
    ChangeColumnValueAssert changeColumnValueModificationAssert = changeColumnModificationAssert.valueAtEndPoint();
    Assertions.assertThat(fieldRowAssert.get(positionModification)).isNotNull();
    ChangeColumnValueAssert changeModificationRowValueAssertBis = changeColumnValueModificationAssert.valueAtEndPoint();
    Assertions.assertThat(changeColumnValueModificationAssert).isSameAs(changeModificationRowValueAssertBis);
    Assertions.assertThat(((Value) fieldValueFromColumnAssert.get(changeColumnModificationAssert)).getValue()).isSameAs(
            ((Value) fieldValueFromValueAssert.get(changeColumnValueModificationAssert)).getValue()).isEqualTo(
            new BigDecimal("1"));

    ChangeAssert changeDeletionAssert = changesAssert.change(6);
    ChangeColumnAssert changeColumnDeletionAssert = changeDeletionAssert.column();
    PositionWithPoints positionDeletion = (PositionWithPoints) fieldPosition.get(changeColumnDeletionAssert);
    Assertions.assertThat(fieldRowAssert.get(positionDeletion)).isNull();
    ChangeColumnValueAssert changeColumnValueDeletionAssert = changeColumnDeletionAssert.valueAtEndPoint();
    Assertions.assertThat(fieldRowAssert.get(positionDeletion)).isNotNull();
    ChangeColumnValueAssert changeDeletionRowValueAssertBis = changeColumnDeletionAssert.valueAtEndPoint();
    Assertions.assertThat(changeColumnValueDeletionAssert).isSameAs(changeDeletionRowValueAssertBis);
    Assertions.assertThat(((Value) fieldValueFromColumnAssert.get(changeColumnDeletionAssert)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueFromValueAssert.get(changeColumnValueDeletionAssert)).getValue()).isNull();
  }

  /**
   * This method tests the {@code valueAtEndPoint} navigation method.
   */
  @Test
  @NeedReload
  public void test_value_at_end_point_with_displays() throws Exception {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    Field fieldPosition = ChangeColumnDisplay.class.getDeclaredField("valuePosition");
    fieldPosition.setAccessible(true);
    Field fieldRowDisplay = PositionWithPoints.class.getDeclaredField("instanceAtEndPoint");
    fieldRowDisplay.setAccessible(true);
    Field fieldValueFromColumnDisplay = ChangeColumnDisplay.class.getDeclaredField("valueAtEndPoint");
    fieldValueFromColumnDisplay.setAccessible(true);
    Field fieldValueFromValueDisplay = AbstractDisplayWithValues.class.getDeclaredField("value");
    fieldValueFromValueDisplay.setAccessible(true);

    ChangesDisplay changesDisplay = display(changes);

    ChangeDisplay changeCreationDisplay = changesDisplay.change();
    ChangeColumnDisplay changeColumnCreationDisplay = changeCreationDisplay.column();
    PositionWithPoints positionCreation = (PositionWithPoints) fieldPosition.get(changeColumnCreationDisplay);
    Assertions.assertThat(fieldRowDisplay.get(positionCreation)).isNull();
    ChangeColumnValueDisplay changeColumnValueCreationDisplay = changeColumnCreationDisplay.valueAtEndPoint();
    Assertions.assertThat(fieldRowDisplay.get(positionCreation)).isNotNull();
    ChangeColumnValueDisplay changeCreationRowValueDisplayBis = changeColumnCreationDisplay.valueAtEndPoint();
    Assertions.assertThat(changeColumnValueCreationDisplay).isSameAs(changeCreationRowValueDisplayBis);
    Assertions.assertThat(((Value) fieldValueFromColumnDisplay.get(changeColumnCreationDisplay)).getValue()).isSameAs(
            ((Value) fieldValueFromValueDisplay.get(changeColumnValueCreationDisplay)).getValue()).isEqualTo(
            new BigDecimal("4"));

    ChangeDisplay changeModificationDisplay = changesDisplay.change(3);
    ChangeColumnDisplay changeColumnModificationDisplay = changeModificationDisplay.column();
    PositionWithPoints positionModification = (PositionWithPoints) fieldPosition.get(changeColumnModificationDisplay);
    Assertions.assertThat(fieldRowDisplay.get(positionModification)).isNull();
    ChangeColumnValueDisplay changeColumnValueModificationDisplay = changeColumnModificationDisplay.valueAtEndPoint();
    Assertions.assertThat(fieldRowDisplay.get(positionModification)).isNotNull();
    ChangeColumnValueDisplay changeModificationRowValueDisplayBis = changeColumnValueModificationDisplay.valueAtEndPoint();
    Assertions.assertThat(changeColumnValueModificationDisplay).isSameAs(changeModificationRowValueDisplayBis);
    Assertions.assertThat(((Value) fieldValueFromColumnDisplay.get(changeColumnModificationDisplay)).getValue()).isSameAs(
            ((Value) fieldValueFromValueDisplay.get(changeColumnValueModificationDisplay)).getValue()).isEqualTo(
            new BigDecimal("1"));

    ChangeDisplay changeDeletionDisplay = changesDisplay.change(6);
    ChangeColumnDisplay changeColumnDeletionDisplay = changeDeletionDisplay.column();
    PositionWithPoints positionDeletion = (PositionWithPoints) fieldPosition.get(changeColumnDeletionDisplay);
    Assertions.assertThat(fieldRowDisplay.get(positionDeletion)).isNull();
    ChangeColumnValueDisplay changeColumnValueDeletionDisplay = changeColumnDeletionDisplay.valueAtEndPoint();
    Assertions.assertThat(fieldRowDisplay.get(positionDeletion)).isNotNull();
    ChangeColumnValueDisplay changeDeletionRowValueDisplayBis = changeColumnDeletionDisplay.valueAtEndPoint();
    Assertions.assertThat(changeColumnValueDeletionDisplay).isSameAs(changeDeletionRowValueDisplayBis);
    Assertions.assertThat(((Value) fieldValueFromColumnDisplay.get(changeColumnDeletionDisplay)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueFromValueDisplay.get(changeColumnValueDeletionDisplay)).getValue()).isNull();
  }
}
