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
 * Tests on the methods which verifies if a value is not equal to a date/time value.
 * 
 * @author Régis Pouiller
 * 
 */
public class ValueAssert_IsNotEqualTo_DateTimeValue_Test extends AbstractTest {

  /**
   * This method tests that the value is not equal to a date/time.
   * 
   * @throws ParseException
   */
  @Test
  public void test_if_value_is_not_equal_to_date() throws ParseException {
    Table table = new Table(source, "test");
    assertThat(table).column("var10").value()
        .isNotEqualTo(DateTimeValue.of(DateValue.of(2014, 5, 25), TimeValue.of(9, 46, 30))).returnToColumn().value()
        .isNotEqualTo(DateTimeValue.parse("2014-05-30T12:29:50"))
        .column("var9")
        .value().isNotEqualTo(DateTimeValue.of(DateValue.of(2014, 5, 26)))
        .value().isNotEqualTo(DateTimeValue.parse("2014-05-30T00:00:50"));
  }

  /**
   * This method should fail because the value is equal to the date/time value.
   */
  @Test
  public void should_fail_because_value_is_equal() {
    try {
      Table table = new Table(source, "test");
      assertThat(table).column("var10").value()
          .isNotEqualTo(DateTimeValue.of(DateValue.of(2014, 5, 24), TimeValue.of(9, 46, 30)));
      
      fail("Une Erreur doit être levée");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Value at index 0 of Column at index 9 of test table] \n" +
          "Expecting:\n" +
          "  <2014-05-24T09:46:30.000000000>\n" +
          "not to be equal to: \n" +
          "  <2014-05-24T09:46:30.000000000>");
    }
  }

  /**
   * This method should fail because the value is not a date/time.
   */
  @Test
  public void should_fail_because_value_is_not_a_date() {
    try {
      Table table = new Table(source, "test");
      assertThat(table).column("var1").value().as("var1")
          .isNotEqualTo(DateTimeValue.of(DateValue.of(2014, 5, 24), TimeValue.of(9, 46, 30)));
      
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
   * This method should fail because the value is equal to the date value.
   */
  @Test
  public void should_fail_because_value_is_equal_to_date() {
    try {
      Table table = new Table(source, "test");
      assertThat(table).column("var10").value(2)
          .isNotEqualTo(DateValue.of(2014, 5, 30));
      
      fail("Une Erreur doit être levée");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Value at index 2 of Column at index 9 of test table] \n" +
          "Expecting:\n" +
          "  <2014-05-30T00:00:00.000000000>\n" +
          "not to be equal to: \n" +
          "  <2014-05-30T00:00:00.000000000>");
    }
  }
}
