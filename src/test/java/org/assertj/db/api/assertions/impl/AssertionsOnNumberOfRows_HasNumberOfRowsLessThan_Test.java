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

import static org.junit.Assert.fail;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.db.api.TableAssert;
import org.junit.Test;

/**
 * Tests on {@link AssertionsOnNumberOfRows} class :
 * {@link AssertionsOnNumberOfRows#hasNumberOfRowsLessThan(org.assertj.db.api.AbstractAssert, org.assertj.core.api.WritableAssertionInfo, int, int)} method.
 *
 * @author Régis Pouiller
 */
public class AssertionsOnNumberOfRows_HasNumberOfRowsLessThan_Test {

  /**
   * This method tests the {@code hasNumberOfRowsLessThan} assertion method.
   */
  @Test
  public void test_has_number_of_rows_less() {
    WritableAssertionInfo info = new WritableAssertionInfo();
    TableAssert tableAssert = new TableAssert(null);
    TableAssert tableAssert2 = AssertionsOnNumberOfRows.hasNumberOfRowsLessThan(tableAssert, info, 8, 9);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
  }

  /**
   * This method should fail because the number of rows is different.
   */
  @Test
  public void should_fail_because_number_of_rows_is_greater_than_or_equal() {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    TableAssert tableAssert = new TableAssert(null);
    try {
      AssertionsOnNumberOfRows.hasNumberOfRowsLessThan(tableAssert, info, 8, 8);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting size (number of rows) to be less than :%n"
        + "   <8>%n"
        + "but was:%n"
        + "   <8>"));
    }
  }
}
