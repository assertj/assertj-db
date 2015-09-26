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
 * Copyright 2012-2015 the original author or authors.
 */
package org.assertj.db.api;

import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.assertj.db.global.AbstractElement;
import org.assertj.db.type.Changes;
import org.assertj.db.type.Request;
import org.assertj.db.type.Table;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;

/**
 * Test on default descriptions.
 *
 * @author Régis Pouiller
 *
 */
public class DefaultDescription_Test extends AbstractTest {

  /**
   * This method tests the description of change with different informations.
   */
  @Test
  @NeedReload
  public void test_default_description_for_change_with_different_informations() throws Exception {
    Field field = AbstractElement.class.getDeclaredField("info");
    field.setAccessible(true);


    Table actorTable = new Table(dataSource, "actor");
    Request request = new Request(dataSource, "select * from actor");
    Changes changes = new Changes(dataSource).setStartPointNow();
    Changes changesOnActorTable = new Changes(actorTable).setStartPointNow();
    Changes changesOnRequest = new Changes(request).setStartPointNow();
    updateChangesForTests();
    update("delete from test2 where var1 = 1");
    changes.setEndPointNow();
    changesOnActorTable.setEndPointNow();
    changesOnRequest.setEndPointNow();


    WritableAssertionInfo info = (WritableAssertionInfo) field.get(assertThat(changes).change());
    WritableAssertionInfo infoOnActorTable = (WritableAssertionInfo) field.get(assertThat(changesOnActorTable).change());
    WritableAssertionInfo infoOnTestTable = (WritableAssertionInfo) field.get(assertThat(changes).change(8));
    WritableAssertionInfo infoOnRequest = (WritableAssertionInfo) field.get(assertThat(changesOnRequest).change());
    assertThat(info.descriptionText()).isEqualTo("Change at index 0 (on table : ACTOR and with primary key : [4]) of Changes on tables of a data source");
    assertThat(infoOnActorTable.descriptionText()).isEqualTo("Change at index 0 (with primary key : [4]) of Changes on actor table of a data source");
    assertThat(infoOnTestTable.descriptionText()).isEqualTo("Change at index 8 (on table : TEST2) of Changes on tables of a data source");
    assertThat(infoOnRequest.descriptionText()).isEqualTo("Change at index 0 of Changes on 'select * from actor' request of a data source");
  }

  /**
   * This method tests the description of changes.
   */
  @Test
  @NeedReload
  public void test_default_description_of_changes() throws Exception {
    Field field = AbstractElement.class.getDeclaredField("info");
    field.setAccessible(true);


    Changes changesFromSource = new Changes(source).setStartPointNow();
    Changes changesFromDataSource = new Changes(dataSource).setStartPointNow();
    updateChangesForTests();
    changesFromSource.setEndPointNow();
    changesFromDataSource.setEndPointNow();


    ChangesAssert assertionFromSource = assertThat(changesFromSource);
    ChangesAssert assertionFromDataSource = assertThat(changesFromDataSource);


    WritableAssertionInfo infoFromSource = (WritableAssertionInfo) field.get(assertionFromSource);
    assertThat(infoFromSource.descriptionText()).isEqualTo("Changes on tables of 'sa/jdbc:h2:mem:test' source");

    WritableAssertionInfo infoFromDataSource = (WritableAssertionInfo) field.get(assertionFromDataSource);
    assertThat(infoFromDataSource.descriptionText()).isEqualTo("Changes on tables of a data source");
  }

  /**
   * This method tests the description of all changes.
   */
  @Test
  @NeedReload
  public void test_default_description_of_all_changes() throws Exception {
    Field field = AbstractElement.class.getDeclaredField("info");
    field.setAccessible(true);


    Changes changesFromSource = new Changes(source).setStartPointNow();
    Changes changesFromDataSource = new Changes(dataSource).setStartPointNow();
    updateChangesForTests();
    changesFromSource.setEndPointNow();
    changesFromDataSource.setEndPointNow();


    ChangesAssert assertionFromSource = assertThat(changesFromSource).ofAll();
    ChangesAssert assertionFromDataSource = assertThat(changesFromDataSource).ofAll();


    WritableAssertionInfo infoFromSource = (WritableAssertionInfo) field.get(assertionFromSource);
    assertThat(infoFromSource.descriptionText()).isEqualTo("Changes on tables of 'sa/jdbc:h2:mem:test' source");

    WritableAssertionInfo infoFromDataSource = (WritableAssertionInfo) field.get(assertionFromDataSource);
    assertThat(infoFromDataSource.descriptionText()).isEqualTo("Changes on tables of a data source");
  }

  /**
   * This method tests the description of creation changes.
   */
  @Test
  @NeedReload
  public void test_default_description_of_creation_changes() throws Exception {
    Field field = AbstractElement.class.getDeclaredField("info");
    field.setAccessible(true);


    Changes changesFromSource = new Changes(source).setStartPointNow();
    Changes changesFromDataSource = new Changes(dataSource).setStartPointNow();
    updateChangesForTests();
    changesFromSource.setEndPointNow();
    changesFromDataSource.setEndPointNow();


    ChangesAssert assertionFromSource = assertThat(changesFromSource).ofCreation();
    ChangesAssert assertionFromDataSource = assertThat(changesFromDataSource).ofCreation();


    WritableAssertionInfo infoFromSource = (WritableAssertionInfo) field.get(assertionFromSource);
    assertThat(infoFromSource.descriptionText()).isEqualTo("Changes on tables of 'sa/jdbc:h2:mem:test' source (only creation changes)");

    WritableAssertionInfo infoFromDataSource = (WritableAssertionInfo) field.get(assertionFromDataSource);
    assertThat(infoFromDataSource.descriptionText()).isEqualTo("Changes on tables of a data source (only creation changes)");
  }

  /**
   * This method tests the description of modification changes.
   */
  @Test
  @NeedReload
  public void test_default_description_of_modification_changes() throws Exception {
    Field field = AbstractElement.class.getDeclaredField("info");
    field.setAccessible(true);


    Changes changesFromSource = new Changes(source).setStartPointNow();
    Changes changesFromDataSource = new Changes(dataSource).setStartPointNow();
    updateChangesForTests();
    changesFromSource.setEndPointNow();
    changesFromDataSource.setEndPointNow();


    ChangesAssert assertionFromSource = assertThat(changesFromSource).ofModification();
    ChangesAssert assertionFromDataSource = assertThat(changesFromDataSource).ofModification();


    WritableAssertionInfo infoFromSource = (WritableAssertionInfo) field.get(assertionFromSource);
    assertThat(infoFromSource.descriptionText()).isEqualTo("Changes on tables of 'sa/jdbc:h2:mem:test' source (only modification changes)");

    WritableAssertionInfo infoFromDataSource = (WritableAssertionInfo) field.get(assertionFromDataSource);
    assertThat(infoFromDataSource.descriptionText()).isEqualTo("Changes on tables of a data source (only modification changes)");
  }

  /**
   * This method tests the description of deletion changes.
   */
  @Test
  @NeedReload
  public void test_default_description_of_deletion_changes() throws Exception {
    Field field = AbstractElement.class.getDeclaredField("info");
    field.setAccessible(true);


    Changes changesFromSource = new Changes(source).setStartPointNow();
    Changes changesFromDataSource = new Changes(dataSource).setStartPointNow();
    updateChangesForTests();
    changesFromSource.setEndPointNow();
    changesFromDataSource.setEndPointNow();


    ChangesAssert assertionFromSource = assertThat(changesFromSource).ofDeletion();
    ChangesAssert assertionFromDataSource = assertThat(changesFromDataSource).ofDeletion();


    WritableAssertionInfo infoFromSource = (WritableAssertionInfo) field.get(assertionFromSource);
    assertThat(infoFromSource.descriptionText()).isEqualTo("Changes on tables of 'sa/jdbc:h2:mem:test' source (only deletion changes)");

    WritableAssertionInfo infoFromDataSource = (WritableAssertionInfo) field.get(assertionFromDataSource);
    assertThat(infoFromDataSource.descriptionText()).isEqualTo("Changes on tables of a data source (only deletion changes)");
  }

  /**
   * This method tests the description of creation on a table changes.
   */
  @Test
  @NeedReload
  public void test_default_description_of_creation_on_table_changes() throws Exception {
    Field field = AbstractElement.class.getDeclaredField("info");
    field.setAccessible(true);


    Changes changesFromSource = new Changes(source).setStartPointNow();
    Changes changesFromDataSource = new Changes(dataSource).setStartPointNow();
    updateChangesForTests();
    changesFromSource.setEndPointNow();
    changesFromDataSource.setEndPointNow();


    ChangesAssert assertionFromSource = assertThat(changesFromSource).ofCreationOnTable("actor");
    ChangesAssert assertionFromDataSource = assertThat(changesFromDataSource).ofCreationOnTable("actor");


    WritableAssertionInfo infoFromSource = (WritableAssertionInfo) field.get(assertionFromSource);
    assertThat(infoFromSource.descriptionText()).isEqualTo("Changes on tables of 'sa/jdbc:h2:mem:test' source (only creation changes on actor table)");

    WritableAssertionInfo infoFromDataSource = (WritableAssertionInfo) field.get(assertionFromDataSource);
    assertThat(infoFromDataSource.descriptionText()).isEqualTo("Changes on tables of a data source (only creation changes on actor table)");
  }

  /**
   * This method tests the description of modification on a table changes.
   */
  @Test
  @NeedReload
  public void test_default_description_of_modification_on_table_changes() throws Exception {
    Field field = AbstractElement.class.getDeclaredField("info");
    field.setAccessible(true);


    Changes changesFromSource = new Changes(source).setStartPointNow();
    Changes changesFromDataSource = new Changes(dataSource).setStartPointNow();
    updateChangesForTests();
    changesFromSource.setEndPointNow();
    changesFromDataSource.setEndPointNow();


    ChangesAssert assertionFromSource = assertThat(changesFromSource).ofModificationOnTable("actor");
    ChangesAssert assertionFromDataSource = assertThat(changesFromDataSource).ofModificationOnTable("actor");


    WritableAssertionInfo infoFromSource = (WritableAssertionInfo) field.get(assertionFromSource);
    assertThat(infoFromSource.descriptionText()).isEqualTo("Changes on tables of 'sa/jdbc:h2:mem:test' source (only modification changes on actor table)");

    WritableAssertionInfo infoFromDataSource = (WritableAssertionInfo) field.get(assertionFromDataSource);
    assertThat(infoFromDataSource.descriptionText()).isEqualTo("Changes on tables of a data source (only modification changes on actor table)");
  }

  /**
   * This method tests the description of deletion on a table changes.
   */
  @Test
  @NeedReload
  public void test_default_description_of_deletion_on_table_changes() throws Exception {
    Field field = AbstractElement.class.getDeclaredField("info");
    field.setAccessible(true);


    Changes changesFromSource = new Changes(source).setStartPointNow();
    Changes changesFromDataSource = new Changes(dataSource).setStartPointNow();
    updateChangesForTests();
    changesFromSource.setEndPointNow();
    changesFromDataSource.setEndPointNow();


    ChangesAssert assertionFromSource = assertThat(changesFromSource).ofDeletionOnTable("actor");
    ChangesAssert assertionFromDataSource = assertThat(changesFromDataSource).ofDeletionOnTable("actor");


    WritableAssertionInfo infoFromSource = (WritableAssertionInfo) field.get(assertionFromSource);
    assertThat(infoFromSource.descriptionText()).isEqualTo("Changes on tables of 'sa/jdbc:h2:mem:test' source (only deletion changes on actor table)");

    WritableAssertionInfo infoFromDataSource = (WritableAssertionInfo) field.get(assertionFromDataSource);
    assertThat(infoFromDataSource.descriptionText()).isEqualTo("Changes on tables of a data source (only deletion changes on actor table)");
  }

  /**
   * This method tests the description of creation on a table changes.
   */
  @Test
  @NeedReload
  public void test_default_description_on_table_changes() throws Exception {
    Field field = AbstractElement.class.getDeclaredField("info");
    field.setAccessible(true);


    Changes changesFromSource = new Changes(source).setStartPointNow();
    Changes changesFromDataSource = new Changes(dataSource).setStartPointNow();
    updateChangesForTests();
    changesFromSource.setEndPointNow();
    changesFromDataSource.setEndPointNow();


    ChangesAssert assertionFromSource = assertThat(changesFromSource).onTable("actor");
    ChangesAssert assertionFromDataSource = assertThat(changesFromDataSource).onTable("actor");


    WritableAssertionInfo infoFromSource = (WritableAssertionInfo) field.get(assertionFromSource);
    assertThat(infoFromSource.descriptionText()).isEqualTo("Changes on tables of 'sa/jdbc:h2:mem:test' source (only changes on actor table)");

    WritableAssertionInfo infoFromDataSource = (WritableAssertionInfo) field.get(assertionFromDataSource);
    assertThat(infoFromDataSource.descriptionText()).isEqualTo("Changes on tables of a data source (only changes on actor table)");
  }

  /**
   * This method tests the description of changes on table.
   */
  @Test
  @NeedReload
  public void test_default_description_of_changes_on_table() throws Exception {
    Field field = AbstractElement.class.getDeclaredField("info");
    field.setAccessible(true);


    Table tableFromSource = new Table(source, "actor");
    Table tableFromDataSource = new Table(dataSource, "actor");
    Changes changesFromSource = new Changes(tableFromSource).setStartPointNow();
    Changes changesFromDataSource = new Changes(tableFromDataSource).setStartPointNow();
    updateChangesForTests();
    changesFromSource.setEndPointNow();
    changesFromDataSource.setEndPointNow();


    ChangesAssert assertionFromSource = assertThat(changesFromSource);
    ChangesAssert assertionFromDataSource = assertThat(changesFromDataSource);


    WritableAssertionInfo infoFromSource = (WritableAssertionInfo) field.get(assertionFromSource);
    assertThat(infoFromSource.descriptionText()).isEqualTo("Changes on actor table of 'sa/jdbc:h2:mem:test' source");

    WritableAssertionInfo infoFromDataSource = (WritableAssertionInfo) field.get(assertionFromDataSource);
    assertThat(infoFromDataSource.descriptionText()).isEqualTo("Changes on actor table of a data source");
  }

  /**
   * This method tests the description of changes on request.
   */
  @Test
  @NeedReload
  public void test_default_description_of_changes_on_request() throws Exception {
    Field field = AbstractElement.class.getDeclaredField("info");
    field.setAccessible(true);


    Request requestFromSource = new Request(source, "select * from actor").setPksName("ID");
    Request requestFromDataSource = new Request(dataSource, "select * from actor").setPksName("ID");
    Changes changesFromSource = new Changes(requestFromSource).setStartPointNow();
    Changes changesFromDataSource = new Changes(requestFromDataSource).setStartPointNow();
    updateChangesForTests();
    changesFromSource.setEndPointNow();
    changesFromDataSource.setEndPointNow();


    ChangesAssert assertionFromSource = assertThat(changesFromSource);
    ChangesAssert assertionFromDataSource = assertThat(changesFromDataSource);


    WritableAssertionInfo infoFromSource = (WritableAssertionInfo) field.get(assertionFromSource);
    assertThat(infoFromSource.descriptionText()).isEqualTo("Changes on 'select * from actor' request of 'sa/jdbc:h2:mem:test' source");

    WritableAssertionInfo infoFromDataSource = (WritableAssertionInfo) field.get(assertionFromDataSource);
    assertThat(infoFromDataSource.descriptionText()).isEqualTo("Changes on 'select * from actor' request of a data source");
  }

  /**
   * This method tests the description of change of changes.
   */
  @Test
  @NeedReload
  public void test_default_description_of_change_of_changes() throws Exception {
    Field field = AbstractElement.class.getDeclaredField("info");
    field.setAccessible(true);


    Changes changesFromSource = new Changes(source).setStartPointNow();
    Changes changesFromDataSource = new Changes(dataSource).setStartPointNow();
    updateChangesForTests();
    changesFromSource.setEndPointNow();
    changesFromDataSource.setEndPointNow();


    ChangeAssert assertionFromSource = assertThat(changesFromSource).change();
    ChangeAssert assertionFromDataSource = assertThat(changesFromDataSource).change(1);


    WritableAssertionInfo infoFromSource = (WritableAssertionInfo) field.get(assertionFromSource);
    assertThat(infoFromSource.descriptionText()).isEqualTo("Change at index 0 (on table : ACTOR and with primary key : [4]) of Changes on tables of 'sa/jdbc:h2:mem:test' source");

    WritableAssertionInfo infoFromDataSource = (WritableAssertionInfo) field.get(assertionFromDataSource);
    assertThat(infoFromDataSource.descriptionText()).isEqualTo("Change at index 1 (on table : INTERPRETATION and with primary key : [6]) of Changes on tables of a data source");
  }

  /**
   * This method tests the description of change of changes on table.
   */
  @Test
  @NeedReload
  public void test_default_description_of_change_of_changes_on_table() throws Exception {
    Field field = AbstractElement.class.getDeclaredField("info");
    field.setAccessible(true);


    Table tableFromSource = new Table(source, "actor");
    Table tableFromDataSource = new Table(dataSource, "actor");
    Changes changesFromSource = new Changes(tableFromSource).setStartPointNow();
    Changes changesFromDataSource = new Changes(tableFromDataSource).setStartPointNow();
    updateChangesForTests();
    changesFromSource.setEndPointNow();
    changesFromDataSource.setEndPointNow();


    ChangeAssert assertionFromSource = assertThat(changesFromSource).change();
    ChangeAssert assertionFromDataSource = assertThat(changesFromDataSource).change(2);

    WritableAssertionInfo infoFromSource = (WritableAssertionInfo) field.get(assertionFromSource);
    assertThat(infoFromSource.descriptionText()).isEqualTo("Change at index 0 (with primary key : [4]) of Changes on actor table of 'sa/jdbc:h2:mem:test' source");

    WritableAssertionInfo infoFromDataSource = (WritableAssertionInfo) field.get(assertionFromDataSource);
    assertThat(infoFromDataSource.descriptionText()).isEqualTo("Change at index 2 (with primary key : [3]) of Changes on actor table of a data source");
  }

  /**
   * This method tests the description of change of changes on request.
   */
  @Test
  @NeedReload
  public void test_default_description_of_change_of_changes_on_request() throws Exception {
    Field field = AbstractElement.class.getDeclaredField("info");
    field.setAccessible(true);


    Request requestFromSource = new Request(source, "select id, name, firstname, birth from actor where id = 1").setPksName("ID");
    Request requestFromDataSource = new Request(dataSource, "select * from actor").setPksName("ID");
    Changes changesFromSource = new Changes(requestFromSource).setStartPointNow();
    Changes changesFromDataSource = new Changes(requestFromDataSource).setStartPointNow();
    updateChangesForTests();
    changesFromSource.setEndPointNow();
    changesFromDataSource.setEndPointNow();


    ChangeAssert assertionFromSource = assertThat(changesFromSource).change();
    ChangeAssert assertionFromDataSource = assertThat(changesFromDataSource).change(2);


    WritableAssertionInfo infoFromSource = (WritableAssertionInfo) field.get(assertionFromSource);
    assertThat(infoFromSource.descriptionText()).isEqualTo("Change at index 0 (with primary key : [1]) of Changes on 'select id, name, firstname, bi...' request of 'sa/jdbc:h2:mem:test' source");

    WritableAssertionInfo infoFromDataSource = (WritableAssertionInfo) field.get(assertionFromDataSource);
    assertThat(infoFromDataSource.descriptionText()).isEqualTo("Change at index 2 (with primary key : [3]) of Changes on 'select * from actor' request of a data source");
  }

  /**
   * This method tests the description of creation change of changes.
   */
  @Test
  @NeedReload
  public void test_default_description_of_creation_change_of_changes() throws Exception {
    Field field = AbstractElement.class.getDeclaredField("info");
    field.setAccessible(true);


    Changes changesFromSource = new Changes(source).setStartPointNow();
    Changes changesFromDataSource = new Changes(dataSource).setStartPointNow();
    updateChangesForTests();
    changesFromSource.setEndPointNow();
    changesFromDataSource.setEndPointNow();


    ChangeAssert assertionFromSource = assertThat(changesFromSource).changeOfCreation();
    ChangeAssert assertionFromDataSource = assertThat(changesFromDataSource).changeOfCreation(0);


    WritableAssertionInfo infoFromSource = (WritableAssertionInfo) field.get(assertionFromSource);
    assertThat(infoFromSource.descriptionText()).isEqualTo("Change at index 0 (on table : ACTOR and with primary key : [4]) of Changes on tables of 'sa/jdbc:h2:mem:test' source (only creation changes)");

    WritableAssertionInfo infoFromDataSource = (WritableAssertionInfo) field.get(assertionFromDataSource);
    assertThat(infoFromDataSource.descriptionText()).isEqualTo("Change at index 0 (on table : ACTOR and with primary key : [4]) of Changes on tables of a data source (only creation changes)");
  }

  /**
   * This method tests the description of creation change of changes on table.
   */
  @Test
  @NeedReload
  public void test_default_description_of_creation_change_of_changes_on_table() throws Exception {
    Field field = AbstractElement.class.getDeclaredField("info");
    field.setAccessible(true);


    Table tableFromSource = new Table(source, "actor");
    Table tableFromDataSource = new Table(dataSource, "actor");
    Changes changesFromSource = new Changes(tableFromSource).setStartPointNow();
    Changes changesFromDataSource = new Changes(tableFromDataSource).setStartPointNow();
    updateChangesForTests();
    changesFromSource.setEndPointNow();
    changesFromDataSource.setEndPointNow();


    ChangeAssert assertionFromSource = assertThat(changesFromSource).changeOfCreation();
    ChangeAssert assertionFromDataSource = assertThat(changesFromDataSource).changeOfCreation(0);


    WritableAssertionInfo infoFromSource = (WritableAssertionInfo) field.get(assertionFromSource);
    assertThat(infoFromSource.descriptionText()).isEqualTo("Change at index 0 (with primary key : [4]) of Changes on actor table of 'sa/jdbc:h2:mem:test' source (only creation changes)");

    WritableAssertionInfo infoFromDataSource = (WritableAssertionInfo) field.get(assertionFromDataSource);
    assertThat(infoFromDataSource.descriptionText()).isEqualTo("Change at index 0 (with primary key : [4]) of Changes on actor table of a data source (only creation changes)");
  }

  /**
   * This method tests the description of creation change of changes on request.
   */
  @Test
  @NeedReload
  public void test_default_description_of_creation_change_of_changes_on_request() throws Exception {
    Field field = AbstractElement.class.getDeclaredField("info");
    field.setAccessible(true);


    Request requestFromSource = new Request(source, "select * from actor").setPksName("ID");
    Request requestFromDataSource = new Request(dataSource, "select * from actor").setPksName("ID");
    Changes changesFromSource = new Changes(requestFromSource).setStartPointNow();
    Changes changesFromDataSource = new Changes(requestFromDataSource).setStartPointNow();
    updateChangesForTests();
    changesFromSource.setEndPointNow();
    changesFromDataSource.setEndPointNow();


    ChangeAssert assertionFromSource = assertThat(changesFromSource).changeOfCreation();
    ChangeAssert assertionFromDataSource = assertThat(changesFromDataSource).changeOfCreation(0);


    WritableAssertionInfo infoFromSource = (WritableAssertionInfo) field.get(assertionFromSource);
    assertThat(infoFromSource.descriptionText()).isEqualTo("Change at index 0 (with primary key : [4]) of Changes on 'select * from actor' request of 'sa/jdbc:h2:mem:test' source (only creation changes)");

    WritableAssertionInfo infoFromDataSource = (WritableAssertionInfo) field.get(assertionFromDataSource);
    assertThat(infoFromDataSource.descriptionText()).isEqualTo("Change at index 0 (with primary key : [4]) of Changes on 'select * from actor' request of a data source (only creation changes)");
  }

  /**
   * This method tests the description of modification change of changes.
   */
  @Test
  @NeedReload
  public void test_default_description_of_modification_change_of_changes() throws Exception {
    Field field = AbstractElement.class.getDeclaredField("info");
    field.setAccessible(true);


    Changes changesFromSource = new Changes(source).setStartPointNow();
    Changes changesFromDataSource = new Changes(dataSource).setStartPointNow();
    updateChangesForTests();
    changesFromSource.setEndPointNow();
    changesFromDataSource.setEndPointNow();


    ChangeAssert assertionFromSource = assertThat(changesFromSource).changeOfModification();
    ChangeAssert assertionFromDataSource = assertThat(changesFromDataSource).changeOfModification(0);


    WritableAssertionInfo infoFromSource = (WritableAssertionInfo) field.get(assertionFromSource);
    assertThat(infoFromSource.descriptionText()).isEqualTo("Change at index 0 (on table : ACTOR and with primary key : [1]) of Changes on tables of 'sa/jdbc:h2:mem:test' source (only modification changes)");

    WritableAssertionInfo infoFromDataSource = (WritableAssertionInfo) field.get(assertionFromDataSource);
    assertThat(infoFromDataSource.descriptionText()).isEqualTo("Change at index 0 (on table : ACTOR and with primary key : [1]) of Changes on tables of a data source (only modification changes)");
  }

  /**
   * This method tests the description of modification change of changes on table.
   */
  @Test
  @NeedReload
  public void test_default_description_of_modification_change_of_changes_on_table() throws Exception {
    Field field = AbstractElement.class.getDeclaredField("info");
    field.setAccessible(true);


    Table tableFromSource = new Table(source, "actor");
    Table tableFromDataSource = new Table(dataSource, "actor");
    Changes changesFromSource = new Changes(tableFromSource).setStartPointNow();
    Changes changesFromDataSource = new Changes(tableFromDataSource).setStartPointNow();
    updateChangesForTests();
    changesFromSource.setEndPointNow();
    changesFromDataSource.setEndPointNow();


    ChangeAssert assertionFromSource = assertThat(changesFromSource).changeOfModification();
    ChangeAssert assertionFromDataSource = assertThat(changesFromDataSource).changeOfModification(0);


    WritableAssertionInfo infoFromSource = (WritableAssertionInfo) field.get(assertionFromSource);
    assertThat(infoFromSource.descriptionText()).isEqualTo("Change at index 0 (with primary key : [1]) of Changes on actor table of 'sa/jdbc:h2:mem:test' source (only modification changes)");

    WritableAssertionInfo infoFromDataSource = (WritableAssertionInfo) field.get(assertionFromDataSource);
    assertThat(infoFromDataSource.descriptionText()).isEqualTo("Change at index 0 (with primary key : [1]) of Changes on actor table of a data source (only modification changes)");
  }

  /**
   * This method tests the description of modification change of changes on request.
   */
  @Test
  @NeedReload
  public void test_default_description_of_modification_change_of_changes_on_request() throws Exception {
    Field field = AbstractElement.class.getDeclaredField("info");
    field.setAccessible(true);


    Request requestFromSource = new Request(source, "select * from actor").setPksName("ID");
    Request requestFromDataSource = new Request(dataSource, "select * from actor").setPksName("ID");
    Changes changesFromSource = new Changes(requestFromSource).setStartPointNow();
    Changes changesFromDataSource = new Changes(requestFromDataSource).setStartPointNow();
    updateChangesForTests();
    changesFromSource.setEndPointNow();
    changesFromDataSource.setEndPointNow();

    ChangeAssert assertionFromSource = assertThat(changesFromSource).changeOfModification();
    ChangeAssert assertionFromDataSource = assertThat(changesFromDataSource).changeOfModification(0);


    WritableAssertionInfo infoFromSource = (WritableAssertionInfo) field.get(assertionFromSource);
    assertThat(infoFromSource.descriptionText()).isEqualTo("Change at index 0 (with primary key : [1]) of Changes on 'select * from actor' request of 'sa/jdbc:h2:mem:test' source (only modification changes)");

    WritableAssertionInfo infoFromDataSource = (WritableAssertionInfo) field.get(assertionFromDataSource);
    assertThat(infoFromDataSource.descriptionText()).isEqualTo("Change at index 0 (with primary key : [1]) of Changes on 'select * from actor' request of a data source (only modification changes)");
  }

  /**
   * This method tests the description of deletion change of changes.
   */
  @Test
  @NeedReload
  public void test_default_description_of_deletion_change_of_changes() throws Exception {
    Field field = AbstractElement.class.getDeclaredField("info");
    field.setAccessible(true);


    Changes changesFromSource = new Changes(source).setStartPointNow();
    Changes changesFromDataSource = new Changes(dataSource).setStartPointNow();
    updateChangesForTests();
    changesFromSource.setEndPointNow();
    changesFromDataSource.setEndPointNow();


    ChangeAssert assertionFromSource = assertThat(changesFromSource).changeOfDeletion();
    ChangeAssert assertionFromDataSource = assertThat(changesFromDataSource).changeOfDeletion(0);


    WritableAssertionInfo infoFromSource = (WritableAssertionInfo) field.get(assertionFromSource);
    assertThat(infoFromSource.descriptionText()).isEqualTo("Change at index 0 (on table : ACTOR and with primary key : [3]) of Changes on tables of 'sa/jdbc:h2:mem:test' source (only deletion changes)");

    WritableAssertionInfo infoFromDataSource = (WritableAssertionInfo) field.get(assertionFromDataSource);
    assertThat(infoFromDataSource.descriptionText()).isEqualTo("Change at index 0 (on table : ACTOR and with primary key : [3]) of Changes on tables of a data source (only deletion changes)");
  }

  /**
   * This method tests the description of deletion change of changes on table.
   */
  @Test
  @NeedReload
  public void test_default_description_of_deletion_change_of_changes_on_table() throws Exception {
    Field field = AbstractElement.class.getDeclaredField("info");
    field.setAccessible(true);


    Table tableFromSource = new Table(source, "actor");
    Table tableFromDataSource = new Table(dataSource, "actor");
    Changes changesFromSource = new Changes(tableFromSource).setStartPointNow();
    Changes changesFromDataSource = new Changes(tableFromDataSource).setStartPointNow();
    updateChangesForTests();
    changesFromSource.setEndPointNow();
    changesFromDataSource.setEndPointNow();


    ChangeAssert assertionFromSource = assertThat(changesFromSource).changeOfDeletion();
    ChangeAssert assertionFromDataSource = assertThat(changesFromDataSource).changeOfDeletion(0);


    WritableAssertionInfo infoFromSource = (WritableAssertionInfo) field.get(assertionFromSource);
    assertThat(infoFromSource.descriptionText()).isEqualTo("Change at index 0 (with primary key : [3]) of Changes on actor table of 'sa/jdbc:h2:mem:test' source (only deletion changes)");

    WritableAssertionInfo infoFromDataSource = (WritableAssertionInfo) field.get(assertionFromDataSource);
    assertThat(infoFromDataSource.descriptionText()).isEqualTo("Change at index 0 (with primary key : [3]) of Changes on actor table of a data source (only deletion changes)");
  }

  /**
   * This method tests the description of deletion change of changes on request.
   */
  @Test
  @NeedReload
  public void test_default_description_of_deletion_change_of_changes_on_request() throws Exception {
    Field field = AbstractElement.class.getDeclaredField("info");
    field.setAccessible(true);


    Request requestFromSource = new Request(source, "select * from actor").setPksName("ID");
    Request requestFromDataSource = new Request(dataSource, "select * from actor").setPksName("ID");
    Changes changesFromSource = new Changes(requestFromSource).setStartPointNow();
    Changes changesFromDataSource = new Changes(requestFromDataSource).setStartPointNow();
    updateChangesForTests();
    changesFromSource.setEndPointNow();
    changesFromDataSource.setEndPointNow();


    ChangeAssert assertionFromSource = assertThat(changesFromSource).changeOfDeletion();
    ChangeAssert assertionFromDataSource = assertThat(changesFromDataSource).changeOfDeletion(0);


    WritableAssertionInfo infoFromSource = (WritableAssertionInfo) field.get(assertionFromSource);
    assertThat(infoFromSource.descriptionText()).isEqualTo("Change at index 0 (with primary key : [3]) of Changes on 'select * from actor' request of 'sa/jdbc:h2:mem:test' source (only deletion changes)");

    WritableAssertionInfo infoFromDataSource = (WritableAssertionInfo) field.get(assertionFromDataSource);
    assertThat(infoFromDataSource.descriptionText()).isEqualTo("Change at index 0 (with primary key : [3]) of Changes on 'select * from actor' request of a data source (only deletion changes)");
  }

  /**
   * This method tests the description of creation change on a table of changes.
   */
  @Test
  @NeedReload
  public void test_default_description_of_creation_change_on_table_of_changes() throws Exception {
    Field field = AbstractElement.class.getDeclaredField("info");
    field.setAccessible(true);


    Changes changesFromSource = new Changes(source).setStartPointNow();
    Changes changesFromDataSource = new Changes(dataSource).setStartPointNow();
    updateChangesForTests();
    changesFromSource.setEndPointNow();
    changesFromDataSource.setEndPointNow();


    ChangeAssert assertionFromSource = assertThat(changesFromSource).changeOfCreationOnTable("actor");
    ChangeAssert assertionFromDataSource = assertThat(changesFromDataSource).changeOfCreationOnTable("actor", 0);


    WritableAssertionInfo infoFromSource = (WritableAssertionInfo) field.get(assertionFromSource);
    assertThat(infoFromSource.descriptionText()).isEqualTo("Change at index 0 (on table : ACTOR and with primary key : [4]) of Changes on tables of 'sa/jdbc:h2:mem:test' source (only creation changes on actor table)");

    WritableAssertionInfo infoFromDataSource = (WritableAssertionInfo) field.get(assertionFromDataSource);
    assertThat(infoFromDataSource.descriptionText()).isEqualTo("Change at index 0 (on table : ACTOR and with primary key : [4]) of Changes on tables of a data source (only creation changes on actor table)");
  }

  /**
   * This method tests the description of creation change on a table of changes on table.
   */
  @Test
  @NeedReload
  public void test_default_description_of_creation_change_on_table_of_changes_on_table() throws Exception {
    Field field = AbstractElement.class.getDeclaredField("info");
    field.setAccessible(true);


    Table tableFromSource = new Table(source, "actor");
    Table tableFromDataSource = new Table(dataSource, "actor");
    Changes changesFromSource = new Changes(tableFromSource).setStartPointNow();
    Changes changesFromDataSource = new Changes(tableFromDataSource).setStartPointNow();
    updateChangesForTests();
    changesFromSource.setEndPointNow();
    changesFromDataSource.setEndPointNow();


    ChangeAssert assertionFromSource = assertThat(changesFromSource).changeOfCreationOnTable("actor");
    ChangeAssert assertionFromDataSource = assertThat(changesFromDataSource).changeOfCreationOnTable("actor", 0);


    WritableAssertionInfo infoFromSource = (WritableAssertionInfo) field.get(assertionFromSource);
    assertThat(infoFromSource.descriptionText()).isEqualTo("Change at index 0 (with primary key : [4]) of Changes on actor table of 'sa/jdbc:h2:mem:test' source (only creation changes on actor table)");

    WritableAssertionInfo infoFromDataSource = (WritableAssertionInfo) field.get(assertionFromDataSource);
    assertThat(infoFromDataSource.descriptionText()).isEqualTo("Change at index 0 (with primary key : [4]) of Changes on actor table of a data source (only creation changes on actor table)");
  }

  /**
   * This method tests the description of modification change on a table of changes.
   */
  @Test
  @NeedReload
  public void test_default_description_of_modification_change_on_table_of_changes() throws Exception {
    Field field = AbstractElement.class.getDeclaredField("info");
    field.setAccessible(true);


    Changes changesFromSource = new Changes(source).setStartPointNow();
    Changes changesFromDataSource = new Changes(dataSource).setStartPointNow();
    updateChangesForTests();
    changesFromSource.setEndPointNow();
    changesFromDataSource.setEndPointNow();


    ChangeAssert assertionFromSource = assertThat(changesFromSource).changeOfModificationOnTable("actor");
    ChangeAssert assertionFromDataSource = assertThat(changesFromDataSource).changeOfModificationOnTable("actor", 0);


    WritableAssertionInfo infoFromSource = (WritableAssertionInfo) field.get(assertionFromSource);
    assertThat(infoFromSource.descriptionText()).isEqualTo("Change at index 0 (on table : ACTOR and with primary key : [1]) of Changes on tables of 'sa/jdbc:h2:mem:test' source (only modification changes on actor table)");

    WritableAssertionInfo infoFromDataSource = (WritableAssertionInfo) field.get(assertionFromDataSource);
    assertThat(infoFromDataSource.descriptionText()).isEqualTo("Change at index 0 (on table : ACTOR and with primary key : [1]) of Changes on tables of a data source (only modification changes on actor table)");
  }

  /**
   * This method tests the description of modification change on a table of changes on table.
   */
  @Test
  @NeedReload
  public void test_default_description_of_modification_change_on_table_of_changes_on_table() throws Exception {
    Field field = AbstractElement.class.getDeclaredField("info");
    field.setAccessible(true);


    Table tableFromSource = new Table(source, "actor");
    Table tableFromDataSource = new Table(dataSource, "actor");
    Changes changesFromSource = new Changes(tableFromSource).setStartPointNow();
    Changes changesFromDataSource = new Changes(tableFromDataSource).setStartPointNow();
    updateChangesForTests();
    changesFromSource.setEndPointNow();
    changesFromDataSource.setEndPointNow();


    ChangeAssert assertionFromSource = assertThat(changesFromSource).changeOfModificationOnTable("actor");
    ChangeAssert assertionFromDataSource = assertThat(changesFromDataSource).changeOfModificationOnTable("actor", 0);


    WritableAssertionInfo infoFromSource = (WritableAssertionInfo) field.get(assertionFromSource);
    assertThat(infoFromSource.descriptionText()).isEqualTo("Change at index 0 (with primary key : [1]) of Changes on actor table of 'sa/jdbc:h2:mem:test' source (only modification changes on actor table)");

    WritableAssertionInfo infoFromDataSource = (WritableAssertionInfo) field.get(assertionFromDataSource);
    assertThat(infoFromDataSource.descriptionText()).isEqualTo("Change at index 0 (with primary key : [1]) of Changes on actor table of a data source (only modification changes on actor table)");
  }

  /**
   * This method tests the description of deletion change on a table of changes.
   */
  @Test
  @NeedReload
  public void test_default_description_of_deletion_change_on_table_of_changes() throws Exception {
    Field field = AbstractElement.class.getDeclaredField("info");
    field.setAccessible(true);


    Changes changesFromSource = new Changes(source).setStartPointNow();
    Changes changesFromDataSource = new Changes(dataSource).setStartPointNow();
    updateChangesForTests();
    changesFromSource.setEndPointNow();
    changesFromDataSource.setEndPointNow();


    ChangeAssert assertionFromSource = assertThat(changesFromSource).changeOfDeletionOnTable("actor");
    ChangeAssert assertionFromDataSource = assertThat(changesFromDataSource).changeOfDeletionOnTable("actor", 0);


    WritableAssertionInfo infoFromSource = (WritableAssertionInfo) field.get(assertionFromSource);
    assertThat(infoFromSource.descriptionText()).isEqualTo("Change at index 0 (on table : ACTOR and with primary key : [3]) of Changes on tables of 'sa/jdbc:h2:mem:test' source (only deletion changes on actor table)");

    WritableAssertionInfo infoFromDataSource = (WritableAssertionInfo) field.get(assertionFromDataSource);
    assertThat(infoFromDataSource.descriptionText()).isEqualTo("Change at index 0 (on table : ACTOR and with primary key : [3]) of Changes on tables of a data source (only deletion changes on actor table)");
  }

  /**
   * This method tests the description of deletion change on a table of changes on table.
   */
  @Test
  @NeedReload
  public void test_default_description_of_deletion_change_on_table_of_changes_on_table() throws Exception {
    Field field = AbstractElement.class.getDeclaredField("info");
    field.setAccessible(true);


    Table tableFromSource = new Table(source, "actor");
    Table tableFromDataSource = new Table(dataSource, "actor");
    Changes changesFromSource = new Changes(tableFromSource).setStartPointNow();
    Changes changesFromDataSource = new Changes(tableFromDataSource).setStartPointNow();
    updateChangesForTests();
    changesFromSource.setEndPointNow();
    changesFromDataSource.setEndPointNow();


    ChangeAssert assertionFromSource = assertThat(changesFromSource).changeOfDeletionOnTable("actor");
    ChangeAssert assertionFromDataSource = assertThat(changesFromDataSource).changeOfDeletionOnTable("actor", 0);


    WritableAssertionInfo infoFromSource = (WritableAssertionInfo) field.get(assertionFromSource);
    assertThat(infoFromSource.descriptionText()).isEqualTo("Change at index 0 (with primary key : [3]) of Changes on actor table of 'sa/jdbc:h2:mem:test' source (only deletion changes on actor table)");

    WritableAssertionInfo infoFromDataSource = (WritableAssertionInfo) field.get(assertionFromDataSource);
    assertThat(infoFromDataSource.descriptionText()).isEqualTo("Change at index 0 (with primary key : [3]) of Changes on actor table of a data source (only deletion changes on actor table)");
  }

  /**
   * This method tests the description of change on a table of changes.
   */
  @Test
  @NeedReload
  public void test_default_description_of_change_on_table_of_changes() throws Exception {
    Field field = AbstractElement.class.getDeclaredField("info");
    field.setAccessible(true);


    Changes changesFromSource = new Changes(source).setStartPointNow();
    Changes changesFromDataSource = new Changes(dataSource).setStartPointNow();
    updateChangesForTests();
    changesFromSource.setEndPointNow();
    changesFromDataSource.setEndPointNow();


    ChangeAssert assertionFromSource = assertThat(changesFromSource).changeOnTable("actor");
    ChangeAssert assertionFromDataSource = assertThat(changesFromDataSource).changeOnTable("actor", 0);

    WritableAssertionInfo infoFromSource = (WritableAssertionInfo) field.get(assertionFromSource);
    assertThat(infoFromSource.descriptionText()).isEqualTo("Change at index 0 (on table : ACTOR and with primary key : [4]) of Changes on tables of 'sa/jdbc:h2:mem:test' source (only changes on actor table)");

    WritableAssertionInfo infoFromDataSource = (WritableAssertionInfo) field.get(assertionFromDataSource);
    assertThat(infoFromDataSource.descriptionText()).isEqualTo("Change at index 0 (on table : ACTOR and with primary key : [4]) of Changes on tables of a data source (only changes on actor table)");
  }

  /**
   * This method tests the description of change on a table of changes on table.
   */
  @Test
  @NeedReload
  public void test_default_description_of_change_on_table_of_changes_on_table() throws Exception {
    Field field = AbstractElement.class.getDeclaredField("info");
    field.setAccessible(true);


    Table tableFromSource = new Table(source, "actor");
    Table tableFromDataSource = new Table(dataSource, "actor");
    Changes changesFromSource = new Changes(tableFromSource).setStartPointNow();
    Changes changesFromDataSource = new Changes(tableFromDataSource).setStartPointNow();
    updateChangesForTests();
    changesFromSource.setEndPointNow();
    changesFromDataSource.setEndPointNow();


    ChangeAssert assertionFromSource = assertThat(changesFromSource).changeOnTable("actor");
    ChangeAssert assertionFromDataSource = assertThat(changesFromDataSource).changeOnTable("actor", 0);


    WritableAssertionInfo infoFromSource = (WritableAssertionInfo) field.get(assertionFromSource);
    assertThat(infoFromSource.descriptionText()).isEqualTo("Change at index 0 (with primary key : [4]) of Changes on actor table of 'sa/jdbc:h2:mem:test' source (only changes on actor table)");

    WritableAssertionInfo infoFromDataSource = (WritableAssertionInfo) field.get(assertionFromDataSource);
    assertThat(infoFromDataSource.descriptionText()).isEqualTo("Change at index 0 (with primary key : [4]) of Changes on actor table of a data source (only changes on actor table)");
  }

  /**
   * This method tests the description of change on a table with pks of changes.
   */
  @Test
  @NeedReload
  public void test_default_description_of_change_on_table_with_pks_of_changes() throws Exception {
    Field field = AbstractElement.class.getDeclaredField("info");
    field.setAccessible(true);


    Changes changesFromSource = new Changes(source).setStartPointNow();
    Changes changesFromDataSource = new Changes(dataSource).setStartPointNow();
    updateChangesForTests();
    changesFromSource.setEndPointNow();
    changesFromDataSource.setEndPointNow();


    ChangeAssert assertionFromSource = assertThat(changesFromSource).changeOnTableWithPks("actor", 1);
    ChangeAssert assertionFromDataSource = assertThat(changesFromDataSource).changeOnTableWithPks("actor", 1);


    WritableAssertionInfo infoFromSource = (WritableAssertionInfo) field.get(assertionFromSource);
    assertThat(infoFromSource.descriptionText()).isEqualTo("Change at index 1 (on table : ACTOR and with primary key : [1]) of Changes on tables of 'sa/jdbc:h2:mem:test' source (only changes on actor table)");

    WritableAssertionInfo infoFromDataSource = (WritableAssertionInfo) field.get(assertionFromDataSource);
    assertThat(infoFromDataSource.descriptionText()).isEqualTo("Change at index 1 (on table : ACTOR and with primary key : [1]) of Changes on tables of a data source (only changes on actor table)");
  }

  /**
   * This method tests the description of change on a table with pks of changes on table.
   */
  @Test
  @NeedReload
  public void test_default_description_of_change_on_table_with_pks_of_changes_on_table() throws Exception {
    Field field = AbstractElement.class.getDeclaredField("info");
    field.setAccessible(true);


    Table tableFromSource = new Table(source, "actor");
    Table tableFromDataSource = new Table(dataSource, "actor");
    Changes changesFromSource = new Changes(tableFromSource).setStartPointNow();
    Changes changesFromDataSource = new Changes(tableFromDataSource).setStartPointNow();
    updateChangesForTests();
    changesFromSource.setEndPointNow();
    changesFromDataSource.setEndPointNow();


    ChangeAssert assertionFromSource = assertThat(changesFromSource).changeOnTableWithPks("actor", 1);
    ChangeAssert assertionFromDataSource = assertThat(changesFromDataSource).changeOnTableWithPks("actor", 1);


    WritableAssertionInfo infoFromSource = (WritableAssertionInfo) field.get(assertionFromSource);
    assertThat(infoFromSource.descriptionText()).isEqualTo("Change at index 1 (with primary key : [1]) of Changes on actor table of 'sa/jdbc:h2:mem:test' source (only changes on actor table)");

    WritableAssertionInfo infoFromDataSource = (WritableAssertionInfo) field.get(assertionFromDataSource);
    assertThat(infoFromDataSource.descriptionText()).isEqualTo("Change at index 1 (with primary key : [1]) of Changes on actor table of a data source (only changes on actor table)");
  }

  /**
   * This method tests the description of column of change of changes.
   */
  @Test
  @NeedReload
  public void test_default_description_column_of_change_of_changes() throws Exception {
    Field field = AbstractElement.class.getDeclaredField("info");
    field.setAccessible(true);


    Changes changesFromSource = new Changes(source).setStartPointNow();
    Changes changesFromDataSource = new Changes(dataSource).setStartPointNow();
    updateChangesForTests();
    changesFromSource.setEndPointNow();
    changesFromDataSource.setEndPointNow();


    ChangeColumnAssert assertionFromSource = assertThat(changesFromSource).change().column();
    ChangeColumnAssert assertionFromDataSource = assertThat(changesFromDataSource).change(1).column();


    WritableAssertionInfo infoFromSource = (WritableAssertionInfo) field.get(assertionFromSource);
    assertThat(infoFromSource.descriptionText()).isEqualTo("Column at index 0 (column name : ID) of Change at index 0 (on table : ACTOR and with primary key : [4]) of Changes on tables of 'sa/jdbc:h2:mem:test' source");

    WritableAssertionInfo infoFromDataSource = (WritableAssertionInfo) field.get(assertionFromDataSource);
    assertThat(infoFromDataSource.descriptionText()).isEqualTo("Column at index 0 (column name : ID) of Change at index 1 (on table : INTERPRETATION and with primary key : [6]) of Changes on tables of a data source");
  }

  /**
   * This method tests the description of row at start point of change of changes.
   */
  @Test
  @NeedReload
  public void test_default_description_row_at_start_point_of_change_of_changes() throws Exception {
    Field field = AbstractElement.class.getDeclaredField("info");
    field.setAccessible(true);


    Changes changesFromSource = new Changes(source).setStartPointNow();
    Changes changesFromDataSource = new Changes(dataSource).setStartPointNow();
    updateChangesForTests();
    changesFromSource.setEndPointNow();
    changesFromDataSource.setEndPointNow();


    ChangeRowAssert assertionFromSource = assertThat(changesFromSource).change().rowAtStartPoint();
    ChangeRowAssert assertionFromDataSource = assertThat(changesFromDataSource).change(1).rowAtStartPoint();


    WritableAssertionInfo infoFromSource = (WritableAssertionInfo) field.get(assertionFromSource);
    assertThat(infoFromSource.descriptionText()).isEqualTo("Row at start point of Change at index 0 (on table : ACTOR and with primary key : [4]) of Changes on tables of 'sa/jdbc:h2:mem:test' source");

    WritableAssertionInfo infoFromDataSource = (WritableAssertionInfo) field.get(assertionFromDataSource);
    assertThat(infoFromDataSource.descriptionText()).isEqualTo("Row at start point of Change at index 1 (on table : INTERPRETATION and with primary key : [6]) of Changes on tables of a data source");
  }

  /**
   * This method tests the description of row at end point of change of changes.
   */
  @Test
  @NeedReload
  public void test_default_description_row_at_end_point_of_change_of_changes() throws Exception {
    Field field = AbstractElement.class.getDeclaredField("info");
    field.setAccessible(true);


    Changes changesFromSource = new Changes(source).setStartPointNow();
    Changes changesFromDataSource = new Changes(dataSource).setStartPointNow();
    updateChangesForTests();
    changesFromSource.setEndPointNow();
    changesFromDataSource.setEndPointNow();


    ChangeRowAssert assertionFromSource = assertThat(changesFromSource).change().rowAtEndPoint();
    ChangeRowAssert assertionFromDataSource = assertThat(changesFromDataSource).change(1).rowAtEndPoint();


    WritableAssertionInfo infoFromSource = (WritableAssertionInfo) field.get(assertionFromSource);
    assertThat(infoFromSource.descriptionText()).isEqualTo("Row at end point of Change at index 0 (on table : ACTOR and with primary key : [4]) of Changes on tables of 'sa/jdbc:h2:mem:test' source");

    WritableAssertionInfo infoFromDataSource = (WritableAssertionInfo) field.get(assertionFromDataSource);
    assertThat(infoFromDataSource.descriptionText()).isEqualTo("Row at end point of Change at index 1 (on table : INTERPRETATION and with primary key : [6]) of Changes on tables of a data source");
  }

  /**
   * This method tests the description of value at start point of of column of change of changes.
   */
  @Test
  @NeedReload
  public void test_default_description_value_at_start_point_of_column_of_change_of_changes() throws Exception {
    Field field = AbstractElement.class.getDeclaredField("info");
    field.setAccessible(true);


    Changes changesFromSource = new Changes(source).setStartPointNow();
    Changes changesFromDataSource = new Changes(dataSource).setStartPointNow();
    updateChangesForTests();
    changesFromSource.setEndPointNow();
    changesFromDataSource.setEndPointNow();


    ChangeColumnValueAssert assertionFromSource = assertThat(changesFromSource).change().column().valueAtStartPoint();
    ChangeColumnValueAssert assertionFromDataSource = assertThat(changesFromDataSource).change(
            1).column().valueAtStartPoint();


    WritableAssertionInfo infoFromSource = (WritableAssertionInfo) field.get(assertionFromSource);
    assertThat(infoFromSource.descriptionText()).isEqualTo("Value at start point of Column at index 0 (column name : ID) of Change at index 0 (on table : ACTOR and with primary key : [4]) of Changes on tables of 'sa/jdbc:h2:mem:test' source");

    WritableAssertionInfo infoFromDataSource = (WritableAssertionInfo) field.get(assertionFromDataSource);
    assertThat(infoFromDataSource.descriptionText()).isEqualTo("Value at start point of Column at index 0 (column name : ID) of Change at index 1 (on table : INTERPRETATION and with primary key : [6]) of Changes on tables of a data source");
  }

  /**
   * This method tests the description of value at end point of column of change of changes.
   */
  @Test
  @NeedReload
  public void test_default_description_value_at_end_point_of_column_of_change_of_changes() throws Exception {
    Field field = AbstractElement.class.getDeclaredField("info");
    field.setAccessible(true);


    Changes changesFromSource = new Changes(source).setStartPointNow();
    Changes changesFromDataSource = new Changes(dataSource).setStartPointNow();
    updateChangesForTests();
    changesFromSource.setEndPointNow();
    changesFromDataSource.setEndPointNow();


    ChangeColumnValueAssert assertionFromSource = assertThat(changesFromSource).change().column().valueAtEndPoint();
    ChangeColumnValueAssert assertionFromDataSource = assertThat(changesFromDataSource).change(
            1).column().valueAtEndPoint();


    WritableAssertionInfo infoFromSource = (WritableAssertionInfo) field.get(assertionFromSource);
    assertThat(infoFromSource.descriptionText()).isEqualTo("Value at end point of Column at index 0 (column name : ID) of Change at index 0 (on table : ACTOR and with primary key : [4]) of Changes on tables of 'sa/jdbc:h2:mem:test' source");

    WritableAssertionInfo infoFromDataSource = (WritableAssertionInfo) field.get(assertionFromDataSource);
    assertThat(infoFromDataSource.descriptionText()).isEqualTo("Value at end point of Column at index 0 (column name : ID) of Change at index 1 (on table : INTERPRETATION and with primary key : [6]) of Changes on tables of a data source");
  }

  /**
   * This method tests the description of value of row at start point of change of changes.
   */
  @Test
  @NeedReload
  public void test_default_description_value_of_row_at_start_point_of_change_of_changes() throws Exception {
    Field field = AbstractElement.class.getDeclaredField("info");
    field.setAccessible(true);


    Changes changesFromSource = new Changes(source).setStartPointNow();
    Changes changesFromDataSource = new Changes(dataSource).setStartPointNow();
    updateChangesForTests();
    changesFromSource.setEndPointNow();
    changesFromDataSource.setEndPointNow();


    ChangeRowValueAssert assertionFromSource = assertThat(changesFromSource).change(3).rowAtStartPoint().value();
    ChangeRowValueAssert assertionFromDataSource = assertThat(changesFromDataSource).change(3).rowAtStartPoint().value();


    WritableAssertionInfo infoFromSource = (WritableAssertionInfo) field.get(assertionFromSource);
    assertThat(infoFromSource.descriptionText()).isEqualTo("Value at index 0 (column name : ID) of Row at start point of Change at index 3 (on table : ACTOR and with primary key : [1]) of Changes on tables of 'sa/jdbc:h2:mem:test' source");

    WritableAssertionInfo infoFromDataSource = (WritableAssertionInfo) field.get(assertionFromDataSource);
    assertThat(infoFromDataSource.descriptionText()).isEqualTo("Value at index 0 (column name : ID) of Row at start point of Change at index 3 (on table : ACTOR and with primary key : [1]) of Changes on tables of a data source");
  }

  /**
   * This method tests the description of value of row at end point of change of changes.
   */
  @Test
  @NeedReload
  public void test_default_description_value_of_row_at_end_point_of_change_of_changes() throws Exception {
    Field field = AbstractElement.class.getDeclaredField("info");
    field.setAccessible(true);


    Changes changesFromSource = new Changes(source).setStartPointNow();
    Changes changesFromDataSource = new Changes(dataSource).setStartPointNow();
    updateChangesForTests();
    changesFromSource.setEndPointNow();
    changesFromDataSource.setEndPointNow();


    ChangeRowValueAssert assertionFromSource = assertThat(changesFromSource).change().rowAtEndPoint().value();
    ChangeRowValueAssert assertionFromDataSource = assertThat(changesFromDataSource).change(1).rowAtEndPoint().value();


    WritableAssertionInfo infoFromSource = (WritableAssertionInfo) field.get(assertionFromSource);
    assertThat(infoFromSource.descriptionText()).isEqualTo("Value at index 0 (column name : ID) of Row at end point of Change at index 0 (on table : ACTOR and with primary key : [4]) of Changes on tables of 'sa/jdbc:h2:mem:test' source");

    WritableAssertionInfo infoFromDataSource = (WritableAssertionInfo) field.get(assertionFromDataSource);
    assertThat(infoFromDataSource.descriptionText()).isEqualTo("Value at index 0 (column name : ID) of Row at end point of Change at index 1 (on table : INTERPRETATION and with primary key : [6]) of Changes on tables of a data source");
  }

  /**
   * This method tests the description of table.
   */
  @Test
  public void test_default_description_of_table() throws Exception {
    Field field = AbstractElement.class.getDeclaredField("info");
    field.setAccessible(true);


    Table tableFromSource = new Table(source, "actor");
    Table tableFromDataSource = new Table(dataSource, "actor");


    TableAssert assertionFromSource = assertThat(tableFromSource);
    TableAssert assertionFromDataSource = assertThat(tableFromDataSource);


    WritableAssertionInfo infoFromSource = (WritableAssertionInfo) field.get(assertionFromSource);
    assertThat(infoFromSource.descriptionText()).isEqualTo("actor table");

    WritableAssertionInfo infoFromDataSource = (WritableAssertionInfo) field.get(assertionFromDataSource);
    assertThat(infoFromDataSource.descriptionText()).isEqualTo("actor table");
  }

  /**
   * This method tests the description of request.
   */
  @Test
  public void test_default_description_of_request() throws Exception {
    Field field = AbstractElement.class.getDeclaredField("info");
    field.setAccessible(true);


    Request requestFromSource = new Request(source, "select id, name, firstname, birth from actor where id = 1").setPksName("ID");
    Request requestFromDataSource = new Request(dataSource, "select * from actor").setPksName("ID");


    RequestAssert assertionFromSource = assertThat(requestFromSource);
    RequestAssert assertionFromDataSource = assertThat(requestFromDataSource);


    WritableAssertionInfo infoFromSource = (WritableAssertionInfo) field.get(assertionFromSource);
    assertThat(infoFromSource.descriptionText()).isEqualTo("'select id, name, firstname, bi...' request");

    WritableAssertionInfo infoFromDataSource = (WritableAssertionInfo) field.get(assertionFromDataSource);
    assertThat(infoFromDataSource.descriptionText()).isEqualTo("'select * from actor' request");
  }

  /**
   * This method tests the description of column of table.
   */
  @Test
  public void test_default_description_of_column_of_table() throws Exception {
    Field field = AbstractElement.class.getDeclaredField("info");
    field.setAccessible(true);


    Table tableFromSource = new Table(source, "actor");
    Table tableFromDataSource = new Table(dataSource, "actor");


    TableColumnAssert assertionFromSource = assertThat(tableFromSource).column();
    TableColumnAssert assertionFromDataSource = assertThat(tableFromDataSource).column();


    WritableAssertionInfo infoFromSource = (WritableAssertionInfo) field.get(assertionFromSource);
    assertThat(infoFromSource.descriptionText()).isEqualTo("Column at index 0 (column name : ID) of actor table");

    WritableAssertionInfo infoFromDataSource = (WritableAssertionInfo) field.get(assertionFromDataSource);
    assertThat(infoFromDataSource.descriptionText()).isEqualTo("Column at index 0 (column name : ID) of actor table");
  }

  /**
   * This method tests the description of row of table.
   */
  @Test
  public void test_default_description_of_row_of_table() throws Exception {
    Field field = AbstractElement.class.getDeclaredField("info");
    field.setAccessible(true);


    Table tableFromSource = new Table(source, "actor");
    Table tableFromDataSource = new Table(dataSource, "actor");


    TableRowAssert assertionFromSource = assertThat(tableFromSource).row();
    TableRowAssert assertionFromDataSource = assertThat(tableFromDataSource).row();


    WritableAssertionInfo infoFromSource = (WritableAssertionInfo) field.get(assertionFromSource);
    assertThat(infoFromSource.descriptionText()).isEqualTo("Row at index 0 of actor table");

    WritableAssertionInfo infoFromDataSource = (WritableAssertionInfo) field.get(assertionFromDataSource);
    assertThat(infoFromDataSource.descriptionText()).isEqualTo("Row at index 0 of actor table");
  }

  /**
   * This method tests the description of column of request.
   */
  @Test
  public void test_default_description_of_column_of_request() throws Exception {
    Field field = AbstractElement.class.getDeclaredField("info");
    field.setAccessible(true);


    Request requestFromSource = new Request(source, "select * from actor").setPksName("ID");
    Request requestFromDataSource = new Request(dataSource, "select * from actor").setPksName("ID");


    RequestColumnAssert assertionFromSource = assertThat(requestFromSource).column();
    RequestColumnAssert assertionFromDataSource = assertThat(requestFromDataSource).column();


    WritableAssertionInfo infoFromSource = (WritableAssertionInfo) field.get(assertionFromSource);
    assertThat(infoFromSource.descriptionText()).isEqualTo("Column at index 0 (column name : ID) of 'select * from actor' request");

    WritableAssertionInfo infoFromDataSource = (WritableAssertionInfo) field.get(assertionFromDataSource);
    assertThat(infoFromDataSource.descriptionText()).isEqualTo("Column at index 0 (column name : ID) of 'select * from actor' request");
  }

  /**
   * This method tests the description of row of request.
   */
  @Test
  public void test_default_description_of_row_of_request() throws Exception {
    Field field = AbstractElement.class.getDeclaredField("info");
    field.setAccessible(true);


    Request requestFromSource = new Request(source, "select * from actor").setPksName("ID");
    Request requestFromDataSource = new Request(dataSource, "select * from actor").setPksName("ID");


    RequestRowAssert assertionFromSource = assertThat(requestFromSource).row();
    RequestRowAssert assertionFromDataSource = assertThat(requestFromDataSource).row();


    WritableAssertionInfo infoFromSource = (WritableAssertionInfo) field.get(assertionFromSource);
    assertThat(infoFromSource.descriptionText()).isEqualTo("Row at index 0 of 'select * from actor' request");

    WritableAssertionInfo infoFromDataSource = (WritableAssertionInfo) field.get(assertionFromDataSource);
    assertThat(infoFromDataSource.descriptionText()).isEqualTo("Row at index 0 of 'select * from actor' request");
  }

  /**
   * This method tests the description of value of column of table.
   */
  @Test
  public void test_default_description_of_value_of_column_of_table() throws Exception {
    Field field = AbstractElement.class.getDeclaredField("info");
    field.setAccessible(true);


    Table tableFromSource = new Table(source, "actor");
    Table tableFromDataSource = new Table(dataSource, "actor");


    TableColumnValueAssert assertionFromSource = assertThat(tableFromSource).column().value();
    TableColumnValueAssert assertionFromDataSource = assertThat(tableFromDataSource).column().value();


    WritableAssertionInfo infoFromSource = (WritableAssertionInfo) field.get(assertionFromSource);
    assertThat(infoFromSource.descriptionText()).isEqualTo("Value at index 0 of Column at index 0 (column name : ID) of actor table");

    WritableAssertionInfo infoFromDataSource = (WritableAssertionInfo) field.get(assertionFromDataSource);
    assertThat(infoFromDataSource.descriptionText()).isEqualTo("Value at index 0 of Column at index 0 (column name : ID) of actor table");
  }

  /**
   * This method tests the description of value of row of table.
   */
  @Test
  public void test_default_description_of_value_of_row_of_table() throws Exception {
    Field field = AbstractElement.class.getDeclaredField("info");
    field.setAccessible(true);


    Table tableFromSource = new Table(source, "actor");
    Table tableFromDataSource = new Table(dataSource, "actor");


    TableRowValueAssert assertionFromSource = assertThat(tableFromSource).row().value();
    TableRowValueAssert assertionFromDataSource = assertThat(tableFromDataSource).row().value();


    WritableAssertionInfo infoFromSource = (WritableAssertionInfo) field.get(assertionFromSource);
    assertThat(infoFromSource.descriptionText()).isEqualTo("Value at index 0 (column name : ID) of Row at index 0 of actor table");

    WritableAssertionInfo infoFromDataSource = (WritableAssertionInfo) field.get(assertionFromDataSource);
    assertThat(infoFromDataSource.descriptionText()).isEqualTo("Value at index 0 (column name : ID) of Row at index 0 of actor table");
  }

  /**
   * This method tests the description of value of column of request.
   */
  @Test
  public void test_default_description_of_value_of_column_of_request() throws Exception {
    Field field = AbstractElement.class.getDeclaredField("info");
    field.setAccessible(true);


    Request requestFromSource = new Request(source, "select * from actor").setPksName("ID");
    Request requestFromDataSource = new Request(dataSource, "select * from actor").setPksName("ID");


    RequestColumnValueAssert assertionFromSource = assertThat(requestFromSource).column().value();
    RequestColumnValueAssert assertionFromDataSource = assertThat(requestFromDataSource).column().value();


    WritableAssertionInfo infoFromSource = (WritableAssertionInfo) field.get(assertionFromSource);
    assertThat(infoFromSource.descriptionText()).isEqualTo("Value at index 0 of Column at index 0 (column name : ID) of 'select * from actor' request");

    WritableAssertionInfo infoFromDataSource = (WritableAssertionInfo) field.get(assertionFromDataSource);
    assertThat(infoFromDataSource.descriptionText()).isEqualTo("Value at index 0 of Column at index 0 (column name : ID) of 'select * from actor' request");
  }

  /**
   * This method tests the description of value of row of request.
   */
  @Test
  public void test_default_description_of_value_of_row_of_request() throws Exception {
    Field field = AbstractElement.class.getDeclaredField("info");
    field.setAccessible(true);


    Request requestFromSource = new Request(source, "select * from actor").setPksName("ID");
    Request requestFromDataSource = new Request(dataSource, "select * from actor").setPksName("ID");


    RequestRowValueAssert assertionFromSource = assertThat(requestFromSource).row().value();
    RequestRowValueAssert assertionFromDataSource = assertThat(requestFromDataSource).row().value();


    WritableAssertionInfo infoFromSource = (WritableAssertionInfo) field.get(assertionFromSource);
    assertThat(infoFromSource.descriptionText()).isEqualTo("Value at index 0 (column name : ID) of Row at index 0 of 'select * from actor' request");

    WritableAssertionInfo infoFromDataSource = (WritableAssertionInfo) field.get(assertionFromDataSource);
    assertThat(infoFromDataSource.descriptionText()).isEqualTo("Value at index 0 (column name : ID) of Row at index 0 of 'select * from actor' request");
  }
}
