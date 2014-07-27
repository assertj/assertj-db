package org.assertj.db.api;

import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Request;
import org.junit.Test;

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
