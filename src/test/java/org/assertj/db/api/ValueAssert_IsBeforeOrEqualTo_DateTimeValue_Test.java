package org.assertj.db.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import java.text.ParseException;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.DateTimeValue;
import org.assertj.db.type.DateValue;
import org.assertj.db.type.Table;
import org.assertj.db.type.TimeValue;
import org.junit.Test;

/**
 * Tests on the methods which verifies if a value is before a date/time value.
 * 
 * @author Régis Pouiller
 * 
 */
public class ValueAssert_IsBeforeOrEqualTo_DateTimeValue_Test extends AbstractTest {

  /**
   * This method tests that the value is before or equal to a date/time.
   * 
   * @throws ParseException
   */
  @Test
  public void test_if_value_is_before_or_equal_to_datetime() throws ParseException {
    Table table = new Table(source, "test");
    assertThat(table)
        .column("var10")
            .value()
                .isBeforeOrEqualTo(DateTimeValue.of(DateValue.of(2014, 5, 24), TimeValue.of(9, 46, 31)))
            .returnToColumn()
            .value()
                .isBeforeOrEqualTo(DateTimeValue.parse("2014-05-30T12:29:50"))
            .returnToColumn()
        .returnToTable()
        .column("var9")
            .value()
                .isBeforeOrEqualTo(DateTimeValue.parse("2014-05-24T00:01"))
            .returnToColumn()
            .value()
                .isBeforeOrEqualTo(DateTimeValue.of(DateValue.of(2014, 5, 30), TimeValue.of(0, 1)))
        .column("var10")
            .value()
                .isBeforeOrEqualTo(DateTimeValue.of(DateValue.of(2014, 5, 24), TimeValue.of(9, 46, 30)))
            .value()
                .isBeforeOrEqualTo(DateTimeValue.parse("2014-05-30T12:29:49"))
            .value()
                .isBeforeOrEqualTo(DateValue.parse("2014-05-30"));
  }

  /**
   * This method should fail because the value is not before or equal to the date/time.
   */
  @Test
  public void should_fail_because_value_is_not_before_or_equal_to() {
    try {
      Table table = new Table(source, "test");
      assertThat(table).column("var10").value()
          .isBeforeOrEqualTo(DateTimeValue.of(DateValue.of(2014, 5, 24), TimeValue.of(9, 46, 29)));
      
      fail("Une Erreur doit être levée");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Value at index 0 of Column at index 9 of test table] \n"+
          "Expecting:\n"+
          "  <2014-05-24T09:46:30.000000000>\n"+
          "to be before or equal to \n"+
          "  <2014-05-24T09:46:29.000000000>");
    }
  }

  /**
   * This method should fail because the value is not a date/time.
   */
  @Test
  public void should_fail_because_value_is_not_a_datetime() {
    try {
      Table table = new Table(source, "test");
      assertThat(table).column("var1").value().as("var1")
          .isBeforeOrEqualTo(DateTimeValue.of(DateValue.of(2014, 5, 24), TimeValue.of(9, 46, 31)));
      
      fail("Une Erreur doit être levée");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[var1] \n" +
          "Expecting:\n" +
          "  <1>\n" +
          "to be of type\n" +
          "  <NUMBER>\n" +
          "but was of type\n" +
          "  <[DATE, DATE_TIME]>");
    }
  }

  /**
   * This method should fail because the value is not before or equal to a date.
   */
  @Test
  public void should_fail_because_value_is_not_before_or_equal_to_a_date() {
    try {
      Table table = new Table(source, "test");
      assertThat(table).column("var10").value(2)
          .isBeforeOrEqualTo(DateValue.of(2014, 5, 29));
      
      fail("Une Erreur doit être levée");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Value at index 2 of Column at index 9 of test table] \n" +
          "Expecting:\n" +
          "  <2014-05-30T00:00:00.000000000>\n" +
          "to be before or equal to \n" +
          "  <2014-05-29T00:00:00.000000000>");
    }
  }
}
