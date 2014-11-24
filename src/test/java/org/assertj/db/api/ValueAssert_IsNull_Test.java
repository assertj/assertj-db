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
 * Test on the methods which verifies if a value is null or not.
 * 
 * @author Régis Pouiller
 * 
 */
public class ValueAssert_IsNull_Test extends AbstractTest {

  /**
   * This method tests the verification of the null value is correct.
   */
  @Test
  public void test_if_a_value_is_null() {
    Table table = new Table(source, "test2");
    
    assertThat(table)
        .row(1)
            .value("var11").isNull();
  }

  /**
   * This method should fail because the value is not null.
   */
  @Test
  public void should_fail_because_value_is_not_null() {
    try {
      Table table = new Table(source, "test2");
      
      assertThat(table)
          .row(0)
              .value("var10").isNull();
      
      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Value at index 9 of Row at index 0 of test2 table] expected:<null> but was:<2014-05-24T09:46:30>");
    }
  }

  /**
   * This method tests the verification of the not null value is correct.
   */
  @Test
  public void test_if_a_value_is_not_null() {
    Table table = new Table(source, "test2");
    
    assertThat(table)
        .row(0)
            .value("var10").isNotNull();
  }

  /**
   * This method should fail because the value is null.
   */
  @Test
  public void should_fail_because_value_is_null() {
    try {
      Table table = new Table(source, "test2");
      
      assertThat(table)
          .row(1)
              .value("var11").isNotNull();
      
      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Value at index 10 of Row at index 1 of test2 table] \n" +
          "Expecting actual not to be null");
    }
  }
}
