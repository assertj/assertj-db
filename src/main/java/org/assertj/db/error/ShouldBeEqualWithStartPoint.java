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
 * Creates an error message indicating that an assertion that verifies that a value at start point is equal to another value.
 *
 * @author Régis Pouiller
 */
public class ShouldBeEqualWithStartPoint extends BasicErrorMessageFactory {

  private static final String EXPECTED_MESSAGE = "%nExpecting that start point:%n  <%s>%nto be equal to: %n  <%s>";
  private static final String EXPECTED_MESSAGE_WITHOUT_VALUES = "%nExpecting that start point to be equal to the expected value but was not equal";

  /**
   * Constructor.
   *
   * @param actual   The actual value in the failed assertion.
   * @param expected The expected value to compare to.
   */
  public ShouldBeEqualWithStartPoint(Object actual, Object expected) {
    super(EXPECTED_MESSAGE, actual, expected);
  }

  /**
   * Constructor.
   */
  private ShouldBeEqualWithStartPoint() {
    super(EXPECTED_MESSAGE_WITHOUT_VALUES);
  }

  /**
   * Creates a new <code>{@link org.assertj.db.error.ShouldBeEqualWithStartPoint}</code>.
   *
   * @param actual   The actual value in the failed assertion.
   * @param expected The expected value to compare to.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldBeEqualWithStartPoint(Object actual, Object expected) {
    return new ShouldBeEqualWithStartPoint(actual, expected);
  }

  /**
   * Creates a new <code>{@link org.assertj.db.error.ShouldBeEqualWithStartPoint}</code>.
   *
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldBeEqualWithStartPoint() {
    return new ShouldBeEqualWithStartPoint();
  }
}
