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
 * Tests on {@link AssertionsOnValueEquality} class :
 * {@link AssertionsOnValueEquality#isEqualTo(org.assertj.db.api.AbstractAssert, org.assertj.core.api.WritableAssertionInfo, org.assertj.db.type.Value, Character)} method.
 *
 * @author Régis Pouiller
 */
public class AssertionsOnValueEquality_IsEqualTo_Character_Test extends AbstractTest {

  /**
   * This method tests the {@code isEqualTo} assertion method.
   */
  @Test
  public void test_is_equal_to() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    TableAssert tableAssert = new TableAssert(null);
    TableAssert tableAssert2 = AssertionsOnValueEquality.isEqualTo(tableAssert, info, getValue(null, 'T'), 'T');
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    tableAssert2 = AssertionsOnValueEquality.isEqualTo(tableAssert, info, getValue(null, "T"), 'T');
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    tableAssert2 = AssertionsOnValueEquality.isEqualTo(tableAssert, info, getValue(null, null), (Character) null);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
  }

  /**
   * This method should fail because the value is not equal to.
   */
  @Test
  public void should_fail_because_value_is_not_equal_to() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    TableAssert tableAssert = new TableAssert(null);
    try {
      AssertionsOnValueEquality.isEqualTo(tableAssert, info, getValue(null, 't'), 'T');
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting:%n"
        + "  <'t'>%n"
        + "to be equal to: %n"
        + "  <'T'>"));
    }
    try {
      AssertionsOnValueEquality.isEqualTo(tableAssert, info, getValue(null, "t"), 'T');
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting:%n"
        + "  <\"t\">%n"
        + "to be equal to: %n"
        + "  <'T'>"));
    }
    try {
      AssertionsOnValueEquality.isEqualTo(tableAssert, info, getValue(null, 'T'), (Character) null);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting:%n"
        + "  <'T'>%n"
        + "to be equal to: %n"
        + "  <null>"));
    }
  }

  /**
   * This method should fail because the value is not a text.
   */
  @Test
  public void should_fail_because_value_is_not_a_text() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    TableAssert tableAssert = new TableAssert(null);
    try {
      AssertionsOnValueEquality.isEqualTo(tableAssert, info, getValue(null, 8), 'T');
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
  }
}
