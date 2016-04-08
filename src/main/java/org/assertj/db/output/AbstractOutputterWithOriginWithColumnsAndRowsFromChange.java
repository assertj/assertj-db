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
package org.assertj.db.output;

import org.assertj.db.navigation.ToColumn;
import org.assertj.db.navigation.ToColumnFromChange;
import org.assertj.db.navigation.ToRowFromChange;
import org.assertj.db.navigation.origin.OriginWithColumnsAndRowsFromChange;

/**
 * Base class for all outputs with an {@link org.assertj.db.navigation.origin.Origin}
 * and have {@link org.assertj.db.type.Column}s and {@link org.assertj.db.type.Row}s from a {@link org.assertj.db.type.Change}.
 *
 * @param <E> The "self" type of this output class. Please read &quot;<a href="http://bit.ly/1IZIRcY"
 *            target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *            for more details.
 * @param <O> The type of the output class of {@link org.assertj.db.navigation.origin.Origin}.
 * @author Régis Pouiller
 */
public abstract class AbstractOutputterWithOriginWithColumnsAndRowsFromChange<E extends AbstractOutputterWithOriginWithColumnsAndRowsFromChange<E, O>, O extends OriginWithColumnsAndRowsFromChange<ChangesOutputter, ChangeOutputter, ChangeColumnOutputter, ChangeRowOutputter>>
        extends AbstractOutputterWithOriginWithChanges<E, O>
        implements ToColumn<ChangeColumnOutputter>,
        ToColumnFromChange<ChangeColumnOutputter>,
        ToRowFromChange<ChangeRowOutputter> {

  /**
   * Constructor.
   *
   * @param selfType Type of this output class : a sub-class of {@code AbstractOutputterWithOriginWithColumnsAndRowsFromChange}.
   * @param origin The output of {@link org.assertj.db.navigation.origin.Origin}.
   */
  AbstractOutputterWithOriginWithColumnsAndRowsFromChange(Class<E> selfType, O origin) {
    super(selfType, origin);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeRowOutputter rowAtStartPoint() {
    return origin.rowAtStartPoint();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeRowOutputter rowAtEndPoint() {
    return origin.rowAtEndPoint();
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnOutputter column() {
    return origin.column();
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnOutputter column(int index) {
    return origin.column(index);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnOutputter column(String columnName) {
    return origin.column(columnName);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnOutputter columnAmongTheModifiedOnes() {
    return origin.columnAmongTheModifiedOnes();
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnOutputter columnAmongTheModifiedOnes(int index) {
    return origin.columnAmongTheModifiedOnes(index);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnOutputter columnAmongTheModifiedOnes(String columnName) {
    return origin.columnAmongTheModifiedOnes(columnName);
  }
}
