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

import java.lang.reflect.Field;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;

/**
 * Test on the assertion methods on row of {@code Change}.
 *
 * @author Régis Pouiller
 */
public class ChangeAssert_Row_Test extends AbstractTest {

  /**
   * This method tests the {@code rowAtStartPoint} and {@code rowAtEndPoint} methods.
   *
   * @throws IllegalAccessException
   * @throws IllegalArgumentException
   * @throws NoSuchFieldException
   * @throws SecurityException
   */
  @Test
  @NeedReload
  public void test_when_get_rows()
          throws IllegalArgumentException, IllegalAccessException, SecurityException, NoSuchFieldException {

    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();
    ChangesAssert assertion = assertThat(changes);

    Field field = ChangeRowAssert.class.getDeclaredField("row");
    field.setAccessible(true);

    ChangeAssert changeAssert0 = assertion.change();
    changeAssert0.isCreation();
    assertThat(field.get(changeAssert0.rowAtStartPoint())).isNull();
    assertThat(field.get(changeAssert0.rowAtEndPoint())).isSameAs(changes.getChangesList().get(0).getRowAtEndPoint());

    ChangeAssert changeAssert3 = assertion.change(3);
    assertThat(field.get(changeAssert3.rowAtStartPoint())).isSameAs(
            changes.getChangesList().get(3).getRowAtStartPoint());
    assertThat(field.get(changeAssert3.rowAtEndPoint())).isSameAs(changes.getChangesList().get(3).getRowAtEndPoint());

    ChangeAssert changeAssert7 = assertion.change(7);
    assertThat(field.get(changeAssert7.rowAtStartPoint())).isSameAs(
            changes.getChangesList().get(7).getRowAtStartPoint());
    assertThat(field.get(changeAssert7.rowAtEndPoint())).isNull();
  }
}
