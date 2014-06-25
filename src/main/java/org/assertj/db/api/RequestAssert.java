package org.assertj.db.api;

import org.assertj.db.type.Request;

/**
 * Assertion about the result of a <code>{@link Request}</code>.
 * 
 * @author Régis Pouiller
 * 
 */
public class RequestAssert extends AbstractDbAssert<RequestAssert, Request> {

  /**
   * Constructor of the assertion.
   * 
   * @param request Request on which the assertion is.
   */
  RequestAssert(Request request) {
    super(request, RequestAssert.class);
  }
}
