package org.assertj.db.api;

import static org.assertj.db.api.Assertions.assertThat;

import java.text.ParseException;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Table;
import org.assertj.db.type.TimeValue;
import org.junit.Test;

/**
 * Tests on the methods which verifies if a value is before a time value.
 * 
 * @author Régis Pouiller
 * 
 */
public class ValueAssert_IsBeforeOrEqualTo_TimeValue_Test extends AbstractTest {

  /**
   * This method tests that the value is before or equal to a time.
   * @throws ParseException 
   */
  @Test
  public void test_if_value_is_before_or_equal_to_time() throws ParseException {
    Table table = new Table(source, "test");
    assertThat(table)
        .column("var8")
            .value().isBeforeOrEqualTo(TimeValue.of(9, 46, 31)).returnToColumn()
            .value().isBeforeOrEqualTo(TimeValue.parse("12:29:50"))
        .column("var8")
            .value().isBeforeOrEqualTo(TimeValue.of(9, 46, 30))
            .value().isBeforeOrEqualTo(TimeValue.parse("12:29:49"));
  }

  /**
   * This method should fail because the value is not before or equal to the time value.
   */
  @Test(expected = AssertionError.class)
  public void should_fail_because_value_is_not_before_or_equal_to() {
    Table table = new Table(source, "test");
    assertThat(table).column("var8")
        .value().isBeforeOrEqualTo(TimeValue.of(9, 46, 29));
  }

  /**
   * This method should fail because the value is not a time.
   */
  @Test(expected = AssertionError.class)
  public void should_fail_because_value_is_not_a_date() {
    Table table = new Table(source, "test");
    assertThat(table).column("var1")
        .value().as("var1").isBeforeOrEqualTo(TimeValue.of(9, 46, 31));
  }

}
