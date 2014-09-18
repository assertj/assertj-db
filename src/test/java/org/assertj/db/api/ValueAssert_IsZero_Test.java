package org.assertj.db.api;

import static org.assertj.db.api.Assertions.assertThat;

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
  @Test(expected = AssertionError.class)
  public void should_fail_because_value_is_not_zeo() {
    Table table = new Table(source, "test");
    assertThat(table)
        .row()
            .value("var1").isZero();
  }

  /**
   * This method should fail because the value is not a number.
   */
  @Test(expected = AssertionError.class)
  public void should_fail_because_value_is_not_a_boolean() {
    Table table = new Table(source, "test");
    assertThat(table).column("var2")
        .value().as("var2").isZero();
  }

}
