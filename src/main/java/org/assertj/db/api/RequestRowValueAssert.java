package org.assertj.db.api;

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

}
