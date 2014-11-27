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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;

import java.lang.reflect.Field;

import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Changes;
import org.assertj.db.type.Request;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Test on default description for {@code Changes}.
 * 
 * @author Régis Pouiller
 * 
 */
public class ChangesAssert_DefaultAs_Test extends AbstractTest {

  /**
   * This method tests the description of changes on datasource.
   * 
   * @throws IllegalAccessException
   * @throws IllegalArgumentException
   * @throws NoSuchFieldException
   * @throws SecurityException
   */
  @Test
  public void test_default_description_of_changes_on_datasource() throws NoSuchFieldException, SecurityException,
      IllegalArgumentException, IllegalAccessException {

    Changes changes = new Changes(dataSource);
    ChangesAssert assertion = assertThat(changes);

    Field field = AbstractAssert.class.getDeclaredField("info");
    field.setAccessible(true);
    WritableAssertionInfo info = (WritableAssertionInfo) field.get(assertion);

    assertThat(info.descriptionText()).isEqualTo("Changes of a data source");
  }

  /**
   * This method tests the description of changes on a source.
   * 
   * @throws IllegalAccessException
   * @throws IllegalArgumentException
   * @throws NoSuchFieldException
   * @throws SecurityException
   */
  @Test
  public void test_default_description_of_changes_on_source() throws NoSuchFieldException, SecurityException,
      IllegalArgumentException, IllegalAccessException {

    Changes changes = new Changes(source);
    ChangesAssert assertion = assertThat(changes);

    Field field = AbstractAssert.class.getDeclaredField("info");
    field.setAccessible(true);
    WritableAssertionInfo info = (WritableAssertionInfo) field.get(assertion);

    assertThat(info.descriptionText()).isEqualTo("Changes of 'sa/jdbc:h2:mem:test' source");
  }

  /**
   * This method tests the description of changes on one table of a source.
   * 
   * @throws IllegalAccessException
   * @throws IllegalArgumentException
   * @throws NoSuchFieldException
   * @throws SecurityException
   */
  @Test
  public void test_default_description_of_changes_on_one_table_of_source() throws NoSuchFieldException, SecurityException,
      IllegalArgumentException, IllegalAccessException {

    Changes changes = new Changes(new Table(source, "movie"));
    ChangesAssert assertion = assertThat(changes);

    Field field = AbstractAssert.class.getDeclaredField("info");
    field.setAccessible(true);
    WritableAssertionInfo info = (WritableAssertionInfo) field.get(assertion);

    assertThat(info.descriptionText()).isEqualTo("Changes on movie table of 'sa/jdbc:h2:mem:test' source");
  }

  /**
   * This method tests the description of changes on one table of a data source.
   * 
   * @throws IllegalAccessException
   * @throws IllegalArgumentException
   * @throws NoSuchFieldException
   * @throws SecurityException
   */
  @Test
  public void test_default_description_of_changes_on_one_table_of_datasource() throws NoSuchFieldException, SecurityException,
      IllegalArgumentException, IllegalAccessException {

    Changes changes = new Changes(new Table(dataSource, "movie"));
    ChangesAssert assertion = assertThat(changes);

    Field field = AbstractAssert.class.getDeclaredField("info");
    field.setAccessible(true);
    WritableAssertionInfo info = (WritableAssertionInfo) field.get(assertion);

    assertThat(info.descriptionText()).isEqualTo("Changes on movie table of a data source");
  }

  /**
   * This method tests the description of changes on many tables of a source.
   * 
   * @throws IllegalAccessException
   * @throws IllegalArgumentException
   * @throws NoSuchFieldException
   * @throws SecurityException
   */
  @Test
  public void test_default_description_of_changes_on_many_tables_of_source() throws NoSuchFieldException, SecurityException,
      IllegalArgumentException, IllegalAccessException {

    Changes changes = new Changes(new Table(source, "movie"), new Table(source, "actor"));
    ChangesAssert assertion = assertThat(changes);

    Field field = AbstractAssert.class.getDeclaredField("info");
    field.setAccessible(true);
    WritableAssertionInfo info = (WritableAssertionInfo) field.get(assertion);

    assertThat(info.descriptionText()).isEqualTo("Changes on tables of 'sa/jdbc:h2:mem:test' source");
  }

  /**
   * This method tests the description of changes on many tables of a data source.
   * 
   * @throws IllegalAccessException
   * @throws IllegalArgumentException
   * @throws NoSuchFieldException
   * @throws SecurityException
   */
  @Test
  public void test_default_description_of_changes_on_many_tables_of_datasource() throws NoSuchFieldException, SecurityException,
      IllegalArgumentException, IllegalAccessException {

    Changes changes = new Changes(new Table(dataSource, "movie"), new Table(dataSource, "actor"));
    ChangesAssert assertion = assertThat(changes);

    Field field = AbstractAssert.class.getDeclaredField("info");
    field.setAccessible(true);
    WritableAssertionInfo info = (WritableAssertionInfo) field.get(assertion);

    assertThat(info.descriptionText()).isEqualTo("Changes on tables of a data source");
  }

  /**
   * This method tests the description on changes of a source when the SQL request length is not important.
   * 
   * @throws IllegalAccessException
   * @throws IllegalArgumentException
   * @throws NoSuchFieldException
   * @throws SecurityException
   */
  @Test
  public void test_default_description_of_changes_of_source_when_sql_request_length_not_important() throws NoSuchFieldException,
      SecurityException, IllegalArgumentException, IllegalAccessException {

    Request request = new Request(source, "SELECT title FROM movie");
    Changes changes = new Changes(request);
    ChangesAssert assertion = assertThat(changes);

    Field field = AbstractAssert.class.getDeclaredField("info");
    field.setAccessible(true);
    WritableAssertionInfo info = (WritableAssertionInfo) field.get(assertion);

    assertThat(info.descriptionText()).isEqualTo("Changes on 'SELECT title FROM movie' request of 'sa/jdbc:h2:mem:test' source");
  }

  /**
   * This method tests the description on changes of a source when the SQL request length is important.
   * 
   * @throws IllegalAccessException
   * @throws IllegalArgumentException
   * @throws NoSuchFieldException
   * @throws SecurityException
   */
  @Test
  public void test_default_description_of_changes_of_source_when_sql_request_length_important() throws NoSuchFieldException,
      SecurityException, IllegalArgumentException, IllegalAccessException {

    Request request = new Request(source, "SELECT actor.name, actor.firstname, movie.year, interpretation.character "
        + " FROM movie, actor, interpretation" + " WHERE movie.id = interpretation.id_movie"
        + " AND interpretation.id_actor = actor.id" + " AND movie.year > ?" + " ORDER BY actor.name, movie.year", 2000);
    Changes changes = new Changes(request);
    ChangesAssert assertion = assertThat(changes);

    Field field = AbstractAssert.class.getDeclaredField("info");
    field.setAccessible(true);
    WritableAssertionInfo info = (WritableAssertionInfo) field.get(assertion);

    assertThat(info.descriptionText()).isEqualTo("Changes on 'SELECT actor.name, actor.first...' request of 'sa/jdbc:h2:mem:test' source");
  }

  /**
   * This method tests the description on changes of a data source when the SQL request length is not important.
   * 
   * @throws IllegalAccessException
   * @throws IllegalArgumentException
   * @throws NoSuchFieldException
   * @throws SecurityException
   */
  @Test
  public void test_default_description_of_changes_of_datasource_when_sql_request_length_not_important() throws NoSuchFieldException,
      SecurityException, IllegalArgumentException, IllegalAccessException {

    Request request = new Request(dataSource, "SELECT title FROM movie");
    Changes changes = new Changes(request);
    ChangesAssert assertion = assertThat(changes);

    Field field = AbstractAssert.class.getDeclaredField("info");
    field.setAccessible(true);
    WritableAssertionInfo info = (WritableAssertionInfo) field.get(assertion);

    assertThat(info.descriptionText()).isEqualTo("Changes on 'SELECT title FROM movie' request of a data source");
  }

  /**
   * This method tests the description on changes of a data source when the SQL request length is important.
   * 
   * @throws IllegalAccessException
   * @throws IllegalArgumentException
   * @throws NoSuchFieldException
   * @throws SecurityException
   */
  @Test
  public void test_default_description_of_changes_of_datasource_when_sql_request_length_important() throws NoSuchFieldException,
      SecurityException, IllegalArgumentException, IllegalAccessException {

    Request request = new Request(dataSource, "SELECT actor.name, actor.firstname, movie.year, interpretation.character "
        + " FROM movie, actor, interpretation" + " WHERE movie.id = interpretation.id_movie"
        + " AND interpretation.id_actor = actor.id" + " AND movie.year > ?" + " ORDER BY actor.name, movie.year", 2000);
    Changes changes = new Changes(request);
    ChangesAssert assertion = assertThat(changes);

    Field field = AbstractAssert.class.getDeclaredField("info");
    field.setAccessible(true);
    WritableAssertionInfo info = (WritableAssertionInfo) field.get(assertion);

    assertThat(info.descriptionText()).isEqualTo("Changes on 'SELECT actor.name, actor.first...' request of a data source");
  }

  /**
   * This method tests the description of changes on datasource (only creation).
   * 
   * @throws IllegalAccessException
   * @throws IllegalArgumentException
   * @throws NoSuchFieldException
   * @throws SecurityException
   */
  @Test
  public void test_default_description_of_changes_on_datasource_only_creation() throws NoSuchFieldException, SecurityException,
      IllegalArgumentException, IllegalAccessException {

    Changes changes = new Changes(dataSource).setStartPointNow().setEndPointNow();
    ChangesAssert assertion = assertThat(changes).ofCreation();

    Field field = AbstractAssert.class.getDeclaredField("info");
    field.setAccessible(true);
    WritableAssertionInfo info = (WritableAssertionInfo) field.get(assertion);

    assertThat(info.descriptionText()).isEqualTo("Changes on tables of a data source (only creation changes)");
  }

  /**
   * This method tests the description of changes on datasource (only modification).
   * 
   * @throws IllegalAccessException
   * @throws IllegalArgumentException
   * @throws NoSuchFieldException
   * @throws SecurityException
   */
  @Test
  public void test_default_description_of_changes_on_datasource_only_modification() throws NoSuchFieldException, SecurityException,
      IllegalArgumentException, IllegalAccessException {

    Changes changes = new Changes(dataSource).setStartPointNow().setEndPointNow();
    ChangesAssert assertion = assertThat(changes).ofModification();

    Field field = AbstractAssert.class.getDeclaredField("info");
    field.setAccessible(true);
    WritableAssertionInfo info = (WritableAssertionInfo) field.get(assertion);

    assertThat(info.descriptionText()).isEqualTo("Changes on tables of a data source (only modification changes)");
  }

  /**
   * This method tests the description of changes on datasource (only deletion).
   * 
   * @throws IllegalAccessException
   * @throws IllegalArgumentException
   * @throws NoSuchFieldException
   * @throws SecurityException
   */
  @Test
  public void test_default_description_of_changes_on_datasource_only_deletion() throws NoSuchFieldException, SecurityException,
      IllegalArgumentException, IllegalAccessException {

    Changes changes = new Changes(dataSource).setStartPointNow().setEndPointNow();
    ChangesAssert assertion = assertThat(changes).ofDeletion();

    Field field = AbstractAssert.class.getDeclaredField("info");
    field.setAccessible(true);
    WritableAssertionInfo info = (WritableAssertionInfo) field.get(assertion);

    assertThat(info.descriptionText()).isEqualTo("Changes on tables of a data source (only deletion changes)");
  }

  /**
   * This method tests the description of changes on datasource (only on a table).
   * 
   * @throws IllegalAccessException
   * @throws IllegalArgumentException
   * @throws NoSuchFieldException
   * @throws SecurityException
   */
  @Test
  public void test_default_description_of_changes_on_datasource_only_on_a_table() throws NoSuchFieldException, SecurityException,
      IllegalArgumentException, IllegalAccessException {

    Changes changes = new Changes(dataSource).setStartPointNow().setEndPointNow();
    ChangesAssert assertion = assertThat(changes).onTable("movie");

    Field field = AbstractAssert.class.getDeclaredField("info");
    field.setAccessible(true);
    WritableAssertionInfo info = (WritableAssertionInfo) field.get(assertion);

    assertThat(info.descriptionText()).isEqualTo("Changes on tables of a data source (only on movie table)");
  }

  /**
   * This method tests the description of changes on datasource (only creation on a table).
   * 
   * @throws IllegalAccessException
   * @throws IllegalArgumentException
   * @throws NoSuchFieldException
   * @throws SecurityException
   */
  @Test
  public void test_default_description_of_changes_on_datasource_only_creation_on_a_table() throws NoSuchFieldException, SecurityException,
      IllegalArgumentException, IllegalAccessException {

    Changes changes = new Changes(dataSource).setStartPointNow().setEndPointNow();
    ChangesAssert assertion = assertThat(changes).ofCreationOnTable("movie");

    Field field = AbstractAssert.class.getDeclaredField("info");
    field.setAccessible(true);
    WritableAssertionInfo info = (WritableAssertionInfo) field.get(assertion);

    assertThat(info.descriptionText()).isEqualTo("Changes on tables of a data source (only creation changes on movie table)");
  }

  /**
   * This method tests the description of changes on datasource (only modification on a table).
   * 
   * @throws IllegalAccessException
   * @throws IllegalArgumentException
   * @throws NoSuchFieldException
   * @throws SecurityException
   */
  @Test
  public void test_default_description_of_changes_on_datasource_only_modification_on_a_table() throws NoSuchFieldException, SecurityException,
      IllegalArgumentException, IllegalAccessException {

    Changes changes = new Changes(dataSource).setStartPointNow().setEndPointNow();
    ChangesAssert assertion = assertThat(changes).ofModificationOnTable("movie");

    Field field = AbstractAssert.class.getDeclaredField("info");
    field.setAccessible(true);
    WritableAssertionInfo info = (WritableAssertionInfo) field.get(assertion);

    assertThat(info.descriptionText()).isEqualTo("Changes on tables of a data source (only modification changes on movie table)");
  }

  /**
   * This method tests the description of changes on datasource (only deletion on a table).
   * 
   * @throws IllegalAccessException
   * @throws IllegalArgumentException
   * @throws NoSuchFieldException
   * @throws SecurityException
   */
  @Test
  public void test_default_description_of_changes_on_datasource_only_deletion_on_a_table() throws NoSuchFieldException, SecurityException,
      IllegalArgumentException, IllegalAccessException {

    Changes changes = new Changes(dataSource).setStartPointNow().setEndPointNow();
    ChangesAssert assertion = assertThat(changes).ofDeletionOnTable("movie");

    Field field = AbstractAssert.class.getDeclaredField("info");
    field.setAccessible(true);
    WritableAssertionInfo info = (WritableAssertionInfo) field.get(assertion);

    assertThat(info.descriptionText()).isEqualTo("Changes on tables of a data source (only deletion changes on movie table)");
  }

}
