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
package org.assertj.db.api.assertions;

import org.assertj.core.api.Assertions;
import org.assertj.db.api.ChangeRowAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.assertj.db.type.Changes;
import org.junit.Test;

import static org.assertj.db.api.Assertions.assertThat;

/**
 * Tests on {@link org.assertj.db.api.assertions.AssertOnRowOfChangeExistence} class :
 * {@link org.assertj.db.api.assertions.AssertOnRowOfChangeExistence#exists()} method.
 *
 * @author Régis Pouiller
 *
 */
public class AssertOnRowOfChangeExistence_Exists_Test extends AbstractTest {

  /**
   * This method tests the {@code exists} assertion method.
   */
  @Test
  @NeedReload
  public void test_exists() throws Exception {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    ChangeRowAssert changeRowAssert = assertThat(changes).change().rowAtEndPoint();
    ChangeRowAssert changeRowAssert2 = changeRowAssert.exists();
    Assertions.assertThat(changeRowAssert).isSameAs(changeRowAssert2);
  }

  /**
   * This method should fail because the row does not exist.
   */
  @Test
  @NeedReload
  public void should_fail_because_row_does_not_exist() throws Exception {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    try {
      assertThat(changes).change().rowAtStartPoint().exists();
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[Row at start point of Change at index 0 (on table : ACTOR and with primary key : [4]) of Changes on tables of 'sa/jdbc:h2:mem:test' source] %n"
                                                      + "Expecting exist but do not exist"));
    }
  }
}
