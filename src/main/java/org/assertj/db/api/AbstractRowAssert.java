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

import org.assertj.db.api.assertions.AssertOnNumberOfColumns;
import org.assertj.db.api.assertions.AssertOnRowEquality;
import org.assertj.db.api.assertions.impl.AssertionsOnNumberOfColumns;
import org.assertj.db.api.assertions.impl.AssertionsOnRowEquality;
import org.assertj.db.navigation.Position;
import org.assertj.db.navigation.PositionWithColumns;
import org.assertj.db.navigation.ToValueFromRow;
import org.assertj.db.navigation.element.RowElement;
import org.assertj.db.type.AbstractDbData;
import org.assertj.db.type.Row;
import org.assertj.db.type.Value;

import java.util.List;

import static org.assertj.db.util.Descriptions.getRowValueDescription;

/**
 * Base class for all {@link Row}s assertions.
 *
 * @author Régis Pouiller
 * 
 * @param <D> The class of the actual value (an sub-class of {@link AbstractDbData}).
 * @param <A> The class of the original assertion (an sub-class of {@link AbstractDbAssert}).
 * @param <C> The class of the equivalent column assertion (an sub-class of {@link AbstractColumnAssert}).
 * @param <CV> The class of the equivalent column assertion on the value (an sub-class of {@link AbstractColumnValueAssert}
 *          ).
 * @param <R> The class of this assertion (an sub-class of {@link AbstractRowAssert}).
 * @param <RV> The class of this assertion on the value (an sub-class of {@link AbstractRowValueAssert}).
 */
public abstract class AbstractRowAssert<D extends AbstractDbData<D>, A extends AbstractDbAssert<D, A, C, CV, R, RV>, C extends AbstractColumnAssert<D, A, C, CV, R, RV>, CV extends AbstractColumnValueAssert<D, A, C, CV, R, RV>, R extends AbstractRowAssert<D, A, C, CV, R, RV>, RV extends AbstractRowValueAssert<D, A, C, CV, R, RV>>
        extends AbstractSubAssert<D, A, R, RV, C, CV, R, RV>
        implements RowElement,
                   ToValueFromRow<RV>,
                   AssertOnRowEquality<R>,
                   AssertOnNumberOfColumns<R> {

  /**
   * Position of navigation to value.
   */
  private final PositionWithColumns<R, RV, Value> valuePosition;

  /**
   * Row on which do the assertion.
   */
  private final Row row;

  /**
   * Constructor.
   * 
   * @param originalDbAssert The original assert. That could be a {@link RequestAssert} or a {@link TableAssert}.
   * @param selfType Type of this assertion class : a sub-class of {@code AbstractRowAssert}.
   * @param valueType Class of the assert on the value : a sub-class of {@code AbstractRowValueAssert}.
   */
  AbstractRowAssert(A originalDbAssert, Class<R> selfType, Class<RV> valueType, Row row) {
    super(originalDbAssert, selfType);
    this.row = row;
    valuePosition = new PositionWithColumns<R, RV, Value>(selfType.cast(this), valueType) {
      @Override protected String getDescription(int index) {
        return getValueDescription(index);
      }
    };
  }

  /** {@inheritDoc} */
  @Override
  protected String getValueDescription(int index) {
    List<String> columnsNameList = row.getColumnsNameList();
    String columnName = columnsNameList.get(index);
    return getRowValueDescription(info, index, columnName);
  }

  /** {@inheritDoc} */
  @Override
  protected Position<R, RV, Value> getValuePosition() {
    return valuePosition;
  }

  /** {@inheritDoc} */
  @Override
  protected List<Value> getValuesList() {
    return row.getValuesList();
  }

  /** {@inheritDoc} */
  @Override
  public RV value(String columnName) {
    return valuePosition.getInstance(getValuesList(), row.getColumnsNameList(), columnName, row.getColumnLetterCase());
  }

  /** {@inheritDoc} */
  @Override
  public R hasNumberOfColumns(int expected) {
    return AssertionsOnNumberOfColumns.hasNumberOfColumns(myself, info, getValuesList().size(), expected);
  }

  /** {@inheritDoc} */
  @Override
  public R hasNumberOfColumnsGreaterThan(int expected) {
    return AssertionsOnNumberOfColumns.hasNumberOfColumnsGreaterThan(myself, info, getValuesList().size(), expected);
  }

  /** {@inheritDoc} */
  @Override
  public R hasNumberOfColumnsLessThan(int expected) {
    return AssertionsOnNumberOfColumns.hasNumberOfColumnsLessThan(myself, info, getValuesList().size(), expected);
  }

  /** {@inheritDoc} */
  @Override
  public R hasNumberOfColumnsGreaterThanOrEqualTo(int expected) {
    return AssertionsOnNumberOfColumns.hasNumberOfColumnsGreaterThanOrEqualTo(myself, info, getValuesList().size(),
                                                                              expected);
  }

  /** {@inheritDoc} */
  @Override
  public R hasNumberOfColumnsLessThanOrEqualTo(int expected) {
    return AssertionsOnNumberOfColumns.hasNumberOfColumnsLessThanOrEqualTo(myself, info, getValuesList().size(), expected);
  }

  /** {@inheritDoc} */
  @Override
  public R hasValues(Object... expected) {
    return AssertionsOnRowEquality.hasValues(myself, info, getValuesList(), expected);
  }
}
