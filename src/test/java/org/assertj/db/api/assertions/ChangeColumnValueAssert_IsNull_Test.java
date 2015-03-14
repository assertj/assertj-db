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
import org.assertj.db.common.NeedReload;
import org.assertj.db.type.Changes;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Test on the methods which verifies if a value is null or not.
 *
 * @author Régis Pouiller
 *
 */
public class ChangeColumnValueAssert_IsNull_Test extends AbstractTest {

  /**
   * This method tests the verification of the null value is correct.
   */
  @Test
  @NeedReload
  public void test_if_a_value_is_null() {
    Changes changes = new Changes(source).setStartPointNow();
    update("insert into test2 values (null, null, null, null, null, null, null, null, null, null, null, null, null, null, null)");
    changes.setEndPointNow();

    assertThat(changes).change().column("var11").valueAtEndPoint()
                       .isNull();
  }

  /**
   * This method should fail because the value is not null.
   */
  @Test
  @NeedReload
  public void should_fail_because_value_is_not_null() {
    try {
      Changes changes = new Changes(source).setStartPointNow();
      update("insert into test2 values (1, true, 2, 3, 4, 5.6, 7.8, PARSEDATETIME('09:46:30', 'HH:mm:ss'),\n"
             + "    PARSEDATETIME('24/05/2014', 'dd/MM/yyyy'), PARSEDATETIME('24/05/2014 09:46:30', 'dd/MM/yyyy HH:mm:ss'),\n"
             + "    FILE_READ('classpath:h2-logo-2.png'), 'text', 5, 7, null)");
      changes.setEndPointNow();

      assertThat(changes).change().column("var10").valueAtEndPoint().isNull();

      fail("An exception must be raised");
    } catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Value at end point of Column at index 9 of Change at index 0 of Changes on tables of 'sa/jdbc:h2:mem:test' source] expected:<null> but was:<2014-05-24T09:46:30>");
    }
  }

  /**
   * This method tests the verification of the not null value is correct.
   */
  @Test
  @NeedReload
  public void test_if_a_value_is_not_null() {
    Changes changes = new Changes(source).setStartPointNow();
    update("insert into test2 values (1, true, 2, 3, 4, 5.6, 7.8, PARSEDATETIME('09:46:30', 'HH:mm:ss'),\n"
           + "    PARSEDATETIME('24/05/2014', 'dd/MM/yyyy'), PARSEDATETIME('24/05/2014 09:46:30', 'dd/MM/yyyy HH:mm:ss'),\n"
           + "    FILE_READ('classpath:h2-logo-2.png'), 'text', 5, 7, null)");
    changes.setEndPointNow();

    assertThat(changes).change().column("var10").valueAtEndPoint().isNotNull();
  }

  /**
   * This method should fail because the value is null.
   */
  @Test
  @NeedReload
  public void should_fail_because_value_is_null() {
    try {
      Changes changes = new Changes(source).setStartPointNow();
      update("insert into test2 values (null, null, null, null, null, null, null, null, null, null, null, null, null, null, null)");
      changes.setEndPointNow();

      assertThat(changes).change().column("var11").valueAtEndPoint().isNotNull();

      fail("An exception must be raised");
    } catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Value at end point of Column at index 10 of Change at index 0 of Changes on tables of 'sa/jdbc:h2:mem:test' source] \n" +
                                                                                    "Expecting actual not to be null");
    }
  }
}

