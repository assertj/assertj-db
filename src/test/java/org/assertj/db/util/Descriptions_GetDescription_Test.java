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
package org.assertj.db.util;

import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.assertj.db.type.ChangeType;
import org.assertj.db.type.Request;
import org.assertj.db.type.Table;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests on {@code getDescription} method from utility class {@code Descriptions}.
 *
 * @author Régis Pouiller
 */
public class Descriptions_GetDescription_Test extends AbstractTest {

  /**
   * This method tests the {@code getDescription} method for the {@code Table}s.
   */
  @Test
  public void test_get_description_for_table() {
    Table fromSource = new Table(source, "actor");
    Table fromDataSource = new Table(dataSource, "actor");

    String descriptionFromSource = Descriptions.getDescription(fromSource);
    String descriptionFromDataSource = Descriptions.getDescription(fromDataSource);

    assertThat(descriptionFromSource).isEqualTo("ACTOR table");
    assertThat(descriptionFromDataSource).isEqualTo("ACTOR table");
  }

  /**
   * This method tests the {@code getDescription} method for the {@code Request}s.
   */
  @Test
  public void test_get_description_for_request() {
    Request fromSource = new Request(source, "select * from actor");
    Request fromDataSource = new Request(dataSource, "select * from actor");
    Request fromSourceLong = new Request(source, "select id, name, firstname, birth, actor_imdb from actor");
    Request fromDataSourceLong = new Request(dataSource, "select id, name, firstname, birth, actor_imdb from actor");

    String descriptionFromSource = Descriptions.getDescription(fromSource);
    String descriptionFromDataSource = Descriptions.getDescription(fromDataSource);
    String descriptionFromSourceLong = Descriptions.getDescription(fromSourceLong);
    String descriptionFromDataSourceLong = Descriptions.getDescription(fromDataSourceLong);

    assertThat(descriptionFromSource).isEqualTo("'select * from actor' request");
    assertThat(descriptionFromDataSource).isEqualTo("'select * from actor' request");
    assertThat(descriptionFromSourceLong).isEqualTo("'select id, name, firstname, bi...' request");
    assertThat(descriptionFromDataSourceLong).isEqualTo("'select id, name, firstname, bi...' request");
  }

  /**
   * This method tests the {@code getDescription} method for the {@code Changes}.
   */
  @Test
  @NeedReload
  public void test_get_description_for_changes() {
    org.assertj.db.type.Changes fromSource = new org.assertj.db.type.Changes(source).setStartPointNow();
    org.assertj.db.type.Changes fromDataSource = new org.assertj.db.type.Changes(dataSource).setStartPointNow();
    updateChangesForTests();
    fromSource.setEndPointNow();
    fromDataSource.setEndPointNow();

    String descriptionFromSource = Descriptions.getDescription(fromSource);
    String descriptionFromDataSource = Descriptions.getDescription(fromDataSource);

    assertThat(descriptionFromSource).isEqualTo("Changes on tables of 'sa/jdbc:h2:mem:test' source");
    assertThat(descriptionFromDataSource).isEqualTo("Changes on tables of a data source");
  }

  /**
   * This method tests the {@code getDescription} method for the {@code Changes}.
   */
  @Test
  @NeedReload
  public void test_get_description_for_changes_from_table() {
    org.assertj.db.type.Changes fromSource = new org.assertj.db.type.Changes(new Table(source, "actor"))
            .setStartPointNow();
    org.assertj.db.type.Changes fromDataSource = new org.assertj.db.type.Changes(new Table(dataSource, "actor"))
            .setStartPointNow();
    updateChangesForTests();
    fromSource.setEndPointNow();
    fromDataSource.setEndPointNow();

    String descriptionFromSource = Descriptions.getDescription(fromSource);
    String descriptionFromDataSource = Descriptions.getDescription(fromDataSource);

    assertThat(descriptionFromSource).isEqualTo("Changes on ACTOR table of 'sa/jdbc:h2:mem:test' source");
    assertThat(descriptionFromDataSource).isEqualTo("Changes on ACTOR table of a data source");
  }

  /**
   * This method tests the {@code getDescription} method for the {@code Changes}.
   */
  @Test
  @NeedReload
  public void test_get_description_for_changes_from_request() {
    org.assertj.db.type.Changes fromSource = new org.assertj.db.type.Changes(new Request(source, "select * from actor"))
            .setStartPointNow();
    org.assertj.db.type.Changes fromDataSource = new org.assertj.db.type.Changes(
            new Request(dataSource, "select * from actor")).setStartPointNow();
    org.assertj.db.type.Changes fromSourceLong = new org.assertj.db.type.Changes(
            new Request(source, "select id, name, firstname, birth, actor_imdb from actor")).setStartPointNow();
    org.assertj.db.type.Changes fromDataSourceLong = new org.assertj.db.type.Changes(
            new Request(dataSource, "select id, name, firstname, birth, actor_imdb from actor")).setStartPointNow();
    updateChangesForTests();
    fromSource.setEndPointNow();
    fromDataSource.setEndPointNow();
    fromSourceLong.setEndPointNow();
    fromDataSourceLong.setEndPointNow();

    String descriptionFromSource = Descriptions.getDescription(fromSource);
    String descriptionFromDataSource = Descriptions.getDescription(fromDataSource);
    String descriptionFromSourceLong = Descriptions.getDescription(fromSourceLong);
    String descriptionFromDataSourceLong = Descriptions.getDescription(fromDataSourceLong);

    assertThat(descriptionFromSource).isEqualTo("Changes on 'select * from actor' request of 'sa/jdbc:h2:mem:test' source");
    assertThat(descriptionFromDataSource).isEqualTo("Changes on 'select * from actor' request of a data source");
    assertThat(descriptionFromSourceLong).isEqualTo("Changes on 'select id, name, firstname, bi...' request of 'sa/jdbc:h2:mem:test' source");
    assertThat(descriptionFromDataSourceLong).isEqualTo("Changes on 'select id, name, firstname, bi...' request of a data source");
  }

  /**
   * This method tests the {@code getDescription} method for the {@code row}.
   */
  @Test
  @NeedReload
  public void test_get_description_for_row() {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");

    String description = Descriptions.getRowDescription(info, 2);

    assertThat(description).isEqualTo("Row at index 2 of description");
  }

  /**
   * This method tests the {@code getDescription} method for the {@code row}.
   */
  @Test
  @NeedReload
  public void test_get_description_for_row_at_start_point() {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");

    String description = Descriptions.getRowAtStartPointDescription(info);

    assertThat(description).isEqualTo("Row at start point of description");
  }

  /**
   * This method tests the {@code getDescription} method for the {@code row}.
   */
  @Test
  @NeedReload
  public void test_get_description_for_row_at_end_point() {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");

    String description = Descriptions.getRowAtEndPointDescription(info);

    assertThat(description).isEqualTo("Row at end point of description");
  }

  /**
   * This method tests the {@code getDescription} method for the {@code column}.
   */
  @Test
  @NeedReload
  public void test_get_description_for_column() {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");

    String description = Descriptions.getColumnDescription(info, 2, "name");

    assertThat(description).isEqualTo("Column at index 2 (column name : name) of description");
  }

  /**
   * This method tests the {@code getDescription} method for the {@code value} of {@code column}.
   */
  @Test
  @NeedReload
  public void test_get_description_for_column_value() {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");

    String description = Descriptions.getColumnValueDescription(info, 2);

    assertThat(description).isEqualTo("Value at index 2 of description");
  }

  /**
   * This method tests the {@code getDescription} method for the {@code value} at start point of {@code column}.
   */
  @Test
  @NeedReload
  public void test_get_description_for_column_value_at_start_point() {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");

    String description = Descriptions.getColumnValueAtStartPointDescription(info);

    assertThat(description).isEqualTo("Value at start point of description");
  }

  /**
   * This method tests the {@code getDescription} method for the {@code value} at end point of {@code column}.
   */
  @Test
  @NeedReload
  public void test_get_description_for_column_value_at_end_point() {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");

    String description = Descriptions.getColumnValueAtEndPointDescription(info);

    assertThat(description).isEqualTo("Value at end point of description");
  }

  /**
   * This method tests the {@code getDescription} method for the {@code changes} from {@code changes}.
   */
  @Test
  @NeedReload
  public void test_get_description_for_changes_from_changes() {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");

    String description0 = Descriptions.getChangesDescription(info, null, null);
    String description1 = Descriptions.getChangesDescription(info, ChangeType.CREATION, null);
    String description2 = Descriptions.getChangesDescription(info, null, "table");
    String description3 = Descriptions.getChangesDescription(info, ChangeType.CREATION, "table");

    assertThat(description0).isEqualTo("description");
    assertThat(description1).isEqualTo("description (only creation changes)");
    assertThat(description2).isEqualTo("description (only changes on table table)");
    assertThat(description3).isEqualTo("description (only creation changes on table table)");
  }

  /**
   * This method tests the {@code getDescription} method for the {@code change} from {@code changes} from {@code table}.
   */
  @Test
  @NeedReload
  public void test_get_description_for_change_from_changes_from_table() {
    org.assertj.db.type.Changes fromSource = new org.assertj.db.type.Changes(new Table(source, "actor"))
            .setStartPointNow();
    org.assertj.db.type.Changes fromDataSource = new org.assertj.db.type.Changes(new Table(dataSource, "actor"))
            .setStartPointNow();
    updateChangesForTests();
    fromSource.setEndPointNow();
    fromDataSource.setEndPointNow();

    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");

    String descriptionFromSource0 = Descriptions.getChangeDescription(info, fromSource, fromSource.getChangesList().get(0), 1, null, null);
    String descriptionFromSource1 = Descriptions.getChangeDescription(info, fromSource, fromSource.getChangesList().get(0), 1, ChangeType.CREATION, null);
    String descriptionFromSource2 = Descriptions.getChangeDescription(info, fromSource, fromSource.getChangesList().get(0), 1, null, "table");
    String descriptionFromSource3 = Descriptions.getChangeDescription(info, fromSource, fromSource.getChangesList().get(0), 1, ChangeType.CREATION, "table");
    String descriptionFromDataSource0 = Descriptions.getChangeDescription(info, fromDataSource, fromDataSource.getChangesList().get(0), 1, null, null);
    String descriptionFromDataSource1 = Descriptions.getChangeDescription(info, fromDataSource, fromDataSource.getChangesList().get(0), 1, ChangeType.CREATION, null);
    String descriptionFromDataSource2 = Descriptions.getChangeDescription(info, fromDataSource, fromDataSource.getChangesList().get(0), 1, null, "table");
    String descriptionFromDataSource3 = Descriptions.getChangeDescription(info, fromDataSource, fromDataSource.getChangesList().get(0), 1, ChangeType.CREATION, "table");

    assertThat(descriptionFromSource0).isEqualTo("Change at index 1 (with primary key : [4]) of description");
    assertThat(descriptionFromSource1).isEqualTo("Change at index 1 (with primary key : [4]) of description (only creation changes)");
    assertThat(descriptionFromSource2).isEqualTo("Change at index 1 (with primary key : [4]) of description (only changes on table table)");
    assertThat(descriptionFromSource3).isEqualTo("Change at index 1 (with primary key : [4]) of description (only creation changes on table table)");
    assertThat(descriptionFromDataSource0).isEqualTo("Change at index 1 (with primary key : [4]) of description");
    assertThat(descriptionFromDataSource1).isEqualTo("Change at index 1 (with primary key : [4]) of description (only creation changes)");
    assertThat(descriptionFromDataSource2).isEqualTo("Change at index 1 (with primary key : [4]) of description (only changes on table table)");
    assertThat(descriptionFromDataSource3).isEqualTo("Change at index 1 (with primary key : [4]) of description (only creation changes on table table)");
  }

  /**
   * This method tests the {@code getDescription} method for the {@code change} from {@code changes} from {@code request}.
   */
  @Test
  @NeedReload
  public void test_get_description_for_change_from_changes_from_request() {
    org.assertj.db.type.Changes fromSource = new org.assertj.db.type.Changes(new Request(source, "select * from actor"))
            .setStartPointNow();
    org.assertj.db.type.Changes fromDataSource = new org.assertj.db.type.Changes(
            new Request(dataSource, "select * from actor")).setStartPointNow();
    org.assertj.db.type.Changes fromSourceLong = new org.assertj.db.type.Changes(
            new Request(source, "select id, name, firstname, birth, actor_imdb from actor")).setStartPointNow();
    org.assertj.db.type.Changes fromDataSourceLong = new org.assertj.db.type.Changes(
            new Request(dataSource, "select id, name, firstname, birth, actor_imdb from actor")).setStartPointNow();
    updateChangesForTests();
    fromSource.setEndPointNow();
    fromDataSource.setEndPointNow();
    fromSourceLong.setEndPointNow();
    fromDataSourceLong.setEndPointNow();

    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");

    String descriptionFromSource0 = Descriptions.getChangeDescription(info, fromSource, fromSource.getChangesList().get(0), 1, null, null);
    String descriptionFromSource1 = Descriptions.getChangeDescription(info, fromSource, fromSource.getChangesList().get(0), 1, ChangeType.CREATION, null);
    String descriptionFromSource2 = Descriptions.getChangeDescription(info, fromSource, fromSource.getChangesList().get(0), 1, null, "table");
    String descriptionFromSource3 = Descriptions.getChangeDescription(info, fromSource, fromSource.getChangesList().get(0), 1, ChangeType.CREATION, "table");
    String descriptionFromDataSource0 = Descriptions.getChangeDescription(info, fromDataSource, fromDataSource.getChangesList().get(0), 1, null, null);
    String descriptionFromDataSource1 = Descriptions.getChangeDescription(info, fromDataSource, fromDataSource.getChangesList().get(0), 1, ChangeType.CREATION, null);
    String descriptionFromDataSource2 = Descriptions.getChangeDescription(info, fromDataSource, fromDataSource.getChangesList().get(0), 1, null, "table");
    String descriptionFromDataSource3 = Descriptions.getChangeDescription(info, fromDataSource, fromDataSource.getChangesList().get(0), 1, ChangeType.CREATION, "table");
    String descriptionFromSourceLong0 = Descriptions.getChangeDescription(info, fromSourceLong, fromSourceLong.getChangesList().get(0), 1, null, null);
    String descriptionFromSourceLong1 = Descriptions.getChangeDescription(info, fromSourceLong, fromSourceLong.getChangesList().get(0), 1, ChangeType.CREATION, null);
    String descriptionFromSourceLong2 = Descriptions.getChangeDescription(info, fromSourceLong, fromSourceLong.getChangesList().get(0), 1, null, "table");
    String descriptionFromSourceLong3 = Descriptions.getChangeDescription(info, fromSourceLong, fromSourceLong.getChangesList().get(0), 1, ChangeType.CREATION, "table");
    String descriptionFromDataSourceLong0 = Descriptions.getChangeDescription(info, fromDataSourceLong, fromDataSourceLong.getChangesList().get(0), 1, null, null);
    String descriptionFromDataSourceLong1 = Descriptions.getChangeDescription(info, fromDataSourceLong, fromDataSourceLong.getChangesList().get(0), 1, ChangeType.CREATION, null);
    String descriptionFromDataSourceLong2 = Descriptions.getChangeDescription(info, fromDataSourceLong, fromDataSourceLong.getChangesList().get(0), 1, null, "table");
    String descriptionFromDataSourceLong3 = Descriptions.getChangeDescription(info, fromDataSourceLong, fromDataSourceLong.getChangesList().get(0), 1, ChangeType.CREATION, "table");

    assertThat(descriptionFromSource0).isEqualTo("Change at index 1 of description");
    assertThat(descriptionFromSource1).isEqualTo("Change at index 1 of description (only creation changes)");
    assertThat(descriptionFromSource2).isEqualTo("Change at index 1 of description (only changes on table table)");
    assertThat(descriptionFromSource3).isEqualTo("Change at index 1 of description (only creation changes on table table)");
    assertThat(descriptionFromDataSource0).isEqualTo("Change at index 1 of description");
    assertThat(descriptionFromDataSource1).isEqualTo("Change at index 1 of description (only creation changes)");
    assertThat(descriptionFromDataSource2).isEqualTo("Change at index 1 of description (only changes on table table)");
    assertThat(descriptionFromDataSource3).isEqualTo("Change at index 1 of description (only creation changes on table table)");
    assertThat(descriptionFromSourceLong0).isEqualTo("Change at index 1 of description");
    assertThat(descriptionFromSourceLong1).isEqualTo("Change at index 1 of description (only creation changes)");
    assertThat(descriptionFromSourceLong2).isEqualTo("Change at index 1 of description (only changes on table table)");
    assertThat(descriptionFromSourceLong3).isEqualTo("Change at index 1 of description (only creation changes on table table)");
    assertThat(descriptionFromDataSourceLong0).isEqualTo("Change at index 1 of description");
    assertThat(descriptionFromDataSourceLong1).isEqualTo("Change at index 1 of description (only creation changes)");
    assertThat(descriptionFromDataSourceLong2).isEqualTo("Change at index 1 of description (only changes on table table)");
    assertThat(descriptionFromDataSourceLong3).isEqualTo("Change at index 1 of description (only creation changes on table table)");
  }
}
