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
import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Table;
import org.junit.Test;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on {@link AssertionsOnModifiedColumn} class :
 * {@link AssertionsOnModifiedColumn#isNotModified(org.assertj.db.api.AbstractAssert, org.assertj.core.api.WritableAssertionInfo, org.assertj.db.type.Value, org.assertj.db.type.Value)} method.
 *
 * @author Régis Pouiller
 *
 */
public class AssertionsOnModifiedColumn_IsNotModified_Test extends AbstractTest {

  /**
   * This method tests the {@code isNotModified} assertion method.
   */
  @Test
  public void test_is_not_modified() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    TableAssert tableAssert2 = AssertionsOnModifiedColumn.isNotModified(tableAssert, info, getValue(null, null), getValue(
            null, null));
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    tableAssert2 = AssertionsOnModifiedColumn.isNotModified(tableAssert, info, getValue(null, "test"), getValue(null,
                                                                                                                "test"));
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
  }

  /**
   * This method should fail because the column is modified.
   */
  @Test
  public void should_fail_because_column_is_modified() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    try {
      AssertionsOnModifiedColumn.isNotModified(tableAssert, info, getValue(null, null), getValue(null, "test"));
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                      + "Expecting :%n"
                                                      + "  <null>%n"
                                                      + "is not modified but is :%n"
                                                      + "  <\"test\">"));
    }
    try {
      AssertionsOnModifiedColumn.isNotModified(tableAssert, info, getValue(null, "test"), getValue(null, null));
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                      + "Expecting :%n"
                                                      + "  <\"test\">%n"
                                                      + "is not modified but is :%n"
                                                      + "  <null>"));
    }
    try {
      AssertionsOnModifiedColumn.isNotModified(tableAssert, info, getValue(null, "test"), getValue(null, "test1"));
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                      + "Expecting :%n"
                                                      + "  <\"test\">%n"
                                                      + "is not modified but is :%n"
                                                      + "  <\"test1\">"));
    }
  }
}
