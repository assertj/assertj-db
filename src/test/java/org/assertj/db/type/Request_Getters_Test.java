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
import org.junit.Test;

/**
 * Tests on the getters of {@code Request}.
 * <p>
 * These tests are on the return from the getters of {@code Request}.
 * </p>
 *
 * @author Régis Pouiller
 * @author Julien Roy
 */
public class Request_Getters_Test extends AbstractTest {

  /**
   * This method test the getters of a {@code Table} when only the jdbc connection is set.
   */
  @Test
  public void test_getters_with_only_jdbc_connection_set() {
    Request request = new Request().setConnectionProvider(jdbcConnectionProvider);

    assertThat(request.getConnectionProvider()).isSameAs(jdbcConnectionProvider);
    assertThat(request.getRequest()).isNull();
    assertThat(request.getParameters()).isNull();
  }

  /**
   * This method test the getters of a {@code Table} when the jdbc connection and the name are set.
   */
  @Test
  public void test_getters_with_jdbc_and_name_set() {
    Request request = new Request(jdbcConnectionProvider,
      "SELECT actor.name, actor.firstname, movie.year, interpretation.character "
        + " FROM movie, actor, interpretation"
        + " WHERE movie.id = interpretation.id_movie"
        + " AND interpretation.id_actor = actor.id"
        + " ORDER BY actor.name, movie.year");

    assertThat(request.getConnectionProvider()).as("Source of MOVIE table").isSameAs(jdbcConnectionProvider);
    assertThat(request.getRequest()).isEqualTo("SELECT actor.name, actor.firstname, movie.year, interpretation.character "
      + " FROM movie, actor, interpretation"
      + " WHERE movie.id = interpretation.id_movie"
      + " AND interpretation.id_actor = actor.id"
      + " ORDER BY actor.name, movie.year");
    assertThat(request.getParameters()).containsExactly();
  }

  /**
   * This method test the getters of a {@code Table} when the jdbc connection, the name and the information about the columns
   * are set.
   */
  @Test
  public void test_getters_with_jdbc_name_and_columns_set() {
    Request request = new Request(jdbcConnectionProvider,
      "SELECT actor.name, actor.firstname, movie.year, interpretation.character "
        + " FROM movie, actor, interpretation"
        + " WHERE movie.id = interpretation.id_movie"
        + " AND interpretation.id_actor = actor.id"
        + " AND movie.year > ?"
        + " ORDER BY actor.name, movie.year", 2000);

    assertThat(request.getConnectionProvider()).as("Source of MOVIE table").isSameAs(jdbcConnectionProvider);
    assertThat(request.getRequest()).isEqualTo("SELECT actor.name, actor.firstname, movie.year, interpretation.character "
      + " FROM movie, actor, interpretation"
      + " WHERE movie.id = interpretation.id_movie"
      + " AND interpretation.id_actor = actor.id"
      + " AND movie.year > ?"
      + " ORDER BY actor.name, movie.year");
    assertThat(request.getParameters()).containsExactly(2000);
  }

}
