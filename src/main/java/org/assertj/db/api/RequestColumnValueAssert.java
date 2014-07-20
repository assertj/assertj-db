package org.assertj.db.api;

import org.assertj.db.type.Column;
import org.assertj.db.type.Request;

/**
 * Assertion methods about a value in a {@link Column} of a {@link Request}.
 * 
 * @author Régis Pouiller
 * 
 */
public class RequestColumnValueAssert extends
    AbstractColumnValueAssert<RequestAssert, Request, RequestColumnAssert, RequestColumnValueAssert> {

  /**
   * Constructor.
   * 
   * @param originalAssert The original assert.
   * @param value The value to assert.
   */
  RequestColumnValueAssert(RequestColumnAssert originalAssert, Object value) {
    super(originalAssert, RequestColumnValueAssert.class, value);
  }

  /**
   * Returns to level of assertion methods on a {@link Column}.
   * 
   * @return a object of assertion methods on a {@link Column}.
   */
  public RequestColumnAssert returnToColumn() {
    return returnToSubAssert();
  }

}
