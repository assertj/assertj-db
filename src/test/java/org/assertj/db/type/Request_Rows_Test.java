package org.assertj.db.type;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.assertj.db.common.AbstractTest;
import org.junit.Test;

/**
 * Tests on the {@code Row} of {@code Request}.
 * <p>
 * These tests are on the values in the {@code Row} got from a {@code Request}.
 * </p>
 * 
 * @author Régis Pouiller
 * 
 */
public class Request_Rows_Test extends AbstractTest {

  /**
   * This method test the rows got from a {@code Source}.
   */
  @Test
  public void test_rows_with_source_set() {
    Request request = new Request(source, "SELECT actor.name, actor.firstname, movie.year, interpretation.character "
        + " FROM movie, actor, interpretation"
        + " WHERE movie.id = interpretation.id_movie"
        + " AND interpretation.id_actor = actor.id"
        + " ORDER BY actor.name, movie.year");

    assertThat(request.getRowsList()).as("Values of the request")
        .hasSize(5);

    assertThat(request.getRow(0).getColumnsNameList()).containsExactly("NAME", "FIRSTNAME", "YEAR", "CHARACTER");
    assertThat(request.getRow(0).getColumnValue(0)).isEqualTo("Phoenix");
    assertThat(request.getRow(0).getColumnValue("firstName")).isEqualTo("Joaquim");

    assertThat(request.getRow(0).getValuesList())
        .containsExactly("Phoenix", "Joaquim", new BigDecimal(2004), "Lucius Hunt");
    assertThat(request.getRow(1).getValuesList())
        .containsExactly("Weaver", "Sigourney", new BigDecimal(1979), "Ellen Louise Ripley");
    assertThat(request.getRow(2).getValuesList())
        .containsExactly("Weaver", "Sigourney", new BigDecimal(2004), "Alice Hunt");
    assertThat(request.getRow(3).getValuesList())
        .containsExactly("Weaver", "Sigourney", new BigDecimal(2009), "Dr Grace Augustine");
    assertThat(request.getRow(4).getValuesList())
        .containsExactly("Worthington", "Sam", new BigDecimal(2009), "Jake Sully");
  }

  /**
   * This method test the rows got from a {@code DataSource}.
   */
  @Test
  public void test_rows_with_datasource_set() {
    Request request = new Request(dataSource, "SELECT actor.name, actor.firstname, movie.year, interpretation.character "
        + " FROM movie, actor, interpretation"
        + " WHERE movie.id = interpretation.id_movie"
        + " AND interpretation.id_actor = actor.id"
        + " ORDER BY actor.name, movie.year");

    assertThat(request.getRowsList()).as("Values of the request")
        .hasSize(5);
    assertThat(request.getRow(0).getValuesList())
        .containsExactly("Phoenix", "Joaquim", new BigDecimal(2004), "Lucius Hunt");
    assertThat(request.getRow(1).getValuesList())
        .containsExactly("Weaver", "Sigourney", new BigDecimal(1979), "Ellen Louise Ripley");
    assertThat(request.getRow(2).getValuesList())
        .containsExactly("Weaver", "Sigourney", new BigDecimal(2004), "Alice Hunt");
    assertThat(request.getRow(3).getValuesList())
        .containsExactly("Weaver", "Sigourney", new BigDecimal(2009), "Dr Grace Augustine");
    assertThat(request.getRow(4).getValuesList())
        .containsExactly("Worthington", "Sam", new BigDecimal(2009), "Jake Sully");
  }

  /**
   * This method should throw a {@code NullPointerException} because of calling {@code getColumnValue} with null in parameter.
   */
  @Test(expected = NullPointerException.class)
  public void should_throw_NullPointerException_because_column_name_parameter_is_null_when_calling_getColumnValue() {
    Request request = new Request(dataSource, "SELECT actor.name, actor.firstname, movie.year, interpretation.character "
        + " FROM movie, actor, interpretation"
        + " WHERE movie.id = interpretation.id_movie"
        + " AND interpretation.id_actor = actor.id"
        + " ORDER BY actor.name, movie.year");
    request.getRow(0).getColumnValue(null);
  }

  /**
   * This method test that the call to {@code getColumnValue} return null when the column name in parameter don't exist.
   */
  @Test
  public void test_that_we_get_null_when_calling_getColumnValue_and_the_column_name_dont_exist() {
    Request request = new Request(dataSource, "SELECT actor.name, actor.firstname, movie.year, interpretation.character "
        + " FROM movie, actor, interpretation"
        + " WHERE movie.id = interpretation.id_movie"
        + " AND interpretation.id_actor = actor.id"
        + " ORDER BY actor.name, movie.year");
    assertThat(request.getRow(0).getColumnValue("not_exist")).isNull();
  }

}
