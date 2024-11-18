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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.db.api.TableAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Value;
import org.junit.Test;

/**
 * Tests on {@link AssertionsOnValuesNullity} class :
 * {@link AssertionsOnValuesNullity#hasOnlyNotNullValues(org.assertj.db.api.AbstractAssert, org.assertj.core.api.WritableAssertionInfo, java.util.List)} method.
 *
 * @author Régis Pouiller
 */
public class AssertionsOnValuesNullity_HasOnlyNotNullValues_Test extends AbstractTest {

  /**
   * This method tests the {@code hasOnlyNotNullValues} assertion method.
   */
  @Test
  public void test_has_only_not_null_values() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    TableAssert tableAssert = new TableAssert(null);
    List<Value> list = new ArrayList<>(Arrays.asList(getValue(null, "test"), getValue(null, 6)));
    TableAssert tableAssert2 = AssertionsOnValuesNullity.hasOnlyNotNullValues(tableAssert, info, list);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
  }

  /**
   * This method should fail because values contain null values.
   */
  @Test
  public void should_fail_because_column_contains_null_values() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    TableAssert tableAssert = new TableAssert(null);
    List<Value> list = new ArrayList<>(Arrays.asList(getValue(null, null), getValue(null, "test")));
    try {
      AssertionsOnValuesNullity.hasOnlyNotNullValues(tableAssert, info, list);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting to contain only not null:%n"
        + "but contains null at index: 0"));
    }
  }
}
