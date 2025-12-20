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
package org.assertj.db.type.lettercase;

import static org.junit.Assert.fail;

import org.assertj.core.api.Assertions;
import org.assertj.db.common.AbstractTest;
import org.junit.Test;

/**
 * Tests on the exceptions of LetterCase
 *
 * @author Régis Pouiller
 */
public class LetterCase_Exception_Test extends AbstractTest {

  /**
   * This method should fail because the case conversion is null.
   */
  @Test
  public void should_fail_because_case_conversion_is_null() {
    try {
      LetterCase.getLetterCase(null, null);
      fail("An exception must be raised");
    } catch (NullPointerException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("The case conversion must be not null"));
    }
  }

  /**
   * This method should fail because the case comparison is null.
   */
  @Test
  public void should_fail_because_case_comparison_is_null() {
    try {
      LetterCase.getLetterCase(CaseConversions.NO, null);
      fail("An exception must be raised");
    } catch (NullPointerException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("The case comparison must be not null"));
    }
  }
}
