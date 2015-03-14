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
package org.assertj.db.api.assertions;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Table;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Test on the {@code hasColumnName} assertion method on {@code Column}.
 *
 * @author Régis Pouiller
 *
 */
public class ColumnAssert_HasColumnName_Test extends AbstractTest {

  /**
   * This method test the assertion on the column name of a {@code Column} from a {@code Table}.
   */
  @Test
  public void test_column_name() {
    Table table = new Table(source, "movie");
    assertThat(table)
              .column().hasColumnName("id")
              .column().hasColumnName("title");
  }

  /**
   * This test should fail because the column name is null.
   */
  @Test(expected = NullPointerException.class)
  public void should_fail_because_the_parameter_is_null() {
      Table table = new Table(source, "movie");
      assertThat(table).column().hasColumnName(null);
  }

  /**
   * This test should fail because the column name is different ("id").
   */
  @Test
  public void should_fail_because_the_column_name_is_different() {
    try {
      Table table = new Table(source, "movie");
      assertThat(table).column().hasColumnName("not_that");

      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Column at index 0 of movie table] \n" + "Expecting :\n"
                                                    + "  \"not_that\"\n" + "to be the name of the column but was:\n"
                                                    + "  \"ID\"");
    }
  }
}
