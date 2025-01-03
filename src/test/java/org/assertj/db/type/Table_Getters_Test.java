/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2015-2024 the original author or authors.
 */
package org.assertj.db.type;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.type.Table.Order.asc;
import static org.assertj.db.type.Table.Order.desc;

import org.assertj.db.common.AbstractTest;
import org.junit.Test;

/**
 * Tests on the getters of {@code Table}.
 * <p>
 * These tests are on the return from the getters of {@code Table}.
 * </p>
 *
 * @author Régis Pouiller
 * @author Julien Roy
 */
public class Table_Getters_Test extends AbstractTest {

  /**
   * This method test the getters of a {@code Table} when the connection provider and the name are set.
   */
  @Test
  public void test_getters_with_connection_provider_and_name_set() {
    Table table = assertDbConnection.table("movie").build();

    assertThat(table.getConnectionProvider()).as("Source of MOVIE table").isNotNull();
    assertThat(table.getName()).isEqualTo("MOVIE");
    assertThat(table.getColumnsToCheck()).isNull();
    assertThat(table.getColumnsToExclude()).isNull();
    assertThat(table.getRequest()).isEqualTo("SELECT * FROM MOVIE");
    assertThat(table.getStartDelimiter()).isNull();
    assertThat(table.getEndDelimiter()).isNull();
  }

  /**
   * This method test the getters of a {@code Table} when the connection provider, the name and the information about the columns
   * are set.
   */
  @Test
  public void test_getters_with_connection_provider_name_and_columns_set() {
    Table table = assertDbConnection.table("movie")
      .columnsToCheck(new String[]{"title", "year"})
      .columnsToExclude(new String[]{"id"})
      .build();

    assertThat(table.getConnectionProvider()).as("Source of MOVIE table").isNotNull();
    assertThat(table.getName()).isEqualTo("MOVIE");
    assertThat(table.getColumnsToCheck()).containsExactly("TITLE", "YEAR");
    assertThat(table.getColumnsToExclude()).containsExactly("ID");
    assertThat(table.getRequest()).isEqualTo("SELECT TITLE, YEAR FROM MOVIE");
    assertThat(table.getStartDelimiter()).isNull();
    assertThat(table.getEndDelimiter()).isNull();
  }

  /**
   * This method test the getters of a {@code Table} when the connection provider, the name and the information about the columns
   * and the orders are set.
   */
  @Test
  public void test_getters_with_connection_provider_name_columns_and_order_set() {
    Table table = assertDbConnection.table("movie")
      .columnsToOrder(new Table.Order[]{asc("title"), desc("year")})
      .columnsToCheck(new String[]{"title", "year"})
      .columnsToExclude(new String[]{"id"})
      .build();

    assertThat(table.getConnectionProvider()).as("Source of MOVIE table").isNotNull();
    assertThat(table.getName()).isEqualTo("MOVIE");
    assertThat(table.getColumnsToCheck()).containsExactly("TITLE", "YEAR");
    assertThat(table.getColumnsToExclude()).containsExactly("ID");
    assertThat(table.getColumnsToOrder()).containsExactly(asc("TITLE"), desc("YEAR"));
    assertThat(table.getRequest()).isEqualTo("SELECT TITLE, YEAR FROM MOVIE ORDER BY TITLE, YEAR DESC");
    assertThat(table.getStartDelimiter()).isNull();
    assertThat(table.getEndDelimiter()).isNull();
  }

  /**
   * This method test the getters of a {@code Table} when the connection provider, the name and the information about the orders
   * are set.
   */
  @Test
  public void test_getters_with_connection_provider_name_and_order_set() {
    Table table = assertDbConnection.table("movie")
      .columnsToOrder(new Table.Order[]{asc("title"), desc("year")})
      .build();

    assertThat(table.getConnectionProvider()).as("Source of MOVIE table").isNotNull();
    assertThat(table.getName()).isEqualTo("MOVIE");
    assertThat(table.getColumnsToCheck()).isNull();
    assertThat(table.getColumnsToExclude()).isNull();
    assertThat(table.getColumnsToOrder()).containsExactly(asc("TITLE"), desc("YEAR"));
    assertThat(table.getRequest()).isEqualTo("SELECT * FROM MOVIE ORDER BY TITLE, YEAR DESC");
    assertThat(table.getStartDelimiter()).isNull();
    assertThat(table.getEndDelimiter()).isNull();
  }

  /**
   * This method test the getters of a {@code Table} when the connection provider, delimiters and the name are set.
   */
  @Test
  public void test_getters_with_connection_provider_delimiters_and_name_set() {
    Table table = assertDbConnection.table("movie").delimiters('1', '2').build();

    assertThat(table.getConnectionProvider()).as("Source of MOVIE table").isNotNull();
    assertThat(table.getName()).isEqualTo("MOVIE");
    assertThat(table.getColumnsToCheck()).isNull();
    assertThat(table.getColumnsToExclude()).isNull();
    assertThat(table.getRequest()).isEqualTo("SELECT * FROM 1MOVIE2");
    assertThat(table.getStartDelimiter()).isEqualTo('1');
    assertThat(table.getEndDelimiter()).isEqualTo('2');
  }

  /**
   * This method test the getters of a {@code Table} when the connection provider, the name, the delimiters
   * and the information about the columns are set.
   */
  @Test
  public void test_getters_with_connection_provider_name_delimiters_and_columns_set() {
    Table table = assertDbConnection.table("movie").delimiters('1', '2')
      .columnsToCheck(new String[]{"title", "year"})
      .columnsToExclude(new String[]{"id"})
      .build();

    assertThat(table.getConnectionProvider()).as("Source of MOVIE table").isNotNull();
    assertThat(table.getName()).isEqualTo("MOVIE");
    assertThat(table.getColumnsToCheck()).containsExactly("TITLE", "YEAR");
    assertThat(table.getColumnsToExclude()).containsExactly("ID");
    assertThat(table.getRequest()).isEqualTo("SELECT 1TITLE2, 1YEAR2 FROM 1MOVIE2");
    assertThat(table.getStartDelimiter()).isEqualTo('1');
    assertThat(table.getEndDelimiter()).isEqualTo('2');
  }

  /**
   * This method test the getters of a {@code Table} when the connection provider, the name, the delimiters
   * and the information about the columns and the orders are set.
   */
  @Test
  public void test_getters_with_connection_provider_name_columns_delimiters_and_order_set() {
    Table table = assertDbConnection.table("movie").delimiters('1', '2')
      .columnsToOrder(new Table.Order[]{asc("title"), desc("year")})
      .columnsToCheck(new String[]{"title", "year"})
      .columnsToExclude(new String[]{"id"})
      .build();

    assertThat(table.getConnectionProvider()).as("Source of MOVIE table").isNotNull();
    assertThat(table.getName()).isEqualTo("MOVIE");
    assertThat(table.getColumnsToCheck()).containsExactly("TITLE", "YEAR");
    assertThat(table.getColumnsToExclude()).containsExactly("ID");
    assertThat(table.getColumnsToOrder()).containsExactly(asc("TITLE"), desc("YEAR"));
    assertThat(table.getRequest()).isEqualTo("SELECT 1TITLE2, 1YEAR2 FROM 1MOVIE2 ORDER BY 1TITLE2, 1YEAR2 DESC");
    assertThat(table.getStartDelimiter()).isEqualTo('1');
    assertThat(table.getEndDelimiter()).isEqualTo('2');
  }

  /**
   * This method test the getters of a {@code Table} when the connection provider, the name, the delimiters
   * and the information about the orders are set.
   */
  @Test
  public void test_getters_with_connection_provider_name_delimiters_and_order_set() {
    Table table = assertDbConnection.table("movie").delimiters('1', '2')
      .columnsToOrder(new Table.Order[]{asc("title"), desc("year")})
      .build();

    assertThat(table.getConnectionProvider()).as("Source of MOVIE table").isNotNull();
    assertThat(table.getName()).isEqualTo("MOVIE");
    assertThat(table.getColumnsToCheck()).isNull();
    assertThat(table.getColumnsToExclude()).isNull();
    assertThat(table.getColumnsToOrder()).containsExactly(asc("TITLE"), desc("YEAR"));
    assertThat(table.getRequest()).isEqualTo("SELECT * FROM 1MOVIE2 ORDER BY 1TITLE2, 1YEAR2 DESC");
    assertThat(table.getStartDelimiter()).isEqualTo('1');
    assertThat(table.getEndDelimiter()).isEqualTo('2');
  }
}
