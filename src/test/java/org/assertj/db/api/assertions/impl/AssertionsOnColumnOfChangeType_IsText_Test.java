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

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on {@link org.assertj.db.api.assertions.impl.AssertionsOnColumnOfChangeType} class :
 * {@link org.assertj.db.api.assertions.impl.AssertionsOnColumnOfChangeType#isText(org.assertj.db.api.AbstractAssert, org.assertj.core.api.WritableAssertionInfo, Object, Object, boolean)} method.
 *
 * @author Régis Pouiller
 *
 */
public class AssertionsOnColumnOfChangeType_IsText_Test {

  /**
   * This method tests the {@code isText} assertion method.
   */
  @Test
  public void test_is_text() {
    WritableAssertionInfo info = new WritableAssertionInfo();
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    TableAssert tableAssert2 = AssertionsOnColumnOfChangeType.isText(tableAssert, info, "test", "test", false);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    tableAssert2 = AssertionsOnColumnOfChangeType.isText(tableAssert, info, "test", "test", true);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    tableAssert2 = AssertionsOnColumnOfChangeType.isText(tableAssert, info, null, "test", true);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
  }

  /**
   * This method should fail because the value at start point have different type.
   */
  @Test
  public void should_fail_because_value_at_start_point_have_different_type() {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    try {
      AssertionsOnColumnOfChangeType.isText(tableAssert, info,
                                            true, "test", false);
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
  public void should_fail_because_value_at_end_point_have_different_type() {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    try {
      AssertionsOnColumnOfChangeType.isText(tableAssert, info,
                                            "test", false, false);
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
  public void should_fail_because_value_at_start_point_is_a_stringbuilder() {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    try {
      AssertionsOnColumnOfChangeType.isTime(tableAssert, info,
                                            new StringBuilder("test"), "test", false);
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
  public void should_fail_because_value_at_end_point_is_a_stringbuilder() {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    try {
      AssertionsOnColumnOfChangeType.isText(tableAssert, info,
                                            "test", new StringBuilder("test"), false);
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
