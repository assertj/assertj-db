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
 * Copyright 2012-2015 the original author or authors.
 */
package org.assertj.db.api;

import org.assertj.db.api.assertions.AssertOnColumnName;
import org.assertj.db.api.assertions.impl.AssertionsOnColumnName;
import org.assertj.db.api.navigation.ToValueFromRow;
import org.assertj.db.type.AbstractDbData;
import org.assertj.db.type.Row;

/**
 * Assertion methods about a value in a {@link Row}.
 * 
 * @author Régis Pouiller
 * 
 * @param <D> The class of the actual value (an sub-class of {@link AbstractDbData}).
 * @param <A> The class of the original assertion (an sub-class of {@link AbstractDbAssert}).
 * @param <C> The class of the equivalent row assertion (an sub-class of {@link AbstractColumnAssert}).
 * @param <CV> The class of the equivalent row assertion on the value (an sub-class of {@link AbstractColumnValueAssert}
 *          ).
 * @param <R> The class of this assertion (an sub-class of {@link AbstractRowAssert}).
 * @param <RV> The class of this assertion on the value (an sub-class of {@link AbstractRowValueAssert}).
 */
public abstract class AbstractRowValueAssert<D extends AbstractDbData<D>, A extends AbstractDbAssert<D, A, C, CV, R, RV>, C extends AbstractColumnAssert<D, A, C, CV, R, RV>, CV extends AbstractColumnValueAssert<D, A, C, CV, R, RV>, R extends AbstractRowAssert<D, A, C, CV, R, RV>, RV extends AbstractRowValueAssert<D, A, C, CV, R, RV>>
        extends AbstractValueAssert<D, A, R, RV, C, CV, R, RV>
        implements ToValueFromRow<RV>,
                   AssertOnColumnName<RV> {

  /**
   * The name of the column.
   */
  private final String columnName;

  /**
   * Constructor.
   * 
   * @param selfType Type of this assertion class : a sub-class of {@code AbstractValueAssert}.
   * @param origin The assertion of {@link org.assertj.db.api.origin.Origin}.
   * @param columnName The column name.
   * @param actualValue The value on which are the assertion methods.
   */
  AbstractRowValueAssert(Class<RV> selfType, R origin, String columnName, Object actualValue) {
    super(selfType, origin, actualValue);
    this.columnName = columnName;
  }

  /** {@inheritDoc} */
  @Override
  public RV value(String columnName) {
    return returnToOrigin().value(columnName);
  }

  /** {@inheritDoc} */
  @Override
  public RV hasColumnName(String columnName) {
    return AssertionsOnColumnName.hasColumnName(myself, info, this.columnName, columnName);
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
