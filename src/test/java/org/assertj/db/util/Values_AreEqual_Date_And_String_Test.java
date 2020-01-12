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
 * Copyright 2015-2020 the original author or authors.
 */
package org.assertj.db.util;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.exception.AssertJDBException;
import org.junit.Test;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests on {@code areEqual} method for {@code DateValue}s and {@code String}s.
 *
 * @author Régis Pouiller
 *
 */
public class Values_AreEqual_Date_And_String_Test extends AbstractTest {

  /**
   * This method tests the {@code areEqual} method for {@code TimeValue}s.
   */
  @Test
  public void test_are_equal_for_dates() throws Exception {
    assertThat(Values.areEqual(getValue(null, Date.valueOf("2007-12-23")), "2007-12-23")).isTrue();
    assertThat(Values.areEqual(getValue(null, Date.valueOf("2007-12-23")), "2007-12-23T00:00")).isTrue();
    assertThat(Values.areEqual(getValue(null, Date.valueOf("2007-12-23")), "2007-12-23T00:00:00")).isTrue();
    assertThat(Values.areEqual(getValue(null, Date.valueOf("2007-12-23")), "2007-12-23T00:00:00.000000000")).isTrue();
    assertThat(Values.areEqual(getValue(null, null), (String) null)).isTrue();

    assertThat(Values.areEqual(getValue(null, Date.valueOf("2007-12-23")), "2007-12-24")).isFalse();
    assertThat(Values.areEqual(getValue(null, Date.valueOf("2007-12-23")), "2007-01-23")).isFalse();
    assertThat(Values.areEqual(getValue(null, Date.valueOf("2007-12-23")), "2008-12-23")).isFalse();
    assertThat(Values.areEqual(getValue(null, Date.valueOf("2007-12-23")), "2007-12-23T00:01")).isFalse();
    assertThat(Values.areEqual(getValue(null, Date.valueOf("2007-12-23")), "2007-12-23T01:00")).isFalse();
    assertThat(Values.areEqual(getValue(null, Date.valueOf("2007-12-23")), "2007-12-24T00:00")).isFalse();
    assertThat(Values.areEqual(getValue(null, Date.valueOf("2007-12-23")), "2007-01-23T00:00")).isFalse();
    assertThat(Values.areEqual(getValue(null, Date.valueOf("2007-12-23")), "2008-12-23T00:00")).isFalse();
    assertThat(Values.areEqual(getValue(null, Date.valueOf("2007-12-23")), "2007-12-23T00:00:01")).isFalse();
    assertThat(Values.areEqual(getValue(null, Date.valueOf("2007-12-23")), "2007-12-23T00:01:00")).isFalse();
    assertThat(Values.areEqual(getValue(null, Date.valueOf("2007-12-23")), "2007-12-23T01:00:00")).isFalse();
    assertThat(Values.areEqual(getValue(null, Date.valueOf("2007-12-23")), "2007-12-24T00:00:00")).isFalse();
    assertThat(Values.areEqual(getValue(null, Date.valueOf("2007-12-23")), "2007-01-23T00:00:00")).isFalse();
    assertThat(Values.areEqual(getValue(null, Date.valueOf("2007-12-23")), "2008-12-23T00:00:00")).isFalse();
    assertThat(Values.areEqual(getValue(null, Date.valueOf("2007-12-23")), "2007-12-23T00:00:00.000000001")).isFalse();
    assertThat(Values.areEqual(getValue(null, Date.valueOf("2007-12-23")), "2007-12-23T00:00:01.000000000")).isFalse();
    assertThat(Values.areEqual(getValue(null, Date.valueOf("2007-12-23")), "2007-12-23T00:01:00.000000000")).isFalse();
    assertThat(Values.areEqual(getValue(null, Date.valueOf("2007-12-23")), "2007-12-23T01:00:00.000000000")).isFalse();
    assertThat(Values.areEqual(getValue(null, Date.valueOf("2007-12-23")), "2007-12-24T00:00:00.000000000")).isFalse();
    assertThat(Values.areEqual(getValue(null, Date.valueOf("2007-12-23")), "2007-01-23T00:00:00.000000000")).isFalse();
    assertThat(Values.areEqual(getValue(null, Date.valueOf("2007-12-23")), "2008-12-23T00:00:00.000000000")).isFalse();
    assertThat(Values.areEqual(getValue(null, Date.valueOf("2007-12-23")), (String) null)).isFalse();
  }

  /**
   * This method should fail because the expected value ("***") is not parsable to do the comparison.
   */
  @Test(expected = AssertJDBException.class)
  public void should_fail_because_string_is_not_parseable() throws Exception {
    Values.areEqual(getValue(null, Date.valueOf("2007-12-23")), "***");
  }

}
