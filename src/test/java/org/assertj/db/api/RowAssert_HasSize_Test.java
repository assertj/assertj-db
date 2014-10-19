package org.assertj.db.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Request;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Test on the {@code hasSize} assertion method on {@code Row}.
 * 
 * @author Régis Pouiller
 * 
 */
public class RowAssert_HasSize_Test extends AbstractTest {

  /**
   * This method test the assertion on the columns size of a {@code Row} from a {@code Table}.
   */
  @Test
  public void test_columns_size_of_row_table_assertion() {
    Table table = new Table(source, "movie");
    assertThat(table).row().hasSize(3);
  }

  /**
   * This test should fail because the columns size is different (3).
   */
  @Test
  public void should_fail_beacause_columns_size_of_row_table_is_different() {
    try {
      Table table = new Table(source, "movie");
      assertThat(table).row().hasSize(4);
      
      fail("Une Erreur doit être levée");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Row at index 0 of movie table] \n" +
          "Expecting size (number of columns) to be equal to :\n" +
          "   <4>\n" +
          "but was:\n" +
          "   <3>");
    }
  }

  /**
   * This method test the assertion on the columns size of a {@code Row} from a {@code Request}.
   */
  @Test
  public void test_columns_size_of_row_request_assertion() {
    Request request = new Request(source, "SELECT actor.name, actor.firstname, movie.year, interpretation.character "
        + " FROM movie, actor, interpretation"
        + " WHERE movie.id = interpretation.id_movie"
        + " AND interpretation.id_actor = actor.id"
        + " AND movie.year > ?"
        + " ORDER BY actor.name, movie.year", 2000);
    assertThat(request).row().hasSize(4);
  }

  /**
   * This test should fail because the columns size is different (3).
   */
  @Test
  public void should_fail_beacause_columns_size_of_row_request_is_different() {
    try {
      Request request = new Request(source, "SELECT actor.name, actor.firstname, movie.year, interpretation.character "
          + " FROM movie, actor, interpretation"
          + " WHERE movie.id = interpretation.id_movie"
          + " AND interpretation.id_actor = actor.id"
          + " AND movie.year > ?"
          + " ORDER BY actor.name, movie.year", 2000);
      assertThat(request).row().hasSize(3);
      
      fail("Une Erreur doit être levée");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Row at index 0 of 'SELECT actor.name, a...' request] \n" +
          "Expecting size (number of columns) to be equal to :\n" +
          "   <3>\n" +
          "but was:\n" +
          "   <4>");
    }
  }
}
