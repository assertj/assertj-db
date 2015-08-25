/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 * <p>
 * Copyright 2012-2015 the original author or authors.
 */
package org.assertj.db.api.assertions.impl;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.db.api.TableAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.*;
import org.junit.Test;

import java.sql.Date;
import java.util.Arrays;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on {@link AssertionsOnPrimaryKey} class :
 * {@link AssertionsOnPrimaryKey#hasPksNames(org.assertj.db.api.AbstractAssert, org.assertj.core.api.WritableAssertionInfo, org.assertj.db.type.Change, String...)} method.
 *
 * @author Régis Pouiller
 *
 */
public class AssertionsOnPrimaryKey_HasPksNames_Test extends AbstractTest {

    /**
     * This method tests the {@code hasPksNames} assertion method.
     */
    @Test
    public void test_has_pks_names() throws Exception {
        WritableAssertionInfo info = new WritableAssertionInfo();
        Table table = new Table();
        TableAssert tableAssert = assertThat(table);
        Row rowAtStartPoint = getRow(Arrays.asList("ID", "ID2"),
                                     Arrays.asList("ID", "NAME", "FIRSTNAME", "BIRTH"),
                                     Arrays.asList(1, "Weaver", "Sigourney", Date.valueOf("1949-10-08")));
        Row rowAtEndPoint = getRow(Arrays.asList("ID", "ID2"),
                                   Arrays.asList("ID", "NAME", "FIRSTNAME", "BIRTH"),
                                   Arrays.asList(1, "Weaverr", "Sigourneyy", Date.valueOf("1949-10-08")));
        Change change = getChange(DataType.TABLE, "test", ChangeType.CREATION, rowAtStartPoint, rowAtEndPoint);
        TableAssert tableAssert2 = AssertionsOnPrimaryKey.hasPksNames(tableAssert, info, change, "ID", "ID2");
        Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    }

    /**
     * This method should fail because the primary keys names are different.
     */
    @Test
    public void should_fail_because_pks_names_are_different() throws Exception {
        WritableAssertionInfo info = new WritableAssertionInfo();
        info.description("description");
        Table table = new Table();
        TableAssert tableAssert = assertThat(table);
        Row rowAtStartPoint = getRow(Arrays.asList("ID", "ID2"),
                                     Arrays.asList("ID", "NAME", "FIRSTNAME", "BIRTH"),
                                     Arrays.asList(1, "Weaver", "Sigourney", Date.valueOf("1949-10-08")));
        Row rowAtEndPoint = getRow(Arrays.asList("ID", "ID2"),
                                   Arrays.asList("ID", "NAME", "FIRSTNAME", "BIRTH"),
                                   Arrays.asList(1, "Weaverr", "Sigourneyy", Date.valueOf("1949-10-08")));
        Change change = getChange(DataType.TABLE, "test", ChangeType.CREATION, rowAtStartPoint, rowAtEndPoint);
        try {
            AssertionsOnPrimaryKey.hasPksNames(tableAssert, info, change, "ID1", "ID2");
            fail("An exception must be raised");
        } catch (AssertionError e) {
            Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                                          + "Expecting :%n"
                                                                          + "  [\"ID1\", \"ID2\"]%n"
                                                                          + "to be the name of the columns of the primary keys but was:%n"
                                                                          + "  [\"ID\", \"ID2\"]"));
        }
    }

    /**
     * This method should fail because the number of primary keys names are different.
     */
    @Test
    public void should_fail_because_number_of_pks_names_are_different() throws Exception {
        WritableAssertionInfo info = new WritableAssertionInfo();
        info.description("description");
        Table table = new Table();
        TableAssert tableAssert = assertThat(table);
        Row rowAtStartPoint = getRow(Arrays.asList("ID", "ID2"),
                                     Arrays.asList("ID", "NAME", "FIRSTNAME", "BIRTH"),
                                     Arrays.asList(1, "Weaver", "Sigourney", Date.valueOf("1949-10-08")));
        Row rowAtEndPoint = getRow(Arrays.asList("ID", "ID2"),
                                   Arrays.asList("ID", "NAME", "FIRSTNAME", "BIRTH"),
                                   Arrays.asList(1, "Weaverr", "Sigourneyy", Date.valueOf("1949-10-08")));
        Change change = getChange(DataType.TABLE, "test", ChangeType.CREATION, rowAtStartPoint, rowAtEndPoint);
        try {
            AssertionsOnPrimaryKey.hasPksNames(tableAssert, info, change, "ID", "ID2", "ID3");
            fail("An exception must be raised");
        } catch (AssertionError e) {
            Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                                          + "Expecting :%n"
                                                                          + "  [\"ID\", \"ID2\", \"ID3\"]%n"
                                                                          + "to be the name of the columns of the primary keys but was:%n"
                                                                          + "  [\"ID\", \"ID2\"]"));
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
        Row rowAtStartPoint = getRow(Arrays.asList("ID", "ID2"),
                                     Arrays.asList("ID", "NAME", "FIRSTNAME", "BIRTH"),
                                     Arrays.asList(1, "Weaver", "Sigourney", Date.valueOf("1949-10-08")));
        Row rowAtEndPoint = getRow(Arrays.asList("ID", "ID2"),
                                   Arrays.asList("ID", "NAME", "FIRSTNAME", "BIRTH"),
                                   Arrays.asList(1, "Weaverr", "Sigourneyy", Date.valueOf("1949-10-08")));
        Change change = getChange(DataType.TABLE, "test", ChangeType.CREATION, rowAtStartPoint, rowAtEndPoint);
        try {
            AssertionsOnPrimaryKey.hasPksNames(tableAssert, info, change, "NAME", null);
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
        Row rowAtStartPoint = getRow(Arrays.asList("ID", "ID2"),
                                     Arrays.asList("ID", "NAME", "FIRSTNAME", "BIRTH"),
                                     Arrays.asList(1, "Weaver", "Sigourney", Date.valueOf("1949-10-08")));
        Row rowAtEndPoint = getRow(Arrays.asList("ID", "ID2"),
                                   Arrays.asList("ID", "NAME", "FIRSTNAME", "BIRTH"),
                                   Arrays.asList(1, "Weaverr", "Sigourneyy", Date.valueOf("1949-10-08")));
        Change change = getChange(DataType.TABLE, "test", ChangeType.CREATION, rowAtStartPoint, rowAtEndPoint);
        try {
            AssertionsOnPrimaryKey.hasPksNames(tableAssert, info, change, (String[]) null);
            fail("An exception must be raised");
        } catch (NullPointerException e) {
            Assertions.assertThat(e.getMessage()).isEqualTo(String.format("Columns names must be not null"));
        }
    }
}
