package org.assertj.db.api;

import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Table;
import org.junit.Test;

public class TableColumnAssert_Column_Test extends AbstractTest {

  /**
   * This method tests the result of {@code value} methods on values assert from a row of a table.
   */
  @Test
  public void test_with_table_and_row() {
    Table table = new Table(source, "test");
    
    TableAssert tableAssert = assertThat(table);
    TableColumnAssert tableColumnAssert = tableAssert.column(1);
    
    assertThat(tableColumnAssert).isEqualTo(tableColumnAssert.column(0).column());
    assertThat(tableColumnAssert).isEqualTo(tableColumnAssert.column(1));
    assertThat(tableColumnAssert).isEqualTo(tableColumnAssert.column("var2"));
  }
}
