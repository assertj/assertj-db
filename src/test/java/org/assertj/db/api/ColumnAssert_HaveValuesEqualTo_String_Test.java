package org.assertj.db.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Test on the {@code haveValuesEqualTo} assertion method on {@code Column} for the texts.
 * 
 * @author Régis Pouiller
 * 
 */
public class ColumnAssert_HaveValuesEqualTo_String_Test extends AbstractTest {

  /**
   * This method tests the {@code haveValuesEqualTo} assertion method.
   */
  @Test
  public void test_haveValuesEqualTo_assertion() {
    Table table = new Table(source, "test");

    assertThat(table)
        .column().as("var1").haveValuesEqualTo("1", "10", "100", "0")
        .column("var3").haveValuesEqualTo("2", "20", "25", "0")
        .column().as("var4").haveValuesEqualTo("3", "30", "300", "0")
        .column().as("var5").haveValuesEqualTo("4", "40", "400", "0")
        .column().as("var6").haveValuesEqualTo("5.6", "50.6", "500.6", "0")
        .column().as("var7").haveValuesEqualTo("7.8", "70.8", "700.8", "0")
        .column("var8").haveValuesEqualTo("09:46:30", "12:29:49", "12:29:49", "12:29:49")
        .column("var9").haveValuesEqualTo("2014-05-24", "2014-05-30", "2014-05-30", "2014-05-30")
        .column("var9").haveValuesEqualTo("2014-05-24T00:00", "2014-05-30T00:00", "2014-05-30T00:00", "2014-05-30T00:00")
        .column("var9").haveValuesEqualTo("2014-05-24T00:00:00", "2014-05-30T00:00:00", "2014-05-30T00:00:00", "2014-05-30T00:00:00")
        .column("var9").haveValuesEqualTo("2014-05-24T00:00:00.000000000", "2014-05-30T00:00:00.000000000", "2014-05-30T00:00:00.000000000", "2014-05-30T00:00:00.000000000")
        .column("var10").haveValuesEqualTo("2014-05-24T09:46:30.000000000", "2014-05-30T12:29:49.000000000", "2014-05-30T00:00:00", "2014-05-30T00:00:00")
        .column("var10").haveValuesEqualTo("2014-05-24T09:46:30", "2014-05-30T12:29:49", "2014-05-30", "2014-05-30")
        .column("var10").haveValuesEqualTo("2014-05-24T09:46:30", "2014-05-30T12:29:49", "2014-05-30T00:00", "2014-05-30T00:00")
        .column("var12").haveValuesEqualTo("text", "another text", "another text again", "another text again")
        .column("var13").haveValuesEqualTo("5", "50", "500", "500")
        .column().as("var14").haveValuesEqualTo("7", "70", "700", "700");

    Table table2 = new Table(source, "test2");

    assertThat(table2)
        .column("var1").haveValuesEqualTo("1", null)
        .column("var3").haveValuesEqualTo("2", null)
        .column("var4").haveValuesEqualTo("3", null)
        .column("var5").haveValuesEqualTo("4", null)
        .column("var6").haveValuesEqualTo("5.6", null)
        .column("var7").haveValuesEqualTo("7.8", null)
        .column("var8").haveValuesEqualTo("09:46:30", null)
        .column("var9").haveValuesEqualTo("2014-05-24", null)
        .column("var9").haveValuesEqualTo("2014-05-24T00:00", null)
        .column("var9").haveValuesEqualTo("2014-05-24T00:00:00", null)
        .column("var9").haveValuesEqualTo("2014-05-24T00:00:00.000000000", null)
        .column("var10").haveValuesEqualTo("2014-05-24T09:46:30.000000000", null)
        .column("var10").haveValuesEqualTo("2014-05-24T09:46:30", null)
        .column("var10").haveValuesEqualTo("2014-05-24T09:46:30", null)
        .column("var12").haveValuesEqualTo("text", null)
        .column("var13").haveValuesEqualTo("5", null)
        .column("var14").haveValuesEqualTo("7", null);
  }

  /**
   * This method should fail because the type of the column is {@code ValueType.Boolean}.
   */
  @Test
  public void should_fail_isOfType_assertion_because_column_is_boolean() {
    try {
      Table table = new Table(source, "test2");
  
      assertThat(table)
          .column(1).as("var2 type").haveValuesEqualTo("1", "10", "100", "0");
      
      fail("Une Erreur doit être levée");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[var2 type] \n" +
          "Expecting that the value at index 0:\n" +
          "  <true>\n" +
          "to be of type\n" +
          "  <[TEXT, NUMBER, DATE, TIME, DATE_TIME, NOT_IDENTIFIED]>\n" +
          "but was of type\n" +
          "  <BOOLEAN>");
    }
  }

  /**
   * This method should fail because the type of the column have less values.
   */
  @Test
  public void should_fail_isOfType_assertion_because_column_have_less_values() {
    try {
      Table table2 = new Table(source, "test2");
  
      assertThat(table2)
          .column("var1").haveValuesEqualTo("1", "10", "100");
      
      fail("Une Erreur doit être levée");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Column at index 0 of test2 table] \n" +
          "Expecting size (number of rows) to be equal to :\n" +
          "   <3>\n" +
          "but was:\n" +
          "   <2>");
    }
  }

  /**
   * This method should fail because the second value is a null number.
   */
  @Test
  public void should_fail_isOfType_assertion_because_value_is_null_number() {
    try {
      Table table = new Table(source, "test2");
  
      assertThat(table)
          .column("var1").haveValuesEqualTo("1", "1");
      
      fail("Une Erreur doit être levée");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Column at index 0 of test2 table] \n" +
          "Expecting that the value at index 1:\n" +
          "  <null>\n" +
          "to be equal to: \n" +
          "  <\"1\">");
    }
  }

  /**
   * This method should fail because the first value is a different number.
   */
  @Test
  public void should_fail_isOfType_assertion_because_value_is_different_number() {
    try {
      Table table = new Table(source, "test2");
  
      assertThat(table)
          .column("var1").haveValuesEqualTo("2", "1");
      
      fail("Une Erreur doit être levée");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Column at index 0 of test2 table] \n" +
          "Expecting that the value at index 0:\n" +
          "  <\"1\">\n" +
          "to be equal to: \n" +
          "  <\"2\">");
    }
  }

  /**
   * This method should fail because the second value is a null time.
   */
  @Test
  public void should_fail_isOfType_assertion_because_value_is_null_time() {
    try {
      Table table = new Table(source, "test2");
  
      assertThat(table)
          .column("var8").haveValuesEqualTo("09:46:30", "09:46:30");
      
      fail("Une Erreur doit être levée");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Column at index 7 of test2 table] \n" +
          "Expecting that the value at index 1:\n" +
          "  <null>\n" +
          "to be equal to: \n" +
          "  <\"09:46:30\">");
    }
  }

  /**
   * This method should fail because the first value is a different time.
   */
  @Test
  public void should_fail_isOfType_assertion_because_value_is_different_time() {
    try {
      Table table = new Table(source, "test2");
  
      assertThat(table)
          .column("var8").haveValuesEqualTo("09:46:31", "09:46:30");
      
      fail("Une Erreur doit être levée");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Column at index 7 of test2 table] \n" +
          "Expecting that the value at index 0:\n" +
          "  <\"09:46:30.000000000\">\n" +
          "to be equal to: \n" +
          "  <\"09:46:31\">");
    }
  }

  /**
   * This method should fail because the second value is a null date.
   */
  @Test
  public void should_fail_isOfType_assertion_because_value_is_null_date() {
    try {
      Table table = new Table(source, "test2");
  
      assertThat(table)
          .column("var9").haveValuesEqualTo("2014-05-24", "2014-05-24");
      
      fail("Une Erreur doit être levée");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Column at index 8 of test2 table] \n" +
          "Expecting that the value at index 1:\n" +
          "  <null>\n" +
          "to be equal to: \n" +
          "  <\"2014-05-24\">");
    }
  }

  /**
   * This method should fail because the first value is a different date.
   */
  @Test
  public void should_fail_isOfType_assertion_because_value_is_different_date() {
    try {
      Table table = new Table(source, "test2");
  
      assertThat(table)
          .column("var9").haveValuesEqualTo("2014-05-25", "2014-05-24");
      
      fail("Une Erreur doit être levée");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Column at index 8 of test2 table] \n" +
          "Expecting that the value at index 0:\n" +
          "  <\"2014-05-24\">\n" +
          "to be equal to: \n" +
          "  <\"2014-05-25\">");
    }
  }

  /**
   * This method should fail because the second value is a null date/time.
   */
  @Test
  public void should_fail_isOfType_assertion_because_value_is_null_datetime() {
    try {
      Table table = new Table(source, "test2");
  
      assertThat(table)
          .column("var10").haveValuesEqualTo("2014-05-24T09:46:30", "2014-05-24T09:46:30");
      
      fail("Une Erreur doit être levée");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Column at index 9 of test2 table] \n" +
          "Expecting that the value at index 1:\n" +
          "  <null>\n" +
          "to be equal to: \n" +
          "  <\"2014-05-24T09:46:30\">");
    }
  }

  /**
   * This method should fail because the first value is a different date/time.
   */
  @Test
  public void should_fail_isOfType_assertion_because_value_is_different_datetime() {
    try {
      Table table = new Table(source, "test2");
  
      assertThat(table)
          .column("var10").haveValuesEqualTo("2014-05-24T09:46:31", "2014-05-24T09:46:30");
      
      fail("Une Erreur doit être levée");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Column at index 9 of test2 table] \n" +
          "Expecting that the value at index 0:\n" +
          "  <\"2014-05-24T09:46:30.000000000\">\n" +
          "to be equal to: \n" +
          "  <\"2014-05-24T09:46:31\">");
    }
  }

  /**
   * This method should fail because the second value is a null text.
   */
  @Test
  public void should_fail_isOfType_assertion_because_value_is_null_text() {
    try {
      Table table = new Table(source, "test2");
  
      assertThat(table)
          .column("var12").haveValuesEqualTo("text", "text");
      
      fail("Une Erreur doit être levée");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Column at index 11 of test2 table] \n" +
          "Expecting that the value at index 1:\n" +
          "  <null>\n" +
          "to be equal to: \n" +
          "  <\"text\">");
    }
  }

  /**
   * This method should fail because the first value is a different text.
   */
  @Test
  public void should_fail_isOfType_assertion_because_value_is_different_text() {
    try {
      Table table = new Table(source, "test2");
  
      assertThat(table)
          .column("var12").haveValuesEqualTo("texT", "text");
      
      fail("Une Erreur doit être levée");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Column at index 11 of test2 table] \n" +
          "Expecting that the value at index 0:\n" +
          "  <\"text\">\n" +
          "to be equal to: \n" +
          "  <\"texT\">");
    }
  }

}
