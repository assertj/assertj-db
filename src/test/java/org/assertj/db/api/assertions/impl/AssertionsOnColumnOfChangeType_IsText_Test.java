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

import org.assertj.core.api.Assertions;
import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.db.api.TableAssert;
import org.assertj.db.common.AbstractTest;
import org.junit.Test;

/**
 * Tests on {@link org.assertj.db.api.assertions.impl.AssertionsOnColumnOfChangeType} class :
 * {@link org.assertj.db.api.assertions.impl.AssertionsOnColumnOfChangeType#isText(org.assertj.db.api.AbstractAssert, org.assertj.core.api.WritableAssertionInfo, org.assertj.db.type.Value, org.assertj.db.type.Value, boolean)} method.
 *
 * @author Régis Pouiller
 */
public class AssertionsOnColumnOfChangeType_IsText_Test extends AbstractTest {

  /**
   * This method tests the {@code isText} assertion method.
   */
  @Test
  public void test_is_text() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    TableAssert tableAssert = new TableAssert(null);
    TableAssert tableAssert2 = AssertionsOnColumnOfChangeType.isText(tableAssert, info, getValue(null, "test"), getValue(
      null, "test"), false);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    tableAssert2 = AssertionsOnColumnOfChangeType.isText(tableAssert, info, getValue(null, "test"), getValue(null,
      "test"), true);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    tableAssert2 = AssertionsOnColumnOfChangeType.isText(tableAssert, info, getValue(null, null), getValue(null, "test"), true);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
  }

  /**
   * This method should fail because the value at start point have different type.
   */
  @Test
  public void should_fail_because_value_at_start_point_have_different_type() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    TableAssert tableAssert = new TableAssert(null);
    try {
      AssertionsOnColumnOfChangeType.isText(tableAssert, info,
        getValue(null, true), getValue(null, "test"), false);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting that the value at start point:%n"
        + "  <true>%n"
        + "to be of type%n"
        + "  <TEXT>%n"
        + "but was of type%n"
        + "  <BOOLEAN>"));
    }
  }

  /**
   * This method should fail because the value at end point have different type.
   */
  @Test
  public void should_fail_because_value_at_end_point_have_different_type() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    TableAssert tableAssert = new TableAssert(null);
    try {
      AssertionsOnColumnOfChangeType.isText(tableAssert, info,
        getValue(null, "test"), getValue(null, false), false);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting that the value at end point:%n"
        + "  <false>%n"
        + "to be of type%n"
        + "  <TEXT>%n"
        + "but was of type%n"
        + "  <BOOLEAN>"));
    }
  }

  /**
   * This method should fail because the value at start point is a stringbuilder.
   */
  @Test
  public void should_fail_because_value_at_start_point_is_a_stringbuilder() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    TableAssert tableAssert = new TableAssert(null);
    try {
      AssertionsOnColumnOfChangeType.isTime(tableAssert, info,
        getValue(null, new StringBuilder("test")), getValue(null, "test"), false);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting that the value at start point:%n"
        + "  <test>%n"
        + "to be of type%n"
        + "  <TIME>%n"
        + "but was of type%n"
        + "  <NOT_IDENTIFIED> (java.lang.StringBuilder)"));
    }
  }

  /**
   * This method should fail because the value at end point is a stringbuilder.
   */
  @Test
  public void should_fail_because_value_at_end_point_is_a_stringbuilder() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    TableAssert tableAssert = new TableAssert(null);
    try {
      AssertionsOnColumnOfChangeType.isText(tableAssert, info,
        getValue(null, "test"), getValue(null, new StringBuilder("test")), false);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting that the value at end point:%n"
        + "  <test>%n"
        + "to be of type%n"
        + "  <TEXT>%n"
        + "but was of type%n"
        + "  <NOT_IDENTIFIED> (java.lang.StringBuilder)"));
    }
  }
}
