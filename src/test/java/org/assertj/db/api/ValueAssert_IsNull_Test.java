package org.assertj.db.api;

import static org.assertj.db.api.Assertions.assertThat;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Test on the methods which verifies if a value is null or not.
 * 
 * @author Régis Pouiller
 * 
 */
public class ValueAssert_IsNull_Test extends AbstractTest {

  /**
   * This method tests the verification of the null value is correct.
   */
  @Test
  public void test_if_a_value_is_null() {
    Table table = new Table(source, "test");
    
    assertThat(table)
        .row(2)
            .value("var11").isNull();
  }

  /**
   * This method should fail because the value is not null.
   */
  @Test(expected = AssertionError.class)
  public void should_fail_because_value_is_not_null() {
    Table table = new Table(source, "test");
    
    assertThat(table)
        .row(2)
            .value("var10").isNull();
  }

  /**
   * This method tests the verification of the not null value is correct.
   */
  @Test
  public void test_if_a_value_is_not_null() {
    Table table = new Table(source, "test");
    
    assertThat(table)
        .row(2)
            .value("var10").isNotNull();
  }

  /**
   * This method should fail because the value is null.
   */
  @Test(expected = AssertionError.class)
  public void should_fail_because_value_is_null() {
    Table table = new Table(source, "test");
    
    assertThat(table)
        .row(2)
            .value("var11").isNotNull();
  }
}
