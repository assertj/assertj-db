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
 * Copyright 2015-2024 the original author or authors.
 */
package org.assertj.db.output;

import org.assertj.db.navigation.Position;
import org.assertj.db.navigation.element.ColumnElement;
import org.assertj.db.output.impl.Output;
import org.assertj.db.type.AbstractDbData;
import org.assertj.db.type.Column;
import org.assertj.db.type.Value;

import java.util.List;

import static org.assertj.db.util.Descriptions.getColumnValueDescription;

/**
 * Base class for all {@link Column}s outputs.
 *
 * @author Régis Pouiller
 *
 * @param <D> The class of the actual value (an sub-class of {@link AbstractDbData}).
 * @param <A> The class of the original assertion (an sub-class of {@link AbstractDbOutputter}).
 * @param <C> The class of this assertion (an sub-class of {@link AbstractColumnOutputter}).
 * @param <CV> The class of this assertion on the value (an sub-class of {@link AbstractColumnValueOutputter}).
 * @param <R> The class of the equivalent row assertion (an sub-class of {@link AbstractRowOutputter}).
 * @param <RV> The class of the equivalent row assertion on the value (an sub-class of {@link AbstractRowValueOutputter}).
 */
public abstract class AbstractColumnOutputter<D extends AbstractDbData<D>, A extends AbstractDbOutputter<D, A, C, CV, R, RV>, C extends AbstractColumnOutputter<D, A, C, CV, R, RV>, CV extends AbstractColumnValueOutputter<D, A, C, CV, R, RV>, R extends AbstractRowOutputter<D, A, C, CV, R, RV>, RV extends AbstractRowValueOutputter<D, A, C, CV, R, RV>>
        extends AbstractSubOutputter<D, A, C, CV, C, CV, R, RV>
        implements ColumnElement {

  /**
   * Position of navigation to value.
   */
  protected final Position<C, CV, Value> valuePosition;

  /**
   * Column on which do the assertion.
   */
  private final Column column;

  /**
   * Constructor.
   *
   * @param originalDbOutputter The original assert. That could be a {@link RequestOutputter} or a {@link TableOutputter}.
   * @param selfType Type of this assertion class : a sub-class of {@code AbstractColumnOutputter}.
   * @param valueType Class of the assert on the value : a sub-class of {@code AbstractColumnValueOutputter}.
   */
  AbstractColumnOutputter(A originalDbOutputter, Class<C> selfType, Class<CV> valueType, Column column) {
    super(originalDbOutputter, selfType);
    this.column = column;
    valuePosition = new Position<C, CV, Value>(selfType.cast(this), valueType) {
      @Override protected String getDescription(int index) {
        return getValueDescription(index);
      }
    };
  }

  /** {@inheritDoc} */
  @Override
  protected String getValueDescription(int index) {
    return getColumnValueDescription(info, index);
  }

  /** {@inheritDoc} */
  @Override
  protected Position<C, CV, Value> getValuePosition() {
    return valuePosition;
  }

  /** {@inheritDoc} */
  @Override
  protected List<Value> getValuesList() {
    return column.getValuesList();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getOutput(Output outputType) {
    return outputType.getColumnOutput(info, column);
  }
}
