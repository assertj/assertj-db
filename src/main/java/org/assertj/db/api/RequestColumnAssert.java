package org.assertj.db.api;

import org.assertj.db.error.AssertJDBException;
import org.assertj.db.type.Column;
import org.assertj.db.type.Request;

/**
 * Assertion methods about the data in a {@link Column} of a {@link Request}.
 * 
 * @author Régis Pouiller
 * 
 */
public class RequestColumnAssert extends AbstractColumnAssert<RequestAssert, Request, RequestColumnAssert> {

  /**
   * Constructor.
   * 
   * @param originalRequestAssert The original assert ({@link RequestAssert}).
   * @param column The column on which do assertion.
   */
  RequestColumnAssert(RequestAssert originalRequestAssert, Column column) {
    super(originalRequestAssert, RequestColumnAssert.class, column);
  }

  /**
   * Returns to level of assertion methods on a {@link Request}.
   * 
   * @return a object of assertion methods on a {@link Request}.
   */
  public RequestAssert returnToRequest() {
    return returnToDbAssert();
  }

  /**
   * Returns assertion methods on the next value in the list of value.
   * 
   * @return An object to make assertions on the next value.
   * @throws AssertJDBException If the {@code index} is out of the bounds.
   */
  public RequestColumnValueAssert value() {
    return new RequestColumnValueAssert(this, getValue());
  }

  /**
   * Returns assertion methods on the value at the {@code index} in parameter.
   * 
   * @param index The index corresponding to the value.
   * @return An object to make assertions on the value.
   * @throws AssertJDBException If the {@code index} is out of the bounds.
   */
  public RequestColumnValueAssert value(int index) {
    return new RequestColumnValueAssert(this, getValue(index));
  }

}