package org.assertj.db.api;

import org.assertj.db.error.AssertJDBException;
import org.assertj.db.type.Row;
import org.assertj.db.type.Table;

/**
 * Assertion methods about a value in a {@link Row} of a {@link Table}.
 * 
 * @author Régis Pouiller
 * 
 */
public class TableRowValueAssert extends AbstractValueAssert<TableAssert, Table, TableRowAssert, TableRowValueAssert> {

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

  /** {@inheritDoc} */
  @Override
  public TableRowValueAssert value() {
    return returnToRow().value();
  }

  /** {@inheritDoc} */
  @Override
  public TableRowValueAssert value(int index) {
    return returnToRow().value(index);
  }

  /**
   * Returns assertion methods on the value corresponding to the column name in parameter in the list of value of the
   * original assertion.
   * 
   * @param columnName The column name.
   * @return An object to make assertions on the value.
   * @throws NullPointerException If the column name in parameter is null.
   * @throws AssertJDBException If there is no column with this name.
   */
  public TableRowValueAssert value(String columnName) {
    return returnToRow().value(columnName);
  }

}
