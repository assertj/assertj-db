/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2015 the original author or authors.
 */
package org.assertj.db.api.assertions.impl;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.db.api.TableAssert;
import org.assertj.db.type.Table;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on {@link org.assertj.db.api.assertions.impl.AssertionsOnColumnContent} class :
 * {@link org.assertj.db.api.assertions.impl.AssertionsOnColumnContent#containsValues(org.assertj.db.api.AbstractAssert, org.assertj.core.api.WritableAssertionInfo, java.util.List, UUID...)} method.
 *
 * @author Régis Pouiller
 *
 */
public class AssertionsOnColumnContent_ContainsValues_UUID {

  /**
   * This method tests the {@code containsValues} assertion method.
   */
  @Test
  public void test_contains_values() {
    WritableAssertionInfo info = new WritableAssertionInfo();
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    List<Object> list = new ArrayList<Object>(Arrays.asList(
            UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"),
            UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"),
            UUID.fromString("0E2A1269-EFF0-4233-B87B-B53E8B6F164D"),
            null));
    TableAssert tableAssert2 = AssertionsOnColumnContent.containsValues(tableAssert, info, list,
                                                                        UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"),
                                                                        UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"),
                                                                        UUID.fromString("0E2A1269-EFF0-4233-B87B-B53E8B6F164D"),
                                                                        null);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    tableAssert2 = AssertionsOnColumnContent.containsValues(tableAssert, info, list,
                                                            null,
                                                            UUID.fromString("0E2A1269-EFF0-4233-B87B-B53E8B6F164D"),
                                                            UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"),
                                                            UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"));
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    tableAssert2 = AssertionsOnColumnContent.containsValues(tableAssert, info, list,
                                                            UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"),
                                                            UUID.fromString("0E2A1269-EFF0-4233-B87B-B53E8B6F164D"),
                                                            UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"),
                                                            null);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
  }

  /**
   * This method should fail because the values are different.
   */
  @Test
  public void should_fail_because_values_are_different() {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    List<Object> list = new ArrayList<Object>(Arrays.asList(
            UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"),
            UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"),
            UUID.fromString("0E2A1269-EFF0-4233-B87B-B53E8B6F164D"),
            null));
    try {
      AssertionsOnColumnContent.containsValues(tableAssert, info, list,
                                               UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"),
                                               UUID.fromString("0E2A1269-EFF0-4233-B87B-B53E8B6F164D"),
                                               UUID.fromString("0E2A1269-EFF0-4233-B87B-B53E8B6F164D"),
                                               null);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                                    + "Expecting:%n"
                                                                    + "  <[30b443ae-c0c9-4790-9bec-ce1380808435,%n"
                                                                    + "    30b443ae-c0c9-4790-9bec-ce1380808435,%n"
                                                                    + "    0e2a1269-eff0-4233-b87b-b53e8b6f164d,%n"
                                                                    + "    null]>%n"
                                                                    + "to contain: %n"
                                                                    + "  <[30b443ae-c0c9-4790-9bec-ce1380808435,%n"
                                                                    + "    0e2a1269-eff0-4233-b87b-b53e8b6f164d,%n"
                                                                    + "    0e2a1269-eff0-4233-b87b-b53e8b6f164d,%n"
                                                                    + "    null]>%n"
                                                                    + " (parameter <0e2a1269-eff0-4233-b87b-b53e8b6f164d> at index 2 is not found)"));
    }
    try {
      AssertionsOnColumnContent.containsValues(tableAssert, info, list,
                                               null,
                                               UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"),
                                               UUID.fromString("0E2A1269-EFF0-4233-B87B-B53E8B6F164D"),
                                               null);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                                    + "Expecting:%n"
                                                                    + "  <[30b443ae-c0c9-4790-9bec-ce1380808435,%n"
                                                                    + "    30b443ae-c0c9-4790-9bec-ce1380808435,%n"
                                                                    + "    0e2a1269-eff0-4233-b87b-b53e8b6f164d,%n"
                                                                    + "    null]>%n"
                                                                    + "to contain: %n"
                                                                    + "  <[null,%n"
                                                                    + "    30b443ae-c0c9-4790-9bec-ce1380808435,%n"
                                                                    + "    0e2a1269-eff0-4233-b87b-b53e8b6f164d,%n"
                                                                    + "    null]>%n"
                                                                    + " (parameter <null> at index 3 is not found)"));
    }
  }

  /**
   * This method should fail because one of the values is not a uuid.
   */
  @Test
  public void should_fail_because_one_value_is_not_a_uuid() {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    List<Object> list = new ArrayList<Object>(Arrays.asList("other", UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435")));
    try {
      AssertionsOnColumnContent.containsValues(tableAssert, info, list, UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"), UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"));
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                                    + "Expecting that the value at index 0:%n"
                                                                    + "  <\"other\">%n"
                                                                    + "to be of type%n"
                                                                    + "  <[UUID, NOT_IDENTIFIED]>%n"
                                                                    + "but was of type%n"
                                                                    + "  <TEXT>"));
    }
  }

  /**
   * This method should fail because the number of values is different.
   */
  @Test
  public void should_fail_because_the_number_of_values_is_different() {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    List<Object> list = new ArrayList<Object>(Arrays.asList(UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"), UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435")));
    try {
      AssertionsOnColumnContent.containsValues(tableAssert, info, list, UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"), UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"), UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"));
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
