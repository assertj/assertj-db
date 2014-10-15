package org.assertj.db.api;

import static org.assertj.db.api.Assertions.assertThat;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.DateValue;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Test on the {@code haveValuesEqualTo} assertion method on {@code Column} for the date values.
 * 
 * @author Régis Pouiller
 * 
 */
public class ColumnAssert_HaveValuesEqualTo_DateValue_Test extends AbstractTest {

  /**
   * This method tests the {@code haveValuesEqualTo} assertion method.
   */
  @Test
  public void test_haveValuesEqualTo_assertion() {
    Table table = new Table(source, "test");

    assertThat(table)
        .column("var9").haveValuesEqualTo(DateValue.of(2014, 5, 24), DateValue.of(2014, 5, 30), 
            DateValue.of(2014, 5, 30), DateValue.of(2014, 5, 30))
            .haveValuesEqualTo("2014-05-24", "2014-05-30", "2014-05-30", "2014-05-30");

    Table table2 = new Table(source, "test2");

    assertThat(table2)
        .column("var9").haveValuesEqualTo(DateValue.of(2014, 5, 24), null);
  }

  /**
   * This method should fail because the type of the column is {@code ValueType.Boolean}.
   */
  @Test(expected = AssertionError.class)
  public void should_fail_isOfType_assertion_because_column_is_boolean() {
    Table table = new Table(source, "test2");

    assertThat(table)
        .column(1).as("var2 type").haveValuesEqualTo(DateValue.of(2014, 5, 24), DateValue.of(2014, 5, 30), 
            DateValue.of(2014, 5, 30), DateValue.of(2014, 5, 30));
  }

  /**
   * This method should fail because the type of the column have less values.
   */
  @Test(expected = AssertionError.class)
  public void should_fail_isOfType_assertion_because_column_have_less_values() {
    Table table2 = new Table(source, "test2");

    assertThat(table2)
        .column().as("var9").haveValuesEqualTo(DateValue.of(2014, 5, 24), DateValue.of(2014, 5, 30), 
            DateValue.of(2014, 5, 30));
  }

  /**
   * This method should fail because the type of the second value is {@code null}.
   */
  @Test(expected = AssertionError.class)
  public void should_fail_isOfType_assertion_because_value_is_different() {
    Table table = new Table(source, "test2");

    assertThat(table)
        .column().as("var9").haveValuesEqualTo(DateValue.of(2014, 5, 24), DateValue.of(2014, 5, 24));
  }

}
