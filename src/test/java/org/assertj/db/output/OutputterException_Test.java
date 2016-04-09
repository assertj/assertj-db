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
package org.assertj.db.output;

import org.assertj.core.api.Assertions;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.exception.AssertJDBException;
import org.assertj.db.type.Request;
import org.assertj.db.type.Table;
import org.junit.Test;

import static org.junit.Assert.fail;

/**
 * Test the exception of output.
 *
 * @author Régis Pouiller
 */
public class OutputterException_Test extends AbstractTest {

  /**
   * This method tests the {@code output} output method.
   */
  @Test
  public void test_display_from_column_for_table() throws Exception {
    Table table = new Table(source, "actor");

    try {
      Outputs.output(table).column(null);
      fail("An exception must be raised");
    } catch (NullPointerException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Column name must be not null");
    }

    try {
      Outputs.output(table).column("TEST");
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("Column <TEST> does not exist%n"
                                                      + "in <[ID, NAME, FIRSTNAME, BIRTH, ACTOR_IMDB]>%n"
                                                      + "with comparison IGNORE - Ignore the case"));
    }
  }

  /**
   * This method tests the {@code output} output method.
   */
  @Test
  public void test_display_from_column_for_request() throws Exception {
    Request request = new Request(source, "select * from actor");

    try {
      Outputs.output(request).column(null);
      fail("An exception must be raised");
    } catch (NullPointerException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Column name must be not null");
    }

    try {
      Outputs.output(request).column("TEST");
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("Column <TEST> does not exist%n"
                                                      + "in <[ID, NAME, FIRSTNAME, BIRTH, ACTOR_IMDB]>%n"
                                                      + "with comparison IGNORE - Ignore the case"));
    }
  }

  /**
   * This method tests the {@code output} output method.
   */
  @Test
  public void test_display_from_value_from_row_for_table() throws Exception {
    Table table = new Table(source, "actor");

    try {
      Outputs.output(table).row().value(null);
      fail("An exception must be raised");
    } catch (NullPointerException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Column name must be not null");
    }

    try {
      Outputs.output(table).row().value("TEST");
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("Column <TEST> does not exist%n"
                                                      + "in <[ID, NAME, FIRSTNAME, BIRTH, ACTOR_IMDB]>%n"
                                                      + "with comparison IGNORE - Ignore the case"));
    }
  }

  /**
   * This method tests the {@code output} output method.
   */
  @Test
  public void test_display_from_value_from_row_for_request() throws Exception {
    Request request = new Request(source, "select * from actor");

    try {
      Outputs.output(request).row().value(null);
      fail("An exception must be raised");
    } catch (NullPointerException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Column name must be not null");
    }

    try {
      Outputs.output(request).row().value("TEST");
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("Column <TEST> does not exist%n"
                                                      + "in <[ID, NAME, FIRSTNAME, BIRTH, ACTOR_IMDB]>%n"
                                                      + "with comparison IGNORE - Ignore the case"));
    }
  }
}
