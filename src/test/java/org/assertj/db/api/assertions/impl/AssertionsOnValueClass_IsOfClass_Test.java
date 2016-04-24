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
 * Copyright 2012-2016 the original author or authors.
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
 * Tests on {@link AssertionsOnValueClass} class :
 * {@link AssertionsOnValueClass#isOfClass(org.assertj.db.api.AbstractAssert, org.assertj.core.api.WritableAssertionInfo, org.assertj.db.type.Value, Class)} method.
 *
 * @author Régis Pouiller
 *
 */
public class AssertionsOnValueClass_IsOfClass_Test extends AbstractTest {

  /**
   * This method tests the {@code isOfClass} assertion method.
   */
  @Test
  public void test_is_of_class() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    TableAssert tableAssert2 = AssertionsOnValueClass.isOfClass(tableAssert, info, getValue(null, "test"), String.class);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    tableAssert2 = AssertionsOnValueClass.isOfClass(tableAssert, info, getValue(null, "test"), CharSequence.class);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    tableAssert2 = AssertionsOnValueClass.isOfClass(tableAssert, info, getValue(null, new StringBuilder("test")), StringBuilder.class);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
  }

  /**
   * This method should fail because the value is not of class.
   */
  @Test
  public void should_fail_because_value_is_not_of_class() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    try {
      AssertionsOnValueClass.isOfClass(tableAssert, info, getValue(null, 8), String.class);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                                    + "Expecting:%n"
                                                                    + "  <8>%n"
                                                                    + "to be of class%n"
                                                                    + "  <java.lang.String>%n"
                                                                    + "but was of class%n"
                                                                    + "  <java.lang.Integer>"));
    }
    try {
      AssertionsOnValueClass.isOfClass(tableAssert, info, getValue(null, null), String.class);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                                    + "Expecting:%n"
                                                                    + "  <null>%n"
                                                                    + "to be of class%n"
                                                                    + "  <java.lang.String>"));
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
      AssertionsOnValueClass.isOfClass(tableAssert, info, getValue(null, 8), null);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("Class of the value is null"));
    }
  }
}
