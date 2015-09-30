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

import org.assertj.db.display.impl.RepresentationType;
import org.assertj.db.type.Table;

/**
 * Assertion methods for a {@link org.assertj.db.type.Table}.
 *
 * @author Régis Pouiller
 *
 */
public class TableDisplay extends AbstractDisplay<Table, TableDisplay> {

  /**
   * Table on which the display is.
   */
  private Table table;

  /**
   * Constructor.
   *
   * @param table Table on which the display is.
   */
  TableDisplay(Table table) {
    super(TableDisplay.class, RepresentationType.PLAIN);
    this.table = table;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getRepresentation(RepresentationType displayType) {
    return displayType.getTableRepresentation(info, table);
  }
}
