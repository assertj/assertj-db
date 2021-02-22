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
package org.assertj.db.output;

import org.assertj.db.navigation.ToValueFromRow;
import org.assertj.db.type.AbstractDbData;
import org.assertj.db.type.Row;
import org.assertj.db.type.Value;

/**
 * Output methods about a value in a {@link Row}.
 *
 * @author Régis Pouiller
 *
 * @param <D> The class of the actual value (an sub-class of {@link AbstractDbData}).
 * @param <A> The class of the original assertion (an sub-class of {@link AbstractDbOutputter}).
 * @param <C> The class of the equivalent row assertion (an sub-class of {@link AbstractColumnOutputter}).
 * @param <CV> The class of the equivalent row assertion on the value (an sub-class of {@link AbstractColumnValueOutputter}
 *          ).
 * @param <R> The class of this assertion (an sub-class of {@link AbstractRowOutputter}).
 * @param <RV> The class of this assertion on the value (an sub-class of {@link AbstractRowValueOutputter}).
 */
public abstract class AbstractRowValueOutputter<D extends AbstractDbData<D>, A extends AbstractDbOutputter<D, A, C, CV, R, RV>, C extends AbstractColumnOutputter<D, A, C, CV, R, RV>, CV extends AbstractColumnValueOutputter<D, A, C, CV, R, RV>, R extends AbstractRowOutputter<D, A, C, CV, R, RV>, RV extends AbstractRowValueOutputter<D, A, C, CV, R, RV>>
        extends AbstractValueOutputter<D, A, R, RV, C, CV, R, RV>
        implements ToValueFromRow<RV> {

  /**
   * Constructor.
   *
   * @param selfType Type of this assertion class : a sub-class of {@code AbstractValueOutputter}.
   * @param origin The assertion of {@link org.assertj.db.navigation.origin.Origin}.
   * @param actualValue The value on which are the assertion methods.
   */
  AbstractRowValueOutputter(Class<RV> selfType, R origin, Value actualValue) {
    super(selfType, origin, actualValue);
  }

  /** {@inheritDoc} */
  @Override
  public RV value(String columnName) {
    return returnToOrigin().value(columnName);
  }

  /**
   * Returns to level of assertion methods on a {@link Row}.
   *
   * @return a object of assertion methods on a {@link Row}.
   */
  public R returnToRow() {
    return returnToOrigin();
  }
}
