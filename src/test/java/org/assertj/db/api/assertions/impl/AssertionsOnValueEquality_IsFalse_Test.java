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
 * Tests on {@link  AssertionsOnValueEquality} class :
 * {@link  AssertionsOnValueEquality#isFalse(org.assertj.db.api.AbstractAssert, org.assertj.core.api.WritableAssertionInfo, Object)} method.
 *
 * @author Régis Pouiller
 *
 */
public class AssertionsOnValueEquality_IsFalse_Test {

  /**
   * This method tests the {@code isFalse} assertion method.
   */
  @Test
  public void test_is_false() {
    WritableAssertionInfo info = new WritableAssertionInfo();
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    TableAssert tableAssert2 = AssertionsOnValueEquality.isFalse(tableAssert, info, false);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
  }

  /**
   * This method should fail because the value is true.
   */
  @Test
  public void should_fail_because_value_is_true() {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    try {
      AssertionsOnValueEquality.isFalse(tableAssert, info, true);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[description] \n"
                                                      + "Expecting:\n"
                                                      + "  <true>\n"
                                                      + "to be equal to: \n"
                                                      + "  <false>");
    }
  }

  /**
   * This method should fail because the value is not a boolean.
   */
  @Test
  public void should_fail_because_value_is_not_a_boolean() {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    try {
      AssertionsOnValueEquality.isFalse(tableAssert, info, 8);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[description] \n"
                                                      + "Expecting:\n"
                                                      + "  <8>\n"
                                                      + "to be of type\n"
                                                      + "  <BOOLEAN>\n"
                                                      + "but was of type\n"
                                                      + "  <NUMBER>");
    }
  }
}
