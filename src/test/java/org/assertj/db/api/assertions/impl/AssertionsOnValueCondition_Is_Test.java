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
import org.assertj.core.api.Condition;
import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.db.api.TableAssert;
import org.assertj.db.common.AbstractTest;
import org.junit.Test;

/**
 * Tests on {@link  AssertionsOnValueCondition} class :
 * {@link  AssertionsOnValueCondition#is(org.assertj.db.api.AbstractAssert, WritableAssertionInfo, org.assertj.db.type.Value, org.assertj.core.api.Condition)} method.
 *
 * @author Julien Roy
 */
public class AssertionsOnValueCondition_Is_Test extends AbstractTest {

  private Condition<Integer> zero = new Condition<Integer>("isZero") {
    @Override
    public boolean matches(Integer value) {
      return value == 0;
    }
  };

  /**
   * This method tests the {@code is} assertion method.
   */
  @Test
  public void test_is() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    TableAssert tableAssert = new TableAssert(null);
    TableAssert tableAssert2 = AssertionsOnValueCondition.is(tableAssert, info, getValue(null, 0), zero);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
  }

  /**
   * This method should fail because the value is not zero.
   */
  @Test
  public void should_fail_because_value_not_satisfies_condition() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    TableAssert tableAssert = new TableAssert(null);
    try {
      AssertionsOnValueCondition.is(tableAssert, info, getValue(null, 1), zero);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting actual:%n"
        + "  1%n"
        + "to be isZero"));
    }
  }
}
