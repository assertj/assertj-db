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
import org.assertj.db.type.Table;
import org.junit.Test;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on {@link AssertionsOnModifiedColumn} class :
 * {@link AssertionsOnModifiedColumn#isModified(org.assertj.db.api.AbstractAssert, org.assertj.core.api.WritableAssertionInfo, Object, Object)} method.
 *
 * @author Régis Pouiller
 *
 */
public class AssertionsOnModifiedColumn_IsModified_Test {

    /**
     * This method tests the {@code isModified} assertion method.
     */
    @Test
    public void test_is_modified() {
        WritableAssertionInfo info = new WritableAssertionInfo();
        Table table = new Table();
        TableAssert tableAssert = assertThat(table);
        TableAssert tableAssert2 = AssertionsOnModifiedColumn.isModified(tableAssert, info, null, "test");
        Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
        tableAssert2 = AssertionsOnModifiedColumn.isModified(tableAssert, info, "test", null);
        Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
        tableAssert2 = AssertionsOnModifiedColumn.isModified(tableAssert, info, "test", "test1");
        Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    }

    /**
     * This method should fail because the column is not modified.
     */
    @Test
    public void should_fail_because_column_is_not_modified() {
        WritableAssertionInfo info = new WritableAssertionInfo();
        info.description("description");
        Table table = new Table();
        TableAssert tableAssert = assertThat(table);
        try {
            AssertionsOnModifiedColumn.isModified(tableAssert, info, null, null);
            fail("An exception must be raised");
        } catch (AssertionError e) {
            Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                                          + "Expecting :%n"
                                                                          + "  <null>%n"
                                                                          + "is modified but is still:%n"
                                                                          + "  <null>"));
        }
        try {
            AssertionsOnModifiedColumn.isModified(tableAssert, info, "test", "test");
            fail("An exception must be raised");
        } catch (AssertionError e) {
            Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                                          + "Expecting :%n"
                                                                          + "  <\"test\">%n"
                                                                          + "is modified but is still:%n"
                                                                          + "  <\"test\">"));
        }
    }
}
