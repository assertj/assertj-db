package org.assertj.db.api;

import org.assertj.db.type.Column;
import org.assertj.db.type.Table;

/**
 * Assertion methods about the data in a {@link Column} of a {@link Table}.
 * 
 * @author Régis Pouiller
 * 
 */
public class TableColumnAssert extends
    AbstractColumnAssert<Table, TableAssert, TableColumnAssert, TableColumnValueAssert> {

  /**
   * Constructor.
   * 
   * @param originalTableAssert The original assert ({@link TableAssert}).
   * @param column The column on which do assertion.
   */
  TableColumnAssert(TableAssert originalTableAssert, Column column) {
    super(originalTableAssert, TableColumnAssert.class, TableColumnValueAssert.class, column);
  }

  /**
   * Returns to level of assertion methods on a {@link Table}.
   * 
   * @return a object of assertion methods on a {@link Table}.
   */
  public TableAssert returnToTable() {
    return returnToDbAssert();
  }

}
