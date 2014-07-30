package org.assertj.db.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Request;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Test on the {@code row} methods (without parameters and with int parameter) on value.
 * 
 * @author Régis Pouiller
 * 
 */
public class ValueAssert_Row_Test extends AbstractTest {

  /**
   * This method tests the result of {@code row} methods on values assert from a row of a table.
   */
  @Test
  public void test_with_table_and_row() {
    Table table = new Table(source, "test");

    TableRowAssert rowAssert = assertThat(table).row(1);
    TableRowValueAssert valueAssert = rowAssert.value(1);

    assertThat(rowAssert)
        .isSameAs(valueAssert.row(0).row())
        .isSameAs(valueAssert.row(1));
  }

  /**
   * This method tests the result of {@code row} methods on values assert from a column of a table.
   */
  @Test
  public void test_with_table_and_column() {
    Table table = new Table(source, "test");

    TableAssert tableAssert = assertThat(table);
    TableRowAssert rowAssert = tableAssert.row(1);
    TableColumnValueAssert valueAssert = tableAssert.column().value(1);

    assertThat(rowAssert)
        .isSameAs(valueAssert.row(0).row())
        .isSameAs(valueAssert.row(1));
  }

  /**
   * This method tests the result of {@code row} methods on values assert from a row of a request.
   */
  @Test
  public void test_with_request_and_row() {
    Request request = new Request(source, "select * from test");

    RequestRowAssert rowAssert = assertThat(request).row(1);
    RequestRowValueAssert valueAssert = rowAssert.value(1);

    assertThat(rowAssert)
        .isSameAs(valueAssert.row(0).row())
        .isSameAs(valueAssert.row(1));
  }

  /**
   * This method tests the result of {@code row} methods on values assert from a column of a request.
   */
  @Test
  public void test_with_request_and_column() {
    Request request = new Request(source, "select * from test");

    RequestAssert requestAssert = assertThat(request);
    RequestRowAssert rowAssert = requestAssert.row(1);
    RequestColumnValueAssert valueAssert = requestAssert.column().value(1);

    assertThat(rowAssert)
        .isSameAs(valueAssert.row(0).row())
        .isSameAs(valueAssert.row(1));
  }
}
