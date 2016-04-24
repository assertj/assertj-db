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
 * Copyright 2012-2016 the original author or authors.
 */
package org.assertj.db.type.lettercase;

import org.assertj.db.common.AbstractTest;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests on the conversion of CaseConversions
 *
 * @author Régis Pouiller
 *
 */
public class CaseConversions_Test extends AbstractTest {

  /**
   * This method tests the method of {@code getConversionName} method from {@code CaseConversions} enum for {@code UPPER}.
   */
  @Test
  public void test_name_of_upper() {
    assertThat(CaseConversions.UPPER.getConversionName()).isEqualTo("UPPER - Upper case conversion");
  }

  /**
   * This method tests the method of {@code getConversionName} method from {@code CaseConversions} enum for {@code LOWER}.
   */
  @Test
  public void test_name_of_lower() {
    assertThat(CaseConversions.LOWER.getConversionName()).isEqualTo("LOWER - Lower case conversion");
  }

  /**
   * This method tests the method of {@code getConversionName} method from {@code CaseConversions} enum for {@code NO}.
   */
  @Test
  public void test_name_of_no() {
    assertThat(CaseConversions.NO.getConversionName()).isEqualTo("NO - No case conversion");
  }

  /**
   * This method tests the conversion of {@code convert} method from {@code CaseConversions} enum for {@code UPPER}.
   */
  @Test
  public void test_conversion_of_upper() {
    assertThat(CaseConversions.UPPER.convert(null)).isNull();
    assertThat(CaseConversions.UPPER.convert("azerty")).isEqualTo("AZERTY");
    assertThat(CaseConversions.UPPER.convert("AzertY")).isEqualTo("AZERTY");
    assertThat(CaseConversions.UPPER.convert("AZERTY")).isEqualTo("AZERTY");
  }

  /**
   * This method tests the conversion of {@code convert} method from {@code CaseConversions} enum for {@code LOWER}.
   */
  @Test
  public void test_conversion_of_lower() {
    assertThat(CaseConversions.LOWER.convert(null)).isNull();
    assertThat(CaseConversions.LOWER.convert("azerty")).isEqualTo("azerty");
    assertThat(CaseConversions.LOWER.convert("AzertY")).isEqualTo("azerty");
    assertThat(CaseConversions.LOWER.convert("AZERTY")).isEqualTo("azerty");
  }

  /**
   * This method tests the conversion of {@code convert} method from {@code CaseConversions} enum for {@code NO}.
   */
  @Test
  public void test_conversion_of_no() {
    assertThat(CaseConversions.NO.convert(null)).isNull();
    assertThat(CaseConversions.NO.convert("azerty")).isEqualTo("azerty");
    assertThat(CaseConversions.NO.convert("AzertY")).isEqualTo("AzertY");
    assertThat(CaseConversions.NO.convert("AZERTY")).isEqualTo("AZERTY");
  }
}
