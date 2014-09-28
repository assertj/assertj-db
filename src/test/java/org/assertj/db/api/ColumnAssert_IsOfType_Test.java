package org.assertj.db.api;

import static org.assertj.db.api.Assertions.assertThat;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Test on the {@code isOfType} assertion method on {@code Column}.
 * 
 * @author Régis Pouiller
 * 
 */
public class ColumnAssert_IsOfType_Test extends AbstractTest {

  /**
   * This method tests the {@code isOfType} assertion method.
   */
  @Test
  public void test_isOfType_assertion() {
    Table table = new Table(source, "test");

    assertThat(table)
        .column().as("var1").isOfType(ValueType.NUMBER, true)
        .column().as("var2").isOfType(ValueType.BOOLEAN, true)
        .column().as("var3").isOfType(ValueType.NUMBER, true)
        .column().as("var4").isOfType(ValueType.NUMBER, true)
        .column().as("var5").isOfType(ValueType.NUMBER, true)
        .column().as("var6").isOfType(ValueType.NUMBER, true)
        .column().as("var7").isOfType(ValueType.NUMBER, true)
        .column().as("var8").isOfType(ValueType.TIME, true)
        .column().as("var9").isOfType(ValueType.DATE, true)
        .column().as("var10").isOfType(ValueType.DATE_TIME, true)
        .column().as("var11").isOfType(ValueType.BYTES, true)
        .column().as("var12").isOfType(ValueType.TEXT, true)
        .column().as("var13").isOfType(ValueType.NUMBER, true)
        .column().as("var14").isOfType(ValueType.NUMBER, true)
        .column(0).as("var1").isOfType(ValueType.NUMBER, false)
        .column().as("var2").isOfType(ValueType.BOOLEAN, false)
        .column().as("var3").isOfType(ValueType.NUMBER, false)
        .column().as("var4").isOfType(ValueType.NUMBER, false)
        .column().as("var5").isOfType(ValueType.NUMBER, false)
        .column().as("var6").isOfType(ValueType.NUMBER, false)
        .column().as("var7").isOfType(ValueType.NUMBER, false)
        .column().as("var8").isOfType(ValueType.TIME, false)
        .column().as("var9").isOfType(ValueType.DATE, false)
        .column().as("var10").isOfType(ValueType.DATE_TIME, false)
        .column().as("var11").isOfType(ValueType.BYTES, false)
        .column().as("var12").isOfType(ValueType.TEXT, false)
        .column().as("var13").isOfType(ValueType.NUMBER, false)
        .column().as("var14").isOfType(ValueType.NUMBER, false);

    Table table2 = new Table(source, "test2");

    assertThat(table2)
        .column().as("var1").isOfType(ValueType.NUMBER, true)
        .column().as("var2").isOfType(ValueType.BOOLEAN, true)
        .column().as("var3").isOfType(ValueType.NUMBER, true)
        .column().as("var4").isOfType(ValueType.NUMBER, true)
        .column().as("var5").isOfType(ValueType.NUMBER, true)
        .column().as("var6").isOfType(ValueType.NUMBER, true)
        .column().as("var7").isOfType(ValueType.NUMBER, true)
        .column().as("var8").isOfType(ValueType.TIME, true)
        .column().as("var9").isOfType(ValueType.DATE, true)
        .column().as("var10").isOfType(ValueType.DATE_TIME, true)
        .column().as("var11").isOfType(ValueType.BYTES, true)
        .column().as("var12").isOfType(ValueType.TEXT, true)
        .column().as("var13").isOfType(ValueType.NUMBER, true)
        .column().as("var14").isOfType(ValueType.NUMBER, true);
  }

  /**
   * This method should fail because the type of the column is {@code ValueType.Number}.
   */
  @Test(expected = AssertionError.class)
  public void should_faile_isOfType_assertion_because_value_is_number() {
    Table table = new Table(source, "test");

    assertThat(table)
        .column().as("var1 type").isOfType(ValueType.BOOLEAN, true);
  }

  /**
   * This method should fail because a value is {@code null}.
   */
  @Test(expected = AssertionError.class)
  public void should_faile_isOfType_assertion_because_value_is_null() {
    Table table = new Table(source, "test2");

    assertThat(table)
        .column().as("var1 type").isOfType(ValueType.NUMBER, false);
  }

  /**
   * This method tests the result of {@code isNumber}, {@code isBoolean} and others methods.
   */
  @Test
  public void test_the_types_with_the_methods_to_test_that() {
    Table table = new Table(source, "test");

    assertThat(table)
        .column().as("var1").isNumber(true)
        .column().as("var2").isBoolean(true)
        .column().as("var3").isNumber(true)
        .column().as("var4").isNumber(true)
        .column().as("var5").isNumber(true)
        .column().as("var6").isNumber(true)
        .column().as("var7").isNumber(true)
        .column().as("var8").isTime(true)
        .column().as("var9").isDate(true)
        .column().as("var10").isDateTime(true)
        .column().as("var11").isBytes(true)
        .column().as("var12").isText(true)
        .column().as("var13").isNumber(true)
        .column().as("var14").isNumber(true)
        .column(0).as("var1").isNumber(false)
        .column().as("var2").isBoolean(false)
        .column().as("var3").isNumber(false)
        .column().as("var4").isNumber(false)
        .column().as("var5").isNumber(false)
        .column().as("var6").isNumber(false)
        .column().as("var7").isNumber(false)
        .column().as("var8").isTime(false)
        .column().as("var9").isDate(false)
        .column().as("var10").isDateTime(false)
        .column().as("var11").isBytes(false)
        .column().as("var12").isText(false)
        .column().as("var13").isNumber(false)
        .column().as("var14").isNumber(false);

    Table table2 = new Table(source, "test2");

    assertThat(table2)
        .column().as("var1").isNumber(true)
        .column().as("var2").isBoolean(true)
        .column().as("var3").isNumber(true)
        .column().as("var4").isNumber(true)
        .column().as("var5").isNumber(true)
        .column().as("var6").isNumber(true)
        .column().as("var7").isNumber(true)
        .column().as("var8").isTime(true)
        .column().as("var9").isDate(true)
        .column().as("var10").isDateTime(true)
        .column().as("var11").isBytes(true)
        .column().as("var12").isText(true)
        .column().as("var13").isNumber(true)
        .column().as("var14").isNumber(true);
  }

  /**
   * This method should fail because the type of the column is a number.
   */
  @Test(expected = AssertionError.class)
  public void should_fail_isBoolean_assertion_because_value_is_number() {
    Table table = new Table(source, "test");

    assertThat(table)
        .column().as("var1 type").isBoolean(true);
  }

  /**
   * This method should fail because a value is {@code null}.
   */
  @Test(expected = AssertionError.class)
  public void should_fail_isNumber_assertion_because_value_is_null() {
    Table table = new Table(source, "test2");

    assertThat(table)
        .column().as("var1 type").isNumber(false);
  }

}
