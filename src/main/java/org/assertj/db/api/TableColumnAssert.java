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
package org.assertj.db.api;

import org.assertj.db.type.Column;
import org.assertj.db.type.Table;

/**
 * Assertion methods for a {@link Column} of a {@link Table}.
 *
 * @author Régis Pouiller
 */
public class TableColumnAssert
  extends AbstractColumnAssert<Table, TableAssert, TableColumnAssert, TableColumnValueAssert, TableRowAssert, TableRowValueAssert> {

  /**
   * Constructor.
   *
   * @param origin The assertion of {@link org.assertj.db.navigation.origin.Origin}.
   * @param column The column on which do assertion.
   */
  public TableColumnAssert(TableAssert origin, Column column) {
    super(origin, TableColumnAssert.class, TableColumnValueAssert.class, column);
  }

  /**
   * Returns to level of assertion methods on a {@link Table}.
   *
   * @return a object of assertion methods on a {@link Table}.
   */
  public TableAssert returnToTable() {
    return returnToOrigin();
  }
}
