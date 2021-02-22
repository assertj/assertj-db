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
package org.assertj.db.api.assertions;

import org.assertj.core.api.Assertions;
import org.assertj.db.api.TableAssert;
import org.assertj.db.api.TableColumnAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Request;
import org.assertj.db.type.Table;
import org.junit.Test;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on {@link org.assertj.db.api.assertions.AssertOnNumberOfRows} class :
 * {@link org.assertj.db.api.assertions.AssertOnNumberOfRows#hasNumberOfRowsGreaterThan(int)} method.
 *
 * @author Régis Pouiller
 *
 */
public class AssertOnNumberOfRows_HasNumberOfRowsGreaterThan_Test extends AbstractTest {

  /**
   * This method tests the {@code hasNumberOfRowsGreaterThan} assertion method.
   */
  @Test
  public void test_has_number_of_rows_greater_than() {
    Table table = new Table(source, "actor");
    TableAssert tableAssert = assertThat(table);
    TableAssert tableAssert2 = tableAssert.hasNumberOfRowsGreaterThan(2);
    Assertions.assertThat(tableAssert).isSameAs(tableAssert2);
    TableColumnAssert tableColumnAssert = assertThat(table).column();
    TableColumnAssert tableColumnAssert2 = tableColumnAssert.hasNumberOfRowsGreaterThan(2);
    Assertions.assertThat(tableColumnAssert).isSameAs(tableColumnAssert2);
  }

  /**
   * This method should fail because the number of rows is less or equal.
   */
  @Test
  public void should_fail_because_number_of_rows_is_less_or_equal() {
    Request request = new Request(source, "select * from actor");
    try {
      assertThat(request).hasNumberOfRowsGreaterThan(9);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("['select * from actor' request] %n"
                                                                    + "Expecting size (number of rows) to be greater than :%n"
                                                                    + "   <9>%n"
                                                                    + "but was:%n"
                                                                    + "   <3>"));
    }
    try {
      assertThat(request).column().hasNumberOfRowsGreaterThan(9);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[Column at index 0 (column name : ID) of 'select * from actor' request] %n"
                                                                    + "Expecting size (number of rows) to be greater than :%n"
                                                                    + "   <9>%n"
                                                                    + "but was:%n"
                                                                    + "   <3>"));
    }
  }
}
