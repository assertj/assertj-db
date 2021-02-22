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

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.db.api.TableAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Tests on {@link AssertionsOnColumnOfChangeEquality} class :
 * {@link AssertionsOnColumnOfChangeEquality#hasValues(org.assertj.db.api.AbstractAssert, org.assertj.core.api.WritableAssertionInfo, org.assertj.db.type.Value, org.assertj.db.type.Value, Character, Character)} method.
 *
 * @author Régis Pouiller
 *
 */
public class AssertionsOnColumnOfChangeEquality_HasValues_Two_Character_Test extends AbstractTest {

  /**
   * This method tests the {@code hasValues} assertion method.
   */
  @Test
  public void test_has_values() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    TableAssert tableAssert2 = AssertionsOnColumnOfChangeEquality.hasValues(tableAssert, info,
                                                                            getValue(null, 'T'),
                                                                            getValue(null, "T"),
                                                                            'T',
                                                                            'T');
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
  }

  /**
   * This method should fail because the value at start point is different.
   */
  @Test
  public void should_fail_because_value_at_start_point_is_different() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    try {
      AssertionsOnColumnOfChangeEquality.hasValues(tableAssert, info,
                                                   getValue(null, 't'),
                                                   getValue(null, 'T'),
                                                   'T', 'T');
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                      + "Expecting that start point:%n"
                                                      + "  <'t'>%n"
                                                      + "to be equal to: %n"
                                                      + "  <'T'>"));
    }
    try {
      AssertionsOnColumnOfChangeEquality.hasValues(tableAssert, info,
                                                   getValue(null, "t"),
                                                   getValue(null, 'T'),
                                                   'T', 'T');
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                      + "Expecting that start point:%n"
                                                      + "  <\"t\">%n"
                                                      + "to be equal to: %n"
                                                      + "  <'T'>"));
    }
  }

  /**
   * This method should fail because the value at end point is different.
   */
  @Test
  public void should_fail_because_value_at_end_point_is_different() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    try {
      AssertionsOnColumnOfChangeEquality.hasValues(tableAssert, info,
                                                   getValue(null, 'T'),
                                                   getValue(null, 't'),
                                                   'T', 'T');
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                      + "Expecting that end point:%n"
                                                      + "  <'t'>%n"
                                                      + "to be equal to: %n"
                                                      + "  <'T'>"));
    }
    try {
      AssertionsOnColumnOfChangeEquality.hasValues(tableAssert, info,
                                                   getValue(null, "T"),
                                                   getValue(null, "t"),
                                                   'T', 'T');
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                      + "Expecting that end point:%n"
                                                      + "  <\"t\">%n"
                                                      + "to be equal to: %n"
                                                      + "  <'T'>"));
    }
  }

  /**
   * This method should fail because one of the values is not a text.
   */
  @Test
  public void should_fail_because_one_value_is_not_a_text() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    try {
      AssertionsOnColumnOfChangeEquality.hasValues(tableAssert, info,
                                                   getValue(null, true),
                                                   getValue(null, "t"),
                                                   'T', 't');
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                      + "Expecting that the value at start point:%n"
                                                      + "  <true>%n"
                                                      + "to be of type%n"
                                                      + "  <[TEXT, NOT_IDENTIFIED]>%n"
                                                      + "but was of type%n"
                                                      + "  <BOOLEAN>"));
    }
    try {
      AssertionsOnColumnOfChangeEquality.hasValues(tableAssert, info,
                                                   getValue(null, "T"),
                                                   getValue(null, true),
                                                   'T', 't');
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                      + "Expecting that the value at end point:%n"
                                                      + "  <true>%n"
                                                      + "to be of type%n"
                                                      + "  <[TEXT, NOT_IDENTIFIED]>%n"
                                                      + "but was of type%n"
                                                      + "  <BOOLEAN>"));
    }
  }
}
