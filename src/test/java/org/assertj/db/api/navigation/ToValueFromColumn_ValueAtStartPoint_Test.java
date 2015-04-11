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
import org.assertj.db.api.*;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.assertj.db.type.Changes;
import org.junit.Test;

import java.lang.reflect.Field;
import java.math.BigDecimal;

import static org.assertj.db.api.Assertions.assertThat;

/**
 * Tests on {@link ToValueFromColumn} class :
 * {@link org.assertj.db.api.navigation.ToValueFromColumn#valueAtStartPoint()} method.
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
  public void test_value_at_start_point() throws Exception {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    Field fieldValueAssert = ChangeColumnAssert.class.getDeclaredField("changeColumnValueAssertAtStartPoint");
    fieldValueAssert.setAccessible(true);
    Field fieldValueFromColumnAssert = ChangeColumnAssert.class.getDeclaredField("valueAtStartPoint");
    fieldValueFromColumnAssert.setAccessible(true);
    Field fieldValueFromValueAssert = AbstractAssertWithValues.class.getDeclaredField("value");
    fieldValueFromValueAssert.setAccessible(true);

    ChangesAssert changesAssert = assertThat(changes);

    ChangeAssert changeCreationAssert = changesAssert.change();
    ChangeColumnAssert changeColumnCreationAssert = changeCreationAssert.column();
    Assertions.assertThat(fieldValueAssert.get(changeColumnCreationAssert)).isNull();
    ChangeColumnValueAssert changeColumnValueCreationAssert = changeColumnCreationAssert.valueAtStartPoint();
    Assertions.assertThat(fieldValueAssert.get(changeColumnCreationAssert)).isNotNull();
    ChangeColumnValueAssert changeCreationRowValueAssertBis = changeColumnCreationAssert.valueAtStartPoint();
    Assertions.assertThat(changeColumnValueCreationAssert).isSameAs(changeCreationRowValueAssertBis);
    Assertions.assertThat(fieldValueFromColumnAssert.get(changeColumnCreationAssert)).isNull();
    Assertions.assertThat(fieldValueFromValueAssert.get(changeColumnValueCreationAssert)).isNull();

    ChangeAssert changeModificationAssert = changesAssert.change(3);
    ChangeColumnAssert changeColumnModificationAssert = changeModificationAssert.column();
    Assertions.assertThat(fieldValueAssert.get(changeColumnModificationAssert)).isNull();
    ChangeColumnValueAssert changeColumnValueModificationAssert = changeColumnModificationAssert.valueAtStartPoint();
    Assertions.assertThat(fieldValueAssert.get(changeColumnModificationAssert)).isNotNull();
    ChangeColumnValueAssert changeModificationRowValueAssertBis = changeColumnValueModificationAssert.valueAtStartPoint();
    Assertions.assertThat(changeColumnValueModificationAssert).isSameAs(changeModificationRowValueAssertBis);
    Assertions.assertThat(fieldValueFromColumnAssert.get(changeColumnModificationAssert)).isSameAs(
            fieldValueFromValueAssert.get(changeColumnValueModificationAssert)).isEqualTo(new BigDecimal("1"));

    ChangeAssert changeDeletionAssert = changesAssert.change(6);
    ChangeColumnAssert changeColumnDeletionAssert = changeDeletionAssert.column();
    Assertions.assertThat(fieldValueAssert.get(changeColumnDeletionAssert)).isNull();
    ChangeColumnValueAssert changeColumnValueDeletionAssert = changeColumnDeletionAssert.valueAtStartPoint();
    Assertions.assertThat(fieldValueAssert.get(changeColumnDeletionAssert)).isNotNull();
    ChangeColumnValueAssert changeDeletionRowValueAssertBis = changeColumnDeletionAssert.valueAtStartPoint();
    Assertions.assertThat(changeColumnValueDeletionAssert).isSameAs(changeDeletionRowValueAssertBis);
    Assertions.assertThat(fieldValueFromColumnAssert.get(changeColumnDeletionAssert)).isSameAs(
            fieldValueFromValueAssert.get(changeColumnValueDeletionAssert)).isEqualTo(new BigDecimal("3"));
  }
}
