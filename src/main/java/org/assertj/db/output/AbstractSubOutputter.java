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
package org.assertj.db.output;

import org.assertj.db.api.RequestAssert;
import org.assertj.db.api.TableAssert;
import org.assertj.db.navigation.Position;
import org.assertj.db.navigation.ToValue;
import org.assertj.db.navigation.origin.OriginWithColumnsAndRows;
import org.assertj.db.type.AbstractDbData;
import org.assertj.db.type.Column;
import org.assertj.db.type.Row;
import org.assertj.db.type.Value;

import java.util.List;

/**
 * Output methods about {@link Column} or {@link Row}.
 *
 * @author Régis Pouiller
 *
 * @param <D> The class of the actual value (an sub-class of {@link AbstractDbData}).
 * @param <A> The class of the original assertion (an sub-class of {@link AbstractDbOutputter}).
 * @param <S> The class of this assertion (an sub-class of {@link AbstractSubOutputter}).
 * @param <V> The class of this assertion on the value (an sub-class of {@link AbstractValueOutputter}).
 * @param <C> The class of this assertion (an sub-class of {@link AbstractColumnOutputter}).
 * @param <CV> The class of this assertion on the value (an sub-class of {@link AbstractColumnValueOutputter}).
 * @param <R> The class of the equivalent row assertion (an sub-class of {@link AbstractRowOutputter}).
 * @param <RV> The class of the equivalent row assertion on the value (an sub-class of {@link AbstractRowValueOutputter}).
 */
public abstract class AbstractSubOutputter<D extends AbstractDbData<D>, A extends AbstractDbOutputter<D, A, C, CV, R, RV>, S extends AbstractSubOutputter<D, A, S, V, C, CV, R, RV>, V extends AbstractValueOutputter<D, A, S, V, C, CV, R, RV>, C extends AbstractColumnOutputter<D, A, C, CV, R, RV>, CV extends AbstractColumnValueOutputter<D, A, C, CV, R, RV>, R extends AbstractRowOutputter<D, A, C, CV, R, RV>, RV extends AbstractRowValueOutputter<D, A, C, CV, R, RV>>
        extends AbstractOutputterWithOriginWithColumnsAndRows<S, A, D, A, C, CV, R, RV>
        implements OriginWithColumnsAndRows<C, R>,
        ToValue<V> {

  /**
   * Constructor.
   *
   * @param originalDbAssert The original assert. That could be a {@link RequestAssert} or a {@link TableAssert}.
   * @param selfType Type of this assertion class : a sub-class of {@code AbstractSubAssert}.
   */
  AbstractSubOutputter(A originalDbAssert, Class<S> selfType) {
    super(selfType, originalDbAssert);
  }

  /**
   * Returns the value description.
   * @param index The index of the value.
   * @return The description.
   */
  protected abstract String getValueDescription(int index);

  /** {@inheritDoc} */
  @Override
  public V value() {
    return getValuePosition().getInstance(getValuesList()).withType(outputType);
  }

  /** {@inheritDoc} */
  @Override
  public V value(int index) {
    return getValuePosition().getInstance(getValuesList(), index).withType(outputType);
  }

  /**
   * Returns the position of navigation to value.
   * @return The position of navigation to value.
   */
  protected abstract Position<S, V, Value> getValuePosition();

  /**
   * Returns the list of values.
   *
   * @return The list of values.
   */
  protected abstract List<Value> getValuesList();
}
