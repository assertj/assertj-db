package org.assertj.db.api;

import static org.assertj.db.api.Assertions.assertThat;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Test on the {@code haveOnlyNullValues} assertion method on {@code Column}.
 * 
 * @author Régis Pouiller
 * 
 */
public class ColumnAssert_HaveOnlyNullValues_Test extends AbstractTest {

  /**
   * This method tests the {@code haveOnlyNullValues} assertion method.
   */
  @Test
  public void test_haveOnlyNullValues_assertion() {
    Table table2 = new Table(source, "test2");

    assertThat(table2)
        .column("var15").as("var15").haveOnlyNullValues();
  }

  /**
   * This method should fail because there is a not null in the first row.
   */
  @Test(expected = AssertionError.class)
  public void should_fail_haveOnlyNotNullValues_assertion_because_there_is_a_not_null() {
    Table table2 = new Table(source, "test2");

    assertThat(table2)
        .column(1).as("var2").haveOnlyNullValues();
  }

  /**
   * This method should fail because there is only not null value in the column.
   */
  @Test(expected = AssertionError.class)
  public void should_fail_haveOnlyNotNullValues_assertion_because_there_is_only_not_null() {
    Table table = new Table(source, "test");

    assertThat(table)
        .column(1).as("var2").haveOnlyNullValues();
  }
}
