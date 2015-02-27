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

import org.assertj.db.api.origin.OriginAssertWithColumnsAndRowsFromChange;

/**
 * Abstract class that represents a assert with an origin assert and which is the origin assert of another assert and have rows.
 *
 * @param <E> The "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/anMa4g"
 *            target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *            for more details.
 * @param <O> The class of the assert of origin
 * @author Régis Pouiller
 */
public abstract class AbstractAssertWithColumnsAndRowsFromChange<E extends AbstractAssertWithColumnsAndRowsFromChange<E, O>, O extends OriginAssertWithColumnsAndRowsFromChange>
        extends AbstractAssertWithChanges<E, O> implements OriginAssertWithColumnsAndRowsFromChange {

  /**
   * Constructor.
   *
   * @param selfType     Class of this assert : a sub-class of {@code AbstractAssertWithRows}.
   * @param originAssert The assert of origin.
   */
  AbstractAssertWithColumnsAndRowsFromChange(Class<E> selfType, O originAssert) {
    super(selfType, originAssert);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeRowAssert rowAtStartPoint() {
    return originAssert.rowAtStartPoint();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeRowAssert rowAtEndPoint() {
    return originAssert.rowAtEndPoint();
  }

  /**
   * Returns assertion methods on the next {@link ChangeColumnAssert} in the list of {@link ChangeColumnAssert}.
   *
   * @return An object to make assertions on the next {@link ChangeColumnAssert}.
   * @throws org.assertj.db.exception.AssertJDBException If the {@code index} is out of the bounds.
   */
  public ChangeColumnAssert column() {
    return originAssert.column();
  }

  /**
   * Returns assertion methods on the {@link ChangeColumnAssert} at the {@code index} in parameter.
   *
   * @param index The index corresponding to the {@link ChangeColumnAssert}.
   * @return An object to make assertions on the {@link ChangeColumnAssert}.
   * @throws org.assertj.db.exception.AssertJDBException If the {@code index} is out of the bounds.
   */
  public ChangeColumnAssert column(int index) {
    return originAssert.column(index);
  }

  /**
   * Returns assertion methods on the {@link ChangeColumnAssert} corresponding to the column name in parameter.
   *
   * @param columnName The column name.
   * @return An object to make assertions on the {@link ChangeColumnAssert}.
   * @throws NullPointerException If the column name in parameter is null.
   * @throws org.assertj.db.exception.AssertJDBException If there is no column with this name.
   */
  public ChangeColumnAssert column(String columnName) {
    return originAssert.column(columnName);
  }

  /**
   * Returns assertion methods on the next {@link ChangeColumnAssert} in the list of the modified columns.
   *
   * @return An object to make assertions on the {@link ChangeColumnAssert}.
   * @throws org.assertj.db.exception.AssertJDBException If the {@code index} is out of the bounds.
   */
  public ChangeColumnAssert columnAmongTheModifiedOnes() {
    return originAssert.columnAmongTheModifiedOnes();
  }

  /**
   * Returns assertion methods on the {@link ChangeColumnAssert} at the {@code index} in parameter among the modified columns.
   *
   * @param index The index corresponding to the {@link ChangeColumnAssert}.
   * @return An object to make assertions on the {@link ChangeColumnAssert}.
   * @throws org.assertj.db.exception.AssertJDBException If the {@code index} is out of the bounds.
   */
  public ChangeColumnAssert columnAmongTheModifiedOnes(int index) {
    return originAssert.columnAmongTheModifiedOnes(index);
  }

  /**
   * Returns assertion methods on the {@link ChangeColumnAssert} corresponding to the column name in parameter among the modified columns.
   *
   * @param columnName The column name.
   * @return An object to make assertions on the {@link ChangeColumnAssert}.
   * @throws NullPointerException                        If the column name in parameter is null.
   * @throws org.assertj.db.exception.AssertJDBException If there is no column with this name.
   */
  public ChangeColumnAssert columnAmongTheModifiedOnes(String columnName) {
    return originAssert.columnAmongTheModifiedOnes(columnName);
  }
}
