package org.assertj.db.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Request;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Test on the {@code value} methods (without parameters, with int parameter and with String parameter) on value.
 * 
 * @author Régis Pouiller
 * 
 */
public class ValueAssert_Value_Test extends AbstractTest {

  /**
   * This method tests the result of {@code value} methods on values assert from a row of a table.
   */
  @Test
  public void test_with_table_and_row() {
    Table table = new Table(source, "test");

    TableRowAssert rowAssert = assertThat(table).row();
    TableRowValueAssert valueAssert = rowAssert.value(1);

    assertThat(valueAssert)
        .isSameAs(rowAssert.value(0).value())
        .isSameAs(rowAssert.value(2).value(1))
        .isSameAs(rowAssert.value(2).value("var2"));
  }

  /**
   * This method tests the result of {@code value} methods on values assert from a column of a table.
   */
  @Test
  public void test_with_table_and_column() {
    Table table = new Table(source, "test");

    TableColumnAssert columnAssert = assertThat(table).column();
    TableColumnValueAssert valueAssert = columnAssert.value(1);

    assertThat(valueAssert)
        .isSameAs(columnAssert.value(0).value())
        .isSameAs(columnAssert.value(2).value(1));
  }

  /**
   * This method tests the result of {@code value} methods on values assert from a row of a request.
   */
  @Test
  public void test_with_request_and_row() {
    Request request = new Request(source, "select * from test");

    RequestRowAssert rowAssert = assertThat(request).row();
    RequestRowValueAssert valueAssert = rowAssert.value(1);

    assertThat(valueAssert)
        .isSameAs(rowAssert.value(0).value())
        .isSameAs(rowAssert.value(2).value(1))
        .isSameAs(rowAssert.value(2).value("var2"));
  }

  /**
   * This method tests the result of {@code value} methods on values assert from a column of a request.
   */
  @Test
  public void test_with_request_and_column() {
    Request request = new Request(source, "select * from test");

    RequestColumnAssert columnAssert = assertThat(request).column();
    RequestColumnValueAssert valueAssert = columnAssert.value(1);

    assertThat(valueAssert)
        .isSameAs(columnAssert.value(0).value())
        .isSameAs(columnAssert.value(2).value(1));
  }
}
