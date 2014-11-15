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

import org.assertj.db.error.AssertJDBException;
import org.assertj.db.type.AbstractDbData;
import org.assertj.db.type.Column;
import org.assertj.db.type.Row;

/**
 * Interface that represents a assert with {@link Column} and {@link Row}.
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
public interface AssertWithColumnsAndRows<D extends AbstractDbData<D>, A extends AbstractDbAssert<D, A, C, CV, R, RV>, C extends AbstractColumnAssert<D, A, C, CV, R, RV>, CV extends AbstractColumnValueAssert<D, A, C, CV, R, RV>, R extends AbstractRowAssert<D, A, C, CV, R, RV>, RV extends AbstractRowValueAssert<D, A, C, CV, R, RV>> {

  /**
   * Returns assertion methods on the next {@link Row} in the list of {@link Row}.
   * 
   * @return An object to make assertions on the next {@link Row}.
   * @throws AssertJDBException If the {@code index} is out of the bounds.
   */
  public R row();

  /**
   * Returns assertion methods on the {@link Row} at the {@code index} in parameter.
   * 
   * @param index The index corresponding to the {@link Row}.
   * @return An object to make assertions on the {@link Row}.
   * @throws AssertJDBException If the {@code index} is out of the bounds.
   */
  public R row(int index);

  /**
   * Returns assertion methods on the next {@link Column} in the list of {@link Column}.
   * 
   * @return An object to make assertions on the next {@link Column}.
   * @throws AssertJDBException If the {@code index} is out of the bounds.
   */
  public C column();

  /**
   * Returns assertion methods on the {@link Column} at the {@code index} in parameter.
   * 
   * @param index The index corresponding to the {@link Column}.
   * @return An object to make assertions on the {@link Column}.
   * @throws AssertJDBException If the {@code index} is out of the bounds.
   */
  public C column(int index);

  /**
   * Returns assertion methods on the {@link Column} corresponding to the column name in parameter.
   * 
   * @param columnName The column name.
   * @return An object to make assertions on the {@link Column}.
   * @throws NullPointerException If the column name in parameter is null.
   * @throws AssertJDBException If there is no column with this name.
   */
  public C column(String columnName);
}
