package org.assertj.db.api;

import org.assertj.db.type.Request;

/**
 * Assertion methods about the result of a <code>{@link Request}</code>.
 * 
 * @author Régis Pouiller
 * 
 */
public class RequestAssert extends AbstractDbAssert<Request, RequestAssert, RequestColumnAssert, RequestColumnValueAssert, RequestRowAssert, RequestRowValueAssert> {

  /**
   * Constructor.
   * 
   * @param request Request on which the assertion is.
   */
  RequestAssert(Request request) {
    super(request, RequestAssert.class, RequestColumnAssert.class, RequestRowAssert.class);
  }
}
