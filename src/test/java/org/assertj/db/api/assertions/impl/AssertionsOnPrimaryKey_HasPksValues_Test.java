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
 * Copyright 2015-2020 the original author or authors.
 */
package org.assertj.db.api.assertions.impl;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.db.api.TableAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.*;
import org.junit.Test;

import java.sql.Date;
import java.util.Arrays;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on {@link AssertionsOnPrimaryKey} class :
 * {@link AssertionsOnPrimaryKey#hasPksValues(org.assertj.db.api.AbstractAssert, org.assertj.core.api.WritableAssertionInfo, org.assertj.db.type.Change, Object...)} method.
 *
 * @author Régis Pouiller
 *
 */
public class AssertionsOnPrimaryKey_HasPksValues_Test extends AbstractTest {

  /**
   * This method tests the {@code hasPksValues} assertion method.
   */
  @Test
  public void test_has_pks_values() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    Row rowAtStartPoint = getRow(Arrays.asList("ID", "NAME"),
                                 Arrays.asList("ID", "NAME", "FIRSTNAME", "BIRTH"),
                                 Arrays.asList(getValue(null, 1), getValue(null, "Weaver"), getValue(null, "Sigourney"),
                                               getValue(null, Date.valueOf("1949-10-08"))));
    Row rowAtEndPoint = getRow(Arrays.asList("ID", "NAME"),
                               Arrays.asList("ID", "NAME", "FIRSTNAME", "BIRTH"),
                               Arrays.asList(getValue(null, 1), getValue(null, "Weaver"), getValue(null, "Sigourneyy"),
                                             getValue(null, Date.valueOf("1949-10-08"))));
    Change change = getChange(DataType.TABLE, "test", ChangeType.CREATION, rowAtStartPoint, rowAtEndPoint);
    TableAssert tableAssert2 = AssertionsOnPrimaryKey.hasPksValues(tableAssert, info, change, 1, "Weaver");
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
  }

  /**
   * This method should fail because the primary keys values are different.
   */
  @Test
  public void should_fail_because_pks_values_are_different() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    Row rowAtStartPoint = getRow(Arrays.asList("ID", "NAME"),
                                 Arrays.asList("ID", "NAME", "FIRSTNAME", "BIRTH"),
                                 Arrays.asList(getValue(null, 1), getValue(null, "Weaver"), getValue(null, "Sigourney"),
                                               getValue(null, Date.valueOf("1949-10-08"))));
    Row rowAtEndPoint = getRow(Arrays.asList("ID", "NAME"),
                               Arrays.asList("ID", "NAME", "FIRSTNAME", "BIRTH"),
                               Arrays.asList(getValue(null, 1), getValue(null, "Weaver"), getValue(null, "Sigourneyy"),
                                             getValue(null, Date.valueOf("1949-10-08"))));
    Change change = getChange(DataType.TABLE, "test", ChangeType.CREATION, rowAtStartPoint, rowAtEndPoint);
    try {
      AssertionsOnPrimaryKey.hasPksValues(tableAssert, info, change, 1, "Weaverr");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                      + "Expecting :%n"
                                                      + "  [1, \"Weaverr\"]%n"
                                                      + "to be the values of the columns of the primary keys but was:%n"
                                                      + "  [1, \"Weaver\"]"));
    }
  }

  /**
   * This method should fail because the number of primary keys values are different.
   */
  @Test
  public void should_fail_because_number_of_pks_values_are_different() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    Row rowAtStartPoint = getRow(Arrays.asList("ID", "NAME"),
                                 Arrays.asList("ID", "NAME", "FIRSTNAME", "BIRTH"),
                                 Arrays.asList(getValue(null, 1), getValue(null, "Weaver"), getValue(null, "Sigourney"),
                                               getValue(null, Date.valueOf("1949-10-08"))));
    Row rowAtEndPoint = getRow(Arrays.asList("ID", "NAME"),
                               Arrays.asList("ID", "NAME", "FIRSTNAME", "BIRTH"),
                               Arrays.asList(getValue(null, 1), getValue(null, "Weaver"), getValue(null, "Sigourneyy"),
                                             getValue(null, Date.valueOf("1949-10-08"))));
    Change change = getChange(DataType.TABLE, "test", ChangeType.CREATION, rowAtStartPoint, rowAtEndPoint);
    try {
      AssertionsOnPrimaryKey.hasPksValues(tableAssert, info, change, 1, "Weaverr", "Sigourney");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
                                                      + "Expecting :%n"
                                                      + "  [1, \"Weaverr\", \"Sigourney\"]%n"
                                                      + "to be the values of the columns of the primary keys but was:%n"
                                                      + "  [1, \"Weaver\"]"));
    }
  }
}
