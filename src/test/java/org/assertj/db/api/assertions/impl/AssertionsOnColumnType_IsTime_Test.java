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

import java.sql.Time;
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
 * Tests on {@link AssertionsOnColumnType} class :
 * {@link AssertionsOnColumnType#isTime(org.assertj.db.api.AbstractAssert, org.assertj.core.api.WritableAssertionInfo, java.util.List, boolean)} method.
 *
 * @author Régis Pouiller
 */
public class AssertionsOnColumnType_IsTime_Test extends AbstractTest {

  /**
   * This method tests the {@code isTime} assertion method.
   */
  @Test
  public void test_is_time() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    TableAssert tableAssert = new TableAssert(null);
    List<Value> list = new ArrayList<>(Arrays.asList(getValue(null, Time.valueOf("09:01:00")),
      getValue(null, Time.valueOf("09:01:00"))));
    TableAssert tableAssert2 = AssertionsOnColumnType.isTime(tableAssert, info, list, false);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    list = new ArrayList<>(Arrays.asList(getValue(null, Time.valueOf("09:01:00")),
      getValue(null, Time.valueOf("09:01:00"))));
    tableAssert2 = AssertionsOnColumnType.isTime(tableAssert, info, list, true);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    list = new ArrayList<>(Arrays.asList(getValue(null, null),
      getValue(null, Time.valueOf("09:01:00"))));
    tableAssert2 = AssertionsOnColumnType.isTime(tableAssert, info, list, true);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
  }

  /**
   * This method should fail because the value is not a time.
   */
  @Test
  public void should_fail_because_value_is_not_a_time() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    TableAssert tableAssert = new TableAssert(null);
    try {
      List<Value> list = new ArrayList<>(Arrays.asList(getValue(null, "test"), getValue(null, Time.valueOf("09:01:00"))));
      AssertionsOnColumnType.isTime(tableAssert, info, list, false);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting that the value at index 0:%n"
        + "  <\"test\">%n"
        + "to be of type%n"
        + "  <TIME>%n"
        + "but was of type%n"
        + "  <TEXT>"));
    }
  }

  /**
   * This method should fail because the value is not a time (with lenience).
   */
  @Test
  public void should_fail_because_value_is_not_a_time_with_lenience() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    TableAssert tableAssert = new TableAssert(null);
    try {
      List<Value> list = new ArrayList<>(Arrays.asList(getValue(null, Time.valueOf("09:01:00")), getValue(null, "test")));
      AssertionsOnColumnType.isTime(tableAssert, info, list, false);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting that the value at index 1:%n"
        + "  <\"test\">%n"
        + "to be of type%n"
        + "  <TIME>%n"
        + "but was of type%n"
        + "  <TEXT>"));
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
      AssertionsOnColumnType.isTime(tableAssert, info, list, false);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting that the value at index 0:%n"
        + "  <test>%n"
        + "to be of type%n"
        + "  <TIME>%n"
        + "but was of type%n"
        + "  <NOT_IDENTIFIED> (java.lang.StringBuilder)"));
    }
  }
}
