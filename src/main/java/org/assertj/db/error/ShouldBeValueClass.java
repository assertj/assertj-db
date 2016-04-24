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
 * Creates an error message indicating that an assertion that verifies that a value is of a class.
 *
 * @author Régis Pouiller
 * @since 1.1.0
 */
public class ShouldBeValueClass extends BasicErrorMessageFactory {

  private static final String EXPECTED_MESSAGE =
          "%nExpecting:%n  <%s>%nto be of class%n  <%s>%nbut was of class%n  <%s>";
  private static final String EXPECTED_MESSAGE_WITH_INDEX =
          "%nExpecting that the value at index %s:%n  <%s>%nto be of class%n  <%s>%nbut was of class%n  <%s>";
  private static final String EXPECTED_MESSAGE_JUST_WITH_EXPECTED =
          "%nExpecting:%n  <%s>%nto be of class%n  <%s>";
  private static final String EXPECTED_MESSAGE_JUST_WITH_EXPECTED_WITH_INDEX =
          "%nExpecting that the value at index %s:%n  <%s>%nto be of class%n  <%s>";

  /**
   * Creates a new <code>{@link ShouldBeValueClass}</code>.
   *
   * @param actual The actual value in the failed assertion.
   * @param expected The expected class.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldBeValueClass(Value actual, Class expected) {
    if (actual.getValue() == null) {
      return new ShouldBeValueClass(actual, expected);
    }
    return new ShouldBeValueClass(actual, actual.getValue().getClass(), expected);
  }

  /**
   * Creates a new <code>{@link ShouldBeValueType}</code>.
   *
   * @param index The index of the value.
   * @param actual The actual value in the failed assertion.
   * @param expected The expected type.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldBeValueClass(int index, Value actual, Class expected) {
    if (actual.getValue() == null) {
      return new ShouldBeValueClass(index, actual, expected);
    }
    return new ShouldBeValueClass(index, actual, actual.getValue().getClass(), expected);
  }

  /**
   * Constructor.
   *
   * @param actual The actual value in the failed assertion.
   * @param tested The tested class.
   * @param expected The expected class.
   */
  private ShouldBeValueClass(Value actual, Class tested, Class expected) {
    super(EXPECTED_MESSAGE, actual.getValue(), expected, tested);
  }

  /**
   * Constructor.
   *
   * @param index The index of the value.
   * @param actual The actual value in the failed assertion.
   * @param tested The tested class.
   * @param expected The expected class.
   */
  private ShouldBeValueClass(int index, Value actual, Class tested, Class expected) {
    super(EXPECTED_MESSAGE_WITH_INDEX, index, actual.getValue(), expected, tested);
  }

  /**
   * Constructor.
   *
   * @param actual The actual value in the failed assertion.
   * @param expected The expected class.
   */
  private ShouldBeValueClass(Value actual, Class expected) {
    super(EXPECTED_MESSAGE_JUST_WITH_EXPECTED, actual.getValue(), expected);
  }

  /**
   * Constructor.
   *
   * @param index The index of the value.
   * @param actual The actual value in the failed assertion.
   * @param expected The expected class.
   */
  private ShouldBeValueClass(int index, Value actual, Class expected) {
    super(EXPECTED_MESSAGE_JUST_WITH_EXPECTED_WITH_INDEX, index, actual.getValue(), expected);
  }
}
