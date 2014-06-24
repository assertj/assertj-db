package org.assertj.db.type;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.db.common.AbstractTest;
import org.junit.Test;

/**
 * Tests on the getters of {@code Table}.
 * <p>
 * These tests are on the return from the getters of {@code Table}.
 * </p>
 * 
 * @author Régis Pouiller
 * 
 */
public class Table_Getters_Test extends AbstractTest {

  /**
   * This method test the getters of a {@code Table} when only the source is set.
   */
  @Test
  public void test_getters_with_only_source_set() {
    Table table = new Table().setSource(source);

    assertThat(table.getSource()).as("Source of MOVIE table").isSameAs(source);
    assertThat(table.getDataSource()).isNull();
    assertThat(table.getName()).isNull();
    assertThat(table.getColumnsToCheck()).isNull();
    assertThat(table.getColumnsToExclude()).isNull();
  }

  /**
   * This method test the getters of a {@code Table} when only the datasource is set.
   */
  @Test
  public void test_getters_with_only_datasource_set() {
    Table table = new Table().setDataSource(dataSource);

    assertThat(table.getSource()).isNull();
    assertThat(table.getDataSource()).as("DataSource of MOVIE table").isSameAs(dataSource);
    assertThat(table.getName()).isNull();
    assertThat(table.getColumnsToCheck()).isNull();
    assertThat(table.getColumnsToExclude()).isNull();
  }

  /**
   * This method test the getters of a {@code Table} when only the source is set after the datasource was set.
   */
  @Test
  public void test_getters_with_only_datasource_set_and_after_only_source_set() {
    Table table = new Table().setDataSource(dataSource).setSource(source);

    assertThat(table.getSource()).as("Source of MOVIE table").isSameAs(source);
    assertThat(table.getDataSource()).isNull();
    assertThat(table.getName()).isNull();
    assertThat(table.getColumnsToCheck()).isNull();
    assertThat(table.getColumnsToExclude()).isNull();
  }

  /**
   * This method test the getters of a {@code Table} when only the datasource is set after the source was set.
   */
  @Test
  public void test_getters_with_only_source_set_and_after_only_datasource_set() {
    Table table = new Table().setSource(source).setDataSource(dataSource);

    assertThat(table.getSource()).isNull();
    assertThat(table.getDataSource()).as("DataSource of MOVIE table").isSameAs(dataSource);
    assertThat(table.getName()).isNull();
    assertThat(table.getColumnsToCheck()).isNull();
    assertThat(table.getColumnsToExclude()).isNull();
  }

  /**
   * This method test the getters of a {@code Table} when the source and the name are set.
   */
  @Test
  public void test_getters_with_source_and_name_set() {
    Table table = new Table(source, "movie");

    assertThat(table.getSource()).as("Source of MOVIE table").isSameAs(source);
    assertThat(table.getDataSource()).isNull();
    assertThat(table.getName()).isEqualTo("movie");
    assertThat(table.getColumnsToCheck()).isNull();
    assertThat(table.getColumnsToExclude()).isNull();
    assertThat(table.getRequest()).isEqualTo("SELECT * FROM movie");
  }

  /**
   * This method test the getters of a {@code Table} when the source, the name and the informations about the columns
   * are set.
   */
  @Test
  public void test_getters_with_source_name_and_columns_set() {
    Table table = new Table(source, "movie", new String[] { "name", "birth" }, new String[] { "id" });

    assertThat(table.getSource()).as("Source of MOVIE table").isSameAs(source);
    assertThat(table.getDataSource()).isNull();
    assertThat(table.getName()).isEqualTo("movie");
    assertThat(table.getColumnsToCheck()).containsExactly("NAME", "BIRTH");
    assertThat(table.getColumnsToExclude()).containsExactly("ID");
    assertThat(table.getRequest()).isEqualTo("SELECT NAME, BIRTH FROM movie");
  }

}
