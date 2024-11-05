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
 * Copyright 2015-2024 the original author or authors.
 */
package org.assertj.db.api.assertions;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import org.assertj.core.api.Assertions;
import org.assertj.db.api.TableAssert;
import org.assertj.db.api.TableColumnAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Request;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Tests on {@link org.assertj.db.api.assertions.AssertOnNumberOfRows} class :
 * {@link org.assertj.db.api.assertions.AssertOnNumberOfRows#hasNumberOfRowsLessThan(int)} method.
 *
 * @author Régis Pouiller
 * @author Julien Roy
 */
public class AssertOnNumberOfRows_HasNumberOfRowsLessThan_Test extends AbstractTest {

  /**
   * This method tests the {@code hasNumberOfRowsLessThan} assertion method.
   */
  @Test
  public void test_has_number_of_rows_less_than() {
    Table table = new Table(jdbcConnectionProvider, "actor");
    TableAssert tableAssert = assertThat(table);
    TableAssert tableAssert2 = tableAssert.hasNumberOfRowsLessThan(4);
    Assertions.assertThat(tableAssert).isSameAs(tableAssert2);
    TableColumnAssert tableColumnAssert = assertThat(table).column();
    TableColumnAssert tableColumnAssert2 = tableColumnAssert.hasNumberOfRowsLessThan(4);
    Assertions.assertThat(tableColumnAssert).isSameAs(tableColumnAssert2);
  }

  /**
   * This method should fail because the number of rows is greater or equal.
   */
  @Test
  public void should_fail_because_number_of_rows_is_greater_or_equal() {
    Request request = new Request(jdbcConnectionProvider, "select * from actor");
    try {
      assertThat(request).hasNumberOfRowsLessThan(2);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("['select * from actor' request] %n"
        + "Expecting size (number of rows) to be less than :%n"
        + "   <2>%n"
        + "but was:%n"
        + "   <3>"));
    }
    try {
      assertThat(request).column().hasNumberOfRowsLessThan(2);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[Column at index 0 (column name : ID) of 'select * from actor' request] %n"
        + "Expecting size (number of rows) to be less than :%n"
        + "   <2>%n"
        + "but was:%n"
        + "   <3>"));
    }
  }
}
