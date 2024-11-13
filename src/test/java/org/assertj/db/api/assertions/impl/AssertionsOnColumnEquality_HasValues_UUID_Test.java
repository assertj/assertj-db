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

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.db.api.TableAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Value;
import org.junit.Test;

/**
 * Tests on {@link AssertionsOnColumnEquality} class :
 * {@link AssertionsOnColumnEquality#hasValues(org.assertj.db.api.AbstractAssert, org.assertj.core.api.WritableAssertionInfo, java.util.List, java.util.UUID...)} method.
 *
 * @author Régis Pouiller
 */
public class AssertionsOnColumnEquality_HasValues_UUID_Test extends AbstractTest {

  /**
   * This method tests the {@code hasValues} assertion method.
   */
  @Test
  public void test_has_values() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    TableAssert tableAssert = new TableAssert(null);
    List<Value> list = new ArrayList<>(Arrays.asList(
      getValue(null, UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435")),
      getValue(null, UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435")),
      getValue(null, null)));
    TableAssert tableAssert2 = AssertionsOnColumnEquality.hasValues(tableAssert, info, list,
      UUID.fromString(
        "30B443AE-C0C9-4790-9BEC-CE1380808435"),
      UUID.fromString(
        "30B443AE-C0C9-4790-9BEC-CE1380808435"),
      null);
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
    try {
      List<Value> list = new ArrayList<>(Arrays.asList(
        getValue(null, UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435")),
        getValue(null, UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"))));
      AssertionsOnColumnEquality.hasValues(tableAssert, info, list, UUID.fromString(
        "30B443AE-C0C9-4790-9BEC-CE1380808435"), UUID.fromString(
        "0E2A1269-EFF0-4233-B87B-B53E8B6F164D"));
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting that the value at index 1:%n"
        + "  <30b443ae-c0c9-4790-9bec-ce1380808435>%n"
        + "to be equal to: %n"
        + "  <0e2a1269-eff0-4233-b87b-b53e8b6f164d>"));
    }
  }

  /**
   * This method should fail because one of the values is not a uuid.
   */
  @Test
  public void should_fail_because_one_value_is_not_a_uuid() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    TableAssert tableAssert = new TableAssert(null);
    List<Value> list = new ArrayList<>(Arrays.asList(getValue(null, false),
      getValue(null, UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"))));
    try {
      AssertionsOnColumnEquality.hasValues(tableAssert, info, list, UUID.fromString(
        "30B443AE-C0C9-4790-9BEC-CE1380808435"), UUID.fromString(
        "30B443AE-C0C9-4790-9BEC-CE1380808435"));
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting that the value at index 0:%n"
        + "  <false>%n"
        + "to be of type%n"
        + "  <[UUID, NOT_IDENTIFIED]>%n"
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
    List<Value> list = new ArrayList<>(Arrays.asList(getValue(null, UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435")),
      getValue(null, UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"))));
    try {
      AssertionsOnColumnEquality.hasValues(tableAssert, info, list, UUID.fromString(
          "30B443AE-C0C9-4790-9BEC-CE1380808435"), UUID.fromString(
          "30B443AE-C0C9-4790-9BEC-CE1380808435"),
        UUID.fromString(
          "30B443AE-C0C9-4790-9BEC-CE1380808435"));
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
