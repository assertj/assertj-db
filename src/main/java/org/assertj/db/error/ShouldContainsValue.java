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
package org.assertj.db.error;

import org.assertj.core.error.BasicErrorMessageFactory;
import org.assertj.core.error.ErrorMessageFactory;

/**
 * Creates an error message indicating that an assertion that verifies that values are contained in values.
 *
 * @author Régis Pouiller
 */
public class ShouldContainsValue extends BasicErrorMessageFactory {

  private static final String EXPECTED_MESSAGE = "%nExpecting:%n  <%s>%nto contain: %n  <%s>%n (parameter <%s> at index %s is not found)";
  private static final String EXPECTED_MESSAGE_BUT_NOT = "%nExpecting to contain values but not%n (parameter at index %s is not found)";

  /**
   * Constructor.
   *
   * @param actual   The actual values in the failed assertion.
   * @param expected The expected values to compare to.
   * @param value    The value which is not found.
   * @param index    The index of the value which is not found.
   */
  private ShouldContainsValue(Object actual, Object expected, Object value, int index) {
    super(EXPECTED_MESSAGE, actual, expected, value, index);
  }

  /**
   * Constructor.
   *
   * @param index The index of the value which is not found.
   */
  private ShouldContainsValue(int index) {
    super(EXPECTED_MESSAGE_BUT_NOT, index);
  }

  /**
   * Creates a new <code>{@link org.assertj.db.error.ShouldContainsValue}</code>.
   *
   * @param actual   The actual values in the failed assertion.
   * @param expected The expected values to compare to.
   * @param value    The value which is not found.
   * @param index    The index of the value which is not found.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldContainsValue(Object actual, Object expected, Object value, int index) {
    return new ShouldContainsValue(actual, expected, value, index);
  }

  /**
   * Creates a new <code>{@link org.assertj.db.error.ShouldContainsValue}</code>.
   *
   * @param index The index of the value which is not found.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldContainsValue(int index) {
    return new ShouldContainsValue(index);
  }
}
