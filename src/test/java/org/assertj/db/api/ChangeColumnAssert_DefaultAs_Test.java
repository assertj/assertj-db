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
 * Test on default description for {@code Column} of {@code Change}.
 *
 * @author Régis Pouiller
 *
 */
public class ChangeColumnAssert_DefaultAs_Test extends AbstractTest {

  /**
   * This method tests the description of column.
   *
   * @throws IllegalAccessException
   * @throws IllegalArgumentException
   * @throws NoSuchFieldException
   * @throws SecurityException
   */
  @Test
  @NeedReload
  public void test_default_description_of_column() throws NoSuchFieldException, SecurityException,
          IllegalArgumentException, IllegalAccessException {

    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();
    ChangeColumnAssert assertion = assertThat(changes).change().column();

    Field field = AbstractAssert.class.getDeclaredField("info");
    field.setAccessible(true);
    WritableAssertionInfo info = (WritableAssertionInfo) field.get(assertion);

    assertThat(info.descriptionText()).isEqualTo("Column at index 0 of Change at index 0 of Changes on tables of 'sa/jdbc:h2:mem:test' source");
  }

  /**
   * This method tests the description of column at index.
   *
   * @throws IllegalAccessException
   * @throws IllegalArgumentException
   * @throws NoSuchFieldException
   * @throws SecurityException
   */
  @Test
  @NeedReload
  public void test_default_description_of_column_at_index() throws NoSuchFieldException, SecurityException,
          IllegalArgumentException, IllegalAccessException {

    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();
    ChangeColumnAssert assertion = assertThat(changes).change().column(2);

    Field field = AbstractAssert.class.getDeclaredField("info");
    field.setAccessible(true);
    WritableAssertionInfo info = (WritableAssertionInfo) field.get(assertion);

    assertThat(info.descriptionText()).isEqualTo("Column at index 2 of Change at index 0 of Changes on tables of 'sa/jdbc:h2:mem:test' source");
  }

  /**
   * This method tests the description of column with_name.
   *
   * @throws IllegalAccessException
   * @throws IllegalArgumentException
   * @throws NoSuchFieldException
   * @throws SecurityException
   */
  @Test
  @NeedReload
  public void test_default_description_of_column_with_name() throws NoSuchFieldException, SecurityException,
          IllegalArgumentException, IllegalAccessException {

    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();
    ChangeColumnAssert assertion = assertThat(changes).change().column("name");

    Field field = AbstractAssert.class.getDeclaredField("info");
    field.setAccessible(true);
    WritableAssertionInfo info = (WritableAssertionInfo) field.get(assertion);

    assertThat(info.descriptionText()).isEqualTo("Column at index 1 of Change at index 0 of Changes on tables of 'sa/jdbc:h2:mem:test' source");
  }
}

