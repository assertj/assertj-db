/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2014 the original author or authors.
 */
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
