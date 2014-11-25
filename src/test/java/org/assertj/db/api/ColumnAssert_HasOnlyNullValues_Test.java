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
package org.assertj.db.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Test on the {@code hasOnlyNullValues} assertion method on {@code Column}.
 * 
 * @author Régis Pouiller
 * 
 */
public class ColumnAssert_HasOnlyNullValues_Test extends AbstractTest {

  /**
   * This method tests the {@code hasOnlyNullValues} assertion method.
   */
  @Test
  public void test_hasOnlyNullValues_assertion() {
    Table table2 = new Table(source, "test2");

    assertThat(table2)
        .column("var15").as("var15").hasOnlyNullValues();
  }

  /**
   * This method should fail because there is a not null in the first row.
   */
  @Test
  public void should_fail_hasOnlyNotNullValues_assertion_because_there_is_a_not_null() {
    try {
      Table table2 = new Table(source, "test2");
  
      assertThat(table2)
          .column(1).as("var2").hasOnlyNullValues();
      
      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[var2] \n" +
          "Expecting to contain only null:\n" +
          "but contains not null at index: 0");
    }
  }

  /**
   * This method should fail because there is only not null value in the column.
   */
  @Test
  public void should_fail_hasOnlyNotNullValues_assertion_because_there_is_only_not_null() {
    try {
      Table table = new Table(source, "test");
  
      assertThat(table)
          .column(1).as("var2").hasOnlyNullValues();
      
      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[var2] \n" +
          "Expecting to contain only null:\n" +
          "but contains not null at index: 0");
    }
  }
}
