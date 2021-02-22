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
 * Copyright 2015-2021 the original author or authors.
 */
package org.assertj.db.error;

import org.assertj.core.error.BasicErrorMessageFactory;
import org.assertj.core.error.ErrorMessageFactory;

/**
 * Creates an error message indicating that an assertion that verifies the values of the columns of the primary key.
 *
 * @author Régis Pouiller
 *
 */
public class ShouldHavePksValues extends BasicErrorMessageFactory {

  /**
   * Creates a new <code>{@link ShouldHavePksValues}</code>.
   *
   * @param values The values of the columns.
   * @param expectedValues The expected values of the columns.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldHavePksValues(Object[] values, Object[] expectedValues) {
    return new ShouldHavePksValues(values, expectedValues);
  }

  /**
   * Constructor.
   *
   * @param values The values of the columns.
   * @param expectedValues The expected values of the columns.
   */
  private ShouldHavePksValues(Object[] values, Object[] expectedValues) {
    super("%nExpecting :%n  %s%nto be the values of the columns of the primary keys but was:%n  %s", expectedValues, values);
  }
}