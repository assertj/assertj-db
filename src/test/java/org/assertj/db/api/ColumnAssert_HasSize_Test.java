package org.assertj.db.api;

import static org.assertj.db.api.Assertions.assertThat;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Request;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Test on the {@code hasSize} assertion method on {@code Column}.
 * 
 * @author Régis Pouiller
 * 
 */
public class ColumnAssert_HasSize_Test extends AbstractTest {

  /**
   * This method test the assertion on the rows size of a {@code Column} from a {@code Table}.
   */
  @Test
  public void test_rows_size_of_column_table_assertion() {
    Table table = new Table(source, "movie");
    assertThat(table).column().hasSize(3);
  }

  /**
   * This test should fail because the rows size is different (3).
   */
  @Test(expected = AssertionError.class)
  public void should_fail_beacause_rows_size_of_column_table_is_different() {
    Table table = new Table(source, "movie");
    assertThat(table).column().hasSize(4);
  }

  /**
   * This method test the assertion on the rows size of a {@code Column} from a {@code Request}.
   */
  @Test
  public void test_rows_size_of_column_request_assertion() {
    Request request = new Request(source, "SELECT actor.name, actor.firstname, movie.year, interpretation.character "
        + " FROM movie, actor, interpretation"
        + " WHERE movie.id = interpretation.id_movie"
        + " AND interpretation.id_actor = actor.id"
        + " AND movie.year > ?"
        + " ORDER BY actor.name, movie.year", 2000);
    assertThat(request).column().hasSize(4);
  }

  /**
   * This test should fail because the rows size is different (3).
   */
  @Test(expected = AssertionError.class)
  public void should_fail_beacause_rows_size_of_column_request_is_different() {
    Request request = new Request(source, "SELECT actor.name, actor.firstname, movie.year, interpretation.character "
        + " FROM movie, actor, interpretation"
        + " WHERE movie.id = interpretation.id_movie"
        + " AND interpretation.id_actor = actor.id"
        + " AND movie.year > ?"
        + " ORDER BY actor.name, movie.year", 2000);
    assertThat(request).column().hasSize(3);
  }

}
