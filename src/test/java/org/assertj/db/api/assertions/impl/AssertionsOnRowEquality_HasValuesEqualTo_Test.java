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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on {@link AssertionsOnRowEquality} class :
 * {@link AssertionsOnRowEquality#hasValuesEqualTo(org.assertj.db.api.AbstractAssert, org.assertj.core.api.WritableAssertionInfo, java.util.List, Object...)} method.
 *
 * @author Régis Pouiller
 *
 */
public class AssertionsOnRowEquality_HasValuesEqualTo_Test {

  /**
   * This method tests the {@code hasValuesEqualTo} assertion method.
   */
  @Test
  public void test_has_values_equal_to() {
    WritableAssertionInfo info = new WritableAssertionInfo();
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    List<Object> list = new ArrayList<Object>(Arrays.asList(1, "Weaver", "Sigourney", Date.valueOf("1949-10-08")));
    TableAssert tableAssert2 = AssertionsOnRowEquality.hasValuesEqualTo(tableAssert, info, list, 1, "Weaver", "Sigourney", "1949-10-08");
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
  }

  /**
   * This method should fail because the values are different.
   */
  @Test
  public void should_fail_because_values_are_different() {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    List<Object> list = new ArrayList<Object>(Arrays.asList(1, "Weaver", "Sigourney", Date.valueOf("1949-10-08")));
    try {
      AssertionsOnRowEquality.hasValuesEqualTo(tableAssert, info, list, 1, "Weaverr", "Sigourney", "1949-10-08");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[description] \n"
                                                      + "Expecting that the value at index 1:\n"
                                                      + "  <\"Weaver\">\n"
                                                      + "to be equal to: \n"
                                                      + "  <\"Weaverr\">");
    }
  }

  /**
   * This method should fail because the types are not compatible.
   */
  @Test
  public void should_fail_because_types_are_not_compatible() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    List<Object> list = new ArrayList<Object>(Arrays.asList(1, "Weaver", "Sigourney", Date.valueOf("1949-10-08")));
    try {
      AssertionsOnRowEquality.hasValuesEqualTo(tableAssert, info, list, 1, true, "Sigourney", "1949-10-08");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[description] \n"
                                                      + "Expecting that the value at index 1:\n"
                                                      + "  <\"Weaver\">\n"
                                                      + "to be of type\n"
                                                      + "  <[BOOLEAN]>\n"
                                                      + "but was of type\n"
                                                      + "  <TEXT>");
    }
  }

  /**
   * This method should fail because the bytes values are different.
   */
  @Test
  public void should_fail_because_bytes_values_are_different() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    List<Object> list = new ArrayList<Object>(Arrays.asList(1, new byte[] {0, 1}, "Sigourney", Date.valueOf("1949-10-08")));
    try {
      AssertionsOnRowEquality.hasValuesEqualTo(tableAssert, info, list, 1, new byte[] {2, 3}, "Sigourney", "1949-10-08");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[description] \n"
                                                      + "Expecting that the value at index 1 to be equal to the expected value but was not equal");
    }
  }
}
