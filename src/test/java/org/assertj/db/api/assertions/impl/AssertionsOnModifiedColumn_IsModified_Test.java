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

import static org.junit.Assert.fail;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.db.api.TableAssert;
import org.assertj.db.common.AbstractTest;
import org.junit.Test;

/**
 * Tests on {@link AssertionsOnModifiedColumn} class :
 * {@link AssertionsOnModifiedColumn#isModified(org.assertj.db.api.AbstractAssert, org.assertj.core.api.WritableAssertionInfo, Object, Object)} method.
 *
 * @author Régis Pouiller
 */
public class AssertionsOnModifiedColumn_IsModified_Test extends AbstractTest {

  /**
   * This method tests the {@code isModified} assertion method.
   */
  @Test
  public void test_is_modified() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    TableAssert tableAssert = new TableAssert(null);
    TableAssert tableAssert2 = AssertionsOnModifiedColumn.isModified(tableAssert, info, getValue(null, null), getValue(
      null, "test"));
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    tableAssert2 = AssertionsOnModifiedColumn.isModified(tableAssert, info, getValue(null, "test"), getValue(null, null));
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    tableAssert2 = AssertionsOnModifiedColumn.isModified(tableAssert, info, getValue(null, "test"), getValue(null,
      "test1"));
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
  }

  /**
   * This method should fail because the column is not modified.
   */
  @Test
  public void should_fail_because_column_is_not_modified() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    TableAssert tableAssert = new TableAssert(null);
    try {
      AssertionsOnModifiedColumn.isModified(tableAssert, info, getValue(null, null), getValue(null, null));
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting :%n"
        + "  <null>%n"
        + "is modified but is still:%n"
        + "  <null>"));
    }
    try {
      AssertionsOnModifiedColumn.isModified(tableAssert, info, getValue(null, "test"), getValue(null, "test"));
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting :%n"
        + "  <\"test\">%n"
        + "is modified but is still:%n"
        + "  <\"test\">"));
    }
  }
}
