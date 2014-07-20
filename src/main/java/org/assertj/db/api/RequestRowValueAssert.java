package org.assertj.db.api;

import org.assertj.db.error.AssertJDBException;
import org.assertj.db.type.Request;
import org.assertj.db.type.Row;

/**
 * Assertion methods about a value in a {@link Row} of a {@link Request}.
 * 
 * @author Régis Pouiller
 * 
 */
public class RequestRowValueAssert extends
    AbstractValueAssert<RequestAssert, Request, RequestRowAssert, RequestRowValueAssert> {

  /**
   * Constructor.
   * 
   * @param originalAssert The original assert.
   * @param value The value to assert.
   */
  RequestRowValueAssert(RequestRowAssert originalAssert, Object value) {
    super(originalAssert, RequestRowValueAssert.class, value);
  }

  /**
   * Returns to level of assertion methods on a {@link Row}.
   * 
   * @return a object of assertion methods on a {@link Row}.
   */
  public RequestRowAssert returnToRow() {
    return returnToSubAssert();
  }

  /** {@inheritDoc} */
  @Override
  public RequestRowValueAssert value() {
    return returnToRow().value();
  }

  /** {@inheritDoc} */
  @Override
  public RequestRowValueAssert value(int index) {
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
  public RequestRowValueAssert value(String columnName) {
    return returnToRow().value(columnName);
  }

}
