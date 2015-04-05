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
import org.junit.Test;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on {@link AssertionsOnColumnName} class :
 * {@link AssertionsOnColumnName#hasColumnName(org.assertj.db.api.AbstractAssert, org.assertj.core.api.WritableAssertionInfo, String, String)}  method.
 *
 * @author Régis Pouiller
 *
 */
public class AssertionsOnColumnName_HasColumnName_Test {

  /**
   * This method tests the {@code hasColumnName} assertion method.
   */
  @Test
  public void test_has_column_name() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    TableAssert tableAssert2 = AssertionsOnColumnName.hasColumnName(tableAssert, info, "test", "test");
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
  }

  /**
   * This method should fail because the column name is different.
   */
  @Test
  public void should_fail_because_column_name_is_different() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    try {
      AssertionsOnColumnName.hasColumnName(tableAssert, info, "test1", "test");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[description] \n"
                                                      + "Expecting :\n"
                                                      + "  \"test\"\n"
                                                      + "to be the name of the column but was:\n"
                                                      + "  \"test1\"");
    }
  }

  /**
   * This method should fail because the column name is null.
   */
  @Test
  public void should_fail_because_column_name_is_null() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    try {
      AssertionsOnColumnName.hasColumnName(tableAssert, info, "test", null);
      fail("An exception must be raised");
    } catch (NullPointerException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Column name must be not null");
    }
  }
}
