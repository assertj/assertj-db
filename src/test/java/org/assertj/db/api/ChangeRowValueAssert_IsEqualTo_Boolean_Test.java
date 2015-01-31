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

import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.assertj.db.type.Changes;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on the methods which verifies if a value is equal to a boolean.
 *
 * @author Régis Pouiller
 *
 */
public class ChangeRowValueAssert_IsEqualTo_Boolean_Test extends AbstractTest {

  /**
   * This method tests that the value is equal to a boolean.
   */
  @Test
  @NeedReload
  public void test_if_value_is_equal_to_boolean() {
    Changes changes = new Changes(source).setStartPointNow();
    update("insert into test2 values (1, true, 2, 3, 4, 5.6, 7.8, PARSEDATETIME('09:46:30', 'HH:mm:ss'),\n"
           + "    PARSEDATETIME('24/05/2014', 'dd/MM/yyyy'), PARSEDATETIME('24/05/2014 09:46:30', 'dd/MM/yyyy HH:mm:ss'),\n"
           + "    FILE_READ('classpath:h2-logo-2.png'), 'text', 5, 7, null)");
    update("insert into test2 values (2, false, 2, 3, 4, 5.6, 7.8, PARSEDATETIME('09:46:30', 'HH:mm:ss'),\n"
           + "    PARSEDATETIME('24/05/2014', 'dd/MM/yyyy'), PARSEDATETIME('24/05/2014 09:46:30', 'dd/MM/yyyy HH:mm:ss'),\n"
           + "    FILE_READ('classpath:h2-logo-2.png'), 'text', 5, 7, null)");
    changes.setEndPointNow();

    assertThat(changes)
            .change().rowAtEndPoint()
                     .value(1).isEqualTo(true)
            .change().rowAtEndPoint()
                     .value(1).isEqualTo(false);
  }

  /**
   * This method tests that the value is equal to true boolean.
   */
  @Test
  @NeedReload
  public void test_if_value_is_true() {
    Changes changes = new Changes(source).setStartPointNow();
    update("insert into test2 values (1, true, 2, 3, 4, 5.6, 7.8, PARSEDATETIME('09:46:30', 'HH:mm:ss'),\n"
           + "    PARSEDATETIME('24/05/2014', 'dd/MM/yyyy'), PARSEDATETIME('24/05/2014 09:46:30', 'dd/MM/yyyy HH:mm:ss'),\n"
           + "    FILE_READ('classpath:h2-logo-2.png'), 'text', 5, 7, null)");
    changes.setEndPointNow();

    assertThat(changes)
            .change().rowAtEndPoint()
                     .value("var2").isTrue();
  }

  /**
   * This method tests that the value is equal to false boolean.
   */
  @Test
  @NeedReload
  public void test_if_value_is_false() {
    Changes changes = new Changes(source).setStartPointNow();
    update("insert into test2 values (2, false, 2, 3, 4, 5.6, 7.8, PARSEDATETIME('09:46:30', 'HH:mm:ss'),\n"
           + "    PARSEDATETIME('24/05/2014', 'dd/MM/yyyy'), PARSEDATETIME('24/05/2014 09:46:30', 'dd/MM/yyyy HH:mm:ss'),\n"
           + "    FILE_READ('classpath:h2-logo-2.png'), 'text', 5, 7, null)");
    changes.setEndPointNow();

    assertThat(changes)
            .change().rowAtEndPoint()
            .value(1).isFalse();
  }

  /**
   * This method should fail because the value is not equal to the boolean.
   */
  @Test
  @NeedReload
  public void should_fail_because_value_is_not_equal() {
    try {
      Changes changes = new Changes(source).setStartPointNow();
      update("insert into test2 values (1, true, 2, 3, 4, 5.6, 7.8, PARSEDATETIME('09:46:30', 'HH:mm:ss'),\n"
             + "    PARSEDATETIME('24/05/2014', 'dd/MM/yyyy'), PARSEDATETIME('24/05/2014 09:46:30', 'dd/MM/yyyy HH:mm:ss'),\n"
             + "    FILE_READ('classpath:h2-logo-2.png'), 'text', 5, 7, null)");
      changes.setEndPointNow();

      assertThat(changes)
              .change().rowAtEndPoint()
                       .value(1).isEqualTo(false);

      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Value at index 1 of Row at end point of Change at index 0 of Changes on tables of 'sa/jdbc:h2:mem:test' source] \n" +
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
  @NeedReload
  public void should_fail_because_value_is_true() {
    try {
      Changes changes = new Changes(source).setStartPointNow();
      update("insert into test2 values (1, true, 2, 3, 4, 5.6, 7.8, PARSEDATETIME('09:46:30', 'HH:mm:ss'),\n"
             + "    PARSEDATETIME('24/05/2014', 'dd/MM/yyyy'), PARSEDATETIME('24/05/2014 09:46:30', 'dd/MM/yyyy HH:mm:ss'),\n"
             + "    FILE_READ('classpath:h2-logo-2.png'), 'text', 5, 7, null)");
      changes.setEndPointNow();

      assertThat(changes)
              .change().rowAtEndPoint()
                       .value(1).isFalse();

      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Value at index 1 of Row at end point of Change at index 0 of Changes on tables of 'sa/jdbc:h2:mem:test' source] \n" +
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
  @NeedReload
  public void should_fail_because_value_is_false() {
    try {
      Changes changes = new Changes(source).setStartPointNow();
      update("insert into test2 values (2, false, 2, 3, 4, 5.6, 7.8, PARSEDATETIME('09:46:30', 'HH:mm:ss'),\n"
             + "    PARSEDATETIME('24/05/2014', 'dd/MM/yyyy'), PARSEDATETIME('24/05/2014 09:46:30', 'dd/MM/yyyy HH:mm:ss'),\n"
             + "    FILE_READ('classpath:h2-logo-2.png'), 'text', 5, 7, null)");
      changes.setEndPointNow();

      assertThat(changes)
              .change().rowAtEndPoint()
                       .value(1).isTrue();

      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Value at index 1 of Row at end point of Change at index 0 of Changes on tables of 'sa/jdbc:h2:mem:test' source] \n" +
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
  @NeedReload
  public void should_fail_because_value_is_not_a_boolean() {
    try {
      Changes changes = new Changes(source).setStartPointNow();
      update("insert into test2 values (2, false, 2, 3, 4, 5.6, 7.8, PARSEDATETIME('09:46:30', 'HH:mm:ss'),\n"
             + "    PARSEDATETIME('24/05/2014', 'dd/MM/yyyy'), PARSEDATETIME('24/05/2014 09:46:30', 'dd/MM/yyyy HH:mm:ss'),\n"
             + "    FILE_READ('classpath:h2-logo-2.png'), 'text', 5, 7, null)");
      changes.setEndPointNow();

      assertThat(changes)
              .change().rowAtEndPoint()
                       .value().as("var1").isTrue();

      fail("An exception must be raised");
    } catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[var1] \n" +
                                                                                    "Expecting:\n" +
                                                                                    "  <2>\n" +
                                                                                    "to be of type\n" +
                                                                                    "  <BOOLEAN>\n" +
                                                                                    "but was of type\n" +
                                                                                    "  <NUMBER>");
    }
  }
}
