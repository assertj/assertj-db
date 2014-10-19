package org.assertj.db.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import java.text.ParseException;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.DateValue;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Tests on the methods which verifies if a value is after a date value.
 * 
 * @author Régis Pouiller
 * 
 */
public class ValueAssert_IsAfter_DateValue_Test extends AbstractTest {

  /**
   * This method tests that the value is after a date.
   * @throws ParseException 
   */
  @Test
  public void test_if_value_is_after_date() throws ParseException {
    Table table = new Table(source, "test");
    assertThat(table)
        .column("var9")
            .value()
                .isAfter(DateValue.of(2014, 5, 23))
            .returnToColumn()
            .value()
                .isAfter(DateValue.parse("2014-05-29"))
            .returnToColumn()
        .returnToTable()
        .column("var10")
            .value()
                .isAfter(DateValue.of(2014, 5, 23))
            .returnToColumn()
            .value()
                .isAfter(DateValue.parse("2014-05-29"))
            .returnToColumn()
        .returnToTable()
;
  }

  /**
   * This method should fail because the value is not after the date value.
   */
  @Test
  public void should_fail_because_value_is_not_after() {
    try {
      Table table = new Table(source, "test");
      assertThat(table).column("var9")
          .value().isAfter(DateValue.of(2014, 5, 24));
      
      fail("Une Erreur doit être levée");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Value at index 0 of Column at index 8 of test table] \n" +
          "Expecting:\n" +
          "  <2014-05-24>\n" +
          "to be after \n" +
          "  <2014-05-24>");
    }
  }

  /**
   * This method should fail because the value is not a date.
   */
  @Test
  public void should_fail_because_value_is_not_a_date() {
    try {
      Table table = new Table(source, "test");
      assertThat(table).column("var1")
          .value().as("var1").isAfter(DateValue.of(2014, 5, 23));
      
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
   * This method tests that the date/time value is after to a date.
   * @throws ParseException 
   */
  @Test
  public void test_if_datetime_value_is_after_date() throws ParseException {
    Table table = new Table(source, "test");
    assertThat(table).column("var10")
        .value(2).isAfter(DateValue.parse("2014-05-29"));
  }

  /**
   * This method should fail because the date/time value is not after the date value.
   * @throws ParseException 
   */
  @Test
  public void should_fail_because_datetime_value_is_not_after() throws ParseException {
    try {
      Table table = new Table(source, "test");
      assertThat(table).column("var10")
          .value(2).isAfter(DateValue.parse("2014-05-30"));
      
      fail("Une Erreur doit être levée");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Value at index 2 of Column at index 9 of test table] \n" +
          "Expecting:\n" +
          "  <2014-05-30T00:00:00.000000000>\n" +
          "to be after \n" +
          "  <2014-05-30T00:00:00.000000000>");
    }
  }

}
