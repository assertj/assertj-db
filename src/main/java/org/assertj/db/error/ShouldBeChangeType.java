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
 * Copyright 2015-2020 the original author or authors.
 */
package org.assertj.db.error;

import org.assertj.core.error.BasicErrorMessageFactory;
import org.assertj.core.error.ErrorMessageFactory;
import org.assertj.db.type.ChangeType;

/**
 * Creates an error message indicating that an assertion that verifies that a change is of a type.
 *
 * @author Régis Pouiller
 */
public class ShouldBeChangeType extends BasicErrorMessageFactory {

  private static final String EXPECTED_MESSAGE =
          "%nExpecting:%nto be of type%n" + "  <%s>%nbut was of type%n  <%s>";

  /**
   * Creates a new <code>{@link ShouldBeChangeType}</code>.
   *
   * @param expected The expected type.
   * @param tested   The tested type.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldBeChangeType(ChangeType expected, ChangeType tested) {
    return new ShouldBeChangeType(expected, tested);
  }

  /**
   * Constructor.
   *
   * @param expected The expected type.
   * @param tested   The tested type.
   */
  private ShouldBeChangeType(ChangeType expected, ChangeType tested) {
    super(EXPECTED_MESSAGE, expected, tested);
  }
}