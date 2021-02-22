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
package org.assertj.db.output;

import org.assertj.db.type.Request;
import org.assertj.db.type.Row;
import org.assertj.db.type.Table;

/**
 * Output methods for a {@link Row} of a {@link Table}.
 *
 * @author Régis Pouiller
 *
 */
public class RequestRowOutputter
        extends
        AbstractRowOutputter<Request, RequestOutputter, RequestColumnOutputter, RequestColumnValueOutputter, RequestRowOutputter, RequestRowValueOutputter> {

  /**
   * Constructor.
   *
   * @param origin The assertion of {@link org.assertj.db.navigation.origin.Origin}.
   * @param row The row on which do assertion.
   */
  public RequestRowOutputter(RequestOutputter origin, Row row) {
    super(origin, RequestRowOutputter.class, RequestRowValueOutputter.class, row);
  }

  /**
   * Returns to level of assertion methods on a {@link Request}.
   *
   * @return a object of assertion methods on a {@link Request}.
   */
  public RequestOutputter returnToRequest() {
    return returnToOrigin();
  }
}
