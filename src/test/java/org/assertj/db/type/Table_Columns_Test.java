package org.assertj.db.type;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.db.common.AbstractTest;
import org.junit.Test;

/**
 * Tests on the {@code Column} of {@code Table}.
 * <p>
 * These tests are on the values in the {@code Column} got from a {@code Table}.
 * </p>
 * 
 * @author Régis Pouiller
 * 
 */
public class Table_Columns_Test extends AbstractTest {

  /**
   * This method test the columns got from a {@code Source}.
   */
  @Test
  public void test_columns_with_source_set() {
    Table table = new Table(source, "movie");

    Column columnFromName = table.getColumn("title");

    assertThat(columnFromName.getName()).isEqualTo("TITLE");
    assertThat(columnFromName.getValuesList()).containsExactly("Alien", "The Village", "Avatar");
    assertThat(columnFromName.getRowValue(1)).isEqualTo("The Village");

    Column columnFromIndex = table.getColumn(1);

    assertThat(columnFromIndex.getName()).isEqualTo("TITLE");
    assertThat(columnFromIndex.getValuesList()).containsExactly("Alien", "The Village", "Avatar");
    assertThat(columnFromIndex.getRowValue(1)).isEqualTo("The Village");
  }

  /**
   * This method test the columns got from a {@code DataSource}.
   */
  @Test
  public void test_columns_with_datasource_set() {
    Table table = new Table(dataSource, "movie");

    Column columnFromName = table.getColumn("title");

    assertThat(columnFromName.getName()).isEqualTo("TITLE");
    assertThat(columnFromName.getValuesList()).containsExactly("Alien", "The Village", "Avatar");
    assertThat(columnFromName.getRowValue(1)).isEqualTo("The Village");

    Column columnFromIndex = table.getColumn(1);

    assertThat(columnFromIndex.getName()).isEqualTo("TITLE");
    assertThat(columnFromIndex.getValuesList()).containsExactly("Alien", "The Village", "Avatar");
    assertThat(columnFromIndex.getRowValue(1)).isEqualTo("The Village");
  }

  /**
   * This method should throw a {@code NullPointerException} because of calling {@code getColumn} with null in parameter.
   */
  @Test(expected = NullPointerException.class)
  public void should_throw_NullPointerException_because_column_name_parameter_is_null_when_calling_getColumn() {
    Table table = new Table(source, "movie");
    table.getColumn(null);
  }

  /**
   * This method test that the call to {@code getColumn} return null when the column name in parameter don't exist.
   */
  @Test
  public void test_that_we_get_null_when_calling_getColumn_and_the_column_name_dont_exist() {
    Table table = new Table(source, "movie");
    assertThat(table.getColumn("not_exist")).isNull();
  }

}
