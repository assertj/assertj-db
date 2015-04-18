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
package org.assertj.db.type;

import org.assertj.db.common.AbstractTest;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests on getting a {@code Row} in a {@code Request} from primary keys values.
 * 
 * @author Régis Pouiller
 * 
 */
public class Request_GetRowFromPksValues_Test extends AbstractTest {

  /**
   * This method test getting a row from primary keys values without finding it.
   */
  @Test
  public void test_getting_row_from_primary_keys_values_without_finding() {
    Request request = new Request(source,
        "SELECT actor.name, actor.firstname, movie.year, interpretation.id, interpretation.character "
            + " FROM movie, actor, interpretation WHERE movie.id = interpretation.id_movie"
            + " AND interpretation.id_actor = actor.id ORDER BY actor.name, movie.year");

    assertThat(request.getRowFromPksValues(1L)).isNull();
    assertThat(request.getRowFromPksValues(3)).isNull();

    request.setPksName("id");

    assertThat(request.getRowFromPksValues()).isNull();
    assertThat(request.getRowFromPksValues(1L, 3)).isNull();
  }

  /**
   * This method test getting a row from primary keys values with finding it.
   */
  @Test
  public void test_getting_row_from_primary_keys_values_with_finding() {
    Request request = new Request(source,
        "SELECT actor.name, actor.firstname, movie.year, interpretation.id, interpretation.character "
            + " FROM movie, actor, interpretation WHERE movie.id = interpretation.id_movie"
            + " AND interpretation.id_actor = actor.id ORDER BY actor.name, movie.year").setPksName("id");

    assertThat(request.getRowFromPksValues(3).getValuesList()).containsExactly("Weaver", "Sigourney",
        new BigDecimal(2009), new BigDecimal(3), "Dr Grace Augustine");
    assertThat(request.getRowFromPksValues(1L).getValuesList()).containsExactly("Weaver", "Sigourney",
        new BigDecimal(1979), new BigDecimal(1), "Ellen Louise Ripley");

    request.setPksName("character");

    assertThat(request.getRowFromPksValues("Lucius Hunt").getValuesList()).containsExactly("Phoenix", "Joaquim",
        new BigDecimal(2004), new BigDecimal(4), "Lucius Hunt");

    request.setPksName("name", "year");

    assertThat(request.getRowFromPksValues("Weaver", "2004").getValuesList()).containsExactly("Weaver", "Sigourney",
        new BigDecimal(2004), new BigDecimal(2), "Alice Hunt");

  }
}
