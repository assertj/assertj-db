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
 * Copyright 2015-2025 the original author or authors.
 */
package org.assertj.db.type;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.UUID;

import org.assertj.db.common.AbstractTest;
import org.junit.Test;

/**
 * Test on {@code isComparisonPossible} method from {@code Value}.
 *
 * @author Régis Pouiller
 */
public class Value_IsComparisonPossible_Test extends AbstractTest {

  /**
   * This method tests {@code isComparisonPossible} method from {@code Value}.
   */
  @Test
  public void test_is_comparison_possible() throws Exception {
    assertThat(getValue("", new byte[]{1}).isComparisonPossible(new byte[]{1})).isTrue();
    assertThat(getValue("", true).isComparisonPossible(true)).isTrue();
    assertThat(getValue("", "").isComparisonPossible("")).isTrue();
    assertThat(getValue("", 9).isComparisonPossible("")).isTrue();
    assertThat(getValue("", 9).isComparisonPossible((byte) 10)).isTrue();
    assertThat(getValue("", 9).isComparisonPossible((short) 10)).isTrue();
    assertThat(getValue("", 9).isComparisonPossible(10)).isTrue();
    assertThat(getValue("", 9).isComparisonPossible((long) 10)).isTrue();
    assertThat(getValue("", 9).isComparisonPossible(10.5f)).isTrue();
    assertThat(getValue("", 9).isComparisonPossible(10.5d)).isTrue();
    assertThat(getValue("", 9).isComparisonPossible(new BigInteger("10"))).isTrue();
    assertThat(getValue("", 9).isComparisonPossible(new BigDecimal(10.5f))).isTrue();
    assertThat(getValue("", Date.valueOf("2007-12-23")).isComparisonPossible("")).isTrue();
    assertThat(getValue("", Date.valueOf("2007-12-23")).isComparisonPossible(DateValue.of(2014, 10, 10))).isTrue();
    assertThat(getValue("", Date.valueOf("2007-12-23")).isComparisonPossible(DateTimeValue.of(DateValue.of(2014, 10, 10), TimeValue.of(10, 10)))).isTrue();
    assertThat(getValue("", Time.valueOf("09:01:00")).isComparisonPossible("")).isTrue();
    assertThat(getValue("", Time.valueOf("09:01:00")).isComparisonPossible(TimeValue.of(10, 10))).isTrue();
    assertThat(getValue("", Timestamp.valueOf("2007-12-23 09:01:00")).isComparisonPossible("")).isTrue();
    assertThat(getValue("", Timestamp.valueOf("2007-12-23 09:01:00")).isComparisonPossible(DateValue.of(2014, 10, 10))).isTrue();
    assertThat(getValue("", Timestamp.valueOf("2007-12-23 09:01:00")).isComparisonPossible(DateTimeValue.of(DateValue.of(2014, 10, 10), TimeValue.of(10, 10)))).isTrue();
    assertThat(getValue("", UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435")).isComparisonPossible("")).isTrue();
    assertThat(getValue("", UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435")).isComparisonPossible(UUID.randomUUID())).isTrue();
    assertThat(getValue("", null).isComparisonPossible(null)).isTrue();
    assertThat(getValue("", new URL("http://github.com")).isComparisonPossible(null)).isFalse();
    assertThat(getValue("", new byte[]{1}).isComparisonPossible("")).isFalse();
    assertThat(getValue("", true).isComparisonPossible("")).isFalse();
    assertThat(getValue("", "").isComparisonPossible(new byte[]{1})).isFalse();
    assertThat(getValue("", 9).isComparisonPossible(new byte[]{1})).isFalse();
    assertThat(getValue("", Date.valueOf("2007-12-23")).isComparisonPossible(new byte[]{1})).isFalse();
    assertThat(getValue("", Time.valueOf("09:01:00")).isComparisonPossible(new byte[]{1})).isFalse();
    assertThat(getValue("", Timestamp.valueOf("2007-12-23 09:01:00")).isComparisonPossible(new byte[]{1})).isFalse();
    assertThat(getValue("", UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435")).isComparisonPossible(new byte[]{1})).isFalse();
    assertThat(getValue("", null).isComparisonPossible(new byte[]{1})).isFalse();
  }
}
