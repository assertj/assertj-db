package org.assertj.db.api;

import static org.assertj.db.api.Assertions.assertThat;

import java.text.ParseException;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Table;
import org.assertj.db.type.TimeValue;
import org.junit.Test;

/**
 * Tests on the methods which verifies if a value is after a time value.
 * 
 * @author Régis Pouiller
 * 
 */
public class ValueAssert_IsAfter_TimeValue_Test extends AbstractTest {

  /**
   * This method tests that the value is after a time.
   * @throws ParseException 
   */
  @Test
  public void test_if_value_is_after_time() throws ParseException {
    Table table = new Table(source, "test");
    assertThat(table).column("var8")
        .value().isAfter(TimeValue.of(9, 46, 29)).returnToColumn()
        .value().isAfter(TimeValue.parse("12:29:48"));
  }

  /**
   * This method should fail because the value is not after the time value.
   */
  @Test(expected = AssertionError.class)
  public void should_fail_because_value_is_not_after() {
    Table table = new Table(source, "test");
    assertThat(table).column("var8")
        .value().isAfter(TimeValue.of(9, 46, 30));
  }

  /**
   * This method should fail because the value is not a time.
   */
  @Test(expected = AssertionError.class)
  public void should_fail_because_value_is_not_a_date() {
    Table table = new Table(source, "test");
    assertThat(table).column("var1")
        .value().as("var1").isAfter(TimeValue.of(9, 46, 29));
  }

}
