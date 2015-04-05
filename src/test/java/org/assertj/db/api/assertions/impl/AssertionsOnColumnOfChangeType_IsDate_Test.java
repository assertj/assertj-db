package org.assertj.db.api.assertions.impl;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.db.api.TableAssert;
import org.assertj.db.type.Table;
import org.junit.Test;

import java.sql.Date;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on {@link org.assertj.db.api.assertions.impl.AssertionsOnColumnOfChangeType} class :
 * {@link org.assertj.db.api.assertions.impl.AssertionsOnColumnOfChangeType#isDate(org.assertj.db.api.AbstractAssert, org.assertj.core.api.WritableAssertionInfo, Object, Object, boolean)} method.
 *
 * @author Régis Pouiller
 *
 */
public class AssertionsOnColumnOfChangeType_IsDate_Test {

  /**
   * This method tests the {@code isBoolean} assertion method.
   */
  @Test
  public void test_is_date() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    TableAssert tableAssert2 = AssertionsOnColumnOfChangeType.isDate(tableAssert, info, Date.valueOf("2007-12-23"),
                                                                     Date.valueOf("2002-07-25"), false);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    tableAssert2 = AssertionsOnColumnOfChangeType.isDate(tableAssert, info, Date.valueOf("2007-12-23"),
                                                         Date.valueOf("2002-07-25"), true);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    tableAssert2 = AssertionsOnColumnOfChangeType.isDate(tableAssert, info, null, Date.valueOf("2007-12-23"), true);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
  }

  /**
   * This method should fail because the value at start point have different type.
   */
  @Test
  public void should_fail_because_value_at_start_point_have_different_type() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    try {
      AssertionsOnColumnOfChangeType.isDate(tableAssert, info,
                                            "test", Date.valueOf("2007-12-23"), false);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[description] \n"
                                                      + "Expecting that the value at start point:\n"
                                                      + "  <\"test\">\n"
                                                      + "to be of type\n"
                                                      + "  <DATE>\n"
                                                      + "but was of type\n"
                                                      + "  <TEXT>");
    }
  }

  /**
   * This method should fail because the value at end point have different type.
   */
  @Test
  public void should_fail_because_value_at_end_point_have_different_type() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    try {
      AssertionsOnColumnOfChangeType.isDate(tableAssert, info,
                                            Date.valueOf("2007-12-23"), "test", false);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[description] \n"
                                                      + "Expecting that the value at end point:\n"
                                                      + "  <\"test\">\n"
                                                      + "to be of type\n"
                                                      + "  <DATE>\n"
                                                      + "but was of type\n"
                                                      + "  <TEXT>");
    }
  }
}
