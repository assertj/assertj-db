package org.assertj.db.api;

import static org.assertj.db.api.Assertions.assertThat;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Test on the {@code haveValuesEqualTo} assertion method on {@code Column} for the {@code Number}.
 * 
 * @author Régis Pouiller
 * 
 */
public class ColumnAssert_HaveValuesEqualTo_Number_Test extends AbstractTest {

  /**
   * This method tests the {@code haveValuesEqualTo} assertion method.
   */
  @Test
  public void test_haveValuesEqualTo_assertion() {
    Table table = new Table(source, "test");

    assertThat(table)
        .column().as("var1").haveValuesEqualTo(1, 10, 100, 0)
        .column("var3").haveValuesEqualTo(2, 20, 25, 0)
        .column().as("var4").haveValuesEqualTo(3, 30, 300, 0)
        .column().as("var5").haveValuesEqualTo(4, 40, 400, 0)
        .column().as("var6").haveValuesEqualTo(5.6, 50.6, 500.6, 0)
        .column().as("var7").haveValuesEqualTo(7.8, 70.8, 700.8, 0)
        .column("var13").haveValuesEqualTo(5, 50, 500, 500)
        .column().as("var14").haveValuesEqualTo(7, 70, 700, 700);

    Table table2 = new Table(source, "test2");

    assertThat(table2)
        .column("var1").haveValuesEqualTo(1, null)
        .column("var3").haveValuesEqualTo(2, null)
        .column("var4").haveValuesEqualTo(3, null)
        .column("var5").haveValuesEqualTo(4, null)
        .column("var6").haveValuesEqualTo(5.6, null)
        .column("var7").haveValuesEqualTo(7.8, null)
        .column("var13").haveValuesEqualTo(5, null)
        .column("var14").haveValuesEqualTo(7, null);
  }

  /**
   * This method should fail because the type of the column is {@code ValueType.Boolean}.
   */
  @Test(expected = AssertionError.class)
  public void should_fail_isOfType_assertion_because_column_is_boolean() {
    Table table = new Table(source, "test2");

    assertThat(table)
        .column(1).as("var2 type").haveValuesEqualTo(1, 10, 100, 0);
  }

  /**
   * This method should fail because the type of the column have less values.
   */
  @Test(expected = AssertionError.class)
  public void should_fail_isOfType_assertion_because_column_have_less_values() {
    Table table2 = new Table(source, "test2");

    assertThat(table2)
        .column().as("var1").haveValuesEqualTo(1, 10, 100);
  }

  /**
   * This method should fail because the type of the second value is {@code null}.
   */
  @Test(expected = AssertionError.class)
  public void should_fail_isOfType_assertion_because_value_is_different() {
    Table table = new Table(source, "test2");

    assertThat(table)
        .column().as("var1").haveValuesEqualTo(1, 1);
  }

}
