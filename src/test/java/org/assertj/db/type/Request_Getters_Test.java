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
 * 
 */
public class Request_Getters_Test extends AbstractTest {

  /**
   * This method test the getters of a {@code Table} when only the source is set.
   */
  @Test
  public void test_getters_with_only_source_set() {
    Request request = new Request().setSource(source);

    assertThat(request.getSource()).isSameAs(source);
    assertThat(request.getDataSource()).isNull();
    assertThat(request.getRequest()).isNull();
    assertThat(request.getParameters()).isNull();
  }

  /**
   * This method test the getters of a {@code Table} when only the datasource is set.
   */
  @Test
  public void test_getters_with_only_datasource_set() {
    Request request = new Request().setDataSource(dataSource);

    assertThat(request.getSource()).isNull();
    assertThat(request.getDataSource()).isSameAs(dataSource);
    assertThat(request.getRequest()).isNull();
    assertThat(request.getParameters()).isNull();
  }

  /**
   * This method test the getters of a {@code Table} when the source and the name are set.
   */
  @Test
  public void test_getters_with_source_and_name_set() {
    Request request = new Request(source, 
        "SELECT actor.name, actor.firstname, movie.year, interpretation.character "
        + " FROM movie, actor, interpretation"
        + " WHERE movie.id = interpretation.id_movie"
        + " AND interpretation.id_actor = actor.id"
        + " ORDER BY actor.name, movie.year");

    assertThat(request.getSource()).as("Source of MOVIE table").isSameAs(source);
    assertThat(request.getDataSource()).isNull();
    assertThat(request.getRequest()).isEqualTo("SELECT actor.name, actor.firstname, movie.year, interpretation.character "
        + " FROM movie, actor, interpretation"
        + " WHERE movie.id = interpretation.id_movie"
        + " AND interpretation.id_actor = actor.id"
        + " ORDER BY actor.name, movie.year");
    assertThat(request.getParameters()).containsExactly();
  }

  /**
   * This method test the getters of a {@code Table} when the source, the name and the informations about the columns
   * are set.
   */
  @Test
  public void test_getters_with_source_name_and_columns_set() {
    Request request = new Request(source, 
        "SELECT actor.name, actor.firstname, movie.year, interpretation.character "
            + " FROM movie, actor, interpretation"
            + " WHERE movie.id = interpretation.id_movie"
            + " AND interpretation.id_actor = actor.id"
            + " AND movie.year > ?"
            + " ORDER BY actor.name, movie.year", 2000);

    assertThat(request.getSource()).as("Source of MOVIE table").isSameAs(source);
    assertThat(request.getDataSource()).isNull();
    assertThat(request.getRequest()).isEqualTo("SELECT actor.name, actor.firstname, movie.year, interpretation.character "
        + " FROM movie, actor, interpretation"
        + " WHERE movie.id = interpretation.id_movie"
        + " AND interpretation.id_actor = actor.id"
        + " AND movie.year > ?"
        + " ORDER BY actor.name, movie.year");
    assertThat(request.getParameters()).containsExactly(2000);
  }

}
