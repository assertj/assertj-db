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

import java.util.Locale;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.db.api.TableAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.ValueType;
import org.junit.Test;

/**
 * Tests on {@link AssertionsOnValueType} class :
 * {@link AssertionsOnValueType#isOfType(org.assertj.db.api.AbstractAssert, org.assertj.core.api.WritableAssertionInfo, org.assertj.db.type.Value, org.assertj.db.type.ValueType)} method.
 *
 * @author Régis Pouiller
 */
public class AssertionsOnValueType_IsOfType_Test extends AbstractTest {

  /**
   * This method tests the {@code isOfType} assertion method.
   */
  @Test
  public void test_is_of_type() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    TableAssert tableAssert = new TableAssert(null);
    TableAssert tableAssert2 = AssertionsOnValueType.isOfType(tableAssert, info, getValue(null, "test"), ValueType.TEXT);
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
      AssertionsOnValueType.isOfType(tableAssert, info, getValue(null, 8), ValueType.TEXT);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting:%n"
        + "  <8>%n"
        + "to be of type%n"
        + "  <TEXT>%n"
        + "but was of type%n"
        + "  <NUMBER>"));
    }
    try {
      AssertionsOnValueType.isOfType(tableAssert, info, getValue(null, null), ValueType.TEXT);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting:%n"
        + "  <null>%n"
        + "to be of type%n"
        + "  <TEXT>%n"
        + "but was of type%n"
        + "  <NOT_IDENTIFIED>"));
    }
    try {
      AssertionsOnValueType.isOfType(tableAssert, info, getValue(null, Locale.FRENCH), ValueType.TEXT);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting:%n"
        + "  <fr>%n"
        + "to be of type%n"
        + "  <TEXT>%n"
        + "but was of type%n"
        + "  <NOT_IDENTIFIED> (java.util.Locale)"));
    }
  }

  /**
   * This method should fail because the value is a stringbuilder.
   */
  @Test
  public void should_fail_because_value_is_a_stringbuilder() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    TableAssert tableAssert = new TableAssert(null);
    try {
      AssertionsOnValueType.isOfType(tableAssert, info, getValue(null, new StringBuilder("text")), ValueType.TEXT);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting:%n"
        + "  <text>%n"
        + "to be of type%n"
        + "  <TEXT>%n"
        + "but was of type%n"
        + "  <NOT_IDENTIFIED> (java.lang.StringBuilder)"));
    }
  }
}
