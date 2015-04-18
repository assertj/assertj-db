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
 * Creates an error message indicating that an assertion that verifies the name of the column.
 *
 * @author Régis Pouiller
 *
 */
public class ShouldHaveName extends BasicErrorMessageFactory {

  /**
   * Creates a new <code>{@link org.assertj.db.error.ShouldHaveName}</code>.
   *
   * @param name The name of the column.
   * @param expectedName The expected name of the column.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldHaveName(String name, String expectedName) {
    return new ShouldHaveName(name, expectedName);
  }

  /**
   * Constructor.
   *
   * @param name The name of the column.
   * @param expectedName The expected name of the column.
   */
  private ShouldHaveName(String name, String expectedName) {
    super("\nExpecting :\n  %s\nto be the name of the column but was:\n  %s", expectedName, name);
  }
}
