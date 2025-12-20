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
import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Change;
import org.assertj.db.type.ChangeType;
import org.assertj.db.type.DataType;
import org.assertj.db.type.Row;
import org.junit.Test;

/**
 * Tests on {@link AssertionsOnChangeType} class :
 * {@link AssertionsOnChangeType#isDeletion(org.assertj.db.api.AbstractAssert, org.assertj.core.api.WritableAssertionInfo, org.assertj.db.type.Change)}} method.
 *
 * @author Régis Pouiller
 */
public class AssertionsOnChangeType_IsDeletion_Test extends AbstractTest {

  /**
   * This method tests the {@code isDeletion} assertion method.
   */
  @Test
  public void test_is_deletion() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    TableAssert tableAssert = new TableAssert(null);
    Row rowAtStartPoint = getRow(null, null, null);
    Row rowAtEndPoint = getRow(null, null, null);
    Change change = getChange(DataType.TABLE, "test", ChangeType.DELETION, rowAtStartPoint, rowAtEndPoint);
    TableAssert tableAssert2 = AssertionsOnChangeType.isDeletion(tableAssert, info, change);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
  }

  /**
   * This method should fail because the type of change is different.
   */
  @Test
  public void should_fail_because_type_of_change_is_different() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    TableAssert tableAssert = new TableAssert(null);
    Row rowAtStartPoint = getRow(null, null, null);
    Row rowAtEndPoint = getRow(null, null, null);
    Change change = getChange(DataType.TABLE, "test", ChangeType.CREATION, rowAtStartPoint, rowAtEndPoint);
    try {
      AssertionsOnChangeType.isDeletion(tableAssert, info, change);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting:%n"
        + "to be of type%n"
        + "  <DELETION>%n"
        + "but was of type%n"
        + "  <CREATION>"));
    }
  }
}
