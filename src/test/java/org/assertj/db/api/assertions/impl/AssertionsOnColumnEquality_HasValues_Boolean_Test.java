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
 * Copyright 2015-2024 the original author or authors.
 */
package org.assertj.db.api.assertions.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.db.api.TableAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Table;
import org.assertj.db.type.Value;
import org.junit.Test;

/**
 * Tests on {@link AssertionsOnColumnEquality} class :
 * {@link AssertionsOnColumnEquality#hasValues(org.assertj.db.api.AbstractAssert, org.assertj.core.api.WritableAssertionInfo, java.util.List, Boolean...)} method.
 *
 * @author Régis Pouiller
 */
public class AssertionsOnColumnEquality_HasValues_Boolean_Test extends AbstractTest {

  /**
   * This method tests the {@code hasValues} assertion method.
   */
  @Test
  public void test_has_values() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    List<Value> list = new ArrayList<>(Arrays.asList(getValue(null, true), getValue(null, false), getValue(null, null)));
    TableAssert tableAssert2 = AssertionsOnColumnEquality.hasValues(tableAssert, info, list, Boolean.TRUE,
      Boolean.FALSE, null);
    assertThat(tableAssert2).isSameAs(tableAssert);
  }

  /**
   * This method should fail because the values are different.
   */
  @Test
  public void should_fail_because_values_are_different() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    List<Value> list = new ArrayList<>(Arrays.asList(getValue(null, false), getValue(null, false)));
    try {
      AssertionsOnColumnEquality.hasValues(tableAssert, info, list, Boolean.TRUE, Boolean.FALSE);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting that the value at index 0:%n"
        + "  <false>%n"
        + "to be equal to: %n"
        + "  <true>"));
    }
  }

  /**
   * This method should fail because one of the values is not a boolean.
   */
  @Test
  public void should_fail_because_one_value_is_not_a_boolean() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    List<Value> list = new ArrayList<>(Arrays.asList(getValue(null, "other"), getValue(null, false)));
    try {
      AssertionsOnColumnEquality.hasValues(tableAssert, info, list, Boolean.TRUE, Boolean.FALSE);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting that the value at index 0:%n"
        + "  <\"other\">%n"
        + "to be of type%n"
        + "  <[BOOLEAN, NOT_IDENTIFIED]>%n"
        + "but was of type%n"
        + "  <TEXT>"));
    }
  }

  /**
   * This method should fail because the number of values is different.
   */
  @Test
  public void should_fail_because_the_number_of_values_is_different() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    List<Value> list = new ArrayList<>(Arrays.asList(getValue(null, true), getValue(null, false)));
    try {
      AssertionsOnColumnEquality.hasValues(tableAssert, info, list, Boolean.TRUE, Boolean.FALSE, Boolean.FALSE);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting size (number of rows) to be equal to :%n"
        + "   <3>%n"
        + "but was:%n"
        + "   <2>"));
    }
  }
}
