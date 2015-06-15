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
 * {@link  AssertionsOnValueEquality#isEqualTo(org.assertj.db.api.AbstractAssert, org.assertj.core.api.WritableAssertionInfo, Object, byte[])} method.
 *
 * @author Régis Pouiller
 *
 */
public class AssertionsOnValueEquality_IsEqualTo_Bytes_Test {

  /**
   * This method tests the {@code isEqualTo} assertion method.
   */
  @Test
  public void test_is_equal_to() {
    WritableAssertionInfo info = new WritableAssertionInfo();
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    TableAssert tableAssert2 = AssertionsOnValueEquality.isEqualTo(tableAssert, info, new byte[]{0, 1}, new byte[]{0, 1});
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
  }

  /**
   * This method should fail because the value is greater than or equal to.
   */
  @Test
  public void should_fail_because_value_is_not_equal_to() {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    try {
      AssertionsOnValueEquality.isEqualTo(tableAssert, info, new byte[]{2, 3}, new byte[]{0, 1});
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                      + "Expecting to be equal to the expected value but was not equal"));
    }
  }

  /**
   * This method should fail because the value is not bytes.
   */
  @Test
  public void should_fail_because_value_is_not_bytes() {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    try {
      AssertionsOnValueEquality.isEqualTo(tableAssert, info, 8, new byte[]{0, 1});
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                      + "Expecting:%n"
                                                      + "  <8>%n"
                                                      + "to be of type%n"
                                                      + "  <BYTES>%n"
                                                      + "but was of type%n"
                                                      + "  <NUMBER>"));
    }
  }
}
