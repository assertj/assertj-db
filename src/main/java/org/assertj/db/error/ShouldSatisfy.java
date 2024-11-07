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

import org.assertj.core.api.Condition;
import org.assertj.core.error.BasicErrorMessageFactory;
import org.assertj.core.error.ErrorMessageFactory;

/**
 * Creates an error message indicating that an assertion that verifies that a value does not satisfying condition.
 *
 * @author Julien Roy
 */
public class ShouldSatisfy extends BasicErrorMessageFactory {

  private static final String EXPECTED_MESSAGE_WITH_INDEX = "%nExpecting that the value at index %s:%n  %s%nto satisfy: %n  %s";

  private ShouldSatisfy(int index, Object actual, Condition<?> condition) {
    super(EXPECTED_MESSAGE_WITH_INDEX, index, actual, condition);
  }

  /**
   * Verifies that the values of a row satisfy to conditions in parameter.
   *
   * @param index     The index of properties
   * @param actual    The actual value that triggered assertion error.
   * @param condition The condition that triggered assertion error.
   * @return {@code this} condition not satisfied error message.
   */
  public static ErrorMessageFactory shouldSatisfy(int index, Object actual, Condition<?> condition) {
    return new ShouldSatisfy(index, actual, condition);
  }

}
