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

import org.junit.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test on the type got from {@code getPossibleTypesForComparison} method from {@code ValueType} enum.
 *
 * @author Régis Pouiller
 *
 */
public class ValueType_GetPossibleTypesForComparison_Test {

  /**
   * This method tests the result of {@code getPossibleTypesForComparison} method from {@code ValueType} enum.
   */
  @Test
  public void test_result_when_getting_type() throws Exception {
    assertThat(ValueType.getPossibleTypesForComparison(new byte[] { 1 })).isEqualTo(new ValueType[] { ValueType.BYTES });
    assertThat(ValueType.getPossibleTypesForComparison(true)).isEqualTo(new ValueType[] { ValueType.BOOLEAN });
    assertThat(ValueType.getPossibleTypesForComparison("")).isEqualTo(new ValueType[] { ValueType.TEXT, ValueType.NUMBER,
            ValueType.DATE, ValueType.TIME, ValueType.DATE_TIME, ValueType.UUID });
    assertThat(ValueType.getPossibleTypesForComparison(DateValue.of(2007, 12, 23))).isEqualTo(new ValueType[] { ValueType.DATE, ValueType.DATE_TIME });
    assertThat(ValueType.getPossibleTypesForComparison(TimeValue.of(9, 1))).isEqualTo(new ValueType[] { ValueType.TIME });
    assertThat(ValueType.getPossibleTypesForComparison(DateTimeValue.of(DateValue.of(2007, 12, 23)))).isEqualTo(
            new ValueType[] { ValueType.DATE_TIME });
    assertThat(ValueType.getPossibleTypesForComparison((short) 10)).isEqualTo(new ValueType[] { ValueType.NUMBER });
    assertThat(ValueType.getPossibleTypesForComparison(10)).isEqualTo(new ValueType[] { ValueType.NUMBER });
    assertThat(ValueType.getPossibleTypesForComparison((long) 10)).isEqualTo(new ValueType[] { ValueType.NUMBER });
    assertThat(ValueType.getPossibleTypesForComparison(10.5d)).isEqualTo(new ValueType[] { ValueType.NUMBER });
    assertThat(ValueType.getPossibleTypesForComparison(10.5f)).isEqualTo(new ValueType[] { ValueType.NUMBER });
    assertThat(ValueType.getPossibleTypesForComparison(new BigDecimal(10.5f))).isEqualTo(new ValueType[] { ValueType.NUMBER });
    assertThat(ValueType.getPossibleTypesForComparison(UUID.randomUUID())).isEqualTo(new ValueType[] { ValueType.UUID });
    assertThat(ValueType.getPossibleTypesForComparison(null)).isEqualTo(new ValueType[] { ValueType.NOT_IDENTIFIED });
  }
}
