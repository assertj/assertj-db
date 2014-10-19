package org.assertj.db.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Tests on the methods which verifies if a value is equal to zero.
 * 
 * @author Régis Pouiller
 * 
 */
public class ValueAssert_IsZero_Test extends AbstractTest {

  /**
   * This method tests that the value is equal to zero.
   */
  @Test
  public void test_if_value_is_zero() {
    Table table = new Table(source, "test");
    assertThat(table)
        .row(3)
            .value("var1").isZero().returnToRow()
            .value("var3").isZero().returnToRow()
            .value("var4").isZero().returnToRow()
            .value("var5").isZero().returnToRow()
            .value("var6").isZero().returnToRow()
            .value("var7").isZero();
  }

  /**
   * This method should fail because the value is not equal to zero.
   */
  @Test
  public void should_fail_because_value_is_not_zeo() {
    try {
      Table table = new Table(source, "test");
      assertThat(table)
          .row()
              .value("var1").isZero();
      
      fail("Une Erreur doit être levée");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Value at index 0 of Row at index 0 of test table] \n" +
          "Expecting:\n" +
          "  <1>\n" +
          "to be equal to: \n" +
          "  <0>");
    }
  }

  /**
   * This method should fail because the value is not a number.
   */
  @Test
  public void should_fail_because_value_is_not_a_boolean() {
    try {
      Table table = new Table(source, "test");
      assertThat(table).column("var2")
          .value().as("var2").isZero();
      
      fail("Une Erreur doit être levée");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[var2] \n" +
          "Expecting:\n" +
          "  <true>\n" +
          "to be of type\n" +
          "  <NUMBER>\n" +
          "but was of type\n" +
          "  <BOOLEAN>");
    }
  }

}
