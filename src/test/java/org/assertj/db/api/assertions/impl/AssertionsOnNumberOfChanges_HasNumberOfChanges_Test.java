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
 * Tests on {@link AssertionsOnNumberOfChanges} class :
 * {@link AssertionsOnNumberOfChanges#hasNumberOfChanges(org.assertj.db.api.AbstractAssert, org.assertj.core.api.WritableAssertionInfo, org.assertj.db.type.Changes, int)} method.
 *
 * @author Régis Pouiller
 *
 */
public class AssertionsOnNumberOfChanges_HasNumberOfChanges_Test extends AbstractTest {

    /**
     * This method tests the {@code hasNumberOfChanges} assertion method.
     */
    @Test
    public void test_has_number_of_changes() throws Exception {
        WritableAssertionInfo info = new WritableAssertionInfo();
        Table table = new Table();
        TableAssert tableAssert = assertThat(table);
        Row rowAtStartPoint = getRow(Arrays.asList("ID"),
                                     Arrays.asList("ID", "NAME", "FIRSTNAME", "BIRTH"),
                                     Arrays.asList(1, "Weaver", "Sigourney", Date.valueOf("1949-10-08")));
        Row rowAtEndPoint = getRow(Arrays.asList("ID"),
                                   Arrays.asList("ID", "NAME", "FIRSTNAME", "BIRTH"),
                                   Arrays.asList(1, "Weaverr", "Sigourneyy", Date.valueOf("1949-10-08")));
        Change change = getChange(DataType.TABLE, "test", ChangeType.CREATION, rowAtStartPoint, rowAtEndPoint);
        Changes changes = getChanges(Arrays.asList(change, change));
        TableAssert tableAssert2 = AssertionsOnNumberOfChanges.hasNumberOfChanges(tableAssert, info, changes, 2);
        Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    }

    /**
     * This method should fail because the number of changes is different.
     */
    @Test
    public void should_fail_because_number_of_changes_is_different() throws Exception {
        WritableAssertionInfo info = new WritableAssertionInfo();
        info.description("description");
        Table table = new Table();
        TableAssert tableAssert = assertThat(table);
        Row rowAtStartPoint = getRow(Arrays.asList("ID"),
                                     Arrays.asList("ID", "NAME", "FIRSTNAME", "BIRTH"),
                                     Arrays.asList(1, "Weaver", "Sigourney", Date.valueOf("1949-10-08")));
        Row rowAtEndPoint = getRow(Arrays.asList("ID"),
                                   Arrays.asList("ID", "NAME", "FIRSTNAME", "BIRTH"),
                                   Arrays.asList(1, "Weaverr", "Sigourneyy", Date.valueOf("1949-10-08")));
        Change change = getChange(DataType.TABLE, "test", ChangeType.CREATION, rowAtStartPoint, rowAtEndPoint);
        Changes changes = getChanges(Arrays.asList(change, change));
        try {
            AssertionsOnNumberOfChanges.hasNumberOfChanges(tableAssert, info, changes, 3);
            fail("An exception must be raised");
        } catch (AssertionError e) {
            Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                                          + "Expecting size (number of changes) to be equal to :%n"
                                                                          + "   <3>%n"
                                                                          + "but was:%n"
                                                                          + "   <2>"));
        }
    }
}
