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

import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.db.api.TableAssert;
import org.assertj.db.common.AbstractTest;
import org.junit.Test;

/**
 * Tests on {@link AssertionsOnColumnOfChangeEquality} class :
 * {@link AssertionsOnColumnOfChangeEquality#hasValues(org.assertj.db.api.AbstractAssert, org.assertj.core.api.WritableAssertionInfo, org.assertj.db.type.Value, org.assertj.db.type.Value, java.util.UUID, java.util.UUID)} method.
 *
 * @author Régis Pouiller
 */
public class AssertionsOnColumnOfChangeEquality_HasValues_Two_UUID_Test extends AbstractTest {

  /**
   * This method tests the {@code hasValues} assertion method.
   */
  @Test
  public void test_has_values() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    TableAssert tableAssert = new TableAssert(null);
    TableAssert tableAssert2 = AssertionsOnColumnOfChangeEquality.hasValues(tableAssert, info,
      getValue(null, UUID.fromString(
        "30B443AE-C0C9-4790-9BEC-CE1380808435")),
      getValue(null, UUID.fromString(
        "0E2A1269-EFF0-4233-B87B-B53E8B6F164D")),
      UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"),
      UUID.fromString("0E2A1269-EFF0-4233-B87B-B53E8B6F164D"));
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
  }

  /**
   * This method should fail because the value at start point is different.
   */
  @Test
  public void should_fail_because_value_at_start_point_is_different() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    TableAssert tableAssert = new TableAssert(null);
    try {
      AssertionsOnColumnOfChangeEquality.hasValues(tableAssert, info,
        getValue(null, UUID.fromString("0E2A1269-EFF0-4233-B87B-B53E8B6F164D")),
        getValue(null, UUID.fromString(
          "0E2A1269-EFF0-4233-B87B-B53E8B6F164D")),
        UUID.fromString(
          "30B443AE-C0C9-4790-9BEC-CE1380808435"),
        UUID.fromString(
          "0E2A1269-EFF0-4233-B87B-B53E8B6F164D"));
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting that start point:%n"
        + "  <0e2a1269-eff0-4233-b87b-b53e8b6f164d>%n"
        + "to be equal to: %n"
        + "  <30b443ae-c0c9-4790-9bec-ce1380808435>"));
    }
  }

  /**
   * This method should fail because the value at end point is different.
   */
  @Test
  public void should_fail_because_value_at_end_point_is_different() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    TableAssert tableAssert = new TableAssert(null);
    try {
      AssertionsOnColumnOfChangeEquality.hasValues(tableAssert, info,
        getValue(null, UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435")),
        getValue(null, UUID.fromString(
          "30B443AE-C0C9-4790-9BEC-CE1380808435")),
        UUID.fromString(
          "30B443AE-C0C9-4790-9BEC-CE1380808435"),
        UUID.fromString(
          "0E2A1269-EFF0-4233-B87B-B53E8B6F164D"));
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting that end point:%n"
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
    try {
      AssertionsOnColumnOfChangeEquality.hasValues(tableAssert, info,
        getValue(null, "other"),
        getValue(null, UUID.fromString(
          "0E2A1269-EFF0-4233-B87B-B53E8B6F164D")),
        UUID.fromString(
          "30B443AE-C0C9-4790-9BEC-CE1380808435"),
        UUID.fromString(
          "0E2A1269-EFF0-4233-B87B-B53E8B6F164D"));
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting that the value at start point:%n"
        + "  <\"other\">%n"
        + "to be of type%n"
        + "  <[UUID, NOT_IDENTIFIED]>%n"
        + "but was of type%n"
        + "  <TEXT>"));
    }
  }
}
