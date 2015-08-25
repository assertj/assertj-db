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

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on {@link AssertionsOnColumnEquality} class :
 * {@link AssertionsOnColumnEquality#hasValues(org.assertj.db.api.AbstractAssert, org.assertj.core.api.WritableAssertionInfo, java.util.List, String...)} method.
 *
 * @author Régis Pouiller
 *
 */
public class AssertionsOnColumnEquality_HasValues_String_Test {

    /**
     * This method tests the {@code hasValues} assertion method.
     */
    @Test
    public void test_has_values() {
        WritableAssertionInfo info = new WritableAssertionInfo();
        Table table = new Table();
        TableAssert tableAssert = assertThat(table);
        List<Object> list = new ArrayList<Object>(Arrays.asList("test1", "test2", null));
        TableAssert tableAssert2 = AssertionsOnColumnEquality
            .hasValues(tableAssert, info, list, "test1", "test2", null);
        Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
        list = new ArrayList<Object>(Arrays.asList(8, 9, null));
        tableAssert2 = AssertionsOnColumnEquality.hasValues(tableAssert, info, list, "8", "9", null);
        Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
        list = new ArrayList<Object>(Arrays.asList(Date.valueOf("2007-12-23"), Date.valueOf("2002-07-25"), null));
        tableAssert2 = AssertionsOnColumnEquality.hasValues(tableAssert, info, list, "2007-12-23", "2002-07-25", null);
        Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
        list = new ArrayList<Object>(Arrays.asList(Time.valueOf("09:01:00"), Time.valueOf("03:30:05"), null));
        tableAssert2 = AssertionsOnColumnEquality.hasValues(tableAssert, info, list, "09:01", "03:30:05", null);
        Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
        list = new ArrayList<Object>(
            Arrays.asList(Timestamp.valueOf("2007-12-23 09:01:00"), Timestamp.valueOf("2002-07-25 03:30:05"), null));
        tableAssert2 = AssertionsOnColumnEquality.hasValues(tableAssert, info, list, "2007-12-23T09:01",
                                                            "2002-07-25T03:30:05", null);
        Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    }

    /**
     * This method should fail because the values are different.
     */
    @Test
    public void should_fail_because_values_are_different() {
        WritableAssertionInfo info = new WritableAssertionInfo();
        info.description("description");
        Table table = new Table();
        TableAssert tableAssert = assertThat(table);
        try {
            List<Object> list = new ArrayList<Object>(Arrays.asList("test", "test2"));
            AssertionsOnColumnEquality.hasValues(tableAssert, info, list, "test1", "test2");
            fail("An exception must be raised");
        } catch (AssertionError e) {
            Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                                          + "Expecting that the value at index 0:%n"
                                                                          + "  <\"test\">%n"
                                                                          + "to be equal to: %n"
                                                                          + "  <\"test1\">"));
        }
        try {
            List<Object> list = new ArrayList<Object>(Arrays.asList(8, 9));
            AssertionsOnColumnEquality.hasValues(tableAssert, info, list, "7", "9");
            fail("An exception must be raised");
        } catch (AssertionError e) {
            Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                                          + "Expecting that the value at index 0:%n"
                                                                          + "  <\"8\">%n"
                                                                          + "to be equal to: %n"
                                                                          + "  <\"7\">"));
        }
        try {
            List<Object> list = new ArrayList<Object>(
                Arrays.asList(Date.valueOf("2007-12-23"), Date.valueOf("2002-07-25")));
            AssertionsOnColumnEquality.hasValues(tableAssert, info, list, "2007-12-23", "2002-07-26");
            fail("An exception must be raised");
        } catch (AssertionError e) {
            Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                                          + "Expecting that the value at index 1:%n"
                                                                          + "  <\"2002-07-25\">%n"
                                                                          + "to be equal to: %n"
                                                                          + "  <\"2002-07-26\">"));
        }
        try {
            List<Object> list = new ArrayList<Object>(
                Arrays.asList(Time.valueOf("09:01:00"), Time.valueOf("03:30:05")));
            AssertionsOnColumnEquality.hasValues(tableAssert, info, list, "09:01", "03:30:06");
            fail("An exception must be raised");
        } catch (AssertionError e) {
            Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                                          + "Expecting that the value at index 1:%n"
                                                                          + "  <\"03:30:05.000000000\">%n"
                                                                          + "to be equal to: %n"
                                                                          + "  <\"03:30:06\">"));
        }
        try {
            List<Object> list = new ArrayList<Object>(
                Arrays.asList(Timestamp.valueOf("2007-12-23 09:01:00"), Timestamp.valueOf("2002-07-25 03:30:05")));
            AssertionsOnColumnEquality.hasValues(tableAssert, info, list, "2007-12-23T09:01", "2002-07-25T03:30:06");
            fail("An exception must be raised");
        } catch (AssertionError e) {
            Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                                          + "Expecting that the value at index 1:%n"
                                                                          + "  <\"2002-07-25T03:30:05.000000000\">%n"
                                                                          + "to be equal to: %n"
                                                                          + "  <\"2002-07-25T03:30:06\">"));
        }
    }

    /**
     * This method should fail because one of the values is not a text.
     */
    @Test
    public void should_fail_because_one_value_is_not_a_text() {
        WritableAssertionInfo info = new WritableAssertionInfo();
        info.description("description");
        Table table = new Table();
        TableAssert tableAssert = assertThat(table);
        List<Object> list = new ArrayList<Object>(Arrays.asList(false, "test2"));
        try {
            AssertionsOnColumnEquality.hasValues(tableAssert, info, list, "test1", "test2");
            fail("An exception must be raised");
        } catch (AssertionError e) {
            Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                                          + "Expecting that the value at index 0:%n"
                                                                          + "  <false>%n"
                                                                          + "to be of type%n"
                                                                          + "  <[TEXT, NUMBER, DATE, TIME, DATE_TIME, NOT_IDENTIFIED]>%n"
                                                                          + "but was of type%n"
                                                                          + "  <BOOLEAN>"));
        }
    }

    /**
     * This method should fail because the number of values is different.
     */
    @Test
    public void should_fail_because_the_number_of_values_is_different() {
        WritableAssertionInfo info = new WritableAssertionInfo();
        info.description("description");
        Table table = new Table();
        TableAssert tableAssert = assertThat(table);
        List<Object> list = new ArrayList<Object>(Arrays.asList("test1", "test2"));
        try {
            AssertionsOnColumnEquality.hasValues(tableAssert, info, list, "test1", "test2", "test3");
            fail("An exception must be raised");
        } catch (AssertionError e) {
            Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                                          + "Expecting size (number of rows) to be equal to :%n"
                                                                          + "   <3>%n"
                                                                          + "but was:%n"
                                                                          + "   <2>"));
        }
    }
}
