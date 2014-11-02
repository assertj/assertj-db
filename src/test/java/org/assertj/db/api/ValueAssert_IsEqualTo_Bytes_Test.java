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
import static org.assertj.db.api.Assertions.bytesContentFromClassPathOf;
import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Tests on the methods which verifies if a value is equal to a array of bytes.
 * 
 * @author Régis Pouiller
 * 
 */
public class ValueAssert_IsEqualTo_Bytes_Test extends AbstractTest {

  private byte[] bytesTest = bytesContentFromClassPathOf("test.txt");
  private byte[] bytesDev = bytesContentFromClassPathOf("logo-dev.jpg");
  private byte[] bytesH2 = bytesContentFromClassPathOf("h2-logo-2.png");

  /**
   * This method tests that the value is equal to a array of bytes.
   */
  @Test
  public void test_if_value_is_equal_to_bytes() {
    Table table = new Table(source, "test");
    assertThat(table).column("var11")
        .value().isEqualTo(bytesH2).returnToColumn()
        .value().isEqualTo(bytesDev);
  }

  /**
   * This method should fail because the value is not equal to the array of bytes.
   */
  @Test
  public void should_fail_because_value_is_not_equal() {
    try {
      Table table = new Table(source, "test");
      assertThat(table).column("var11")
          .value().isEqualTo(bytesTest);
      
      fail("Une Erreur doit être levée");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Value at index 0 of Column at index 10 of test table] \n" +
          "Expecting to be equal to the expected value but was not equal");
    }
  }

  /**
   * This method should fail because the value is not a array of bytes.
   */
  @Test
  public void should_fail_because_value_is_not_a_bytes() {
    try {
      Table table = new Table(source, "test");
      assertThat(table).column("var1")
          .value().as("var1").isEqualTo(bytesTest);
      
      fail("Une Erreur doit être levée");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[var1] \n" +
          "Expecting:\n" +
          "  <1>\n" +
          "to be of type\n" +
          "  <BYTES>\n" +
          "but was of type\n" +
          "  <NUMBER>");
    }
  }
}
