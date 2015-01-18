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

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on the methods which verifies if a value is equal to a number.
 *
 * @author Régis Pouiller
 *
 */
public class ChangeValueAssert_IsEqualTo_Number_Test extends AbstractTest {

  /**
   * This method tests that the value is equal to a byte number.
   */
  @Test
  @NeedReload
  public void test_if_value_is_equal_to_number_byte() {
    Changes changes = new Changes(source).setStartPointNow();
    update("insert into test values (1, true, 2, 3, 4, 5.6, 7.8, PARSEDATETIME('09:46:30', 'HH:mm:ss'), "
           + "PARSEDATETIME('24/05/2014', 'dd/MM/yyyy'), PARSEDATETIME('24/05/2014 09:46:30', 'dd/MM/yyyy HH:mm:ss'), "
           + "FILE_READ('classpath:h2-logo-2.png'), 'text', 5, 7)");
    update("insert into test values (10, false, 20, 30, 40, 50.6, 70.8, PARSEDATETIME('12:29:49', 'HH:mm:ss'), "
           + "PARSEDATETIME('30/05/2014', 'dd/MM/yyyy'), PARSEDATETIME('30/05/2014 12:29:49', 'dd/MM/yyyy HH:mm:ss'), "
           + "FILE_READ('classpath:logo-dev.jpg'), 'another text', 50, 70)");
    update("insert into test values (100, false, 25, 300, 400, 500.6, 700.8, PARSEDATETIME('12:29:49', 'HH:mm:ss'), "
           + "PARSEDATETIME('30/05/2014', 'dd/MM/yyyy'), PARSEDATETIME('30/05/2014', 'dd/MM/yyyy'), "
           + "FILE_READ('classpath:logo-dev.jpg'), 'another text again', 500, 700)");
    changes.setEndPointNow();

    assertThat(changes).change().rowAtEndPoint()
            .value("var1").isEqualTo((byte) 1)
            .value("var3").isEqualTo((byte) 2)
            .value("var4").isEqualTo((byte) 3)
            .value("var5").isEqualTo((byte) 4)
            .value("var13").isEqualTo((byte) 5)
            .value("var14").isEqualTo((byte) 7)
            .change().rowAtEndPoint()
            .value("var1").isEqualTo((byte) 10)
            .value("var3").isEqualTo((byte) 20)
            .value("var4").isEqualTo((byte) 30)
            .value("var5").isEqualTo((byte) 40)
            .value("var13").isEqualTo((byte) 50)
            .value("var14").isEqualTo((byte) 70);
  }

  /**
   * This method tests that the value is equal to a short number.
   */
  @Test
  @NeedReload
  public void test_if_value_is_equal_to_number_short() {
    Changes changes = new Changes(source).setStartPointNow();
    update("insert into test values (1, true, 2, 3, 4, 5.6, 7.8, PARSEDATETIME('09:46:30', 'HH:mm:ss'), "
           + "PARSEDATETIME('24/05/2014', 'dd/MM/yyyy'), PARSEDATETIME('24/05/2014 09:46:30', 'dd/MM/yyyy HH:mm:ss'), "
           + "FILE_READ('classpath:h2-logo-2.png'), 'text', 5, 7)");
    update("insert into test values (10, false, 20, 30, 40, 50.6, 70.8, PARSEDATETIME('12:29:49', 'HH:mm:ss'), "
           + "PARSEDATETIME('30/05/2014', 'dd/MM/yyyy'), PARSEDATETIME('30/05/2014 12:29:49', 'dd/MM/yyyy HH:mm:ss'), "
           + "FILE_READ('classpath:logo-dev.jpg'), 'another text', 50, 70)");
    update("insert into test values (100, false, 25, 300, 400, 500.6, 700.8, PARSEDATETIME('12:29:49', 'HH:mm:ss'), "
           + "PARSEDATETIME('30/05/2014', 'dd/MM/yyyy'), PARSEDATETIME('30/05/2014', 'dd/MM/yyyy'), "
           + "FILE_READ('classpath:logo-dev.jpg'), 'another text again', 500, 700)");
    changes.setEndPointNow();

    assertThat(changes).change().rowAtEndPoint()
            .value("var1").isEqualTo((short) 1)
            .value("var3").isEqualTo((short) 2)
            .value("var4").isEqualTo((short) 3)
            .value("var5").isEqualTo((short) 4)
            .value("var13").isEqualTo((short) 5)
            .value("var14").isEqualTo((short) 7)
            .change().rowAtEndPoint()
            .value("var1").isEqualTo((short) 10)
            .value("var3").isEqualTo((short) 20)
            .value("var4").isEqualTo((short) 30)
            .value("var5").isEqualTo((short) 40)
            .value("var13").isEqualTo((short) 50)
            .value("var14").isEqualTo((short) 70);
  }

  /**
   * This method tests that the value is equal to a integer number.
   */
  @Test
  @NeedReload
  public void test_if_value_is_equal_to_number_integer() {
    Changes changes = new Changes(source).setStartPointNow();
    update("insert into test values (1, true, 2, 3, 4, 5.6, 7.8, PARSEDATETIME('09:46:30', 'HH:mm:ss'), "
           + "PARSEDATETIME('24/05/2014', 'dd/MM/yyyy'), PARSEDATETIME('24/05/2014 09:46:30', 'dd/MM/yyyy HH:mm:ss'), "
           + "FILE_READ('classpath:h2-logo-2.png'), 'text', 5, 7)");
    update("insert into test values (10, false, 20, 30, 40, 50.6, 70.8, PARSEDATETIME('12:29:49', 'HH:mm:ss'), "
           + "PARSEDATETIME('30/05/2014', 'dd/MM/yyyy'), PARSEDATETIME('30/05/2014 12:29:49', 'dd/MM/yyyy HH:mm:ss'), "
           + "FILE_READ('classpath:logo-dev.jpg'), 'another text', 50, 70)");
    update("insert into test values (100, false, 25, 300, 400, 500.6, 700.8, PARSEDATETIME('12:29:49', 'HH:mm:ss'), "
           + "PARSEDATETIME('30/05/2014', 'dd/MM/yyyy'), PARSEDATETIME('30/05/2014', 'dd/MM/yyyy'), "
           + "FILE_READ('classpath:logo-dev.jpg'), 'another text again', 500, 700)");
    changes.setEndPointNow();

    assertThat(changes).change().rowAtEndPoint()
            .value("var1").isEqualTo((int) 1)
            .value("var3").isEqualTo((int) 2)
            .value("var4").isEqualTo((int) 3)
            .value("var5").isEqualTo((int) 4)
            .value("var13").isEqualTo((int) 5)
            .value("var14").isEqualTo((int) 7)
            .change().rowAtEndPoint()
            .value("var1").isEqualTo((int) 10)
            .value("var3").isEqualTo((int) 20)
            .value("var4").isEqualTo((int) 30)
            .value("var5").isEqualTo((int) 40)
            .value("var13").isEqualTo((int) 50)
            .value("var14").isEqualTo((int) 70);
  }

  /**
   * This method tests that the value is equal to a long number.
   */
  @Test
  @NeedReload
  public void test_if_value_is_equal_to_number_long() {
    Changes changes = new Changes(source).setStartPointNow();
    update("insert into test values (1, true, 2, 3, 4, 5.6, 7.8, PARSEDATETIME('09:46:30', 'HH:mm:ss'), "
           + "PARSEDATETIME('24/05/2014', 'dd/MM/yyyy'), PARSEDATETIME('24/05/2014 09:46:30', 'dd/MM/yyyy HH:mm:ss'), "
           + "FILE_READ('classpath:h2-logo-2.png'), 'text', 5, 7)");
    update("insert into test values (10, false, 20, 30, 40, 50.6, 70.8, PARSEDATETIME('12:29:49', 'HH:mm:ss'), "
           + "PARSEDATETIME('30/05/2014', 'dd/MM/yyyy'), PARSEDATETIME('30/05/2014 12:29:49', 'dd/MM/yyyy HH:mm:ss'), "
           + "FILE_READ('classpath:logo-dev.jpg'), 'another text', 50, 70)");
    update("insert into test values (100, false, 25, 300, 400, 500.6, 700.8, PARSEDATETIME('12:29:49', 'HH:mm:ss'), "
           + "PARSEDATETIME('30/05/2014', 'dd/MM/yyyy'), PARSEDATETIME('30/05/2014', 'dd/MM/yyyy'), "
           + "FILE_READ('classpath:logo-dev.jpg'), 'another text again', 500, 700)");
    changes.setEndPointNow();

    assertThat(changes).change().rowAtEndPoint()
            .value("var1").isEqualTo((long) 1)
            .value("var3").isEqualTo((long) 2)
            .value("var4").isEqualTo((long) 3)
            .value("var5").isEqualTo((long) 4)
            .value("var13").isEqualTo((long) 5)
            .value("var14").isEqualTo((long) 7)
            .change().rowAtEndPoint()
            .value("var1").isEqualTo((long) 10)
            .value("var3").isEqualTo((long) 20)
            .value("var4").isEqualTo((long) 30)
            .value("var5").isEqualTo((long) 40)
            .value("var13").isEqualTo((long) 50)
            .value("var14").isEqualTo((long) 70);
  }

  /**
   * This method tests that the value is equal to a nig integer number.
   */
  @Test
  @NeedReload
  public void test_if_value_is_equal_to_number_biginteger() {
    Changes changes = new Changes(source).setStartPointNow();
    update("insert into test values (1, true, 2, 3, 4, 5.6, 7.8, PARSEDATETIME('09:46:30', 'HH:mm:ss'), "
           + "PARSEDATETIME('24/05/2014', 'dd/MM/yyyy'), PARSEDATETIME('24/05/2014 09:46:30', 'dd/MM/yyyy HH:mm:ss'), "
           + "FILE_READ('classpath:h2-logo-2.png'), 'text', 5, 7)");
    update("insert into test values (10, false, 20, 30, 40, 50.6, 70.8, PARSEDATETIME('12:29:49', 'HH:mm:ss'), "
           + "PARSEDATETIME('30/05/2014', 'dd/MM/yyyy'), PARSEDATETIME('30/05/2014 12:29:49', 'dd/MM/yyyy HH:mm:ss'), "
           + "FILE_READ('classpath:logo-dev.jpg'), 'another text', 50, 70)");
    update("insert into test values (100, false, 25, 300, 400, 500.6, 700.8, PARSEDATETIME('12:29:49', 'HH:mm:ss'), "
           + "PARSEDATETIME('30/05/2014', 'dd/MM/yyyy'), PARSEDATETIME('30/05/2014', 'dd/MM/yyyy'), "
           + "FILE_READ('classpath:logo-dev.jpg'), 'another text again', 500, 700)");
    changes.setEndPointNow();

    assertThat(changes).change().rowAtEndPoint()
            .value("var1").isEqualTo(new BigInteger("1"))
            .value("var3").isEqualTo(new BigInteger("2"))
            .value("var4").isEqualTo(new BigInteger("3"))
            .value("var5").isEqualTo(new BigInteger("4"))
            .change().rowAtEndPoint()
            .value("var1").isEqualTo(new BigInteger("10"))
            .value("var3").isEqualTo(new BigInteger("20"))
            .value("var4").isEqualTo(new BigInteger("30"))
            .value("var5").isEqualTo(new BigInteger("40"));
  }

  /**
   * This method tests that the value is equal to a float number.
   */
  @Test
  @NeedReload
  public void test_if_value_is_equal_to_number_float() {
    Changes changes = new Changes(source).setStartPointNow();
    update("insert into test values (1, true, 2, 3, 4, 5.6, 7.8, PARSEDATETIME('09:46:30', 'HH:mm:ss'), "
           + "PARSEDATETIME('24/05/2014', 'dd/MM/yyyy'), PARSEDATETIME('24/05/2014 09:46:30', 'dd/MM/yyyy HH:mm:ss'), "
           + "FILE_READ('classpath:h2-logo-2.png'), 'text', 5, 7)");
    update("insert into test values (10, false, 20, 30, 40, 50.6, 70.8, PARSEDATETIME('12:29:49', 'HH:mm:ss'), "
           + "PARSEDATETIME('30/05/2014', 'dd/MM/yyyy'), PARSEDATETIME('30/05/2014 12:29:49', 'dd/MM/yyyy HH:mm:ss'), "
           + "FILE_READ('classpath:logo-dev.jpg'), 'another text', 50, 70)");
    update("insert into test values (100, false, 25, 300, 400, 500.6, 700.8, PARSEDATETIME('12:29:49', 'HH:mm:ss'), "
           + "PARSEDATETIME('30/05/2014', 'dd/MM/yyyy'), PARSEDATETIME('30/05/2014', 'dd/MM/yyyy'), "
           + "FILE_READ('classpath:logo-dev.jpg'), 'another text again', 500, 700)");
    changes.setEndPointNow();

    assertThat(changes).change().rowAtEndPoint()
            .value("var1").isEqualTo((float) 1)
            .value("var3").isEqualTo((float) 2)
            .value("var4").isEqualTo((float) 3)
            .value("var5").isEqualTo((float) 4)
            .value("var6").isEqualTo((float) 5.6)
            .value("var7").isEqualTo((float) 7.8)
            .value("var13").isEqualTo((float) 5)
            .value("var14").isEqualTo((float) 7)
            .change().rowAtEndPoint()
            .value("var1").isEqualTo((float) 10)
            .value("var3").isEqualTo((float) 20)
            .value("var4").isEqualTo((float) 30)
            .value("var5").isEqualTo((float) 40)
            .value("var6").isEqualTo((float) 50.6)
            .value("var7").isEqualTo((float) 70.8)
            .value("var13").isEqualTo((float) 50)
            .value("var14").isEqualTo((float) 70);
  }

  /**
   * This method tests that the value is equal to a double number.
   */
  @Test
  @NeedReload
  public void test_if_value_is_equal_to_number_double() {
    Changes changes = new Changes(source).setStartPointNow();
    update("insert into test values (1, true, 2, 3, 4, 5.6, 7.8, PARSEDATETIME('09:46:30', 'HH:mm:ss'), "
           + "PARSEDATETIME('24/05/2014', 'dd/MM/yyyy'), PARSEDATETIME('24/05/2014 09:46:30', 'dd/MM/yyyy HH:mm:ss'), "
           + "FILE_READ('classpath:h2-logo-2.png'), 'text', 5, 7)");
    update("insert into test values (10, false, 20, 30, 40, 50.6, 70.8, PARSEDATETIME('12:29:49', 'HH:mm:ss'), "
           + "PARSEDATETIME('30/05/2014', 'dd/MM/yyyy'), PARSEDATETIME('30/05/2014 12:29:49', 'dd/MM/yyyy HH:mm:ss'), "
           + "FILE_READ('classpath:logo-dev.jpg'), 'another text', 50, 70)");
    update("insert into test values (100, false, 25, 300, 400, 500.6, 700.8, PARSEDATETIME('12:29:49', 'HH:mm:ss'), "
           + "PARSEDATETIME('30/05/2014', 'dd/MM/yyyy'), PARSEDATETIME('30/05/2014', 'dd/MM/yyyy'), "
           + "FILE_READ('classpath:logo-dev.jpg'), 'another text again', 500, 700)");
    changes.setEndPointNow();

    assertThat(changes).change().rowAtEndPoint()
            .value("var1").isEqualTo((double) 1)
            .value("var3").isEqualTo((double) 2)
            .value("var4").isEqualTo((double) 3)
            .value("var5").isEqualTo((double) 4)
            .value("var6").isEqualTo((double) 5.6)
            .value("var7").isEqualTo((double) 7.8)
            .value("var13").isEqualTo((double) 5)
            .value("var14").isEqualTo((double) 7)
            .change().rowAtEndPoint()
            .value("var1").isEqualTo((double) 10)
            .value("var3").isEqualTo((double) 20)
            .value("var4").isEqualTo((double) 30)
            .value("var5").isEqualTo((double) 40)
            .value("var6").isEqualTo((double) 50.6)
            .value("var7").isEqualTo((double) 70.8)
            .value("var13").isEqualTo((double) 50)
            .value("var14").isEqualTo((double) 70);
  }

  /**
   * This method tests that the value is equal to a big decimal number.
   */
  @Test
  @NeedReload
  public void test_if_value_is_equal_to_number_bigdecimal() {
    Changes changes = new Changes(source).setStartPointNow();
    update("insert into test values (1, true, 2, 3, 4, 5.6, 7.8, PARSEDATETIME('09:46:30', 'HH:mm:ss'), "
           + "PARSEDATETIME('24/05/2014', 'dd/MM/yyyy'), PARSEDATETIME('24/05/2014 09:46:30', 'dd/MM/yyyy HH:mm:ss'), "
           + "FILE_READ('classpath:h2-logo-2.png'), 'text', 5, 7)");
    update("insert into test values (10, false, 20, 30, 40, 50.6, 70.8, PARSEDATETIME('12:29:49', 'HH:mm:ss'), "
           + "PARSEDATETIME('30/05/2014', 'dd/MM/yyyy'), PARSEDATETIME('30/05/2014 12:29:49', 'dd/MM/yyyy HH:mm:ss'), "
           + "FILE_READ('classpath:logo-dev.jpg'), 'another text', 50, 70)");
    update("insert into test values (100, false, 25, 300, 400, 500.6, 700.8, PARSEDATETIME('12:29:49', 'HH:mm:ss'), "
           + "PARSEDATETIME('30/05/2014', 'dd/MM/yyyy'), PARSEDATETIME('30/05/2014', 'dd/MM/yyyy'), "
           + "FILE_READ('classpath:logo-dev.jpg'), 'another text again', 500, 700)");
    changes.setEndPointNow();

    assertThat(changes).change().rowAtEndPoint()
            .value("var1").isEqualTo(new BigDecimal("1"))
            .value("var3").isEqualTo(new BigDecimal("2"))
            .value("var4").isEqualTo(new BigDecimal("3"))
            .value("var5").isEqualTo(new BigDecimal("4"))
            .value("var6").isEqualTo(new BigDecimal("5.6"))
            .value("var7").isEqualTo(new BigDecimal("7.8"))
            .value("var13").isEqualTo(new BigDecimal("5"))
            .value("var14").isEqualTo(new BigDecimal("7"))
            .change().rowAtEndPoint()
            .value("var1").isEqualTo(new BigDecimal("10"))
            .value("var3").isEqualTo(new BigDecimal("20"))
            .value("var4").isEqualTo(new BigDecimal("30"))
            .value("var5").isEqualTo(new BigDecimal("40"))
            .value("var6").isEqualTo(new BigDecimal("50.6"))
            .value("var7").isEqualTo(new BigDecimal("70.8"))
            .value("var13").isEqualTo(new BigDecimal("50"))
            .value("var14").isEqualTo(new BigDecimal("70"));
  }

  /**
   * This method should fail because the value is not equal to the number in parameter.
   */
  @Test
  @NeedReload
  public void should_fail_because_value_is_not_equal() {
    try {
      Changes changes = new Changes(source).setStartPointNow();
      update("insert into test values (1, true, 2, 3, 4, 5.6, 7.8, PARSEDATETIME('09:46:30', 'HH:mm:ss'), "
             + "PARSEDATETIME('24/05/2014', 'dd/MM/yyyy'), PARSEDATETIME('24/05/2014 09:46:30', 'dd/MM/yyyy HH:mm:ss'), "
             + "FILE_READ('classpath:h2-logo-2.png'), 'text', 5, 7)");
      update("insert into test values (10, false, 20, 30, 40, 50.6, 70.8, PARSEDATETIME('12:29:49', 'HH:mm:ss'), "
             + "PARSEDATETIME('30/05/2014', 'dd/MM/yyyy'), PARSEDATETIME('30/05/2014 12:29:49', 'dd/MM/yyyy HH:mm:ss'), "
             + "FILE_READ('classpath:logo-dev.jpg'), 'another text', 50, 70)");
      update("insert into test values (100, false, 25, 300, 400, 500.6, 700.8, PARSEDATETIME('12:29:49', 'HH:mm:ss'), "
             + "PARSEDATETIME('30/05/2014', 'dd/MM/yyyy'), PARSEDATETIME('30/05/2014', 'dd/MM/yyyy'), "
             + "FILE_READ('classpath:logo-dev.jpg'), 'another text again', 500, 700)");
      changes.setEndPointNow();

      assertThat(changes).change().rowAtEndPoint()
              .value("var1").isEqualTo(2);

      fail("An exception must be raised");
    } catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Value at index 0 of Row at end point of Change at index 0 of Changes on tables of 'sa/jdbc:h2:mem:test' source] \n" +
                                                                                    "Expecting:\n" +
                                                                                    "  <1>\n" +
                                                                                    "to be equal to: \n" +
                                                                                    "  <2>");
    }
  }

  /**
   * This method should fail because the value is not a number.
   */
  @Test
  @NeedReload
  public void should_fail_because_value_is_not_a_number() {
    try {
      Changes changes = new Changes(source).setStartPointNow();
      update("insert into test values (1, true, 2, 3, 4, 5.6, 7.8, PARSEDATETIME('09:46:30', 'HH:mm:ss'), "
             + "PARSEDATETIME('24/05/2014', 'dd/MM/yyyy'), PARSEDATETIME('24/05/2014 09:46:30', 'dd/MM/yyyy HH:mm:ss'), "
             + "FILE_READ('classpath:h2-logo-2.png'), 'text', 5, 7)");
      update("insert into test values (10, false, 20, 30, 40, 50.6, 70.8, PARSEDATETIME('12:29:49', 'HH:mm:ss'), "
             + "PARSEDATETIME('30/05/2014', 'dd/MM/yyyy'), PARSEDATETIME('30/05/2014 12:29:49', 'dd/MM/yyyy HH:mm:ss'), "
             + "FILE_READ('classpath:logo-dev.jpg'), 'another text', 50, 70)");
      update("insert into test values (100, false, 25, 300, 400, 500.6, 700.8, PARSEDATETIME('12:29:49', 'HH:mm:ss'), "
             + "PARSEDATETIME('30/05/2014', 'dd/MM/yyyy'), PARSEDATETIME('30/05/2014', 'dd/MM/yyyy'), "
             + "FILE_READ('classpath:logo-dev.jpg'), 'another text again', 500, 700)");
      changes.setEndPointNow();

      assertThat(changes).change().rowAtEndPoint()
                       .value("var2").as("var2").isEqualTo(1);

      fail("An exception must be raised");
    } catch (AssertionError e) {
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
