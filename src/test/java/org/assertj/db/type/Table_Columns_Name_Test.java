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
package org.assertj.db.type;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.exception.AssertJDBException;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests on the columns name of {@code Table}.
 * <p>
 * These tests are on the name of the columns got from a {@code Table}.
 * </p>
 * 
 * @author Régis Pouiller
 * 
 */
public class Table_Columns_Name_Test extends AbstractTest {

  /**
   * This method test the columns got from a {@code Source}.
   */
  @Test
  public void test_columns_name_with_source_set() {
    Table table = new Table(source, "movie");

    assertThat(table.getColumnsNameList()).as("Columns of MOVIE table").hasSize(3)
        .containsExactly("ID", "TITLE", "YEAR");
  }

  /**
   * This method test the columns got from a {@code DataSource}.
   */
  @Test
  public void test_columns_name_with_datasource_set() {
    Table table = new Table(dataSource, "movie");

    assertThat(table.getColumnsNameList()).as("Columns of MOVIE table").hasSize(3)
        .containsExactly("ID", "TITLE", "YEAR");
  }

  /**
   * This method test the columns got from a {@code Source} when the columns to check are set.
   */
  @Test
  public void test_columns_name_to_check_with_source_set() {
    Table table = new Table(source, "actor", new String[] { "id", "name", "birth" }, null);

    assertThat(table.getColumnsNameList()).as("Columns of ACTOR table").hasSize(3)
        .containsExactly("ID", "NAME", "BIRTH");
  }

  /**
   * This method test the columns got from a {@code DataSource} when the columns to check are set.
   */
  @Test
  public void test_columns_name_to_check_with_datasource_set() {
    Table table = new Table(dataSource, "actor", new String[] { "id", "name", "birth" }, null);

    assertThat(table.getColumnsNameList()).as("Columns of ACTOR table").hasSize(3)
        .containsExactly("ID", "NAME", "BIRTH");
  }

  /**
   * This method test the columns got from a {@code Source} when the columns to exclude are set.
   */
  @Test
  public void test_columns_name_to_exclude_with_source_set() {
    Table table = new Table(source, "interpretation", null, new String[] { "id" });

    assertThat(table.getColumnsNameList()).as("Columns of INTERPRETATION table").hasSize(3)
        .containsExactly("ID_MOVIE", "ID_ACTOR", "CHARACTER");
  }

  /**
   * This method test the columns got from a {@code DataSource} when the columns to exclude are set.
   */
  @Test
  public void test_columns_name_to_exclude_with_datasource_set() {
    Table table = new Table(dataSource, "interpretation", null, new String[] { "id" });

    assertThat(table.getColumnsNameList()).as("Columns of INTERPRETATION table").hasSize(3)
        .containsExactly("ID_MOVIE", "ID_ACTOR", "CHARACTER");
  }

  /**
   * This method should throw a {@code AssertJDBException} because of a {@code SQLException} caused by a table not
   * found.
   */
  @Test(expected = AssertJDBException.class)
  public void should_throw_AssertJDBException_because_SQLException_caused_by_table_not_found() {
    Table table = new Table(dataSource, "interpret");
    table.getColumnsNameList();
  }
}
