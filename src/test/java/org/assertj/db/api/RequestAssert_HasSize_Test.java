package org.assertj.db.api;

import static org.assertj.db.api.Assertions.assertThat;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Request;
import org.junit.Test;

/**
 * Tests on {@code hasRowsSize} and {@code hasColumnsSize} methods for assertion on {@code Request}.
 * 
 * @author Régis Pouiller
 * 
 */
public class RequestAssert_HasSize_Test extends AbstractTest {

  /**
   * This method test the assertion on the rows size.
   */
  @Test
  public void test_rows_size_assertion() {
    Request request = new Request(source, "SELECT actor.name, actor.firstname, movie.year, interpretation.character "
        + " FROM movie, actor, interpretation"
        + " WHERE movie.id = interpretation.id_movie"
        + " AND interpretation.id_actor = actor.id"
        + " AND movie.year > ?"
        + " ORDER BY actor.name, movie.year", 2000);
    assertThat(request).hasRowsSize(4);
  }

  /**
   * This test should fail because the rows size is different (3).
   */
  @Test(expected = AssertionError.class)
  public void should_fail_beacause_rows_size_is_different() {
    Request request = new Request(source, "SELECT actor.name, actor.firstname, movie.year, interpretation.character "
        + " FROM movie, actor, interpretation"
        + " WHERE movie.id = interpretation.id_movie"
        + " AND interpretation.id_actor = actor.id"
        + " AND movie.year > ?"
        + " ORDER BY actor.name, movie.year", 2000);
    assertThat(request).hasRowsSize(3);
  }

  /**
   * This method test the assertion on the columns size.
   */
  @Test
  public void test_columns_size_assertion() {
    Request request = new Request(source, "SELECT actor.name, actor.firstname, movie.year, interpretation.character "
        + " FROM movie, actor, interpretation"
        + " WHERE movie.id = interpretation.id_movie"
        + " AND interpretation.id_actor = actor.id"
        + " AND movie.year > ?"
        + " ORDER BY actor.name, movie.year", 2000);
    assertThat(request).hasColumnsSize(4);
  }

  /**
   * This test should fail because the columns size is different (3).
   */
  @Test(expected = AssertionError.class)
  public void should_fail_beacause_columns_size_is_different() {
    Request request = new Request(source, "SELECT actor.name, actor.firstname, movie.year, interpretation.character "
        + " FROM movie, actor, interpretation"
        + " WHERE movie.id = interpretation.id_movie"
        + " AND interpretation.id_actor = actor.id"
        + " AND movie.year > ?"
        + " ORDER BY actor.name, movie.year", 2000);
    assertThat(request).hasColumnsSize(3);
  }
}
