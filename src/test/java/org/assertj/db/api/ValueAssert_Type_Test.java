package org.assertj.db.api;

import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Test on the type methods ({@code getType}, {@code isOfType}, ...) on value.
 * 
 * @author Régis Pouiller
 * 
 */
public class ValueAssert_Type_Test extends AbstractTest {

  /**
   * This method tests the result of {@code getType} method.
   */
  @Test
  public void test_the_result_when_getting_the_type() {
    Table table = new Table(source, "test");
    TableRowAssert tableAssert = assertThat(table).row();

    assertThat(tableAssert.value().getType()).as("var1 type").isEqualTo(ValueType.NUMBER);
    assertThat(tableAssert.value().getType()).as("var2 type").isEqualTo(ValueType.BOOLEAN);
    assertThat(tableAssert.value().getType()).as("var3 type").isEqualTo(ValueType.NUMBER);
    assertThat(tableAssert.value().getType()).as("var4 type").isEqualTo(ValueType.NUMBER);
    assertThat(tableAssert.value().getType()).as("var5 type").isEqualTo(ValueType.NUMBER);
    assertThat(tableAssert.value().getType()).as("var6 type").isEqualTo(ValueType.NUMBER);
    assertThat(tableAssert.value().getType()).as("var7 type").isEqualTo(ValueType.NUMBER);
    assertThat(tableAssert.value().getType()).as("var8 type").isEqualTo(ValueType.TIME);
    assertThat(tableAssert.value().getType()).as("var9 type").isEqualTo(ValueType.DATE);
    assertThat(tableAssert.value().getType()).as("var10 type").isEqualTo(ValueType.DATE_TIME);
    assertThat(tableAssert.value().getType()).as("var11 type").isEqualTo(ValueType.BYTES);
    assertThat(tableAssert.value().getType()).as("var12 type").isEqualTo(ValueType.TEXT);
    assertThat(tableAssert.value().getType()).as("var13 type").isEqualTo(ValueType.NUMBER);
    assertThat(tableAssert.value().getType()).as("var14 type").isEqualTo(ValueType.NUMBER);
  }

  /**
   * This method tests the {@code isOfType} assertion method.
   */
  @Test
  public void test_isOfType_assertion() {
    Table table = new Table(source, "test");

    assertThat(table)
        .row()
            .value().as("var1 type").isOfType(ValueType.NUMBER).returnToRow()
            .value().as("var2 type").isOfType(ValueType.BOOLEAN).returnToRow()
            .value().as("var3 type").isOfType(ValueType.NUMBER).returnToRow()
            .value().as("var4 type").isOfType(ValueType.NUMBER).returnToRow()
            .value().as("var5 type").isOfType(ValueType.NUMBER).returnToRow()
            .value().as("var6 type").isOfType(ValueType.NUMBER).returnToRow()
            .value().as("var7 type").isOfType(ValueType.NUMBER).returnToRow()
            .value().as("var8 type").isOfType(ValueType.TIME).returnToRow()
            .value().as("var9 type").isOfType(ValueType.DATE).returnToRow()
            .value().as("var10 type").isOfType(ValueType.DATE_TIME).returnToRow()
            .value().as("var11 type").isOfType(ValueType.BYTES).returnToRow()
            .value().as("var12 type").isOfType(ValueType.TEXT).returnToRow()
            .value().as("var13 type").isOfType(ValueType.NUMBER).returnToRow()
            .value().as("var14 type").isOfType(ValueType.NUMBER);
  }

  /**
   * This method should fail because the type of the value is {@code ValueType.Number}.
   */
  @Test(expected = AssertionError.class)
  public void should_faile_isOfType_assertion_because_value_is_number() {
    Table table = new Table(source, "test");

    assertThat(table)
        .row()
            .value().as("var1 type").isOfType(ValueType.BOOLEAN);
  }
}
