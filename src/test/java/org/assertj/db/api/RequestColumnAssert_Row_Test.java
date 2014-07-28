package org.assertj.db.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Request;
import org.junit.Test;

/**
 * Test on {@code row} methods on column assert from a request.
 * 
 * @author Régis Pouiller
 * 
 */
public class RequestColumnAssert_Row_Test extends AbstractTest {

  /**
   * This method tests the result of {@code row} methods on column assert from a request.
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
    RequestRowAssert requestRowAssert = requestAssert.row(1);
    RequestColumnAssert requestColumnAssert = requestAssert.column(1);
    
    assertThat(requestRowAssert).isEqualTo(requestColumnAssert.row(0).row());
    assertThat(requestRowAssert).isEqualTo(requestColumnAssert.row(1));
  }
}
