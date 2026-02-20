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

import org.assertj.core.api.Assertions;
import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.db.api.TableAssert;
import org.assertj.db.common.AbstractTest;
import org.junit.Test;

/**
 * Tests on {@link  AssertionsOnValueInequality} class :
 * {@link AssertionsOnValueInequality#isNotEqualTo(org.assertj.db.api.AbstractAssert, org.assertj.core.api.WritableAssertionInfo, org.assertj.db.type.Value, Boolean)} method.
 *
 * @author Régis Pouiller
 */
public class AssertionsOnValueInequality_IsNotEqualTo_Boolean_Test extends AbstractTest {

  /**
   * This method tests the {@code isNotEqualTo} assertion method.
   */
  @Test
  public void test_is_not_equal_to() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    TableAssert tableAssert = new TableAssert(null);
    TableAssert tableAssert2 = AssertionsOnValueInequality.isNotEqualTo(tableAssert, info, getValue(null, true), false);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    tableAssert2 = AssertionsOnValueInequality.isNotEqualTo(tableAssert, info, getValue(null, true), (Boolean) null);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
  }

  /**
   * This method should fail because the value is equal to.
   */
  @Test
  public void should_fail_because_value_is_equal_to() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    TableAssert tableAssert = new TableAssert(null);
    try {
      AssertionsOnValueInequality.isNotEqualTo(tableAssert, info, getValue(null, true), true);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting:%n"
        + "  <true>%n"
        + "not to be equal to: %n"
        + "  <true>"));
    }
    try {
      AssertionsOnValueInequality.isNotEqualTo(tableAssert, info, getValue(null, null), (Boolean) null);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting:%n"
        + "  <null>%n"
        + "not to be equal to: %n"
        + "  <null>"));
    }
  }

  /**
   * This method should fail because the value is not a boolean.
   */
  @Test
  public void should_fail_because_value_is_not_a_boolean() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    TableAssert tableAssert = new TableAssert(null);
    try {
      AssertionsOnValueInequality.isNotEqualTo(tableAssert, info, getValue(null, 8), true);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting:%n"
        + "  <8>%n"
        + "to be of type%n"
        + "  <BOOLEAN>%n"
        + "but was of type%n"
        + "  <NUMBER>"));
    }
  }
}
