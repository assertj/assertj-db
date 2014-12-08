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
package org.assertj.db.api;

/**
 * Assertion methods about a value of a {@code Row} of a {@code Change}.
 *
 * @author Régis Pouiller
 *
 */
public class ChangeValueAssert extends AbstractAssert<ChangeValueAssert> {

  /**
   * The actual value on which the assertion is.
   */
  private final Object value;

  /**
   * The original assert.
   */
  private final ChangeRowAssert originalAssert;

  /**
   * Constructor.
   *
   * @param originalAssert The original assert.
   * @param value The value on which are the assertions.
   */
  ChangeValueAssert(ChangeRowAssert originalAssert, Object value) {
    super(ChangeValueAssert.class);
    this.originalAssert = originalAssert;
    this.value = value;
  }
}
