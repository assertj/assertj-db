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
 * Copyright 2015-2021 the original author or authors.
 */
package org.assertj.db.api.assertions.impl;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.db.api.TableAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Table;
import org.assertj.db.type.Value;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on {@link AssertionsOnColumnType} class :
 * {@link AssertionsOnColumnType#isText(org.assertj.db.api.AbstractAssert, org.assertj.core.api.WritableAssertionInfo, java.util.List, boolean)} method.
 *
 * @author Régis Pouiller
 *
 */
public class AssertionsOnColumnType_IsText_Test extends AbstractTest {

  /**
   * This method tests the {@code isText} assertion method.
   */
  @Test
  public void test_is_text() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    List<Value> list = new ArrayList<>(Arrays.asList(getValue(null, "test"), getValue(null, "test")));
    TableAssert tableAssert2 = AssertionsOnColumnType.isText(tableAssert, info, list, false);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    list = new ArrayList<>(Arrays.asList(getValue(null, "test"), getValue(null, "test")));
    tableAssert2 = AssertionsOnColumnType.isText(tableAssert, info, list, true);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    list = new ArrayList<>(Arrays.asList(getValue(null, null), getValue(null, "test")));
    tableAssert2 = AssertionsOnColumnType.isText(tableAssert, info, list, true);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
  }

  /**
   * This method should fail because the value is not a text.
   */
  @Test
  public void should_fail_because_value_is_not_a_text() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    try {
      List<Value> list = new ArrayList<>(Arrays.asList(getValue(null, true), getValue(null, "test")));
      AssertionsOnColumnType.isText(tableAssert, info, list, false);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                      + "Expecting that the value at index 0:%n"
                                                      + "  <true>%n"
                                                      + "to be of type%n"
                                                      + "  <TEXT>%n"
                                                      + "but was of type%n"
                                                      + "  <BOOLEAN>"));
    }
  }

  /**
   * This method should fail because the value is not a text (with lenience).
   */
  @Test
  public void should_fail_because_value_is_not_a_text_with_lenience() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    try {
      List<Value> list = new ArrayList<>(Arrays.asList(getValue(null, "test"), getValue(null, false)));
      AssertionsOnColumnType.isText(tableAssert, info, list, false);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                      + "Expecting that the value at index 1:%n"
                                                      + "  <false>%n"
                                                      + "to be of type%n"
                                                      + "  <TEXT>%n"
                                                      + "but was of type%n"
                                                      + "  <BOOLEAN>"));
    }
  }

  /**
   * This method should fail because the value is a stringbuiler.
   */
  @Test
  public void should_fail_because_value_is_a_stringbuilder() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    try {
      List<Value> list = new ArrayList<>(Arrays.asList(getValue(null, new StringBuilder("test")), getValue(null, true)));
      AssertionsOnColumnType.isText(tableAssert, info, list, false);
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
