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

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.db.api.TableAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Tests on {@link  AssertionsOnValueEquality} class :
 * {@link  AssertionsOnValueEquality#isZero(org.assertj.db.api.AbstractAssert, org.assertj.core.api.WritableAssertionInfo, org.assertj.db.type.Value)} method.
 *
 * @author Régis Pouiller
 */
public class AssertionsOnValueEquality_IsZero_Test extends AbstractTest {

  /**
   * This method tests the {@code isZero} assertion method.
   */
  @Test
  public void test_is_zero() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    TableAssert tableAssert2 = AssertionsOnValueEquality.isZero(tableAssert, info, getValue(null, 0));
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
  }

  /**
   * This method should fail because the value is not zero.
   */
  @Test
  public void should_fail_because_value_is_not_zero() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    try {
      AssertionsOnValueEquality.isZero(tableAssert, info, getValue(null, 1));
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting:%n"
        + "  <1>%n"
        + "to be equal to: %n"
        + "  <0>"));
    }
  }

  /**
   * This method should fail because the value is not a number.
   */
  @Test
  public void should_fail_because_value_is_not_a_number() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    try {
      AssertionsOnValueEquality.isZero(tableAssert, info, getValue(null, false));
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting:%n"
        + "  <false>%n"
        + "to be of type%n"
        + "  <NUMBER>%n"
        + "but was of type%n"
        + "  <BOOLEAN>"));
    }
  }
}
