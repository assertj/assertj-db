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

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on {@link AssertionsOnValueNonEquality} class :
 * {@link AssertionsOnValueNonEquality#isNotEqualTo(org.assertj.db.api.AbstractAssert, org.assertj.core.api.WritableAssertionInfo, Object, String)} method.
 *
 * @author Régis Pouiller
 *
 */
public class AssertionsOnValueNonEquality_IsNotEqualTo_String_Test {

  /**
   * This method tests the {@code isNotEqualTo} assertion method.
   */
  @Test
  public void test_is_not_equal_to() {
    WritableAssertionInfo info = new WritableAssertionInfo();
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    TableAssert tableAssert2 = AssertionsOnValueNonEquality.isNotEqualTo(tableAssert, info, "test1", "test");
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    tableAssert2 = AssertionsOnValueNonEquality.isNotEqualTo(tableAssert, info, 9, "8");
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    tableAssert2 = AssertionsOnValueNonEquality.isNotEqualTo(tableAssert, info, Date.valueOf("2007-12-24"),
                                                             "2007-12-23");
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    tableAssert2 = AssertionsOnValueNonEquality.isNotEqualTo(tableAssert, info,
                                                             Timestamp.valueOf("2007-12-24 00:00:00"), "2007-12-23");
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    tableAssert2 = AssertionsOnValueNonEquality.isNotEqualTo(tableAssert, info, Time.valueOf("09:01:05"), "09:01");
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    tableAssert2 = AssertionsOnValueNonEquality.isNotEqualTo(tableAssert, info, Date.valueOf("2007-12-24"),
                                                             "2007-12-23T00:00:00");
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    tableAssert2 = AssertionsOnValueNonEquality.isNotEqualTo(tableAssert, info,
                                                             Timestamp.valueOf("2007-12-23 09:01:05"),
                                                             "2007-12-23T09:01");
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
  }

  /**
   * This method should fail because the value is greater than or equal to.
   */
  @Test
  public void should_fail_because_value_is_equal_to() {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    try {
      AssertionsOnValueNonEquality.isNotEqualTo(tableAssert, info, "test", "test");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                         + "Expecting:%n"
                                                         + "  <\"test\">%n"
                                                         + "not to be equal to: %n"
                                                         + "  <\"test\">"));
    }
    try {
      AssertionsOnValueNonEquality.isNotEqualTo(tableAssert, info, 8, "8");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                      + "Expecting:%n"
                                                      + "  <\"8\">%n"
                                                      + "not to be equal to: %n"
                                                      + "  <\"8\">"));
    }
    try {
      AssertionsOnValueNonEquality.isNotEqualTo(tableAssert, info, Date.valueOf("2007-12-23"), "2007-12-23");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                      + "Expecting:%n"
                                                      + "  <\"2007-12-23\">%n"
                                                      + "not to be equal to: %n"
                                                      + "  <\"2007-12-23\">"));
    }
    try {
      AssertionsOnValueNonEquality.isNotEqualTo(tableAssert, info, Timestamp.valueOf("2007-12-23 00:00:00"),
                                                "2007-12-23");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                      + "Expecting:%n"
                                                      + "  <\"2007-12-23T00:00:00.000000000\">%n"
                                                      + "not to be equal to: %n"
                                                      + "  <\"2007-12-23\">"));
    }
    try {
      AssertionsOnValueNonEquality.isNotEqualTo(tableAssert, info, Time.valueOf("09:01:00"), "09:01");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                      + "Expecting:%n"
                                                      + "  <\"09:01:00.000000000\">%n"
                                                      + "not to be equal to: %n"
                                                      + "  <\"09:01\">"));
    }
    try {
      AssertionsOnValueNonEquality.isNotEqualTo(tableAssert, info, Date.valueOf("2007-12-23"), "2007-12-23T00:00:00");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                      + "Expecting:%n"
                                                      + "  <\"2007-12-23T00:00:00.000000000\">%n"
                                                      + "not to be equal to: %n"
                                                      + "  <\"2007-12-23T00:00:00\">"));
    }
    try {
      AssertionsOnValueNonEquality.isNotEqualTo(tableAssert, info, Timestamp.valueOf("2007-12-23 09:01:00"),
                                                "2007-12-23T09:01");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                      + "Expecting:%n"
                                                      + "  <\"2007-12-23T09:01:00.000000000\">%n"
                                                      + "not to be equal to: %n"
                                                      + "  <\"2007-12-23T09:01\">"));
    }
  }

  /**
   * This method should fail because the value is not a text.
   */
  @Test
  public void should_fail_because_value_is_not_a_text() {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    try {
      AssertionsOnValueNonEquality.isNotEqualTo(tableAssert, info, false, "test");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                      + "Expecting:%n"
                                                      + "  <false>%n"
                                                      + "to be of type%n"
                                                      + "  <[TEXT, NUMBER, DATE, TIME, DATE_TIME, UUID]>%n"
                                                      + "but was of type%n"
                                                      + "  <BOOLEAN>"));
    }
  }
}
