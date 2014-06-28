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

    Column columnFromIndex = table.getColumn(1);

    assertThat(columnFromIndex.getName()).isEqualTo("TITLE");
    assertThat(columnFromIndex.getValuesList()).containsExactly("Alien", "The Village", "Avatar");
    assertThat(columnFromIndex.getRowValue(1)).isEqualTo("The Village");
  }

}
