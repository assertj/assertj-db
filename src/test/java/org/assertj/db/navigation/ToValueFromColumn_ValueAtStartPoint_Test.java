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
import org.assertj.db.api.*;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.assertj.db.output.*;
import org.assertj.db.type.Changes;
import org.assertj.db.type.Value;
import org.junit.Test;

import java.lang.reflect.Field;
import java.math.BigDecimal;

import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;

/**
 * Tests on {@link org.assertj.db.navigation.ToValueFromColumn} class :
 * {@link org.assertj.db.navigation.ToValueFromColumn#valueAtStartPoint()} method.
 *
 * @author Régis Pouiller
 *
 */
public class ToValueFromColumn_ValueAtStartPoint_Test extends AbstractTest {

  /**
   * This method tests the {@code valueAtStartPoint} navigation method.
   */
  @Test
  @NeedReload
  public void test_value_at_start_point_with_assertions() throws Exception {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    Field fieldPosition = ChangeColumnAssert.class.getDeclaredField("valuePosition");
    fieldPosition.setAccessible(true);
    Field fieldRowAssert = PositionWithPoints.class.getDeclaredField("instanceAtStartPoint");
    fieldRowAssert.setAccessible(true);
    Field fieldValueFromColumnAssert = ChangeColumnAssert.class.getDeclaredField("valueAtStartPoint");
    fieldValueFromColumnAssert.setAccessible(true);
    Field fieldValueFromValueAssert = AbstractAssertWithValues.class.getDeclaredField("value");
    fieldValueFromValueAssert.setAccessible(true);

    ChangesAssert changesAssert = assertThat(changes);

    ChangeAssert changeCreationAssert = changesAssert.change();
    ChangeColumnAssert changeColumnCreationAssert = changeCreationAssert.column();
    PositionWithPoints positionCreation = (PositionWithPoints) fieldPosition.get(changeColumnCreationAssert);
    Assertions.assertThat(fieldRowAssert.get(positionCreation)).isNull();
    ChangeColumnValueAssert changeColumnValueCreationAssert = changeColumnCreationAssert.valueAtStartPoint();
    Assertions.assertThat(fieldRowAssert.get(positionCreation)).isNotNull();
    ChangeColumnValueAssert changeCreationRowValueAssertBis = changeColumnCreationAssert.valueAtStartPoint();
    Assertions.assertThat(changeColumnValueCreationAssert).isSameAs(
            changeCreationRowValueAssertBis);
    Assertions.assertThat(((Value) fieldValueFromColumnAssert.get(changeColumnCreationAssert)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueFromValueAssert.get(changeColumnValueCreationAssert)).getValue()).isNull();

    ChangeAssert changeModificationAssert = changesAssert.change(3);
    ChangeColumnAssert changeColumnModificationAssert = changeModificationAssert.column();
    PositionWithPoints positionModification = (PositionWithPoints) fieldPosition.get(changeColumnModificationAssert);
    Assertions.assertThat(fieldRowAssert.get(positionModification)).isNull();
    ChangeColumnValueAssert changeColumnValueModificationAssert = changeColumnModificationAssert.valueAtStartPoint();
    Assertions.assertThat(fieldRowAssert.get(positionModification)).isNotNull();
    ChangeColumnValueAssert changeModificationRowValueAssertBis = changeColumnValueModificationAssert.valueAtStartPoint();
    Assertions.assertThat(changeColumnValueModificationAssert).isSameAs(changeModificationRowValueAssertBis);
    Assertions.assertThat(((Value) fieldValueFromColumnAssert.get(changeColumnModificationAssert)).getValue()).isSameAs(
            ((Value) fieldValueFromValueAssert.get(changeColumnValueModificationAssert)).getValue()).isEqualTo(
            new BigDecimal("1"));

    ChangeAssert changeDeletionAssert = changesAssert.change(6);
    ChangeColumnAssert changeColumnDeletionAssert = changeDeletionAssert.column();
    PositionWithPoints positionDeletion = (PositionWithPoints) fieldPosition.get(changeColumnDeletionAssert);
    Assertions.assertThat(fieldRowAssert.get(positionDeletion)).isNull();
    ChangeColumnValueAssert changeColumnValueDeletionAssert = changeColumnDeletionAssert.valueAtStartPoint();
    Assertions.assertThat(fieldRowAssert.get(positionDeletion)).isNotNull();
    ChangeColumnValueAssert changeDeletionRowValueAssertBis = changeColumnDeletionAssert.valueAtStartPoint();
    Assertions.assertThat(changeColumnValueDeletionAssert).isSameAs(changeDeletionRowValueAssertBis);
    Assertions.assertThat(((Value) fieldValueFromColumnAssert.get(changeColumnDeletionAssert)).getValue()).isSameAs(
            ((Value) fieldValueFromValueAssert.get(changeColumnValueDeletionAssert)).getValue()).isEqualTo(
            new BigDecimal("3"));
  }

  /**
   * This method tests the {@code valueAtStartPoint} navigation method.
   */
  @Test
  @NeedReload
  public void test_value_at_start_point_with_displays() throws Exception {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    Field fieldPosition = ChangeColumnOutputter.class.getDeclaredField("valuePosition");
    fieldPosition.setAccessible(true);
    Field fieldRowOutputter = PositionWithPoints.class.getDeclaredField("instanceAtStartPoint");
    fieldRowOutputter.setAccessible(true);
    Field fieldValueFromColumnOutputter = ChangeColumnOutputter.class.getDeclaredField("valueAtStartPoint");
    fieldValueFromColumnOutputter.setAccessible(true);
    Field fieldValueFromValueOutputter = AbstractOutputterWithValues.class.getDeclaredField("value");
    fieldValueFromValueOutputter.setAccessible(true);

    ChangesOutputter changesOutputter = output(changes);

    ChangeOutputter changeCreationOutputter = changesOutputter.change();
    ChangeColumnOutputter changeColumnCreationOutputter = changeCreationOutputter.column();
    PositionWithPoints positionCreation = (PositionWithPoints) fieldPosition.get(changeColumnCreationOutputter);
    Assertions.assertThat(fieldRowOutputter.get(positionCreation)).isNull();
    ChangeColumnValueOutputter changeColumnValueCreationOutputter = changeColumnCreationOutputter.valueAtStartPoint();
    Assertions.assertThat(fieldRowOutputter.get(positionCreation)).isNotNull();
    ChangeColumnValueOutputter changeCreationRowValueOutputterBis = changeColumnCreationOutputter.valueAtStartPoint();
    Assertions.assertThat(changeColumnValueCreationOutputter).isSameAs(
            changeCreationRowValueOutputterBis);
    Assertions.assertThat(((Value) fieldValueFromColumnOutputter.get(changeColumnCreationOutputter)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueFromValueOutputter.get(changeColumnValueCreationOutputter)).getValue()).isNull();

    ChangeOutputter changeModificationOutputter = changesOutputter.change(3);
    ChangeColumnOutputter changeColumnModificationOutputter = changeModificationOutputter.column();
    PositionWithPoints positionModification = (PositionWithPoints) fieldPosition.get(changeColumnModificationOutputter);
    Assertions.assertThat(fieldRowOutputter.get(positionModification)).isNull();
    ChangeColumnValueOutputter changeColumnValueModificationOutputter = changeColumnModificationOutputter.valueAtStartPoint();
    Assertions.assertThat(fieldRowOutputter.get(positionModification)).isNotNull();
    ChangeColumnValueOutputter changeModificationRowValueOutputterBis = changeColumnValueModificationOutputter.valueAtStartPoint();
    Assertions.assertThat(changeColumnValueModificationOutputter).isSameAs(changeModificationRowValueOutputterBis);
    Assertions.assertThat(((Value) fieldValueFromColumnOutputter.get(changeColumnModificationOutputter)).getValue()).isSameAs(
            ((Value) fieldValueFromValueOutputter.get(changeColumnValueModificationOutputter)).getValue()).isEqualTo(
            new BigDecimal("1"));

    ChangeOutputter changeDeletionOutputter = changesOutputter.change(6);
    ChangeColumnOutputter changeColumnDeletionOutputter = changeDeletionOutputter.column();
    PositionWithPoints positionDeletion = (PositionWithPoints) fieldPosition.get(changeColumnDeletionOutputter);
    Assertions.assertThat(fieldRowOutputter.get(positionDeletion)).isNull();
    ChangeColumnValueOutputter changeColumnValueDeletionOutputter = changeColumnDeletionOutputter.valueAtStartPoint();
    Assertions.assertThat(fieldRowOutputter.get(positionDeletion)).isNotNull();
    ChangeColumnValueOutputter changeDeletionRowValueOutputterBis = changeColumnDeletionOutputter.valueAtStartPoint();
    Assertions.assertThat(changeColumnValueDeletionOutputter).isSameAs(changeDeletionRowValueOutputterBis);
    Assertions.assertThat(((Value) fieldValueFromColumnOutputter.get(changeColumnDeletionOutputter)).getValue()).isSameAs(
            ((Value) fieldValueFromValueOutputter.get(changeColumnValueDeletionOutputter)).getValue()).isEqualTo(
            new BigDecimal("3"));
  }
}
