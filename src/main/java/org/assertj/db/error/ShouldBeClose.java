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
package org.assertj.db.error;

import org.assertj.core.error.BasicErrorMessageFactory;
import org.assertj.core.error.ErrorMessageFactory;

/**
 * Creates an error message indicating that an assertion that verifies that a value is close to another value.
 *
 * @author Régis Pouiller
 *
 */
public class ShouldBeClose extends BasicErrorMessageFactory {

    private static final String EXPECTED_MESSAGE = "%nExpecting:%n  <%s>%nto be close to: %n  <%s> %n with tolerance <%s>";

  /**
   * Creates a new <code>{@link ShouldBeClose}</code>.
   *
   * @param actual The actual value in the failed assertion.
   * @param expected The expected value to compare to.
   * @param tolerance The tolerance of the closeness.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldBeClose(Object actual, Object expected, Object tolerance) {
    return new ShouldBeClose(actual, expected, tolerance);
  }

  /**
   * Constructor.
   *
   * @param actual The actual value in the failed assertion.
   * @param expected The expected value to compare to.
   * @param tolerance The tolerance of the closeness.
   */
  private ShouldBeClose(Object actual, Object expected, Object tolerance) {
    super(EXPECTED_MESSAGE, actual, expected, tolerance);
  }
}
