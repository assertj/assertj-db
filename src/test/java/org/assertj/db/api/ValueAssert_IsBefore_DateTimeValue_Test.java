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
public class ValueAssert_IsBefore_DateTimeValue_Test extends AbstractTest {

  /**
   * This method tests that the value is before a date/time.
   * 
   * @throws ParseException
   */
  @Test
  public void test_if_value_is_before_datetime() throws ParseException {
    Table table = new Table(source, "test");
    assertThat(table)
        .column("var10")
            .value()
                .isBefore(DateTimeValue.of(DateValue.of(2014, 5, 24), TimeValue.of(9, 46, 31)))
            .returnToColumn()
            .value()
                .isBefore(DateTimeValue.parse("2014-05-30T12:29:50"))
            .returnToColumn()
            .value()
                .isBefore(DateValue.of(2014, 5, 31))
            .returnToColumn()
        .returnToTable()
        .column("var9")
            .value()
                .isBefore(DateTimeValue.parse("2014-05-24T00:01"))
            .returnToColumn()
            .value()
                .isBefore(DateTimeValue.of(DateValue.of(2014, 5, 30), TimeValue.of(0, 1)));
  }

  /**
   * This method should fail because the value is not before the date/time.
   */
  @Test
  public void should_fail_because_value_is_not_before() {
    try {
      Table table = new Table(source, "test");
      assertThat(table).column("var10").value()
          .isBefore(DateTimeValue.of(DateValue.of(2014, 5, 24), TimeValue.of(9, 46, 30)));
      
      fail("Une Erreur doit être levée");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Value at index 0 of Column at index 9 of test table] \n" +
          "Expecting:\n" +
          "  <2014-05-24T09:46:30.000000000>\n" +
          "to be before \n" +
          "  <2014-05-24T09:46:30.000000000>");
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
          .isBefore(DateTimeValue.of(DateValue.of(2014, 5, 24), TimeValue.of(9, 46, 31)));
      
      fail("Une Erreur doit être levée");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[var1] \n" +
          "Expecting:\n" +
          "  <1>\n" +
          "to be of type\n" +
          "  <[DATE, DATE_TIME]>\n" +
          "but was of type\n" +
          "  <NUMBER>");
    }
  }

  /**
   * This method should fail because the value is not before the date.
   */
  @Test
  public void should_fail_because_value_is_not_before_date() {
    try {
      Table table = new Table(source, "test");
      assertThat(table).column("var10").value(2)
          .isBefore(DateValue.of(2014, 5, 30));
      
      fail("Une Erreur doit être levée");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Value at index 2 of Column at index 9 of test table] \n" +
          "Expecting:\n" +
          "  <2014-05-30T00:00:00.000000000>\n" +
          "to be before \n" +
          "  <2014-05-30T00:00:00.000000000>");
    }
  }
}
