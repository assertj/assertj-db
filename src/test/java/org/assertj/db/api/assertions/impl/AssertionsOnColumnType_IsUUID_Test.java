/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2015 the original author or authors.
 */
package org.assertj.db.api.assertions.impl;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.db.api.TableAssert;
import org.assertj.db.type.Table;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on {@link AssertionsOnColumnType} class :
 * {@link AssertionsOnColumnType#isUUID(org.assertj.db.api.AbstractAssert, org.assertj.core.api.WritableAssertionInfo, java.util.List, boolean)} method.
 *
 * @author Régis Pouiller
 *
 */
public class AssertionsOnColumnType_IsUUID_Test {

  /**
   * This method tests the {@code isTime} assertion method.
   */
  @Test
  public void test_is_time() {
    WritableAssertionInfo info = new WritableAssertionInfo();
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    List<Object> list = new ArrayList<Object>(Arrays.asList(UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"),
                                                            UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435")));
    TableAssert tableAssert2 = AssertionsOnColumnType.isUUID(tableAssert, info, list, false);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    list = new ArrayList<Object>(Arrays.asList(UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"),
                                               UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435")));
    tableAssert2 = AssertionsOnColumnType.isUUID(tableAssert, info, list, true);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    list = new ArrayList<Object>(Arrays.asList(null,
                                               UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435")));
    tableAssert2 = AssertionsOnColumnType.isUUID(tableAssert, info, list, true);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
  }

  /**
   * This method should fail because the value is not a uuid.
   */
  @Test
  public void should_fail_because_value_is_not_a_uuid() {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    try {
      List<Object> list = new ArrayList<Object>(Arrays.asList("test", UUID.fromString(
              "30B443AE-C0C9-4790-9BEC-CE1380808435")));
      AssertionsOnColumnType.isUUID(tableAssert, info, list, false);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                                    + "Expecting that the value at index 0:%n"
                                                                    + "  <\"test\">%n"
                                                                    + "to be of type%n"
                                                                    + "  <UUID>%n"
                                                                    + "but was of type%n"
                                                                    + "  <TEXT>"));
    }
  }

  /**
   * This method should fail because the value is not a uuid (with lenience).
   */
  @Test
  public void should_fail_because_value_is_not_a_uuid_with_lenience() {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    try {
      List<Object> list = new ArrayList<Object>(Arrays.asList(UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"), "test"));
      AssertionsOnColumnType.isUUID(tableAssert, info, list, false);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                                    + "Expecting that the value at index 1:%n"
                                                                    + "  <\"test\">%n"
                                                                    + "to be of type%n"
                                                                    + "  <UUID>%n"
                                                                    + "but was of type%n"
                                                                    + "  <TEXT>"));
    }
  }

  /**
   * This method should fail because the value is a stringbuiler.
   */
  @Test
  public void should_fail_because_value_is_a_stringbuilder() {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    try {
      List<Object> list = new ArrayList<Object>(Arrays.asList(new StringBuilder("test"), true));
      AssertionsOnColumnType.isUUID(tableAssert, info, list, false);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                                    + "Expecting that the value at index 0:%n"
                                                                    + "  <test>%n"
                                                                    + "to be of type%n"
                                                                    + "  <UUID>%n"
                                                                    + "but was of type%n"
                                                                    + "  <NOT_IDENTIFIED> (java.lang.StringBuilder)"));
    }
  }
}
