/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2015-2021 the original author or authors.
 */
package org.assertj.db.api.assertions.impl;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.db.api.TableAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Table;
import org.assertj.db.type.Value;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on {@link org.assertj.db.api.assertions.impl.AssertionsOnColumnContent} class :
 * {@link org.assertj.db.api.assertions.impl.AssertionsOnColumnContent#containsValues(org.assertj.db.api.AbstractAssert, org.assertj.core.api.WritableAssertionInfo, java.util.List, Object...)} method.
 *
 * @author Régis Pouiller
 *
 */
public class AssertionsOnColumnContent_ContainsValues_Object_Test extends AbstractTest {

  /**
   * This method tests the {@code containsValues} assertion method.
   */
  @Test
  public void test_contains_values() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    List<Value> list = new ArrayList<>(Arrays.asList(getValue(null, Locale.FRENCH), getValue(null, Locale.ENGLISH), getValue(
            null, Locale.FRENCH), getValue(null, null)));
    TableAssert tableAssert2 = AssertionsOnColumnContent.containsValues(tableAssert, info, list, Locale.FRENCH, Locale.ENGLISH, Locale.FRENCH, null);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    tableAssert2 = AssertionsOnColumnContent.containsValues(tableAssert, info, list, Locale.FRENCH, Locale.FRENCH, Locale.ENGLISH, null);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    tableAssert2 = AssertionsOnColumnContent.containsValues(tableAssert, info, list, Locale.ENGLISH, null, Locale.FRENCH, Locale.FRENCH);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    list = new ArrayList<>(Arrays.asList(getValue(null, Locale.FRENCH), getValue(null, Locale.ENGLISH), getValue(null,
                                                                                                                 Locale.ENGLISH), getValue(
            null, null)));
    tableAssert2 = AssertionsOnColumnContent.containsValues(tableAssert, info, list, null, Locale.ENGLISH,
                                                            Locale.FRENCH, Locale.ENGLISH);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
  }

  /**
   * This method should fail because the values are different.
   */
  @Test
  public void should_fail_because_values_are_different() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    List<Value> list = new ArrayList<>(Arrays.asList(getValue(null, Locale.FRENCH), getValue(null, Locale.ENGLISH), getValue(
            null, Locale.FRENCH), getValue(null, null)));
    try {
      AssertionsOnColumnContent.containsValues(tableAssert, info, list, null, null,
                                               Locale.FRENCH, Locale.ENGLISH);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                                    + "Expecting:%n"
                                                                    + "  <[fr, en, fr, null]>%n"
                                                                    + "to contain: %n"
                                                                    + "  <[null, null, fr, en]>%n"
                                                                    + " (parameter <null> at index 1 is not found)"));
    }
    list = new ArrayList<>(Arrays.asList(getValue(null, true), getValue(null, false), getValue(null, false), getValue(
            null, null)));
    try {
      AssertionsOnColumnContent.containsValues(tableAssert, info, list, null, Locale.ENGLISH,
                                               Locale.FRENCH, Locale.ENGLISH);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                                    + "Expecting:%n"
                                                                    + "  <[true, false, false, null]>%n"
                                                                    + "to contain: %n"
                                                                    + "  <[null, en, fr, en]>%n"
                                                                    + " (parameter <en> at index 1 is not found)"));
    }
  }

  /**
   * This method should fail because one of the values is not of class.
   */
  @Test
  public void should_fail_because_one_value_is_not_of_class() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    List<Value> list = new ArrayList<>(Arrays.asList(getValue(null, "other"), getValue(null, Locale.FRENCH)));
    try {
      AssertionsOnColumnContent.containsValues(tableAssert, info, list, Locale.FRENCH, Locale.FRENCH);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                                    + "Expecting:%n"
                                                                    + "  <[\"other\", fr]>%n"
                                                                    + "to contain: %n"
                                                                    + "  <[fr, fr]>%n"
                                                                    + " (parameter <fr> at index 1 is not found)"));
    }
  }

  /**
   * This method should fail because the number of values is different.
   */
  @Test
  public void should_fail_because_the_number_of_values_is_different() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    List<Value> list = new ArrayList<>(Arrays.asList(getValue(null, Locale.FRENCH), getValue(null, Boolean.FALSE)));
    try {
      AssertionsOnColumnContent.containsValues(tableAssert, info, list, Locale.FRENCH, Boolean.FALSE, Boolean.FALSE);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                                    + "Expecting size (number of rows) to be equal to :%n"
                                                                    + "   <3>%n"
                                                                    + "but was:%n"
                                                                    + "   <2>"));
    }
  }
}
