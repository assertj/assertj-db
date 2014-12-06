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

import org.assertj.db.type.Change;

/**
 * Assertion methods about the {@link Change}.
 * 
 * @author Régis Pouiller
 * 
 */
public class ChangeAssert extends AbstractAssert<ChangeAssert> {

  /**
   * The actual change on which the assertion is.
   */
  private final Change actual;

  /**
   * The original assert.
   */
  private final ChangesAssert originalAssert;

  /**
   * Constructor.
   * 
   * @param originalAssert The original assert.
   * @param change The {@link Change} on which are the assertions.
   */
  ChangeAssert(ChangesAssert originalAssert, Change change) {
    super(ChangeAssert.class);
    this.originalAssert = originalAssert;
    this.actual = change;
  }

  /**
   * Returns the assert on the changes.
   * 
   * @return The assert on the changes.
   */
  public ChangesAssert returnToOriginAssert() {
    return originalAssert;
  }

}
