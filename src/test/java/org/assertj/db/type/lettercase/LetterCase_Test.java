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
package org.assertj.db.type.lettercase;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.db.common.AbstractTest;
import org.junit.Test;

/**
 * Tests on LetterCase
 *
 * @author Régis Pouiller
 */
public class LetterCase_Test extends AbstractTest {

  /**
   * This method tests the conversion of {@code convert} method from {@code LetterCase.DEFAULT}.
   */
  @Test
  public void test_conversion_of_default() {
    assertThat(LetterCase.getLetterCase(CaseConversions.UPPER, CaseComparisons.IGNORE).convert(null)).isNull();
    assertThat(LetterCase.getLetterCase(CaseConversions.UPPER, CaseComparisons.IGNORE).convert("azerty")).isEqualTo("AZERTY");
    assertThat(LetterCase.getLetterCase(CaseConversions.UPPER, CaseComparisons.IGNORE).convert("AzertY")).isEqualTo("AZERTY");
    assertThat(LetterCase.getLetterCase(CaseConversions.UPPER, CaseComparisons.IGNORE).convert("AZERTY")).isEqualTo("AZERTY");
  }

  /**
   * This method tests the comparison of {@code isEqual} method from {@code LetterCase.DEFAULT}.
   */
  @Test
  public void test_comparison_of_default() {
    assertThat(LetterCase.getLetterCase(CaseConversions.UPPER, CaseComparisons.IGNORE).isEqual(null, null)).isTrue();
    assertThat(LetterCase.getLetterCase(CaseConversions.UPPER, CaseComparisons.IGNORE).isEqual("AZERTY", null)).isFalse();
    assertThat(LetterCase.getLetterCase(CaseConversions.UPPER, CaseComparisons.IGNORE).isEqual(null, "AZERTY")).isFalse();
    assertThat(LetterCase.getLetterCase(CaseConversions.UPPER, CaseComparisons.IGNORE).isEqual("azerty", "AZERTY")).isTrue();
    assertThat(LetterCase.getLetterCase(CaseConversions.UPPER, CaseComparisons.IGNORE).isEqual("AzertY", "AZERTY")).isTrue();
    assertThat(LetterCase.getLetterCase(CaseConversions.UPPER, CaseComparisons.IGNORE).isEqual("AZERTY", "AZERTY")).isTrue();
  }

  /**
   * This method tests the conversion name of {@code getConversionName} method from {@code LetterCase}.
   */
  @Test
  public void test_conversion_name_of_default() {
    assertThat(LetterCase.TABLE_DEFAULT.getConversionName()).isEqualTo("NO - No case conversion");
    assertThat(LetterCase.COLUMN_DEFAULT.getConversionName()).isEqualTo("UPPER - Upper case conversion");
    assertThat(LetterCase.PRIMARY_KEY_DEFAULT.getConversionName()).isEqualTo("UPPER - Upper case conversion");
  }

  /**
   * This method tests the comparison name of {@code getComparisonName} method from {@code LetterCase}.
   */
  @Test
  public void test_comparison_name_of_default() {
    assertThat(LetterCase.TABLE_DEFAULT.getComparisonName()).isEqualTo("IGNORE - Ignore the case");
    assertThat(LetterCase.COLUMN_DEFAULT.getComparisonName()).isEqualTo("IGNORE - Ignore the case");
    assertThat(LetterCase.PRIMARY_KEY_DEFAULT.getComparisonName()).isEqualTo("IGNORE - Ignore the case");
  }
}
