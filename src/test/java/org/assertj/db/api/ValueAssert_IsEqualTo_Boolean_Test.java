package org.assertj.db.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Tests on the methods which verifies if a value is equal to a boolean.
 * 
 * @author Régis Pouiller
 * 
 */
public class ValueAssert_IsEqualTo_Boolean_Test extends AbstractTest {

  /**
   * This method tests that the value is equal to a boolean.
   */
  @Test
  public void test_if_value_is_equal_to_boolean() {
    Table table = new Table(source, "test");
    assertThat(table).column("var2")
        .value().isEqualTo(true).returnToColumn()
        .value().isEqualTo(false);
  }

  /**
   * This method tests that the value is equal to true boolean.
   */
  @Test
  public void test_if_value_is_true() {
    Table table = new Table(source, "test");
    assertThat(table).column("var2")
        .value().isTrue();
  }

  /**
   * This method tests that the value is equal to false boolean.
   */
  @Test
  public void test_if_value_is_false() {
    Table table = new Table(source, "test");
    assertThat(table).column("var2")
        .value(1).isFalse();
  }

  /**
   * This method should fail because the value is not equal to the boolean.
   */
  @Test
  public void should_fail_because_value_is_not_equal() {
    try {
      Table table = new Table(source, "test");
      assertThat(table).column("var2")
          .value().isEqualTo(false);
      
      fail("Une Erreur doit être levée");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Value at index 0 of Column at index 1 of test table] \n" +
          "Expecting:\n" +
          "  <true>\n" +
          "to be equal to: \n" +
          "  <false>");
    }
  }

  /**
   * This method should fail because the value is equal to true boolean.
   */
  @Test
  public void should_fail_because_value_is_true() {
    try {
      Table table = new Table(source, "test");
      assertThat(table).column("var2")
          .value().isFalse();
      
      fail("Une Erreur doit être levée");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Value at index 0 of Column at index 1 of test table] \n" +
          "Expecting:\n" +
          "  <true>\n" +
          "to be equal to: \n" +
          "  <false>");
    }
  }

  /**
   * This method should fail because the value is equal to true boolean.
   */
  @Test
  public void should_fail_because_value_is_false() {
    try {
      Table table = new Table(source, "test");
      assertThat(table).column("var2")
          .value(1).isTrue();
      
      fail("Une Erreur doit être levée");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Value at index 1 of Column at index 1 of test table] \n" +
          "Expecting:\n" +
          "  <false>\n" +
          "to be equal to: \n" +
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
          .value().as("var1").isTrue();
      
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
