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

/**
 * Creates an error message indicating that an assertion that verifies the rows size a value failed.
 *
 * @author Régis Pouiller
 * @since 1.1.0
 */
public class ShouldHaveRowsSizeGreater extends BasicErrorMessageFactory {

  /**
   * Creates a new <code>{@link ShouldHaveRowsSizeGreater}</code>.
   *
   * @param actualSize the size of {@code actual}.
   * @param expectedSize the expected size.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldHaveRowsSizeGreater(int actualSize, int expectedSize) {
    return new ShouldHaveRowsSizeGreater(actualSize, expectedSize);
  }

  /**
   * Constructor.
   *
   * @param actualSize the size of {@code actual}.
   * @param expectedSize the expected size.
   */
  private ShouldHaveRowsSizeGreater(int actualSize, int expectedSize) {
    super("%nExpecting size (number of rows) to be greater than :%n   <%s>%nbut was:%n   <%s>", expectedSize, actualSize);
  }
}
