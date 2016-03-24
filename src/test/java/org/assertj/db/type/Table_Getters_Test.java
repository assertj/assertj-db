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
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests on the getters of {@code Table}.
 * <p>
 * These tests are on the return from the getters of {@code Table}.
 * </p>
 * 
 * @author Régis Pouiller
 * 
 */
public class Table_Getters_Test extends AbstractTest {

  /**
   * This method test the getters of a {@code Table} when only the source is set.
   */
  @Test
  public void test_getters_with_only_source_set() {
    Table table = new Table().setSource(source);

    assertThat(table.getSource()).as("Source of MOVIE table").isSameAs(source);
    assertThat(table.getDataSource()).isNull();
    assertThat(table.getName()).isNull();
    assertThat(table.getColumnsToCheck()).isNull();
    assertThat(table.getColumnsToExclude()).isNull();
  }

  /**
   * This method test the getters of a {@code Table} when only the datasource is set.
   */
  @Test
  public void test_getters_with_only_datasource_set() {
    Table table = new Table().setDataSource(dataSource);

    assertThat(table.getSource()).isNull();
    assertThat(table.getDataSource()).as("DataSource of MOVIE table").isSameAs(dataSource);
    assertThat(table.getName()).isNull();
    assertThat(table.getColumnsToCheck()).isNull();
    assertThat(table.getColumnsToExclude()).isNull();
  }

  /**
   * This method test the getters of a {@code Table} when only the source is set after the datasource was set.
   */
  @Test
  public void test_getters_with_only_datasource_set_and_after_only_source_set() {
    Table table = new Table().setDataSource(dataSource).setSource(source);

    assertThat(table.getSource()).as("Source of MOVIE table").isSameAs(source);
    assertThat(table.getDataSource()).isNull();
    assertThat(table.getName()).isNull();
    assertThat(table.getColumnsToCheck()).isNull();
    assertThat(table.getColumnsToExclude()).isNull();
  }

  /**
   * This method test the getters of a {@code Table} when only the datasource is set after the source was set.
   */
  @Test
  public void test_getters_with_only_source_set_and_after_only_datasource_set() {
    Table table = new Table().setSource(source).setDataSource(dataSource);

    assertThat(table.getSource()).isNull();
    assertThat(table.getDataSource()).as("DataSource of MOVIE table").isSameAs(dataSource);
    assertThat(table.getName()).isNull();
    assertThat(table.getColumnsToCheck()).isNull();
    assertThat(table.getColumnsToExclude()).isNull();
  }

  /**
   * This method test the getters of a {@code Table} when the source and the name are set.
   */
  @Test
  public void test_getters_with_source_and_name_set() {
    Table table = new Table(source, "movie");

    assertThat(table.getSource()).as("Source of MOVIE table").isSameAs(source);
    assertThat(table.getDataSource()).isNull();
    assertThat(table.getName()).isEqualTo("MOVIE");
    assertThat(table.getColumnsToCheck()).isNull();
    assertThat(table.getColumnsToExclude()).isNull();
    assertThat(table.getRequest()).isEqualTo("SELECT * FROM MOVIE");
  }

  /**
   * This method test the getters of a {@code Table} when the source, the name and the informations about the columns
   * are set.
   */
  @Test
  public void test_getters_with_source_name_and_columns_set() {
    Table table = new Table(source, "movie", new String[] { "title", "year" }, new String[] { "id" });

    assertThat(table.getSource()).as("Source of MOVIE table").isSameAs(source);
    assertThat(table.getDataSource()).isNull();
    assertThat(table.getName()).isEqualTo("MOVIE");
    assertThat(table.getColumnsToCheck()).containsExactly("TITLE", "YEAR");
    assertThat(table.getColumnsToExclude()).containsExactly("ID");
    assertThat(table.getRequest()).isEqualTo("SELECT TITLE, YEAR FROM MOVIE");
  }

}
