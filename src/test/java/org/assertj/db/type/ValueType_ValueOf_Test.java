/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2015-2024 the original author or authors.
 */
package org.assertj.db.type;

import org.assertj.db.common.AbstractTest;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test on the type got from {@code valueOf} method from {@code ValueType} enum.
 * 
 * @author Régis Pouiller
 * 
 */
public class ValueType_ValueOf_Test extends AbstractTest {

  /**
   * This method tests the result of {@code valueOf} method from {@code ValueType} enum.
   */
  @Test
  public void test_result_of_valueOf() {
    assertThat(ValueType.valueOf("BYTES")).isEqualTo(ValueType.BYTES);
    assertThat(ValueType.valueOf("BOOLEAN")).isEqualTo(ValueType.BOOLEAN);
    assertThat(ValueType.valueOf("TEXT")).isEqualTo(ValueType.TEXT);
    assertThat(ValueType.valueOf("DATE")).isEqualTo(ValueType.DATE);
    assertThat(ValueType.valueOf("TIME")).isEqualTo(ValueType.TIME);
    assertThat(ValueType.valueOf("DATE_TIME")).isEqualTo(ValueType.DATE_TIME);
    assertThat(ValueType.valueOf("NUMBER")).isEqualTo(ValueType.NUMBER);
    assertThat(ValueType.valueOf("UUID")).isEqualTo(ValueType.UUID);
    assertThat(ValueType.valueOf("NOT_IDENTIFIED")).isEqualTo(ValueType.NOT_IDENTIFIED);
  }
}
