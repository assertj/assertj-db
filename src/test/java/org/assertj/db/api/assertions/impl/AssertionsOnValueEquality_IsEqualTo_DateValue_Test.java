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
 * Tests on {@link  AssertionsOnValueEquality} class :
 * {@link  AssertionsOnValueEquality#isEqualTo(org.assertj.db.api.AbstractAssert, org.assertj.core.api.WritableAssertionInfo, Object, org.assertj.db.type.DateValue)} method.
 *
 * @author Régis Pouiller
 *
 */
public class AssertionsOnValueEquality_IsEqualTo_DateValue_Test {

  /**
   * This method tests the {@code isEqualTo} assertion method.
   */
  @Test
  public void test_is_equal_to() {
    WritableAssertionInfo info = new WritableAssertionInfo();
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    TableAssert tableAssert2 = AssertionsOnValueEquality.isEqualTo(tableAssert, info, Date.valueOf("2007-12-23"), DateValue.of(2007, 12, 23));
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    tableAssert2 = AssertionsOnValueEquality.isEqualTo(tableAssert, info, Timestamp.valueOf("2007-12-23 00:00:00"), DateValue.of(2007, 12, 23));
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
  }

  /**
   * This method should fail because the value is greater than or equal to.
   */
  @Test
  public void should_fail_because_value_is_not_equal_to() {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    try {
      AssertionsOnValueEquality.isEqualTo(tableAssert, info, Date.valueOf("2007-12-24"), DateValue.of(2007, 12, 23));
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[description] \n"
                                                      + "Expecting:\n"
                                                      + "  <2007-12-24>\n"
                                                      + "to be equal to: \n"
                                                      + "  <2007-12-23>");
    }
    try {
      AssertionsOnValueEquality.isEqualTo(tableAssert, info, Timestamp.valueOf("2007-12-24 00:00:00"), DateValue.of(2007, 12, 23));
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[description] \n"
                                                      + "Expecting:\n"
                                                      + "  <2007-12-24T00:00:00.000000000>\n"
                                                      + "to be equal to: \n"
                                                      + "  <2007-12-23T00:00:00.000000000>");
    }
  }

  /**
   * This method should fail because the value is not a date.
   */
  @Test
  public void should_fail_because_value_is_not_a_date() {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    try {
      AssertionsOnValueEquality.isEqualTo(tableAssert, info, 8, DateValue.of(2007, 12, 23));
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[description] \n"
                                                      + "Expecting:\n"
                                                      + "  <8>\n"
                                                      + "to be of type\n"
                                                      + "  <[DATE, DATE_TIME]>\n"
                                                      + "but was of type\n"
                                                      + "  <NUMBER>");
    }
  }
}
