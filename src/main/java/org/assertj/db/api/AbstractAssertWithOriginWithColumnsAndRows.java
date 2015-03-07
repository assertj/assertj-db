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

import org.assertj.db.api.navigation.WithColumns;
import org.assertj.db.api.navigation.WithRows;
import org.assertj.db.api.origin.OriginWithColumnsAndRows;
import org.assertj.db.type.AbstractDbData;

/**
 * Abstract class that represents a assert with an origin with columns and rows (like {@link AbstractValueAssert} with
 * {@link AbstractSubAssert}).
 *
 * @param <E>  The "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/anMa4g"
 *             target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *             for more details.
 * @param <O>  The class of the assert of origin
 * @param <D>  The class of the actual value (an sub-class of {@link AbstractDbData}).
 * @param <A>  The class of the original assert (an sub-class of {@link AbstractDbAssert}).
 * @param <C>  The class of this assert (an sub-class of {@link AbstractColumnAssert}).
 * @param <CV> The class of this assertion on the value (an sub-class of {@link AbstractColumnValueAssert}).
 * @param <R>  The class of the equivalent row assert (an sub-class of {@link AbstractRowAssert}).
 * @param <RV> The class of the equivalent row assertion on the value (an sub-class of {@link AbstractRowValueAssert}).
 * @author Régis Pouiller
 */
public abstract class AbstractAssertWithOriginWithColumnsAndRows<E extends AbstractAssertWithOriginWithColumnsAndRows<E, O, D, A, C, CV, R, RV>, O extends OriginWithColumnsAndRows<C, R>, D extends AbstractDbData<D>, A extends AbstractDbAssert<D, A, C, CV, R, RV>, C extends AbstractColumnAssert<D, A, C, CV, R, RV>, CV extends AbstractColumnValueAssert<D, A, C, CV, R, RV>, R extends AbstractRowAssert<D, A, C, CV, R, RV>, RV extends AbstractRowValueAssert<D, A, C, CV, R, RV>>
        extends AbstractAssertWithOrigin<E, O> implements WithColumns<C>, WithRows<R> {

  /**
   * Constructor.
   *
   * @param selfType     Class of this assert : a sub-class of {@code AbstractAssertWithOriginWithColumnsAndRows}.
   * @param originAssert The assert of origin.
   */
  AbstractAssertWithOriginWithColumnsAndRows(Class<E> selfType, O originAssert) {
    super(selfType, originAssert);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public R row() {
    return returnToOrigin().row();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public R row(int index) {
    return returnToOrigin().row(index);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public C column() {
    return returnToOrigin().column();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public C column(int index) {
    return returnToOrigin().column(index);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public C column(String columnName) {
    return returnToOrigin().column(columnName);
  }

}
