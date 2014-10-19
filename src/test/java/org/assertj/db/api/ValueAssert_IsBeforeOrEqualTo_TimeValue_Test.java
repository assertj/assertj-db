package org.assertj.db.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

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
  @Test
  public void should_fail_because_value_is_not_before_or_equal_to() {
    try {
      Table table = new Table(source, "test");
      assertThat(table).column("var8")
          .value().isBeforeOrEqualTo(TimeValue.of(9, 46, 29));
      
      fail("Une Erreur doit être levée");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Value at index 0 of Column at index 7 of test table] \n" +
          "Expecting:\n" +
          "  <09:46:30.000000000>\n" +
          "to be before or equal to \n" +
          "  <09:46:29.000000000>");
    }
  }

  /**
   * This method should fail because the value is not a time.
   */
  @Test
  public void should_fail_because_value_is_not_a_date() {
    try {
      Table table = new Table(source, "test");
      assertThat(table).column("var1")
          .value().as("var1").isBeforeOrEqualTo(TimeValue.of(9, 46, 31));
      
      fail("Une Erreur doit être levée");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[var1] \n" +
          "Expecting:\n" +
          "  <1>\n" +
          "to be of type\n" +
          "  <TIME>\n" +
          "but was of type\n" +
          "  <NUMBER>");
    }
  }

}
