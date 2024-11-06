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
package org.assertj.db.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.lettercase.CaseComparisons;
import org.junit.Test;

/**
 * Tests on the comparator for the names.
 *
 * @author Régis Pouiller
 */
public class NameComparator_Test extends AbstractTest {

  /**
   * Test the {@code valueOf} method.
   */
  @Test
  public void test_valueOf() {
    assertThat(NameComparator.valueOf("INSTANCE")).isEqualTo(NameComparator.INSTANCE);
  }

  /**
   * Test the {@code contains} method.
   *
   * @throws Exception Exception
   */
  @Test
  public void test_contains() throws Exception {
    assertThat(NameComparator.INSTANCE.contains(Arrays.asList("azerty", "AzerTy"), "AZERTY", CaseComparisons.IGNORE)).isTrue();
    assertThat(NameComparator.INSTANCE.contains(Arrays.asList("azerty", "AzerTy"), "azerty", CaseComparisons.IGNORE)).isTrue();
    assertThat(NameComparator.INSTANCE.contains(Arrays.asList("azerty", "AzerTy"), "AzerTy", CaseComparisons.IGNORE)).isTrue();
    assertThat(NameComparator.INSTANCE.contains(Arrays.asList("azerty", "AzerTy"), "AZERTY", CaseComparisons.STRICT)).isFalse();
    assertThat(NameComparator.INSTANCE.contains(Arrays.asList("azerty", "AzerTy"), "azerty", CaseComparisons.STRICT)).isTrue();
    assertThat(NameComparator.INSTANCE.contains(Arrays.asList("azerty", "AzerTy"), "AzerTy", CaseComparisons.STRICT)).isTrue();
  }

  /**
   * Test the {@code indexOf} method.
   *
   * @throws Exception Exception
   */
  @Test
  public void test_indexOf() throws Exception {
    assertThat(NameComparator.INSTANCE.indexOf(Arrays.asList("azerty", "AzerTy"), "AZERTY", CaseComparisons.IGNORE)).isEqualTo(0);
    assertThat(NameComparator.INSTANCE.indexOf(Arrays.asList("azerty", "AzerTy"), "azerty", CaseComparisons.IGNORE)).isEqualTo(0);
    assertThat(NameComparator.INSTANCE.indexOf(Arrays.asList("azerty", "AzerTy"), "AzerTy", CaseComparisons.IGNORE)).isEqualTo(0);
    assertThat(NameComparator.INSTANCE.indexOf(Arrays.asList("azerty", "AzerTy"), "AZERTY", CaseComparisons.STRICT)).isEqualTo(-1);
    assertThat(NameComparator.INSTANCE.indexOf(Arrays.asList("azerty", "AzerTy"), "azerty", CaseComparisons.STRICT)).isEqualTo(0);
    assertThat(NameComparator.INSTANCE.indexOf(Arrays.asList("azerty", "AzerTy"), "AzerTy", CaseComparisons.STRICT)).isEqualTo(1);
  }
}
