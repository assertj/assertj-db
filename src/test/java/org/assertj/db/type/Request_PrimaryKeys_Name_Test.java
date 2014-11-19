package org.assertj.db.type;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.exception.AssertJDBException;
import org.junit.Test;

/**
 * Tests on the primary keys name of {@code Request}.
 * <p>
 * These tests are on the name of the primary keys got from a {@code Request}.
 * </p>
 * 
 * @author Régis Pouiller
 * 
 */
public class Request_PrimaryKeys_Name_Test extends AbstractTest {

  /**
   * This method test the primary keys name got from a {@code Source}.
   */
  @Test
  public void test_pks_name_with_source_set_but_not_primary_keys() {
    Request request = new Request(source, 
        "SELECT actor.name, actor.firstname, movie.year, interpretation.character "
        + " FROM movie, actor, interpretation"
        + " WHERE movie.id = interpretation.id_movie"
        + " AND interpretation.id_actor = actor.id"
        + " ORDER BY actor.name, movie.year");

    assertThat(request.getPksNameList()).as("Primary Keys of the request")
        .isNull();
  }

  /**
   * This method test the primary keys name got from a {@code Source}.
   */
  @Test
  public void test_pks_name_with_source_set() {
    Request request = new Request(source, 
        "SELECT actor.name, actor.firstname, movie.year, interpretation.character "
        + " FROM movie, actor, interpretation"
        + " WHERE movie.id = interpretation.id_movie"
        + " AND interpretation.id_actor = actor.id"
        + " ORDER BY actor.name, movie.year").setPksNameList("NAME");

    assertThat(request.getPksNameList()).as("Primary Keys of the request")
        .hasSize(1)
        .containsExactly("NAME");
  }

  /**
   * This method test the primary keys name got from a {@code DataSource}.
   */
  @Test
  public void test_pks_name_with_datasource_set() {
    Request request = new Request(dataSource, 
        "SELECT actor.name, actor.firstname, movie.year, interpretation.character "
        + " FROM movie, actor, interpretation"
        + " WHERE movie.id = interpretation.id_movie"
        + " AND interpretation.id_actor = actor.id"
        + " ORDER BY actor.name, movie.year").setPksNameList("NAME");

    assertThat(request.getPksNameList()).as("Primary Keys of the request")
        .hasSize(1)
        .containsExactly("NAME");
  }

  /**
   * This method test the primary keys name got from a {@code Source}.
   */
  @Test
  public void test_pks_name_with_source_and_parameters_set() {
    Request request = new Request(source, 
        "SELECT actor.name, actor.firstname, movie.year, interpretation.character "
            + " FROM movie, actor, interpretation"
            + " WHERE movie.id = interpretation.id_movie"
            + " AND interpretation.id_actor = actor.id"
            + " AND movie.year > ?"
            + " ORDER BY actor.name, movie.year", 2000).setPksNameList("NAME", "ID");

    assertThat(request.getPksNameList()).as("Primary Keys of the request")
        .hasSize(2)
        .containsExactly("NAME", "ID");
  }

  /**
   * This method test the primary keys name got from a {@code DataSource}.
   */
  @Test
  public void test_pks_name_with_datasource_and_parameters_set() {
    Request request = new Request(dataSource, 
        "SELECT actor.name, actor.firstname, movie.year, interpretation.character "
            + " FROM movie, actor, interpretation"
            + " WHERE movie.id = interpretation.id_movie"
            + " AND interpretation.id_actor = actor.id"
            + " AND movie.year > ?"
            + " ORDER BY actor.name, movie.year")
        .setParameters(2000).setPksNameList("NAME", "ID");

    assertThat(request.getPksNameList()).as("Primary Keys of the request")
        .hasSize(2)
        .containsExactly("NAME", "ID");
  }

  /**
   * This method should throw a {@code AssertJDBException} because of a {@code SQLException} caused by a table not
   * found.
   */
  @Test(expected = AssertJDBException.class)
  public void should_throw_AssertJDBException_because_SQLException_caused_by_table_not_found() {
    Table table = new Table(dataSource, "select * from interpret");
    table.getPksNameList();
  }

}
