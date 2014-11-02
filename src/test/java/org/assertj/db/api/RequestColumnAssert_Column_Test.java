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
package org.assertj.db.api;

import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Request;
import org.junit.Test;

/**
 * Test on {@code column} methods on column assert from a request.
 * 
 * @author Régis Pouiller
 * 
 */
public class RequestColumnAssert_Column_Test extends AbstractTest {

  /**
   * This method tests the result of {@code column} methods on column assert from a request.
   */
  @Test
  public void test_with_request_and_column() {
    Request request = new Request(source, "SELECT actor.name, actor.firstname, movie.year, interpretation.character "
        + " FROM movie, actor, interpretation"
        + " WHERE movie.id = interpretation.id_movie"
        + " AND interpretation.id_actor = actor.id"
        + " AND movie.year > ?"
        + " ORDER BY actor.name, movie.year", 2000);
    
    RequestAssert requestAssert = assertThat(request);
    RequestColumnAssert requestColumnAssert = requestAssert.column(1);
    
    assertThat(requestColumnAssert).isEqualTo(requestColumnAssert.column(0).column());
    assertThat(requestColumnAssert).isEqualTo(requestColumnAssert.column(1));
    assertThat(requestColumnAssert).isEqualTo(requestColumnAssert.column("firstname"));
  }
}
