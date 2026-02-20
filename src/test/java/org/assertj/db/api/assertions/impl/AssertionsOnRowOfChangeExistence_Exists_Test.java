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
import java.util.Arrays;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.db.api.TableAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Row;
import org.junit.Test;

/**
 * Tests on {@link AssertionsOnRowOfChangeExistence} class :
 * {@link AssertionsOnRowOfChangeExistence#exists(org.assertj.db.api.AbstractAssert, org.assertj.core.api.WritableAssertionInfo, org.assertj.db.type.Row)} method.
 *
 * @author Régis Pouiller
 */
public class AssertionsOnRowOfChangeExistence_Exists_Test extends AbstractTest {

  /**
   * This method tests the {@code exists} assertion method.
   */
  @Test
  public void test_exists() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    TableAssert tableAssert = new TableAssert(null);
    Row row = getRow(null, Arrays.asList("ID", "NAME", "FIRSTNAME", "BIRTH"),
      Arrays.asList(getValue(null, 1), getValue(null, "Weaver"), getValue(null, "Sigourney"),
        getValue(null, Date.valueOf("1949-10-08"))));
    TableAssert tableAssert2 = AssertionsOnRowOfChangeExistence.exists(tableAssert, info, row);
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
  }

  /**
   * This method should fail because the row does not exist.
   */
  @Test
  public void should_fail_because_row_does_not_exist() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    TableAssert tableAssert = new TableAssert(null);
    try {
      AssertionsOnRowOfChangeExistence.exists(tableAssert, info, null);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[description] %n"
        + "Expecting exist but do not exist"));
    }
  }
}
