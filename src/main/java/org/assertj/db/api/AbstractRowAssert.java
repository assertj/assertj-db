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

import org.assertj.db.api.assertions.AssertOnRow;
import org.assertj.db.exception.AssertJDBException;
import org.assertj.db.type.AbstractDbData;
import org.assertj.db.type.Row;
import org.assertj.db.util.AssertionsOnRow;

import java.util.List;

/**
 * Assertion methods about the data in a <code>{@link Row}</code>.
 * 
 * @author Régis Pouiller
 * 
 * @param <D> The class of the actual value (an sub-class of {@link AbstractDbData}).
 * @param <A> The class of the original assert (an sub-class of {@link AbstractDbAssert}).
 * @param <C> The class of the equivalent row assert (an sub-class of {@link AbstractColumnAssert}).
 * @param <CV> The class of the equivalent row assertion on the value (an sub-class of {@link AbstractColumnValueAssert}
 *          ).
 * @param <R> The class of this assert (an sub-class of {@link AbstractRowAssert}).
 * @param <RV> The class of this assertion on the value (an sub-class of {@link AbstractRowValueAssert}).
 */
public abstract class AbstractRowAssert<D extends AbstractDbData<D>, A extends AbstractDbAssert<D, A, C, CV, R, RV>, C extends AbstractColumnAssert<D, A, C, CV, R, RV>, CV extends AbstractColumnValueAssert<D, A, C, CV, R, RV>, R extends AbstractRowAssert<D, A, C, CV, R, RV>, RV extends AbstractRowValueAssert<D, A, C, CV, R, RV>>
    extends AbstractSubAssert<D, A, R, RV, C, CV, R, RV> implements AssertOnRow<R> {

  /**
   * Row on which do the assertion.
   */
  private Row row;

  /**
   * Constructor.
   * 
   * @param originalDbAssert The original assert. That could be a {@link RequestAssert} or a {@link TableAssert}.
   * @param selfType Class of this assert (the sub assert) : a sub-class of {@code AbstractRowAssert}.
   * @param valueType Class of the assert on the value : a sub-class of {@code AbstractRowValueAssert}.
   */
  AbstractRowAssert(A originalDbAssert, Class<R> selfType, Class<RV> valueType, Row row) {
    super(originalDbAssert, selfType, valueType);
    this.row = row;
  }

  /** {@inheritDoc} */
  @Override
  protected List<Object> getValuesList() {
    return row.getValuesList();
  }

  /**
   * Returns assertion methods on the value corresponding to the column name in parameter.
   * 
   * @param columnName The column name.
   * @return An object to make assertions on the value.
   * @throws NullPointerException If the column name in parameter is null.
   * @throws AssertJDBException If there is no column with this name.
   */
  public RV value(String columnName) {
    if (columnName == null) {
      throw new NullPointerException("Column name must be not null");
    }
    List<String> columnsNameList = row.getColumnsNameList();
    int index = columnsNameList.indexOf(columnName.toUpperCase());
    if (index == -1) {
      throw new AssertJDBException("Column <%s> does not exist", columnName);
    }
    return getValueAssertInstance(index);
  }

  /**
   * Verifies that the size of a {@link Row} is equal to the number in parameter.
   * <p>
   * Example where the assertion verifies that the first row of the table has 8 columns :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).row().hasNumberOfColumns(8);
   * </code></pre>
   *
   * @param expected The number to compare to the size.
   * @return {@code this} assertion object.
   * @throws AssertionError If the size is different to the number in parameter.
   */
  public R hasNumberOfColumns(int expected) {
    return AssertionsOnRow.hasSize(myself, info, getValuesList().size(), expected);
  }

  /**
   * Verifies that the values of a column are equal to values in parameter.
   * <p>
   * Example where the assertion verifies that the values in the first {@code Row} of the {@code Table} are equal to the
   * values in parameter :
   * </p>
   * 
   * <pre><code class='java'>
   * assertThat(table).row().hasValuesEqualTo(1, &quot;Text&quot;, TimeValue.of(9, 1));
   * </code></pre>
   * 
   * @param expected The expected values.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not equal to the values in parameter.
   */
  public R hasValuesEqualTo(Object... expected) {
    return AssertionsOnRow.hasValuesEqualTo(myself, info, getValuesList(), expected);
  }
}
