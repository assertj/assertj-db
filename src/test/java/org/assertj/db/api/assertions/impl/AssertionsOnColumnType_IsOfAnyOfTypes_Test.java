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
 * Copyright 2012-2014 the original author or authors.
 */
package org.assertj.db.api.assertions.impl;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.db.api.TableAssert;
import org.assertj.db.type.Table;
import org.assertj.db.type.ValueType;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on {@link AssertionsOnColumnType} class :
 * {@link AssertionsOnColumnType#isOfAnyOfTypes(org.assertj.db.api.AbstractAssert, org.assertj.core.api.WritableAssertionInfo, java.util.List, org.assertj.db.type.ValueType...)} method.
 *
 * @author Régis Pouiller
 *
 */
public class AssertionsOnColumnType_IsOfAnyOfTypes_Test {

  /**
   * This method tests the {@code isOfAnyOfTypes} assertion method.
   */
  @Test
  public void test_is_of_any_of_types() {
    WritableAssertionInfo info = new WritableAssertionInfo();
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    List<Object> list = new ArrayList<Object>(Arrays.asList("test", "test"));
    TableAssert tableAssert2 = AssertionsOnColumnType.isOfAnyOfTypes(tableAssert, info, list,
                                                                             ValueType.TEXT);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    list = new ArrayList<Object>(Arrays.asList("test", "test"));
    tableAssert2 = AssertionsOnColumnType.isOfAnyOfTypes(tableAssert, info, list, ValueType.TEXT, ValueType.NUMBER);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    list = new ArrayList<Object>(Arrays.asList(null, "test"));
    tableAssert2 = AssertionsOnColumnType.isOfAnyOfTypes(tableAssert, info, list, ValueType.TEXT, ValueType.NOT_IDENTIFIED);
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
      List<Object> list = new ArrayList<Object>(Arrays.asList(8, "test"));
      AssertionsOnColumnType.isOfAnyOfTypes(tableAssert, info, list, ValueType.TEXT, ValueType.DATE);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[description] \n"
                                                      + "Expecting that the value at index 0:\n"
                                                      + "  <8>\n"
                                                      + "to be of type\n"
                                                      + "  <[TEXT, DATE]>\n"
                                                      + "but was of type\n"
                                                      + "  <NUMBER>");
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
      List<Object> list = new ArrayList<Object>(Arrays.asList("test", 8));
      AssertionsOnColumnType.isOfAnyOfTypes(tableAssert, info, list, ValueType.TEXT, ValueType.DATE);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[description] \n"
                                                      + "Expecting that the value at index 1:\n"
                                                      + "  <8>\n"
                                                      + "to be of type\n"
                                                      + "  <[TEXT, DATE]>\n"
                                                      + "but was of type\n"
                                                      + "  <NUMBER>");
    }
  }
}
