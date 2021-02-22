/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2015-2021 the original author or authors.
 */
package org.assertj.db.api;

import org.assertj.db.type.Column;
import org.assertj.db.type.Request;

/**
 * Assertion methods for a {@link Column} of a {@link Request}.
 * 
 * @author Régis Pouiller
 * 
 */
public class RequestColumnAssert
        extends AbstractColumnAssert<Request, RequestAssert, RequestColumnAssert, RequestColumnValueAssert, RequestRowAssert, RequestRowValueAssert> {

  /**
   * Constructor.
   * 
   * @param origin The assertion of {@link org.assertj.db.navigation.origin.Origin}.
   * @param column The column on which do assertion.
   */
  public RequestColumnAssert(RequestAssert origin, Column column) {
    super(origin, RequestColumnAssert.class, RequestColumnValueAssert.class, column);
  }

  /**
   * Returns to level of assertion methods on a {@link Request}.
   * 
   * @return a object of assertion methods on a {@link Request}.
   */
  public RequestAssert returnToRequest() {
    return returnToOrigin();
  }
}