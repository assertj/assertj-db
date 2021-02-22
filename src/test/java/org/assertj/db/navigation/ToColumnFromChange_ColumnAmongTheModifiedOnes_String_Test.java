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

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Date;

import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;
import static org.junit.Assert.fail;

/**
 * Tests on {@link org.assertj.db.navigation.ToColumnFromChange} class :
 * {@link org.assertj.db.navigation.ToColumnFromChange#columnAmongTheModifiedOnes()} method.
 *
 * @author Régis Pouiller
 *
 */
public class ToColumnFromChange_ColumnAmongTheModifiedOnes_String_Test extends AbstractTest {

  /**
   * This method tests the {@code columnAmongTheModifiedOnes} navigation method.
   */
  @Test
  @NeedReload
  public void test_column_among_the_modified_ones_with_column_name_with_assertions() throws Exception {
    Changes changes = new Changes(source).setStartPointNow();
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
    ChangeColumnAssert changeColumnAssert0 = changeAssert.columnAmongTheModifiedOnes("ID");
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(1);
    ChangeColumnAssert changeColumnAssert1 = changeAssert.columnAmongTheModifiedOnes("NAME");
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(2);
    ChangeColumnAssert changeColumnAssert2 = changeAssert.columnAmongTheModifiedOnes("FIRSTNAME");
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(3);
    ChangeColumnAssert changeColumnAssert3 = changeAssert.columnAmongTheModifiedOnes("BIRTH");
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(4);
    try {
      changeAssert.columnAmongTheModifiedOnes("TEST");
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("Column <TEST> does not exist among the modified columns%n"
                                                      + "in <[ID, NAME, FIRSTNAME, BIRTH, ACTOR_IMDB]>%n"
                                                      + "with comparison IGNORE - Ignore the case"));
    }
    try {
      changeAssert.columnAmongTheModifiedOnes(null);
      fail("An exception must be raised");
    } catch (NullPointerException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Column name must be not null");
    }

    ChangesAssert changesAssertBis = assertThat(changes);
    ChangeAssert changeAssertBis = changesAssertBis.change();
    PositionWithColumnsChange<ChangeAssert, ChangeColumnAssert> positionBis = 
              (PositionWithColumnsChange) fieldPosition.get(changeAssertBis);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(0);
    ChangeColumnAssert changeColumnAssertBis0 = changeAssertBis.columnAmongTheModifiedOnes("ID");
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(1);
    ChangeColumnAssert changeColumnAssertBis1 = changeColumnAssertBis0.columnAmongTheModifiedOnes("NAME");
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(2);
    ChangeColumnAssert changeColumnAssertBis2 = changeColumnAssertBis1.columnAmongTheModifiedOnes("FIRSTNAME");
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(3);
    ChangeColumnAssert changeColumnAssertBis3 = changeColumnAssertBis2.columnAmongTheModifiedOnes("BIRTH");
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(4);
    try {
      changeColumnAssertBis3.columnAmongTheModifiedOnes("TEST");
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("Column <TEST> does not exist among the modified columns%n"
                                                                    + "in <[ID, NAME, FIRSTNAME, BIRTH, ACTOR_IMDB]>%n"
                                                                    + "with comparison IGNORE - Ignore the case"));
    }
    try {
      changeColumnAssertBis3.columnAmongTheModifiedOnes(null);
      fail("An exception must be raised");
    } catch (NullPointerException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Column name must be not null");
    }

    Assertions.assertThat(fieldColumnName.get(changeColumnAssert0)).isEqualTo(fieldColumnName.get(changeColumnAssertBis0)).isEqualTo(
            "ID");
    Assertions.assertThat(fieldColumnName.get(changeColumnAssert1)).isEqualTo(fieldColumnName.get(changeColumnAssertBis1)).isEqualTo(
            "NAME");
    Assertions.assertThat(fieldColumnName.get(changeColumnAssert2)).isEqualTo(fieldColumnName.get(changeColumnAssertBis2)).isEqualTo(
            "FIRSTNAME");
    Assertions.assertThat(fieldColumnName.get(changeColumnAssert3)).isEqualTo(fieldColumnName.get(changeColumnAssertBis3)).isEqualTo(
            "BIRTH");

    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnAssert0)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnAssert1)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnAssert2)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnAssert3)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnAssertBis0)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnAssertBis1)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnAssertBis2)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnAssertBis3)).getValue()).isNull();

    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnAssert0)).getValue()).isEqualTo(
            ((Value) fieldValueAtEndPoint.get(changeColumnAssertBis0)).getValue()).isEqualTo(
            new BigDecimal("4"));
    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnAssert1)).getValue()).
    isEqualTo(((Value) fieldValueAtEndPoint.get(changeColumnAssertBis1)).getValue()).
    isEqualTo("Murray");
    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnAssert2)).getValue()).
    isEqualTo(((Value) fieldValueAtEndPoint.get(changeColumnAssertBis2)).getValue()).
    isEqualTo("Bill");
    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnAssert3)).getValue()).
    isEqualTo(((Value) fieldValueAtEndPoint.get(changeColumnAssertBis3)).getValue()).
    isEqualTo(
            Date.valueOf("1950-09-21"));

    ChangeColumnAssert changeColumnAssert = assertThat(changes).change(3).columnAmongTheModifiedOnes("firstname");
    try {
      changeColumnAssert.columnAmongTheModifiedOnes("birth");
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("Column <birth> does not exist among the modified columns%n"
                                                                    + "in <[FIRSTNAME]>%n"
                                                                    + "with comparison IGNORE - Ignore the case"));
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
  public void test_column_among_the_modified_ones_with_column_name_with_displays() throws Exception {
    Changes changes = new Changes(source).setStartPointNow();
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

    ChangesOutputter changesDisplay = output(changes);
    ChangeOutputter changeDisplay = changesDisplay.change();
    PositionWithColumnsChange<ChangeAssert, ChangeColumnAssert> position = 
              (PositionWithColumnsChange) fieldPosition.get(changeDisplay);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(0);
    ChangeColumnOutputter changeColumnDisplay0 = changeDisplay.columnAmongTheModifiedOnes("ID");
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(1);
    ChangeColumnOutputter changeColumnDisplay1 = changeDisplay.columnAmongTheModifiedOnes("NAME");
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(2);
    ChangeColumnOutputter changeColumnDisplay2 = changeDisplay.columnAmongTheModifiedOnes("FIRSTNAME");
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(3);
    ChangeColumnOutputter changeColumnDisplay3 = changeDisplay.columnAmongTheModifiedOnes("BIRTH");
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(4);
    try {
      changeDisplay.columnAmongTheModifiedOnes("TEST");
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("Column <TEST> does not exist among the modified columns%n"
                                                      + "in <[ID, NAME, FIRSTNAME, BIRTH, ACTOR_IMDB]>%n"
                                                      + "with comparison IGNORE - Ignore the case"));
    }
    try {
      changeDisplay.columnAmongTheModifiedOnes(null);
      fail("An exception must be raised");
    } catch (NullPointerException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Column name must be not null");
    }

    ChangesOutputter changesDisplayBis = output(changes);
    ChangeOutputter changeDisplayBis = changesDisplayBis.change();
    PositionWithColumnsChange<ChangeAssert, ChangeColumnAssert> positionBis = 
              (PositionWithColumnsChange) fieldPosition.get(changeDisplayBis);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(0);
    ChangeColumnOutputter changeColumnDisplayBis0 = changeDisplayBis.columnAmongTheModifiedOnes("ID");
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(1);
    ChangeColumnOutputter changeColumnDisplayBis1 = changeColumnDisplayBis0.columnAmongTheModifiedOnes("NAME");
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(2);
    ChangeColumnOutputter changeColumnDisplayBis2 = changeColumnDisplayBis1.columnAmongTheModifiedOnes("FIRSTNAME");
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(3);
    ChangeColumnOutputter changeColumnDisplayBis3 = changeColumnDisplayBis2.columnAmongTheModifiedOnes("BIRTH");
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(4);
    try {
      changeColumnDisplayBis3.columnAmongTheModifiedOnes("TEST");
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("Column <TEST> does not exist among the modified columns%n"
                                                                    + "in <[ID, NAME, FIRSTNAME, BIRTH, ACTOR_IMDB]>%n"
                                                                    + "with comparison IGNORE - Ignore the case"));
    }
    try {
      changeColumnDisplayBis3.columnAmongTheModifiedOnes(null);
      fail("An exception must be raised");
    } catch (NullPointerException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Column name must be not null");
    }

    Assertions.assertThat(fieldColumnName.get(changeColumnDisplay0)).isEqualTo(fieldColumnName.get(changeColumnDisplayBis0)).isEqualTo(
            "ID");
    Assertions.assertThat(fieldColumnName.get(changeColumnDisplay1)).isEqualTo(fieldColumnName.get(changeColumnDisplayBis1)).isEqualTo(
            "NAME");
    Assertions.assertThat(fieldColumnName.get(changeColumnDisplay2)).isEqualTo(fieldColumnName.get(changeColumnDisplayBis2)).isEqualTo(
            "FIRSTNAME");
    Assertions.assertThat(fieldColumnName.get(changeColumnDisplay3)).isEqualTo(fieldColumnName.get(changeColumnDisplayBis3)).isEqualTo(
            "BIRTH");

    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnDisplay0)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnDisplay1)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnDisplay2)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnDisplay3)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnDisplayBis0)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnDisplayBis1)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnDisplayBis2)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnDisplayBis3)).getValue()).isNull();

    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnDisplay0)).getValue()).isEqualTo(
            ((Value) fieldValueAtEndPoint.get(changeColumnDisplayBis0)).getValue()).isEqualTo(
            new BigDecimal("4"));
    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnDisplay1)).getValue()).
            isEqualTo(((Value) fieldValueAtEndPoint.get(changeColumnDisplayBis1)).getValue()).
                      isEqualTo("Murray");
    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnDisplay2)).getValue()).
            isEqualTo(((Value) fieldValueAtEndPoint.get(changeColumnDisplayBis2)).getValue()).
                      isEqualTo("Bill");
    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnDisplay3)).getValue()).
            isEqualTo(((Value) fieldValueAtEndPoint.get(changeColumnDisplayBis3)).getValue()).
                      isEqualTo(
                              Date.valueOf("1950-09-21"));

    ChangeColumnOutputter changeColumnDisplay = output(changes).change(3).columnAmongTheModifiedOnes("firstname");
    try {
      changeColumnDisplay.columnAmongTheModifiedOnes("birth");
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("Column <birth> does not exist among the modified columns%n"
                                                                    + "in <[FIRSTNAME]>%n"
                                                                    + "with comparison IGNORE - Ignore the case"));
    }
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnDisplay)).getValue()).isEqualTo("Sigourney");
    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnDisplay)).getValue()).isEqualTo(
            "Susan Alexandra");
  }
}
