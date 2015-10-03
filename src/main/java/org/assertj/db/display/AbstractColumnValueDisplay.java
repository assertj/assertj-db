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
package org.assertj.db.display;

import org.assertj.db.type.AbstractDbData;
import org.assertj.db.type.Column;
import org.assertj.db.type.Value;

/**
 * Display methods about a value in a {@link Column}.
 *
 * @author Régis Pouiller
 *
 * @param <D> The class of the actual value (an sub-class of {@link AbstractDbData}).
 * @param <A> The class of the original assertion (an sub-class of {@link AbstractDbDisplay}).
 * @param <C> The class of this assertion (an sub-class of {@link AbstractColumnDisplay}).
 * @param <CV> The class of this assertion on the value (an sub-class of {@link AbstractColumnValueDisplay}).
 * @param <R> The class of the equivalent row assertion (an sub-class of {@link AbstractRowDisplay}).
 * @param <RV> The class of the equivalent row assertion on the value (an sub-class of {@link AbstractRowValueDisplay}).
 */
public abstract class AbstractColumnValueDisplay<D extends AbstractDbData<D>, A extends AbstractDbDisplay<D, A, C, CV, R, RV>, C extends AbstractColumnDisplay<D, A, C, CV, R, RV>, CV extends AbstractColumnValueDisplay<D, A, C, CV, R, RV>, R extends AbstractRowDisplay<D, A, C, CV, R, RV>, RV extends AbstractRowValueDisplay<D, A, C, CV, R, RV>>
        extends AbstractValueDisplay<D, A, C, CV, C, CV, R, RV> {

  /**
   * Constructor.
   *
   * @param selfType    Type of this assertion class : a sub-class of {@code AbstractColumnValueDisplay}.
   * @param origin      The assertion of {@link org.assertj.db.navigation.origin.Origin}.
   * @param actualValue The value on which are the assertion methods.
   */
  AbstractColumnValueDisplay(Class<CV> selfType, C origin, Value actualValue) {
    super(selfType, origin, actualValue);
  }

  /**
   * Returns to level of assertion methods on a {@link Column}.
   *
   * @return a object of assertion methods on a {@link Column}.
   */
  public C returnToColumn() {
    return returnToOrigin();
  }
}