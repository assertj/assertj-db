package org.assertj.db.api.assertions.impl;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.db.api.TableAssert;
import org.assertj.db.type.Table;
import org.junit.Test;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on {@link AssertionsOnNumberOfColumns} class :
 * {@link AssertionsOnNumberOfColumns#hasNumberOfColumns(org.assertj.db.api.AbstractAssert, org.assertj.core.api.WritableAssertionInfo, int, int)} method.
 *
 * @author Régis Pouiller
 *
 */
public class AssertionsOnNumberOfColumns_HasNumberOfColumns_Test {

  /**
   * This method tests the {@code hasNumberOfColumns} assertion method.
   */
  @Test
  public void test_has_number_of_columns() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    TableAssert tableAssert2 = AssertionsOnNumberOfColumns.hasNumberOfColumns(tableAssert, info, 3, 3);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
  }

  /**
   * This method should fail because the number of columns is different.
   */
  @Test
  public void should_fail_because_number_of_columns_is_different() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    try {
      AssertionsOnNumberOfColumns.hasNumberOfColumns(tableAssert, info, 3, 4);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[description] \n"
                                                      + "Expecting size (number of columns) to be equal to :\n"
                                                      + "   <4>\n"
                                                      + "but was:\n"
                                                      + "   <3>");
    }
  }
}
