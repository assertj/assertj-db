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
package org.assertj.db.api.navigation;

import org.assertj.core.api.Assertions;
import org.assertj.db.api.ChangeAssert;
import org.assertj.db.api.ChangeColumnAssert;
import org.assertj.db.api.ChangesAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.assertj.db.exception.AssertJDBException;
import org.assertj.db.type.Changes;
import org.junit.Test;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on {@link ToColumnFromChange} class :
 * {@link org.assertj.db.api.navigation.ToColumnFromChange#columnAmongTheModifiedOnes()} method.
 *
 * @author Régis Pouiller
 *
 */
public class ToColumnFromChange_ColumnAmongTheModifiedOnes_Test extends AbstractTest {

  /**
   * This method tests the {@code columnAmongTheModifiedOnes} navigation method.
   */
  @Test
  @NeedReload
  public void test_column_among_the_modified_ones() throws Exception {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    Field fieldIndex = ChangeAssert.class.getDeclaredField("indexNextColumn");
    fieldIndex.setAccessible(true);
    Field fieldColumnName = ChangeColumnAssert.class.getDeclaredField("columnName");
    fieldColumnName.setAccessible(true);
    Field fieldValueAtStartPoint = ChangeColumnAssert.class.getDeclaredField("valueAtStartPoint");
    fieldValueAtStartPoint.setAccessible(true);
    Field fieldValueAtEndPoint = ChangeColumnAssert.class.getDeclaredField("valueAtEndPoint");
    fieldValueAtEndPoint.setAccessible(true);

    ChangesAssert changesAssert = assertThat(changes);
    ChangeAssert changeAssert = changesAssert.change();
    Assertions.assertThat(fieldIndex.get(changeAssert)).isEqualTo(0);
    ChangeColumnAssert changeColumnAssert0 = changeAssert.columnAmongTheModifiedOnes();
    Assertions.assertThat(fieldIndex.get(changeAssert)).isEqualTo(1);
    ChangeColumnAssert changeColumnAssert1 = changeAssert.columnAmongTheModifiedOnes();
    Assertions.assertThat(fieldIndex.get(changeAssert)).isEqualTo(2);
    ChangeColumnAssert changeColumnAssert2 = changeAssert.columnAmongTheModifiedOnes();
    Assertions.assertThat(fieldIndex.get(changeAssert)).isEqualTo(3);
    ChangeColumnAssert changeColumnAssert3 = changeAssert.columnAmongTheModifiedOnes();
    Assertions.assertThat(fieldIndex.get(changeAssert)).isEqualTo(4);
    ChangeColumnAssert changeColumnAssert4 = changeAssert.columnAmongTheModifiedOnes();
    Assertions.assertThat(fieldIndex.get(changeAssert)).isEqualTo(5);
    try {
      changeAssert.columnAmongTheModifiedOnes();
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("No more modified columns");
    }

    ChangesAssert changesAssertBis = assertThat(changes);
    ChangeAssert changeAssertBis = changesAssertBis.change();
    Assertions.assertThat(fieldIndex.get(changeAssertBis)).isEqualTo(0);
    ChangeColumnAssert changeColumnAssertBis0 = changeAssertBis.columnAmongTheModifiedOnes();
    Assertions.assertThat(fieldIndex.get(changeAssertBis)).isEqualTo(1);
    ChangeColumnAssert changeColumnAssertBis1 = changeColumnAssertBis0.columnAmongTheModifiedOnes();
    Assertions.assertThat(fieldIndex.get(changeAssertBis)).isEqualTo(2);
    ChangeColumnAssert changeColumnAssertBis2 = changeColumnAssertBis1.columnAmongTheModifiedOnes();
    Assertions.assertThat(fieldIndex.get(changeAssertBis)).isEqualTo(3);
    ChangeColumnAssert changeColumnAssertBis3 = changeColumnAssertBis2.columnAmongTheModifiedOnes();
    Assertions.assertThat(fieldIndex.get(changeAssertBis)).isEqualTo(4);
    ChangeColumnAssert changeColumnAssertBis4 = changeColumnAssertBis3.columnAmongTheModifiedOnes();
    Assertions.assertThat(fieldIndex.get(changeAssertBis)).isEqualTo(5);
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

    Assertions.assertThat(fieldValueAtStartPoint.get(changeColumnAssert0)).isNull();
    Assertions.assertThat(fieldValueAtStartPoint.get(changeColumnAssert1)).isNull();
    Assertions.assertThat(fieldValueAtStartPoint.get(changeColumnAssert2)).isNull();
    Assertions.assertThat(fieldValueAtStartPoint.get(changeColumnAssert3)).isNull();
    Assertions.assertThat(fieldValueAtStartPoint.get(changeColumnAssert4)).isNull();
    Assertions.assertThat(fieldValueAtStartPoint.get(changeColumnAssertBis0)).isNull();
    Assertions.assertThat(fieldValueAtStartPoint.get(changeColumnAssertBis1)).isNull();
    Assertions.assertThat(fieldValueAtStartPoint.get(changeColumnAssertBis2)).isNull();
    Assertions.assertThat(fieldValueAtStartPoint.get(changeColumnAssertBis3)).isNull();
    Assertions.assertThat(fieldValueAtStartPoint.get(changeColumnAssertBis4)).isNull();

    Assertions.assertThat(fieldValueAtEndPoint.get(changeColumnAssert0)).isEqualTo(fieldValueAtEndPoint.get(changeColumnAssertBis0)).isEqualTo(new BigDecimal("4"));
    Assertions.assertThat(fieldValueAtEndPoint.get(changeColumnAssert1)).isEqualTo(fieldValueAtEndPoint.get(changeColumnAssertBis1)).isEqualTo("Murray");
    Assertions.assertThat(fieldValueAtEndPoint.get(changeColumnAssert2)).isEqualTo(fieldValueAtEndPoint.get(changeColumnAssertBis2)).isEqualTo("Bill");
    Assertions.assertThat(fieldValueAtEndPoint.get(changeColumnAssert3)).isEqualTo(fieldValueAtEndPoint.get(changeColumnAssertBis3)).isEqualTo(
            Date.valueOf("1950-09-21"));
    Assertions.assertThat(fieldValueAtEndPoint.get(changeColumnAssert4)).isEqualTo(fieldValueAtEndPoint.get(changeColumnAssertBis4)).isEqualTo(
            UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"));

    ChangeColumnAssert changeColumnAssert = assertThat(changes).change(3).columnAmongTheModifiedOnes();
    try {
      changeColumnAssert.columnAmongTheModifiedOnes();
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("No more modified columns");
    }
    Assertions.assertThat(fieldValueAtStartPoint.get(changeColumnAssert)).isEqualTo("Sigourney");
    Assertions.assertThat(fieldValueAtEndPoint.get(changeColumnAssert)).isEqualTo("Susan Alexandra");
  }
}
