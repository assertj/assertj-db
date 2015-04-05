package org.assertj.db.api.assertions.impl;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.db.api.TableAssert;
import org.assertj.db.type.Table;
import org.assertj.db.type.ValueType;
import org.junit.Test;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on {@link org.assertj.db.api.assertions.impl.AssertionsOnColumnOfChangeType} class :
 * {@link org.assertj.db.api.assertions.impl.AssertionsOnColumnOfChangeType#isOfAnyOfTypes(org.assertj.db.api.AbstractAssert, org.assertj.core.api.WritableAssertionInfo, Object, Object, org.assertj.db.type.ValueType...)} method.
 *
 * @author Régis Pouiller
 *
 */
public class AssertionsOnColumnOfChangeType_IsOfAnyOfTypes_Test {

  /**
   * This method tests the {@code isOfAnyOfTypes} assertion method.
   */
  @Test
  public void test_is_of_any_of_types() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    TableAssert tableAssert2 = AssertionsOnColumnOfChangeType.isOfAnyOfTypes(tableAssert, info, "test", "test",
                                                                             ValueType.TEXT);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    tableAssert2 = AssertionsOnColumnOfChangeType.isOfAnyOfTypes(tableAssert, info, "test", "test", ValueType.TEXT,
                                                                 ValueType.NUMBER);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    tableAssert2 = AssertionsOnColumnOfChangeType.isOfAnyOfTypes(tableAssert, info, null, "test", ValueType.TEXT, ValueType.NOT_IDENTIFIED);
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
      AssertionsOnColumnOfChangeType.isOfAnyOfTypes(tableAssert, info,
                                                    8, "test", ValueType.TEXT, ValueType.DATE);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[description] \n"
                                                      + "Expecting that the value at start point:\n"
                                                      + "  <8>\n"
                                                      + "to be of type\n"
                                                      + "  <[TEXT, DATE]>\n"
                                                      + "but was of type\n"
                                                      + "  <NUMBER>");
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
      AssertionsOnColumnOfChangeType.isOfAnyOfTypes(tableAssert, info,
                                                    "test", 8, ValueType.TEXT, ValueType.DATE);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[description] \n"
                                                      + "Expecting that the value at end point:\n"
                                                      + "  <8>\n"
                                                      + "to be of type\n"
                                                      + "  <[TEXT, DATE]>\n"
                                                      + "but was of type\n"
                                                      + "  <NUMBER>");
    }
  }
}
