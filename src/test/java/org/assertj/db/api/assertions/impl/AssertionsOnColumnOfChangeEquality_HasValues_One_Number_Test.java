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
 * Tests on {@link org.assertj.db.api.assertions.impl.AssertionsOnColumnOfChangeEquality} class :
 * {@link org.assertj.db.api.assertions.impl.AssertionsOnColumnOfChangeEquality#hasValues(org.assertj.db.api.AbstractAssert, org.assertj.core.api.WritableAssertionInfo, Object, Object, Number)} method.
 *
 * @author Régis Pouiller
 *
 */
public class AssertionsOnColumnOfChangeEquality_HasValues_One_Number_Test {

    /**
     * This method tests the {@code hasValues} assertion method.
     */
    @Test
    public void test_has_values() {
        WritableAssertionInfo info = new WritableAssertionInfo();
        Table table = new Table();
        TableAssert tableAssert = assertThat(table);
        TableAssert tableAssert2 = AssertionsOnColumnOfChangeEquality.hasValues(tableAssert, info, 1, 1,
                                                                                1);
        Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    }

    /**
     * This method should fail because the value at start point is different.
     */
    @Test
    public void should_fail_because_value_at_start_point_is_different() {
        WritableAssertionInfo info = new WritableAssertionInfo();
        info.description("description");
        Table table = new Table();
        TableAssert tableAssert = assertThat(table);
        try {
            AssertionsOnColumnOfChangeEquality.hasValues(tableAssert, info, 0, 1, 1);
            fail("An exception must be raised");
        } catch (AssertionError e) {
            Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                                          + "Expecting that start point:%n"
                                                                          + "  <0>%n"
                                                                          + "to be equal to: %n"
                                                                          + "  <1>"));
        }
    }

    /**
     * This method should fail because the value at end point is different.
     */
    @Test
    public void should_fail_because_value_at_end_point_is_different() {
        WritableAssertionInfo info = new WritableAssertionInfo();
        info.description("description");
        Table table = new Table();
        TableAssert tableAssert = assertThat(table);
        try {
            AssertionsOnColumnOfChangeEquality.hasValues(tableAssert, info, 1, 2, 1);
            fail("An exception must be raised");
        } catch (AssertionError e) {
            Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                                          + "Expecting that end point:%n"
                                                                          + "  <2>%n"
                                                                          + "to be equal to: %n"
                                                                          + "  <1>"));
        }
    }

    /**
     * This method should fail because one of the values is not a number.
     */
    @Test
    public void should_fail_because_one_value_is_not_a_number() {
        WritableAssertionInfo info = new WritableAssertionInfo();
        info.description("description");
        Table table = new Table();
        TableAssert tableAssert = assertThat(table);
        try {
            AssertionsOnColumnOfChangeEquality.hasValues(tableAssert, info, "other", 1, 1);
            fail("An exception must be raised");
        } catch (AssertionError e) {
            Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                                          + "Expecting that the value at start point:%n"
                                                                          + "  <\"other\">%n"
                                                                          + "to be of type%n"
                                                                          + "  <[NUMBER, NOT_IDENTIFIED]>%n"
                                                                          + "but was of type%n"
                                                                          + "  <TEXT>"));
        }
    }
}
