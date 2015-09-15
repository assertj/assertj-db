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
import org.assertj.db.type.ValueType;
import org.junit.Test;

import java.util.Locale;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on {@link AssertionsOnColumnOfChangeType} class :
 * {@link AssertionsOnColumnOfChangeType#isOfType(org.assertj.db.api.AbstractAssert, org.assertj.core.api.WritableAssertionInfo, Object, Object, org.assertj.db.type.ValueType, boolean)} method.
 *
 * @author Régis Pouiller
 *
 */
public class AssertionsOnColumnOfChangeType_IsOfType_Test {

  /**
   * This method tests the {@code isOfType} assertion method.
   */
  @Test
  public void test_is_of_type() {
    WritableAssertionInfo info = new WritableAssertionInfo();
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    TableAssert tableAssert2 = AssertionsOnColumnOfChangeType.isOfType(tableAssert, info, "test", "test", ValueType.TEXT, false);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    tableAssert2 = AssertionsOnColumnOfChangeType.isOfType(tableAssert, info, "test", "test", ValueType.TEXT, true);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    tableAssert2 = AssertionsOnColumnOfChangeType.isOfType(tableAssert, info, null, "test", ValueType.TEXT, true);
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
      AssertionsOnColumnOfChangeType.isOfType(tableAssert, info,
                                              8, "test", ValueType.TEXT, false);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                      + "Expecting that the value at start point:%n"
                                                      + "  <8>%n"
                                                      + "to be of type%n"
                                                      + "  <TEXT>%n"
                                                      + "but was of type%n"
                                                      + "  <NUMBER>"));
    }
    try {
      AssertionsOnColumnOfChangeType.isOfType(tableAssert, info,
                                              null, "test", ValueType.TEXT, false);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                                    + "Expecting that the value at start point:%n"
                                                                    + "  <null>%n"
                                                                    + "to be of type%n"
                                                                    + "  <TEXT>%n"
                                                                    + "but was of type%n"
                                                                    + "  <NOT_IDENTIFIED>"));
    }
    try {
      AssertionsOnColumnOfChangeType.isOfType(tableAssert, info,
                                              Locale.FRENCH, "test", ValueType.TEXT, false);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                                    + "Expecting that the value at start point:%n"
                                                                    + "  <fr>%n"
                                                                    + "to be of type%n"
                                                                    + "  <TEXT>%n"
                                                                    + "but was of type%n"
                                                                    + "  <NOT_IDENTIFIED> (java.util.Locale)"));
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
      AssertionsOnColumnOfChangeType.isOfType(tableAssert, info,
                                              "test", 8, ValueType.TEXT, false);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                      + "Expecting that the value at end point:%n"
                                                      + "  <8>%n"
                                                      + "to be of type%n"
                                                      + "  <TEXT>%n"
                                                      + "but was of type%n"
                                                      + "  <NUMBER>"));
    }
    try {
      AssertionsOnColumnOfChangeType.isOfType(tableAssert, info,
                                              "test", null, ValueType.TEXT, false);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                                    + "Expecting that the value at end point:%n"
                                                                    + "  <null>%n"
                                                                    + "to be of type%n"
                                                                    + "  <TEXT>%n"
                                                                    + "but was of type%n"
                                                                    + "  <NOT_IDENTIFIED>"));
    }
    try {
      AssertionsOnColumnOfChangeType.isOfType(tableAssert, info,
                                              "test", Locale.FRENCH, ValueType.TEXT, false);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                                    + "Expecting that the value at end point:%n"
                                                                    + "  <fr>%n"
                                                                    + "to be of type%n"
                                                                    + "  <TEXT>%n"
                                                                    + "but was of type%n"
                                                                    + "  <NOT_IDENTIFIED> (java.util.Locale)"));
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
      AssertionsOnColumnOfChangeType.isOfType(tableAssert, info,
                                              new StringBuilder("test"), "test", ValueType.TEXT, false);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                                    + "Expecting that the value at start point:%n"
                                                                    + "  <test>%n"
                                                                    + "to be of type%n"
                                                                    + "  <TEXT>%n"
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
      AssertionsOnColumnOfChangeType.isOfType(tableAssert, info,
                                              "test", new StringBuilder("test"), ValueType.TEXT, false);
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
