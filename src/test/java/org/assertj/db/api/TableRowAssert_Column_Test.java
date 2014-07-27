package org.assertj.db.api;

import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Test on {@code column} methods on row assert from a table.
 * 
 * @author Régis Pouiller
 * 
 */
public class TableRowAssert_Column_Test extends AbstractTest {

  /**
   * This method tests the result of {@code column} methods on row assert from a table.
   */
  @Test
  public void test_with_table_and_row() {
    Table table = new Table(source, "test");
    
    TableAssert tableAssert = assertThat(table);
    TableRowAssert tableRowAssert = tableAssert.row(1);
    TableColumnAssert tableColumnAssert = tableAssert.column(1);
    
    assertThat(tableColumnAssert).isEqualTo(tableRowAssert.column(0).column());
    assertThat(tableColumnAssert).isEqualTo(tableRowAssert.column(1));
    assertThat(tableColumnAssert).isEqualTo(tableRowAssert.column("var2"));
  }
}
