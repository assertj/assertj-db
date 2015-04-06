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
 * Copyright 2012-2014 the original author or authors.
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
 * Tests on {@link AssertionsOnModifiedColumns} class :
 * {@link AssertionsOnModifiedColumns#hasModifiedColumns(org.assertj.db.api.AbstractAssert, org.assertj.core.api.WritableAssertionInfo, org.assertj.db.type.Change, Integer...)} method.
 *
 * @author Régis Pouiller
 *
 */
public class AssertionsOnModifiedColumns_HasModifiedColumns_Integer_Test extends AbstractTest {

  /**
   * This method tests the {@code hasModifiedColumns} assertion method.
   */
  @Test
  public void test_has_modified_columns() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    Row rowAtStartPoint = getRow(null,
                                 Arrays.asList("ID", "NAME", "FIRSTNAME", "BIRTH"),
                                 Arrays.asList(1, "Weaver", "Sigourney", Date.valueOf("1949-10-08")));
    Row rowAtEndPoint = getRow(null,
                               Arrays.asList("ID", "NAME", "FIRSTNAME", "BIRTH"),
                               Arrays.asList(1, "Weaverr", "Sigourneyy", Date.valueOf("1949-10-08")));
    Change change = getChange(DataType.TABLE, "test", ChangeType.MODIFICATION, rowAtStartPoint, rowAtEndPoint);
    TableAssert tableAssert2 = AssertionsOnModifiedColumns.hasModifiedColumns(tableAssert, info, change, 1, 2);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    tableAssert2 = AssertionsOnModifiedColumns.hasModifiedColumns(tableAssert, info, change, 2, 1);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
  }

  /**
   * This method should fail because the modified columns are different.
   */
  @Test
  public void should_fail_because_modified_columns_are_different() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    Row rowAtStartPoint = getRow(null,
                                 Arrays.asList("ID", "NAME", "FIRSTNAME", "BIRTH"),
                                 Arrays.asList(1, "Weaver", "Sigourney", Date.valueOf("1949-10-08")));
    Row rowAtEndPoint = getRow(null,
                               Arrays.asList("ID", "NAME", "FIRSTNAME", "BIRTH"),
                               Arrays.asList(1, "Weaverr", "Sigourneyy", Date.valueOf("1949-10-08")));
    Change change = getChange(DataType.TABLE, "test", ChangeType.MODIFICATION, rowAtStartPoint, rowAtEndPoint);
    try {
      AssertionsOnModifiedColumns.hasModifiedColumns(tableAssert, info, change, 2, 3);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[description] \n"
                                                      + "Expecting :\n"
                                                      + "  [2, 3]\n"
                                                      + "as indexes of modified columns but was:\n"
                                                      + "  [1, 2]");
    }
  }
}
