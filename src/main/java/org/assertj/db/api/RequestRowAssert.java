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
import org.assertj.db.type.Row;
import org.assertj.db.type.Table;

/**
 * Assertion methods about the data in a {@link Row} of a {@link Table}.
 * 
 * @author Régis Pouiller
 * 
 */
public class RequestRowAssert extends
    AbstractRowAssert<Request, RequestAssert, RequestColumnAssert, RequestColumnValueAssert, RequestRowAssert, RequestRowValueAssert> {

  /**
   * Constructor.
   * 
   * @param originalRequestAssert The original assertion ({@link RequestAssert}).
   * @param row The row on which do assertion.
   */
  RequestRowAssert(RequestAssert originalRequestAssert, Row row) {
    super(originalRequestAssert, RequestRowAssert.class, RequestRowValueAssert.class, row);
  }

  /**
   * Returns to level of assertion methods on a {@link Request}.
   * 
   * @return a object of assertion methods on a {@link Request}.
   */
  public RequestAssert returnToRequest() {
    return returnToOriginAssert();
  }

}
