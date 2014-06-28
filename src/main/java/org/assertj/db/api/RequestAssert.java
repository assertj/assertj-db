package org.assertj.db.api;

import org.assertj.db.error.AssertJDBException;
import org.assertj.db.type.Request;
import org.assertj.db.type.Row;

/**
 * Assertion methods about the result of a <code>{@link Request}</code>.
 * 
 * @author Régis Pouiller
 * 
 */
public class RequestAssert extends AbstractDbAssert<RequestAssert, Request> {

  /**
   * Constructor.
   * 
   * @param request Request on which the assertion is.
   */
  RequestAssert(Request request) {
    super(request, RequestAssert.class);
  }

  /**
   * Returns assertion methods on the next {@link Row} in the list of {@link Row}.
   * 
   * @return An object to make assertions on the next {@link Row}.
   * @throws AssertJDBException If the {@code index} is out of the bounds.
   */
  public RequestRowAssert row() {
    return new RequestRowAssert(this, getRow());
  }

  /**
   * Returns assertion methods on the {@link Row} at the {@code index} in parameter.
   * 
   * @param index The index corresponding to the {@link Row}.
   * @return An object to make assertions on the {@link Row}.
   * @throws AssertJDBException If the {@code index} is out of the bounds.
   */
  public RequestRowAssert row(int index) {
    return new RequestRowAssert(this, getRow(index));
  }

  /**
   * Returns assertion methods on the next {@link Column} in the list of {@link Column}.
   * 
   * @return An object to make assertions on the next {@link Column}.
   * @throws AssertJDBException If the {@code index} is out of the bounds.
   */
  public RequestColumnAssert column() {
    return new RequestColumnAssert(this, getColumn());
  }

  /**
   * Returns assertion methods on the {@link Column} at the {@code index} in parameter.
   * 
   * @param index The index corresponding to the {@link Column}.
   * @return An object to make assertions on the {@link Column}.
   * @throws AssertJDBException If the {@code index} is out of the bounds.
   */
  public RequestColumnAssert column(int index) {
    return new RequestColumnAssert(this, getColumn(index));
  }

  /**
   * Returns assertion methods on the {@link Column} corresponding to the column name in parameter.
   * 
   * @param columnName The column name.
   * @return An object to make assertions on the {@link Column}.
   * @throws NullPointerException If the column name in parameter is null.
   * @throws AssertJDBException If there is no column with this name.
   */
  public RequestColumnAssert column(String columnName) {
    return new RequestColumnAssert(this, getColumn(columnName));
  }
}
