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
package org.assertj.db.api.assertions.impl;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.db.api.TableAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Value;
import org.junit.Test;

/**
 * Tests on {@link org.assertj.db.api.assertions.impl.AssertionsOnColumnContent} class :
 * {@link org.assertj.db.api.assertions.impl.AssertionsOnColumnContent#containsValues(org.assertj.db.api.AbstractAssert, org.assertj.core.api.WritableAssertionInfo, java.util.List, String...)} method.
 *
 * @author Régis Pouiller
 */
public class AssertionsOnColumnContent_ContainsValues_String_Test extends AbstractTest {

  /**
   * This method tests the {@code containsValues} assertion method.
   */
  @Test
  public void test_contains_values() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    TableAssert tableAssert = new TableAssert(null);
    List<Value> list = new ArrayList<>(Arrays.asList(getValue(null, "test1"), getValue(null, "test2"), getValue(null,
      "test1"), getValue(
      null, null)));
    TableAssert tableAssert2 = AssertionsOnColumnContent.containsValues(tableAssert, info, list, "test1", "test2", "test1", null);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    tableAssert2 = AssertionsOnColumnContent.containsValues(tableAssert, info, list, "test2", "test1", "test1", null);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    tableAssert2 = AssertionsOnColumnContent.containsValues(tableAssert, info, list, null, "test1", "test1", "test2");
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
  }

  /**
   * This method should fail because the values are different.
   */
  @Test
  public void should_fail_because_values_are_different() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    TableAssert tableAssert = new TableAssert(null);
    List<Value> list = new ArrayList<>(Arrays.asList(getValue(null, "test1"), getValue(null, "test2"), getValue(null,
      "test1"), getValue(
      null, null)));
    try {
      AssertionsOnColumnContent.containsValues(tableAssert, info, list, "test1", null, "test1", null);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting:%n"
        + "  <[\"test1\", \"test2\", \"test1\", null]>%n"
        + "to contain: %n"
        + "  <[\"test1\", null, \"test1\", null]>%n"
        + " (parameter <null> at index 3 is not found)"));
    }
    list = new ArrayList<>(Arrays.asList(getValue(null, true), getValue(null, false), getValue(null, false), getValue(
      null, null)));
    try {
      AssertionsOnColumnContent.containsValues(tableAssert, info, list, "test1", "test2", "test1", "test1");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting that the value at index 0:%n"
        + "  <true>%n"
        + "to be of type%n"
        + "  <[TEXT, NUMBER, DATE, TIME, DATE_TIME, UUID, NOT_IDENTIFIED]>%n"
        + "but was of type%n"
        + "  <BOOLEAN>"));
    }
  }

  /**
   * This method should fail because one of the values is not a text.
   */
  @Test
  public void should_fail_because_one_value_is_not_a_text() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    TableAssert tableAssert = new TableAssert(null);
    List<Value> list = new ArrayList<>(Arrays.asList(getValue(null, "other"), getValue(null, false)));
    try {
      AssertionsOnColumnContent.containsValues(tableAssert, info, list, "test1", "test2");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting that the value at index 1:%n"
        + "  <false>%n"
        + "to be of type%n"
        + "  <[TEXT, NUMBER, DATE, TIME, DATE_TIME, UUID, NOT_IDENTIFIED]>%n"
        + "but was of type%n"
        + "  <BOOLEAN>"));
    }
  }

  /**
   * This method should fail because the number of values is different.
   */
  @Test
  public void should_fail_because_the_number_of_values_is_different() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    TableAssert tableAssert = new TableAssert(null);
    List<Value> list = new ArrayList<>(Arrays.asList(getValue(null, "test1"), getValue(null, "test2")));
    try {
      AssertionsOnColumnContent.containsValues(tableAssert, info, list, "test1", "test2", "test2");
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
