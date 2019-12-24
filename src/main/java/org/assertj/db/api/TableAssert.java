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

import org.assertj.db.api.assertions.AssertOnExistence;
import org.assertj.db.api.assertions.impl.AssertionsOnTableExistence;
import org.assertj.db.type.Table;

/**
 * Assertion methods for a {@link Table}.
 *
 * @author Régis Pouiller
 *
 */
public class TableAssert
    extends AbstractDbAssert<Table, TableAssert, TableColumnAssert, TableColumnValueAssert, TableRowAssert, TableRowValueAssert>
    implements AssertOnExistence<TableAssert> {

  /**
   * Constructor.
   *
   * @param table Table on which the assertion is.
   */
  TableAssert(Table table) {
    super(table, TableAssert.class, TableColumnAssert.class, TableRowAssert.class);
  }

  /**
   * Verifies that the table exist.
   * <p>
   * Example where the assertion verifies that the table exists:
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).exists();
   * </code></pre>
   *
   * @return {@code this} assertion object.
   */
  @Override
  public TableAssert exists() {
    return AssertionsOnTableExistence.exists(this, info, actual.getName(), actual.getSource(), actual.getDataSource());
  }

  /**
   * Verifies that the table doesn't exist.
   * <p>
   * Example where the assertion verifies that the table doesn't exists:
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).doesNotExists();
   * </code></pre>
   *
   * @return {@code this} assertion object.
   */
  @Override
  public TableAssert doesNotExist() {
    return AssertionsOnTableExistence.doesNotExists(this, info, actual.getName(), actual.getSource(), actual.getDataSource());
  }
}
