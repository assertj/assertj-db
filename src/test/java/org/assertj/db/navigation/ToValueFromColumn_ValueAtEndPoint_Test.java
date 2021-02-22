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
 * Copyright 2015-2021 the original author or authors.
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

    Field fieldPosition = ChangeColumnOutputter.class.getDeclaredField("valuePosition");
    fieldPosition.setAccessible(true);
    Field fieldRowOutputter = PositionWithPoints.class.getDeclaredField("instanceAtEndPoint");
    fieldRowOutputter.setAccessible(true);
    Field fieldValueFromColumnOutputter = ChangeColumnOutputter.class.getDeclaredField("valueAtEndPoint");
    fieldValueFromColumnOutputter.setAccessible(true);
    Field fieldValueFromValueOutputter = AbstractOutputterWithValues.class.getDeclaredField("value");
    fieldValueFromValueOutputter.setAccessible(true);

    ChangesOutputter changesOutputter = output(changes);

    ChangeOutputter changeCreationOutputter = changesOutputter.change();
    ChangeColumnOutputter changeColumnCreationOutputter = changeCreationOutputter.column();
    PositionWithPoints positionCreation = (PositionWithPoints) fieldPosition.get(changeColumnCreationOutputter);
    Assertions.assertThat(fieldRowOutputter.get(positionCreation)).isNull();
    ChangeColumnValueOutputter changeColumnValueCreationOutputter = changeColumnCreationOutputter.valueAtEndPoint();
    Assertions.assertThat(fieldRowOutputter.get(positionCreation)).isNotNull();
    ChangeColumnValueOutputter changeCreationRowValueOutputterBis = changeColumnCreationOutputter.valueAtEndPoint();
    Assertions.assertThat(changeColumnValueCreationOutputter).isSameAs(changeCreationRowValueOutputterBis);
    Assertions.assertThat(((Value) fieldValueFromColumnOutputter.get(changeColumnCreationOutputter)).getValue()).isSameAs(
            ((Value) fieldValueFromValueOutputter.get(changeColumnValueCreationOutputter)).getValue()).isEqualTo(
            new BigDecimal("4"));

    ChangeOutputter changeModificationOutputter = changesOutputter.change(3);
    ChangeColumnOutputter changeColumnModificationOutputter = changeModificationOutputter.column();
    PositionWithPoints positionModification = (PositionWithPoints) fieldPosition.get(changeColumnModificationOutputter);
    Assertions.assertThat(fieldRowOutputter.get(positionModification)).isNull();
    ChangeColumnValueOutputter changeColumnValueModificationOutputter = changeColumnModificationOutputter.valueAtEndPoint();
    Assertions.assertThat(fieldRowOutputter.get(positionModification)).isNotNull();
    ChangeColumnValueOutputter changeModificationRowValueOutputterBis = changeColumnValueModificationOutputter.valueAtEndPoint();
    Assertions.assertThat(changeColumnValueModificationOutputter).isSameAs(changeModificationRowValueOutputterBis);
    Assertions.assertThat(((Value) fieldValueFromColumnOutputter.get(changeColumnModificationOutputter)).getValue()).isSameAs(
            ((Value) fieldValueFromValueOutputter.get(changeColumnValueModificationOutputter)).getValue()).isEqualTo(
            new BigDecimal("1"));

    ChangeOutputter changeDeletionOutputter = changesOutputter.change(6);
    ChangeColumnOutputter changeColumnDeletionOutputter = changeDeletionOutputter.column();
    PositionWithPoints positionDeletion = (PositionWithPoints) fieldPosition.get(changeColumnDeletionOutputter);
    Assertions.assertThat(fieldRowOutputter.get(positionDeletion)).isNull();
    ChangeColumnValueOutputter changeColumnValueDeletionOutputter = changeColumnDeletionOutputter.valueAtEndPoint();
    Assertions.assertThat(fieldRowOutputter.get(positionDeletion)).isNotNull();
    ChangeColumnValueOutputter changeDeletionRowValueOutputterBis = changeColumnDeletionOutputter.valueAtEndPoint();
    Assertions.assertThat(changeColumnValueDeletionOutputter).isSameAs(changeDeletionRowValueOutputterBis);
    Assertions.assertThat(((Value) fieldValueFromColumnOutputter.get(changeColumnDeletionOutputter)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueFromValueOutputter.get(changeColumnValueDeletionOutputter)).getValue()).isNull();
  }
}
