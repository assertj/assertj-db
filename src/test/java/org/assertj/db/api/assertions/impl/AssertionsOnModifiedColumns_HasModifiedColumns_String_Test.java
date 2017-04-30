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
package org.assertj.db.api.assertions.impl;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.db.api.TableAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.*;
import org.assertj.db.type.lettercase.CaseComparisons;
import org.assertj.db.type.lettercase.CaseConversions;
import org.assertj.db.type.lettercase.LetterCase;
import org.junit.Test;

import java.sql.Date;
import java.util.Arrays;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on {@link AssertionsOnModifiedColumns} class :
 * {@link AssertionsOnModifiedColumns#hasModifiedColumns(org.assertj.db.api.AbstractAssert, org.assertj.core.api.WritableAssertionInfo, org.assertj.db.type.Change, LetterCase, String...)} method.
 *
 * @author Régis Pouiller
 *
 */
public class AssertionsOnModifiedColumns_HasModifiedColumns_String_Test extends AbstractTest {

  /**
   * This method tests the {@code hasModifiedColumns} assertion method.
   */
  @Test
  public void test_has_modified_columns() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    Row rowAtStartPoint = getRow(null,
                                 Arrays.asList("ID", "NAME", "FIRSTNAME", "BIRTH"),
                                 Arrays.asList(getValue(null, 1), getValue(null, "Weaver"), getValue(null, "Sigourney"),
                                               getValue(null, Date.valueOf("1949-10-08"))));
    Row rowAtEndPoint = getRow(null,
                               Arrays.asList("ID", "NAME", "FIRSTNAME", "BIRTH"),
                               Arrays.asList(getValue(null, 1), getValue(null, "Weaverr"), getValue(null, "Sigourneyy"),
                                             getValue(null, Date.valueOf("1949-10-08"))));
    Change change = getChange(DataType.TABLE, "test", ChangeType.MODIFICATION, rowAtStartPoint, rowAtEndPoint);
    TableAssert tableAssert2 = AssertionsOnModifiedColumns.hasModifiedColumns(tableAssert, info, change, LetterCase.COLUMN_DEFAULT, "NAME", "FIRSTNAME");
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    tableAssert2 = AssertionsOnModifiedColumns.hasModifiedColumns(tableAssert, info, change, LetterCase.COLUMN_DEFAULT, "FIRSTNAME", "NAME");
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    tableAssert2 = AssertionsOnModifiedColumns.hasModifiedColumns(tableAssert, info, change, LetterCase.COLUMN_DEFAULT, "FirstNAME", "NaME");
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
  }

  /**
   * This method should fail because the columns names are different.
   */
  @Test
  public void should_fail_because_column_names_are_different() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    Row rowAtStartPoint = getRow(null,
                                 Arrays.asList("ID", "NAME", "FIRSTNAME", "BIRTH"),
                                 Arrays.asList(getValue(null, 1), getValue(null, "Weaver"), getValue(null, "Sigourney"),
                                               getValue(null, Date.valueOf("1949-10-08"))));
    Row rowAtEndPoint = getRow(null,
                               Arrays.asList("ID", "NAME", "FIRSTNAME", "BIRTH"),
                               Arrays.asList(getValue(null, 1), getValue(null, "Weaverr"), getValue(null, "Sigourneyy"),
                                             getValue(null, Date.valueOf("1949-10-08"))));
    Change change = getChange(DataType.TABLE, "test", ChangeType.MODIFICATION, rowAtStartPoint, rowAtEndPoint);
    try {
      AssertionsOnModifiedColumns.hasModifiedColumns(tableAssert, info, change, LetterCase.COLUMN_DEFAULT, "NAME", "BIRTH");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                                    + "Expecting :%n"
                                                                    + "  [\"NAME\", \"BIRTH\"]%n"
                                                                    + "as modified columns but was:%n"
                                                                    + "  [\"FIRSTNAME\", \"NAME\"]"));
    }
    try {
      AssertionsOnModifiedColumns.hasModifiedColumns(tableAssert, info, change, LetterCase.getLetterCase(
              CaseConversions.NO,
              CaseComparisons.STRICT), "NAME", "FirstNAME");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                                    + "Expecting :%n"
                                                                    + "  [\"NAME\", \"FirstNAME\"]%n"
                                                                    + "as modified columns but was:%n"
                                                                    + "  [\"FIRSTNAME\", \"NAME\"]"));
    }
    try {
      AssertionsOnModifiedColumns.hasModifiedColumns(tableAssert, info, change, LetterCase.getLetterCase(CaseConversions.NO,
                                                                                                         CaseComparisons.STRICT), "FirstNAME", "NAME");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                                    + "Expecting :%n"
                                                                    + "  [\"FirstNAME\", \"NAME\"]%n"
                                                                    + "as modified columns but was:%n"
                                                                    + "  [\"FIRSTNAME\", \"NAME\"]"));
    }
    try {
      AssertionsOnModifiedColumns.hasModifiedColumns(tableAssert, info, change, LetterCase.getLetterCase(CaseConversions.NO,
                                                                                                         CaseComparisons.STRICT), "FIRSTNAME", "NAME", "ZANE");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                                    + "Expecting :%n"
                                                                    + "  [\"FIRSTNAME\", \"NAME\", \"ZANE\"]%n"
                                                                    + "as modified columns but was:%n"
                                                                    + "  [\"FIRSTNAME\", \"NAME\"]"));
    }
    try {
      AssertionsOnModifiedColumns.hasModifiedColumns(tableAssert, info, change, LetterCase.getLetterCase(CaseConversions.NO,
                                                                                                         CaseComparisons.STRICT), "FIRSTNAME");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                                    + "Expecting :%n"
                                                                    + "  [\"FIRSTNAME\"]%n"
                                                                    + "as modified columns but was:%n"
                                                                    + "  [\"FIRSTNAME\", \"NAME\"]"));
    }
  }

  /**
   * This method should fail because the modified columns are different.
   */
  @Test
  public void should_fail_because_number_of_modified_columns_is_different() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    Row rowAtStartPoint = getRow(null,
                                 Arrays.asList("ID", "NAME", "FIRSTNAME", "BIRTH"),
                                 Arrays.asList(getValue(null, 1), getValue(null, "Weaver"), getValue(null, "Sigourney"),
                                               getValue(null, Date.valueOf("1949-10-08"))));
    Row rowAtEndPoint = getRow(null,
                               Arrays.asList("ID", "NAME", "FIRSTNAME", "BIRTH"),
                               Arrays.asList(getValue(null, 1), getValue(null, "Weaverr"), getValue(null, "Sigourneyy"),
                                             getValue(null, Date.valueOf("1949-10-08"))));
    Change change = getChange(DataType.TABLE, "test", ChangeType.MODIFICATION, rowAtStartPoint, rowAtEndPoint);
    try {
      AssertionsOnModifiedColumns.hasModifiedColumns(tableAssert, info, change, LetterCase.COLUMN_DEFAULT, "NAME", "FIRSTNAME", "BIRTH");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                      + "Expecting :%n"
                                                      + "  [\"NAME\", \"FIRSTNAME\", \"BIRTH\"]%n"
                                                      + "as modified columns but was:%n"
                                                      + "  [\"FIRSTNAME\", \"NAME\"]"));
    }
  }

  /**
   * This method should fail because the expected name must be not {@code null}.
   */
  @Test
  public void should_fail_because_expected_name_must_be_not_null() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    Row rowAtStartPoint = getRow(null,
                                 Arrays.asList("ID", "NAME", "FIRSTNAME", "BIRTH"),
                                 Arrays.asList(getValue(null, 1), getValue(null, "Weaver"), getValue(null, "Sigourney"),
                                               getValue(null, Date.valueOf("1949-10-08"))));
    Row rowAtEndPoint = getRow(null,
                               Arrays.asList("ID", "NAME", "FIRSTNAME", "BIRTH"),
                               Arrays.asList(getValue(null, 1), getValue(null, "Weaverr"), getValue(null, "Sigourneyy"),
                                             getValue(null, Date.valueOf("1949-10-08"))));
    Change change = getChange(DataType.TABLE, "test", ChangeType.MODIFICATION, rowAtStartPoint, rowAtEndPoint);
    try {
      AssertionsOnModifiedColumns.hasModifiedColumns(tableAssert, info, change, LetterCase.COLUMN_DEFAULT, "NAME", null);
      fail("An exception must be raised");
    } catch (NullPointerException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("Column name must be not null"));
    }
  }

  /**
   * This method should fail because the expected names must be not {@code null}.
   */
  @Test
  public void should_fail_because_expected_names_must_be_not_null() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    Row rowAtStartPoint = getRow(null,
                                 Arrays.asList("ID", "NAME", "FIRSTNAME", "BIRTH"),
                                 Arrays.asList(getValue(null, 1), getValue(null, "Weaver"), getValue(null, "Sigourney"),
                                               getValue(null, Date.valueOf("1949-10-08"))));
    Row rowAtEndPoint = getRow(null,
                               Arrays.asList("ID", "NAME", "FIRSTNAME", "BIRTH"),
                               Arrays.asList(getValue(null, 1), getValue(null, "Weaverr"), getValue(null, "Sigourneyy"),
                                             getValue(null, Date.valueOf("1949-10-08"))));
    Change change = getChange(DataType.TABLE, "test", ChangeType.MODIFICATION, rowAtStartPoint, rowAtEndPoint);
    try {
      AssertionsOnModifiedColumns.hasModifiedColumns(tableAssert, info, change, LetterCase.COLUMN_DEFAULT, (String[]) null);
      fail("An exception must be raised");
    } catch (NullPointerException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("Columns names must be not null"));
    }
  }
}
