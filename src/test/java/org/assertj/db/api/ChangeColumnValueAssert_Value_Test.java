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
package org.assertj.db.api;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.assertj.db.type.Changes;
import org.junit.Test;

import static org.assertj.db.api.Assertions.assertThat;

/**
 * Test on the assertion methods on value of column of {@code Change}.
 *
 * @author Régis Pouiller
 */
public class ChangeColumnValueAssert_Value_Test extends AbstractTest {

  /**
   * This method test the {@code valueAtStartPoint()} of {@code Column} of {@code Change}.
   */
  @Test
  @NeedReload
  public void test_value_methods() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    ChangeColumnAssert changeColumnAssert = assertThat(changes).changeOfModification().column();
    ChangeColumnValueAssert changeColumnValueAssert = changeColumnAssert.valueAtStartPoint();

    org.assertj.core.api.Assertions.assertThat(changeColumnValueAssert.valueAtStartPoint()).as("changeColumnValueAssert.valueAtStartPoint()")
                                   .isSameAs(changeColumnAssert.valueAtStartPoint());
  }

  /**
   * This method test the {@code valueAtEndPoint()} of {@code Column} of {@code Change}.
   */
  @Test
  @NeedReload
  public void test_value_index_methods() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    ChangeColumnAssert changeColumnAssert = assertThat(changes).changeOfModification().column();
    ChangeColumnValueAssert changeColumnValueAssert = changeColumnAssert.valueAtEndPoint();

    org.assertj.core.api.Assertions.assertThat(changeColumnValueAssert.valueAtEndPoint()).as("changeColumnValueAssert.valueAtEndPoint()")
                                   .isSameAs(changeColumnAssert.valueAtEndPoint());
  }
}
