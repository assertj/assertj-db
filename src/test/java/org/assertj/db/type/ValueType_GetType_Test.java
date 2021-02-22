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
 * Copyright 2015-2021 the original author or authors.
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
  public void test_result_when_getting_type() throws Exception {
    assertThat(getValue(null, new byte[] { 1 }).getValueType()).isEqualTo(ValueType.BYTES);
    assertThat(getValue(null, true).getValueType()).isEqualTo(ValueType.BOOLEAN);
    assertThat(getValue(null, "").getValueType()).isEqualTo(ValueType.TEXT);
    assertThat(getValue(null, new Date(10)).getValueType()).isEqualTo(ValueType.DATE);
    assertThat(getValue(null, new Time(10)).getValueType()).isEqualTo(ValueType.TIME);
    assertThat(getValue(null, new Timestamp(10)).getValueType()).isEqualTo(ValueType.DATE_TIME);
    assertThat(getValue(null, (short) 10).getValueType()).isEqualTo(ValueType.NUMBER);
    assertThat(getValue(null, 10).getValueType()).isEqualTo(ValueType.NUMBER);
    assertThat(getValue(null, (long) 10).getValueType()).isEqualTo(ValueType.NUMBER);
    assertThat(getValue(null, 10.5d).getValueType()).isEqualTo(ValueType.NUMBER);
    assertThat(getValue(null, 10.5f).getValueType()).isEqualTo(ValueType.NUMBER);
    assertThat(getValue(null, new BigDecimal(10.5f)).getValueType()).isEqualTo(ValueType.NUMBER);
    assertThat(getValue(null, UUID.randomUUID()).getValueType()).isEqualTo(ValueType.UUID);
    assertThat(getValue(null, null).getValueType()).isEqualTo(ValueType.NOT_IDENTIFIED);
  }
}
