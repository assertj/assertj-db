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
package org.assertj.db.error;

import org.assertj.core.error.BasicErrorMessageFactory;
import org.assertj.core.error.ErrorMessageFactory;
import org.assertj.db.type.Value;

/**
 * Creates an error message indicating that an assertion that verifies that a value is compatible with a value.
 *
 * @author Régis Pouiller
 *
 */
public class ShouldBeCompatible extends BasicErrorMessageFactory {

  /**
   * Creates a new <code>{@link ShouldBeCompatible}</code>.
   *
   * @param actual The actual value in the failed assertion.
   * @param expected The expected value to compare to.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldBeCompatible(Value actual, Object expected) {
    if (expected == null) {
      return new ShouldBeCompatible(actual, expected);
    }
    return new ShouldBeCompatible(actual, expected.getClass(), expected);
  }

  /**
   * Constructor.
   *
   * @param actual The actual value in the failed assertion.
   * @param clazz The class of the expected value.
   * @param expected The expected value to compare to.
   */
  private ShouldBeCompatible(Value actual, Class<?> clazz, Object expected) {
    super("%nExpecting:%n  %s : <%s>%nto be compatible with %n  %s : <%s>", actual.getValueTypeRepresentation(),
          actual.getValue(), clazz, expected);
  }

  /**
   * Constructor.
   *
   * @param actual The actual value in the failed assertion.
   * @param expected The expected value to compare to.
   */
  private ShouldBeCompatible(Value actual, Object expected) {
    super("%nExpecting:%n  %s : <%s>%nto be compatible with %n  <%s>", actual.getValueTypeRepresentation(),
          actual.getValue(), expected);
  }
}
