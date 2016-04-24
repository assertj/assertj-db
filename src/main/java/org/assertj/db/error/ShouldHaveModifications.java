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

/**
 * Creates an error message indicating that an assertion that verifies the modified columns.
 *
 * @author Régis Pouiller
 *
 */
public class ShouldHaveModifications extends BasicErrorMessageFactory {

  /**
   * Creates a new <code>{@link ShouldHaveModifications}</code>.
   *
   * @param modifications The modifications.
   * @param expectedModifications The expected modifications.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldHaveModifications(Integer[] modifications, Integer[] expectedModifications) {
    return new ShouldHaveModifications(modifications, expectedModifications);
  }

  /**
   * Creates a new <code>{@link ShouldHaveModifications}</code>.
   *
   * @param modifications The modifications.
   * @param expectedModifications The expected modifications.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldHaveModifications(String[] modifications, String[] expectedModifications) {
    return new ShouldHaveModifications(modifications, expectedModifications);
  }

  /**
   * Constructor.
   *
   * @param modifications The modifications.
   * @param expectedModifications The expected modifications.
   */
  private ShouldHaveModifications(Integer[] modifications, Integer[] expectedModifications) {
    super("%nExpecting :%n  %s%nas indexes of modified columns but was:%n  %s", expectedModifications, modifications);
  }

  /**
   * Constructor.
   *
   * @param modifications The modifications.
   * @param expectedModifications The expected modifications.
   */
  private ShouldHaveModifications(String[] modifications, String[] expectedModifications) {
    super("%nExpecting :%n  %s%nas modified columns but was:%n  %s", expectedModifications, modifications);
  }
}
