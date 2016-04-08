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

import org.assertj.db.output.impl.Output;
import org.assertj.db.type.Table;

/**
 * Output methods for a {@link org.assertj.db.type.Table}.
 *
 * @author Régis Pouiller
 *
 */
public class TableOutputter extends
        AbstractDbOutputter<Table, TableOutputter, TableColumnOutputter, TableColumnValueOutputter, TableRowOutputter, TableRowValueOutputter> {

  /**
   * Constructor.
   *
   * @param table Table on which the output is.
   */
  TableOutputter(Table table) {
    super(table, TableOutputter.class, TableColumnOutputter.class, TableRowOutputter.class);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getOutput(Output outputType) {
    return outputType.getTableOutput(info, actual);
  }
}
