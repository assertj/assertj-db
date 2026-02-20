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

import static org.assertj.core.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.db.api.TableAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Value;
import org.assertj.db.type.ValueType;
import org.junit.Test;

/**
 * Tests on {@link AssertionsOnColumnType} class :
 * {@link AssertionsOnColumnType#isOfType(org.assertj.db.api.AbstractAssert, org.assertj.core.api.WritableAssertionInfo, java.util.List, org.assertj.db.type.ValueType, boolean)} method.
 *
 * @author Régis Pouiller
 */
public class AssertionsOnColumnType_IsOfType_Test extends AbstractTest {

  /**
   * This method tests the {@code isOfType} assertion method.
   */
  @Test
  public void test_is_of_type() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    TableAssert tableAssert = new TableAssert(null);
    List<Value> list = new ArrayList<>(Arrays.asList(getValue(null, "test"), getValue(null, "test")));
    TableAssert tableAssert2 = AssertionsOnColumnType.isOfType(tableAssert, info, list, ValueType.TEXT, false);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    list = new ArrayList<>(Arrays.asList(getValue(null, "test"), getValue(null, "test")));
    tableAssert2 = AssertionsOnColumnType.isOfType(tableAssert, info, list, ValueType.TEXT, true);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    list = new ArrayList<>(Arrays.asList(getValue(null, null), getValue(null, "test")));
    tableAssert2 = AssertionsOnColumnType.isOfType(tableAssert, info, list, ValueType.TEXT, true);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
  }

  /**
   * This method should fail because the value is not of type.
   */
  @Test
  public void should_fail_because_value_is_not_of_type() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    TableAssert tableAssert = new TableAssert(null);
    try {
      List<Value> list = new ArrayList<>(Arrays.asList(getValue(null, 8), getValue(null, "test")));
      AssertionsOnColumnType.isOfType(tableAssert, info, list, ValueType.TEXT, false);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting that the value at index 0:%n"
        + "  <8>%n"
        + "to be of type%n"
        + "  <TEXT>%n"
        + "but was of type%n"
        + "  <NUMBER>"));
    }
    try {
      List<Value> list = new ArrayList<>(Arrays.asList(getValue(null, null), getValue(null, "test")));
      AssertionsOnColumnType.isOfType(tableAssert, info, list, ValueType.TEXT, false);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting that the value at index 0:%n"
        + "  <null>%n"
        + "to be of type%n"
        + "  <TEXT>%n"
        + "but was of type%n"
        + "  <NOT_IDENTIFIED>"));
    }
    try {
      List<Value> list = new ArrayList<>(Arrays.asList(getValue(null, Locale.FRENCH), getValue(null, "test")));
      AssertionsOnColumnType.isOfType(tableAssert, info, list, ValueType.TEXT, false);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting that the value at index 0:%n"
        + "  <fr>%n"
        + "to be of type%n"
        + "  <TEXT>%n"
        + "but was of type%n"
        + "  <NOT_IDENTIFIED> (java.util.Locale)"));
    }
  }

  /**
   * This method should fail because the value is not of type (with lenience).
   */
  @Test
  public void should_fail_because_value_is_not_of_type_with_lenience() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    TableAssert tableAssert = new TableAssert(null);
    try {
      List<Value> list = new ArrayList<>(Arrays.asList(getValue(null, "test"), getValue(null, 8)));
      AssertionsOnColumnType.isOfType(tableAssert, info, list, ValueType.TEXT, false);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting that the value at index 1:%n"
        + "  <8>%n"
        + "to be of type%n"
        + "  <TEXT>%n"
        + "but was of type%n"
        + "  <NUMBER>"));
    }
  }

  /**
   * This method should fail because the value is a stringbuiler.
   */
  @Test
  public void should_fail_because_value_is_a_stringbuilder() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    TableAssert tableAssert = new TableAssert(null);
    try {
      List<Value> list = new ArrayList<>(Arrays.asList(getValue(null, new StringBuilder("test")), getValue(null, true)));
      AssertionsOnColumnType.isOfType(tableAssert, info, list, ValueType.TEXT, false);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting that the value at index 0:%n"
        + "  <test>%n"
        + "to be of type%n"
        + "  <TEXT>%n"
        + "but was of type%n"
        + "  <NOT_IDENTIFIED> (java.lang.StringBuilder)"));
    }
  }
}
