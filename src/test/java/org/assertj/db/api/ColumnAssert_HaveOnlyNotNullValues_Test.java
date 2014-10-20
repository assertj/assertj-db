package org.assertj.db.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Test on the {@code haveOnlyNotNullValues} assertion method on {@code Column}.
 * 
 * @author Régis Pouiller
 * 
 */
public class ColumnAssert_HaveOnlyNotNullValues_Test extends AbstractTest {

  /**
   * This method tests the {@code haveOnlyNotNullValues} assertion method.
   */
  @Test
  public void test_haveOnlyNotNullValues_assertion() {
    Table table = new Table(source, "test");

    assertThat(table)
        .column(1).as("var2").haveOnlyNotNullValues();
  }

  /**
   * This method should fail because there is a null in the second row.
   */
  @Test
  public void should_fail_haveOnlyNotNullValues_assertion_because_there_is_a_null() {
    try {
      Table table2 = new Table(source, "test2");
  
      assertThat(table2)
          .column(1).as("var2").haveOnlyNotNullValues();
  
      fail("Une Erreur doit être levée");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[var2] \n" +
          "Expecting to contain only not null:\n" +
          "but contains null at index: 1");
    }
  }

  /**
   * This method should fail because there is only null value in the column.
   */
  @Test
  public void should_fail_haveOnlyNotNullValues_assertion_because_there_is_only_null() {
    try {
      Table table2 = new Table(source, "test2");
  
      assertThat(table2)
          .column("var15").as("var15").haveOnlyNotNullValues();
      
      fail("Une Erreur doit être levée");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[var15] \n" +
          "Expecting to contain only not null:\n" +
          "but contains null at index: 0");
    }
  }

}
