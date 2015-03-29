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

import org.assertj.db.api.navigation.ToColumn;
import org.assertj.db.api.navigation.ToColumnFromChange;
import org.assertj.db.api.navigation.ToRowFromChange;
import org.assertj.db.api.origin.OriginWithColumnsAndRowsFromChange;

/**
 * Base class for all assertions with an {@link org.assertj.db.api.origin.Origin}
 * and have {@link org.assertj.db.type.Column}s and {@link org.assertj.db.type.Row}s from a {@link org.assertj.db.type.Change}.
 *
 * @param <E> The "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/anMa4g"
 *            target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *            for more details.
 * @param <O> The type of the assertion class of {@link org.assertj.db.api.origin.Origin}.
 * @author Régis Pouiller
 */
public abstract class AbstractAssertWithOriginWithColumnsAndRowsFromChange<E extends AbstractAssertWithOriginWithColumnsAndRowsFromChange<E, O>, O extends OriginWithColumnsAndRowsFromChange>
        extends AbstractAssertWithOriginWithChanges<E, O>
        implements ToColumn<ChangeColumnAssert>,
                   ToColumnFromChange<ChangeColumnAssert>,
                   ToRowFromChange<ChangeRowAssert> {

  /**
   * Constructor.
   *
   * @param selfType Type of this assertion class : a sub-class of {@code AbstractAssertWithOriginWithColumnsAndRowsFromChange}.
   * @param origin The assertion of {@link org.assertj.db.api.origin.Origin}.
   */
  AbstractAssertWithOriginWithColumnsAndRowsFromChange(Class<E> selfType, O origin) {
    super(selfType, origin);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeRowAssert rowAtStartPoint() {
    return origin.rowAtStartPoint();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeRowAssert rowAtEndPoint() {
    return origin.rowAtEndPoint();
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnAssert column() {
    return origin.column();
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnAssert column(int index) {
    return origin.column(index);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnAssert column(String columnName) {
    return origin.column(columnName);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnAssert columnAmongTheModifiedOnes() {
    return origin.columnAmongTheModifiedOnes();
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnAssert columnAmongTheModifiedOnes(int index) {
    return origin.columnAmongTheModifiedOnes(index);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnAssert columnAmongTheModifiedOnes(String columnName) {
    return origin.columnAmongTheModifiedOnes(columnName);
  }
}
