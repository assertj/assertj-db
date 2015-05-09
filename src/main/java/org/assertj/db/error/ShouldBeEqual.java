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

/**
 * Creates an error message indicating that an assertion that verifies that a value is equal to another value.
 * 
 * @author Régis Pouiller
 * 
 */
public class ShouldBeEqual extends BasicErrorMessageFactory {

  private static final String EXPECTED_MESSAGE = "\nExpecting:\n  <%s>\nto be equal to: \n  <%s>";
  private static final String EXPECTED_MESSAGE_BUT_NOT = "\nExpecting to be equal to the expected value but was not equal";
  private static final String EXPECTED_MESSAGE_WITH_INDEX = "\nExpecting that the value at index %s:\n  <%s>\nto be equal to: \n  <%s>";
  private static final String EXPECTED_MESSAGE_BUT_NOT_WITH_INDEX = "\nExpecting that the value at index %s to be equal to the expected value but was not equal";

  /**
   * Creates a new <code>{@link ShouldBeEqual}</code>.
   * 
   * @param actual The actual value in the failed assertion.
   * @param expected The expected value to compare to.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldBeEqual(Object actual, Object expected) {
    return new ShouldBeEqual(actual, expected);
  }

  /**
   * Creates a new <code>{@link ShouldBeEqual}</code>.
   * 
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldBeEqual() {
    return new ShouldBeEqual();
  }

  /**
   * Creates a new <code>{@link ShouldBeEqual}</code>.
   * 
   * @param index The index of the value.
   * @param actual The actual value in the failed assertion.
   * @param expected The expected value to compare to.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldBeEqual(int index, Object actual, Object expected) {
    return new ShouldBeEqual(index, actual, expected);
  }

  /**
   * Creates a new <code>{@link ShouldBeEqual}</code>.
   * 
   * @param index The index of the value.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldBeEqual(int index) {
    return new ShouldBeEqual(index);
  }

  /**
   * Constructor.
   * 
   * @param actual The actual value in the failed assertion.
   * @param expected The expected value to compare to.
   */
  private ShouldBeEqual(Object actual, Object expected) {
    super(EXPECTED_MESSAGE, actual, expected);
  }

  /**
   * Constructor.
   */
  private ShouldBeEqual() {
    super(EXPECTED_MESSAGE_BUT_NOT);
  }

  /**
   * Constructor.
   * 
   * @param index The index of the value.
   * @param actual The actual value in the failed assertion.
   * @param expected The expected value to compare to.
   */
  private ShouldBeEqual(int index, Object actual, Object expected) {
    super(EXPECTED_MESSAGE_WITH_INDEX, index, actual, expected);
  }

  /**
   * Constructor.
   * 
   * @param index The index of the value.
   */
  private ShouldBeEqual(int index) {
    super(EXPECTED_MESSAGE_BUT_NOT_WITH_INDEX, index);
  }
}
