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
 * Copyright 2012-2016 the original author or authors.
 */
package org.assertj.db.api.assertions.impl;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;
import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.db.api.TableAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Table;
import org.junit.Test;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on {@link  AssertionsOnValueCondition} class :
 * {@link  AssertionsOnValueCondition#isNot(org.assertj.db.api.AbstractAssert, WritableAssertionInfo, org.assertj.db.type.Value, Condition)} method.
 *
 * @author Julien Roy
 *
 */
public class AssertionsOnValueCondition_IsNot_Test extends AbstractTest {

  private Condition<Integer> zero = new Condition<Integer>("isZero") {
    @Override
    public boolean matches(Integer value) {
      return value == 0;
    }
  };

  /**
   * This method tests the {@code isNot} assertion method.
   */
  @Test
  public void test_is_not() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    TableAssert tableAssert2 = AssertionsOnValueCondition.isNot(tableAssert, info, getValue(null, 1), zero);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
  }

  /**
   * This method should fail because the value is zero.
   */
  @Test
  public void should_fail_because_value_not_satisfies_condition() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    try {
      AssertionsOnValueCondition.isNot(tableAssert, info, getValue(null, 0), zero);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                      + "Expecting:%n"
                                                      + " <0>%n"
                                                      + "not to be <isZero>"));
    }
  }
}
