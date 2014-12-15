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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;

/**
 * Test on the assertion methods on change of {@code Change}.
 *
 * @author Régis Pouiller
 *
 */
public class ChangeAssert_Change_Test extends AbstractTest {

  /**
   * This method tests the {@code change} method when using without parameter.
   *
   * @throws IllegalAccessException
   * @throws IllegalArgumentException
   * @throws NoSuchFieldException
   * @throws SecurityException
   */
  @Test
  @NeedReload
  public void test_when_get_change_without_parameters() throws IllegalArgumentException, IllegalAccessException,
          SecurityException, NoSuchFieldException {

    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();
    ChangesAssert changesAssert = assertThat(changes);
    ChangeAssert changeAssert = changesAssert.change();

    assertThat(changeAssert).isSameAs(changesAssert.change(0));
    assertThat(changeAssert.change()).isSameAs(changesAssert.change(1));
    assertThat(changeAssert.change()).isSameAs(changesAssert.change(2));
  }

  /**
   * This method tests the {@code change} method when using with {@code index} parameter.
   *
   * @throws IllegalAccessException
   * @throws IllegalArgumentException
   * @throws NoSuchFieldException
   * @throws SecurityException
   */
  @Test
  @NeedReload
  public void test_when_get_changes_with_parameter() throws IllegalArgumentException, IllegalAccessException,
          SecurityException, NoSuchFieldException {

    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();
    ChangesAssert changesAssert = assertThat(changes);
    ChangeAssert changeAssert = changesAssert.change();

    assertThat(changeAssert).isSameAs(changesAssert.change(0));
    assertThat(changeAssert.change(1)).isSameAs(changesAssert.change(1));
    assertThat(changeAssert.change(2)).isSameAs(changesAssert.change(2));
  }
}
