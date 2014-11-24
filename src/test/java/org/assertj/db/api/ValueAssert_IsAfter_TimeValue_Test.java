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

import java.text.ParseException;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Table;
import org.assertj.db.type.TimeValue;
import org.junit.Test;

/**
 * Tests on the methods which verifies if a value is after a time value.
 * 
 * @author Régis Pouiller
 * 
 */
public class ValueAssert_IsAfter_TimeValue_Test extends AbstractTest {

  /**
   * This method tests that the value is after a time.
   * @throws ParseException 
   */
  @Test
  public void test_if_value_is_after_time() throws ParseException {
    Table table = new Table(source, "test");
    assertThat(table).column("var8")
        .value().isAfter(TimeValue.of(9, 46, 29)).returnToColumn()
        .value().isAfter(TimeValue.parse("12:29:48"));
  }

  /**
   * This method should fail because the value is not after the time value.
   */
  @Test
  public void should_fail_because_value_is_not_after() {
    try {
      Table table = new Table(source, "test");
      assertThat(table).column("var8")
          .value().isAfter(TimeValue.of(9, 46, 30));
      
      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Value at index 0 of Column at index 7 of test table] \n" +
          "Expecting:\n" +
          "  <09:46:30.000000000>\n" +
          "to be after \n" +
          "  <09:46:30.000000000>");
    }
  }

  /**
   * This method should fail because the value is not a time.
   */
  @Test
  public void should_fail_because_value_is_not_a_date() {
    try {
      Table table = new Table(source, "test");
      assertThat(table).column("var1")
          .value().as("var1").isAfter(TimeValue.of(9, 46, 29));
      
      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[var1] \n" +
          "Expecting:\n" +
          "  <1>\n" +
          "to be of type\n" +
          "  <TIME>\n" +
          "but was of type\n" +
          "  <NUMBER>");
    }
  }

}
