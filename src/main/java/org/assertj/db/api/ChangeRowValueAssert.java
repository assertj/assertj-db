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

import org.assertj.db.api.navigation.ToValue;
import org.assertj.db.api.navigation.ToValueFromRow;

/**
 * Assertion methods for a value of a {@code Row} of a {@code Change}.
 *
 * @author Régis Pouiller
 *
 */
public class ChangeRowValueAssert
        extends AbstractAssertWithValues<ChangeRowValueAssert, ChangeRowAssert>
        implements ToValue<ChangeRowValueAssert>,
                   ToValueFromRow<ChangeRowValueAssert> {

  /**
   * Constructor.
   *
   * @param origin The assertion of {@link org.assertj.db.api.origin.Origin}.
   * @param value The value on which are the assertions.
   */
  ChangeRowValueAssert(ChangeRowAssert origin, Object value) {
    super(ChangeRowValueAssert.class, origin, value);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeRowValueAssert value() {
    return origin.value();
  }

  /** {@inheritDoc} */
  @Override
  public ChangeRowValueAssert value(int index) {
    return origin.value(index);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeRowValueAssert value(String columnName) {
    return origin.value(columnName);
  }
}
