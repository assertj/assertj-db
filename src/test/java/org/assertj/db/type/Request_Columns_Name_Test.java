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
 * Tests on the columns name of {@code Request}.
 * <p>
 * These tests are on the name of the columns got from a {@code Request}.
 * </p>
 *
 * @author Régis Pouiller
 * @author Julien Roy
 */
public class Request_Columns_Name_Test extends AbstractTest {

  /**
   * This method test the columns name got from a {@code ConnectionProvider}.
   */
  @Test
  public void test_columns_name() {
    Request request = assertDbConnection.request("SELECT actor.name, actor.firstname, movie.year, interpretation.character "
      + " FROM movie, actor, interpretation"
      + " WHERE movie.id = interpretation.id_movie"
      + " AND interpretation.id_actor = actor.id"
      + " ORDER BY actor.name, movie.year").build();

    assertThat(request.getColumnsNameList()).as("Columns of the request")
      .hasSize(4)
      .containsExactly("NAME", "FIRSTNAME", "YEAR", "CHARACTER");
  }

  /**
   * This method test the columns name got from a {@code ConnectionProvider}.
   */
  @Test
  public void test_columns_name_with_parameters_set() {
    Request request = assertDbConnection.request(
        "SELECT actor.name, actor.firstname, movie.year, interpretation.character "
          + " FROM movie, actor, interpretation"
          + " WHERE movie.id = interpretation.id_movie"
          + " AND interpretation.id_actor = actor.id"
          + " AND movie.year > ?"
          + " ORDER BY actor.name, movie.year")
      .parameters(2000)
      .build();

    assertThat(request.getColumnsNameList()).as("Columns of the request")
      .hasSize(4)
      .containsExactly("NAME", "FIRSTNAME", "YEAR", "CHARACTER");
  }

  /**
   * This method should throw a {@code AssertJDBException} because of a {@code SQLException} caused by a table not
   * found.
   */
  @Test(expected = AssertJDBException.class)
  public void should_throw_AssertJDBException_because_SQLException_caused_by_table_not_found() {
    Table table = assertDbConnection.table("select * from interpret").build();
    table.getColumnsNameList();
  }

}
