package org.assertj.db.type;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.error.AssertJDBException;
import org.junit.Test;

/**
 * Tests on the columns name of {@code Request}.
 * <p>
 * These tests are on the name of the columns got from a {@code Request}.
 * </p>
 * 
 * @author Régis Pouiller
 * 
 */
public class Request_Columns_Name_Test extends AbstractTest {

  /**
   * This method test the columns name got from a {@code Source}.
   */
  @Test
  public void test_columns_name_with_source_set() {
    Request request = new Request(source, 
        "SELECT actor.name, actor.firstname, movie.year, interpretation.character "
        + " FROM movie, actor, interpretation"
        + " WHERE movie.id = interpretation.id_movie"
        + " AND interpretation.id_actor = actor.id"
        + " ORDER BY actor.name, movie.year");

    assertThat(request.getColumnsNameList()).as("Columns of the request")
        .hasSize(4)
        .containsExactly("NAME", "FIRSTNAME", "YEAR", "CHARACTER");
  }

  /**
   * This method test the columns name got from a {@code DataSource}.
   */
  @Test
  public void test_columns_name_with_datasource_set() {
    Request request = new Request(dataSource, 
        "SELECT actor.name, actor.firstname, movie.year, interpretation.character "
        + " FROM movie, actor, interpretation"
        + " WHERE movie.id = interpretation.id_movie"
        + " AND interpretation.id_actor = actor.id"
        + " ORDER BY actor.name, movie.year");

    assertThat(request.getColumnsNameList()).as("Columns of the request")
        .hasSize(4)
        .containsExactly("NAME", "FIRSTNAME", "YEAR", "CHARACTER");
  }

  /**
   * This method test the columns name got from a {@code Source}.
   */
  @Test
  public void test_columns_name_with_source_and_parameters_set() {
    Request request = new Request(source, 
        "SELECT actor.name, actor.firstname, movie.year, interpretation.character "
            + " FROM movie, actor, interpretation"
            + " WHERE movie.id = interpretation.id_movie"
            + " AND interpretation.id_actor = actor.id"
            + " AND movie.year > ?"
            + " ORDER BY actor.name, movie.year", 2000);

    assertThat(request.getColumnsNameList()).as("Columns of the request")
        .hasSize(4)
        .containsExactly("NAME", "FIRSTNAME", "YEAR", "CHARACTER");
  }

  /**
   * This method test the columns name got from a {@code DataSource}.
   */
  @Test
  public void test_columns_name_with_datasource_and_parameters_set() {
    Request request = new Request(dataSource, 
        "SELECT actor.name, actor.firstname, movie.year, interpretation.character "
            + " FROM movie, actor, interpretation"
            + " WHERE movie.id = interpretation.id_movie"
            + " AND interpretation.id_actor = actor.id"
            + " AND movie.year > ?"
            + " ORDER BY actor.name, movie.year")
        .setParameters(2000);

    assertThat(request.getColumnsNameList()).as("Columns of the request")
        .hasSize(4)
        .containsExactly("NAME", "FIRSTNAME", "YEAR", "CHARACTER");
  }

  /**
   * This method should throw a {@code AssertJDBException} because of a {@code SQLException} caused by a table not
   * found.
   */
  @Test(expected = AssertJDBException.class)
  public void should_throw_AssertJDBException_because_SQLException_caused_by_table_not_found() {
    Table table = new Table(dataSource, "select * from interpret");
    table.getColumnsNameList();
  }

}
