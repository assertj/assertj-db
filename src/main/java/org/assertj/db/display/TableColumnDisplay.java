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
package org.assertj.db.display;

import org.assertj.db.type.Column;
import org.assertj.db.type.Table;

/**
 * Display methods for a {@link Column} of a {@link Table}.
 *
 * @author Régis Pouiller
 *
 */
public class TableColumnDisplay
        extends
        AbstractColumnDisplay<Table, TableDisplay, TableColumnDisplay, TableColumnValueDisplay, TableRowDisplay, TableRowValueDisplay> {

  /**
   * Constructor.
   *
   * @param origin The assertion of {@link org.assertj.db.navigation.origin.Origin}.
   * @param column The column on which do assertion.
   */
  public TableColumnDisplay(TableDisplay origin, Column column) {
    super(origin, TableColumnDisplay.class, TableColumnValueDisplay.class, column);
  }

  /**
   * Returns to level of assertion methods on a {@link Table}.
   *
   * @return a object of assertion methods on a {@link Table}.
   */
  public TableDisplay returnToTable() {
    return returnToOrigin();
  }
}
