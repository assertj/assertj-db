package org.assertj.db.api;

import static org.assertj.db.api.Assertions.assertThat;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Tests on {@code hasRowsSize} and {@code hasColumnsSize} methods for assertion on {@code Table}.
 * 
 * @author Régis Pouiller
 * 
 */
public class TableAssert_HasSize_Test extends AbstractTest {

  /**
   * This method test the assertion on the rows size.
   */
  @Test
  public void test_rows_size_assertion() {
    Table table = new Table(source, "movie");
    assertThat(table).hasRowsSize(3);
  }

  /**
   * This test should fail because the rows size is different (3).
   */
  @Test(expected = AssertionError.class)
  public void should_fail_beacause_rows_size_is_different() {
    Table table = new Table(source, "movie");
    assertThat(table).hasRowsSize(4);
  }

  /**
   * This method test the assertion on the columns size.
   */
  @Test
  public void test_columns_size_assertion() {
    Table table = new Table(source, "movie");
    assertThat(table).hasColumnsSize(3);
  }

  /**
   * This test should fail because the columns size is different (3).
   */
  @Test(expected = AssertionError.class)
  public void should_fail_beacause_columns_size_is_different() {
    Table table = new Table(source, "movie");
    assertThat(table).hasColumnsSize(4);
  }
}
