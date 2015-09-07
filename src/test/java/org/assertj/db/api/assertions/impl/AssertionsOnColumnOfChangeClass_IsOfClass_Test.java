package org.assertj.db.api.assertions.impl;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.db.api.TableAssert;
import org.assertj.db.exception.AssertJDBException;
import org.assertj.db.type.Table;
import org.junit.Test;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on {@link AssertionsOnColumnOfChangeClass} class :
 * {@link AssertionsOnColumnOfChangeClass#isOfClass(org.assertj.db.api.AbstractAssert, org.assertj.core.api.WritableAssertionInfo, Object, Object, Class, boolean)} method.
 *
 * @author Régis Pouiller
 *
 */
public class AssertionsOnColumnOfChangeClass_IsOfClass_Test {

  /**
   * This method tests the {@code isOfClass} assertion method.
   */
  @Test
  public void test_is_of_type() {
    WritableAssertionInfo info = new WritableAssertionInfo();
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    TableAssert tableAssert2 = AssertionsOnColumnOfChangeClass.isOfClass(tableAssert, info, "test", "test",
                                                                         String.class, false);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    tableAssert2 = AssertionsOnColumnOfChangeClass.isOfClass(tableAssert, info, "test", "test", String.class, true);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    tableAssert2 = AssertionsOnColumnOfChangeClass.isOfClass(tableAssert, info, null, "test", String.class, true);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
  }

  /**
   * This method should fail because the value at start point have different class.
   */
  @Test
  public void should_fail_because_value_at_start_point_have_different_type() {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    try {
      AssertionsOnColumnOfChangeClass.isOfClass(tableAssert, info,
                                                8, "test", String.class, false);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                                    + "Expecting that the value at start point:%n"
                                                                    + "  <8>%n"
                                                                    + "to be of class%n"
                                                                    + "  <java.lang.String>%n"
                                                                    + "but was of class%n"
                                                                    + "  <java.lang.Integer>"));
    }
    try {
      AssertionsOnColumnOfChangeClass.isOfClass(tableAssert, info,
                                                null, "test", String.class, false);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                                    + "Expecting that the value at start point:%n"
                                                                    + "  <null>%n"
                                                                    + "to be of class%n"
                                                                    + "  <java.lang.String>"));
    }
  }

  /**
   * This method should fail because the value at end point have different class.
   */
  @Test
  public void should_fail_because_value_at_end_point_have_different_type() {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    try {
      AssertionsOnColumnOfChangeClass.isOfClass(tableAssert, info,
                                                "test", 8, String.class, false);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                                    + "Expecting that the value at end point:%n"
                                                                    + "  <8>%n"
                                                                    + "to be of class%n"
                                                                    + "  <java.lang.String>%n"
                                                                    + "but was of class%n"
                                                                    + "  <java.lang.Integer>"));
    }
    try {
      AssertionsOnColumnOfChangeClass.isOfClass(tableAssert, info,
                                                "test", null, String.class, false);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                                    + "Expecting that the value at end point:%n"
                                                                    + "  <null>%n"
                                                                    + "to be of class%n"
                                                                    + "  <java.lang.String>"));
    }
  }

  /**
   * This method should fail because the value at start point is a stringbuilder.
   */
  @Test
  public void should_fail_because_value_at_start_point_is_a_stringbuilder() {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    try {
      AssertionsOnColumnOfChangeClass.isOfClass(tableAssert, info,
                                                new StringBuilder("test"), "test", String.class, false);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                                    + "Expecting that the value at start point:%n"
                                                                    + "  <test>%n"
                                                                    + "to be of class%n"
                                                                    + "  <java.lang.String>%n"
                                                                    + "but was of class%n"
                                                                    + "  <java.lang.StringBuilder>"));
    }
  }

  /**
   * This method should fail because the value at end point is a stringbuilder.
   */
  @Test
  public void should_fail_because_value_at_end_point_is_a_stringbuilder() {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    try {
      AssertionsOnColumnOfChangeClass.isOfClass(tableAssert, info,
                                                "test", new StringBuilder("test"), String.class, false);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                                    + "Expecting that the value at end point:%n"
                                                                    + "  <test>%n"
                                                                    + "to be of class%n"
                                                                    + "  <java.lang.String>%n"
                                                                    + "but was of class%n"
                                                                    + "  <java.lang.StringBuilder>"));
    }
  }

  /**
   * This method should fail because the class value is null.
   */
  @Test
  public void should_fail_because_class_value_is_null() {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    try {
      AssertionsOnColumnOfChangeClass.isOfClass(tableAssert, info, "test", "test",
                                                null, false);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("Class of the column is null"));
    }
  }
}
