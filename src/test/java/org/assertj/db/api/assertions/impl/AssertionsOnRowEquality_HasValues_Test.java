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
 * Copyright 2015-2025 the original author or authors.
 */
package org.assertj.db.api.assertions.impl;

import static org.assertj.core.api.Assertions.fail;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.db.api.TableAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Value;
import org.junit.Test;

/**
 * Tests on {@link AssertionsOnRowEquality} class :
 * {@link AssertionsOnRowEquality#hasValues(org.assertj.db.api.AbstractAssert, org.assertj.core.api.WritableAssertionInfo, java.util.List, Object...)} method.
 *
 * @author Régis Pouiller
 */
public class AssertionsOnRowEquality_HasValues_Test extends AbstractTest {

  /**
   * This method tests the {@code hasValues} assertion method.
   */
  @Test
  public void test_has_values() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    TableAssert tableAssert = new TableAssert(null);
    List<Value> list = new ArrayList<>(Arrays.asList(getValue(null, 1), getValue(null, "Weaver"), getValue(null,
        "Sigourney"),
      getValue(null, Date.valueOf("1949-10-08")), getValue(null, new Locale("fr"))));
    TableAssert tableAssert2 = AssertionsOnRowEquality.hasValues(tableAssert, info, list, 1, "Weaver", "Sigourney",
      "1949-10-08", Locale.FRENCH);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
  }

  /**
   * This method should fail because the values are different.
   */
  @Test
  public void should_fail_because_values_are_different() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    TableAssert tableAssert = new TableAssert(null);
    List<Value> list = new ArrayList<>(Arrays.asList(getValue(null, 1), getValue(null, "Weaver"), getValue(null,
        "Sigourney"),
      getValue(null, Date.valueOf("1949-10-08"))));
    try {
      AssertionsOnRowEquality.hasValues(tableAssert, info, list, 1, "Weaverr", "Sigourney", "1949-10-08");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting that the value at index 1:%n"
        + "  <\"Weaver\">%n"
        + "to be equal to: %n"
        + "  <\"Weaverr\">"));
    }
  }

  /**
   * This method should fail because the types are not compatible.
   */
  @Test
  public void should_fail_because_types_are_not_compatible() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    TableAssert tableAssert = new TableAssert(null);
    List<Value> list = new ArrayList<>(Arrays.asList(getValue(null, 1), getValue(null, "Weaver"), getValue(null,
        null),
      getValue(null, Date.valueOf("1949-10-08"))));
    try {
      AssertionsOnRowEquality.hasValues(tableAssert, info, list, 1, true, "Sigourney", "1949-10-08");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting:%n"
        + "  \"TEXT\" : <\"Weaver\">%n"
        + "to be compatible with %n"
        + "  java.lang.Boolean : <true>"));
    }
    try {
      AssertionsOnRowEquality.hasValues(tableAssert, info, list, 1, "Weaver", "Sigourney", "1949-10-08");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting:%n"
        + "  \"NOT_IDENTIFIED\" : <null>%n"
        + "to be compatible with %n"
        + "  java.lang.String : <\"Sigourney\">"));
    }
    try {
      AssertionsOnRowEquality.hasValues(tableAssert, info, list, 1, null, "Sigourney", "1949-10-08");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting:%n"
        + "  \"TEXT\" : <\"Weaver\">%n"
        + "to be compatible with %n"
        + "  <null>"));
    }
  }

  /**
   * This method should fail because the bytes values are different.
   */
  @Test
  public void should_fail_because_bytes_values_are_different() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    TableAssert tableAssert = new TableAssert(null);
    List<Value> list = new ArrayList<>(Arrays.asList(getValue(null, 1), getValue(null, new byte[]{0, 1}),
      getValue(null, "Sigourney"),
      getValue(null, Date.valueOf("1949-10-08"))));
    try {
      AssertionsOnRowEquality.hasValues(tableAssert, info, list, 1, new byte[]{2, 3}, "Sigourney", "1949-10-08");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting that the value at index 1 to be equal to the expected value but was not equal"));
    }
  }
}
