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
 * Copyright 2012-2015 the original author or authors.
 */
package org.assertj.db.error;

import org.assertj.core.error.BasicErrorMessageFactory;
import org.assertj.core.error.ErrorMessageFactory;
import org.assertj.db.type.ValueType;

/**
 * Creates an error message indicating that an assertion that verifies that a value is of a type.
 * 
 * @author Régis Pouiller
 * 
 */
public class ShouldBeValueTypeWithStartPoint extends BasicErrorMessageFactory {

  private static final String EXPECTED_MESSAGE = "\nExpecting that the value at start point:\n  <%s>\nto be of type\n"
      + "  <%s>\nbut was of type\n  <%s>";

  /**
   * Creates a new <code>{@link org.assertj.db.error.ShouldBeValueTypeWithStartPoint}</code>.
   *
   * @param actual The actual value in the failed assertion.
   * @param expected The expected type.
   * @param tested The tested type.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldBeValueTypeWithStartPoint(Object actual, ValueType expected, ValueType tested) {
    return new ShouldBeValueTypeWithStartPoint(actual, expected, tested);
  }

  /**
   * Constructor.
   *
   * @param actual The actual value in the failed assertion.
   * @param expected The expected type.
   * @param tested The tested type.
   */
  private ShouldBeValueTypeWithStartPoint(Object actual, ValueType expected, ValueType tested) {
    super(EXPECTED_MESSAGE, actual, expected, tested);
  }
}
