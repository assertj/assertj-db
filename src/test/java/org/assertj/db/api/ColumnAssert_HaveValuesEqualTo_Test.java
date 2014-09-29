package org.assertj.db.api;

import static org.assertj.db.api.Assertions.assertThat;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Test on the {@code haveValuesEqualTo} assertion method on {@code Column} for the {@code boolean}.
 * 
 * @author Régis Pouiller
 * 
 */
public class ColumnAssert_HaveValuesEqualTo_Test extends AbstractTest {

  /**
   * This method tests the {@code haveValuesEqualTo} assertion method.
   */
  @Test
  public void test_haveValuesEqualTo_assertion() {
    Table table = new Table(source, "test");

    assertThat(table)
        .column(1).as("var2").haveValuesEqualTo(true, false, false, false);

    Table table2 = new Table(source, "test2");

    assertThat(table2)
        .column(1).as("var2").haveValuesEqualTo(true, null);
  }

  /**
   * This method should fail because the type of the column is {@code ValueType.Number}.
   */
  @Test(expected = AssertionError.class)
  public void should_fail_isOfType_assertion_because_column_is_number() {
    Table table = new Table(source, "test2");

    assertThat(table)
        .column().as("var1 type").haveValuesEqualTo(true, null);
  }

  /**
   * This method should fail because the type of the column have less values.
   */
  @Test(expected = AssertionError.class)
  public void should_fail_isOfType_assertion_because_column_have_less_values() {
    Table table2 = new Table(source, "test2");

    assertThat(table2)
        .column(1).as("var2").haveValuesEqualTo(true, null, null);
  }

  /**
   * This method should fail because the type of the second value is {@code null}.
   */
  @Test(expected = AssertionError.class)
  public void should_fail_isOfType_assertion_because_value_is_different() {
    Table table = new Table(source, "test2");

    assertThat(table)
        .column().as("var1 type").haveValuesEqualTo(true, false);
  }

}
