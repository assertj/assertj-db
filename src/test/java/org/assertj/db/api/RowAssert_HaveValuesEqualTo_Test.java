package org.assertj.db.api;

import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.bytesContentFromClassPathOf;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.DateTimeValue;
import org.assertj.db.type.DateValue;
import org.assertj.db.type.Table;
import org.assertj.db.type.TimeValue;
import org.junit.Test;

/**
 * Test on the {@code haveValuesEqualTo) assertion methods on {@code Row}.
 * 
 * @author Régis Pouiller
 * 
 */
public class RowAssert_HaveValuesEqualTo_Test extends AbstractTest {

  private byte[] bytesDev = bytesContentFromClassPathOf("logo-dev.jpg");
  private byte[] bytesH2 = bytesContentFromClassPathOf("h2-logo-2.png");

  /**
   * This method tests the {@code haveValuesEqualTo} assertion method.
   */
  @Test
  public void test_haveValuesEqualTo_assertion() {
    Table table = new Table(source, "test");

    assertThat(table)
        .row().haveValuesEqualTo(1, true, 2, 3, 4, 5.6, 7.8, TimeValue.of(9, 46, 30),
            DateValue.of(2014, 5, 24), DateTimeValue.of(DateValue.of(2014, 5, 24), TimeValue.of(9, 46, 30)),
            bytesH2, "text", 5, 7)
        .row().haveValuesEqualTo("10", false, "20", "30", "40", "50.6", "70.8", TimeValue.of(12, 29, 49),
            DateValue.of(2014, 5, 30), DateTimeValue.of(DateValue.of(2014, 5, 30), TimeValue.of(12, 29, 49)),
            bytesDev, "another text", "50", "70")
        .row().haveValuesEqualTo(100, false, 25, 300, 400, 500.6, 700.8, TimeValue.of(12, 29, 49),
            DateValue.of(2014, 5, 30), DateValue.of(2014, 5, 30),
            bytesDev, "another text again", 500, 700)
        .row().haveValuesEqualTo(0, false, 0, 0, 0, 0, 0, TimeValue.of(12, 29, 49),
            DateValue.of(2014, 5, 30), DateValue.of(2014, 5, 30),
            bytesDev, "another text again", 500, 700);

    Table table2 = new Table(source, "test2");

    assertThat(table2)
        .row().haveValuesEqualTo(1, true, 2, 3, 4, 5.6, 7.8, TimeValue.of(9, 46, 30),
            DateValue.of(2014, 5, 24), DateTimeValue.of(DateValue.of(2014, 5, 24), TimeValue.of(9, 46, 30)),
            bytesH2, "text", 5, 7, null)
        .row().haveValuesEqualTo(null, null, null, null, null, null, null, null, 
            null, null, 
            null, null, null, null, null);
  }

  /**
   * This method should fail because the type of the column is {@code ValueType.Boolean}.
   */
  @Test(expected = AssertionError.class)
  public void should_fail_isOfType_assertion_because_column_is_boolean() {
    Table table2 = new Table(source, "test2");

    assertThat(table2)
        .row().haveValuesEqualTo(1, true, 2, 3, 4, 5.6, 7.8, TimeValue.of(9, 46, 30),
            DateValue.of(2014, 5, 24), DateTimeValue.of(DateValue.of(2014, 5, 24), TimeValue.of(9, 46, 30)),
            bytesH2, "text", 5, 7, null)
        .row().haveValuesEqualTo(null, "1", null, null, null, null, null, null, 
            null, null, 
            null, null, null, null, null);
  }

  /**
   * This method should fail because the second row have more values.
   */
  @Test(expected = AssertionError.class)
  public void should_fail_isOfType_assertion_because_column_have_less_values() {
    Table table2 = new Table(source, "test2");

    assertThat(table2)
        .row().haveValuesEqualTo(1, true, 2, 3, 4, 5.6, 7.8, TimeValue.of(9, 46, 30),
            DateValue.of(2014, 5, 24), DateTimeValue.of(DateValue.of(2014, 5, 24), TimeValue.of(9, 46, 30)),
            bytesH2, "text", 5, 7, null)
        .row().haveValuesEqualTo(null, null, null, null, null, null, null, 
            null, null, 
            null, null, null, null, null);
  }

  /**
   * This method should fail because the second value of the first row is {@code true}.
   */
  @Test(expected = AssertionError.class)
  public void should_fail_isOfType_assertion_because_value_is_different() {
    Table table2 = new Table(source, "test2");

    assertThat(table2)
        .row().haveValuesEqualTo(1, false, 2, 3, 4, 5.6, 7.8, TimeValue.of(9, 46, 30),
            DateValue.of(2014, 5, 24), DateTimeValue.of(DateValue.of(2014, 5, 24), TimeValue.of(9, 46, 30)),
            bytesH2, "text", 5, 7, null)
        .row().haveValuesEqualTo(null, null, null, null, null, null, null, null, 
            null, null, 
            null, null, null, null, null);
  }
}
