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
 * Test on default description for {@code Change}.
 *
 * @author Régis Pouiller
 *
 */
public class ChangeAssert_DefaultAs_Test extends AbstractTest {

  /**
   * This method tests the description of change.
   *
   * @throws IllegalAccessException
   * @throws IllegalArgumentException
   * @throws NoSuchFieldException
   * @throws SecurityException
   */
  @Test
  @NeedReload
  public void test_default_description_of_change() throws NoSuchFieldException, SecurityException,
          IllegalArgumentException, IllegalAccessException {

    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();
    ChangeAssert assertion = assertThat(changes).change();

    Field field = AbstractAssert.class.getDeclaredField("info");
    field.setAccessible(true);
    WritableAssertionInfo info = (WritableAssertionInfo) field.get(assertion);

    assertThat(info.descriptionText()).isEqualTo("Change at index 0 of Changes on tables of 'sa/jdbc:h2:mem:test' source");
  }

  /**
   * This method tests the description of change with index.
   *
   * @throws IllegalAccessException
   * @throws IllegalArgumentException
   * @throws NoSuchFieldException
   * @throws SecurityException
   */
  @Test
  @NeedReload
  public void test_default_description_of_change_with_index() throws NoSuchFieldException, SecurityException,
          IllegalArgumentException, IllegalAccessException {

    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();
    ChangeAssert assertion = assertThat(changes).change(2);

    Field field = AbstractAssert.class.getDeclaredField("info");
    field.setAccessible(true);
    WritableAssertionInfo info = (WritableAssertionInfo) field.get(assertion);

    assertThat(info.descriptionText()).isEqualTo("Change at index 2 of Changes on tables of 'sa/jdbc:h2:mem:test' source");
  }

  /**
   * This method tests the description of change from creation changes.
   *
   * @throws IllegalAccessException
   * @throws IllegalArgumentException
   * @throws NoSuchFieldException
   * @throws SecurityException
   */
  @Test
  @NeedReload
  public void test_default_description_of_change_from_creation() throws NoSuchFieldException, SecurityException,
          IllegalArgumentException, IllegalAccessException {

    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();
    ChangeAssert assertion = assertThat(changes).changeOfCreation();

    Field field = AbstractAssert.class.getDeclaredField("info");
    field.setAccessible(true);
    WritableAssertionInfo info = (WritableAssertionInfo) field.get(assertion);

    assertThat(info.descriptionText()).isEqualTo("Change at index 0 of Changes on tables of 'sa/jdbc:h2:mem:test' source (only creation changes)");
  }

  /**
   * This method tests the description of change from modification changes.
   *
   * @throws IllegalAccessException
   * @throws IllegalArgumentException
   * @throws NoSuchFieldException
   * @throws SecurityException
   */
  @Test
  @NeedReload
  public void test_default_description_of_change_from_modification() throws NoSuchFieldException, SecurityException,
          IllegalArgumentException, IllegalAccessException {

    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();
    ChangeAssert assertion = assertThat(changes).changeOfModification();

    Field field = AbstractAssert.class.getDeclaredField("info");
    field.setAccessible(true);
    WritableAssertionInfo info = (WritableAssertionInfo) field.get(assertion);

    assertThat(info.descriptionText()).isEqualTo("Change at index 0 of Changes on tables of 'sa/jdbc:h2:mem:test' source (only modification changes)");
  }

  /**
   * This method tests the description of change from deletion changes.
   *
   * @throws IllegalAccessException
   * @throws IllegalArgumentException
   * @throws NoSuchFieldException
   * @throws SecurityException
   */
  @Test
  @NeedReload
  public void test_default_description_of_change_from_deletion() throws NoSuchFieldException, SecurityException,
          IllegalArgumentException, IllegalAccessException {

    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();
    ChangeAssert assertion = assertThat(changes).changeOfDeletion();

    Field field = AbstractAssert.class.getDeclaredField("info");
    field.setAccessible(true);
    WritableAssertionInfo info = (WritableAssertionInfo) field.get(assertion);

    assertThat(info.descriptionText()).isEqualTo("Change at index 0 of Changes on tables of 'sa/jdbc:h2:mem:test' source (only deletion changes)");
  }

  /**
   * This method tests the description of change from creation changes with index.
   *
   * @throws IllegalAccessException
   * @throws IllegalArgumentException
   * @throws NoSuchFieldException
   * @throws SecurityException
   */
  @Test
  @NeedReload
  public void test_default_description_of_change_from_creation_with_index() throws NoSuchFieldException, SecurityException,
          IllegalArgumentException, IllegalAccessException {

    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();
    ChangeAssert assertion = assertThat(changes).changeOfCreation(1);

    Field field = AbstractAssert.class.getDeclaredField("info");
    field.setAccessible(true);
    WritableAssertionInfo info = (WritableAssertionInfo) field.get(assertion);

    assertThat(info.descriptionText()).isEqualTo("Change at index 1 of Changes on tables of 'sa/jdbc:h2:mem:test' source (only creation changes)");
  }

  /**
   * This method tests the description of change from modification changes with index.
   *
   * @throws IllegalAccessException
   * @throws IllegalArgumentException
   * @throws NoSuchFieldException
   * @throws SecurityException
   */
  @Test
  @NeedReload
  public void test_default_description_of_change_from_modification_with_index() throws NoSuchFieldException, SecurityException,
          IllegalArgumentException, IllegalAccessException {

    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();
    ChangeAssert assertion = assertThat(changes).changeOfModification(1);

    Field field = AbstractAssert.class.getDeclaredField("info");
    field.setAccessible(true);
    WritableAssertionInfo info = (WritableAssertionInfo) field.get(assertion);

    assertThat(info.descriptionText()).isEqualTo("Change at index 1 of Changes on tables of 'sa/jdbc:h2:mem:test' source (only modification changes)");
  }

  /**
   * This method tests the description of change from deletion changes with index.
   *
   * @throws IllegalAccessException
   * @throws IllegalArgumentException
   * @throws NoSuchFieldException
   * @throws SecurityException
   */
  @Test
  @NeedReload
  public void test_default_description_of_change_from_deletion_with_index() throws NoSuchFieldException, SecurityException,
          IllegalArgumentException, IllegalAccessException {

    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();
    ChangeAssert assertion = assertThat(changes).changeOfDeletion(1);

    Field field = AbstractAssert.class.getDeclaredField("info");
    field.setAccessible(true);
    WritableAssertionInfo info = (WritableAssertionInfo) field.get(assertion);

    assertThat(info.descriptionText()).isEqualTo("Change at index 1 of Changes on tables of 'sa/jdbc:h2:mem:test' source (only deletion changes)");
  }
}
