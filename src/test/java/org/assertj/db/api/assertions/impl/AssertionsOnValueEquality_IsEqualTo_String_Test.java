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

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.db.api.TableAssert;
import org.assertj.db.common.AbstractTest;
import org.junit.Test;

/**
 * Tests on {@link  AssertionsOnValueEquality} class :
 * {@link  AssertionsOnValueEquality#isEqualTo(org.assertj.db.api.AbstractAssert, org.assertj.core.api.WritableAssertionInfo, org.assertj.db.type.Value, String)} method.
 *
 * @author Régis Pouiller
 */
public class AssertionsOnValueEquality_IsEqualTo_String_Test extends AbstractTest {

  /**
   * This method tests the {@code isEqualTo} assertion method.
   */
  @Test
  public void test_is_equal_to() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    TableAssert tableAssert = new TableAssert(null);
    TableAssert tableAssert2 = AssertionsOnValueEquality.isEqualTo(tableAssert, info, getValue(null, "test"), "test");
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    tableAssert2 = AssertionsOnValueEquality.isEqualTo(tableAssert, info, getValue(null, 8), "8");
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    tableAssert2 = AssertionsOnValueEquality.isEqualTo(tableAssert, info, getValue(null, Date.valueOf("2007-12-23")), "2007-12-23");
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    tableAssert2 = AssertionsOnValueEquality.isEqualTo(tableAssert, info, getValue(
      null, Timestamp.valueOf("2007-12-23 00:00:00")), "2007-12-23");
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    tableAssert2 = AssertionsOnValueEquality.isEqualTo(tableAssert, info, getValue(null, Time.valueOf("09:01:00")), "09:01");
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    tableAssert2 = AssertionsOnValueEquality.isEqualTo(tableAssert, info, getValue(null, Date.valueOf("2007-12-23")), "2007-12-23T00:00:00");
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    tableAssert2 = AssertionsOnValueEquality.isEqualTo(tableAssert, info, getValue(
      null, Timestamp.valueOf("2007-12-23 09:01:00")), "2007-12-23T09:01");
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    tableAssert2 = AssertionsOnValueEquality.isEqualTo(tableAssert, info,
      getValue(null, UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435")), "30B443AE-C0C9-4790-9BEC-CE1380808435");
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    tableAssert2 = AssertionsOnValueEquality.isEqualTo(tableAssert, info, getValue(null, null), (String) null);
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
      AssertionsOnValueEquality.isEqualTo(tableAssert, info, getValue(null, "test1"), "test");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting:%n"
        + "  <\"test1\">%n"
        + "to be equal to: %n"
        + "  <\"test\">"));
    }
    try {
      AssertionsOnValueEquality.isEqualTo(tableAssert, info, getValue(null, 9), "8");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting:%n"
        + "  <\"9\">%n"
        + "to be equal to: %n"
        + "  <\"8\">"));
    }
    try {
      AssertionsOnValueEquality.isEqualTo(tableAssert, info, getValue(null, Date.valueOf("2007-12-24")), "2007-12-23");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting:%n"
        + "  <\"2007-12-24\">%n"
        + "to be equal to: %n"
        + "  <\"2007-12-23\">"));
    }
    try {
      AssertionsOnValueEquality.isEqualTo(tableAssert, info, getValue(null, Timestamp.valueOf("2007-12-24 00:00:00")), "2007-12-23");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting:%n"
        + "  <\"2007-12-24T00:00:00.000000000\">%n"
        + "to be equal to: %n"
        + "  <\"2007-12-23\">"));
    }
    try {
      AssertionsOnValueEquality.isEqualTo(tableAssert, info, getValue(null, Time.valueOf("09:01:05")), "09:01");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting:%n"
        + "  <\"09:01:05.000000000\">%n"
        + "to be equal to: %n"
        + "  <\"09:01\">"));
    }
    try {
      AssertionsOnValueEquality.isEqualTo(tableAssert, info, getValue(null, Date.valueOf("2007-12-24")), "2007-12-23T00:00:00");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting:%n"
        + "  <\"2007-12-24T00:00:00.000000000\">%n"
        + "to be equal to: %n"
        + "  <\"2007-12-23T00:00:00\">"));
    }
    try {
      AssertionsOnValueEquality.isEqualTo(tableAssert, info, getValue(null, Timestamp.valueOf("2007-12-23 09:01:05")), "2007-12-23T09:01");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting:%n"
        + "  <\"2007-12-23T09:01:05.000000000\">%n"
        + "to be equal to: %n"
        + "  <\"2007-12-23T09:01\">"));
    }
    try {
      AssertionsOnValueEquality.isEqualTo(tableAssert, info,
        getValue(null, UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435")), "0E2A1269-EFF0-4233-B87B-B53E8B6F164D");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting:%n"
        + "  <\"30b443ae-c0c9-4790-9bec-ce1380808435\">%n"
        + "to be equal to: %n"
        + "  <\"0E2A1269-EFF0-4233-B87B-B53E8B6F164D\">"));
    }
    try {
      AssertionsOnValueEquality.isEqualTo(tableAssert, info, getValue(null, "test1"), (String) null);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting:%n"
        + "  <\"test1\">%n"
        + "to be equal to: %n"
        + "  <null>"));
    }
    try {
      AssertionsOnValueEquality.isEqualTo(tableAssert, info, getValue(null, 9), (String) null);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting:%n"
        + "  <9>%n"
        + "to be equal to: %n"
        + "  <null>"));
    }
    try {
      AssertionsOnValueEquality.isEqualTo(tableAssert, info, getValue(null, Date.valueOf("2007-12-24")), (String) null);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting:%n"
        + "  <2007-12-24T00:00:00.000 (java.sql.Date)>%n"
        + "to be equal to: %n"
        + "  <null>"));
    }
    try {
      AssertionsOnValueEquality.isEqualTo(tableAssert, info, getValue(null, Timestamp.valueOf("2007-12-24 00:00:00")), (String) null);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting:%n"
        + "  <2007-12-24T00:00:00.000000000>%n"
        + "to be equal to: %n"
        + "  <null>"));
    }
    try {
      AssertionsOnValueEquality.isEqualTo(tableAssert, info, getValue(null, Time.valueOf("09:01:05")), (String) null);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting:%n"
        + "  <09:01:05.000000000>%n"
        + "to be equal to: %n"
        + "  <null>"));
    }
    try {
      AssertionsOnValueEquality.isEqualTo(tableAssert, info, getValue(null, Timestamp.valueOf("2007-12-23 09:01:05")), (String) null);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting:%n"
        + "  <2007-12-23T09:01:05.000000000>%n"
        + "to be equal to: %n"
        + "  <null>"));
    }
    try {
      AssertionsOnValueEquality.isEqualTo(tableAssert, info,
        getValue(null, UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435")), (String) null);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting:%n"
        + "  <30b443ae-c0c9-4790-9bec-ce1380808435>%n"
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
      AssertionsOnValueEquality.isEqualTo(tableAssert, info, getValue(null, false), "test");
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
