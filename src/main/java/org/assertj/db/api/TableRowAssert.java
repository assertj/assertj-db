package org.assertj.db.api;

import org.assertj.db.type.Row;
import org.assertj.db.type.Table;

/**
 * Assertion methods about the data in a {@link Row} of a {@link Table}.
 * 
 * @author Régis Pouiller
 * 
 */
public class TableRowAssert extends RowAssert<TableAssert, Table, TableRowAssert> {

  /**
   * Constructor.
   * 
   * @param originalTableAssert The original assert ({@link TableAssert}).
   * @param row The row on which do assertion.
   */
  TableRowAssert(TableAssert originalTableAssert, Row row) {
    super(originalTableAssert, TableRowAssert.class, row);
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
