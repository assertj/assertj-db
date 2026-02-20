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

import static org.assertj.core.api.Assertions.fail;
import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.assertj.db.api.ChangeAssert;
import org.assertj.db.api.ChangeColumnAssert;
import org.assertj.db.api.ChangesAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.assertj.db.exception.AssertJDBException;
import org.assertj.db.output.ChangeColumnOutputter;
import org.assertj.db.output.ChangeOutputter;
import org.assertj.db.output.ChangesOutputter;
import org.assertj.db.type.Changes;
import org.assertj.db.type.Value;
import org.junit.Test;

/**
 * Tests on {@link org.assertj.db.navigation.ToColumnFromChange} class :
 * {@link org.assertj.db.navigation.ToColumnFromChange#columnAmongTheModifiedOnes()} method.
 *
 * @author Régis Pouiller
 * @author Julien Roy
 */
public class ToColumnFromChange_ColumnAmongTheModifiedOnes_Test extends AbstractTest {

  /**
   * This method tests the {@code columnAmongTheModifiedOnes} navigation method.
   */
  @Test
  @NeedReload
  public void test_column_among_the_modified_ones_with_assertions() throws Exception {
    Changes changes = assertDbConnection.changes().build().setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    Field fieldPosition = ChangeAssert.class.getDeclaredField("columnPosition");
    fieldPosition.setAccessible(true);
    Field fieldIndex = PositionWithColumnsChange.class.getDeclaredField("nextIndex");
    fieldIndex.setAccessible(true);
    Field fieldColumnName = ChangeColumnAssert.class.getDeclaredField("columnName");
    fieldColumnName.setAccessible(true);
    Field fieldValueAtStartPoint = ChangeColumnAssert.class.getDeclaredField("valueAtStartPoint");
    fieldValueAtStartPoint.setAccessible(true);
    Field fieldValueAtEndPoint = ChangeColumnAssert.class.getDeclaredField("valueAtEndPoint");
    fieldValueAtEndPoint.setAccessible(true);

    ChangesAssert changesAssert = assertThat(changes);
    ChangeAssert changeAssert = changesAssert.change();
    PositionWithColumnsChange<ChangeAssert, ChangeColumnAssert> position =
      (PositionWithColumnsChange) fieldPosition.get(changeAssert);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(0);
    ChangeColumnAssert changeColumnAssert0 = changeAssert.columnAmongTheModifiedOnes();
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(1);
    ChangeColumnAssert changeColumnAssert1 = changeAssert.columnAmongTheModifiedOnes();
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(2);
    ChangeColumnAssert changeColumnAssert2 = changeAssert.columnAmongTheModifiedOnes();
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(3);
    ChangeColumnAssert changeColumnAssert3 = changeAssert.columnAmongTheModifiedOnes();
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(4);
    ChangeColumnAssert changeColumnAssert4 = changeAssert.columnAmongTheModifiedOnes();
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(5);
    try {
      changeAssert.columnAmongTheModifiedOnes();
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("No more modified columns");
    }

    ChangesAssert changesAssertBis = assertThat(changes);
    ChangeAssert changeAssertBis = changesAssertBis.change();
    PositionWithColumnsChange<ChangeAssert, ChangeColumnAssert> positionBis =
      (PositionWithColumnsChange) fieldPosition.get(changeAssertBis);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(0);
    ChangeColumnAssert changeColumnAssertBis0 = changeAssertBis.columnAmongTheModifiedOnes();
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(1);
    ChangeColumnAssert changeColumnAssertBis1 = changeColumnAssertBis0.columnAmongTheModifiedOnes();
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(2);
    ChangeColumnAssert changeColumnAssertBis2 = changeColumnAssertBis1.columnAmongTheModifiedOnes();
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(3);
    ChangeColumnAssert changeColumnAssertBis3 = changeColumnAssertBis2.columnAmongTheModifiedOnes();
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(4);
    ChangeColumnAssert changeColumnAssertBis4 = changeColumnAssertBis3.columnAmongTheModifiedOnes();
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(5);
    try {
      changeColumnAssertBis4.columnAmongTheModifiedOnes();
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("No more modified columns");
    }

    Assertions.assertThat(fieldColumnName.get(changeColumnAssert0)).isEqualTo(fieldColumnName.get(changeColumnAssertBis0)).isEqualTo(
      "ID");
    Assertions.assertThat(fieldColumnName.get(changeColumnAssert1)).isEqualTo(fieldColumnName.get(changeColumnAssertBis1)).isEqualTo(
      "NAME");
    Assertions.assertThat(fieldColumnName.get(changeColumnAssert2)).isEqualTo(fieldColumnName.get(changeColumnAssertBis2)).isEqualTo(
      "FIRSTNAME");
    Assertions.assertThat(fieldColumnName.get(changeColumnAssert3)).isEqualTo(fieldColumnName.get(changeColumnAssertBis3)).isEqualTo(
      "BIRTH");
    Assertions.assertThat(fieldColumnName.get(changeColumnAssert4)).isEqualTo(fieldColumnName.get(changeColumnAssertBis4)).isEqualTo(
      "ACTOR_IMDB");

    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnAssert0)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnAssert1)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnAssert2)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnAssert3)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnAssert4)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnAssertBis0)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnAssertBis1)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnAssertBis2)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnAssertBis3)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnAssertBis4)).getValue()).isNull();

    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnAssert0)).getValue()).isEqualTo(
      ((Value) fieldValueAtEndPoint.get(changeColumnAssertBis0)).getValue()).isEqualTo(
      new BigDecimal("4"));
    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnAssert1)).getValue()).
      isEqualTo(((Value) fieldValueAtEndPoint.get(changeColumnAssertBis1)).getValue()).isEqualTo(
        "Murray");
    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnAssert2)).getValue()).
      isEqualTo(((Value) fieldValueAtEndPoint.get(changeColumnAssertBis2)).getValue()).isEqualTo(
        "Bill");
    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnAssert3)).getValue()).
      isEqualTo(((Value) fieldValueAtEndPoint.get(changeColumnAssertBis3)).getValue()).isEqualTo(
        Date.valueOf("1950-09-21"));
    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnAssert4)).getValue()).
      isEqualTo(((Value) fieldValueAtEndPoint.get(changeColumnAssertBis4)).getValue()).
      isEqualTo(
        UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"));

    ChangeColumnAssert changeColumnAssert = assertThat(changes).change(3).columnAmongTheModifiedOnes();
    try {
      changeColumnAssert.columnAmongTheModifiedOnes();
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("No more modified columns");
    }
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnAssert)).getValue()).isEqualTo("Sigourney");
    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnAssert)).getValue()).isEqualTo(
      "Susan Alexandra");
  }

  /**
   * This method tests the {@code columnAmongTheModifiedOnes} navigation method.
   */
  @Test
  @NeedReload
  public void test_column_among_the_modified_ones_with_displays() throws Exception {
    Changes changes = assertDbConnection.changes().build().setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    Field fieldPosition = ChangeOutputter.class.getDeclaredField("columnPosition");
    fieldPosition.setAccessible(true);
    Field fieldIndex = PositionWithColumnsChange.class.getDeclaredField("nextIndex");
    fieldIndex.setAccessible(true);
    Field fieldColumnName = ChangeColumnOutputter.class.getDeclaredField("columnName");
    fieldColumnName.setAccessible(true);
    Field fieldValueAtStartPoint = ChangeColumnOutputter.class.getDeclaredField("valueAtStartPoint");
    fieldValueAtStartPoint.setAccessible(true);
    Field fieldValueAtEndPoint = ChangeColumnOutputter.class.getDeclaredField("valueAtEndPoint");
    fieldValueAtEndPoint.setAccessible(true);

    ChangesOutputter changesOutputter = output(changes);
    ChangeOutputter changeOutputter = changesOutputter.change();
    PositionWithColumnsChange<ChangeAssert, ChangeColumnAssert> position =
      (PositionWithColumnsChange) fieldPosition.get(changeOutputter);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(0);
    ChangeColumnOutputter changeColumnOutputter0 = changeOutputter.columnAmongTheModifiedOnes();
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(1);
    ChangeColumnOutputter changeColumnOutputter1 = changeOutputter.columnAmongTheModifiedOnes();
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(2);
    ChangeColumnOutputter changeColumnOutputter2 = changeOutputter.columnAmongTheModifiedOnes();
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(3);
    ChangeColumnOutputter changeColumnOutputter3 = changeOutputter.columnAmongTheModifiedOnes();
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(4);
    ChangeColumnOutputter changeColumnOutputter4 = changeOutputter.columnAmongTheModifiedOnes();
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(5);
    try {
      changeOutputter.columnAmongTheModifiedOnes();
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("No more modified columns");
    }

    ChangesOutputter changesOutputterBis = output(changes);
    ChangeOutputter changeOutputterBis = changesOutputterBis.change();
    PositionWithColumnsChange<ChangeAssert, ChangeColumnAssert> positionBis =
      (PositionWithColumnsChange) fieldPosition.get(changeOutputterBis);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(0);
    ChangeColumnOutputter changeColumnOutputterBis0 = changeOutputterBis.columnAmongTheModifiedOnes();
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(1);
    ChangeColumnOutputter changeColumnOutputterBis1 = changeColumnOutputterBis0.columnAmongTheModifiedOnes();
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(2);
    ChangeColumnOutputter changeColumnOutputterBis2 = changeColumnOutputterBis1.columnAmongTheModifiedOnes();
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(3);
    ChangeColumnOutputter changeColumnOutputterBis3 = changeColumnOutputterBis2.columnAmongTheModifiedOnes();
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(4);
    ChangeColumnOutputter changeColumnOutputterBis4 = changeColumnOutputterBis3.columnAmongTheModifiedOnes();
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(5);
    try {
      changeColumnOutputterBis4.columnAmongTheModifiedOnes();
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("No more modified columns");
    }

    Assertions.assertThat(fieldColumnName.get(changeColumnOutputter0)).isEqualTo(fieldColumnName.get(changeColumnOutputterBis0)).isEqualTo(
      "ID");
    Assertions.assertThat(fieldColumnName.get(changeColumnOutputter1)).isEqualTo(fieldColumnName.get(changeColumnOutputterBis1)).isEqualTo(
      "NAME");
    Assertions.assertThat(fieldColumnName.get(changeColumnOutputter2)).isEqualTo(fieldColumnName.get(changeColumnOutputterBis2)).isEqualTo(
      "FIRSTNAME");
    Assertions.assertThat(fieldColumnName.get(changeColumnOutputter3)).isEqualTo(fieldColumnName.get(changeColumnOutputterBis3)).isEqualTo(
      "BIRTH");
    Assertions.assertThat(fieldColumnName.get(changeColumnOutputter4)).isEqualTo(fieldColumnName.get(changeColumnOutputterBis4)).isEqualTo(
      "ACTOR_IMDB");

    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnOutputter0)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnOutputter1)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnOutputter2)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnOutputter3)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnOutputter4)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnOutputterBis0)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnOutputterBis1)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnOutputterBis2)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnOutputterBis3)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnOutputterBis4)).getValue()).isNull();

    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnOutputter0)).getValue()).isEqualTo(
      ((Value) fieldValueAtEndPoint.get(changeColumnOutputterBis0)).getValue()).isEqualTo(
      new BigDecimal("4"));
    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnOutputter1)).getValue()).
      isEqualTo(((Value) fieldValueAtEndPoint.get(changeColumnOutputterBis1)).getValue()).isEqualTo(
        "Murray");
    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnOutputter2)).getValue()).
      isEqualTo(((Value) fieldValueAtEndPoint.get(changeColumnOutputterBis2)).getValue()).isEqualTo(
        "Bill");
    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnOutputter3)).getValue()).
      isEqualTo(((Value) fieldValueAtEndPoint.get(changeColumnOutputterBis3)).getValue()).isEqualTo(
        Date.valueOf("1950-09-21"));
    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnOutputter4)).getValue()).
      isEqualTo(((Value) fieldValueAtEndPoint.get(changeColumnOutputterBis4)).getValue()).
      isEqualTo(
        UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"));

    ChangeColumnOutputter changeColumnOutputter = output(changes).change(3).columnAmongTheModifiedOnes();
    try {
      changeColumnOutputter.columnAmongTheModifiedOnes();
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("No more modified columns");
    }
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnOutputter)).getValue()).isEqualTo("Sigourney");
    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnOutputter)).getValue()).isEqualTo(
      "Susan Alexandra");
  }
}
