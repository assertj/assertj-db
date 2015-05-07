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
import org.assertj.db.type.DateValue;
import org.assertj.db.type.Table;
import org.junit.Test;

import java.sql.Date;
import java.sql.Timestamp;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on {@link AssertionsOnValueChronology} class :
 * {@link AssertionsOnValueChronology#isBefore(org.assertj.db.api.AbstractAssert, org.assertj.core.api.WritableAssertionInfo, Object, org.assertj.db.type.DateValue)} method.
 *
 * @author Régis Pouiller
 *
 */
public class AssertionsOnValueChronology_IsBefore_DateValue_Test {

  /**
   * This method tests the {@code isBefore} assertion method.
   */
  @Test
  public void test_is_before() {
    WritableAssertionInfo info = new WritableAssertionInfo();
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    TableAssert tableAssert2 = AssertionsOnValueChronology.isBefore(tableAssert, info,
                                                                    Timestamp.valueOf("2007-12-23 09:01:05"),
                                                                    DateValue.of(2007, 12, 24));
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    tableAssert2 = AssertionsOnValueChronology.isBefore(tableAssert, info,
                                                        Date.valueOf("2007-12-23"),
                                                        DateValue.of(2007, 12, 24));
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
  }

  /**
   * This method should fail because the value is after or equal to.
   */
  @Test
  public void should_fail_because_value_is_after_or_equal_to() {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    try {
      AssertionsOnValueChronology.isBefore(tableAssert, info,
                                           Timestamp.valueOf("2007-12-23 00:00:00"),
                                           DateValue.of(2007, 12, 23));
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[description] \n"
                                                      + "Expecting:\n"
                                                      + "  <2007-12-23T00:00:00.000000000>\n"
                                                      + "to be before \n"
                                                      + "  <2007-12-23T00:00:00.000000000>");
    }
    try {
      AssertionsOnValueChronology.isBefore(tableAssert, info,
                                           Timestamp.valueOf("2007-12-23 09:01:05"),
                                           DateValue.of(2007, 12, 23));
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[description] \n"
                                                      + "Expecting:\n"
                                                      + "  <2007-12-23T09:01:05.000000000>\n"
                                                      + "to be before \n"
                                                      + "  <2007-12-23T00:00:00.000000000>");
    }
    try {
      AssertionsOnValueChronology.isBefore(tableAssert, info,
                                           Date.valueOf("2007-12-23"),
                                           DateValue.of(2007, 12, 23));
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[description] \n"
                                                      + "Expecting:\n"
                                                      + "  <2007-12-23>\n"
                                                      + "to be before \n"
                                                      + "  <2007-12-23>");
    }
    try {
      AssertionsOnValueChronology.isBefore(tableAssert, info,
                                           Date.valueOf("2007-12-24"),
                                           DateValue.of(2007, 12, 23));
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[description] \n"
                                                      + "Expecting:\n"
                                                      + "  <2007-12-24>\n"
                                                      + "to be before \n"
                                                      + "  <2007-12-23>");
    }
  }

  /**
   * This method should fail because the value is not compatible.
   */
  @Test
  public void should_fail_because_value_is_not_compatible() {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    try {
      AssertionsOnValueChronology.isBefore(tableAssert, info,
                                           "test",
                                           DateValue.of(2007, 12, 23));
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[description] \n"
                                                      + "Expecting:\n"
                                                      + "  <\"test\">\n"
                                                      + "to be of type\n"
                                                      + "  <[DATE, DATE_TIME]>\n"
                                                      + "but was of type\n"
                                                      + "  <TEXT>");
    }
  }
}
