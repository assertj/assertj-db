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
 * Creates an error message indicating that an assertion that verifies that a change is on a table.
 *
 * @author Régis Pouiller
 */
public class ShouldBeOnTable extends BasicErrorMessageFactory {

  private static final String EXPECTED_MESSAGE =
          "%nExpecting to be on the table:%n" + "  <%s>%nbut was on the table:%n  <%s>";

  /**
   * Creates a new <code>{@link ShouldBeOnTable}</code>.
   *
   * @param expected The expected table.
   * @param tested   The tested table.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldBeOnTable(String expected, String tested) {
    return new ShouldBeOnTable(expected, tested);
  }

  /**
   * Constructor.
   *
   * @param expected The expected table.
   * @param tested   The tested table.
   */
  private ShouldBeOnTable(String expected, String tested) {
    super(EXPECTED_MESSAGE, expected, tested);
  }
}
