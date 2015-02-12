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

import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.assertj.db.type.Changes;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;

/**
 * Test on default description for value of {@code Column} of {@code Change}.
 *
 * @author Régis Pouiller
 */
public class ChangeColumnValueAssert_DefaultAs_Test extends AbstractTest {

  /**
   * This method tests the description of value.
   *
   * @throws IllegalAccessException
   * @throws IllegalArgumentException
   * @throws NoSuchFieldException
   * @throws SecurityException
   */
  @Test
  @NeedReload
  public void test_default_description_of_value()
          throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {

    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();
    ChangeColumnValueAssert assertion = assertThat(changes).changeOfDeletion().column().valueAtStartPoint();

    Field field = AbstractAssert.class.getDeclaredField("info");
    field.setAccessible(true);
    WritableAssertionInfo info = (WritableAssertionInfo) field.get(assertion);

    assertThat(info.descriptionText()).isEqualTo("Value at start point of Column at index 0 of Change at index 0 of Changes on tables of 'sa/jdbc:h2:mem:test' source (only deletion changes)");
  }

  /**
   * This method tests the description of value with index.
   *
   * @throws IllegalAccessException
   * @throws IllegalArgumentException
   * @throws NoSuchFieldException
   * @throws SecurityException
   */
  @Test
  @NeedReload
  public void test_default_description_of_value_with_index()
          throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {

    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();
    ChangeColumnValueAssert assertion = assertThat(changes).change().column(2).valueAtEndPoint();

    Field field = AbstractAssert.class.getDeclaredField("info");
    field.setAccessible(true);
    WritableAssertionInfo info = (WritableAssertionInfo) field.get(assertion);

    assertThat(info.descriptionText()).isEqualTo("Value at end point of Column at index 2 of Change at index 0 of Changes on tables of 'sa/jdbc:h2:mem:test' source");
  }

}

