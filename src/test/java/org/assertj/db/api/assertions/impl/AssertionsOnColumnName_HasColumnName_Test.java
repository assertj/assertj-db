package org.assertj.db.api.assertions.impl;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.db.api.TableAssert;
import org.assertj.db.type.Table;
import org.junit.Test;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on {@link AssertionsOnColumnName} class :
 * {@link AssertionsOnColumnName#hasColumnName(org.assertj.db.api.AbstractAssert, org.assertj.core.api.WritableAssertionInfo, String, String)}  method.
 *
 * @author Régis Pouiller
 *
 */
public class AssertionsOnColumnName_HasColumnName_Test {

  /**
   * This method tests the {@code hasColumnName} assertion method.
   */
  @Test
  public void test_has_column_name() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    TableAssert tableAssert2 = AssertionsOnColumnName.hasColumnName(tableAssert, info, "test", "test");
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
  }

  /**
   * This method should fail because the column name is different.
   */
  @Test
  public void should_fail_because_column_name_is_different() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    try {
      AssertionsOnColumnName.hasColumnName(tableAssert, info, "test1", "test");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[description] \n"
                                                      + "Expecting :\n"
                                                      + "  \"test\"\n"
                                                      + "to be the name of the column but was:\n"
                                                      + "  \"test1\"");
    }
  }

  /**
   * This method should fail because the column name is null.
   */
  @Test
  public void should_fail_because_column_name_is_null() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    try {
      AssertionsOnColumnName.hasColumnName(tableAssert, info, "test", null);
      fail("An exception must be raised");
    } catch (NullPointerException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Column name must be not null");
    }
  }
}
