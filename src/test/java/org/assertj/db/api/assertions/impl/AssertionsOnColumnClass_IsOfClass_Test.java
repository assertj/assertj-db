package org.assertj.db.api.assertions.impl;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.db.api.TableAssert;
import org.assertj.db.exception.AssertJDBException;
import org.assertj.db.type.Table;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on {@link AssertionsOnColumnClass} class :
 * {@link AssertionsOnColumnClass#isOfClass(org.assertj.db.api.AbstractAssert, org.assertj.core.api.WritableAssertionInfo, java.util.List, Class, boolean)} method.
 *
 * @author Régis Pouiller
 *
 */
public class AssertionsOnColumnClass_IsOfClass_Test {

  /**
   * This method tests the {@code isOfClass} assertion method.
   */
  @Test
  public void test_is_of_type() {
    WritableAssertionInfo info = new WritableAssertionInfo();
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    List<Object> list = new ArrayList<Object>(Arrays.asList("test", "test"));
    TableAssert tableAssert2 = AssertionsOnColumnClass.isOfClass(tableAssert, info, list, String.class, false);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    list = new ArrayList<Object>(Arrays.asList("test", "test"));
    tableAssert2 = AssertionsOnColumnClass.isOfClass(tableAssert, info, list, String.class, true);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    list = new ArrayList<Object>(Arrays.asList(null, "test"));
    tableAssert2 = AssertionsOnColumnClass.isOfClass(tableAssert, info, list, String.class, true);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
  }

  /**
   * This method should fail because the value is not of class.
   */
  @Test
  public void should_fail_because_value_is_not_of_class() {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    try {
      List<Object> list = new ArrayList<Object>(Arrays.asList(8, "test"));
      AssertionsOnColumnClass.isOfClass(tableAssert, info, list, String.class, false);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                                    + "Expecting that the value at index 0:%n"
                                                                    + "  <8>%n"
                                                                    + "to be of class%n"
                                                                    + "  <java.lang.String>%n"
                                                                    + "but was of class%n"
                                                                    + "  <java.lang.Integer>"));
    }
    try {
      List<Object> list = new ArrayList<Object>(Arrays.asList(null, "test"));
      AssertionsOnColumnClass.isOfClass(tableAssert, info, list, String.class, false);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                                    + "Expecting that the value at index 0:%n"
                                                                    + "  <null>%n"
                                                                    + "to be of class%n"
                                                                    + "  <java.lang.String>"));
    }
  }

  /**
   * This method should fail because the value is not of type (with lenience).
   */
  @Test
  public void should_fail_because_value_is_not_of_type_with_lenience() {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    try {
      List<Object> list = new ArrayList<Object>(Arrays.asList("test", 8));
      AssertionsOnColumnClass.isOfClass(tableAssert, info, list, String.class, true);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                                    + "Expecting that the value at index 1:%n"
                                                                    + "  <8>%n"
                                                                    + "to be of class%n"
                                                                    + "  <java.lang.String>%n"
                                                                    + "but was of class%n"
                                                                    + "  <java.lang.Integer>"));
    }
  }

  /**
   * This method should fail because the value is a stringbuiler.
   */
  @Test
  public void should_fail_because_value_is_a_stringbuilder() {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    try {
      List<Object> list = new ArrayList<Object>(Arrays.asList(new StringBuilder("test"), true));
      AssertionsOnColumnClass.isOfClass(tableAssert, info, list, String.class, false);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                                    + "Expecting that the value at index 0:%n"
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
    List<Object> list = new ArrayList<Object>(Arrays.asList("test", "test"));
    try {
      AssertionsOnColumnClass.isOfClass(tableAssert, info, list, null, false);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("Class of the column is null"));
    }
  }
}
