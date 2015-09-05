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
 * Copyright 2012-2015 the original author or authors.
 */
package org.assertj.db.type;

import org.assertj.db.common.AbstractTest;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test on the type got from {@code getType} method from {@code ValueType} enum.
 * 
 * @author Régis Pouiller
 * 
 */
public class ValueType_GetType_Test extends AbstractTest {

  /**
   * This method tests the result of {@code getType} method from {@code ValueType} enum.
   */
  @Test
  public void test_result_when_getting_type() {
    assertThat(ValueType.getType(new byte[]{1})).isEqualTo(ValueType.BYTES);
    assertThat(ValueType.getType(true)).isEqualTo(ValueType.BOOLEAN);
    assertThat(ValueType.getType("")).isEqualTo(ValueType.TEXT);
    assertThat(ValueType.getType(new Date(10))).isEqualTo(ValueType.DATE);
    assertThat(ValueType.getType(new Time(10))).isEqualTo(ValueType.TIME);
    assertThat(ValueType.getType(new Timestamp(10))).isEqualTo(ValueType.DATE_TIME);
    assertThat(ValueType.getType((short) 10)).isEqualTo(ValueType.NUMBER);
    assertThat(ValueType.getType(10)).isEqualTo(ValueType.NUMBER);
    assertThat(ValueType.getType((long) 10)).isEqualTo(ValueType.NUMBER);
    assertThat(ValueType.getType(10.5d)).isEqualTo(ValueType.NUMBER);
    assertThat(ValueType.getType(10.5f)).isEqualTo(ValueType.NUMBER);
    assertThat(ValueType.getType(new BigDecimal(10.5f))).isEqualTo(ValueType.NUMBER);
    assertThat(ValueType.getType(UUID.randomUUID())).isEqualTo(ValueType.UUID);
    assertThat(ValueType.getType(null)).isEqualTo(ValueType.NOT_IDENTIFIED);
  }
}
