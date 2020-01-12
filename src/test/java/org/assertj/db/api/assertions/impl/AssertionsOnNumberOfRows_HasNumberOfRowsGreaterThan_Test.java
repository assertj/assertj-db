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
 * Copyright 2015-2020 the original author or authors.
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
 * Tests on {@link AssertionsOnNumberOfRows} class :
 * {@link AssertionsOnNumberOfRows#hasNumberOfRowsGreaterThan(org.assertj.db.api.AbstractAssert, org.assertj.core.api.WritableAssertionInfo, int, int)} method.
 *
 * @author Régis Pouiller
 *
 */
public class AssertionsOnNumberOfRows_HasNumberOfRowsGreaterThan_Test {

  /**
   * This method tests the {@code hasNumberOfRowsGreaterThan} assertion method.
   */
  @Test
  public void test_has_number_of_rows_greater() {
    WritableAssertionInfo info = new WritableAssertionInfo();
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    TableAssert tableAssert2 = AssertionsOnNumberOfRows.hasNumberOfRowsGreaterThan(tableAssert, info, 8, 7);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
  }

  /**
   * This method should fail because the number of rows is different.
   */
  @Test
  public void should_fail_because_number_of_rows_is_less_than_or_equal() {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    try {
      AssertionsOnNumberOfRows.hasNumberOfRowsGreaterThan(tableAssert, info, 8, 8);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                                    + "Expecting size (number of rows) to be greater than :%n"
                                                                    + "   <8>%n"
                                                                    + "but was:%n"
                                                                    + "   <8>"));
    }
  }
}
