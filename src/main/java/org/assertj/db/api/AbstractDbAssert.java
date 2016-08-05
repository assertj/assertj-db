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
 * Copyright 2012-2016 the original author or authors.
 */
package org.assertj.db.api;

import org.assertj.db.api.assertions.AssertOnNumberOfColumns;
import org.assertj.db.api.assertions.AssertOnNumberOfRows;
import org.assertj.db.api.assertions.impl.AssertionsOnNumberOfColumns;
import org.assertj.db.api.assertions.impl.AssertionsOnNumberOfRows;
import org.assertj.db.navigation.Position;
import org.assertj.db.navigation.PositionWithColumns;
import org.assertj.db.navigation.origin.OriginWithColumnsAndRows;
import org.assertj.db.type.AbstractDbData;
import org.assertj.db.type.Column;
import org.assertj.db.type.Row;

import java.util.List;

import static org.assertj.db.util.Descriptions.getColumnDescription;
import static org.assertj.db.util.Descriptions.getRowDescription;

/**
 * Base class for all data ({@code Table} or {@code Request}) assertions.
 *
 * @author Régis Pouiller
 * 
 * @param <D> The class of the actual value (an sub-class of {@link AbstractDbData}).
 * @param <A> The class of the original assert (an sub-class of {@link AbstractDbAssert}).
 * @param <C> The class of this assert (an sub-class of {@link AbstractColumnAssert}).
 * @param <CV> The class of this assertion on the value (an sub-class of {@link AbstractColumnValueAssert}).
 * @param <R> The class of the equivalent row assert (an sub-class of {@link AbstractRowAssert}).
 * @param <RV> The class of the equivalent row assertion on the value (an sub-class of {@link AbstractRowValueAssert}).
 */
public abstract class AbstractDbAssert<D extends AbstractDbData<D>, A extends AbstractDbAssert<D, A, C, CV, R, RV>, C extends AbstractColumnAssert<D, A, C, CV, R, RV>, CV extends AbstractColumnValueAssert<D, A, C, CV, R, RV>, R extends AbstractRowAssert<D, A, C, CV, R, RV>, RV extends AbstractRowValueAssert<D, A, C, CV, R, RV>>
        extends AbstractAssert<A>
        implements OriginWithColumnsAndRows<C, R>,
                   AssertOnNumberOfColumns<A>,
                   AssertOnNumberOfRows<A> {

  /**
   * The actual value on which the assertion is.
   */
  protected final D actual;

  /**
   * Position of navigation to column.
   */
  private final PositionWithColumns<A, C, Column> columnPosition;
  /**
   * Position of navigation to row.
   */
  private final Position<A, R, Row> rowPosition;

  /**
   * Constructor of the database assertions.
   * 
   * @param actualValue The actual value on which the assertion is.
   * @param selfType Type of this assertion class.
   * @param columnAssertType Class of the assertion on the column.
   * @param rowAssertType Class of the assertion on the row.
   */
  AbstractDbAssert(D actualValue, Class<A> selfType, Class<C> columnAssertType, Class<R> rowAssertType) {
    super(selfType);
    actual = actualValue;
    columnPosition = new PositionWithColumns<A, C, Column>(selfType.cast(this), columnAssertType) {
      @Override protected String getDescription(int index) {
        List<String> columnsNameList = actual.getColumnsNameList();
        String columnName = columnsNameList.get(index);
        return getColumnDescription(myself.getInfo(), index, columnName);
      }
    };
    rowPosition = new Position<A, R, Row>(selfType.cast(this), rowAssertType) {
      @Override protected String getDescription(int index) {
        return getRowDescription(myself.getInfo(), index);
      }
    };
  }

  /** {@inheritDoc} */
  @Override
  public R row() {
    return rowPosition.getInstance(actual.getRowsList());
  }

  /** {@inheritDoc} */
  @Override
  public R row(int index) {
    return rowPosition.getInstance(actual.getRowsList(), index);
  }

  /** {@inheritDoc} */
  @Override
  public C column() {
    return columnPosition.getInstance(actual.getColumnsList());
  }

  /** {@inheritDoc} */
  @Override
  public C column(int index) {
    return columnPosition.getInstance(actual.getColumnsList(), index);
  }

  /** {@inheritDoc} */
  @Override
  public C column(String columnName) {
    return columnPosition.getInstance(actual.getColumnsList(), actual.getColumnsNameList(), columnName, actual.getColumnLetterCase());
  }

  /** {@inheritDoc} */
  @Override
  public A isEmpty() {
    return hasNumberOfRows(0);
  }

  /** {@inheritDoc} */
  @Override
  public A hasNumberOfRows(int expected) {
    List<Row> rowsList = actual.getRowsList();
    int size = rowsList.size();
    return AssertionsOnNumberOfRows.hasNumberOfRows(myself, info, size, expected);
  }

  /** {@inheritDoc} */
  @Override
  public A hasNumberOfRowsGreaterThan(int expected) {
    List<Row> rowsList = actual.getRowsList();
    int size = rowsList.size();
    return AssertionsOnNumberOfRows.hasNumberOfRowsGreaterThan(myself, info, size, expected);
  }

  /** {@inheritDoc} */
  @Override
  public A hasNumberOfRowsLessThan(int expected) {
    List<Row> rowsList = actual.getRowsList();
    int size = rowsList.size();
    return AssertionsOnNumberOfRows.hasNumberOfRowsLessThan(myself, info, size, expected);
  }

  /** {@inheritDoc} */
  @Override
  public A hasNumberOfRowsGreaterThanOrEqualTo(int expected) {
    List<Row> rowsList = actual.getRowsList();
    int size = rowsList.size();
    return AssertionsOnNumberOfRows.hasNumberOfRowsGreaterThanOrEqualTo(myself, info, size, expected);
  }

  /** {@inheritDoc} */
  @Override
  public A hasNumberOfRowsLessThanOrEqualTo(int expected) {
    List<Row> rowsList = actual.getRowsList();
    int size = rowsList.size();
    return AssertionsOnNumberOfRows.hasNumberOfRowsLessThanOrEqualTo(myself, info, size, expected);
  }

  /** {@inheritDoc} */
  @Override
  public A hasNumberOfColumns(int expected) {
    List<String> columnsNameList = actual.getColumnsNameList();
    int size = columnsNameList.size();
    return AssertionsOnNumberOfColumns.hasNumberOfColumns(myself, info, size, expected);
  }

  /** {@inheritDoc} */
  @Override
  public A hasNumberOfColumnsGreaterThan(int expected) {
    List<String> columnsNameList = actual.getColumnsNameList();
    int size = columnsNameList.size();
    return AssertionsOnNumberOfColumns.hasNumberOfColumnsGreaterThan(myself, info, size, expected);
  }

  /** {@inheritDoc} */
  @Override
  public A hasNumberOfColumnsLessThan(int expected) {
    List<String> columnsNameList = actual.getColumnsNameList();
    int size = columnsNameList.size();
    return AssertionsOnNumberOfColumns.hasNumberOfColumnsLessThan(myself, info, size, expected);
  }

  /** {@inheritDoc} */
  @Override
  public A hasNumberOfColumnsGreaterThanOrEqualTo(int expected) {
    List<String> columnsNameList = actual.getColumnsNameList();
    int size = columnsNameList.size();
    return AssertionsOnNumberOfColumns.hasNumberOfColumnsGreaterThanOrEqualTo(myself, info, size, expected);
  }

  /** {@inheritDoc} */
  @Override
  public A hasNumberOfColumnsLessThanOrEqualTo(int expected) {
    List<String> columnsNameList = actual.getColumnsNameList();
    int size = columnsNameList.size();
    return AssertionsOnNumberOfColumns.hasNumberOfColumnsLessThanOrEqualTo(myself, info, size, expected);
  }
}
