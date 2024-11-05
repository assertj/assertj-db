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
 * Tests on the columns of {@code Request}.
 * <p>
 * These tests are on the values in the columns got from a {@code Request}.
 * </p>
 *
 * @author Régis Pouiller
 * @author Julien Roy
 */
public class Request_Columns_Test extends AbstractTest {

  /**
   * This method test the columns got from a {@code ConnectionProvider}.
   */
  @Test
  public void test_columns_with_jdbc_set() {
    Request request = new Request(jdbcConnectionProvider, "SELECT actor.name, actor.firstname, movie.year, interpretation.character "
      + " FROM movie, actor, interpretation"
      + " WHERE movie.id = interpretation.id_movie"
      + " AND interpretation.id_actor = actor.id"
      + " ORDER BY actor.name, movie.year");

    Column columnFromIndex = request.getColumn(1);

    assertThat(columnFromIndex.getName()).isEqualTo("FIRSTNAME");
    assertThat(columnFromIndex.getValuesList().get(0).getValue()).isEqualTo("Joaquim");
    assertThat(columnFromIndex.getValuesList().get(1).getValue()).isEqualTo("Sigourney");
    assertThat(columnFromIndex.getValuesList().get(2).getValue()).isEqualTo("Sigourney");
    assertThat(columnFromIndex.getValuesList().get(3).getValue()).isEqualTo("Sigourney");
    assertThat(columnFromIndex.getValuesList().get(4).getValue()).isEqualTo("Sam");
    assertThat(columnFromIndex.getRowValue(4).getValue()).isEqualTo("Sam");
  }

  /**
   * This method test the columns got from a {@code DataSource}.
   */
  @Test
  public void test_columns_with_datasource_set() {
    Request request = new Request(dsConnectionProvider, "SELECT actor.name, actor.firstname, movie.year, interpretation.character "
      + " FROM movie, actor, interpretation"
      + " WHERE movie.id = interpretation.id_movie"
      + " AND interpretation.id_actor = actor.id"
      + " ORDER BY actor.name, movie.year");

    Column columnFromIndex = request.getColumn(1);

    assertThat(columnFromIndex.getName()).isEqualTo("FIRSTNAME");
    assertThat(columnFromIndex.getValuesList().get(0).getValue()).isEqualTo("Joaquim");
    assertThat(columnFromIndex.getValuesList().get(1).getValue()).isEqualTo("Sigourney");
    assertThat(columnFromIndex.getValuesList().get(2).getValue()).isEqualTo("Sigourney");
    assertThat(columnFromIndex.getValuesList().get(3).getValue()).isEqualTo("Sigourney");
    assertThat(columnFromIndex.getValuesList().get(4).getValue()).isEqualTo("Sam");
    assertThat(columnFromIndex.getRowValue(4).getValue()).isEqualTo("Sam");
  }

}
