package org.assertj.db.api;

import org.assertj.db.type.Column;
import org.assertj.db.type.Request;

/**
 * Assertion methods about the data in a {@link Column} of a {@link Request}.
 * 
 * @author Régis Pouiller
 * 
 */
public class RequestColumnAssert extends
    AbstractColumnAssert<Request, RequestAssert, RequestColumnAssert, RequestColumnValueAssert> {

  /**
   * Constructor.
   * 
   * @param originalRequestAssert The original assert ({@link RequestAssert}).
   * @param column The column on which do assertion.
   */
  RequestColumnAssert(RequestAssert originalRequestAssert, Column column) {
    super(originalRequestAssert, RequestColumnAssert.class, RequestColumnValueAssert.class, column);
  }

  /**
   * Returns to level of assertion methods on a {@link Request}.
   * 
   * @return a object of assertion methods on a {@link Request}.
   */
  public RequestAssert returnToRequest() {
    return returnToDbAssert();
  }

}