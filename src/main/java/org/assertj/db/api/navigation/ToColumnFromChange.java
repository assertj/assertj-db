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
package org.assertj.db.api.navigation;

import org.assertj.db.api.ChangeColumnAssert;

/**
 * Defines methods to navigate to a {@link org.assertj.db.type.Column} from a {@link org.assertj.db.type.Change}.
 * <p>The different methods return an assertion on one column {@link org.assertj.db.api.navigation.ColumnAssert}.</p>
 *
 * @author Régis Pouiller
 *
 * @param <C> The class of a assertion on a column (an sub-class of {@link org.assertj.db.api.navigation.ColumnAssert}).
 */
public interface ToColumnFromChange<C extends ColumnAssert> {

  /**
   * Returns assertion methods on the next {@link ChangeColumnAssert} in the list of the modified columns.
   *
   * @return An object to make assertions on the {@link ChangeColumnAssert}.
   * @throws org.assertj.db.exception.AssertJDBException If the {@code index} is out of the bounds.
   */
  public C columnAmongTheModifiedOnes();

  /**
   * Returns assertion methods on the {@link ChangeColumnAssert} at the {@code index} in parameter among the modified columns.
   *
   * @param index The index corresponding to the {@link ChangeColumnAssert}.
   * @return An object to make assertions on the {@link ChangeColumnAssert}.
   * @throws org.assertj.db.exception.AssertJDBException If the {@code index} is out of the bounds.
   */
  public C columnAmongTheModifiedOnes(int index);

  /**
   * Returns assertion methods on the {@link ChangeColumnAssert} corresponding to the column name in parameter among the modified columns.
   *
   * @param columnName The column name.
   * @return An object to make assertions on the {@link ChangeColumnAssert}.
   * @throws NullPointerException                        If the column name in parameter is null.
   * @throws org.assertj.db.exception.AssertJDBException If there is no column with this name.
   */
  public C columnAmongTheModifiedOnes(String columnName);
}
