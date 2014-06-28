package org.assertj.db.api;

import org.assertj.db.type.Column;
import org.assertj.db.type.Table;

/**
 * Assertion methods about a value in a {@link Column} of a {@link Table}.
 * 
 * @author Régis Pouiller
 * 
 */
public class TableColumnValueAssert extends
    AbstractValueAssert<TableAssert, Table, TableColumnAssert, TableColumnValueAssert> {

  /**
   * Constructor.
   * 
   * @param originalAssert The original assert.
   * @param value The value to assert.
   */
  TableColumnValueAssert(TableColumnAssert originalAssert, Object value) {
    super(originalAssert, TableColumnValueAssert.class, value);
  }

  /**
   * Returns to level of assertion methods on a {@link Column}.
   * 
   * @return a object of assertion methods on a {@link Column}.
   */
  public TableColumnAssert returnToRow() {
    return returnToSubAssert();
  }

}
