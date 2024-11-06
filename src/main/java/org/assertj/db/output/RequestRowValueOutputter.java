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
 * Copyright 2015-2024 the original author or authors.
 */
package org.assertj.db.output;

import org.assertj.db.type.Request;
import org.assertj.db.type.Row;
import org.assertj.db.type.Value;

/**
 * Output methods for a value in a {@link Row} of a {@link Request}.
 *
 * @author Régis Pouiller
 */
public class RequestRowValueOutputter
  extends
  AbstractRowValueOutputter<Request, RequestOutputter, RequestColumnOutputter, RequestColumnValueOutputter, RequestRowOutputter, RequestRowValueOutputter> {

  /**
   * Constructor.
   *
   * @param origin The assertion of {@link org.assertj.db.navigation.origin.Origin}.
   * @param value  The value to assert.
   */
  public RequestRowValueOutputter(RequestRowOutputter origin, Value value) {
    super(RequestRowValueOutputter.class, origin, value);
  }
}
