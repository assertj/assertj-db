package org.assertj.db.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Tests on the methods which verifies if a value is not equal to a boolean.
 * 
 * @author Régis Pouiller
 * 
 */
public class ValueAssert_IsNotEqualTo_Boolean_Test extends AbstractTest {

  /**
   * This method tests that the value is not equal to a boolean.
   */
  @Test
  public void test_if_value_is_not_equal_to_boolean() {
    Table table = new Table(source, "test");
    assertThat(table).column("var2")
        .value().isNotEqualTo(false).returnToColumn()
        .value().isNotEqualTo(true);
  }

  /**
   * This method should fail because the value is equal to the boolean.
   */
  @Test
  public void should_fail_because_value_is_equal() {
    try {
      Table table = new Table(source, "test");
      assertThat(table).column("var2")
          .value().isNotEqualTo(true);
      
      fail("Une Erreur doit être levée");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Value at index 0 of Column at index 1 of test table] \n" +
          "Expecting:\n" +
          "  <true>\n" +
          "not to be equal to: \n" +
          "  <true>");
    }
  }

  /**
   * This method should fail because the value is not a boolean.
   */
  @Test
  public void should_fail_because_value_is_not_a_boolean() {
    try {
      Table table = new Table(source, "test");
      assertThat(table).column("var1")
          .value().as("var1").isNotEqualTo(true);
      
      fail("Une Erreur doit être levée");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[var1] \n" +
          "Expecting:\n" +
          "  <1>\n" +
          "to be of type\n" +
          "  <BOOLEAN>\n" +
          "but was of type\n" +
          "  <NUMBER>");
    }
  }

}
