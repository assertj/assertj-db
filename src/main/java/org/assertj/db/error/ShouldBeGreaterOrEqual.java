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
import org.assertj.db.type.Value;

/**
 * Creates an error message indicating that an assertion that verifies that a value is greater than or equal to another
 * value.
 *
 * @author Régis Pouiller
 */
public class ShouldBeGreaterOrEqual extends BasicErrorMessageFactory {

  /**
   * Constructor.
   *
   * @param actual   The actual value in the failed assertion.
   * @param expected The expected value to compare to.
   */
  private ShouldBeGreaterOrEqual(Value actual, Object expected) {
    super("%nExpecting:%n  <%s>%nto be greater than or equal to %n  <%s>", actual.getValue(), expected);
  }

  /**
   * Creates a new <code>{@link ShouldBeGreaterOrEqual}</code>.
   *
   * @param actual   The actual value in the failed assertion.
   * @param expected The expected value to compare to.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldBeGreaterOrEqual(Value actual, Object expected) {
    return new ShouldBeGreaterOrEqual(actual, expected);
  }
}
