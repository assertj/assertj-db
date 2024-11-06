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

import org.assertj.core.api.Assertions;
import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.db.api.TableAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.exception.AssertJDBException;
import org.assertj.db.type.Table;
import org.junit.Test;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on {@link AssertionsOnColumnOfChangeClass} class :
 * {@link AssertionsOnColumnOfChangeClass#isOfClass(org.assertj.db.api.AbstractAssert, org.assertj.core.api.WritableAssertionInfo, org.assertj.db.type.Value, org.assertj.db.type.Value, Class, boolean)} method.
 *
 * @author Régis Pouiller
 *
 */
public class AssertionsOnColumnOfChangeClass_IsOfClass_Test extends AbstractTest {

  /**
   * This method tests the {@code isOfClass} assertion method.
   */
  @Test
  public void test_is_of_type() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    TableAssert tableAssert2 = AssertionsOnColumnOfChangeClass.isOfClass(tableAssert, info,
                                                                         getValue(null, "test"),
                                                                         getValue(null, "test"),
                                                                         String.class, false);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    tableAssert2 = AssertionsOnColumnOfChangeClass.isOfClass(tableAssert, info,
                                                             getValue(null, "test"),
                                                             getValue(null, "test"),
                                                             String.class, true);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    tableAssert2 = AssertionsOnColumnOfChangeClass.isOfClass(tableAssert, info,
                                                             getValue(null, null),
                                                             getValue(null, "test"),
                                                             String.class, true);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    tableAssert2 = AssertionsOnColumnOfChangeClass.isOfClass(tableAssert, info,
                                                             getValue(null, "test"),
                                                             getValue(null, null),
                                                             String.class, true);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
  }

  /**
   * This method should fail because the value at start point have different class.
   */
  @Test
  public void should_fail_because_value_at_start_point_have_different_type() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    try {
      AssertionsOnColumnOfChangeClass.isOfClass(tableAssert, info,
                                                getValue(null, 8),
                                                getValue(null, "test"),
                                                String.class, false);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                                    + "Expecting that the value at start point:%n"
                                                                    + "  <8>%n"
                                                                    + "to be of class%n"
                                                                    + "  <java.lang.String>%n"
                                                                    + "but was of class%n"
                                                                    + "  <java.lang.Integer>"));
    }
    try {
      AssertionsOnColumnOfChangeClass.isOfClass(tableAssert, info,
                                                getValue(null, null),
                                                getValue(null, "test"),
                                                String.class, false);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                                    + "Expecting that the value at start point:%n"
                                                                    + "  <null>%n"
                                                                    + "to be of class%n"
                                                                    + "  <java.lang.String>"));
    }
    try {
      AssertionsOnColumnOfChangeClass.isOfClass(tableAssert, info,
                                                getValue(null, "test"),
                                                getValue(null, null),
                                                String.class, false);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                                    + "Expecting that the value at end point:%n"
                                                                    + "  <null>%n"
                                                                    + "to be of class%n"
                                                                    + "  <java.lang.String>"));
    }
  }

  /**
   * This method should fail because the value at end point have different class.
   */
  @Test
  public void should_fail_because_value_at_end_point_have_different_type() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    try {
      AssertionsOnColumnOfChangeClass.isOfClass(tableAssert, info,
                                                getValue(null, "test"),
                                                getValue(null, 8),
                                                String.class, false);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                                    + "Expecting that the value at end point:%n"
                                                                    + "  <8>%n"
                                                                    + "to be of class%n"
                                                                    + "  <java.lang.String>%n"
                                                                    + "but was of class%n"
                                                                    + "  <java.lang.Integer>"));
    }
    try {
      AssertionsOnColumnOfChangeClass.isOfClass(tableAssert, info,
                                                getValue(null, "test"),
                                                getValue(null, null),
                                                String.class, false);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                                    + "Expecting that the value at end point:%n"
                                                                    + "  <null>%n"
                                                                    + "to be of class%n"
                                                                    + "  <java.lang.String>"));
    }
    try {
      AssertionsOnColumnOfChangeClass.isOfClass(tableAssert, info,
                                                getValue(null, null),
                                                getValue(null, "test"),
                                                String.class, false);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                                    + "Expecting that the value at start point:%n"
                                                                    + "  <null>%n"
                                                                    + "to be of class%n"
                                                                    + "  <java.lang.String>"));
    }
  }

  /**
   * This method should fail because the value at start point is a stringbuilder.
   */
  @Test
  public void should_fail_because_value_at_start_point_is_a_stringbuilder() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    try {
      AssertionsOnColumnOfChangeClass.isOfClass(tableAssert, info,
                                                getValue(null, new StringBuilder("test")),
                                                getValue(null, "test"),
                                                String.class, false);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                                    + "Expecting that the value at start point:%n"
                                                                    + "  <test>%n"
                                                                    + "to be of class%n"
                                                                    + "  <java.lang.String>%n"
                                                                    + "but was of class%n"
                                                                    + "  <java.lang.StringBuilder>"));
    }
    try {
      AssertionsOnColumnOfChangeClass.isOfClass(tableAssert, info,
                                                getValue(null, new StringBuilder("test")),
                                                getValue(null, "test"),
                                                String.class, true);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                                    + "Expecting that the value at start point:%n"
                                                                    + "  <test>%n"
                                                                    + "to be of class%n"
                                                                    + "  <java.lang.String>%n"
                                                                    + "but was of class%n"
                                                                    + "  <java.lang.StringBuilder>"));
    }
  }

  /**
   * This method should fail because the value at end point is a stringbuilder.
   */
  @Test
  public void should_fail_because_value_at_end_point_is_a_stringbuilder() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    try {
      AssertionsOnColumnOfChangeClass.isOfClass(tableAssert, info,
                                                getValue(null, "test"),
                                                getValue(null, new StringBuilder("test")),
                                                String.class, false);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                                    + "Expecting that the value at end point:%n"
                                                                    + "  <test>%n"
                                                                    + "to be of class%n"
                                                                    + "  <java.lang.String>%n"
                                                                    + "but was of class%n"
                                                                    + "  <java.lang.StringBuilder>"));
    }
    try {
      AssertionsOnColumnOfChangeClass.isOfClass(tableAssert, info,
                                                getValue(null, "test"),
                                                getValue(null, new StringBuilder("test")),
                                                String.class, true);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                                    + "Expecting that the value at end point:%n"
                                                                    + "  <test>%n"
                                                                    + "to be of class%n"
                                                                    + "  <java.lang.String>%n"
                                                                    + "but was of class%n"
                                                                    + "  <java.lang.StringBuilder>"));
    }
  }

  /**
   * This method should fail because the class value is null.
   */
  @Test
  public void should_fail_because_class_value_is_null() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    try {
      AssertionsOnColumnOfChangeClass.isOfClass(tableAssert, info,
                                                getValue(null, "test"),
                                                getValue(null, "test"),
                                                null, false);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("Class of the column is null"));
    }
  }
}
