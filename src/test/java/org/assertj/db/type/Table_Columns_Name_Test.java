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

import org.assertj.db.common.AbstractTest;
import org.assertj.db.exception.AssertJDBException;
import org.junit.Test;

/**
 * Tests on the columns name of {@code Table}.
 * <p>
 * These tests are on the name of the columns got from a {@code Table}.
 * </p>
 *
 * @author Régis Pouiller
 * @author Julien Roy
 */
public class Table_Columns_Name_Test extends AbstractTest {

  /**
   * This method test the columns got from a {@code Connection}.
   */
  @Test
  public void test_columns_name() {
    Table table = assertDbConnection.table("movie").build();

    assertThat(table.getColumnsNameList()).as("Columns of MOVIE table").hasSize(4)
      .containsExactly("ID", "TITLE", "YEAR", "MOVIE_IMDB");
  }

  /**
   * This method test the columns got from a {@code Connection} when the columns to check are set.
   */
  @Test
  public void test_columns_name_to_check() {
    Table table = assertDbConnection.table("actor").columnsToCheck(new String[]{"id", "name", "birth"}).build();

    assertThat(table.getColumnsNameList()).as("Columns of ACTOR table").hasSize(3)
      .containsExactly("ID", "NAME", "BIRTH");
  }

  /**
   * This method test the columns got from a {@code Connection} when the columns to exclude are set.
   */
  @Test
  public void test_columns_name_to_exclude() {
    Table table = assertDbConnection.table("interpretation").columnsToExclude(new String[]{"id"}).build();

    assertThat(table.getColumnsNameList()).as("Columns of INTERPRETATION table").hasSize(3)
      .containsExactly("ID_MOVIE", "ID_ACTOR", "CHARACTER");
  }


  /**
   * This method should throw a {@code AssertJDBException} because of a {@code SQLException} caused by a table not
   * found.
   */
  @Test(expected = AssertJDBException.class)
  public void should_throw_AssertJDBException_because_SQLException_caused_by_table_not_found() {
    Table table = assertDbConnection.table("interpret").build();
    table.getColumnsNameList();
  }
}
