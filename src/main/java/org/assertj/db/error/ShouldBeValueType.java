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
import org.assertj.db.type.ValueType;

/**
 * Creates an error message indicating that an assertion that verifies that a value is of a type.
 * 
 * @author Régis Pouiller
 * 
 */
public class ShouldBeValueType extends BasicErrorMessageFactory {

  private static final String EXPECTED_MESSAGE =
          "%nExpecting:%n  <%s>%nto be of type%n  <%s>%nbut was of type%n  <%s>";
  private static final String EXPECTED_MESSAGE_WITH_INDEX =
          "%nExpecting that the value at index %s:%n  <%s>%nto be of type%n  <%s>%nbut was of type%n  <%s>";
  private static final String EXPECTED_MESSAGE_NOT_IDENTIFIED =
          "%nExpecting:%n  <%s>%nto be of type%n  <%s>%nbut was of type%n  <%s> (%s)";
  private static final String EXPECTED_MESSAGE_NOT_IDENTIFIED_WITH_INDEX =
          "%nExpecting that the value at index %s:%n  <%s>%nto be of type%n  <%s>%nbut was of type%n  <%s> (%s)";

  /**
   * Creates a new <code>{@link ShouldBeValueType}</code>.
   * 
   * @param actual The actual value in the failed assertion.
   * @param tested The tested type.
   * @param expected The expected type.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldBeValueType(Value actual, ValueType tested, ValueType expected) {
    if (actual.getValue() != null && tested == ValueType.NOT_IDENTIFIED) {
      return  new ShouldBeValueType(actual, actual.getValue().getClass(), tested, expected);
    }
    return new ShouldBeValueType(actual, tested, expected);
  }

  /**
   * Creates a new <code>{@link ShouldBeValueType}</code>.
   * 
   * @param index The index of the value.
   * @param actual The actual value in the failed assertion.
   * @param tested The tested type.
   * @param expected The expected type.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldBeValueType(int index, Value actual, ValueType tested, ValueType expected) {
    if (actual.getValue() != null && tested == ValueType.NOT_IDENTIFIED) {
      return  new ShouldBeValueType(index, actual, actual.getValue().getClass(), tested, expected);
    }
    return new ShouldBeValueType(index, actual, tested, expected);
  }

  /**
   * Constructor.
   * 
   * @param actual The actual value in the failed assertion.
   * @param tested The tested type.
   * @param expected The expected type.
   */
  private ShouldBeValueType(Value actual, ValueType tested, ValueType expected) {
    super(EXPECTED_MESSAGE, actual.getValue(), expected, tested);
  }

  /**
   * Constructor.
   * 
   * @param index The index of the value.
   * @param actual The actual value in the failed assertion.
   * @param tested The tested type.
   * @param expected The expected type.
   */
  private ShouldBeValueType(int index, Value actual, ValueType tested, ValueType expected) {
    super(EXPECTED_MESSAGE_WITH_INDEX, index, actual.getValue(), expected, tested);
  }

  /**
   * Constructor.
   *
   * @param actual The actual value in the failed assertion.
   * @param classOfActual Class of the actual value (for not identified type).
   * @param tested The tested type.
   * @param expected The expected type.
   */
  private ShouldBeValueType(Value actual, Class classOfActual, ValueType tested, ValueType expected) {
    super(EXPECTED_MESSAGE_NOT_IDENTIFIED, actual.getValue(), expected, tested, classOfActual);
  }

  /**
   * Constructor.
   *
   * @param index The index of the value.
   * @param actual The actual value in the failed assertion.
   * @param classOfActual Class of the actual value (for not identified type).
   * @param tested The tested type.
   * @param expected The expected type.
   */
  private ShouldBeValueType(int index, Value actual, Class classOfActual, ValueType tested, ValueType expected) {
    super(EXPECTED_MESSAGE_NOT_IDENTIFIED_WITH_INDEX, index, actual.getValue(), expected, tested, classOfActual);
  }
}
