package org.assertj.db.api;

import org.assertj.db.type.Table;

/**
 * Assertion methods about the data in a {@link Table}.
 * 
 * @author Régis Pouiller
 * 
 */
public class TableAssert extends AbstractDbAssert<Table, TableAssert, TableColumnAssert, TableColumnValueAssert, TableRowAssert, TableRowValueAssert> {

  /**
   * Constructor.
   * 
   * @param table Table on which the assertion is.
   */
  TableAssert(Table table) {
    super(table, TableAssert.class, TableColumnAssert.class, TableRowAssert.class);
  }
}
