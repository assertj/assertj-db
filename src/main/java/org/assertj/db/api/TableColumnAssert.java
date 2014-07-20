package org.assertj.db.api;

import org.assertj.db.error.AssertJDBException;
import org.assertj.db.type.Column;
import org.assertj.db.type.Table;

/**
 * Assertion methods about the data in a {@link Column} of a {@link Table}.
 * 
 * @author Régis Pouiller
 * 
 */
public class TableColumnAssert extends
    AbstractColumnAssert<TableAssert, Table, TableColumnAssert, TableColumnValueAssert> {

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

  /**
   * Returns assertion methods on the next value in the list of value.
   * 
   * @return An object to make assertions on the next value.
   * @throws AssertJDBException If the {@code index} is out of the bounds.
   */
  public TableColumnValueAssert value() {
    return new TableColumnValueAssert(this, getValue());
  }

  /**
   * Returns assertion methods on the value at the {@code index} in parameter.
   * 
   * @param index The index corresponding to the value.
   * @return An object to make assertions on the value.
   * @throws AssertJDBException If the {@code index} is out of the bounds.
   */
  public TableColumnValueAssert value(int index) {
    return new TableColumnValueAssert(this, getValue(index));
  }

}
