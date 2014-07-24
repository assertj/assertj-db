package org.assertj.db.api;

import org.assertj.db.error.AssertJDBException;
import org.assertj.db.type.Row;
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
    super(table, TableAssert.class);
  }

  /**
   * Returns assertion methods on the next {@link Row} in the list of {@link Row}.
   * 
   * @return An object to make assertions on the next {@link Row}.
   * @throws AssertJDBException If the {@code index} is out of the bounds.
   */
  public TableRowAssert row() {
    return new TableRowAssert(this, getRow());
  }

  /**
   * Returns assertion methods on the {@link Row} at the {@code index} in parameter.
   * 
   * @param index The index corresponding to the {@link Row}.
   * @return An object to make assertions on the {@link Row}.
   * @throws AssertJDBException If the {@code index} is out of the bounds.
   */
  public TableRowAssert row(int index) {
    return new TableRowAssert(this, getRow(index));
  }

  /**
   * Returns assertion methods on the next {@link Column} in the list of {@link Column}.
   * 
   * @return An object to make assertions on the next {@link Column}.
   * @throws AssertJDBException If the {@code index} is out of the bounds.
   */
  public TableColumnAssert column() {
    return new TableColumnAssert(this, getColumn());
  }

  /**
   * Returns assertion methods on the {@link Column} at the {@code index} in parameter.
   * 
   * @param index The index corresponding to the {@link Column}.
   * @return An object to make assertions on the {@link Column}.
   * @throws AssertJDBException If the {@code index} is out of the bounds.
   */
  public TableColumnAssert column(int index) {
    return new TableColumnAssert(this, getColumn(index));
  }

  /**
   * Returns assertion methods on the {@link Column} corresponding to the column name in parameter.
   * 
   * @param columnName The column name.
   * @return An object to make assertions on the {@link Column}.
   * @throws NullPointerException If the column name in parameter is null.
   * @throws AssertJDBException If there is no column with this name.
   */
  public TableColumnAssert column(String columnName) {
    return new TableColumnAssert(this, getColumn(columnName));
  }
}
