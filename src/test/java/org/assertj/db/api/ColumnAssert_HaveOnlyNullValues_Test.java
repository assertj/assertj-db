package org.assertj.db.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Test on the {@code haveOnlyNullValues} assertion method on {@code Column}.
 * 
 * @author Régis Pouiller
 * 
 */
public class ColumnAssert_HaveOnlyNullValues_Test extends AbstractTest {

  /**
   * This method tests the {@code haveOnlyNullValues} assertion method.
   */
  @Test
  public void test_haveOnlyNullValues_assertion() {
    Table table2 = new Table(source, "test2");

    assertThat(table2)
        .column("var15").as("var15").haveOnlyNullValues();
  }

  /**
   * This method should fail because there is a not null in the first row.
   */
  @Test
  public void should_fail_haveOnlyNotNullValues_assertion_because_there_is_a_not_null() {
    try {
      Table table2 = new Table(source, "test2");
  
      assertThat(table2)
          .column(1).as("var2").haveOnlyNullValues();
      
      fail("Une Erreur doit être levée");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[var2] \n" +
          "Expecting to contain only null:\n" +
          "but contains not null at index: 0");
    }
  }

  /**
   * This method should fail because there is only not null value in the column.
   */
  @Test
  public void should_fail_haveOnlyNotNullValues_assertion_because_there_is_only_not_null() {
    try {
      Table table = new Table(source, "test");
  
      assertThat(table)
          .column(1).as("var2").haveOnlyNullValues();
      
      fail("Une Erreur doit être levée");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[var2] \n" +
          "Expecting to contain only null:\n" +
          "but contains not null at index: 0");
    }
  }
}
