package org.assertj.db.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.error.AssertJDBException;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Tests on the methods which verifies if a value is after a string.
 * 
 * @author Régis Pouiller
 * 
 */
public class ValueAssert_IsAfter_String_Test extends AbstractTest {

  /**
   * This method tests that the value is after a string.
   */
  @Test
  public void test_if_value_is_after_string() {
    Table table = new Table(source, "test");
    assertThat(table)
        .column("var10")
            .value()
                .isAfter("2014-05-24T09:46:29")
            .returnToColumn()
            .value()
                .isAfter("2014-05-30T12:29:48")
            .returnToColumn()
        .returnToTable()
        .column("var10")
            .value()
                .isAfter("2014-05-24")
            .returnToColumn()
            .value()
                .isAfter("2014-05-30")
            .returnToColumn()
        .returnToTable()
        .column("var9")
            .value()
                .isAfter("2014-05-23T23:59:59")
            .returnToColumn()
            .value()
                .isAfter("2014-05-29T23:59:59")
            .returnToColumn()
        .returnToTable()
        .column("var9")
            .value()
                .isAfter("2014-05-23")
            .returnToColumn()
            .value()
                .isAfter("2014-05-29")
            .returnToColumn()
        .returnToTable()
        .column("var8")
            .value()
                .isAfter("09:46:29")
            .returnToColumn()
            .value()
                .isAfter("12:29:48");
  }

  /**
   * This method should fail because the value is not after the string.
   */
  @Test
  public void should_fail_because_time_value_is_not_after_time() {
    try {
      Table table = new Table(source, "test");
      assertThat(table).column("var8").value()
          .isAfter("09:46:30");
      
      fail("Une Erreur doit être levée");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Value at index 0 of Column at index 7 of test table] \n" +
          "Expecting:\n" +
          "  <09:46:30.000000000>\n" +
          "to be after \n" +
          "  <09:46:30.000000000>");
    }
  }

  /**
   * This method should fail because the value is not after the string.
   */
  @Test
  public void should_fail_because_date_value_is_not_after_date() {
    try {
      Table table = new Table(source, "test");
      assertThat(table).column("var9").value()
          .isAfter("2014-05-24");
      
      fail("Une Erreur doit être levée");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Value at index 0 of Column at index 8 of test table] \n" +
          "Expecting:\n" +
          "  <2014-05-24T00:00:00.000000000>\n" +
          "to be after \n" +
          "  <2014-05-24T00:00:00.000000000>");
    }
  }

  /**
   * This method should fail because the value is not after the string.
   */
  @Test
  public void should_fail_because_date_value_is_not_after_datetime() {
    try {
      Table table = new Table(source, "test");
      assertThat(table).column("var9").value()
          .isAfter("2014-05-24T00:00");
      
      fail("Une Erreur doit être levée");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Value at index 0 of Column at index 8 of test table] \n" +
          "Expecting:\n" +
          "  <2014-05-24T00:00:00.000000000>\n" +
          "to be after \n" +
          "  <2014-05-24T00:00:00.000000000>");
    }
  }

  /**
   * This method should fail because the value is not after the string.
   */
  @Test
  public void should_fail_because_datetime_value_is_not_after_date() {
    try {
      Table table = new Table(source, "test");
      assertThat(table).column("var10").value(2)
          .isAfter("2014-05-30");
      
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

  /**
   * This method should fail because the value is not after the string.
   */
  @Test
  public void should_fail_because_datetime_value_is_not_after_datetime() {
    try {
      Table table = new Table(source, "test");
      assertThat(table).column("var10").value()
          .isAfter("2014-05-24T09:46:30");
      
      fail("Une Erreur doit être levée");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Value at index 0 of Column at index 9 of test table] \n" +
          "Expecting:\n" +
          "  <2014-05-24T09:46:30.000000000>\n" +
          "to be after \n" +
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
          .isAfter("2014-05-24T09:46:29");
      
      fail("Une Erreur doit être levée");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[var1] \n" +
          "Expecting:\n" +
          "  <1>\n" +
          "to be of type\n" +
          "  <[DATE, TIME, DATE_TIME]>\n" +
          "but was of type\n" +
          "  <NUMBER>");
    }
  }

  /**
   * This method should fail because the string is not correct to compare with date.
   */
  @Test(expected = AssertJDBException.class)
  public void should_fail_because_string_is_not_correct_to_compare_with_date() {
    Table table = new Table(source, "test");
    assertThat(table)
        .column("var9")
            .value()
                .isAfter("a014-05-24");
  }

  /**
   * This method should fail because the string is not correct to compare with time.
   */
  @Test(expected = AssertJDBException.class)
  public void should_fail_because_string_is_not_correct_to_compare_with_time() {
    Table table = new Table(source, "test");
    assertThat(table)
        .column("var8")
            .value()
                .isAfter("a9:46:29");
  }

  /**
   * This method should fail because the string is not correct to compare with date/time.
   */
  @Test(expected = AssertJDBException.class)
  public void should_fail_because_string_is_not_correct_to_compare_with_datetime() {
    Table table = new Table(source, "test");
    assertThat(table)
        .column("var10")
            .value()
                .isAfter("a014-05-24T09:46:29");
  }
}
