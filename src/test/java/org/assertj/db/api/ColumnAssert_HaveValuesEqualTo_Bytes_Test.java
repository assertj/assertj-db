package org.assertj.db.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.bytesContentFromClassPathOf;
import static org.junit.Assert.fail;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Test on the {@code haveValuesEqualTo} assertion method on {@code Column} for the arrays of {@code byte}.
 * 
 * @author Régis Pouiller
 * 
 */
public class ColumnAssert_HaveValuesEqualTo_Bytes_Test extends AbstractTest {

  private byte[] bytesTest = bytesContentFromClassPathOf("test.txt");
  private byte[] bytesDev = bytesContentFromClassPathOf("logo-dev.jpg");
  private byte[] bytesH2 = bytesContentFromClassPathOf("h2-logo-2.png");

  /**
   * This method tests the {@code haveValuesEqualTo} assertion method.
   */
  @Test
  public void test_haveValuesEqualTo_assertion() {
    Table table = new Table(source, "test");

    assertThat(table)
        .column("var11").haveValuesEqualTo(bytesH2, bytesDev, bytesDev, bytesDev);

    Table table2 = new Table(source, "test2");

    assertThat(table2)
        .column("var11").haveValuesEqualTo(bytesH2, null);
  }

  /**
   * This method should fail because the type of the column is {@code ValueType.Boolean}.
   */
  @Test
  public void should_fail_haveValuesEqualTo_assertion_because_column_is_boolean() {
    try {
      Table table = new Table(source, "test2");
  
      assertThat(table)
          .column(1).as("var2 type").haveValuesEqualTo(bytesTest);
      
      fail("Une Erreur doit être levée");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[var2 type] \n" +
          "Expecting that the value at index 0:\n" +
          "  <true>\n" +
          "to be of type\n" +
          "  <[BYTES, NOT_IDENTIFIED]>\n" +
          "but was of type\n" +
          "  <BOOLEAN>");
    }
  }

  /**
   * This method should fail because the type of the column have less values.
   */
  @Test
  public void should_fail_haveValuesEqualTo_assertion_because_column_have_less_values() {
    try {
      Table table2 = new Table(source, "test2");
  
      assertThat(table2)
          .column("var11").haveValuesEqualTo(bytesH2, bytesDev, bytesDev);
      
      fail("Une Erreur doit être levée");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Column at index 10 of test2 table] \n" +
          "Expecting size (number of rows) to be equal to :\n" +
          "   <3>\n" +
          "but was:\n" +
          "   <2>");
    }
  }

  /**
   * This method should fail because the second value is {@code null}.
   */
  @Test
  public void should_fail_haveValuesEqualTo_assertion_because_value_is_different_because_is_null() {
    try {
      Table table = new Table(source, "test2");
  
      assertThat(table)
          .column("var11").haveValuesEqualTo(bytesH2, bytesTest);
      
      fail("Une Erreur doit être levée");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Column at index 10 of test2 table] \n" +
          "Expecting that the value at index 1 to be equal to the expected value but was not equal");
    }
  }

  /**
   * This method should fail because the first value is different.
   */
  @Test
  public void should_fail_haveValuesEqualTo_assertion_because_value_is_different() {
    try {
      Table table = new Table(source, "test2");
  
      assertThat(table)
          .column("var11").haveValuesEqualTo(bytesTest, bytesTest);
      
      fail("Une Erreur doit être levée");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Column at index 10 of test2 table] \n" +
          "Expecting that the value at index 0 to be equal to the expected value but was not equal");
    }
  }

}
