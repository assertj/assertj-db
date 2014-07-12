package org.assertj.db.api;

import static org.assertj.db.api.Assertions.assertThat;

import java.text.ParseException;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Table;
import org.assertj.db.type.TimeValue;
import org.junit.Test;

/**
 * Tests on the methods which verifies if a value is equal to a time value.
 * 
 * @author Régis Pouiller
 * 
 */
public class ValueAssert_IsEqualTo_TimeValue_Test extends AbstractTest {

  /**
   * This method tests that the value is equal to a time.
   * @throws ParseException 
   */
  @Test
  public void test_if_value_is_equal_to_time() throws ParseException {
    Table table = new Table(source, "test");
    assertThat(table).column("var8")
        .value().isEqualTo(TimeValue.of(9, 46, 30)).returnToRow()
        .value().isEqualTo(TimeValue.parse("12:29:49"));
  }

  /**
   * This method should fail because the value is not equal to the time value.
   */
  @Test(expected = AssertionError.class)
  public void should_fail_because_value_is_not_equal() {
    Table table = new Table(source, "test");
    assertThat(table).column("var8")
        .value().isEqualTo(TimeValue.of(9, 46, 31));
  }

  /**
   * This method should fail because the value is not a time.
   */
  @Test(expected = AssertionError.class)
  public void should_fail_because_value_is_not_a_date() {
    Table table = new Table(source, "test");
    assertThat(table).column("var1")
        .value().as("var1").isEqualTo(TimeValue.of(9, 46, 30));
  }

}
