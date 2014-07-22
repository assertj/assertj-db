package org.assertj.db.api;

import org.assertj.db.type.Row;
import org.assertj.db.type.Table;

/**
 * Assertion methods about a value in a {@link Row} of a {@link Table}.
 * 
 * @author Régis Pouiller
 * 
 */
public class TableRowValueAssert extends
    AbstractRowValueAssert<Table, TableAssert, TableColumnAssert, TableColumnValueAssert, TableRowAssert, TableRowValueAssert> {

  /**
   * Constructor.
   * 
   * @param originalAssert The original assert.
   * @param value The value to assert.
   */
  TableRowValueAssert(TableRowAssert originalAssert, Object value) {
    super(originalAssert, TableRowValueAssert.class, value);
  }

  /**
   * Returns to level of assertion methods on a {@link Row}.
   * 
   * @return a object of assertion methods on a {@link Row}.
   */
  public TableRowAssert returnToRow() {
    return returnToSubAssert();
  }

}
