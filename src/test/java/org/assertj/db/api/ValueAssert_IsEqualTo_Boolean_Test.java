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
 * Tests on the methods which verifies if a value is equal to a boolean.
 * 
 * @author Régis Pouiller
 * 
 */
public class ValueAssert_IsEqualTo_Boolean_Test extends AbstractTest {

  /**
   * This method tests that the value is equal to a boolean.
   */
  @Test
  public void test_if_value_is_equal_to_boolean() {
    Table table = new Table(source, "test");
    assertThat(table).column("var2")
        .value().isEqualTo(true).returnToColumn()
        .value().isEqualTo(false);
  }

  /**
   * This method tests that the value is equal to true boolean.
   */
  @Test
  public void test_if_value_is_true() {
    Table table = new Table(source, "test");
    assertThat(table).column("var2")
        .value().isTrue();
  }

  /**
   * This method tests that the value is equal to false boolean.
   */
  @Test
  public void test_if_value_is_false() {
    Table table = new Table(source, "test");
    assertThat(table).column("var2")
        .value(1).isFalse();
  }

  /**
   * This method should fail because the value is not equal to the boolean.
   */
  @Test
  public void should_fail_because_value_is_not_equal() {
    try {
      Table table = new Table(source, "test");
      assertThat(table).column("var2")
          .value().isEqualTo(false);
      
      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Value at index 0 of Column at index 1 of test table] \n" +
          "Expecting:\n" +
          "  <true>\n" +
          "to be equal to: \n" +
          "  <false>");
    }
  }

  /**
   * This method should fail because the value is equal to true boolean.
   */
  @Test
  public void should_fail_because_value_is_true() {
    try {
      Table table = new Table(source, "test");
      assertThat(table).column("var2")
          .value().isFalse();
      
      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Value at index 0 of Column at index 1 of test table] \n" +
          "Expecting:\n" +
          "  <true>\n" +
          "to be equal to: \n" +
          "  <false>");
    }
  }

  /**
   * This method should fail because the value is equal to true boolean.
   */
  @Test
  public void should_fail_because_value_is_false() {
    try {
      Table table = new Table(source, "test");
      assertThat(table).column("var2")
          .value(1).isTrue();
      
      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Value at index 1 of Column at index 1 of test table] \n" +
          "Expecting:\n" +
          "  <false>\n" +
          "to be equal to: \n" +
          "  <true>");
    }
  }

  /**
   * This method should fail because the value is not a boolean.
   */
  @Test
  public void should_fail_because_value_is_not_a_boolean() {
    try {
      Table table = new Table(source, "test");
      assertThat(table).column("var1")
          .value().as("var1").isTrue();
      
      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[var1] \n" +
          "Expecting:\n" +
          "  <1>\n" +
          "to be of type\n" +
          "  <BOOLEAN>\n" +
          "but was of type\n" +
          "  <NUMBER>");
    }
  }
}
