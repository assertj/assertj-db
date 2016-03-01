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
package org.assertj.db.type.lettercase;

import org.assertj.db.common.AbstractTest;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests on the conversion of CaseComparisons
 *
 * @author Régis Pouiller
 *
 */
public class CaseComparisons_Test extends AbstractTest {

  /**
   * This method tests the comparison of {@code isEqual} method from {@code CaseComparisons} enum for {@code IGNORE}.
   */
  @Test
  public void test_comparison_of_ignore() {
    assertThat(CaseComparisons.IGNORE.isEqual(null, null)).isTrue();
    assertThat(CaseComparisons.IGNORE.isEqual("AZERTY", null)).isFalse();
    assertThat(CaseComparisons.IGNORE.isEqual(null, "AZERTY")).isFalse();
    assertThat(CaseComparisons.IGNORE.isEqual("azerty", "AZERTY")).isTrue();
    assertThat(CaseComparisons.IGNORE.isEqual("AzertY", "AZERTY")).isTrue();
    assertThat(CaseComparisons.IGNORE.isEqual("AZERTY", "AZERTY")).isTrue();
  }

  /**
   * This method tests the comparison of {@code isEqual} method from {@code CaseComparisons} enum for {@code STRICT}.
   */
  @Test
  public void test_comparison_of_strict() {
    assertThat(CaseComparisons.STRICT.isEqual(null, null)).isTrue();
    assertThat(CaseComparisons.STRICT.isEqual("AZERTY", null)).isFalse();
    assertThat(CaseComparisons.STRICT.isEqual(null, "AZERTY")).isFalse();
    assertThat(CaseComparisons.STRICT.isEqual("azerty", "AZERTY")).isFalse();
    assertThat(CaseComparisons.STRICT.isEqual("AzertY", "AZERTY")).isFalse();
    assertThat(CaseComparisons.STRICT.isEqual("AZERTY", "AZERTY")).isTrue();
  }
}
