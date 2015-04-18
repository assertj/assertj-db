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
 * Copyright 2012-2014 the original author or authors.
 */
package org.assertj.db.error;

import org.assertj.core.error.BasicErrorMessageFactory;
import org.assertj.core.error.ErrorMessageFactory;

/**
 * Creates an error message indicating that an assertion that verifies that a value is after or equal a value.
 * 
 * @author Régis Pouiller
 * 
 */
public class ShouldBeAfterOrEqual extends BasicErrorMessageFactory {

  /**
   * Creates a new <code>{@link ShouldBeAfterOrEqual}</code>.
   * 
   * @param actual The actual value in the failed assertion.
   * @param expected The expected value to compare to.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldBeAfterOrEqual(Object actual, Object expected) {
    return new ShouldBeAfterOrEqual(actual, expected);
  }

  /**
   * Constructor.
   * 
   * @param actual The actual value in the failed assertion.
   * @param expected The expected value to compare to.
   */
  private ShouldBeAfterOrEqual(Object actual, Object expected) {
    super("\nExpecting:\n  <%s>\nto be after or equal to \n  <%s>", actual, expected);
  }
}
