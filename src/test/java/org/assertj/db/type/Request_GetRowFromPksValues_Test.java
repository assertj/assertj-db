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

import java.math.BigDecimal;

import org.assertj.db.common.AbstractTest;
import org.junit.Test;

/**
 * Tests on getting a {@code Row} in a {@code Request} from primary keys values.
 *
 * @author Régis Pouiller
 * @author Julien Roy
 */
public class Request_GetRowFromPksValues_Test extends AbstractTest {

  /**
   * This method test getting a row from primary keys values without finding it.
   */
  @Test
  public void test_getting_row_from_primary_keys_values_without_finding() throws Exception {
    Request request = new Request(jdbcConnectionProvider,
      "SELECT actor.name, actor.firstname, movie.year, interpretation.id, interpretation.character "
        + " FROM movie, actor, interpretation WHERE movie.id = interpretation.id_movie"
        + " AND interpretation.id_actor = actor.id ORDER BY actor.name, movie.year");

    assertThat(request.getRowFromPksValues(getValue(null, 1L))).isNull();
    assertThat(request.getRowFromPksValues(getValue(null, 3))).isNull();

    request.setPksName("id");

    assertThat(request.getRowFromPksValues()).isNull();
    assertThat(request.getRowFromPksValues(getValue(null, 1L), getValue(null, 3))).isNull();
  }

  /**
   * This method test getting a row from primary keys values with finding it.
   */
  @Test
  public void test_getting_row_from_primary_keys_values_with_finding() throws Exception {
    Request request = new Request(jdbcConnectionProvider,
      "SELECT actor.name, actor.firstname, movie.year, interpretation.id, interpretation.character "
        + " FROM movie, actor, interpretation WHERE movie.id = interpretation.id_movie"
        + " AND interpretation.id_actor = actor.id ORDER BY actor.name, movie.year").setPksName("id");

    assertThat(request.getRowFromPksValues(getValue(null, 3)).getValuesList().get(0).getValue()).isEqualTo("Weaver");
    assertThat(request.getRowFromPksValues(getValue(null, 3)).getValuesList().get(1).getValue()).isEqualTo("Sigourney");
    assertThat(request.getRowFromPksValues(getValue(null, 3)).getValuesList().get(2).getValue()).isEqualTo(new BigDecimal(2009));
    assertThat(request.getRowFromPksValues(getValue(null, 3)).getValuesList().get(3).getValue()).isEqualTo(new BigDecimal(3));
    assertThat(request.getRowFromPksValues(getValue(null, 3)).getValuesList().get(4).getValue()).isEqualTo("Dr Grace Augustine");
    assertThat(request.getRowFromPksValues(getValue(null, 1L)).getValuesList().get(0).getValue()).isEqualTo("Weaver");
    assertThat(request.getRowFromPksValues(getValue(null, 1L)).getValuesList().get(1).getValue()).isEqualTo("Sigourney");
    assertThat(request.getRowFromPksValues(getValue(null, 1L)).getValuesList().get(2).getValue()).isEqualTo(new BigDecimal(1979));
    assertThat(request.getRowFromPksValues(getValue(null, 1L)).getValuesList().get(3).getValue()).isEqualTo(new BigDecimal(1));
    assertThat(request.getRowFromPksValues(getValue(null, 1L)).getValuesList().get(4).getValue()).isEqualTo("Ellen Louise Ripley");

    request.setPksName("character");

    assertThat(request.getRowFromPksValues(getValue(null, "Lucius Hunt")).getValuesList().get(0).getValue()).isEqualTo("Phoenix");
    assertThat(request.getRowFromPksValues(getValue(null, "Lucius Hunt")).getValuesList().get(1).getValue()).isEqualTo("Joaquim");
    assertThat(request.getRowFromPksValues(getValue(null, "Lucius Hunt")).getValuesList().get(2).getValue()).isEqualTo(new BigDecimal(2004));
    assertThat(request.getRowFromPksValues(getValue(null, "Lucius Hunt")).getValuesList().get(3).getValue()).isEqualTo(new BigDecimal(4));
    assertThat(request.getRowFromPksValues(getValue(null, "Lucius Hunt")).getValuesList().get(4).getValue()).isEqualTo("Lucius Hunt");

    request.setPksName("name", "year");

    assertThat(request.getRowFromPksValues(getValue(null, "Weaver"), getValue(null, "2004")).getValuesList().get(0).getValue()).isEqualTo("Weaver");
    assertThat(request.getRowFromPksValues(getValue(null, "Weaver"), getValue(null, "2004")).getValuesList().get(1).getValue()).isEqualTo("Sigourney");
    assertThat(request.getRowFromPksValues(getValue(null, "Weaver"), getValue(null, "2004")).getValuesList().get(2).getValue()).isEqualTo(new BigDecimal(2004));
    assertThat(request.getRowFromPksValues(getValue(null, "Weaver"), getValue(null, "2004")).getValuesList().get(3).getValue()).isEqualTo(new BigDecimal(2));
    assertThat(request.getRowFromPksValues(getValue(null, "Weaver"), getValue(null, "2004")).getValuesList().get(4).getValue()).isEqualTo("Alice Hunt");
  }
}
