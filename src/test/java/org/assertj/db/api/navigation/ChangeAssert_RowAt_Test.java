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
package org.assertj.db.api.navigation;

import org.assertj.db.api.ChangeAssert;
import org.assertj.db.api.ChangeRowValueAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.assertj.db.type.Changes;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;

/**
 * Test on {@code rowAtStartPoint()} and {@code rowAtEndPoint()} methods.
 *
 * @author Régis Pouiller
 */
public class ChangeAssert_RowAt_Test extends AbstractTest {

  /**
   * This method test the {@code rowAtStartPoint()} of {@code Change}.
   */
  @Test
  @NeedReload
  public void test_rowAtStartPoint_methods() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    ChangeAssert changeAssert = assertThat(changes).changeOfModification();
    ChangeRowValueAssert changeRowValueAssert = changeAssert.rowAtStartPoint().value();

    assertThat(changeAssert.rowAtStartPoint()).as("changeAssert.rowAtStartPoint()")
                                   .isSameAs(changeRowValueAssert.rowAtStartPoint());
  }

  /**
   * This method test the {@code rowAtEndPoint()} of {@code Change}.
   */
  @Test
  @NeedReload
  public void test_rowAtEndPoint_methods() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    ChangeAssert changeAssert = assertThat(changes).changeOfModification();
    ChangeRowValueAssert changeRowValueAssert = changeAssert.rowAtStartPoint().value();

    assertThat(changeAssert.rowAtEndPoint()).as("changeAssert.rowAtEndPoint()")
                                              .isSameAs(changeRowValueAssert.rowAtEndPoint());
  }
}
