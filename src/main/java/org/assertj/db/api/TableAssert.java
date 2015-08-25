/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 * <p>
 * Copyright 2012-2015 the original author or authors.
 */
package org.assertj.db.api;

import org.assertj.db.type.Table;

/**
 * Assertion methods for a {@link Table}.
 *
 * @author Régis Pouiller
 *
 */
public class TableAssert
    extends
    AbstractDbAssert<Table, TableAssert, TableColumnAssert, TableColumnValueAssert, TableRowAssert, TableRowValueAssert> {

    /**
     * Constructor.
     *
     * @param table Table on which the assertion is.
     */
    TableAssert(Table table) {
        super(table, TableAssert.class, TableColumnAssert.class, TableRowAssert.class);
    }
}
