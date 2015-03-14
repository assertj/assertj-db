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
 * Tests on the methods which verifies if a value is not equal to zero.
 * 
 * @author Régis Pouiller
 * 
 */
public class ValueAssert_IsNotZero_Test extends AbstractTest {

  /**
   * This method tests that the value is not equal to zero.
   */
  @Test
  public void test_if_value_is_not_zero() {
    Table table = new Table(source, "test");
    assertThat(table)
        .row()
            .value("var1").isNotZero().returnToRow()
            .value("var3").isNotZero().returnToRow()
            .value("var4").isNotZero().returnToRow()
            .value("var5").isNotZero().returnToRow()
            .value("var13").isNotZero().returnToRow()
            .value("var14").isNotZero().returnToRow()
        .returnToTable()
        .row()
            .value("var1").isNotZero().returnToRow()
            .value("var3").isNotZero().returnToRow()
            .value("var4").isNotZero().returnToRow()
            .value("var5").isNotZero().returnToRow()
            .value("var13").isNotZero().returnToRow()
            .value("var14").isNotZero();
  }

  /**
   * This method should fail because the value is equal to zero.
   */
  @Test
  public void should_fail_because_value_is_zero() {
    try {
      Table table = new Table(source, "test");
      assertThat(table)
          .row(3)
              .value("var3").isNotZero();
      
      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Value at index 2 of Row at index 3 of test table] \n" +
          "Expecting:\n" +
          "  <0>\n" +
          "not to be equal to: \n" +
          "  <0>");
    }
  }

  /**
   * This method should fail because the value is not a number.
   */
  @Test
  public void should_fail_because_value_is_not_a_boolean() {
    try {
      Table table = new Table(source, "test");
      assertThat(table).column("var2")
          .value().as("var2").isNotZero();
      
      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[var2] \n" +
          "Expecting:\n" +
          "  <true>\n" +
          "to be of type\n" +
          "  <NUMBER>\n" +
          "but was of type\n" +
          "  <BOOLEAN>");
    }
  }

}
