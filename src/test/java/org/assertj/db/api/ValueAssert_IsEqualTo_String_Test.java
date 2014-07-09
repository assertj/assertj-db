package org.assertj.db.api;

import static org.assertj.db.api.Assertions.assertThat;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Tests on the methods which verifies if a value is equal to a string.
 * 
 * @author Régis Pouiller
 * 
 */
public class ValueAssert_IsEqualTo_String_Test extends AbstractTest {

  /**
   * This method tests that the value is equal to a string.
   */
  @Test
  public void test_if_value_is_equal_to_string() {
    Table table = new Table(source, "test");
    assertThat(table).column("var12")
        .value().isEqualTo("text").returnToRow()
        .value().isEqualTo("another text");
  }

  /**
   * This method should fail because the value is not equal to the string.
   */
  @Test(expected = AssertionError.class)
  public void should_fail_because_value_is_not_equal() {
    Table table = new Table(source, "test");
    assertThat(table).column("var12")
        .value().isEqualTo("Text");
  }

  /**
   * This method should fail because the value is not a text.
   */
  @Test(expected = AssertionError.class)
  public void should_fail_because_value_is_not_a_text() {
    Table table = new Table(source, "test");
    assertThat(table).column("var2")
        .value().as("var2").isEqualTo("Text");
  }

}
