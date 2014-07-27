package org.assertj.db.api;

import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Test on {@code column} methods on column assert from a table.
 * 
 * @author Régis Pouiller
 * 
 */
public class TableColumnAssert_Column_Test extends AbstractTest {

  /**
   * This method tests the result of {@code column} methods on column assert from a table.
   */
  @Test
  public void test_with_table_and_column() {
    Table table = new Table(source, "test");
    
    TableAssert tableAssert = assertThat(table);
    TableColumnAssert tableColumnAssert = tableAssert.column(1);
    
    assertThat(tableColumnAssert).isEqualTo(tableColumnAssert.column(0).column());
    assertThat(tableColumnAssert).isEqualTo(tableColumnAssert.column(1));
    assertThat(tableColumnAssert).isEqualTo(tableColumnAssert.column("var2"));
  }
}
